package br.com.crescer.selecao.controller;

import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.entities.Informacao;
import br.com.crescer.selecao.entities.Processoseletivo;
import br.com.crescer.selecao.service.services.CandidatoService;
import br.com.crescer.selecao.service.services.EmailService;
import br.com.crescer.selecao.service.services.ProcessoseletivoService;
import br.com.crescer.selecao.service.services.TokenService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author michel.fernandes
 */
@Controller
@RequestMapping("/email")
public class EmailController {

    @Autowired
    EmailService emailService;

    @Autowired
    TokenService tokenService;
    
    @Autowired
    CandidatoService candidatoService;
    
    @Autowired
    ProcessoseletivoService processoseletivoService;

    @RequestMapping(value = "/enviar", method = RequestMethod.GET)
    String enviarEmail() {

        //emailService.enviarEmail(, "token gigante");
        return "administrativo";
    }

    @RequestMapping(value = "/confirmar-interesse", method = RequestMethod.GET)
    String confirmarToken(String token, Model model) {

        Boolean confirmado = tokenService.confirmarInteresse(token);

        if (confirmado) {
            model.addAttribute("mensagemConfirmacaoEmail", "Inscrição efetuada com êxito");
            return "Sucesso";
        } else {
            return "paginaErro";
        }
    }

    @RequestMapping(value = "/confirmar-inscricao", method = RequestMethod.GET)
    String confirmarTokenInscricao(String token, Model model) {

        Candidato candidato = tokenService.confirmarInscricao(token);

        if (candidato != null) {
            model.addAttribute("informacao", new Informacao());
            model.addAttribute("candidato", candidato);
            return "FormConfirmarInscricao";
        } else {
            return "paginaErro";
        }
    }

    @RequestMapping(value = "/confirmar-inscricao", method = RequestMethod.POST)
    String confirmarTokenInteresse(@Valid Informacao informacao,BindingResult bindingResult, Model model) {       
        
        Processoseletivo processo = processoseletivoService.buscarProcessoAtual();        
        candidatoService.salvarInformacoes(informacao,processo);
        tokenService.invalidarTokenParaCandidato(informacao.getIdcandidato());
        model.addAttribute("mensagemSucessoInscricao", "Confirmação efetuada com sucesso");
        return "Sucesso";
        
    }

}
