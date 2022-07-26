(function($){
    //실시간 값 검증 - 실제 검증은 spring validation 에서 이루어짐
    $(document).ready(function(){
        $('#username').on('keypress keydown keyup',function(){
            const $regexname = /^[A-Za-z0-9]{6,18}$/;
            if (!$(this).val().match($regexname)) {
                $('#username').css("border", "3px solid red");
            } else{
                // $('#username').attr("class", "correct");
                $('#username').css("border", "3px solid lime");
            }
        });
        $('#password').on('keypress keydown keyup',function(){
            const $regexname = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
            if (!$(this).val().match($regexname)) {
                $('#password').css("border", "3px solid red");
            } else{
                $('#password').css("border", "3px solid lime");
            }
        });
        $('#repeatPassword').on('keypress keydown keyup',function(){
            const password = document.getElementById("password");
            const repeatPassword = document.getElementById("repeatPassword");
            if ($('#password').val() !== $('#repeatPassword').val()) {
                $('#repeatPassword').css("border", "3px solid red");
            } else{
                $('#repeatPassword').css("border", "3px solid lime");
            }
        });
        $('#email').on('keypress keydown keyup',function(){
            const $regexname = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
            if (!$(this).val().match($regexname)) {
                $('#email').css("border", "3px solid red");
            } else{
                $('#email').css("border", "3px solid lime");
            }
        });
    });
})(jQuery);