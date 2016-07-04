$('#password, #confirm_password').on('keyup', function () {
    if ($('#password').val() == $('#confirm_password').val()) {
        $('#message').html('Ok').css('color', 'green');
    } else 
        $('#message').html('Senhas diferentes').css('color', 'red');
});

$('#formulario-confirmar').submit(function(e){
    if ($('#password').val() == $('#confirm_password').val()){
        $('#message').html('Ok').css('color', 'green');
    }else{
        $('#message').html('Senhas diferentes').css('color', 'red');
        e.preventDefault();
    }
});