package br.experience.angular.server.webservice.services;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import br.experience.angular.server.dto.ContatoDTO;
import br.experience.angular.server.rules.local.IContatoBeanLocal;
import br.experience.angular.server.webservice.convertes.ConverterContatoTO;
import br.experience.angular.server.webservice.enums.ETipoRetornoTO;
import br.experience.angular.server.webservice.model.contato.ConsultarContatoPorIdTO;
import br.experience.angular.server.webservice.model.contato.ConsultarContatosPorNomeTO;
import br.experience.angular.server.webservice.model.contato.ContatosTO;
import br.experience.angular.server.webservice.model.contato.DeletarContatoTO;
import br.experience.angular.server.webservice.model.contato.RetornoConsultarContatoPorIdTO;
import br.experience.angular.server.webservice.model.contato.RetornoConsultarContatosPorNomeTO;
import br.experience.angular.server.webservice.model.contato.RetornoDeletarContatoTO;
import br.experience.angular.server.webservice.model.contato.RetornoSalvarContatoTO;
import br.experience.angular.server.webservice.model.contato.SalvarContatoTO;

@Stateless
@WebService(serviceName="ContatoWS")
public class ContatoWS {
	
	@EJB
	private IContatoBeanLocal contatoBean;
	
	@EJB
	private ConverterContatoTO converterContatoTO;

	@WebMethod(operationName="salvarContato")
	public RetornoSalvarContatoTO salvarContatoTO(
			@WebParam(name="salvarContatoTO")
			SalvarContatoTO salvarContatoTO){
		
		RetornoSalvarContatoTO retorno = new RetornoSalvarContatoTO();
		try {
			
			ContatoDTO contatoDTO = converterContatoTO
					.converterTOParaDTO(salvarContatoTO.getContato());
			
			contatoBean.salvarContato(contatoDTO);
			
			retorno.setTipoRetorno(ETipoRetornoTO.SUCESSO);
			
		} catch (Exception e) {
			
			retorno.setTipoRetorno(ETipoRetornoTO.FALHA);
			retorno.setMensagem(e.getMessage());
			
		}
		return retorno;
	}
	
	@WebMethod(operationName="consultarContatoPorId")
	public RetornoConsultarContatoPorIdTO consultarContatoPorIdTO(
			@WebParam(name="consultarContatoPorIdTO")
			ConsultarContatoPorIdTO consultarContatoPorIdTO){
		
		RetornoConsultarContatoPorIdTO retorno = new RetornoConsultarContatoPorIdTO();
		try {
			
			ContatoDTO contatoDTO = contatoBean
					.consultarContatoPorId(consultarContatoPorIdTO.getId());
			
			retorno.setTipoRetorno(ETipoRetornoTO.SUCESSO);
			retorno.setContato(converterContatoTO.converterDTOParaTO(contatoDTO));
			
		} catch (Exception e) {
			
			retorno.setTipoRetorno(ETipoRetornoTO.FALHA);
			retorno.setMensagem(e.getMessage());
			
		}
		return retorno;
	}

	@WebMethod(operationName="consultarContatosPorNome")
	public RetornoConsultarContatosPorNomeTO consultarContatosPorNomeTO(
			@WebParam(name="consultarContatosPorNomeTO")
			ConsultarContatosPorNomeTO consultarContatosPorNomeTO){
		
		RetornoConsultarContatosPorNomeTO retorno = new RetornoConsultarContatosPorNomeTO();
		try {
			
			ArrayList<ContatoDTO> contatosDTO = new ArrayList<>(contatoBean
					.consultarContatosPorNome(consultarContatosPorNomeTO.getNome()));
			
			retorno.setTipoRetorno(ETipoRetornoTO.SUCESSO);
			ContatosTO contatosTO = new ContatosTO();
			contatosTO.setContato(converterContatoTO
					.converterColecaoDTOParaColecaoTO(contatosDTO));
			retorno.setContatos(contatosTO);
			
		} catch (Exception e) {
			
			retorno.setTipoRetorno(ETipoRetornoTO.FALHA);
			retorno.setMensagem(e.getMessage());
			
		}
		return retorno;
		
	}

	@WebMethod(operationName="deletarContato")
	public RetornoDeletarContatoTO deletarContatoTO(
			@WebParam(name="deletarContatoTO")
			DeletarContatoTO deletarContatoTO){
		
		RetornoDeletarContatoTO retorno = new RetornoDeletarContatoTO();
		try {
			
			ContatoDTO contatoDTO = new ContatoDTO();
			contatoDTO.setId(deletarContatoTO.getId());
			
			contatoBean.deletarContato(contatoDTO);
			
			retorno.setTipoRetorno(ETipoRetornoTO.SUCESSO);
			
		} catch (Exception e) {
			
			retorno.setTipoRetorno(ETipoRetornoTO.FALHA);
			retorno.setMensagem(e.getMessage());
			
		}
		return retorno;
		
	}
}
