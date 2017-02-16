package br.experience.angular.server.converters.dto;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;

import br.experience.angular.server.dto.OperadoraDTO;
import br.experience.angular.server.model.Operadora;

@Stateless
public class ConverterOperadoraDTO {

	public Collection<Operadora> converterColecaoDTOParaColecaoModel(
			Collection<OperadoraDTO> operadorasDTO){
		
		if (operadorasDTO == null)
			return null;

		Collection<Operadora> operadoras = new ArrayList<>();
		
		for (OperadoraDTO operadoraDTO : operadorasDTO) {
			operadoras.add(converterDTOParaModel(operadoraDTO));
		}
		
		return operadoras;
	}
	
	public Operadora converterDTOParaModel(OperadoraDTO operadoraDTO){
		
		if (operadoraDTO == null)
			return null;
		
		Operadora operadora = new Operadora();
		
		operadora.setId(operadoraDTO.getId());
		operadora.setNome(operadoraDTO.getNome());
		operadora.setCodigo(operadoraDTO.getCodigo());
		
		return operadora;
	}

	public Collection<OperadoraDTO> converterColecaoModelParaColecaoDTO(
			Collection<Operadora> operadoras){
		
		if (operadoras == null)
			return null;

		Collection<OperadoraDTO> operadorasDTO = new ArrayList<>();
		
		for (Operadora operadora : operadoras) {
			operadorasDTO.add(converterModelParaDTO(operadora));
		}
		
		return operadorasDTO;
	}
	
	public OperadoraDTO converterModelParaDTO(Operadora operadora){
		
		if (operadora == null)
			return null;
		
		OperadoraDTO operadoraDTO = new OperadoraDTO();
		
		operadoraDTO.setId(operadora.getId());
		operadoraDTO.setNome(operadora.getNome());
		operadoraDTO.setCodigo(operadora.getCodigo());
		
		return operadoraDTO;
	}

}
