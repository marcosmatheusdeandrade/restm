package cockpit.portal.server.rest.model;

import java.util.Date;

import cockpit.portal.server.rest.enums.NivelAcesso;

public class Administrador {

	private Integer id;
	private String nome;
	private String login; 
	private String senha; //Mínimo de 6 caracteres
	private NivelAcesso nivelAcesso; //Capacidade de ação do usuário
	private Date dataCadastro; //Data com hora que o usuário foi cadastrado
	private String email; //Será o e-mail Softpharma/Linx/Big
	
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public NivelAcesso getNivelAcesso() {
		return nivelAcesso;
	}

	public void setNivelAcesso(NivelAcesso nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date cadastro) {
		this.dataCadastro = cadastro;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}
