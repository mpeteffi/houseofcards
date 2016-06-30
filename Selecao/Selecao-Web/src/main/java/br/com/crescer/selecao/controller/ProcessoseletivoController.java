package br.com.crescer.selecao.controller;

import br.com.crescer.selecao.webservices.WebService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author murillo.peteffi
 */
@Controller
public class ProcessoseletivoController {
    
    @Autowired
    WebService webService;
    
    @ResponseBody
    @RequestMapping(value="/verificarProcessoSeletivo")
    boolean logout() {
        return webService.getProcessoseletivoService().existeProcessoAtivo();
    }
}