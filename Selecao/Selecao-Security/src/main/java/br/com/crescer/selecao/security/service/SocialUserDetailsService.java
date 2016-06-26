package br.com.crescer.selecao.security.service;

import br.com.crescer.selecao.entities.Usuario;
import br.com.crescer.selecao.entities.UsuarioLogadoModel;
import br.com.crescer.selecao.security.enumeration.SocialRoles;
import br.com.crescer.selecao.service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Murillo
 */
@Service
public class SocialUserDetailsService implements UserDetailsService {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.isEmpty()) {
            throw new UsernameNotFoundException(String.format("User with username=%s was not found", username));
        }
        
        Usuario usuario = usuarioRepository.findOneByEmail(username);
        return new UsuarioLogadoModel(usuario, SocialRoles.valuesToList());
    }

}
