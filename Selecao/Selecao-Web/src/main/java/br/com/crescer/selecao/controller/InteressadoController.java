package br.com.crescer.selecao.controller;

import br.com.crescer.selecao.captcha.RecaptchaService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Murillo
 */
@Controller
public class InteressadoController {
    
    @Autowired
    RecaptchaService recaptchaService;
    
    @RequestMapping(value="/")
    String index() {
        return "Index";
    } 
    
    @RequestMapping(value="/cadastro", method = RequestMethod.GET)
    String exemplo() {
        return "_InteressadoCadastro";
    } 
    
    @RequestMapping(value="/cadastro", method = RequestMethod.POST)
    String login2(String username, String password, HttpServletRequest req) {
        String response = req.getParameter("g-recaptcha-response");
        String ipAcesso = req.getRemoteAddr();
        boolean captchaValido = recaptchaService.isResponseValid(ipAcesso, response);
        
        if(captchaValido) {
            try {
                //interessadoService.save(INTERESSADO);
            } catch (Exception e){
                return "redirect:cadastro?erroEmail";
            }
            return "TelaSucesso";
        }        
        return "redirect:cadastro?erroCaptcha";
    }
}
