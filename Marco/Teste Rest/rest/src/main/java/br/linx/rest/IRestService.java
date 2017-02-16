package br.linx.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.linx.dto.UsuarioDto;

@Path("usuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface IRestService {

	@Path("inserir")
	@POST
	public Response inserirUsuario(UsuarioDto dto);
	
	@Path("listar")
	@GET
	public Response listarUsuarios();
	
	@Path("deletar")
	@POST
	public Response deletarUsuarios(List<UsuarioDto> dtos);
}
