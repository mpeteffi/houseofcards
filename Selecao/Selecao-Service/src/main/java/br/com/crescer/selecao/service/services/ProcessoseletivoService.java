package br.com.crescer.selecao.service.services;

import br.com.crescer.selecao.service.repository.ProcessoseletivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Murillo
 */
@Service
public class ProcessoseletivoService {
    
    @Autowired
    ProcessoseletivoRepository processoseletivoRepository;
}
