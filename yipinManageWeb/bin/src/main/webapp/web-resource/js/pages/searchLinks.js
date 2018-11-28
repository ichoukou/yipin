﻿//验证
$.formValidator.initConfig({
  validatorGroup: '100020',
  formID: 'form1',
  errorFocus:false, //错误时不聚焦到第一个控件
  theme: 'Default'
});
$("#name").formValidator({
	validatorGroup: '100020',
	onFocus:"请输入正确网站名称",
	tipID:'nameTips'
	}).inputValidator({
	min:1,
	max:24,
	empty:{leftEmpty:false,rightEmpty:false,emptyError:"两边不能有空格"},
	onError:"你输入的网站名称格式错误" 
	}).ajaxValidator({ 
		type : "post",
		dataType : "json",
		async : true,              
		url : _ctxPath+"/admin/link/link-checkName.action", 
		success : function(data){ 
			if(data.info == "true" || $("#tempName").val() == $("#name").val()){
				return true;
			}else{
				return  "该网站名称已存在";
			}
		},              
		onError : "该网站名称已存在"              
		//onWait : "正在进行合法性校验，请稍候..."          
	});
$("#linkUrl").formValidator({
	validatorGroup: '100020',
	empty:false,
	onFocus:"请输入网站链接地址",
	tipID:'linkUrlTips'
	}).inputValidator({
		min:3,
		max:200,
		onError:"你输入网址格式不正确"
	}).regexValidator({
		regExp:"url",
		dataType:"enum",
		onError:"你输入网址格式不正确"});

$("#sort").formValidator({
	validatorGroup: '100020',
  onFocus:"请输入序列号",
  tipID:'sortTips'
}).inputValidator({
	min:1,
	max:3,
	empty:false,
	empty:{leftEmpty:false,rightEmpty:false,emptyError:"两边不能有空格"},
  onError:"请输入1-999之间的整数"
}).regexValidator({
	regExp:"intege1",
	dataType:"enum",
	onError:"请输入1-999之间的整数"});

//显示操作层
$('.links-wrap li').on({
	"mouseover": function(){
		$(this).find('.links-oprea').show();
	},
	 "mouseout": function(){
		$(this).find('.links-oprea').hide();
	 }
});


//弹层 添加
$('#addSale-btn').on("click", function () {
$("input[class='input-key']").val("");
 	$("textarea").val("");	
 	
 var html = $(".add-seo-info").get([0]);
 $.dialog({
	    lock: true,
	    padding: "10px",
	    title:"新增链接页面",
	    content:html,
	    fixed: true,
	    cancel: true,
	    cancelValue:"取  消",
	    ok:function(){
	    	var result = $.formValidator.pageIsValid('100020');
	    	if(result){
	    		var url = _ctxPath+'/admin/link/link-saveLinks.htm';
	    		var data = $('#form1').serialize();
	    		$.post(url,data,function(e){
	    			window.location.href=_ctxPath+'/admin/link/link-searchLinks.htm';
	    		});
	    	}
	    },
	    cancel:function(){
	    	 $("input[class='input-key']").val("");
	    	 $("textarea").val("");
	    	 $(".position").children().remove();
	    	 window.location.reload();
	    },
	    okValue:"保  存"
  });
});


//编辑
$('.J-edit-seo').on("click", function () {
	$("input[class='input-key']").val("");
 		$("textarea").val("");
 	
    var html = $(".add-seo-info").get([0]);
	var linkId = $(this).prev().val();
	$.ajax({
		type:"post",
		dataType:"json",
		data:{'links.linkId':linkId},
		url:_ctxPath+"/admin/link/link-queryLinksByLinkId.htm",
		success:function(data){
			var json = $.parseJSON(data.info);
			console.log(json.linkId);
//			alert(data.info.linkId+"/"+data.info.name+"/"+data.links.linkId+"/"+data.name);
			$('#linkId').val(json.linkId);
			$('#name').val(json.name);
			$('#tempName').val(json.name);
			$('#linkUrl').val(json.linkUrl);
			$('#sort').val(json.sort);
		}
	});
	
	 $.dialog({
		    lock: true,
		    padding: "10px",
		    title:"编辑链接页面",
		    content:html,
		    fixed: true,
		    cancel: true,
		    cancelValue:"取  消",
		    ok:function(){
		    	var result = $.formValidator.pageIsValid('100020');
		    	if(result){
		    		var url = _ctxPath+'/admin/link/link-saveLinks.htm';
		    		var data = $('#form1').serialize();
		    		$.post(url,data,function(e){
		    			window.location.href=_ctxPath+'/admin/link/link-searchLinks.htm';
		    		});
		    	}
		    },
		    cancel:function(){
		    	 $("input[class='input-key']").val("");
		    	 $("textarea").val("");
		    	 $(".position").children().remove();
		    	 window.location.href=_ctxPath+'/admin/link/link-searchLinks.htm';
		    },
		    okValue:"保  存"
	  });
	 
});



/*删除 */
function deleteLinks(linkId){
if(confirm("确认删除该条记录？")){
$.ajax({
	type : 'POST',
	url : _ctxPath+'/admin/link/link-deleteLinks.htm',
	data : {"links.linkId" : linkId},
	success : function(data) {
		window.location.href=_ctxPath+'/admin/link/link-searchLinks.htm';
		}
});
}
}


