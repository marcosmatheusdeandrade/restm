package cockpit.portal.server.infra;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import cockpit.portal.server.infra.po.AdministradorPo;
import cockpit.portal.server.infra.po.AdministradorPo_;

@Stateless
public class AdministradorEao {
	
	@PersistenceContext(unitName = "cockpit.portal.server")
	private EntityManager em;
	
	public AdministradorPo salvarAdministrador(AdministradorPo administradorPo){
		
		AdministradorPo administradorPoSalvo = em.merge(administradorPo);
		em.flush();
		return administradorPoSalvo;
	}
	
	public AdministradorPo consultarAdministradorPorId(Integer id){
		return em.find(AdministradorPo.class, id);
	}

	public void excluirAdministrador(Integer idAdministrador) {
		em.remove(em.getReference(AdministradorPo.class, idAdministrador));
		em.flush();
	}
	
	public List<AdministradorPo> consultarAdministradoresPorNome(String nome){
		
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(AdministradorPo.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		if(nome != null){
			criteria.add(Restrictions.ilike(AdministradorPo_.nome.getName(), nome, MatchMode.ANYWHERE));
		}
		
		return criteria.list();
		
	}
	

}
