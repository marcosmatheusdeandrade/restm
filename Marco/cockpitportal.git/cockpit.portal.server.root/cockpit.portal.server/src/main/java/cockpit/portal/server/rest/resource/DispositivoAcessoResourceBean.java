package cockpit.portal.server.rest.resource;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.core.Response;

import cockpit.diretor.server.app.json.BusinessExceptionDiretor;
import cockpit.portal.server.infra.exception.LoginBusinessException;
import cockpit.portal.server.infra.rules.DispositivoAcessoDiretorBean;
import cockpit.portal.server.infra.rules.DispositivoAcessoVendedorBean;
import cockpit.portal.server.rest.BusinessResourceException;
import cockpit.portal.server.rest.LoginException;
import cockpit.portal.server.rest.session.InterceptorSessao;
import cockpit.portal.server.rest.util.ExceptionsUtil;
import cockpit.vendedor.server.app.json.BusinessExceptionVendedor;
import cockpit.vendedor.server.portal.vo.enums.ETipoColaboradorVO;

@Stateless
@Interceptors(InterceptorSessao.class)
public class DispositivoAcessoResourceBean implements DispositivoAcessoResource {

	@EJB
	private DispositivoAcessoVendedorBean dispositivoVendedorAcessoBean;
	
	@EJB
	private DispositivoAcessoDiretorBean dispositivoAcessoDiretorBean;
	
	@Override
	public Response gerarCodigoAcessoDispositivoVendedor(String cpf) {
		try {
			return Response.ok(dispositivoVendedorAcessoBean.gerarCodigoAcesso(
					cpf, ETipoColaboradorVO.VENDEDOR))
					.build();
		} catch (Exception e) {
			try {
				ExceptionsUtil.throwRootCauseException(e);
			} catch (BusinessExceptionVendedor e1) {
				throw new BusinessResourceException(e.getMessage());
			} catch (LoginBusinessException e1) {
				throw new LoginException(e.getMessage());
			} catch (Throwable e1) {
			}

			throw new BusinessResourceException();
		}
	}

	@Override
	public Response gerarCodigoAcessoDispositivoGerente(String cpf) {
		try {
			return Response.ok(dispositivoVendedorAcessoBean.gerarCodigoAcesso(
					cpf, ETipoColaboradorVO.GERENTE))
					.build();
		} catch (Exception e) {
			try {
				ExceptionsUtil.throwRootCauseException(e);
			} catch (BusinessExceptionVendedor e1) {
				throw new BusinessResourceException(e.getMessage());
			} catch (LoginBusinessException e1) {
				throw new LoginException(e.getMessage());
			} catch (Throwable e1) {
			}

			throw new BusinessResourceException();
		}
	}

	@Override
	public Response gerarCodigoAcessoDispositivoDiretor(Integer id) {
		System.err.println("comeceiiiiiiiiiiiiiiiiiiiiiiii");
		try {
			String codigoGerado = dispositivoAcessoDiretorBean.gerarCodigoAcesso(id);
			
			System.err.println("termineiiiiiiiiiiiiiiiiiiiiiiii");
			return Response.ok(codigoGerado).build();
		} catch(Exception e) {
			try {
				ExceptionsUtil.throwRootCauseException(e);
			} catch (BusinessExceptionDiretor e1) {
				throw new BusinessResourceException(e.getMessage());
			} catch (LoginBusinessException e1) {
				throw new LoginException(e.getMessage());
			} catch (Throwable e1) {}
			
			throw new BusinessResourceException();
		}
	}

	@Override
	public Response excluirDispositivoVendedor(String cpf, String codigoGerado) {
		try {

			dispositivoVendedorAcessoBean.removerDispositivoAcesso(
					cpf, codigoGerado, ETipoColaboradorVO.VENDEDOR);
			return Response.ok().build();

		} catch (Exception e) {
			try {
				ExceptionsUtil.throwRootCauseException(e);
			} catch (BusinessExceptionVendedor e1) {
				throw new BusinessResourceException(e.getMessage());
			} catch (LoginBusinessException e1) {
				throw new LoginException(e.getMessage());
			} catch (Throwable e1) {
			}

			throw new BusinessResourceException();
		}

	}

	@Override
	public Response excluirDispositivoGerente(String cpf, String codigoGerado) {
		try {

			dispositivoVendedorAcessoBean.removerDispositivoAcesso(
					cpf, codigoGerado, ETipoColaboradorVO.GERENTE);
			return Response.ok().build();

		} catch (Exception e) {
			try {
				ExceptionsUtil.throwRootCauseException(e);
			} catch (BusinessExceptionVendedor e1) {
				throw new BusinessResourceException(e.getMessage());
			} catch (LoginBusinessException e1) {
				throw new LoginException(e.getMessage());
			} catch (Throwable e1) {
			}

			throw new BusinessResourceException();
		}
	}

	@Override
	public Response excluirDispositivoDiretor(Integer id, String codigoGerado) {
		
		try {

			dispositivoAcessoDiretorBean.removerDispositivoAcesso(id, codigoGerado);;

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
