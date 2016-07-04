package br.com.crescer.selecao.service.repository;

import br.com.crescer.selecao.entities.Datahora;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Andrews Kuhn
 */
public interface DataHoraRepository extends PagingAndSortingRepository<Datahora, Long> {
    
    Datahora findByIdDataHora(Datahora dataHora);
    
}