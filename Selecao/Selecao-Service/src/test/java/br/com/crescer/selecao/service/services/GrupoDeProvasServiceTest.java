package br.com.crescer.selecao.service.services;

import br.com.crescer.selecao.entities.Datahora;
import br.com.crescer.selecao.entities.Grupodeprovas;
import br.com.crescer.selecao.entities.Processoseletivo;
import br.com.crescer.selecao.service.repository.GrupoDeProvasRepository;
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

/**
 * @author Murillo
 */
public class GrupoDeProvasServiceTest {
    
    @InjectMocks
    GrupoDeProvasService grupoDeProvasService;
    
    @Mock 
    GrupoDeProvasRepository grupoDeProvasRepository;
    
    @Mock Grupodeprovas grupodeprovas;
    
    Grupodeprovas grupo = new Grupodeprovas(1);
    
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        when(grupoDeProvasRepository.save(any(Grupodeprovas.class))).thenReturn(grupodeprovas);
        when(grupoDeProvasRepository.save(grupo)).thenReturn(null);
    }
    
    @Test
    public void saveGrupoValidoRetornaGrupo() {
        Grupodeprovas gp = grupoDeProvasService.salvar(new Grupodeprovas());
        assertTrue(gp != null && gp.equals(grupodeprovas));
    } 
    
    @Test
    public void saveGrupoInvalidoRetornaGrupo() {
        Grupodeprovas gp = grupoDeProvasService.salvar(grupo);
        assertNull(gp);
    } 
}
