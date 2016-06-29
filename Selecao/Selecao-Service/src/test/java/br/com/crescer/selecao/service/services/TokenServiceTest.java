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
        String codigo = "CodigoValido";
        when(tokenRepository.save(new Token())).thenReturn(null);
        when(tokenRepository.findOneByTokenAndStatusAndTipo(null, "PENDENTE", "INTERESSADO")).thenReturn(null);
        when(tokenRepository.findOneByTokenAndStatusAndTipo("CodigoInvalido", "PENDENTE", "INTERESSADO")).thenReturn(null);
        when(tokenRepository.findOneByTokenAndStatusAndTipo(codigo, "PENDENTE", "INTERESSADO")).thenReturn(new Token(1, codigo, codigo, codigo));
        when(candidatoRepository.save(new Candidato())).thenReturn(null);
        when(candidatoRepository.findOneByIdcandidato(1)).thenReturn(new Candidato());
    }
    
    @Test
    public void newTokenRetornaStringValida() {
        Candidato candidato = new Candidato();
        String codigo = tokenService.newTokenForCandidato(candidato);
        assertTrue(codigo != null && !codigo.isEmpty());
    }
    
    @Test(expected=NullPointerException.class)
    public void newTokenRetornaNullPointerExceptionParaCandidatoNull() {
        Candidato candidato = null;
        tokenService.newTokenForCandidato(candidato);
    }
    
    @Test
    public void newTokenRetornaChavesDiferentesParaCandidatosDiferentes() {
        Candidato candidato1 = new Candidato();
        Candidato candidato2 = new Candidato();
        Candidato candidato3 = new Candidato();
        String codigo1 = tokenService.newTokenForCandidato(candidato1);
        String codigo2 = tokenService.newTokenForCandidato(candidato2);
        String codigo3 = tokenService.newTokenForCandidato(candidato3);
        assertTrue(!codigo1.equals(codigo2) && !codigo2.equals(codigo3) && !codigo3.equals(codigo1));
    }
    
    @Test
    public void newTokenRetornaChavesDiferentesParaMesmoCandidato() {
        Candidato candidato1 = new Candidato();
        String codigo1 = tokenService.newTokenForCandidato(candidato1);
        String codigo2 = tokenService.newTokenForCandidato(candidato1);
        String codigo3 = tokenService.newTokenForCandidato(candidato1);
        assertTrue(!codigo1.equals(codigo2) && !codigo2.equals(codigo3) && !codigo3.equals(codigo1));
    }
    
    @Test
    public void confirmarInteresseComTokenValidoRetornaTrue() {
        String codigo = "CodigoValido";
        boolean confirmado = tokenService.confirmarInteresse(codigo);
        assertTrue(confirmado);
    }
    
    @Test
    public void confirmarInteresseComTokenInvalidoRetornaFalso() {
        String codigo = "CodigoInvalido";
        boolean confirmado = tokenService.confirmarInteresse(codigo);
        assertFalse(confirmado);
    }
    
    @Test
    public void confirmarInteresseComTokenNullRetornaFalse() {
        boolean confirmado = tokenService.confirmarInteresse("CodigoInvalido");
        assertFalse(confirmado);
    }
}
