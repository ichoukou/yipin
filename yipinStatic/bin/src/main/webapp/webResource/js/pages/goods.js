$(function() {
	// ding yi quan ju bian liang
	var yipin = yipin || {};
	// xin zeng jian shao goods
	yipin.changeGoods = function() {
		// jian shao goods
		$(".less").on("click", function() {
			var _this = $(this);
			var _input = _this.next();
			var id = _this.closest('li').attr('data-id');
			var _inputVal = parseInt(_input.val());
			if (_inputVal <= 0) {
				return false;
			} else {
				_input.val(_inputVal - 1);
				var unitNum = _input.val(); //unit num
				if(unitNum ==0){
					$('#appendBox li[data-id='+id+']').remove();
				}
				$('#appendBox li[data-id='+id+']').find('.J_num').text(unitNum); //show unitNum
				yipin.sum(); //sum it
				if($('#appendBox').find('li').length ==0){
					$('#showProduct').hide(); //hide product wrap
				}
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
			var imgSrc = _li.find('img').attr('src'); //img src
			var dataId = _li.attr('data-id'); //sku id
			var unitPri = _li.find('.J_pri').text(); //unit price
			var tit = _li.find('.goods_name').text(); //tit
			if(_inputVal<goodLen){
				_input.val(_inputVal + 1);
				var unitNum = _input.val(); //unit num
				$('#showProduct').show(); //show product wrap
				yipin.install(imgSrc,dataId,tit,unitNum,unitPri); //append goal obj
				yipin.sum(); //sum it
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
			var imgSrc = _li.find('img').attr('src'); //img src
			var id = _li.attr('data-id');
			var tit = _li.find('.goods_name').text(); //tit
			var goodLen = parseInt(_li.attr("data-len"));
			var unitPri = _li.find('.J_pri').text(); //unit price
			$(this).val(number(value));
			$('#appendBox li[data-id='+id+']').find('.J_num').text(unitNum); //show unitNum
			if($(this).val()>goodLen){
				$.dialog({
					"title":false,
					"lock":true,
					"content":"<P class='dialogDiv'>不能大于库存</p>",
					"okValue":"确定",
					"ok":true
				});
				$(".d-titleBar").remove();
				$(this).val(goodLen);
				var unitNum = $(this).val();
				$('#appendBox li[data-id='+id+']').find('.J_num').text(goodLen); //show unitNum
				yipin.sum(); //sum it
			}
			var unitNum = $(this).val();
			$('#showProduct').show(); //show product wrap
			yipin.install(imgSrc,id,tit,unitNum,unitPri); //creat obj
			if(unitNum ==0){
				$('#appendBox li[data-id='+id+']').remove();
			}
			if($('#appendBox li').length<=0){
					$('#showProduct').hide();
		    }
			yipin.sum(); //sum it
			yipin.goodsMsg();
		});
		// cur style
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
	//加法
	yipin.add=function(){ //数组求和
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
	
	// action install
	yipin.install = function(s,id,t,un,up){
		var str = '',
			flag = false;
		str += '<li data-id="'+id+'"><img src="'+s+'" width="76" height="76" /><p>'+t+'<br /><span class="J_num">'+un+'</span>×￥<span class="J_val">'+up+'</span></p></li>';
		$('#appendBox').find('li').each(function(){
			var skuId = $(this).attr('data-id');
			if(id == skuId){
				flag = true;
			}
		});
		if(!flag){
			$('#appendBox').append(str);
		}
		$('#appendBox li[data-id='+id+']').find('.J_num').text(un); //show unitNum
		// append str
		/*$('#showProduct').Xslider({
			unitdisplayed:10,
			numtoMove:10,
			scrollobjSize:Math.ceil($('#showProduct').find("li").length/10)*960
		});*/
	}
	// show sum
	yipin.sum = function(){
		var un=0,price;
		$('#appendBox').find('li').each(function(){
			var _this = $(this);
			var unm = _this.find('.J_num').text();
			var str_price=_this.find('.J_val').text();
			un =yipin.add(unm,un);
			price=yipin.add(yipin.add(unm,un),yipin.multiply(unm,str_price));
		});
		$('#showSum').find('em').text($('#appendBox').find('li').length); //计算总商品总类
		$('#showSum').find('i').text(un); //就算总数
		$('#showSum').find('var').text(price); //就算总数
	}
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
	
	// Xslider
//	yipin.slider = function(){
//		$('#showProduct').live(function(){
//			var _this = $(this);
//			
//		});
//	}
	
	// qidong pei zhi
	yipin.init = (function() {
		yipin.changeGoods();
		yipin.sendCheck();
		yipin.goodsMsg();
		//yipin.slider();
	})();

});