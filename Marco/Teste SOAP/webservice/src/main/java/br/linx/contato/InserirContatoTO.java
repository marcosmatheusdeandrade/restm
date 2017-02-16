package br.linx.contato;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class InserirContatoTO {

	@XmlElement(nillable=true, required=true)
	private ContatoTO contatoTO;

	public ContatoTO getContatoTO() {
		return contatoTO;
	}

	public void setContatoTO(ContatoTO contatoTO) {
		this.contatoTO = contatoTO;
	}
}
