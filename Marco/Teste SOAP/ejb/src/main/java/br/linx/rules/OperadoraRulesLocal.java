package br.linx.rules;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.linx.eao.OperadoraEAO;
import br.linx.model.Operadora;

@Stateless
public class OperadoraRulesLocal {

	@EJB
	private OperadoraEAO eao;
	
	public Operadora inserirOperadora(Operadora operadora) {
		return eao.inserirOperadora(operadora);
	}

	public List<Operadora> listarOperadoras() {
		return eao.listarOperadoras();
	}
	
}
