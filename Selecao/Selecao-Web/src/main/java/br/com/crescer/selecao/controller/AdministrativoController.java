package br.com.crescer.selecao.controller;

import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.entities.Entrevista;
import br.com.crescer.selecao.entities.Grupodeprovas;
import br.com.crescer.selecao.webservices.WebService;
import br.com.crescer.selecao.entities.Processoseletivo;
import br.com.crescer.selecao.entities.Usuario;
import java.util.ArrayList;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @RequestMapping(value = "/administrativo", method = RequestMethod.GET)
    String adm(Model model) {
        Usuario user = webService.getUsuarioLogadoService().buscarUsuarioLogado();
        model.addAttribute("user", user);
        model.addAttribute("edicao", webService.getProcessoseletivoService().buscarProcessoAtual().getEdicao());
        return "Administrativo";
    }

    @RequestMapping(value = "/novaedicao", method = RequestMethod.GET)
    String novaEdicao() {
        return "nova-edicao";
    } 
    
    @RequestMapping(value="/administrativo-home", method = RequestMethod.GET)
    String home(Model model) {
        String edicao = webService.getProcessoseletivoService().buscarProcessoAtual().getEdicao();
        ArrayList<Integer> counts = webService.getCandidatoService().buscarContadores(edicao);
        Page<Entrevista> entrevistas = webService.getEntrevistaService().buscarProximasEntrevistas();
        Iterable<Grupodeprovas> grupos = webService.getGrupoDeProvasService().buscarGrupoDeProvasEdicaoAtual();
        ArrayList<Integer> contadoresGrupos = webService.getGrupoDeProvasService().buscarEntrevistasPorGrupo(grupos);
        
        entrevistas.iterator().hasNext();
        model.addAttribute("grupos",grupos);
        model.addAttribute("contadoresGrupos",contadoresGrupos);
        model.addAttribute("counts", counts);
        model.addAttribute("entrevistas", entrevistas);
        return "_adm-home";
    }

    @RequestMapping(value = "/cadastro-nova-edicao", method = RequestMethod.POST)
    String save(@Valid Processoseletivo processoseletivo) {
        webService.getProcessoseletivoService().criarProcessoSeletivo(processoseletivo);
        return "Administrativo";
    }

    @RequestMapping(value = "/buscarParecer")
    String buscarParecer(Integer idCandidato,Model model) {
        Entrevista entrevista = webService.getEntrevistaService().buscarEntrevistaDeCandidato(new Candidato(idCandidato));
        model.addAttribute("entrevista", entrevista);
        return "_parecer";
    }
}
