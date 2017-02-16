angular.module("listaTelefonica").controller("detalhesContatoCtrl", function ($scope, $routeParams, promiseConato) {

	$scope.contato = promiseConato.contato;

});