package br.com.crescer.selecao.controller;

import br.com.crescer.selecao.entities.Datahora;
import br.com.crescer.selecao.entities.Entrevista;
import br.com.crescer.selecao.entities.Grupodeprovas;
import br.com.crescer.selecao.webservices.WebService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    
    @RequestMapping(value = "/rest/agendamento/delete", method = RequestMethod.DELETE)
    public void deletarAgendamento(Integer id) {
        //webService.getDataHoraService().deletarAgendamento(new Datahora(1));
    }
            
    @RequestMapping(value = "/rest/agendamento/update", method = RequestMethod.PUT)
    public @ResponseBody String updateAgendamento(Integer id,@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")Date end,@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")Date start,Boolean allDay,String title) {
        Datahora dataHora = webService.getDataHoraService().findById(new Datahora(id));
        dataHora.setTitulo(title);
        dataHora.setDataHoraInicial(start);
        dataHora.setDataHoraFinal(end);
        dataHora.setTodoDia(allDay);
        webService.getDataHoraService().salvarAgendamento(dataHora);
        return "Sucesso";
    }
    
    @RequestMapping(value = "/rest/agendamento/novo", method = RequestMethod.POST)
    public @ResponseBody Integer novoAgendamento(String title,Integer idCandidato,@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")Date start,@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")Date end,boolean allDay) {
        
        if(idCandidato != null){
            return webService
                        .getEntrevistaService()
                        .salvarEntrevista(
                            new Entrevista(
                                idCandidato,
                                new Datahora(title,start,end,allDay,"ENTREVISA_RH")
                            )
                        )
                        .getIdDataHora()
                        .getIdDataHora();
        }else{
            return webService
                        .getGrupoDePovasService()
                        .salvar(
                            new Grupodeprovas(
                                new Datahora(title,start,end,allDay,"GRUPO_PROVA")
                            )
                        )
                        .getIdDataHora()
                        .getIdDataHora();
        }       
    }

}
