package br.com.crescer.selecao.controller;

import br.com.crescer.selecao.entities.Processoseletivo;
import br.com.crescer.selecao.entities.Usuario;
import br.com.crescer.selecao.service.services.CandidatoService;
import br.com.crescer.selecao.service.services.EmailService;
import br.com.crescer.selecao.service.services.ProcessoseletivoService;
import br.com.crescer.selecao.service.services.UsuarioLogadoService;
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
    CandidatoService candidatoservice;
    
    @Autowired
    ProcessoseletivoService processoSeletivoService;
    
    @Autowired
    UsuarioLogadoService usuarioLogadoService;
    
    @Autowired
    EmailService emailService;
    
    @RequestMapping(value="/administrativo")
    String login(Model model) {
        Usuario user = usuarioLogadoService.getUsuarioLogado();
        model.addAttribute("user", user);
        String edicao = processoSeletivoService.buscarProcessoAtual().getEdicao();
        model.addAttribute("edicao", edicao);
        return "Administrativo";
    } 
    
    @RequestMapping(value="/novaedicao")
    String novaEdicao() {
        return "nova-edicao";
    } 
    
    @RequestMapping(value="/cadastro-nova-edicao", method = RequestMethod.POST)
    String save(@Valid Processoseletivo processoseletivo,Model model) {
        processoSeletivoService.save(processoseletivo);    
       return "Administrativo";
    }
}
