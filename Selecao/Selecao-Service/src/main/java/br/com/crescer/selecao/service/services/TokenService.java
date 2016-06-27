package br.com.crescer.selecao.service.services;

import br.com.crescer.selecao.entities.Interessado;
import br.com.crescer.selecao.entities.Token;
import br.com.crescer.selecao.service.repository.InteressadoRepository;
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
    InteressadoRepository interessadoRepository;
    
    public String newTokenForInteressado(Interessado interessado){
        
        String codigo = gerarCodigo();
        Token token = new Token(interessado.getIdinteressado(), codigo, "PENDENTE", "INTERESSADO");
        try{
            tokenRepository.save(token);
            return codigo;
        } catch (Exception e) {
            return null;
        }
    }
    
    public boolean confirmarInteressado(String codigo){
        
        Token token = tokenRepository.findOneByTokenAndStatusAndTipo(codigo, "PENDENTE", "INTERESSADO");
        if(token != null) {
            
            Interessado interessado = interessadoRepository.findOneByIdInteressado(token.getIdparaconfirmar());
            interessado.setStatus("INTERESSADO");
            interessadoRepository.save(interessado);
            
            token.setStatus("FINAL");
            tokenRepository.save(token);
            
            return true;
        } else {
            return false;
        }
    }
    
    private String gerarCodigo(){
        
        //Gera um token aleatorio, encriptado a partir da data atual.
        String data = new Date().toString();
        return new BCryptPasswordEncoder().encode(data);
    }
}
