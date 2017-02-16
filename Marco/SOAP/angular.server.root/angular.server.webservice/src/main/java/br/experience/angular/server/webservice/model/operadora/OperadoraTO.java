package br.experience.angular.server.webservice.model.operadora;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class OperadoraTO {

	@XmlElement(required=true, nillable=true)
	private Integer id;
	
	@XmlElement(required=true, nillable=true)
	private String nome;
	
	@XmlElement(required=true, nillable=true)
	private Integer codigo;

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

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

}
