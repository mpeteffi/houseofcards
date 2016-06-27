package br.com.crescer.selecao.controller;

import br.com.crescer.selecao.captcha.RecaptchaService;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Murillo
 */
@Controller
public class AcessController {
    
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
