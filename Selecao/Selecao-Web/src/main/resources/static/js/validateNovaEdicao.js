"use strict";
(function(){
    $.validator.messages.required = "O campo é requirido!";
    $.validator.addMethod(
        "dateBrazil",
        function(value, element) {            
            return value.match(/^\d\d?\/\d\d?\/\d\d\d\d$/);
        },
        "Por favor entre uma data no formato dd/mm/yyyy."
    );
    $("#nova-edicao-form").validate({
        rules : {
            myDate : {
                dateBrazil : true
            }
        }
    });
})();