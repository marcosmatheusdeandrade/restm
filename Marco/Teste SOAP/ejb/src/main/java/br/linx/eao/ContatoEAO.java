package br.linx.eao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.linx.model.Contato;

@Stateless
public class ContatoEAO {

	@PersistenceContext(unitName="ejbPU")
	private EntityManager em;
	
	public Contato inserirContato(Contato contato){
		return em.merge(contato);
	}
	
	public List<Contato> listarContatos(){
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Contato.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		return criteria.list();
	}
	
	public void deletarContato(Integer id){
		em.remove(em.find(Contato.class, id));
	}

	public Contato consultarContatoPorId(Integer id) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Contato.class);
		criteria.add(Restrictions.eq("id", id));
		
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		return (Contato) criteria.uniqueResult();
	}
	
}
