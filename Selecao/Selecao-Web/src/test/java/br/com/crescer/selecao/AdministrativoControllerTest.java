package br.com.crescer.selecao;

import br.com.crescer.selecao.controller.AdministrativoController;
import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.entities.Entrevista;
import br.com.crescer.selecao.entities.Processoseletivo;
import br.com.crescer.selecao.entities.Usuario;
import br.com.crescer.selecao.service.services.EntrevistaService;
import br.com.crescer.selecao.service.services.ProcessoseletivoService;
import br.com.crescer.selecao.service.services.UsuarioLogadoService;
import br.com.crescer.selecao.webservices.WebService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Murillo
 */
@RunWith(MockitoJUnitRunner.class)
public class AdministrativoControllerTest {
    
    MockMvc mockMvc;
    
    @InjectMocks
    AdministrativoController administrativoController;
    
    @Mock WebService webService;
    @Mock UsuarioLogadoService usuarioLogadoService;
    @Mock ProcessoseletivoService processoSeletivoService;
    @Mock EntrevistaService entrevistaService;
    
    @Mock Usuario usuario;
    @Mock Entrevista entrevista;
    @Mock Processoseletivo processoseletivo;
    
    @Before
    public void setUp() {
        doReturn(usuarioLogadoService).when(webService).getUsuarioLogadoService();
        doReturn(usuario).when(usuarioLogadoService).buscarUsuarioLogado();
        doReturn(processoSeletivoService).when(webService).getProcessoseletivoService();
        doReturn(processoseletivo).when(processoSeletivoService).buscarProcessoAtual();
        doReturn("EDICAO").when(processoseletivo).getEdicao();
        doReturn(entrevistaService).when(webService).getEntrevistaService();
        doReturn(entrevista).when(entrevistaService).buscarEntrevistaDeCandidato(any(Candidato.class));
        
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");

        mockMvc = MockMvcBuilders.standaloneSetup(administrativoController).setViewResolvers(viewResolver).build();
    }

    @Test
    public void admActionSemParametrosRetornaAdministrativo() throws Exception {
        mockMvc.perform(get("/administrativo"))
                .andExpect(model().attribute("user", usuario))
                .andExpect(model().attribute("edicao", "EDICAO"))
                .andExpect(status().isOk())
                .andExpect(view().name("Administrativo"));
    }
    
    @Test
    public void admNaoAceitaPost() throws Exception{
        mockMvc.perform(post("/administrativo"))
                .andExpect(status().isMethodNotAllowed());
    }
    
    @Test
    public void novaEdicaoRetornaCorretamente() throws Exception{
        mockMvc.perform(get("/novaedicao"))
                .andExpect(status().isOk())
                .andExpect(view().name("nova-edicao"));
    }
    
    @Test
    public void novaEdicaoNaoAceitaPost() throws Exception{
        mockMvc.perform(post("/novaedicao"))
                .andExpect(status().isMethodNotAllowed());
    }
    
    @Test
    public void cadastroNovaEdicaoRetornaAdministrativo() throws Exception{
        mockMvc.perform(post("/cadastro-nova-edicao"))
                .andExpect(status().isOk())
                .andExpect(view().name("Administrativo"));
    }
    
    @Test
    public void cadastroNovaEdicaoNaoAceitaGet() throws Exception{
        mockMvc.perform(get("/cadastro-nova-edicao"))
                .andExpect(status().isMethodNotAllowed());
    }
    
    @Test
    public void buscaParecerRetornaCorretamente() throws Exception{
        mockMvc.perform(get("/buscarParecer"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("entrevista", entrevista))
                .andExpect(view().name("_parecer"));
    }
}
