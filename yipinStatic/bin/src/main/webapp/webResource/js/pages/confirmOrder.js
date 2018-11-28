/**
 * Creator: yipin
 * Describe: 支付
 */
$(function () {
    // 定义一个全局类
    var yipin = yipin || {};
	yipin.Form=function(){
		$("#payBtn").on("click",function(){
			$.ajax({
				type: "POST",
				url: _ctxPath+'/netpay/checkOrder.htm',
				dataType:"json",
				async:false,
				data: {
					"netpayModel.orderIds":$("input[name='netpayModel.orderIds']").val()
				},
				success: function(data){
					if(data.code=="true"){
						$.dialog({
							"title":false,
							"lock":true,
							"content":$(".J_confirmOrder").get(0)
						});
						$("#_rePay").on("click",function(){
							$("#payform").submit();
						});
						$("#payform").submit();
					}else{
						$.dialog({
							"title":false,
							"lock":true,
							"content":data.info+'<p>立即查看 <a href="'+_ctxPath+'/order/order-myOrders.htm" title="订单详情>" style="color:#1538ac;">订单详情></a></p>'
						});
					}
			    }
			});
		});
	}
    // 启动配置
    yipin.init = (function () {
		yipin.Form();
    })();
});