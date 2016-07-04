package br.com.crescer.selecao.controller;

import br.com.crescer.selecao.entities.Candidato;
import br.com.crescer.selecao.entities.Datahora;
import br.com.crescer.selecao.entities.Entrevista;
import br.com.crescer.selecao.entities.Grupodeprovas;
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
   

}
