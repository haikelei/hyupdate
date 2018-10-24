$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/log/list',
        datatype: "json",
        colModel: [
            {label: 'ID', name: 'id', width: 150, key: true},
            {label: '动作用户', name: 'username', width: 100},
            {label: '动作描述', name: 'operation', width: 240},
            {label: '请求方法', name: 'method', width: 550},
            {label: '请求参数', name: 'params', width: 340},
            {label: '执行时长(毫秒)', name: 'time', width: 140},
            {label: 'IP地址', name: 'ip', width: 140},
            {label: '动作时间', name: 'createDate', width: 160}
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
    },
    methods: {
        query: function () {
            vm.reload();
        },
        reload: function (event) {
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: vm.q,
                page: page
            }).trigger("reloadGrid");
        }
    }
});