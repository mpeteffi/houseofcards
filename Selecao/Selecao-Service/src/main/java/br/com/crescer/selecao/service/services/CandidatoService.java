package br.com.crescer.selecao.service.services;

import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.entities.Informacao;
import br.com.crescer.selecao.entities.Processoseletivo;
import br.com.crescer.selecao.service.repository.CandidatoRepository;
import br.com.crescer.selecao.service.repository.InformacaoRepository;
import br.com.crescer.selecao.service.repository.ProcessoseletivoRepository;
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
    ProcessoseletivoRepository processoseletivoRepository;
    
    @Autowired
    InformacaoRepository informacaoRepository;
    
    public Informacao saveInformacao(Informacao informacao){
        return informacaoRepository.save(informacao);
    }
    
    public Informacao salvarInformacoes(Informacao informacao, Processoseletivo processo){
        informacao.getIdcandidato().setStatus("AGUARDANDO CONTATO");
        informacao.setIdprocessoseletivo(processo);
        String senha = new BCryptPasswordEncoder().encode(informacao.getSenha());
        informacao.setSenha(senha);
        
        try {
            candidatoRepository.save(informacao.getIdcandidato());
            return informacaoRepository.save(informacao);
        } catch (Exception e){
            return null;
        }
    } 
    
    public Candidato salvar(Candidato candidato){
        try {
            return candidatoRepository.save(candidato);
        } catch (Exception e){
            return null;
        }
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
    
    public Candidato findByIdCandidato(int id){
        return candidatoRepository.findOneByIdcandidato(id);
    }
    
    public Page<Informacao> findByFilters(String edicao, String status, String nome, String email, String telefone, int pagina){
        if(status == null){status = "";}
        if(email == null){email = "";}
        if(telefone == null){telefone = "";}
        if(nome == null){nome = "";}
        if(edicao == null){edicao = processoseletivoRepository.findTopByOrderByEdicaoDesc().getEdicao();}
        
        Pageable pageable = new PageRequest(pagina, 5, Sort.Direction.DESC, "idinformacao");
        return informacaoRepository.findByIdprocessoseletivo_EdicaoContainingIgnoreCaseAndIdcandidato_StatusContainingIgnoreCaseAndIdcandidato_NomeContainingIgnoreCaseAndIdcandidato_EmailContainingIgnoreCaseAndTelefoneContainingIgnoreCase(edicao, status, nome, email, telefone, pageable);
    }
    //TODO:Mover informação para um service dela mesmo
    public Informacao findInformcaoesDoCandidato(Candidato candidato){
        return informacaoRepository.findByIdCandidato(candidato);
    }
    
    public void salvarInformcaoesDoCandidato(Informacao informacao){
        informacaoRepository.save(informacao);
    }
}
