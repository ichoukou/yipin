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
			Timer = setTimeout(function(){
				addCart.show();
				seeCart.hide();
			},3000);
		})
	}
	
	//订单列表上一页   下一页
	yipin.prevPage = function(){
		$('.page_nav .prev').live("click",function(){
			var _this = $(this);
			var _prev = $('.navigation .prev_link').get(0);
			if(undefined != _prev){
				_prev.click();
			}
		});
	};
	yipin.nextPage = function(){
		$('.page_nav .next').live("click",function(){
			var _this = $(this);
			var _next = $('.navigation .next_page').get(0);
			if(undefined != _next){
				_next.click();
			}
		});
	};
	
	//将订单底部的分页数量复制到 顶部
	function copyPageData(){
		var _curPage = $('.fy_total input').val();
		var _totalPage = $($('.fy_total').find("i").get(0)).text();
		if(_curPage == undefined || _totalPage == undefined){
			$('.J_pagenum').remove();
			return;
		}
		var pageNum = $('.J_pagenum').find("i");
		$(pageNum.get(0)).text(_curPage);
		$(pageNum.get(1)).text(_totalPage);
	}
	
	// 启动配置
	yipin.init = (function(){
		yipin.hover();
		yipin.addCart();
		yipin.prevPage();
		yipin.nextPage();
		copyPageData();
	})()
}