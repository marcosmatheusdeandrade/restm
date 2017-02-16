package br.linx.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import br.linx.contato.ConsultaContatoPorId;
import br.linx.contato.ContatosTO;
import br.linx.contato.DeletarContatoTO;
import br.linx.contato.InserirContatoTO;
import br.linx.contato.RetornoConsultarContatoPorId;
import br.linx.contato.RetornoConsultarContatos;
import br.linx.contato.RetornoDeletarContato;
import br.linx.contato.RetornoInsercaoContato;
import br.linx.converter.ConverterContato;
import br.linx.enums.ETipoRetorno;
import br.linx.local.rules.IContatoRules;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
@WebService(serviceName="ContatoWS")
public class ContatoWS {

	@EJB
	private IContatoRules rulesLocal;
	
	@EJB
	private ConverterContato converter;
	
	
	@WebMethod(operationName="inserirContato")
	public RetornoInsercaoContato inserirContato(@WebParam(name="inserirContatoTO")
															InserirContatoTO inserirContatoTO){
		RetornoInsercaoContato retorno = new RetornoInsercaoContato();
		
		try {
			rulesLocal.inserirContato(converter.toDTO(inserirContatoTO.getContatoTO()));
			retorno.setTipoRetorno(ETipoRetorno.SUCESSO);
		} catch (Exception e) {
			retorno.setTipoRetorno(ETipoRetorno.FALHA);
		}
		
		return retorno;
	}
	
	@WebMethod(operationName="listarContatos")
	public RetornoConsultarContatos listarContatos(){
		RetornoConsultarContatos retorno = new RetornoConsultarContatos();
		
		try {
			ContatosTO contatosTO = new ContatosTO();
			contatosTO.setContatos(converter.toListTo(rulesLocal.listarContatos()));
			
			retorno.setContatosTO(contatosTO);
			retorno.setTipoRetorno(ETipoRetorno.SUCESSO);
		} catch (Exception e) {
			retorno.setTipoRetorno(ETipoRetorno.FALHA);
		}
		
		return retorno;
	}

	@WebMethod(operationName="consultarContatoPorId")
	public RetornoConsultarContatoPorId consultarContatoPorId(@WebParam(name="consultarContatoPorId")
									ConsultaContatoPorId consultarContatoPorId) {
		RetornoConsultarContatoPorId retorno = new RetornoConsultarContatoPorId();
		
		try {
			retorno.setContato(converter.toTO(
						rulesLocal.consultarContatoPorId(consultarContatoPorId.getId())));
			retorno.setTipoRetorno(ETipoRetorno.SUCESSO);
		} catch (Exception e) {
			retorno.setTipoRetorno(ETipoRetorno.FALHA);
		}
		
		return retorno;
	}
	
	@WebMethod(operationName="deletarContato")
	public RetornoDeletarContato deletarContato(@WebParam(name="deletarContatoTO") 
															DeletarContatoTO deletarContatoTO){
		RetornoDeletarContato retorno = new RetornoDeletarContato();
		
		try {
			rulesLocal.deletarContato(deletarContatoTO.getId());
			retorno.setTipoRetorno(ETipoRetorno.SUCESSO);
		} catch (Exception e) {
			retorno.setTipoRetorno(ETipoRetorno.FALHA);
		}
	
		return retorno;
	}
	
	
}
