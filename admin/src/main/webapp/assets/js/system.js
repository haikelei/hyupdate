$(function () {
    /**
     * 检查登录态
     */
    checkLoginStatus();

    /**
     * 获取所有系统设置
     */
    ajaxByGET("user/getSystemConfig", {}, initSystemConfigCallback);
    initFile();
})
function initFile() {

    $("#payPicSubmit").fileinput({
        uploadUrl: 'user/uploadPayPic',
        allowedFileExtensions: ['jpg', 'png', 'gif'],
        showUpload: true,
        showRemove: true,
        showCaption: true,
        showPreview: false,
        dropZoneEnabled: false,
        language: 'zh',
        allowedPreviewTypes: ['image'],
        allowedFileTypes: ['image'],
        maxFileCount: 1,
        layoutTemplates: {
            actionDelete: '',
            actionUpload: ''
        }
    });
    $("#payPicSubmit").on("filebatchuploadsuccess", function (event, data, previewId, index) {
        $("#payPicInput").attr("src", "https://www.i-cw.net/upload/pay/" + data.response.data);
    })
}
function initSystemConfigCallback(result) {
    let errCode = result.errCode;
    if (errCode < 0) {
        $.alert(checkErrCode(errCode), function () {
        })
    } else {
        let data = result.data;
        $("#notMemberCallNumInput").val(data.notMemberCallNum);
        $("#memberCallNumInput").val(data.memberCallNum);
        $("#customServiceTelInput").val(data.customServiceTel);
        $("#appVersionInput").val(data.appVersion);
        $("#refreshNumInput").val(data.refreshNum);
        let payPic = data.payPic;
        if ("" == payPic) {
            payPic = "https://www.i-cw.net/upload/nopic.png";
        }
        $("#payPicInput").attr("src", payPic);
    }
}

function cleanSystemConfig() {
    $("#notMemberCallNumInput").val("");
    $("#memberCallNumInput").val("");
    $("#customServiceTelInput").val("");
}

function saveSystemConfig() {
    let system = {
        id: 1,
        notMemberCallNum: $("#notMemberCallNumInput").val(),
        memberCallNum: $("#memberCallNumInput").val(),
        customServiceTel: $("#customServiceTelInput").val(),
        refreshNum: $("#refreshNumInput").val(),
        appVersion: $("#appVersionInput").val(),
        payPic: $("#payPicInput").attr("src")

    }
    ajaxByPOST("user/saveSystemConfig", system, saveCallback);
}

function saveCallback(result) {
    let errCode = result.errCode;
    if (errCode < 0) {
        $.alert(checkErrCode(errCode), function () {
        });
    } else {
        $.alert("操作成功！", function () {
        });
    }
}
