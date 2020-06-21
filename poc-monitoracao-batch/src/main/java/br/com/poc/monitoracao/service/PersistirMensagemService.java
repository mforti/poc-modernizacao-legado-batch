
package br.com.poc.monitoracao.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import br.com.poc.monitoracao.converter.MonitoracaoConverter;
import br.com.poc.monitoracao.dominio.MonitoracaoRedis;
import br.com.poc.monitoracao.repository.MonitoracaoRepository;


/**
 * Serviço simples para persistência no Redis.
 *
 * @author mforti
 */
@Component
public class PersistirMensagemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersistirMensagemService.class);
    
    @Autowired
    private MonitoracaoRepository repository;
    
    @Autowired
    private MonitoracaoConverter converter;
    
    public void process(final Message<?> message) {
        LOGGER.debug("Processando Mensagem: {}", message.getPayload());
        MonitoracaoRedis monitoracaoRedis = this.converter.converte((String) message.getPayload());
        this.repository.save(monitoracaoRedis);
    }

    
}