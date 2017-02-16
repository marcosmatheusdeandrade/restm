package cockpit.portal.server.rest.util;

public final class ExceptionsUtil {

	public static void throwRootCauseException(final Exception exception) throws Throwable {
		Throwable rootCause = exception.getCause();

		exception.printStackTrace();
		
		while (rootCause.getCause() != null)
			rootCause = rootCause.getCause();
		
		throw rootCause;
	}
	
}
