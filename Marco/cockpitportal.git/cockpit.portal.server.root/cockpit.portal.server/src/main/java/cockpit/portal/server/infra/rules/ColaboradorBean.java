package cockpit.portal.server.infra.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import cockpit.portal.server.infra.ResponsavelEao;
import cockpit.portal.server.infra.exception.LoginBusinessException;
import cockpit.portal.server.infra.po.RedePo;
import cockpit.portal.server.infra.po.ResponsavelPo;
import cockpit.portal.server.infra.po.UsuarioPo;
import cockpit.vendedor.server.portal.rules.local.IColaboradorBeanLocal;
import cockpit.vendedor.server.portal.vo.ColaboradorPortalVO;
import cockpit.vendedor.server.portal.vo.FiltroColaboradorVO;
import cockpit.vendedor.server.portal.vo.enums.ETipoColaboradorVO;

@Stateless
public class ColaboradorBean {


//	@EJB(lookup = "java:app/cockpit.vendedor.ear/cockpit.vendedor.server/ColaboradorBeanLocal!cockpit.vendedor.server.portal.rules.local.IColaboradorBeanLocal")
	@EJB
	private IColaboradorBeanLocal colaboradorBean;
	
	@EJB
    private ControleAcessoBean controleAcessoBean;
	
	@EJB
	private ResponsavelEao responsavelEao;

    public ColaboradorPortalVO consultarColaboradorPorCpf(String cpf, 
    		ETipoColaboradorVO tipoColaboradorVO) {
    	
    	UsuarioPo usuarioLogado= controleAcessoBean.getUsuarioLogado();
    	
    	validarSeResponsavelLogado(usuarioLogado);
    	
    	ResponsavelPo responsavelLogado = 
    			responsavelEao.consultarResponsavelPorId(
    					((ResponsavelPo)usuarioLogado).getId());
    	
    	Set<RedePo> redes = responsavelLogado.getRedes();
    	
    	List<String> idsErp = extrairIdsErpDaRede(redes);
    	
    	ColaboradorPortalVO colaboradorVO = 
    			colaboradorBean.consultarColaboradorPorCpf(cpf, idsErp, tipoColaboradorVO);
    	
		return colaboradorVO;
	}

	private List<String> extrairIdsErpDaRede(Set<RedePo> redes) {
		List<String> idsErp = new ArrayList<>();
    	
    	redes.forEach(rede -> idsErp.add(rede.getIdErp()));
		return idsErp;
	}

	private void validarSeResponsavelLogado(UsuarioPo usuarioLogado) {
		if (usuarioLogado == null)
			throw new LoginBusinessException(Message.ERRO_USUARIO_DEVE_ESTAR_LOGADO.toString());
		
		if ( !(usuarioLogado instanceof ResponsavelPo))
			throw new LoginBusinessException(Message.ERRO_USUARIO_DEVE_LOGADO_DEVE_SER_TIPO_RESPONSAVEL.toString());
	}

	public List<ColaboradorPortalVO> consultarColaboradorPorFiltro(FiltroColaboradorVO filtro) {
    	
    	UsuarioPo usuarioLogado = controleAcessoBean.getUsuarioLogado();
    	
    	validarSeResponsavelLogado(usuarioLogado);
    	
    	ResponsavelPo responsavelLogado =
    			responsavelEao.consultarResponsavelPorId(
    					((ResponsavelPo)usuarioLogado).getId());
    	
    	if(filtro == null)
    		filtro = new FiltroColaboradorVO();
    	
    	List<String> idsErp = extrairIdsErpDaRede(responsavelLogado.getRedes());
    	
    	filtro.setIdsErp(idsErp);
		
		return colaboradorBean.consultarColaboradorPorFiltro(filtro);
	}

//	public List<ColaboradorPortalVO> consultarColaboradoresVisiveis() {
//    	
//    	UsuarioPo usuarioLogado = controleAcessoBean.getUsuarioLogado();
//    	
//    	validarSeResponsavelLogado(usuarioLogado);
//    	
//    	ResponsavelPo responsavelLogado = (ResponsavelPo) usuarioLogado;
//    	
//    	List<String> idsErp = extrairIdsErpDaRede(responsavelLogado.getRedes());
//    	
//		return colaboradorBean.consultarColaboradoresVisiveis(idsErp);
//	}
//	
	public void removerColaboradorListagem(String cpf, ETipoColaboradorVO tipoColaboradorVO){
    	
    	UsuarioPo usuarioLogado = controleAcessoBean.getUsuarioLogado();
    	
    	validarSeResponsavelLogado(usuarioLogado);

    	ResponsavelPo responsavelLogado = 
    			responsavelEao.consultarResponsavelPorId(
    					((ResponsavelPo)usuarioLogado).getId());
    	
    	List<String> idsERP  = extrairIdsErpDaRede(responsavelLogado.getRedes());
		colaboradorBean.removerColaboradorListagem(cpf, idsERP, tipoColaboradorVO);
    	
	}
	
//	public void exibirColaboradorListagem(String cpf){
//		
//		UsuarioPo usuarioLogado = controleAcessoBean.getUsuarioLogado();
//		
//		validarSeResponsavelLogado(usuarioLogado);
//		
//		ColaboradorPo colaboradorPoConsultado = consultarColaboradorPorCpf(cpf);
//		validarSeColaboradorExiste(colaboradorPoConsultado);
//		
//		colaboradorPoConsultado.setVisivel(true);
//		colaboradorEao.salvarColaborador(colaboradorPoConsultado);
//		
//	}
	
	enum Message {

		ERRO_USUARIO_DEVE_ESTAR_LOGADO("Usúario deve estar logado para realizar a operação"),
		ERRO_USUARIO_DEVE_LOGADO_DEVE_SER_TIPO_RESPONSAVEL("Usúario logado para realizar a operação deve ser o responsavel pela rede"),

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
