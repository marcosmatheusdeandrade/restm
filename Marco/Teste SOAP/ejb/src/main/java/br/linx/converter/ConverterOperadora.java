package br.linx.converter;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import br.linx.dto.OperadoraDTO;
import br.linx.model.Operadora;

@Stateless
public class ConverterOperadora {

	public Operadora toEntity(OperadoraDTO dto) {
		Operadora operadora = new Operadora();
		operadora.setId(dto.getId());
		operadora.setNome(dto.getNome());
		operadora.setCodigo(dto.getCodigo());
		
		return operadora;
	}
	
	public List<OperadoraDTO> toDtos(List<Operadora> operadoras){
		List<OperadoraDTO> dtos = new ArrayList<>();
		
		operadoras.forEach(op ->{
			dtos.add(toDto(op));
		});
		
		return dtos;
	}
	
	public OperadoraDTO toDto(Operadora operadora){
		OperadoraDTO dto = new OperadoraDTO();
		dto.setId(operadora.getId());
		dto.setNome(operadora.getNome());
		dto.setCodigo(operadora.getCodigo());
		
		return dto;
	}
	
}
