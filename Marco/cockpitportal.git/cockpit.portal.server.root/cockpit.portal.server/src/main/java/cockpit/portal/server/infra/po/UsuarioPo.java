package cockpit.portal.server.infra.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class UsuarioPo implements Serializable{
	
	private static final long serialVersionUID = -1137935488141764662L;

    private static final String FIELD_NOME 							= "p_usua_nome";
    private static final String FIELD_LOGIN 						= "p_usua_login";
    private static final String FIELD_SENHA 						= "p_usua_senha";
    private static final String FIELD_DATA_CADASTRO 				= "p_usua_data_cadastro";
    private static final String FIELD_EMAIL 						= "p_usua_email";
    
    @Column(name = FIELD_NOME, nullable = false)
    private String nome;
    
    @Column(name = FIELD_LOGIN, nullable = false, unique = true)
    private String login;
    
    @Column(name = FIELD_SENHA, nullable = false)
    private String senha;
    
    @Column(name = FIELD_DATA_CADASTRO, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;
    
    @Column(name = FIELD_EMAIL, nullable = false)
    private String email;


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

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
