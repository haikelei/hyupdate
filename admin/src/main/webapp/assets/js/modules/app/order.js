var url_id = T.p('id');
$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'app/order/list',
        datatype: "json",
        colModel: [
            {label: 'ID', name: 'id', index: 'id', width: 50, key: true},
            {
                label: '状态', name: 'status', index: 'status', width: 80, formatter: function (value, options, row) {
                return value == 0 ? '<a class="btn btn-danger btn-xs" href="javascript:void(0)" onclick="deal(\'' + row['id'] + '\')" title="点击处理">待发货</a>' : (value == 1 ? '<a class="btn btn-success btn-xs" href="javascript:void(0)">已发货</a>' : '非法状态');
            }
            },
            {label: '流水号', name: 'serialNum', index: 'serial_num', width: 80},
            {
                label: '用户', name: 'userId', index: 'user_id', width: 80, formatter: function (value, options, row) {
                return value === 0 ? "无" : '<a class="btn btn-default btn-xs" href="javascript:void(0)" onclick="frame(\'查看下单用户\',\'' + nginxURL + 'modules/app/user.html?username=' + row['userName'] + '\')" title="点击查看">' + row['userName'] + '</a>';
            }
            },
            {
                label: '商品', name: 'goodsId', index: 'goods_id', width: 80, formatter: function (value, options, row) {
                return value === 0 ? "无" : '<a class="btn btn-default btn-xs" href="javascript:void(0)" onclick="frame(\'查看下单商品\',\'' + nginxURL + 'modules/app/goods.html?id=' + value + '\')" title="点击查看">' + row['goodsTitle'] + '</a>';
            }
            },
            {
                label: '收货地址',
                name: 'shipAddressId',
                index: 'ship_address_id',
                width: 80,
                formatter: function (value, options, row) {
                    if (value == null) {
                        return "已删除";
                    } else if (value === 0) {
                        return "无";
                    } else {
                        return '<a class="btn btn-default btn-xs" href="javascript:void(0)" onclick="frame(\'查看收货地址\',\'' + nginxURL + 'modules/app/shipaddress.html?id=' + value + '\')" title="点击查看">' + row['shipAddressConsignee'] + '</a>';
                    }
                }
            },
            {
                label: '支付方式', name: 'payment', index: 'payment', width: 80, formatter: function (value, options, row) {
                return value === 0 ? '<button class="btn btn-warning btn-xs">悦支付</button>' : (value === 1 ? '<button class="btn btn-primary btn-xs">支付宝</button>' : '<button class="btn btn-success btn-xs">微信支付</button>');
            }
            },
            {label: '支付金额', name: 'money', index: 'money', width: 80},
            {label: '登记时间', name: 'createTime', index: 'create_time', width: 80}
        ],
        viewrecords: true,
        height: "auto",
        rowNum: 10,
        rowList: [10, 30, 50, 100, 200, 500],
        rownumbers: true,
        rownumWidth: 25,
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
            $("#jqGrid").closest(".ui-jqgrid-bdiv").animate({
                "min-height": "400px",
                "max-height": "600px"
            }, "slow").css({"overflow-x": "hidden"});
            $("#jqGridPager_left").text("本次加载共耗时 " + $("#jqGrid").jqGrid("getGridParam", "totaltime") + " ms.");
        }
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            s_user_name: null,
            s_good_title: null,
            s_ship_addrezz_consignee: null,
            s_id: null,
            s_serial_num: null,
            s_money: null
        },
        showList: true,
        title: null,
        order: {}
    },
    created: function () {
        if (url_id) {
            this.q.s_id = url_id;
            $(".grid-btn").hide();
            msg("正在自动检索目标数据...", 2000, function () {
                vm.query();
            })
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
            confirm('您正在进行批量发货操作！请确认是否已对选定订单进行了发货处理,该动作不可恢复！请慎重操作！', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "app/order/batchdeal",
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
            $.get(baseURL + "app/order/info/" + id, function (r) {
                vm.order = r.order;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: vm.q,
                page: page
            }).trigger("reloadGrid");
        }
    }
});


function deal(id) {
    confirm('你正在对ID为：' + id + ' 的订单进行操作！请确认是否已对选定订单进行了发货处理,该动作不可恢复！请慎重操作！', function () {
        $.ajax({
            type: "POST",
            url: baseURL + "app/order/deal/" + id,
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