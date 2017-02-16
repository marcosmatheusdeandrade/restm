angular.module("listaTelefonica").controller("listaTelefonicaCtrl", function ($scope, contatosAPI) {

	$scope.app = "Lista Telefonica";

	$scope.contatos = [];
	$scope.operadoras = [];
	
	
	$scope.carregarContatos = function (){

		contatosAPI.getContatos().then(function(response){
			if(response.tipoRetorno == 'SUCESSO'){
				$scope.contatos = response.contatos;
			}
		});

	}
	
	$scope.carregarOperadoras = function(){
		contatosAPI.getOperadoras().then(function(response){
			if(response.tipoRetorno == 'SUCESSO'){
				$scope.operadoras = response.operadoras;
			}
		});
	}
	
	$scope.apagarContatos = function (contatos) {
		contatos.forEach(function(contato){
			if(contato.selecionado){
				contatosAPI.deletarContato(contato).then(function(response){
					if(response.tipoRetorno == 'SUCESSO'){
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
	
	$scope.adicionarContato = function (contato) {
		var promisse = contatosAPI.saveContato(contato);
	
		promisse.error(function(data, status, headers, config) {
			alert(status);
		});
		
		promisse.success(function (data) {
			delete $scope.contato;
			$scope.contatoForm.$setPristine();
			$scope.carregarContatos();
//			$location.path("/contatos");
		});
	
	};
	
	$scope.carregarOperadoras();
	$scope.carregarContatos();
});