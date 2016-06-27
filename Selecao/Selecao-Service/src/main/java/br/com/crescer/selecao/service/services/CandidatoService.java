package br.com.crescer.selecao.service.services;

import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.service.repository.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Murillo
 */
@Service
public class CandidatoService {
    
    @Autowired
    CandidatoRepository candidatoRepository;
    
    public Candidato save(Candidato candidato){
        try {
            candidato.setStatus("INICIAL");            
            return candidatoRepository.save(candidato);
        } catch (Exception e) {
            return null;
        }
    }
}
