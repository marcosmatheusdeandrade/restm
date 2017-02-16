package br.linx.rules.local;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import br.linx.converter.Converter;
import br.linx.dto.UsuarioDto;
import br.linx.regras.Rules;

@Stateless
@Local(IRulesLocal.class)
public class RulesLocal implements IRulesLocal{

	@EJB
	private Converter converter;

	@EJB
	private Rules rules;
	
	public UsuarioDto inserir(UsuarioDto dto) {
		return converter.toDto(rules.inserir(converter.toEntity(dto)));
	}

	public List<UsuarioDto> listar() {
		return converter.toDtos(rules.listar());
	}

	public void deletarUsuarios(List<UsuarioDto> dtos) {
		for(UsuarioDto dto : dtos){
			rules.deletar(converter.toEntity(dto));
		}
	}

}
