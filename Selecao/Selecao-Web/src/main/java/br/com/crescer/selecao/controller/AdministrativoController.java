package br.com.crescer.selecao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Murillo
 */
@Controller
@RequestMapping(value="/administrativo")
public class AdministrativoController {
    
    @RequestMapping(value="")
    String login() {
        return "Administrativo";
    } 
}
