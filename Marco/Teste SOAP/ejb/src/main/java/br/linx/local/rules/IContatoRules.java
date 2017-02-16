package br.linx.local.rules;

import java.util.List;

import br.linx.dto.ContatoDTO;

public interface IContatoRules {

	public ContatoDTO inserirContato(ContatoDTO dto);
	
	public ContatoDTO consultarContatoPorId(Integer id);
	
	public List<ContatoDTO> listarContatos();
	
	public void deletarContato(Integer id);
}
