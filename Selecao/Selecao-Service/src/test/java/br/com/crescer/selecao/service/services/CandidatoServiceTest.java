package br.com.crescer.selecao.service.services;

import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.service.repository.CandidatoRepository;
import br.com.crescer.selecao.service.repository.InformacaoRepository;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 * @author Murillo
 */
public class CandidatoServiceTest {
    
    @InjectMocks
    private CandidatoService candidatoService;

    @Mock
    private InformacaoRepository informacaoRepository;

    @Mock
    private CandidatoRepository candidatoRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        Iterable<Candidato> candidatos = new ArrayList<>();
        when(candidatoRepository.save(new Candidato())).thenReturn(new Candidato());
        when(candidatoRepository.findByStatus("StatusValido")).thenReturn(candidatos);
        when(candidatoRepository.findByStatus("StatusInvalido")).thenReturn(null);
        when(candidatoRepository.save(new Candidato())).thenReturn(null);
    }
//    
//    @Test
//    public void metodoSaveRecebeNullERetornaNull() {
//        Candidato candidato = null;
//        assertTrue(candidatoService.saveCandidato(candidato) == null);
//    }
    
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
}
