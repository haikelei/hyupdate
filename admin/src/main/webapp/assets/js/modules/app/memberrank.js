var url_id = T.p('id');
$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'app/memberrank/list',
        datatype: "json",
        colModel: [
            {label: 'ID', name: 'id', index: 'id', width: 30, key: true},
            {label: '名称', name: 'name', index: 'name', width: 80},
            // { label: '图片', name: 'pic', index: 'pic', width: 80 },
            {
                label: '图标', name: 'smallpic', index: 'smallpic', width: 30, formatter: function (value, options, row) {
                return value ? '<img src="' + picServerURL + value + '" width="30px" height="30px" class="img-circle">' : '无';
            }
            },
            {label: '描述', name: 'description', index: 'description', width: 200},
            {label: '分成基数', name: 'divideCnum', index: 'divide_cnum', width: 50},
            {label: '分成占比(%)', name: 'dividePercent', index: 'divide_percent', width: 50},
            {label: '解锁:直属达标下线', name: 'deblockDirectnum', index: 'deblock_directnum', width: 80},
            {label: '解锁:下线总数', name: 'deblockNodenum', index: 'deblock_nodenum', width: 50},
            // { label: '状态', name: 'status', index: 'status', width: 80 },
            {label: '发布时间', name: 'createTime', index: 'create_time', width: 80}
        ],
        viewrecords: true,
        height: "auto",
        rowNum: 10,
        rowList: [10, 30, 50],
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
            s_name: null,
            s_description: null
        },
        showList: true,
        title: null,
        config: {},
        memberRank: {}
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
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.memberRank = {};
            clearContent();
            vm.getConfig();
        },
        update: function (event) {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id);
            vm.getConfig();
        },
        saveOrUpdate: function (event) {
            vm.memberRank.description = ue_getContent();
            var url = vm.memberRank.id == null ? "app/memberrank/save" : "app/memberrank/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.memberRank),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        del: function (event) {
            var ids = getSelectedRows();
            if (ids == null) {
                return;
            }
            for (var id in ids) {
                if (id == 1) {
                    alert("1级会员禁止删除！");
                    return false;
                }
            }
            confirm('确定要删除选中的记录？', function () {
                confirm('<font color="red">警告：该会员等级下的用户将会自动变更为当前最低级！其绑定的商品亦将恢复初始化待绑定状态！请再次确认！</font>', function () {
                    $.ajax({
                        type: "POST",
                        url: baseURL + "app/memberrank/delete",
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
            });
        },
        getInfo: function (id) {
            $.get(baseURL + "app/memberrank/info/" + id, function (r) {
                vm.memberRank = r.memberRank;
                ue_setContent(vm.memberRank.description)
            });
        },
        getConfig: function () {
            $.getJSON(baseURL + "sys/oss/config", function (r) {
                vm.config = r.config;
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

// document.domain = domainConfig;
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