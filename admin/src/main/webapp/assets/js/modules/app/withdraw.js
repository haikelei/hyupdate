$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'app/withdraw/list',
        datatype: "json",
        colModel: [
            {label: 'ID', name: 'id', index: 'id', width: 50, key: true},
            {
                label: '状态', name: 'status', index: 'status', width: 80, formatter: function (value, options, row) {
                return value == 0 ? '<a class="btn btn-danger btn-xs" href="javascript:void(0)" onclick="deal(\'' + row['id'] + '\')" title="点击处理">待处理</a>' : (value == 1 ? '<a class="btn btn-success btn-xs" href="javascript:void(0)">已到账</a>' : '非法状态');
            }
            },
            {
                label: '提现用户', name: 'userId', index: 'user_id', width: 120, formatter: function (value, options, row) {
                return value === 0 ? "无" : '<a class="btn btn-default btn-xs" href="javascript:void(0)" onclick="frame(\'查看用户\',\'' + nginxURL + 'modules/app/user.html?username=' + row['userName'] + '\')" title="点击查看">' + row['userName'] + '</a>';
            }
            },
            {label: '流水号', name: 'serialNum', index: 'serial_num', width: 160},
            {label: '提现申请金额', name: 'money', index: 'money', width: 120},
            {label: '实际到账金额', name: 'realmoney', index: 'realmoney', width: 120},
            {label: '姓名', name: 'name', index: 'name', width: 120},
            {label: '手机号', name: 'mobile', index: 'mobile', width: 120},
            {label: '银行卡号', name: 'bankcardNum', index: 'bankcard_num', width: 200},
            {
                label: '银行卡信息',
                name: 'bankinfo',
                index: 'bankinfo',
                width: 250,
                formatter: function (value, options, row) {
                    var ihtml = "";
                    if (value) {
                        vary = value.split(" ");
                        var str1 = vary[0];
                        var str2 = vary[1];
                        ihtml += '<button class="btn btn-default btn-xs">' + str1.split(":")[0] + '</button> ' + str1.split(":")[1];
                        ihtml += '    <button class="btn btn-default btn-xs">' + str2.split(":")[0] + '</button> ' + str2.split(":")[1];
                    } else {
                        ihtml = "无法识别";
                    }
                    return ihtml;
                }
            },
            {label: '开户行', name: 'bank', index: 'bank', width: 250},
            {label: '申请时间', name: 'createTime', index: 'create_time', width: 150}
        ],
        viewrecords: true,
        height: "auto",
        rowNum: 10,
        rowList: [10, 30, 50, 100, 200, 500],
        rownumbers: true,
        rownumWidth: 25,
        //以下两个参数决定横向滚动条是否出现，还需要去掉“overflow-x:hidden;”
        shrinkToFit: false,
        autoScroll: true,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").animate({"min-height": "400px", "max-height": "600px"}, "slow");
            $("#jqGridPager_left").text("本次加载共耗时 " + $("#jqGrid").jqGrid("getGridParam", "totaltime") + " ms.");
        }
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            s_user_name: null,
            s_serial_num: null,
            s_money: null,
            s_realmoney: null,
            s_name: null,
            s_mobile: null,
            s_bankcard_num: null,
            s_bankinfo: null,
            s_bank: null
        },
        showList: true,
        title: null,
        withdraw: {},
        form: {
            value1: '',
            value2: '',
            value3: ''
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        batchdeal: function (event) {
            var ids = getSelectedRows();
            if (ids == null) {
                return;
            }
            confirm('您正在进行批量打款操作！请确认是否已对选定提现申请进行了线下打款处理,该动作不可恢复！请慎重操作！', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "app/withdraw/batchdeal",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.code == 0) {
                            alert('操作成功', function (index) {
                                $("#jqGrid").trigger("reloadGrid");
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        getInfo: function (id) {
            $.get(baseURL + "app/withdraw/info/" + id, function (r) {
                vm.withdraw = r.withdraw;
            });
        },
        getParamSet: function () {
            $.get(baseURL + "app/config/getbykey/" + "withdraw", function (r) {
                console.log(r.list);
                vm.form.value1 = r.list[0].value;
                vm.form.value2 = r.list[1].value;
                vm.form.value3 = r.list[2].value;
            });
        },
        paramSet: function () {
            vm.getParamSet();
            layer.open({
                type: 1,
                title: "参数配置",
                offset: '100px',
                area: ['560px', '280px'],
                shade: 0,
                shadeClose: false,
                content: $("#paramSetLayer"),
                btn: ['修改', '取消'],
                btn1: function (index) {
                    //数值合法性校验
                    if (!vm.validator()) {
                        return;
                    } else {
                        $.ajax({
                            type: "POST",
                            url: baseURL + "app/withdraw/paramset?value1=" + vm.form.value1 + "&&value2=" + vm.form.value2 + "&&value3=" + vm.form.value3,
                            dataType: "json",
                            contentType: "application/json",
                            success: function (r) {
                                if (r.code == 0) {
                                    alert('修改成功,修改的配置将在下一订单起生效', function () {
                                        location.reload();
                                    });
                                } else {
                                    alert(r.msg);
                                }
                            }
                        });
                    }
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: vm.q,
                page: page
            }).trigger("reloadGrid");
        },
        validator: function () {
            if (!isNum(vm.form.value1) || !isNum(vm.form.value2) || !isNum(vm.form.value2)) {
                msg("必须输入数字")
                return false;
            }
            if (vm.form.value1 < 0) {
                msg("最低提现金额值不合法")
                return false;
            }
            if (vm.form.value2 < 0 || vm.form.value2 > 100 || vm.form.value3 < 0 || vm.form.value3 > 100) {
                msg("百分值范围为：0~100");
                return false;
            }
            return true;
        }
    }
});

function deal(id) {
    confirm('你正在对ID为：' + id + ' 的提现申请进行操作！请确认是否已对选定提现申请进行了线下打款处理,该动作不可恢复！请慎重操作！', function () {
        $.ajax({
            type: "POST",
            url: baseURL + "app/withdraw/deal/" + id,
            contentType: "application/json",
            success: function (r) {
                if (r.code == 0) {
                    alert('操作成功', function (index) {
                        $("#jqGrid").trigger("reloadGrid");
                    });
                } else {
                    alert(r.msg);
                }
            }
        });
    });
}