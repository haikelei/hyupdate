$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'app/commission/list',
        datatype: "json",
        colModel: [
            {label: 'ID', name: 'id', index: 'id', width: 50, key: true},
            {
                label: '佣金所属', name: 'userId', index: 'user_id', width: 80, formatter: function (value, options, row) {
                return value === 0 ? "无" : '<a class="btn btn-default btn-xs" href="javascript:void(0)" onclick="frame(\'查看用户\',\'' + nginxURL + 'modules/app/user.html?username=' + row['userName'] + '\')" title="点击查看">' + row['userName'] + '</a>';
            }
            },
            {
                label: '订单', name: 'orderId', index: 'order_id', width: 80, formatter: function (value, options, row) {
                return value === 0 ? "无" : '<a class="btn btn-default btn-xs" href="javascript:void(0)" onclick="frame(\'查看订单\',\'' + nginxURL + 'modules/app/order.html?id=' + value + '\')" title="点击查看">' + row['orderSerialNum'] + '</a>';
            }
            },
            {label: '佣金', name: 'money', index: 'money', width: 80},
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
            s_id: null,
            s_user_name: null,
            s_order_serial_num: null,
            s_money: null,
        },
        showList: true,
        title: null,
        commission: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },
        getInfo: function (id) {
            $.get(baseURL + "app/commission/info/" + id, function (r) {
                vm.commission = r.commission;
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