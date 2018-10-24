$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'app/distributiontree/list',
        datatype: "json",
        colModel: [
            {label: 'ID', name: 'id', index: 'id', width: 50, key: true},
            {
                label: '根节点', name: 'rootId', index: 'root_id', width: 80, formatter: function (value, options, row) {
                return value === 0 ? "无" : '<a class="btn btn-default btn-xs" href="javascript:void(0)" onclick="frame(\'查看用户\',\'' + nginxURL + 'modules/app/user.html?username=' + row['rootName'] + '\')" title="点击查看">' + row['rootName'] + '</a>';
            }
            },
            {
                label: '节点', name: 'nodeId', index: 'node_id', width: 80, formatter: function (value, options, row) {
                return value === 0 ? "无" : '<a class="btn btn-default btn-xs" href="javascript:void(0)" onclick="frame(\'查看用户\',\'' + nginxURL + 'modules/app/user.html?username=' + row['nodeName'] + '\')" title="点击查看">' + row['nodeName'] + '</a>';
            }
            },
            {label: '分支深度', name: 'depth', index: 'depth', width: 80},
            {
                label: '是否叶子节点',
                name: 'isLeaf',
                index: 'is_leaf',
                width: 80,
                formatter: function (value, options, row) {
                    return value === 0 ? "否" : '是';
                }
            },
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
            s_root_name: null,
            s_node_name: null,
            s_depth: null
        },
        showList: true,
        title: null,
        distributionTree: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },
        getInfo: function (id) {
            $.get(baseURL + "app/distributiontree/info/" + id, function (r) {
                vm.distributionTree = r.distributionTree;
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