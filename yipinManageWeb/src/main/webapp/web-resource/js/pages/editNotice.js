$(function(){
	//ckeditor操作
	CKEDITOR.on('dialogDefinition', function( ev ){
	    var dialogName = ev.data.name;
	    var dialogDefinition = ev.data.definition;
	    if ( dialogName == 'image' ){
	        var infoTab = dialogDefinition.getContents('info');
	        infoTab.add({
	            type : 'button',
	            id : 'upload_image',
	            align : 'center',
	            label : '添加图片',
	            onClick : function( evt ){
	                var thisDialog = this.getDialog();
	                var txtUrlObj = thisDialog.getContentElement('info', 'txtUrl');
	                var txtUrlId = txtUrlObj.getInputElement().$.id;
	                addUploadImage(txtUrlId);
	            }
	
	        },"txtAlt");
	    }
	});
	function addUploadImage(theURLElementId){
	    var uploadUrl = "${_ctxPath}/upload/image-upload.htm";
	    var imgUrl = window.showModalDialog(uploadUrl,'title',
				'resizable=no;help=no;status=no;dialogWidth=400px;dialogHeight=300px'); 
	    var urlObj = document.getElementById(theURLElementId);
	    if(imgUrl){
		    urlObj.value = imgUrl;
		    onchangeFun(urlObj);
	    }
	}
	function onchangeFun(obj){
		if (document.all) {
			obj.fireEvent("onchange");
		} else {
			var evt = document.createEvent('HTMLEvents');
			evt.initEvent('change', true, true);
			obj.dispatchEvent(evt);
		} 
	}
	var editor = CKEDITOR.replace("content");
	//验证
	$.formValidator.initConfig({formID:"form1",theme:"Default",errorFocus:false,wideWord:false,submitOnce:true,onError:function(){}});
	$("#title").formValidator({onShow:"请输入标题",onFocus:"请输入标题"}).inputValidator({min:1,max:50,onError:"请输入1-50个字符的标题"});
	$("#type").formValidator({onShow:"请选公告类型",onFocus:"请选公告类型"}).inputValidator({min:1,onError: "请选公告类型！"});
	$("#source").formValidator({onShow:"请输入来源",onFocus:"请输入来源"}).inputValidator({min:1,max:12,onError:"请输入1-12个字符的来源"});
	$("#content").formValidator({
		tipId : "contentTip",
		onFocus : "请填写公告内容",
		onCorrect : "谢谢你的配合"
	}).functionValidator({
		fun : function() {
			var content = editor.getData();
			if(content.length == 0){
				return "公告内容不能为空";
			}
		}
	});

});

//跳转查询页面
function turnSearch(){
	window.location.href=_ctxPath+"/admin/notice/notice-searchNotice.htm";
};