var resp;

var app = angular.module("login", []);

app.config(function ($httpProvider) {
    $httpProvider.interceptors.push(function () {
        return {
            'request': function (config) {
                return config;
            },

            'response': function (response) {
                return response;
            },

            'responseError': function (response) {
                return response;
            }

        };
    });
});


app.controller("controllerLogin", function ($scope, $http, $timeout) {



    var descobreQuemE = function () {
        var informacoesPortal = ccptUrlBase + caminhos.infoPortal;


        $http.get(informacoesPortal, {
            headers: {
                COCKPTI_SESSION_ID_KEY: window.sessionStorage.COCKPTI_SESSION_ID_KEY
            }
        }).then(function (response) {
            if (response.data.administrador) {
                var admin = response.data.administrador;

                if (admin.nivelAcesso == "SUPER_USUARIO" || admin.nivelAcesso == "ADMINISTRADOR") {
                    window.sessionStorage.isAdmin = true;
                    window.location = "indexPortalAdmin.html";

                } else if (admin.nivelAcesso == "COMERCIAL") {
                    window.sessionStorage.isAdmin = false;
                    window.sessionStorage.isResp = true;
                    window.location = "indexPortalAdmin.html";

                } else if (admin.nivelAcesso == "SOMENTE_LEITURA") {
                    window.sessionStorage.isAdmin = false;
                    window.sessionStorage.isResp = false;
                    window.location = "indexPortalAdmin.html";
                };

            } else {
                window.location = "index.html";

            };
        });
    };

    var urlBase = ccptUrlBase + caminhos.login;

    $scope.login = {
        usuario: "",
        senha: ""
    };
    $scope.erroLogin = false;

    $scope.logar = function () {
        $http.post(urlBase, $scope.login).then(function (response) {
            if (response.status >= 400 && response.status < 600) {

                $scope.erroLogin = true;
                $timeout(function () {
                    $scope.erroLogin = false;
                }, 3000);

            } else {

                window.sessionStorage.logado = true;
                window.sessionStorage.COCKPTI_SESSION_ID_KEY = response.headers("COCKPTI_SESSION_ID_KEY");

                descobreQuemE();
            };
        });

    };

});