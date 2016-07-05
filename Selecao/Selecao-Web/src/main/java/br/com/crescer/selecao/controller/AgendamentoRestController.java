package br.com.crescer.selecao.controller;

import br.com.crescer.selecao.entities.Datahora;
import br.com.crescer.selecao.entities.Entrevista;
import br.com.crescer.selecao.entities.Grupodeprovas;
import br.com.crescer.selecao.entities.enums.TipoAgendamento;
import br.com.crescer.selecao.webservices.WebService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Andrews Kuhn
 */
@Controller
public class AgendamentoRestController {

    @Autowired
    WebService webService;
    
    @RequestMapping(value = "/rest/agendamento/todos", method = RequestMethod.GET)
    public @ResponseBody Iterable<Datahora> todosAgendamentos() {
        return  webService.getDataHoraService().todosAgendamentos();
    }
    
    @RequestMapping(value = "/rest/agendamento/grupo-prova/delete", method = RequestMethod.POST)
    public ResponseEntity<String> deletarAgendamento(Integer id) {
         
        try{
            webService.getGrupoDeProvasService().deletarGrupoDeProvasByIdDataHora(new Datahora(id));
            return ResponseEntity.status(HttpStatus.OK).body("Sucesso");
        }catch(DataIntegrityViolationException e ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Há integrantes no grupo");
        }
    }
            
    @RequestMapping(value = "/rest/agendamento/update", method = RequestMethod.POST)
    public @ResponseBody String updateAgendamento(Integer id,@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")Date end,@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")Date start,boolean allDay,String title) {
        Datahora data = webService.getDataHoraService().findByIdDataHora(id);
        data.setTitulo(title);
        data.setDataHoraInicial(start);
        data.setDataHoraFinal(end);
        data.setTodoDia(allDay);        
        webService.getDataHoraService().salvarAgendamento(data);
        return "Sucesso";
    }
    
    @RequestMapping(value = "/rest/agendamento/novo", method = RequestMethod.POST)
   public @ResponseBody ResponseEntity<Object> novoAgendamento(String title,Integer idCandidato,@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")Date start,@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")Date end,boolean allDay) { 
        if(idCandidato != null){
           try{
             Integer id = webService.getEntrevistaService()
                                    .salvarEntrevista(
                                            idCandidato,
                                            new Datahora(title,start,end,allDay,TipoAgendamento.ENTREVISTA_RH),
                                            webService.getUsuarioLogadoService().buscarUsuarioLogado()
                                    );
            return ResponseEntity.status(HttpStatus.OK).body(id); 
           }catch(Exception e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); 
           }
        }else{ 
            Integer id =  webService
                            .getGrupoDeProvasService() 
                            .salvar( 
                                new Grupodeprovas(  
                                    new Datahora(title,start,end,allDay,TipoAgendamento.GRUPO_PROVA) 
                                ) 
                            ) 
                             .getIdDataHora() 
                             .getIdDataHora();
            return ResponseEntity.status(HttpStatus.OK).body(id); 
        }
   }

}
