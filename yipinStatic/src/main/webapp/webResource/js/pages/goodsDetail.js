$(function() {
	// 定义全局变量
	var yipin = yipin || {};
	//悬浮
	yipin.suspend=function(){
		var tH=$("#suspendHd").offset().top;
		var sH=$(document).scrollTop();
		if(parseInt(tH)<=sH){
			$(".suspend").show();
		}else{
			$(".suspend").hide();
			$("#suspendHd a").removeClass("cur");
			$(".J_tab a").removeClass("cur");
			$("#suspendHd a").eq(0).addClass("cur");
			$(".J_tab a").eq(0).addClass("cur");
		}
	};
	//描点
	yipin.tab=function(){
		$("#suspendHd a").each(function(i){
			$(this).on('click',function(){
				if(i==1){
					$("#suspendHd a").removeClass("cur");
					$(".J_tab a").removeClass("cur");
					$("#suspendHd a").eq(1).addClass("cur");
					$(".J_tab a").eq(1).addClass("cur");
				}else{
					$("#suspendHd a").removeClass("cur");
					$(".J_tab a").removeClass("cur");
					$("#suspendHd a").eq(0).addClass("cur");
					$(".J_tab a").eq(0).addClass("cur");
				}
			});
		});
		
		$(".J_tab a").each(function(i){
			$(this).on('click',function(){
				if(i==1){
					$("#suspendHd a").removeClass("cur");
					$(".J_tab a").removeClass("cur");
					$("#suspendHd a").eq(1).addClass("cur");
					$(".J_tab a").eq(1).addClass("cur");
				}else{
					$("#suspendHd a").removeClass("cur");
					$(".J_tab a").removeClass("cur");
					$("#suspendHd a").eq(0).addClass("cur");
					$(".J_tab a").eq(0).addClass("cur");
				}
			});
		});
		$(".slide_box .next").hover(function(){
			$(this).addClass("next_hover");
		},function(){
			$(this).removeClass("next_hover");
		});
		$(".slide_box .prev").hover(function(){
			$(this).addClass("prev_hover");
		},function(){
			$(this).removeClass("prev_hover");
		});
	};
	//规格切换
	yipin.sku=function(){
		$(".J_sku li:not('.disable')").on("click",function(){
			var $this=$(this);
			var price=$this.attr("data-price");
			var oPrice=$this.attr("data-oprice");
			var weight=$this.attr("data-weight");
			var num=$this.attr("data-num");
			var gz=$(this).find("span").text();
			$this.addClass("cur").siblings().removeClass("cur");
			$(".J_price").text(price);//一品价
			$(".J_oPrice").text(oPrice);//原价
			$(".J_wi").text(weight);//重量
			$(".J_js").text(yipin.subtract(price,oPrice));//优惠
			$(".J_gz").text(gz);//规格
			//计算运费
			if(weight>1000){
				$(".J_yf,.J_yh").text(20);
			}else{
				$(".J_yf,.J_yh").text(10);
			}
		});
	};
	yipin.changeGoods = function() {
		// 减少商品
		$("#num .J_less").on("click", function() {
			var _this = $(this);
			var _input = _this.next();
			var _inputVal = parseInt(_input.val());
			if (_inputVal <= 1) {
				return false;
			}else{
			  _input.val(_inputVal - 1);
			}
		});
		// 新增商品
		$("#num .J_more").on("click", function() {
			var _this = $(this);
			var _input = _this.prev();
			var _parent=_this.parent();
			var _inputVal = parseInt(_input.val());
			var goodLen = $(".J_sku .cur").attr("data-num");
			if(_inputVal<goodLen){
				if(_inputVal>=99){
					$.dialog({
						"title":false,
						"lock":true,
						"content":"<P class='dialogDiv'>最大只能添加99个</p>",
						"time":1000
					});
					$(".d-titleBar").remove();
					_input.val(99);
				}else{
					_input.val(_inputVal + 1);
				}
			}else{
				$.dialog({
					"title":false,
					"lock":true,
					"content":"<P class='dialogDiv'>不能大于库存</p>",
					"time":1000
				});
				$(".d-titleBar").remove();
			}
		});
		
		// input输入框
		$("#num .J_input").blur(function() {
			var _this = $(this);
			var value = _this.val();
			var _parent=_this.parent();
			var goodLen = $(".J_sku .cur").attr("data-num");
			$(this).val(yipin.number(value));
			if(parseInt($(this).val())>parseInt(goodLen)){
				$.dialog({
					"title":false,
					"lock":true,
					"content":"<P class='dialogDiv'>不能大于库存</p>",
					"time":1000
				});
				$(".d-titleBar").remove();
				if(goodLen>99){
					$(this).val(99);
				}else{
					$(this).val(goodLen);
				}
			}
		});
	};
	//过滤出数字
	yipin.number=function(str,kucun){
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
		if(newStr==""||newStr=="0"){
			newStr="1";
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
	yipin.form=function(){
		//加入购物车
		var formBoo=true;
		$(".J_joinShoppCart").on("click",function(){
			var productId=$(".J_sku .cur").attr("data-productSkuId");
			var unm=$(".J_input").val();
			var $parent=$(this).parent();
			//防止多次提交表单
			if(formBoo){
				$.ajax({
					"url":_ctxPath+"/order/order-addOrDelProductToCart.htm",
					"type":"post",
					"dataType":"json",
					"data":{
						"params.skuId":productId,
						"params.skuNum":unm
					},
					"success":function(msg){
						if(msg.code=='false'){
							$.dialog({
								"title":false,
								"lock":true,
								"content":"<P class='dialogDiv'>"+msg.info+"</p>",
								"okValue":"确定",
								"ok":true
							});
						}else{
						    $parent.hide();
							$parent.next().show();
							setTimeout(function(){
								$parent.show();
								$parent.next().hide();
							},3000);
							setCart();
						}
						formBoo=true;
					},
					"error":function(){
						formBoo=true;
					}
				});
			}
			formBoo=false;
		});
		//立即购买
		var formBuyBoo=true;
		$(".J_submit").on("click",function(){
			var productId=$(".J_sku .cur").attr("data-productSkuId");
			var unm=$(".J_input").val();
			//防止多次提交表单
			if(formBuyBoo){
				$.ajax({
					"url":_ctxPath+"/order/order-addOrDelProductToCart.htm",
					"type":"post",
					"dataType":"json",
					"data":{
						"params.skuId":productId,
						"params.skuNum":unm
					},
					"success":function(msg){
						if(msg.code=="false"){
							$.dialog({
								"title":false,
								"lock":true,
								"content":"<P class='dialogDiv'>"+msg.info+"</p>",
								"okValue":"确定",
								"ok":true
							});
						}else{
							window.location.href=_ctxPath +"/order/order-showCart.htm";
						}
					}
				});
			}
			formBuyBoo=false;
		});
		//抢购立即购买
		$(".w_norm").on("click",'.J_submitQg',function(){
			$("#goodsForm").submit();
		});
	};
	yipin.init = (function() {
		yipin.suspend();//悬浮
		yipin.tab();//描点
		yipin.sku();//规格切换
		yipin.changeGoods();//添加商品
		yipin.form();
		$(window).scroll(function(){
			yipin.suspend();//悬浮
		});
		$(window).resize(function(){
			yipin.suspend();//悬浮
		});
		//轮播
		 $('#goods_left').slides({
			crossfade: true,
			play: false,
			pause: 2500,
			hoverPause: true,
			generatePagination: false
		}); 
		//图片延迟
		$("#product img").lazyload({
                effect: "fadeIn"
            });
		 //倒计时
		 if($(".J-countdown").length>0){
			$(".J-countdown").each(function () {
				var $this = $(this),
				data = $this.attr('data-config');
				data = eval('(' + data + ')');
				//活动开始回调
				data.callbackOne = function(){
					$(".goods_form").find("a").removeClass().addClass("redBtn J_submitQg");
					$(".goods_form").find("a").text('立即抢购');
					$(".pr_btn").find("a").removeClass().addClass("redBtn1 J_submitQg");
					$(".pr_btn").find("a").text('立即抢购');
					
				};
				//活动结束回调
				data.callbackTwo = function(){
					$("#qg").find("a").removeClass().addClass("grayBtn");
					$("#qg").find("a").text('已过期');
					$(".pr_btn").find("a").removeClass().addClass("grayBtn1");
					$(".pr_btn").find("a").text('已过期');
				};
				
				$this.Plugin({
					"countDown": data
				});
			}); 
		 }
	})();
});