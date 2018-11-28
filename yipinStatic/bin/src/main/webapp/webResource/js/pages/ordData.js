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
				"className":'change',
				"callback":function(){
					//TODO 只允许一次ajax
					var $this = $(this);
					var oId = $this.children(".orderIdHiddenVal").val()
					if(oId != undefined){
						var orderId = $.param({"orderId":oId},true);
						$.ajax({
							type : "POST",
							url : _ctxPath + "/order/order-myOrderDetail.htm",
							data : orderId,
							success : function(html){
								$this.empty().append(html);
							}
						});
					}
				}
			}
		});
	};
	
	//订单默认展开
	yipin.unfold = function(){
		$('.fold').each(function(){
			$(this).trigger('click');
		})
	} 

	//订单状态的切换
	yipin.orderStatusChange = function(){
		$('#orderStatusId').on("change",function(){
			$this = $(this);
			var statusValue = $this.val();
			var queryTimeValue = $('#queryTimeId').val();
			//订单状态信息传过去
			var ss = $.param({
				"orderPage.params.status":statusValue,
				"orderPage.params.queryTime":queryTimeValue
				});
			//清空旧数据 添加loading图标
			var loading = '<div class="loading"><img src="' + _imagesPath + '/loading.gif" /></div>';
			$('.my_order').empty().append(loading);
			$.ajax({
				type : "POST",
				url : _ctxPath + "/order/order-myOrdersAjax.htm",
				data : ss,
				success : function(html){
					$('.my_order').empty().append(html);
					//绑定 .data_item 事件
					yipin.fold();
					//订单默认展开
					yipin.unfold();
				}
			});
		});
	};
	
	//订单时间切换
	yipin.queryTimeIdChange = function(){
		$('#queryTimeId').on("change",function(){
			//将状态欢迎到 全部订单
			$('#orderStatusId').val("");
			$this = $(this);
			var queryTimeValue = $this.val();
			var qTime = $.param({
				"orderPage.params.queryTime":queryTimeValue,
				"orderPage.params.status":""
				});
			//清空旧数据 添加loading图标
			var loading = '<div class="loading"><img src="' + _imagesPath + '/loading.gif" /></div>';
			$('.my_order').empty().append(loading);
			$.ajax({
				type : "POST",
				url : _ctxPath + "/order/order-myOrdersAjax.htm",
				data : qTime,
				success : function(html){
					$('.my_order').empty().append(html);
					//绑定 .data_item 事件
					yipin.fold();
					//订单默认展开
					yipin.unfold();
				}
			});
		});
	};
	
	//确认收货
	yipin.confirmGoods = function(){
		$('.J_confirmgood').live("click",function(){
			$this = $(this);
			//二次确认窗口
			$.dialog({
				lock : true,
				title : false,
				content : "<p class='dialogDiv'>您确认已经收到该商品</p>",
				okValue: "确定",
		        cancel: true,
		        cancelValue: "取消",
				ok : function(){
					var orderAddressId = $this.attr("data-id");
					var g = $.param({"orderAddressId":orderAddressId});
					$.ajax({
						type : "POST",
						url : _ctxPath + "/order/order-confirmGoods.htm",
						data : g,
						success : function(html){
							if("true" == html.code){
								//如果都确认收货了 将订单的状态改成 已经完成 TODO
								var j = $this.closest(".uf_item").siblings();
								if(j && $(j).find(".J_confirmgood").length == 0){
									$($this.parents(".data_item").find(".col1").children("span")[2]).text("已完成");
								}
								$this.prev("span").remove();
								$this.remove();
							}else{
								alert("confirmGoods fail");
								//提示错误信息 TODO
							}
						}
					});
				}
			});
			//隐藏关闭按钮
			$(".d-close").hide();
		});
	};
	
	//取消订单
	yipin.cancelOrder = function(){
		$('.J_cancelorder').live("click",function(){
			$this = $(this);
			//二次确认窗口
			$.dialog({
				lock : true,
				title : false,
				content : "<p class='tac' style='color:#C00;'>您确认要【取消】本订单？</p>",
				okValue: "确定",
		        cancel: true,
		        cancelValue: "取消",
				ok : function(){
					var orderId = $this.attr("data-id");
					var g = $.param({"orderId":orderId});
					$.ajax({
						type : "POST",
						url : _ctxPath + "/order/order-cancelOrder.htm",
						data : g,
						success :function(html){
							if("true" == html.code){
								//$this.prev("span").remove();
								$this.removeClass("J_cancelorder").addClass("J_delorder").text("删除").siblings().remove();
								$($this.parent().siblings(".col1").children("span")[2]).text("已取消");
							}else{
								alert("cancelOrder fail");
								//提示错误信息 TODO
							}
						}
					});
				}
			});
			//隐藏关闭按钮
			$(".d-close").hide();
		});
	};
	
	//逻辑删除订单
	yipin.delOrder = function(){
		$('.J_delorder').live("click",function(){
			$this = $(this);
			//二次确认窗口
			$.dialog({
				lock : true,
				title : false,
				content : "<div class='tac' style='color:#C00;'>您确认要【删除】本订单？</div>",
				okValue: "确定",
		        cancel: true,
		        cancelValue: "取消",
				ok : function(){
					var orderId = $this.attr("data-id");
					var g = $.param({"orderId":orderId});
					$.ajax({
						type : "POST",
						url : _ctxPath + "/order/order-deleteOrder.htm",
						data : g,
						success :function(html){
							if("true" == html.code){
								//TODO 删除此条数据
								$this.closest(".data_item").fadeOut(600,function(){
									$(this).remove();
									//如果都删除完了ajax动态刷新页面
									if($('.my_order .data_item').length == 0){
										$('#orderStatusId').change();
									}
								});
							}else{
								alert("deleteOrder fail");
								//提示错误信息 TODO
							}
						}
					});
				}
			});
			//隐藏关闭按钮
			$(".d-close").hide();
		});
	};

	//快递单信息验证
	yipin.checkCode = function(){
		$.formValidator.initConfig({
			validatorGroup: '1',
			formID: 'form1',
			theme: 'yto',
			errorFocus: false
			//submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
		});
			
		// 快递公司
		$("#expcompany").formValidator({validatorGroup:'1', tipID: 'expcompanyTips', onShow: '', onFocus: '', onCorrect: ''})
		.inputValidator({
			min:1,
			max:20,
			onError:"快递公司必须小于或等于20位"
		})
		.functionValidator({
			fun: function(val, el) {
			   var val= $.trim(val);
				if(val.length<=0){
					return '请输入快递公司';
				}
			}
		});
		//快递单号
		$("#expInfo").formValidator({validatorGroup:'1', tipID: 'expInfoTips', onShow: '', onFocus: '', onCorrect: ''})
		.inputValidator({
			min:1,
			max:20,
			onError:"快递单号必须小于或等于20位"
		})
		.functionValidator({
			fun: function(val, el) {
			   var val= $.trim(val);
				if(val.length<=0){
					return '请输入快递单号';
				}
			}
		});
	};
	
	//重置快递信息
	yipin.reset = function(){
		$('#reset').on('click',function(){
			$('#expcompany').val(''); //快递公司
			$('#expInfo').val(''); //快递单号
		})
	}
	
	//快递单信息后端提交
	yipin.Form = function(){
		$('#refundExpressInfo').on('click',function(){
			//验证表单
			var result = $.formValidator.pageIsValid('1');
			if(result){ 
				//验证通过执行语句
				var $this = $(this);
				var orderRefundId = $this.attr("data-id");
				var g = $.param({
					"orderRefundExpress.orderRefundId":orderRefundId,
					"orderRefundExpress.expressName":$('#expcompany').val(),
					"orderRefundExpress.expressNo":$('#expInfo').val()
					});
				$.ajax({
					type : "POST",
					url : _ctxPath + "/order/order-saveRefundExpressInfo.htm",
					data : g,
					success:function(html){
						if("true" == html.code){
							//隐藏提交按钮提示  成功
							$('#refundExpressInfo').after(html.info).attr("style","display: none;");
							$('#reset').attr("style","display: none;");
						}else{
							$('#refundExpressInfo').after(html.info).attr("style","display: none;");
							$('#reset').attr("style","display: none;");
						}
					}
				});
			}
		})
	};
	//订单跟踪
	yipin.track=function(){
		var timer;
		$(".J_track").live('mouseenter',function(){
			//判断是否加载出来
			var _this = $(this),
				$trackTiP = _this.parent().find(".tip_popup"),
				mark = 0;
			_this.parent().addClass('status_on');
			if(timer){
				clearTimeout(timer);
			}
			if($trackTiP.find("table").length > 0 || mark > 0){
				$trackTiP.show();
			}else{
				//防止重复出现
				if(mark > 0){
					return;
				}
				$trackTiP.show();
				var queryData = _this.attr("data");
				var _queryData = $.parseJSON(queryData);
				var expressId = $.trim(_queryData['expressId']);
				var mailNo = $.trim(_queryData['expressNo']);
				var orderId = $.trim(_queryData['orderId']);
				if(orderId != ""){
					mark ++;
					var q = $.param({"expressId":expressId,"mailNo":mailNo,"orderId":orderId});
					$.ajax({
						type : "POST",
						url : _ctxPath + "/order/order-expressInfoAjax.htm",
						data : q,
						success:function(html){
							//TODO
							$trackTiP.find("table").remove(); //防止重复出现
							_this.next().children('.tip_inner').find('p').after(html);
							_this.next().children('.tip_inner').find('.loading').remove();
						}
					});
				}
			}
		});
		$(".J_track").live('mouseleave',function(){
			var _this = $(this),
			   $trackTiP = _this.parent().find(".tip_popup");
			timer=setTimeout(function(){
				_this.parent().removeClass('status_on');
				$trackTiP.hide();
			},25);
		});
		$('.tip_popup').live('mouseenter',function(){
			if(timer){
				clearTimeout(timer);
			}
		})
		$(".tip_popup").live('mouseleave',function(){
			var _this = $(this);
			_this.parent().removeClass('status_on');
			_this.hide();
		})
	}
	// 启动配置
	yipin.init = (function(){
		yipin.fold();
		//订单默认展开
		yipin.unfold();
		//订单状态的切换
		yipin.orderStatusChange();
		//订单时间切换
		yipin.queryTimeIdChange();
		//确认收货
		yipin.confirmGoods();
		//取消订单
		yipin.cancelOrder();
		//逻辑删除订单
		yipin.delOrder();
		//快递单信息
		yipin.checkCode();
		//重置快递信息
		yipin.reset();
		//订单跟踪
		yipin.track();
		yipin.Form();
	})();
})


