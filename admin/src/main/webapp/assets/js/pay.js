$(function () {
    /**
     * 检查登录态
     */
    checkLoginStatus();

    /**
     * 获取所有支付列表
     */
    ajaxByGET("user/getAllPayCount", {}, initPayCallback);
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

function initPayCallback(result) {
    let count = result.data.count;
    let page = Math.ceil(count / showNum);
    pagination(count, page, initAllPayListPage);
    maxId = result.data.maxId > maxId ? result.data.maxId : maxId;
}

function initAllPayListPage(page) {
    currentPage = page;
    ajaxByGET("user/listAllPayList", {
        offset: (page - 1) * showNum,
        limit: showNum,
        maxId: maxId
    }, listAllPayListCallBack);

}

function listAllPayListCallBack(result) {
    console.info(result);
    let array = result.data;
    let table = $("#table");
    table.empty();
    for (let i = 0; i < array.length; i++) {
        let td = "<tr>";
        td += "<td style='vertical-align: middle;'>" + ((currentPage - 1) * 10 + i + 1) + "</td>";
        td += "<td style='vertical-align: middle;'>" + array[i].memberName + "</td>";
        td += "<td style='vertical-align: middle;'>" + timetrans(array[i].createTime) + "</td>";
        td += "<td style='vertical-align: middle;'>" + array[i].payName + "</td>";
        td += "<td style='vertical-align: middle;'>" + array[i].payAmount + "</td>";
        td += "<td style='vertical-align: middle;'>" + array[i].packageName + "</td>";
        td += "<td style='vertical-align: middle;'><button onclick='deletePay(" + array[i].id + ")' type='button' class='btn btn-danger'>删除</button></td>";
        table.append(td);
    }
}


function deletePay(id) {
    $.confirm("您确定要删除这条信息吗？", function () {
        ajaxByPOST("user/deletePayById", {payId: id}, deletePayByIdCallback);
    })

}

function deletePayByIdCallback(result) {
    let errCode = result.errCode;
    if (errCode < 0) {
        $.alert(checkErrCode(errCode), function () {
        });
    } else {
        $.alert("操作成功！", function () {
            ajaxByGET("user/getAllPayCount", {}, initPayCallback);
        })
    }
}

