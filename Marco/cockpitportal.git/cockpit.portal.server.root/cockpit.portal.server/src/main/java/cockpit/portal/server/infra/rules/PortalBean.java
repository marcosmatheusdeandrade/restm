package cockpit.portal.server.infra.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import cockpit.diretor.server.portal.rules.local.IDispositivoDiretorBeanLocal;
import cockpit.portal.server.infra.AdministradorEao;
import cockpit.portal.server.infra.ResponsavelEao;
import cockpit.portal.server.infra.po.AdministradorPo;
import cockpit.portal.server.infra.po.RedePo;
import cockpit.portal.server.infra.po.ResponsavelPo;
import cockpit.portal.server.infra.po.UsuarioPo;
import cockpit.vendedor.server.app.json.BusinessExceptionVendedor;
import cockpit.vendedor.server.portal.rules.local.IDispositivoVendedorBeanLocal;

@Stateless
public class PortalBean {

	@EJB
	private ControleAcessoBean controleAcessoBean; 
	
//	@EJB( lookup = "java:app/cockpit.vendedor.ear/cockpit.vendedor.server/DispositivoAcessoBeanLocal!cockpit.vendedor.server.portal.rules.local.IDispositivoVendedorBeanLocal")
	@EJB
	private IDispositivoVendedorBeanLocal dispositivoVendedorBean;
	
	@EJB
	private ResponsavelEao responsavelEao;
	
	@EJB
	private AdministradorEao administradorEao;
	
//	@EJB(lookup="java:app/cockpit.diretor.ear/cockpit.diretor.server/DispositivoAcessoDiretorBeanLocal!cockpit.diretor.server.portal.rules.local.IDispositivoDiretorBeanLocal")
	@EJB
	private IDispositivoDiretorBeanLocal dispositivoDiretorBean;
	
	public UsuarioPo informacoesPortal(){
		
		
		UsuarioPo usuarioLogado = controleAcessoBean.getUsuarioLogado();
		
		validarSeUsuarioLogado(usuarioLogado);
		
		if(usuarioLogado instanceof AdministradorPo){
			
			administradorEao.consultarAdministradorPorId(
					((AdministradorPo)usuarioLogado).getId());
		}else if(usuarioLogado instanceof ResponsavelPo){
			
			return responsavelEao.consultarResponsavelPorId(
					((ResponsavelPo)usuarioLogado).getId());
		}
		
		return usuarioLogado;
	}
	
	public Integer quantidadeCodigosUtilizadosVendedor(){
		
		UsuarioPo usuarioLogado = controleAcessoBean.getUsuarioLogado();
		
		validarSeUsuarioLogado(usuarioLogado);
		
		ResponsavelPo responsavelPoLogado = responsavelEao.consultarResponsavelPorId(
				((ResponsavelPo)usuarioLogado).getId());
		
		List<String> idsErp = extrairIdsErpDaRede(responsavelPoLogado.getRedes());
		
		return dispositivoVendedorBean.quantidadeCodigosUtilizadosPelosErps(idsErp);
		
	}
	
	public Integer quantidadeCodigosUtilizadosDiretor(){
		
		UsuarioPo usuarioLogado = controleAcessoBean.getUsuarioLogado();
		
		validarSeUsuarioLogado(usuarioLogado);
		ResponsavelPo responsavelPoLogado = responsavelEao.consultarResponsavelPorId(
				((ResponsavelPo)usuarioLogado).getId());
		
		List<String> idsErp = extrairIdsErpDaRede(responsavelPoLogado.getRedes());
		
		return dispositivoDiretorBean.quantidadeCodigosUtilizadosPelosErps(idsErp);
		
	}
	
	private List<String> extrairIdsErpDaRede(Set<RedePo> redes) {
		List<String> idsErp = new ArrayList<>();
    	
    	redes.forEach(rede -> idsErp.add(rede.getIdErp()));
		return idsErp;
	}
	
	private void validarSeUsuarioLogado(UsuarioPo usuarioLogado) {
		if (usuarioLogado == null) {
			throw new BusinessExceptionVendedor(Message.ERRO_USUARIO_DEVE_ESTAR_LOGADO.toString());
		}
	}
	
	enum Message {

		ERRO_USUARIO_DEVE_ESTAR_LOGADO("Usúario deve estar logado para realizar a operação"),
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
