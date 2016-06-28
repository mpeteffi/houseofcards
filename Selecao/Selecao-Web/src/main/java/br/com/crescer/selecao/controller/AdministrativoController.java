package br.com.crescer.selecao.controller;

import br.com.crescer.selecao.entities.Informacao;
import br.com.crescer.selecao.service.services.CandidatoService;
import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Murillo
 */
@Controller
@RequestMapping(value="/administrativo")
public class AdministrativoController {
    
    @Autowired
    CandidatoService candidatoservice;
    
    @RequestMapping(value="")
    String login() {
        return "Administrativo";
    } 
}
