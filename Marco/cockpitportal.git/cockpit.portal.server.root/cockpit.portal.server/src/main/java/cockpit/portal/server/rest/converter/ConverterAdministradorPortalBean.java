package cockpit.portal.server.rest.converter;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import cockpit.portal.server.infra.enums.ENivelAcesso;
import cockpit.portal.server.infra.po.AdministradorPo;
import cockpit.portal.server.rest.enums.NivelAcesso;
import cockpit.portal.server.rest.model.Administrador;

@Stateless
public class ConverterAdministradorPortalBean {
	
	public AdministradorPo converterAdministradorParaAdministradorPo(
			Administrador administrador){
		
		if(administrador == null){
			return null;
		}
		
		AdministradorPo administradorPo = new AdministradorPo();
		
		administradorPo.setDataCadastro(administrador.getDataCadastro());
		administradorPo.setId(administrador.getId());
		administradorPo.setLogin(administrador.getLogin());
		administradorPo.setNome(administrador.getNome());
		administradorPo.setSenha(administrador.getSenha());
		administradorPo.setEmail(administrador.getEmail());
		
		ENivelAcesso nivelAcesso = converterNivelAcessoParaENivelAcesso(administrador.getNivelAcesso());
		administradorPo.setNivelAcesso(nivelAcesso);
		
		return administradorPo;
	}

	private ENivelAcesso converterNivelAcessoParaENivelAcesso(NivelAcesso nivelAcesso) {
		
		if(nivelAcesso == null){
			return null;
		}
		
		switch (nivelAcesso) {
		case ADMINISTRADOR:
			return ENivelAcesso.ADMINISTRADOR;
			
		case COMERCIAL:
			return ENivelAcesso.COMERCIAL;
			
		case SOMENTE_LEITURA:
			return ENivelAcesso.SOMENTE_LEITURA;
			
		case SUPER_USUARIO:
			return ENivelAcesso.SUPER_USUARIO;
		}
		
		return null;
	}
	
	public Administrador converterAdministradorPoParaAdministrador(
			AdministradorPo administrador){
		
		if(administrador == null){
			return null;
		}
		
		Administrador administradorVO = new Administrador();
		
		administradorVO.setDataCadastro(administrador.getDataCadastro());
		administradorVO.setId(administrador.getId());
		administradorVO.setLogin(administrador.getLogin());
		administradorVO.setNome(administrador.getNome());
		administradorVO.setSenha(administrador.getSenha());
		administradorVO.setEmail(administrador.getEmail());
		
		NivelAcesso nivelAcesso = converterENivelAcessoParaNivelAcesso(administrador.getNivelAcesso());
		administradorVO.setNivelAcesso(nivelAcesso);
		
		return administradorVO;
	}
	
	private NivelAcesso converterENivelAcessoParaNivelAcesso(ENivelAcesso nivelAcesso) {
		
		if(nivelAcesso == null){
			return null;
		}
		
		switch (nivelAcesso) {
		case ADMINISTRADOR:
			return NivelAcesso.ADMINISTRADOR;
			
		case COMERCIAL:
			return NivelAcesso.COMERCIAL;
			
		case SOMENTE_LEITURA:
			return NivelAcesso.SOMENTE_LEITURA;
			
		case SUPER_USUARIO:
			return NivelAcesso.SUPER_USUARIO;
		}
		
		return null;
	}

	public List<Administrador> converterAdministradorPoParaAdministrador(
			List<AdministradorPo> administradoresPo) {
		
		if(administradoresPo == null){
			return null;
		}
		
		List<Administrador> administradores = new ArrayList<>();
		
		administradoresPo.forEach(administradorPo -> administradores.add(
				converterAdministradorPoParaAdministrador(administradorPo)));
		
		return administradores;
	}

}
