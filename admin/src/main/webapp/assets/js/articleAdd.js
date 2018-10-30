$(function () {
    /**
     * 检查登录态
     */
    checkLoginStatus();
    var div = $("#textareaDiv");
    div.empty();
    var textarea = "<center><textarea id='textarea1'></testarea></center>";
    div.append(textarea);


    CKEDITOR.replace( 'textarea1');



    /*tinymce.init({
        selector: '#textarea1',
        branding: false,
        elementpath: false,
        height: 300,
        width:800,
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
        table_default_styles: {
            width: '60%',
            borderCollapse: 'collapse'
        }

    })*/

})





    new AjaxUpload('#upload-btn', {
        action: "/hy/file/saveFile",
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
                // alert('');
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



function update() {
    var url = getCookie("url");

    title=$("#title").val()
    firstContent=$("#firstContent").val()
    //content=tinymce.activeEditor.getContent();
    content = CKEDITOR.instances.textarea1.getData();
    code=$("#code").val();
    var type;
    $("input[name='optionsRadios']").each(function(i,v){
        if ($(v).is(":checked"))
        {
            type = ($(v).val());
            // alert(type);
        }
    });



    ajaxByPOST("/hy/article/addArticle",{title:title,firstContent:firstContent,code:code,url:url,content:content,userId:1,type:type},initSuccess)

    
}

function initSuccess(result) {
    removeCookie("url")
    if(result.code==200){
        alert("操作成功！")
        location.href="article.html";
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