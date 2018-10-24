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
    //console.log(id)
    ajaxByPOST("/hy/base/message/findMessageById", {id: id}, init);


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
        $("#headTitle").val(data.headMessage);
        $("#title").val(data.title);
        $("#code").val(data.code);
        $("#messageId").val(data.id);

        var div = $("#textareaDiv");
        div.empty();
        var textarea = "<textarea id='textarea1'></testarea>";
        div.append(textarea);
        $("#textarea1").html(data.content);
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
            table_default_styles: {
                width: '100%',
                borderCollapse: 'collapse'
            }
        });
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

    var content = tinymce.activeEditor.getContent();
    var title = $("#title").val();
    var headMessage = $("#headTitle").val();
    var code = $("#code").val();
    var id  = $("#messageId").val();
    //console.log(title)
   // console.log(headMessage)
   // console.log(code)
    ajaxByPOST("/hy/base/message/updMessage", {id: id,code:code,title:title,headMessage:headMessage,content:content}, forSuccess);

}

