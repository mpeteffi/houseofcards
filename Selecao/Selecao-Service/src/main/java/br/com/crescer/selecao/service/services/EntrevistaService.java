package br.com.crescer.selecao.service.services;

import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.entities.Datahora;
import br.com.crescer.selecao.entities.Entrevista;
import br.com.crescer.selecao.entities.Grupodeprovas;
import br.com.crescer.selecao.entities.Usuario;
import br.com.crescer.selecao.entities.enums.StatusCandidato;
import br.com.crescer.selecao.service.repository.EntrevistaRepository;
import br.com.crescer.selecao.service.repository.ProcessoseletivoRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author Murillo
 */
@Service
public class EntrevistaService {
    
    @Autowired
    EntrevistaRepository entrevistaRepository;
    
    @Autowired
    ProcessoseletivoRepository processoseletivoRepository;
    
    public Entrevista buscarEntrevistaDeCandidato(Candidato candidato){
        return entrevistaRepository.findOneByIdCandidato(candidato);
    }
    
    public Iterable<Entrevista> buscarEntrevistasPorCandidato(Candidato candidato){
        return entrevistaRepository.findAllByIdCandidato(candidato);
    }
    public Entrevista salvarEntrevista(Entrevista entrevista){
        return entrevistaRepository.save(entrevista);
    }
    
    public Integer salvarEntrevista(Integer idCandidato,Datahora data,Usuario usuario) throws Exception {
        Entrevista entrevista= this.buscarEntrevistaDeCandidato(new Candidato(idCandidato)); 
        if(entrevista == null){ 
            return this.salvarEntrevista( 
                            new Entrevista( 
                                new Candidato(idCandidato,StatusCandidato.ENTREVISTA_AGENDADA), 
                                data, 
                                usuario 
                            ) 
                        )
                        .getIdDataHora() 
                        .getIdDataHora();  
        }else if(entrevista.getIdDataHora() != null){ 
            throw new Exception("Candidato já tem uma entrevista");
        }else{ 
            entrevista.setIdDataHora(data);
            entrevista.getIdCandidato().setStatus(StatusCandidato.ENTREVISTA_AGENDADA);
            return this.salvarEntrevista(entrevista) 
                        .getIdDataHora() 
                        .getIdDataHora(); 
        }
        
            
    }
    
    public Integer contarPorGrupo(Grupodeprovas grupo){
        return entrevistaRepository.countByIdGrupoDeProvas(grupo);
    }
    
    public Page<Entrevista> buscarEntrevistasPorFiltros(String edicao, StatusCandidato status,Integer pagina){
        pagina = pagina != null ? pagina : 0;
        edicao = edicao != null ? edicao : processoseletivoRepository.findTopByOrderByEdicaoDesc().getEdicao();
        StatusCandidato[] statusEntrevistados = new StatusCandidato[]{StatusCandidato.ENTREVISTADO,StatusCandidato.EM_ANALISE,StatusCandidato.SELECIONADO,StatusCandidato.NAO_SELECIONADO,StatusCandidato.PRE_SELECIONADO};
        StatusCandidato[] listaStatus = status == null ? statusEntrevistados : new StatusCandidato[]{status};
        
        Pageable pageable = new PageRequest(pagina, 7,Sort.Direction.DESC,"idEntrevista");
        
        Page<Entrevista> entrevistas = entrevistaRepository.findByIdCandidato_StatusIn(listaStatus, pageable);
                
        return entrevistas;
    }
    
    public Page<Entrevista> buscarProximasEntrevistas(){
        Pageable pageable = new PageRequest(0, 10, Sort.Direction.DESC, "idDataHora_DataHoraInicial");
        return entrevistaRepository.findByIdDataHora_DataHoraInicialAfter(new Date(), pageable);
    }   
}
