'use strict';
function mainView(options) {
    options = options || {};   
    this.$corpo = options.$corpo;
    this.$html = $('html');
};

mainView.prototype.atualizaView = function (pagina,dados) { 
    var dados = dados || {};  
    var self = this;
    $.get(pagina,dados, function(res) {
        self.appendCorpo(res);
    });      
};

mainView.prototype.postForm = function (pagina,dados) { 
    dados = dados || {};  
    $.post(pagina,dados, function(res) {
        
    });      
};

//https://api.jquery.com/serializeArray/
mainView.prototype.serializeArrayToObj = function (serializeArray) { 
    var objRetorno = {};
    $.each(serializeArray, function(i,item) {
        objRetorno[item.name] = item.value;
    });
    return objRetorno;  
};

mainView.prototype.appendCorpo = function (item) {
    this.$corpo.empty().append(item);
};

mainView.prototype.appendEventoHtml = function (options) {
    this.$html.on(options.evento,options.obj, options.funcao);
};


mainView.prototype.init = function () {
    var self = this;
    this.appendEventoHtml({
            evento:'submit',
            obj:'.form-filtro',
            funcao:function(e){
                $('.input-pagina').val(0);                
                self.atualizaView($(this).data('url'),self.serializeArrayToObj($('.form-filtro').serializeArray()));
                e.preventDefault();
            }
    });
    
    this.appendEventoHtml({
            evento:'click',
            obj:'.btn-paginacao',
            funcao:function(e){
                $('.input-pagina').val($(this).data('page'));                
                self.atualizaView($(this).data('url'),self.serializeArrayToObj($('.form-filtro').serializeArray()));
            }
    });
    
    this.appendEventoHtml({
            evento:'click',
            obj:'.opcao-side-bar',
            funcao:function(){                
                self.atualizaView($(this).data('url'));
            }
    });
    
    this.appendEventoHtml({
            evento:'submit',
            obj:'#nova-edicao-form',
            funcao:function(e){                
                self.postForm($(this).data('url'),self.serializeArrayToObj($(this).serializeArray()));
                e.preventDefault();
            }
    });
};