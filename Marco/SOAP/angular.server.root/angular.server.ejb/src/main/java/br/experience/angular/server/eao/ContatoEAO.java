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
import br.experience.angular.server.model.Contato_;

@Stateless
public class ContatoEAO {
	
	@PersistenceContext(unitName="ejbPU")
	private EntityManager em;

	public Contato salvarContato(Contato contato){
		return em.merge(contato);
	}
	
	public Contato consultarContatoPorId(Integer id){
		return em.find(Contato.class, id);
	}
	
	public Collection<Contato> consultarContatosPorNome(String nome){
		
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Contato.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		criteria.add(Restrictions.ilike(Contato_.nome.getName(),
				nome, MatchMode.ANYWHERE));
		
		return criteria.list();
	}
	
	public void deletarContato(Contato contato){
		em.remove(em.find(Contato.class, contato.getId()));
	}
	
}
