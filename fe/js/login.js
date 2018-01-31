"use strict"
var login_Data;


var logout = function () {
    sessionStorage.removeItem('user');
    $.ajax({
        url: "user",
        contentType: 'application/json; charset=utf-8',
        method: "get"
    });
    location.reload();
};
var login = function (data) {
    $.unblockUI();
    $('#user-org').text("[" + data.orgNm + "]");
    $('#user-name').text(data.userNm);
    timeout();
    init();
};
if (window.sessionStorage) {
    login_Data = sessionStorage.getItem('user');
    if (login_Data != null) {
        login(JSON.parse(login_Data));


    } else {
        sessionStorage.clear();
    }
}


$.blockUI({message: $('#login_dialog'), overlayCSS: {opacity: 1}, css: {margin: '0, auto', width: '20%', top: "20%"}});

$('#logout_btn').click(function () {
    logout();
});
/* 로그인 엔터로도 버튼이눌러지도록.. */
$('#user_id, #user_pw').keypress(function (event) {
    if (event.which == 13) {
        event.preventDefault();
        $(this).parent().find('button').click();
    }

});

$(document).ready(function () {
    $("#dialog").hide();
    $("#org_dialog").hide();
    $("#history_dialog").hide();
    $('#login_btn').click(function () {
        var data = {};
        data.userId = $("#user_id").val();
        data.userPw = $("#user_pw").val();
        if (data.userId.length < 2 || data.userPw.length < 2) {

            $('#loginErrmsg').html("<i class=\"fa fa-times-circle\" aria-hidden=\"true\"></i> 아이디 혹은 비밀번호를 확인해 주세요 ").fadeOut("slow").fadeIn("slow").css({
                "color": "red"
            });

            return false;
        }
        $.ajax({
            url: "user",
            contentType: 'application/json; charset=utf-8',
            method: "post",
            data: JSON.stringify(data),
            success: function (data) {

                if (data.result == "S") {

                    $('#loginErrmsg').html("").fadeOut("slow").css({
                        "color": "red"
                    });
                    sessionStorage.setItem('user', JSON.stringify(data.user));
                    login(data.user);

                } else {
                    $('#loginErrmsg').html("<i class=\"fa fa-times-circle\" aria-hidden=\"true\"></i> 아이디 혹은 비밀번호를 확인해 주세요 ").fadeOut("slow").fadeIn("slow").css({
                        "color": "red"
                    });
                }
            }, error: function () {
                $('#loginErrmsg').html("<i class=\"fa fa-times-circle\" aria-hidden=\"true\"></i> 아이디 혹은 비밀번호를 확인해 주세요 ").fadeOut("slow").fadeIn("slow").css({
                    "color": "red"
                });
            }
        });
    });


});