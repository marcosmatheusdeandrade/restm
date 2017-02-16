package br.experience.angular.server.webservice.convertes;

import java.util.ArrayList;

import javax.ejb.Stateless;

import br.experience.angular.server.dto.OperadoraDTO;
import br.experience.angular.server.webservice.model.operadora.OperadoraTO;

@Stateless
public class ConverterOperadoraTO {

	public ArrayList<OperadoraDTO> converterColecaoTOParaColecaoDTO(
			ArrayList<OperadoraTO> operadorasTO){
		
		if (operadorasTO == null)
			return null;

		ArrayList<OperadoraDTO> operadorasDTO = new ArrayList<>();
		
		for (OperadoraTO operadoraTO : operadorasTO) {
			operadorasDTO.add(converterTOParaDTO(operadoraTO));
		}
		
		return operadorasDTO;
	}
	
	public OperadoraDTO converterTOParaDTO(OperadoraTO operadoraTO){
		
		if (operadoraTO == null)
			return null;
		
		OperadoraDTO operadoraDTO = new OperadoraDTO();
		
		operadoraDTO.setId(operadoraTO.getId());
		operadoraDTO.setNome(operadoraTO.getNome());
		operadoraDTO.setCodigo(operadoraTO.getCodigo());
		
		return operadoraDTO;
	}

	public ArrayList<OperadoraTO> converterColecaoDTOParaColecaoTO(
			ArrayList<OperadoraDTO> operadorasDTO){
		
		if (operadorasDTO == null)
			return null;

		ArrayList<OperadoraTO> operadorasTO = new ArrayList<>();
		
		for (OperadoraDTO operadoraDTO : operadorasDTO) {
			operadorasTO.add(converterDTOParaTO(operadoraDTO));
		}
		
		return operadorasTO;
	}
	
	public OperadoraTO converterDTOParaTO(OperadoraDTO operadoraDTO){
		
		if (operadoraDTO == null)
			return null;
		
		OperadoraTO operadoraTO = new OperadoraTO();
		
		operadoraTO.setId(operadoraDTO.getId());
		operadoraTO.setNome(operadoraDTO.getNome());
		operadoraTO.setCodigo(operadoraDTO.getCodigo());
		
		return operadoraTO;
	}

}
