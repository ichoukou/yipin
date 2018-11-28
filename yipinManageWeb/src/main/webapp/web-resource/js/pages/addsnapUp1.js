$(function(){	
var yipin = yipin || {};
	
	// 初始化页面加载
	 yipin.initContent = function(){
		var firstChar = $("#firstChar").val();
		if($.trim(firstChar)!=null && firstChar!=undefined){
			queryProduct(firstChar);
		}
	};
	
	
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
				var time=$this.attr("data-time");
				if($("#qzBox").find("#goods"+Id).length<=0){
					
					 html+='<li class="cf" id="goods'+Id+'">'+
						'<img src="'+imgSrc+'" alt="" class="fl"  width="58" height="66"/>'+
						'<div class="fl qz_time">'+
							'<p>商品权重：<input name="rankId" class="txt-input W50" onkeyup="javascript:replaceRankText(this);"/></p>'+
							'<div>预计发货时间：'+time+''+
							'</div>'+
						'</div>'+
					'</li>';
				}
			 });
			 $("#qzBox").append(html);
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
		var time=$li.attr("data-time");
		var imgSrc=$li.find("img").attr("src");
		var subBoo=$parent.find(".subCheck").length==$parent.find(".subCheck:checked").length?true:false;
		var html ="";
		$parent.prev().find(".allCheck").prop("checked",subBoo);
		if($(this).is(":checked")){
			
			 html+='<li class="cf" id="goods'+Id+'">'+
				'<img src="'+imgSrc+'" alt="" class="fl"  width="58" height="66"/>'+
				'<div class="fl qz_time">'+
					'<p>商品权重：<input name="rankId" class="txt-input W50" onkeyup="javascript:replaceRankText(this);"/></p>'+
					'<div>预计发货时间：'+time+''+
					'</div>'+
				'</div>'+
			'</li>';
	
				$("#qzBox").append(html);
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
			 $("#qzBox").find("#goods"+Id).remove();
		}
	});
	

	// 取消
	$("#cancelForm").on("click",function() {
		$('#addSaleZoneFrm')[0].reset();
		window.location = _ctxPath+ '/admin/zone/zone-searchZone.htm';
	});

	// 提交
	$("#submitForm").on("click",function() {
		
		$("#submitForm").attr("disabled",true);
		
		var formZone = $('#addSaleZoneFrm');
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
								content : "保存失败！",
								time : 2000
							});
							$("#submitForm").attr("disabled",false);
							$(".d-close").hide();
							break;
						case '2':
							if (confirm("保存成功！")) {
								window.location = _ctxPath+ '/admin/zone/zone-searchZone.htm';
							}
							break;
						}
					},
					dataType : 'json'
				});

		}

	});
	
	yipin.init = (function() {
		yipin.initContent();
	})();
	
	
});

$.formValidator.initConfig({formID:"addSaleZoneFrm",theme:"Default",validatorGroup: '1',submitOnce:false,wideWord:false,onError:function(){}});
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
	//查询首字母对应品牌商品
	var queryProduct = function(brandNo){
		zid = $("#zoneId").val();
		$("#firstChar").val(brandNo);
		var urlPath = _ctxPath+"/admin/product/product-listProducts.htm";
		var brand_temp = "p-list";
		var flag = true;
		$.ajax({
			url : urlPath,
			type : "POST",
			data : {'firstChar' : brandNo,'saleType':2,'zoneId':zid},
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
						var time=$li.attr("data-time"); // 预计发货时间
						var rankValue=$li.attr("data-rank"); // 商品权重
						var imgSrc=$li.find("img").attr("src");
						if($("#qzBox").find("#goods"+Id).length<=0){
							
							 ohtml+='<li class="cf" id="goods'+Id+'">'+
								'<img src="'+imgSrc+'" alt="" class="fl" width="58" height="66"/>'+
								'<div class="fl qz_time">'+
								"<p>商品权重：<input name='rankId' class='txt-input W50' value='"+rankValue+"' onkeyup='javascript:replaceRankText(this);'/>"+
								'<div>预计发货时间：'+time+''+
									'</div>'+
								'</div>'+
							'</li>';
						}
					});
					$("#qzBox").append(ohtml);
					$("#"+brand_temp).show();
				
				}
			},
			error : function(res) {
				alert(res.responseText);
			}
			
		});	
	}