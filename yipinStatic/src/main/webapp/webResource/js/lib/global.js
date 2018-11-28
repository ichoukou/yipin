$(function(){
	var yipin = yipin || {};
	
	//导航
	yipin.mainNav = function(){
		var tEnt = null,tLev = null;
		var navBox = $('.nav_box'); //下拉层对象
		var speed = 100;
		//主导航
		$('.main_nav').find('.J_nav').on({
			'mouseenter':function(){
				var _this = $(this),
					idx = _this.index();
				tEnt = setTimeout(function(){
					_this.addClass('navon');
					navBox.eq(idx-1).show();
				},speed)
			},
			'mouseleave':function(){
				var _this = $(this),
					idx = _this.index();
				clearTimeout(tEnt);
				tLev = setTimeout(function(){
					_this.removeClass('navon');
					navBox.eq(idx-1).hide();
				},speed)
				//下拉层
				navBox.on({
					'mouseenter':function(){
						clearTimeout(tLev);
					},
					'mouseleave':function(){
						setTimeout(function(){
							_this.removeClass('navon');
							navBox.eq(idx-1).hide();
						},speed)
					}
				})
			}
		});
		//切换导航内容
		$('.J_panel').Plugin({
			'tabSwitcher':{
				startIndex:0, 
				activeCls:"cur", 
				tabCls:".panel li", 
				itemCls:".nav_con", 
				delay:0, 
				duration:5000, 
				autoPlay:false, 
				trigger:"mouseenter", 
				effect:false
			}
		});
	}
	
	//抢购倒计时
	yipin.countdown = function(){
		$("#panic_buy .J_countdown").each(function () {
			var $this = $(this),
				_li = $this.closest("li"),
				_url = _li.attr('data-url'),
				data = $this.attr('data-config');
			data = eval('(' + data + ')');
			//活动开始回调
			data.callbackOne = function(){
				_li.find('.s_opr>a').removeClass('coming_order').addClass('g_order').attr('href',_url).text('立即抢购');
			};
			//活动结束回调
			data.callbackTwo = function(){
				_li.find('.s_opr>a').removeClass('g_order').addClass('btn_panic_out').attr('href','javascript:;').text('已过期');
			};
			$this.Plugin({
				"countDown": data
			});
		});
	}
	
	//过滤出数字
	yipin.number = function(str,kucun){
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
	yipin.sum = function(){ //数组求和
        var r=0;
        for (var i=0;i<arguments.length ;i++ )
        {
            r=new BigDecimal(arguments[i].toString()).add(new BigDecimal(r.toString()));
        }
        return r;
    }
	//乘法
	yipin.multiply = function(){ //数组求和
        var r=1;
        for (var i=0;i<arguments.length ;i++ )
        {
            r=new BigDecimal(arguments[i].toString()).multiply(new BigDecimal(r.toString()));
        }
        return r;
    }
	//减法
	yipin.subtract = function(){ //数组求和
        var r=0;
        for (var i=0;i<arguments.length ;i++ )
        {
            r=new BigDecimal(arguments[i].toString()).subtract(new BigDecimal(r.toString()));
        }
        return r;
    }
	
	//mini购物车
	yipin.miniCart = function(){
		var cartData = $('.J_cartdata'); //折叠数据层
		//显示购物车弹层
		$('.J_mcart').on('mouseenter',function(){
			$(this).closest('.J_init').hide();
			cartData.show('fast',function(){
				cartData.html(yipin.getCartHtml());//添加悬浮购物车html
			});
		});
		//显示折叠数据层
		cartData.on('mouseleave',function(){
			$('.J_init').show();
			$(this).hide();
			setCart();
		});
		//显示删除按钮和加减符
		cartData.find('.m_mcOrder').live({
			'mouseenter':function(){
				$(this).addClass('m_mcOrderSelected');
			},
			'mouseleave':function(){
				$(this).removeClass('m_mcOrderSelected');
			}
		});
		//删除单条商品
		cartData.find('.m_mcDel').live('click',function(){
			var _this = $(this),
				bundle = _this.closest('.m_mcBundle'), //按发货地排列的商品组对象
				pri = _this.closest('.m_mcOrder').attr('data-price'), //当前单价
				num = _this.closest('.m_mcBundleList').find('.m_mcQuantity').text(), //当前数量
				_skuId = _this.closest(".m_mcOrder").attr("data-product"),
				sig_pri = bundle.find('.J_bundle').text(), //当前组总价
				hzVal = yipin.subtract(yipin.multiply(pri,num),sig_pri); //删除后的组总价
			if(yipin.addOrDelProductToCart(_skuId,0)){
				_this.closest('.m_mcBundleList').fadeOut(500,function(){
					$(this).remove();
					if(bundle.find('.m_mcBundleList').length == 0){
						bundle.remove();
					}
					if($('.m_mcListBox').find('.m_mcBundle').length ==0){
						$('.m_mcListBox').remove();
						$('.m_mcCashier').remove();
						cartData.append('<div class="nogoods">购物车中还没有商品，赶紧选购吧！</div>')
					}
					bundle.find('.J_bundle').text(hzVal); //单组汇总
					yipin.cash(); //总计汇总
				});
			}
			
		});
		//增加数量
		cartData.find('.m_mcPlus').live('click',function(){
			var _this = $(this),
            hideVal = _this.closest(".m_mcOrder").attr('data-order'),
            amount = parseInt(_this.prev().text());
			if(amount == parseInt(hideVal) || amount ==99){
				return false;
			}else{
				amount++;
				yipin.editVal(_this,amount);
			}
		});
		//减少数量
		cartData.find('.m_mcMinus').live('click',function(){
			var _this = $(this),
            amount = parseInt(_this.next().text());
			if(amount <= 1){
				return false;
			}else{
				amount--;
				yipin.editVal(_this,amount);
			}
		});
		//增加商品数量函数
		yipin.editVal = function(o,v){
			var mcOrder = o.closest('.m_mcOrder'), //当前单条商品对象
				Quantity = mcOrder.find('.m_mcQuantity'), //当前商品数量对象
				_skuId = mcOrder.attr("data-product"),//商品skuId
				_proPri = mcOrder.attr('data-price'), //商品单价
				maxVal = mcOrder.attr('data-order'); //当前单条商品库存
			if(yipin.rectifyProductCount(_skuId,v)){
				Quantity.text(v); //赋值
				mcOrder.find('.m_mcPrice').text(yipin.multiply(v,_proPri)); //赋值单条商品总价
				yipin.cash(o); //汇总
				var classNmL = (parseInt(Quantity.text()) == 1) ? "m_mcMinus disable" : "m_mcMinus";
	            var classNmR = (parseInt(Quantity.text()) == maxVal || v ==99) ? "m_mcPlus disable" : "m_mcPlus";
				mcOrder.find(".m_mcMinus").removeClass().addClass(classNmL);
	            mcOrder.find(".m_mcPlus").removeClass().addClass(classNmR);
			}
		
		};
		//汇总
		yipin.cash = function(o){
			var t_item_num = 0,t_sig_bundle = 0,t_tot_bundle = 0,d_sig_bundle = 0;
			var bundleList = $('.m_mcBundleList'); //当前单条商品对象
			var bundle = $('.m_mcBundle'); //按发货地址的商品组
			//计算总件数
			bundleList.find('.m_mcQuantity').each(function(){
				var _this = $(this),
					Quantity = _this.text(); //当前商品数量
					t_item_num = yipin.sum(t_item_num,Quantity); //汇总件数
			});
			$('.J_mcart').find('.J_cartNum').text(t_item_num); //购物栏中物品数量
			$('.J_item_num').text(t_item_num); //显示总件数
			//修改当前数量时汇总单组总价
			if(arguments.length){
				var bundle = o.closest('.m_mcBundle'); //当前组对象
				bundle.find('.m_mcPrice').each(function(){
					var _this = $(this),
						Quantity = _this.closest('.m_mcBundleList').find('.m_mcQuantity').text(), //商品数量
						s_bundle = _this.text(); //单价
						t_sig_bundle = yipin.sum(t_sig_bundle,s_bundle); //汇总单组总价钱
				});
				bundle.find('.J_bundle').text(t_sig_bundle); //显示单组总价
			}
			//遍历商品组
			$('.m_mcListBox').find('.m_mcBundle').each(function(){
				var _this = $(this);
					sig_bundle = _this.find('.J_bundle').text(); //单组价格
					t_tot_bundle = yipin.sum(t_tot_bundle,sig_bundle);
			});
			$('.J_total').text(t_tot_bundle);
		}
		//返回顶部
		$('.J_gtop').on('click',function(){
			$('html,body').animate({
				scrollTop : 0
			},1000)
		});
	};
	//载入数据
	yipin.getCartHtml = function(){
		var _cartHtml = null;
		$.ajax({
			type: "get",
			async:false,
			cache:false,
			dataType:"html",
		   	url: _ctxPath+"/order/order-showHoverCart.htm",
		   	success: function(html){
		   		_cartHtml = html;
		   	}
		});
		return _cartHtml;
	}
	
	/*=========
	 * cookie修改和删除部分
	 * ========*/
	//调整商品数量
	yipin.rectifyProductCount = function(skuId,skuNum){
		var bFlag = false;
		$.ajax({
			type: "POST",
			async:false,
		   	url: _ctxPath+"/order/order-rectifyProductCount.htm",
		   	data: {
		   		"params.skuId":skuId,
		   		"params.skuNum":skuNum
		   	},
		   	success: function(data){
		     	if(data.code=="true"){
		     		bFlag = true;
		     	}
		   	}
		});
		return bFlag;
	};
	//删除和添加商品
	yipin.addOrDelProductToCart = function(skuId,skuNum){
		var bFlag = false;
		$.ajax({
			type: "POST",
			async:false,
		   	url: _ctxPath+"/order/order-addOrDelProductToCart.htm",
		   	data: {
		   		"params.skuId":skuId,
		   		"params.skuNum":skuNum
		   	},
		   	success: function(data){
		     	if(data.code=="true"){
		     		bFlag = true;
		     	}
		   	}
		});
		return bFlag;
	};
	
	/* ========
	 * 加入购物车动作
	 * ======== */
	yipin.addCart = function(){
		//增加数量
		$('.gd_item .J_more').on('click',function(){
			var _this = $(this),
            hideVal = _this.closest("li").attr('data-inventory'),
            amount = parseInt(_this.prev().val());
			if(amount == parseInt(hideVal)){
				return false;
			}else{
				amount++;
				yipin.editValIndex(_this,amount);
			}
		});
		//减少数量
		$('.gd_item .J_less').on('click',function(){
			var _this = $(this),
            amount = parseInt(_this.next().val());
			if(amount <= 1){
				return false;
			}else{
				amount--;
				yipin.editValIndex(_this,amount);
			}
		});
		//增加商品数量函数
		yipin.editValIndex = function(o,v){
			var mcOrder = o.closest('li'), //当前单条商品对象
				Quantity = mcOrder.find('.J_input'), //当前商品数量对象
				_skuId = mcOrder.attr("data-productskuid"), //商品skuId
				maxVal = mcOrder.attr('data-inventory'); //当前单条商品库存
				Quantity.val(v); //赋值
				var classNmL = (parseInt(Quantity.val()) == 1) ? "less J_less disable" : "less J_less";
	            var classNmR = (parseInt(Quantity.val()) == maxVal) ? "more J_more disable" : "more J_more";
	            mcOrder.find(".J_less").removeClass().addClass(classNmL);
	            mcOrder.find(".J_more").removeClass().addClass(classNmR);
		};
		//输入数量
		$('.gd_item .J_input').on({
			'blur':function(){
				var _this = $(this),
				_li = _this.closest('li'), //当前单条商品对象
				_maxVal = _li.attr('data-inventory'), //当前单条商品库存
				_val = _this.val(); //当前单条商品数量
				if(parseInt(_val) > parseInt(_maxVal) && parseInt(_maxVal) < 100){
					_this.val(_maxVal);
				}
				if(parseInt(_val) > 99 && parseInt(_maxVal) > 99){
					_this.val(99);
				}
				if(_val == ''){
					_this.val(1);
				}
			}
		})
		//刷新cookie
		$('.J_g_cart').on('click',function(){
			var _this = $(this),
				_li = _this.closest('li'), //当前单条商品对象
				_skuId = _li.attr("data-productskuid"), //商品skuId
				_val = _li.find('.J_input').val(); //当前单条商品数量
			if(yipin.addOrDelProductToCart(_skuId,_val)){
				setCart();
			}
		})
	}
	//预订商品
	yipin.Reservation = function(){
		$('.J_g_presale').on('click',function(){
			var _this = $(this),
				_li = _this.closest('li'), //当前单条商品对象
				_skuId = _li.attr("data-productskuid"); //商品skuId
			if(yipin.addOrDelProductToCart(_skuId,1)){
				location.href = _ctxPath + "/order/order-showCart.htm"
			}
		})
	}

	//配置项
	yipin.init = (function(){
		//导航
		yipin.mainNav();
		//抢购倒计时
		yipin.countdown();
		//悬浮购物车
		yipin.miniCart();
		//加入购物车
		yipin.addCart();
		//预订商品
		yipin.Reservation();
	})()
})
	//读取cookie
	function getCookie(name)
	{
		var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
		if(arr != null)
		return unescape(arr[2]);
		return null;
	}
	//赋值购物车总件数
	function setCart(){
		if(getCookie('_cookie_shoppingcart') != undefined){
			var cookie = $.parseJSON(getCookie('_cookie_shoppingcart')); //字符串化json
			var totalNum = $.parseJSON(cookie).totalNum; //cookie中总件数
		}
		if(parseInt(totalNum) > 0 && totalNum != undefined){
			$('.J_mcart').find('.J_cartNum').text(totalNum); //赋值总件数
		}
	}
	setCart();
