window.T = {};
var host = "/";
//var baseURL = host+":58088/";
var baseURL = host + "hy/";
var imageDevURL = "http://47.106.196.89:8080";
var imageProdUrl = "http://47.106.196.89:8080";
var image = false;
// 获取请求参数
// 使用示例
// location.href = http://localhost/index.html?id=123
// T.p('id') --> 123;
var url = function (name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)return unescape(r[2]);
    return null;
};
T.p = url;


var errCodeObj = {
    "1": "后台管理员账号或密码有误",
    "2": "后台管理员账号或密码有误",
    "-2001": "后台管理员账号或密码有误",
    "-2002": "修改密码失败",
    "-2003": "用户不存在",
    "-2004": "旧密码有误",
    "-2005": "登录信息失效，请重新登录",
    "-2006": "审核失败",
    "-2007": "会员不存在",
    "-2008": "审核类型不正确",
    "-2009": "上传图片失败",
    "-2010": "上传图片参数有误",
    "-2011": "轮播图不存在",
    "-2012": "删除轮播图失败",
    "-2013": "新增资讯失败",
    "-2014": "获取资讯信息失败",
    "-2015": "删除资讯失败",
    "-2016": "该资讯不存在，请刷新页面重试",
    "-2017": "编辑资讯失败",
    "-2018": "删除套餐失败",
    "-2019": "新增套餐失败",
    "-2020": "编辑套餐失败",
    "-2021": "编辑用户协议失败",
    "-2022": "参数不正确",
    "-2023": "发布信息不存在",
    "-2024": "下线该信息失败",
    "-2025": "上线该信息失败",
    "-2026": "修改系统设置失败",
    "-2027": "修改信息失败",
    "-2028": "删除支付信息失败"

};
function checkErrCode(errCode) {
    return errCodeObj[errCode] == undefined ? "发生了一个未知的错误！ " : errCodeObj[errCode];
}


function ajaxByGET(url, data, callback) {
    $.ajax({
        type: "get",
        url: url,
        async: false,
        data: data,
        dataType: "json",
        success: function (result) {
            callback(result);
        }, error: function (result) {
            alert("操作失败，请重试！");
        }
    });
}


function ajaxByPOST(url, data, callback) {
    $.ajax({
        type: "post",
        url: url,
        async: false,
        data: data,
        dataType: "json",
        success: function (result) {
            callback(result);
        }, error: function (result) {
            alert("操作失败，请重试！");
        }
    });
}


function checkLoginStatus() {
    ajaxByGET("/hy/account/checkLogin", {}, function (result) {
        if (result != 0) {
            $.alert("当前不在登录状态，请登录！", function () {
                window.location = 'login.html';
            })
        }
    });
}

function exit(id) {
    $("#" + id).parent().removeClass("active");
    $("#index_li_exit").addClass("active");
    $.confirm("确认退出吗？", function () {
        //确认按钮
        ajaxByPOST("user/exit", {}, exitCallback);
    }, function () {
        //取消按钮
        $("#" + id).parent().addClass("active");
        $("#index_li_exit").removeClass("active");
    })
}

function exitCallback(result) {
    if (result.errCode == 0) {
        window.location = "login.html";
    }
}
function initAddSuccess(result) {

}

function pagination(count, page, callback) {
    $('#box').paging({
        initPageNo: 1, // 初始页码
        totalPages: page, //总页数
        totalCount: '合计' + count + '条数据', // 条目总数
        slideSpeed: 600, // 缓动速度。单位毫秒
        callback: function (pageResult) { // 回调函数
            callback(pageResult);
        }
    })
}
function pagination3(count, page, callback) {
    $('#boxRecording').paging({
        initPageNo: 1, // 初始页码
        totalPages: page, //总页数
        totalCount: '合计' + count + '条数据', // 条目总数
        slideSpeed: 600, // 缓动速度。单位毫秒
        callback: function (pageResult) { // 回调函数
            callback(pageResult);
        }
    })
}
function pagination2(count, page, callback) {
    $('#boxBank').paging({
        initPageNo: 1, // 初始页码
        totalPages: page, //总页数
        totalCount: '合计' + count + '条数据', // 条目总数
        slideSpeed: 600, // 缓动速度。单位毫秒
        callback: function (pageResult) { // 回调函数
            callback(pageResult);
        }
    })
}

function timetrans(date) {
    var date = new Date(date);//如果date为13位不需要乘1000
    var Y = date.getFullYear() + '-';
    var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    var D = (date.getDate() < 10 ? '0' + (date.getDate()) : date.getDate()) + ' ';
    var h = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':';
    var m = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()) + ':';
    var s = (date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds());
    return Y + M + D + h + m + s;
}

function timetransByYMD(date) {
    var date = new Date(date);//如果date为13位不需要乘1000
    var Y = date.getFullYear() + '-';
    var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    var D = (date.getDate() < 10 ? '0' + (date.getDate()) : date.getDate()) + ' ';
    return Y + M + D;
}

    function forSuccess(result) {
        if(result.code==200){
            alert("操作成功！")
            location.reload();
        }else{
            console.log(result.msg)
            alert(result.msg)
            location.reload();
        }
}



function setCookie(key, value, iDay) {
    var oDate = new Date();
    oDate.setDate(oDate.getDate() + iDay);
    document.cookie = key + '=' + value + ';expires=' + oDate;

}
function removeCookie(key) {
    setCookie(key, '', -1);//这里只需要把Cookie保质期退回一天便可以删除
}


function getCookie(key) {
    var cookieArr = document.cookie.split('; ');
    for(var i = 0; i < cookieArr.length; i++) {
        var arr = cookieArr[i].split('=');
        if(arr[0] === key) {
            return arr[1];
        }
    }
    return false;
}