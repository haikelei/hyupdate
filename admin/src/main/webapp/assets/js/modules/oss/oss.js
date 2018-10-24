$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/oss/list',
        datatype: "json",
        colModel: [
            {label: 'ID', name: 'id', width: 20, key: true},
            {label: 'URL地址', name: 'url', width: 160},
            {label: '创建时间', name: 'createDate', width: 40}
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

    document.domain = domainConfig;
    new AjaxUpload('#upload-btn', {
        action: baseURL + "sys/oss/upload",
        name: 'file',
        autoSubmit: true,
        secureuri: false,   //是否启用安全提交
        // responseType:"json",
        crossDomain: true,
        dataType: "jsonp",//数据类型为jsonp
        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
        onChange: function (file, extension) {
            if (!(extension && /^(jpg|jpeg|png|gif|bmp)$/.test(extension.toLowerCase()))) {
                msg("只限上传图片文件，请重新选择！");
            }
        },
        onSubmit: function (file, extension) {
            if (vm.config.type == null) {
                alert("云存储配置未配置");
                return false;
            }
            if (!(extension && /^(jpg|jpeg|png|gif|bmp)$/.test(extension.toLowerCase()))) {
                alert('只支持jpg、png、gif、bmp格式的图片！');
                return false;
            }
            msg("正在上传文件:" + file + "...");
        },
        success: function (data, status) {
            alert(data);
            if (data.status == 1) {
                alert(data.result);
            } else {
                alert("【提交失败！】");
            }
        },
        error: function (data, status, e) {
            alert("【服务器异常，请连续管理员！】" + e);
        },
        onComplete: function (file, r) {
            console.log(file);
            console.log(r);
            if (r.url) {
                alert("上传成功！<br/>" + r.url);
                vm.reload();
            } else {
                alert("上传失败！");
            }
        }
    });

});

var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            s_id: null,
            s_url: null
        },
        showList: true,
        title: null,
        config: {}
    },
    created: function () {
        this.getConfig();
    },
    methods: {
        query: function () {
            vm.reload();
        },
        getConfig: function () {
            $.getJSON(baseURL + "sys/oss/config", function (r) {
                vm.config = r.config;
            });
        },
        addConfig: function () {
            vm.showList = false;
            vm.title = "云存储配置";
        },
        saveOrUpdate: function () {
            var url = baseURL + "sys/oss/saveConfig";
            $.ajax({
                type: "POST",
                url: url,
                contentType: "application/json",
                data: JSON.stringify(vm.config),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function () {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        del: function () {
            var ossIds = getSelectedRows();
            if (ossIds == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/oss/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ossIds),
                    success: function (r) {
                        if (r.code === 0) {
                            alert('操作成功', function () {
                                vm.reload();
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: vm.q,
                page: page
            }).trigger("reloadGrid");
        }
    }
});