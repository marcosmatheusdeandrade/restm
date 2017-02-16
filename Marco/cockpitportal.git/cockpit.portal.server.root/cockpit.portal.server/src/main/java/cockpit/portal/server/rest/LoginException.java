package cockpit.portal.server.rest;

import javax.ws.rs.core.Response;

import org.jboss.resteasy.spi.ReaderException;

import cockpit.portal.server.rest.enums.CockpitWebMensagens;

public class LoginException extends ReaderException {

	private static final long serialVersionUID = 4412025503048235894L;

	public LoginException() {
		super(CockpitWebMensagens.MENSAGEM_FALHA_AO_LOGAR.getMensagem(), 
				Response.Status.UNAUTHORIZED.getStatusCode());
	}
	
	public LoginException(String mensagem) {
		super(mensagem, Response.Status.UNAUTHORIZED.getStatusCode());
	}

}
