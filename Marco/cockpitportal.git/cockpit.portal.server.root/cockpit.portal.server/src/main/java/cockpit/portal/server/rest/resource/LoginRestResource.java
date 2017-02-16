package cockpit.portal.server.rest.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cockpit.portal.server.rest.model.Login;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public interface LoginRestResource {

	@POST
	@Path("login")
	public Response login(Login login);
	
	@GET
	@Path("logout")
	public Response logout();
	
}
