package br.linx.eao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.linx.model.Usuario;

@Stateless
public class UsuarioEAO {

	@PersistenceContext(unitName="ejbPU")
	private EntityManager em;
	
	public Usuario inserir(Usuario usuario){
		return em.merge(usuario);
	}
	
	public List<Usuario> listar(){
		
		Session session = em.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		return criteria.list();
	}
	
	public void deletar(Usuario usuario){
		em.remove(em.find(Usuario.class, usuario.getId()));
	}
	
}
