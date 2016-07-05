package br.com.crescer.selecao.service.services;

import br.com.crescer.selecao.entities.Datahora;
import br.com.crescer.selecao.service.repository.DataHoraRepository;
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
public class DataHoraServiceTest {
    
    @InjectMocks
    DataHoraService dataHoraService;
    
    @Mock
    DataHoraRepository dataHoraRepository;
    
    @Mock
    Datahora datahora;
    
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        when(dataHoraRepository.findByIdDataHora(any(Integer.class))).thenReturn(datahora);
        when(dataHoraRepository.findByIdDataHora(null)).thenReturn(null);
    }
    
    @Test
    public void findDatahoraByIdValidoRetornaDatahora() {
        Datahora dh = dataHoraService.findByIdDataHora(1);
        assertNotNull(dh);
    }
    
    @Test
    public void findDatahoraByIdNullRetornaNull() {
        Datahora dh = dataHoraService.findByIdDataHora(null);
        assertNull(dh);
    }
}
