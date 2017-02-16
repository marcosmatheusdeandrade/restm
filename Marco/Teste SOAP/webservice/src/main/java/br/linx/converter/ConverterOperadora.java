package br.linx.converter;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import br.linx.dto.OperadoraDTO;
import br.linx.operadora.OperadoraTO;

@Stateless
public class ConverterOperadora {

	public List<OperadoraTO> toListTO(List<OperadoraDTO> dtos){
		List<OperadoraTO> tos = new ArrayList<>();
		
		dtos.forEach(dto ->{
			tos.add(toTO(dto));
		});
		
		return tos;
	}
	
	public OperadoraDTO toDto(OperadoraTO to){
		OperadoraDTO dto = new OperadoraDTO();
		dto.setId(to.getId());
		dto.setNome(to.getNome());
		dto.setCodigo(to.getCodigo());
		
		return dto;
	}

	public OperadoraTO toTO(OperadoraDTO dto){
		if(dto == null)
			return null;
		OperadoraTO to = new OperadoraTO();
		to.setId(dto.getId());
		to.setNome(dto.getNome());
		to.setCodigo(dto.getCodigo());
		
		return to;
	}
	
}
