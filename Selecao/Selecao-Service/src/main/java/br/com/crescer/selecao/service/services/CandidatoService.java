package br.com.crescer.selecao.service.services;

import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.entities.Informacao;
import br.com.crescer.selecao.entities.Processoseletivo;
import br.com.crescer.selecao.entities.enums.StatusCandidato;
import br.com.crescer.selecao.service.repository.CandidatoRepository;
import br.com.crescer.selecao.service.repository.InformacaoRepository;
import br.com.crescer.selecao.service.repository.ProcessoseletivoRepository; 
import java.util.Calendar;
import java.util.Date;
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
    EmailService emailService;
    
    @Autowired
    ProcessoseletivoRepository processoseletivoRepository;
    
    @Autowired
    InformacaoRepository informacaoRepository;
    
    public Informacao atualizarInformacao(Informacao informacao){
        return informacaoRepository.save(informacao);
    }
    
    public Candidato salvarCandidato(Candidato candidato){
        
        if(candidato.getStatus() == null){
            candidato.setStatus(StatusCandidato.INICIAL);            
        }
        
        try {
            Candidato c = candidatoRepository.save(candidato);
            emailService.enviarEmailParaConfirmacaoDeInteresse(candidato);
            return c;
        } catch (Exception e) {
            return null;
        }
    }
    
    public Informacao buscarInformacoesDeCandidato(Candidato candidato){
        return informacaoRepository.findByIdCandidato(candidato);
    }
    
    public Informacao salvarInformacao(Informacao informacao, Processoseletivo processo){
        
        informacao.getIdCandidato().setStatus(StatusCandidato.AGUARDANDO_CONTATO);
        informacao.setIdProcessoSeletivo(processo);
        String senha = new BCryptPasswordEncoder().encode(informacao.getSenha());
        informacao.setSenha(senha);
        
        try {
            candidatoRepository.save(informacao.getIdCandidato());
            return informacaoRepository.save(informacao);
        } catch (Exception e){
            return null;
        }
    }
    
    public Iterable<Candidato> buscarCandidatosPorStatus(String status){
        return candidatoRepository.findByStatus(status);
    }
    
    public Candidato buscarCandidatoPorId(Integer id){
        if (id == null){ id = 0;}
        return candidatoRepository.findOneByIdCandidato(id);
    }
    
    public Page<Informacao> buscarCandidatosPorFiltros(String edicao, StatusCandidato status, String nome, String email, String telefone, Integer pagina){
        nome = nome != null ? nome : "";
        email = email != null ? email : "";
        pagina = pagina != null ? pagina : 0;
        telefone = telefone != null ? telefone : "";
        edicao = edicao != null ? edicao : processoseletivoRepository.findTopByOrderByEdicaoDesc().getEdicao();
        StatusCandidato[] listaStatus = status == null ? StatusCandidato.values() : new StatusCandidato[]{status};
        
        Pageable pageable = new PageRequest(pagina, 10, Sort.Direction.DESC, "idInformacao");
        Page<Informacao> candidatos = informacaoRepository.findByIdProcessoSeletivo_EdicaoContainingIgnoreCaseAndIdCandidato_StatusInAndIdCandidato_NomeContainingIgnoreCaseAndIdCandidato_EmailContainingIgnoreCaseAndTelefoneContainingIgnoreCase(edicao, listaStatus, nome, email, telefone, pageable);
        
        for (Informacao i : candidatos) {
            i.setDataNascimento(tempoDecorrido(i.getDataNascimento()));
        }
        
        return candidatos;
    }
    
    private Date tempoDecorrido(Date date) {
        Calendar c = Calendar.getInstance();
        if (date == null) {
            return null;
        }
        long diff = new Date().getTime() - date.getTime();
        c.setTime(new Date(diff));

        return c.getTime();
    }
}
