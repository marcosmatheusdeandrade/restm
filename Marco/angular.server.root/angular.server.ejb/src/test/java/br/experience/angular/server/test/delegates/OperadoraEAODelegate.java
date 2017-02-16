package br.experience.angular.server.test.delegates;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.experience.angular.server.eao.OperadoraEAO;
import br.experience.angular.server.model.Operadora;

@Stateless
public class OperadoraEAODelegate {

	@EJB
	private OperadoraEAO operadoraEAO;

	public Operadora salvarOperadora(Operadora operdora) {
		return operadoraEAO.salvarOperadora(operdora);
	}

	public Operadora consultarOperadoraPorId(Integer id) {
		return operadoraEAO.consultarOperadoraPorId(id);
	}

	public Collection<Operadora> consultarOperadorasPorNome(String nome) {
		return operadoraEAO.consultarOperadorasPorNome(nome);
	}

	public void deletarOperadora(Operadora operdora) {
		operadoraEAO.deletarOperadora(operdora);
	}
	
}
