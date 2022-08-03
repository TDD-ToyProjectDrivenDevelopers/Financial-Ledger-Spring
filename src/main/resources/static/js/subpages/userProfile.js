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
