package br.linx.rules;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.linx.eao.ContatoEAO;
import br.linx.model.Contato;

@Stateless
public class ContatoRulesLocal {

	@EJB
	private ContatoEAO eao;
	
	public Contato inserirContato(Contato contato){
		return eao.inserirContato(contato);
	}
	
	public Contato consultarContatoPorId(Integer id){
		return eao.consultarContatoPorId(id);
	}
	
	public List<Contato> listarContatos(){
		return eao.listarContatos();
	}
	
	public void deletarContato(Integer id){
		eao.deletarContato(id);
	}
	
}
