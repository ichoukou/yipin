$(function(){
	var yipin = yipin || {};
	
	yipin.mainNav = function(){
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
	}

	yipin.init = (function(){
		yipin.mainNav();
	})()
})
