$(function () {
    /**
     * 检查登录态
     */
    //checkLoginStatus();

    /**
     * 获取所有管理员列表
     */
    ajaxByPOST("/hy/report/findReportCount", {}, initPersonCallback);


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
var type ;
$("#selectForClass").change(function(){
    if ($('#selectForClass option:selected').val() != "" && $('#selectForClass option:selected').val() != undefined && $('#selectForClass option:selected').val() != "提现方式") {
        type = $('#selectForClass option:selected').val();
    }
});

function initPersonCallback(result) {
    var count = result;
    var page = Math.ceil(count / showNum);

    pagination(count, page, initAllPersonPage);
}

function initAllPersonPage(page) {
    currentPage = page;

    ajaxByPOST("/hy/report/page", {start: page , limit: showNum ,type: $("#typeId").val() }, listAllPersonCallBack);

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
        var username = array[i].username == undefined ? " " : array[i].username;
        td += "<td style='vertical-align: middle;'>" + username + "</td>";

        var type = array[i].type;
        var typeValue = "";
        if (type == '100') {
            typeValue = "举报专辑";
        } else if (type == '200') {
            typeValue = "举报个人";
        } else if (type == '300') {
            typeValue = "举报文章";
        }
        td += "<td style='vertical-align: middle;'>" + typeValue + "</td>";

        var reportType = array[i].reportType;
        var reportTypeValue = "";
        if (reportType == '100') {
            reportTypeValue = "广告诈骗";
        } else if (reportType == '200') {
            reportTypeValue = "有害信息";
        } else if (reportType == '300') {
            reportTypeValue = "色情";
        } else if (reportType == '400') {
            reportTypeValue = "违法";
        } else if (reportType == '500') {
            var content = array[i].content == undefined ? "" : array[i].content;
            reportTypeValue = "其它（"+ content +"）";
        }
        td += "<td style='vertical-align: middle;'>" + reportTypeValue + "</td>";

        td += "<td style='vertical-align: middle;'>" + array[i].reportUsername + "</td>";

        td += "<td style='vertical-align: middle;'>" + dateFormatUtil(array[i].createTime) + "</td>";

        td += "<td style='vertical-align: middle;'><button type='button' class='btn btn-primary' onclick='audit(" + id + ")'>审核</a></button></td>";


        table.append(td);
    }

}

function audit() {
    alert("维护中...")
}

function delArticle(id) {
    ajaxByPOST("/hy/article/delArticle",{id:id},forSuccess);

}


function updMessage(id) {

    window.location= "messageEdit.html?id="+id;
    
}

function addArticle() {

    window.location= "articleAdd.html";
}

function updArticle(id) {

    window.location= "articleUpd.html?id="+id;
}


function initAddGift() {
    if (result.code != 200) {
        alert(result.msg)
        return
    } else {
        alert("添加成功!")
        return
    }

}





function initFindById(result) {
    $("#editGiftId").val(result.data.id)
    $("#edidGiftName").val(result.data.giftName);
    $("#editGiftCode").val(result.data.code);
    $("#editGiftDefaultPrice").val(result.data.defaultPrice);
    $("#editGiftDefaultMoney").val(result.data.defaultMoney);
    $("#editGiftPrice").val(result.data.price);
    $("#editGiftMoney").val(result.data.money);
}


function edidGiftSubmit() {
    var gift = {
        id: $("#editGiftId").val(),
        giftName: $("#edidGiftName").val(),
        defaultPrice: $("#editGiftDefaultPrice").val(),
        defaultMoney: $("#editGiftDefaultMoney").val(),
        code: $("#editGiftCode").val(),
        price: $("#editGiftPrice").val(),
        money: $("#editGiftMoney").val()
    }

    ajaxByPOST("/hy/gift/updGift", {gift: gift}, initEditGift)

}


function initEditGift(result) {
    if (result.code != 200) {
        alert(result.msg)
        return
    } else {
        alert("修改成功!")
        return
    }

}


function cancel() {

    location.reload();
}

function lookUp(){
    ajaxByPOST("/hy/report/findReportCount", {type: $("#typeId").val() }, initPersonCallback);

}

