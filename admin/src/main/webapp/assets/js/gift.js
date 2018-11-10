$(function () {
    /**
     * 检查登录态
     */
    //checkLoginStatus();

    /**
     * 获取所有管理员列表
     */
    ajaxByPOST("/hy/gift/findGiftCount", {}, initPersonCallback);


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
var showNum = 100;
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
    ajaxByPOST("/hy/gift/findGift", {start: page, limit: showNum}, listAllPersonCallBack);

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
        var giftName = array[i].giftName == undefined ? " " : array[i].giftName;
        td += "<td style='vertical-align: middle;'>" + giftName + "</td>";

        var url = array[i].url == undefined ? " " : imageProdUrl + array[i].url;
        td += "<td style='vertical-align: middle;'>"+"<img width='100px' src='" + url + "'  />" + "</td>";

        var defaultPrice = array[i].defaultPrice == undefined ? " " : array[i].defaultPrice;
        td += "<td style='vertical-align: middle;'>" + defaultPrice + "</td>";
        var defaultMoney = array[i].defaultMoney == undefined ? " " : array[i].defaultMoney;
        td += "<td style='vertical-align: middle;'>" + defaultMoney + "</td>";

        var price = array[i].price == undefined ? " " : array[i].price;
        td += "<td style='vertical-align: middle;'>" + price + "</td>";
        var money = array[i].money == undefined ? " " : array[i].money;
        td += "<td style='vertical-align: middle;'>" + money + "</td>";
        var exp = array[i].exp == undefined ? " " : array[i].exp;
        td += "<td style='vertical-align: middle;'>" + exp + "</td>";

        var code = array[i].code == undefined ? " " : array[i].code;
        td += "<td style='vertical-align: middle;'>" + code + "</td>";

        td += "<td style='vertical-align: middle;'>" + dateFormatUtil(array[i].createTime) + "</td>";

        td += "<td style='vertical-align: middle;'><button onclick='editGift(" + id + ")' type='button' class='btn btn-primary'>编辑</button><button type='button' class='btn btn-primary' onclick='delGift(" + array[i].id + ")'>删除</button></td>";


        table.append(td);
    }

}

function giftAddSubmit() {
    layer.open({
        type: 1,
        shade: false,
        title: '添加礼物  提示:礼物的华语币和金额的配置按照1：10的比例配置', //不显示标题
        area: ['700px', '500px'],
        content: $('.modal-add-body'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
    });

}


function addGiftSubmit() {
    var gift = {
        giftName: $("#addGiftName").val(),
        defaultPrice: $("#addGiftDefaultPrice").val(),
        defaultMoney: $("#addGiftDefaultMoney").val(),
        code: $("#addGiftCode").val(),
        price: $("#addGiftPrice").val(),
        money: $("#addGiftMoney").val(),
        exp: $("#addGiftExp").val(),
        url: $("#addGiftUrl").val()
    }
    ajaxByPOST("/hy/gift/addGift", gift, initAddGift);
}

function initAddGift(result) {
    if (result.code != 200) {
        alert(result.msg)
        return
    } else {
        alert("添加成功!")
    }
    location.reload();
}


function delGift(id) {
    ajaxByPOST("/hy/gift/delGift", {id: id}, initDelById)

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


function editGift(id) {

    layer.open({
        type: 1,
        shade: false,
        title: '修改礼物', //不显示标题
        area: ['700px', '500px'],
        content: $('.modal-eddit-body'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响

    });

    ajaxByPOST("/hy/gift/findGiftById", {id: id}, initFindById)

}


function initFindById(result) {
    $("#editGiftId").val(result.data.id)
    $("#edidGiftName").val(result.data.giftName);
    $("#editGiftCode").val(result.data.code);
    $("#editGiftDefaultPrice").val(result.data.defaultPrice);
    $("#editGiftDefaultMoney").val(result.data.defaultMoney);
    $("#editGiftPrice").val(result.data.price);
    $("#editGiftMoney").val(result.data.money);
    $("#editGiftExp").val(result.data.exp);
}


function edidGiftSubmit() {
    var gift = {
        id: $("#editGiftId").val(),
        giftName: $("#edidGiftName").val(),
        defaultPrice: $("#editGiftDefaultPrice").val(),
        defaultMoney: $("#editGiftDefaultMoney").val(),
        code: $("#editGiftCode").val(),
        price: $("#editGiftPrice").val(),
        money: $("#editGiftMoney").val(),
        exp: $("#editGiftExp").val(),
        url: $("#edidGiftUrl").val()

    }

    ajaxByPOST("/hy/gift/updGift", gift, initEditGift);

    ajaxByPOST("/hy/gift/findGiftCount", {}, initPersonCallback);

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


//修改图片
new AjaxUpload('#upload-btn', {
    action : '/hy/file/saveGiftFile',
    name : 'file',
    responseType : 'json',
    onSubmit : function(file, ext) {
        if (ext && /^(jpg|png|bmp|gif)$/.test(ext.toLowerCase())) {
            this.setData({
                'file' : file
            });
        } else {
            alert("请上传格式为 jpg|png|bmp|gif 的图片！");
            return false;
        }
    },
    onComplete : function(file, response) {
        if (response.error) {
            alert(response.error);
            return;
        }
        $("#imageAddId").attr(
            "src",
            imageProdUrl + response.data);
        $("#edidGiftUrl").val(response.data);
    }
});



//添加图片
new AjaxUpload('#upload-picture', {
    action : '/hy/file/saveGiftFile',
    name : 'file',
    responseType : 'json',
    onSubmit : function(file, ext) {
        if (ext && /^(jpg|png|bmp|gif)$/.test(ext.toLowerCase())) {
            this.setData({
                'file' : file
            });
        } else {
            alert("请上传格式为 jpg|png|bmp|gif 的图片！");
            return false;
        }
    },
    onComplete : function(file, response) {
        if (response.error) {
            alert(response.error);
            return;
        }
        $("#imageAdd").attr(
            "src",
            imageProdUrl + response.data);
        $("#addGiftUrl").val(response.data);
    }
});

