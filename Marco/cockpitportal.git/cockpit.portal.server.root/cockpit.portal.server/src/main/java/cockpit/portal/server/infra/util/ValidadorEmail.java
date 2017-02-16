package cockpit.portal.server.infra.util;

public class ValidadorEmail implements Validador<String> {
	
	@Override
	public void validar(String value) throws ValidadorException {
		if (value.matches("[a-z0-9\\._-]+@[a-z0-9\\_-]+\\.[a-z]{3}+\\.[a-z]{2}") ) {
			return;
		}
		if (value.matches("[a-z0-9\\._-]+@[a-z0-9\\_-]+\\.[a-z]{2,3}") ) {
			return;
		}
		throw new ValidadorException("Email inválido."); 
	}

	@Override
	public String formatar(String t) {
		throw new ValidadorException("Não suportado.");
	}

	@Override
	public String converter(String s) {
		throw new ValidadorException("Não suportado.");
	}
}
