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
    ajaxByPOST("/hy/banner/findBannerById", {id: id}, init);

})


function init(result) {
    console.log(result)
    if(result.code!=200){
        alert(result.msg)
        history.go(-1);
    }else {
        var url = result.data.url
        $("#classifyId").val(result.data.id)
        $("#updateId").val(0)
        $("#urlId").val(result.data.image)
        if(result.data.type==0){
            $("#rdo1").attr("checked", "checked");
        }else if(result.data.type==1){
            $("#rdo2").attr("checked", "checked");
        }else {

            $("#rdo3").attr("checked", "checked");
        }
        $("#code").val(result.data.code)
        $("#name").val(result.data.title)
        $("#imageAddId").attr('src',imageDevURL+url);
        $("#showImg").slideDown(500);

    }

}

function initClassify(result) {
    var html = "";
    var role_html = ''
    var baseCompany = "分类列表"
    var parentId = $("#parentId").val()
    for (var i = 0; i < result.data.length; i++) {
        // if (i == 0) {
        //     if(result.da.id==parentId){
        //         role_html += '<option value="" selected="selected">' + baseCompany + '</option>'
        //     }
        //     role_html += '<option value="" selected="selected">' + baseCompany + '</option>'
        // }
        if(result.data.id==parentId){
            role_html += '<option value="' + result.data[i].id + '" selected="selected">' + result.data[i].name + '</option>'
        }
        role_html += '<option value="' + result.data[i].id + '">' + result.data[i].name + '</option>'
        $("#selectForClassify").html(role_html)
    }

}





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
            var obj = $.parseJSON(r.replace(/<.*?>/ig,""))
            // r = JSON.parse(r)
            removeCookie("url")
            setCookie("url",obj.data)
            $("#imageAddId").attr('src',imageDevURL+obj.data);
            msg("上传成功！");
            $("#updateId").val(1)
            $("#ImgUrl").val(file);
            $("#showImg").slideDown(500);

        }
    });



function update() {
        var code=$("#code").val()

    var title=$("#name").val()
    var urlId=$("#urlId").val()
    var id = $("#classifyId").val()
    alert(id)

    if($("#updateId").val()==0){
        ajaxByPOST("/hy/banner/updBanner",{code:code,title:title,url:urlId,id:id},initSuccess)

    }else{
        var url = getCookie("url");
        //alert(url)
        ajaxByPOST("/hy/banner/updBanner",{code:code,title:name,id:id,url:urlId,image:url},initSuccess)

    }



    
}

function initSuccess(result) {
    removeCookie("url")
    if(result.code==200){
        alert("操作成功！")
        location.href="resource.html";
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