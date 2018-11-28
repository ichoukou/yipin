$(function(){

  //查看/审核商品页面js

	$("#remark").blur(function(){
		var $this = $(this);
		if(remark != $this.attr("data-default")){
			$("#remarkTip").hide();
		}
	});
	
	//审核通过
	$("#pass").bind("click",function(){
		var productId = $("#productId").val();
		var value = "product.productId="+productId
					+"&product.status="+STATUS_PASS;
		$.ajax({
			type: "POST",
			url: _ctxPath + "/admin/product/product-saveReviewResult.htm",
			data: value,
			success:function(msg){
				if(msg.code == "true"){
					window.location.href = _ctxPath + "/admin/product/product-searchProducts.htm";
				}
			},
			error: function(msg){
				popupDialog("error:"+msg.info);
	        }
		});
	});
	
	//审核不通过
	$("#notPass").click(function(){
		var remark = $("#remark").val();
		if(remark.length > 200){
			alert("【不通过原因】请输入小于200个字符");
			return ;
		}
		if(remark == $("#remark").attr("data-default")){
			$("#remarkTip").show();
			return;
		}
		var productId = $("#productId").val();
		var value = "product.productId="+productId
					+"&product.status="+STATUS_NO_PASS+"&product.remark="+remark;
		$.ajax({
			type:'POST',
			url: _ctxPath + '/admin/product/product-saveReviewResult.htm',
			data: value,
			success:function(msg){
				if(msg.code == "true"){
					window.location.href = _ctxPath + "/admin/product/product-searchProducts.htm";
				}
			},
			error: function(msg){
				popupDialog("error:"+msg.info);
	        }
		});
	});
	
	//返回
	$("#back").click(function(){
		window.location.href = _ctxPath + '/admin/product/product-searchProducts.htm';
	});
});