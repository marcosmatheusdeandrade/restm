package cockpit.portal.server.infra.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import cockpit.diretor.server.app.json.BusinessExceptionDiretor;
import cockpit.diretor.server.portal.rules.local.IDiretorBeanLocal;
import cockpit.diretor.server.portal.vo.DiretorPortalVO;
import cockpit.diretor.server.portal.vo.FiltroDiretorVO;
import cockpit.diretor.server.portal.vo.enums.ETipoRedeVO;
import cockpit.portal.server.infra.ResponsavelEao;
import cockpit.portal.server.infra.exception.LoginBusinessException;
import cockpit.portal.server.infra.po.RedePo;
import cockpit.portal.server.infra.po.ResponsavelPo;
import cockpit.portal.server.infra.po.UsuarioPo;

@Stateless
public class DiretorBean {

	//@EJB(lookup = "java:app/cockpit.diretor.ear/cockpit.diretor.server/DiretorBeanLocal!cockpit.diretor.server.portal.rules.local.IDiretorBeanLocal")
	@EJB
	private IDiretorBeanLocal diretorBeanLocal;
	
	@EJB
	private ControleAcessoBean controleAcessoBean;
	
	@EJB
	private ResponsavelEao responsavelEao;

	public DiretorPortalVO consultarDiretorPorCpf(String cpf) 
			throws BusinessExceptionDiretor {
		
		UsuarioPo usuarioLogado= controleAcessoBean.getUsuarioLogado();
    	
    	validarSeResponsavelLogado(usuarioLogado);
		
		return diretorBeanLocal.consultarDiretorPorCpf(cpf);
	}
	
	private void validarSeResponsavelLogado(UsuarioPo usuarioLogado) {
		if (usuarioLogado == null)
			throw new LoginBusinessException(Message.ERRO_USUARIO_DEVE_ESTAR_LOGADO.toString());
		
		if ( !(usuarioLogado instanceof ResponsavelPo))
			throw new LoginBusinessException(Message.ERRO_USUARIO_DEVE_LOGADO_DEVE_SER_TIPO_RESPONSAVEL.toString());
	}

	public List<DiretorPortalVO> consultarDiretorPorNome(String nome) 
			throws BusinessExceptionDiretor {
		
		UsuarioPo usuarioLogado= controleAcessoBean.getUsuarioLogado();
    	
    	validarSeResponsavelLogado(usuarioLogado);
    	
    	ResponsavelPo responsavelLogado = 
    			responsavelEao.consultarResponsavelPorId(
    					((ResponsavelPo)usuarioLogado).getId());
    	
    	Set<RedePo> redes = responsavelLogado.getRedes();

    	FiltroDiretorVO filtro = new FiltroDiretorVO();
		
		filtro.setNome(nome);
		List<String> idsErp = extrairIdsErpDaRede(redes);
		filtro.setIdsErp(idsErp);
		
		return diretorBeanLocal.consultarDiretorPorFiltro(filtro);
	}

	private List<String> extrairIdsErpDaRede(Set<RedePo> redes) {
		List<String> idsErp = new ArrayList<>();
    	
    	redes.forEach(rede -> idsErp.add(rede.getIdErp()));
		return idsErp;
	}
	
	public void removerDiretor(Integer id) throws BusinessExceptionDiretor {
		
		UsuarioPo usuarioLogado= controleAcessoBean.getUsuarioLogado();
    	
    	validarSeResponsavelLogado(usuarioLogado);
    	
		diretorBeanLocal.removerDiretor(id);
	}

	public DiretorPortalVO salvarDiretor(DiretorPortalVO diretorPortalVO) throws BusinessExceptionDiretor {
		
		UsuarioPo usuarioLogado= controleAcessoBean.getUsuarioLogado();
    	
    	validarSeResponsavelLogado(usuarioLogado);
		
    	ResponsavelPo responsavelLogado = 
    			responsavelEao.consultarResponsavelPorId(
    					((ResponsavelPo)usuarioLogado).getId());
    	
    	Set<RedePo> redes = responsavelLogado.getRedes();
    	
    	if(redes.size() > 1){
    		diretorPortalVO.setTipoRede(ETipoRedeVO.INDIVIDUAL);
    	}else{
    		diretorPortalVO.setTipoRede(ETipoRedeVO.NORMAL);
    	}
    	
    	diretorPortalVO.setIdsErp(
    			redes.stream().map(rede -> rede.getIdErp()).collect(
    					Collectors.toList()));
    	
		return diretorBeanLocal.salvarDiretor(diretorPortalVO);
	}
	
	
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
