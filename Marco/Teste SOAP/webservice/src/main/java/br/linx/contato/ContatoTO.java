package br.linx.contato;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import br.linx.operadora.OperadoraTO;

@XmlAccessorType(XmlAccessType.FIELD)
public class ContatoTO {

	@XmlElement(nillable=false, required=true)
	private Integer id;
	
	@XmlElement(nillable=false, required=true)
	private String nome;
	
	@XmlElement(nillable=false, required=true)
	private String telefone;

	@XmlElement(nillable=false, required=true)
	private OperadoraTO operadora;
	
	public OperadoraTO getOperadoraTO() {
		return operadora;
	}

	public void setOperadoraTO(OperadoraTO operadora) {
		this.operadora = operadora;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
