"use strict";
(function(){ 
    
    var view = new mainView({$corpo:$('.corpo'),edicaoAtual:$('#edicao').text()});
  
    view.appendEventoNoHtml({
            evento:'click',
            obj:'#menu-toggle',
            funcao:function(){
                $("#wrapper").toggleClass("toggled");
            }
    });
    
    
    view.appendEventoNoHtml({
            evento:'submit',
            obj:'.form-filtro',
            funcao:function(e){
                $('.input-pagina').val(0);                
                view.atualizaView($(this).data('url'),$(this));
                e.preventDefault();
            }
    });
    
    view.appendEventoNoHtml({
            evento:'click',
            obj:'.btn-paginacao',
            funcao:function(){
                $('.input-pagina').val($(this).data('page'));                
                view.atualizaView($(this).data('url'),$('.form-filtro'));
            }
    });
    
    view.appendEventoNoHtml({
            evento:'click',
            obj:'.opcao-side-bar',
            funcao:function(){                
                view.atualizaView($(this).data('url'));
            }
    });
    
    view.appendEventoNoHtml({
            evento:'submit',
            obj:'#nova-edicao-form',
            funcao:function(e){                
                mainView.postForm($(this).data('url'),$(this));
                e.preventDefault();
            }
    });
    
    view.appendEventoNoHtml({
            evento:'click',
            obj:'.entrevistas-candidatos',
            funcao:function(){
                view.atualizaView($(this).data('url'));
            }
    });
    
    view.appendEventoNoHtml({
            evento:'click',
            obj:'.btn-novo-candidato',
            funcao:function(){
                view.atualizaView($(this).data('url'));
            }
    });
    
    view.appendEventoNoHtml({
        evento: 'click',
        obj: '.btnLogout',
        funcao: function(){swal({
            title: 'Logout solicitado.',
            text: "Tem certeza que deseja sair?",
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            cancelButtonColor: '#3085d6',
            cancelButtonText: 'Cancelar',
            confirmButtonText: 'Sair!'
            }).then(function() {
                window.location.href = "/logout";
            }).done();
        }
    });
    
    view.appendEventoNoHtml({
        evento: 'click',
        obj: '.btn-new-processo',
        funcao: function(res){
            if(!res){
                
            } else {
                swal({
                title: 'Você não pode fazer isso.',
                text: "Já existe um processo seletivo em andamento.",
                type: 'error',
                showCancelButton: false,
                confirmButtonColor: '#3085d6',
                confirmButtonText: 'Entendi'
                }).then().done();
            }
        }
    });
})();