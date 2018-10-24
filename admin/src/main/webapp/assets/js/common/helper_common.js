/*-----------------------------------------utils function--------------------------------*/

/*打印方法
 * wyx 2017-03-27
 * */
/*支付*/
function printGetBill_showPay(data, obj) {
    console.info('支付：', data)
    $.each(data, function (i, item) {
        var method = "收款/";
        if (item.scene == "退款" || item.scene == "收款") {
            method = "";
        }
        obj.append(
            '<li style="width: 27%;float:left;border-bottom:1px solid #000;" >' +
            (getDateNoSS(item.createTime)) +
            '</li><li style="width: 5%;text-align:center;float:left;border-bottom:1px solid #000;">' +
            item.receptionPageNumber +
            '</li><li style="width: 42%;text-align:center;float:left;border-bottom:1px solid #000;">' +
            '' + method + item.scene + '/' + item.paymethodName +
            '</li><li style="width: 14%;text-align:center;float:left;border-bottom:1px solid #000;">' +
            '&nbsp;' +
            '</li><li style="width: 10%;text-align:center;float:left;border-bottom:1px solid #000;">' +
            NP.divide(item.amount, -100) +
            '</li>'
        );

    })
}
/*消费*/
function printGetBill_showConsume(data, obj, locks) {
    var check = "";
    $.each(data, function (i, item) {
        switch (item.checkinType) {
            case 1:
                check = "全日房";
                break;
            case 2:
                check = "晚房";
                break;
            case 3:
                chenc = "钟点房";
                break;
        }
        var X = "";
        if (locks.indexOf(item.receptionPageNumber) != -1) {
            X = '*';
        }
        if (item.feeItemType != 1) {
            obj.append(
                '<li style="width: 27%;float:left;border-bottom:1px solid #000;" >' +
                (getDateNoSS(item.accountTime)) +//时间格式只显示年月日
                '</li><li style="width: 5%;float:left;text-align:center;border-bottom:1px solid #000;">' +
                item.receptionPageNumber + X +
                '</li><li style="width: 42%;text-align:center;float:left;border-bottom:1px solid #000;">' +
                item.feeItemCode +
                '</li><li style="width:14%;float:left;text-align:center;border-bottom:1px solid #000;">' +
                item.number + '*' + NP.divide(item.salePrice, 100) +
                '</li><li style="width: 10%;float:left;text-align:center;border-bottom:1px solid #000;">' +
                NP.divide(item.amount, 100) +
                '</li>'
            );
        } else {
            obj.append(
                '<li style="width: 27%;float:left;border-bottom:1px solid #000;" >' +
                (getDateForHoliday(item.accountDate)) +//时间格式只显示年月日
                '</li><li style="width: 5%;float:left;text-align:center;border-bottom:1px solid #000;">' +
                item.receptionPageNumber + X +
                '</li><li style="width: 42%;text-align:center;float:left;border-bottom:1px solid #000;">' +
                item.feeItemCode + '/' + item.roomtypeName + '/' +
                item.roomCode + '/' + check +
                '</li><li style="width:14%;float:left;text-align:center;border-bottom:1px solid #000;">' +
                item.number + '*' + NP.divide(item.salePrice, 100) +
                '</li><li style="width: 10%;float:left;text-align:center;border-bottom:1px solid #000;">' +
                NP.divide(item.amount, 100) +
                '</li>'
            );
        }
    })
}
/*签单*/
function printGetBill_showBill(data, obj) {
    $.each(data, function (i, item) {
        if (item.amount < 0) {
            item.paymethodName = "撤销签单";
        }
        obj.append(
            '<li style="width: 27%;float:left;border-bottom:1px solid #000;" >' +
            (getDateNoSS(item.createTime)) +//时间格式只显示年月日
            '</li><li style="width: 5%;float:left;text-align:center;border-bottom:1px solid #000;">' +
            item.receptionPageNumber + '*' +
            '</li><li style="width: 42%;text-align:center;float:left;border-bottom:1px solid #000;">' +
            item.paymethodName + '/' + item.creaditChannelName +
            '</li><li style="width:14%;float:left;text-align:center;border-bottom:1px solid #000;">' +
            '&nbsp;' +
            '</li><li style="width: 10%;float:left;text-align:center;border-bottom:1px solid #000;">' +
            item.amount / -100 +
            '</li>'
        );
    })
};
//坏账
function printGetBill_showBebt(data, obj) {
    $.each(data, function (i, item) {
        if (item.amount < 0) {
            item.paymethodName = "撤销坏账";
        }
        obj.append(
            '<li style="width: 27%;float:left;border-bottom:1px solid #000;" >' +
            (getDateNoSS(item.createTime)) +
            '</li><li style="width: 5%;float:left;text-align:center;border-bottom:1px solid #000;">' +
            item.receptionPageNumber + '*' +
            '</li><li style="width: 42%;text-align:center;float:left;border-bottom:1px solid #000;">' +
            item.paymethodName +
            '</li><li style="width:14%;float:left;text-align:center;border-bottom:1px solid #000;">' +
            '&nbsp;' +
            '</li><li style="width: 10%;float:left;text-align:center;border-bottom:1px solid #000;">' +
            NP.divide(item.amount, 100) +
            '</li>'
        );
    })
};
//免单
function printGetBill_showFree(data, obj) {
    $.each(data, function (i, item) {
        if (item.amount < 0) {
            item.paymethodName = "撤销免单";
        }
        obj.append(
            '<li style="width: 27%;float:left;border-bottom:1px solid #000;" >' +
            (getDateNoSS(item.createTime)) +
            '</li><li style="width: 5%;float:left;text-align:center;border-bottom:1px solid #000;">' +
            item.receptionPageNumber + '*' +
            '</li><li style="width: 42%;text-align:center;float:left;border-bottom:1px solid #000;">' +
            item.paymethodName +
            '</li><li style="width:14%;float:left;text-align:center;border-bottom:1px solid #000;">' +
            '&nbsp;' +
            '</li><li style="width: 10%;float:left;text-align:center;border-bottom:1px solid #000;">' +
            NP.divide(item.amount, 100) +
            '</li>'
        );
    })
};
//转移
function printGetBill_showTranfer(data, obj) {
    $.each(data, function (i, item) {
        if (item.cancelTransfer == 1 && item.type == 6) {
            item.paymethodName = "撤销转出";
            obj.append(
                '<li style="width: 27%;float:left;border-bottom:1px solid #000;" >' +
                (getDateNoSS(item.date)) +
                '</li><li style="width: 5%;float:left;text-align:center;border-bottom:1px solid #000;">' +
                item.folio + '*' +
                '</li><li style="width: 42%;text-align:center;float:left;border-bottom:1px solid #000;">' +
                item.paymethodName +
                '</li><li style="width:14%;float:left;text-align:center;border-bottom:1px solid #000;">' +
                '&nbsp;' +
                '</li><li style="width: 10%;float:left;text-align:center;border-bottom:1px solid #000;">' +
                NP.divide(item.money, 100) +
                '</li>'
            );
        }
        if (item.cancelTransfer == 0 && item.type == 6) {
            item.paymethodName = "转出";
            obj.append(
                '<li style="width: 27%;float:left;border-bottom:1px solid #000;" >' +
                (getDateNoSS(item.date)) +
                '</li><li style="width: 5%;float:left;text-align:center;border-bottom:1px solid #000;">' +
                item.folio + '*' +
                '</li><li style="width: 42%;text-align:center;float:left;border-bottom:1px solid #000;">' +
                item.paymethodName +
                '</li><li style="width:14%;float:left;text-align:center;border-bottom:1px solid #000;">' +
                '&nbsp;' +
                '</li><li style="width: 10%;float:left;text-align:center;border-bottom:1px solid #000;">' +
                item.money / -100 +
                '</li>'
            );
        }
        if (item.cancelTransfer == 1 && item.type == 7) {
            item.paymethodName = "撤销转入";
            obj.append(
                '<li style="width: 27%;float:left;border-bottom:1px solid #000;" >' +
                (getDateNoSS(item.date)) +
                '</li><li style="width: 5%;float:left;text-align:center;border-bottom:1px solid #000;">' +
                item.folio + '*' +
                '</li><li style="width: 42%;text-align:center;float:left;border-bottom:1px solid #000;">' +
                item.paymethodName +
                '</li><li style="width:14%;float:left;text-align:center;border-bottom:1px solid #000;">' +
                '&nbsp;' +
                '</li><li style="width: 10%;float:left;text-align:center;border-bottom:1px solid #000;">' +
                NP.divide(item.money, 100) +
                '</li>'
            );
        }
        if (item.cancelTransfer == 0 && item.type == 7) {
            item.paymethodName = "转入";
            if (item.consumeTypeId == 1) {//房型消费 时间显示年月日
                obj.append(
                    '<li style="width: 27%;float:left;border-bottom:1px solid #000;" >' +
                    (getDateForHoliday(item.date)) +
                    '</li><li style="width: 5%;float:left;text-align:center;border-bottom:1px solid #000;">' +
                    item.folio + '*' +
                    '</li><li style="width: 42%;text-align:center;float:left;border-bottom:1px solid #000;">' +
                    item.paymethodName + '/' + item.consumeTypeName + '/' +
                    item.roomCode + '/' + item.roomtypeName +
                    '</li><li style="width:14%;float:left;text-align:center;border-bottom:1px solid #000;">' +
                    item.number + '*' + NP.divide(item.price, 100) +
                    '</li><li style="width: 10%;float:left;text-align:center;border-bottom:1px solid #000;">' +
                    NP.divide(item.money, 100) +
                    '</li>'
                );
            } else {
                obj.append(//其他消费时间显示年月日时分
                    '<li style="width: 27%;float:left;border-bottom:1px solid #000;" >' +
                    (getDateNoSS(item.date)) +
                    '</li><li style="width: 5%;float:left;text-align:center;border-bottom:1px solid #000;">' +
                    item.folio + '*' +
                    '</li><li style="width: 42%;text-align:center;float:left;border-bottom:1px solid #000;">' +
                    item.paymethodName + '/' + item.consumeTypeName +
                    '</li><li style="width:14%;float:left;text-align:center;border-bottom:1px solid #000;">' +
                    item.number + '*' + NP.divide(item.price, 100) +
                    '</li><li style="width: 10%;float:left;text-align:center;border-bottom:1px solid #000;">' +
                    NP.divide(item.money, 100) +
                    '</li>'
                );
            }
        }

    })
};
function printGetBill_show(data) {
    /*ul*/
    var payDetailsTr = $('#payDetails_printGetBill');
    payDetailsTr.text("");
    var consumeDetailsTr = $('#consumeDetails_printGetBill');
    consumeDetailsTr.text("");
    var billDetailsTr = $("#billDetails_printGetBill");
    billDetailsTr.text("");
    var debtDetailsTr = $("#debtDetails_printGetBill");
    debtDetailsTr.text("");
    var freeDetailsTr = $("#freeDetails_printGetBill");
    freeDetailsTr.text("");
    var tranferDetailsTr = $("#tranferDetails_printGetBill");
    tranferDetailsTr.text("");

    if (data.paymentList && data.paymentList.length != 0) {
        $('#payDetails_printGetBill').show();
    } else {
        $('#payDetails_printGetBill').hide();
    }
    if (data.consumeList && data.consumeList.length != 0) {
        $('#consumeDetails_printGetBill').show();
    } else {
        $('#consumeDetails_printGetBill').hide();
    }

    if (data.billList && data.billList.length != 0) {
        $('#billDetails_printGetBill').show();
    } else {
        $('#billDetails_printGetBill').hide();
    }
    if (data.debtList && data.debtList.length != 0) {
        $('#debtDetails_printGetBill').show();
    } else {
        $('#debtDetails_printGetBill').hide();
    }
    if (data.freeList && data.freeList.length != 0) {
        $('#freeDetails_printGetBill').show();
    } else {
        $('#freeDetails_printGetBill').hide();
    }
    if (data.tranferList && data.tranferList.length != 0) {
        $('#tranferDetails_printGetBill').show();
    } else {
        $('#tranferDetails_printGetBill').hide();
    }

    //支付
    printGetBill_showPay(data.paymentList, payDetailsTr);
    //消费
    printGetBill_showConsume(data.consumeList, consumeDetailsTr, data.locks);
    //签单
    printGetBill_showBill(data.billList, billDetailsTr);
    //坏账
    printGetBill_showBebt(data.debtList, debtDetailsTr);
    //免单
    printGetBill_showFree(data.freeList, freeDetailsTr);
    //转移
    printGetBill_showTranfer(data.tranferList, tranferDetailsTr);

}
/**
 * 名称：打印宾客账单JS
 * 创建时间：2017年3月6日17:29:49
 * 创建人：cxt
 * 传入的参数为 通过 该打印接口 ：url:"../print/printReception",
 传入 data:{receptionId,page,roomId,guestId},的result

 使用时：pfunction（receptionId,page,roomId,guestId）;
 */
var pfunction = function (data_receptionId, page, roomId, guestId) {//参数data_receptionId为receptionId
    console.info(data_receptionId);
    console.info(roomId);
    console.info(guestId);
    if (!data_receptionId) {
        $.messager.show({title: '系统提示', msg: '参数错误！', timeout: 2000, showType: 'slide'});
        return;
    }
    $.ajax({
        type: "post",
        url: "../print/printReception",
        data: {receptionId: data_receptionId, roomId: roomId, guestId: guestId},
        //async:false,
        dataType: "json"
    })
        .done(function (ajaxResult) {
            if (!ajaxResult) {
                $.messager.show({title: '系统提示', msg: '参数有误！', timeout: 2000, showType: 'slide'});
                return;
            }
            var savePDFName = printGetBill(JSON.stringify(ajaxResult), page);
            //popTitle:savePDFName 为给打印显示页面 点击保存PDF文件时候， 预先设置浏览器title 即为PDF文件名称
            $("div#print").printArea({popTitle: '宾客账单:__' + savePDFName, mode: 'popup'});// mode:'popup' // 弹出新的网页
        })
        .fail(function (result, v, o) {
            console.info(result);
            console.info(v);
            console.info(o);
        });
}
var printGetBill = function (result, page) {
    var data = JSON.parse(result);
    $('#hotelName_printGetBill').text(data.hotelName);//酒店名称
    /*if(data.guestName != undefined && data.guestName != "" && data.guestName != null){
     var nameArr = data.guestName.split(";");
     }*/
    $('#guestName_printGetBill').text(data.guestName);//宾客姓名

    $('#phone_printGetBill').text(data.phone);//手机
    $('#channelName_printGetBill').text(data.channelName);//客源
    console.info(data)
    console.info(data.consumeList)
    if (data.consumeList.length == 0) {
        $('#receptionCode_printGetBill').text("");//宾客账单
    } else {
        $('#receptionCode_printGetBill').text(data.consumeList[0].receptionCode);//宾客账单
    }
    $('#printTime_printGetBill').text(getDate(new Date().getTime()));//打印日期

    if (data.enterTime) {
        $('#enterTime_printGetBill').text(data.enterTime);//登记时间
    } else {
        $('#enterTime_printGetBill').text("--");
    }

    if (data.checkoutTime) {
        $('#leaveTime_printGetBill').text(data.checkoutTime);//结账时间
    } else {
        $('#leaveTime_printGetBill').text("--");
    }
    $('#roomtypeName_printGetBill').text(data.roomtypeName);//房型list
    $('#roomCode_printGetBill').text(data.roomCode);//房号list

    $('#pay_printGetBill').text(NP.divide(data.pay, 100) + "元");//支付合计
    $('#consume_printGetBill').text(NP.divide(data.consume, 100) + "元");//消费合计
    $("#bill_printGetBill").text(NP.divide(data.bill, 100) + "元");//签单合计
    //总消费consume-总支付pay-签单bill-免单free-坏账debt-transfer[transferOut(转出)+transferIn(转入)]
    var transfer = NP.plus(-data.out, data.in);
    var amountResult = (NP.divide(data.consume, 100) - NP.divide(data.pay, 100) - NP.divide(data.bill, 100)
    - NP.divide(data.free, 100) - NP.divide(data.debt, 100) - NP.divide(transfer, 100)).toFixed(2);
    $('#printBalance_printGetBill').text(amountResult + "元");//余额

    $('#handler_printGetBill').text(data.handler);//接待员

    var folios = {};
    /*//根据账页显示明细*/
    folios.locks = data.locks;
    if (page == 0) {
        //所有帐页
        folios.paymentList = data.paymentList;
        folios.consumeList = data.consumeList;
        folios.billList = data.billList;
        folios.debtList = data.debtList;
        folios.freeList = data.freeList;
        folios.tranferList = data.tranferList;
        printGetBill_show(folios);
    } else {
        //支付
        $.each(data.paymentList, function (i, item) {
            if (item.receptionPageNumber == page && item.amount != 0) {
                folios.paymentList = data.paymentList;
                return;
            }
        })
        //消费
        $.each(data.consumeList, function (i, item) {
            if (item.receptionPageNumber == page) {
                folios.consumeList = data.consumeList;
                return;
            }
        })
        //签单
        $.each(data.billList, function (i, item) {
            if (item.receptionPageNumber == page) {
                folios.billList = data.billList;
                return;
            }
        })
        //坏账
        $.each(data.debtList, function (i, item) {
            if (item.receptionPageNumber == page) {
                folios.debtList = data.debtList;
                return;
            }
        })
        //免单
        $.each(data.freeList, function (i, item) {
            if (item.receptionPageNumber == page) {
                folios.freeList = data.freeList;
                return;
            }
        })
        //转出
        $.each(data.tranferList, function (i, item) {
            if (item.receptionPageNumber == page) {
                folios.tranferList = data.tranferList;
                return;
            }
        })
        printGetBill_show(folios);
    }
    if (data.consumeList.length == 0) {
        return "";
    } else {
        return data.consumeList[0].receptionCode;
    }
}
/***********************小票打印*********************************************/
function pfunctionSmall(data_receptionId, page, roomId, guestId) {
    console.info(data_receptionId);
    console.info(roomId);
    console.info(guestId);
    if (!data_receptionId) {
        $.messager.show({title: '系统提示', msg: '参数错误！', timeout: 3000, showType: 'slide'});
        return;
    }
    $.ajax({
        type: "post",
        url: "../print/printReception",
        data: {receptionId: data_receptionId, roomId: roomId, guestId: guestId},
        dataType: "json"
    })
        .done(function (ajaxResult) {
            console.info(ajaxResult)
            if (!ajaxResult) {
                $.messager.show({title: '系统提示', msg: '参数有误！', timeout: 2000, showType: 'slide'});
                return;
            }
            var savePDFName = printGetBillSmall(JSON.stringify(ajaxResult), page);
            //popTitle:savePDFName 为给打印显示页面 点击保存PDF文件时候， 预先设置浏览器title 即为PDF文件名称
            $("div#printSmall").printArea({popTitle: '宾客账单:__' + savePDFName, mode: 'popup'});// mode:'popup' // 弹出新的网页
        })
        .fail(function (result, v, o) {
            console.info(result);
            console.info(v);
            console.info(o);
        });
}
function printGetBillSmall(result, page, type) {
    type = 'Small';
    var data = JSON.parse(result);
    console.info(data.hotelName)
    $('#hotelName_printGetBill' + type).text(data.hotelName);//酒店名称
    console.info($('#hotelName_printGetBill' + type))
    /*if(data.guestName != undefined && data.guestName != "" && data.guestName != null){
     var nameArr = data.guestName.split(";");
     }*/
    $('#guestName_printGetBill' + type).text(data.guestName);//宾客姓名

    $('#phone_printGetBill' + type).text(data.phone);//手机
    $('#channelName_printGetBill' + type).text(data.channelName);//客源
    console.info(data)
    console.info(data.consumeList)
    if (data.consumeList.length == 0) {
        $('#receptionCode_printGetBill' + type).text("");//宾客账单
    } else {
        $('#receptionCode_printGetBill' + type).text(data.consumeList[0].receptionCode);//宾客账单
    }
    $('#printTime_printGetBill' + type).text(getDate(new Date().getTime()));//打印日期

    if (data.enterTime) {
        $('#enterTime_printGetBill' + type).text(data.enterTime);//登记时间
    } else {
        $('#enterTime_printGetBill' + type).text("--");
    }

    if (data.checkoutTime) {
        $('#leaveTime_printGetBill' + type).text(data.checkoutTime);//结账时间
    } else {
        $('#leaveTime_printGetBill' + type).text("--");
    }
    $('#roomtypeName_printGetBill' + type).text(data.roomtypeName);//房型list
    $('#roomCode_printGetBill' + type).text(data.roomCode);//房号list

    $('#pay_printGetBill' + type).text(NP.divide(data.pay, 100) + "元");//支付合计
    $('#consume_printGetBill' + type).text(NP.divide(data.consume, 100) + "元");//消费合计
    $("#bill_printGetBill" + type).text(NP.divide(data.bill, 100) + "元");//签单合计
    //总消费consume-总支付pay-签单bill-免单free-坏账debt-transfer[transferOut(转出)+transferIn(转入)]
    var transfer = NP.plus(-data.out, data.in);
    var amountResult = (NP.divide(data.consume, 100) - NP.divide(data.pay, 100) - NP.divide(data.bill, 100)
    - NP.divide(data.free, 100) - NP.divide(data.debt, 100) - NP.divide(transfer, 100)).toFixed(2);
    $('#printBalance_printGetBill' + type).text(amountResult + "元");//余额

    $('#handler_printGetBill' + type).text(data.handler);//接待员

    // ***************************************
    $('#content_printGetBillSmall').append()
    var folios = {
        locks: data.locks
    };
    console.info('page:', page);
    /*//根据账页显示明细*/
    if (page == 0) {
        //所有帐页
        folios.paymentList = data.paymentList;
        folios.consumeList = data.consumeList;
        folios.billList = data.billList;
        folios.debtList = data.debtList;
        folios.freeList = data.freeList;
        folios.tranferList = data.tranferList;
        printGetBill_show(folios);
    } else {
        //支付
        $.each(data.paymentList, function (i, item) {
            if (item.receptionPageNumber == page && item.amount != 0) {
                folios.paymentList = data.paymentList;
                return;
            }
        })
        //消费
        $.each(data.consumeList, function (i, item) {
            if (item.receptionPageNumber == page) {
                folios.consumeList = data.consumeList;
                return;
            }
        })
        //签单
        $.each(data.billList, function (i, item) {
            if (item.receptionPageNumber == page) {
                folios.billList = data.billList;
                return;
            }
        })
        //坏账
        $.each(data.debtList, function (i, item) {
            if (item.receptionPageNumber == page) {
                folios.debtList = data.debtList;
                return;
            }
        })
        //免单
        $.each(data.freeList, function (i, item) {
            if (item.receptionPageNumber == page) {
                folios.freeList = data.freeList;
                return;
            }
        })
        //转出
        $.each(data.tranferList, function (i, item) {
            if (item.receptionPageNumber == page) {
                folios.tranferList = data.tranferList;
                return;
            }
        })
        printGetBill_show(folios);
    }
    if (data.consumeList.length == 0) {
        return "";
    } else {
        return data.consumeList[0].receptionCode;
    }
    /*支付*/
    function printGetBill_showPay(data, obj) {
        console.info('支付：', data)
        $.each(data, function (i, item) {
            var method = "收款/";
            if (item.scene == "退款" || item.scene == "收款") {
                method = "";
            }
            obj.append(
                // '<li style="width: 27%;float:left;border-bottom:1px solid #000;" >' +
                // 	(getDateNoSS(item.createTime)) +
                // '</li>'+
                // '<li style="width: 5%;text-align:center;float:left;border-bottom:1px solid #000;">' +
                // 	item.receptionPageNumber +
                // '</li>'+
                '<li style="width:50%;text-align:center;border-bottom:1px solid #000;list-style:none;display:inline-block;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
                '' + method + item.scene + '/' + item.paymethodName +
                '</li><li style="width:30%;text-align:center;border-bottom:1px solid #000;list-style:none;display:inline-block;">' +
                '&nbsp;' +
                '</li><li style="width:20%;text-align:left;border-bottom:1px solid #000;list-style:none;display:inline-block;">' +
                NP.divide(item.amount, -100) +
                '</li>'
            );

        })
    }

    /*消费*/
    function printGetBill_showConsume(data, obj, locks) {
        var check = "";
        $.each(data, function (i, item) {
            switch (item.checkinType) {
                case 1:
                    check = "全日房";
                    break;
                case 2:
                    check = "晚房";
                    break;
                case 3:
                    chenc = "钟点房";
                    break;
            }
            var X = "";
            if (locks.indexOf(item.receptionPageNumber) != -1) {
                X = '*';
            }
            if (item.feeItemType != 1) {
                obj.append(
                    //  '<li style="width: 27%;float:left;border-bottom:1px solid #000;" >'+
                    //  (getDateNoSS(item.accountTime))+//时间格式只显示年月日
                    //  '</li><li style="width: 5%;float:left;text-align:center;border-bottom:1px solid #000;">'+
                    //  item.receptionPageNumber+ X +
                    //  '</li>'+
                    '<li style="width:50%;text-align:center;border-bottom:1px solid #000;list-style:none;display:inline-block;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
                    item.feeItemCode +
                    '</li><li style="width:30%;text-align:center;border-bottom:1px solid #000;list-style:none;display:inline-block;">' +
                    item.number + '*' + NP.divide(item.salePrice, 100) +
                    '</li><li style="width:20%;text-align:left;border-bottom:1px solid #000;list-style:none;display:inline-block;">' +
                    NP.divide(item.amount, 100) +
                    '</li>'
                );
            } else {
                obj.append(
                    // '<li style="width: 27%;float:left;border-bottom:1px solid #000;" >'+
                    // (getDateForHoliday(item.accountDate))+//时间格式只显示年月日
                    // '</li><li style="width: 5%;float:left;text-align:center;border-bottom:1px solid #000;">'+
                    // item.receptionPageNumber+ X +
                    // '</li>'+
                    '<li style="list-style:none;display:inline-block;width:50%;text-align:center;border-bottom:1px solid #000;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
                    item.feeItemCode + '/' + item.roomtypeName + '/' +
                    item.roomCode + '/' + check +
                    '</li><li style="list-style:none;display:inline-block;width:30%;text-align:center;border-bottom:1px solid #000;">' +
                    item.number + '*' + NP.divide(item.salePrice, 100) +
                    '</li><li style="list-style:none;display:inline-block;width:20%;text-align:left;border-bottom:1px solid #000;">' +
                    NP.divide(item.amount, 100) +
                    '</li>'
                );
            }
        })
    }

    /*签单*/
    function printGetBill_showBill(data, obj) {
        $.each(data, function (i, item) {
            if (item.amount < 0) {
                item.paymethodName = "撤销签单";
            }
            obj.append(
                // '<li style="width: 27%;float:left;border-bottom:1px solid #000;" >'+
                // 	(getDateNoSS(item.createTime))+//时间格式只显示年月日
                // '</li><li style="width: 5%;float:left;text-align:center;border-bottom:1px solid #000;">'+
                // item.receptionPageNumber+'*'+
                // '</li>'+
                '<li style="list-style:none;display:inline-block;width:50%;text-align:center;border-bottom:1px solid #000;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
                item.paymethodName + '/' + item.creaditChannelName +
                '</li><li style="list-style:none;display:inline-block;width:30%;text-align:center;border-bottom:1px solid #000;">' +
                '&nbsp;' +
                '</li><li style="list-style:none;display:inline-block;width:20%;text-align:left;border-bottom:1px solid #000;">' +
                item.amount / -100 +
                '</li>'
            );
        })
    };
    //坏账
    function printGetBill_showBebt(data, obj) {
        $.each(data, function (i, item) {
            if (item.amount < 0) {
                item.paymethodName = "撤销坏账";
            }
            obj.append(
                // '<li style="width: 27%;float:left;border-bottom:1px solid #000;" >'+
                // 	(getDateNoSS(item.createTime))+
                // '</li><li style="width: 5%;float:left;text-align:center;border-bottom:1px solid #000;">'+
                // item.receptionPageNumber+'*'+
                // '</li>'+
                '<li style="list-style:none;display:inline-block;width:50%;text-align:center;border-bottom:1px solid #000;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
                item.paymethodName +
                '</li><li style="list-style:none;display:inline-block;width:30%;text-align:center;border-bottom:1px solid #000;">' +
                '&nbsp;' +
                '</li><li style="list-style:none;display:inline-block;width:20%;text-align:left;border-bottom:1px solid #000;">' +
                NP.divide(item.amount, 100) +
                '</li>'
            );
        })
    };
    //免单
    function printGetBill_showFree(data, obj) {
        $.each(data, function (i, item) {
            if (item.amount < 0) {
                item.paymethodName = "撤销免单";
            }
            obj.append(
                // '<li style="width: 27%;float:left;border-bottom:1px solid #000;" >'+
                // 	(getDateNoSS(item.createTime))+
                // '</li><li style="width: 5%;float:left;text-align:center;border-bottom:1px solid #000;">'+
                // item.receptionPageNumber+'*'+
                // '</li>'+
                '<li style="list-style:none;display:inline-block;width:50%;text-align:center;border-bottom:1px solid #000;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
                item.paymethodName +
                '</li><li style="list-style:none;display:inline-block;width:30%;text-align:center;border-bottom:1px solid #000;">' +
                '&nbsp;' +
                '</li><li style="list-style:none;display:inline-block;width:20%;text-align:left;border-bottom:1px solid #000;">' +
                NP.divide(item.amount, 100) +
                '</li>'
            );
        })
    };
    //转移
    function printGetBill_showTranfer(data, obj) {
        $.each(data, function (i, item) {
            if (item.cancelTransfer == 1 && item.type == 6) {
                item.paymethodName = "撤销转出";
                obj.append(
                    // '<li style="width: 27%;float:left;border-bottom:1px solid #000;" >'+
                    // (getDateNoSS(item.date))+
                    // '</li><li style="width: 5%;float:left;text-align:center;border-bottom:1px solid #000;">'+
                    // item.folio+'*'+
                    // '</li>'+
                    '<li style="list-style:none;display:inline-block;width:50%;text-align:center;border-bottom:1px solid #000;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
                    item.paymethodName +
                    '</li><li style="list-style:none;display:inline-block;width:30%;text-align:center;border-bottom:1px solid #000;">' +
                    '&nbsp;' +
                    '</li><li style="list-style:none;display:inline-block;width:20%;text-align:left;border-bottom:1px solid #000;">' +
                    NP.divide(item.money, 100) +
                    '</li>'
                );
            }
            if (item.cancelTransfer == 0 && item.type == 6) {
                item.paymethodName = "转出";
                obj.append(
                    // '<li style="width: 27%;float:left;border-bottom:1px solid #000;" >'+
                    // (getDateNoSS(item.date))+
                    // '</li><li style="width: 5%;float:left;text-align:center;border-bottom:1px solid #000;">'+
                    // item.folio+'*'+
                    // '</li>'+
                    '<li style="list-style:none;display:inline-block;width:50%;text-align:center;border-bottom:1px solid #000;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
                    item.paymethodName +
                    '</li><li style="list-style:none;display:inline-block;width:30%;text-align:center;border-bottom:1px solid #000;">' +
                    '&nbsp;' +
                    '</li><li style="list-style:none;display:inline-block;width:20%;text-align:left;border-bottom:1px solid #000;">' +
                    item.money / -100 +
                    '</li>'
                );
            }
            if (item.cancelTransfer == 1 && item.type == 7) {
                item.paymethodName = "撤销转入";
                obj.append(
                    // '<li style="width: 27%;float:left;border-bottom:1px solid #000;" >'+
                    // (getDateNoSS(item.date))+
                    // '</li><li style="width: 5%;float:left;text-align:center;border-bottom:1px solid #000;">'+
                    // item.folio+'*'+
                    // '</li>'+
                    '<li style="list-style:none;display:inline-block;width:50%;text-align:center;border-bottom:1px solid #000;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
                    item.paymethodName +
                    '</li><li style="list-style:none;display:inline-block;width:30%;text-align:center;border-bottom:1px solid #000;">' +
                    '&nbsp;' +
                    '</li><li style="list-style:none;display:inline-block;width:20%;text-align:left;border-bottom:1px solid #000;">' +
                    NP.divide(item.money, 100) +
                    '</li>'
                );
            }
            if (item.cancelTransfer == 0 && item.type == 7) {
                item.paymethodName = "转入";
                if (item.consumeTypeId == 1) {//房型消费 时间显示年月日
                    obj.append(
                        // '<li style="width: 27%;float:left;border-bottom:1px solid #000;" >'+
                        // (getDateForHoliday(item.date))+
                        // '</li><li style="width: 5%;float:left;text-align:center;border-bottom:1px solid #000;">'+
                        // item.folio+'*'+
                        // '</li>'+
                        '<li style="list-style:none;display:inline-block;width:50%;text-align:center;border-bottom:1px solid #000;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
                        item.paymethodName + '/' + item.consumeTypeName + '/' +
                        item.roomCode + '/' + item.roomtypeName +
                        '</li><li style="list-style:none;display:inline-block;width:30%;text-align:center;border-bottom:1px solid #000;">' +
                        item.number + '*' + NP.divide(item.price, 100) +
                        '</li><li style="list-style:none;display:inline-block;width:20%;text-align:left;border-bottom:1px solid #000;">' +
                        NP.divide(item.money, 100) +
                        '</li>'
                    );
                } else {
                    obj.append(//其他消费时间显示年月日时分
                        // '<li style="width: 27%;float:left;border-bottom:1px solid #000;" >'+
                        // (getDateNoSS(item.date))+
                        // '</li><li style="width: 5%;float:left;text-align:center;border-bottom:1px solid #000;">'+
                        // item.folio+'*'+
                        // '</li>'+
                        '<li style="list-style:none;display:inline-block;width:50%;text-align:center;border-bottom:1px solid #000;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
                        item.paymethodName + '/' + item.consumeTypeName +
                        '</li><li style="list-style:none;display:inline-block;width:30%;text-align:center;border-bottom:1px solid #000;">' +
                        item.number + '*' + NP.divide(item.price, 100) +
                        '</li><li style="list-style:none;display:inline-block;width:20%;text-align:left;border-bottom:1px solid #000;">' +
                        NP.divide(item.money, 100) +
                        '</li>'
                    );
                }
            }

        })
    };
    function printGetBill_show(data) {
        /*ul*/
        var payDetailsTr = $('#payDetails_printGetBillSmall');
        payDetailsTr.text("");
        var consumeDetailsTr = $('#consumeDetails_printGetBillSmall');
        consumeDetailsTr.text("");
        var billDetailsTr = $("#billDetails_printGetBillSmall");
        billDetailsTr.text("");
        var debtDetailsTr = $("#debtDetails_printGetBillSmall");
        debtDetailsTr.text("");
        var freeDetailsTr = $("#freeDetails_printGetBillSmall");
        freeDetailsTr.text("");
        var tranferDetailsTr = $("#tranferDetails_printGetBillSmall");
        tranferDetailsTr.text("");

        if (data.paymentList && data.paymentList.length !== 0) {
            payDetailsTr.show();
        } else {
            payDetailsTr.hide();
        }
        if (data.consumeList && data.consumeList.length !== 0) {
            consumeDetailsTr.show();
        } else {
            consumeDetailsTr.hide();
        }

        if (data.billList && data.billList.length !== 0) {
            billDetailsTr.show();
        } else {
            billDetailsTr.hide();
        }
        if (data.debtList && data.debtList.length !== 0) {
            debtDetailsTr.show();
        } else {
            debtDetailsTr.hide();
        }
        if (data.freeList && data.freeList.length !== 0) {
            freeDetailsTr.show();
        } else {
            freeDetailsTr.hide();
        }
        if (data.tranferList && data.tranferList.length !== 0) {
            tranferDetailsTr.show();
        } else {
            tranferDetailsTr.hide();
        }

        //支付
        printGetBill_showPay(data.paymentList, payDetailsTr);
        //消费
        printGetBill_showConsume(data.consumeList, consumeDetailsTr, data.locks);
        //签单
        printGetBill_showBill(data.billList, billDetailsTr);
        //坏账
        printGetBill_showBebt(data.debtList, debtDetailsTr);
        //免单
        printGetBill_showFree(data.freeList, freeDetailsTr);
        //转移
        printGetBill_showTranfer(data.tranferList, tranferDetailsTr);

    }

    //	var folios = {};
    //	//根据账页显示明细
    //	folios.locks = data.locks;
    //	if(page == 0){
    //		//所有帐页
    //		folios.paymentList = data.paymentList;
    //		folios.consumeList = data.consumeList;
    //		folios.billList = data.billList;
    //		folios.debtList = data.debtList;
    //		folios.freeList = data.freeList;
    //		folios.tranferList = data.tranferList;
    //		printGetBill_show(folios);
    //	}else{
    //		//支付
    //		$.each(data.paymentList,function(i,item){
    //			if(item.receptionPageNumber == page && item.amount!=0){
    //				folios.paymentList = data.paymentList;
    //				return;
    //			}
    //		})
    //		//消费
    //		$.each(data.consumeList,function(i,item){
    //			if(item.receptionPageNumber == page){
    //				folios.consumeList = data.consumeList;
    //				return;
    //			}
    //		})
    //		//签单
    //		$.each(data.billList,function(i,item){
    //			if(item.receptionPageNumber == page){
    //				folios.billList = data.billList;
    //				return;
    //			}
    //		})
    //		//坏账
    //		$.each(data.debtList,function(i,item){
    //			if(item.receptionPageNumber == page){
    //				folios.debtList = data.debtList;
    //				return;
    //			}
    //		})
    //		//免单
    //		$.each(data.freeList,function(i,item){
    //			if(item.receptionPageNumber == page){
    //				folios.freeList = data.freeList;
    //				return;
    //			}
    //		})
    //		//转出
    //		$.each(data.tranferList,function(i,item){
    //			if(item.receptionPageNumber == page){
    //				folios.tranferList = data.tranferList;
    //				return;
    //			}
    //		})
    //		printGetBill_show(folios);
    //	}
    if (data.consumeList.length == 0) {
        return "";
    } else {
        return data.consumeList[0].receptionCode;
    }
}
//-----------------身份证民族--start-------------------------------

function getNationIdReturnNationName(NationId) {
    var nationName = "";
    if (NationId) {
        switch (NationId) {
            case "01":
                nationName = "汉";
                break;
            case "02":
                nationName = "蒙古";
                break;
            case "03":
                nationName = "回";
                break;
            case "04":
                nationName = "藏";
                break;
            case "05":
                nationName = "维吾尔";
                break;
            case "06":
                nationName = "苗";
                break;
            case "07":
                nationName = "彝";
                break;
            case "08":
                nationName = "壮";
                break;
            case "09":
                nationName = "布依";
                break;
            case "10":
                nationName = "朝鲜";
                break;
            case "11":
                nationName = "满";
                break;
            case "12":
                nationName = "侗";
                break;
            case "13":
                nationName = "瑶";
                break;
            case "14":
                nationName = "白";
                break;
            case "15":
                nationName = "土家";
                break;
            case "16":
                nationName = "哈尼";
                break;
            case "17":
                nationName = "哈萨克";
                break;
            case "18":
                nationName = "傣";
                break;
            case "19":
                nationName = "黎";
                break;
            case "20":
                nationName = "傈僳";
                break;
            case "21":
                nationName = "佤";
                break;
            case "22":
                nationName = "畲";
                break;
            case "23":
                nationName = "高山";
                break;
            case "24":
                nationName = "拉祜";
                break;
            case "25":
                nationName = "水";
                break;
            case "26":
                nationName = "东乡";
                break;
            case "27":
                nationName = "纳西";
                break;
            case "28":
                nationName = "景颇";
                break;
            case "29":
                nationName = "柯尔克孜";
                break;
            case "30":
                nationName = "土";
                break;
            case "31":
                nationName = "达斡尔";
                break;
            case "32":
                nationName = "仫佬";
                break;
            case "33":
                nationName = "羌";
                break;
            case "34":
                nationName = "布朗";
                break;
            case "35":
                nationName = "撒拉";
                break;
            case "36":
                nationName = "毛南";
                break;
            case "37":
                nationName = "仡佬";
                break;
            case "38":
                nationName = "锡伯";
                break;
            case "39":
                nationName = "阿昌";
                break;
            case "40":
                nationName = "普米";
                break;
            case "41":
                nationName = "塔吉克";
                break;
            case "42":
                nationName = "怒";
                break;
            case "43":
                nationName = "乌孜别克";
                break;
            case "44":
                nationName = "俄罗斯";
                break;
            case "45":
                nationName = "鄂温克";
                break;
            case "46":
                nationName = "德昂";
                break;
            case "47":
                nationName = "保安";
                break;
            case "48":
                nationName = "裕固";
                break;
            case "49":
                nationName = "京";
                break;
            case "50":
                nationName = "塔塔尔";
                break;
            case "51":
                nationName = "独龙";
                break;
            case "52":
                nationName = "鄂伦春";
                break;
            case "53":
                nationName = "赫哲";
                break;
            case "54":
                nationName = "门巴";
                break;
            case "55":
                nationName = "珞巴";
                break;
            case "56":
                nationName = "基诺";
            default:
                nationName = NationId;
        }
    }
    return nationName;
};
//-----------------身份证民族--end-------------------------------

//时间格式(YYYY-MM-DD)
function getDateForHoliday(time) {
    if (!time) {
        return "";
    }
    var date = new Date(time);
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
    return currentdate;
}
//时间格式(HH:mm:ss)
//规则设置
function getDateForRule(time) {
    if (!time) {
        return "";
    }
    var date = new Date(time);
    var seperator1 = "-";
    var seperator2 = ":";
    var currentdate = date.getHours() + seperator2 + date.getMinutes()
        + seperator2 + date.getSeconds();
    return currentdate;
}
//时间格式(YYYY-MM-DD 00:00:00)
function getTimeForTodayStart() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
        + " 00" + seperator2 + "00" + seperator2 + "00";
    return currentdate;
}
//时间格式(YYYY-MM-DD 23:59:59)
function getTimeForTodayEnd() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
        + " " + "23" + seperator2 + "59" + seperator2 + "59";
    return currentdate;
}
//时间格式(YYYY-MM-DD 12:00:00)
function getDateForRoomOpen(time) {
    if (!time) {
        return "";
    }
    var rule = $('#index_ruleData').val();

    if (rule) {
        rule = JSON.parse(rule);
    }
    if (!rule.noonCheckoutTime) {
        return;
    }
    var g = "1970-01-01 " + rule.noonCheckoutTime
    var noonTime = new Date(g);  //中午退房时间
    var h = noonTime.getHours();
    var m = noonTime.getMinutes();
    var s = noonTime.getSeconds();
    var date = new Date(time);
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    if (s >= 0 && s <= 9) {
        s = "0" + s;
    }
    if (m >= 0 && m <= 9) {
        m = "0" + m;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
        + " " + h + seperator2 + m + seperator2 + s;
    return currentdate;
}
//时间格式(YYYY-MM-DD HH:mm:ss)
function getDate(time) {
    if (!time) {
        return "";
    }
    var date = new Date(time);
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    var hours = date.getHours();//时
    var minutes = date.getMinutes();//分
    var seconds = date.getSeconds();//秒
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    if (hours >= 0 && hours <= 9) {
        hours = "0" + hours;
    }
    if (minutes >= 0 && minutes <= 9) {
        minutes = "0" + minutes;
    }
    if (seconds >= 0 && seconds <= 9) {
        seconds = "0" + seconds;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
        + " " + hours + seperator2 + minutes
        + seperator2 + seconds;
    return currentdate;
}
//时间格式(YYYY-MM-DD HH:mm)
function getDateNoSS(time) {
    if (!time) {
        return "";
    }
    var date = new Date(time);
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    var hours = date.getHours();//时
    var minutes = date.getMinutes();//分
    var seconds = date.getSeconds();//秒
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    if (hours >= 0 && hours <= 9) {
        hours = "0" + hours;
    }
    if (minutes >= 0 && minutes <= 9) {
        minutes = "0" + minutes;
    }
    if (seconds >= 0 && seconds <= 9) {
        seconds = "0" + seconds;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
        + " " + hours + seperator2 + minutes;
    return currentdate;
}
//去掉中间空的--> 2016-01-0112：00：00
function getDateNoEmpty(time) {
    if (!time) {
        return "";
    }
    var date = new Date(time);
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    var hours = date.getHours();//时
    var minutes = date.getMinutes();//分
    var seconds = date.getSeconds();//秒
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    if (hours >= 0 && hours <= 9) {
        hours = "0" + hours;
    }
    if (minutes >= 0 && minutes <= 9) {
        minutes = "0" + minutes;
    }
    if (seconds >= 0 && seconds <= 9) {
        seconds = "0" + seconds;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
        + "" + hours + seperator2 + minutes
        + seperator2 + seconds;
    return currentdate;
}

/*获取当前时间(YYYY-MM-DD HH:mm:ss)*/
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    var strHours = date.getHours();
    var strMinutes = date.getMinutes();
    var strSeconds = date.getSeconds();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    if (strHours >= 0 && strHours <= 9) {
        strHours = "0" + strHours;
    }
    if (strMinutes >= 0 && strMinutes <= 9) {
        strMinutes = "0" + strMinutes;
    }
    if (strSeconds >= 0 && strSeconds <= 9) {
        strSeconds = "0" + strSeconds;
    }

    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
        + " " + strHours + seperator2 + strMinutes
        + seperator2 + strSeconds;
    return currentdate;
}
// 得到当前时间 加3小时的 日期 2017-2-16 16:17:51 --to--》 2017-2-16 18:00:00
function nowTimeAddNHour() {
    var date = new Date(new Date().getTime() + 3 * 60 * 60 * 1000);
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
        + " " + (Number(date.getHours())) + seperator2 + "00" + seperator2 + "00";
    console.info(currentdate);
    return currentdate;
}
//新建预订 得到入住日期 若当前时间小于 16：00 则 默认时间为 yyyy--mm--dd 18:00 
//若 大于 16:00 则 默认时间为 当前时间加三个小时 
//夜审点JSON.parse($('#index_ruleData').val()).auditTime  +3600000  数据库 得到的 是 以8点为起始时间 所以需加 3600000 使得 起始时间为 0 点
function newScheduled_toHotelTime(time, type) {//传入的time为string 如'2017-2-16'  type为入住类型 1全日房 2钟点房 3晚房
    var nowGetTime = new Date().getTime();
    if (type == 1) {//全日房情况下
        //当前时间超过16：00预订的 情况下
        if ((new Date(time).getTime() + 8 * 60 * 60 * 1000) < nowGetTime && (new Date(time).getTime() + 16 * 60 * 60 * 1000) > nowGetTime) {
            return nowTimeAddNHour();//得到当前时间 加 三小时 的日期格式
        } else {//当前时间未超过16：00预订的 情况下
            return getDate(new Date(time).getTime() + 8 * 60 * 60 * 1000);
        }
        return getDate(new Date(time).getTime() + 8 * 60 * 60 * 1000);
    } else if (type == 2) {//钟点房情况下
        nowTimeAddNHour();//得到当前时间 加 两小时 的日期格式
    } else {//晚房情况下
        nowTimeAddNHour();//得到当前时间 加 两小时 的日期格式
    }

};

/*获取房价的回调函数*/
var srRoomDetails_getRoomPriceCallback = function (data) {
    var td = $('#srRoomDetails_priceDetail');
    $('#srRoomDetails_priceDetail ul:not(:first)').empty();
    var total = 0;
    $.each(data, function (i, item) {
        var u = "";
        var time = new Date(item.date).toLocaleDateString();
        u += '<ul>';
        u += '<li style="float:left;width:110px;border:1px solid black"><span>' + time + '</span></li>'
        u += '<li style="float:left;width:70px;border:1px solid black"><span>' + item.price + '</span></li>'
        u += '</ul>';
        u += '<div style="clear:both"></div>';
        td.append(u);
        total += item.price;
    });
    $('#srRoomDetails_totalPrice').val(total);
    //更新验证提示
    $('#srRoomDetails_budgetTime-error').remove();
    $('#srRoomDetails_totalPrice-error').remove();


}

/*计算预住天数 并且 请求房价*/
function srRoomDetails_endTimeChange() {
    if ($('#srRoomDetails_saveRentPlanId').val() == "") {
        alert('客源不能为空！');
        $('#srRoomDetails_canalsName').focus();//使客源输入框获得焦点，弹出客源选项

    } else {
        if (beginTime == "" || endTime == "") {
            alert('时间不能为空！');
        } else {
            //计算总房价
            var beginTime = $('#srRoomDetails_beginTime').datetimebox('getValue');
            var endTime = $('#srRoomDetails_endTime').datetimebox('getValue');
            var srRoomDetails_getRoomPriceData = {};
            srRoomDetails_getRoomPriceData.startTime = beginTime;
            srRoomDetails_getRoomPriceData.endTime = endTime;
            srRoomDetails_getRoomPriceData.roomtypeId = $('#srRoomDetails_roomType').val();
            srRoomDetails_getRoomPriceData.checkinType = $('#srRoomDetails_checkinType').val();
            srRoomDetails_getRoomPriceData.rentplanId = $('#srRoomDetails_saveRentPlanId').val();

            //console.info(srRoomDetails_getRoomPriceData);
            srajax("../rentprice/getRoomPriceListByPeriod", srRoomDetails_getRoomPriceData,
                srRoomDetails_getRoomPriceCallback);
            //根据时间计算预住天数
            time1 = new Date(Date.parse(beginTime.replace(/-/g, "/")));
            time2 = new Date(Date.parse(endTime.replace(/-/g, "/")));
            time = time2 - time1;
            var days = time / 1000 / 3600 / 24;
            var daysRound = Math.floor(days);
            var hours = time / 1000 / 60 / 60 - (24 * daysRound);
            var hoursRound = Math.floor(hours);
            var rectime = "";
            if (daysRound < 1) {
                rectime = hoursRound + "小时";
            } else {
                rectime = daysRound + "天" + hoursRound + "小时";
            }
            $('#srRoomDetails_budgetTime').val(rectime);
        }
    }
}