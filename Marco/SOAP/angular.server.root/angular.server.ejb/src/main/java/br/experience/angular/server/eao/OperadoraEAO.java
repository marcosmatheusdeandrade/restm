package br.experience.angular.server.eao;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.experience.angular.server.model.Contato;
import br.experience.angular.server.model.Operadora;
import br.experience.angular.server.model.Operadora_;

@Stateless
public class OperadoraEAO {
	
	@PersistenceContext(unitName="ejbPU")
	private EntityManager em;

	public Operadora salvarOperadora(Operadora operdora){
		return em.merge(operdora);
	}
	
	public Operadora consultarOperadoraPorId(Integer id){
		return em.find(Operadora.class, id);
	}
	
	public Collection<Operadora> consultarOperadorasPorNome(String nome){
		
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Operadora.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		criteria.add(Restrictions.ilike(Operadora_.nome.getName(),
				nome, MatchMode.ANYWHERE));
		
		return criteria.list();
	}
	
	public void deletarOperadora(Operadora operdora){
		em.remove(em.find(Operadora.class, operdora.getId()));
	}
	
}
