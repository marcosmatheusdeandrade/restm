package br.linx.rules.local;

import java.util.List;

import br.linx.dto.UsuarioDto;

public interface IRulesLocal {

	public UsuarioDto inserir(UsuarioDto dto);
	
	public List<UsuarioDto> listar();

	public void deletarUsuarios(List<UsuarioDto> dtos);
}
