$(function(){
	var yipin = yipin || {};
	
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
			cartData.show();
		});
		//显示折叠数据层
		cartData.on('mouseleave',function(){
			$('.J_init').show();
			$(this).hide();
		});
		//显示删除按钮和加减符
		cartData.find('.m_mcOrder').on({
			'mouseenter':function(){
				$(this).addClass('m_mcOrderSelected');
			},
			'mouseleave':function(){
				$(this).removeClass('m_mcOrderSelected');
			}
		});
		//删除单条商品
		cartData.find('.m_mcDel').on('click',function(){
			var _this = $(this),
				bundle = _this.closest('.m_mcBundle'); //按发货地排列的商品组对象
			_this.closest('.m_mcBundleList').fadeOut(500,function(){
				$(this).remove();
				yipin.cash(_this);
				if(bundle.find('.m_mcBundleList').length == 0){
					bundle.remove();
				}
			});
		});
		//增加数量
		cartData.find('.m_mcPlus').on('click',function(){
			var _this = $(this),
            hideVal = _this.closest(".m_mcOrder").attr('data-order'),
            amount = parseInt(_this.prev().text());
         if(amount == parseInt(hideVal)){
             return false;
         }else{
            amount++;
            yipin.editVal(_this,amount);
         }
		});
		//减少数量
		cartData.find('.m_mcMinus').on('click',function(){
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
				maxVal = mcOrder.attr('data-order'); //当前单条商品库存
			Quantity.text(v); //赋值
			yipin.cash(o); //汇总
			var classNmL = (parseInt(Quantity.text()) == 1) ? "m_mcMinus disable" : "m_mcMinus";
            var classNmR = (parseInt(Quantity.text()) == maxVal) ? "m_mcPlus disable" : "m_mcPlus";
			mcOrder.find(".m_mcMinus").removeClass().addClass(classNmL);
            mcOrder.find(".m_mcPlus").removeClass().addClass(classNmR);
		};
		//汇总
		yipin.cash = function(o){
			var t_item_num = 0,t_sig_bundle = 0,t_tot_bundle = 0;
			var bundleList = $('.m_mcBundleList'); //当前单条商品对象
			var bundle = $('.m_mcBundle'); //按发货地址的商品组
			//计算总件数
			bundleList.find('.m_mcQuantity').each(function(){
				var _this = $(this),
					Quantity = _this.text(); //当前商品数量
					t_item_num = yipin.sum(t_item_num,Quantity); //汇总件数
			});
			$('.J_item_num').text(t_item_num); //显示总件数
			//计算单组总价
			bundleList.find('.m_mcPrice').each(function(){
				var _this = $(this),
					s_bundle =_this.text(); //单价
					Quantity = _this.closest('.m_mcBundleList').find('.m_mcQuantity').text(); //当前商品数量
					t_sig_bundle = yipin.sum(t_sig_bundle,yipin.multiply(Quantity,s_bundle)); //汇总单组总价钱
			});
			o.closest('.m_mcBundle').find('.J_bundle').text(t_sig_bundle); //显示单组总价
			//遍历商品组
			bundle.each(function(){
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
	}
	
	yipin.init = (function(){
		yipin.miniCart();
	})()
})
