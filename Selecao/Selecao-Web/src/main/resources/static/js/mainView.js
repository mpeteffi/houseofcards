'use strict';
function mainView(options) {
    options = options || {};
    this.urlListaCandidatos = options.urlListaCandidatos ;
    this.urlNovaEdicao = options.UrlNovaEdicao;
    this.btnNovaEdicao = options.btnNovaEdicao;
    this.btnListaCandidato = options.btnListaCandidato;
    this.$corpo = options.$corpo;
    this.$html = $('html');
    this.viewListaCandidatos = options.viewListaCandidatos;
    return this;
};

mainView.prototype.atualizaView = function (pagina,dados) { 
    $.get(pagina,dados, function(res) {
        this.appendCorpo(res);
    });      
};
//https://api.jquery.com/serializeArray/
mainView.prototype.formToObj = function ($formObj) { 
    var objRetorno = {};
    $.each($formObj, function(item) {
        objRetorno[item.name] = item.value;
    });
    return objRetorno;  
};

mainView.prototype.appendCorpo = function (item) {
    this.$corpo.empty().append(item);
};

mainView.prototype.appendFuncaoHtml = function (options) {
    this.$html.on(options.evento,options.obj, options.funcao);
};


mainView.prototype.init = function () {
    
    this.appendFuncaoHtml({
            evento:'submit',
            obj:'#pesq',
            funcao:function(e){
                e.preventDefault();
                this.atualizaView(this.UrlListaCandidatos,this.formToObj(this.viewListaCandidatos.$getForm()));
            }
    });
    //TODO:fazer refactory e terminar
    this.appendFuncaoHtml({
            evento:'click',
            obj:'.btnPag',
            funcao:function(e){
                e.preventDefault();
                this.atualizaView(this.UrlListaCandidatos,'MUDARRRR');
            }
    });
    
};