$(function(){	
	//预览图片
	$(".preview").on("mouseover",function(){
		var $this=$(this);
		$this.parent().css("z-index","999");
		$this.next().show();
	});
	$(".preview").on("mouseout",function(){
		var $this=$(this);
		$this.next().hide();
		$this.parent().css("z-index","1");
	});
});