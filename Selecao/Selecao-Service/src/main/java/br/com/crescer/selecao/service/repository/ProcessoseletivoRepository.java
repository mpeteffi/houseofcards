package br.com.crescer.selecao.service.repository;

import br.com.crescer.selecao.entities.Processoseletivo;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Murillo
 */
public interface ProcessoseletivoRepository extends PagingAndSortingRepository<Processoseletivo, Long> {
    
    Processoseletivo findTopByOrderByEdicaoDesc();
    
}