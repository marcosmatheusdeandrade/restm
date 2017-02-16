package cockpit.portal.server.infra.rules.sessao;

import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Classe de modelo que representa uma sessão para o sistema.
 * 
 * @author Evaristo Wychoski Benfatti
 *
 */
public class Sessao implements Serializable {

	private static final long serialVersionUID = 8714908146165758825L;

	private String sessionId;

	private ConcurrentHashMap<String, Object> sessionHashMap = new ConcurrentHashMap<>();

	public Sessao() {
		this.sessionId = UUID.randomUUID().toString();
	}

	public String getSessionId() {
		return sessionId;
	}

	public Object get(String varName) {
		return sessionHashMap.get(varName);
	}

	public Object put(String varName, Object varValue) {
		return sessionHashMap.put(varName, varValue);
	}

	public Object remove(String varName) {
		return sessionHashMap.remove(varName);
	}

}