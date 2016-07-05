package br.com.crescer.selecao;

import br.com.crescer.selecao.controller.AcessController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author Murillo
 */
@RunWith(MockitoJUnitRunner.class)
public class AcessControllerTest {
    
    MockMvc mockMvc;
    
    @InjectMocks
    AcessController acessController;
    
    @Before
    public void setUp() {       
        
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");

        mockMvc = MockMvcBuilders.standaloneSetup(acessController).setViewResolvers(viewResolver).build();
    }    
    
    @Test
    public void loginRetornaPaginaDeLogin() throws Exception{
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("Login"));
    }
    
    @Test
    public void logoutRedirecionaParaLogin() throws Exception{
        mockMvc.perform(post("/logout"))
                .andExpect(status().is3xxRedirection());
    }
}
