package br.com.crescer.selecao.service.services;

import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.entities.Entrevista;
import br.com.crescer.selecao.entities.Informacao;
import br.com.crescer.selecao.entities.enums.StatusCandidato;
import br.com.crescer.selecao.service.repository.EntrevistaRepository;
import br.com.crescer.selecao.service.repository.ProcessoseletivoRepository;
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
    public void salvarEntrevista(Entrevista entrevista){
        entrevistaRepository.save(entrevista);
    }
    
    public Iterable<Entrevista> buscarEntrevistasPorFiltros(String edicao, StatusCandidato status){
        //pagina = pagina != null ? pagina : 0;
        edicao = edicao != null ? edicao : processoseletivoRepository.findTopByOrderByEdicaoDesc().getEdicao();
        StatusCandidato[] statusEntrevistados = new StatusCandidato[]{StatusCandidato.ENTREVISTADO,StatusCandidato.EM_ANALISE,StatusCandidato.SELECIONADO,StatusCandidato.NAO_SELECIONADO,StatusCandidato.PRE_SELECIONADO};
        StatusCandidato[] listaStatus = status == null ? statusEntrevistados : new StatusCandidato[]{status};
        
        Iterable<Entrevista> entrevistas = entrevistaRepository.findByIdCandidato_StatusIn(listaStatus);
                
        return entrevistas;
    }
    
}
