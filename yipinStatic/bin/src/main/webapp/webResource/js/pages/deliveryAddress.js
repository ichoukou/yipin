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
	
	// 地址联动
	yipin.select = function(){
		var prov = $("#area_prov").val(), city = $("#area_city").val(), district = $("#area_region").val();
		// 实例对象
		var linkage = new linkageSelect();
		linkage.init({
			"oneSel":['#province','请选择',prov],
		    "twoSel":['#city', '请选择',city],
		    "threeSel":['#region', '请选择',district]
		});
	}
	// 验证
	yipin.checkCode = function(){
		// 验证
	    $.formValidator.initConfig({
		    validatorGroup: '1',
		    formID: 'form1',
		 	errorFocus:false,
		    theme: 'yto'
		});
		// 收货人姓名
		$("#addName").formValidator({
	        onShow:"",
	        onFocus:"",
	        onCorrect:"",
	        tipID:'addNameTips'
		}).inputValidator({
			 min:1,
	    	 max:40,
	    	 empty:false,
	        onError:"请输入1-20个字符组成的姓名"
	    }).functionValidator({
            fun: function (val, el) {
              	 var val=val.replace(/\s+/g,"");
               $("#addName").val(val);
               if(val==""){
               	 return '请输入1-20个字符组成的姓名';
               }
	               if(val.length>20){
	                	 return '请输入1-20个字符组成的姓名';
	                }
              }
          });

		// 手机
		$("#mobile").formValidator({
	        onShow:"",
	        onFocus:"",
	        onCorrect:"",
	        tipID:'telephoneTips'
	    }).inputValidator({
	        min:11,
	        max:11,
	        onError:"收货人手机为 11位数字"
	    }).regexValidator({regExp:"mobile",dataType:"enum",onError:"请输入正确的手机号码"});
		// 固话
	    $("#phoneNum").formValidator({
	        onShow:'',
	        onFocus:'',
	        onCorrect:'',
	        onEmpty:"",
	        empty:true,
	        tipID:'phoneNumTips'
	    }).inputValidator({
	            max:13,
	            onErrorMax:'收货人固定电话为最长12位'
	        }).functionValidator({
	            fun: function (val, el) {
	                var reg=/^([0-9]{3,4}-)?[0-9]{7,8}$/;
	                if(!reg.test(val)){
	                	return '请输入正确的固定电话';
	                	
	                }  
	            }
	        });
	    // 邮编
	    $("#zipcode").formValidator({
	        onShow:'',
	        onFocus:'',
	        onCorrect:'',
	        onEmpty: '',
	        empty: true,
	        tipID:'zipcodeTips'
	    }).inputValidator({
	    	min:6,
            max:6,
            onError:'请输入6位数字的邮编'
	    }).functionValidator({
            fun: function (val, el) {
                var reg=/^\d+$/;
                if(!reg.test(val)){
                	return '请输入6位数字的邮编';
                }  
            }
        });
		// 街道地址
		$("#detailAddress").formValidator({
	        onShow:"",
	        onFocus:"",
	        onCorrect:"",
	        tipID:'addressTips'
	    }).inputValidator({
	    	min:1,
	    	max:100,
	    	empty:false,
	        onError:"请填写1-50个字符组成的地址"
	    }).functionValidator({
            fun: function (val, el) {
              	 var val=val.replace(/\s+/g,"");
               $("#detailAddress").val(val);
               if(val==""){
               	 return '请填写1-50个字符组成的地址';
               }
	               if(val.length>50){
	                	 return '请填写1-50个字符组成的地址';
	                }
              }
          });
	}
	
	// 地址增删改
	yipin.sel_widget = function(){
		$('.deliv_adr').Plugin({
			// 表格行hover
			"trHover": {
			  "eType":'hover', // 事件类型
			  "trList":'.adr_list',
			  "trMCVal":"#fdf8e7" // hover颜色值
			},
			// 表格点击下拉
			"foldingTable":{
				"derail":'.btn_adr',
				"content":'.new_adr'
			}
		});
		$('.deliv_adr').find('.adr_list').on({
			'mouseover':function(){
				$(this).find('.defa').css('visibility','visible');
			},
			'mouseleave':function(){
				$(this).find('.defa').css('visibility','hidden');
			}
		})
		var save =$('#address_save');
		var checkLoad =$('#checkLoad');
		var isDefault = $('#isDefault');
		// 初始化确定选择状态
		if(isDefault.val()==1){
			checkLoad.attr("checked",true);
		}else{
			checkLoad.attr("checked",false);
		}
		// 点击的时候确定设置的状态
		checkLoad.click(function(){
			if(checkLoad.attr("checked")=='checked'){
				isDefault.val('1');
			}else{
				isDefault.val('0');
			}
		});
		// 添加一个收货地址
		save.click(function(){
			$('#regionId').val($("#region").val());
			var form1 = $('#form1');
			if(!isDefault.val()){
				isDefault.val(0);
			}
			var index=0;
			// 省
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
			
			// 去除验证提示
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
			
			setInterval(function(){
				cancelF($('#province'),$("#city"),$("#region"),$("#provinceTip"));
			},1000);
			// 校验数据格式
			var result = $.formValidator.pageIsValid('1');
			if (!result || index>0) {
				return false;   // 验证不通过,直接返回
			}else{
				
				// 地址超过20条
				var mun = $("#addTotal").val();
				var opert = $('#opert').val();
				if(mun >= 20 && opert!='edit'){
					$.dialog({
			             title: false,
			             content:"<span style='color:#C00;'>收货地址条数已超过限制，请修改或删除后再新增！</span>",
			             lock:true,
			             ok:true
			         });
					$(".d-close").hide();
					return
				};
				
				var d = $.dialog({
					title: false,
					content: "<span style='color:#C00;'>正在保存....</span>",
					lock:true,
					fixed:true
				});
				$(".d-close").hide();
				$.ajax({
				type: 'POST',
				url: _ctxPath+'/address/address-addAddress.htm',
				data: form1.serialize(),
				success: function(data){
					d.close();
					// 收货地址 > 20
					if(data.code == 'false'){
						$.dialog({
			                title: false,
			                content:data.info,
			                time: 5000
				            });
						 $(".d-close").hide();
						 dataType:'json'
						 return
					}
					window.location=_ctxPath+'/address/address-getUserAddress.htm';
			        },
				dataType:'json'
				});
			}
		});
	}
	
	// 启动配置
	yipin.init = (function(){
		yipin.select();
		yipin.checkCode();
		yipin.sel_widget();
		
		//input
		$("#form1 input[type='text']").input();
	})()
})

function setDefualt(userAddressId){
	var d = $.dialog({
        title: false,
        content: "<span style='color:#C00;'>正在设置....</span>" ,
        time: 5000,
        lock:true
    });
	$(".d-close").hide();
	$.ajax({
		type: 'POST',
		url: _ctxPath+'/address/address-setDefualtReceiverAddress.htm',
		data: {'receiverAdd.userAddressId':userAddressId},
		success: function(data){
			d.close();
			 window.location.reload();
	    },
		dataType:'json'
	});
}

function setDel(userAddressId,isDefault){
	if(isDefault == 1){
		 $.dialog({
             title: false,
             content:"<span style='color:#C00;'>设置默认收货地址后不可被删除，必须切换默认地址后才可删除。</span>",
             lock:true,
             ok:true
         });
		 $(".d-close").hide();
	}else{
	 $.dialog({
		title: false,
		content: "<span style='color:#C00;'><center><br/>您确定要删除此条地址？</center></span>",
		lock:true,
		fixed:true,
        ok: function(){
            $.ajax({
                type: 'POST',
                url:_ctxPath+'/address/address-delReceiverAddress.htm',
                data: {
                    'receiverAdd.userAddressId':$.trim(userAddressId)
                }
                ,
                success: function(data){
                    $.dialog({
                        title: false,
                        content:data.info,
                        time: 5000
                    });
                    $(".d-close").hide();
                    window.location=_ctxPath+"/address/address-getUserAddress.htm";
                },
                dataType:'json'
            });
        },
        okValue: "确定",
        cancel: true,
        cancelValue: "取消"
	 });
    $(".d-close").hide();
	}
};
	 
function updateAddress(userAddressId){
		window.location=_ctxPath+"/address/address-getSingleReceiverAddress.htm?receiverAdd.userAddressId="+userAddressId+"&opert=edit";
 };
 
 $(".cancel").click(function(){
	 $("#form1 input[type=text]").val("");
	 $('#area_prov').val("");
	 $('#area_city').val("");
	 $('#area_region').val("");
	 $('#regionId').val("");
	 $('#userAddressId').val("");
	 $("#province").val("0");
	 $("#city").val("0");
	 $("#region").val("0");
	 $('#checkLoad').attr("checked",false);
	 $('#isDefault').val('0');
 });
 
 