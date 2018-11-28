/**
 * Creator: yipin
 * Date created: 13-2-27 上午10:19
 * Contact: QQ 77642304
 * Describe: 入库管理 列表查询页
 */
var yipin = yipin || {};
//显示提示
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
$(function () {
    // 定义一个全局类
    
	
    // 密码框默认文字
    yipin.psw = function () {
    	//记住密码
    	if($.trim($("#password").val())!=""){
    		$("#passwordStr").hide();
    	}
		$("#mail").focus(function(){
    	   $("#usernameStr").hide();
		   $(this).removeClass("input-error");
		   $(this).addClass("input-focus").removeClass("input-default");
       });
       $("#mail").blur(function(){
    	   if($(this).val()==""){
        	   $("#usernameStr").show(); 
			   $(this).removeClass("input-focus").addClass("input-default");
    	   }
		   $(this).removeClass("input-focus");
       });
       $("#password").focus(function(){
    	   $("#passwordStr").hide();
		   $(this).removeClass("input-error");
		   $(this).addClass("input-focus").removeClass("input-default");
       });
       $("#password").blur(function(){
    	   if($(this).val()==""){
        	   $("#passwordStr").show(); 
			   $(this).removeClass("input-focus").addClass("input-default");
    	   }
		   $(this).removeClass("input-focus");
       });
	   
	   $("#psw").focus(function(){
    	   $("#pswStr").hide();
		   $(this).removeClass("input-error");
		   $(this).addClass("input-focus").removeClass("input-default");
       });
       $("#psw").blur(function(){
    	   if($(this).val()==""){
        	   $("#pswStr").show(); 
			   $(this).removeClass("input-focus").addClass("input-default");
    	   }
		   $(this).removeClass("input-focus");
       });
    };
	// 验证
    yipin.checkCode = function () {
       $.formValidator.initConfig({
				validatorGroup: '1',
				formID: 'register_form',
				theme: 'yto',
				errorFocus: false
				//submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
			});
			
			//邮箱
			$("#mail").formValidator({
			validatorGroup:'1',
            "onShow":'',
            "onFocus":'',
            "onCorrect":''
			}).functionValidator({
                    fun: function(val, el) {
                    	  var val=val.replace(/\s+/g,"");
                          $("#mail").val(val);
                        if(val.length<=0||val==$("#mail").attr("data-default")){
                            return '用户名不能为空或空格';
                        }else{
                        	var email = '^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$';
                        	var tel = '^1\\d{10}$';
                        	var t =/@+/;
                        	if(t.test(val)){//如果含有@就是邮箱
                        		if(!val.match(email)){//验证是否为邮箱
                            		return '邮箱格式不正确';
                            	}
                        	}else{
								if(val.length>25||val.length<6){
									return '请输入6-25位字母数字符号或组合';
								}
							}
                        }
                        
                    }
                })
				
			// 密码
			$("#password").
				formValidator({validatorGroup:'1', tipID: 'passwordTip', onShow: '', onFocus: '', onCorrect: ''})
                .functionValidator({
                    fun: function(val, el) {
                    	var val=val.replace(/\s+/g,"");
                        $("#password").val(val);
                        if(val.length<=0||val==$("#password").attr("data-default")){
                        	$("#password").val("");
                            return '密码不能为空';
                        }
                    }
                }).inputValidator({
                    "min":6,
                    "max":15,
                    "onErrorMin":'请输入6-15位数字、字母或符号组合',
                    "onErrorMax":'请输入6-15位数字、字母或符号组合'
                });
			//验证码
			$("#authCode").
				formValidator({validatorGroup:'1', tipID: 'authCodetTip', onShow: '', onFocus: '', onCorrect: ''})
                .functionValidator({
                    fun: function(val, el) {
                    	var val=val.replace(/\s+/g,"");
                        $("#authCode").val(val);
                        if(val.length<=0||val==$("#authCode").attr("data-default")){
                            return '验证码不能为空';
                        }
                    }
                })
				
			$("#psw").formValidator({validatorGroup:'1',onShow:"",onFocus:"",onCorrect:""})
			.inputValidator({min:1,onError:"确认密码不能为空"})
			.compareValidator({desID:"password",operateor:"=",onError:"两次密码输入不一致，请重新输入"});
    };
    yipin.Form=function(){
		$("#loginBtn").on("click",function(){
			var index=0;
			//验证表单
            var result = $.formValidator.pageIsValid('1');
			if(!$("#cheboxTK").prop("checked")){
				$("#cheboxTKTip").html("请接受条款");
				$("#cheboxTKTip").show();
				index++;
			}
			if(result&&index<=0){
				$("#login_form").submit();
			}
		});
	}
    // 启动配置
    yipin.init = (function () {
		yipin.psw();
		yipin.checkCode();
       $("#mail").input();
       $("#authCode").input();
    })();

});