$(function () {
    /**
     * 检查登录态
     */
    //checkLoginStatus();

    /**
     * 获取所有列表
     */
    ajaxByPOST("/hy/deal/findAllDealCount", {status: 1}, initPersonCallback);

    initAllPersonPage();
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

    //$("#aliMode").css("display","block");
    currentPage = page;
    ajaxByPOST("/hy/deal/findAllDeal", {
        start: page ,
        limit: showNum,
        status: 1,
        type: $("#selectForType").val()
    }, listAllPersonCallBack);

}

function lookUp(){
    ajaxByPOST("/hy/deal/findAllDealCount", {status: 1, type: $("#selectForType").val()}, initPersonCallback);
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
    $("#bankMode").css("display", "none");
    $("#aliMode").css("display", "block");

    var array = result.data;
    var table = $("#table");
    table.empty();
    for (var i = 0; i < array.length; i++) {
        var td = "<tr>";
        var id = array[i].id;
        td += "<td>" + array[i].id + "</td>";

        var username = array[i].username == undefined ? " " : array[i].username;
        td += "<td style='vertical-align: middle;text-align: center'>" + username + "</td>";

        var phone = array[i].phone == undefined ? " " : array[i].phone;
        td += "<td style='vertical-align: middle;text-align: center'>" + phone + "</td>";

        var code = array[i].code == undefined ? " " : array[i].code;
        td += "<td style='vertical-align: middle;'>" + code + "</td>";

        var type = array[i].type == undefined ? " " : array[i].type;
        var typeValue = "";
        if (type == 0) {
            typeValue = "充值";
        } else if (type == 1) {
            typeValue = "提现";
        }  else if (type == 2) {
            typeValue = "发出打赏";
        }  else if (type == 3) {
            typeValue = "接受礼物";
        }  else if (type == 4) {
            typeValue = "返回";
        }  else if (type == 5) {
            typeValue = "开通会员";
        }
        td += "<td style='vertical-align: middle;text-align: center'>" + typeValue + "</td>";

        td += "<td style='vertical-align: middle;text-align: center'>" +array[i].money + "</td>";

        td += "<td style='vertical-align: middle;'>" + dateFormatUtil(array[i].createTime) + "</td>";

        table.append(td);
    }
}

function cancel() {

    location.reload();
}


function getExcle(){
    if(currentPage == undefined){
        currentPage = 1;
    }
    var type = $("#selectForType").val();
    location.href="/hy/deal/getExcel?limit=" + 65535 + "&status=" + 1 + "&type=" + type;
    // location.href="/hy/deal/getExcel?start=" + currentPage + "&limit=" + showNum + "&status=" + 1 + "&type=" + type;
}
