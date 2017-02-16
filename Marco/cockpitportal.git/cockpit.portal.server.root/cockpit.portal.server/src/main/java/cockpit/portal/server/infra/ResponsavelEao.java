package cockpit.portal.server.infra;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import cockpit.portal.server.infra.po.ResponsavelPo;
import cockpit.portal.server.infra.po.ResponsavelPo_;

@Stateless
public class ResponsavelEao {

	@PersistenceContext(unitName = "cockpit.portal.server")
	private EntityManager em;

	public ResponsavelPo salvarResponsavel(ResponsavelPo responsavelPo){
		ResponsavelPo responsavelPoSalvo = em.merge(responsavelPo);
		em.flush();
		return responsavelPoSalvo;
	}
	
	public ResponsavelPo consultarResponsavelPorId(Integer id){
		return em.find(ResponsavelPo.class, id);
	}
	
	public ResponsavelPo consultarResponsavelPorLogin(String login){
		
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(ResponsavelPo.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		criteria.add(Restrictions.eq(ResponsavelPo_.login.getName(), login));
		
		return (ResponsavelPo) criteria.uniqueResult();
	}
	
	public List<ResponsavelPo> consultarResponsavelPorNome(String nome){
		
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(ResponsavelPo.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		if(nome != null && !nome.isEmpty()){
			
			criteria.add(Restrictions.eq(ResponsavelPo_.nome.getName(), nome));
		}
		
		return criteria.list();
	}

	public void excluir(ResponsavelPo responsavelConsultado) {
		em.remove(responsavelConsultado);
		em.flush();
	}

	public ResponsavelPo consultarResponsavelPorCPF(String cpf) {
		
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(ResponsavelPo.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		criteria.add(Restrictions.eq(ResponsavelPo_.cpf.getName(), cpf));
		
		return (ResponsavelPo) criteria.uniqueResult();
	}
	

}
