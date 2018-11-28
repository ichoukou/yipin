/*
 * 待页面所有元素加载完成后执行
 * 
**/
window.onload = function(){ 
	// 定义一个全局类
	var yipin = yipin || {};
    
	//鼠标移入移出效果
	yipin.hover = function(){
		$('.J_hover').find('li').on({
			'mouseenter':function(){
				$(this).find('.b_reason').stop().animate({'top':0},250);
			},
			'mouseleave':function(){
				$(this).find('.b_reason').stop().animate({'top':-100},250);
			}
		})
	}
	
	//添加购物车
	yipin.addCart = function(){
		$('.J_g_cart').on('click',function(){
			var _this = $(this),
				Timer = null,
				addCart = _this.closest('.J_add_cart'), //加入购物车层对象
				seeCart = addCart.next(); //查看购物车对象
			addCart.hide();
			seeCart.show();
			seeCart.on({
				'mouseenter':function(){
					clearTimeout(Timer);
				},
				'mouseleave':function(){
					Timer = setTimeout(function(){
						addCart.show();
						seeCart.hide();
					},3000);
				}
			});
		})
	}
	
	// 启动配置
	yipin.init = (function(){
		yipin.hover();
		yipin.addCart();
	})()
}