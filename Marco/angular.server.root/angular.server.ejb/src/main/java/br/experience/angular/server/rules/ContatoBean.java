package br.experience.angular.server.rules;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.experience.angular.server.eao.ContatoEAO;
import br.experience.angular.server.eao.OperadoraEAO;
import br.experience.angular.server.model.Contato;
import br.experience.angular.server.model.Operadora;

@Stateless
public class ContatoBean {

	@EJB
	private ContatoEAO contatoEAO;
	
	@EJB
	private OperadoraEAO operadorasEAO;

	public Contato salvarContato(Contato contato) {
		
		validarSeContatoFoiInformado(contato);
		
		Contato contatoContexto = consultarContatoContexto(contato);
		
		realizarValidcoes(contato, contatoContexto);
		
		Contato contatoAPersistir = atualizarContato(contato, contatoContexto);
		
		return contatoEAO.salvarContato(contatoAPersistir);
	}

	private void validarSeContatoFoiInformado(Contato contato) {
		if (contato != null)
			return;
		
		throw new RuntimeException("Contato deve ser informado");
		
	}

	private Contato consultarContatoContexto(Contato contato) {
		if (contato.getId() != null)
			return contatoEAO.consultarContatoPorId(contato.getId());
		return null;
	}

	private void realizarValidcoes(Contato contato, Contato contatoContexto) {
		
		validarSeContatoASerAlteradoExiste(contato.getId(), contatoContexto);
		
		validarCamposObrigatorios(contato);
		
		validarTamanhoCampos(contato);
		
		validarDadosCampos(contato);
		
	}

	private void validarSeContatoASerAlteradoExiste(Integer id, Contato contatoContexto) {
		if(id != null && contatoContexto == null)
			throw new RuntimeException("Contato a ser alterado não existe");
	}

	private void validarCamposObrigatorios(Contato contato) {
		
		validarSeNomeFoiInformado(contato.getNome());
		
		validarSeTelefoneFoiInformado(contato.getTelefone());
		
		validarSeOperadoraFoiInformado(contato.getOperadora());
		
	}

	private void validarSeNomeFoiInformado(String nome) {
		if (nome == null)
			throw new RuntimeException("Nome do contato deve ser informado");
	}

	private void validarSeTelefoneFoiInformado(String telefone) {
		if (telefone == null)
			throw new RuntimeException("Telefone do contato deve ser informado");
	}

	private void validarSeOperadoraFoiInformado(Operadora operadora) {
		if (operadora == null)
			throw new RuntimeException("Operadora do contato deve ser informado");
	}

	private void validarTamanhoCampos(Contato contato) {
		
		validarTamanhoCampoNome(contato.getNome());
		
	}

	private void validarTamanhoCampoNome(String nome) {
		if(nome.length() > 60)
			throw new RuntimeException("Nome do conato deve conter no maximo 60"
					+ "caracteres");
	}

	private void validarDadosCampos(Contato contato) {
		
		validarDadosCampoTelefone(contato.getTelefone());
		
		validarDadosCampoOperadora(contato.getOperadora());
		
	}

	private void validarDadosCampoTelefone(String telefone) {
		if(telefone.matches("^[\\d]*"))
			throw new RuntimeException("Telefone do contato deve conter apenas "
					+ "números");
	}

	private void validarDadosCampoOperadora(Operadora operadora) {
		
		validarSeOperadoraExiste(operadora.getId());
		
	}

	private void validarSeOperadoraExiste(Integer id) {
		if(id == null || operadorasEAO.consultarOperadoraPorId(id) == null)
			throw new RuntimeException("A operadora deve estar cadastrada no "
					+ "sistema");
	}

	private Contato atualizarContato(Contato contato, Contato contatoContexto) {
		
		Contato contatoASerPersistido = null;
		
		if(contatoContexto == null){
			contatoASerPersistido = contato;
		}else{
			contatoContexto.setNome(contato.getNome());
			contatoContexto.setTelefone(contato.getTelefone());
			contatoContexto.setOperadora(contato.getOperadora());
			contatoContexto.setData(contato.getData());
			
			contatoASerPersistido = contatoContexto;
		}
		
		return contatoASerPersistido;
	}

	public Contato consultarContatoPorId(Integer id) {
		
		validarSeIdFoiInformado(id);
		
		return contatoEAO.consultarContatoPorId(id);
	}

	private void validarSeIdFoiInformado(Integer id) {
		if (id == null)
			throw new RuntimeException("O id não foi informado");
	}

	public Collection<Contato> consultarContatosPorNome(String nome) {
		return contatoEAO.consultarContatosPorNome(nome);
	}

	public void deletarContato(Contato contato) {
		
		validarSeContatoFoiInformado(contato);
		
		validarSeIdFoiInformado(contato.getId());
		
		Contato contatoASerDeletado = consultarContatoContexto(contato);
		
		validarSeContatoASerDeletadoExiste(contato.getId(), contatoASerDeletado);
		
		contatoEAO.deletarContato(contatoASerDeletado);
	}

	private void validarSeContatoASerDeletadoExiste(Integer id, Contato contatoASerDeletado) {
		if(id != null && contatoASerDeletado == null)
			throw new RuntimeException("Contato a ser deletado não existe");
	}
	
}
