package br.com.crescer.selecao.controller;

import br.com.crescer.selecao.webservices.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Andrews
 */
@Controller
public class AgendamentoController {

    @Autowired
    WebService webService;

    @RequestMapping(value = "/agendamento", method = RequestMethod.GET)
    String agendamento() {
        return "_agendamento";
    }

}
