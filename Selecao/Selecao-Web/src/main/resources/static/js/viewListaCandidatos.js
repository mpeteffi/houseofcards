'use strict';
function viewCandidatos(options){
    options = options || {};
    this.urlListaCandidatos = options.urlListaCandidatos;
    this.urlListaCandidatos = options.urlListaCandidatos;
    this.formFiltro = options.formFiltro
};


viewCandidatos.prototype.getSerializeForm = function () {
    return $(this.form).serialize();
};

viewCandidatos.prototype.getFormFiltro = function () {
    return this.formPesquisa;
};
