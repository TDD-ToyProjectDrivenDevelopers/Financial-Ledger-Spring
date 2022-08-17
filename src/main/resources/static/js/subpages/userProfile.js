function changePassword(){
    $("#userUpdateBtn").show();
    $("#cancelBtn").show();
    $("#changePasswordBtn").hide();
    $("#deleteUserBtn").hide();
    $("#pw0").show();
    $("#pw1").show();
    $("#pw2").show();
}

function cancelUpdate(){
    $("#userUpdateBtn").hide();
    $("#cancelBtn").hide();
    $("#changePasswordBtn").show();
    $("#deleteUserBtn").show();
    $("#pw0").hide();
    $("#pw1").hide();
    $("#pw2").hide();
}

function deleteUser(){
    console.log("deleteUser");
    $.ajax({
        url: "/profile/users",
        dataType: "json",
        contentType: "application/json; charset=UTF-8",
        type: "DELETE",
        data:  $("#inputUsername").val(),
        beforeSend: function (jqXHR, settings) {
            //CSRF 해결
            let header = $("meta[name='_csrf_header']").attr("content");
            let token = $("meta[name='_csrf']").attr("content");
            jqXHR.setRequestHeader(header, token);
        },
        done: function (data) {
            console.log(data);
            if(data === "success"){
                alert("회원탈퇴에 성공하였습니다.");
            } else {
                alert("회원탈퇴에 실패하였습니다.");
            }
        },

    });
}

function userUpdate(){
    console.log("userUpdate");

    // [Step1] : userUpdateValidation
    let inputPassword = $("inputPassword").val()
    let inputPasswordCheck = $("inputPasswordCheck").val();

    //새 비밀번호 검증 check 검증  -> Spring Valid로 하도록 변경
    // if (inputPassword != inputPasswordCheck) {
    //
    //     alert("새 비밀번호가 일치 하지 않습니다.");
    //     return;
    // }
    //구현중..
}

