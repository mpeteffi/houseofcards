package br.com.crescer.selecao;

import br.com.crescer.selecao.captcha.RecaptchaService;
import br.com.crescer.selecao.controller.CandidatoController;
import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.service.services.CandidatoService;
import br.com.crescer.selecao.webservices.WebService;
import javax.servlet.http.HttpServletRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author Murillo
 */
@RunWith(MockitoJUnitRunner.class)
public class CandidatoControllerTest {
    
    MockMvc mockMvc;
    
    @InjectMocks
    CandidatoController candidatoController;
    
    @Mock WebService webService;
    @Mock RecaptchaService recaptchaService;
    @Mock CandidatoService candidatoService;
    
    @Mock BindingResult bindingResult;
    @Mock HttpServletRequest request;
    @Mock Candidato candidato;
            
    @Before
    public void setUp() {
        doReturn(false).when(bindingResult).hasErrors();
        doReturn(recaptchaService).when(webService).getRecaptchaService();
        doReturn(true).when(recaptchaService).isResponseValid(any(String.class), "TOKEN_VALIDO");
        doReturn(false).when(recaptchaService).isResponseValid(any(String.class), "TOKEN_INVALIDO");
        doReturn(candidatoService).when(webService).getCandidatoService();
        doReturn(new Candidato()).when(candidatoService).salvarCandidato(any(Candidato.class));
        
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");

        mockMvc = MockMvcBuilders.standaloneSetup(candidatoController).setViewResolvers(viewResolver).build();
    }
    
    @Test
    public void getIndexRetornaIndex() throws Exception{
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
    
    @Test
    public void postComDadosValidosRetornaSucesso() throws Exception{
        mockMvc.perform(post("/")
            .param("g-recaptcha-response", "TOKEN_VALIDO")
            .param("nome", "Candidato")
            .param("email", "email@email.com")
            .param("instituicaoEnsino", "Crescer")
            .param("curso", "Crescer")
            .param("previsaoFormatura", "2016/01"))
                .andExpect(model().attribute("mensagemFormCadastro", "Confirme a inscrição acessando seu email e clicando no link de confirmação"))
                .andExpect(status().isOk())
                .andExpect(view().name("Sucesso"));
    }
    
    @Test
    public void postComDadosValidosETokenInvalidoRetornaIndex() throws Exception{
        mockMvc.perform(post("/")
            .param("g-recaptcha-response", "TOKEN_INVALIDO")
            .param("nome", "Candidato")
            .param("email", "email@email.com")
            .param("instituicaoEnsino", "Crescer")
            .param("curso", "Crescer")
            .param("previsaoFormatura", "2016/01"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
    
    @Test
    public void postComEmailIinvalidoRetornaIndex() throws Exception{
        mockMvc.perform(post("/")
            .param("g-recaptcha-response", "TOKEN_VALIDO")
            .param("nome", "Candidato")
            .param("email", "EMAIL_INVALIDO")
            .param("instituicaoEnsino", "Crescer")
            .param("curso", "Crescer")
            .param("previsaoFormatura", "2016/01"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
    
    @Test
    public void postComEmailETokenIinvalidosRetornaIndex() throws Exception{
        mockMvc.perform(post("/")
            .param("g-recaptcha-response", "TOKEN_INVALIDO")
            .param("nome", "Candidato")
            .param("email", "EMAIL_INVALIDO")
            .param("instituicaoEnsino", "Crescer")
            .param("curso", "Crescer")
            .param("previsaoFormatura", "2016/01"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
}