package cockpit.portal.server.rest.session;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;

import org.jboss.resteasy.spi.ResteasyProviderFactory;

import cockpit.portal.server.infra.rules.sessao.AutenticadorSessaoBean;

public class InterceptorSessao {
	
	private static final String COCKPTI_SESSION_ID_KEY = "COCKPTI_SESSION_ID_KEY";
	
	@EJB
	private AutenticadorSessaoBean autenticadorSessaoBean;
	
	@AroundInvoke
	public Object intercept(InvocationContext ctx) throws Exception {
		
		HttpServletRequest httpServletRequest = ResteasyProviderFactory.getContextData(HttpServletRequest.class);
		
		String sessionId = httpServletRequest.getHeader(COCKPTI_SESSION_ID_KEY);
		
		if (sessionId != null) {
			autenticadorSessaoBean.retrieveSessionFromSessionId(sessionId);
		}

		return ctx.proceed();
	}
	
}
