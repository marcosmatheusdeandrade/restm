package br.experience.angular.server.webservice.model.contato;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class SalvarContatoTO {
	
	@XmlElement(required=true, nillable=true)
	private ContatoTO contato;

	public ContatoTO getContato() {
		return contato;
	}

	public void setContato(ContatoTO contato) {
		this.contato = contato;
	}

}
