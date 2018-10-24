$(function () {
    /**
     * 检查登录态
     */
    //checkLoginStatus();

    /**
     * 获取所有管理员列表
     */
    ajaxByGET("/hy/account/findRoleCount", {}, initPersonCallback);


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

function initPersonCallback(result) {
    var count = result;
    var page = Math.ceil(count / showNum);
    pagination(count, page, initAllPersonPage);
}

function initAllPersonPage(page) {
    currentPage = page;
    ajaxByGET("/hy/account/findRole", {start: (page - 1) * showNum, limit: showNum}, listAllPersonCallBack);

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


function listAllPersonCallBack(result) {
    var array = result.data;
    var table = $("#table");
    table.empty();
    for (var i = 0; i < array.length; i++) {
        var td = "<tr>";
        var id = array[i].id;
        //td += "<td style='vertical-align: middle;'>" + ((currentPage - 1) * 10 + i + 1) + "</td>";
        td += "<td style='display: none'>" + array[i].id + "</td>";
        var name = array[i].name == undefined ? " " : array[i].name;
        td += "<td style='vertical-align: middle;'>" + name + "</td>";

        var memo = array[i].memo == undefined ? " " : array[i].memo;
        td += "<td style='vertical-align: middle;'>" + memo + "</td>";

        var code = array[i].code == undefined ? " " : array[i].code;
        td += "<td style='vertical-align: middle;'>" + code + "</td>";

        var permissionIds = array[i].permissionIds == undefined ? " " : array[i].permissionIds;
        td += "<td style='vertical-align: middle;'><button type='button' class='btn btn-primary' onclick='findPermissions(\"" + permissionIds + "\")'>点击查看</button> </td>";

        td += "<td style='vertical-align: middle;'>" + dateFormatUtil(array[i].createTime) + "</td>";

        td += "<td style='vertical-align: middle;'><button type='button' class='btn btn-primary' onclick='findRecording(" + array[i].id + ")'>编辑</button> &nbsp;&nbsp;&nbsp;&nbsp; <button type='button' class='btn btn-primary' onclick='delRole(" + array[i].id + ")'>删除</button></td>";


        table.append(td);
    }

}


function findPermissions(ids) {

    layer.open({
        type: 1,
        shade: false,
        title: '查看权限', //不显示标题
        area: ['300px', '300px'],
        content: $('.modal-body'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响

    });

    ajaxByGET("/hy/account/findPermissionByIds",{ids:ids},initPermission)

    function initPermission(result) {
        var array = result.data;
        console.log(array)
        var html = "";
        for (var i = 0; i < array.length; i++) {
            var memo =array[i].memo
            html+= "<div><h5>"+memo+"</h5>"
            if(array[i].flag==0){

                html+="   <label class='single' style='margin-right: 30px'><input type='radio' name='"+array[i].tag+"' value='male'  />是</label>"
                html+="  <label class='single' style='margin-right: 20px'><input type='radio' name='"+array[i].tag+"'  value='female' checked='checked'  />否</label></div>";
            }else {
                html+="   <label class='single' style='margin-right: 30px'><input type='radio' name='"+array[i].tag+"' value='male' checked='checked' />是</label>"
                html+="  <label class='single' style='margin-right: 20px'><input type='radio' name='"+array[i].tag+"'  value='female'/>否</label></div>";
            }
            html+="<HR  width='80%'>"

        }

        $("#permission").append(html)

    }

}

function addRole() {
    window.location= "roleAdd.html";
}

function findRecording(id) {
    
}

function delRole(id) {

}


function findAlbum() {

    var classifyId = null;
    var bumSet = null;
    var chargeStatus = null;
    var str = null;
    if ($('#selectForClass option:selected').val() != "" && $('#selectForClass option:selected').val() != undefined && $('#selectForClass option:selected').val() != "收费类型") {


        bumSet = $('#selectForClass option:selected').val();
    }

    if ($('#selectForMember option:selected').val() != "" && $('#selectForMember option:selected').val() != undefined && $('#selectForMember option:selected').val() != "是否会员制") {
        chargeStatus = $('#selectForMember option:selected').val();
    }

    if ($('#selectForClassify option:selected').val() != "" && $('#selectForClassify option:selected').val() != undefined) {
        classifyId = $('#selectForClassify option:selected').val();
    }
    if ($("#albumFind").val() != "" && $("#albumFind").val() != undefined) {
        str = $("#albumFind").val();
    }


    ajaxByPOST("/hy/album/findAlbumForAccountCount", {
        classifyId: classifyId,
        moneyStatus: bumSet,
        str: str,
        memberStatus: chargeStatus
    }, listSomePersonCallBack);


    function listSoPersonCallBack(page) {
        currentPage = page;
        ajaxByPOST("/hy/album/findAlbumForAccount", {
            start: (page - 1) * showNum, limit: showNum, classifyId: classifyId,
            moneyStatus: bumSet,
            str: str,
            memberStatus: chargeStatus
        }, listAllPersonCallBack);

    }


    function listSomePersonCallBack(result) {
        var count = result;
        var page = Math.ceil(count / showNum);
        pagination(count, page, listSoPersonCallBack);
    }

}


function findAlbumMoney(id) {

    ajaxByPOST("/hy/album/findAlbumMoney", {id: id}, findAlbumMoneySuccess)

}


function findAlbumMoneySuccess(result) {

    layer.open({
        type: 1,
        shade: false,
        title: '查看或修改专辑金额', //不显示标题
        area: ['400px', '250px'],
        content: $('.modal-body'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响

    });

    $("#albumName").val(result.data.albumName)
    $("#albumId").val(result.data.albumId)

    if (result.data.type == 100) {
        $("#rdi1").attr("checked", "checked");
    } else if (result.data.type == 200) {
        $("#rdi2").attr("checked", "checked");
    } else {
        $("#rdi3").attr("checked", "checked");
    }


    if (result.data.chargeStatus == 0) {
        $("#rdo1").attr("checked", "checked");
    } else {

        $("#rdo2").attr("checked", "checked");
    }
    $("#bumSet").val(result.data.bumSet)
    $("#bumMoney").val(result.data.bumMoney)


}

//查看音频列表
function findRecording(id) {
    var albumId = id;
    layer.open({
        type: 1,
        shade: false,
        title: '查看音频列表', //不显示标题
        area: ['778px', '434px'],
        content: $('.modal-recording-body'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响

    });

    ajaxByPOST("/hy/album/findRecordingCount", {albumId: id}, initRecordingCallback);


    function initRecordingCallback(result) {
        var count = result;
        var page = Math.ceil(count / showNum);
        pagination3(count, page, initAllRecordingPage);
    }

    function initAllRecordingPage(page) {
        currentPage = page;
        ajaxByPOST("/hy/album/findRecordingForAccount", {
            start: (page - 1) * showNum,
            limit: showNum,
            albumId: albumId
        }, listAllRecordCallBack);
    }


    function listAllRecordCallBack(result) {
        var array = result.data;
        var table = $("#tableRecording");
        table.empty();
        for (var i = 0; i < array.length; i++) {
            var td = "<tr>";
            var id = array[i].id;
            td += "<td style='display: none'>" + array[i].id + "</td>";
            var username = array[i].username == undefined ? " " : array[i].username;
            td += "<td style='vertical-align: middle;'>" + username + "</td>";

            var title = array[i].title == undefined ? " " : array[i].title;
            td += "<td style='vertical-align: middle;'>" + title + "</td>";
            td += "<td style='vertical-align: middle;'>" + "" + "</td>";


            var clickNumber = array[i].clickNumber == undefined ? " " : array[i].clickNumber;
            td += "<td style='vertical-align: middle;'>" + clickNumber + "</td>";

            var playNumber = array[i].playNumber == undefined ? " " : array[i].playNumber;
            td += "<td style='vertical-align: middle;'>" + playNumber + "</td>";


            td += "<td style='vertical-align: middle;'>" + dateFormatUtil(array[i].createTime) + "</td>";

            var delFlag = array[i].delFlag == undefined ? " " : array[i].delFlag;
            if (delFlag == 0) {
                td += "<td style='vertical-align: middle;'><button type='button' class='btn btn-primary' onclick='freezeRecord(" + array[i].id + ")'>下架</button></td>";
            } else {
                td += "<td style='vertical-align: middle;'><button type='button' class='btn btn-primary' onclick='cancelFreezeRecord(" + array[i].id + ")'>上架</button></td>";

            }


            table.append(td);

        }
    }

}


function freezeRecord(id) {
    ajaxByPOST("/hy/album/freezeRecording", {id: id, type: 0}, initAddSuccess)
}

function cancelFreezeRecord(id) {
    ajaxByPOST("/hy/album/freezeRecording", {id: id, type: 1}, initAddSuccess)
}


function initAddSuccess(result) {

    if (result.code == 200) {
        alert("操作成功！")
        cancel()
    } else {
        alert("操作失败！")
        cancel()
    }

}

function cancel() {

    location.reload();
}


findClassify()

function findClassify() {
    ajaxByPOST("/hy/classify/findAllClassify", {type: 0}, listSelectCallBack);
}

function listSelectCallBack(result) {
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


function albumSubmit() {

    var id = $("#albumId").val();
    var type = $('#wrap1 input[name="type"]:checked ').val();
    var bumSet = $("#bumSet").val();
    var bumMoney = $("#bumMoney").val();
    var chargeStatus = $('#wrap input[name="chargeStatus"]:checked ').val();

    ajaxByPOST("/hy/album/updAlbum", {
        id: id,
        type: type,
        bumSet: bumSet,
        bumMoney: bumMoney,
        chargeStatus: chargeStatus
    }, initAddSuccess);

}