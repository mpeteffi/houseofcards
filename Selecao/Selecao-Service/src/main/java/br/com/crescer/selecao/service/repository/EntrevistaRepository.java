package br.com.crescer.selecao.service.repository;

import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.entities.Entrevista;
import br.com.crescer.selecao.entities.Grupodeprovas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.crescer.selecao.entities.enums.StatusCandidato;
import java.util.Date;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Murillo
 */
public interface EntrevistaRepository extends PagingAndSortingRepository<Entrevista, Integer> {
    
    Entrevista findByIdEntrevista(Integer id);
    
    Entrevista findOneByIdCandidato(Candidato candidato);
    
    Iterable<Entrevista> findAllByIdCandidato(Candidato candidato);
    
    Page<Entrevista> findByIdCandidato_StatusIn(StatusCandidato[] status,Pageable pegeable);
    
    Iterable<Entrevista> findByIdCandidato_StatusIn(StatusCandidato[] status);
    
    Page<Entrevista> findByIdDataHora_DataHoraInicialAfter(Date dataHoraInicial, Pageable pageable);
    
    Integer countByIdGrupoDeProvas(Grupodeprovas idGrupoDeProvas);
    
    Long countByIdGrupoDeProvasIsNotNull();
    Long countByParecerRhIsNotNull();
}
