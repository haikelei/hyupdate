$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'app/log/list',
        datatype: "json",
        colModel: [
            {label: 'ID', name: 'id', index: 'id', width: 150, key: true},
            {
                label: '动作用户', name: 'userId', index: 'user_id', width: 120, formatter: function (value, options, row) {
                return value === 0 ? "无" : '<a class="btn btn-default btn-xs" href="javascript:void(0)" onclick="frame(\'查看用户\',\'' + nginxURL + 'modules/app/user.html?username=' + row['username'] + '\')" title="点击查看">' + row['username'] + '</a>';
            }
            },
            {label: '动作描述', name: 'operation', index: 'operation', width: 250},
            {label: '请求方法', name: 'method', index: 'method', width: 550},
            {label: '请求参数', name: 'params', index: 'params', width: 140},
            {label: '执行时长(毫秒)', name: 'time', index: 'time', width: 140},
            {label: 'IP地址', name: 'ip', index: 'ip', width: 140},
            {label: '动作时间', name: 'createDate', index: 'create_date', width: 160}
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
        multiselect: false,
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
            s_username: null,
            s_operation: null,
            s_ip: null
        },
        showList: true,
        title: null,
        log: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },
        getInfo: function (id) {
            $.get(baseURL + "app/log/info/" + id, function (r) {
                vm.log = r.log;
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