package br.linx.converter;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import br.linx.dto.UsuarioDto;
import br.linx.model.Usuario;

@Stateless
public class Converter {

	public Usuario toEntity(UsuarioDto dto){
		Usuario usuario = new Usuario();
		usuario.setId(dto.getId());
		usuario.setNome(dto.getNome());
		
		return usuario;
	}
	
	public UsuarioDto toDto(Usuario usuario){
		UsuarioDto dto = new UsuarioDto();
		dto.setId(usuario.getId());
		dto.setNome(usuario.getNome());
		
		return dto;
	}
	
	public List<UsuarioDto> toDtos(List<Usuario> usuarios){
		List<UsuarioDto> dtos = new ArrayList<UsuarioDto>();
		
		for(Usuario user : usuarios){
			UsuarioDto dto = new UsuarioDto();
			dto.setId(user.getId());
			dto.setNome(user.getNome());
		
			dtos.add(dto);
		}
		
		return dtos;
	}
	
}
