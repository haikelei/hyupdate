var url_id = T.p('id');
$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'app/shipaddress/list',
        datatype: "json",
        colModel: [
            {label: 'ID', name: 'id', index: 'id', width: 30, key: true},
            {
                label: '所属用户', name: 'userId', index: 'user_id', width: 80, formatter: function (value, options, row) {
                return value === 0 ? "无" : '<a class="btn btn-default btn-xs" href="javascript:void(0)" onclick="frame(\'查看收货地址所属用户\',\'' + nginxURL + 'modules/app/user.html?username=' + row['userName'] + '\')" title="点击查看">' + row['userName'] + '</a>';
            }
            },
            {label: '收件人', name: 'consignee', index: 'consignee', width: 80},
            {label: '收件手机号', name: 'mobile', index: 'mobile', width: 80},
            {label: '城市地区', name: 'region', index: 'region', width: 100},
            {label: '详细地址', name: 'detail', index: 'detail', width: 120},
            {label: '创建时间', name: 'createTime', index: 'create_time', width: 80}
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
            s_id: null,
            s_consignee: null,
            s_mobile: null,
            s_region: null,
            s_detail: null
        },
        showList: true,
        title: null,
        shipAddress: {}
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
        getInfo: function (id) {
            $.get(baseURL + "app/shipaddress/info/" + id, function (r) {
                vm.shipAddress = r.shipAddress;
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

