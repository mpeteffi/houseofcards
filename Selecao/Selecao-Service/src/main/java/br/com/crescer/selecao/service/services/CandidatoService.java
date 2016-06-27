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
    
    public boolean save(Candidato candidato){
        try {
            candidatoRepository.save(candidato);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
