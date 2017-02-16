package cockpit.portal.server.rest.model;

import java.util.ArrayList;
import java.util.List;

public class Diretor {

	private Integer id;
	private String cpf;
	private String nome;
	
	private List<DispositivoAcessoDiretor> dispositivos = new ArrayList<>();

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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<DispositivoAcessoDiretor> getDispositivos() {
		return dispositivos;
	}

	public void setDispositivos(List<DispositivoAcessoDiretor> dispositivos) {
		this.dispositivos = dispositivos;
	}
	
}
