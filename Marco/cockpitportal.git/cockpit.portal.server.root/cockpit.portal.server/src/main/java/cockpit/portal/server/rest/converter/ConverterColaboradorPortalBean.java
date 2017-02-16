package cockpit.portal.server.rest.converter;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import cockpit.portal.server.rest.enums.ColaboradorSituacao;
import cockpit.portal.server.rest.model.Colaborador;
import cockpit.portal.server.rest.model.DispositivoAcessoVendedor;
import cockpit.portal.server.rest.model.FiltroPesquisaColaborador;
import cockpit.vendedor.server.portal.vo.ColaboradorPortalVO;
import cockpit.vendedor.server.portal.vo.DispositivoAcessoPortalVO;
import cockpit.vendedor.server.portal.vo.FiltroColaboradorVO;

@Stateless
public class ConverterColaboradorPortalBean {

	public List<Colaborador> converterListaColaboradorPortalVOToListaColaboradorRest(
			List<ColaboradorPortalVO> listColaboradorPortalVO) {
		
		if (listColaboradorPortalVO == null)
			return null;
		
		List<Colaborador> listColaboradorRest = new ArrayList<>();
		
		for (ColaboradorPortalVO colaboradorPortalVO : listColaboradorPortalVO) {
			listColaboradorRest.add(
					converterColaboradorPortalVOToColaboradorRest(colaboradorPortalVO));
		}
		
		return listColaboradorRest;
	}

	public Colaborador converterColaboradorPortalVOToColaboradorRest(
			ColaboradorPortalVO colaboradorPortalVO) {
		
		if (colaboradorPortalVO == null)
			return null;
		
		Colaborador colaborador = new Colaborador();
		
		ColaboradorSituacao situacao = ColaboradorSituacao.ATIVO;
		
		switch (colaboradorPortalVO.getSituacao()) {
		case ATIVO: 
			situacao = ColaboradorSituacao.ATIVO;
			break;
			
		case INATIVO: 
			situacao = ColaboradorSituacao.INATIVO;
			break;
			
		default: break;
		}
		
		colaborador.setCpf(colaboradorPortalVO.getCpf());
		colaborador.setNome(colaboradorPortalVO.getNome());
		colaborador.setSituacao(situacao);
		colaborador.setPodeSerRemovidoDaListagem(
				colaboradorPortalVO.isPodeSerRemovidoDaListagem());
		colaborador.setPodeGerarCodigo(colaboradorPortalVO.isPodeGerarCodigo());
		
		if(colaboradorPortalVO.getDispositivosAcesso() != null){
			colaboradorPortalVO.getDispositivosAcesso()
			.forEach(dispositivoAcessoPo->{
				colaborador.getDispositivosAcesso().add(
						converterDispositivoAcessoPortalVOToDispositivoRest(
								dispositivoAcessoPo));
			});
		}
		
		return colaborador;
	}

	private DispositivoAcessoVendedor converterDispositivoAcessoPortalVOToDispositivoRest(
			DispositivoAcessoPortalVO dispositivoAcessoPo) {
		if (dispositivoAcessoPo == null)
			return null;
		
		DispositivoAcessoVendedor dispositivoAcessoPortalVO = 
				new  DispositivoAcessoVendedor();
		
		dispositivoAcessoPortalVO.setModelo(dispositivoAcessoPo.getModelo());
		dispositivoAcessoPortalVO.setChaveDispositivo(dispositivoAcessoPo.getChaveDispositivo());
		dispositivoAcessoPortalVO.setCodigoAcesso(dispositivoAcessoPo.getCodigoAcesso());
		
		return dispositivoAcessoPortalVO;
	}

	public FiltroColaboradorVO converterFiltroColaboradorVoToFiltroColaborador(
			FiltroPesquisaColaborador filtroVO) {
		
		if (filtroVO == null)
			return null;
		
		FiltroColaboradorVO filtro = new FiltroColaboradorVO();
		filtro.setNome(filtroVO.getNome());
		
		return filtro;
	}
	
}
