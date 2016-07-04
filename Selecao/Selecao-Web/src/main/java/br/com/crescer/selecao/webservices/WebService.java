package br.com.crescer.selecao.webservices;

import br.com.crescer.selecao.captcha.RecaptchaService;
import br.com.crescer.selecao.service.services.CandidatoService;
import br.com.crescer.selecao.service.services.DataHoraService;
import br.com.crescer.selecao.service.services.EmailService;
import br.com.crescer.selecao.service.services.EntrevistaService;
import br.com.crescer.selecao.service.services.GrupoDeProvasService;
import br.com.crescer.selecao.service.services.ProcessoseletivoService;
import br.com.crescer.selecao.service.services.TokenService;
import br.com.crescer.selecao.service.services.UsuarioLogadoService;
import br.com.crescer.selecao.service.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Murillo
 */
@Service
public class WebService {
    
    @Autowired CandidatoService candidatoService;
    @Autowired DataHoraService dataHoraService;
    @Autowired EmailService emailService;
    @Autowired GrupoDeProvasService grupodeDeProvasService;
    @Autowired EntrevistaService entrevistaService;
    @Autowired ProcessoseletivoService processoseletivoService;
    @Autowired TokenService tokenService;
    @Autowired UsuarioLogadoService usuarioLogadoService;
    @Autowired UsuarioService usuarioService;
    @Autowired RecaptchaService recaptchaService;

    public CandidatoService getCandidatoService() {
        return candidatoService;
    }
        
    public DataHoraService getDataHoraService() {
        return dataHoraService;
    }
    
    public EmailService getEmailService() {
        return emailService;
    }

    public EntrevistaService getEntrevistaService() {
        return entrevistaService;
    }

    public ProcessoseletivoService getProcessoseletivoService() {
        return processoseletivoService;
    }

    public TokenService getTokenService() {
        return tokenService;
    }

    public UsuarioLogadoService getUsuarioLogadoService() {
        return usuarioLogadoService;
    }
    
    public GrupoDeProvasService getGrupoDeProvasService() {
        return grupodeDeProvasService;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public RecaptchaService getRecaptchaService() {
        return recaptchaService;
    }
}
