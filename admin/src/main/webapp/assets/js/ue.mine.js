//每个方法的多余形参是为了防止页面中有多个UE实例
var um;
var editorId;
//渲染编辑器
function renderUE(textareaId, type, param3) {
    textareaId = textareaId == null ? "uecontent" : textareaId;//UE编辑器ID
    type = type == null ? 1 : type;//编辑器类型
    param3 = param3 == null ? true : param3;//第三可选参数:boolean
    editorId = textareaId;
    if (type == 0) {//完全版
        um = UM.getEditor(editorId, {
            autoClearinitialContent: true,////focus时自动清空初始化时的内容
            wordCount: false,////关闭字数统计
        });
    } else if (type == 1) {//普通版
        um = UM.getEditor(editorId, {
            autoClearinitialContent: true,
            wordCount: true,
            //去掉指定按钮
            toolbar: (function () {
                var toolbar = UMEDITOR_CONFIG.toolbar;
                $.each(toolbar, function (k, v) {
                    toolbar[k] = v.replace('map', '');//去掉地图
                });
                $.each(toolbar, function (k, v) {
                    toolbar[k] = v.replace('print', '');//去掉打印
                });
                return toolbar;
            })()
        });
    } else if (type == 2) {//精简版
        um = UM.getEditor(editorId, {
            autoClearinitialContent: true,
            wordCount: true,
            //指定要那些工具项
            toolbar: ['undo redo bold italic underline strikethrough forecolor backcolor removeformat insertorderedlist insertunorderedlist name-fontsize edui-combobox justifyleft justifycenter justifyright emotion formula drafts']
        });
    } else if (type == 3) {//全屏幕版
        um = UM.getEditor(editorId, {
            autoClearinitialContent: true,
            wordCount: true,
            fullscreen: true, //初始就是全屏状态
            withoutToolbar: param3, //设置toolbar不占位
            toolbar: (function () { //去掉全屏按钮
                var toolbar = UMEDITOR_CONFIG.toolbar;
                $.each(toolbar, function (k, v) {
                    toolbar[k] = v.replace('fullscreen', '');
                });
                return toolbar;
            })()
        });
    }
    return um;
}

//添加内容：isAppendTo=true时表示追加,无参数或isAppendTo=false时表示覆盖
function ue_setContent(zxxcontent, eId) {
    if (zxxcontent == null) return;
    editorId = (eId == null) ? editorId : eId;
    UM.getEditor(editorId).setContent(zxxcontent, false);
}
function ue_setContentAppend(zxxcontent, eId) {
    if (zxxcontent == null) return;
    editorId = (eId == null) ? editorId : eId;
    UM.getEditor(editorId).setContent(zxxcontent, true);
}

//获取内容
function ue_getContent(eId) {
    editorId = (eId == null) ? editorId : eId;
    return UM.getEditor(editorId).getContent();
}
//获得HTML代码文本
function ue_getAllHtml(eId) {
    editorId = (eId == null) ? editorId : eId;
    return UM.getEditor(editorId).getAllHtml();
}
//使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容
function getPlainTxt(eId) {
    editorId = (eId == null) ? editorId : eId;
    return UM.getEditor(editorId).getPlainTxt();
}
//使用editor.getContentTxt()方法可以获得编辑器的纯文本内容
function getContentTxt(eId) {
    editorId = (eId == null) ? editorId : eId;
    return UM.getEditor(editorId).getContentTxt();
}
//使用editor.hasContents()方法判断编辑器里是否有内容
function hasContent(eId) {
    editorId = (eId == null) ? editorId : eId;
    return UM.getEditor(editorId).hasContents();
}

//清屏
function clearContent(eId) {
    editorId = (eId == null) ? editorId : eId;
    UM.getEditor(editorId).setContent('', false);
}
//设置获得焦点
function setFocus(eId) {
    editorId = (eId == null) ? editorId : eId;
    UM.getEditor(editorId).focus();
}
//插入HTml代码文本
function ue_insertHtml(zxxhtml, zxxUmO) {
    if (zxxhtml == null) return;
    um = (zxxUmO == null) ? um : zxxUmO;
    um.execCommand('insertHtml', zxxhtml);
}
//是否获得焦点
function isFocus(zxxUmO) {
    um = (zxxUmO == null) ? um : zxxUmO;
    return um.isFocus();
}
//取消焦点
function doBlur(zxxUmO) {
    um = (zxxUmO == null) ? um : zxxUmO;
    um.blur();
}