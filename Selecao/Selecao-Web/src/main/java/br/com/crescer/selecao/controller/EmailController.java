package br.com.crescer.selecao.controller;

import br.com.crescer.selecao.service.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    
    @RequestMapping(value="/enviar", method = RequestMethod.GET)
    String enviarEmail() {
        
        //emailService.enviarEmail(, "token gigante");
        
        return "administrativo";
    } 
    
}
