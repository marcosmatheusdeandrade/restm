package cockpit.portal.server.rest.model;

import java.util.ArrayList;
import java.util.List;

import cockpit.portal.server.rest.enums.ColaboradorSituacao;

public class Colaborador {

	private String cpf;
	private String nome;
	private String telefone;
	private ColaboradorSituacao situacao;
	private boolean podeSerRemovidoDaListagem;
	private boolean podeGerarCodigo;
	private List<DispositivoAcessoVendedor> dispositivosAcesso = new ArrayList<>();
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public ColaboradorSituacao getSituacao() {
		return situacao;
	}

	public void setSituacao(ColaboradorSituacao situacao) {
		this.situacao = situacao;
	}

	public boolean isPodeSerRemovidoDaListagem() {
		return podeSerRemovidoDaListagem;
	}

	public void setPodeSerRemovidoDaListagem(boolean podeSerRemovidoDaListagem) {
		this.podeSerRemovidoDaListagem = podeSerRemovidoDaListagem;
	}

	public List<DispositivoAcessoVendedor> getDispositivosAcesso() {
		return dispositivosAcesso;
	}

	public void setDispositivosAcesso(List<DispositivoAcessoVendedor> dispositivos) {
		this.dispositivosAcesso = dispositivos;
	}

	public boolean isPodeGerarCodigo() {
		return podeGerarCodigo;
	}

	public void setPodeGerarCodigo(boolean podeGerarCodigo) {
		this.podeGerarCodigo = podeGerarCodigo;
	}
}
