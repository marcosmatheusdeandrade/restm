package br.linx.converter;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.linx.dto.ContatoDTO;
import br.linx.model.Contato;

@Stateless
public class ConverterUsuario {

	@EJB
	private ConverterOperadora converterOperadora;
	
	
	public List<ContatoDTO> dtos(List<Contato> contatos){
		List<ContatoDTO> dtos = new ArrayList<>();
		
		contatos.forEach(ct ->{
			dtos.add(toDTO(ct));
		});
		
		return dtos;
	}
	
	public ContatoDTO toDTO(Contato contato){
		ContatoDTO dto = new ContatoDTO();
		dto.setId(contato.getId());
		dto.setNome(contato.getNome());
		dto.setTelefone(contato.getTelefone());
		
		return dto;
	}
	
	public Contato toEntity(ContatoDTO dto){
		Contato contato = new Contato();
		contato.setId(dto.getId());
		contato.setNome(dto.getNome());
		contato.setTelefone(dto.getTelefone());
		
		contato.setOperadora(converterOperadora.
							toEntity(dto.getOperadoraDTO()));
		
		return contato;
	}
	
}
