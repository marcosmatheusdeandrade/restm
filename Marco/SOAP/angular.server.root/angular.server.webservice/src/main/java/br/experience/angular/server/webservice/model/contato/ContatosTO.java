package br.experience.angular.server.webservice.model.contato;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ContatosTO {

	@XmlElement(nillable=false, required=false)
	private ArrayList<ContatoTO> contato = new ArrayList<>();

	public ArrayList<ContatoTO> getContato() {
		return contato;
	}

	public void setContato(ArrayList<ContatoTO> contato) {
		this.contato = contato;
	}

}
