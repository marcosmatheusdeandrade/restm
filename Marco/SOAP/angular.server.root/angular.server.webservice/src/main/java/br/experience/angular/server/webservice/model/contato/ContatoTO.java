package br.experience.angular.server.webservice.model.contato;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import br.experience.angular.server.webservice.model.operadora.OperadoraTO;

@XmlAccessorType(XmlAccessType.FIELD)
public class ContatoTO {

	@XmlElement(required=true, nillable=true)
	private Integer id;

	@XmlElement(required=true, nillable=true)
	private String nome;

	@XmlElement(required=true, nillable=true)
	private String telefone;

	@XmlElement(required=true, nillable=false)
	private Date data;

	@XmlElement(required=true, nillable=true)
	private OperadoraTO operadora;

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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public OperadoraTO getOperadora() {
		return operadora;
	}

	public void setOperadora(OperadoraTO operadora) {
		this.operadora = operadora;
	}

}
