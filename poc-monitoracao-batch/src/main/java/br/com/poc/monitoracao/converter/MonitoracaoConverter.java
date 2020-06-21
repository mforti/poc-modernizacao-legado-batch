package br.com.poc.monitoracao.converter;

import org.springframework.stereotype.Component;

import br.com.poc.monitoracao.dominio.Mensagem;
import br.com.poc.monitoracao.dominio.MonitoracaoRedis;

/**
 * Conversores de {@link Mensagem} para {@link MonitoracaoRedis}
 *
 * @author mforti
 */
@Component
public class MonitoracaoConverter {
	
	public MonitoracaoRedis converte(final String mensagemKafka) {
		Mensagem mensagem = Mensagem.parse(mensagemKafka);
		MonitoracaoRedis monitoracaoRedis = new MonitoracaoRedis(mensagem.getTipoTransacao(), mensagem.getNsuTransacao(),
				mensagem.getDataHoraTransacao());
		
		monitoracaoRedis.setValorTransacao(mensagem.getValorTransacao());
		monitoracaoRedis.setProduto(mensagem.getProduto());
		monitoracaoRedis.setCodigoResposta(mensagem.getCodigoResposta());
	
		return monitoracaoRedis;
		
	}

}
