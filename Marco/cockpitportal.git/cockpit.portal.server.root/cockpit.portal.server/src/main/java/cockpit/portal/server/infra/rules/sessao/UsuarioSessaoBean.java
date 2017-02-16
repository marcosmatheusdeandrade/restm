package cockpit.portal.server.infra.rules.sessao;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.transaction.TransactionSynchronizationRegistry;

/**
 * Bean responsável por disponibilizar para a aplicação o usuário e a sessão do 
 * usuário logado ao sistema.
 * 
 * @author Evaristo Wychoski Benfatti
 *
 */
@Stateless
public class UsuarioSessaoBean {
	
	static final String SOFTPHARMA_SESSION = "SOFTPHARMA_SESSION";
	
	@Resource
	private TransactionSynchronizationRegistry registry;
	
	public Sessao getSoftpharmaSession() {
		return (Sessao) registry.getResource(SOFTPHARMA_SESSION);
	}
	
	public boolean isUsuarioSessaoLogado() {
		return getSoftpharmaSession() != null;
	}

}
