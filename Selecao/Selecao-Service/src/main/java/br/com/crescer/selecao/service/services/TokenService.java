package br.com.crescer.selecao.service.services;

import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.entities.Token;
import br.com.crescer.selecao.entities.enums.StatusCandidato;
import br.com.crescer.selecao.entities.enums.StatusToken;
import br.com.crescer.selecao.entities.enums.TipoToken;
import br.com.crescer.selecao.service.repository.CandidatoRepository;
import br.com.crescer.selecao.service.repository.TokenRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author murillo.peteffi
 */
@Service
public class TokenService {
    
    @Autowired
    TokenRepository tokenRepository;
    
    @Autowired
    CandidatoRepository candidatoRepository;
    
    
    public String criarTokenParaCandidato(Candidato candidato){
        
        String codigo = gerarCodigo();
        Token token = new Token(candidato.getIdCandidato(), codigo, StatusToken.PENDENTE, TipoToken.INTERESSE);
        try{
            tokenRepository.save(token);
            return codigo;
        } catch (Exception e) {
            return null;
        }
    }
    
    public boolean confirmarInteresse(String codigo){
        
        Token token = tokenRepository.findOneByTokenAndStatusAndTipo(codigo, StatusToken.PENDENTE, TipoToken.INTERESSE);
        if(token != null) {
            
            Candidato candidato = candidatoRepository.findOneByIdCandidato(token.getIdparaconfirmar());
            candidato.setStatus(StatusCandidato.INTERESSADO);
            candidatoRepository.save(candidato);
            
            token.setStatus(StatusToken.FINAL);
            tokenRepository.save(token);
            
            return true;
        } else {
            return false;
        }
    }
        
    public Candidato confirmarCandidatura(String codigo){
        
        Token token = tokenRepository.findOneByTokenAndStatusAndTipo(codigo, StatusToken.PENDENTE, TipoToken.INTERESSE);
        if(token != null) {            
            Candidato candidato = candidatoRepository.findOneByIdCandidato(token.getIdparaconfirmar());            
            return candidato;
        } else {
            return null;
        }
    }
    
    public void invalidarTokenParaCandidato(Candidato candidato){
        Token token = tokenRepository.findOneByIdParaConfirmarAndStatusAndTipo(candidato.getIdCandidato(),StatusToken.PENDENTE,TipoToken.INTERESSE);
        token.setStatus(StatusToken.FINAL);
        tokenRepository.save(token);
    }
    
    private String gerarCodigo(){
        String data = new Date().toString();
        return new BCryptPasswordEncoder().encode(data);
    }
}
