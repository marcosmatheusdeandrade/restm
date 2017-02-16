package cockpit.portal.server.infra.rules;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import cockpit.diretor.server.portal.rules.local.IDiretorBeanLocal;
import cockpit.portal.server.infra.ResponsavelEao;
import cockpit.portal.server.infra.UsuarioEao;
import cockpit.portal.server.infra.enums.ENivelAcesso;
import cockpit.portal.server.infra.po.AdministradorPo;
import cockpit.portal.server.infra.po.RedePo;
import cockpit.portal.server.infra.po.ResponsavelPo;
import cockpit.portal.server.infra.po.UsuarioPo;
import cockpit.portal.server.infra.util.GeneratorValue;
import cockpit.portal.server.infra.util.ValidadorCPF;
import cockpit.portal.server.infra.util.ValidadorEmail;
import cockpit.portal.server.infra.util.ValidadorException;
import cockpit.vendedor.server.app.json.BusinessExceptionVendedor;

@Stateless
public class ResponsavelPortalBean {
	
	private static final int MAX_CODIGO = 999_999;

	@EJB
	private UsuarioEao usuarioEao;
	
	@EJB
	private ResponsavelEao responsavelEao;
	
	@EJB
	private ControleAcessoBean controleAcessoBean;
	
	//@EJB(lookup = "java:app/cockpit.diretor.ear/cockpit.diretor.server/DiretorBeanLocal!cockpit.diretor.server.portal.rules.local.IDiretorBeanLocal")
	@EJB
	private IDiretorBeanLocal diretorBeanLocal;
	
	
	public ResponsavelPo salvarResponsavel(ResponsavelPo responsavel){
		
		UsuarioPo usuarioLogado = controleAcessoBean.getUsuarioLogado();
		
		ResponsavelPo responsavelConsultado = consultarResponsavel(responsavel);
		ResponsavelPo responsavelConsultadoPorCPF = consultarResponsavelPorCPF(responsavel);
		UsuarioPo usuarioConsultadoPorLogin = consultarUsuarioPorLogin(responsavel);
		UsuarioPo usuarioConsultadoPorEmail = consultarUsuarioPorEmail(responsavel);
		
		realizarValidacoesUsuarioLogado(usuarioLogado);
		realizarValidacoesSalvarResponsavel(responsavel, responsavelConsultado, 
				usuarioLogado, usuarioConsultadoPorLogin,
				usuarioConsultadoPorEmail, responsavelConsultadoPorCPF);
		
		salvarAlteracoesNaRedeDiretor(responsavel, responsavelConsultado);
		
		responsavelConsultado = atualizarResponsavel(responsavel, responsavelConsultado);
		
		ResponsavelPo responsavelPersistido = responsavelEao.salvarResponsavel(responsavelConsultado);
		
		return responsavelPersistido;
	}
	
	private void salvarAlteracoesNaRedeDiretor(ResponsavelPo responsavel,
			ResponsavelPo responsavelConsultado) {
		
		if(responsavelConsultado == null){
			return;
		}
		
		List<String> idsErpAtual = responsavelConsultado.getRedes()
				.stream().map(rede -> rede.getIdErp()).collect(Collectors.toList());
		
		List<String> idsErpNovos = responsavel.getRedes()
		.stream().map(rede -> rede.getIdErp()).collect(Collectors.toList());
		
		diretorBeanLocal.alterarRedesDiretor(idsErpAtual, idsErpNovos);
		
	}
	
	private void realizarValidacoesUsuarioLogado(UsuarioPo usuarioLogado) {
		
		if (usuarioLogado == null) {
			throw new BusinessExceptionVendedor(Message.ERRO_USUARIO_DEVE_ESTAR_LOGADO.toString());
		}
	}

	private ResponsavelPo consultarResponsavel(ResponsavelPo responsavel) {
		
		if(responsavel == null || responsavel.getId() == null){
			return null;
		}
		
		return responsavelEao.consultarResponsavelPorId(responsavel.getId());
	}
	
	private ResponsavelPo consultarResponsavelPorCPF(ResponsavelPo responsavel) {
		
		if(responsavel == null || responsavel.getCpf() == null || 
				responsavel.getCpf().isEmpty()){
			return null;
		}
		
		return responsavelEao.consultarResponsavelPorCPF(responsavel.getCpf());
	}
	
	private UsuarioPo consultarUsuarioPorLogin(ResponsavelPo responsavel) {
		
		if(responsavel == null || responsavel.getLogin() == null){
			return null;
		}
		
		return usuarioEao.consultarUsuarioPorLogin(responsavel.getLogin());
	}
	
	private UsuarioPo consultarUsuarioPorEmail(ResponsavelPo responsavel) {
		
		if(responsavel == null || responsavel.getEmail() == null){
			return null;
		}
		
		return usuarioEao.consultarUsuarioPorEmail(responsavel.getEmail());
	}
	
	private void realizarValidacoesSalvarResponsavel(ResponsavelPo responsavel, 
			ResponsavelPo responsavelConsultado, UsuarioPo usuarioLogado, 
			UsuarioPo usuarioConsultadoPorLogin, UsuarioPo usuarioConsultadoPorEmail,
			ResponsavelPo responsavelConsultadoPorCPF) {
		
		validarCamposObrigatorios(responsavel);
		validarTamanhoCampos(responsavel);
		validarInformacoesDoEmail(responsavel, usuarioConsultadoPorEmail);
		validarSeUsuarioExisteParaAlteracao(responsavel, responsavelConsultado);
		validarSeLoginSendoUtilizadoPorOutroUsuario(responsavelConsultado, usuarioConsultadoPorLogin);
		valdiarSeCpfSendoUtilizadoPorOutroResponsavel(responsavelConsultado, responsavelConsultadoPorCPF);
		validarSeCpfValido(responsavel);
		
	}
	
	private void validarCamposObrigatorios(ResponsavelPo responsavel) {
		
		if(responsavel == null){
			throw new BusinessExceptionVendedor(Message.ERRO_RESPONSAVEL_NAO_INFORMADO.toString());
		}
		
		if(responsavel.getEmail() == null || responsavel.getEmail().isEmpty()){
			throw new BusinessExceptionVendedor(Message.ERRO_EMAIL_RESPONSAVEL_NAO_INFORMADO.toString());
		}
		
		if(responsavel.getLogin() == null || responsavel.getLogin().isEmpty()){
			throw new BusinessExceptionVendedor(Message.ERRO_LOGIN_RESPONSAVEL_NAO_INFORMADO.toString());
		}
		
		if(responsavel.getNome() == null || responsavel.getNome().isEmpty()){
			throw new BusinessExceptionVendedor(Message.ERRO_NOME_RESPONSAVEL_NAO_INFORMADO.toString());
			
		}
		
		if(responsavel.getQuantidadeCodigosAdquiridosDiretor() == null ||
				responsavel.getQuantidadeCodigosAdquiridosDiretor().equals(0)){
			
			throw new BusinessExceptionVendedor(Message.ERRO_QUANTIDADE_CODIGOS_DIRETOR_DEVE_SER_MAIOR_QUE_ZERO.toString());
			
		}
		
		if(responsavel.getQuantidadeCodigosAdquiridosVendedor() == null){
			
			throw new BusinessExceptionVendedor(Message.ERRO_QUANTIDADE_CODIGOS_VENDEDOR_NAO_INFORMADO.toString());
			
		}
		
		if(responsavel.getRedes() == null || responsavel.getRedes().isEmpty()){
			throw new BusinessExceptionVendedor(Message.ERRO_REDE_RESPONSAVEL_NAO_INFORMADO.toString());
		}
		
		if(responsavel.getSenha() == null || responsavel.getSenha().isEmpty()){
			throw new BusinessExceptionVendedor(Message.ERRO_SENHA_RESPONSAVEL_NAO_INFORMADO.toString());
			
			
		}
		
		if(responsavel.getCpf() == null || responsavel.getCpf().isEmpty()){
			throw new BusinessExceptionVendedor(Message.ERRO_CPF_RESPONSAVEL_NAO_INFORMADO.toString());
		}
		
		for (RedePo redePo : responsavel.getRedes()) {
			
			if(redePo == null){
				
				throw new BusinessExceptionVendedor(Message.ERRO_EXISTE_PELO_MENOS_UMA_REDE_NULA.toString());
			}
			
			if(redePo.getDescricaoRede() == null || redePo.getDescricaoRede().isEmpty()){
				
				throw new BusinessExceptionVendedor(Message.ERRO_DESCRICAO_REDE_NAO_INFORMADO.toString());
			}
			
		}
	}

	private void validarTamanhoCampos(ResponsavelPo responsavel) {
		
		if(responsavel.getEmail().length() > 100){
			throw new BusinessExceptionVendedor(Message.ERRO_TAMANHO_CAMPO_EMAIL_ULTRAPASSADO.toString());
		}
		
		if(responsavel.getLogin().length() > 100){
			throw new BusinessExceptionVendedor(Message.ERRO_TAMANHO_CAMPO_LOGIN_ULTRAPASSADO.toString());
		}
		
		if(responsavel.getNome().length() > 100){
			throw new BusinessExceptionVendedor(Message.ERRO_TAMANHO_CAMPO_NOME_ULTRAPASSADO.toString());
		}
		
		if(responsavel.getSenha().length() > 100){
			throw new BusinessExceptionVendedor(Message.ERRO_TAMANHO_CAMPO_SENHA_ULTRAPASSADO.toString());
		}
		
		if(responsavel.getSenha().length() < 6){
			throw new BusinessExceptionVendedor(Message.ERRO_TAMANHO_CAMPO_SENHA_MINIMO_NAO_ATINGIDO.toString());
		}
		
		if(responsavel.getQuantidadeCodigosAdquiridosDiretor() > MAX_CODIGO){
			throw new BusinessExceptionVendedor(Message.ERRO_TAMANHO_CAMPO_CODIGO_DIRETOR_ULTRAPASSADO.toString());
		}
		
		if(responsavel.getQuantidadeCodigosAdquiridosVendedor() > MAX_CODIGO){
			throw new BusinessExceptionVendedor(Message.ERRO_TAMANHO_CAMPO_CODIGO_VENDEDOR_ULTRAPASSADO.toString());
		}
		
		for (RedePo redePo : responsavel.getRedes()) {
			if(redePo.getDescricaoRede().length() > 100){
				throw new BusinessExceptionVendedor(Message.ERRO_TAMANHO_CAMPO_DESCRICAO_REDE_ULTRAPASSADO.toString());
			}
		}
	}

	private void validarInformacoesDoEmail(ResponsavelPo responsavel,
			UsuarioPo usuarioConsultadoPorEmail) {
		
		validarSeEmailJaCadastrado(responsavel, usuarioConsultadoPorEmail);
		validarSeEmailValido(responsavel.getEmail());
		
	}
	
	private void validarSeEmailValido(String email) {
		try {
			new ValidadorEmail().validar(email);
		} catch (ValidadorException e) {
			throw new BusinessExceptionVendedor(Message.ERRO_EMAIL_INFORMADO_INVALIDO.toString());
		}
		
	}

	private void validarSeEmailJaCadastrado(ResponsavelPo responsavelPo, UsuarioPo usuarioConsultadoPorEmail) {
		
		if(usuarioConsultadoPorEmail == null){
			return;
		}
		
		if(!(usuarioConsultadoPorEmail instanceof ResponsavelPo)){
			throw new BusinessExceptionVendedor(Message.ERRO_EMAIL_CADASTRADO.toString());
		}
		
		if(!((ResponsavelPo)usuarioConsultadoPorEmail).getId().equals(responsavelPo.getId())){
			throw new BusinessExceptionVendedor(Message.ERRO_EMAIL_CADASTRADO.toString());
		}
	}

	private void validarSeUsuarioExisteParaAlteracao(ResponsavelPo responsavel,
			ResponsavelPo responsavelConsultado) {
		
		if(responsavel.getId() != null && responsavelConsultado == null){
			throw new BusinessExceptionVendedor(Message.ERRO_USUARIO_NAO_ENCONTRADO_PARA_ALTERACAO.toString());
		}
		
	}

	private void validarSeLoginSendoUtilizadoPorOutroUsuario(ResponsavelPo responsavelConsultado,
			UsuarioPo usuarioConsultadoPorLogin) {
		
		if(usuarioConsultadoPorLogin == null){
			return;
		}
		
		if(responsavelConsultado == null || 
				!((ResponsavelPo)usuarioConsultadoPorLogin).getId().equals(responsavelConsultado.getId())){
			throw new BusinessExceptionVendedor(Message.ERRO_LOGIN_ESTA_SENDO_UTILIZADO.toString());
		}
		
	}
	
	private void valdiarSeCpfSendoUtilizadoPorOutroResponsavel(ResponsavelPo responsavelConsultado,
			ResponsavelPo responsavelConsultadoPorCPF) {
		
		if(responsavelConsultadoPorCPF == null){
			return;
		}
		
		if(responsavelConsultado == null || 
				!responsavelConsultadoPorCPF.getId().equals(responsavelConsultado.getId())){
			throw new BusinessExceptionVendedor(Message.ERRO_CPF_ESTA_SENDO_UTILIZADO.toString());
		}
		
	}
	
	private void validarSeCpfValido(ResponsavelPo responsavelPo) {
		try {
			ValidadorCPF validador = new ValidadorCPF();
			validador.validar(responsavelPo.getCpf());
		} catch (ValidadorException e) {
			throw new BusinessExceptionVendedor(Message.ERRO_CPF_INVALIDO.toString());
		}
	}

	private ResponsavelPo atualizarResponsavel(ResponsavelPo responsavel, 
			ResponsavelPo responsavelConsultado) {
		
		if(responsavelConsultado == null){
			
			responsavelConsultado = responsavel;
			responsavelConsultado.setDataCadastro(new Date());
			
			//SETAR O REPONSAVEL PARA AS REDES
			for (RedePo redePo : responsavelConsultado.getRedes()) {
				redePo.setResponsavel(responsavelConsultado);
			}
			
		}else{
			
			responsavelConsultado.setLogin(responsavel.getLogin());
			responsavelConsultado.setNome(responsavel.getNome());
			
			responsavelConsultado.setQuantidadeCodigosAdquiridosDiretor(
					responsavel.getQuantidadeCodigosAdquiridosDiretor());
			
			responsavelConsultado.setQuantidadeCodigosAdquiridosVendedor(
					responsavel.getQuantidadeCodigosAdquiridosVendedor());
			
			responsavelConsultado.setSenha(responsavel.getSenha());
			
			responsavelConsultado.setEmail(responsavel.getEmail());
			
			Set<RedePo> redes = atualizarRedes(responsavelConsultado,
					responsavel.getRedes());
			responsavelConsultado.getRedes().clear();
			responsavelConsultado.getRedes().addAll(redes);
		}
		
		gerarIdErpParaRedesASeremInseridas(responsavel);
		
		return responsavelConsultado;
	}

	private Set<RedePo> atualizarRedes(ResponsavelPo responsavelConsultado, Set<RedePo> redes) {
		
		Map<Integer, RedePo> mapRedesContexto = new HashMap<>();
		Set<RedePo> redesContexto = responsavelConsultado.getRedes();
		redesContexto.forEach(redeContexto -> mapRedesContexto.put(
				redeContexto.getId(), redeContexto));
		
		Set<RedePo> redesPersistir = new LinkedHashSet<>();
		
		for (RedePo redePo : redes) {
			
			if (redePo.getId() != null) {
				RedePo redePoAtualizar = mapRedesContexto.get(redePo.getId());
				redePoAtualizar.setDescricaoRede(redePo.getDescricaoRede());
				
				// NUNCA SERA ATUALIZADO ESTE DADO
//				redePoAtualizar.setIdErp(idErp);
				
				redesPersistir.add(redePoAtualizar);
			}else{
				redePo.setResponsavel(responsavelConsultado);
				redesPersistir.add(redePo);
			}
			
		}
		
		return redesPersistir;
	}

	private void gerarIdErpParaRedesASeremInseridas(ResponsavelPo responsavel) {
		
		for (RedePo redePo : responsavel.getRedes()) {
			if(redePo.getId() == null){
				
				String idErp = new Date().getTime() + GeneratorValue.getRandomValue(6);
				
				redePo.setIdErp(idErp);
			}
			
		}
		
	}

	public List<ResponsavelPo> consultarResponsavelPorNome(String nome){
		
		UsuarioPo usuarioLogado = controleAcessoBean.getUsuarioLogado();
		realizarValidacoesUsuarioLogado(usuarioLogado);
		
		return responsavelEao.consultarResponsavelPorNome(nome);
	}
	
	public void excluirResponsavel(Integer idResponsavel){
		
		UsuarioPo usuarioLogado = controleAcessoBean.getUsuarioLogado();
		
		ResponsavelPo responsavelConsultado = consultarResponsavelPorId(idResponsavel);
		
		realizarValidacoesUsuarioLogado(usuarioLogado);
		realizarValidacoesExcluir(idResponsavel, responsavelConsultado, usuarioLogado);
		
		responsavelEao.excluir(responsavelConsultado);
		
	}

	private ResponsavelPo consultarResponsavelPorId(Integer idResponsavel) {
		
		if(idResponsavel == null){
			return null;
		}
		
		return responsavelEao.consultarResponsavelPorId(idResponsavel);
	}
	
	private void realizarValidacoesExcluir(Integer idResponsavel, 
			ResponsavelPo responsavelConsultado, UsuarioPo usuarioLogado) {
		
		if(idResponsavel == null){
			throw new BusinessExceptionVendedor(Message.ERRO_RESPONSAVEL_NAO_INFORMADO.toString());
		}
		
		if(responsavelConsultado == null){
			throw new BusinessExceptionVendedor(Message.ERRO_RESPONSAVEL_NAO_ENCONTRADO_PARA_EXCLUIR.toString());
		}
		
		if(!(usuarioLogado instanceof AdministradorPo)){
			throw new BusinessExceptionVendedor(Message.ERRO_USUARIO_LOGADO_SEM_ACESSO_A_REALIZAR_OPERACAO.toString());
		}
		
		if (((AdministradorPo) usuarioLogado).getNivelAcesso().equals(ENivelAcesso.SOMENTE_LEITURA)) {
			throw new BusinessExceptionVendedor(Message.ERRO_USUARIO_LOGADO_SEM_ACESSO_A_REALIZAR_OPERACAO.toString());
		}
		
	}
	
	enum Message {

		ERRO_USUARIO_DEVE_ESTAR_LOGADO("Usúario deve estar logado para realizar a operação"),
		ERRO_USUARIO_LOGADO_SEM_ACESSO_A_REALIZAR_OPERACAO("Usúario logado não possui acesso a realizar está operação"),
		ERRO_RESPONSAVEL_NAO_INFORMADO("É necessário informar o responsável que deseja salvar"),
		ERRO_NOME_RESPONSAVEL_NAO_INFORMADO("É necessário informar o nome do responsável que deseja salvar"),
		ERRO_LOGIN_RESPONSAVEL_NAO_INFORMADO("É necessário informar o login do responsável que deseja salvar"),
		ERRO_SENHA_RESPONSAVEL_NAO_INFORMADO("É necessário informar a senha do responsável que deseja salvar"),
		ERRO_EMAIL_RESPONSAVEL_NAO_INFORMADO("É necessário informar a senha do responsável que deseja salvar"),
		ERRO_CPF_RESPONSAVEL_NAO_INFORMADO("É necessário informar O cpf do responsável que deseja salvar"), 
		ERRO_REDE_RESPONSAVEL_NAO_INFORMADO("É necessário informar pelo menos uma rede"),
		ERRO_QUANTIDADE_CODIGOS_VENDEDOR_NAO_INFORMADO("É necessário informar valor o campo quantidade de códigos liberados vendedor"), 
		ERRO_QUANTIDADE_CODIGOS_DIRETOR_DEVE_SER_MAIOR_QUE_ZERO("Quantidade de códigos liberados para o diretor deve ser maior que zero"),
		ERRO_DESCRICAO_REDE_NAO_INFORMADO("A descrição de pelo menos uma rede não foi informada"),
		ERRO_USUARIO_NAO_ENCONTRADO_PARA_ALTERACAO("Não foi possivel localizar o usuário que esta sendo alterado"),
		ERRO_RESPONSAVEL_NAO_ENCONTRADO_PARA_EXCLUIR("Não foi possivel localizar o responsavel que deseja excluir"), 
		ERRO_LOGIN_ESTA_SENDO_UTILIZADO("O login informado já está sendo utilizado"),
		ERRO_CPF_ESTA_SENDO_UTILIZADO("O CPF informado já está sendo utilizado"),
		ERRO_TAMANHO_CAMPO_EMAIL_ULTRAPASSADO("O campo e-mail deve ter no máximo 100 caracteres"),
		ERRO_TAMANHO_CAMPO_NOME_ULTRAPASSADO("O campo nome deve ter no máximo 100 caracteres"),
		ERRO_TAMANHO_CAMPO_LOGIN_ULTRAPASSADO("O campo login deve ter no máximo 100 caracteres"),
		ERRO_TAMANHO_CAMPO_SENHA_ULTRAPASSADO("O campo senha deve ter no máximo 100 caracteres"),
		ERRO_TAMANHO_CAMPO_CODIGO_DIRETOR_ULTRAPASSADO("O campo quantidade código liberado diretor deve possuir valor máximo de 999,999"), 
		ERRO_TAMANHO_CAMPO_CODIGO_VENDEDOR_ULTRAPASSADO("O campo quantidade código liberado vendedor deve possuir valor máximo de 999,999"), 
		ERRO_TAMANHO_CAMPO_DESCRICAO_REDE_ULTRAPASSADO("O campo descrição da rede deve ter no máximo 100 caracteres"), 
		ERRO_EMAIL_CADASTRADO("E-mail informado já está sendo utilizado por outro usuário"), 
		ERRO_EMAIL_INFORMADO_INVALIDO("E-mail informado está invalido"), 
		ERRO_TAMANHO_CAMPO_SENHA_MINIMO_NAO_ATINGIDO("A senha deve conter no minimo 6 digitos"), 
		ERRO_EXISTE_PELO_MENOS_UMA_REDE_NULA("Existe pelo menos uma rede nula informada"), 
		ERRO_CPF_INVALIDO("É necessário informar um CPF válido"), 
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
