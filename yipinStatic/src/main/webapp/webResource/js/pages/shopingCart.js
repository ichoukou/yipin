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
				var data=yipin.rectifyProductCount(_productSkuId,_inputVal-1);
				if(data.code=="true"){
					_tr.find(".J_productTip").hide();
				}else{
					yipin.producekc(data,_tr);
				}
				yipin.goodsMoney(_this,_input.val(),price);
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
			var _productSkuId = _tr.attr("data-product");
			var timer;
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
			if((_inputVal+1)>99){
				return false;
			}
			_input.val(_inputVal+1);
			var data=yipin.rectifyProductCount(_productSkuId,_inputVal+1);
			if(data.code=="true"){
				_tr.find(".J_productTip").hide();
			}else{
				yipin.producekc(data,_tr,timer);
			}
			yipin.goodsMoney(_this,_input.val(),price);
			yipin.freight();
		});
		//文本框输入
		$(".order_table .len_input").blur(function(){
			var _this=$(this);
			var value=_this.val();
			var _tr=_this.parents("tr");
			var price=_tr.attr("data-price");
			var kucun=_tr.attr("data-kucun");
			var _productSkuId = _tr.attr("data-product");
			var timer;
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
            var _inputVal=number(value,100000000);
			//判断不能为0
			if(_inputVal=="0"){
				_tr.find(".J_productTip").hide();
				_this.val(1);	
			}else{
				var data=yipin.rectifyProductCount(_productSkuId,_inputVal);
				_this.val(_inputVal);
				if(data.code!="true"){
					yipin.producekc(data,_tr,timer);
				}
			}
			//自用计算费用
			yipin.goodsMoney(_this,_this.val(),price);
			yipin.freight();
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
					if(yipin.addOrDelProductToCart(goodsId)){
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
							yipin.freight();//删除计算重量
						});
					}
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
			if($checked.length<=0&&$(".parentCheck:checked").length<=0){
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
							if(yipin.addOrDelProductToCart(goodsId)){
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
									yipin.freight();//删除计算重量
								});
							}
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
			yipin.freight();//删除计算重量
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
			yipin.freight();//删除计算重量
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
			yipin.freight();//删除计算重量
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
		if(arguments.length>1){
			var _tr=obj.parents("tr");
			$allPrice=_tr.find(".J-allPrice");
			$allPrice.text(yipin.multiply(len,jine));
		}
	};

	//计算出运费
	yipin.freight=function(){
		//计算总个数
		var zongLen=0,zongjine=0,zongjifen=0;
		$(".order_inventory .subCheck:checked").each(function(){
			if(!$(this).prop("disabled")){
				var $input=$(this).parents("tr").find(".len_input");
				var thisVla=$input.val();
				zongLen=yipin.sum(zongLen,thisVla);
			}
		});
		$(".J_shuliang").text(zongLen);
		$(".J_goodsLen").text(zongLen);
		//计算订单
		var orderLength=0;
		$(".order_th").each(function(){
			var $this=$(this);
			if($this.next().find(".subCheck:checked").length>=1){
				orderLength=yipin.sum(orderLength,1);
			}
		});
		$(".J_orderLen").text(orderLength);
		//计算总金额
		$(".order_inventory .subCheck:checked").each(function(){
			if(!$(this).prop("disabled")){
				var $price=$(this).parents("tr").find(".J-allPrice");
				var thisVla=$price.text();
				zongjine=yipin.sum(zongjine,thisVla);
			}
		});
		$(".J_jine").text(zongjine);
		$(".J-jifen").text(parseInt(zongjine));
		//计算下面的结算金额
		var freightVal=parseInt($('.freight').text());
		var freightFireVal=$('.freightFire').text();
		$(".clearing_msg .allPrice").text(zongjine);
		$(".clearing_msg .price").text(yipin.sum(freightVal,zongjine,freightFireVal));
		$(".clearing_msg .shuliang").text(zongLen);
	
		//遍历上面总商品
		var freight=0,freightVal=0;
		$(".order_inventory .order_table").each(function(){
			var orderWeight=0,orderFreight=0;//单个订单的重量,单个订单的运费
			$(this).find(".subCheck:checked").each(function(){
				if(!$(this).prop("disabled")){
					var $tr=$(this).parents("tr");
					if($tr.find(".len_input").length>0){
						var lent=$tr.find(".len_input").val();//单个商品的个数
						var weight=$tr.attr("data-sku");//每个商品的重量
						if(weight==""){
							weight=0;
						}
						orderWeight=yipin.sum(orderWeight,yipin.multiply(lent,weight));//每个商品的总重量
					}
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
			//判断是否只有商品已售罄的订单
			if($(".order_inventory .subCheck:checked").length<=0){
				$.dialog({
					"title":false,
					"lock":true,
					"content":"<p class='dialogDiv'>请先选择要结算的订单</p>",
					"okValue":"确定",
					"ok":true
				});
				$(".d-titleBar").remove();
				return false;
			}else{
				var len=$(".order_inventory .subCheck:checked").length,disableNum=0;
				$(".order_inventory .subCheck:checked").each(function(){
					if($(this).prop("disabled")){
						disableNum++
					}
				}); 
				if(len==disableNum){
					$.dialog({
						"title":false,
						"lock":true,
						"content":"<p class='dialogDiv'>商品已售罄，请重新选择商品</p>",
						"okValue":"确定",
						"ok":true
					});
					$(".d-titleBar").remove();
					return false;
				}
			}
				
			yipin.setGoodsNum();
		});
	};
	//检测后台库存
	yipin.setGoodsNum=function(){
		var json={};
		$(".subCheck:checked").each(function(i){
			if(!$(this).prop("disabled")){
				var $tr=$(this).parents("tr"),
				id=$tr.attr("data-product"),
				num=$tr.find(".len_input").val();
				json["orders[0].items["+i+"].productSkuId"]=id;
				json["orders[0].items["+i+"].num"]=num;
			}
		});
		yipin.checkOrderProductInfo(json);
	};
	yipin.setOrderInfo = function(){
		$("#_dynamicFormData").empty();
		var html = [],numFlag = 0;
		$(".order_table").each(function(i,n){
			var num = $(this).find(".subCheck:checked").size();
			if(num>0){
				$(this).find(".subCheck:checked").each(function(i,n){
					var $tr = $(this).parent().parent();
					var _productSkuId = $tr.attr("data-product"),_skuNum = $tr.find(".J_skuNum").val();
					html.push("<input type='hidden' name='orders["+numFlag+"].items["+i+"].productSkuId' value='"+_productSkuId+"'/>");
					html.push("<input type='hidden' name='orders["+numFlag+"].items["+i+"].num' value='"+_skuNum+"'/>");
				});
				numFlag++;
			}
		});
		html = html.join(" ");
		$("#_dynamicFormData").html(html);
		setTimeout('$("#cartform").submit()',20);
	};
	yipin.checkOrderProductInfo = function(jsonObj){
		$.ajax({
			type: "POST",
			async:false,
		   	url: _ctxPath+"/order/order-checkOrderProductInfo.htm",
		   	data: jsonObj,
		   	success: function(data){
		     	if(data.code=="true"){
		     		yipin.setOrderInfo();
		     	}else{
		     		//效验不通过
					jsonAddress = eval('(' + data.info + ')');
					$.each(jsonAddress,function(i){
						if(jsonAddress[i].result==false){
							var productSkuId=jsonAddress[i].productSkuId;//获取商品id
							var num=jsonAddress[i].num;//获取最新库存
							var $tr=$("tr[data-product='"+productSkuId+"']");
							var price=$tr.attr("data-price");
							$tr.attr("data-kucun",num);//刷新库存
							//下架
							if(jsonAddress[i].code==cartProductDrop){
								$tr.find(".J_numChange").remove();
								obj.find(".subCheck").prop("disabled",true);
								$tr.find(".J_outTip").show().text("商品已下架！");
								yipin.checkOrder();
							}else if(jsonAddress[i].code==cartProductSoldOut){//售罄
								$tr.find(".J_numChange").remove();
								obj.find(".subCheck").prop("disabled",true);
								$tr.find(".J_outTip").show().text("商品已售罄！");
								yipin.checkOrder();
							}else if(jsonAddress[i].code==cartProductShortage){//缺货
								$tr.find(".len_input").val(num);
								$tr.find(".J_productTip").show().text("库存不足，调整为"+num+"件！");
								yipin.goodsMoney($tr.find(".len_input"),$tr.find(".len_input").val(),price);
							}
						}
					});
					//显示下面的提示文案
					$(".J_orderTip").show();
					//自用计算费用
					yipin.freight();
		     	}
		   	}
		});
	};
	//库存验证后的操作
	yipin.producekc=function(data,obj,timer){
		//效验不通过
		jsonAddress = eval('(' + data.info + ')');
		if(jsonAddress.result==false){
			var num=jsonAddress.num;//获取最新库存
			obj.attr("data-kucun",num);//刷新库存
			//下架
			if(jsonAddress.code==cartProductDrop){
				obj.find(".J_numChange").remove();
				obj.find(".subCheck").prop("disabled",true);
				obj.find(".J_outTip").show().text("商品已下架！");
				yipin.checkOrder();
			}else if(jsonAddress.code==cartProductSoldOut){//售罄
				obj.find(".J_numChange").remove();
				obj.find(".subCheck").prop("disabled",true);
				obj.find(".J_outTip").show().text("商品已售罄！");
				yipin.checkOrder();
			}else if(jsonAddress.code==cartProductShortage){//缺货
				obj.find(".len_input").val(num);
				obj.find(".J_productTip").show().text("库存不足，调整为"+num+"件！");
				if(timer){
					clearTimeout(timer);
				}
				timer=setTimeout(function(){
					obj.find(".J_productTip").fadeOut();
				},2000);
			}
		}
	}
	//调整商品数量
	yipin.rectifyProductCount = function(skuId,skuNum){
		var objData;
		$.ajax({
			type: "POST",
			async:false,
		   	url: _ctxPath+"/order/order-rectifyProductCount.htm",
		   	data: {
		   		"params.skuId":skuId,
		   		"params.skuNum":skuNum
		   	},
		   	success: function(data){
		     	objData = data;
		   	}
		});
		return objData;
	};
	yipin.addOrDelProductToCart = function(skuId){
		var bFlag = false;
		$.ajax({
			type: "POST",
			async:false,
		   	url: _ctxPath+"/order/order-addOrDelProductToCart.htm",
		   	data: {
		   		"params.skuId":skuId,
		   		"params.skuNum":0
		   	},
		   	success: function(data){
		     	if(data.code=="true"){
		     		bFlag = true;
		     	}
		   	}
		});
		return bFlag;
	};
	//检测订单下是否有商品可选择 不然去除订单的checkbox
	yipin.checkOrder=function(){
		$(".order_th").each(function(){
			var $this=$(this);
			var $next=$this.next();
			var len=$next.find(".subCheck").length,disableNum=0;
			$next.find(".subCheck").each(function(){
				if($(this).prop("disabled")){
					disableNum++;
				}
			});
			if(len==disableNum){
				$this.find(".parentCheck").prop("disabled",true);
			}
		});
	}
	// 启动配置
	yipin.init = (function(){
		yipin.form();
		yipin.goods();
		yipin.delet();
		yipin.check();
		yipin.table();
		//默认选中
		yipin.checkOrder();
		$(".allCheck,.subCheck,.parentCheck").prop("checked",true);
	})()
})