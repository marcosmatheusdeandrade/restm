package br.experience.angular.server.dto;

import java.io.Serializable;

public class OperadoraDTO implements Serializable {

	private static final long serialVersionUID = 220324197888660143L;

	private Integer id;
	private String nome;
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
