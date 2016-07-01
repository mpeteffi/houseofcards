package br.com.crescer.selecao.service.repository;

import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.entities.Informacao;
import br.com.crescer.selecao.entities.enums.StatusCandidato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Murillo
 */
public interface InformacaoRepository extends PagingAndSortingRepository<Informacao, Long> {
    
    Page<Informacao> findByIdProcessoSeletivo_EdicaoContainingIgnoreCaseAndIdCandidato_StatusInAndIdCandidato_NomeContainingIgnoreCaseAndIdCandidato_EmailContainingIgnoreCaseAndTelefoneContainingIgnoreCase(String edicao, StatusCandidato[] status, String nome, String email, String telefone, Pageable pegeable);
    Informacao findByIdCandidato(Candidato candidato);
}