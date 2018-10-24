$(function () {
    /**
     * 检查登录态
     */
    checkLoginStatus();

    /**
     * 获取所有轮播图列表
     */
    ajaxByGET("user/getAllPackageCount", {}, initPackageListCallback);


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
function initPackageListCallback(result) {
    let count = result.data.count;
    let page = Math.ceil(count / showNum);
    pagination(count, page, PackagePageCallback);
    maxId = result.data.maxId > maxId ? result.data.maxId : maxId;
}

function PackagePageCallback(page) {
    currentPage = page;
    ajaxByGET("user/listAllPackage", {offset: (page - 1) * showNum, limit: showNum, maxId: maxId}, listAllPackCallback);
}

function listAllPackCallback(result) {
    packageArray = result.data;
    let table = $("#auditTable");
    table.empty();
    for (let i = 0; i < packageArray.length; i++) {
        let td = "<tr>";
        td += "<td style='vertical-align: middle;'>" + ((currentPage - 1) * 10 + i + 1) + "</td>";
        td += "<td style='vertical-align: middle;'>" + packageArray[i].name + "</td>";
        td += "<td style='vertical-align: middle;'>" + packageArray[i].price + "</td>";
        td += "<td style='vertical-align: middle;'>" + packageArray[i].duration + "</td>";
        td += "<td style='vertical-align: middle;'>" + timetrans(packageArray[i].createTime) + "</td>";
        td += "<td style='vertical-align: middle;'><button onclick='editPackage(" + packageArray[i].id + ")' type='button' class='btn btn-primary'>编辑</button> </td>";
        //<button onclick='deletePackageById(" + packageArray[i].id + ")' type='button' class='btn btn-danger'>删除</button>
        table.append(td);
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

function addPackage() {
    $("#packageEditModalLabel").html("新增套餐");
    $("#packageAddModal").modal("show");
}

function addPackageSubmit() {
    let packageObj = {
        name: $("#addPackageName").val(),
        price: $("#addPackagePrice").val(),
        duration: $("#addPackageDuration").val()
    }
    ajaxByPOST("user/addPackage", packageObj, addPackageSubmitCallback);
}

function addPackageSubmitCallback(result) {
    let errCode = result.errCode;
    if (errCode < 0) {
        $.alert(checkErrCode(errCode), function () {
        });
    } else {
        $.alert("新增成功！", function () {
            ajaxByGET("user/getAllPackageCount", {}, initPackageListCallback);
            $("#packageAddModal").modal("hide");
            $("#addPackageName").val("");
            $("#addPackagePrice").val("");
            $("#addPackageDuration").val("");
        })
    }
}

function deletePackageById(id) {
    $.confirm("你确认要删除这个套餐吗？", function () {
        ajaxByPOST("user/deletePackageById", {packageId: id}, deletePackageCallback);
    })
}

function deletePackageCallback(result) {
    let errCode = result.errCode;
    if (errCode < 0) {
        $.alert(checkErrCode(errCode), function () {
        });
    } else {
        $.alert("删除成功！", function () {
            ajaxByGET("user/getAllPackageCount", {}, initPackageListCallback);
        })
    }
}

function editPackage(id) {
    $("#packageEditModalLabel").html("编辑套餐");
    ajaxByGET("user/getPackageById", {packageId: id}, editPackageCallback);
}

function editPackageCallback(result) {
    let packageObj = result.data;
    $("#editPackageId").val(packageObj.id);
    $("#editPackageName").val(packageObj.name);
    $("#editPackagePrice").val(packageObj.price);
    $("#editPackageDuration").val(packageObj.duration);
    $("#packageEditModal").modal("show");
}

function editPackageSubmit() {
    let packageObj = {
        id: $("#editPackageId").val(),
        name: $("#editPackageName").val(),
        price: $("#editPackagePrice").val(),
        duration: $("#editPackageDuration").val()
    }
    ajaxByPOST("user/editPackage", packageObj, editPackageSubmitCallback);
}

function editPackageSubmitCallback(result) {
    let errCode = result.errCode;
    if (errCode < 0) {
        $.alert(checkErrCode(errCode), function () {
        });
    } else {
        $.alert("编辑成功!", function () {
            ajaxByGET("user/getAllPackageCount", {}, initPackageListCallback);
            $("#packageEditModal").modal("hide");
        })
    }
}
