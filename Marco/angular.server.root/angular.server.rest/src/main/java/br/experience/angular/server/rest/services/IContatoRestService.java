package br.experience.angular.server.rest.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.experience.angular.server.dto.ContatoDTO;

@Path("contato")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public interface IContatoRestService {
	
	@POST
	@Path("salvar")
	public Response salvarContato(ContatoDTO contatoDTO);

	@GET
	@Path("consultar_id")
	public Response consultarContatoPorId(@QueryParam("id") Integer id);

	@GET
	@Path("consultar_nome")
	public Response consultarContatoPorNome(@QueryParam("nome") String nome);

	@GET
	@Path("deletar")
	public Response deletarContato(@QueryParam("id") Integer id);

}
