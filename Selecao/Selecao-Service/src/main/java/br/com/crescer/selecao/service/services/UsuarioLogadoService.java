package br.com.crescer.selecao.service.services;

import br.com.crescer.selecao.entities.Usuario;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author Murillo
 */
@Service
public class UsuarioLogadoService {
        
    public Usuario getUsuarioLogado(){
        return (Usuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
