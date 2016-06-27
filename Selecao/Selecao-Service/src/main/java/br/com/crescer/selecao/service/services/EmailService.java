package br.com.crescer.selecao.service.services;


import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

/**
 *
 * @author michel.fernandes
 */
@Service
public class EmailService {
    
    
    public void enviarEmail(String destinatario,String assunto,String mensagem) {
        try {
            Email email = new SimpleEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setAuthentication("processoseletivocwi@gmail.com", "cwisoftware2");
            email.setSSLOnConnect(true);
            email.setFrom("processoseletivocwi@gmail.com");
            email.setSubject(assunto);
            email.setMsg(mensagem);
            email.addTo(destinatario);
            email.send();
        } catch (EmailException ex) {
            Logger.getLogger(EmailService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

