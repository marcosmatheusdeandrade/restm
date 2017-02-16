package br.linx.local.rules;

import java.util.List;

import br.linx.dto.OperadoraDTO;

public interface IOperadoraRules {

	public OperadoraDTO inserirOperadora(OperadoraDTO dto);
	
	public List<OperadoraDTO> listarOperadoras();
	
}
