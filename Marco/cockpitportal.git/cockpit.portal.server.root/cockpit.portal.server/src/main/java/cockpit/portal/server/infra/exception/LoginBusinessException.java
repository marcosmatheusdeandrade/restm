package cockpit.portal.server.infra.exception;

public class LoginBusinessException  extends RuntimeException {

	private static final long serialVersionUID = -7156642771076761413L;

	private String message;

	public LoginBusinessException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
