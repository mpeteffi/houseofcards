package br.com.crescer.selecao.service.services;

import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.entities.Processoseletivo;
import br.com.crescer.selecao.service.repository.ProcessoseletivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Murillo
 */
@Service
public class ProcessoseletivoService {
    
    @Autowired
    ProcessoseletivoRepository processoseletivoRepository;
    
    @Autowired
    EmailService emailService;
    
    @Autowired
    CandidatoService candidatoService;
    
    public boolean save(Processoseletivo p){  
        
        try {
            Processoseletivo processo = processoseletivoRepository.save(p);
            Iterable<Candidato> candidatos = candidatoService.findByStatus("INTERESSADO");
            
            for(Candidato candidato : candidatos){
                emailService.enviarEmailParaInteressado(candidato, processo);
            }
            return true;
            
        } catch (Exception e) {
            return false;
        }
    }
    
    public Processoseletivo buscarProcessoAtual(){
        return processoseletivoRepository.findTopByOrderByEdicaoDesc();
    }
    
}
