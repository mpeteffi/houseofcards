'use strict';
function calendarioView($objeto,opcaoFullCalendar) {
    opcaoFullCalendar = opcaoFullCalendar || {};   
    this.$objeto = $objeto;
    this.opcaoFullCalendar = opcaoFullCalendar;

};

calendarioView.prototype.quandoMouseEmCimaEvento = function (evento) {
    if(evento.title.startsWith('Entrevista'))return;
    $('.fc-content', this)
            .prepend( 
                    $('<span>')
                        .attr('id',evento.id)
                        .addClass('btn-deleta-evento pull-right')
                        .append(
                            $('<i>').addClass('glyphicon glyphicon-remove')
                            .click(function(){
                            $.post('/rest/agendamento/grupo-prova/delete',{id:evento.id},function(){
                                $('#calendar').fullCalendar('removeEvents',evento.id);
                                }).fail(function(res) {
                                    swal('Erro!',res.responseText,'error');
                                });                        
                            })
                        )
                    );
                    
    
};

calendarioView.prototype.quandoMouseSairDeCimaEvento = function (evento) {
    if(evento.title.startsWith('Entrevista'))return;
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
    $.post('/rest/agendamento/update',updateEvento).fail(function() {
        swal('Erro!','Não foi possível fazer a mudança','error');
        revertFunc();
    });     
};


calendarioView.prototype.quandoSelecionado = function (comecoData, fimData, jsEvent, view) {
    if (view.name=='month') {
        $('#calendar').fullCalendar('changeView', 'agendaDay');
        $('#calendar').fullCalendar('gotoDate', comecoData);
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
                var $input = $conteudoModal.find('.input-titulo');
                var tipo = $conteudoModal.find('.novo-evento-tipo').val();
                var titulo =  $input.val();            
                if(tipo === 'Selecione o tipo'){
                    reject('Selecione um tipo de evento!');
                }else if (!titulo) {
                    reject('Campo não pode estar vazio!');  
                }else{
                    var ehEntrevista =(tipo === 'ENTREVISTA_RH');
                    titulo = (ehEntrevista ?'Entrevista com ':'Grupo: ')+titulo;
                    var dataInicioFormatada = comecoData.format('DD/MM/YYYY HH:mm');
                    var dataFimFormatada =fimData.format('DD/MM/YYYY HH:mm')
                    var evento = {
                        title: titulo,
                        allDay: !comecoData.hasTime() && !fimData.hasTime(),
                        start: dataInicioFormatada,
                        end: dataFimFormatada
                    };
                    if(ehEntrevista) evento.idCandidato = $input.data('id-candidato');
                    $.post('/rest/agendamento/novo',evento,function(res){                       
                        evento.id = res;
                        evento.start = moment(comecoData).format();
                        evento.end = moment(fimData).format();                         
                        resolve(evento);
                    }).fail(function(res) {
                        reject('Erro: '+res.responseText);  
                    });                     
                }
            });
        }
        }).then(function(evento) {
            $('#calendar').fullCalendar('renderEvent',evento,true);
            swal('Sucesso!','Evento criado','success');          
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
                $input.text(valorAtual === 'GRUPO_PROVA' ?
                        'Nome do grupo:' : 'Candidato:' );
            $divGrupoTitulo.show()
            $inputTitulo.val('');
            
            if(valorAtual === 'ENTREVISTA_RH'){
                $inputTitulo.tinyAutocomplete({
                    url: '/rest/candidato/nomes',
                    minChars:3,
                    maxItems: 5,
                    itemTemplate:'<li class="autocomplete-item">{{nome}}</li>' ,
                    showNoResults: true,
                    onSelect: function(el, val) { 
                        $(this).val( val.nome ).data('id-candidato',val.id);             
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