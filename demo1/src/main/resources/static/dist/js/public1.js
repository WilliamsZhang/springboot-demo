function register() {
    var userName = $("#userName").val();
    var email = $("#email").val();
    var password = $("#password").val();
    var data = {"userName": userName, "email": email, "password": password}
    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "register",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data),
        success: function (result) {
            if (result.resultCode == 200) {
                $('.alert-danger').css("display", "none");
                alert("注册成功，请前往注册邮箱激活账号")
                window.location.href = "/toLogin";
            }
            ;
            if (result.resultCode == 412) {
                showErrorInfo(result.msg);
                return;
            }
        },
        error: function () {
            $('.alert-danger').css("display", "none");
            showErrorInfo("接口异常，请联系管理员！");
            return;
        }
    });
}


function showErrorInfo(info) {
    $('.alert-danger').css("display", "block");
    $('.alert-danger').html(info);
}