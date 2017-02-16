var app = angular.module("app", ['ngRoute', 'ui.mask']);

app.config(function ($routeProvider) {
    $routeProvider

        .when('/', {
        templateUrl: 'view/diretor.html',
        controller: 'diretor'
    })

    .when('/vendedores', {
        templateUrl: 'view/vendedores.html',
        controller: 'vendedores'
    })

    .when('/editarUserResp', {
        templateUrl: 'view/editarUserResp.html',
        controller: 'editarUserResp'
    });

    /* .when('/metas', {
         templateUrl: 'view/metas.html',
         controller: 'metas'
     });*/
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

app.controller("index", function ($scope, $http) {

    var logout = ccptUrlBase + caminhos.logout;

    $scope.sair = function () {

        $http.get(logout).then(function (response) {
            delete window.sessionStorage.logado;
            window.location = "login.html";
        });
    };


    var InformacoesPortal = ccptUrlBase + caminhos.infoPortal;


    $http.get(InformacoesPortal).then(function (response) {
        $scope.nomeUsuario = response.data.responsavel.nome;
        $scope.nomeRede = response.data.responsavel.redes;
    });

});

//Vendedores
app.controller('vendedores', function ($scope, $http) {

    var urlGerarCodigoGerente = ccptUrlBase + caminhos.gerarCodigoGerente;


    $scope.gerarCodigoGerenteSim = function (gerente) {
        gerente.exibirPainelGerarCodigo = false;
        gerente.gerandoCodigo = true;

        var success = function (response) {
            if (response.status == 400) {
                alert(response.data);
            } else {
                gerente.codigoGerado = response.data;
            }
            gerente.gerandoCodigo = false;
        };


        $http.get(urlGerarCodigoGerente + "?cpf=" + encodeURI(gerente.cpf)).then(success);
    };

    var urlGerarCodigoVendedor = ccptUrlBase + caminhos.gerarCodigoVendedor;

    $scope.gerarCodigoVendedorSim = function (vendedor) {
        vendedor.exibirPainelGerarCodigo = false;
        vendedor.gerandoCodigo = true;

        var success = function (response) {
            if (response.status == 400) {
                alert(response.data);
            } else {
                vendedor.codigoGerado = response.data;
            }
            vendedor.gerandoCodigo = false;
        };

        $http.get(urlGerarCodigoVendedor + "?cpf=" + encodeURI(vendedor.cpf)).then(success);
    };

    $scope.reloadPagina = function () {
        location.reload();
    };

    var urlRemoverAparelhoGerente = ccptUrlBase + caminhos.excluirDispositivoGerente;

    $scope.removerAparelhoGerenteSim = function (gerente, dispositivo) {
        $http.get(urlRemoverAparelhoGerente + "?cpf=" + encodeURI(gerente.cpf) + "&codigoGerado=" + encodeURI(dispositivo.codigoAcesso)).then(function (response) {
            gerente.dispositivosAcesso.splice(gerente.dispositivosAcesso.indexOf(dispositivo), 1);
        });
        $scope.reloadPagina();
    };

    var urlRemoverAparelhoVendedor = ccptUrlBase + caminhos.excluirDispositivoVendedor;

    $scope.removerAparelhoVendedorSim = function (vendedor, dispositivo) {
        $http.get(urlRemoverAparelhoVendedor + "?cpf=" + encodeURI(vendedor.cpf) + "&codigoGerado=" + encodeURI(dispositivo.codigoAcesso)).then(function (response) {
            vendedor.dispositivosAcesso.splice(vendedor.dispositivosAcesso.indexOf(dispositivo), 1);
        });
        $scope.reloadPagina();
    };

    var urlRemoverGerente = ccptUrlBase + caminhos.excluirGerente;
    $scope.removerGerenteSim = function (gerente) {
        $http.get(urlRemoverGerente + "?cpf=" + encodeURI(gerente.cpf)).then(function (response) {
            $scope.gerentes.splice($scope.gerentes.indexOf(gerente), 1);
        });
        $scope.reloadPagina();
    };

    var urlRemoverVendedor = ccptUrlBase + caminhos.excluirVendedor;
    $scope.removerVendedorSim = function (vendedor) {
        $http.get(urlRemoverVendedor + "?cpf=" + encodeURI(vendedor.cpf)).then(function (response) {
            $scope.vendedores.splice($scope.vendedores.indexOf(vendedor), 1);
        });
        $scope.reloadPagina();
    };

    var urlPesquisarGerente = ccptUrlBase + caminhos.pesquisarGerentes;

    $scope.filtroPesquisaGerente = {
        nome: ""
    };
    $scope.pesquisarGerente = function () {
        $http.post(urlPesquisarGerente, $scope.filtroPesquisaGerente).then(function (response) {
            $scope.gerentes = response.data;
        });
    };

    var urlPesquisarVendedor = ccptUrlBase + caminhos.pesquisarVendedores;

    $scope.filtroPesquisaVendedor = {
        nome: ""
    };
    $scope.pesquisarVendedor = function () {
        $http.post(urlPesquisarVendedor, $scope.filtroPesquisaVendedor).then(function (response) {
            $scope.vendedores = response.data;
        });
    };

    var urlGerentes = ccptUrlBase + caminhos.listarGerentes;

    $http.get(urlGerentes).then(function (response) {
        $scope.gerentes = response.data;
    });

    var urlVendedores = ccptUrlBase + caminhos.listarVendedores;

    $http.get(urlVendedores).then(function (response) {
        $scope.vendedores = response.data;

    });

    var InformacoesPortal = ccptUrlBase + caminhos.infoPortal;


    $http.get(InformacoesPortal).then(function (response) {
        $scope.quantidadeCodigosUsados = response.data.responsavel.numeroCodigosUsadosVendedor;
        $scope.quantidadeCodigosAdiquiridos = response.data.responsavel.numeroCodigosAdiquiridosVendedor;


        angular.element('#statusCodigosGerente')
            .progress({
                value: $scope.quantidadeCodigosUsados,
                total: $scope.quantidadeCodigosAdiquiridos,

                text: {
                    active: 'Códigos utilizados: {value} de {total}',
                    success: '{total} - Todos os códigos utilizados!'
                }

            });


        angular.element('#statusCodigosVendedor')
            .progress({
                value: $scope.quantidadeCodigosUsados,
                total: $scope.quantidadeCodigosAdiquiridos,

                text: {
                    active: 'Códigos utilizados: {value} de {total}',
                    success: '{total} - Todos os códigos utilizados!'
                }

            });
    });
});

//Diretor
app.controller('diretor', function ($scope, $http) {

    var urlGerarCodigoDiretor = ccptUrlBase + caminhos.gerarCodigoDiretor;


    $scope.gerarCodigoDiretorSim = function (diretor) {
        diretor.exibirPainelGerarCodigo = false;
        diretor.gerandoCodigo = true;

        var success = function (response) {
            if (response.status == 400) {
                alert(response.data);
            } else {
                diretor.codigoGerado = response.data;
            }
            diretor.gerandoCodigo = false;
        };


        $http.get(urlGerarCodigoDiretor + "?id=" + encodeURI(diretor.id)).then(success);
    };

    $scope.reloadPagina = function () {
        location.reload();
    };

    var urlRemoverAparelhoDiretor = ccptUrlBase + caminhos.excluirDispositivoDiretor;

    $scope.removerAparelhoDiretorSim = function (diretor, dispositivo) {
        $http.get(urlRemoverAparelhoDiretor + "?id=" + encodeURI(diretor.id) + "&codigoGerado=" + encodeURI(dispositivo.codigoAcesso)).then(function (response) {
            diretor.dispositivo.splice(diretor.dispositivo.indexOf(dispositivo), 1);
        });
        $scope.reloadPagina();
    };

    var urlSalvarDiretor = ccptUrlBase + caminhos.salvarDiretor;

    $scope.dadosAseremSalvosDiretor = {
        nome: "",
        cpf: ""

    };

    $scope.salvarDiretor = function () {

        $http.post(urlSalvarDiretor, $scope.dadosAseremSalvosDiretor).then(function (resposta) {

            if (resposta.status >= 400 && resposta.status < 600) {
                alert(resposta.data);
            } else {
                $scope.reloadPagina();
                $scope.diretores = $scope.consultarDiretor(resposta.data);

            };
        });
    };

    var urlConsultarDiretor = ccptUrlBase + caminhos.consultarDiretor;

    $scope.filtroPesquisaDiretor = {
        nome: ""
    };

    $scope.consultarDiretor = function () {
        $http.get(urlConsultarDiretor, $scope.filtroPesquisaDiretor).then(function (resposta) {
            $scope.diretores = resposta.data;
        });
    };

    var urlExcluirDiretor = ccptUrlBase + caminhos.excluirDiretor;

    $scope.excluirDiretor = function (diretor) {
        $http.delete(urlExcluirDiretor + "?id=" + encodeURI(diretor.id)).then(function (response) {
            $scope.diretores.splice($scope.diretores.indexOf(diretor), 1);
        });

        $scope.reloadPagina();
    };

    var InformacoesPortal = ccptUrlBase + caminhos.infoPortal;


    $http.get(InformacoesPortal).then(function (response) {
        $scope.quantidadeCodigosUsadosDiretor = response.data.responsavel.numeroCodigosUsadosDiretor;
        $scope.quantidadeCodigosAdiquiridosDiretor = response.data.responsavel.numeroCodigosAdiquiridosDiretor;


        angular.element('#statusCodigosDiretor')
            .progress({
                value: $scope.quantidadeCodigosUsadosDiretor,
                total: $scope.quantidadeCodigosAdiquiridosDiretor,

                text: {
                    active: 'Códigos utilizados: {value} de {total}',
                    success: '{total} - Todos os códigos utilizados!'
                }

            });

    });


});

app.controller('editarUserResp', function ($scope, $http) {

    $scope.dadosParaAlterarSalvar = {
        nome: "",
        cpf: "",
        login: "",
        senha: "",
        email: "",
        redes: []
    };



    var urlSalvarResponsavel = ccptUrlBase + caminhos.salvarResponsavel;

    $scope.salvarResponsavel = function () {

        $http.post(urlSalvarResponsavel, $scope.dadosParaAlterarSalvar).then(function (resposta) {

            if (resposta.status >= 400 && resposta.status < 600) {
                alert(resposta.data);
            } else {
                location.reload();
                $scope.consultarResponsavel(resposta.data);
                $scope.sair();
            };
        });
    };

    var urlConsultarDadosUsuario = ccptUrlBase + caminhos.infoPortal;

    $scope.consultarResponsavel = function () {
        $http.get(urlConsultarDadosUsuario).then(function (resposta) {

            if (resposta.status >= 400 && resposta.status < 600) {
                alert(resposta.data);
            } else {
                $scope.dadosParaAlterarSalvar = resposta.data.responsavel;

            };
        });
    };
});