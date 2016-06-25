'use strict';

$(function(){

    // Reorganizar divs na tela
    $(".waterfall").waterfall();
  
    //buscar novas publicações;
    appendPosts(0);
    
    $('.viewMore').click(function(e){
        var nextPage = parseInt(e.target.dataset.page);
        appendPosts(nextPage);
    });
  
});

function appendPosts(pag){
    $.ajax({ url: '/pubs', type: 'get', data: {pagina: pag}})
    .done(function(res){
        if(res === ''){
            removerBotao();
        } else {
            $('.waterfall').append(res);
            $('.waterfall').waterfall();
            atribuirFuncaoLike();
            incrementarBotao();
        }});
}

function incrementarBotao(){
    var novoValor = parseInt($('.viewMoreButton').attr('data-page')) + 1;
    $('.viewMoreButton').attr('data-page', novoValor);
}

function removerBotao(){
    $('.viewMoreButton')
        .text('Não existem mais publicações para ver')
        .removeClass('.viewMore')
        .addClass('disabled')
        .attr('data-page', null);    
}

function atribuirFuncaoLike(){
    $('.like').click(function(e){
        var idPublicacao = parseInt(e.target.dataset.pub);
        likePost(idPublicacao);
    });
}

function likePost(idPub){
    $.ajax({url:'/like', type: 'get', data: {id : idPub}})
        .done(function(res){
            var query = 'span[data-pub=' + idPub + ']';
            if (res) {
                var novoValor = parseInt($(query).text()) + 1;
                $(query).text(novoValor);
            } else {
                $('.likeFail').remove();
                $(query).parent('div').append(
                        $('<p>').text('Você só pode curtir uma vez.')
                        .addClass('likeFail'));
            }
        });
}