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

        var code = array[i].code == undefined ? " " : array[i].code;
        td += "<td style='vertical-align: middle;'>" + code + "</td>";

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
    location.href="/hy/withdraw/getExcel?start=" + currentPage + "&withdrawType=" + 1 + "&withdrawStatus=" + status;
}


    new AjaxUpload('#import_excle', {
        action: "/hy/withdraw/importExcel",
        name: 'file',
        autoSubmit: true,
        secureuri: false,
        crossDomain: true,
        dataType: "json",
        onSubmit: function (file, extension) {
            if (!(extension && /^(xls|xlsx)$/.test(extension.toLowerCase()))) {
                alert('请上传.xls或.xlsx类型的文件');
                return false;
            }
           /* msg(":" + file + "...");*/
        },
        success: function (data) {
            alert(data);
            if (data.code == 200) {
                alert(data.msg);
            } else {
                alert("操作失败！");
            }
        },
        error: function (data, status, e) {
            alert(data.msg);
        }
    });




