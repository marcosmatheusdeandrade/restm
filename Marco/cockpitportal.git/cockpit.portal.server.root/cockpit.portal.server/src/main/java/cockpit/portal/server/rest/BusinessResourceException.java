package cockpit.portal.server.rest;

import javax.ws.rs.core.Response;

import org.jboss.resteasy.spi.ReaderException;

import cockpit.portal.server.rest.enums.CockpitWebMensagens;

public class BusinessResourceException extends ReaderException {

	private static final long serialVersionUID = 4412025503048235894L;

	public BusinessResourceException() {
		super(CockpitWebMensagens.MENSAGEM_ERRO_DESCONHECIDO.getMensagem(), 
				Response.Status.BAD_REQUEST.getStatusCode());
	}
	
	public BusinessResourceException(String mensagem) {
		super(mensagem, Response.Status.BAD_REQUEST.getStatusCode());
	}
}
