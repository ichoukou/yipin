$(function(){
	// 定义一个全局类
	var yipin = yipin || {};
	
	 // 显示提示
    yipin.showIcon = {
        correct:function (el, text) {
            el.show().html('<span class="onError-txt">' + text + '</span>');
        },
        error:function (el, text) {
            el.show().html('<span class="onError-txt">' + text + '</span>');
        },
        show:function (el, text) {
            el.show().html('<span class="onError-txt">' + text + '</span>');
        }
    };
	// 地址增删改
	yipin.sel_widget = function(){
		//新增地址
		$(".create_adr").click(function(){
			//判断是否有超过20条记录
			var $thisObj = $(this);
			var $dressLi=$thisObj.parents(".deliv_adr").find(".adr_list");
			if($dressLi.length>=20){
				$.dialog({
					"title":false,
					"lock":true,
					"content":"<P class='dialogDiv'>地址已超过20条，请先去我的一品删除地址</p>",
					"okValue":"确定",
					"ok":true
				});
				$(".d-titleBar").remove();
			}else{
				$(".new_adr").each(function(){
					$(this).slideUp(300,function(){
						$(this).html("");
					});
				});
				var _next=$(this).parent().next().find(".new_adr");
				if(_next.is(":visible")){
					_next.slideUp(500,function(){
						_next.html("");
					});
				}else{
					$.ajax({
						type: 'POST',
						url: _ctxPath+'/order/order-getAddressHtml.htm',
						async:'false',
						dataType:"html",
						success: function(html){
							_next.html(html);
							_next.slideDown();
							// 地址联动
							var linkage = new linkageSelect();
							linkage.init({
								"oneSel":['#province','请选择'],
								"twoSel":['#city', '请选择'],
								"threeSel":['#region', '请选择']
							});
							if($("input").size()){
								$("input").inputFocus();
							}
							yipin.checkCode();//验证
							//取消按钮
							$(".cancel").on("click",function(){
								$(this).parents(".new_adr").slideUp();
							});
							//提交保存按钮
							$("#dressSave").on('click',function(){
								var index=0;
								//省
								if($('#province').val()==0){
									yipin.showIcon.error($("#provinceTip"), '请选择省份');
									index++;
								}
								// 市
								if($('#province').find("option:selected").text()!="请选择"){
									if($("#city").val()==0){
										yipin.showIcon.error($("#provinceTip"), '请选择市');
										index++;
									}
								}
								// 区
								if($("#city").find("option:selected").text()!="请选择"){
									if($("#region").val()==0){
										yipin.showIcon.error($("#provinceTip"), '请选择区');
										index++;
									}
								}
								setInterval(function(){
									cancelF($('#province'),$("#city"),$("#region"),$("#provinceTip"));
								},1000);
								 //验证表单
								var result = $.formValidator.pageIsValid('1');
								if (!result ||index>0) {
									return false;   //验证不通过,直接返回
								}
								//提交表单
								var parents=$(this).parents(".deliv_adr");
								var $ul=parents.find(".has_adr ul");
								var checkVal=0;
								if($("#checkDefault").is(":checked")){
									checkVal="1";
								}else{
									checkVal="0";
								};
								var dataJson={
									"receiverAdd.receiverName":$("#name").val(),
									"receiverAdd.mobile":$("#phone").val(),
									"receiverAdd.regionId":$("#region").val(),
									"receiverAdd.detailAddress":$("#details").val(),
									"receiverAdd.telephone":$("#tellPhone").val(),
									"receiverAdd.postCode":$("#mail").val(),
									"receiverAdd.isDefault":checkVal
								};
								$(this).unbind("click");
								$.ajax({
									type: 'POST',
									url: _ctxPath+'/address/address-saveAddress.htm',
									data: dataJson,
									async:'false',
									dataType:"json",
									success: function(data){
										if(data.code == 'true'){
											var jsonAddress = eval('(' + data.info + ')');
											//添加送礼地址
											var html='<li class="adr_list" data-address="'+jsonAddress.userAddressId+'"><div class="adr_tab">'+
															'<table width="100%">'+
															'<tbody>'+
																'<tr>'+
																'<td width="830px">'+jsonAddress.receiveAddress+'  ('+jsonAddress.receiverName+'收)  '+jsonAddress.mobile+'</td>'+
																'<td class="right">'+
																	'<div class="right_div">'+
																		'<a href="javascript:" class="addGoodBtn J_addBtn" >添加商品</a>'+
																		' <p class="J_editBox hide"><span class="bg_num"></span><a title="" class="edit J_edit">取消</a></p>'+
																	'</div>'+
																'</td>'+
																'</tr>'+
															'</tbody>'+
														'</table>'+
													'</div></li>';
											$("#giveAdr ul").append(html);
											_next.slideUp(500,function(){
												_next.html("");
											});
											//判断是否为第一条地址
											if($("#myAdr li").length<=0){
												var html1='<li class="adr_list adr_listCur" data-address="'+jsonAddress.userAddressId+'"><a href="javascript:" title="寄送到此地址" class="J_hoverDress">寄送到此地址</a>'+jsonAddress.receiveAddress+'  ('+jsonAddress.receiverName+'收)  '+jsonAddress.mobile+'<span class="J_defaultDress" style="margin-left:10px;">(默认地址)</span></li>';
												$("#myAdr ul,#invoiceAdr ul").prepend(html1);
												$.dialog({
													"title":false,
													"lock":true,
													"content":"<P class='dialogDiv' style='line-height:30px;'>当前默认收货地址是您刚刚添加的第一条收货地址，<br/>您可以到【我的一品】->【收货地址】中更改您的默认地址</p>",
													"time":3000
												});
												$(".d-titleBar").remove();
											}else{
												//判断是否为默认地址
												if(jsonAddress.isDefault=="0"){
													var html1='<li class="adr_list adr_listCur" data-address="'+jsonAddress.userAddressId+'"><a href="javascript:" title="寄送到此地址" class="J_hoverDress">寄送到此地址</a>'+jsonAddress.receiveAddress+'  ('+jsonAddress.receiverName+'收)  '+jsonAddress.mobile+'</li>';
													$("#myAdr ul li,#invoiceAdr ul li").removeClass("adr_listCur");
													$("#myAdr ul,#invoiceAdr ul").prepend(html1);
												}else{
													var html1='<li class="adr_list adr_listCur" data-address="'+jsonAddress.userAddressId+'"><a href="javascript:" title="寄送到此地址" class="J_hoverDress">寄送到此地址</a>'+jsonAddress.receiveAddress+'  ('+jsonAddress.receiverName+'收)  '+jsonAddress.mobile+'<span class="J_defaultDress" style="margin-left:10px;">(默认地址)</span></li>';
													$("#myAdr ul li,#invoiceAdr ul li").removeClass("adr_listCur");
													$(".J_defaultDress").remove();
													$("#myAdr ul,#invoiceAdr ul").prepend(html1);
												}
											}
										}else{
											$.dialog({
												"title":false,
												"lock":true,
												"content":"<P class='dialogDiv'>"+data.info+"</p>",
												"okValue":"确定",
												"ok":true
											});
											$(".d-titleBar").remove();
											_next.slideUp(500,function(){
												_next.html("");
											});
										}
									}
								});
							});
							
						}
					});
				}
			}
			
		});
		$(".J_myDressCur").on("click",".J_hoverDress",function(){
			$(this).parent().removeClass("bg");
			$(this).parent().find("a").hide();
			$(this).parent().addClass("adr_listCur").siblings().removeClass("adr_listCur");
		});
		$(".J_invoice").on("click",".J_hoverDress",function(){
			$(this).parent().removeClass("bg");
			$(this).parent().find("a").hide();
			$(this).parent().addClass("adr_listCur").siblings().removeClass("adr_listCur");
		});
		//地址移上去
		$(".J_invoice,.J_myDressCur").on("mouseover",".adr_list",function(){
			if(!$(this).hasClass("adr_listCur")){
				$(this).addClass("bg");
				$(this).find("a").show();
			}
		});
		$(".J_invoice,.J_myDressCur").on("mouseout",".adr_list",function(){
			$(this).removeClass("bg");
			$(this).find("a").hide();
		});
	}
	//tab 切换
	yipin.tab=function(){
		//发票切换
		$(".J_invoiceTab a").each(function(i){
			$(this).on("click",function(){
				$(this).addClass("orderBtn_cur").siblings().removeClass("orderBtn_cur");
				$(".J_invoice .new_adr").html('');
				$(".J_invoice .new_adr").hide();
				if(i==1){
					$(".J_invoice").show();
					$("input[name='params.hasInvoice']").val("2");
				}else{
					$("input[name='params.hasInvoice']").val("1");
					$(".J_invoice").hide();
				}
			});
		});
		//单位个人切换
		$(".J_rise a").each(function(i){
			$(this).on("click",function(){
				$(this).addClass("orderBtn_cur").siblings().removeClass("orderBtn_cur");
				if(i==1){
					$(".J_riseBox").show();
				}else{
					$("input[name='orders[0].invoiceTitle']").val("");
				   $(".J_riseBox").hide();
				}
			});
		});
		//收货地址切换
		$(".J_profile_hd li").each(function(i){
			$(this).click(function(){
				/*if(i==1){
					//判断只有一个商品 并且数量为1的时候不能为送礼
					if($("#goodsTable tbody tr").length<=2&&parseInt($("#goodsTable tbody tr .len_input").val())<=1){
						$.dialog({
							"title":false,
							"lock":true,
							"content":"<P class='dialogDiv'>商品为一个时，不能为送礼</p>",
							"okValue":"确定",
							"ok":true
						});
						$(".d-titleBar").remove();
						return false;
					}
				}*/
				$(this).addClass("cur").siblings().removeClass("cur");
				$(".J_myDress").hide();
				$(".J_myDress").eq(i).show();
				$(".J_myDress .new_adr").html('');
				$(".J_myDress .new_adr").hide();
				if(i==0){
					$("input[name='params.orderType']").val("1");//自用
					//自用计算费用
					yipin.freight();
				}else{
					$("input[name='params.orderType']").val("2");//送礼
					if($("#giveAdr .adr_Cur").length>0){
						yipin.givFreight();
					}
				}
			});
		});
		
		var indexT=1;
		//新增送礼
		$(".give_adr").on("click",".J_addBtn",function(event){
			var giveLen;
			var _this=$(this);
			var html=$("#give").html();
			var _li=_this.closest("li");
			var $cancel=_li.find(".J_editBox");
			var bg_num=_li.find(".bg_num");
			var ulBox = $('.give_adr ul');
			_this.hide();
			$cancel.show();
			if(_li.find(".goods_list").length<=0){
				_li.addClass("adr_Cur");
				_li.append(html);
				if(indexT<10){
					giveLen="00"+indexT;
				}else if(indexT>=10&&indexT<100){
				   giveLen="0"+indexT;
				}else{
					giveLen=indexT;
				}
				bg_num.text("包裹"+giveLen);
				indexT++;
				var curLen=$(".give_adr .adr_Cur").length;
				if(curLen==1){
					ulBox.prepend(_li);
				}else{
					$(".give_adr .adr_Cur").eq(curLen-2).after(_li);
				}
			}
			//新增 显示隐藏按钮
			$(".J_enter").text("确认");
			$(".J_enter").show();
		});
		//取消分配
		$(".give_adr").on("click",".J_edit",function(event){
			var _this=$(this);
			var _li=_this.closest("li");
			var $cancel=_li.find(".J_editBox");
			var $addBtn=_li.find(".J_addBtn");
			var goods_list =_li.find(".goods_list");
			var ulBox = $('.give_adr ul');
			_li.removeClass(" adr_Cur");
			var giveLen="";
			$.dialog({
				"title":false,
				"lock":true,
				"content":"<P class='dialogDiv'>您确定删除吗?删除了需要重新分配商品</p>",
				"okValue":"确定",
				"cancelValue":"取消",
				"cancel":true,
				"ok":function(){
					$cancel.hide();
					goods_list.remove();
					$addBtn.text("添加商品");
					$addBtn.show();
					ulBox.append(_li);
					indexT--;
					$("#giveAdr .adr_Cur").each(function(i){
						var bg_num=$(this).find(".bg_num");
						var t=i+1;
						if(t<10){
							giveLen="00"+t;
						}else if(t>=10&&t<100){
						   giveLen="0"+t;
						}else{
							giveLen=t;
						}
						bg_num.text("包裹"+giveLen);
					});
					//取消完 隐藏确认按钮
					if($("#giveAdr .adr_Cur").length<=0){
						$(".J_enter").hide();
					}
				}
			});
			$(".d-titleBar").remove();
		});
	};
	//添加 减少商品
	yipin.goods=function(){
		//减少商品
		$("#goodsTable .less").on("click",function(){
			var _this=$(this);
			var _input=_this.next();
			var _tr=_this.parents("tr");
			var _inputVal=parseInt(_input.val());
			var price=_tr.attr("data-price");
			var kucun=_tr.attr("data-kucun");
			var jifen=_tr.attr("data-jifen");
			var _productSkuId = _tr.attr("data-product");
			if(_tr.find(".J_outTip").text()!=""){
				var textTip=_tr.find(".J_outTip").text();
				$.dialog({
					"title":false,
					"lock":true,
					"content":"<p class='dialogDiv'>"+textTip+",请重新选择商品",
					"okValue":"确定",
					"ok":true
				});
				$(".d-titleBar").remove();
				return false;
			}
			if($("#giveAdr").is(":visible")){
				if(_inputVal<=1){
					return false;
				}else if(_inputVal>yipin.orderLen(_tr)){
					_input.val(_inputVal-1);
					yipin.goodsMoney(_this,_input.val(),price,jifen);
				}else{
					$.dialog({
						"title":false,
						"lock":true,
						"content":"<P class='dialogDiv'>不能小于下面分配好的商品数量</p>",
						"okValue":"确定",
						"ok":true
					});
					$(".d-titleBar").remove();
				}
			}else{
				if(_inputVal<=1){
					return false;
				}else{
					_input.val(_inputVal-1);
					yipin.goodsMoney(_this,_input.val(),price,jifen);
					//自用计算费用
					yipin.freight();
				}
			}
			yipin.rectifyProductMount(_productSkuId,_input.val());
		});
		//增加商品
		$("#goodsTable .more").on("click",function(){
			var _this=$(this);
			var _input=_this.prev();
			var _tr=_this.parents("tr");
			var _inputVal=parseInt(_input.val());
			var price=_tr.attr("data-price");
			var kucun=_tr.attr("data-kucun");
			var jifen=_tr.attr("data-jifen");
			var _productSkuId = _tr.attr("data-product");
			if(_tr.find(".J_outTip").text()!=""){
				var textTip=_tr.find(".J_outTip").text();
				$.dialog({
					"title":false,
					"lock":true,
					"content":"<p class='dialogDiv'>"+textTip+",请重新选择商品",
					"okValue":"确定",
					"ok":true
				});
				$(".d-titleBar").remove();
				return false;
			}
			if(_inputVal<kucun){
				_input.val(_inputVal+1);
			    yipin.goodsMoney(_this,_input.val(),price,jifen);
			}else{
				$.dialog({
					"title":false,
					"lock":true,
					"content":"<P class='dialogDiv'>不能大于库存</p>",
					"okValue":"确定",
					"ok":true
				});
				$(".d-titleBar").remove();
			}
			yipin.rectifyProductMount(_productSkuId,_input.val());
			
			if($("#myAdr").is(":visible")){
				//自用计算费用
				yipin.freight();
			}
		});
		//文本框输入
		$("#goodsTable .len_input").blur(function(){
			var _this=$(this);
			var value=_this.val();
			var _tr=_this.parents("tr");
			var price=_tr.attr("data-price");
			var kucun=_tr.attr("data-kucun");
			var jifen=_tr.attr("data-jifen");
			//判断是否为已售完 或者已下架
			if(_tr.find(".J_outTip").text()!=""){
				var textTip=_tr.find(".J_outTip").text();
				$.dialog({
					"title":false,
					"lock":true,
					"content":"<p class='dialogDiv'>"+textTip+",请重新选择商品",
					"okValue":"确定",
					"ok":true
				});
				$(".d-titleBar").remove();
				return false;
			}
			if($("#giveAdr").is(":visible")){
				if(number(value,1000000000)<yipin.orderLen(_tr)){
					$.dialog({
						"title":false,
						"lock":true,
						"content":"<P class='dialogDiv'>不能小于下面分配好的商品数量</p>",
						"okValue":"确定",
						"ok":true
					});
					$(".d-titleBar").remove();
					_this.val(yipin.orderLen(_tr));
				}else if(number(value,1000000000)>kucun){
					$.dialog({
						"title":false,
						"lock":true,
						"content":"<P class='dialogDiv'>不能大于库存</p>",
						"okValue":"确定",
						"ok":true
					});
					$(".d-titleBar").remove();
					_this.val(number(value,kucun));
				}else{
					//判断不能为0
					if(number(value,kucun)=="0"){
						_this.val(1);	
					}else{
						_this.val(number(value,kucun));	
					}
				}
			}else{
				if(number(value,1000000000)>kucun){
					$.dialog({
						"title":false,
						"lock":true,
						"content":"<P class='dialogDiv'>不能大于库存</p>",
						"okValue":"确定",
						"ok":true
					});
					$(".d-titleBar").remove();
					_this.val(number(value,kucun));
				}else{
					//判断不能为0
					if(number(value,kucun)=="0"){
						_this.val(1);	
					}else{
						_this.val(number(value,kucun));	
					}
				}
				//自用计算费用
				yipin.freight();
			}
			
			
			yipin.goodsMoney(_this,_this.val(),price,jifen);
		});
		//赠礼--------------------------
		//减少商品
		$("#giveAdr").on("click",".less",function(){
			var _this=$(this);
			var _input=_this.next();
			var _inputVal=parseInt(_input.val());
			if(_inputVal<=0){
				return false;
			}else{
				_input.val(_inputVal-1);
			}
		});
		//增加商品
		$("#giveAdr").on("click",".more",function(){
			var _this=$(this);
			var _input=_this.prev();//文本框
			var _tr=_this.parents("tr");//tr
			var data_product=_tr.attr("data-product");//tr
			var goodsLen=$("#goods"+data_product).find(".len_input").val();
			var _inputVal=parseInt(_input.val());//当前的val

			if(_inputVal<yipin.goodsLen(_tr)){
				_input.val(_inputVal+1);
			}else{
				$.dialog({
					"title":false,
					"lock":true,
					"content":"<P class='dialogDiv'>此商品您只购买了 "+goodsLen+"件，请调整购买数量或者包裹中的商品数量！</p>",
					"okValue":"确定",
					"ok":true
				});
				$(".d-titleBar").remove();
			}
		});
		//文本框输入
		$("#giveAdr").on("blur",".len_input",function(){
			var _this=$(this);
			var value=_this.val();
			var _tr=_this.parents("tr");
			var data_product=_tr.attr("data-product");//tr
			var goodsLen=$("#goods"+data_product).find(".len_input").val();
			//判断不能为0
			_this.val(number(value,100000000));
			if(number(value,100000000)>yipin.goodsLen(_tr)){
				$.dialog({
					"title":false,
					"lock":true,
					"content":"<P class='dialogDiv'>此商品您只购买了 "+goodsLen+"件，请调整购买数量或者包裹中的商品数量！</p>",
					"okValue":"确定",
					"ok":true
				});
				$(".d-titleBar").remove();
				_this.val(number(value,yipin.goodsLen(_tr)));
			}else{
				_this.val(number(value,100000000));
			}
		});
	};
	//提交结算
	yipin.form=function(){
		$("#ClearingBtn").on("click",function(){
			
			//判断是否有已下架或者没有库存的商品
			var tipBoo=true,textTip="";
			$(".J_outTip").each(function(){
				if($(this).text()!=""){
					tipBoo=false;
					textTip=$(this).text();
				}
				return false;
			});
			if(!tipBoo){
				$.dialog({
					"title":false,
					"lock":true,
					"content":"<p class='dialogDiv'>"+textTip+",请重新选择商品",
					"okValue":"确定",
					"ok":true
				});
				$(".d-titleBar").remove();
				return false;
			}
			//判断是否为送礼
			if($(".give_adr").is(":visible")){
				//判断是否选择了地址
				if($("#giveAdr .adr_Cur").length<=0){
					$.dialog({
						"title":false,
						"lock":true,
						"content":"<P class='dialogDiv'>请选择收货地址！</p>",
						"okValue":"确定",
						"ok":true
					});
					$(".d-titleBar").remove();
					return false;
				}else{
					//判断是否没有确定
					var btnBoo=true;
					$("#giveAdr .J_changeLen div").each(function(){
						if($(this).is(":visible")){
							$.dialog({
								"title":false,
								"lock":true,
								"content":"<P class='dialogDiv'>请先确定分配的商品</p>",
								"okValue":"确定",
								"ok":true
							});
							$(".d-titleBar").remove();
							btnBoo=false;
							return false;
						}
					});
					//判断数量是否匹配
					if(btnBoo){
						var adrLen=$("#goodsTable tbody tr").length;
						var boo=true;
						for(var i=1;i<adrLen;i++){
							var allVal=0;
							var goodsVal=$("#goodsTable tbody tr").eq(i-1).find(".len_input").val();
							var goodId=$("#goodsTable tbody tr").eq(i-1).attr("data-product");
							var name=$("#goodsTable tbody tr").eq(i-1).find(".goods_name").text();
							$(".giveGoods"+goodId).each(function(){
								var val=$(this).find(".len_input").val();
								allVal=parseInt(allVal)+parseInt(val);
							});
							var sx=parseInt(goodsVal)-allVal;
							if(goodsVal!=allVal){
								$.dialog({
									"title":false,
									"lock":true,
									"content":"<P class='dialogDiv'>您的 "+name+"商品你购买了"+goodsVal+"件，还有 "+sx+"件没有指定收货人，请调整购买商品数量或者包裹中的商品数量！</p>",
									"okValue":"确定",
									"ok":true
								});
								$(".d-titleBar").remove();
								boo=false;
								return false;
							}
						};
						//判断新增收货地址是否保存
						var dressBoo=true;
						$(".new_adr").each(function(){
							if($(this).css("display")!="none"){
								$.dialog({
									"title":false,
									"lock":true,
									"content":"<P class='dialogDiv'>请选确定新增地址</p>",
									"okValue":"确定",
									"ok":true
								});
								$(".d-titleBar").remove();
								dressBoo=false;
								return false;
							}
						});
						
						if(dressBoo){
							//判断是否选择了需要开发票
							if($(".J_invoiceTab a").eq(1).hasClass("orderBtn_cur")){
								//判断是否选择了发票地址
								if($("#invoiceAdr li.adr_listCur").length<=0){
									$.dialog({
										"title":false,
										"lock":true,
										"content":"<P class='dialogDiv'>请选择发票地址</p>",
										"okValue":"确定",
										"ok":true
									});
									$(".d-titleBar").remove();
									return false;
								}
								//判断单位名称是否为空
								if($("#company_name").is(":visible")){
									if($("#company_name").val()!=""){
										 var val=$("#company_name").val();
										 var reg=/(^\s+)|(\s+$)/;
						                   if(reg.test(val)){
						                	   $.dialog({
													"title":false,
													"lock":true,
													"content":"<P class='dialogDiv'>单位名称不能有前后空格</p>",
													"okValue":"确定",
													"ok":true
												});
												$(".d-titleBar").remove();
												return false;
						                   }  
									}else{
										$.dialog({
											"title":false,
											"lock":true,
											"content":"<P class='dialogDiv'>单位名称不能为空</p>",
											"okValue":"确定",
											"ok":true
										});
										$(".d-titleBar").remove();
										return false;
									}
								}
								
							}
							yipin.addOrders();
						}
					}
				}
			}else{
				//判断是否选择了地址
				if($(".J_myDressCur .adr_listCur").length<=0){
					$.dialog({
						"title":false,
						"lock":true,
						"content":"<P class='dialogDiv'>请选择收货地址</p>",
						"okValue":"确定",
						"ok":true
					});
					$(".d-titleBar").remove();
					return false;
				}else{
					if($(".new_adr").css("display")!="none"){
						$.dialog({
							"title":false,
							"lock":true,
							"content":"<P class='dialogDiv'>请选确定新增地址</p>",
							"okValue":"确定",
							"ok":true
						});
						$(".d-titleBar").remove();
						return false;
					}
				}
				//判断是否选择了需要开发票
				if($(".J_invoiceTab a").eq(1).hasClass("orderBtn_cur")){
					//判断是否选择了发票地址
					if($("#invoiceAdr li.adr_listCur").length<=0){
						$.dialog({
							"title":false,
							"lock":true,
							"content":"<P class='dialogDiv'>请选择发票地址</p>",
							"okValue":"确定",
							"ok":true
						});
						$(".d-titleBar").remove();
						return false;
					}
					if($("#company_name").is(":visible")){
						//判断单位名称是否为空
						if($("#company_name").val()!=""){
							 var val=$("#company_name").val();
							 var reg=/(^\s+)|(\s+$)/;
			                   if(reg.test(val)){
			                	   $.dialog({
										"title":false,
										"lock":true,
										"content":"<P class='dialogDiv'>单位名称不能有前后空格</p>",
										"okValue":"确定",
										"ok":true
									});
									$(".d-titleBar").remove();
									return false;
			                   }  
						}else{
							$.dialog({
								"title":false,
								"lock":true,
								"content":"<P class='dialogDiv'>单位名称不能为空</p>",
								"okValue":"确定",
								"ok":true
							});
							$(".d-titleBar").remove();
							return false;
						}
					}
				}
				yipin.addOrders();
			};
		});
		
		//确定分配
		$(".J_enter").on("click",function(){
			if($(this).text()=="确认"){
				//判断数量是否匹配
				var adrLen=$("#goodsTable tbody tr").length;
				var boo=true;
				for(var i=1;i<adrLen;i++){
					var allVal=0;
					var goodsVal=$("#goodsTable tbody tr").eq(i-1).find(".len_input").val();
					var goodId=$("#goodsTable tbody tr").eq(i-1).attr("data-product");
					var name=$("#goodsTable tbody tr").eq(i-1).find(".goods_name").text();
					$(".giveGoods"+goodId).each(function(){
						var val=$(this).find(".len_input").val();
						allVal=parseInt(allVal)+parseInt(val);
					});
					var sx=parseInt(goodsVal)-allVal;
					if(goodsVal!=allVal){
						$.dialog({
							"title":false,
							"lock":true,
							"content":"<P class='dialogDiv'>您的 "+name+"商品你购买了"+goodsVal+"件，还有 "+sx+"件没有指定收货人，请调整购买商品数量或者包裹中的商品数量！</p>",
							"okValue":"确定",
							"ok":true
						});
						$(".d-titleBar").remove();
						boo=false;
						return false;
					}
				};
				if(boo){
					//都正确 把input隐藏
					$("#giveAdr .adr_Cur .gift_table tr").each(function(){
						var $this=$(this);
						var val=$this.find(".J_changeLen .len_input").val();
						$this.find(".J_addBtn").hide();
						$this.find(".J_changeLen div").hide();
						$this.find(".J_changeLen p").text(val);
						$this.find(".J_changeLen p").show();
					});
					$(this).text("修改");
					yipin.givFreight();
					
				}
			}else{
				//修改
				$("#giveAdr .adr_Cur").each(function(){
					var $this=$(this);
					$this.find(".J_addBtn").hide();
					$this.find(".J_changeLen div").show();
					$this.find(".J_changeLen p").hide();
				});
				$(this).text("确认");
			}
		});
	};
	//删除商品
	yipin.delet=function(){
		$(".J_delete").on("click",function(){
			var $this=$(this);
			var $tr=$this.parents("tr");
			var goodsId=$tr.attr("data-product");
			$.dialog({
				"title":false,
				"lock":true,
				"content":"<P class='dialogDiv'>您确定删除商品吗？</p>",
				"okValue":"确定",
				"ok":function(){
					if(yipin.rectifyProductMount(goodsId,0)){
						$(".d-titleBar").hide();
					}else{
						return;
					}
					$tr.fadeOut(500,function(){
						$tr.remove();
						$(".giveGoods"+goodsId).remove();
						yipin.goodsMoney();
						yipin.freight();//删除计算重量
						//ajax
						if($("#goodsTable tbody tr").length<=1){
							$.dialog({
								"lock":true,
								"title":false,
								"content":"<P class='dialogDiv'>您删除了所有商品，您可以返回首页重新选择商品！</p>",
								"okValue":"确定",
								"ok":function(){
									window.location.href=window.location.href;
								}
							});
						
						}
					});
				},
				"cancelValue":"取消",
				"cancel":true
			});
			$(".d-titleBar").remove();
		});
	}
	// 验证
    yipin.checkCode = function () {
       $.formValidator.initConfig({
				validatorGroup: '1',
				theme: 'yto',
				errorFocus: false
		});
		//姓名
        $("#name").formValidator({
            "onShow":'',
            "onFocus":'',
            "onCorrect":''
        }).inputValidator({
                "min":1,
                "max":40,
                "onErrorMin":'请输入 1 — 20 个字符组成的姓名',
                "onErrorMax":'请输入 1 — 20 个字符组成的姓名'
            }).functionValidator({
                fun: function (val, el) {
               	 var val=val.replace(/\s+/g,"");
               	 var val=val.replace(/[\\<\\>\\\"\\']/g,"");
                $("#name").val(val);
                if(val==""){
                	 return '请输入 1 — 20 个字符组成的姓名';
                }
	               if(val.length>20){
	                	 return '请输入 1 — 20 个字符组成的姓名';
	                }
               }
           });
		//手机
        $("#phone").formValidator({
            "onShow":'',
            "onFocus":'',
            "onCorrect":''
        }).inputValidator({
                "min":11,
                "max":11,
                "onErrorMin":'请输入正确的手机号码',
                "onErrorMax":'请输入正确的手机号码'
            }).functionValidator({
                fun: function (val, el) {
                    var reg=/^[0-9]{11}$/;
                    if(!reg.test(val)){
                    	return '请输入正确的手机号码';
                    	
                    }  
                }
            });;
		//固话
        $("#tellPhone").formValidator({
            "onShow":'',
            "onFocus":'',
            "onCorrect":'',
            "onEmpty":"",
            "empty":true
        }).inputValidator({
                "max":13,
                "onErrorMax":'请输入正确的固定电话'
            }).functionValidator({
                fun: function (val, el) {
                    var reg=/^([0-9]{3,4}-)?[0-9]{7,8}$/;
                    if(!reg.test(val)){
                    	return '请输入正确的固定电话';
                    	
                    }  
                }
            });
		//详细地址
        $("#details").formValidator({
            "onShow":'',
            "onFocus":'',
            "onCorrect":''
        }).inputValidator({
                "min":1,
                "max":100,
                "onErrorMin":'请填写 1 — 50 个字符组成的地址',
                "onErrorMax":'请填写 1 — 50 个字符组成的地址'
            }).functionValidator({
                fun: function (val, el) {
                	 var val=val.replace(/\s+/g,"");
                	 var val=val.replace(/[\\<\\>\\\"\\']/g,"");
                     $("#details").val(val);
                	if(val==""){
                   	 return '请输入 1 — 20 个字符组成的姓名';
                   }
                    if(val.length>50){
                      	 return '请填写 1 — 50 个字符组成的地址';
                      }
                }
            });
		//邮编
        $("#mail").formValidator({
            "onShow":'',
            "onFocus":'',
            "onCorrect":'',
            "empty": true,
            "onEmpty": ''
        }).inputValidator({
                "min":6,
                "max":6,
                "onErrorMin":'邮编为 6 位数字',
                "onErrorMax":'邮编为 6 位数字'
            }).functionValidator({
                fun: function (val, el) {
                    var reg=/^[0-9]{6}$/;
                    if(!reg.test(val)){
                    	return '邮编为 6 位数字';
                    	
                    }  
                }
            });;
    };
	//过滤出数字
	function number(str,kucun){
		var arr=str.split("");
		var newArr=[];
		var newStr="";
		var feg=/^[0-9]{1}$/;
		for (i=0;i<str.length;i++){
			if(feg.test(arr[i])){
				newArr.push(arr[i]);
			}
		}
		newStr=newArr.join("");
		if(newStr==""){
			newStr="0";
		}else if(parseInt(newStr)>parseInt(kucun)){
			newStr=kucun;
		}
		return parseInt(newStr);
	}
	//加法
	yipin.sum=function(){ //数组求和
        var r=0;
        for (var i=0;i<arguments.length ;i++ )
        {
            r=new BigDecimal(arguments[i].toString()).add(new BigDecimal(r.toString()));
        }
        return r;
    }
	//乘法
	yipin.multiply=function(){ //数组求和
        var r=1;
        for (var i=0;i<arguments.length ;i++ )
        {
            r=new BigDecimal(arguments[i].toString()).multiply(new BigDecimal(r.toString()));
        }
        return r;
    }
	//减法
	yipin.subtract=function(){ //数组求和
        var r=0;
        for (var i=0;i<arguments.length ;i++ )
        {
            r=new BigDecimal(arguments[i].toString()).subtract(new BigDecimal(r.toString()));
        }
        return r;
    }
	//计算金额 积分
	yipin.goodsMoney=function(obj,len,jine){
		if(arguments.length==4){
			var _tr=obj.parents("tr");
			$allPrice=_tr.find(".J-allPrice");
			$allPrice.text(yipin.multiply(len,jine));
		}
		//计算总个数
		var zongLen=0,zongjine=0,zongjifen=0;
		$("#goodsTable .len_input").each(function(){
			var thisVla=$(this).val();
			zongLen=yipin.sum(zongLen,thisVla);
			//parseInt(zongLen)+parseInt(thisVla);
		});
		$(".J_shuliang").text(zongLen);
		//计算总金额
		$("#goodsTable .J-allPrice").each(function(){
			var thisVla=$(this).text();
			zongjine=yipin.sum(zongjine,thisVla);
				//parseFloat(zongjine)+parseFloat(thisVla);
		});
		$(".J_jine").text(zongjine);
		$(".J-jifen").text(parseInt(zongjine));
		//计算下面的结算金额
		var freightVal=parseInt($('.freight').text());
		var freightFireVal=$('.freightFire').text();
		$(".clearing_msg .allPrice").text(zongjine);
		$(".clearing_msg .price").text(yipin.sum(freightVal,zongjine,freightFireVal));
		$(".clearing_msg .shuliang").text(zongLen);
	};
	//送礼计算个数
	yipin.goodsLen=function(obj){
		var _trClass=obj.attr("data-product");//获取当前tr的clas 遍历同类
		var classLen=parseInt(_trClass);//获取当前引索
		var _inputVal=parseInt(obj.find(".len_input").val());//当前的val
		var allGoodsLen=parseInt($("#goods"+classLen).find(".len_input").val());//获取上面商品总个数
		var slLen=0,otherLen=0;
		//遍历送礼所有商品的个数和
		$(".giveGoods"+classLen).each(function(){
			var thisVal=parseInt($(this).find(".len_input").val());
			slLen=yipin.sum(slLen,thisVal);
				//parseInt(slLen)+thisVal;
		});
		otherLen=yipin.subtract(slLen,yipin.sum(allGoodsLen,_inputVal));
		return otherLen;
	}
	//订单计算个数
	yipin.orderLen=function(obj){
		var classLen=obj.attr("data-product");//获取当前引索
		var _inputVal=parseInt(obj.find(".len_input").val());//当前的val
		var slLen=0;
		//遍历送礼所有商品的个数和
		$(".giveGoods"+classLen).each(function(){
			var thisVal=parseInt($(this).find(".len_input").val());
			slLen=yipin.sum(slLen,thisVal);
			//slLen=parseInt(slLen)+thisVal;
		});
		return slLen;
	}
	//自用计算出运费
	yipin.freight=function(){
		//遍历上面总商品
		var freightVal=0,freight=0;
		$("#goodsTable tbody tr:not('.last')").each(function(){
			var lent=$(this).find(".len_input").val();//单个商品的个数
			var weight=$(this).attr("data-sku");//每个商品的重量
			if(weight==""){
				weight=0;
			}
			freightVal=yipin.sum(freightVal,yipin.multiply(lent,weight));//每个商品的总重量
		});
		//计算费用
		if(parseFloat(freightVal)>1000){
			freight=20;
		}else if(parseFloat(freightVal)>0&&parseFloat(freightVal)<=1000){
			freight=10;
		}else{
			freight=0;
		}
		$(".freight").html(freight+".00");
		$(".freightFire").html("-"+freight+".00");
		var strFreight=$('.freight').text();
		var strFreightFire=$('.freightFire').text();
		var zongjine=$(".allPrice").text();
		$(".clearing_msg .price").text(yipin.sum(strFreight,zongjine,strFreightFire));
		
	}
	//送礼计算出运费
	yipin.givFreight=function(){
		//遍历下面分配总商品
		var arr=[],allFreightVa=0;
		$("#giveAdr .adr_Cur").each(function(){
			var freightVal=0,freight=0,num=0;
			$(this).find(".gift_table tr").each(function(){
				var lent=$(this).find(".len_input").val();//单个商品的个数
				var weight=$(this).attr("data-sku");//每个商品的重量
				if(weight==""){
					weight=0;
				}
				freightVal=yipin.sum(freightVal,yipin.multiply(lent,weight));//每个商品的总重量
				num=yipin.sum(num,lent);//一个包裹下的数量
			});
			if(num>0){
				//计算出每个包裹的运费
				if(parseFloat(freightVal)>1000){
					freight=20;
				}else{
					freight=10;
				}
			}else{
				freight=0;
			}
			arr.push(freight);
		});
		//把每个包裹运费相加
		for(var i=0;i<arr.length;i++){
			allFreightVa=yipin.sum(allFreightVa,arr[i]);
		}
		$(".freight").html(allFreightVa+".00");
		$(".freightFire").html("-"+allFreightVa+".00");
		var strFreight=$('.freight').text();
		var strFreightFire=$('.freightFire').text();
		var zongjine=$(".allPrice").text();
		$(".clearing_msg .price").text(yipin.sum(strFreight,zongjine,strFreightFire));
		
	}
	//20130813 add 去除验证提示
	function cancelF(province,city,area,tip){
		var tip=tip;
		if(province.val()!=0){
			if(tip.find("span").text()=="请选择省份"){
				tip.html('');
			}
		}
		if(city.val()!=0){
			if(tip.find("span").text()=="请选择市"){
				tip.html('');
			}
		}
		if(area.val()!=0){
			if(tip.find("span").text()=="请选择区"){
				tip.html('');
			}
		}
	}
	//调整购物车的商品数量
	yipin.rectifyProductMount = function(goodsId,num){
		var bFlag = false;
		$.ajax({
			type: 'POST',
			url: _ctxPath+'/order/order-updateShopingCart.htm',
			data: {
				 "orders[0].items[0].productSkuId":goodsId,
				 "orders[0].items[0].num":num
			},
			async:false,
			dataType:"json",
			success: function(data){
				if(data.code == 'true'){
					bFlag = true;
				}
			}
		});
		return bFlag;
	}
	yipin.preData = function(val){
		$("#_hiddenFormData").empty();
		if(val=="1"){
			 var _userAddressId = $("#myAdr .has_adr .adr_listCur").attr("data-address");
			$("#_hiddenFormData").html("<input type='hidden' name='orders[0].addressItems[0].userAddressId' value='"+_userAddressId+"'/>");
		}else{
			var html = [];
			$("#giveAdr .has_adr .adr_Cur").each(function(i,n){
				var _userAddressId = $(this).attr("data-address");
				html.push("<input type='hidden' name='orders[0].addressItems["+i+"].userAddressId' value='"+_userAddressId+"'/>");
				$(this).find(".gift_table tr").each(function(j,m){
					var _productId = $(this).attr("data-product");
					var _productNum = $(this).find(".len_input").val();
					html.push("<input type='hidden' name='orders[0].addressItems["+i+"].orderAddressItems["+j+"].productSkuId' value='"+_productId+"'/>");
					html.push("<input type='hidden' name='orders[0].addressItems["+i+"].orderAddressItems["+j+"].assignCount' value='"+_productNum+"'/>");
				});
			});
			html = html.join(" ");
			$("#_hiddenFormData").html(html);
		}
		var isHasInvoice = $("input[name='params.hasInvoice']").val();
		if(isHasInvoice=='1'){
			$("input[name='orders[0].orderInvoiceAddress.userAddressId']").val('');
		}else{
			var _invoiceAddressId = $("#invoiceAdr .has_adr .adr_listCur").attr("data-address");
			$("input[name='orders[0].orderInvoiceAddress.userAddressId']").val(_invoiceAddressId);
		}
	}
	var _submitFlag = 0;
	yipin.addOrders = function(){
		if(_submitFlag==0){
			yipin.preData($("input[name='params.orderType']").val());
			_submitFlag++;
			setTimeout('$("#cartform").submit()',20);
			//$("#cartform").submit();
		}else{
			$.dialog({
			    title:false,
			    lock:true,
			    content: '<p class="dialogDiv"><img src="'+_ctxPath+'/web-resource/images/preload.gif"/> 订单正在提交请稍后</p>'
			});
		}
	
	}
	// 启动配置
	yipin.init = (function(){
		yipin.form();
		yipin.sel_widget();
		yipin.tab();
		yipin.goods();
		yipin.delet();
	})()
})