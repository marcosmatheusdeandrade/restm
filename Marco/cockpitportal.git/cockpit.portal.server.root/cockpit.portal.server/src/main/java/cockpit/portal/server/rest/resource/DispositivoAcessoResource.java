package cockpit.portal.server.rest.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("dispositivo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public interface DispositivoAcessoResource {

	@GET
	@Path("vendedor/gerar")
	public Response gerarCodigoAcessoDispositivoVendedor(
			@QueryParam("cpf") String cpf);
	
	@GET
	@Path("gerente/gerar")
	public Response gerarCodigoAcessoDispositivoGerente(
			@QueryParam("cpf") String cpf);
	
	@GET
	@Path("diretor/gerar")
	public Response gerarCodigoAcessoDispositivoDiretor(
			@QueryParam("id") Integer id);

	@GET
	@Path("vendedor/excluir")
	public Response excluirDispositivoVendedor(@QueryParam("cpf") String cpf, 
			@QueryParam("codigoGerado") String codigoGerado);
	
	@GET
	@Path("gerente/excluir")
	public Response excluirDispositivoGerente(@QueryParam("cpf") String cpf,
			@QueryParam("codigoGerado") String codigoGerado);
	
	@GET
	@Path("diretor/excluir")
	public Response excluirDispositivoDiretor(@QueryParam("id") Integer id, 
			@QueryParam("codigoGerado") String codigoGerado);
}
