package br.com.crescer.selecao.controller;

import br.com.crescer.selecao.webservices.WebService;
import br.com.crescer.selecao.entities.Processoseletivo;
import br.com.crescer.selecao.entities.Usuario;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Murillo
 */
@Controller
public class AdministrativoController {
    
    @Autowired
    WebService webService;
    
    @RequestMapping(value="/administrativo", method = RequestMethod.GET)
    String adm(Model model) {
        Usuario user = webService.getUsuarioLogadoService().buscarUsuarioLogado();
        model.addAttribute("user", user);
        String edicao = webService.getProcessoseletivoService().buscarProcessoAtual().getEdicao();
        model.addAttribute("edicao", edicao);
        return "Administrativo";
    } 
    
    @RequestMapping(value="/novaedicao", method = RequestMethod.GET)
    String novaEdicao() {
        return "nova-edicao";
    } 
    
    @RequestMapping(value="/cadastro-nova-edicao", method = RequestMethod.POST)
    String save(@Valid Processoseletivo processoseletivo) {
        webService.getProcessoseletivoService().criarProcessoSeletivo(processoseletivo);    
        return "Administrativo";
    }
}
