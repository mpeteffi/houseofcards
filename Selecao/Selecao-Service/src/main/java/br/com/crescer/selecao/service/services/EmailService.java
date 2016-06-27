package br.com.crescer.selecao.service.services;

import br.com.crescer.selecao.entities.Candidato;
import java.net.URL;
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

    private static void configurar(Email email) {
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthentication("processoseletivocwi@gmail.com", "cwisoftware2");
        email.setSSLOnConnect(true);
    }

    public void enviarEmail(Candidato candidato) {
        HtmlEmail email = new HtmlEmail();
        configurar(email);
        String token = tokenService.newTokenForCandidato(candidato);
        try {
            email.setFrom("processoseletivocwi@gmail.com");
            email.setSubject("Confirmação de interesse");
            //email.setMsg(msg);
            email.addTo(candidato.getEmail());
            
            // set the html message
            email.setHtmlMsg("<html>Quase lá... <p>Para confirmar o interesse no projeto: <a href=\"http://localhost:9090/confirmar/"+token+"\">link</a> </html>");
            // set the alternative message
            email.setTextMsg("Your email client does not support HTML messages");

            email.send();
        } catch (EmailException ex) {
            Logger.getLogger(EmailService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
