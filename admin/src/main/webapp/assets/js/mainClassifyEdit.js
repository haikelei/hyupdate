$(function () {
    /**
     * 检查登录态
     */
    checkLoginStatus();

    var url = window.location.href;
    var id = url.split("id=")[1];
    if(id == undefined){
        $.alert("页面参数有误！", function(){})
        return false;
    };
    //console.log(id)
    ajaxByPOST("/hy/main/classify/findMainClassify", {id: id}, init);

})


function init(result) {
    console.log(result)
    if(result.code!=200){
        alert(result.msg)
        history.go(-1);
    }else {
        var url = result.data.url
        $("#classifyId").val(result.data.id)
        $("updateId").val(0)
        $("#code").val(result.data.code)
        $("#name").val(result.data.name)
        if(result.data.type==0){
            $("#rdo1").attr("checked", "checked");
        }else {
            $("#rdo2").attr("checked", "checked");
        }
        $("#imageAddId").attr('src',imageDevURL+url);
        $("#showImg").slideDown(500);

    }

}







    new AjaxUpload('#upload-btn', {
        action: "/hy/file/saveMainClassifyFile",
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
            alert(data);
            if (data.status == 200) {
                //把url保存到cookie中

                alert(data.result);

            } else {
                alert("操作失败！");
            }
        },
        error: function (data, status, e) {
            alert("访问失败" + e);
        },
        onComplete: function (file, r) {

            r =JSON.parse(r);

            removeCookie("url")
            setCookie("url",r.data)
            $("#imageAddId").attr('src',imageDevURL+r.data);
            msg("上传成功！");
            $("#updateId").val(1)
            $("#ImgUrl").val(file);
            $("#showImg").slideDown(500);

        }
    });



function update() {
        var code=$("#code").val()

    var name=$("#name").val()
    var id = $("#classifyId").val()
    if($("#updateId").val()==0){
        ajaxByPOST("/hy/main/classify/updMainClassify",{code:code,name:name,id:id},initSuccess)

    }else{
        var url = getCookie("url");
        //alert(url)
        ajaxByPOST("/hy/main/classify/updMainClassify",{code:code,name:name,id:id,url:url},initSuccess)

    }



    
}

function initSuccess(result) {
    removeCookie("url")
    if(result.code==200){
        alert("操作成功！")
        location.href="mainClassify.html";
    }else{
        console.log(result.msg)
        alert(result.msg)
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