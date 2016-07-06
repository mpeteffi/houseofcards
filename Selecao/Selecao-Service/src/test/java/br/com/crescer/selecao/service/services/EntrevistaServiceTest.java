package br.com.crescer.selecao.service.services;

import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.entities.Datahora;
import br.com.crescer.selecao.entities.Entrevista;
import br.com.crescer.selecao.entities.Grupodeprovas;
import br.com.crescer.selecao.entities.Usuario;
import br.com.crescer.selecao.entities.enums.StatusCandidato;
import br.com.crescer.selecao.service.repository.EntrevistaRepository;
import br.com.crescer.selecao.service.repository.ProcessoseletivoRepository;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;

/**
 * @author Murillo
 */
public class EntrevistaServiceTest {
    
    @InjectMocks
    EntrevistaService entrevistaService;
    
    @Mock EntrevistaRepository entrevistaRepository;
    @Mock ProcessoseletivoRepository processoseletivoRepository;
    
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        Entrevista EntrevistaNull = null;
        when(entrevistaRepository.findOneByIdCandidato(any(Candidato.class))).thenReturn(new Entrevista());
        when(entrevistaRepository.findOneByIdCandidato(null)).thenReturn(null);
        when(entrevistaRepository.findByIdEntrevista(any(Integer.class))).thenReturn(new Entrevista());
        when(entrevistaRepository.findByIdEntrevista(null)).thenReturn(null);
        when(entrevistaRepository.save(any(Entrevista.class))).thenReturn(new Entrevista());
        when(entrevistaRepository.save(EntrevistaNull)).thenReturn(null);
        when(entrevistaRepository.countByIdGrupoDeProvas(any(Grupodeprovas.class))).thenReturn(1);
        when(entrevistaRepository.countByIdGrupoDeProvas(null)).thenReturn(null);
    }
    
    @Test
    public void findEntrevistaByCandidatoValidoRetornaEntrevista() {
        Entrevista e = entrevistaService.buscarEntrevistaDeCandidato(new Candidato());
        assertNotNull(e);
    }
    
    @Test
    public void findEntrevistaByCandidatoNullRetornaNull() {
        Entrevista e = entrevistaService.buscarEntrevistaDeCandidato(null);
        assertNull(e);
    }
    
    @Test
    public void findEntrevistaByIdValidoRetornaEntrevista() {
        Entrevista e = entrevistaService.findByIdEntrevista(2);
        assertNotNull(e);
    }
    
    @Test
    public void findEntrevistaByIdNullRetornaNull() {
        Entrevista e = entrevistaService.findByIdEntrevista(null);
        assertNull(e);
    }
    
    @Test
    public void saveEntrevistaValidaRetornaEntrevista() {
        Entrevista e = entrevistaService.salvarEntrevista(new Entrevista());
        assertNotNull(e);
    }
    
    @Test
    public void saveEntrevistaNullRetornaException() {
        Entrevista e = entrevistaService.salvarEntrevista(null);
        assertNull(e);
    }
    
    @Test
    public void countEntrevistasPorGrupoValidoRetornaInt() {
        Integer num = entrevistaService.contarPorGrupo(new Grupodeprovas());
        assertTrue(num.equals(1));
        assertNotNull(num);
    }
    
    @Test
    public void countEntrevistasPorGrupoValidoRetornaNull() {
        Integer num = entrevistaService.contarPorGrupo(null);
        assertNull(num);
    }
}
