package cockpit.portal.server.rest.model;

public class Rede {

	private Integer id;
	
	private String idInstalacaoCockpit;
	
	private String descricao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdInstalacaoCockpit() {
		return idInstalacaoCockpit;
	}

	public void setIdInstalacaoCockpit(String idInstalacaoCockpit) {
		this.idInstalacaoCockpit = idInstalacaoCockpit;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
