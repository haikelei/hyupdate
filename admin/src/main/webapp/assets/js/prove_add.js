$(function () {
    /**
     * 检查登录态
     */
    //checkLoginStatus();

    /**
     * 获取所有管理员列表
     */
    ajaxByGET("/hy/account/findListAddProveCount", {}, initPersonCallback);


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
    ajaxByGET("/hy/account/findListAddProve", {start: (page - 1) * showNum, limit: showNum}, listAllPersonCallBack);

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
        td += "<td style='display: none'>" + array[i].memberId + "</td>";
        var username = array[i].username == undefined ? " " : array[i].username;
        td += "<td style='vertical-align: middle;'>" + username + "</td>";
        var userPhone = array[i].userPhone == undefined ? " " : array[i].userPhone;
        td += "<td style='vertical-align: middle;'>" + userPhone + "</td>";

        var cardClassify = array[i].cardClassify == undefined ? " " : array[i].cardClassify;
        if (cardClassify == 100) {

            td += "<td style='vertical-align: middle;'>" + "二代身份证" + "</td>";
        } else if (cardClassify == 200) {
            td += "<td style='vertical-align: middle;'>" + "台湾通行证" + "</td>";
        } else {

            td += "<td style='vertical-align: middle;'>" + "港澳通行证" + "</td>";
        }
        var cardNumber = array[i].cardNumber == undefined ? " " : array[i].cardNumber;
        td += "<td style='vertical-align: middle;'>" + cardNumber + "</td>";

        var flowStatus = array[i].flowStatus == undefined ? " " : array[i].flowStatus;
        if (flowStatus == 0) {
            td += "<td style='vertical-align: middle;'>" + "<font color='#dc143c' font-weigh='bold'>待审核</font>" + "</td>";

        } else if (flowStatus == 1) {
            td += "<td style='vertical-align: middle;'>" + "<font color='black' font-weigh='bold'>审核通过</font>" + "</td>";

        } else {

            td += "<td style='vertical-align: middle;'>" + "<font color='#4b0082' font-weigh='bold'>审核不通过</font>" + "</td>";
        }

        var name = array[i].name == undefined ? " " : array[i].name;
        td += "<td style='vertical-align: middle;'>" + name + "</td>";

        td += "<td style='vertical-align: middle;'>" + dateFormatUtil(array[i].createTime) + "</td>";
        if (array[i].flowStatus == 0) {
            td += "<td style='vertical-align: middle;'><button type='button' class='btn btn-primary' onclick='updateProve(" + array[i].id + ")'>审核</button></td>";
        } else {
            td += "<td style='vertical-align: middle;'></td>";

        }

        table.append(td);
    }

}

function updateProve(id) {
    layer.open({
        type: 1,
        shade: false,
        title: '审核主播', //不显示标题
        area: ['300px', '210px'],
        content: $('.modal-body'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响

    });

    $("#hostProveId").val(id)
}


function updateProveForSuccessSubmit() {
    var hostProveId = $("#hostProveId").val();
    var se = $('#selectForClassify option:selected').val();
    if (se == "" && se == undefined) {
        alert("分类类型不能为空")
    }
    ajaxByPOST("/hy/live/updProve", {status: 0, id: hostProveId, classifyId: se}, initAddSuccess)

}
function updateProveForFailSubmit() {
    var hostProveId = $("#hostProveId").val();

    ajaxByPOST("/hy/live/updProve", {status: 1, id: hostProveId}, initAddSuccess)

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


$("select#selectForClass").click(function () {
    var se = $('#selectForClass option:selected').val();
    if (se == 0) {

        ajaxByGET("/hy/account/findListAddProve", {status: 0}, listAllPersonCallBack);
    } else if (se == 1) {
        ajaxByGET("/hy/account/findListAddProve", {status: 1}, listAllPersonCallBack);

    } else if (se == 2) {
        ajaxByGET("/hy/account/findListAddProve", {status: 2}, listAllPersonCallBack);
    } else {
        ajaxByGET("/hy/account/findListAddProve", {}, listAllPersonCallBack);
    }
})

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
