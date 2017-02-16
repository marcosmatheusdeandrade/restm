package cockpit.portal.server.infra;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import cockpit.portal.server.infra.po.UsuarioPo;
import cockpit.portal.server.infra.po.UsuarioPo_;


@Stateless
public class UsuarioEao {

	@PersistenceContext(unitName = "cockpit.portal.server")
	private EntityManager em;
	
	public UsuarioPo consultarUsuarioPorId(Integer id){
		return em.find(UsuarioPo.class, id);
	}
	
	public UsuarioPo consultarUsuarioPorLogin(String login){
		
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(UsuarioPo.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		criteria.add(Restrictions.eq(UsuarioPo_.login.getName(), login));
		
		return (UsuarioPo) criteria.uniqueResult();
	}

	public UsuarioPo consultarUsuarioPorEmail(String email) {
		
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(UsuarioPo.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		criteria.add(Restrictions.eq(UsuarioPo_.email.getName(), email));
		
		return (UsuarioPo) criteria.uniqueResult();
		
	}
	
}
