package br.linx.local.rules;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import br.linx.converter.ConverterUsuario;
import br.linx.dto.ContatoDTO;
import br.linx.rules.ContatoRulesLocal;

@Stateless
@Local(IContatoRules.class)
public class ContatoRules implements IContatoRules{

	@EJB
	private ContatoRulesLocal rules;
	
	@EJB
	private ConverterUsuario converter;
	
	@Override
	public ContatoDTO inserirContato(ContatoDTO dto) {
		return converter.toDTO(rules.inserirContato(converter.toEntity(dto)));
	}

	public ContatoDTO consultarContatoPorId(Integer id){
		return converter.toDTO(rules.consultarContatoPorId(id));
	}
	
	@Override
	public List<ContatoDTO> listarContatos() {
		return converter.dtos(rules.listarContatos());
	}

	@Override
	public void deletarContato(Integer id) {
		rules.deletarContato(id);
	}

}
