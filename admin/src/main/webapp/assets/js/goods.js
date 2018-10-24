$(function () {
    /**
     * 检查登录态
     */
    checkLoginStatus();

    /**
     * 获取所有轮播图列表
     */
    ajaxByPOST("user/findAllListGoods", {}, initGoodsListCallback);
})

function initGoodsListCallback(result) {
    let data = result.data;
    let table = $("#table");
    table.empty();
    for (let i = 0; i < data.length; i++) {
        $("#goodsId").val(data[i].id)
        let td = "<tr>";
        let name = data[i].name;
        td += "<td style='vertical-align: middle;'>" + name + "</td>";
        let phone = data[i].code;
        td += "<td style='vertical-align: middle;'>" + phone + "</td>";
        let id = data[i].id;
        td += "<td style='vertical-align: middle;'><button onclick='editPackage(" + data[i].id + ")' type='button' class='btn btn-primary'>编辑</button><button type='button' class='btn btn-primary' onclick='delGoods(" + id + ")'>删除</button></td>";

        table.append(td);
    }
}


function delGoods(id) {
    $.confirm("是否删除该商品名？", function () {
        //拒绝通过申请
        ajaxByPOST("user/delGoods", {id: id}, initGoodsListhahCallback);
    })

}


function initGoodsListhahCallback() {
    location.reload();
}

function addGoods() {
    $("#goodsAddModalLabel").html("添加货物");
}


function editPackage(id) {
    $("#goodsEditModalLabel").html("编辑货物");
    ajaxByPOST("goods/getGoodsById", {id: id}, editPackageCallback);
}

function editPackageCallback(result) {
    let packageObj = result.data;
    $("#editGoodsId").val(packageObj.id);
    $("#editGoodsName").val(packageObj.name);
    $("#editGoodsCode").val(packageObj.code);
    $("#packageEditModal").modal("show");
}

function editPackageSubmit() {
    let editGoodsObj = {
        id: $("#editGoodsId").val(),
        name: $("#editGoodsName").val(),
        code: $("#editGoodsCode").val()
    }
    ajaxByPOST("user/editGoods", editGoodsObj, editPackageSubmitCallback);
}

function editPackageSubmitCallback(result) {
    let errCode = result.errCode;
    if (errCode < 0) {
        $.alert(checkErrCode(errCode), function () {
        });
    } else {
        $.alert("编辑成功!", function () {
            location.reload();
        })
    }
}


function closePackageModal(type) {
    if (type == 1) {
        //新增关闭
        $("#packageAddModal").modal("hide");
    }

    if (type == 2) {
        $("#packageEditModal").modal("hide");
    }
}

function addPackageSubmit() {

    let name = $("#addGoodsName").val();
    let code = $("#addGoodsCode").val();

    if (name == "") {
        alert("货物名不能为空！")
        return;
    }
    if (code == "") {
        alet("排序值不能为空")
        return;
    }
    let addGoodsObj = {
        name: $("#addGoodsName").val(),
        code: $("#addGoodsCode").val()
    }


    ajaxByPOST("goods/addGoods", addGoodsObj, editPackageSubmitCallback);
}

