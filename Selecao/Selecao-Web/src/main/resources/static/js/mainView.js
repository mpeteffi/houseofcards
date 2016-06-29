'use strict';
function mainView(options) {
    options = options || {};   
    this.$corpo = options.$corpo;
    this.$html = $('html');
};

mainView.prototype.atualizaView = function (pagina,obj) { 
    var self = this;
    var dados = obj ? self.serializeArrayToObj(obj.serializeArray()) : {};
    $.get(pagina,dados, function(res) {
        self.appendCorpo(res);
    });      
};

mainView.prototype.postForm = function (pagina) { 
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

mainView.prototype.appendEventoNoHtml = function (options) {
    this.$html.on(options.evento,options.obj, options.funcao);
};
