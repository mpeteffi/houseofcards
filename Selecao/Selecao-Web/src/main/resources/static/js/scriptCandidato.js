'use strict';

$(function(){
    
    $('html').on('submit', '#pesq', function(e){
        e.preventDefault();
        atualizarPaginaLista(e.target.dataset.page);
    });
    $('html').on('click', '.btnPag', function(e){
        atualizarPaginaLista(e.target.dataset.page);
    });
    
    atualizarPaginaLista(0);
});


function atualizarPaginaLista(page){

    if(page !== undefined){
        
        var n = $('#txtNome').val();
        var e = $('#txtEmail').val();
        var t = $('#txtTelefone').val();
        var s = $('#txtStatus').val();

        $.ajax({
            url:'/candidatos', 
            type:'GET', 
            data:{status: s, nome: n, email: e, telefone: t, page: page}
        }).done(function(res){
            $('.corpo').empty();
            $('.corpo').append(res);
            atribuirFuncoes();
        });
    }
}