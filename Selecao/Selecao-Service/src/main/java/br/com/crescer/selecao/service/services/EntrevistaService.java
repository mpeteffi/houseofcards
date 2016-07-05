package br.com.crescer.selecao.service.services;

import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.entities.Entrevista;
import br.com.crescer.selecao.entities.Informacao;
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
    
    public Page<Entrevista> buscarEntrevistasPorFiltros(String edicao, StatusCandidato status, Integer pagina){
        pagina = pagina != null ? pagina : 0;
        edicao = edicao != null ? edicao : processoseletivoRepository.findTopByOrderByEdicaoDesc().getEdicao();
        StatusCandidato[] statusEntrevistados = new StatusCandidato[]{StatusCandidato.ENTREVISTADO,StatusCandidato.EM_ANALISE,StatusCandidato.SELECIONADO,StatusCandidato.NAO_SELECIONADO,StatusCandidato.PRE_SELECIONADO};
        StatusCandidato[] listaStatus = status == null ? statusEntrevistados : new StatusCandidato[]{status};
        
        Pageable pageable = new PageRequest(pagina, 1);
        
        Page<Entrevista> entrevistas = entrevistaRepository.findByIdCandidato_StatusIn(listaStatus, pageable);
                
        return entrevistas;
    }
    
    public Page<Entrevista> buscarProximasEntrevistas(){
        Pageable pageable = new PageRequest(0, 10, Sort.Direction.DESC, "idDataHora_DataHoraInicial");
        return entrevistaRepository.findByIdDataHora_DataHoraInicialAfter(new Date(), pageable);
    }   
}
