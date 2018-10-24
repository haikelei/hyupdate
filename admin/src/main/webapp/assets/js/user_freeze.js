$(function () {
    /**
     * 检查登录态
     */
    checkLoginStatus();

    /**
     * 获取用户冻结列表
     */
    ajaxByGET("user/getMemberFreezeCount", {type: 0}, initDriversAuditPageCallback);
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

        td += "<td style='vertical-align: middle;'>" + timetrans(memberArray[i].createTime) + "</td>";
        let isDriver = memberArray[i].isDriver;
        let isFreeze = memberArray[i].isFreeze;
        let isFreezeContent = "";
        let isFreezeClass = "";
        if (isFreeze == 0) {
            //未冻结
            isFreezeContent = "未冻结！";
            isFreezeClass = "alert-success";
        }
        if (isFreeze == 1) {
            //已冻结
            isFreezeContent = "已冻结";
            isFreezeClass = "alert-danger";
        }

        td += "<td style='vertical-align: middle;'><div class='alert " + isFreezeClass + " alert-small alert-inline' role='alert'><strong>" + isFreezeContent + "</strong></div></td>";

        let memberId = memberArray[i].memberId;
        if (isFreeze == 1) {
            td += "<td style='vertical-align: middle;'><button type='button' class='btn btn-primary' onclick='refuseAuditDrivers(" + memberId + ")'>解冻</button></td>";
        } else {
            td += "<td style='vertical-align: middle;'><button type='button' class='btn btn-primary' onclick='acceptAuditDrivers(" + memberId + ")'>冻结</button></td>";
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
    ajaxByGET("user/listMemberFreeze", {
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
    ajaxByGET("user/getMemberFreezeCount", {type: type}, initDriversAuditPageCallback);
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

/*function picbigmodal(member){

 if(member.drivers == undefined || member.drivers == ""){
 console.info(1);
 $("#bigPicHand").attr("src","https://www.i-cw.net/upload/nopic.png");
 }else{
 $("#bigPicHand").attr("src","https://www.i-cw.net/upload/driverLicense/" + member.drivers);
 }

 $('#bigpicModal').modal('show')
 }
 */
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
function refuseAuditDrivers(memberId) {
    $.confirm("是否确定解冻该会员？", function () {
        //拒绝通过申请
        ajaxByGET("user/freezeMember", {memberId: memberId}, auditDriversCallback);
    })

}
/**
 * 同意
 * @returns
 */
function acceptAuditDrivers(memberId) {
    $.confirm("是否确定冻结该会员？", function () {
        s
        //通过申请
        ajaxByGET("user/freezeMember", {memberId: memberId}, auditDriversCallback);
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
        ajaxByGET("user/getMemberFreezeCount", {type: 0}, initDriversAuditPageCallback);
        closeDriversModal();
    });

}

