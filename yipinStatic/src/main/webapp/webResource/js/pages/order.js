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
					"content":"<P class='dialogDiv'>收货地址条数已超过限制，请到<a href='"+_ctxPath+"/address/address-getUserAddress.htm' target='_blank' style='color:#36c;'>[收货地址]</a>中删除后再新增</p>",
					"okValue":"确定",
					"ok":true
				});
				$(".d-titleBar").remove();
			}else{
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
											_next.slideUp(500,function(){
												_next.html("");
											});
											//判断是否为第一条地址
											if($("#myAdr li").length<=0){
												var html1='<li class="adr_list adr_listCur" data-address="'+jsonAddress.userAddressId+'"><a href="javascript:" title="寄送到此地址" class="J_hoverDress">寄送到此地址</a>'+jsonAddress.receiveAddress+'  ('+jsonAddress.receiverName+'收)  '+jsonAddress.mobile+'<span class="J_defaultDress" style="margin-left:10px;">(默认地址)</span></li>';
												$("#myAdr ul").prepend(html1);
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
													$("#myAdr ul li").removeClass("adr_listCur");
													$("#myAdr ul").prepend(html1);
												}else{
													var html1='<li class="adr_list adr_listCur" data-address="'+jsonAddress.userAddressId+'"><a href="javascript:" title="寄送到此地址" class="J_hoverDress">寄送到此地址</a>'+jsonAddress.receiveAddress+'  ('+jsonAddress.receiverName+'收)  '+jsonAddress.mobile+'<span class="J_defaultDress" style="margin-left:10px;">(默认地址)</span></li>';
													$("#myAdr ul li").removeClass("adr_listCur");
													$(".J_defaultDress").remove();
													$("#myAdr ul").prepend(html1);
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
		//地址移上去
		$(".J_myDressCur").on("mouseover",".adr_list",function(){
			if(!$(this).hasClass("adr_listCur")){
				$(this).addClass("bg");
				$(this).find("a").show();
			}
		});
		$(".J_myDressCur").on("mouseout",".adr_list",function(){
			$(this).removeClass("bg");
			$(this).find("a").hide();
		});
	};
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
					$("input[name='params.invoiceTitle']").val("");
				   $(".J_riseBox").hide();
				}
			});
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
			var submitBoo=true;
			//是否有备注没保存
			$(".J_writeBox").each(function(){
				if($(this).is(":visible")){
					$.dialog({
						"title":false,
						"lock":true,
						"content":"<P class='dialogDiv'>请先保存填写好的备注</p>",
						"okValue":"确定",
						"ok":true
					});
					$(".d-titleBar").remove();
					submitBoo=false;
					return false;
				}
			});
			if(submitBoo){
				$(".J_bzInput ").each(function(){
					if($(this).val()==$(this).attr("data-default")){
						$(this).val("");
					}
				});
				yipin.setGoodsNum();
			}
		});
	};
	//检测后台库存
	yipin.setGoodsNum=function(){
		var json={};
		$(".order_table tr:not('.last')").each(function(i){
			var $tr=$(this),
			id=$tr.attr("data-product"),
			num=$tr.attr("data-num");
			json["orders[0].items["+i+"].productSkuId"]=id;
			json["orders[0].items["+i+"].num"]=num;
		});
		$.ajax({
			type: "POST",
			async:false,
		   	url: _ctxPath+"/order/order-checkOrderProductInfo.htm",
		   	data: json,
		   	success: function(data){
		     	if(data.code=="true"){
					//判断通过 提交表单
		     		yipin.addOrders();
		     	}else{
					jsonAddress = eval('(' + data.info + ')');
					$.each(jsonAddress,function(i){
						if(jsonAddress[i].result==false){
							var productSkuId=jsonAddress[i].productSkuId;//获取商品id
							var tr_name=$("tr[data-product='"+productSkuId+"']").attr("data-name");
							//下架
							if(jsonAddress[i].code==cartProductDrop){
								$.dialog({
									"title":false,
									"content":"<p class='dialogDiv'>抱歉，"+tr_name+"商品已下架啦，请您返回购物车删除该商品后再试</p>",
									"okValue":"确定",
									"ok":true
								});
							}else if(jsonAddress[i].code==cartProductSoldOut){//售罄
								$.dialog({
									"title":false,
									"content":"<p class='dialogDiv'>抱歉，"+tr_name+"商品已被买光啦，请您返回购物车删除该商品后再试</p>",
									"okValue":"确定",
									"ok":true
								});
							}else if(jsonAddress[i].code==cartProductShortage){//缺货
								$.dialog({
									"title":false,
									"content":"<p class='dialogDiv'>抱歉，"+tr_name+"商品库存不足，请您返回购物车修改购买数量后再试</p>",
									"okValue":"确定",
									"ok":true
								});
							}
							return false;
						}
					});
				}
		   	}
		});
	};

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
                    var reg=/^1[0-9]{10}$/;
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
	//备注
	yipin.bz=function(){
		var writeVal="",writeBoo=false;
		//开始编写
		$(".J_write").on("click",function(){
			var $this=$(this);
			var $writeBox=$this.parents("td").find(".J_writeBox");
			$this.hide();
			$writeBox.show();
			writeBoo=true;
		});
		//修改
		$(".J_edit").on("click",function(){
			var $this=$(this);
			var $parent=$this.parent();
			var $writeBox=$this.parents("td").find(".J_writeBox");
			var $bzInput=$this.parents("td").find(".J_bzInput");
			$parent.hide();
			$writeBox.show();
			writeVal=$bzInput.val();//记录文本框的值
			writeBoo=false;
		});
		//确定填写备注
		$(".J_orderEnter").on("click",function(){
			var $this=$(this);
			var $parent=$this.parent();
			var $writeBtn=$this.parents("td").find(".J_write");
			var $changeBox=$this.parents("td").find(".J_changeBox");
			var $bzInput=$this.parents("td").find(".J_bzInput");
			var $bz=$changeBox.find(".J_bz");
			//判断是否为空
			if($.trim($bzInput.val())==""||$bzInput.val()==$bzInput.attr("data-default")){
				$writeBtn.show();
			    $parent.hide();
			}else{
				$bz.text($bzInput.val());
				$changeBox.show();
			    $parent.hide();
			}
		});
		//取消填写备注
		$(".J_orderCancel").on("click",function(){
			var $this=$(this);
			var $parent=$this.parent();
			var $writeBtn=$this.parents("td").find(".J_write");
			var $changeBox=$this.parents("td").find(".J_changeBox");
			var $bzInput=$this.parents("td").find(".J_bzInput");
			var $bz=$changeBox.find(".J_bz");
			//判断是否为填写点进来的
			if(writeBoo){
				$writeBtn.show();
			    $parent.hide();
				$bzInput.val("");
			}else{
				if($.trim($bzInput.val())==""||$bzInput.val()==$bzInput.attr("data-default")){
					$writeBtn.show();
					$parent.hide();
				}else{
					$bz.text(writeVal);
					$changeBox.show();
					$parent.hide();
				}
			}
		});
	};
	var _submitFlag = 0;
	yipin.addOrders = function(){
		var _userAddressId = $("#myAdr .has_adr .adr_listCur").attr("data-address");
		$("input[name='params.userAddressId']").val(_userAddressId); 
		if(_submitFlag==0){
			_submitFlag++;
			setTimeout('$("#cartform").submit()',20);
		}else{
			$.dialog({
			    title:false,
			    lock:true,
			    content: '<p class="dialogDiv"><img src="'+_imagesPath+'/preload.gif"/> 订单正在提交请稍后</p>'
			});
		}
	};
	// 启动配置
	yipin.init = (function(){
		yipin.form();
		yipin.sel_widget();
		yipin.tab();
		yipin.bz();
		$(".J_bzInput").input();
		
		//倒计时
		 if($(".J_countdown").length>0){
			$(".J_countdown").each(function () {
				var $this = $(this),
				data = $this.attr('data-config');
				data = eval('(' + data + ')');
				$this.Plugin({
					"countDown": data
				});
			}); 
		 }
	})();
});