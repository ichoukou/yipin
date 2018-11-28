$(function(){	
	//编辑排序
	$(".J_edit").on("click",function(){
		var $this=$(this);
		var $input=$this.parent().find(".J_input");
		var $num=$this.parent().find(".J_num");
		var numVal=$num.text();
		if($this.text()=="编辑"){
			$input.show();
			$num.hide();
			$this.text("确定");
			$input.val(numVal);
		}else{
			$input.hide();
			$num.show();
			$this.text("编辑");
			
			// 获取排序序号
			var rankId = parseInt($input.val()); 
			var zid = $input.attr("data-item");
						
			$.ajax({
				type : 'POST',
				url :  _ctxPath+ '/admin/zone/zone-doSort.htm',
				data : {'zone.zoneId':zid,'zone.rank':rankId},
				success : function(data) {
					switch (data.info) {
					case '1':
						$.dialog({
							title : false,
							content : "排序修改失败！",
							time : 2000
						});
						$(".d-close").hide();
						
						$num.text(numVal);
						break;
					case '2':
						$.dialog({
							title : false,
							content : "排序修改成功！",
							time : 2000
						});
						$(".d-close").hide();
						$num.text($input.val());
						window.location.reload();
						break;
					}
				},
				dataType : 'json'
			});
		}
	});
		
		
		//编辑排数
		$(".J_edits").on("click",function(){
			var $this=$(this);
			var $input=$this.parent().find(".J_inputs");
			var $num=$this.parent().find(".J_num");
			var numVal=$num.text();
			if($this.text()=="编辑"){
				$input.show();
				$num.hide();
				$this.text("确定");
				$input.val(numVal);
			}else{
				$input.hide();
				$num.show();
				$this.text("编辑");
				
				// 获取排序序号
				var lineNo = parseInt($input.val()); 
				var zid = $input.attr("data-item");
							
				$.ajax({
					type : 'POST',
					url :  _ctxPath+ '/admin/zone/zone-doModifyLineNo.htm',
					data : {'zone.zoneId':zid,'zone.lineNo':lineNo},
					success : function(data) {
						switch (data.info) {
						case '1':
							$.dialog({
								title : false,
								content : "排序修改失败！",
								time : 2000
							});
							$(".d-close").hide();
							
							$num.text(numVal);
							break;
						case '2':
							$.dialog({
								title : false,
								content : "排序修改成功！",
								time : 2000
							});
							$(".d-close").hide();
							$num.text($input.val());
							window.location.reload();
							break;
						}
					},
					dataType : 'json'
				});
			}
	});
	// 文本框只能输入1-10的限制
	$(".J_input").keyup(function(){
		var value = $(this).val();
		$(this).val($(this).val().replace(/[^0-9]/g,''));
		if($(this).val()>10){
			$(this).val(10);
		}
		if($(this).val()==0){
			$(this).val("");
		}
	});
	
	// 文本框只能输入1-4的限制
	$(".J_inputs").keyup(function(){
		var value = $(this).val();
		$(this).val($(this).val().replace(/[^0-4]/g,''));
		if($(this).val()>4){
			$(this).val(4);
		}
		if($(this).val()==0){
			$(this).val("");
		}
	});
});

// 编辑
var editZone = function(zoneId){
	var zid = zoneId;
	window.location = _ctxPath+ '/admin/zone/zone-showUpdateZone.htm?zone.zoneId='+zoneId;
				
}

//发布
var releaseZone = function(zoneId){
	var zid = zoneId;
	if (confirm("确认发布专区！")) {
		$.ajax({
			type : 'POST',
			url :  _ctxPath+ '/admin/zone/zone-doRelease.htm',
			data : {'zone.zoneId':zid},
			success : function(data) {
				switch (data.info) {
				case '1':
					$.dialog({
						title : false,
						content : "发布失败！",
						time : 2000
					});
					$(".d-close").hide();
					break;
				case '2':
					$.dialog({
						title : false,
						content : "发布成功！",
						time : 2000
					});
					$(".d-close").hide();
					window.location.reload();
					break;
				}
			},
			dataType : 'json'
		});
	}
}


// 查看
var showZone = function(zoneId){
	window.location = _ctxPath+ '/admin/zone/zone-showZone.htm?zone.zoneId='+zoneId;
}


//下架
var offZone = function(zoneId){
	var zid = zoneId;
	if (confirm("确认下架专区！")) {
		$.ajax({
			type : 'POST',
			url :  _ctxPath+ '/admin/zone/zone-doOff.htm',
			data : {'zone.zoneId':zid},
			success : function(data) {
				switch (data.info) {
				case '1':
					$.dialog({
						title : false,
						content : "下架失败！",
						time : 2000
					});
					$(".d-close").hide();
					break;
				case '2':
					$.dialog({
						title : false,
						content : "下架成功！",
						time : 2000
					});
					$(".d-close").hide();
					window.location.reload();
					break;
				}
			},
			dataType : 'json'
		});
	}
	
				
}