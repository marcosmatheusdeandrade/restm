package br.experience.angular.server.dto;

import java.io.Serializable;
import java.util.Date;

public class ContatoDTO implements Serializable {

	private static final long serialVersionUID = -1102393375634039338L;

	private Integer id;
	private String nome;
	private String telefone;
	private Date data;
	private OperadoraDTO operadora;

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

	public OperadoraDTO getOperadora() {
		return operadora;
	}

	public void setOperadora(OperadoraDTO operadora) {
		this.operadora = operadora;
	}

}
