package br.experience.angular.server.enums;

public enum EMensagensAngularXP {
	
	// CONTATO
	CONTATO_001(1, "Contato deve ser informado"),
	CONTATO_002(2, "Contato a ser alterado não existe"),
	CONTATO_003(3, "Nome do contato deve ser informado"),
	CONTATO_004(4, "Telefone do contato deve ser informado"),
	CONTATO_005(5, "Operadora do contato deve ser informado"),
	CONTATO_006(6, "Nome do conato deve conter no maximo 60 caracteres"),
	CONTATO_007(7, "Telefone do contato deve conter apenas números"),
	CONTATO_008(8, "A operadora deve estar cadastrada no sistema"),
	CONTATO_009(9, "O id do contato não foi informado"),
	CONTATO_010(10, "Contato a ser deletado não existe"),
	
	;
	
	private Integer codigo;
	private String mensagem;

	private EMensagensAngularXP(Integer codigo, String mensagem) {
		this.codigo = codigo;
		this.mensagem = mensagem;
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
