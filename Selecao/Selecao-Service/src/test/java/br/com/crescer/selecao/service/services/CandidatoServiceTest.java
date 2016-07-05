package br.com.crescer.selecao.service.services;

import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.entities.Informacao;
import br.com.crescer.selecao.entities.Processoseletivo;
import br.com.crescer.selecao.service.repository.CandidatoRepository;
import br.com.crescer.selecao.service.repository.InformacaoRepository;
import java.util.ArrayList;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 * @author Murillo
 */
public class CandidatoServiceTest {
    
    @InjectMocks
    private CandidatoService candidatoService;

    @Mock InformacaoRepository informacaoRepository;
    @Mock EmailService emailService;
    @Mock CandidatoRepository candidatoRepository;
    @Mock Processoseletivo processoseletivo;
    @Mock Informacao informacao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        Iterable<Candidato> candidatos = new ArrayList<>();
        when(candidatoRepository.save(any(Candidato.class))).thenReturn(new Candidato());
        when(candidatoRepository.findByStatus("StatusValido")).thenReturn(candidatos);
        when(candidatoRepository.findByStatus("StatusInvalido")).thenReturn(null);
        when(candidatoRepository.save(new Candidato())).thenReturn(null);
        when(candidatoRepository.findOneByIdCandidato(1)).thenReturn(new Candidato(1));
        when(candidatoRepository.findOneByIdCandidato(0)).thenReturn(new Candidato());
        when(informacaoRepository.save(any(Informacao.class))).thenReturn(new Informacao());
    }
    
    @Test(expected=NullPointerException.class)
    public void metodoSaveRecebeNullERetornaExceptionNullPointer() {
        Candidato candidato = null;
        assertTrue(candidatoService.salvarCandidato(candidato) == null);
    }
    
    @Test
    public void metodoSaveRecebeCandidatoValidoERetornaCandidato() {
        Candidato candidato = new Candidato();
        assertTrue(candidatoService.salvarCandidato(candidato) != null);
    }
    
    @Test
    public void findByStatusValidoRetornaIterable() {
        Iterable<Candidato> candidatos = candidatoService.buscarCandidatosPorStatus("StatusValido");
        assertTrue(candidatos != null);
    }
    
    @Test
    public void findByStatusInalidoRetornaNull() {
        Iterable<Candidato> candidatos = candidatoService.buscarCandidatosPorStatus("StatusInvalido");
        assertTrue(candidatos == null);
    }
    
    @Test (expected=NullPointerException.class)
    public void salvarInformacaoRecebeInformacaoNullRetornaException() {
        Informacao info = null;
        Processoseletivo processo = new Processoseletivo();
        candidatoService.salvarInformacao(info, processo);
    }
    
    @Test (expected=NullPointerException.class)
    public void salvarInformacaoRecebeProcessoNullEInfoVazioRetornaException() {
        Informacao info = new Informacao();
        Processoseletivo processo = null;
        candidatoService.salvarInformacao(info, processo);
    }

    @Test 
    public void salvarInformacaoRecebeProcessoNullEInfoPopuladaRetornaValido() {
        Candidato candidato = new Candidato();
        Informacao info = new Informacao("", new Date(), "", "", candidato);
        info.setSenha("abc123");
        Processoseletivo processo = null;
        assertTrue(candidatoService.salvarInformacao(info, processo) != null);
    }

    @Test 
    public void salvarInformacaoRecebeProcessoVazioEInfoPopuladaRetornaValido() {
        Candidato candidato = new Candidato();
        Informacao info = new Informacao("", new Date(), "", "", candidato);
        info.setSenha("abc123");
        Processoseletivo processo = new Processoseletivo();
        assertTrue(candidatoService.salvarInformacao(info, processo) != null);
    }
    
    @Test 
    public void BuscarCandidatoPorIdUmRetornaOk() {
        Candidato c = candidatoService.buscarCandidatoPorId(1);
        assertTrue(c.getIdCandidato().equals(1));
    }
    
    @Test 
    public void BuscarCandidatoPorIdZeroRetornaOk() {
        Candidato c = candidatoService.buscarCandidatoPorId(1);
        assertTrue(c != null);
    }
    
    @Test 
    public void BuscarCandidatoPorIdNullRetornaZero() {
        Candidato c = candidatoService.buscarCandidatoPorId(null);
        assertTrue(c != null);
    }
}
