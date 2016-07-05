package br.com.crescer.selecao;

import br.com.crescer.selecao.controller.AgendamentoController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author Murillo
 */
@RunWith(MockitoJUnitRunner.class)
public class AgendamentoControllerTest {
    
    MockMvc mockMvc;
    
    @InjectMocks
    AgendamentoController agendamentoController;
    
    @Before
    public void setUp() {       
        
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");

        mockMvc = MockMvcBuilders.standaloneSetup(agendamentoController).setViewResolvers(viewResolver).build();
    }
    
    @Test
    public void confirmarInteresseComTokenInvalidoRetornaErro() throws Exception{
        mockMvc.perform(get("/agendamento"))
                .andExpect(status().isOk())
                .andExpect(view().name("_agendamento"));
    }
    
    @Test
    public void confirmarInteresseNaoAceitaPost() throws Exception{
        mockMvc.perform(post("/agendamento"))
                .andExpect(status().isMethodNotAllowed());
    }
}
