package br.com.crescer.selecao.service.services;

import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.entities.Informacao;
import br.com.crescer.selecao.entities.Processoseletivo;
import br.com.crescer.selecao.service.repository.CandidatoRepository;
import br.com.crescer.selecao.service.repository.InformacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Murillo
 */
@Service
public class CandidatoService {
    
    @Autowired
    CandidatoRepository candidatoRepository;
    
    @Autowired
    InformacaoRepository informacaoRepository;
    
    public Informacao salvarInformacoes(Informacao informacao,Processoseletivo processo){
        informacao.getIdcandidato().setStatus("AGUARDANDO CONTATO");
        informacao.setIdprocessoseletivo(processo);
        String senha = new BCryptPasswordEncoder().encode(informacao.getSenha());
        informacao.setSenha(senha);
        candidatoRepository.save(informacao.getIdcandidato());
        return informacaoRepository.save(informacao);
    } 
    
    public Candidato salvar(Candidato candidato){
        return candidatoRepository.save(candidato);
    }
    
    public Candidato save(Candidato candidato){
        try {
            candidato.setStatus("INICIAL");            
            return candidatoRepository.save(candidato);
        } catch (Exception e) {
            return null;
        }
    }
    
    public Iterable<Candidato> findByStatus(String status){
        return candidatoRepository.findByStatus(status);
    }
    
    public Page<Informacao> findByFilters(String status, String nome, String email, String telefone, int pagina){
        if(status == null){status = "";}
        if(email == null){email = "";}
        if(telefone == null){telefone = "";}
        if(nome == null){nome = "";}
        
        Pageable pageable = new PageRequest(pagina, 5, Sort.Direction.DESC, "idinformacao");
        return informacaoRepository.findByIdprocessoseletivo_EdicaoContainingIgnoreCaseAndIdcandidato_StatusContainingIgnoreCaseAndIdcandidato_NomeContainingIgnoreCaseAndIdcandidato_EmailContainingIgnoreCaseAndTelefoneContainingIgnoreCase("2016/02", status, nome, email, telefone, pageable);
        //return informacaoRepository.findByIdcandidato_StatusAndIdcandidato_NomeContainingIgnoreCaseAndIdcandidato_EmailContainingIgnoreCaseAndTelefoneContainingIgnoreCase(status, nome, email, telefone, pageable);
    }
}
