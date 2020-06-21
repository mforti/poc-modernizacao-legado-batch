
package br.com.poc.monitoracao.dominio;

/**
 * Modelo para a Mensagem de Request 
 *
 * @author mforti
 */
public class Mensagem {

    private String tipoTransacao;
    private String nsuTransacao;
    private String dataHoraTransacao;
    private String valorTransacao;
    private String produto;
    private String codigoResposta;
   
    public Mensagem() {

    }
  
    public static Mensagem parse(final String mensagem) {
        return new Mensagem(mensagem);
    }


    public Mensagem(final String mensagem) {
    	
    	this.tipoTransacao = mensagem.substring(LayoutMensagem.TIPO_TRANSACAO.getPosicaoInicial(), LayoutMensagem.TIPO_TRANSACAO.getPosicaoFinal());
    	this.nsuTransacao = mensagem.substring(LayoutMensagem.NSU_TRANSACAO.getPosicaoInicial(), LayoutMensagem.NSU_TRANSACAO.getPosicaoFinal());
    	this.dataHoraTransacao = mensagem.substring(LayoutMensagem.DATA_HORA_TRANSACAO.getPosicaoInicial(), LayoutMensagem.DATA_HORA_TRANSACAO.getPosicaoFinal());
    	this.valorTransacao = mensagem.substring(LayoutMensagem.VALOR_TRANSACAO.getPosicaoInicial(), LayoutMensagem.VALOR_TRANSACAO.getPosicaoFinal());
    	this.produto =  mensagem.substring(LayoutMensagem.PRODUTO.getPosicaoInicial(), LayoutMensagem.PRODUTO.getPosicaoFinal());
    	this.codigoResposta = mensagem.substring(LayoutMensagem.CODIGO_RESPOSTA.getPosicaoInicial(), LayoutMensagem.PRODUTO.getPosicaoFinal());
    }

	public String getTipoTransacao() {
		return tipoTransacao;
	}

	public String getNsuTransacao() {
		return nsuTransacao;
	}

	public String getDataHoraTransacao() {
		return dataHoraTransacao;
	}

	public String getValorTransacao() {
		return valorTransacao;
	}

	public String getProduto() {
		return produto;
	}

	public String getCodigoResposta() {
		return codigoResposta;
	}




}
