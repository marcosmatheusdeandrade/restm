package br.linx.regras;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.linx.eao.UsuarioEAO;
import br.linx.model.Usuario;

@Stateless
public class Rules {
	
	@EJB
	private UsuarioEAO eao;
	
	public Usuario inserir(Usuario usuario) {
		return eao.inserir(usuario);
	}
	
	public void deletar(Usuario usuario){
		eao.deletar(usuario);
	}
	
	public List<Usuario> listar(){
		return 	eao.listar();
	}
	
}
