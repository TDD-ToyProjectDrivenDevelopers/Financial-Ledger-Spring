(function($){
    // 이메일 인증 AJAX
    let accessCode = '';
    $('#email_auth_btn').click(function(){
        const receiver = $('#email').val();

        if(receiver === ''){
            alert('이메일을 입력해주세요');
            return false;
        }

        $.ajax({
            type: 'GET',
            url: '/register/emailSend',
            data: { receiver: receiver },
            contentType :"text/plain;charset=UTF-8",
            success: function(data){
                alert('인증번호가 발송되었습니다.');
                $('#authKey').attr('disabled', false);
                accessCode = data;
            },
            error: function(request, status, error){
                alert('메일 발송에 실패하였습니다.');
            }
        });
    });

    // 이메일 validation - 실제 검증
    $('#authKey').on('keypress keydown keyup',function(){
        if ($('#authKey').val() !== accessCode) {
            $('#authKey').css("border", "3px solid red");
            $('#password').attr('disabled', true);
            $('#repeatPassword').attr('disabled', true);
            $('#pw-inquiry-submit-btn').attr('disabled', true);
        } else{
            $('#authKey').css("border", "3px solid lime");
            $('#password').attr('disabled', false);
            $('#repeatPassword').attr('disabled', false);
            $('#pw-inquiry-submit-btn').attr('disabled', false);
        }
    });
    $('#pwInquiryForm').submit(function(){
        if($('#authKey').val() !== accessCode){
            alert('일증번호 불일치');
            return false;
        } else {
            return true;
        }
    });
})(jQuery);