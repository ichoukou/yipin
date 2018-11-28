$(function(){	
	
var yipin = yipin || {};
	
	// 初始化页面加载
	 yipin.initContent = function(){
		var firstChar = $("#firstChar").val();
		if($.trim(firstChar)!=null && firstChar!=undefined){
			queryProduct(firstChar);
		}
	};
	
	yipin.init = (function() {
		yipin.initContent();
	})();
	
	//全选和反选
	$(".seller").on("click",".allCheck",function(){
		var $sub=$(this).parent().next().find(".subCheck");
		var html="";
		if($(this).is(":checked")){
			 $sub.prop("checked",true);
			 $(this).parent().next().find("li").each(function(){
				var $this=$(this);
				var Id=$this.attr("data-id");
				var imgSrc=$this.find("img").attr("src");
				if($("#qzBox").find("#goods"+Id).length<=0){
					 html+='<li class="cf" id="goods'+Id+'">'+
					'<img src="'+imgSrc+'" alt="" class="fl" width="58" height="66"/>'+
					'<div class="fl qz_time">'+
							'<p>商品权重：<input name="rankId" class="txt-input W50" onkeyup="javascript:replaceRankText(this);"/></p>'+
							'<div>抢购时间：<input type="input" name="beginTime" class="txt-input Wdate startTime"/>'+
								'<span class="m5">至</span>'+
								'<input type="input" name="endTime" class="txt-input Wdate endTime"/>'+
							'</div>'+
						'</div>'+
					'</li>';
				}
			 });
			 $("#qzBox").append(html);
			 wdate();
			//预览价格和规格
			$(".J_msg").hover(function(){
				$(this).find(".defaSku").show();
			},function(){
				$(this).find(".defaSku").hide();
			});
			//
			$("#prolist ul").each(function(){
				var num=$(this).find("li").length;
				$(this).find("li").each(function(i){
					$(this).css("z-index",num-i);	
				})
				
			});
		}else{
			 $sub.prop("checked",false);
			 $(this).parent().next().find("li").each(function(){
					var $this=$(this);
					var Id=$this.attr("data-id");
					$("#qzBox").find("#goods"+Id).remove();
			});
		}
	});
	//单个订单全选
	$(".seller").on("click",".subCheck",function(){
		var $this=$(this);
		var $parent=$this.parents("ul");
		var $li=$this.parents("li");
		var Id=$li.attr("data-id");
		var imgSrc=$li.find("img").attr("src");
		var subBoo=$parent.find(".subCheck").length==$parent.find(".subCheck:checked").length?true:false;
		var html="";
		$parent.prev().find(".allCheck").prop("checked",subBoo);
		if($(this).is(":checked")){
			 html+='<li class="cf" id="goods'+Id+'">'+
			'<img src="'+imgSrc+'" alt="" class="fl" width="58" height="66"/>'+
			'<div class="fl qz_time">'+
					'<p>商品权重：<input name="rankId" class="txt-input W50" onkeyup="javascript:replaceRankText(this);"/></p>'+
					'<div>抢购时间：<input type="input" name="beginTime" class="txt-input Wdate startTime"/>'+
						'<span class="m5">至</span>'+
						'<input type="input"  name="endTime" class="txt-input Wdate endTime"/>'+
					'</div>'+
				'</div>'+
			'</li>';
			
		
				$("#qzBox").append(html);
				wdate();
				//预览价格和规格
				$(".J_msg").hover(function(){
					$(this).find(".defaSku").show();
				},function(){
					$(this).find(".defaSku").hide();
				});
		}else{
			 $("#qzBox").find("#goods"+Id).remove();
		}
	});
	$("#startTime").on('click', function () {
		var $dateTip = $('#dateTip');
		WdatePicker({
			"onpicked": function () {
				var $this = $(this),
					startVal = $this.val(),
					endVal = $("#endTime").val(),
					checkRangeDate = rangeDate(startVal, endVal);
				// 验证是否正确选择日期
				if (startVal != '' && endVal != '' && !checkRangeDate) {
					showIcon.error($dateTip, '起始日期不能大于结束日期');
				} else {
					$dateTip.empty();
				}	
				$this.trigger('blur');
			},
			//"startDate": '#F{$dp.$D(\'startTime\')}', // 开始日期
			//"minDate": '#F{$dp.$D(\'startTime\')}', // 最小日期
			// "maxDate": '%y-%M-%d', // 最大日期
			"dateFmt":'yyyy-MM-dd HH:mm:ss',
			"isShowClear": true, // 是否显示清空按钮
			"readOnly": true // 是否只读
		});
     });
	
	
	$("#selectTime").on('click',function(){
		var startTimeVal=$("#startTime").val();
		$(".startTime").each(function(){
			$(this).val(startTimeVal);
		});
		var endTimeVal=$("#endTime").val();
		$(".endTime").each(function(){
			$(this).val(endTimeVal);
		});
		
	});
	

	 $("#endTime").on('click', function () {
		var $dateTip = $('#dateTip');
		WdatePicker({
			"onpicked": function () {
				var $this = $(this),
					endVal = $this.val(),
					startVal = $("#startTime").val(),
					checkRangeDate = rangeDate(startVal, endVal);

				// 验证是否正确选择日期
				if (startVal != '' && endVal != '' && !checkRangeDate) {
					showIcon.error($dateTip, '起始日期不能大于结束日期');
				} else {
					$dateTip.empty();
				}
				$this.trigger('blur');
			},
			//"startDate": '#F{$dp.$D(\'startTime\')}', // 开始日期
			//"minDate": '#F{$dp.$D(\'startTime\')}', // 最小日期
			// "maxDate": '%y-%M-%d', // 最大日期
			"dateFmt":'yyyy-MM-dd HH:mm:ss',
			"isShowClear": true, // 是否显示清空按钮
			"readOnly": true // 是否只读
		});
     });
	 showIcon={
        correct: function (el, text) {
            el.show().html('<span class="yto_onCorrect">' + text + '</span>');
        },
        error: function (el, text) {
            el.show().html('<span class="onError_top">' + text + '</span>');
        },
        show: function (el, text) {
            el.show().html('<span class="yto_onShow">' + text + '</span>');
        }
    }
	 //验证日历
	 function rangeDate(startVal, endVal) {
            if (startVal != '') {
                var startUTC = (Date.parse(startVal.replace(/-/g, '/')) / 1000),
                    formatUTC = (Date.parse(endVal.replace(/-/g, '/')) / 1000),
                    dateGap = parseInt(formatUTC - startUTC);
                // return 这里写验证条件
                return (dateGap >= 0);
            }
        }
	 var wdate = function(){
		$(".Wdate").each(function(){
			$(this).focus(function(){
				WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})
			});
		});
	}
});
$.formValidator.initConfig({formID:"addSpecialZoneFrm",theme:"Default",validatorGroup: '1',submitOnce:true,wideWord:false,onError:function(){}});
$('#zonename').formValidator({
    "onShow": '请输入4-10个中英文字符',
    "onFocus": '请输入4-10个中英文字符',
    "onCorrect": ''
}).inputValidator({
        "min": 4,
        "max": 10,
        "onError": '请输入4-10个中英文字符'
    }).functionValidator({
        fun: function (val, el) {
            val = $.trim(val);
            var regText =  /[A-Za-z\u4E00-\u9FA5\uF900-\uFA2D]/;
            if (!regText.test(val)) {
                return '请输入4-10个中英文字符'
            }
        }
    });
$('#lineno').formValidator({
    "onShow": '请输入专区排数',
    "onFocus": '请输入专区排数',
    "onCorrect": ''
}).inputValidator({
        "min": 1,
        "onError": '请输入专区排数'
    });
// 取消
$("#cancelForm").on("click",function() {
	$('#addSpecialZoneFrm')[0].reset();
	window.location = _ctxPath+ '/admin/zone/zone-searchZone.htm';
});

// 提交
$("#submitForm").on("click",function() {
	$("#submitForm").attr("disabled",true);
	var formZone = $('#addSpecialZoneFrm');
	var result = $.formValidator.pageIsValid('1');
   
	if (result) {
		$.ajax({
					type : 'POST',
					url : _ctxPath+ "/admin/zone/zone-saveZone.htm",
					data : formZone.serialize(),
					success : function(data) {
						switch (data.info) {
						case '1':
							$.dialog({
								title : false,
								content : "保存失败!",
								time : 2000
							});
							$(".d-close").hide();
							$("#submitForm").attr("disabled",false);
							break;
						case '2':
							if (confirm("保存成功!")) {
								window.location = _ctxPath+ '/admin/zone/zone-searchZone.htm';
							}
							break;
						}
					},
					dataType : 'json'
				});

	} 
});

var wdate=function(){
	$(".Wdate").each(function(){
		$(this).focus(function(){
			WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})
		});
	});
}

// 文本框只能输入1-4的限制
var replaceLineText = function(input){
	var value = $(input).val();
	$(input).val($(input).val().replace(/[^0-4]/g,''));
	if($(input).val()>4){
		$(input).val(4);
	}
	if($(input).val()<1){
		$(input).val("");
	}
}

// 文本框只能输入1-10的限制
function replaceRankText(input){
	var value = $(input).val();
	$(input).val($(input).val().replace(/[^0-9]/g,''));
	if($(input).val()>10){
		$(input).val(10);
	}
	if($(input).val()==0){
		$(input).val("");
	}
}
	
	// 查询首字母对应品牌商品
	var queryProduct = function(brandNo){
		zid = $("#zoneId").val();
		$("#firstChar").val(brandNo);
		var urlPath = _ctxPath+"/admin/product/product-listProductsSku.htm";
		var brand_temp = "p-list";
		$.ajax({
			url : urlPath,
			type : "POST",
			data :  {'firstChar' : brandNo,'saleType':1,'zoneId':zid},
			dataType : "html",
			success : function(res) {
				if(res!='' && res!=null){
					var data="<div id=\""+brand_temp+"\" class=\"seller_list cf\">"+res+"</div>";
					$("#prolist").html("");
					$("#qzBox").html("");
					$("#prolist").append($(data));
					//预览价格和规格
					$(".J_msg").hover(function(){
						$(this).find(".defaSku").show();
					},function(){
						$(this).find(".defaSku").hide();
					});
					//
					$("#prolist ul").each(function(){
						var num=$(this).find("li").length;
						$(this).find("li").each(function(i){
							$(this).css("z-index",num-i);	
						})
						
					});
					var items=$(".pid"); // 选上ID
					var arr =[];
					$.each(items,function(){
						arr.push($(this).val());
					});
					for(var i=0;i<arr.length;i++){
						$(".seller li[data-id='"+arr[i]+"']").find(".subCheck").prop("checked",true);
					}
					//
					var ohtml="";
					$(".seller .subCheck:checked").each(function(){
						var $this=$(this);
						var $li=$this.parents("li");
						var Id=$li.attr("data-id");
						var btime=$li.attr("data-btime"); // 抢购开始时间
						var etime = $li.attr("data-etime");// 抢购结束时间
						var rankValue=$li.attr("data-rank"); // 商品权重
						var imgSrc=$li.find("img").attr("src");
						if($("#qzBox").find("#goods"+Id).length<=0){
						
							 ohtml+='<li class="cf" id="goods'+Id+'">'+
							'<img src="'+imgSrc+'" alt="" class="fl" width="58" height="66"/>'+
							'<div class="fl qz_time">'+
							"<p>商品权重：<input name='rankId' class='txt-input W50' value='"+rankValue+"' onkeyup='javascript:replaceRankText(this);'/>"+
									"<div>抢购时间：<input class='txt-input Wdate startTime' name='beginTime' value='"+btime+"'/>"+
										'<span class="m5">至</span>'+
										"<input class='txt-input Wdate endTime' name='endTime' value='"+etime+"'/>"+
									'</div>'+
								'</div>'+
							'</li>';
						}
					});
					
					$("#qzBox").append(ohtml);
					wdate();
					$("#"+brand_temp).show();
				}
			},
			error : function(res) {
				alert(res.responseText);
			}
		});
	}
	