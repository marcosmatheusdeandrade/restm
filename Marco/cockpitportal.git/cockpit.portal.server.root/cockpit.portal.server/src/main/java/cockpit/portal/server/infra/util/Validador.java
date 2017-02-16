package cockpit.portal.server.infra.util;

public interface Validador<T> {

	public void validar(String value) throws ValidadorException;
	
	public String formatar(T t);
	
	public T converter(String s);
	
}
