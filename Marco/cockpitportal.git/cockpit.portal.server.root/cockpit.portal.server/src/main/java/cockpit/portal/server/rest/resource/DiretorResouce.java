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

import cockpit.portal.server.rest.model.Diretor;

@Path("diretor")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public interface DiretorResouce {
	
	@POST
	@Path("salvar")
	public Response salvarDiretor(Diretor diretor);
	
	@GET
	@Path("consultar")
	public Response consultarDiretoresPorNome(@QueryParam("nome") String nome);
	
	@DELETE
	@Path("excluir")
	public Response excluirDiretor(@QueryParam("id") Integer id);
	
}
