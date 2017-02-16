package br.experience.angular.server.converters.dto;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.experience.angular.server.dto.ContatoDTO;
import br.experience.angular.server.model.Contato;

@Stateless
public class ConverterContatoDTO {
	
	@EJB
	private ConverterOperadoraDTO converterOperadoraDTO;
	
	public Collection<Contato> converterColecaoDTOParaColecaoModel(
			Collection<ContatoDTO> contatosDTO){
		
		if (contatosDTO == null)
			return null;

		Collection<Contato> contatos = new ArrayList<>();
		
		for (ContatoDTO contatoDTO : contatosDTO) {
			contatos.add(converterDTOParaModel(contatoDTO));
		}
		
		return contatos;
	}
	
	public Contato converterDTOParaModel(ContatoDTO contatoDto){
		
		if (contatoDto == null)
			return null;
		
		Contato contato = new Contato();
		
		contato.setId(contatoDto.getId());
		contato.setNome(contatoDto.getNome());
		contato.setTelefone(contatoDto.getTelefone());
		contato.setData(contatoDto.getData());
		
		contato.setOperadora(converterOperadoraDTO
				.converterDTOParaModel(contatoDto.getOperadora()));
		
		return contato;
		
	}

	public Collection<ContatoDTO> converterColecaoModelParaColecaoDTO(
			Collection<Contato> contatos){
		
		if (contatos == null)
			return null;

		Collection<ContatoDTO> contatosDTO = new ArrayList<>();
		
		for (Contato contato : contatos) {
			contatosDTO.add(converterModelParaDTO(contato));
		}
		
		return contatosDTO;
	}
	
	public ContatoDTO converterModelParaDTO(Contato contato){
		
		if (contato == null)
			return null;
		
		ContatoDTO contatoDTO = new ContatoDTO();
		
		contatoDTO.setId(contato.getId());
		contatoDTO.setNome(contato.getNome());
		contatoDTO.setTelefone(contato.getTelefone());
		contatoDTO.setData(contato.getData());
		
		contatoDTO.setOperadora(converterOperadoraDTO
				.converterModelParaDTO(contato.getOperadora()));
		
		return contatoDTO;
		
	}

}
