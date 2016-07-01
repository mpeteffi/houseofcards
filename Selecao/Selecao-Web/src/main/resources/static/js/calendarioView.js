'use strict';
function calendarioView(obj,options) {
    options = options || {};   
    this.objeto = obj;
    this.options = options;
};

calendarioView.prototype.quandoSelecionar = function (comecoData, fimData) {
    var self = this;
    var fim = moment(fimData).format('MMMM Do YYYY, h:mm a');
    var inicio = moment(comecoData).format('MMMM Do YYYY, h:mm a');
    var quandoAcontecer = inicio + ' - ' + fim;
    swal({
      title: 'Novo evento',
      html:$('#modal-criar-novo-evento').clone().show().append($('<span>').text('Quando: '+quandoAcontecer))
              .data('url',{inicio:inicio,fim:fim}),        
      preConfirm: function() {
        return new Promise(function(resolve, reject) {          
            var titulo = 'dasdasd';
            var tipo = 'sadasdasd';
            if (tipo && titulo) {
              resolve({titulo:titulo,tipo:tipo,dataInicio:$('#modal-criar-novo-evento').data('url').inicio,dataFinal:$('#modal-criar-novo-evento').data('url').fim});      
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

calendarioView.prototype.init = function () { 
    this.options.select = this.quandoSelecionar;
    this.objeto.fullCalendar(this.options);
};