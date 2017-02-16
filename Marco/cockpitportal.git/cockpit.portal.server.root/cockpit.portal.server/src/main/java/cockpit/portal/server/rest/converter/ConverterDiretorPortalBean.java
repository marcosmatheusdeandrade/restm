package cockpit.portal.server.rest.converter;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import cockpit.diretor.server.portal.vo.DiretorPortalVO;
import cockpit.diretor.server.portal.vo.DispositivoAcessoDiretorPortalVO;
import cockpit.portal.server.rest.model.Diretor;
import cockpit.portal.server.rest.model.DispositivoAcessoDiretor;

@Stateless
public class ConverterDiretorPortalBean {

	public DiretorPortalVO converterDiretorParaDiretorPortalVO(Diretor diretor) {
		
		if(diretor == null){
			return null;
		}
		
		DiretorPortalVO diretorPortalVO = new DiretorPortalVO();
		
		diretorPortalVO.setCpf(diretor.getCpf());
		
		//DISPOSITIVOS NÃO SERÃO ATUALIZADOS AO SALVAR O DIRETOR,
		//SENDO ASSIM NÃO ESTA SENDO CONVERTIDO
//		diretorPortalVO.setDispositivosAcesso(dispositivosAcesso);
		
		diretorPortalVO.setId(diretor.getId());
		diretorPortalVO.setNome(diretor.getNome());

		return diretorPortalVO;
	}
	
	public List<Diretor> converterDiretorParaDiretorPortalVO(
			List<DiretorPortalVO> diretoresVO) {
		
		if(diretoresVO == null){
			return null;
		}
		
		List<Diretor> diretores = new ArrayList<>();
		
		diretoresVO.forEach(diretorVO -> diretores.add(
				converterDiretorPortalVOParaDiretor(diretorVO)));
		
		return diretores;
	}

	public Diretor converterDiretorPortalVOParaDiretor(DiretorPortalVO diretorVO) {
		
		if(diretorVO == null){
			return null;
		}
		
		Diretor diretor = new Diretor();
		
		diretor.setCpf(diretorVO.getCpf());
		diretor.setId(diretorVO.getId());
		diretor.setNome(diretorVO.getNome());
		
		List<DispositivoAcessoDiretor> dispositivos = 
				converterDispositivoAcessoDiretorVOParaDispositivoAcessoDiretor(
						diretorVO.getDispositivosAcesso());
		diretor.setDispositivos(dispositivos);
		
		return diretor;
	}

	private List<DispositivoAcessoDiretor> converterDispositivoAcessoDiretorVOParaDispositivoAcessoDiretor(
			List<DispositivoAcessoDiretorPortalVO> dispositivosAcessoVO) {
		
		if(dispositivosAcessoVO == null ){
			return null;
		}
		
		List<DispositivoAcessoDiretor> dispositivosAcessoDiretor = new ArrayList<>();
		
		dispositivosAcessoVO.forEach(dispositivoAcessoVO ->
			dispositivosAcessoDiretor.add(
					converterDispositivoAcessoDiretorVOParaDispositivoAcessoDiretor(dispositivoAcessoVO)));
		
		return dispositivosAcessoDiretor;
	}

	private DispositivoAcessoDiretor converterDispositivoAcessoDiretorVOParaDispositivoAcessoDiretor(
			DispositivoAcessoDiretorPortalVO dispositivoAcessoVO) {
		
		if(dispositivoAcessoVO == null){
			return null;
		}
		
		DispositivoAcessoDiretor dispositivoAcessoDiretor = new DispositivoAcessoDiretor();
		
		dispositivoAcessoDiretor.setChaveDispositivo(dispositivoAcessoVO.getChaveDispositivo());
		dispositivoAcessoDiretor.setCodigoAcesso(dispositivoAcessoVO.getCodigoAcesso());
		dispositivoAcessoDiretor.setModelo(dispositivoAcessoVO.getModelo());
		
		return dispositivoAcessoDiretor;
	}

}
