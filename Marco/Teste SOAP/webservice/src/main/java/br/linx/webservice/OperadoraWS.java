package br.linx.webservice;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import br.linx.converter.ConverterOperadora;
import br.linx.enums.ETipoRetorno;
import br.linx.local.rules.IOperadoraRules;
import br.linx.operadora.InserirOperadoraTO;
import br.linx.operadora.OperadorasTO;
import br.linx.operadora.RetornoConsultarOperadoras;
import br.linx.operadora.RetornoInsercaoOperadora;

@Stateless
@WebService(serviceName="OperadoraWS")
public class OperadoraWS {

	@EJB
	private IOperadoraRules iOperadoraRules;
	
	@EJB
	private ConverterOperadora converter;
	
	@WebMethod(operationName="inserirOperadora")
	public RetornoInsercaoOperadora inserirOperadora(@WebParam(name="inserirOperadoraTO") 
															InserirOperadoraTO inserirOperadoraTO){
		
		RetornoInsercaoOperadora retorno = new RetornoInsercaoOperadora();
		
		try {
			iOperadoraRules.inserirOperadora(
				converter.toDto(inserirOperadoraTO.getOperadoraTO()));
		
			retorno.setTipoRetorno(ETipoRetorno.SUCESSO);
		} catch (Exception e) {
			retorno.setTipoRetorno(ETipoRetorno.FALHA);
		}
		
		return retorno;
	}
	
	@WebMethod(operationName="listarOperadoras")
	public RetornoConsultarOperadoras listarOperadoras(){
		RetornoConsultarOperadoras retorno = new RetornoConsultarOperadoras();
		
		try {
			OperadorasTO operadorasTO = new OperadorasTO();
				
			operadorasTO.setOperadora(converter.toListTO(
										iOperadoraRules.listarOperadoras()));
			
			retorno.setOperadorasTO(operadorasTO);
			retorno.setTipoRetorno(ETipoRetorno.SUCESSO);
		} catch (Exception e) {
			retorno.setTipoRetorno(ETipoRetorno.FALHA);
		}
		
		return retorno;
	}
	
}
