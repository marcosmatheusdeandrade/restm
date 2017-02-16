package br.linx.contato;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import br.linx.enums.ETipoRetorno;

@XmlAccessorType(XmlAccessType.FIELD)
public class RetornoConsultarContatoPorId {

	@XmlElement(nillable=false, required=true)
	private ETipoRetorno tipoRetorno;
	
	@XmlElement(nillable=true, required=true)
	private ContatoTO contato;

	public ETipoRetorno getTipoRetorno() {
		return tipoRetorno;
	}

	public void setTipoRetorno(ETipoRetorno tipoRetorno) {
		this.tipoRetorno = tipoRetorno;
	}

	public ContatoTO getContato() {
		return contato;
	}

	public void setContato(ContatoTO contatoTO) {
		this.contato = contatoTO;
	}
}
