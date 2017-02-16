package br.linx.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import br.linx.dto.UsuarioDto;
import br.linx.rules.local.IRulesLocal;

@Stateless
public class RestService implements IRestService{

	@EJB
	private IRulesLocal iRulesLocal;
	
	@Override
	public Response inserirUsuario(UsuarioDto dto) {
		
		try {
			UsuarioDto user = iRulesLocal.inserir(dto);
			return Response.ok(user).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@Override
	public Response listarUsuarios() {
		try {
			List<UsuarioDto> users = iRulesLocal.listar();
			return Response.ok(users).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@Override
	public Response deletarUsuarios(List<UsuarioDto> dtos) {
		try {
			iRulesLocal.deletarUsuarios(dtos);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

}
