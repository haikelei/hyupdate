$(function () {
    /**
     * 检查登录态
     */
    //checkLoginStatus();

    /**
     * 获取所有管理员列表
     */
    ajaxByGET("/hy/account/findAllUserCount", {}, initPersonCallback);


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
    ajaxByGET("/hy/account/findAllUser", {start: page , limit: showNum}, listAllPersonCallBack);

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

    $("#table").unbind("mouseenter").unbind("mouseleave");
    for (var i = 0; i < array.length; i++) {

        var color = "blick";
        if(array[i].proveStatus == 1){
            color = "#00FF7F";
        }
        if(array[i].memberStatus == 1){
            color = "#F0E68C";
        }
        var td = "<tr style='background-color: " + color + " '>";

        var id = array[i].id;
        //td += "<td style='vertical-align: middle;'>" + ((currentPage - 1) * 10 + i + 1) + "</td>";
        td += "<td style='vertical-align: middle;'>" + array[i].id + "</td>";
        var username = array[i].username == undefined ? " " : array[i].username;
        td += "<td style='vertical-align: middle;'>" + username + "</td>";
        var sex = array[i].sex == undefined ? " " : array[i].sex;
        if (sex == 1) {

            td += "<td style='vertical-align: middle;'>" + "男" + "</td>";
        } else {
            td += "<td style='vertical-align: middle;'>" + "女" + "</td>";

        }
        var phone = array[i].phone == undefined ? " " : array[i].phone;
        td += "<td style='vertical-align: middle;'>" + phone + "</td>";

        if (array[i].address != undefined) {

            td += "<td style='vertical-align: middle;'>" + array[i].address + "</td>";
        } else {
            td += "<td style='vertical-align: middle;' readonly='readonly'>" + "<font color='gray'>暂无</font></td>"
        }

        if (array[i].intro != undefined) {

            td += "<td style='vertical-align: middle;'>" + array[i].intro + "</td>";
        } else {
            td += "<td style='vertical-align: middle;' readonly='readonly'>" + "<font color='gray'>暂无</font></td>"
        }

        var level = array[i].level == undefined ? " " : array[i].level;
        td += "<td style='vertical-align: middle;'>" + level + "</td>";

        var money = array[i].money == undefined ? " " : array[i].money;
        td += "<td style='vertical-align: middle;'>" + money + "</td>";

        var proveStatus = array[i].proveStatus == undefined ? " " : array[i].proveStatus;
        if (proveStatus == 0) {

            td += "<td style='vertical-align: middle;'>" + "不是" + "</td>";
        } else {
            td += "<td style='vertical-align: middle;'>" + "是" + "</td>";

        }
        var memberStatus = array[i].memberStatus == undefined ? " " : array[i].memberStatus;
        if (memberStatus == 0) {

            td += "<td style='vertical-align: middle;'>" + "不是" + "</td>";
        } else {
            td += "<td style='vertical-align: middle;'>" + "是" + "</td>";


        }
        td += "<td style='vertical-align: middle;'>" + dateFormatUtil(array[i].createTime) + "</td>";
        if (array[i].delFlag == 0) {
            td += "<td style='vertical-align: middle;'><button type='button' class='btn btn-primary' onclick='freezeUser(" + array[i].id + ")'>冻结</button></td>";
        } else {
            td += "<td style='vertical-align: middle;'><button type='button' class='btn btn-primary' onclick='cancelFreezeUser(" + array[i].id + ")'>解冻</button></td>";

        }
        table.append(td);
    }
}

function freezeUser(id) {
    ajaxByGET("/hy/account/freezeUser", {id: id, type: 0}, initFreezeCallBack);

}
function cancelFreezeUser(id) {
    ajaxByGET("/hy/account/freezeUser", {id: id, type: 1}, initCancleFreezeCallBack);

}


function initFreezeCallBack(result) {

    if (result.code == 200) {
        alert("冻结成功")
        cancel()
    } else {
        alert("操作失败")
        cancel()
    }

}
function initCancleFreezeCallBack(result) {

    if (result.code == 200) {
        alert("解冻成功")
        cancel()
    } else {
        alert("操作失败")
        cancel()
    }

}


function cancel() {

    location.reload();
}

