package br.com.crescer.selecao.service.services;

import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.entities.Processoseletivo;
import br.com.crescer.selecao.service.repository.ProcessoseletivoRepository;
import java.util.Date;
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
    
    public boolean criarProcessoSeletivo(Processoseletivo p){  
        
        if(p.getInicioselecao().before(p.getFinalselecao())
            && p.getFinalselecao().before(p.getInicioaula())
            && p.getInicioaula().before(p.getFinalaula())){
            
            try {
                Processoseletivo processo = processoseletivoRepository.save(p);
                Iterable<Candidato> candidatos = candidatoService.buscarCandidatosPorStatus("INTERESSADO");

                for(Candidato candidato : candidatos){
                    emailService.enviarEmailParaInteressado(candidato, processo);
                }
                return true;

            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }
    
    public Processoseletivo buscarProcessoAtual(){   
        return processoseletivoRepository.findTopByOrderByEdicaoDesc();
    }
        
    public boolean verificarExistenciaDeProcessoAtivo (){
        Date dataAtual = new Date();
        Date finalProcessoCorrente = buscarProcessoAtual().getFinalselecao();
        return dataAtual.before(finalProcessoCorrente);
    }
}