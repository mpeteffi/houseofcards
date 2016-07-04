package br.com.crescer.selecao.service.services;

import br.com.crescer.selecao.entities.Datahora;
import br.com.crescer.selecao.service.repository.DataHoraRepository;
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
    
    public Iterable<Datahora> todosAgendamentos(){
        return dataHoraRepository.findAll();
    }
    
    public void deletarAgendamento(Datahora data){
        dataHoraRepository.delete(data);
    }
    
     public Datahora findById(Datahora dataHora){
        return dataHoraRepository.findByIdDataHora(dataHora);
    }
    
    public void salvarAgendamento(Datahora data){
        dataHoraRepository.save(data);
    }
    
    
}
