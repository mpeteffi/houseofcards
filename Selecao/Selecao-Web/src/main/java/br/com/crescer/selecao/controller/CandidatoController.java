package br.com.crescer.selecao.controller;

import br.com.crescer.selecao.webservices.WebService;
import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.entities.Entrevista;
import br.com.crescer.selecao.entities.Informacao;
import java.util.Calendar;
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
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    String cadastro() {
        return "index";
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    String save(@Valid Candidato candidato, BindingResult bindingResult, HttpServletRequest req, Model model) {
        String response = req.getParameter("g-recaptcha-response");
        String ipAcesso = req.getRemoteAddr();
        boolean captchaValido = webService.getRecaptchaService().isResponseValid(ipAcesso, response);

        if (captchaValido) {
            try {
                if (!bindingResult.hasErrors()) {
                    if (webService.getCandidatoService().save(candidato) != null) {
                        webService.getEmailService().enviarEmailParaConfirmacaoDeInteresse(candidato);
                    } else {
                        //email ja existe
                    }
                } else {
                    return "index";
                }
            } catch (Exception e) {
                return "redirect:cadastro?erroEmail";
            }
            model.addAttribute("mensagemFormCadastro", "Confirme a inscrição acessando seu email e clicando no link de confirmação");
            return "Sucesso";
        }
        return "redirect:cadastro?erroCaptcha";
    }

    @ModelAttribute("candidato")
    public Candidato candidato() {
        return new Candidato();
    }

    @RequestMapping(value = "/candidatos")
    String candidatos(String nome,String email,String telefone,String status, String edicao, Integer page, Model model) {
        if (page == null) {
            page = 0;
        }
        Page<Informacao> candidatos = webService.getCandidatoService().findByFilters(edicao, status, nome, email,telefone, page);
        for (Informacao i : candidatos) {
            i.setDatanascimento(tempoDecorrido(i.getDatanascimento()));
        }
        model.addAttribute("valorAntigoInput", new HashMap<String, String>(){
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

    public Date tempoDecorrido(Date date) {
        Calendar c = Calendar.getInstance();
        if (date == null) {
            return null;
        }
        long diff = new Date().getTime() - date.getTime();
        c.setTime(new Date(diff));

        return c.getTime();
    }
    
    
    @RequestMapping(value="/entrevistas",method = RequestMethod.GET)
    String entrevistas(Integer idCandidato, Model model) {
        if (idCandidato == null){ idCandidato = 0;}
        Candidato candidato = webService.getCandidatoService().findByIdCandidato(idCandidato);
        Iterable<Entrevista> entrevistas = webService.getEntrevistaService().findByCandidato(candidato);            
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
       webService.getEntrevistaService().save(new Entrevista(   dataentrevista,
                                                                parecerrh,
                                                                parecertecnico,
                                                                provag36,
                                                                provaac,
                                                                provatecnica,
                                                                new Candidato(idCandidato),
                                                                webService.getUsuarioLogadoService().getUsuarioLogado()
                                                            ));   
       return "Sucesso";
    }
    
    @RequestMapping(value="/editar-candidato",method = RequestMethod.GET)
    String editarCandidatoGET(Integer idCandidato, Model model) {        
        model.addAttribute("candidato", webService.getCandidatoService().findInformcaoesDoCandidato(new Candidato(idCandidato)));
        return "editar-candidato";
    }
    
    @RequestMapping(value="/editar-candidato",method = RequestMethod.POST)
    String salvarCandidatoPOST(int idCandidato,String nome,String email,String  instituicaoensino,String curso,String previsaoformatura,String status, String telefone, Date datanascimento, String cidade, String urllinkedin) {
        Candidato candidato = new Candidato(idCandidato,nome,email,instituicaoensino,curso,previsaoformatura,status); 
        webService
                .getCandidatoService()
                .salvarInformcaoesDoCandidato(new Informacao( 
                                                    telefone,
                                                    datanascimento,
                                                    cidade,
                                                    urllinkedin,
                                                    candidato                                                                
                                                ));   
        return "Sucesso";
    }
}
