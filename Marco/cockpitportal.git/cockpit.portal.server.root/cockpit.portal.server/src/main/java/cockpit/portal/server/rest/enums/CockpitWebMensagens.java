package cockpit.portal.server.rest.enums;

public enum CockpitWebMensagens {

	MENSAGEM_FALHA_AO_LOGAR("Falha ao tentar Logar no Sistema."),
	MENSAGEM_USUARIO_NAO_LOGADO("Usuário não logado no sistema."),
	MENSAGEM_ERRO_DESCONHECIDO("Erro desconhecido.");
	
	private String mensagem;
	
	private CockpitWebMensagens(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}
	
}
