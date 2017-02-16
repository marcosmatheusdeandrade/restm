angular.module("listaTelefonica").factory("contatosAPI", function ($soap, config) {
	var _getContatos = function () {
		
		var consultarContatosPorNomeTO = {"consultarContatosPorNomeTO":{"nome": ""}};
		
		return $soap.post(config.baseUrl+"/ContatoWS/ContatoWS",
				"consultarContatosPorNome", consultarContatosPorNomeTO);
		
	};

	var _getContato = function (id) {
		
		var consultarContatoPorIdTO = {"consultarContatoPorIdTO":{"id": id}};
		
		return $soap.post(config.baseUrl+"/ContatoWS/ContatoWS",
				"consultarContatoPorId", consultarContatoPorIdTO);
		
	};

	var _saveContato = function (contato) {
		
		var salvarContatoTO = {"salvarContatoTO":{"contato": contato}};
		
		return $soap.post(config.baseUrl+"/ContatoWS/ContatoWS",
				"salvarContato", salvarContatoTO);
		
	};

	var _deletarContato = function (contato) {
		
		var deletarContatoTO = {"deletarContatoTO":{"id": contato.id}};
		
		return $soap.post(config.baseUrl+"/ContatoWS/ContatoWS",
				"deletarContato", deletarContatoTO);
		
	};

	return {
		getContatos: _getContatos,
		getContato: _getContato,
		saveContato: _saveContato,
		deletarContato: _deletarContato
	};
});