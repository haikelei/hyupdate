$(function () {
    /**
     * 检查登录态
     */
    checkLoginStatus();

    /**
     * 获取车主审核列表
     */
    ajaxByGET("user/getMemberAuditDriveringCount", {type: 0}, initDriveringAuditPageCallback);
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
function initDriveringAuditPageCallback(result) {
    let count = result.data.count;
    let page = Math.ceil(count / showNum);
    pagination(count, page, auditDriveringPageCallback);
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

        let driveringLicense = memberArray[i].driveringLicense;
        let member = {
            id: memberArray[i].memberId,
            idcardName: memberArray[i].idcardName,
            idcardNumber: memberArray[i].idcardNumber,
            drivering: memberArray[i].driveringLicense,
            nickname: memberArray[i].nickname
        };
        let jsonMember = JSON.stringify(member);

        let td = "<tr>";
        td += "<td style='vertical-align: middle;'>" + ((currentPage - 1) * 10 + i + 1) + "</td>";
        td += "<td style='vertical-align: middle;'>" + memberArray[i].nickname + "</td>";
        td += "<td style='vertical-align: middle;'>" + memberArray[i].phone + "</td>";


        td += "<td style='vertical-align: middle;'>" + memberArray[i].idcardName + "</td>";
        td += "<td style='vertical-align: middle;'>" + memberArray[i].idcardNumber + "</td>";

        if (driveringLicense == "" || driveringLicense == undefined) {
            td += "<td style='vertical-align: middle;'><img width='100px' src='https://www.i-cw.net/upload/nopic.png' /></td>";
        } else {
            td += "<td style='vertical-align: middle;'><a href='javascript:picbigmodal(" + jsonMember + ")'><img width='100px' src='https://www.i-cw.net/upload/drivingLicense/" + driveringLicense + "' /></a></td>";
        }
        td += "<td style='vertical-align: middle;'>" + timetrans(memberArray[i].createTime) + "</td>";
        let isCarOwner = memberArray[i].isCarOwner;
        let isCarOwnerContent = "";
        let isCarOwnerClass = "";
        if (isCarOwner == 1) {
            //审核成功
            isCarOwnerContent = "审核成功！";
            isCarOwnerClass = "alert-success";
        }

        if (isCarOwner == 2) {
            //审核失败
            isCarOwnerContent = "审核失败！";
            isCarOwnerClass = "alert-danger";
        }

        if (isCarOwner == 3) {
            //待上传
            isCarOwnerContent = "待上传！";
            isCarOwnerClass = "alert-warning";
        }

        if (isCarOwner == 0) {
            //待审核
            isCarOwnerContent = "待审核！";
            isCarOwnerClass = "alert-info";
        }
        td += "<td style='vertical-align: middle;'><div class='alert " + isCarOwnerClass + " alert-small alert-inline' role='alert'><strong>" + isCarOwnerContent + "</strong></div></td>";

        if (isCarOwner == 0) {
            td += "<td style='vertical-align: middle;'><button type='button' class='btn btn-primary' onclick='auditDriveringModel(" + jsonMember + ")'>审核</button></td>";
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
function auditDriveringPageCallback(page) {
    currentPage = page;
    ajaxByGET("user/listMemberAuditDrivering", {
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
    ajaxByGET("user/getMemberAuditDriveringCount", {type: type}, initDriveringAuditPageCallback);
}
/**
 * 审核
 * @returns
 */
function auditDriveringModel(member) {
    $("#memberId").val(member.id);
    $("#memberIdcardName").val(member.idcardName);
    $("#memberIdcarNumber").val(member.idcardNumber);
    $("#memberNickname").val(member.nickname);
    if (member.drivering == undefined || member.drivering == "") {
        $("#driveringLicense").attr("src", "https://www.i-cw.net/upload/nopic.png");
    } else {
        $("#driveringLicense").attr("src", "https://www.i-cw.net/upload/drivingLicense/" + member.drivering);
    }

    $('#driveringModal').modal('show');
}

function picbigmodal(member) {

    if (member.drivering == undefined || member.drivering == "") {
        console.info(1);
        $("#bigPicHand").attr("src", "https://www.i-cw.net/upload/nopic.png");
    } else {
        $("#bigPicHand").attr("src", "https://www.i-cw.net/upload/drivingLicense/" + member.drivering);
    }

    $('#bigpicModal').modal('show')
}

/**
 * 关闭模态框
 * @returns
 */
function closeDriveringModal() {
    $("#driveringModal").modal('hide');
}
/**
 * 拒绝
 * @returns
 */
function refuseAuditDrivering() {
    $.confirm("是否确定拒绝该会员车主审核？", function () {
        let memberId = $("#memberId").val();
        //拒绝通过申请
        ajaxByPOST("user/refuseAudit", {memberId: memberId, type: 2}, auditDriveringCallback);
    })

}
/**
 * 同意
 * @returns
 */
function acceptAuditDrivering() {
    $.confirm("是否确定通过该会员车主审核？", function () {
        let memberId = $("#memberId").val();
        //通过申请
        ajaxByPOST("user/acceptAudit", {memberId: memberId, type: 2}, auditDriveringCallback);
    })
}
/**
 * 回调函数
 * @param result
 * @returns
 */
function auditDriveringCallback(result) {
    let errCode = result.errCode;
    if (errCode < 0) {
        $.alert(checkErrCode(errCode) + "!", function () {
            closeDriversModal();
        });
        return false;
    }
    $.alert("操作成功！", function () {
        ajaxByGET("user/getMemberAuditDriveringCount", {type: 0}, initDriveringAuditPageCallback);
        closeDriveringModal();
    });

}

