package br.experience.angular.server.test.delegates;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.experience.angular.server.eao.ContatoEAO;
import br.experience.angular.server.model.Contato;

@Stateless
public class ContatoEAODelegate {

	@EJB
	private ContatoEAO contatoEAO;

	public Contato salvarContato(Contato contato) {
		return contatoEAO.salvarContato(contato);
	}

	public Contato consultarContatoPorId(Integer id) {
		return contatoEAO.consultarContatoPorId(id);
	}

	public Collection<Contato> consultarContatosPorNome(String nome) {
		return contatoEAO.consultarContatosPorNome(nome);
	}

	public void deletarContato(Contato contato) {
		contatoEAO.deletarContato(contato);
	}
	
}
