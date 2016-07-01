package br.com.crescer.selecao.controller;

import br.com.crescer.selecao.webservices.WebService;
import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.entities.Entrevista;
import br.com.crescer.selecao.entities.Informacao;
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
    String cadastro() {
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    String save(@Valid Candidato candidato, BindingResult bindingResult, HttpServletRequest req, Model model) {
        
        String response = req.getParameter("g-recaptcha-response");
        String ipAcesso = req.getRemoteAddr();
        boolean captchaValido = webService.getRecaptchaService().isResponseValid(ipAcesso, response);

        if (captchaValido && !bindingResult.hasErrors()) {
            if (webService.getCandidatoService().salvarCandidato(candidato) != null) {
                model.addAttribute("mensagemFormCadastro", "Confirme a inscrição acessando seu email e clicando no link de confirmação");
                return "Sucesso";
            } else {
                return "redirect:cadastro?erroEmail";
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
        model.addAttribute("candidatos", candidatos);
        model.addAttribute("pagina", page);
        return "_Candidatos";
    }    
    
    @RequestMapping(value="/entrevistas",method = RequestMethod.GET)
    String entrevistas(Integer idCandidato, Model model) {
        Candidato candidato = webService.getCandidatoService().buscarCandidatoPorId(idCandidato);
        Iterable<Entrevista> entrevistas = webService.getEntrevistaService().buscarEntrevistasPorCandidato(candidato);            
        model.addAttribute("candidato", candidato);
        model.addAttribute("entrevistas", entrevistas);
        return "_entrevistas";
    }
    
    @RequestMapping(value="/nova-entrevista",method = RequestMethod.GET)
    String novaEntrevistaGET(Integer idCandidato, Model model) {        
        model.addAttribute("idCandidato", idCandidato);        
        return "_nova-entrevista";
    }
    
    @RequestMapping(value="/nova-entrevista",method = RequestMethod.POST)
    String salvarEntrevistaPOST(int idCandidato,Date dataentrevista,String parecerrh,String  parecertecnico,Double provag36,Double provaac,Double provatecnica) {        
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
    
    @RequestMapping(value="/editar-candidato",method = RequestMethod.GET)
    String editarCandidatoGET(Integer idCandidato, Model model) {        
        model.addAttribute("candidato", webService.getCandidatoService().buscarInformacoesDeCandidato(new Candidato(idCandidato)));
        return "_editar-candidato";
    }
    
    @RequestMapping(value="/editar-candidato",method = RequestMethod.POST)
    String salvarCandidatoPOST(int idCandidato,String nome,String email,String  instituicaoensino,String curso,String previsaoformatura,StatusCandidato status, String telefone, Date datanascimento, String cidade, String urllinkedin) {
        Candidato candidato = new Candidato(idCandidato,nome,email,instituicaoensino,curso,previsaoformatura,status); 
        webService.getCandidatoService().atualizarInformacao(new Informacao(telefone,
                                                                                    datanascimento,
                                                                                    cidade,
                                                                                    urllinkedin,
                                                                                    candidato));   
        return "Sucesso";
    }
}
