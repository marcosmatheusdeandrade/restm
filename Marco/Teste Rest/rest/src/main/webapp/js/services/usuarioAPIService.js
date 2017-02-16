angular.module("lista").factory("usuarioAPI", function($http, config){
	
	var _inserir = function(usuario){
		return $http.post(config.baseUrl+"/usuario/inserir?usuario=", usuario);
	};

	var _listar = function(){
		return $http.get(config.baseUrl+"/usuario/listar");
	};
	
	var _deletar = function(usuarios){
		return $http.post(config.baseUrl+"/usuario/deletar", usuarios);
	};
	
	return {
		deletar: _deletar,
		inserir: _inserir,
		listar: _listar
	}
	
});