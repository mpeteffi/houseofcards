package br.com.crescer.selecao.service.services;

import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.entities.Processoseletivo;
import br.com.crescer.selecao.service.repository.ProcessoseletivoRepository;
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
public class ProcessoseletivoServiceTest {
    
    @InjectMocks
    private ProcessoseletivoService processoseletivoService;
    
    @Mock
    private CandidatoService candidatoService;
    
    @Mock
    private ProcessoseletivoRepository processoseletivoRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        Iterable<Candidato> candidatos = new ArrayList<>();
        Processoseletivo processoValido = new Processoseletivo();
        Processoseletivo processoNull = null;
        when(candidatoService.buscarCandidatosPorStatus("INTERESSADO")).thenReturn(candidatos);
        when(processoseletivoRepository.save(processoNull)).thenThrow(NullPointerException.class);
        when(processoseletivoRepository.save(processoValido)).thenReturn(new Processoseletivo());
        when(processoseletivoRepository.findTopByOrderByEdicaoDesc()).thenReturn(new Processoseletivo());
    }
   
    @Test
    public void findTopRetornaProcessoValido() {
        Processoseletivo processo = processoseletivoService.buscarProcessoAtual();
        assertTrue(processo != null);
    } 
    
//    @Test
//    public void salvarProcessoValidoRetornaTrue() {
//        boolean save = processoseletivoService.save(new Processoseletivo()) ;
//        assertTrue(save);
//    } 
//    
//    @Test
//    public void salvarProcessoInvalidoRetornaFalse() {
//        boolean save = processoseletivoService.save(null);
//        assertFalse(save);
//    } 
}
