
var classifyId = null;

$(function () {
    //获取专辑类型
    ajaxByPOST("/hy/album/findAlbumForAccount", {start: 0  , limit: 1000000000}, listSelectCallBack);

    // 获取主播
    ajaxByGET("/hy/account/findAllUser", {start: 0  , limit: 1000000000, proveStatus: 1}, listProveSelectCallBack);

});


//添加分类下拉列表
if ($('#albumId option:selected').val() != "" && $('#albumId option:selected').val() != undefined) {
    classifyId = $('#albumId option:selected').val();
}

function listSelectCallBack(result) {
    var html = "";
    var role_html = ''
    var baseCompany = "分类列表"
    for (var i = 0; i < result.data.length; i++) {
        if (i == 0) {
            role_html += '<option value="" selected="selected">' + baseCompany + '</option>'
        }
        role_html += '<option value="' + result.data[i].id + '" onclick="">' + result.data[i].albumName + '</option>'
        $("#albumId").html(role_html)
    }
}

function listProveSelectCallBack(result) {
    var html = "";
    var role_html = '';
    var baseCompany = "主播列表";
    for (var i = 0; i < result.data.length; i++) {
        if (i == 0) {
            role_html += '<option value="" selected="selected">' + baseCompany + '</option>'
        }
        role_html += '<option value="' + result.data[i].id + '" onclick="">' + result.data[i].username + '</option>'
        $("#proveId").html(role_html)
    }
}

function gotoAddAublm() {
    location.href="albumRealAdd.html";
}

function  HtmlLoadAndContinue(){
    $('#myModal').modal('show');
    var title = $("#title").val();
    var albumId = $("#albumId").val();
    var url = $("#ImgUrl").val();
    var sign = $("#sign").val();

    var userId = $("#anchorId").val();
    if (userId == '') {
        userId = 1;
    }
    $.ajax({
        type: "post",
        url: "/hy/record/addBybackstage",
        async: false,
        data: {title:title,albumId:albumId,userId:userId,url:url,sign:sign},
        dataType: "json",
        success: function (result) {
            if(result.code == 200){
                alert("操作成功！");
                location.href="albumAdd.html";
            }else {
                alert("操作失败，请重新操作！");
                $('#myModal').modal('hide');
            }
        },error: function (result) {
            alert("操作失败，请重试！");
            $('#myModal').modal('hide');
        }
    });

}

function  HtmlLoad(){
    $('#myModal').modal('show');
    updata();
}

//保存数据
function updata(){
    var title = $("#title").val();
    var albumId = $("#albumId").val();
    var url = $("#ImgUrl").val();
    var sign = $("#sign").val();
    $.ajax({
        type: "post",
        url: "/hy/record/addBybackstage",
        async: false,
        data: {title:title,albumId:albumId,userId:1,url:url,sign:sign},
        dataType: "json",
        success: function (result) {
            if(result.code == 200){
                alert("操作成功！");
                location.href="album.html";
            }else {
                alert("操作失败，请重新操作！");
                $('#myModal').modal('hide');
            }
        },error: function (result) {
            alert("操作失败，请重试！");
            $('#myModal').modal('hide');
        }
    });

};

function init(result) {
    if(result.code==200){
        $('#myModal').modal('hide');
        alert("操作成功！")
        location.href="album.html";
    }else{
        $('#myModal').modal('hide');
        console.log(result.msg)
        alert(result.msg)
    }
}


//音频封面上传
new AjaxUpload('#upload-btn', {
    action : '/hy/file/saveRecordingFile',
    name : 'file',
    responseType : 'json',
    onSubmit : function(file, ext) {
        if (ext && /^(jpg|png|bmp|gif)$/.test(ext.toLowerCase())) {
            this.setData({
                'file' : file
            });
        } else {
            alert("请上传格式为 jpg|png|bmp|gif 的图片！");
            return false;
        }
    },
    onComplete : function(file, response) {
        if (response.error) {
            alert(response.error);
            return;
        }
        $("#imageAddId").attr(
            "src",
            imageProdUrl + response.data);
        $("#ImgUrl").val(response.data);
        $("#showImg").slideDown(500);
    }
});

//音频上传
new AjaxUpload('#upload-recroding', {
    action : '/hy/record/addRecording',
    name : 'file',
    responseType : 'json',
    onSubmit : function(file, ext) {
        if (ext && /^(mp3|mp4)$/.test(ext.toLowerCase())) {
            this.setData({
                'file' : file
            });
        } else {
            alert("请上传格式为 mp3|mp4 的音频！");
            return false;
        }
    },
    onComplete : function(file, response) {
        if (response.error) {
            alert(response.error);
            return;
        }
        $("#sign").attr("value",response.data);
        alert("音频上传成功");

    }
});





