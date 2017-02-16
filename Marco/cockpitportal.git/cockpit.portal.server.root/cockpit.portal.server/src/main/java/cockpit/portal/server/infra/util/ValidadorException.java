package cockpit.portal.server.infra.util;

public class ValidadorException extends RuntimeException {
	private static final long serialVersionUID = -9018430680847830150L;
	
	private String mensagem;

	public ValidadorException(String _mensagem) {
		this.mensagem = _mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}
	
}
