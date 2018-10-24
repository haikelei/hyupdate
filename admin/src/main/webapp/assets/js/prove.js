$(function () {
    /**
     * 检查登录态
     */
    //checkLoginStatus();

    /**
     * 获取所有管理员列表
     */
    ajaxByGET("/hy/account/findProveCount", {}, initPersonCallback);


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
    ajaxByGET("/hy/account/findProve", {start: page , limit: showNum}, listAllPersonCallBack);

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
        td += "<td style='display: none'>" + array[i].liveId + "</td>";
        var username = array[i].username == undefined ? " " : array[i].username;
        td += "<td style='vertical-align: middle;'>" + username + "</td>";
        var liveCode = array[i].liveCode == undefined ? " " : array[i].liveCode;
        td += "<td style='vertical-align: middle;'>" + liveCode + "</td>";
        var liveStatus = array[i].liveStatus == undefined ? " " : array[i].liveStatus;
        if (liveStatus == 0) {

            td += "<td style='vertical-align: middle;'>" + "未开播" + "</td>";
        } else {
            td += "<td style='vertical-align: middle;'>" + "开播中" + "</td>";
        }
        var level = array[i].level == undefined ? " " : array[i].level;
        td += "<td style='vertical-align: middle;'>" + level + "</td>";

        var classifyName = array[i].classifyName == undefined ? " " : array[i].classifyName;
        td += "<td style='vertical-align: middle;'>" + classifyName + "</td>";


        if (array[i].theme != undefined) {

            td += "<td style='vertical-align: middle;'>" + array[i].theme + "</td>";
        } else {
            td += "<td style='vertical-align: middle;' readonly='readonly'>" + "<font color='gray'>暂无</font></td>"
        }

        if (array[i].title != undefined) {

            td += "<td style='vertical-align: middle;'>" + array[i].title + "</td>";
        } else {
            td += "<td style='vertical-align: middle;' readonly='readonly'>" + "<font color='gray'>暂无</font></td>"
        }

        var timeSum = array[i].timeSum == undefined ? " " : array[i].timeSum;
        td += "<td style='vertical-align: middle;'>" + timeSum + "</td>";


        var memberStatus = array[i].memberStatus == undefined ? " " : array[i].memberStatus;
        if (memberStatus == 0) {

            td += "<td style='vertical-align: middle;'>" + "是" + "</td>";
        } else {
            td += "<td style='vertical-align: middle;'>" + "不是" + "</td>";

        }
        var code = array[i].code == undefined ? " " : array[i].code;
        td += "<td style='vertical-align: middle;'>" + code + "</td>";
        var newCode = array[i].newCode == undefined ? " " : array[i].newCode;
        td += "<td style='vertical-align: middle;'>" + newCode + "</td>";
        var personCount = array[i].personCount == undefined ? " " : array[i].personCount;
        td += "<td style='vertical-align: middle;'>" + personCount + "</td>";

        td += "<td style='vertical-align: middle;'>" + dateFormatUtil(array[i].createTime) + "</td>";
        if (array[i].delFlag == 0) {
            td += "<td style='vertical-align: middle;'><button type='button' class='btn btn-primary' onclick='freezeUser(" + array[i].liveId + ")'>禁播</button>&nbsp;&nbsp;" +
                "<button type='button' class='btn btn-primary' onclick='changeCode(" + array[i].liveId + ")'>修改</button></td>";
        } else {
            td += "<td style='vertical-align: middle;'><button type='button' class='btn btn-primary' onclick='cancelFreezeUser(" + array[i].liveId + ")'>解禁</button>&nbsp;&nbsp;" +
                "<button type='button' class='btn btn-primary' onclick='changeCode(" + array[i].liveId + ")'>修改</button></td>";

        }

        table.append(td);
    }


}


function changeCode(id) {

    $("#editLiveId").val(id)
    layer.open({
        type: 1,
        shade: false,
        title: '修改直播间', //不显示标题
        area: ['400px', '250px'],
        content: $('.modal-eddit-body'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响

    });
    ajaxByPOST("/hy/live/findProveById", {id: id}, initFindSuccess)

}

function initFindSuccess(result) {
    if (result.code != 200) {
        alert(result.msg)
        return
    }
    $("#edidCode").val(result.data.code)
    $("#editPersonCount").val(result.data.personCount)
    $("#editnewcode").val(result.data.newCode)

}

function edidLiveSubmit() {

    var code = $("#edidCode").val();
    var personCount = $("#editPersonCount").val();
    var id = $("#editLiveId").val();
    var newCode = $("#editnewcode").val();
    ajaxByGET("/hy/account/updateProve", {code: code,newCode: newCode, personCount: personCount, id: id}, initSuccess)

}


function cancel() {

    location.reload();
}

function freezeUser(id) {
    ajaxByPOST("/hy/account/freezeProve", {type: 0, id: id}, initSuccess)
}
function cancelFreezeUser(id) {
    ajaxByPOST("/hy/account/freezeProve", {type: 1, id: id}, initSuccess)
}

function initSuccess(result) {
    if (result.code != 200) {
        alert(result.msg)
        cancel()
    } else {
        alert("操作成功")
        cancel()
    }

}


findClassify()

function findClassify() {
    ajaxByPOST("/hy/classify/findAllClassify", {type: 1}, listSelectCallBack);
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


function findAlbum() {

    var classifyId = null;
    var sex = null;
    var liveStatus = null;
    var str = null;
    if ($('#selectForClass option:selected').val() != "" && $('#selectForClass option:selected').val() != undefined && $('#selectForClass option:selected').val() != "直播间状态") {


        liveStatus = $('#selectForClass option:selected').val();
    }

    if ($('#selectForMember option:selected').val() != "" && $('#selectForMember option:selected').val() != undefined && $('#selectForMember option:selected').val() != "主播性别") {
        sex = $('#selectForMember option:selected').val();
    }

    if ($('#selectForClassify option:selected').val() != "" && $('#selectForClassify option:selected').val() != undefined) {
        classifyId = $('#selectForClassify option:selected').val();
    }
    if ($("#albumFind").val() != "" && $("#albumFind").val() != undefined) {
        str = $("#albumFind").val();
    }


    function listSoPersonCallBack(page) {
        currentPage = page;
        ajaxByGET("/hy/account/findProve", {
            start: (page - 1) * showNum, limit: showNum, liveStatus: liveStatus,
            sex: sex,
            str: str,
            classifyId: classifyId
        }, listAllPersonCallBack);

    }

    ajaxByGET("/hy/account/findProveCount", {
        liveStatus: liveStatus,
        sex: sex,
        str: str,
        classifyId: classifyId
    }, listSomePersonCallBack);


    function listSomePersonCallBack(result) {
        var count = result;
        var page = Math.ceil(count / showNum);
        pagination(count, page, listSoPersonCallBack);
    }

}
