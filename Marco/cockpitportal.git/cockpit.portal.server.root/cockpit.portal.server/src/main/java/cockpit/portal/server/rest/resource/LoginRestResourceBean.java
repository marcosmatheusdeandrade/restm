package cockpit.portal.server.rest.resource;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import cockpit.portal.server.infra.exception.BusinessException;
import cockpit.portal.server.infra.exception.LoginBusinessException;
import cockpit.portal.server.infra.rules.LoginUsuarioBean;
import cockpit.portal.server.infra.rules.sessao.UsuarioSessaoBean;
import cockpit.portal.server.rest.BusinessResourceException;
import cockpit.portal.server.rest.LoginException;
import cockpit.portal.server.rest.model.Login;
import cockpit.portal.server.rest.util.ExceptionsUtil;
import cockpit.vendedor.server.app.json.BusinessExceptionVendedor;

@Stateless
public class LoginRestResourceBean implements LoginRestResource {
	
	public static final String COCKPTI_SESSION_ID_KEY = "COCKPTI_SESSION_ID_KEY";
	
	@EJB
	private LoginUsuarioBean loginResponsavelBean;
	
	@EJB
	private UsuarioSessaoBean usuarioSessaoBean;
	
	@Override
	public Response login(Login login) {
		try {
			
			loginResponsavelBean.logar(login.getUsuario(), login.getSenha());
			
			String sessionId = usuarioSessaoBean
					.getSoftpharmaSession().getSessionId();
			
			return Response.ok()
					.header("Access-Control-Expose-Headers", COCKPTI_SESSION_ID_KEY)
					.header(COCKPTI_SESSION_ID_KEY, sessionId)
					.build();
			
		} catch(Exception e) {
			try {
				ExceptionsUtil.throwRootCauseException(e);
			} catch (BusinessExceptionVendedor e1) {
				throw new BusinessResourceException(e.getMessage());
			} catch (LoginBusinessException e1) {
				throw new BusinessResourceException(e.getMessage());
			} catch (BusinessException e1) {
				throw new BusinessResourceException(e.getMessage());
			} catch (Throwable e1) {}
			
			throw new BusinessResourceException();
		}
	}
	
	@Override
	public Response logout() {
		try {
			
			loginResponsavelBean.logout();
			
			return Response.ok().build();
			
		} catch(Exception e) {
			try {
				ExceptionsUtil.throwRootCauseException(e);
			} catch (BusinessExceptionVendedor e1) {
				throw new BusinessResourceException(e.getMessage());
			} catch (LoginBusinessException e1) {
				throw new LoginException(e.getMessage());
			} catch (Throwable e1) {}
			
			throw new BusinessResourceException();
		}
	}
	
}
