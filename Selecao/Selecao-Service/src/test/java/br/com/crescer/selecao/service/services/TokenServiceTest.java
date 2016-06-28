package br.com.crescer.selecao.service.services;

import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.entities.Token;
import br.com.crescer.selecao.service.repository.CandidatoRepository;
import br.com.crescer.selecao.service.repository.TokenRepository;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 * @author murillo.peteffi
 */
public class TokenServiceTest {
    
    @InjectMocks
    private TokenService tokenService;

    @Mock
    private TokenRepository tokenRepository;

    @Mock
    private CandidatoRepository candidatoRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        when(tokenRepository.save(new Token())).thenReturn(null);
    }
    
    @Test
    public void newTokenRetornaStringValida() {
        Candidato candidato = new Candidato();
//        Token token = new Token();
//        when(tokenRepository.save(token)).thenReturn(null);
        String codigo = tokenService.newTokenForCandidato(candidato);
        assertTrue(codigo != null);
    }
    
    @Test(expected=NullPointerException.class)
    public void newTokenRetornaNullPointerExceptionParaCandidatoNull() {
        Candidato candidato = null;
//        Token token = new Token();
//        when(tokenRepository.save(token)).thenReturn(null);
        tokenService.newTokenForCandidato(candidato);
    }
    
    @Test
    public void newTokenRetornaChavesDiferentesParaCandidatosDiferentes() {
        Candidato candidato1 = new Candidato();
        Candidato candidato2 = new Candidato();
        Candidato candidato3 = new Candidato();
//        Token token = new Token();
//        when(tokenRepository.save(token)).thenReturn(null);
        String codigo1 = tokenService.newTokenForCandidato(candidato1);
        String codigo2 = tokenService.newTokenForCandidato(candidato2);
        String codigo3 = tokenService.newTokenForCandidato(candidato3);
        assertTrue(!codigo1.equals(codigo2) && !codigo2.equals(codigo3) && !codigo3.equals(codigo1));
    }
    
    @Test
    public void newTokenRetornaChavesDiferentesParaMesmoCandidato() {
        Candidato candidato1 = new Candidato();
//        Token token = new Token();
//        when(tokenRepository.save(token)).thenReturn(null);
        String codigo1 = tokenService.newTokenForCandidato(candidato1);
        String codigo2 = tokenService.newTokenForCandidato(candidato1);
        String codigo3 = tokenService.newTokenForCandidato(candidato1);
        assertTrue(!codigo1.equals(codigo2) && !codigo2.equals(codigo3) && !codigo3.equals(codigo1));
    }

    
//    @Test
//    public void testConfirmarInteresse() {
//        System.out.println("confirmarInteresse");
//        String codigo = "";
//        TokenService instance = new TokenService();
//        boolean expResult = false;
//        boolean result = instance.confirmarInteresse(codigo);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
