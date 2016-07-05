package br.com.crescer.selecao;

import br.com.crescer.selecao.captcha.RecaptchaService;
import br.com.crescer.selecao.controller.CandidatoController;
import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.entities.Entrevista;
import br.com.crescer.selecao.entities.Processoseletivo;
import br.com.crescer.selecao.entities.enums.StatusCandidato;
import br.com.crescer.selecao.service.services.CandidatoService;
import br.com.crescer.selecao.service.services.EntrevistaService;
import br.com.crescer.selecao.service.services.ProcessoseletivoService;
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
    @Mock EntrevistaService entrevistaService;
    @Mock ProcessoseletivoService processoseletivoService;
    
    @Mock BindingResult bindingResult;
    @Mock HttpServletRequest request;
    @Mock Candidato candidato;
    @Mock Entrevista entrevista;
      
    @Before
    public void setUp() {
        doReturn(false).when(bindingResult).hasErrors();
        doReturn(recaptchaService).when(webService).getRecaptchaService();
        doReturn(true).when(recaptchaService).isResponseValid("127.0.0.1", "TOKEN_VALIDO");
        doReturn(false).when(recaptchaService).isResponseValid("127.0.0.1", "TOKEN_INVALIDO");
        doReturn(candidatoService).when(webService).getCandidatoService();
        doReturn(new Candidato()).when(candidatoService).salvarCandidato(any(Candidato.class));
        doReturn(null).when(candidatoService).buscarCandidatosPorFiltros("",StatusCandidato.ENTREVISTADO,"","","",0);
        doReturn(processoseletivoService).when(webService).getProcessoseletivoService();
        doReturn(new Processoseletivo()).when(processoseletivoService).buscarProcessoAtual();
        doReturn(true).when(processoseletivoService).verificarExistenciaDeProcessoAtivo();
        doReturn(entrevistaService).when(webService).getEntrevistaService();
        doReturn(null).when(entrevistaService).buscarEntrevistasPorFiltros("", StatusCandidato.INICIAL, 1);
        doReturn(null).when(entrevistaService).buscarEntrevistasPorFiltros("", StatusCandidato.INICIAL, 0);
        doReturn(null).when(entrevistaService).salvarEntrevista(any(Entrevista.class));
        
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
    public void postIndexComDadosValidosRetornaSucesso() throws Exception{
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
    public void postIndexComDadosValidosETokenInvalidoRetornaIndex() throws Exception{
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
    public void postIndexComEmailIinvalidoRetornaIndex() throws Exception{
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
    public void postIndexComEmailETokenIinvalidosRetornaIndex() throws Exception{
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
    
    @Test
    public void candidatosRetornaAtributosCorretos() throws Exception{
        mockMvc.perform(get("/candidatos")
            .param("page", "1")
            .param("status", StatusCandidato.ENTREVISTADO.toString()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("pagina", 1))
                .andExpect(view().name("_Candidatos"));
    }
    
    @Test
    public void candidatosSemParametroDePageRetornaPageZero() throws Exception{
        mockMvc.perform(get("/candidatos")
            .param("status", StatusCandidato.ENTREVISTADO.toString()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("pagina", 0))
                .andExpect(view().name("_Candidatos"));
    }
    
    @Test
    public void entrevistadosRetornaAtributosCorretos() throws Exception{
        mockMvc.perform(get("/entrevistados")
            .param("page", "1")
            .param("status", StatusCandidato.INICIAL.toString()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("pagina", 1))
                .andExpect(view().name("_entrevistados"));
    }
    
    @Test
    public void entrevistadosSemParametroDePageRetornaPageZero() throws Exception{
        mockMvc.perform(get("/entrevistados")
            .param("status", StatusCandidato.INICIAL.toString()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("pagina", 0))
                .andExpect(view().name("_entrevistados"));
    }
    
    @Test
    public void entrevistasEdicaoNaoAceitaPost() throws Exception{
        mockMvc.perform(post("/entrevistas"))
                .andExpect(status().isMethodNotAllowed());
    }
    
    @Test
    public void getNovaEntrevistaRetornaCorreto() throws Exception{
        mockMvc.perform(get("/nova-entrevista"))
                .andExpect(status().isOk())
                .andExpect(view().name("_nova-entrevista"));
    }
}