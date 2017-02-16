angular.module("listaTelefonica").controller("listaTelefonicaCtrl", function ($scope, contatosAPI) {

	$scope.app = "Lista Telefonica";

	var carregarContatos = function (){

		contatosAPI.getContatos().then(function(response){
			if(response.tipoRetorno == "SUCESSO"){
				$scope.contatos = response.contatos;
			}
		});

	}
	
	$scope.apagarContatos = function (contatos) {
		contatos.forEach(function(contato){
			if(contato.selecionado){
				contatosAPI.deletarContato(contato).then(function(response){
					if(response.tipoRetorno == "SUCESSO"){
						carregarContatos();
					}
				});
			}
		});
	};

	$scope.isContatoSelecionado = function (contatos) {
		if (!contatos)
			return false;
		return contatos.some(function (contato) {
			return contato.selecionado;
		});
	};

	$scope.ordenarPor = function (campo) {
		$scope.criterioDeOrdenacao = campo;
		$scope.direcaoDaOrdenacao = !$scope.direcaoDaOrdenacao;
	};
	
	carregarContatos();
});