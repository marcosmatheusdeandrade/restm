package cockpit.portal.server.rest.model;

import java.util.Date;
import java.util.List;

public class Responsavel {
	
	private Integer id;

	private List<Rede> redes;
	
    private String nome;
    
    private String login;
    
    private String senha;
    
    private Date dataCadastro;
    
    private String email;
    
    private String cpf;
	
	private int numeroCodigosUsadosVendedor;
	
	private int numeroCodigosAdiquiridosVendedor;
	
	private int numeroCodigosUsadosDiretor;
	
	private int numeroCodigosAdiquiridosDiretor;

	public List<Rede> getRedes() {
		return redes;
	}

	public void setRedes(List<Rede> redes) {
		this.redes = redes;
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

	public int getNumeroCodigosUsadosVendedor() {
		return numeroCodigosUsadosVendedor;
	}

	public void setNumeroCodigosUsadosVendedor(int numeroCodigosUsadosVendedor) {
		this.numeroCodigosUsadosVendedor = numeroCodigosUsadosVendedor;
	}

	public int getNumeroCodigosAdiquiridosVendedor() {
		return numeroCodigosAdiquiridosVendedor;
	}

	public void setNumeroCodigosAdiquiridosVendedor(int numeroCodigosAdiquiridosVendedor) {
		this.numeroCodigosAdiquiridosVendedor = numeroCodigosAdiquiridosVendedor;
	}

	public int getNumeroCodigosUsadosDiretor() {
		return numeroCodigosUsadosDiretor;
	}

	public void setNumeroCodigosUsadosDiretor(int numeroCodigosUsadosDiretor) {
		this.numeroCodigosUsadosDiretor = numeroCodigosUsadosDiretor;
	}

	public int getNumeroCodigosAdiquiridosDiretor() {
		return numeroCodigosAdiquiridosDiretor;
	}

	public void setNumeroCodigosAdiquiridosDiretor(int numeroCodigosAdiquiridosDiretor) {
		this.numeroCodigosAdiquiridosDiretor = numeroCodigosAdiquiridosDiretor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
