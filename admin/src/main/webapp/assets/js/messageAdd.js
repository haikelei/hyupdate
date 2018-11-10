$(function () {
    /**
     * 检查登录态
     */
    checkLoginStatus();
    var div = $("#textareaDiv");
    div.empty();
    var textarea = "<textarea id='textarea1'></testarea>";
    div.append(textarea);
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

    })

})





function update() {

    var content = tinymce.activeEditor.getContent();
    var title = $("#title").val();
    var headMessage = $("#headTitle").val();
    var code = $("#code").val();

    // console.log(headMessage)
    // console.log(code)
    ajaxByPOST("/hy/base/message/addMessage", {code:code,title:title,headMessage:headMessage,content:content}, forSuccessCallBack);

}


function forSuccessCallBack(result) {
    if(result.code==200){
        alert("操作成功！")
        window.location= "message.html";
    }else{
        alert(result.msg)
    }
}
