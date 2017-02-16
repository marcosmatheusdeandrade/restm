package cockpit.portal.server.infra.rules;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import cockpit.portal.server.infra.po.UsuarioPo;
import cockpit.portal.server.infra.rules.sessao.AutenticadorSessaoBean;
import cockpit.portal.server.infra.rules.sessao.Sessao;
import cockpit.portal.server.infra.rules.sessao.UsuarioSessaoBean;

@Stateless
public class ControleAcessoBean {

	private static final String SOFTPHARMA_SESSION_USER_LOGGED = "SOFTPHARMA_SESSION_USER_LOGGED";
	
	@EJB
	private UsuarioSessaoBean usuarioSessaoBean;
	
	@EJB
	private AutenticadorSessaoBean autenticadorSessaoBean;
	
	public UsuarioPo getUsuarioLogado() {
		if (usuarioSessaoBean.isUsuarioSessaoLogado()) {
			Sessao softpharmaSession = usuarioSessaoBean.getSoftpharmaSession();
			
			return (UsuarioPo) softpharmaSession.get(SOFTPHARMA_SESSION_USER_LOGGED);
		}
		
		return null;
	}

	public void logarSessao(UsuarioPo usuario) {
		boolean logadoSessao = autenticadorSessaoBean.login();
		
		if (logadoSessao) {
			usuarioSessaoBean.getSoftpharmaSession().put(SOFTPHARMA_SESSION_USER_LOGGED, usuario);
		}
	}
	
	public void deslogarSessao() {
		if (usuarioSessaoBean.isUsuarioSessaoLogado()) {
			autenticadorSessaoBean.logout();
		}
	}
	
}