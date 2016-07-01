'use strict';
function calendarioView(obj,options) {
    options = options || {};   
    this.objeto = obj;
    this.options = options;

};

calendarioView.prototype.quandoSelecionar = function (comecoData, fimData) {
    debugger;
    var self = this;
    var fim = moment(fimData).format('MMMM Do YYYY, h:mm a');
    var inicio = moment(comecoData).format('MMMM Do YYYY, h:mm a');
    var quandoAcontecer = inicio + ' - ' + fim;
    swal({
      title: 'Novo evento',
      html: self.formCriacaoEvento('titulo','tipo'),        
      preConfirm: function() {
        return new Promise(function(resolve, reject) {          
            var titulo = 'dsada';
            var tipo = 'sadasdasd';
            if (tipo && titulo) {
              resolve({titulo:titulo,tipo:tipo});      
            } else {
              reject('Campos não podem estar vazios!');
            }
        });
      }
    }).then(function(resultados) {
        var eventData;
        if (resultados) {
                eventData = {
                        title: resultados.titulo,
                        start:resultados.dataInicio,
                        end:resultados.fim 
                };
                self.objeto.fullCalendar('renderEvent', eventData, true); 
        }
        self.objeto.fullCalendar('unselect');
    }).done();
};

calendarioView.prototype.formCriacaoEvento = function (titulo,tipo) {
    var $modal = $('#modal-criar-novo-evento').clone().show();
    $modal.find('.titulo').attr('id',titulo);
    $modal.find('.tipo').attr('id',tipo);            
    return $modal;
};


calendarioView.prototype.init = function () { 
    debugger;
    this.options.select = this.quandoSelecionar.bind(this);
    this.objeto.fullCalendar(this.options);
};