package cockpit.portal.server.infra.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import cockpit.diretor.server.app.json.BusinessExceptionDiretor;
import cockpit.diretor.server.portal.rules.local.IDispositivoDiretorBeanLocal;
import cockpit.portal.server.infra.ResponsavelEao;
import cockpit.portal.server.infra.exception.LoginBusinessException;
import cockpit.portal.server.infra.po.RedePo;
import cockpit.portal.server.infra.po.ResponsavelPo;
import cockpit.portal.server.infra.po.UsuarioPo;

@Stateless
public class DispositivoAcessoDiretorBean {
	
	//@EJB(lookup="java:app/cockpit.diretor.ear/cockpit.diretor.server/DispositivoAcessoDiretorBeanLocal!cockpit.diretor.server.portal.rules.local.IDispositivoDiretorBeanLocal")
	@EJB
	private IDispositivoDiretorBeanLocal dispositivoDiretorBeanLocal;
	
	@EJB
	private ControleAcessoBean controleAcessoBean;
	
	@EJB
	private ResponsavelEao responsavelEao;

	public String gerarCodigoAcesso(Integer idDiretor) throws BusinessExceptionDiretor {
		
    	
		UsuarioPo usuarioLogado = controleAcessoBean.getUsuarioLogado();
    	
    	validarSeResponsavelLogado(usuarioLogado);
    	
    	ResponsavelPo responsavelLogado = responsavelEao.consultarResponsavelPorId(
				((ResponsavelPo)usuarioLogado).getId());
    	
    	List<String> idsErp = extrairIdsErpDaRede(responsavelLogado.getRedes());
    	
    	Integer quantidadeUtilizada = dispositivoDiretorBeanLocal.quantidadeCodigosUtilizadosPelosErps(idsErp);
    	
    	validarQuantidadeMaximaDispositivos(
    			responsavelLogado.getQuantidadeCodigosAdquiridosVendedor(),
    			quantidadeUtilizada);
		
		return dispositivoDiretorBeanLocal.gerarCodigoAcesso(idDiretor);
	}
	
	private void validarQuantidadeMaximaDispositivos(
			Integer quantidadeCodigosAdquiridos,
			Integer quantidadeCodigosUtilizados) {
		
		validarSeQuantidadeMaximaMaiorQueZero(quantidadeCodigosAdquiridos);
		
		validarSeQuantidadeDispositivosIgualQuantidadeMaxima(
				quantidadeCodigosAdquiridos, quantidadeCodigosUtilizados);
		
	}

	private void validarSeQuantidadeMaximaMaiorQueZero(Integer quantidadeCodigosAdquiridos) {
		if(quantidadeCodigosAdquiridos == 0)
			throw new BusinessExceptionDiretor(Message.ERRO_LIMITE_DISPOSITIVOS_ATINGIDO.toString());
	}

	private void validarSeQuantidadeDispositivosIgualQuantidadeMaxima(Integer quantidadeCodigosAdquiridos,
			Integer quantidadeCodigosUtilizados) {
		if(quantidadeCodigosAdquiridos <= quantidadeCodigosUtilizados)
			throw new BusinessExceptionDiretor(Message.ERRO_LIMITE_DISPOSITIVOS_ATINGIDO.toString());
			
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
			throw new LoginBusinessException(Message.ERRO_USUARIO_DEVE_ESTAR_LOGADO.toString());
	}

	public void removerDispositivoAcesso(Integer id, String codigoGerado) 
			throws BusinessExceptionDiretor {
		
		UsuarioPo usuarioLogado = controleAcessoBean.getUsuarioLogado();
    	
    	validarSeResponsavelLogado(usuarioLogado);
		
		dispositivoDiretorBeanLocal.removerDispositivoAcesso(id, codigoGerado);
	}
	
	enum Message {

		ERRO_USUARIO_DEVE_ESTAR_LOGADO("Usúario deve estar logado para realizar a operação"),
		ERRO_USUARIO_DEVE_LOGADO_DEVE_SER_TIPO_RESPONSAVEL("Usúario logado para realizar a operação deve ser o responsavel pela rede"),        
        ERRO_LIMITE_DISPOSITIVOS_ATINGIDO("O limite de dispositivos autorizados foi atingido."), 
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
