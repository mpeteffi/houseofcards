package br.com.crescer.selecao.service.repository;

import br.com.crescer.selecao.entities.Token;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author murillo.peteffi
 */
public interface TokenRepository extends PagingAndSortingRepository<Token, Long>{
    
    Token findOneByTokenAndStatusAndTipo(String token, String status, String tipo);
    Token findOneByIdparaconfirmarAndStatusAndTipo(int idparaconfirmar,String status,String tipo);
}

