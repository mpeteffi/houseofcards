'use strict';
function mainView(options) {
    options = options || {};
    this.$corpo = options.$corpo;
    this.$html = $('html');
    this.edicaoAtual = options.edicaoAtual;
}
;

mainView.prototype.atualizaView = function (obj) { 
    var objeto = $(obj);
    var self = this;
    var dados = objeto ? self.serializeArrayToObj(objeto.serializeArray()) : {};
    dados.edicao = this.edicaoAtual;
    return $.get(objeto.data('url'),dados, function(res) {
        self.appendCorpo(res);
    });
};

mainView.prototype.postForm = function (obj) { 
    var dados = obj ? this.serializeArrayToObj($(obj).serializeArray()) : {};
    var pagina = $(obj).data('url');    
    return $.post(pagina,dados);
};

//https://api.jquery.com/serializeArray/
mainView.prototype.serializeArrayToObj = function (serializeArray) {
    var objRetorno = {};
    $.each(serializeArray, function (i, item) {
        objRetorno[item.name] = item.value;
    });
    return objRetorno;
};

mainView.prototype.appendCorpo = function (item) {
    this.$corpo.empty().append(item);
};

mainView.prototype.appendEventoNoHtml = function (options) {
    this.$html.on(options.evento, options.obj, options.funcao);
};
