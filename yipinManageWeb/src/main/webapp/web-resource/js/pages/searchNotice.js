$(function(){
	//查询按钮绑定事件
	$('#search').bind('click', function() {
		$("#searchForm").attr("action",_ctxPath+"admin/notice/notice-searchNotice.htm");
		$("#searchForm").submit();
	});
	//复选框事件
	$('.tab-control').Merlin({
			// 全选
	    "checkAll": {
	      "eType":'on', // 事件类型
        "checkAll":'input[name="checkAll"]', // 全选按钮
        "checkSub":'input[name="checkSub"]' // 子选择按钮
	    }
    });
	//“新增公告”按钮绑定事件
	$(".J-editAd").bind('click', function() {
		window.location.href=_ctxPath+"/admin/notice/notice-addNotice.htm";
	});
	//“审核通过”按钮绑定事件
	$('#checkNotice').bind('click', function() {
		var noticeIds = allCheckedVal();
		if(!noticeIds){
			alert("请选中要“审核”的记录！");
			return false;
		}
		if(confirm("确认“审核通过”选中的记录？")){
			$.ajax({
				type : 'POST',
				url : _ctxPath+'/admin/notice/notice-checkNotice.htm',
				data : {"noticeIds" : noticeIds},
				success : function(data) {
					if(data){
						alert(data.info);
					}
// 					window.location.reload();
					window.location.href=window.location.href;
					}
			});
		}
	});
	//“批量删除”按钮绑定事件
	$('#batchDel').bind('click', function() {
		var noticeIds = allCheckedVal();
		if(!noticeIds){
			alert("请选中要“删除”的记录！");
			return false;
		}
		if(confirm("确认删除选中的记录？")){
			$.ajax({
				type : 'POST',
				url : _ctxPath+'/admin/notice/notice-deleteNotice.htm',
				data : {"noticeIds" : noticeIds},
				success : function(data) {
					if(data){
						alert(data.info);
					}
// 					window.location.reload();
					window.location.href=window.location.href;
					}
			});
		}
	});
	
	
	//更新公告置顶状态
	function updateNoticeTop(noticeId,isTop){
		var noTop = '${TOP_NO}'; //取消置顶
		var yesTop = '${TOP_YES}'; //置顶
		var msg="确认“置顶”该条记录？";
		if(noTop == isTop){
			msg = "确认该条记录“取消置顶”？";
		}
		if(confirm(msg)){
			$.ajax({
				type : 'POST',
				url : _ctxPath+'/admin/notice/notice-updateNoticeTop.htm',
				data : {"notice.noticeId" : noticeId,"notice.isTop":isTop},
				success : function(data) {
					if(data){
						alert(data.info);
					}
//	 				window.location.reload();
					window.location.href=window.location.href;
					}
			});
		}
	}
	
	
	//获取所有选中复选框的值
	function allCheckedVal(){
		var str="";
		$("input:checkbox[name='checkSub']:checked").each(function(){
			str += $(this).val()+",";
		});
		return str.substring(0,str.length-1);
	}
	
});

//删除单条公告
function deleteNotice(noticeId){
	if(confirm("确认删除该条记录？")){
		$.ajax({
			type : 'POST',
			url : _ctxPath+'/admin/notice/notice-deleteNotice.htm',
			data : {"noticeIds" : noticeId},
			success : function(data) {
				if(data){
					alert(data.info);
				}
// 				window.location.reload();
				window.location.href=window.location.href;
				}
		});
	}
}