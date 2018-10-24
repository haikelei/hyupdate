$(function () {
    /**
     * 检查登录态
     */
    checkLoginStatus();

    /**
     * 获取行资讯列表
     */
    ajaxByGET("user/getInformationCount", {}, initInformationPageCallback);
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
 * 初始化资讯列表分页控件
 * @returns
 */
function initInformationPageCallback(result) {
    let count = result.data.count;
    let page = Math.ceil(count / showNum);
    pagination(count, page, informationPageCallback);
    maxId = result.data.maxId > maxId ? result.data.maxId : maxId;
}
/**
 * 获取资讯列表
 * @param page
 * @returns
 */
function informationPageCallback(page) {
    currentPage = page;
    ajaxByGET("user/listInformation", {
        offset: (page - 1) * showNum,
        limit: showNum,
        maxId: maxId
    }, listInformationCallback);
}
/**
 * 加载数据
 * @param result
 * @returns
 */
function listInformationCallback(result) {
    let data = result.data;
    let table = $("#auditTable");
    table.empty();
    for (let i = 0; i < data.length; i++) {
        let td = "<tr>";
        td += "<td style='vertical-align: middle;'>" + ((currentPage - 1) * 10 + i + 1) + "</td>";
        td += "<td style='vertical-align: middle;'>" + data[i].title + "</td>";
        let infoPic = data[i].infoPic;
        if (infoPic == "" || infoPic == undefined) {
            td += "<td style='vertical-align: middle;'><img width='100px' src='https://www.i-cw.net/upload/nopic.png' /></td>";
        } else {
            td += "<td style='vertical-align: middle;'><img width='100px' src='" + infoPic + "' /></td>";
        }
        td += "<td style='vertical-align: middle;'>" + data[i].subtitle + "</td>";
        td += "<td style='vertical-align: middle; width:500px; max-height:120px'>" + data[i].detail + "</td>";
        td += "<td style='vertical-align: middle;'>" + timetrans(data[i].createTime) + "</td>";
        td += "<td style='vertical-align: middle;'><button onclick='editInformation(" + data[i].id + ")' type='button' class='btn btn-primary'>编辑</button> <button onclick='deleteInformation(" + data[i].id + ")' type='button' class='btn btn-danger'>删除</button></td>";
        table.append(td);
    }
}

function addInformation() {
    window.location.href = "info/add.html";
}

function editInformation(id) {
    window.location.href = "info/edit.html?id=" + id;
}

function deleteInformation(id) {
    $.confirm("你确认删除这条资讯吗？", function () {
        ajaxByPOST("user/deleteInformationById", {informationId: id}, deleteInformationCallback);
    })

}

function deleteInformationCallback(result) {
    let errCode = result.errCode;
    if (errCode < 0) {
        $(checkErrCode(errCode), function () {
        });
    } else {
        $.alert("删除成功！", function () {
            ajaxByGET("user/getInformationCount", {}, initInformationPageCallback);
        })
    }
}
