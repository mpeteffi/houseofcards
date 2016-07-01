package br.com.crescer.selecao.service.services;

import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.entities.Processoseletivo;
import br.com.crescer.selecao.entities.enums.StatusCandidato;
import java.text.SimpleDateFormat;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author michel.fernandes
 */
@Service
public class EmailService {    

    @Autowired
    JavaMailSenderImpl emailSender; 
    
    @Autowired
    TokenService tokenService;

    @Autowired
    CandidatoService candidatoService;
    
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    private String gerarToken(Candidato candidato){
        return tokenService.criarTokenParaCandidato(candidato);
    }
    
    public void enviarEmailParaConfirmacaoDeInteresse(Candidato candidato) {
        MimeMessage message = emailSender.createMimeMessage();
        String token = gerarToken(candidato);
        String html = "<html>Ola "+candidato.getNome()+" <p>Voce preencheu o formulário de interesse no projeto, clique <a href=\"http://localhost:9090/email/confirmar-interesse?token=" + token + "\">aqui</a> para confirmar a inscrição</p></html>";
        try {
            message.setSubject("Confirmação de interesse");
            MimeMessageHelper helper = new MimeMessageHelper(message, true);      
            helper.setTo(candidato.getEmail());
            helper.setText(html, true);
            emailSender.send(message);
        } catch (Exception ex) {
            System.out.println(ex);
            //...
        }
    }
    
    public void enviarEmailParaInteressado(Candidato candidato, Processoseletivo processoSeletivo) {
        MimeMessage message = emailSender.createMimeMessage();
        String token = gerarToken(candidato);
        String html = "<html><h3>Iniciado o precesso seletivo do projeto crescer " + processoSeletivo.getEdicao() + "</h3>"
                    + "<p>Data início do agendamento de entrevista:" + sdf.format(processoSeletivo.getInicioSelecao()) + "</p>"
                    + "<p>Data final do agendamento de entrevista:" + sdf.format(processoSeletivo.getFinalSelecao()) + "</p>"
                    + "<p>Data início das aulas:" + sdf.format(processoSeletivo.getInicioAula()) + "</p>"
                    + "<p>Data início das aulas:" + sdf.format(processoSeletivo.getFinalAula()) + "</p>"
                    + "<p>Para confirmar sua inscrição no projeto, clique <a href=\"http://localhost:9090/email/confirmar-inscricao?token=" + token + "\">aqui</a>para preencher seus dados e completar a inscrição</html>";
            
        try {
            message.setSubject("Projeto Crescer");
            MimeMessageHelper helper = new MimeMessageHelper(message, true);      
            helper.setTo(candidato.getEmail());
            helper.setText(html, true);
            emailSender.send(message);
            candidato.setStatus(StatusCandidato.NOTIFICADO);
            candidatoService.salvarCandidato(candidato);
        } catch (Exception ex) {
            //...
        }
    }
}
