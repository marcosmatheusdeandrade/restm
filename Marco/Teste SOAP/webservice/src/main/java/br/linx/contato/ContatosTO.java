package br.linx.contato;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ContatosTO {

	@XmlElement(nillable=false, required=false)
	private List<ContatoTO> contatos = new ArrayList<>();

	public List<ContatoTO> getContatos() {
		return contatos;
	}

	public void setContatos(List<ContatoTO> contatos) {
		this.contatos = contatos;
	}
}
