package br.com.crescer.selecao.service.services;

import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.entities.Entrevista;
import br.com.crescer.selecao.service.repository.EntrevistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Murillo
 */
@Service
public class EntrevistaService {
    
    @Autowired
    EntrevistaRepository entrevistaRepository;
    
    public Iterable<Entrevista> findByCandidato(Candidato candidato){
        return entrevistaRepository.findAllByIdCandidato(candidato);
    }
}
