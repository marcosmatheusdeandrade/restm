package br.experience.angular.server.rules.local;

import java.util.Collection;

import br.experience.angular.server.dto.OperadoraDTO;

public interface IOperadoraBeanLocal {
	
	public Collection<OperadoraDTO> consultarOperadoras();

}
