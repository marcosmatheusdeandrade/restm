angular.module("lista").controller("usuarioCtrl", function($scope, usuarioAPI){

	$scope.usuarios = [];

	$scope.adicionarUsuario = function(usuario){
		usuarioAPI.inserir(usuario).success(function(data){
			$scope.listarUsuarios();
		});
		
	};
	
	$scope.listarUsuarios = function(){
		usuarioAPI.listar().success(function(data){
			$scope.usuarios = data;
		});
	};
	
	$scope.isUsuarioSelecionados = function(){
		return $scope.usuarios.some(function(usuario){
			return usuario.selecionado;
		});
	};
	
	$scope.ordenarPor = function(campo){
	    $scope.campoOrdenacao = campo;
		$scope.OrdenacaoReversa = !$scope.OrdenacaoReversa;
	};
	
	$scope.apagarUsuarios = function(){
		usuariosASeremRemovidos = $scope.usuarios.filter(function(usuario){
			if(usuario.selecionado) {
				usuario.selecionado = undefined;
				return usuario;
			}
				
		});
		
		
		usuarioAPI.deletar(usuariosASeremRemovidos).success(function(){
			$scope.listarUsuarios();
		});
	};
	
	$scope.listarUsuarios();
	
});