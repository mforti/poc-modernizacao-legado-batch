package br.com.poc.monitoracao.config;

import java.nio.ByteBuffer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.kafka.inbound.KafkaMessageDrivenChannelAdapter;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.ContainerProperties.AckMode;

import br.com.poc.monitoracao.service.ConsumidorMensagem;

/** 
 * Classe consumidora do kafka
 * @author mforti
 */
@Configuration
public class ConsumerConfig {
    
    @Value("${monitoracao.topic}")
    private String topic;
    
    @Value("${monitoracao.kafka.concurrency}")
    private int concurrency;
    
    @Bean
    public DirectChannel consumingChannel() {
        return new DirectChannel();
    }
    
    @Bean
    @DependsOn("consumingChannel")
    public KafkaMessageDrivenChannelAdapter<String, ByteBuffer> kafkaMessageDrivenChannelAdapter(
                    final ConcurrentMessageListenerContainer<String, ByteBuffer> listener,
                    final DirectChannel consumingChannel) {
        KafkaMessageDrivenChannelAdapter<String, ByteBuffer> kafkaMessageDrivenChannelAdapter = new KafkaMessageDrivenChannelAdapter<>(
                        listener);
        kafkaMessageDrivenChannelAdapter.setOutputChannelName("consumingChannel");
        kafkaMessageDrivenChannelAdapter.setOutputChannel(consumingChannel);

        return kafkaMessageDrivenChannelAdapter;
    }
    
    @Bean
    @ServiceActivator(inputChannel = "consumingChannel")
    public ConsumidorMensagem messageConsumerHandler() {
        return new ConsumidorMensagem();
    }

    /**
     * @param consumerFactory
     *            Factory gerado pelo spring usando os parametros do yml
     * @return
     */
    @Bean
    public ConcurrentMessageListenerContainer<String, ByteBuffer> kafkaListenerContainer(
                    final ConsumerFactory<String, ByteBuffer> consumerFactory) {
        ContainerProperties containerProps = new ContainerProperties(this.topic);
        containerProps.setAckMode(AckMode.MANUAL_IMMEDIATE);
        ConcurrentMessageListenerContainer<String, ByteBuffer> container = new ConcurrentMessageListenerContainer<>(
                        consumerFactory, containerProps);
        container.setAutoStartup(true);
        container.setConcurrency(this.concurrency);
        return container;
    }
  
}

