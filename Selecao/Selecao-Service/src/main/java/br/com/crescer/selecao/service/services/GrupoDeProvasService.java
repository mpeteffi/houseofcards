package br.com.crescer.selecao.service.services;

import br.com.crescer.selecao.entities.Datahora;
import br.com.crescer.selecao.entities.Grupodeprovas;
import br.com.crescer.selecao.service.repository.GrupoDeProvasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andrews Kuhn
 */
@Service
public class GrupoDeProvasService {    

    @Autowired
    GrupoDeProvasRepository grupoDeProvasRepositorio;
    
    public Grupodeprovas salvar(Grupodeprovas grupo){
        return grupoDeProvasRepositorio.save(grupo);
    }
    
    
    public void deleteByDataHora(Datahora data){
        grupoDeProvasRepositorio.deleteByIdDataHora(data);
    }    
}
