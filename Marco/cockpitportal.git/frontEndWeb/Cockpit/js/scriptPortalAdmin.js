var app = angular.module("appAdmin", ['ngRoute', 'ui.mask']);

app.config(function ($routeProvider) {
    $routeProvider

        .when('/', {
        templateUrl: 'view/home.html',
        controller: 'home'
    })

    .when('/admin', {
        templateUrl: 'view/usuarioAdmin.html',
        controller: 'admin'
    })

    .when('/responsavel', {
        templateUrl: 'view/usuarioResponsavel.html',
        controller: 'responsavel'
    })

    .when('/leitura', {
        templateUrl: 'view/usuarioSomenteLeitura.html',
        controller: 'leitura'
    });

});

app.config(function ($httpProvider) {
    $httpProvider.interceptors.push(function () {
        return {
            'request': function (config) {
                config.headers.COCKPTI_SESSION_ID_KEY = window.sessionStorage.COCKPTI_SESSION_ID_KEY;
                return config;
            },

            'response': function (response) {
                return response;
            },

            'responseError': function (response) {

                if (response.status == 401) {
                    window.location = "login.html";
                }

                return response;
            }

        };
    });
});


app.controller("ctrlAdmin", function ($scope, $http) {

    $scope.isAdmin = window.sessionStorage.isAdmin == "true";

    $scope.isResp = window.sessionStorage.isResp == "true";

    var logout = ccptUrlBase + caminhos.logout;

    $scope.sair = function () {

        $http.get(logout).then(function (response) {
            delete window.sessionStorage.logado;
            delete window.sessionStorage.isAdmin;
            delete window.sessionStorage.isResp;
            window.location = "login.html";
        });
    };
    
    $scope.reloadPagina = function(){
      location.reload();    
    };

});
app.controller("home", function ($scope, $http) {

    var InformacoesPortal = ccptUrlBase + caminhos.infoPortal;
    
    $http.get(InformacoesPortal).then(function (response) {
        $scope.nomeUsuario = response.data.administrador.nome;
    });

    $scope.mostrarDivAlterar = function () {
        $scope.abrir = true;
    };
    $scope.mostrarDivAlterarCancelar = function () {
        $scope.abrir = false;
    };

    $scope.dadosParaAlterarSalvar = {
        nome: "",
        email: "",
        login: "",
        senha: ""
    };


    var urlSalvarAdministrador = ccptUrlBase + caminhos.salvarAdministrador;

    $scope.salvarAdmin = function () {

        $http.post(urlSalvarAdministrador, $scope.dadosParaAlterarSalvar).then(function (resposta) {

            if (resposta.status >= 400 && resposta.status < 600) {
                alert(resposta.data);
            } else {
                location.reload();
                $scope.dadosParaAlterarSalvar = $scope.consultarAdmin(resposta.data);
                $scope.sair();
            };
        });
    };

    var urlConsultarDadosUsuario = ccptUrlBase + caminhos.infoPortal;

    $scope.consultarAdmin = function () {
        $http.get(urlConsultarDadosUsuario).then(function (resposta) {

            if (resposta.status >= 400 && resposta.status < 600) {
                alert(resposta.data);
            } else {
                $scope.dadosParaAlterarSalvar = resposta.data.administrador;

            };
        });
    };

});


app.controller("admin", function ($scope, $http) {

    $scope.niveisDeAcesso = {
        niveis: [
            {
                nome: 'SUPER_USUARIO'
            },
            {
                nome: 'ADMINISTRADOR'
            },
            {
                nome: 'COMERCIAL'
            },
            {
                nome: 'SOMENTE_LEITURA'
            }
    ]
    };

    var urlSalvarAdministrador = ccptUrlBase + caminhos.salvarAdministrador;

    $scope.dadosAseremSalvosAdmin = {
        nome: "",
        login: "",
        senha: "",
        email: "",
        nivelAcesso: ""
    };

    $scope.salvarAdmin = function () {

        $http.post(urlSalvarAdministrador, $scope.dadosAseremSalvosAdmin).then(function (resposta) {

            if (resposta.status >= 400 && resposta.status < 600) {
                alert(resposta.data);
            } else {
                location.reload();
                $scope.administradores = $scope.consultarAdmin(resposta.data);

            };
        });
    };

    var urlConsultarDadosAdmin = ccptUrlBase + caminhos.consultarAdministrador;

    $scope.filtroPesquisaAdmin = {
        nome: ""
    };

    $scope.consultarAdmin = function () {
        $http.get(urlConsultarDadosAdmin, $scope.filtroPesquisaAdmin).then(function (resposta) {

            if (resposta.status >= 400 && resposta.status < 600) {
                alert(resposta.data);
            } else {
                $scope.administradores = resposta.data;

            };
        });
    };

    $scope.orderList = "nome";

    $scope.editarAdmin = function (administrador) {
        $scope.dadosAseremSalvosAdmin = angular.copy(administrador);
    };

    var urlExcluirAdmin = ccptUrlBase + caminhos.excluirAdministrador;

    $scope.excluirAdmin = function (administrador) {
        $http.delete(urlExcluirAdmin + "?id=" + encodeURI(administrador.id)).then(function (response) {

            if (response.status >= 400 && response.status < 600) {
                alert(response.data);
            } else {
                $scope.administradores.splice($scope.administradores.indexOf(administrador), 1);

            };
        });
    };

});

app.controller("responsavel", function ($scope, $http) {
    var urlSalvarResponsavel = ccptUrlBase + caminhos.salvarResponsavel;

    $scope.dadosAseremSalvosResp = {

        nome: "",
        cpf: "",
        login: "",
        senha: "",
        email: "",
        numeroCodigosAdiquiridosDiretor: "",
        numeroCodigosAdiquiridosVendedor: "",
        redes: []


    };

    $scope.addRedeLista = function () {
        $scope.dadosAseremSalvosResp.redes.push({});
    };

    $scope.salvarResposavel = function () {

        $http.post(urlSalvarResponsavel, $scope.dadosAseremSalvosResp).then(function (resposta) {

            if (resposta.status >= 400 && resposta.status < 600) {
                alert(resposta.data);
            } else {
                location.reload();
                $scope.responsaveis = $scope.consultarResp(resposta.data);

            };

        });
    };

    var urlConsultarDadosResp = ccptUrlBase + caminhos.consultarResponsavel;

    $scope.filtroPesquisaResp = {
        nome: ""
    };

    $scope.consultarResp = function () {
        $http.get(urlConsultarDadosResp, $scope.filtroPesquisaResp).then(function (resposta) {

            if (resposta.status >= 400 && resposta.status < 600) {
                alert(resposta.data);
            } else {
                $scope.responsaveis = resposta.data;
            };
        });
    };

    $scope.orderList = "nome";

    $scope.editarResponsavel = function (responsavel) {
        $scope.dadosAseremSalvosResp = angular.copy(responsavel);
    };

    var urlExcluirResp = ccptUrlBase + caminhos.excluirResponsavel;

    $scope.excluirResp = function (responsavel) {
        $http.delete(urlExcluirResp + "?id=" + encodeURI(responsavel.id)).then(function (response) {

            if (response.status >= 400 && response.status < 600) {
                alert(response.data);
            } else {
                $scope.responsaveis.splice($scope.responsaveis.indexOf(responsavel), 1);
            };
        });
    };

    $scope.excluirRede = function (rede) {;
        $scope.dadosAseremSalvosResp.redes.splice($scope.dadosAseremSalvosResp.redes.indexOf(rede), 1);
    };

});

app.controller("leitura", function ($scope, $http) {

    var urlConsultarDadosRedeResp = ccptUrlBase + caminhos.consultarResponsavel;

    $scope.filtroPesquisaResponsavel = {
        nome: ""
    };

    $scope.consultarRedesResp = function () {
        $http.get(urlConsultarDadosRedeResp, $scope.filtroPesquisaResponsavel).then(function (resposta) {


            if (resposta.status >= 400 && resposta.status < 600) {
                alert(resposta.data);
            } else {
                $scope.responsaveis = resposta.data;
            };


        });
    };

    $scope.orderList = "nome";
    $scope.orderListRede = "descricao";
});