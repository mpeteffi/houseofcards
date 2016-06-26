package br.com.crescer.selecao.service.repository;

import br.com.crescer.selecao.entities.Entrevista;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Murillo
 */
public interface EntrevistaRepository extends PagingAndSortingRepository<Entrevista, Long> {
    
}
