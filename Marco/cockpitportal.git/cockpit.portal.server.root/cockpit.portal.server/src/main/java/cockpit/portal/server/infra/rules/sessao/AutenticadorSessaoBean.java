package cockpit.portal.server.infra.rules.sessao;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.TransactionSynchronizationRegistry;

import cockpit.portal.server.infra.exception.LoginBusinessException;

@Stateless
public class AutenticadorSessaoBean {

	@EJB
	private ControladorSessaoBean controladorSessaoBean;
	
	@Resource
	private TransactionSynchronizationRegistry registry;
	
	public boolean login() {
		// TODO VERIFICAR SE HÁ NECESSIDADE DE CONTROLAR O ENCADEAMENTO DE LOGINS

		Sessao sessaoLogada = controladorSessaoBean.criarERegistrarNovaSessao();

		registry.putResource(UsuarioSessaoBean.SOFTPHARMA_SESSION, sessaoLogada);
		
		return true;
	}

	public void logout() {
		Sessao sessao = (Sessao) registry.getResource(UsuarioSessaoBean.SOFTPHARMA_SESSION);

		controladorSessaoBean.liberarSessao(sessao);
		registry.putResource(UsuarioSessaoBean.SOFTPHARMA_SESSION, null);
	}

	private void validarSessaoDiferenteDeNulo(Sessao sessao) {
		if (sessao == null)
			throw new LoginBusinessException(Message.ERRO_SESSAO_INVALIDA.toString());
	}

	public void retrieveSessionFromSessionId(String sessionId) {
		Sessao session = controladorSessaoBean.getSessao(sessionId);
		
		validarSessaoDiferenteDeNulo(session);
		
		registry.putResource(UsuarioSessaoBean.SOFTPHARMA_SESSION, session);
	}
	
	enum Message {

		ERRO_SESSAO_INVALIDA("Sessão inválida."),

        ;

        private String message;

        Message(String message) {

            this.message = message;
        }

        @Override
        public String toString() {
            return message;
        }
    }

}
