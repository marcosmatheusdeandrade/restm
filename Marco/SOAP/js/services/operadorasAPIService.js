angular.module("listaTelefonica").service("operadorasAPI", function ($soap, config) {
	this.getOperadoras = function () {
		
		return $soap.post(config.baseUrl+"/OperadoraWS/OperadoraWS",
				"consultarOperadoras");
		
	};
});