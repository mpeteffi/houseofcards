package br.com.crescer.selecao.service.repository;

import br.com.crescer.selecao.entities.Datahora;
import br.com.crescer.selecao.entities.Grupodeprovas;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Andrews Kuhn
 */
public interface GrupoDeProvasRepository extends PagingAndSortingRepository<Grupodeprovas, Integer> {
    
    void deleteByIdDataHora(Datahora data);
    
    Iterable<Grupodeprovas> findByIdDataHoraIn(Iterable<Datahora> datas);
}