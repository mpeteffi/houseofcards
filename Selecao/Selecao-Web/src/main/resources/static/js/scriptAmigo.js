'use sctrict';

$(function(){
    
    $('.switch').click(function(e){
        var tipo = e.target.parentElement.dataset.tipo;
        alterarUrl(tipo);
    });
    
    if($('.param').text() === "solicita"){
        alterarUrl("solicitacoes");
    } else {
        carregarAmigos("");
    }
});

function alterarUrl(tipo, filtro){
    $('.switch').removeClass('active');
    var query = 'li[data-tipo=' + tipo + ']';
    $(query).addClass('active');
    $('.conteudoAmigo').empty();
    
    switch(tipo) {
    case "amigos":
        carregarAmigos(filtro || "");
        break;
    case "solicitacoes":
        carregarSolicitacoes();
        break;
    case "outros":
        carregarOutros(filtro || "");
        break;
    }
}

function adicionarFuncaoPesquisa(){
    $('#pesq').submit(function(res){
        console.log('aqui');
        console.log(res.target.dataset.func);
        var formData = new FormData($('#pesq')[0]);
        console.log(formData.get('imagem'));
        alterarUrl(res.target.dataset.func, formData.get('imagem'));
        res.preventDefault();
    });
}

function adicionarFuncaoAdd(){
    $('.adicionar').click(function(e){
        var tipo = parseInt(e.target.dataset.perfil);
        solicitarPerfil(tipo);
    });
}

function adicionarFuncaoAcc(){
    $('.aceitar').click(function(e){
        var tipo = parseInt(e.target.dataset.perfil);
        console.log('add' + tipo);
        adicionarPerfil(tipo);
    });
    $('.rejeitar').click(function(e){
        var tipo = parseInt(e.target.dataset.rejeitar);
        rejeitarPerfil(tipo);
    });
}

function solicitarPerfil(perfil){
    $.ajax({url:'/solicitar', type: 'get', data: {id : perfil}})
        .done(function(res){
            var query = 'button[data-perfil=' + perfil + ']';
            if (res){
                $(query).text('Solicitação Enviada').addClass('disabled');
            } else {
                $('.erroSolicitar').remove();
                $(query).parent('li').append($('<p>').text('Você só pode enviar uma solicitação.').addClass('erroSolicitar'));
            }});
}

function adicionarPerfil(perfil){
    $.ajax({url:'/relacionar', type: 'get', data: {id : perfil}})
        .done(function(res){
            var query = 'button[data-perfil=' + perfil + ']';
            var queryOutro = 'button[data-rejeitar=' + perfil + ']';
            if (res){
                $(queryOutro).remove();
                $(query).text('Convite Aceito!').addClass('disabled');
            }});
}

function rejeitarPerfil(perfil){
    $.ajax({url:'/rejeitar', type: 'get', data: {id : perfil}})
        .done(function(){
            var query = 'button[data-rejeitar=' + perfil + ']';
            var queryOutro = 'button[data-perfil=' + perfil + ']';
            $(queryOutro).remove();
            $(query).text('Convite rejeitado!').addClass('disabled');
        });
}

function carregarAmigos(pesquisa){
    $.ajax({ url: '/amigos', type: 'POST', data: {filtro: pesquisa}})
    .done(function(res){
        $('.conteudoAmigo').append(res);
        adicionarFuncaoPesquisa("amigos");
        $('#txtPesq').focus();
    });
}

function carregarSolicitacoes(){
    $.ajax({ url: '/solicitacoes', type: 'POST'})
    .done(function(res){
        $('.conteudoAmigo').append(res);
        adicionarFuncaoAcc();
    });
}

function carregarOutros(pesquisa){
    $.ajax({ url: '/outros', type: 'POST', data: {filtro: pesquisa}})
    .done(function(res){
        $('.conteudoAmigo').append(res);
        adicionarFuncaoAdd();
        adicionarFuncaoPesquisa("outros");
        $('#txtPesq').focus();
    });
}