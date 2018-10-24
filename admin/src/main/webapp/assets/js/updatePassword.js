$(function () {
    /**
     * 检查登录态
     */
    checkLoginStatus();
    /**
     * 修改密码提交按钮
     */
    $("#submit_updatePassword").click(function () {
        let oldpwd = $("#old_password").val();
        let newpwd = $("#new_password").val();
        let newpwd2 = $("#new_password_second").val();
        if (newpwd != newpwd2) {
            $.alert("两次新密码输入不一致", function () {
                $("#new_password").val("");
                $("#new_password_second").val("");
            })
            return false;
        }
        ajaxByPOST("user/updatePassword", {
            oldPassword: hex_md5(oldpwd),
            newPassword: hex_md5(newpwd)
        }, updatePasswordCallback);
    })
    function updatePasswordCallback(result) {
        let errCode = result.errCode;
        if (errCode < 0) {
            $.alert(checkErrcode(errCode));
        } else {
            $.alert("修改成功！");
        }
        cleanUpdatePassword();

    }

    /**
     * 修改密码清空按钮
     */
    $("#clean_updatePassword").click(function () {
        cleanUpdatePassword();
    })


})

function cleanUpdatePassword() {
    $("#new_password").val("");
    $("#new_password_second").val("");
    $("#old_password").val("");
}
