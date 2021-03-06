package br.com.crescer.selecao.service.services;

import br.com.crescer.selecao.entities.Datahora;
import br.com.crescer.selecao.entities.enums.TipoAgendamento;
import br.com.crescer.selecao.service.repository.DataHoraRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author michel.fernandes
 */
@Service
public class DataHoraService {    
    @Autowired
    DataHoraRepository dataHoraRepository;
    
    public Iterable<Datahora> todosAgendamentosRageDatas(Date end,Date start){
        return dataHoraRepository.findByDataHoraInicialBetween(start,end);
    }
    
    public Iterable<Datahora> findByTipoAndDataHoraInicialBetween(TipoAgendamento tipo,Date start,Date end){
        return dataHoraRepository.findByTipoAndDataHoraInicialBetween(tipo,start,end);
    }
    
    
    public Datahora findByIdDataHora(Integer id){
        return dataHoraRepository.findByIdDataHora(id);
    }
    
    public void deletarAgendamento(Datahora data){
        dataHoraRepository.delete(data);
    }
    
    public void salvarAgendamento(Datahora data){
        dataHoraRepository.save(data);
    }
}
