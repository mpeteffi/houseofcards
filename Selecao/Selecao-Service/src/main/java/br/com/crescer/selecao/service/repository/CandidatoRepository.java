package br.com.crescer.selecao.service.repository;

import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.entities.enums.StatusCandidato;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Murillo
 */
public interface CandidatoRepository extends PagingAndSortingRepository<Candidato, Integer> {
    
    Candidato findOneByIdCandidato(int idcandidato);
    Iterable<Candidato> findByStatus(String status);
    Iterable<Candidato> findTop5ByNomeIgnoreCaseStartingWith(String nome);
    Integer countByStatus(StatusCandidato status);
}