package br.com.crescer.selecao.service.services;

import br.com.crescer.selecao.service.repository.EntrevistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Murillo
 */
@Service
public class EntrevistaService {
    
    @Autowired
    EntrevistaRepository entrevistaRepository;
}
