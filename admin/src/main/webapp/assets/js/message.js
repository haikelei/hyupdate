$(function () {
    /**
     * 检查登录态
     */
    //checkLoginStatus();

    /**
     * 获取所有管理员列表
     */
    ajaxByPOST("/hy/base/message/findMessageCount", {}, initPersonCallback);


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
    ajaxByPOST("/hy/base/message/findMessage", {start: page , limit: showNum}, listAllPersonCallBack);

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
        var headMessage = array[i].headMessage == undefined ? " " : array[i].headMessage;
        td += "<td style='vertical-align: middle;'>" + headMessage + "</td>";
        var title = array[i].title == undefined ? " " : array[i].title;
        td += "<td style='vertical-align: middle;'>" + title + "</td>";
        var content = array[i].content == undefined ? " " : array[i].content;
        td += "<td style='vertical-align: middle;'>" + content + "</td>";

        var code = array[i].code == undefined ? " " : array[i].code;
        td += "<td style='vertical-align: middle;'>" + code + "</td>";

        td += "<td style='vertical-align: middle;'>" + dateFormatUtil(array[i].createTime) + "</td>";

        td += "<td style='vertical-align: middle;'><button type='button' class='btn btn-primary' onclick='updMessage(" + id + ")'>编辑</a></button><button type='button' class='btn btn-primary' onclick='delMessage(" + array[i].id + ")'>删除</button></td>";


        table.append(td);
    }

}



function updMessage(id) {

    window.location= "messageEdit.html?id="+id;
    
}

function addMessage() {

    window.location= "messageAdd.html";
}

function delMessage(id) {
    ajaxByPOST("/hy/base/message//delMessage", {id: id}, initDelById)

}
function initDelById(result) {
    if (result.code != 200) {
        alert(result.msg)
        return
    } else {
        alert("删除成功!")
    }
    location.reload();
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

