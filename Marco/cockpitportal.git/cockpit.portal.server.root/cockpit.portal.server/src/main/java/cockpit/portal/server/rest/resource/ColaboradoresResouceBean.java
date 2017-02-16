package cockpit.portal.server.rest.resource;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.core.Response;

import cockpit.portal.server.infra.exception.LoginBusinessException;
import cockpit.portal.server.infra.rules.ColaboradorBean;
import cockpit.portal.server.infra.rules.DispositivoAcessoVendedorBean;
import cockpit.portal.server.rest.BusinessResourceException;
import cockpit.portal.server.rest.LoginException;
import cockpit.portal.server.rest.converter.ConverterColaboradorPortalBean;
import cockpit.portal.server.rest.model.Colaborador;
import cockpit.portal.server.rest.model.FiltroPesquisaColaborador;
import cockpit.portal.server.rest.session.InterceptorSessao;
import cockpit.portal.server.rest.util.ExceptionsUtil;
import cockpit.vendedor.server.app.json.BusinessExceptionVendedor;
import cockpit.vendedor.server.portal.vo.FiltroColaboradorVO;
import cockpit.vendedor.server.portal.vo.enums.ETipoColaboradorVO;

@Stateless
@Interceptors(InterceptorSessao.class)
public class ColaboradoresResouceBean implements ColaboradoresResouce {

	@EJB
	private ColaboradorBean colaboradorBean;
	
	@EJB
	private DispositivoAcessoVendedorBean dispositivoAcessoVendedorBean;
	
	@EJB
	private ConverterColaboradorPortalBean converter;
	
	@Override
	public Response pesquisarVendedores(
			FiltroPesquisaColaborador filtro) {
		try {
			
			FiltroColaboradorVO filtroColaboradorVO = converter
					.converterFiltroColaboradorVoToFiltroColaborador(filtro);
			filtroColaboradorVO.setTipo(ETipoColaboradorVO.VENDEDOR);
			
			List<Colaborador> colaboradoresRest = converter
					.converterListaColaboradorPortalVOToListaColaboradorRest(
							colaboradorBean.consultarColaboradorPorFiltro(
									filtroColaboradorVO));
			
			return Response.ok(colaboradoresRest).build();
			
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
	public Response listarVendedores() {
		try {
			
			FiltroColaboradorVO filtroColaboradorVO = new FiltroColaboradorVO();
			filtroColaboradorVO.setTipo(ETipoColaboradorVO.VENDEDOR);
			
			List<Colaborador> colaboradoresRest = converter
					.converterListaColaboradorPortalVOToListaColaboradorRest(
							colaboradorBean.consultarColaboradorPorFiltro(
									filtroColaboradorVO));
			
			return Response.ok(colaboradoresRest).build();
			
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
	public Response removerVendedorListagem(String cpf) {
		try {

			colaboradorBean.removerColaboradorListagem(cpf, ETipoColaboradorVO.VENDEDOR);
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
	public Response pesquisarGerentes(FiltroPesquisaColaborador filtro) {
		try {
			
			FiltroColaboradorVO filtroColaboradorVO = converter
					.converterFiltroColaboradorVoToFiltroColaborador(filtro);
			filtroColaboradorVO.setTipo(ETipoColaboradorVO.GERENTE);
			
			List<Colaborador> colaboradoresRest = converter
					.converterListaColaboradorPortalVOToListaColaboradorRest(
							colaboradorBean.consultarColaboradorPorFiltro(
									filtroColaboradorVO));
			
			return Response.ok(colaboradoresRest).build();
			
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
	public Response listarGerentes() {
		try {
			
			FiltroColaboradorVO filtroColaboradorVO = new FiltroColaboradorVO();
			filtroColaboradorVO.setTipo(ETipoColaboradorVO.GERENTE);
			
			List<Colaborador> colaboradoresRest = converter
					.converterListaColaboradorPortalVOToListaColaboradorRest(
							colaboradorBean.consultarColaboradorPorFiltro(
									filtroColaboradorVO));
			
			return Response.ok(colaboradoresRest).build();
			
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
	public Response removerGerenteListagem(String cpf) {
		try {
			
			colaboradorBean.removerColaboradorListagem(cpf, ETipoColaboradorVO.GERENTE);
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
