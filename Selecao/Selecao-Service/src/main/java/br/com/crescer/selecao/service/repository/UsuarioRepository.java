package br.com.crescer.selecao.service.repository;

import br.com.crescer.selecao.entities.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Murillo
 */
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long>{
    
    Usuario findOneByEmail(String email);
}
