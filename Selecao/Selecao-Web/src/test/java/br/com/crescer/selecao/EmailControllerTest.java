package br.com.crescer.selecao;

import br.com.crescer.selecao.controller.EmailController;
import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.entities.Informacao;
import br.com.crescer.selecao.entities.Processoseletivo;
import br.com.crescer.selecao.service.services.CandidatoService;
import br.com.crescer.selecao.service.services.ProcessoseletivoService;
import br.com.crescer.selecao.service.services.TokenService;
import br.com.crescer.selecao.webservices.WebService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Murillo
 */
@RunWith(MockitoJUnitRunner.class)
public class EmailControllerTest {
    
    MockMvc mockMvc;
    
    @InjectMocks
    EmailController emailController;
    
    @Mock WebService webService;
    @Mock TokenService tokenService;
    @Mock CandidatoService candidatoService;
    @Mock ProcessoseletivoService processoseletivoService;
    
    @Mock Candidato candidato;
    @Mock Processoseletivo processoseletivo;
    
    @Before
    public void setUp() {
        doReturn(tokenService).when(webService).getTokenService();
        doReturn(candidatoService).when(webService).getCandidatoService();
        doReturn(processoseletivoService).when(webService).getProcessoseletivoService();
        doReturn(true).when(tokenService).confirmarInteresse("TOKEN_VALIDO");
        doReturn(false).when(tokenService).confirmarInteresse("TOKEN_INVALIDO");
        doReturn(candidato).when(tokenService).confirmarCandidatura("TOKEN_VALIDO");
        doReturn(null).when(tokenService).confirmarCandidatura("TOKEN_INVALIDO");
        doReturn(null).when(processoseletivoService).buscarProcessoAtual();
        doReturn(null).when(candidatoService).salvarInformacao(any(Informacao.class),any(Processoseletivo.class));
        
        
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");

        mockMvc = MockMvcBuilders.standaloneSetup(emailController).setViewResolvers(viewResolver).build();
    }
    
    @Test
    public void confirmarInteresseComTokenValidoRetornaSucesso() throws Exception{
        mockMvc.perform(get("/email/confirmar-interesse")
            .param("token", "TOKEN_VALIDO"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("mensagemConfirmacaoEmail", "Inscrição efetuada com êxito"))
                .andExpect(view().name("Sucesso"));
    }
    
    @Test
    public void confirmarInteresseComTokenInvalidoRetornaErro() throws Exception{
        mockMvc.perform(get("/email/confirmar-interesse")
            .param("token", "TOKEN_INVALIDO"))
                .andExpect(status().isOk())
                .andExpect(view().name("pagina-erro"));
    }
    
    @Test
    public void confirmarInteresseNaoAceitaPost() throws Exception{
        mockMvc.perform(post("/email/confirmar-interesse"))
                .andExpect(status().isMethodNotAllowed());
    }
    
    @Test
    public void confirmarCandidaturaComTokenValidoRetornaForm() throws Exception{
        mockMvc.perform(get("/email/confirmar-inscricao")
            .param("token", "TOKEN_VALIDO"))
                .andExpect(status().isOk())
                .andExpect(view().name("FormConfirmarInscricao"));
    }
    
    @Test
    public void confirmarCandidaturaComTokenInvalidoRetornaErro() throws Exception{
        mockMvc.perform(get("/email/confirmar-inscricao")
            .param("token", "TOKEN_INVALIDO"))
                .andExpect(status().isOk())
                .andExpect(view().name("pagina-erro"));
    }
    
    @Test
    public void postInscricaoComModelValidoRetornaSucesso() throws Exception{
        mockMvc.perform(post("/email/confirmar-inscricao")
            .param("telefone", "99995555")
            .param("dataNascimento", "06/08/1992")
            .param("cidade", "Porto Alegre")
            .param("urlLinkedin", "www.linkedin.com")
            .param("senha", "Crescer"))
                .andExpect(model().attribute("mensagemSucessoInscricao", "Confirmação efetuada com sucesso"))
                .andExpect(view().name("Sucesso"));
    }
    
    @Test
    public void postInscricaoComDataNascimentoInvalidoRetornaForm() throws Exception{
        mockMvc.perform(post("/email/confirmar-inscricao")
            .param("telefone", "99995555")
            .param("dataNascimento", "DATA_INVALIDA")
            .param("cidade", "Porto Alegre")
            .param("urlLinkedin", "www.linkedin.com")
            .param("senha", "Crescer"))
                .andExpect(view().name("FormConfirmarInscricao"));
    }
    
    @Test
    public void postInscricaoComSenhaNullRetornaForm() throws Exception{
        mockMvc.perform(post("/email/confirmar-inscricao")
            .param("telefone", "99995555")
            .param("dataNascimento", "DATA_INVALIDA")
            .param("cidade", "Porto Alegre")
            .param("urlLinkedin", "www.linkedin.com"))
                .andExpect(view().name("FormConfirmarInscricao"));
    }
    
    @Test
    public void postInscricaoComSenhaNullEDataValidaRetornaForm() throws Exception{
        mockMvc.perform(post("/email/confirmar-inscricao")
            .param("telefone", "99995555")
            .param("dataNascimento", "06/08/1992")
            .param("cidade", "Porto Alegre")
            .param("urlLinkedin", "www.linkedin.com"))
                .andExpect(view().name("FormConfirmarInscricao"));
    }
}
