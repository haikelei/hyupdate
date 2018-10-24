//生成菜单
// var menuItem = Vue.extend({
//     name: 'menu-item',
//     props:{item:{},index:0},
//     template:[
//         '<li :class="{active: (item.type===0 && index === 0)}">',
//         '<a v-if="item.type === 0" href="javascript:;">',
//         '<i v-if="item.icon != null" :class="item.icon"></i>',
//         '<span>{{item.name}}</span>',
//         '<i class="fa fa-angle-left pull-right"></i>',
//         '</a>',
//         '<ul v-if="item.type === 0" class="treeview-menu">',
//         '<menu-item :item="item" :index="index" v-for="(item, index) in item.list"></menu-item>',
//         '</ul>',
//         '<a v-if="item.type === 1" :href="\'#\'+item.url">' +
//         '<i v-if="item.icon != null" :class="item.icon"></i>' +
//         '<i v-else class="fa fa-circle-o"></i> {{item.name}}' +
//         '</a>',
//         '</li>'
//     ].join('')
// });


//注册菜单组件


var vm = new Vue({
    el: '#rrapp',
    data: {
        user: {},
        menuList: {},
        form: {
            password: '',
            newPassword: ''
        },
    },
    methods: {
        getUser: function () {
            $.getJSON(baseURL + "account/findAccount", function (r) {
                vm.user = r.data;

                //console.log(r)
            });
        },
        updatePassword: function () {

            layer.open({
                type: 1,
                title: "修改密码",
                offset: '100px',
                area: ['550px', '270px'],
                shade: 0,
                shadeClose: false,
                content: $("#passwordLayer"),
                btn: ['修改', '取消'],
                btn1: function (index) {
                    $.ajax({
                        type: "GET",
                        url: baseURL + "/account/updPassword",
                        dataType: "json",
                        contentType: "application/json",
                        data: {password: $("#oldPass").val(), newPassword: $("#newPass").val()},
                        success: function (r) {
                            if (r.code == 200) {
                                // layer.close(index);
                                alert('修改成功', function () {
                                    location.reload();
                                });
                            } else {
                                alert(r.msg);
                            }
                        }
                    });
                }
            });
        },
        logout: function () {
            $.ajax({
                type: "GET",
                url: baseURL + "account/logout",
                dataType: "json",
                success: function (r) {
                    //跳转到登录页面
                    location.href = 'login.html';
                }
            });
        }
    },
    created: function () {

        this.getUser();
    }
    // updated: function(){
    //     //路由
    //     var router = new Router();
    //     routerList(router, vm.menuList);
    //     router.start();
    // }
});


//
// function routerList(router, menuList){
//     for(var key in menuList){
//         var menu = menuList[key];
//         if(menu.type == 0){
//             routerList(router, menu.list);
//         }else if(menu.type == 1){
//             if(menu.url == 'druid/sql.html'){
//                 menu.url = baseURL + 'druid/sql.html';
//             }
//             router.add('#'+menu.url, function() {
//                 var url = window.location.hash;
//
//                 //替换iframe的url
//                 vm.main = url.replace('#', '');
//                 console.log(vm.main);
//
//                 //导航菜单展开
//                 $(".treeview-menu li").removeClass("active");
//                 $(".sidebar-menu li").removeClass("active");
//                 $("a[href='"+url+"']").parents("li").addClass("active");
//
//                 vm.navTitle = $("a[href='"+url+"']").text();
//             });
//         }
//     }
// }

function goIndex() {
    location.href = "login.html";
}
