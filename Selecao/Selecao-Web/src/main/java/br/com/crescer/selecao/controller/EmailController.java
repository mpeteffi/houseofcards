package br.com.crescer.selecao.controller;

import br.com.crescer.selecao.service.services.EmailService;
import br.com.crescer.selecao.service.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author michel.fernandes
 */
@Controller
@RequestMapping("/email")
public class EmailController {
    
    @Autowired
    EmailService emailService;
    
    @Autowired
    TokenService tokenService;
    
    @RequestMapping(value="/enviar", method = RequestMethod.GET)
    String enviarEmail() {
        
        //emailService.enviarEmail(, "token gigante");
        
        return "administrativo";
    } 
    
    @RequestMapping(value="/confirmar", method = RequestMethod.GET)
    String confirmarToken(String token,Model model) {
        
        Boolean confirmado = tokenService.confirmarInteresse(token);
        
        if(confirmado){
            model.addAttribute("mensagemConfirmacaoEmail", "Inscrição efetuada com êxito");
            return "Sucesso";
        } else {
            return "paginaErro";
        }
    } 
    
}
