var url_id = T.p('id');
$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'app/goods/list',
        datatype: "json",
        colModel: [
            {label: 'ID', name: 'id', index: 'id', width: 50, key: true},
            {
                label: '绑定等级',
                name: 'memberRankId',
                index: 'member_rank_id',
                width: 50,
                formatter: function (value, options, row) {
                    return value === 0 ? "无" : '<a class="btn btn-default btn-xs" href="javascript:void(0)" onclick="frame(\'查看会员等级\',\'' + nginxURL + 'modules/app/memberrank.html?id=' + value + '\')" title="点击查看">' + row['memberRankName'] + '</a>';
                }
            },
            {label: '标题', name: 'title', index: 'title', width: 80},
            {label: '价格', name: 'price', index: 'price', width: 80},
            {label: '图文信息', name: 'content', index: 'content', width: 200},
            {
                label: '预览图', name: 'banners', index: 'banners', width: 80, formatter: function (value, options, row) {
                return value ? '<img src="' + picServerURL + value.split(';')[0] + '" width="80px" height="50px">' : '';
            }
            },
            {label: '排序', name: 'sort', index: 'sort', width: 30},
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
            s_title: null,
            s_price: null,
            s_content: null
        },
        showList: true,
        title: null,
        config: {},
        goods: {}
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
            vm.goods = {};
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
            vm.goods.content = ue_getContent();
            if (vm.goods.title == null || vm.goods.title.trim() == '') {
                alert("商品标题不能为空");
                return false;
            }
            if (vm.goods.banners == null) {
                alert("至少上传一张图片");
                return false;
            }
            if (!vm.validator()) {
                return;
            } else {
                var url = vm.goods.id == null ? "app/goods/save" : "app/goods/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.goods),
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
            }
        },
        del: function (event) {
            var ids = getSelectedRows();
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                confirm('<font color="red">警告：该商品关联的订单数据等将会一并删除！其绑定的会员等级亦将初始化至待绑定状态！请再次确认！</font>', function () {
                    $.ajax({
                        type: "POST",
                        url: baseURL + "app/goods/delete",
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
        status: function (event) {
            var ids = getSelectedRows();
            if (ids == null) {
                return;
            }

            confirm('确定要对选中的订单进行发货操作？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "app/goods/status",
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
        checkMemberRank: function () {
            $("#mrf").css("border", "");
            $.get(baseURL + "app/memberrank/info/" + vm.goods.memberRankId, function (r) {
                if (r.memberRank) {
                    if (r.memberRank.goodsId != 0) {
                        alert("警告：目标会员等级已绑定商品礼包，继续操作将会更新绑定关系，商品与等级为一一对应关系！");
                    }
                } else {
                    msg("目标会员等级不存在！");
                    $("#mrf").focus();
                    $("#mrf").css("border", "1px red solid");
                }
            });
        },
        getInfo: function (id) {
            $.get(baseURL + "app/goods/info/" + id, function (r) {
                vm.goods = r.goods;
                ue_setContent(vm.goods.content);
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
        },
        validator: function () {
            if (!isNum(vm.goods.memberRankId) || !isNum(vm.goods.sort)) {
                msg("必须输入数字")
                return false;
            }
            if (!isFloat(vm.goods.price, 2)) {
                msg("价格最高精确至小数点后2位")
                return false;
            }
            if (vm.goods.sort < 0 || vm.goods.sort > 100) {
                msg("排序值范围：0~100")
                return false;
            }
            if (vm.goods.price < 0) {
                msg("价格取值非法");
                return false;
            }
            return true;
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
    onComplete: function (file, r) {
        console.log(file);
        console.log(r);
        if (r.url) {
            alert("上传成功！<br/>" + r.url);
            vm.goods.banners = vm.goods.banners + r.url + ";";
        } else {
            alert("上传失败！");
        }
    }
});