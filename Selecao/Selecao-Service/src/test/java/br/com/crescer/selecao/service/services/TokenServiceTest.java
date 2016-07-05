package br.com.crescer.selecao.service.services;

import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.entities.Token;
import br.com.crescer.selecao.entities.enums.StatusToken;
import br.com.crescer.selecao.entities.enums.TipoToken;
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

    @Mock TokenRepository tokenRepository;
    @Mock CandidatoRepository candidatoRepository;
    
    @Mock Token token;
    @Mock Candidato candidato;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        String codigo = "CodigoValido";
        when(tokenRepository.save(token)).thenReturn(null);
        when(tokenRepository.findOneByTokenAndStatusAndTipo(null, StatusToken.PENDENTE, TipoToken.INTERESSE)).thenReturn(null);
        when(tokenRepository.findOneByTokenAndStatusAndTipo("CodigoInvalido", StatusToken.PENDENTE, TipoToken.INTERESSE)).thenReturn(null);
        when(tokenRepository.findOneByTokenAndStatusAndTipo(codigo, StatusToken.PENDENTE, TipoToken.INTERESSE)).thenReturn(new Token(1, "", StatusToken.FINAL, TipoToken.INSCRICAO));
        when(candidatoRepository.save(candidato)).thenReturn(null);
        when(candidatoRepository.findOneByIdCandidato(1)).thenReturn(new Candidato());
    }
    
    @Test
    public void newTokenRetornaStringValida() {
        String codigo = tokenService.criarTokenParaCandidato(candidato);
        assertTrue(codigo != null && !codigo.isEmpty());
    }
    
    @Test(expected=NullPointerException.class)
    public void newTokenRetornaNullPointerExceptionParaCandidatoNull() {
        Candidato c = null;
        tokenService.criarTokenParaCandidato(c);
    }
    
    @Test
    public void newTokenRetornaChavesDiferentesParaCandidatosDiferentes() {
        Candidato candidato1 = new Candidato();
        Candidato candidato2 = new Candidato();
        Candidato candidato3 = new Candidato();
        String codigo1 = tokenService.criarTokenParaCandidato(candidato1);
        String codigo2 = tokenService.criarTokenParaCandidato(candidato2);
        String codigo3 = tokenService.criarTokenParaCandidato(candidato3);
        assertTrue(!codigo1.equals(codigo2) && !codigo2.equals(codigo3) && !codigo3.equals(codigo1));
    }
    
    @Test
    public void newTokenRetornaChavesDiferentesParaMesmoCandidato() {
        Candidato candidato1 = new Candidato();
        String codigo1 = tokenService.criarTokenParaCandidato(candidato1);
        String codigo2 = tokenService.criarTokenParaCandidato(candidato1);
        String codigo3 = tokenService.criarTokenParaCandidato(candidato1);
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
        boolean confirmado = tokenService.confirmarInteresse(null);
        assertFalse(confirmado);
    }
    
    @Test
    public void confirmarCandidaturaComTokenValidoRetornaCandidato() {
        String codigo = "CodigoValido";
        Candidato c = tokenService.confirmarCandidatura(codigo);
        assertTrue(c != null);
    }
    
    @Test
    public void confirmarCandidaturaComTokenInvalidoRetornaNull() {
        String codigo = "CodigoInvalido";
        Candidato c = tokenService.confirmarCandidatura(codigo);
        assertTrue(c == null);
    }
    
    @Test
    public void confirmarCandidaturaComTokenNullRetornaNull() {
        Candidato c =  tokenService.confirmarCandidatura(null);
        assertTrue(c == null);
    }
}
