package br.com.crescer.selecao.service.services;

import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.entities.Processoseletivo;
import br.com.crescer.selecao.service.repository.ProcessoseletivoRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
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
    
    @Test
    public void salvarProcessoValidoRetornaTrue() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Processoseletivo valido = new Processoseletivo("2016/02", format.parse("01/08/2016"), format.parse("02/08/2016"), format.parse("03/08/2016"), format.parse("04/08/2016"));
        boolean save = processoseletivoService.criarProcessoSeletivo(valido);
        assertTrue(save);
    } 
    
    @Test
    public void salvarProcessoInvalidoRetornaFalse() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Processoseletivo valido = new Processoseletivo("2016/02", format.parse("05/08/2016"), format.parse("02/08/2016"), format.parse("03/08/2016"), format.parse("04/08/2016"));
        boolean save = processoseletivoService.criarProcessoSeletivo(valido);
        assertFalse(save);
    } 
    
    @Test(expected=NullPointerException.class)
    public void salvarProcessoNullRetornaNullPointerException(){
        processoseletivoService.criarProcessoSeletivo(null);
    }
}
