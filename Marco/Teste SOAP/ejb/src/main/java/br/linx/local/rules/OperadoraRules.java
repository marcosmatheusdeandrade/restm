package br.linx.local.rules;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import br.linx.converter.ConverterOperadora;
import br.linx.dto.OperadoraDTO;
import br.linx.rules.OperadoraRulesLocal;

@Stateless
@Local(IOperadoraRules.class)
public class OperadoraRules implements IOperadoraRules{

	@EJB
	private OperadoraRulesLocal rulesLocal;
	
	@EJB
	private ConverterOperadora converter;
	
	@Override
	public OperadoraDTO inserirOperadora(OperadoraDTO dto) {
		return converter.toDto(
				rulesLocal.inserirOperadora(converter.toEntity(dto)));
	}

	@Override
	public List<OperadoraDTO> listarOperadoras() {
		return converter.toDtos(rulesLocal.listarOperadoras());
	}

}
