package cockpit.portal.server.rest.converter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;

import cockpit.portal.server.infra.po.RedePo;
import cockpit.portal.server.infra.po.ResponsavelPo;
import cockpit.portal.server.rest.model.Rede;
import cockpit.portal.server.rest.model.Responsavel;

@Stateless
public class ConverterResponsavelBean {
	
	public Responsavel converterResponsavelPoToResponsavel(
			ResponsavelPo responsavelPo, Integer quantidadeCodigosUsadosVendedor, 
			Integer quantidadeCodigosUsadosDiretor){
		
		if (responsavelPo == null)
			return null;
		
		Responsavel responsavel = new Responsavel();
		
		responsavel.setId(responsavelPo.getId());
		responsavel.setNome(responsavelPo.getNome());
		responsavel.setLogin(responsavelPo.getLogin());
		responsavel.setSenha(responsavelPo.getSenha());
		responsavel.setCpf(responsavelPo.getCpf());
		responsavel.setEmail(responsavelPo.getEmail());
		
		
		responsavel.setNumeroCodigosAdiquiridosDiretor(responsavelPo.getQuantidadeCodigosAdquiridosDiretor());
		responsavel.setNumeroCodigosAdiquiridosVendedor(responsavelPo.getQuantidadeCodigosAdquiridosVendedor());
		
		responsavel.setNumeroCodigosUsadosDiretor(quantidadeCodigosUsadosDiretor);
		responsavel.setNumeroCodigosUsadosVendedor(quantidadeCodigosUsadosVendedor);
		
		responsavel.setDataCadastro(responsavelPo.getDataCadastro());
		
		List<Rede> redes = converterRedePoParaRede(responsavelPo.getRedes());
		responsavel.setRedes(redes);
		
		return responsavel;
	}

	private List<Rede> converterRedePoParaRede(Set<RedePo> redesPo) {

		if(redesPo == null){
			return null;
		}
		
		List<Rede> redes = new ArrayList<>();
		
		redesPo.forEach(redePo -> redes.add(converterRedePoParaRede(redePo)));
	
		return redes;
	}

	private Rede converterRedePoParaRede(RedePo redePo) {

		if(redePo == null){
			return null;
		}
		
		Rede rede = new Rede();
		
		rede.setDescricao(redePo.getDescricaoRede());
		rede.setId(redePo.getId());
		rede.setIdInstalacaoCockpit(redePo.getIdErp());
		
		return rede;
	}
	
	public ResponsavelPo converterResponsavelParaResponsavelPo(Responsavel responsavel){
		
		if(responsavel == null){
			return null;
		}
		
		ResponsavelPo responsavelPo = new ResponsavelPo();
		
		responsavelPo.setCpf(responsavel.getCpf());
		responsavelPo.setDataCadastro(responsavel.getDataCadastro());
		responsavelPo.setEmail(responsavel.getEmail());
		responsavelPo.setId(responsavel.getId());
		responsavelPo.setLogin(responsavel.getLogin());
		responsavelPo.setNome(responsavel.getNome());
		responsavelPo.setSenha(responsavel.getSenha());
		responsavelPo.setCpf(responsavel.getCpf());
		responsavelPo.setQuantidadeCodigosAdquiridosDiretor(
				responsavel.getNumeroCodigosAdiquiridosDiretor());
		responsavelPo.setQuantidadeCodigosAdquiridosVendedor(
				responsavel.getNumeroCodigosAdiquiridosVendedor());
		
		Set<RedePo> redes = converterRedeParaRedePo(responsavel.getRedes());
		responsavelPo.setRedes(redes);
		
		return responsavelPo;
		
	}

	private Set<RedePo> converterRedeParaRedePo(List<Rede> redes) {
		
		if(redes == null){
			return null;
		}
		
		Set<RedePo> redesPo = new LinkedHashSet<>();
		
		redes.forEach(rede -> redesPo.add(converterRedeParaRedePo(rede)));
		
		return redesPo;
		
	}

	private RedePo converterRedeParaRedePo(Rede rede) {
		
		if(rede == null){
			return null;
		}
		
		RedePo redePo = new RedePo();
		
		redePo.setDescricaoRede(rede.getDescricao());
		redePo.setId(rede.getId());
		redePo.setIdErp(rede.getIdInstalacaoCockpit());
		
		return redePo;
	}

	public List<Responsavel> converterResponsavelPoToResponsavel(List<ResponsavelPo> responsaveisPo) {
		
		if(responsaveisPo == null){
			return null;
		}
		
		List<Responsavel> responsaveis = new ArrayList<>();
		
		responsaveisPo.forEach(responsavelPo -> responsaveis.add(
				converterResponsavelPoToResponsavel(responsavelPo)));
		
		return responsaveis;
	}

	private Responsavel converterResponsavelPoToResponsavel(ResponsavelPo responsavelPo) {
		
		return converterResponsavelPoToResponsavel(responsavelPo, 0, 0);
	}

}
