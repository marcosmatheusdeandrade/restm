package cockpit.portal.server.infra.exception;

/**
 * Os erros referentes a regras de negócio para o aplicativo 
 * deverão passar por esta exception.
 *
 * @since 03/08/2015
 * @author Lucas Zulpo Pasa
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -7781308670176239312L;
	
	private String message;

	public BusinessException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
