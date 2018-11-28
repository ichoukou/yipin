/**
 * Created with JetBrains PhpStorm.
 * User: lll
 * Date: 13-12-11
 * Time: 下午3:45
 * To change this template use File | Settings | File Templates.
 */
/*默认文本*/
var _ctxPath=$('#_ctxPath').val();

function nextValidateCodePass() {
			$("#j_validateCodePass").attr("src",
					_ctxPath+"/validationCode.htm?date="+new Date());
		}
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
	//验证
    yipin.checkCode = function () {
	    $.formValidator.initConfig({
		    validatorGroup: '1',
		    formID: 'getpassFrom',
		    errorFocus:false, //错误时不聚焦到第一个控件
		    theme: 'yto'
		});
		$("#username").formValidator({
			validatorGroup:'1',
            onShow:'',
            onFocus:'',
            onCorrect:'',
            tipID:'usernameTips'
			}).inputValidator({
					"min":1,
					"onErrorMin":'邮箱不能为空'
				}).functionValidator({
                    fun: function(val, el) {
                        var val= $.trim(val);
                        if(val.length<=0||val==$("#username").attr("data-default")){
                            return '邮箱不能为空';
                        }
                    }
                }).regexValidator({
					regExp:"email",
					dataType:"enum",
					onError:"邮箱格式不正确"
				});
		
		$("#j_captcha_1").formValidator({
			validatorGroup:'1',
	        onShow:"",
	        onFocus:"",
	        onCorrect:"",
	        tipID:'j_captcha_1Tips'
	    }).inputValidator({
			"min":1,
			"max":4,
			"onErrorMin":'请填写正确的验证码'
		}).functionValidator({
            fun: function(val, el) {
                var val= $.trim(val);
                if(val.length<=0||val==$("#j_captcha_1").attr("data-default")){
                    return '请填写正确的验证码';
                }
            }
        });
		$("#newPsw").formValidator({
	    	validatorGroup:'1',
	        onShow:"",
	        onFocus:"",
	        onCorrect:"",
	        tipID:'newPswTips'
	    }).inputValidator({
	        min:6,
	        max:15,
	        empty:{
	            leftEmpty:false,
	            rightEmpty:false,
	            emptyError:"密码两边不能有空符号"},
            "onErrorMin":'密码不能小于6位',
            "onErrorMax":'密码不能大于15位'
	    }).regexValidator({
	    	regExp:"^[^\\s]{1,}$",onError:"密码格式不正确"
	    }).functionValidator({
	        fun: function(val, el) {
	            var val= $.trim(val);
	            if(val.length<=0||val==$("#newPsw").attr("data-default")){
	                return '密码不能为空或空格';
	            }
	        }
	    });
	    $("#password").formValidator({
	    	validatorGroup:'1',
	        onShow:"",
	        onFocus:"",
	        onCorrect:"",
	        tipID:'passwordTips'
	    }).inputValidator({
	        min:6,
	        max:15,
	        empty:{
	            leftEmpty:false,
	            rightEmpty:false,
	            emptyError:"重复密码两边不能有空符号"},
            "onErrorMin":'密码不能小于6位',
            "onErrorMax":'密码不能大于15位'
	    }).functionValidator({
	        fun: function(val, el) {
	            var val= $.trim(val);
	            if(val.length<=0||val==$("#password").attr("data-default")){
	                return '密码不能为空或空格';
	            }
	        }
	    }).compareValidator({
	        desID:"newPsw",
	        operateor:"=",
	        onError:"两次密码输入不一致，请重新输入"
	    });
    };
    //提交
    yipin.Form=function(){
	    $("#save").on("click",function(){
	    	//校验数据格式
	    	var result = $.formValidator.pageIsValid('1');
	    	if(result){
	    		//
	    		$.ajax({
    		        type:'POST',
    		        dataType : "json",
    		        url: _ctxPath+"/user/user-ajaxCheckEmail.htm",
    		        data:{'username':$("#username").val()},
    		        success:function(data){
    		        	if(data!=null){
    		        		if(data.code == "false"){
    		        			yipin.showIcon.error($("#usernameTips"),data.info);
       		        		 return false;
                           }else{
                        	   $.ajax({
                   		        type:'POST',
                   		        dataType : "json",
                   		        url: _ctxPath+"/user/user-ajaxCheckJCaptchaResponse.htm",
                   		        data:{'jCaptchaResponse':$("#j_captcha_1").val()},
                   		        success:function(data){
                   		        	if(data!=null){
                   		        		if(data.code == "false"){
                   		        				$("#j_validateCodePass").attr("src",
                   		        						_ctxPath+"/validationCode.htm?date="+new Date().getTime());
                   		        			yipin.showIcon.error($("#j_captcha_1Tips"),data.info);
                      		        		 return false;
                                          }else{
                                        	  var getpassFrom =$('#getpassFrom');
                          			    	  getpassFrom.submit();
                                       	      return true;
                                          }
                   		        	}
                                   }
                   		    	});
                        	   return true;
                           }
    		        	}
                    }
    		    });
	    		return false;
	    	}else{
	    		return false;
	    	}
	    });
    };
    //新密码保存
    yipin.FormPwd=function(){
    	$("#savePwd").on("click",function(){
    		//校验数据格式
	    	var result = $.formValidator.pageIsValid('1');
	    	if(result){
	    		 var getpassFrom =$('#getpassFrom');
			    	  getpassFrom.submit();
          	      return true;
	    	}else{
	    		return false;
	    	}
    	});
    };
 // 启动配置
    yipin.init = (function () {
		yipin.checkCode();
		yipin.Form();
		yipin.FormPwd();
		$("#username").input();
		$("#j_captcha_1").input();
		$("#newPsw").input();
		$("#password").input();
    })();
});
