$(function () {
    /**
     * 检查登录态
     */
    //checkLoginStatus();

    /**
     * 获取 count
     */
    ajaxByPOST("/hy/album/findAlbumByBestCount", {id: 1}, initPersonCallback);
})
/**
 * 最大值id
 */
var maxId = 0;
/**
 * 当前页码
 */
var currentPage = 1;
/**
 * 一页显示条数
 */
var showNum = 10;
/**
 * select类型
 */
var type = 0;

function initPersonCallback(result) {
    var count = result;
    var page = Math.ceil(count / showNum);
    pagination(count, page, initAllPersonPage);
}

function initAllPersonPage(page) {
    currentPage = page;
    ajaxByPOST("/hy/album/findAlbumByBest", {start: page  , limit: showNum, id: 1}, listAllPersonCallBack);

}

function listAllPersonCallBack(result) {
    var array = result.data;
    var table = $("#table");
    table.empty();
    for (var i = 0; i < array.length; i++) {
        var td = "<tr>";
        var id = array[i].id;
        //td += "<td style='vertical-align: middle;'>" + ((currentPage - 1) * 10 + i + 1) + "</td>";
        td += "<td >" + array[i].id + "</td>";
        var username = array[i].username == undefined ? " " : array[i].username;
        td += "<td style='vertical-align: middle;'>" + username + "</td>";

        var albumName = array[i].albumName == undefined ? " " : array[i].albumName;
        td += "<td style='vertical-align: middle;'>" + albumName + "</td>";

        var detail = array[i].detail == undefined ? " " : array[i].detail;
        td += "<td style='vertical-align: middle;'>" + detail + "</td>";

        td += "<td style='vertical-align: middle;width: 15%'><button type='button' class='btn btn-primary' onclick='delBest(" + array[i].id + ")'>删除精品</button></td>";

        table.append(td);
    }
}

function delBest(albumId) {
    ajaxByPOST("/hy/album/delAlbumByBest", {albumId: albumId}, initDelSuccess)
}
function initDelSuccess(result) {
    if (result.code == 200) {
        alert("删除成功！")
        cancel()
    } else {
        alert(result.msg)
    }
}

function cancel() {
    location.reload();
}

findAlbumList()

// 所有专辑列表
function findAlbumList() {
    ajaxByPOST("/hy/album/findAlbumForAccount", {start: 0, limit: 1000000000}, listSelectCallBack);
}

function listSelectCallBack(result) {
    var role_html = ''
    var baseCompany = "专辑列表"
    for (var i = 0; i < result.data.length; i++) {
        if (i == 0) {
            role_html += '<option value="" selected="selected">' + baseCompany + '</option>'
        }
        role_html += '<option value="' + result.data[i].id + '">' + result.data[i].albumName + '</option>'
        $("#selectForAlbum").html(role_html)
    }
}

// 增加精品页面
function addBest() {
    // window.location= "albumAdd.html";
    layer.open({
        type: 1,
        shade: false,
        title: '增加精品', //不显示标题
        area: ['300px', '210px'],
        content: $('.modal-body'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响

    });
}

// 保存精品
function bestSubmit() {
    var albumId = $("#selectForAlbum").val();

    ajaxByPOST("/hy/album/addAlbumByBest", {
        id: 1,
        albumId: albumId
    }, initAddSuccess);

}
function initAddSuccess(result) {
    if (result.code == 200) {
        alert("操作成功！")
        cancel()
    } else {
        alert(result.msg)
    }
}
