package br.com.crescer.selecao.service.repository;

import br.com.crescer.selecao.entities.Datahora;
import br.com.crescer.selecao.entities.enums.TipoAgendamento;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Andrews Kuhn
 */
public interface DataHoraRepository extends PagingAndSortingRepository<Datahora, Integer> {
    
    Datahora findByIdDataHora(Integer id);
    
    @Temporal(TemporalType.TIMESTAMP)
    Iterable<Datahora> findByDataHoraInicialBetween(Date start,Date end);
    
    Iterable<Datahora> findByTipoAndDataHoraInicialBetween(TipoAgendamento tipo,Date start,Date end);
}