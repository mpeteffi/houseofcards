package br.com.crescer.selecao.controller;

import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.entities.Informacao;
import br.com.crescer.selecao.entities.Processoseletivo;
import br.com.crescer.selecao.webservices.WebService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author michel.fernandes
 */
@Controller
@RequestMapping("/email")
public class EmailController {

    @Autowired
    WebService webService;

    @RequestMapping(value = "/confirmar-interesse", method = RequestMethod.GET)
    String confirmarTokenInteresse(String token, Model model) {

        Boolean confirmado = webService.getTokenService().confirmarInteresse(token);

        if (confirmado) {
            model.addAttribute("mensagemConfirmacaoEmail", "Inscrição efetuada com êxito");
            return "Sucesso";
        } else {
            return "pagina-erro";
        }
    }

    @RequestMapping(value = "/confirmar-inscricao", method = RequestMethod.GET)
    String confirmarTokenInscricao(Informacao informacao, String token, Model model) {

        Candidato candidato = webService.getTokenService().confirmarCandidatura(token);
        informacao.setIdCandidato(candidato);

        if (candidato != null) {
            model.addAttribute("informacao", informacao);
            return "FormConfirmarInscricao";
        } else {
            return "pagina-erro";
        }
    }

    @RequestMapping(value = "/confirmar-inscricao", method = RequestMethod.POST)
    String confirmarTokenInteresse(@Valid Informacao informacao, BindingResult bindingResult, Model model) {

        if (!bindingResult.hasErrors()) {

            Processoseletivo processo = webService.getProcessoseletivoService().buscarProcessoAtual();
            webService.getCandidatoService().salvarInformacao(informacao, processo);
            webService.getTokenService().invalidarTokenParaCandidato(informacao.getIdCandidato());
            model.addAttribute("mensagemSucessoInscricao", "Confirmação efetuada com sucesso");
            return "Sucesso";

        } else {
            return "FormConfirmarInscricao";
        }
    }
}
