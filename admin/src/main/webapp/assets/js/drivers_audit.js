$(function () {
    /**
     * 检查登录态
     */
    checkLoginStatus();

    /**
     * 获取车主审核列表
     */
    ajaxByGET("user/getMemberAuditDriversCount", {type: 0}, initDriversAuditPageCallback);
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
/**
 * 初始化分页控件
 * @param result
 * @returns
 */
function initDriversAuditPageCallback(result) {
    let count = result.data.count;
    let page = Math.ceil(count / showNum);
    pagination(count, page, auditDriversPageCallback);
    maxId = result.data.maxId > maxId ? result.data.maxId : maxId;
}
/**
 * 加载数据
 * @param result
 * @returns
 */
function listMemberAuditCallback(result) {
    memberArray = result.data;
    let table = $("#auditTable");
    table.empty();
    for (let i = 0; i < memberArray.length; i++) {
        let driversLicence = memberArray[i].driversLicence;
        let member = {
            id: memberArray[i].memberId,
            idcardName: memberArray[i].idcardName,
            idcardNumber: memberArray[i].idcardNumber,
            drivers: memberArray[i].driversLicence,
            nickname: memberArray[i].nickname
        };
        let jsonMember = JSON.stringify(member);

        let td = "<tr>";
        td += "<td style='vertical-align: middle;'>" + ((currentPage - 1) * 10 + i + 1) + "</td>";
        td += "<td style='vertical-align: middle;'>" + memberArray[i].nickname + "</td>";
        td += "<td style='vertical-align: middle;'>" + memberArray[i].phone + "</td>";

        td += "<td style='vertical-align: middle;'>" + memberArray[i].idcardName + "</td>";
        td += "<td style='vertical-align: middle;'>" + memberArray[i].idcardNumber + "</td>";

        if (driversLicence == "" || driversLicence == undefined) {
            td += "<td style='vertical-align: middle;'><img width='100px' src='https://www.i-cw.net/upload/nopic.png' /></td>";
        } else {
            td += "<td style='vertical-align: middle;'><a href='javascript:picbigmodal(" + jsonMember + ")'><img width='100px' src='https://www.i-cw.net/upload/driverLicense/" + driversLicence + "' /></a></td>";
        }
        td += "<td style='vertical-align: middle;'>" + timetrans(memberArray[i].createTime) + "</td>";
        let isDriver = memberArray[i].isDriver;
        let isDriverContent = "";
        let isDriverClass = "";
        if (isDriver == 1) {
            //审核成功
            isDriverContent = "审核成功！";
            isDriverClass = "alert-success";
        }

        if (isDriver == 2) {
            //审核失败
            isDriverContent = "审核失败！";
            isDriverClass = "alert-danger";
        }

        if (isDriver == 3) {
            //待上传
            isDriverContent = "待上传！";
            isDriverClass = "alert-warning";
        }

        if (isDriver == 0) {
            //待审核
            isDriverContent = "待审核！";
            isDriverClass = "alert-info";
        }
        td += "<td style='vertical-align: middle;'><div class='alert " + isDriverClass + " alert-small alert-inline' role='alert'><strong>" + isDriverContent + "</strong></div></td>";

        if (isDriver == 0) {
            td += "<td style='vertical-align: middle;'><button type='button' class='btn btn-primary' onclick='auditDriversModel(" + jsonMember + ")'>审核</button></td>";
        } else {
            td += "<td></td>";
        }
        table.append(td);
    }
}
/**
 * 分页控件按钮回调函数
 * @param page
 * @returns
 */
function auditDriversPageCallback(page) {
    currentPage = page;
    ajaxByGET("user/listMemberAuditDrivers", {
        offset: (page - 1) * showNum,
        limit: showNum,
        maxId: maxId,
        type: type
    }, listMemberAuditCallback);
}
/**
 * 类型选择
 * @param obj
 * @returns
 */
function auditTypeSelect(obj) {
    type = obj.value;
    ajaxByGET("user/getMemberAuditDriversCount", {type: type}, initDriversAuditPageCallback);
}
/**
 * 审核
 * @returns
 */
function auditDriversModel(member) {
    $("#memberId").val(member.id);
    $("#memberIdcardName").val(member.idcardName);
    $("#memberIdcarNumber").val(member.idcardNumber);
    $("#memberNickname").val(member.nickname);
    if (member.drivers == undefined || member.drivers == "") {
        $("#driversLicense").attr("src", "https://www.i-cw.net/upload/nopic.png");
    } else {
        $("#driversLicense").attr("src", "https://www.i-cw.net/upload/driverLicense/" + member.drivers);
    }

    $('#driversModal').modal('show');
}

function picbigmodal(member) {

    if (member.drivers == undefined || member.drivers == "") {
        console.info(1);
        $("#bigPicHand").attr("src", "https://www.i-cw.net/upload/nopic.png");
    } else {
        $("#bigPicHand").attr("src", "https://www.i-cw.net/upload/driverLicense/" + member.drivers);
    }

    $('#bigpicModal').modal('show')
}

/**
 * 关闭模态框
 * @returns
 */
function closeDriversModal() {
    $("#driversModal").modal('hide');
}
/**
 * 拒绝
 * @returns
 */
function refuseAuditDrivers() {
    $.confirm("是否确定拒绝该会员司机审核？", function () {
        let memberId = $("#memberId").val();
        //拒绝通过申请
        ajaxByPOST("user/refuseAudit", {memberId: memberId, type: 3}, auditDriversCallback);
    })

}
/**
 * 同意
 * @returns
 */
function acceptAuditDrivers() {
    $.confirm("是否确定通过该会员司机审核？", function () {
        let memberId = $("#memberId").val();
        //通过申请
        ajaxByPOST("user/acceptAudit", {memberId: memberId, type: 3}, auditDriversCallback);
    })
}
/**
 * 回调函数
 * @param result
 * @returns
 */
function auditDriversCallback(result) {
    let errCode = result.errCode;
    if (errCode < 0) {
        $.alert(checkErrCode(errCode) + "!", function () {
            closeDriversModal();
        });
        return false;
    }
    $.alert("操作成功！", function () {
        ajaxByGET("user/getMemberAuditDriversCount", {type: 0}, initDriversAuditPageCallback);
        closeDriversModal();
    });

}

