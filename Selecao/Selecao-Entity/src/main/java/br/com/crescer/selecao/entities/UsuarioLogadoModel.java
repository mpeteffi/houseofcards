package br.com.crescer.selecao.entities;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * @author Murillo
 */
public class UsuarioLogadoModel extends User {
    
    private Usuario usuario;

    public Usuario getUsuarioLogado() {
        return usuario;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuario = usuarioLogado;
    }
    
    public UsuarioLogadoModel(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
        super(usuario.getEmail(), usuario.getSenha(), authorities);
        this.usuario = usuario;
    }
}
