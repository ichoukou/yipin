/**
 * 卖家商品列表页面js
 */
$(function(){
	$("#search").click(function(){
		var keywords = $(".J-keywords").val();
		if(keywords == $(".J-keywords").attr("data-default")){
			$(".J-keywords").val("");
		}
		$(".sub-form").submit();
	});
	//添加商品
	$("#addProduct").click(function(){
		window.location.href = _ctxPath + "/seller/product/product-addProduct.htm";
	});
	//删除
	$(".delete").click(function(){
		var productId = $(this).closest("tr").attr("productId");
		$.dialog({
            title: false,
            lock: true,
            content: "删除后，将无法找回；是否确定删除该商品？",
            okValue: "确认",
            ok: function(){ 
            	$.ajax({
        			type:'POST',
        			url: _ctxPath + '/seller/product/product-delete.htm',
        			data: "product.productId="+productId,
        			success:function(msg){
        				if(msg.code == "true"){
        					$.dialog({
    				            title: false,
    				            content: msg.info,
    				            time : 2000
    				        });	
    						$("#search").click();
        				}
        			},
        			error: function(xhr){
        				popupDialog(xhr.responseText);
        	        }
        		});
            },
            cancelValue: "取消",
            cancel: true
     	});
		$(".d-close").hide();
	});
	//编辑
	$(".edit").click(function(){
		var productId = $(this).closest("tr").attr("productId");
		window.location.href = _ctxPath + "/seller/product/product-edit.htm?product.productId="+productId;
	});
	
	//售罄
	$(".sellOut").click(function(){
		var productId = $(this).closest("tr").attr("productId");
		 $.dialog({
	            title: false,
	            lock: true,
	            content: "售罄后，将无法继续参加销售；是否确认售罄？",
	            okValue: "确认",
	            ok: function(){
	    			$.ajax({
	        			type:'POST',
	        			url: _ctxPath + '/seller/product/product-sellOut.htm',
	        			data: "product.productId="+productId,
	        			success:function(msg){
	        				if(msg.code == "true"){
	        					$.dialog({
	    				            title: false,
	    				            content: msg.info,
	    				            time : 2000
	    				        });	
	    						$("#search").click();
	        				}
	        			},
	        			error: function(xhr){
	        				popupDialog(xhr.responseText);
	        	        }
	        		});
	            },
	            cancelValue: "取消",
	            cancel: true
	     });
		 $(".d-close").hide();
	});
	//审核不通过愿意
	$(".shenhe").on({
		mouseenter:function(){
			$(this).css("z-index","1");
			$(this).find(".not-through").show();
		},
		mouseleave:function(){
			$(this).css("z-index","0");
			$(this).find(".not-through").hide();
		}
	});
});