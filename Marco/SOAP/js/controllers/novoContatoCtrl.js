angular.module("listaTelefonica").controller("novoContatoCtrl", function ($scope, contatosAPI, $location, promiseOperadoras) {

	$scope.operadoras = promiseOperadoras.operadoras;

	$scope.adicionarContato = function (contato) {
		contatosAPI.saveContato(contato).then(function (data) {
			delete $scope.contato;
			$scope.contatoForm.$setPristine();
			$location.path("/contatos");
		});
	};
	
});