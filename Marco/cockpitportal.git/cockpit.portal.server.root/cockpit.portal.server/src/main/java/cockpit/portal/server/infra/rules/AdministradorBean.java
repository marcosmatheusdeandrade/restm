package cockpit.portal.server.infra.rules;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import cockpit.portal.server.infra.AdministradorEao;
import cockpit.portal.server.infra.UsuarioEao;
import cockpit.portal.server.infra.enums.ENivelAcesso;
import cockpit.portal.server.infra.po.AdministradorPo;
import cockpit.portal.server.infra.po.UsuarioPo;
import cockpit.portal.server.infra.util.ValidadorEmail;
import cockpit.portal.server.infra.util.ValidadorException;
import cockpit.vendedor.server.app.json.BusinessExceptionVendedor;

@Stateless
public class AdministradorBean {
	
	@EJB
	private ControleAcessoBean controleAcessoBean;
	
	@EJB 
	private AdministradorEao administradorEao;
	
	@EJB
	private UsuarioEao usuarioEao;
	
	public AdministradorPo salvarAdministrador(AdministradorPo administrador){
		
		UsuarioPo usuarioLogado = controleAcessoBean.getUsuarioLogado();
		
		realizarValidacoesUsuarioLogado(usuarioLogado);
		
		AdministradorPo administradorConsultado = consultarAdministradorPorId(administrador);
		UsuarioPo usuarioConsultadoPorLogin = consultarUsuarioPorLogin(administrador);
		UsuarioPo usuarioConsultadoPorEmail = consultarAdministradoresPorEmail(administrador);
		
		realizarValidacoesSalvarAdministrador(administrador, 
				administradorConsultado, usuarioConsultadoPorLogin, 
				usuarioConsultadoPorEmail, (AdministradorPo)usuarioLogado);
		
		administradorConsultado = atualizarAdministrador(administrador, 
				administradorConsultado);
		
		return administradorEao.salvarAdministrador(administradorConsultado);
	}



	private AdministradorPo consultarAdministradorPorId(AdministradorPo administrador){
	
		if(administrador == null || administrador.getId() == null){
			return null;
		}
		
		return administradorEao.consultarAdministradorPorId(administrador.getId());
		
	}
	
	private UsuarioPo consultarUsuarioPorLogin(AdministradorPo administrador) {
		
		if(administrador == null || administrador.getLogin() == null || 
				administrador.getLogin().trim().isEmpty()){
			return null;
		}
		
		return usuarioEao.consultarUsuarioPorLogin(administrador.getLogin());
	}
	
	private UsuarioPo consultarAdministradoresPorEmail(AdministradorPo administrador) {
		
		if(administrador == null || administrador.getLogin() == null || 
				administrador.getLogin().trim().isEmpty()){
			return null;
		}
		
		return usuarioEao.consultarUsuarioPorEmail(administrador.getEmail());
	}
	
	private void realizarValidacoesSalvarAdministrador(AdministradorPo administrador, 
			AdministradorPo administradorConsultado, UsuarioPo usuarioConsultadoPorLogin, 
			UsuarioPo usuarioConsultadoPorEmail, AdministradorPo administradorLogado) {
		
		validarCamposObrigatorios(administrador);
		validarTamanhoDosCampos(administrador);
		validarInformacoesDoEmail(administrador, usuarioConsultadoPorEmail);
		validarSeUsuarioExisteParaAlteracao(administrador, administradorConsultado);
		validarSeLoginSendoUtilizadoPorOutroUsuario(administradorConsultado, 
				usuarioConsultadoPorLogin);
		
		validarSeUsuarioPossuiAcessoParaSalvar(administrador, administradorLogado);

	}

	private void validarSeUsuarioPossuiAcessoParaSalvar(AdministradorPo administrador, 
			AdministradorPo administradorLogado) {
		
		if(administradorLogado.getId().equals(administrador.getId())){
			return;
		}
		
		if(administradorLogado.getNivelAcesso().equals(ENivelAcesso.ADMINISTRADOR) &&
				administrador.getNivelAcesso().equals(ENivelAcesso.SUPER_USUARIO)){
		
			throw new BusinessExceptionVendedor(Message.ERRO_USUARIO_LOGADO_SEM_ACESSO_A_REALIZAR_OPERACAO.toString());
		}
		
		if(!(administradorLogado.getNivelAcesso().equals(ENivelAcesso.ADMINISTRADOR) || 
				administradorLogado.getNivelAcesso().equals(ENivelAcesso.SUPER_USUARIO))){
			
			throw new BusinessExceptionVendedor(Message.ERRO_USUARIO_LOGADO_SEM_ACESSO_A_REALIZAR_OPERACAO.toString());
		}
		
		if(administradorLogado.getNivelAcesso().equals(ENivelAcesso.ADMINISTRADOR) &&
				administrador.getNivelAcesso().equals(ENivelAcesso.SUPER_USUARIO)){
			throw new BusinessExceptionVendedor(Message.ERRO_USUARIO_LOGADO_SEM_ACESSO_A_ALTERAR_INCLUIR_EXCLUIR_SUPER_USUARIO.toString());
			
		}
	}

	private void validarSeLoginSendoUtilizadoPorOutroUsuario(
			AdministradorPo administradorConsultado, UsuarioPo usuarioConsultadoPorLogin) {
		
		if(usuarioConsultadoPorLogin == null){
			return;
		}
		
		if(administradorConsultado == null || 
				!((AdministradorPo)usuarioConsultadoPorLogin).getId().equals(administradorConsultado.getId())){
			throw new BusinessExceptionVendedor(Message.ERRO_LOGIN_ESTA_SENDO_UTILIZADO.toString());
		}
	}

	private void validarSeUsuarioExisteParaAlteracao(AdministradorPo administrador, 
			AdministradorPo administradorConsultado) {
		if(administrador.getId() != null && administradorConsultado == null){
			throw new BusinessExceptionVendedor(Message.ERRO_USUARIO_NAO_ENCONTRADO_PARA_ALTERACAO.toString());
		}
	}

	private void validarCamposObrigatorios(AdministradorPo administrador) {
		if(administrador == null){
			throw new BusinessExceptionVendedor(Message.ERRO_USUARIO_NAO_INFORMADO.toString());
		}
		
		if(administrador.getLogin() == null || administrador.getLogin().trim().isEmpty()){
			throw new BusinessExceptionVendedor(Message.ERRO_LOGIN_USUARIO_NAO_INFORMADO.toString());
		}
		
		if(administrador.getNivelAcesso() == null){
			throw new BusinessExceptionVendedor(Message.ERRO_NIVEL_ACESSO_USUARIO_NAO_INFORMADO.toString());
		}
		
		if(administrador.getNome() == null || administrador.getNome().trim().isEmpty()){
			throw new BusinessExceptionVendedor(Message.ERRO_NOME_USUARIO_NAO_INFORMADO.toString());
		}
		
		if(administrador.getSenha() == null || administrador.getSenha().trim().isEmpty()){
			throw new BusinessExceptionVendedor(Message.ERRO_SENHA_USUARIO_NAO_INFORMADO.toString());
		}
		
		if(administrador.getEmail() == null || administrador.getEmail().trim().isEmpty()){
			throw new BusinessExceptionVendedor(Message.ERRO_EMAIL_USUARIO_NAO_INFORMADO.toString());
		}
	}

	private void validarTamanhoDosCampos(AdministradorPo administrador) {
		
		if(administrador.getEmail().length() > 100){
			throw new BusinessExceptionVendedor(Message.ERRO_TAMANHO_CAMPO_EMAIL_ULTRAPASSADO.toString());
		}
		
		if(administrador.getLogin().length() > 100){
			throw new BusinessExceptionVendedor(Message.ERRO_TAMANHO_CAMPO_LOGIN_ULTRAPASSADO.toString());
		}
		
		if(administrador.getNome().length() > 100){
			throw new BusinessExceptionVendedor(Message.ERRO_TAMANHO_CAMPO_NOME_ULTRAPASSADO.toString());
		}
		
		if(administrador.getSenha().length() > 100){
			throw new BusinessExceptionVendedor(Message.ERRO_TAMANHO_CAMPO_SENHA_ULTRAPASSADO.toString());
		}
		
		if(administrador.getSenha().length() < 6){
			throw new BusinessExceptionVendedor(Message.ERRO_TAMANHO_CAMPO_SENHA_MINIMO_NAO_ATINGIDO.toString());
			
		}
		
	}
	
	private void validarInformacoesDoEmail(AdministradorPo administrador, UsuarioPo usuarioConsultadoPorEmail) {
		
		
		validarSeEmailJaCadastrado(administrador, usuarioConsultadoPorEmail);
		validarSeEmailValido(administrador.getEmail());
		
	}
	
	private void validarSeEmailValido(String email) {
		try {
			new ValidadorEmail().validar(email);
		} catch (ValidadorException e) {
			throw new BusinessExceptionVendedor(Message.ERRO_EMAIL_INFORMADO_INVALIDO.toString());
		}
		
	}

	private void validarSeEmailJaCadastrado(AdministradorPo administrador, UsuarioPo usuarioConsultadoPorEmail) {
		
		if(usuarioConsultadoPorEmail == null){
			return;
		}
		
		if(!(usuarioConsultadoPorEmail instanceof AdministradorPo)){
			throw new BusinessExceptionVendedor(Message.ERRO_EMAIL_CADASTRADO.toString());
		}
		
		if(!((AdministradorPo)usuarioConsultadoPorEmail).getId().equals(administrador.getId())){
			throw new BusinessExceptionVendedor(Message.ERRO_EMAIL_CADASTRADO.toString());
		}
	}

	private void realizarValidacoesUsuarioLogado(UsuarioPo usuarioLogado) {
		
		if (usuarioLogado == null) {
			throw new BusinessExceptionVendedor(Message.ERRO_USUARIO_DEVE_ESTAR_LOGADO.toString());
		}

		if( !(usuarioLogado instanceof AdministradorPo)){
			throw new BusinessExceptionVendedor(Message.ERRO_USUARIO_LOGADO_SEM_ACESSO_A_REALIZAR_OPERACAO.toString());
		}
				
	}
	
	private AdministradorPo atualizarAdministrador(AdministradorPo administrador,
			AdministradorPo administradorConsultado) {
		
		AdministradorPo administradorSalvar = null;
		
		if(administradorConsultado == null){
			administradorSalvar = administrador;
			administradorSalvar.setDataCadastro(new Date());
			
		}else{
			administradorSalvar = administradorConsultado;
			
			administradorSalvar.setLogin(administrador.getLogin());
			administradorSalvar.setNivelAcesso(administrador.getNivelAcesso());
			administradorSalvar.setNome(administrador.getNome());
			administradorSalvar.setSenha(administrador.getSenha());
		}
		
		return administradorSalvar;
	}

	public void excluirAdministrador(Integer idAdministrador){
		
		UsuarioPo usuarioLogado = controleAcessoBean.getUsuarioLogado();
		
		realizarValidacoesUsuarioLogado(usuarioLogado);
		
		AdministradorPo administradorConsultado = consultarAdministradorPorId(idAdministrador);
		
		
		realizarValidacoesExcluirAdministrador(idAdministrador, administradorConsultado,
				(AdministradorPo)usuarioLogado);
		
		
		administradorEao.excluirAdministrador(idAdministrador);
		
	}
	
	private AdministradorPo consultarAdministradorPorId(Integer idAdministrador) {
		
		if(idAdministrador == null){
			return null;
		}
		
		return administradorEao.consultarAdministradorPorId(idAdministrador);
	}
	
	private void realizarValidacoesExcluirAdministrador(Integer idAdministrador,
			AdministradorPo administradorConsultado,
			AdministradorPo administradorLogado) {
		
		if(idAdministrador == null){
			throw new BusinessExceptionVendedor(Message.ERRO_USUARIO_NAO_INFORMADO.toString());
		}
		
		if(administradorConsultado == null){
			throw new BusinessExceptionVendedor(Message.ERRO_USUARIO_NAO_ENCONTRADO_PARA_EXCLUIR.toString());
		}
		
		if(administradorLogado.getNivelAcesso().equals(ENivelAcesso.ADMINISTRADOR) &&
				administradorConsultado.getNivelAcesso().equals(ENivelAcesso.SUPER_USUARIO)){
			throw new BusinessExceptionVendedor(Message.ERRO_USUARIO_LOGADO_SEM_ACESSO_A_ALTERAR_INCLUIR_EXCLUIR_SUPER_USUARIO.toString());
			
		}

		
	}

	public List<AdministradorPo> consultarAdministradoresPorNome(String nome){
		
		UsuarioPo usuarioLogado = controleAcessoBean.getUsuarioLogado();
		
		realizarValidacoesUsuarioLogado(usuarioLogado);
		
		return administradorEao.consultarAdministradoresPorNome(nome);
	}
	
	
	enum Message {

		ERRO_USUARIO_DEVE_ESTAR_LOGADO("Usúario deve estar logado para realizar a operação"),
		ERRO_USUARIO_LOGADO_SEM_ACESSO_A_REALIZAR_OPERACAO("Usúario logado não possui acesso a realizar está operação"),
		ERRO_USUARIO_NAO_INFORMADO("É necessário informar o usuário que deseja salvar"),
		ERRO_NOME_USUARIO_NAO_INFORMADO("É necessário informar o nome do usuário que deseja salvar"),
		ERRO_LOGIN_USUARIO_NAO_INFORMADO("É necessário informar o login do usuário que deseja salvar"),
		ERRO_SENHA_USUARIO_NAO_INFORMADO("É necessário informar a senha do usuário que deseja salvar"),
		ERRO_EMAIL_USUARIO_NAO_INFORMADO("É necessário informar a senha do usuário que deseja salvar"),
		ERRO_NIVEL_ACESSO_USUARIO_NAO_INFORMADO("É necessário informar o nivel de acesso do usuário que deseja salvar"), 
		ERRO_USUARIO_NAO_ENCONTRADO_PARA_ALTERACAO("Não foi possivel localizar o usuário que esta sendo alterado"),
		ERRO_USUARIO_NAO_ENCONTRADO_PARA_EXCLUIR("Não foi possivel localizar o usuário que deseja excluir"), 
		ERRO_LOGIN_ESTA_SENDO_UTILIZADO("O login informado já está sendo utilizado"),
		ERRO_TAMANHO_CAMPO_EMAIL_ULTRAPASSADO("O campo e-mail deve ter no máximo 100 caracteres"),
		ERRO_TAMANHO_CAMPO_NOME_ULTRAPASSADO("O campo nome deve ter no máximo 100 caracteres"),
		ERRO_TAMANHO_CAMPO_LOGIN_ULTRAPASSADO("O campo login deve ter no máximo 100 caracteres"),
		ERRO_TAMANHO_CAMPO_SENHA_ULTRAPASSADO("O campo senha deve ter no máximo 100 caracteres"),
		ERRO_EMAIL_CADASTRADO("E-mail informado já está sendo utilizado por outro usuário"), 
		ERRO_EMAIL_INFORMADO_INVALIDO("E-mail informado está invalido"), 
		ERRO_USUARIO_LOGADO_SEM_ACESSO_A_ALTERAR_INCLUIR_EXCLUIR_SUPER_USUARIO("O usuário administrador não possui acesso a incluir/alterar/excluir um super usuário"), 
		ERRO_TAMANHO_CAMPO_SENHA_MINIMO_NAO_ATINGIDO("A senha deve conter no minimo 6 digitos"),
        
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
