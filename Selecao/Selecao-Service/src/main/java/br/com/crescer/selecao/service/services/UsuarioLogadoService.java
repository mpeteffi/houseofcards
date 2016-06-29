package br.com.crescer.selecao.service.services;

import br.com.crescer.selecao.entities.Usuario;
import br.com.crescer.selecao.entities.UsuarioLogadoModel;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author Murillo
 */
@Service
public class UsuarioLogadoService {
        
    public Usuario getUsuarioLogado(){
        UsuarioLogadoModel logado = (UsuarioLogadoModel)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return logado.getUsuarioLogado();
    }
}
