package br.com.crescer.selecao.service.repository;

import br.com.crescer.selecao.entities.Token;
import br.com.crescer.selecao.entities.enums.StatusToken;
import br.com.crescer.selecao.entities.enums.TipoToken;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author murillo.peteffi
 */
public interface TokenRepository extends PagingAndSortingRepository<Token, Long>{
    
    Token findOneByTokenAndStatusAndTipo(String token, StatusToken status, TipoToken tipo);
    Token findOneByIdParaConfirmarAndStatusAndTipo(int idparaconfirmar,StatusToken status,TipoToken tipo);
}

