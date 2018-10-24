var id = T.p('id');
var vm = new Vue({
    el: '#rrapp',
    data: {
        title: null,
        article: {},
        user: {username: null},
    },
    created: function () {
        if (id) {
            this.getUser();
            this.getInfo(id);
        } else {
            alert("非法访问！", function () {
                location.href = nginxURL + "main.html";
            });
        }
    },
    methods: {
        update: function (event) {
            vm.article.content = ue_getContent();
            $.ajax({
                type: "POST",
                url: baseURL + "app/article/update",
                contentType: "application/json",
                data: JSON.stringify(vm.article),
                success: function (r) {
                    if (r.code === 0) {
                        alert('修改成功', function (index) {
                            vm.getInfo(id);
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        getUser: function () {
            $.getJSON(baseURL + "sys/user/info", function (r) {
                vm.user = r.user;
            });
        },
        getInfo: function (id) {
            $.get(baseURL + "app/article/info/" + id, function (r) {
                vm.article = r.article;
                vm.article.author = vm.user.username;
                ue_setContent(vm.article.content);
                vm.title = '修改 "' + vm.article.title + '"';
            });
        }
    }
});