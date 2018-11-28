<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${_jsPath}/jquery/jquery-1.8.1.js"></script>  
<script type="text/javascript" src="${_jsPath}/plugin/uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="${_jsPath }/plugin/artdialog/jquery.artDialog.min.js"></script>
<link rel="stylesheet" type="text/css" href="${_jsPath}/plugin/uploadify/uploadify.css" media="all" />
<link rel="stylesheet" type="text/css" href="${_jsPath }/plugin/artdialog/skins/ytoxl.css?d=${_resVerion}" media="all">

<title>upload image</title>
</head>
<body>
<div>
	<span>上传图片:</span>
	<input type="file" id="imgUpload" name="file"/>
	<div id="showDiv" class="completed_show">
		<img id="showImg" alt="" src="">
		<div class="mask"></div>
		<div class="completed_del"></div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	var $this = $(this),
	completedId = $this.find(".completed_show").attr("id"),
	fileId = $this.find("input").attr("id"); //上传file控件对象
	$('#'+fileId).uploadify({
			'formData':{
				'timestamp':'1386842515',
				'token':'4117e3defacb3cf3464fe71d333818b1',
				'category':'test'
			},
			'swf':'${_jsPath}/plugin/uploadify/uploadify.swf',
			'uploader':'${_ctxPath}/upload/upload.htm',
			'fileObjName': 'file',
	        'queueID': fileId,//与下面的id对应
	        'queueSizeLimit': 1,
	        'fileTypeExts': '*.jpg;*.gif;*.png',
	        'fileTypeDesc': '(.JPG, .GIF, .PNG)',//控制可上传文件的扩展名，启用本项时需同时声明fileDesc
	        'auto': true,
	        'multi': false,
	        'fileSizeLimit': '2048KB',
	        'simUploadLimit': 2,
	        'width':80,
	        'height':80,
	        'buttonText': '',
	        'onUploadSuccess': function (file, data, response) {
	            //处理上传成功后的逻辑
	            $("#showImg").attr("src", '${_fileThumbPath}' + data + "_t84" + file.type); //给图片赋值
	            $("#" + fileId).hide(); //隐藏file控件层
	            $("#" + completedId).show(); //显示完成层图片
	        }
	});
	//显示遮罩和删除层
	$("#" + completedId).on({
		'mouseover':function(){
			var _this = $(this);
			_this.find('.mask').show();
			_this.find('.completed_del').show();
		},
		'mouseout':function(){
			var _this = $(this);
			_this.find('.mask').hide();
			_this.find('.completed_del').hide();
		}
	});
	//删除图片重新释放并可重复以上步骤
	$("#" + completedId).find('.completed_del').on('click',function(){
		$.dialog({
			title: false,
			content: "<span style='color:#C00;'>您确定要删除这张图片？</span>",
	        lock: true,
	        fixed: true,
	        okValue: "确定",
			ok: function(){
				$("#" + completedId).attr('data-url','');
				$("#" + fileId).show();
				$("#" + completedId).hide();
	        },
	        cancel: true,
	        cancelValue: "取消"
		 });
	    $(".d-close").hide();
	});
	
});
</script>
</body>
</html>