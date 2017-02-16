//var ccptUrlBase = "http://localhost:8080/cockpit.vendedor.server.rest/";
var ccptUrlBase = "http://localhost:8080/cockpit.portal.server/portal/";
var caminhos = {

    login: "login",
    logout:"logout",
    infoPortal: "informacoes/portal",

    //Dispositivo
    gerarCodigoGerente: "dispositivo/gerente/gerar",
    gerarCodigoVendedor: "dispositivo/vendedor/gerar",
    gerarCodigoDiretor: "dispositivo/diretor/gerar",

    excluirDispositivoGerente: "dispositivo/gerente/excluir",
    excluirDispositivoVendedor: "dispositivo/vendedor/excluir",
    excluirDispositivoDiretor: "dispositivo/diretor/excluir",

    //Diretor
    
    salvarDiretor:"diretor/salvar",
    consultarDiretor:"diretor/consultar",
    excluirDiretor:"diretor/excluir",
    
    //colaboradores

    pesquisarGerentes: "colaborador/gerente/pesquisar",
    pesquisarVendedores: "colaborador/vendedor/pesquisar",

    listarGerentes: "colaborador/gerente/listar",
    listarVendedores: "colaborador/vendedor/listar",

    excluirGerente: "colaborador/gerente/remover",
    excluirVendedor: "colaborador/vendedor/remover",

    //admin
    salvarAdministrador: "administrador/salvar",
    consultarAdministrador: "administrador/consultar",
    excluirAdministrador: "administrador/excluir",
    //responsavel
    salvarResponsavel: "responsavel/salvar",
    consultarResponsavel: "responsavel/consultar",
    excluirResponsavel: "responsavel/excluir"


};