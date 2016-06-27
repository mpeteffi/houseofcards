package br.com.crescer.selecao.service.services;

import br.com.crescer.selecao.entities.Interessado;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author murillo.peteffi
 */
@Service
public class TokenService {
    
    //@Autowired
    
//    public String newTokenForInteressado(Interessado interessado){
//        
//        
//    }
    
    private String gerarToken(){
        //Gera um token aleatorio, encriptado a partir da data atual.
        String data = new Date().toString();
        return new BCryptPasswordEncoder().encode(data);
    }
}
