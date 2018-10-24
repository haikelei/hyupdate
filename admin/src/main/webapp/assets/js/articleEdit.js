$(function () {
    /**
     * 检查登录态
     */
    checkLoginStatus();

    /**
     * 获取第一个协议
     */
    var url = window.location.href;
    var id = url.split("id=")[1];
    if(id == undefined){
        $.alert("页面参数有误！", function(){})
        return false;
    };
    console.log(id)
    ajaxByPOST("/hy/article/findArticleById", {id: id}, init);


})
/**
 * 最大值id
 */
var maxId = 0;
/**
 * 当前页码
 */
var currentPage = 1;
/**
 * 一页显示条数
 */
var showNum = 10;
/**
 * select类型
 */
var type = 0;

function init(result) {
    //console.info(result);
    var errCode = result.errCode;
    if (errCode < 0) {
        $.alert(checkErrCode(errCode), function () {
        });
    } else {
        var data = result.data;
        $("#firstContent").val(data.firstContent);
        $("#title").val(data.title);
        $("#code").val(data.code);
        $("#ima").val(1)
        $("#messageId").val(data.id);
        $("#articleImage").attr('src',imageDevURL+data.articleUrl);
        var div = $("#textareaDiv");
        div.empty();
        var textarea = "<textarea id='textarea1'></testarea>";
        div.append(textarea);
        $("#textarea1").html(data.content);
        CKEDITOR.replace( 'textarea1');

        /*tinymce.init({
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
            table_default_styles: {
                width: '100%',
                borderCollapse: 'collapse'
            }
        });*/
    }
}


function dateFormatUtil(longTypeDate) {
    var dateTypeDate = "";
    var date = new Date(longTypeDate);
    dateTypeDate += date.getFullYear();   //年
    dateTypeDate += "-" + (date.getMonth() + 1); //月
    dateTypeDate += "-" + date.getDate();  //日
    dateTypeDate += ' '
    dateTypeDate += date.getHours();
    dateTypeDate += ":" + date.getMinutes();
    dateTypeDate += ":" + date.getSeconds();
    return dateTypeDate;
}


function cancel() {

    location.reload();
}


function update() {


    var firstContent = $("#firstContent").val();
    var content = tinymce.activeEditor.getContent();
    var title = $("#title").val();
    var code = $("#code").val();
    var id  = $("#messageId").val();
    //console.log(title)
   // console.log(headMessage)
   // console.log(code)

    alert($("#ima").val())
    alert(getCookie("url"))
    if($("#ima").val()==2){
        var url = getCookie("url");
        ajaxByPOST("/hy/article/updArticle", {id: id,url:url,code:code,title:title,firstContent:firstContent,content:content}, initSuccess);
    }else {
        ajaxByPOST("/hy/article/updArticle", {id: id,code:code,title:title,firstContent:firstContent,content:content}, initSuccess);

    }

}

function initSuccess(result) {
    removeCookie("url")
    if(result.code==200){
        alert("操作成功！")
        location.reload();
    }else{
        console.log(result.msg)
        alert(result.msg)
        location.reload();
    }
}


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
            alert('');
            return false;
        }

        // removeCookie("url")
        // setCookie("url",file.data)
        // $("#ima").val(2)

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
        r=JSON.parse(r);
        //alert(r.data)
        removeCookie("url")
        setCookie("url",r.data)
        $("#ima").val(2)

        alert("上传成功！");

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