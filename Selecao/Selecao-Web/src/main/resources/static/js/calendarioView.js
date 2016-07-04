'use strict';
function calendarioView($objeto,opcaoFullCalendar) {
    opcaoFullCalendar = opcaoFullCalendar || {};   
    this.$objeto = $objeto;
    this.opcaoFullCalendar = opcaoFullCalendar;

};

calendarioView.prototype.quandoMouseEmCimaEvento = function (evento) {
    $('.fc-content', this)
            .prepend( 
                    $('<span>')
                        .attr('id',evento.id)
                        .addClass('btn-deleta-evento pull-right')
                        .append(
                            $('<i>').addClass('glyphicon glyphicon-remove')
                            .click(function(){
                            $.delete('/rest/agendamento/delete',{id:evento.id},function(){
                                $('#calendar').fullCalendar('removeEvents',evento.id);
                                });                        
                            })
                        )
                    )
                    
    
};

calendarioView.prototype.quandoMouseSairDeCimaEvento = function (evento) {
    $('#'+evento.id).remove();
};


calendarioView.prototype.quandoMudarEventoHorario = function (evento, delta, revertFunc) {
    var updateEvento =
                {
                    id:evento.id,
                    title:evento.title,
                    end:moment(evento.end).format('DD/MM/YYYY HH:mm'),
                    allDay:!evento.start.hasTime() && !evento.end.hasTime(),
                    start:moment(evento.start).format('DD/MM/YYYY HH:mm')
                };
    $.put('/rest/agendamento/update',updateEvento,function(){
        if(response.status != 'success')
            revertFunc();
    });    
};


calendarioView.prototype.quandoSelecionado = function (comecoData, fimData, jsEvent, view) {
    if (view.name=='month') {
        $('#calendar').fullCalendar('changeView', 'agendaDay');
        $('#calendar').fullCalendar('gotoDate', comecoData)
        return;
    }
    var self = this;
    var fim = fimData.format('DD/MM/YYYY - HH:mm');
    var inicio = comecoData.format('DD/MM/YYYY - HH:mm');
    var rangeTempoEvento = inicio + ' até ' + fim;  
    var $conteudoModal = self.modalConteudoNovoAgendamento(rangeTempoEvento);
    swal({
        title: 'Novo agendamento',
        html:  $conteudoModal,
        showLoaderOnConfirm: true,
        showCancelButton: true,
        preConfirm: function() {
            return new Promise(function(resolve, reject) {            
                var tipo = $conteudoModal.find('.novo-evento-tipo').val();
                var titulo =  $conteudoModal.find('.input-titulo').val();            
                if(tipo === 'Selecione o tipo'){
                    reject('Selecione um tipo de evento!');
                }else if (!titulo) {
                    reject('Campo não pode estar vazio!');  
                }else{
                    if(tipo === 'Entrevista de candidato') titulo = 'Entrevista com '+titulo;
                    var dataInicioFormatada = comecoData.format('DD/MM/YYYY HH:mm');
                    var dataFimFormatada =fimData.format('DD/MM/YYYY HH:mm')
                    var evento ={
                        title: titulo,
                        allDay: !comecoData.hasTime() && !fimData.hasTime(),
                        start: dataInicioFormatada,
                        end: dataFimFormatada
                    };
                    $.post('/rest/agendamento/novo',evento,function(res){                       
                        evento.id = res;
                        evento.start = moment(comecoData).format();
                        evento.end = moment(fimData).format();
                        
                        resolve(evento);
                    });                       
                }
            });
        }
        }).then(function(evento) {
            $('#calendar').fullCalendar('renderEvent',evento,true);
            swal(
              'Sucesso!',
              'Evento criado',
              'success'
            )          
        }).done();
};

calendarioView.prototype.modalConteudoNovoAgendamento = function (rangeDeTempo) { 
    var $modalConteudo = $('#modal-criar-novo-evento').clone().show();
    $modalConteudo.find('.range-tempo-evento').text(rangeDeTempo);
    
    $modalConteudo.find('.novo-evento-tipo').change(function(){
        var valorAtual = $(this).val();        
        var $divGrupoTitulo = $modalConteudo.find('.grupo-titulo');
        var $inputTitulo = $divGrupoTitulo.find('.input-titulo');
        
        if(valorAtual === 'Selecione o tipo'){
            $divGrupoTitulo.hide();
            $inputTitulo.val('');
        }else{           
            var $input = $divGrupoTitulo.find('.label-titulo');
                $input.text(valorAtual === 'Grupo de provas' ?
                        'Nome do grupo:' : 'Candidato:' );
            $divGrupoTitulo.show()
            $inputTitulo.val('');
            if ($inputTitulo.hasClass('autocomplete-field')) {
                $inputTitulo.removeClass('autocomplete-field');
            }
            if(valorAtual === 'Entrevista de candidato'){
                $inputTitulo.tinyAutocomplete({
                    url: '/rest/candidato/nomes',
                    maxItems: 5,
                    itemTemplate:'<li class="autocomplete-item">{{nome}}</li>' ,
                    showNoResults: true,
                    onSelect: function(el, val) { 
                        $(this).val( val.nome );
                        $('.results').html('<h1>' + val.nome + '</h1>');                      
                    }
                });
                $inputTitulo.addClass('autocomplete-field');
            }
        }
    });
    return $modalConteudo;
}

calendarioView.prototype.init = function () {
    this.opcaoFullCalendar.eventDrop = this.quandoMudarEventoHorario;
    this.opcaoFullCalendar.eventResize = this.quandoMudarEventoHorario;
    this.opcaoFullCalendar.eventMouseover = this.quandoMouseEmCimaEvento;
    this.opcaoFullCalendar.eventMouseout = this.quandoMouseSairDeCimaEvento;
    this.opcaoFullCalendar.select = this.quandoSelecionado.bind(this);
    this.$objeto.fullCalendar(this.opcaoFullCalendar);
};