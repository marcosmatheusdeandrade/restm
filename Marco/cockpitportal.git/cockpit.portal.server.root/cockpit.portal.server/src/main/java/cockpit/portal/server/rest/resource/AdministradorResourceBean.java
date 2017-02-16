package cockpit.portal.server.rest.resource;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.core.Response;

import cockpit.portal.server.infra.exception.LoginBusinessException;
import cockpit.portal.server.infra.po.AdministradorPo;
import cockpit.portal.server.infra.rules.AdministradorBean;
import cockpit.portal.server.rest.BusinessResourceException;
import cockpit.portal.server.rest.LoginException;
import cockpit.portal.server.rest.converter.ConverterAdministradorPortalBean;
import cockpit.portal.server.rest.model.Administrador;
import cockpit.portal.server.rest.session.InterceptorSessao;
import cockpit.portal.server.rest.util.ExceptionsUtil;
import cockpit.vendedor.server.app.json.BusinessExceptionVendedor;

@Stateless
@Interceptors(InterceptorSessao.class)
public class AdministradorResourceBean implements AdministradorResource {

	@EJB
	private AdministradorBean administradorBean;
	
	@EJB
	private ConverterAdministradorPortalBean converter;
	
	@Override
	public Response salvar(Administrador administrador) {
		
		try {
			
			AdministradorPo administradorPo = converter.converterAdministradorParaAdministradorPo(
					administrador);
			
			AdministradorPo administradorPoSalvo = administradorBean.salvarAdministrador(administradorPo);
			
			return Response.ok(converter.converterAdministradorPoParaAdministrador(administradorPoSalvo)).build();
		} catch (Exception e) {
				
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

	@Override
	public Response consultarPorNome(String nome) {
		
		try {
			List<AdministradorPo> administradoresPo = administradorBean.consultarAdministradoresPorNome(nome);
			
			List<Administrador> administradores = converter.converterAdministradorPoParaAdministrador
					(administradoresPo);
			
			return Response.ok(administradores).build();
			
		} catch (Exception e) {
			
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

	@Override
	public Response excluir(Integer id) {
		
		try {
			
			administradorBean.excluirAdministrador(id);
			
			return Response.ok().build();
			
		} catch (Exception e) {
			
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
