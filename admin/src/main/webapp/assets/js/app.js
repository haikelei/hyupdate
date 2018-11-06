$(function () {
    /**
     * 检查登录态
     */
    checkLoginStatus();
    // var div = $("#textareaDiv");
    // div.empty();
    // var textarea = "<center><textarea id='textarea1'></testarea></center>";
    // div.append(textarea);
    //
    //
    // CKEDITOR.replace( 'textarea1');
})


new AjaxUpload('#upload-btn', {
    action: "/hy/file/saveAppFile",
    name: 'file',
    autoSubmit: true,
    secureuri: false,
    crossDomain: true,
    dataType: "json",
    onChange: function (file, extension) {
        //     msg("文件格式错误");
    },
    onSubmit: function (file, extension) {
        // msg(":" + file + "...");

        $("#uploading").show();
        $("#uploaddone").hide();
    },
    success: function (data, status) {
        if (data.status == 200) {
            alert(data.result);
        } else {
            alert("操作失败！");
        }
    },
    error: function (data, status, e) {
        alert("访问失败" + e);
    },
    onComplete: function (file, r) {
        var obj = $.parseJSON(r.replace(/<.*?>/ig,""))
        // r = JSON.parse(r)
        // removeCookie("url")
        // setCookie("url",obj.data)
        // $("#imageAddId").attr('src',imageDevURL+obj.data);
        msg("上传成功！");
        // $("#ImgUrl").val(file);
        // $("#showImg").slideDown(500);

        $("#uploading").hide();
        $("#uploaddone").show();

        $("#hrefApk").html("http://47.106.196.89:8080/app/HYZS.apk")
    }
});

//自定义透明提示
function msg(content, timeout, callback) {
    content = content == null ? "这是一个非侵入式提示框" : content;
    timeout = timeout == null ? 2000 : timeout;
    var index = layer.msg(content, {
        offset: '100px',
        time: timeout
    }, function () {//提示关闭后的静默事件
        if (typeof (callback) === "function") {
            callback("ok");
        }
    })
    return index;

}