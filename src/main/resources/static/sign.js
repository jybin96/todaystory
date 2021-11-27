let pwVerify = false;

function signupSubmit() {

    var idReg = /^[A-za-z0-9]{3}/g;
    let userId = $("#user_id").val();
    let userPw = $("#user_pw").val();
    let userPw2 = $("#user_pw2").val();
    let userEmail = $("#user_email").val()
    console.log(userPw2);
    if(!idReg.test(userId)){
        alert("아이디 형식을 맞춰주세요!");
        return;
    }

    if(userPw.length < 4 || userPw.trim()=="" || userPw == userId){
        alert("비밀번호 형식을 맞춰주세요!");
        return;
    }

    if(pwVerify == false){
        alert("비밀번호 확인을 해주세요!");
        return;
    }

    let userData = {
        username:userId,
        password:userPw,
        password2:userPw2,
        email : userEmail
    }

    $.ajax({
        type: "POST",
        url: "/user/signup",
        data:JSON.stringify(userData),
        contentType:"application/json",
        success:function (response) {
            console.log(response);
            if(response == "idDuplicate"){
                $("#id_verify").text("이미 존재하는 아이디 입니다.")
                $("#id_verify").css('color','#f82a2aa3')
                return;
            }else if(response == "idValid"){
                alert("아이디 양식을 강제로 잘못 참조하였습니다.")
                return;
            }else if(response == "pwValid"){
                alert("비밀번호 양식을 강제로 잘못 참조하였습니다.")
                return;
            }else if(response == "success"){
                alert("회원가입에 성공하셨습니다.")
                window.location.href = "/user/login";
            }
        }
    })
}

$(function() {
    $("#user_pw2").keyup(function (){
        if ($("#user_pw").val() != $("#user_pw2").val()){
            $("#verify").text("비밀번호가 일치하지 않습니다.")
            $("#verify").css('color','#f82a2aa3')
            pwVerify = false;
        }else{
            $("#verify").text("비밀번호가 일치합니다.")
            $("#verify").css('color','#199894b3')
            pwVerify = true;
        }
    })
    $("#user_pw").keyup(function (){
        if ($("#user_pw").val() != $("#user_pw2").val()){
            $("#verify").text("비밀번호가 일치하지 않습니다.")
            $("#verify").css('color','#f82a2aa3')
            pwVerify = false;
        }else{
            $("#verify").text("비밀번호가 일치합니다.")
            $("#verify").css('color','#199894b3')
            pwVerify = true;
        }
    })
});