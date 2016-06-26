package br.com.crescer.selecao.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Murillo
 */
@Controller
public class AcessController {
    
    @RequestMapping(value="/")
    String index() {
        return "Index";
    } 
    
    @RequestMapping(value="/login")
    String login() {
        return "Login";
    } 
    
    @RequestMapping(value="/logout")
    String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:login";
    } 
}
