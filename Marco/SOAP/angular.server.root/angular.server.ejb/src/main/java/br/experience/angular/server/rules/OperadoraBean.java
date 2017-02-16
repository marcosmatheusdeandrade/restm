package br.experience.angular.server.rules;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.experience.angular.server.eao.OperadoraEAO;
import br.experience.angular.server.model.Operadora;

@Stateless
public class OperadoraBean {
	
	@EJB
	private OperadoraEAO operadorasEAO;

	public Collection<Operadora> consultarOperadoras() {
		return operadorasEAO.consultarOperadorasPorNome("");
	}

}
