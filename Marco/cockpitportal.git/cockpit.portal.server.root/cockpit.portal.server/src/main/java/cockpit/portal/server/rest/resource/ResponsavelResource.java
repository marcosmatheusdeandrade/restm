package cockpit.portal.server.rest.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cockpit.portal.server.rest.model.Responsavel;

@Path("responsavel")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public interface ResponsavelResource {
	
	@POST
	@Path("salvar")
	public Response salvar(Responsavel responsavel);
	
	@DELETE
	@Path("excluir")
	public Response excluir(@QueryParam("id") Integer id);
	
	@GET
	@Path("consultar")
	public Response consultarPorParteNome(@QueryParam("nome") String nome);

}
