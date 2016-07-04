package br.com.crescer.selecao.service.repository;

import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.entities.Entrevista;
import br.com.crescer.selecao.entities.enums.StatusCandidato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.crescer.selecao.entities.enums.StatusCandidato;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Murillo
 */
public interface EntrevistaRepository extends PagingAndSortingRepository<Entrevista, Long> {
    
    Entrevista findOneByIdCandidato(Candidato candidato);
    
    Iterable<Entrevista> findAllByIdCandidato(Candidato candidato);
    
    Page<Entrevista> findByIdCandidato_StatusIn(StatusCandidato[] status,Pageable pegeable);
    
    Iterable<Entrevista> findByIdCandidato_StatusIn(StatusCandidato[] status);
}
