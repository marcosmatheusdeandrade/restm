package br.experience.angular.server.webservice.convertes;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.experience.angular.server.dto.ContatoDTO;
import br.experience.angular.server.webservice.model.contato.ContatoTO;

@Stateless
public class ConverterContatoTO {
	
	@EJB
	private ConverterOperadoraTO converterOperadoraTO;
	
	public ArrayList<ContatoTO> converterColecaoDTOParaColecaoTO(
			ArrayList<ContatoDTO> contatosDTO){
		
		if (contatosDTO == null)
			return null;

		ArrayList<ContatoTO> contatosTO = new ArrayList<>();
		
		for (ContatoDTO contatoDTO : contatosDTO) {
			contatosTO.add(converterDTOParaTO(contatoDTO));
		}
		
		return contatosTO;
	}
	
	public ContatoTO converterDTOParaTO(ContatoDTO contatoDTO){
		
		if (contatoDTO == null)
			return null;
		
		ContatoTO contatoTO = new ContatoTO();
		
		contatoTO.setId(contatoDTO.getId());
		contatoTO.setNome(contatoDTO.getNome());
		contatoTO.setTelefone(contatoDTO.getTelefone());
		contatoTO.setData(contatoDTO.getData());
		
		contatoTO.setOperadora(converterOperadoraTO
				.converterDTOParaTO(contatoDTO.getOperadora()));
		
		return contatoTO;
		
	}

	public ArrayList<ContatoDTO> converterColecaoTOParaColecaoDTO(
			ArrayList<ContatoTO> contatosTO){
		
		if (contatosTO == null)
			return null;

		ArrayList<ContatoDTO> contatosDTO = new ArrayList<>();
		
		for (ContatoTO contatoTO : contatosTO) {
			contatosDTO.add(converterTOParaDTO(contatoTO));
		}
		
		return contatosDTO;
	}
	
	public ContatoDTO converterTOParaDTO(ContatoTO contatoTO){
		
		if (contatoTO == null)
			return null;
		
		ContatoDTO contatoDTO = new ContatoDTO();
		
		contatoDTO.setId(contatoTO.getId());
		contatoDTO.setNome(contatoTO.getNome());
		contatoDTO.setTelefone(contatoTO.getTelefone());
		contatoDTO.setData(contatoTO.getData());
		
		contatoDTO.setOperadora(converterOperadoraTO
				.converterTOParaDTO(contatoTO.getOperadora()));
		
		return contatoDTO;
		
	}

}
