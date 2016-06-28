package br.com.crescer.selecao.service.services;

import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.entities.Processoseletivo;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author michel.fernandes
 */
@Service
public class EmailService {

    @Autowired
    TokenService tokenService;

    @Autowired
    CandidatoService candidatoService;
    
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private static void configurar(Email email) {
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthentication("processoseletivocwi@gmail.com", "cwisoftware2");
        email.setSSLOnConnect(true);
    }

    public void enviarEmailParaConfirmacaoDeInteresse(Candidato candidato) {
        HtmlEmail email = new HtmlEmail();
        configurar(email);
        String token = tokenService.newTokenForCandidato(candidato);
        try {
            email.setFrom("processoseletivocwi@gmail.com");
            email.setSubject("Confirmação de interesse");
            email.addTo(candidato.getEmail());
            email.setHtmlMsg("<html>Quase lá... <p>Para confirmar o interesse no projeto: <a href=\"http://localhost:9090/email/confirmar-interesse?token=" + token + "\">link</a> </html>");
            email.send();
        } catch (EmailException ex) {
            Logger.getLogger(EmailService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void enviarEmailParaInteressado(Candidato candidato, Processoseletivo processoSeletivo) {
        HtmlEmail email = new HtmlEmail();
        configurar(email);
        String token = tokenService.newTokenForCandidato(candidato);
        try {
            email.setFrom("processoseletivocwi@gmail.com");
            email.setSubject("Confirmação de inscrição");
            email.addTo(candidato.getEmail());
            email.setHtmlMsg("<html><h3>Iniciado o precesso seletivo do projeto crescer " + processoSeletivo.getEdicao() + "</h3>"
                    + "<p>Data início do agendamento de entrevista:" + sdf.format(processoSeletivo.getInicioselecao()) + "</p>"
                    + "<p>Data final do agendamento de entrevista:" + sdf.format(processoSeletivo.getFinalselecao()) + "</p>"
                    + "<p>Data início dasaulas:" + sdf.format(processoSeletivo.getInicioaula()) + "</p>"
                    + "<p>Data início dasaulas:" + sdf.format(processoSeletivo.getFinalaula()) + "</p>"
                    + "<p>Para confirmar sua inscrição no projeto, acesse: <a href=\"http://localhost:9090/email/confirmar-inscricao?token=" + token + "\">link</a> </html>");
            email.send();
            candidato.setStatus("NOTIFICADO");
            candidatoService.salvar(candidato);
        } catch (EmailException ex) {
            Logger.getLogger(EmailService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
