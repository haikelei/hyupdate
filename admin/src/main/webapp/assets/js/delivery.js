$(function () {
    /**
     * 检查登录态
     */
    checkLoginStatus();

    /**
     * 获取所有轮播图列表
     */
    ajaxByGET("user/getAllDeliveryCount", {type: 0}, initDeliveryCallback);
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

function initDeliveryCallback(result) {
    var count = result.data.count;
    var page = Math.ceil(count / showNum);
    pagination(count, page, initAllDeliveryPage);
    maxId = result.data.maxId > maxId ? result.data.maxId : maxId;
}

function initAllDeliveryPage(page) {
    currentPage = page;
    ajaxByGET("user/listAllDelivery", {
        offset: (page - 1) * showNum,
        limit: showNum,
        maxId: maxId,
        type: type
    }, listAllDeliveryCallBack);

}

function listAllDeliveryCallBack(result) {
    var array = result.data;
    var table = $("#table");
    table.empty();
    for (var i = 0; i < array.length; i++) {
        var td = "<tr>";
        td += "<td style='vertical-align: middle;'>" + ((currentPage - 1) * 10 + i + 1) + "</td>";
        var name = array[i].memberName == undefined ? " " : array[i].memberName;
        td += "<td style='vertical-align: middle;'>" + name + "</td>";
        var phone = array[i].memberPhone == undefined ? " " : array[i].memberPhone;
        td += "<td style='vertical-align: middle;'>" + phone + "</td>";
        var startAddress = array[i].startProvince + array[i].startCity + array[i].startDistrict + array[i].startAddress;
        td += "<td style='vertical-align: middle; width:500px;'>" + startAddress + "</td>";
        var endAddress = array[i].endProvince + array[i].endCity + array[i].endDistrict + array[i].endAddress;
        td += "<td style='vertical-align: middle; width:500px;'>" + endAddress + "</td>";
        var demand = (array[i].demandDistance / 1000).toFixed(2);
        td += "<td style='vertical-align: middle;'>" + demand + "</td>";
        td += "<td style='vertical-align: middle;'>" + timetrans(array[i].createTime) + "</td>";
        var status = array[i].status;
        if (status == 1) {
            td += "<td style='vertical-align: middle;'><div class='alert alert-success alert-small alert-inline' role='alert'><strong>已上线</strong></div></td>";
            td += "<td style='vertical-align: middle;'><button onclick='downDelivery(" + array[i].id + ")' type='button' class='btn btn-primary'>下线</button></td>";
        }

        if (status == 2) {
            td += "<td style='vertical-align: middle;'><div class='alert alert-warning alert-small alert-inline' role='alert'><strong>已下线</strong></div></td>";
            td += "<td style='vertical-align: middle;'><button onclick='upDelivery(" + array[i].id + ")' type='button' class='btn btn-primary'>上线</button></td>";
        }

        if (status == 3) {
            td += "<td style='vertical-align: middle;'><div class='alert alert-danger alert-small alert-inline' role='alert'><strong>举报为虚假消息</strong></div></td>";
            td += "<td style='vertical-align: middle;'><button type='button' class='btn btn-primary'>查看</button></td>";
        }
        table.append(td);
    }
}


function deliveryType(obj) {
    type = obj.value;
    ajaxByGET("user/getAllDeliveryCount", {type: type}, initDeliveryCallback);
}

function downDelivery(id) {
    $.confirm("你确定下线该信息吗？", function () {
        ajaxByPOST("user/downDeliveryById", {deliveryId: id}, handleDeliveryCallback);
    })
}

function handleDeliveryCallback(result) {
    console.info(result);
    var errCode = result.errCode;
    if (errCode < 0) {
        $.alert(checkErrCode(errCode), function () {
        });
    } else {
        $.alert("操作成功！", function () {
            ajaxByGET("user/getAllDeliveryCount", {type: 0}, initDeliveryCallback);
        })
    }
}

function upDelivery(id) {
    $.confirm("你确定上线该信息吗？", function () {
        ajaxByPOST("user/upDeliveryById", {deliveryId: id}, handleDeliveryCallback);
    })
}


