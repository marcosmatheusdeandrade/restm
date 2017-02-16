package br.experience.angular.server.webservice.model.operadora;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import br.experience.angular.server.webservice.enums.ETipoRetornoTO;

@XmlAccessorType(XmlAccessType.FIELD)
public class RetornoConsultarOperadorasTO {
	
	@XmlElement(nillable=true, required=true)
	private ETipoRetornoTO tipoRetorno;
	
	@XmlElement(nillable=false, required=false)
	private Integer codigoMensagem;
	
	@XmlElement(nillable=false, required=false)
	private String mensagem;

	@XmlElement(nillable=false, required=false)
	private OperadorasTO operadoras;

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

	public OperadorasTO getOperadoras() {
		return operadoras;
	}

	public void setOperadoras(OperadorasTO operadoras) {
		this.operadoras = operadoras;
	}

}
