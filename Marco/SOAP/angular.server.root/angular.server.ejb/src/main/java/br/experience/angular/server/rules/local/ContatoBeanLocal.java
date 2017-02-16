package br.experience.angular.server.rules.local;

import java.util.Collection;
import java.util.concurrent.locks.ReentrantLock;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import br.experience.angular.server.converters.dto.ConverterContatoDTO;
import br.experience.angular.server.dto.ContatoDTO;
import br.experience.angular.server.model.Contato;
import br.experience.angular.server.rules.ContatoBean;

@Stateless
@Local(IContatoBeanLocal.class)
public class ContatoBeanLocal implements IContatoBeanLocal {

	@EJB
	private ContatoBean contatoBean;

	@EJB
	private ConverterContatoDTO converterContatoDTO;
	
	private final static ReentrantLock mutex = new ReentrantLock(true);

	public ContatoDTO salvarContato(ContatoDTO contatoDTO) {
		
		mutex.lock();
		
		try{
			Contato contato = converterContatoDTO.converterDTOParaModel(contatoDTO);
			
			return converterContatoDTO.converterModelParaDTO(
					contatoBean.salvarContato(contato));
		}finally{
			mutex.unlock();
		}
		
		
	}

	public ContatoDTO consultarContatoPorId(Integer id) {
		
		mutex.lock();
		
		try{
			return converterContatoDTO.converterModelParaDTO(
					contatoBean.consultarContatoPorId(id));
		}finally{
			mutex.unlock();
		}
	}

	public Collection<ContatoDTO> consultarContatosPorNome(String nome) {
		
		mutex.lock();
		
		try{
			return converterContatoDTO.converterColecaoModelParaColecaoDTO(
					contatoBean.consultarContatosPorNome(nome));
		}finally{
			mutex.unlock();
		}
	}

	public void deletarContato(ContatoDTO contato) {
		
		mutex.lock();
		
		try{
			contatoBean.deletarContato(
					converterContatoDTO.converterDTOParaModel(contato));
		}finally{
			mutex.unlock();
		}
	}
	
}
