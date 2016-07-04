package br.com.crescer.selecao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Andrews Kuhn
 */
@Controller
public class AgendamentoController {

    @RequestMapping(value = "/agendamento", method = RequestMethod.GET)
    String agendamento() {
        return "_agendamento";
    }    
}
