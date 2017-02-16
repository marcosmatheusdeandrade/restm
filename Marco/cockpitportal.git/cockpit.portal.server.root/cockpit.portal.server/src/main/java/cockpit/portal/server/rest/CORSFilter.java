package cockpit.portal.server.rest;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import cockpit.portal.server.rest.resource.LoginRestResourceBean;


public class CORSFilter implements Filter {
	
	
	public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

		((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
		((HttpServletResponse) response).addHeader("Access-Control-Allow-Headers", "accept, content-type," + LoginRestResourceBean.COCKPTI_SESSION_ID_KEY);
		 
		((HttpServletResponse) response).addHeader("Access-Control-Allow-Credentials", "true");
		((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		
        chain.doFilter(request, response);        
        
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }
    
}
