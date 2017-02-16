package cockpit.portal.server.rest.resource;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.core.Response;

import cockpit.diretor.server.app.json.BusinessExceptionDiretor;
import cockpit.diretor.server.portal.vo.DiretorPortalVO;
import cockpit.portal.server.infra.exception.LoginBusinessException;
import cockpit.portal.server.infra.rules.DiretorBean;
import cockpit.portal.server.rest.BusinessResourceException;
import cockpit.portal.server.rest.LoginException;
import cockpit.portal.server.rest.converter.ConverterDiretorPortalBean;
import cockpit.portal.server.rest.model.Diretor;
import cockpit.portal.server.rest.session.InterceptorSessao;
import cockpit.portal.server.rest.util.ExceptionsUtil;

@Stateless
@Interceptors(InterceptorSessao.class)
public class DiretorResourceBean implements DiretorResouce {

	@EJB
	private DiretorBean diretorBean;
	
	@EJB
	private ConverterDiretorPortalBean converter;

	@Override
	public Response salvarDiretor(Diretor diretor) {
		try {
			DiretorPortalVO diretorPortalVO = converter.converterDiretorParaDiretorPortalVO(diretor);

			DiretorPortalVO diretorPersistido = diretorBean.salvarDiretor(diretorPortalVO);

			return Response.ok(converter.converterDiretorPortalVOParaDiretor(diretorPersistido)).build();

		} catch (Exception e) {
			try {
				ExceptionsUtil.throwRootCauseException(e);
			} catch (BusinessExceptionDiretor e1) {
				throw new BusinessResourceException(e.getMessage());
			} catch (LoginBusinessException e1) {
				throw new LoginException(e.getMessage());
			} catch (Throwable e1) {
			}

			throw new BusinessResourceException();
		}
	}

	@Override
	public Response consultarDiretoresPorNome(String nome) {
		try {
			List<DiretorPortalVO> diretoresVO = diretorBean.consultarDiretorPorNome(nome);

			List<Diretor> diretores = converter.converterDiretorParaDiretorPortalVO(diretoresVO);

			return Response.ok(diretores).build();

		} catch (Exception e) {
			try {
				ExceptionsUtil.throwRootCauseException(e);
			} catch (BusinessExceptionDiretor e1) {
				throw new BusinessResourceException(e.getMessage());
			} catch (LoginBusinessException e1) {
				throw new LoginException(e.getMessage());
			} catch (Throwable e1) {
			}

			throw new BusinessResourceException();
		}
	}

	@Override
	public Response excluirDiretor(Integer id) {

		try {
			diretorBean.removerDiretor(id);

			return Response.ok().build();

		} catch (Exception e) {
			try {
				ExceptionsUtil.throwRootCauseException(e);
			} catch (BusinessExceptionDiretor e1) {
				throw new BusinessResourceException(e.getMessage());
			} catch (LoginBusinessException e1) {
				throw new LoginException(e.getMessage());
			} catch (Throwable e1) {
			}

			throw new BusinessResourceException();
		}
	}

}
