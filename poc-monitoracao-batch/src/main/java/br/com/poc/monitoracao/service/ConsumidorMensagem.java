
package br.com.poc.monitoracao.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.scheduling.annotation.Async;


/**
 * Recebe a mensagem e persiste no Redis
 * @author mforti
 *
 */

@Async
public class ConsumidorMensagem implements MessageHandler{

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumidorMensagem.class);

    @Autowired
    private PersistirMensagemService service;
    
    @Override
    public void handleMessage(final Message<?> message) {
            this.processaMensagem(message);

    }

    private void processaMensagem(final Message<?> message) {
        LOGGER.debug("Processando mensagem");
        if (message.getHeaders().get(KafkaHeaders.RECEIVED_MESSAGE_KEY, String.class) == null) {
            LOGGER.debug("Mensagem sem chave sera descartada.");
            this.marcaLeitura(message);
            return;
        }
        try {
            this.service.process(message);
            this.marcaLeitura(message);
        } catch (Exception e) {
            this.marcaLeitura(message);
            LOGGER.error("Erro na persistência: {} ", e.getMessage());
        }
    }

    private void marcaLeitura(final Message<?> message) {
        Acknowledgment acknowledgment = message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
        acknowledgment.acknowledge();
    }


}
