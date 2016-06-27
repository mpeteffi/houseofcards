package br.com.crescer.selecao.service.repository;

import br.com.crescer.selecao.entities.Interessado;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Murillo
 */
public interface InteressadoRepository extends PagingAndSortingRepository<Interessado, Long> {
    
    Interessado findOneByIdInteressado(int idInteressado);
}
