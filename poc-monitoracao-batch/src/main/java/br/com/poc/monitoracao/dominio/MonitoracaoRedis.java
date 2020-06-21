
package br.com.poc.monitoracao.dominio;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;



@RedisHash("MONITORACAO")
public class MonitoracaoRedis {

	@Id
	private String id;
	private String tipoTransacao;
    private String nsuTransacao;
    private String dataHoraTransacao;
    private String valorTransacao;
    private String produto;
    private String codigoResposta;
	
	public MonitoracaoRedis() {
		// Do nothing (sonar)
	}
	
	public MonitoracaoRedis(final String tipoTransacao, final String nsuTransacao, final String dataHoraTransacao) {
		this.id = this.geraChave(tipoTransacao, nsuTransacao, dataHoraTransacao);
		this.tipoTransacao = tipoTransacao;
		this.nsuTransacao = nsuTransacao;
		this.dataHoraTransacao = dataHoraTransacao;
	}
	

	
	private String geraChave(final String tipoTransacao, final String nsuTransacao, final String dataHoraTransacao) {
		StringBuilder builder = new StringBuilder();

		builder.append(tipoTransacao).append(nsuTransacao).append(dataHoraTransacao);
		return  builder.toString();
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	public String getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(String tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	public String getNsuTransacao() {
		return nsuTransacao;
	}

	public void setNsuTransacao(String nsuTransacao) {
		this.nsuTransacao = nsuTransacao;
	}

	public String getDataHoraTransacao() {
		return dataHoraTransacao;
	}

	public void setDataHoraTransacao(String dataHoraTransacao) {
		this.dataHoraTransacao = dataHoraTransacao;
	}

	public String getValorTransacao() {
		return valorTransacao;
	}

	public void setValorTransacao(String valorTransacao) {
		this.valorTransacao = valorTransacao;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodigoResposta() {
		return codigoResposta;
	}

	public void setCodigoResposta(String codigoResposta) {
		this.codigoResposta = codigoResposta;
	}

	
}
