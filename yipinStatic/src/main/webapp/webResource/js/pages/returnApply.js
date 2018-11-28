//修改退货数量
function editRefundNum(_this){
	//只能输入数字函数
	olnyNumber(_this);
	var refundNum = _this.val();
	var buyNum = _this.attr("data-num");
	var unitPrice =_this.attr("data-unitPrice");
	if(parseInt(refundNum) > parseInt(buyNum)){
		refundNum = buyNum;
		_this.attr("value",buyNum);
	}
	if(refundNum == "" || parseInt(refundNum) == 0){
		refundNum = 1;
		_this.attr("value",1);
	}
	//修改小计数量
	$(".refundnum").text(refundNum);
	//修改价格
	var amount = refundNum * unitPrice;
	amount = new BigDecimal(refundNum).multiply(new BigDecimal(unitPrice));
	$(".refundamount").text(amount);
};
$(function(){
	// 定义一个全局类
	var yipin = yipin || {};
	
	//订单展开收缩
	yipin.fold = function(){
		$('.data_item').Plugin({
			// 表格行hover
			"trHover": {
			  "eType":'hover', // 事件类型
			  "trList":'.list-tr',
			  "trMCVal":"#fdf8e7" // hover颜色值
			},
			// 表格点击下拉
			"foldingTable":{
				"derail":'.fold',
				"content":'.data_con',
				"className":'change'
			}
		});
	};
	
	//订单默认展开
	yipin.unfold = function(){
		$('.fold').each(function(){
			$(this).trigger('click');
		})
	};
	
	//重置退货申请
	yipin.reset = function(){
		$('#reset').on('click',function(){
			/*$('#refundContactName').val(''); //联系人
			$('#refundMobile').val(''); //手机号码
			$('#refundReason').val(''); //退货原因
			$('#divUploadImage').find('li').each(function(){
				var _this = $(this);
				_this.find('.completed_show').attr('data-url','').hide();
				_this.find('.uploadify').show();
			});
			上传图片有问题 暂时换成
			 */
			location.reload();
		});
	}

	//快递单信息验证
	yipin.checkCode = function(){
		$.formValidator.initConfig({
			validatorGroup: '100',
			formID: 'form1',
			theme: 'yto',
			errorFocus: false
			//submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
		});
			
		// 联系人
		$("#refundContactName").formValidator({validatorGroup:'100', tipID: 'refundContactNameTips', onShow: '', onFocus: '', onCorrect: ''})
		.inputValidator({
	        min:1,
	        max:20,
	        onError:"联系人不能超过20字"
	    })
		.functionValidator({
			fun: function(val, el) {
			   var val= $.trim(val);
				if(val.length<=0){
					return '请输入退货联系人姓名';
				}
			}
		});
		//手机号码
		$("#refundMobile").formValidator({validatorGroup:'100', tipID: 'refundMobileTips', onShow: '', onFocus: '', onCorrect: ''})
		.inputValidator({
	        min:11,
	        max:11,
	        onError:"收货人手机为 11位数字"
	    }).regexValidator({regExp:"mobile",dataType:"enum",onError:"请输入正确的手机号码"})
		/*.functionValidator({
			fun: function(val, el) {
			   var val= $.trim(val);
				if(val.length<=0){
					return '请输入手机号码';
				}
			}
		})*/;
		//退货原因
		$("#refundReason").formValidator({validatorGroup:'100', tipID: 'refundReasonTips', onShow: '', onFocus: '', onCorrect: ''})
		.inputValidator({
	        min:1,
	        max:300,
	        onError:"退货原因填写不能超过300字"
	    })
		.functionValidator({
			fun: function(val, el) {
			   var val= $.trim(val);
				if(val.length<=0){
					return '请输入退货原因';
				}
			}
		});
	};
	
	//退货后端提交
	yipin.Form = function(){
		$('#refundSubmit').on('click',function(){
			//验证表单
			var imageUrls = "";
			$('.completed_show').each(function(){
				var url = $(this).attr("data-url");
				if("" != url){
					imageUrls += url + ",";
				}
			});
			var result = $.formValidator.pageIsValid('100');
			if(result){ 
				$('#refundSubmit').unbind("click");
				//判断是退货还是退款
				var orderItemId = $('#orderItemIdHidden').val();
				//获取数据
				var images = "";
				var refund = "";
				var targetUrl = ""
				if("" != $.trim(orderItemId)){
					if(imageUrls.length > 0){
						images = imageUrls.substring(0,imageUrls.lastIndexOf(","))
					}
					refund = $.param({
						"orderRefund.orderId":$('#orderIdHidden').val(),
						"orderRefund.orderAddressId":$('#orderAddressIdHidden').val(),
						"orderRefund.orderItemId":orderItemId,
						"orderRefund.refundNum":$('.J_refundnum').val(),
						"orderRefund.customerName":$('#refundContactName').val(),
						"orderRefund.mobile":$('#refundMobile').val(),
						"orderRefund.refundReason":$('#refundReason').val(),
						"orderRefund.imageUrls":images
						});
					targetUrl = _ctxPath +"/order/order-saveOrderRefundInfo.htm";
				}else{
					//退款
					refund = $.param({
						"orderCancel.orderId":$('#orderIdHidden').val(),
						"orderCancel.customerName":$('#refundContactName').val(),
						"orderCancel.mobile":$('#refundMobile').val(),
						"orderCancel.refundReason":$('#refundReason').val()
					});
					targetUrl = _ctxPath +"/order/order-saveCancelOrderInfo.htm";
				}
				//发送ajax请求
				$.ajax({
					type : "POST",
					url : targetUrl,
					data : refund,
					success : function(html){
						if("true" == html.code){
							$('#refundSubmit').after(html.info).attr("style","display: none;");
						}else{
							$('#refundSubmit').after(html.info).attr("style","display: none;");
						}
					}
				});
			}
		})
	};
	
	// 启动配置
	yipin.init = (function(){
		yipin.fold();
		yipin.unfold();
		//重置退货申请
		yipin.reset(); 
		//退货信息
		yipin.checkCode();
		yipin.Form();
		//统计字数
		$('#refundReason').textarea({
			/*"tipName":"#refundReasonTips",*/
			"maxlength":300
		});
	})();
})