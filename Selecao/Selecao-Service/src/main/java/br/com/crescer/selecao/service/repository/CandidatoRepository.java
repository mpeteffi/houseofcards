package br.com.crescer.selecao.service.repository;

import br.com.crescer.selecao.entities.Candidato;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Murillo
 */
public interface CandidatoRepository extends PagingAndSortingRepository<Candidato, Long> {
    
    Candidato findOneByIdCandidato(int idcandidato);
    Iterable<Candidato> findByStatus(String status);
    
}