package br.com.crescer.selecao;

import br.com.crescer.selecao.controller.ProcessoseletivoController;
import br.com.crescer.selecao.service.services.ProcessoseletivoService;
import br.com.crescer.selecao.webservices.WebService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author Murillo
 */
@RunWith(MockitoJUnitRunner.class)
public class ProcessoseletivoControllerTest {
    
    MockMvc mockMvc;
    
    @InjectMocks
    ProcessoseletivoController processoseletivoController;
    
    @Mock WebService webService;
    @Mock ProcessoseletivoService processoseletivoService;
    
    @Before
    public void setUp() {
        doReturn(processoseletivoService).when(webService).getProcessoseletivoService();
        doReturn(true).when(processoseletivoService).verificarExistenciaDeProcessoAtivo();
        
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");

        mockMvc = MockMvcBuilders.standaloneSetup(processoseletivoController).setViewResolvers(viewResolver).build();
    }
    
    @Test
    public void confirmarInteresseComTokenInvalidoRetornaErro() throws Exception{
        mockMvc.perform(get("/verificarProcessoSeletivo"))
                .andExpect(status().isOk())
                .andReturn();
    }
}
