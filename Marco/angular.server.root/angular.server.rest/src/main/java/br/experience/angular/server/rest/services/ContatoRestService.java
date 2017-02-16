package br.experience.angular.server.rest.services;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import br.experience.angular.server.dto.ContatoDTO;
import br.experience.angular.server.rules.local.IContatoBeanLocal;

@Stateless
public class ContatoRestService implements IContatoRestService {
	
	@EJB
	private IContatoBeanLocal contatoBeanLocal;

	@Override
	public Response salvarContato(ContatoDTO contatoDTO){
		try{
			
			ContatoDTO contatoSalvo = contatoBeanLocal.salvarContato(contatoDTO);
			
			return Response.ok(contatoSalvo).build();
			
		}catch(Exception e){
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@Override
	public Response consultarContatoPorId(Integer id) {
		try{
			
			ContatoDTO contatoConsultado = contatoBeanLocal.consultarContatoPorId(id);
			
			return Response.ok(contatoConsultado).build();
			
		}catch(Exception e){
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@Override
	public Response consultarContatoPorNome(String nome) {
		try{
			
			Collection<ContatoDTO> contatosConsultados =
					contatoBeanLocal.consultarContatosPorNome(nome);
			
			return Response.ok(contatosConsultados).build();
			
		}catch(Exception e){
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@Override
	public Response deletarContato(Integer id) {
		try{
			
			ContatoDTO contatoDTO = new ContatoDTO();
			contatoDTO.setId(id);

			contatoBeanLocal.deletarContato(contatoDTO);
			
			return Response.ok().build();
			
		}catch(Exception e){
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
}
