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
import cockpit.vendedor.server.app.json.BusinessExceptionVendedor;
import cockpit.vendedor.server.portal.rules.local.IDispositivoVendedorBeanLocal;
import cockpit.vendedor.server.portal.vo.enums.ETipoColaboradorVO;

@Stateless
public class DispositivoAcessoVendedorBean {
	
    @EJB
    private ControleAcessoBean controleAcessoBean;
    
    @EJB
    private IDispositivoVendedorBeanLocal dispositivoVendedorBean;

    @EJB
	private ResponsavelEao responsavelEao; 
    
    public String gerarCodigoAcesso(String cpf, 
    		ETipoColaboradorVO tipoColaboradorVO){
    	
    	// TODO POSTERIORMENTE VALIDAR SE CPF DUPLICADO PARA COLABORADOR
    	
    	UsuarioPo usuarioLogado = controleAcessoBean.getUsuarioLogado();
    	
    	validarSeResponsavelLogado(usuarioLogado);
    	
    	ResponsavelPo responsavelLogado = responsavelEao.consultarResponsavelPorId(
				((ResponsavelPo)usuarioLogado).getId());
    	
    	List<String> idsErp = extrairIdsErpDaRede(responsavelLogado.getRedes());
    		
    	Integer quantidadeUtilizada = dispositivoVendedorBean.quantidadeCodigosUtilizadosPelosErps(idsErp);
    	
    	validarQuantidadeMaximaDispositivos(
    			responsavelLogado.getQuantidadeCodigosAdquiridosVendedor(),
    			quantidadeUtilizada);
    	
		return dispositivoVendedorBean.gerarCodigoAcesso(cpf, idsErp, tipoColaboradorVO);
    	
    }
    
	private List<String> extrairIdsErpDaRede(Set<RedePo> redes) {
		List<String> idsErp = new ArrayList<>();
    	
    	redes.forEach(rede -> idsErp.add(rede.getIdErp()));
		return idsErp;
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
			throw new BusinessExceptionVendedor(Message.ERRO_LIMITE_DISPOSITIVOS_ATINGIDO.toString());
	}

	private void validarSeQuantidadeDispositivosIgualQuantidadeMaxima(Integer quantidadeCodigosAdquiridos,
			Integer quantidadeCodigosUtilizados) {
		if(quantidadeCodigosAdquiridos <= quantidadeCodigosUtilizados)
			throw new BusinessExceptionVendedor(Message.ERRO_LIMITE_DISPOSITIVOS_ATINGIDO.toString());
			
	}
	
	

	public void removerDispositivoAcesso(String cpf, String codigoGerado, 
			ETipoColaboradorVO tipoColaboradorVO) {
    	
    	UsuarioPo usuarioLogado = controleAcessoBean.getUsuarioLogado();
    	
    	validarSeResponsavelLogado(usuarioLogado);
    	
    	dispositivoVendedorBean.removerDispositivoAcesso(cpf, codigoGerado, 
    			tipoColaboradorVO);
	}

	private void validarSeResponsavelLogado(UsuarioPo usuarioLogado) {
		if (usuarioLogado == null)
			throw new LoginBusinessException(Message.ERRO_USUARIO_DEVE_ESTAR_LOGADO.toString());
		
		if ( !(usuarioLogado instanceof ResponsavelPo))
			throw new LoginBusinessException(Message.ERRO_USUARIO_DEVE_ESTAR_LOGADO.toString());
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