package cockpit.portal.server.infra.rules;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import cockpit.portal.server.infra.UsuarioEao;
import cockpit.portal.server.infra.exception.BusinessException;
import cockpit.portal.server.infra.po.UsuarioPo;

@Stateless
public class LoginUsuarioBean {
	
	@EJB
	private UsuarioEao usuarioEao;

	@EJB
	private ControleAcessoBean controleAcessoBean;
	
	public void logar(String login, String senha){
		
		UsuarioPo usuarioPo = consultarResponsavelPorLogin(login);
		
		realizarValidacoesLogin(login, senha, usuarioPo);
		
		controleAcessoBean.logarSessao(usuarioPo);
	}

	private UsuarioPo consultarResponsavelPorLogin(String login) {
		if (login == null)
			return null;
		
		return usuarioEao.consultarUsuarioPorLogin(login);
	}
	
	private void realizarValidacoesLogin(String login, String senha,
			UsuarioPo usuarioPo) {
		
		validarSeLoginFoiInformado(login);
		
		validarSeSenhaFoiInformada(senha);
		
		validarSeUsuarioCadastrado(usuarioPo);
		
		validarSeSenhaInformadaEstaCorreta(usuarioPo, senha);
		
	}

	private void validarSeLoginFoiInformado(String login) {

		if (login == null || login.isEmpty()) {
			throw new BusinessException(Message.ERRO_LOGIN_NAO_INFORMADO.toString());
		}
	}

	private void validarSeSenhaFoiInformada(String senha) {
		
		if (senha == null || senha.isEmpty()) {
			throw new BusinessException(Message.ERRO_SENHA_NAO_INFORMADA.toString());
		}
	}

	private void validarSeUsuarioCadastrado(
			UsuarioPo usuario) {
		
		if (usuario == null) {
			throw new BusinessException(Message.ERRO_LOGIN_SENHA_INVALIDOS.toString());
		}
	}

	private void validarSeSenhaInformadaEstaCorreta(
			UsuarioPo usuario, String senha) {
		
		if (!senha.equals(usuario.getSenha())) {
			throw new BusinessException(Message.ERRO_LOGIN_SENHA_INVALIDOS.toString());
		}
	}
	
	public void logout(){
		controleAcessoBean.deslogarSessao();
	}
	
	enum Message {

		ERRO_LOGIN_NAO_INFORMADO("O login deve ser informado"),
		ERRO_SENHA_NAO_INFORMADA("A senha deve ser informada"),
		ERRO_LOGIN_SENHA_INVALIDOS("O login e/ou a senha informado está inválido."),

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
