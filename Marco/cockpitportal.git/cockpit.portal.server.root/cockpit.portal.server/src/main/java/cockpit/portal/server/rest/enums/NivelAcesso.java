package cockpit.portal.server.rest.enums;

public enum NivelAcesso {

	SUPER_USUARIO, //Pode cadastrar ADMINISTRADOR, COMERCIAL e SOMENTE_LEITURA  
	ADMINISTRADOR, //Pode cadastrar COMERCIAL e SOMENTE_LEITURA
	COMERCIAL,// Pode cadastras responsáveis
	SOMENTE_LEITURA, //Pode somente visualizar os responsáveis 
	
}
