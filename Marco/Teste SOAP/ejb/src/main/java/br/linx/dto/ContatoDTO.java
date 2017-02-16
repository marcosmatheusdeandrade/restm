package br.linx.dto;

import java.io.Serializable;

public class ContatoDTO implements Serializable{

	private Integer id;
	private String nome;
	private String telefone;
	private OperadoraDTO operadoraDTO;
	
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
	public OperadoraDTO getOperadoraDTO() {
		return operadoraDTO;
	}
	public void setOperadoraDTO(OperadoraDTO operadoraDTO) {
		this.operadoraDTO = operadoraDTO;
	}
}
