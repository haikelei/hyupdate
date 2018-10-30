$(function () {
    /**
     * 检查登录态
     */
    checkLoginStatus();

})




    new AjaxUpload('#upload-btn', {
        action: "/hy/file/saveBannerFile",
        name: 'file',
        autoSubmit: true,
        secureuri: false,
        crossDomain: true,
        dataType: "json",
        onChange: function (file, extension) {
            if (!(extension && /^(jpg|jpeg|png|gif|bmp)$/.test(extension.toLowerCase()))) {
                msg("");
            }
        },
        onSubmit: function (file, extension) {

            if (!(extension && /^(jpg|jpeg|png|gif|bmp)$/.test(extension.toLowerCase()))) {
                alert('');
                return false;
            }
            msg(":" + file + "...");
        },
        success: function (data, status) {
            // alert(data);
            if (data.status == 200) {
                //把url保存到cookie中

                // alert(data.result);

            } else {
                alert("操作失败！");
            }
        },
        error: function (data, status, e) {
            alert("访问失败" + e);
        },
        onComplete: function (file, r) {

            removeCookie("url")
            setCookie("url",r.data)
            $("#imageAddId").attr('src',imageDevURL+r.data);
            msg("上传成功！");
            $("#ImgUrl").val(file);
            $("#showImg").slideDown(500);

        }
    });




function initSuccess(result) {
    removeCookie("url")
    if(result.code==200){
        alert("操作成功！")
        location.href="resource.html";
    }else{
        console.log(result.msg)
        // alert(result.msg)
        location.reload();
    }
}

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


//添加
function addCla() {

    var name = $("#title").val();
    var image = getCookie("url");
    var code = $("#code").val();
    var url = $("#url").val();
    var type =  $('#selectForClass option:selected').val();
    $("#typeId").val(type)
    type=$("#typeId").val();

    if(type==""&&type==undefined&&type=="分类类型"){
        alert("请选择类型")
        return
    }

    if(image==""){
        alert("图片尚未上传!")
        return
    }

   ajaxByPOST("/hy/banner/saveBanner",{title:name,url:url,image:image,code:code,type:type},initForUrl)
    
}

function initClassify(result) {
    var html = "";
    var role_html = ''
    var baseCompany = "分类列表"
    for (var i = 0; i < result.data.length; i++) {
        if (i == 0) {

            role_html += '<option value="" selected="selected">' + baseCompany + '</option>'
        }
        role_html += '<option value="' + result.data[i].id + '">' + result.data[i].name + '</option>'
        $("#selectForClassify").html(role_html)
    }

}


function initForUrl(result) {

        removeCookie("url")
    if(result.code==200){
        alert("操作成功！")
        location.href="resource.html";
    }else {
        console.log(result.msg)
        // alert(result.msg)
        location.reload();
    }

}
