package br.com.crescer.selecao.service.services;

import br.com.crescer.selecao.entities.Datahora;
import br.com.crescer.selecao.entities.Grupodeprovas;
import br.com.crescer.selecao.entities.Processoseletivo;
import br.com.crescer.selecao.entities.enums.TipoAgendamento;
import br.com.crescer.selecao.service.repository.GrupoDeProvasRepository;
import java.util.ArrayList;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andrews Kuhn
 */
@Service
public class GrupoDeProvasService {    

    @Autowired
    GrupoDeProvasRepository grupoDeProvasRepositorio;
    
    @Autowired
    ProcessoseletivoService processoSeletivoService;
    
    @Autowired
    EntrevistaService entrevistaService;
    
    @Autowired
    DataHoraService dataHoraService;
    
    public Grupodeprovas salvar(Grupodeprovas grupo){
        
        try{
            return grupoDeProvasRepositorio.save(grupo);
        } catch (Exception e){
            return null;
        }
    }
    
    public Iterable<Grupodeprovas> buscarGrupoDeProvasEdicaoAtual(){
        Iterable<Grupodeprovas> grupos = null;
        if(processoSeletivoService.verificarExistenciaDeProcessoAtivo()){
            Processoseletivo crescerAtual= processoSeletivoService.buscarProcessoAtual();
            Iterable<Datahora> datas =  dataHoraService
                                        .findByTipoAndDataHoraInicialBetween(TipoAgendamento.GRUPO_PROVA,
                                                                             crescerAtual.getInicioSelecao(),
                                                                             crescerAtual.getFinalAula());
            grupos = grupoDeProvasRepositorio.findByIdDataHoraIn(datas);
        }
        return grupos;
    }
    
    public ArrayList<Integer> buscarEntrevistasPorGrupo(Iterable<Grupodeprovas> grupos){
        ArrayList<Integer> array = new ArrayList<>();
        for(Grupodeprovas grupo : grupos){
            array.add(entrevistaService.contarPorGrupo(grupo));
        }        
        return array;
    }
    
    @Transactional
    public void deletarGrupoDeProvasByIdDataHora(Datahora data) throws DataIntegrityViolationException{
         grupoDeProvasRepositorio.deleteByIdDataHora(data);
    }
}
