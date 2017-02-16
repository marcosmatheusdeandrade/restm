angular.module("listaTelefonica").controller("novoContatoCtrl", function ($scope, contatosAPI, $location, operadoras) {
	$scope.operadoras = operadoras.data;

	$scope.adicionarContato = function (contato) {
		contatosAPI.saveContato(contato).success(function (data) {
			delete $scope.contato;
			$scope.contatoForm.$setPristine();
			$location.path("/contatos");
		});
	};
});