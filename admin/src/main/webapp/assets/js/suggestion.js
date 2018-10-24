$(function () {
    /**
     * 检查登录态
     */
    checkLoginStatus();

    /**
     * 初始化获取投诉建议列表
     */
    ajaxByGET("user/getAllSuggestionCount", {}, initSuggestionPageCallback);
})
var maxId = 0;
var currentPage = 1;
var showNum = 10;
function initSuggestionPageCallback(result) {
    let count = result.data.count;
    let page = Math.ceil(count / showNum);
    pagination(count, page, suggestionPageCallback);
    maxId = result.data.maxId;
}

function listAllSuggestionCallback(result) {
    let suggestionArray = result.data;
    let table = $("#suggestionTable");
    table.empty();
    for (let i = 0; i < suggestionArray.length; i++) {
        let td = "<tr>";
        let suggestionType = suggestionArray[i].suggestionType;
        let type = "";
        switch (suggestionType) {
            case 1:
                type = "建议";
                break;
            case 2:
                type = "投诉";
                break;
            case 3:
                type = "举报";
                break;
        }
        td += "<td>" + ((currentPage - 1) * 10 + i + 1) + "</td>";
        td += "<td>" + type + "</td>";
        td += "<td>" + suggestionArray[i].suggestionContent + "</td>";
        td += "<td>" + suggestionArray[i].memberPhone + "</td>";
        td += "<td>" + suggestionArray[i].memberNickname + "</td>";
        td += "<td>" + timetrans(suggestionArray[i].createTime) + "</td>";
        td += "</tr>";
        table.append(td);
    }
}


function suggestionPageCallback(page) {
    currentPage = page;
    ajaxByGET("user/listAllSuggestion", {
        offset: (page - 1) * showNum,
        limit: showNum,
        maxId: maxId
    }, listAllSuggestionCallback);
}
