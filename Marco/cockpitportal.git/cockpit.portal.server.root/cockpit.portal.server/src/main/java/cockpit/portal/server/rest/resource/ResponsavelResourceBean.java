package cockpit.portal.server.rest.resource;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.core.Response;

import cockpit.portal.server.infra.exception.LoginBusinessException;
import cockpit.portal.server.infra.po.ResponsavelPo;
import cockpit.portal.server.infra.rules.ResponsavelPortalBean;
import cockpit.portal.server.rest.BusinessResourceException;
import cockpit.portal.server.rest.LoginException;
import cockpit.portal.server.rest.converter.ConverterResponsavelBean;
import cockpit.portal.server.rest.model.Responsavel;
import cockpit.portal.server.rest.session.InterceptorSessao;
import cockpit.portal.server.rest.util.ExceptionsUtil;
import cockpit.vendedor.server.app.json.BusinessExceptionVendedor;

@Stateless
@Interceptors(InterceptorSessao.class)
public class ResponsavelResourceBean implements ResponsavelResource {

	
	@EJB
	private ConverterResponsavelBean converter;
	
	@EJB
	private ResponsavelPortalBean responsavelBean;
	
	@Override
	public Response salvar(Responsavel responsavel) {
		
		try {
			ResponsavelPo responsavelPo = converter.converterResponsavelParaResponsavelPo(responsavel);
			
			responsavelBean.salvarResponsavel(responsavelPo);
			
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

	@Override
	public Response excluir(Integer idResponsavel) {
		
		try {
			responsavelBean.excluirResponsavel(idResponsavel);;
			
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

	@Override
	public Response consultarPorParteNome(String nome) {
		
		try {
			List<ResponsavelPo> responsaveisPo = responsavelBean.consultarResponsavelPorNome(nome);
			
			List<Responsavel> responsaveis = converter.converterResponsavelPoToResponsavel(
					responsaveisPo);
			
			return Response.ok(responsaveis).build();
			
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
