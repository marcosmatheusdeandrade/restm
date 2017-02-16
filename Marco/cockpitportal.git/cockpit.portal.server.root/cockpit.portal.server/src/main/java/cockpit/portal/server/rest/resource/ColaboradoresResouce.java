package cockpit.portal.server.rest.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cockpit.portal.server.rest.model.FiltroPesquisaColaborador;

@Path("colaborador")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public interface ColaboradoresResouce {
	
	@POST
	@Path("vendedor/pesquisar")
	public Response pesquisarVendedores(FiltroPesquisaColaborador filtro);
	
	@GET
	@Path("vendedor/listar")
	public Response listarVendedores();
	
	@GET
	@Path("vendedor/remover")
	public Response removerVendedorListagem(@QueryParam("cpf") String cpf);

	@POST
	@Path("gerente/pesquisar")
	public Response pesquisarGerentes(FiltroPesquisaColaborador filtro);
	
	@GET
	@Path("gerente/listar")
	public Response listarGerentes();
	
	@GET
	@Path("gerente/remover")
	public Response removerGerenteListagem(@QueryParam("cpf") String cpf);
	
}
