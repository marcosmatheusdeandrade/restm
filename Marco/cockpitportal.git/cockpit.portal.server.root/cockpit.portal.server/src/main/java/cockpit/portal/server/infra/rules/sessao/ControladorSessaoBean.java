package cockpit.portal.server.infra.rules.sessao;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Singleton;

/**
 * Bean responsável por controlar as sessões existentes no sistema.
 * 
 * @author Evaristo Wychoski Benfatti
 *
 */
@Singleton
public class ControladorSessaoBean {

	private Map<String, Sessao> activeSessions = new HashMap<>();

	public Sessao criarERegistrarNovaSessao() {
		Sessao sessao = new Sessao();
		
		activeSessions.put(sessao.getSessionId(), sessao);
		
		return sessao;
	}
	
	public Sessao getSessao(String sessionId) {
		return activeSessions.get(sessionId);
	}
	
	public synchronized void liberarSessao(Sessao sessao) {
		activeSessions.remove(sessao.getSessionId());
	}

}
