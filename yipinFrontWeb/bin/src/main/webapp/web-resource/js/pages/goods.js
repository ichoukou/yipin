$(function() {
	// ding yi quan ju bian liang
	var yipin = yipin || {};
	// xin zeng jian shao goods
	yipin.changeGoods = function() {
		// jian shao goods
		$(".less").on("click", function() {
			var _this = $(this);
			var _input = _this.next();
			var _inputVal = parseInt(_input.val());
			if (_inputVal <= 0) {
				return false;
			} else {
				_input.val(_inputVal - 1);
				yipin.goodsMsg();
			}
		});
		// add goods
		$(".more").on("click", function() {
			var _this = $(this);
			var _input = _this.prev();
			var _li=_this.parents("li");
			var _inputVal = parseInt(_input.val());
			var goodLen = _li.attr("data-len");
			if(_inputVal<goodLen){
				_input.val(_inputVal + 1);
			    yipin.goodsMsg();
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
		
		// input white
		$(".len_input").blur(function() {
			var _this = $(this);
			var value = _this.val();
			var _li=_this.parents("li");
			var goodLen = parseInt(_li.attr("data-len"));
			$(this).val(number(value));
			if($(this).val()>goodLen){
				$.dialog({
					"title":false,
					"lock":true,
					"content":"<P class='dialogDiv'>不能大于库存</p>",
					"okValue":"确定",
					"ok":true
				});
				$(".d-titleBar").remove();
				$(this).val(goodLen)
			}
			yipin.goodsMsg();
		});
		//cur style
		$(".goods_list li").hover(function(){
			$(this).addClass("hover");
		},function(){
			$(this).removeClass("hover");
		});
	};
	// num
	function number(str) {
		var arr = str.split("");
		var newArr = [];
		var newStr = "";
		var feg = /^[0-9]{1}$/;
		for (i = 0; i < str.length; i++) {
			if (feg.test(arr[i])) {
				newArr.push(arr[i]);
			}
		}
		newStr = newArr.join("");
		if (newStr == "") {
			newStr = "0";
		}
		return parseInt(newStr);
	}
	// shengcheng
	yipin.goodsMsg = function() {
		var html = "";
		$(".goods_list li").each(function() {
			var _this = $(this);
			var name = _this.find(".goods_name").text();
			var len = _this.find(".len_input").val();
			if(len>0){
				html += '<p>' + name + '<span class="red">X' + len + '</span></p>'
			}else{
				html +="";
			}
		});
		$(".J_goodsMsg").html('');
		$(".J_goodsMsg").append(html);
	};
	
	/*
	 * Title: setShoppingCart 
	 * Description: ajax
	 * commitUrl: /order/order-getOrderInfo.htm
	 * 
	 * @param orders[0].items[0].productSkuId ID 
	 * 
	 */
	yipin.sendCheck =function(){
		/*
		 * <p> Title: setShoppingCart </p> <p> Description: ajax
		 * commitUrl: /order/order-setShoppingCart.htm
		 *  </p>
		 * 
		 * @param orders[0].items[0].productSkuId good ID 
		 * @param orders[0].items[0].num 
		 * 
		 * @see com.ytoxl.yipin.web.action.order.OrderAction#setShoppingCart(java.lang.Integer,
		 *      java.lang.Integer)
		 */
		//submit
		$(".continueBtn").on("click",function(){
			
			if($(".J_goodsMsg p").length>0){
				var json={};
				$(".goods_list li").each(function(i) {
					var _this = $(this);
					var len =_this.find(".len_input").val();
					if(len !=undefined){
						var id=_this.attr("data-id");
						var sellerId=_this.attr("data-sellerId");
						json["orders[0].items["+i+"].productSkuId"]=id;
						json["orders[0].items["+i+"].num"]=len;
						json["orders[0].items["+i+"].sellerId"]=sellerId;
					}
				});
				var _ctxPath =$('#_ctxPath').val();
				var url = _ctxPath+"/order/order-setShoppingCart.htm";  
				$.ajax({
					"url" : url,
					"type" : "Post",
					"dataType" : "json",
					"cache":false,
					"data" :json,
					"success" : function() {
						var _url = _ctxPath+"/seller/seller-send.htm";
						window.location.href=_url;
					}
				});
			}else{
				$.dialog({
					"title":false,
					"lock":true,
					"content":"<P class='dialogDiv'>请先选择商品</p>",
					"okValue":"确定",
					"ok":true
				});
				$(".d-titleBar").remove();
			}
		});
	}
	
	// qidong pei zhi
	yipin.init = (function() {
		yipin.changeGoods();
		yipin.sendCheck();
		yipin.goodsMsg();
	})();

});