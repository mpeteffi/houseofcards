"use strict";
(function(){ 
    
    var view = new mainView({$corpo:$('.corpo')});
  
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
})();