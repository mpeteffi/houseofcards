"use strict";
(function () {

    var view = new mainView({$corpo: $('.corpo'), edicaoAtual: $('#edicao').text()});

    view.appendEventoNoHtml({
        evento: 'click',
        obj: '#menu-toggle',
        funcao: function () {
            $("#wrapper").toggleClass("toggled");
        }
    });

    view.appendEventoNoHtml({
        evento: 'submit',
        obj: '.form-filtro',
        funcao: function (e) {
            $('.input-pagina').val(0);
            view.atualizaView(this);
            e.preventDefault();
        }
    });

    view.appendEventoNoHtml({
        evento: 'click',
        obj: '.btn-paginacao',
        funcao: function () {
            $('.input-pagina').val($(this).data('page'));
            view.atualizaView('.form-filtro');
        }
    });

    view.appendEventoNoHtml({
        evento: 'click',
        obj: '.opcao-side-bar',
        funcao: function () {
            view.atualizaView(this);
        }
    });

    view.appendEventoNoHtml({
        evento: 'click',
        obj: '.opcao-entrevistados',
        funcao: function () {
            view.atualizaView(this);
        }
    });

    view.appendEventoNoHtml({
        evento: 'submit',
        obj: '#nova-edicao-form',
        funcao: function (e) {
            view.postForm(this);
            e.preventDefault();
        }
    });
//    view.appendEventoNoHtml({
//        evento: 'click',
//        obj: '.editar-candidato',
//        funcao: function () {
//            view.postForm(this);
//            e.preventDefault();
//        }
//    });

    view.appendEventoNoHtml({
        evento: 'click',
        obj: '.btn-nova-entrevista',
        funcao: function () {
            view.atualizaView(this);
        }
    });

//    view.appendEventoNoHtml({
//        evento: 'submit',
//        obj: '#edicao-candidato',
//        funcao: function (e) {
//            view.postFormJSON(this);
//            e.preventDefault();
//        }
//    });


    view.appendEventoNoHtml({
        evento: 'click',
        obj: '.btn-novo-agendamento',
        funcao: function () {
            view.atualizaView(this).always(function () {
                new calendarioView($('#calendar'), {
                    lang: 'pt-br',
                    selectable: true,
                    selectHelper: true,
                    editable: true,
                    hiddenDays: [6, 0],
                    header:
                            {
                                left: 'prev,next today',
                                center: 'title',
                                right: 'month,agendaWeek,agendaDay'
                            },
                    eventLimit: true

                }).init();
            });
        }
    });



    view.appendEventoNoHtml({
        evento: 'click',
        obj: '.btnLogout',
        funcao: function () {
            swal({
                title: 'Logout solicitado.',
                text: "Tem certeza que deseja sair?",
                type: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#d33',
                cancelButtonColor: '#3085d6',
                cancelButtonText: 'Cancelar',
                confirmButtonText: 'Sair!'
            }).then(function () {
                window.location.href = "/logout";
            }).done();
        }
    });

    view.appendEventoNoHtml({
        evento: 'click',
        obj: '.btn-new-processo',
        funcao: function () {
            var self = this;

            $.get('/verificarProcessoSeletivo').done(function (res) {
                if (!res) {
                    view.atualizaView(self);
                } else {
                    swal({
                        title: 'Você não pode fazer isso.',
                        text: "Já existe um processo seletivo em andamento.",
                        type: 'error',
                        showCancelButton: false,
                        confirmButtonColor: '#3085d6',
                        confirmButtonText: 'Entendi'
                    }).then().done();
                }
            });
        }
    });
})();

$(function () {
    $('.corpo').on('submit', '#edicao-candidato', function (form) {
        var dados = form.target;
        debugger;
        $.ajax({
            url: '/editar-candidato-teste',
            type: 'POST',
            data: dados
        })
        form.preventDefault();
    });
});