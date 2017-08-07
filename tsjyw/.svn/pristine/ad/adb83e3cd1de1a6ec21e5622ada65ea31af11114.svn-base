<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
 -->
	<script src="../ueditor/ueditor.config.js"></script>
	<script src="../ueditor/ueditor.all.js"></script>
	<script src="../ueditor/lang/zh-cn/zh-cn.js"></script>
	
<input type="hidden" id="ue_content" name="ue_content" value="" />
<script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>

<script type="text/javascript">
    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例

    var ue = UE.getEditor('editor');
	
	function ue_init(paraName) {
		$("#ue_content").attr("name", paraName);
	}
	
	function ue_onsubmit() {
		$("#ue_content").val(ue_getContent());
	}
	
    function ue_isFocus(e){
        alert(UE.getEditor('editor').isFocus());
        UE.dom.domUtils.preventDefault(e)
    }
    function ue_setblur(e){
        UE.getEditor('editor').blur();
        UE.dom.domUtils.preventDefault(e)
    }
    function ue_insertHtml(value) {
        UE.getEditor('editor').execCommand('insertHtml', value)
    }
    function ue_createEditor() {
        enableBtn();
        UE.getEditor('editor');
    }
    function ue_getAllHtml() {
        return UE.getEditor('editor').getAllHtml();
    }
    function ue_getContent() {
        return UE.getEditor('editor').getContent();
    }
    function ue_getPlainTxt() {
        return UE.getEditor('editor').getPlainTxt();
    }
    function ue_setContent(content, isAppendTo) {
        //UE.getEditor('editor').setContent(content, isAppendTo);
        var c = content;
        var ue = UE.getEditor('editor');
        //ue.setContent(c, false);
        ue.addListener("ready", function () {
        	ue.setContent(c, false);
        });
    }
    function setDisabled() {
        UE.getEditor('editor').setDisabled('fullscreen');
        disableBtn("enable");
    }

    function setEnabled() {
        UE.getEditor('editor').setEnabled();
        enableBtn();
    }

    function getText() {
        //当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
        var range = UE.getEditor('editor').selection.getRange();
        range.select();
        return UE.getEditor('editor').selection.getText();
    }

    function getContentTxt() {
        return UE.getEditor('editor').getContentTxt();
    }
    function hasContent() {
        return UE.getEditor('editor').hasContents();
    }
    function setFocus() {
        UE.getEditor('editor').focus();
    }
    function deleteEditor() {
        disableBtn();
        UE.getEditor('editor').destroy();
    }
    function disableBtn(str) {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            if (btn.id == str) {
                UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
            } else {
                btn.setAttribute("disabled", "true");
            }
        }
    }
    function enableBtn() {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
        }
    }

    function getLocalData () {
        return UE.getEditor('editor').execCommand( "getlocaldata" );
    }

    function clearLocalData () {
        UE.getEditor('editor').execCommand( "clearlocaldata" );
    }
</script>
