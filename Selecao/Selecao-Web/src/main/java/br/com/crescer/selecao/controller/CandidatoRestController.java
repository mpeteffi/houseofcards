package br.com.crescer.selecao.controller;

import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.entities.Datahora;
import br.com.crescer.selecao.entities.Entrevista;
import br.com.crescer.selecao.entities.Grupodeprovas;
import br.com.crescer.selecao.entities.Informacao;
import br.com.crescer.selecao.entities.Processoseletivo;
import br.com.crescer.selecao.entities.Usuario;
import br.com.crescer.selecao.entities.enums.StatusCandidato;
import br.com.crescer.selecao.webservices.WebService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import static javafx.scene.input.KeyCode.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Andrews Kuhn
 */
@Controller
public class CandidatoRestController {

    @Autowired
    WebService webService;
    
    @RequestMapping(value = "/rest/candidato/nomes", method = RequestMethod.GET)
    public @ResponseBody ArrayList<HashMap<Object,Object>> nomeCandidatos(@RequestParam("q") String nome) {
        Iterable<Candidato> candidatos = webService.getCandidatoService().todosNomesComecam(nome);
        ArrayList<HashMap<Object,Object>> nomes = new ArrayList<>();
        for(Candidato candidato: candidatos){
            String nomeCandidato = candidato.getNome();
            Integer id = candidato.getIdCandidato();
            nomes.add(
                    new HashMap<Object,Object>()
                    {
                        {
                            put("nome",nomeCandidato);
                            put("id",id);
                        }                    
                    }
            );
        }
        return nomes;
    }
   
    @RequestMapping(value="/rest/candidato/post",method = RequestMethod.POST)
    public  @ResponseBody String salvarCandidatoPOST(@RequestParam(required = true)Integer idCandidato,@RequestParam(required = true)Integer idInformacao,String nome,String email,String  instituicaoEnsino,String curso,String previsaoFormatura,StatusCandidato status, String telefone,@DateTimeFormat(pattern = "dd/MM/yyyy") Date dataNascimento, String cidade,String urlLinkedin) {
        String senha = webService.getCandidatoService().buscarInformacoesDeCandidato(new Candidato(idCandidato)).getSenha();
        Candidato candidato = new Candidato(idCandidato,nome,email,instituicaoEnsino,curso,previsaoFormatura,status);
        Processoseletivo processo = webService.getProcessoseletivoService().buscarProcessoAtual();
        webService.getCandidatoService().atualizarInformacao(new Informacao(idInformacao,
                                                                                    telefone,
                                                                                    dataNascimento,
                                                                                    cidade,
                                                                                    urlLinkedin,
                                                                                    senha,
                                                                                    candidato,
                                                                                    processo));   
        return "Sucesso";
    }
    
    @RequestMapping(value="/rest/entrevista/post",method = RequestMethod.POST)
    public @ResponseBody String salvarEntrevistaPOST(@RequestParam(required = true)Integer idEntrevista,Integer idGrupoDeProvas, String pareceRh,String pareceTecnico,Double provaG36,Double provaAc,Double provaTecnica  ) {
        
        Entrevista entrevista =  webService.getEntrevistaService().findByIdEntrevista(idEntrevista);
        Grupodeprovas grupoDeProvas =  webService.getGrupoDeProvasService().findByIdGrupoDeProvas(idGrupoDeProvas);
        Usuario usuario = webService.getUsuarioLogadoService().buscarUsuarioLogado();
        
        entrevista.setIdGrupoDeProvas(grupoDeProvas);
        entrevista.setParecerRh(pareceRh);
        entrevista.setParecerTecnico(pareceTecnico);
        entrevista.setProvaAc(provaAc);
        entrevista.setProvaG36(provaG36);
        entrevista.setProvaTecnica(provaTecnica);
        entrevista.setIdUsuario(usuario);
        if(grupoDeProvas != null){
            entrevista.getIdCandidato().setStatus(StatusCandidato.PROVA_AGENDADA);
        }
        
        webService.getEntrevistaService().salvarEntrevista(entrevista);
        
        return "Sucesso";
    }
}
