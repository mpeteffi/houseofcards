package br.com.crescer.selecao.service.services;

import br.com.crescer.selecao.entities.Usuario;
import br.com.crescer.selecao.service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Murillo
 */
@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
    public Usuario buscarUsuarioPorEmail(String email){
        return usuarioRepository.findOneByEmail(email);
    }
}
