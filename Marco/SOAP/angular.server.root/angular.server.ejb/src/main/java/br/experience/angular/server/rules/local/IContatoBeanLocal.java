package br.experience.angular.server.rules.local;

import java.util.Collection;

import br.experience.angular.server.dto.ContatoDTO;

public interface IContatoBeanLocal {
	
	public ContatoDTO salvarContato(ContatoDTO contatoDTO);

	public ContatoDTO consultarContatoPorId(Integer id);

	public Collection<ContatoDTO> consultarContatosPorNome(String nome);

	public void deletarContato(ContatoDTO contato);

}
