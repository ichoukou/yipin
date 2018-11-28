$(function(){	
	var yipin = yipin || {};
	// 初始化页面加载
	 yipin.initContent = function(){
		queryProduct();
	};
	yipin.init = (function() {
		yipin.initContent();
	})();
	var showIcon={
	    correct: function (el, text) {
	        el.show().html('<span class="yto_onCorrect">' + text + '</span>');
	    },
	    error: function (el, text) {
	        el.show().html('<span class="onError_top">' + text + '</span>');
	    },
	    show: function (el, text) {
	        el.show().html('<span class="yto_onShow">' + text + '</span>');
	    }
	};
});

//取消
$("#cancelForm").on("click",function() {
	window.location = _ctxPath+ '/admin/zone/zone-searchZone.htm';
});


// 查询首字母对应品牌商品
var queryProduct = function(){
	var brandNo = $("#firstChar").val();
	var zid = $("#zoneId").val();
	var urlPath = _ctxPath+"/admin/product/product-showProducts.htm"; 
	var brand_temp = "p-list";
	$.ajax({
		url : urlPath,
		type : "POST",
		data : {'firstChar' : brandNo,'saleType':1,'zoneId':zid},
		dataType : "html",
		success : function(res) {
			if(res!='' && res!=null){
				$("#prolist").html("");
				$("#qzBox").html("");
				$("#dvstate").after(res);
				$("#"+brand_temp).show();
			}
		},
		error : function(res) {
			alert(res.responseText);
		}
	});
}

	