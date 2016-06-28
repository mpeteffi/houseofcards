'use strict';

$(function(){
    
    $('.btnPag').click(function(e){
        var page = e.target.dataset.page;
        if(page !== undefined){
            atualizarPagina(page);
        }
    });
    
    $('#pesq').submit(function(e){
        e.preventDefault();
        var page = e.target.dataset.page;
        if(page !== undefined){
            atualizarPagina(page);
        }
    });
});

function atualizarPagina(page){
    var n = $('#txtNome').val();
    var e = $('#txtEmail').val();
    var t = $('#txtTelefone').val();
    var s = $('#txtStatus').val();
    
    $.ajax({
        url:'/candidatos', 
        type:'GET', 
        data:{status: s, nome: n, email: e, telefone: t, pagina: page}
    }).done(function(res){
        $('#txtNome').append(res);
    });
}