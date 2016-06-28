package br.com.crescer.selecao.service.repository;

import br.com.crescer.selecao.entities.Informacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Murillo
 */
public interface InformacaoRepository extends PagingAndSortingRepository<Informacao, Long> {
    Page<Informacao> findByIdprocessoseletivo_EdicaoContainingIgnoreCaseAndIdcandidato_StatusContainingIgnoreCaseAndIdcandidato_NomeContainingIgnoreCaseAndIdcandidato_EmailContainingIgnoreCaseAndTelefoneContainingIgnoreCase(String edicao, String status, String nome, String email, String telefone, Pageable pegeable);
}