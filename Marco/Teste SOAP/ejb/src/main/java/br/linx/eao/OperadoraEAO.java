package br.linx.eao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.linx.model.Operadora;

@Stateless
public class OperadoraEAO {

	@PersistenceContext(unitName="ejbPU")
	private EntityManager em;
	
	public Operadora inserirOperadora(Operadora operadora){
		return em.merge(operadora);
	}
	
	public List<Operadora> listarOperadoras(){
		Session session = em.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(Operadora.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		return criteria.list();
	}
	
}
