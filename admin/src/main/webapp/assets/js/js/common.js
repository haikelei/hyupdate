//请求前缀
var host = "localhost:7080";
//var baseURL = host+":58088/";
var baseURL = host + "/hy/";
var nginxURL = host + ":58000/";
var webappURL = host + ":58001/";
var picServerURL = host + ":58001/images/";
var domainConfig = "yn.com";//跨域配置

//jqGrid的配置信息
// $.jgrid.defaults.width = 1000;
// $.jgrid.defaults.responsive = true;
// $.jgrid.defaults.styleUI = 'Bootstrap';

//工具集合Tools
window.T = {};

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

//登录token
// var token = localStorage.getItem("token");
// if(token == 'null'){
//     parent.location.href = baseURL+'login.html';
// }else{//界面加载的显示优化 - > 视觉动画
// 	$(".hiden").fadeIn(500).addClass("animated fadeIn");
// }

//jquery全局配置
$.ajaxSetup({
    dataType: "json",
    cache: false,
    headers: {
        "Access-Control-Allow-Origin": "*",
        "token": token
    },
    xhrFields: {
        withCredentials: true
    },
    complete: function (xhr) {
        //token过期，则跳转到登录页面
        // console.log(xhr.responseJSON.code);
        if (xhr.responseJSON == null || xhr.responseJSON.code == 401) {
            parent.location.href = nginxURL + 'login.html';
        }
        // if(xhr.responseJSON.code == 0){
        // 	alert('无法与远程服务器建立安全通信！<br/>错误代码: 0x12100544',function(){
        // 		parent.location.href = nginxURL + 'login.html';
        // 	});
        // }
    }
});

//服务可用性监测
function checkServer() {
    $.ajax({
        url: baseURL + "auth/notToken",
        type: 'GET',
        complete: function (response) {
            // console.log(response.status)
            if (response.status != 200) {
                alert('无法与远程服务器建立安全通信！<br/>错误代码: 0x12100544');
            }
        }
    });
}
// checkServer();


//jqgrid全局配置
$.extend($.jgrid.defaults, {
    ajaxGridOptions: {
        headers: {
            "token": token
        }
    }
});

//权限判断
function hasPermission(permission) {
    if (window.parent.permissions.indexOf(permission) > -1) {
        return true;
    } else {
        return false;
    }
}

//重写alert
window.alert = function (msg, callback) {
    parent.layer.alert(msg, {
        title: '提示',
        offset: '100px'
    }, function (index) {
        parent.layer.close(index);
        if (typeof (callback) === "function") {
            callback("ok");
        }
    });
}

//重写confirm式样框
window.confirm = function (msg, callback) {
    parent.layer.confirm(msg, {
        title: '提示',
        offset: '100px',
        btn: ['确定', '取消']
    }, function () { //确定事件
        if (typeof (callback) === "function") {
            callback("ok");
        }
    });
}

//自定义吸附tip方法:默认选择器".zxx-tip"
function tip(tipcontent, ele, tiptype, bgcolor, timeoutt) {
    ele = ele == null ? ".zxx-tip" : ele;
    tiptype = tiptype == null ? 1 : tiptype;
    bgcolor = bgcolor == null ? "#3385FF" : bgcolor;
    timeoutt = timeoutt == null ? "4000" : timeoutt;
    layer.tips(tipcontent, ele, {
        tips: [tiptype, bgcolor],
        time: timeoutt
    });
}

//自定义透明提示
function msg(content, timeout, callback) {
    content = content == null ? "这是一个非侵入式提示框" : content;
    timeout = timeout == null ? 2000 : timeout;
    var index = layer.msg(content, {
        offset: '100px',
        time: timeout
    }, function () {//提示关闭后的静默事件
        if (typeof (callback) === "function") {
            callback("ok");
        }
    })
    return index;

}

function frame(title, uri) {
    title = title == null ? "弹出Iframe框" : title;
    uri = uri == null ? "http://www.baidu.com" : uri;
    parent.layer.open({
        type: 2,
        offset: '100px',
        title: title,
        area: ['1000px', '600px'],
        // shade: 0.8,
        maxmin: true,
        shadeClose: false,
        content: uri
    })
}

//Layer全局对象
var layerIndex;

//自定义tip方法:默认选择器".zxx-tip"
function tip(tipcontent, ele, tiptype, bgcolor, timeoutt) {
    ele = ele == null ? ".zxx-tip" : ele;
    tiptype = tiptype == null ? 1 : tiptype;
    bgcolor = bgcolor == null ? "#3385FF" : bgcolor;
    timeoutt = timeoutt == null ? "3000" : timeoutt;
    var layerTip = layer.tips(tipcontent, ele, {
        tips: [tiptype, bgcolor],
        time: timeoutt
    });
    return layerTip;
}

//自定义loading方法
// function loading(loadingtype, timeout, moffset) {
// 	loadingtype = loadingtype == null ? 0 : loadingtype;
// 	moffset = moffset == null ? '200px' : moffset;
// 	timeout = timeout == null ? 800 : timeout;
// 	var layerIndex;
// 	if (loadingtype == 0) {
// 		layerIndex = parent.layer.load(0, {
// 			offset : moffset,
// 			time : timeout
// 		}); //0代表加载的风格，支持0-2
// 	} else if (loadingtype == 1) {
// 		layerIndex = parent.layer.load(1, {
// 			offset : moffset,
// 			time : timeout
// 		}); //0.1透明度的白色背景: [0.1,'#fff']
// 	} else if (loadingtype == 2) {
// 		layerIndex = parent.layer.load(2, {
// 			offset : moffset,
// 			time : timeout
// 		}); //0.1透明度的白色背景
// 	}
// 	return layerIndex;
// }


//选择一条记录
function getSelectedRow() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if (!rowKey) {
        alert("请选择一条记录");
        return;
    }

    var selectedIDs = grid.getGridParam("selarrrow");
    if (selectedIDs.length > 1) {
        alert("只能选择一条记录");
        return;
    }

    return selectedIDs[0];
}

//选择多条记录
function getSelectedRows() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if (!rowKey) {
        alert("请选择一条记录");
        return;
    }

    return grid.getGridParam("selarrrow");
}

//判断是否为空
function isBlank(value) {
    return !value || !/\S/.test(value)
}

function getUUID() {
    var s = [];
    var hexDigits = "0123456789abcdef";
    for (var i = 0; i < 36; i++) {
        s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
    }
    s[14] = "4";
    s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);

    s[8] = s[13] = s[18] = s[23] = "-";

    var uuid = s.join("");
    return uuid;
}

//计算字符串长度:英文占1字节,中文占2字节
function strlen(str) {
    var len = 0;
    for (var i = 0; i < str.length; i++) {
        var c = str.charCodeAt(i);
        //单字节加1
        if ((c >= 0x0001 && c <= 0x007e) || (0xff60 <= c && c <= 0xff9f)) {
            len++;
        } else {
            len += 2;
        }
    }
    return len;
}

//日期格式化:long->string字符串
function formatDate(formatStr, fdate) {
    var fTime,
        fStr = 'ymdhis';
    if (!formatStr)
        formatStr = "y-m-d h:i:s";
    if (fdate)
        fTime = new Date(fdate);
    else
        fTime = new Date();
    var formatArr = [
        fTime.getFullYear().toString(),
        (fTime.getMonth() + 1).toString(),
        fTime.getDate().toString(),
        fTime.getHours().toString(),
        fTime.getMinutes().toString(),
        fTime.getSeconds().toString()
    ]
    for (var i = 0; i < formatArr.length; i++) {
        var tempVla = parseInt(formatArr[i]) < 10 ? "0" + formatArr[i] : formatArr[i];
        formatStr = formatStr.replace(fStr.charAt(i), tempVla);
    }
    return formatStr;
}
//日期格式化:string字符串->long,/或-分隔年月日
function string2DateLong(datestr) {
    if (datestr == null || datestr.trim() == "")
        return null;
    var ddate = new Date((datestr).replace(new RegExp("-", "gm"), "/")).getTime()
    return ddate;
}
// loading(1);
//全局动画数组
var amIn = ["bounceIn", "bounceInDown", "bounceInLeft", "bounceInRight", "bounceInUp", "fadeIn", "fadeInDown", "fadeInDownBig", "fadeInLeft", "fadeInLeftBig", "fadeInRight", "fadeInRightBig", "fadeInUp", "fadeInUpBig", "flipInX", "flipInY", "lightSpeedIn", "rotateIn", "rotateInDownLeft", "rotateInDownRight", "rotateInUpLeft", "rotateInUpRight", "slideInDown", "slideInLeft", "slideInRight", "rollIn"];
var amOut = ["bounceOut", "bounceOutDown", "bounceOutLeft", "bounceOutRight", "bounceOutUp", "fadeOut", "fadeOutDown", "fadeOutDownBig", "fadeOutLeft", "fadeOutLeftBig", "fadeOutRight", "fadeOutRightBig", "fadeOutUp", "fadeOutUpBig", "flipOutX", "flipOutY", "lightSpeedOut", "rotateOut", "rotateOutDownLeft", "rotateOutDownRight", "rotateOutUpLeft", "rotateOutUpRight", "slideOutUp", "slideOutLeft", "slideOutRight", "hinge", "rollOut"];
var amStay = ["bounce", "flash", "pulse", "rubberBand", "shake", "swing", "tada", "wobble", "flip"];
$(function () {
    //指定块内容随机动画出现
    $(".zxxAnimateIn").each(function () {
        $(this).css("display", "none");
        var amrannum = Math.floor(Math.random() * amIn.length);
        $(this).fadeIn(100).addClass("animated " + amIn[amrannum]);
    });
    $(".zxxAnimateFadeIn").each(function () {
        $(this).css("display", "none");
        $(this).fadeIn(100).addClass("animated fadeInUp");
    });
})

window.onload = function () {
    // layer.closeAll();
    // parent.layer.closeAll();
}