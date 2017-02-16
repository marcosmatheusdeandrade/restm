package cockpit.portal.server.rest.resource;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.core.Response;

import cockpit.portal.server.infra.exception.LoginBusinessException;
import cockpit.portal.server.infra.po.AdministradorPo;
import cockpit.portal.server.infra.po.ResponsavelPo;
import cockpit.portal.server.infra.po.UsuarioPo;
import cockpit.portal.server.infra.rules.PortalBean;
import cockpit.portal.server.rest.BusinessResourceException;
import cockpit.portal.server.rest.LoginException;
import cockpit.portal.server.rest.converter.ConverterAdministradorPortalBean;
import cockpit.portal.server.rest.converter.ConverterResponsavelBean;
import cockpit.portal.server.rest.model.Administrador;
import cockpit.portal.server.rest.model.InformacoesPortal;
import cockpit.portal.server.rest.model.Responsavel;
import cockpit.portal.server.rest.session.InterceptorSessao;
import cockpit.portal.server.rest.util.ExceptionsUtil;
import cockpit.vendedor.server.app.json.BusinessExceptionVendedor;

@Stateless
@Interceptors(InterceptorSessao.class)
public class InformacoesPortalResourceBean implements InformacoesPortalResource {
	

	@EJB
	private ConverterAdministradorPortalBean converterAdministradorPortalBean;
	
	@EJB
	private ConverterResponsavelBean converterResponsavelBean;
	
	@EJB
	private PortalBean portalBean;
	
	@Override
	public Response getInformacoesPortal() {
		try {

			UsuarioPo usuario = portalBean.informacoesPortal();
			
			InformacoesPortal informacoesPortal = new InformacoesPortal();
			
			if(usuario instanceof ResponsavelPo){
				
				Integer quantidadeCodigosUsadosVendedor = portalBean.quantidadeCodigosUtilizadosVendedor();
				Integer quantidadeCodigosUsadosDiretor = portalBean.quantidadeCodigosUtilizadosDiretor();
				
				Responsavel responsavel = converterResponsavelBean.converterResponsavelPoToResponsavel(
						(ResponsavelPo)usuario, quantidadeCodigosUsadosVendedor,
						quantidadeCodigosUsadosDiretor);
				
				informacoesPortal.setResponsavel(responsavel);
				
			}else if(usuario instanceof AdministradorPo){
				
				Administrador administrador = converterAdministradorPortalBean.converterAdministradorPoParaAdministrador(
						(AdministradorPo)usuario);
				
				informacoesPortal.setAdministrador(administrador);
			}
			
			return Response.ok(informacoesPortal).build();

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
