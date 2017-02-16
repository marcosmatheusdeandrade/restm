package br.experience.angular.server.rules.local;

import java.util.Collection;
import java.util.concurrent.locks.ReentrantLock;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import br.experience.angular.server.converters.dto.ConverterOperadoraDTO;
import br.experience.angular.server.dto.OperadoraDTO;
import br.experience.angular.server.rules.OperadoraBean;

@Stateless
@Local(IOperadoraBeanLocal.class)
public class OperadoraBeanLocal implements IOperadoraBeanLocal {

	@EJB
	private OperadoraBean operadoraBean;
	
	@EJB
	private ConverterOperadoraDTO converterOperadoraDTO;
	
	private final static ReentrantLock mutex = new ReentrantLock(true);

	public Collection<OperadoraDTO> consultarOperadoras() {
		
		mutex.lock();
		
		try{
			return converterOperadoraDTO
					.converterColecaoModelParaColecaoDTO(
							operadoraBean.consultarOperadoras());
		}finally{
			mutex.unlock();
		}
	}

	
}
