$(function () {
    checkLoginStatus2();

    //当前网页
    let url = window.location.href;
    let id = url.split("id=")[1];
    console.info(id);
    if (id == undefined) {
        $.alert("页面参数有误！", function () {
        })
        return false;
    }
    ;
    iid = id;
    ajaxByGET("../user/getInformationById", {informationId: id}, init);
})

var iid = 0;

function init(result) {
    console.info(result);
    let errCode = result.errCode;
    if (errCode < 0) {
        $.alert(checkErrCode(errCode), function () {
        });
    } else {
        let data = result.data;
        $("#informationTitle").val(data.title);
        $("#informationPicAddr").val(data.infoPic);
        $("#informationSubtitle").val(data.subtitle);

        let div = $("#textareaDiv");
        div.empty();
        let textarea = "<textarea id='textarea1'></testarea>";
        div.append(textarea);
        $("#textarea1").html(data.detail);
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
        $("#informationPic").fileinput({
            uploadUrl: '../user/uploadInformationPic',
            allowedFileExtensions: ['jpg', 'png', 'gif'],
            showUpload: true,
            showRemove: true,
            showCaption: false,
            showPreview: true,
            dropZoneEnabled: false,
            language: 'zh',
            allowedPreviewTypes: ['image'],
            allowedFileTypes: ['image'],
            maxFileCount: 1,
            layoutTemplates: {
                actionDelete: '',
                actionUpload: ''
            }
        });
        $("#informationPic").on("fileuploaded", function (event, data, previewId, index) {
            $("#informationPicAddr").val("https://www.i-cw.net/upload/information/" + data.response.data);
        })

        $("#informationContentPic").fileinput({
            uploadUrl: '../user/uploadInformationContentPic',
            allowedFileExtensions: ['jpg', 'png', 'gif'],
            showUpload: true,
            showRemove: true,
            showCaption: true,
            showPreview: false,
            dropZoneEnabled: false,
            language: 'zh',
            allowedPreviewTypes: ['image'],
            allowedFileTypes: ['image'],
            maxFileCount: 1,
            layoutTemplates: {
                actionDelete: '',
                actionUpload: ''
            }
        })
        $("#informationContentPic").on("filebatchuploadsuccess", function (event, data, previewId, index) {
            let insertPicUrl = "https://www.i-cw.net/upload/information/" + data.response.data;
            tinymce.activeEditor.insertContent("<img src='" + insertPicUrl + "'/>");
        })
    }


}


function update() {
    let information = {
        title: $("#informationTitle").val(),
        detail: tinymce.activeEditor.getContent(),
        subtitle: $("#informationSubtitle").val() == "" ? "官方发布" : $("#informationSubtitle").val(),
        infoPic: $("#informationPicAddr").val(),
        id: iid
    }
    ajaxByPOST("../user/editInformation", information, updateCallback);
}

function updateCallback(result) {
    let errCode = result.errCode;
    if (errCode < 0) {
        $.alert(checkErrCode(errCode), function () {
        })
    } else {
        $.alert("保存成功！", function () {
            window.location.href = "../information.html";
        })
    }
}


