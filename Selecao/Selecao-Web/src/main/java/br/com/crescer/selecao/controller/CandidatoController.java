/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.selecao.controller;

import br.com.crescer.selecao.captcha.RecaptchaService;
import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.service.services.CandidatoService;
import br.com.crescer.selecao.service.services.EmailService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    EmailService emailService;
    
    @RequestMapping(value="/cadastro", method = RequestMethod.GET)
    String exemplo() {
        return "_InteressadoCadastro";
    } 
    
    @RequestMapping(value="/cadastro", method = RequestMethod.POST)
    String save(@Valid Candidato candidato, HttpServletRequest req,Model model) {
        String response = req.getParameter("g-recaptcha-response");
        String ipAcesso = req.getRemoteAddr();
        boolean captchaValido = recaptchaService.isResponseValid(ipAcesso, response);
        
        if(captchaValido) {
            try {
                if(candidatoService.save(candidato) != null){
                    emailService.enviarEmail(candidato);
                }else{
                    //email ja existe
                }
            } catch (Exception e){
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
    
}
