$(function () {
    /**
     * 检查登录态
     */
    checkLoginStatus();

    /**
     * 获取用户协议
     */
    ajaxByGET("user/getUserProtocol", {}, init);

    /**
     * 获取用户发布协议
     */
    ajaxByGET("user/getUserReleaseProtocol", {}, initRelease);
})

function init(result) {

    let div = $("#textareaDiv");
    div.empty();
    let textarea = "<textarea id='textarea1'></testarea>";
    div.append(textarea);
    if (result.data != undefined) {
        $("#textarea1").html(result.data.content);
    } else {
        $("#textarea1").html("");
    }
    tinymce.init({
        selector: '#textarea1',
        branding: false,
        elementpath: false,
        height: 200,
        language: 'zh_CN.GB2312',
        menubar: 'edit insert view format table tools',
        theme: 'modern',
        plugins: [
            'advlist autolink lists link image charmap print preview hr anchor pagebreak imagetools',
            'searchreplace visualblocks visualchars code fullscreen fullpage',
            'insertdatetime media nonbreaking save table contextmenu directionality',
            'emoticons paste textcolor colorpicker textpattern imagetools codesample'
        ],
        toolbar1: ' newnote print fullscreen preview | insert | forecolor backcolor bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image emoticons codesample',
        autosave_interval: '20s',
        image_advtab: true,
        table_default_styles: {
            width: '100%',
            borderCollapse: 'collapse'
        }
    });

}

function cleanUserProtocol() {
    init();
}

function userProtocolSubmit() {
    let userProtocol = {
        content: tinymce.activeEditor.getContent()
    }
    ajaxByPOST("user/userProtocolSubmit", userProtocol, userProtocolSubmitCallback);
}

function userProtocolSubmitCallback(result) {
    let errCode = result.errCode;
    if (errCode < 0) {
        $.alert(checkErrCode(errCode), function () {
        });
        return;
    }
    $.alert("编辑成功！", function () {
    });
}

/**---------------用户发布协议-------------------*/


function initRelease(result) {
    console.info(result);
    let div = $("#textareaReleaseDiv");
    div.empty();
    let textarea = "<textarea id='textareaRelease'></testarea>";
    div.append(textarea);
    if (result.data != undefined) {
        $("#textareaRelease").html(result.data.content);
    } else {
        $("#textareaRelease").html("");
    }
    tinymce.init({
        selector: '#textareaRelease',
        branding: false,
        elementpath: false,
        height: 200,
        language: 'zh_CN.GB2312',
        menubar: 'edit insert view format table tools',
        theme: 'modern',
        plugins: [
            'advlist autolink lists link image charmap print preview hr anchor pagebreak imagetools',
            'searchreplace visualblocks visualchars code fullscreen fullpage',
            'insertdatetime media nonbreaking save table contextmenu directionality',
            'emoticons paste textcolor colorpicker textpattern imagetools codesample'
        ],
        toolbar1: ' newnote print fullscreen preview | insert | forecolor backcolor bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image emoticons codesample',
        autosave_interval: '20s',
        image_advtab: true,
        table_default_styles: {
            width: '100%',
            borderCollapse: 'collapse'
        }
    });

}

function cleanUserReleaseProtocol() {
    initRelease();
}

function userReleaseProtocolSubmit() {
    let userProtocol = {
        content: tinymce.activeEditor.getContent()
    }
    ajaxByPOST("user/userReleaseProtocolSubmit", userProtocol, userReleaseProtocolSubmitCallback);
}

function userReleaseProtocolSubmitCallback(result) {
    let errCode = result.errCode;
    if (errCode < 0) {
        $.alert(checkErrCode(errCode), function () {
        });
        return;
    }
    $.alert("编辑成功！", function () {
    });
}
