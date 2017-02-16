package br.experience.angular.server.webservice.model.contato;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import br.experience.angular.server.webservice.enums.ETipoRetornoTO;

@XmlAccessorType(XmlAccessType.FIELD)
public class RetornoDeletarContatoTO {
	
	@XmlElement(nillable=true, required=true)
	private ETipoRetornoTO tipoRetorno;
	
	@XmlElement(nillable=false, required=false)
	private Integer codigoMensagem;
	
	@XmlElement(nillable=false, required=false)
	private String mensagem;

	public ETipoRetornoTO getTipoRetorno() {
		return tipoRetorno;
	}

	public void setTipoRetorno(ETipoRetornoTO tipoRetorno) {
		this.tipoRetorno = tipoRetorno;
	}

	public Integer getCodigoMensagem() {
		return codigoMensagem;
	}

	public void setCodigoMensagem(Integer codigoMensagem) {
		this.codigoMensagem = codigoMensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
