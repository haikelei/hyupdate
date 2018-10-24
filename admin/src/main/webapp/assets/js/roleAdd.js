

    $(function () {
        //checkLoginStatus();
        /**
         * 获取所有管理员列表
         */
        ajaxByGET("/hy/account/findPermissions", {start:0,limit:10000}, initPersonCallback);


    })


  function initPersonCallback(result){

        var array = result.data;
        var html = "";
         for (var i = 0; i < array.length; i++) {
          var memo =array[i].memo
          html+= "<div style='margin-left:20px;'><h5>"+memo+"</h5>"
          html+="   <label class='single' style='margin-left: 30px'><input type='radio' name='"+array[i].tag+"' value='male' checked='checked' />是</label>"
          html+="  <label class='single' style='margin-left: 20px'><input type='radio' name='"+array[i].tag+"'  value='female'/>否</label></div>";
          html+="<HR  width='80%'>"
             if(i == array.length-1){
                 html+="<div style='margin-left:20px;'><input name='code' type='text' placeholder='排序值' class='form-control' style='width:380px;'></div><br>";
                 html+="<div style='margin-left:20px;'><input name='memo' type='text' placeholder='权限描述' class='form-control' style='width:380px;'></div><br>";
                 html+="<div style='margin-left:20px;'><input name='name' type='text' placeholder='权限名' class='form-control' style='width:380px;'></div><br>";
                 html+=" <div>"
                 html+=" <input type='button' class='btn btn-default' onclick='addRole()'value='确定' style='margin-left:15px;'/> &nbsp;&nbsp;"
                html+="<input type='button' class='btn btn-default' onclick='history.go(-1)' value='返回' />   </div>"

             }

      }
      $("#permission").append(html)

    }


function addRole() {

    ajaxByGET("/hy/account/findPermissions", {start:0,limit:10000}, findPer);

}


    function findPer(result) {

        var array = result.data;
        var ids = "";
        for (var i = 0; i < array.length; i++) {
            var tag = array[i].tag;

            var hah=$("input:radio[name="+tag+"]:checked").val();

            if(hah=="female"){

            }else {
                if(i==array.length-1){
                    ids+=array[i].id;
                }else{
                    ids+=array[i].id+";";
                }
            }
        }
        var code =$("input[name='code']").val();
        var memo = $("input[name='memo']").val();
        var name = $("input[name='name']").val();

        ajaxByGET("/hy/account/addRole",{memo:memo,name:name,code:code,permissionIdlist:ids},forSuccess)
    }


