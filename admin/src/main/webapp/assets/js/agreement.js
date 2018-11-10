$(function () {
    /**
     * 检查登录态
     */
    checkLoginStatus();

    /**
     * 获取第一个协议
     */
    ajaxByGET("/hy/agreement/findAgreementById", {id: 1}, init);


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
    console.info(result);
    var errCode = result.errCode;
    if (errCode < 0) {
        $.alert(checkErrCode(errCode), function () {
        });
    } else {
        var data = result.data;
        $("#informationTitle").val(data.title);
        $("#agreementId").val(data.id);

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

findClassify()

function findClassify() {
    ajaxByGET("/hy/agreement/findAgreementForType", {}, listSelectCallBack);
}


function listSelectCallBack(result) {
    var html = "";
    var role_html = ''
    var baseCompany = "协议列表"
    for (var i = 0; i < result.data.length; i++) {
        if (i == 0) {
            role_html += '<option value="" selected="selected">' + baseCompany + '</option>'
        }
        role_html += '<option value="' + result.data[i].id + '">' + result.data[i].title + '</option>'
        $("#selectForClass").html(role_html)
    }
}




$("select#selectForClass").click(function () {
    var se = $('#selectForClass option:selected').val();

        ajaxByGET("/hy/agreement/findAgreementById", {id: se}, init);

})

function update() {

    var content = tinymce.activeEditor.getContent();
    var title = $("#informationTitle").val();
    var id  = $("#agreementId").val();
    //alert(content)
    ajaxByPOST("/hy/agreement/updAgreement", {id: id,title:title,content:content}, forSuccess);

}

