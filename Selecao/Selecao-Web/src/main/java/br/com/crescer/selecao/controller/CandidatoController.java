/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.selecao.controller;

import br.com.crescer.selecao.captcha.RecaptchaService;
import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.entities.CandidatoFiltroDTO;
import br.com.crescer.selecao.entities.Entrevista;
import br.com.crescer.selecao.entities.Informacao;
import br.com.crescer.selecao.service.services.CandidatoService;
import br.com.crescer.selecao.service.services.EmailService;
import br.com.crescer.selecao.service.services.EntrevistaService;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author michel.fernandes
 */
@Controller
public class CandidatoController {

    @Autowired
    RecaptchaService recaptchaService;
    
    @Autowired
    CandidatoService candidatoService;
    
    @Autowired
    EntrevistaService entrevistaService;
    
    @Autowired
    EmailService emailService;

    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    String exemplo() {
        return "_InteressadoCadastro";
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    String save(@Valid Candidato candidato, BindingResult bindingResult, HttpServletRequest req, Model model) {
        String response = req.getParameter("g-recaptcha-response");
        String ipAcesso = req.getRemoteAddr();
        boolean captchaValido = recaptchaService.isResponseValid(ipAcesso, response);

        if (captchaValido) {
            try {
                if (!bindingResult.hasErrors()) {
                    if (candidatoService.save(candidato) != null) {
                        emailService.enviarEmailParaConfirmacaoDeInteresse(candidato);
                    } else {
                        //email ja existe
                    }
                } else {
                    model.addAttribute("errors", bindingResult.getAllErrors());
                    return "_InteressadoCadastro";
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
    String candidatos(@RequestParam(required = false) CandidatoFiltroDTO filtro, Integer page, Model model) {
        if (page == null) {
            page = 0;
        }
        if (filtro == null) {
            filtro = new CandidatoFiltroDTO(null, null, null, null);
        }
        Page<Informacao> candidatos = candidatoService.findByFilters(filtro.getStatus(), filtro.getNome(), filtro.getEmail(),
                filtro.getTelefone(), page);
        for (Informacao i : candidatos) {
            i.setDatanascimento(tempoDecorrido(i.getDatanascimento()));
        }
        model.addAttribute("valorAntigoInput", filtro);
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
    
    
    @RequestMapping(value="/entrevistas")
    String entrevistas(Integer idCandidato, Model model) {
        if (idCandidato == null){ idCandidato = 0;}
        Candidato candidato = candidatoService.findByIdCandidato(idCandidato);
        Entrevista entrevistas = entrevistaService.findByCandidato(candidato);        
        model.addAttribute("candidato", candidato);
        model.addAttribute("entrevistas", entrevistas);
        return "_entrevistas";
    }
}
