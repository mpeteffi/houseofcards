package br.com.crescer.selecao.service.services;

import br.com.crescer.selecao.entities.Usuario;
import br.com.crescer.selecao.service.repository.UsuarioRepository;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 * @author Murillo
 */
public class UsuarioServiceTest {
    
    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        when(usuarioRepository.findOneByEmail("EmailValido")).thenReturn(new Usuario());
        when(usuarioRepository.findOneByEmail("EmailInvalido")).thenReturn(null);
    }
    
    @Test
    public void findByEmailValidoRetornaUser() {
        Usuario user = usuarioService.buscarUsuarioPorEmail("EmailValido");
        assertTrue(user != null);
    }
    
    @Test
    public void findByEmailInvalidoRetornaNull() {
        Usuario user = usuarioService.buscarUsuarioPorEmail("EmailInvalido");
        assertTrue(user == null);
    }
}
