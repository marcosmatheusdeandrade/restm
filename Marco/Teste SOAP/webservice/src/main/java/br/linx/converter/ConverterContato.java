package br.linx.converter;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.linx.contato.ContatoTO;
import br.linx.dto.ContatoDTO;

@Stateless
public class ConverterContato {

	@EJB
	private ConverterOperadora converterOperadora;
	
	public ContatoDTO toDTO(ContatoTO to) {
		ContatoDTO dto = new ContatoDTO();
		dto.setId(to.getId());
		dto.setNome(to.getNome());
		dto.setTelefone(to.getTelefone());
		dto.setOperadoraDTO(
				converterOperadora.toDto(to.getOperadoraTO()));
		
		return dto;
	}
	
	public List<ContatoTO> toListTo(List<ContatoDTO> dtos) {
		List<ContatoTO> tos = new ArrayList<>();
		
		dtos.forEach(dto ->{
			tos.add(toTO(dto));
		});
		
		return tos;
	}
	
	public ContatoTO toTO(ContatoDTO dto) {
		ContatoTO to = new ContatoTO();
		to.setId(dto.getId());
		to.setNome(dto.getNome());
		to.setTelefone(dto.getTelefone());
		to.setOperadoraTO(
				converterOperadora.toTO(dto.getOperadoraDTO()));
		
		return to;
	}
	
}
