$(function(){
		//图片上传操作
		var uploadDefaultParams = {
			'auto' : true,
			'buttonImg' :_jsPath+'/plugin/uploadify/uploadimg_btn.png',
			'cancelImg' : _jsPath+'/plugin/uploadify/cancel.png',
			'expressInstall' : _jsPath+'/plugin/uploadify/expressInstall.swf',
			'fileDataName' : 'file',
			'fileDesc' : '请选择jpg、gif、png文件',
			'fileExt' : '*.jpg;*.jpeg;*.gif;*.png',
			'height' : 20,
			'multi' : false,
			'script' : _ctxPath+'/upload/upload.htm',
			'sizeLimit' : 2097152,
			'uploader' : _jsPath+'/plugin/uploadify/uploadify.allglyphs.swf',
			'width' : 94
		};

		var uploadImgParams = uploadDefaultParams;
		uploadImgParams.scriptData = {'category' : 'seller'};
		uploadImgParams.onComplete = function(event, ID, fileObj, response, data) {
			$('#uploadImageValueId').val(response);
			//得到缩略图url
			var suffix = response.split(".")[1];
			var url = _fileThumbPath + response + "_t24." + suffix;
			$(".license").empty();
			$(".license").append("<img src='"+url+"'/>");
		};
		$('#imgUpload').uploadify(uploadImgParams);
		
		
		
		
		
		//初始化ckeditor编辑器
	    var businessScopeEditor = CKEDITOR.replace("userInfo.businessScope");
	    var remarkEditor = CKEDITOR.replace("userInfo.remark");
	    function validatorSelect(cssName,id,isValid){
	    	$(cssName).formValidator({
	    		empty:isValid,
	        	validatorGroup: '2001',
	            onShow:"请选择地区",
	            onFocus:"请选择具体地区",
	            onCorrect:"",
	            tipID:id
	        }).inputValidator({min:1,onError: "请选择具体地区!"});
	    }
	    $(function(){
	   	 	//验证
			$.formValidator.initConfig({
 	    	    validatorGroup: '2001',
 	    	    formID: 'form1',
 	    	 	errorFocus:false,
 	    	    theme: 'Default'
 	    	});
	    	//验证三级联动地址
		 	//validatorSelect(".company","regionTips1",true);
		 	validatorSelect(".shiper","regionTips2",false);
		 	validatorSelect(".receiver","regionTips3",false);
	    	 var prov = $("#company_area_prov").val(), city = $("#company_area_city").val(), district = $("#company_area_region").val();
	    	 var linkage = new linkageSelect();
             linkage.init({
                 "oneSel":['#company_province','请选择',prov],
                 "twoSel":['#company_city', '请选择',city],
                 "threeSel":['#company_region', '请选择',district]
             });
             var prov1 = $("#shiper_area_prov").val(), city1 = $("#shiper_area_city").val(), district1 = $("#shiper_area_region").val();
	    	 var linkage1 = new linkageSelect();
             linkage1.init({
                 "oneSel":['#shiper_province','请选择',prov1],
                 "twoSel":['#shiper_city', '请选择',city1],
                 "threeSel":['#shiper_region', '请选择',district1]
             });
             var prov2 = $("#receiver_area_prov").val(), city2 = $("#receiver_area_city").val(), district2 = $("#receiver_area_region").val();
	    	 var linkage2 = new linkageSelect();
             linkage2.init({
                 "oneSel":['#receiver_province','请选择',prov2],
                 "twoSel":['#receiver_city', '请选择',city2],
                 "threeSel":['#receiver_region', '请选择',district2]
             });
	    	//linkageSel1.changeValues([$("#shiper_area_prov").val(), $("#shiper_area_city").val(), $("#shiper_area_region").val()]);
	    	//linkageSel2.changeValues([$("#receiver_area_prov").val(), $("#receiver_area_city").val(), $("#receiver_area_region").val()]);
	    	
	    	$('#submitForm').bind('click',function(){
	    		//成立日期
	    		var regDateStr = $("#rq").val();
	    		if(regDateStr){
		    		$("#registerDate").val(regDateStr + "-01");	
	    		}
		        $('#companyRegionCodes').val($("#company_region").val());
		        $('#shiperRegionCodes').val($("#shiper_region").val());
		        $('#receiverRegionCodes').val($("#receiver_region").val());
		        $('#form1').submit();
		        
		    });
	    });
	   
	  $.formValidator.initConfig({
		  formID:"form1",
		  theme:"Default",
		  submitOnce:true,
		  wideWord:false,
		  onError:function(){}
	  });
	  
	  
	  $("#business-name")
	  .formValidator({
		  	onShow:"由6~30位，英文，中文，数字和下划线组成",
	  		onFocus:"由6~30位，英文，中文，数字和下划线组成",
	  		onCorrect:"登陆名可以使用"})
	  	.regexValidator({
	  		regExp:"^[a-zA-Z0-9_\\u4E00-\\u9FA5]{6,30}$",
	  		dataType:"string",
	  		onError:"由6~30位，英文，中文，数字和下划线组成"})
	  	.inputValidator({
	  		min:6,max:30,
	  		onErrorMin:"请输入大于6个字符的登陆名",
	  		onErrorMax:"请输入不大于30个字符的登陆名",
	  		empty:{
	  			leftEmpty:false,
	  			rightEmpty:false,
	  			emptyError:"登陆名两边不能有空格"
	  			},
	  		onError:"你输入的品牌名称格式错误"})
	  	.ajaxValidator({ 
			dataType : 'json',              
			async : true,   
			data:{
				'userInfo.user.username':function(){return $("#business-name").val();}
			},
			url : _ctxPath+'/admin/user/user-validLoginName.htm',             
			success : function(data){  
				if(data.info==1){
					return false;
				}
				return true;              
			},              
			buttons: $("#submitForm"),              
			error: function(jqXHR, textStatus, errorThrown){
			alert("服务器没有返回数据，可能服务器忙，请重试"+errorThrown);},              
			onError : "该登录名已经存在",              
			onWait : "正在对登录名进行合法性校验，请稍候..."          
		});
	  
		$("#linkman").formValidator({
			onShow:"请输入2~20位英文或中文姓名",
			onFocus:"请输入2~20位英文或中文姓名"
		}).inputValidator({
			min:2,
			max:20,
			empty:{
				leftEmpty:false,
				rightEmpty:false,
				emptyError:"业务联系人两边不能有空格"
				},
			onError:"请输入2~20位英文或中文姓名"
		}).functionValidator({
			fun: function(val, el){
				var val= $.trim(val),
					reg=/^[a-zA-Z\u4e00-\u9fa5]+$/;
				if(!reg.test(val)){
					return '请输入2~20位英文或中文姓名'
				}
			}
		});
		$("#tel-text").formValidator({
			onShow:"请输入手机号码",
			onFocus:"请输入手机号",
			onCorrect:"谢谢你的合作"
		}).inputValidator({
			min:11,
			max:11,
			onError:"手机号码必须是11位的,请确认"
		}).regexValidator({
			regExp:"mobile",
			dataType:"enum",
			onError:"手机号码格式不正确"
		});
		$("#QQ").formValidator({
			empty:true,
			onShow:"请输入QQ号码",
			onFocus:"请输入QQ号码",
			onCorrect:"谢谢你的合作，你的QQ号码正确"
		}).inputValidator({
			min:5,
			max:10
		}).regexValidator({
			regExp:"qq",
			dataType:"enum",
			onError:"QQ号码格式不正确"
		});
		$("#fax").formValidator({
			empty:true,
			onShow:"请输入你的传真号码",
			onFocus:"请输入正确的传真号码",
			onCorrect:"谢谢你的合作"
		}).inputValidator({
			min:10,
			max:12,
			onError:"传真号码不正确，请确认"
		}).regexValidator({
			regExp:"tel",
			dataType:"enum",
			onError:"你输入的传真号码格式不正确"
		});;
		$("#dh").formValidator({
			empty:true,
			onShow:"请输入固定电话",
			onFocus:"例如：021-88888888或省略区号88888888",
			onCorrect:"谢谢你的合作，你的固定电话正确"
		}).regexValidator({
			regExp:"tel",
			dataType:"enum",
			onError:"国内电话格式不正确"
		});
	  	$("#company-name").formValidator({
	  		onShow:"由6~30个字符组成",
	  		onFocus:"由6~30个字符组成",
	  		onCorrect:"公司名称可以使用"
	  	}).inputValidator({
	  		min:6,
	  		max:30,
	  		empty:{
	  			leftEmpty:false,
	  			rightEmpty:false,
	  			emptyError:"公司名称两边不能有空格"
	  			},
	  		onError:"你输入的公司名称格式错误"
	  	});
		
	  	$("#company-type").formValidator({
	  		empty:true,
	  		onShow:"请填写3~20个字符",
	  		onFocus:"请填写3~20个字符",
	  		onCorrect:"公司类型可以使用"
	  	}).inputValidator({
	  		min:3,
	  		max:20,
	  		empty:{
	  			leftEmpty:false,
	  			rightEmpty:false,
	  			emptyError:"公司类型两边不能有空格"
	  			},
	  		onError:"请填写3~20个字符"
	  	});
 		$("#rq").formValidator({
 			empty:true,
 			onShow:"请输入成立日期",
 			onFocus:"例如：2012-01",
 			onCorrect:"成立日期正确"
 		}).regexValidator({
 			regExp:"^\\d{4}(\\-|\\/|\.)\\d{1,2}$",
 			dataType:"string",
 			onError:"日期格式不正确"
 		});
		$("#regdit-zb").formValidator({
			empty:true,onShow:"请填写0~8位数字",
			onFocus:"请填写0~8位数字",
			onCorrect:"谢谢你的合作，你的注册资本数正确"
		}).inputValidator({
			min:0,
			max:8
		}).regexValidator({
			regExp:"decmal4",
			dataType:"enum",
			onError:"请填写0~8位数字"
		});
		$("#ss-zb").formValidator({
			empty:true,
			onShow:"请填写0~8位数字",
			onFocus:"请填写0~8位数字",
			onCorrect:"谢谢你的合作，你的实收资本数正确"
		}).inputValidator({
			min:0,
			max:8
		}).regexValidator({
			regExp:"decmal4",
			dataType:"enum",
			onError:"请填写0~8位数字"
		});
		
		$("#url").formValidator({
			empty:true,
			defaultValue:""
		}).inputValidator({
			min:3,
			max:100,
			onError:"你输入公司网站格式不正确"
		}).regexValidator({
			regExp:"url",
			dataType:"enum",
			onError:"你输入公司网站格式不正确"
		});
		$("input:checkbox").formValidator({
			tipID:"brandTip",
			onShow:"请选择可售品牌",
			onFocus:"你至少选择1个可售品牌",
			onCorrect:"恭喜你,你选对了"
		}).inputValidator({
			min:1,
			onError:"你至少选择1个可售品牌"
		});
		//$("#addr-info").formValidator({empty:true,tipID:"addrTip",onShow:"请输入0~30位的详细地址",onFocus:"请输入0~30位的公司详细地址"}).inputValidator({min:0,max:30,empty:{leftEmpty:false,rightEmpty:false,emptyError:"两边不能有空格"},onError:"请输入0~30位的详细地址"});
		$("#add-f").on("click","select",function(){
	    	var obj = $(this).next("select");
	    	if(obj.length == 0){
	    		obj = $(this);
	    	}
	    	obj.formValidator({
		          onFocus:"请选择具体地区",
		          tipID:"addrTip-f"
		      }).inputValidator({min:1,onError: "请选择具体地区!"});
	    });
		$("#addr-info-f").formValidator({
			onShow:"请输入1~30位的详细地址",onFocus:"请输入1~30位的详细地址"}).inputValidator({
			min:1,
			max:30,
			empty:{leftEmpty:false,rightEmpty:false,emptyError:"两边不能有空格"},
			onError:"请输入1~30位的详细地址"});
		$("#add-t").on("click","select",function(){
	    	var obj = $(this).next("select");
	    	if(obj.length == 0){
	    		obj = $(this);
	    	}
	    	obj.formValidator({
		          onFocus:"请选择具体地区",
		          tipID:"addrTip-t"
		      }).inputValidator({min:1,onError: "请选择具体地区!"});
	    });
		$("#addr-info-t").formValidator({
			onShow:"请输入1~30位的详细地址",onFocus:"请输入1~30位的详细地址"}).inputValidator({
			min:1,
			max:30,
			empty:{leftEmpty:false,rightEmpty:false,emptyError:"两边不能有空格"},
			onError:"请输入1~30位的详细地址"});
		
		$("#juridicalPperson").formValidator({
			empty:true,
			onShow:"请输入2~20位英文或中文姓名",
			onFocus:"请输入2~20位英文或中文姓名"
		}).inputValidator({
			min:2,
			max:20,
			empty:{
				leftEmpty:false,
				rightEmpty:false,
				emptyError:"两边不能有空格"
				},
			onError:"请输入2~20位英文或中文姓名"
		});
		$("#registration").formValidator({
			empty:true,
			onShow:"请输入税务登记号",
			onFocus:"请输入税务登记号",
			onCorrect:"谢谢你的合作，你的税务登记号正确"
		}).inputValidator({
			min:1,
			max:20,
			empty:{
				leftEmpty:false,
				rightEmpty:false,
				emptyError:"两边不能有空格"
				},
			onError:"请填写1~20位字符"
		});
		$("#companyNum").formValidator({
			empty:true,
			onShow:"请输入企业编码",
			onFocus:"请输入企业编码",
			onCorrect:"谢谢你的合作，你的企业编码输入正确"
		}).inputValidator({
			min:0,
			max:20,
			empty:{
				leftEmpty:false,
				rightEmpty:false,
				emptyError:"两边不能有空格"
				},
			onError:"请填写0~20位字符"
		});
		$("#registrationNum").formValidator({
			empty:true,
			onShow:"请输入企业法人营业执照注册号",
			onFocus:"请输入企业法人营业执照注册号",
			onCorrect:"你的企业法人营业执照注册号输入正确"
		}).inputValidator({
			min:15,
			max:15,
			onError:"请填写15位数字"
		}).regexValidator({
			regExp:"intege1",
			dataType:"enum",
			onError:"请填写15位数字"
		});
		
// 		$("#alipay").formValidator({tipID:"paynum",onShow:"请输入支付宝帐号",onFocus:"请输入支付宝帐号"}).inputValidator({min:1,max:30,empty:{leftEmpty:false,rightEmpty:false,emptyError:"两边不能有空格"},onError:"请输入1~30位的支付宝帐号"});
// 		$("#alipayName").formValidator({tipID:"paynum",onShow:"请输入注册支付宝姓名",onFocus:"请输入注册支付宝姓名"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"两边不能有空格"},onError:"你输入的姓名错误"});
// 		$("#bank").formValidator({tipID:"bankTip",onShow:"请输入开户银行",onFocus:"请输入开户银行"}).inputValidator({min:3,max:30,empty:{leftEmpty:false,rightEmpty:false,emptyError:"两边不能有空格"},onError:"你输入3~30位的银行名称"});
// 		$("#bankcardNum").formValidator({tipID:"bankTip",onShow:"请输入1~20位数字的银行卡号",onFocus:"请输入1~20位数字的银行卡号"}).inputValidator({min:1,max:20,empty:{leftEmpty:false,rightEmpty:false,emptyError:"两边不能有空格"},onError:"请输入1~20位数字的银行卡号"});
// 		function allEmpty(val,elem){return ($("#alipay").val()=="" && $("#bank").val()=="")?'为了保证您能收到货款，请至少填写一种收<br/>款帐号！':true;} 
	
		$("#alipay,#bank,#alipayName,#bankcardNum").formValidator({
			tipID:"paynum",onFocus:"银行账号和支付宝账号至少填写一个",onCorrect:"输入正确！"
			})
			.functionValidator({
				fun : function() {
					var $alipay = $("#alipay"),
						$alipayName = $("#alipayName"),
					    $bank = $("#bank"),
					    $bankcardNum = $("#bankcardNum");
					    
					var alipayVal = $.trim($alipay.val()),
					    bankVal = $.trim($bank.val()),
					    alipayNameVal = $.trim($alipayName.val()),
					    bankcardNumVal = $.trim($bankcardNum.val());
					
					if(alipayVal.length == 0 && bankVal.length == 0
							&& alipayNameVal.length == 0 && bankcardNumVal.length == 0){
						return "银行账号和支付宝账号至少填写一个";
					}
					if(alipayVal.length > 0 || alipayNameVal.length > 0){
						var $span = $("#paynum");
						$("#paynum").remove();
						var $td = $alipayName.closest("tr").find("td:last");
						$td.append($span);
						
						if(alipayVal.length == 0 || alipayVal.length > 30){
							return"请输入1~30位的支付宝帐号";
						}
						if(alipayNameVal.length < 1 || alipayNameVal.length > 50){
							return "请输入1~50位的注册支付宝姓名";
						}
					}
					if(bankVal.length > 0 || bankcardNumVal.length > 0){
						var $span = $("#paynum");
						$("#paynum").remove();
						var $td = $bankcardNum.closest("tr").find("td:last");
						$td.append($span);
						
						if(bankVal.length < 3 || bankVal.length > 30){
	 						return "请输入3~30位的开户银行名称";
	 					}
						if(!/^\d{1,20}$/.test(bankcardNumVal)){
							return "请输入1~20位数字的银行卡号";
						}
					}
				}
			});
		$("#uploadImageValueId").formValidator({
			empty:true, 
			onShow: "请输入图片名", 
			onCorrect: "谢谢你的合作，你的图片名正确"
		}).inputValidator({
			min:1,
			onErrorMin:"请上传图片"
		}).regexValidator({
			regExp: "picture", 
			dataType: "enum", 
			onError: "图片名格式不正确" 
		});
		$('#businessScope').formValidator({
			empty:true,
			tipId : "businessScopeTip",
			onShow : "请填写经营范围",
			onFocus : "经营范围必须填写",
			onCorrect : "谢谢您的配合"
		}).inputValidator({
			min : 0,
			max : 1000,
			onError : "长度为需小于1000个字符"
		}).functionValidator({
			fun : function() {
				var describe = businessScopeEditor.getData();
				if(describe.length == 0){
					return "请输入经营范围";
				}
// 				else if(describe.length > 100 || describe.length<3){
// 					return "请输入请输入3~100个字符个字符";
// 				}
			}
		});
		
		$('#remark').formValidator({
			tipId : "remarkTip",
			onShow : "请填写公司简介",
			onFocus : "公司简介必须填写",
			onCorrect : "谢谢您的配合"
		}).inputValidator({
			min : 0,
			max : 1000,
			onError : "长度为需小于1000个字符"
		}).functionValidator({
			fun : function() {
				var remark = remarkEditor.getData();
				if(remark.length == 0){
					return "请输入公司简介";
				}
// 				else if(remark.length > 200 ||　remark.length<3){
// 					return "请输入3~200个字符";
// 				}
			}
		});
		$("#email").formValidator({
			onShow : "请输入邮箱",
			onFocus : "邮箱6-100个字符,输入正确了才能离开焦点",
			onCorrect : "恭喜你,你输对了"
		}).inputValidator({
			min : 6,
			max : 100,
			onError : "邮箱格式错误"
		}).regexValidator({
			regExp:"email",
			dataType:"enum",
			onError:"邮箱格式不正确"
		}).ajaxValidator({
			type : 'GET',
			url : _ctxPath+'/admin/user/user-validateEmailIsRepate.htm',
			data : {
				"userId" : function(){return $("#sellerUserId").val();},
				"email" : function(){return $("#email").val();}
			},
			datatype : "json",
			async : "true",
			success : function(data) {
				var json = $.parseJSON(data);
				if (json.code == "false") {
					return false;
				}
				return true;
			},
			buttons: $("#submitForm"),
			error : function(jqXHR, textStatus, errorThrown) {
				alert("服务器没有返回数据，可能服务器忙，请重试" + errorThrown);
			},
			onError : "该邮箱已被使用，请更换邮箱",
			onWait : "正在对邮箱进行合法性校验，请稍候..."
		});
		
	  //品牌筛选
	  var bindCheckBox=function(){
		  $(':checkbox[name="userInfo.listBrandIds"]').bind('click',function(){
			  var brandName = $(this).next().text();
			  var st=$('#selectedBrandNames').text();
			  if(this.checked){
			 	 if($('#selectedBrandNames').text() == ''){
						$('#selectedBrandNames').text(brandName);
					}else{
						$('#selectedBrandNames').text(st + ',' + brandName);
					}
				}else{
					$(this).removeAttr('checked');
					if(st.indexOf(brandName) == 0){
						if(st.indexOf(',')==-1){
							$('#selectedBrandNames').text(st.replace(brandName,''));
						}else{
							$('#selectedBrandNames').text(st.replace(brandName+',',''));
						}
					}else if(st.indexOf(brandName)>0){
						$('#selectedBrandNames').text(st.replace(','+brandName,''));
					}
				}
				
			});
	  };
		$(document).ready(function(){
			$("#add-f select").click();
			$("#add-t select").click();
			
			$('#backList').bind('click', function(){
				  window.location.href=_ctxPath+'/admin/user/user-sellerManage.htm';
			});
			bindCheckBox();
			
			
// 			获取省份
			  $.ajax({
				    "url":_ctxPath+'/admin/user/user-getCity.htm',
				    "type": 'get',
				    "success": function (result) {
				    	var html='<option value="">--请选择--</option>';
				    	var selecrOption=$("#brandCityId").val();
				    	var selectUserId=$("#sellerUserId").val();
				    	var select='';
				    	$("#selectCity").html(html);
				        // 数据是否存在
				        if (result) {
				            var jsonData = $.parseJSON(result.info);
				            for (var i = 0; i < jsonData.length; i ++) {
				            	if(selectUserId !='' && selectUserId!=null){
					            	if(selecrOption==jsonData[i].cityId){
					            		select='selected="selected"';
					            	}else{
					            		select='';
					            	}
				            	}
				            	html+='<option value="'+jsonData[i].cityId+'" '+select+'>'+jsonData[i].name+'</option>';
				            }
				            $("#selectCity").html(html);
				        }
				    },
				    "error": function () {
				        alert('请求错误，请稍后尝试:'+url);
				    }
				});
			
			
		});
		
		/*var Salable = $('.br-list'),
			checkBox = Salable.find('input:checkbox');
		checkBox.on('click',function(){
			$(this).removeAttr('checked');
		})*/
		$("#selectCity").change(function(){
			$('.br-list').html('');
			$('#selectedBrandNames').text('');
		});
		
		$(".sal-brand-t a").click(function(){
			var text = $(this).text();
			/* if(text.length > 1){
				text = "";
			} */
			var brand_temp = "brand-" + text;
//			if(!$("#"+brand_temp)[0]){
				var selectValue=$("#selectCity").val();
				alert(2)
				if(selectValue !=''){
					$.ajax({
						url : _ctxPath+'/admin/brand/brand-listBrands.htm',
						type : "POST",
						data : {
							'brand.firstChar' : text,
							'brand.userInfo.userInfoId': $('#sellerId').val(),
							'brand.userInfo.user.userId':$('#sellerUserId').val(),
							'brand.cityId':selectValue
						},
						dataType : "html",
						success : function(data) {
							if(data!='' && data!=null){
								var data="<div id=\""+brand_temp+"\" class=\"br-list\">"+data+"</div>";
								$(".sal-brand-t").after($(data));
								$("#"+brand_temp).show().siblings(".br-list").hide();
								$(':checkbox[name="userInfo.listBrandIds"]').unbind('click');
								bindCheckBox();
							}
						},
						error : function(data) {
							alert(data.responseText);
						}
					});
					
				}else{
					alert("请选择城市！")
				}
				
//			}else{
//				$("#"+brand_temp).show().siblings(".br-list").hide();
//			}
			
		});
		
	
});
