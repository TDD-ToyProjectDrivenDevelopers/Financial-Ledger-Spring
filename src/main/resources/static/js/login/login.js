

function validateform(){
    let username=document.form.username.value;
    let password=document.form.password.value;

    if (username==null || username == ""){
        alert("아이디를 입력하세요.");
        return false;
    } else if(password==null || password == ""){
        alert("비밀번호를 입력하세요.");
        return false;
    }
}