$(function(){
	// 定义一个全局类
	var yipin = yipin || {};
	
	 // 显示提示
    yipin.showIcon = {
        correct:function (el, text) {
            el.show().html('<span class="onError-txt">' + text + '</span>');
        },
        error:function (el, text) {
            el.show().html('<span class="onError-txt">' + text + '</span>');
        },
        show:function (el, text) {
            el.show().html('<span class="onError-txt">' + text + '</span>');
        }
    };
	//添加 减少商品
	yipin.goods=function(){
		//减少商品
		$(".order_table .less").on("click",function(){
			var _this=$(this);
			var _input=_this.next();
			var _tr=_this.parents("tr");
			var _inputVal=parseInt(_input.val());
			var price=_tr.attr("data-price");
			var kucun=_tr.attr("data-kucun");
			var jifen=_tr.attr("data-jifen");
			var _productSkuId = _tr.attr("data-product");
			if(_tr.find(".J_outTip").text()!=""){
				var textTip=_tr.find(".J_outTip").text();
				$.dialog({
					"title":false,
					"lock":true,
					"content":"<p class='dialogDiv'>"+textTip+",请重新选择商品",
					"okValue":"确定",
					"ok":true
				});
				$(".d-titleBar").remove();
				return false;
			}
			if(_inputVal<=1){
				return false;
			}else{
				_input.val(_inputVal-1);
				yipin.goodsMoney(_this,_input.val(),price,jifen);
				//自用计算费用
				yipin.freight();
			}
		});
		//增加商品
		$(".order_table .more").on("click",function(){
			var _this=$(this);
			var _input=_this.prev();
			var _tr=_this.parents("tr");
			var _inputVal=parseInt(_input.val());
			var price=_tr.attr("data-price");
			var kucun=_tr.attr("data-kucun");
			var jifen=_tr.attr("data-jifen");
			var _productSkuId = _tr.attr("data-product");
			if(_tr.find(".J_outTip").text()!=""){
				var textTip=_tr.find(".J_outTip").text();
				$.dialog({
					"title":false,
					"lock":true,
					"content":"<p class='dialogDiv'>"+textTip+",请重新选择商品",
					"okValue":"确定",
					"ok":true
				});
				$(".d-titleBar").remove();
				return false;
			}
			if(_inputVal<kucun){
				_input.val(_inputVal+1);
			    yipin.goodsMoney(_this,_input.val(),price,jifen);
				yipin.freight();
			}else{
				$.dialog({
					"title":false,
					"lock":true,
					"content":"<P class='dialogDiv'>不能大于库存</p>",
					"okValue":"确定",
					"ok":true
				});
				$(".d-titleBar").remove();
			}
		});
		//文本框输入
		$(".order_table .len_input").blur(function(){
			var _this=$(this);
			var value=_this.val();
			var _tr=_this.parents("tr");
			var price=_tr.attr("data-price");
			var kucun=_tr.attr("data-kucun");
			var jifen=_tr.attr("data-jifen");
			//判断是否为已售完 或者已下架
			if(_tr.find(".J_outTip").text()!=""){
				var textTip=_tr.find(".J_outTip").text();
				$.dialog({
					"title":false,
					"lock":true,
					"content":"<p class='dialogDiv'>"+textTip+",请重新选择商品",
					"okValue":"确定",
					"ok":true
				});
				$(".d-titleBar").remove();
				return false;
			}
	
			if(number(value,1000000000)>kucun){
				$.dialog({
					"title":false,
					"lock":true,
					"content":"<P class='dialogDiv'>不能大于库存</p>",
					"okValue":"确定",
					"ok":true
				});
				$(".d-titleBar").remove();
				_this.val(number(value,kucun));
			}else{
				//判断不能为0
				if(number(value,kucun)=="0"){
					_this.val(1);	
				}else{
					_this.val(number(value,kucun));	
				}
			}
			//自用计算费用
			yipin.freight();
			
			
			yipin.goodsMoney(_this,_this.val(),price,jifen);
		});
	};
	//删除商品
	yipin.delet=function(){
		//单个删除
		$(".delect").on("click",function(){
			var $this=$(this);
			$.dialog({
				"title":false,
				"lock":true,
				"content":"<P class='dialogDiv'>您确定删除商品吗？</p>",
				"okValue":"确定",
				"ok":function(){
					var $tr=$this.parents("tr");
					var goodsId=$tr.attr("data-product");
					var $parent=$this.parents(".order_table");
					var $orderTh=$parent.prev();
					$tr.fadeOut(500,function(){
						$tr.remove();
						//判断删除整个订单
						if($parent.find("tr").length<1){
							$parent.remove();
							$orderTh.remove();
						}
						//判断购物车里面是否有商品
						if($(".order_th").length<=0){
							$("#no_goods").show();
							$("#order_inventory").hide();
						}
						yipin.goodsMoney();
						yipin.freight();//删除计算重量
					});
				},
				"cancelValue":"取消",
				"cancel":true
			});
			$(".d-titleBar").remove();
		});
		//删除选中的
		$(".recycle").on('click',function(){
			var $checked=$(".subCheck:checked");
			//判断是否选中商品
			if($checked.length<=0){
				$.dialog({
					"title":false,
					"lock":true,
					"content":"<P class='dialogDiv'>请先选择要删除的商品</p>",
					"okValue":"确定",
					"ok":true
				});
			}else{
				$.dialog({
					"title":false,
					"lock":true,
					"content":"<P class='dialogDiv'>您确定删除商品吗？</p>",
					"okValue":"确定",
					"ok":function(){
						$(".subCheck:checked").each(function(){
							var $this=$(this);
							var $tr=$this.parents("tr");
							var goodsId=$tr.attr("data-product");
							var $parent=$this.parents(".order_table");
							var $orderTh=$parent.prev();
							$tr.fadeOut(500,function(){
								$tr.remove();
								//删除整个订单
								if($parent.find("tr").length<1){
									$parent.remove();
									$orderTh.remove();
								}
								//判断购物车里面是否有商品
								if($(".order_th").length<=0){
									$("#no_goods").show();
									$("#order_inventory").hide();
								}
								yipin.goodsMoney();
								yipin.freight();//删除计算重量
							});
						});
					},
					"cancelValue":"取消",
					"cancel":true
				});
							
			}
			$(".d-titleBar").remove();
		});
	}
	//全选 反选
	yipin.check=function(){
		//全选
		$(".allCheck").on("click",function(){
			if($(this).is(":checked")){
				$(".parentCheck,.subCheck,.allCheck").prop("checked",true);
			}else{
				$(".parentCheck,.subCheck,.allCheck").prop("checked",false);
			}
		});
		//订单全选
		$(".parentCheck").on('click',function(){
			var $this=$(this);
			var $parent=$this.parents(".order_th");
			var $next=$parent.next();
			if($this.is(":checked")){
				$next.find(".subCheck").prop("checked",true);
			}else{
				$next.find(".subCheck").prop("checked",false);
			}
			
			var boo=$(".parentCheck").length==$(".parentCheck:checked").length?true:false;
			$(".allCheck").prop("checked",boo);
		});
		//单个订单全选
		$(".subCheck").on('click',function(){
			var $this=$(this);
			var $parent=$this.parents(".order_table");
			var $next=$parent.prev();
			var subBoo=$parent.find(".subCheck").length==$parent.find(".subCheck:checked").length?true:false;
			$next.find(".parentCheck").prop("checked",subBoo);
			var boo=$(".parentCheck").length==$(".parentCheck:checked").length?true:false;
			$(".allCheck").prop("checked",boo);
		});
	}
	//表格颜色处理
	yipin.table=function(){
		//表格隔行变色
		$(".order_table").each(function(){
			$(this).find("tr").each(function(i){
                if (i % 2 != 0) {
                    $(this).addClass("tr_blue");
                }
			});
			$(this).find("tr").hover(function(){
				$(this).addClass("tr_hover");
			},function(){
				$(this).removeClass("tr_hover");
			});
		});
	};
	//过滤出数字
	function number(str,kucun){
		var arr=str.split("");
		var newArr=[];
		var newStr="";
		var feg=/^[0-9]{1}$/;
		for (i=0;i<str.length;i++){
			if(feg.test(arr[i])){
				newArr.push(arr[i]);
			}
		}
		newStr=newArr.join("");
		if(newStr==""){
			newStr="0";
		}else if(parseInt(newStr)>parseInt(kucun)){
			newStr=kucun;
		}
		return parseInt(newStr);
	}
	//加法
	yipin.sum=function(){ //数组求和
        var r=0;
        for (var i=0;i<arguments.length ;i++ )
        {
            r=new BigDecimal(arguments[i].toString()).add(new BigDecimal(r.toString()));
        }
        return r;
    }
	//乘法
	yipin.multiply=function(){ //数组求和
        var r=1;
        for (var i=0;i<arguments.length ;i++ )
        {
            r=new BigDecimal(arguments[i].toString()).multiply(new BigDecimal(r.toString()));
        }
        return r;
    }
	//减法
	yipin.subtract=function(){ //数组求和
        var r=0;
        for (var i=0;i<arguments.length ;i++ )
        {
            r=new BigDecimal(arguments[i].toString()).subtract(new BigDecimal(r.toString()));
        }
        return r;
    }
	//计算金额
	yipin.goodsMoney=function(obj,len,jine){
		if(arguments.length==4){
			var _tr=obj.parents("tr");
			$allPrice=_tr.find(".J-allPrice");
			$allPrice.text(yipin.multiply(len,jine));
		}
		//计算总个数
		var zongLen=0,zongjine=0,zongjifen=0;
		$(".order_inventory .len_input").each(function(){
			var thisVla=$(this).val();
			zongLen=yipin.sum(zongLen,thisVla);
		});
		$(".J_shuliang").text(zongLen);
		$(".J_goodsLen").text(zongLen);
		$(".J_orderLen").text($(".order_th").length);
		//计算总金额
		$(".order_inventory .J-allPrice").each(function(){
			var thisVla=$(this).text();
			zongjine=yipin.sum(zongjine,thisVla);
		});
		$(".J_jine").text(zongjine);
		$(".J-jifen").text(parseInt(zongjine));
		//计算下面的结算金额
		var freightVal=parseInt($('.freight').text());
		var freightFireVal=$('.freightFire').text();
		$(".clearing_msg .allPrice").text(zongjine);
		$(".clearing_msg .price").text(yipin.sum(freightVal,zongjine,freightFireVal));
		$(".clearing_msg .shuliang").text(zongLen);
	};

	//计算出运费
	yipin.freight=function(){
		//遍历上面总商品
		var freight=0,freightVal=0;
		$(".order_inventory .order_table").each(function(){
			var orderWeight=0,orderFreight=0;//单个订单的重量,单个订单的运费
			$(this).find("tr").each(function(){
				if($(this).find(".len_input").length>0){
					var lent=$(this).find(".len_input").val();//单个商品的个数
					var weight=$(this).attr("data-sku");//每个商品的重量
					if(weight==""){
						weight=0;
					}
					orderWeight=yipin.sum(orderWeight,yipin.multiply(lent,weight));//每个商品的总重量
				}
			});
			//计算费用
			if(parseFloat(orderWeight)>1000){
				orderFreight=20;
			}else if(parseFloat(orderWeight)>0&&parseFloat(orderWeight)<=1000){
				orderFreight=10;
			}else{
				orderFreight=0;
			}
			freightVal=yipin.sum(freightVal,orderWeight);
			freight=yipin.sum(orderFreight,freight);
			$(this).prev().find(".J_orderFreight").html(orderFreight+".00");
			$(this).prev().find(".J_yhFreight").html("-"+orderFreight+".00");
		});
		
		$(".allWeight").text(freightVal);
		$(".freight").html(freight+".00");
		$(".freightFire").html("-"+freight+".00");
		var strFreight=$('.freight').text();
		var strFreightFire=$('.freightFire').text();
		var zongjine=$(".allPrice").text();
		$(".clearing_msg .price").text(yipin.sum(strFreight,zongjine,strFreightFire));
		
	}
	//提交结算
	yipin.form=function(){
		$("#ClearingBtn").on("click",function(){
			//判断是否有已下架或者没有库存的商品
			var tipBoo=true,textTip="";
			$(".J_outTip").each(function(){
				if($(this).text()!=""){
					tipBoo=false;
					textTip=$(this).text();
				}
				return false;
			});
			if(!tipBoo){
				$.dialog({
					"title":false,
					"lock":true,
					"content":"<p class='dialogDiv'>"+textTip+",请重新选择商品",
					"okValue":"确定",
					"ok":true
				});
				$(".d-titleBar").remove();
				return false;
			}
			yipin.addOrders();
		});
	};
	// 启动配置
	yipin.init = (function(){
		yipin.form();
		yipin.goods();
		yipin.delet();
		yipin.check();
		yipin.table();
	})()
})