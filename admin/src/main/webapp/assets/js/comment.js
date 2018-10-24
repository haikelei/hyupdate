$(function () {
    /**
     * 检查登录态
     */
    //checkLoginStatus();

    /**
     * 获取所有管理员列表
     */
    ajaxByPOST("/hy/comment/findAllComment", {}, listAllPersonCallBack);


})





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
        var type = array[i].type == undefined ? " " : array[i].type;
        if(type==0){
            td += "<td style='vertical-align: middle;'>" + '动态评论' + "</td>";
        }else if(type==1){
            td += "<td style='vertical-align: middle;'>" + '专辑评论' + "</td>";
        }else {
            td += "<td style='vertical-align: middle;'>" + '' + "</td>";

        }
        var status = array[i].status == undefined ? " " : array[i].status;
        if(status==0){

            td += "<td style='vertical-align: middle;'>" + '开启中' + "</td>";
        }else {
            td += "<td style='vertical-align: middle;'>" + '关闭中' + "</td>";

        }
        if(status==0){

            td += "<td style='vertical-align: middle;'><button type='button' class='btn btn-primary' onclick='openComment(" + array[i].id + ")'>关闭评论</button></td>";
        }else {
            td += "<td style='vertical-align: middle;'><button type='button' class='btn btn-primary' onclick='closeComment(" + array[i].id + ")'>开启评论</button></td>";


        }


        table.append(td);
    }

}


function openComment(id) {

    ajaxByPOST("/hy/comment/openComment",{id:id,type:1},forSuccess)


}
function closeComment(id) {

    ajaxByPOST("/hy/comment/openComment",{id:id,type:0},forSuccess)


}


function updMessage(id) {

    window.location= "messageEdit.html?id="+id;
    
}

function addMessage() {

    window.location= "messageAdd.html";
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

