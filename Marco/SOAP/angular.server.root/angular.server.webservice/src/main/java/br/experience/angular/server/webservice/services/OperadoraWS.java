package br.experience.angular.server.webservice.services;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import br.experience.angular.server.dto.OperadoraDTO;
import br.experience.angular.server.rules.local.IOperadoraBeanLocal;
import br.experience.angular.server.webservice.convertes.ConverterOperadoraTO;
import br.experience.angular.server.webservice.enums.ETipoRetornoTO;
import br.experience.angular.server.webservice.model.operadora.OperadorasTO;
import br.experience.angular.server.webservice.model.operadora.RetornoConsultarOperadorasTO;

@Stateless
@WebService(serviceName="OperadoraWS")
public class OperadoraWS {
	
	@EJB
	private IOperadoraBeanLocal operadoraBean;

	@EJB
	private ConverterOperadoraTO converterOperadoraTO;
	
	@WebMethod(operationName="consultarOperadoras")
	public RetornoConsultarOperadorasTO consultarOperadorasTO(){
		
		RetornoConsultarOperadorasTO retorno = new RetornoConsultarOperadorasTO();
		try {
			
			ArrayList<OperadoraDTO> operadoras = new ArrayList<>(operadoraBean.consultarOperadoras());
			
			retorno.setTipoRetorno(ETipoRetornoTO.SUCESSO);
			OperadorasTO operadorasTO = new OperadorasTO();
			operadorasTO.setOperadora(converterOperadoraTO
					.converterColecaoDTOParaColecaoTO(operadoras));
			retorno.setOperadoras(operadorasTO);
			
		} catch (Exception e) {
			
			retorno.setTipoRetorno(ETipoRetornoTO.FALHA);
			retorno.setMensagem(e.getMessage());
			
		}
		return retorno;		
	}
	
}
