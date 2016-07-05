package br.com.crescer.selecao.controller;

import br.com.crescer.selecao.webservices.WebService;
import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.entities.Entrevista;
import br.com.crescer.selecao.entities.Informacao;
import br.com.crescer.selecao.entities.Processoseletivo;
import br.com.crescer.selecao.entities.enums.StatusCandidato;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author michel.fernandes
 */
@Controller
public class CandidatoController {
    
    @Autowired
    WebService webService; 
    
    @ModelAttribute("candidato")
    public Candidato candidato() {
        return new Candidato();
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    String index(Model model) {
        Processoseletivo processo = webService.getProcessoseletivoService().buscarProcessoAtual();
        boolean inscricoesEncerradas = webService.getProcessoseletivoService().verificarExistenciaDeProcessoAtivo();
        model.addAttribute("processo", processo);
        model.addAttribute("inscricoesEncerradas", inscricoesEncerradas);
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    String save(@Valid Candidato candidato, BindingResult bindingResult, HttpServletRequest req, Model model) {
        
        Processoseletivo processo = webService.getProcessoseletivoService().buscarProcessoAtual();
        boolean inscricoesEncerradas = webService.getProcessoseletivoService().verificarExistenciaDeProcessoAtivo();
        model.addAttribute("processo", processo);
        model.addAttribute("inscricoesEncerradas", inscricoesEncerradas);
        
        String response = req.getParameter("g-recaptcha-response");
        String ipAcesso = req.getRemoteAddr();
        boolean captchaValido = webService.getRecaptchaService().isResponseValid(ipAcesso, response);

        if (captchaValido && !bindingResult.hasErrors()) {
            if (webService.getCandidatoService().salvarCandidato(candidato) != null) {
                model.addAttribute("mensagemFormCadastro", "Confirme a inscrição acessando seu email e clicando no link de confirmação");
                return "Sucesso";
            } else {
                return "redirect:index?erroEmail";
            }
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/candidatos")
    String candidatos(String nome,String email,String telefone,StatusCandidato status, String edicao, Integer page, Model model) {
        Page<Informacao> candidatos = webService.getCandidatoService().buscarCandidatosPorFiltros(edicao, status, nome, email,telefone, page);
        model.addAttribute("valorAntigoInput", new HashMap<String, Object>(){
            {
                put("nome", nome);
                put("telefone", telefone);
                put("email", email);
                put("status", status);
            }
        });
        if(page==null)page=0;
        model.addAttribute("candidatos", candidatos);
        model.addAttribute("pagina", page);
        return "_Candidatos";
    }
    
    @RequestMapping(value = "/entrevistados")
    String entrevistados(String edicao,StatusCandidato status,Integer page ,Model model) {
        Page<Entrevista> entrevistas = webService.getEntrevistaService().buscarEntrevistasPorFiltros(edicao,status,page);
        if(page==null)page=0;
        model.addAttribute("entrevistas", entrevistas);
        model.addAttribute("pagina", page);
        return "_entrevistados";
    }    
    
    @RequestMapping(value="/entrevistas",method = RequestMethod.GET)
    String entrevistas(Integer idCandidato, Model model) {
        Informacao candidato = webService.getCandidatoService().buscarInformacoesDeCandidato(new Candidato(idCandidato));
        Entrevista entrevista = webService.getEntrevistaService().buscarEntrevistaDeCandidato(candidato.getIdCandidato());
        if(entrevista == null) entrevista = new Entrevista();
        model.addAttribute("candidato", candidato);
        model.addAttribute("entrevista", entrevista);
        return "_entrevistas";
    }
    
    @RequestMapping(value="/nova-entrevista",method = RequestMethod.GET)
    String novaEntrevistaGET(Integer idCandidato, Model model) {        
        model.addAttribute("idCandidato", idCandidato);
        return "_nova-entrevista";
    }
    
    @RequestMapping(value="/nova-entrevista",method = RequestMethod.POST)
    String salvarEntrevistaPOST(Integer idCandidato,Date dataentrevista,String parecerrh,String  parecertecnico,Double provag36,Double provaac,Double provatecnica) {        
       webService.getEntrevistaService().salvarEntrevista(new Entrevista(dataentrevista,
                                                                parecerrh,
                                                                parecertecnico,
                                                                provag36,
                                                                provaac,
                                                                provatecnica,
                                                                new Candidato(idCandidato),
                                                                webService.getUsuarioLogadoService().buscarUsuarioLogado()
                                                            ));   
       return "Sucesso";
    }    
    
    @RequestMapping(value="/editar-candidato-teste",method = RequestMethod.POST)
    public String salvarCandidatoPOST(int idCandidato,String nome,String email,String  instituicaoensino,String curso,String previsaoformatura,StatusCandidato status, String telefone, Date datanascimento, String cidade, String urllinkedin) {
        Candidato candidato = new Candidato(idCandidato,nome,email,instituicaoensino,curso,previsaoformatura,status); 
        webService.getCandidatoService().atualizarInformacao(new Informacao(telefone,
                                                                                    datanascimento,
                                                                                    cidade,
                                                                                    urllinkedin,
                                                                                    candidato));   
        return "Sucesso";
    }
}
