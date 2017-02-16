package br.experience.angular.server.rest.services;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import br.experience.angular.server.dto.OperadoraDTO;
import br.experience.angular.server.rules.local.IOperadoraBeanLocal;

@Stateless
public class OperadoraRestService implements IOperadoraRestService {

	@EJB
	private IOperadoraBeanLocal operadoraBeanLocal;
	
	@Override
	public Response consultarOperadoras() {
		try{
			
			Collection<OperadoraDTO> operadorasConsultadas =
					operadoraBeanLocal.consultarOperadoras();
			
			return Response.ok(operadorasConsultadas).build();
			
		}catch(Exception e){
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

}
