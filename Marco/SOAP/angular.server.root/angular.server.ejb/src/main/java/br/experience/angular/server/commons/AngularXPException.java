package br.experience.angular.server.commons;

import br.experience.angular.server.enums.EMensagensAngularXP;

public class AngularXPException extends RuntimeException {

	private static final long serialVersionUID = -3615322723818110490L;
	
	private Integer codigo;
	private String mensagem;
	
	public AngularXPException(Integer codigo, String mensagem) {
		this.codigo = codigo;
		this.mensagem = mensagem;
	}
	
	public AngularXPException(EMensagensAngularXP mensagemAngularXP) {
		this.codigo = mensagemAngularXP.getCodigo();
		this.mensagem = mensagemAngularXP.getMensagem();
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
