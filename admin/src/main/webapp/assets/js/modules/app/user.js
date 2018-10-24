var url_username = T.p('username');
$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'app/user/list',
        datatype: "json",
        colModel: [
            {label: 'ID', name: 'userId', index: 'user_id', width: 80, key: true},
            {
                label: '头像', name: 'headpic', index: 'headpic', width: 50, formatter: function (value, options, row) {
                return value ? '<img src="' + picServerURL + value + '" width="30px" height="30px" class="img-circle">' : '<img src="' + picServerURL + 'face.jpg" width="30px" height="30px" class="img-circle">';
            }
            },
            {label: '用户名', name: 'username', index: 'username', width: 120},
            {label: '手机号', name: 'mobile', index: 'mobile', width: 120},
            // { label: '密码', name: 'password', index: 'password', width: 80 },
            {label: '微信号', name: 'wechat', index: 'wechat', width: 120},
            {label: '真实姓名', name: 'realname', index: 'realname', width: 80},
            {label: '身份证号', name: 'idcard', index: 'idcard', width: 180},
            {label: '城市地区', name: 'addressRegion', index: 'address_region', width: 200},
            {label: '详细地址', name: 'addressDetail', index: 'address_detail', width: 240},
            {
                label: '默认收货地址',
                name: 'defaultShipAddressId',
                index: 'default_ship_address_id',
                width: 300,
                formatter: function (value, options, row) {
                    return value ? '<a class="btn btn-default btn-xs" href="javascript:void(0)" onclick="frame(\'查看收货地址\',\'' + nginxURL + 'modules/app/shipaddress.html?id=' + value + '\')" title="点击查看">' + value + '</a>' : "无";
                }
            },
            {
                label: '推荐人',
                name: 'recommendId',
                index: 'recommend_id',
                width: 120,
                formatter: function (value, options, row) {
                    return value === 0 ? "无" : '<a class="btn btn-default btn-xs" href="javascript:void(0)" onclick="frame(\'查看推荐人\',\'' + nginxURL + 'modules/app/user.html?username=' + row['recommendUsername'] + '\')" title="点击查看">' + row['recommendUsername'] + '</a>';
                }
            },
            {label: '可提现佣金', name: 'commissionEnable', index: 'commission_enable', width: 100},
            {label: '累计佣金', name: 'commissionSum', index: 'commission_sum', width: 80},
            {
                label: '会员等级',
                name: 'memberRankId',
                index: 'member_rank_id',
                width: 100,
                formatter: function (value, options, row) {
                    return value == null ? "无" : (value === 0 ? '<a class="btn btn-default btn-xs" href="javascript:void(0)">免费会员</a>' : '<a class="btn btn-default btn-xs" href="javascript:void(0)" onclick="frame(\'查看会员等级\',\'' + nginxURL + 'modules/app/memberrank.html?id=' + value + '\')" title="点击查看">' + row['memberRankName'] + '</a>');
                }
            },
            {label: '直属下线', name: 'directnum', index: 'directnum', width: 80},
            {label: '下线总数', name: 'nodenum', index: 'nodenum', width: 80},
            {label: '注册时间', name: 'createTime', index: 'create_time', width: 180}
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
            recommend_username: null,
            s_recommend_username: null,
            s_username: null,
            s_mobile: null,
            s_wechat: null,
            s_realname: null,
            s_idcard: null
        },
        showList: true,
        title: null,
        zlayer: null,
        user: {
            username: '',
            mobile: '',
            recommendId: null
        }
    },
    created: function () {
        if (url_username) {
            this.q.s_username = url_username;
            console.log(url_username)
            $(".grid-btn").hide();
            msg("正在自动检索目标数据...", 2000, function () {
                vm.query();
            })
        }
    },
    methods: {
        query: function () {
            if (vm.q.recommend_username != null)
                if (vm.q.recommend_username == '0' || vm.q.recommend_username == '无')
                    vm.q.s_recommend_username = 0;
                else
                    vm.q.s_recommend_username = vm.q.recommend_username;
            else
                vm.q.s_recommend_username = null;
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.user = {recommendId: 0};

        },
        update: function (event) {
            var userId = getSelectedRow();
            if (userId == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(userId)
        },
        saveOrUpdate: function (event) {
            if (vm.validator()) {
                return false;
            }

            var url = "app/user/update";
            if (vm.user.userId == null) {
                url = "app/user/save";
                vm.user.recommendId = 0;//新增时候，推荐人只能为0
            }
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.user),
                success: function (r) {
                    if (r.code === 0) {
                        var str = vm.user.userId == null ? "，新添用户初始密码为:yn123456" : "";
                        alert('操作成功' + str, function (index) {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        del: function (event) {
            var userIds = getSelectedRows();
            if (userIds == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                confirm('<font color="red">警告：该用户关联的日志动作、分销枝节、佣金记录、订单记录、收货地址、提现记录等数据将会一并删除！</font><br/>本操作涉及数据影响极大，请再次确认！', function () {
                    var index = msg('该动作耗时较长，请耐心等候...', 1000 * 59);//59s
                    $.ajax({
                        type: "POST",
                        url: baseURL + "app/user/delete",
                        contentType: "application/json",
                        data: JSON.stringify(userIds),
                        success: function (r) {
                            layer.close(index);
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
        resetPasw: function (event) {
            var userIds = getSelectedRows();
            if (userIds == null) {
                return;
            }
            confirm('确定要对选中的用户重置密码吗？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "app/user/resetPasw",
                    contentType: "application/json",
                    data: JSON.stringify(userIds),
                    success: function (r) {
                        if (r.code == 0) {
                            alert('操作成功,重置后的密码为:yn123456', function (index) {
                                $("#jqGrid").trigger("reloadGrid");
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        getInfo: function (userId) {
            $.get(baseURL + "app/user/info/" + userId, function (r) {
                vm.user = r.user;
            });
        },
        reload: function (event) {
            vm.showList = true;
            layer.close(vm.zlayer);
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: vm.q,
                page: page
            }).trigger("reloadGrid");
        },
        validator: function () {
            // console.log(vm.user.username)
            if (isBlank(vm.user.username)) {
                alert("用户名不能为空");
                return true;
            }
            console.log(vm.user.mobile)
            if (vm.user.mobile == null || isBlank(vm.user.mobile)) {
                alert("手机号不能为空");
                return true;
            }
        },
        RecomTip: function () {
            vm.zlayer = tip("管理中心新增用户推荐人ID只能为0！修改用户不允许变更推荐人信息！", "#recomId", 2);
        }
    }
});