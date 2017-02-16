package cockpit.portal.server.infra.exception;

/**
 * Todos os erros referentes a problemas no servidor deverão lançar esta exceção,  
 *  
 * 
 * @since 03/08/2015
 * @author Lucas Zulpo Pasa
 */
public class ServerException extends RuntimeException {

	private static final long serialVersionUID = 5638932834078811797L;

	private String message;

	public ServerException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
