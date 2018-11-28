/*
 * 待页面所有元素加载完成后执行
 * 
**/
window.onload = function(){ 
	$('.header_right>ul>li').on({
		'mouseenter':function(){
			var _this = $(this);
			_this.find('ul').show();
		},
		'mouseleave':function(){
			var _this = $(this);
			_this.find('ul').slideUp();
		}
	})

	$('.focus').Plugin({
		'slider':{
			"effect":'fade',
			"speed":600,
			"space":5000, 
			"auto":true, 
			"trigger":'click', 
			"content_box":'.show_wp',
			"content_tag":'.pro_show', 
			"prev":'prev', 
			"next":'next', 
			"rand":false 
		}
	})
	
}