$(function () {
    /**
     * 检查登录态
     */
    checkLoginStatus();

    /**
     * 获取用户协议
     */
    ajaxByGET("user/getAbout", {}, init);


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

function cleanAbout() {
    init();
}

function aboutSubmit() {
    let about = {
        id: 1,
        content: tinymce.activeEditor.getContent()
    }
    ajaxByPOST("user/saveAbout", about, aboutSubmitCallback);
}

function aboutSubmitCallback(result) {
    console.info(result);
    let errCode = result.errCode;
    if (errCode < 0) {
        $.alert(checkErrCode(errCode), function () {
        });
        return;
    }
    $.alert("编辑成功！", function () {
    });
}

