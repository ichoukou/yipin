/**
 * Creator: yipin
 * Describe: 修改密码
 */
$(function () {
    // 定义一个全局类
    var yipin = yipin || {};
    var _ctxPath=$('#_ctxPath').val();
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
	//ajax
	 yipin.ajaxValidator = {
        password: {valid: false, complete: false}
    };
	// 验证
    yipin.checkCode = function () {
       $.formValidator.initConfig({
				validatorGroup: '1',
				formID: 'changePswForm',
				theme: 'yto',
				errorFocus: false
			});
			
			// 密码
			$("#password").
				formValidator({
					validatorGroup:'1', 
					tipID: 'passwordTip', 
					onShow: '', 
					onFocus: '', 
					onCorrect: ''
			}).functionValidator({
                    fun: function(val, el) {
                       // var val= $.trim(val);
                        if(val.length<=0||val==$("#password").attr("data-default")){
                            return '密码不能为空';
                        }
                    }
                }).inputValidator({
                    "min":6,
                    "max":15,
                    "onErrorMin":'格式错误,长度为6-15位！',
                    "onErrorMax":'格式错误,长度为6-15位！'
                }).functionValidator({
					fun: function(val, el) {
						var textBox = $(el);
						var	password = $.trim(textBox.val());
						var	newPassword = $.trim($("#newPsw").val());
						var	newPswEn = $.trim($("#newPswEn").val());
						var tip = $('#passwordTip');
						yipin.ajaxValidator["password"]["complete"] = false;
						yipin.ajaxValidator["password"]["valid"] = false;
						$.ajax({
							data:{
								"newPassword":newPassword,
								"password":password
							},
							url :  _ctxPath+'/user/user-checkPwdVaild.htm',
							datatype : "post",
							success: function(data) {
								if(data.info=='false'){
									yipin.showIcon.error(tip, '请输入正确的密码');
									return false;
								}
								else if(data.info=='true'){
									tip.html("");
									yipin.ajaxValidator["password"]["valid"] = true;
								}
							},
							cache: false,
							complete: function(){
								tip.show();
								yipin.ajaxValidator["password"]["complete"] = true;
							}
						});
					}
				});
				// 新密码
				$("#newPsw").
					formValidator({validatorGroup:'1', onShow: '', onFocus: '', onCorrect: ''})
					.functionValidator({
						fun: function(val, el) {
							var val= $.trim(val);
							if(val.length<=0||val==$("#newPsw").attr("data-default")){
								return '新密码不能为空';
							}
						}
					}).inputValidator({
	                    "min":6,
	                    "max":15,
	                    "onErrorMin":'格式错误,长度为6-15位！',
	                    "onErrorMax":'格式错误,长度为6-15位！'
	                })/*.
					regexValidator({regExp: 'password', dataType: 'enum', onError: "密码格式错误"})*/;
				
			$("#newPswEn").formValidator({validatorGroup:'1',onShow:"",onFocus:"",onCorrect:""})
			.inputValidator({min:1,onError:"确认新密码不能为空"})
			.compareValidator({desID:"newPsw",operateor:"=",onError:"2次密码不一致"});
    };
	yipin.Form=function(){
		$("#saveBtn").on("click",function(){
			//验证表单
            var result = $.formValidator.pageIsValid('1');
			if(!result){
				return false;
			}
			
			
			//验证通过,存在AJAX异步验证,定时循环,等待验证结果
            if($("#password").length>0){
                var timer = setInterval(function(){
                    var complete = true,valid = true;
                    for(prop in yipin.ajaxValidator){
                        if(!yipin.ajaxValidator[prop]["complete"]){
                            complete = false;
                        }
                        if(!yipin.ajaxValidator[prop]["valid"]){
                            valid = false;
                        }
                    }
                    if(complete) {                 //AJAX已验证,
                        clearInterval(timer);      //去除定时循环
                    }else {
                        return;                    //未验证,继续
                    }

                    if(!valid) {   //验证不通过,跳出
                        return;
                    }
                   
                    var newPd =$("#newPsw").val();
                    var pwd =$("#password").val();
                    $.ajax({
                        url: _ctxPath+'/user/user-updatePassword.htm',
                        datatype : "post",
                        data: {
                		        "password":pwd,
                		        "newPassword":newPd
                		        },
                        success: function (result) {
                			if(result.info=='success'){
                				 $.dialog({
									title: false,
									content: "密码修改成功!",
							        lock: true,
							        fixed: true,
							        okValue: "确定",
									ok: function(){
										location.href =_ctxPath+'/user/user-changePsw.htm';
									}
//							        cancel: true,
//							        cancelValue: "取消"
								 });
                				 $(".d-close").hide(); //屏蔽关闭按钮
                				
                			}
                        }
                		});
                    
//                    $("#changePswForm").submit();
                },500);
			}
		});
	}
    // 启动配置
    yipin.init = (function () {
		yipin.checkCode();
		yipin.Form();
       $("#password").input();
       $("#newPsw").input();
       $("#newPswEn").input();
    })();
});

