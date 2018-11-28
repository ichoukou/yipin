/**
 * Creator: yipin
 * Date created: 13-2-27 上午10:19
 * Contact: QQ 77642304
 * Describe: 入库管理 列表查询页
 */
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
    yipin.remeberName = function(name,value){
    	var index = $("#remember").attr("checked");
    	if(index=='checked'){//如果选中了
    		//创建cookie
    		this.setCookie(name,value);
    	}else{//如果没选中 删除cookie
    		this.delCookie(name);
    	}
    };
    yipin.setCookie = function(name,value){
    	this.ajaxCookie("POST",_ctxPath+'/cookies.htm', {"cookieName":name,"index":"add","userName":value});
    };
    yipin.delCookie = function(name){
    	this.ajaxCookie("POST",_ctxPath+'/cookies.htm', {"cookieName":name,"index":"del"});
    };
    yipin.ajaxCookie = function(method,url,info){
    	$.ajax({
			type: method,
			url:url,
			data: info,
			error:function (XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
			},
			success:function(data){
				alert(data);
			},
			dataType:'json'
		});
    };
$(function () {
    // 密码框默认文字
    yipin.psw = function () {
    	//记住密码 清空默认值
    	if($.trim($("#password").val())!=""){
    		$("#pwdStr").hide();
    	}
    	var timer=setInterval(function(){
    		if($.trim($("#password").val())!=""){
        		$("#pwdStr").hide();
        	}
    	},1000)
       $("#password").focus(function(){
        	   $("#pwdStr").hide();
			   $(this).removeClass("input-error");
			   $(this).addClass("input-focus").removeClass("input-default");
           });
           $("#password").blur(function(){
        	   if($(this).val()==""){// if($.trim($(this).val())=="")
            	   $("#pwdStr").show(); 
				   $(this).removeClass("input-focus").addClass("input-default");
        	   }
			   $(this).removeClass("input-focus");
           });
    };
    yipin.getCookie = function(name){
    	 var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    	 if(arr=document.cookie.match(reg))
    	        return unescape(arr[2]);
    	 else
    	        return null;
    };
	// 验证
    yipin.checkCode = function () {
       $.formValidator.initConfig({
				validatorGroup: '1',
				formID: 'login_form',
				theme: 'yto',
				errorFocus: false
				//submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
			});
			
			// 用户名
			$("#account").
				formValidator({validatorGroup:'1', tipID: 'accountTip', onShow: '', onFocus: '', onCorrect: ''})
                .functionValidator({
                    fun: function(val, el) {
                      var val=val.replace(/\s+/g,"");
                      $("#account").val(val);
                        if(val.length<=0||val==$("#account").attr("data-default")){
                            return '请输入你的账号';
                        }
                    }
                });
				
			// 密码
			$("#password").
				formValidator({validatorGroup:'1', tipID: 'passwordTip', onShow: '', onFocus: '', onCorrect: ''})
                .functionValidator({
                    fun: function(val, el) {
                    	var val=val.replace(/\s+/g,"");
                        $("#password").val(val);
                        if(val.length<=0||val==$("#password").attr("data-default")){
                            return '密码不能为空';
                        }
                    }
                })/*.
				regexValidator({regExp: 'password', dataType: 'enum', onError: "密码格式错误"})*/;
			$("#authCode").formValidator({validatorGroup:'1', tipID: 'authCodetTip', onShow: '', onFocus: '', onCorrect: ''})
			.functionValidator({
                    fun: function(val, el) {
                    	if(opertNum>3){
                    		var val=val.replace(/\s+/g,"");
                            $("#authCode").val(val);
                            if(val.length<=0||val==$("#authCode").attr("data-default")){
                                return '验证码不能为空';
                            }
                    	}
                    }
                })
    };
    // 启动配置
    yipin.init = (function () {
    	var yipinusername = yipin.getCookie("yipinusername");//登录名
    	if(yipinusername!=null){//记住用户名
    		var reg = new RegExp('"',"g");  
        	var username = yipinusername.replace(reg, "");
    		$("#account").val(decodeURIComponent(escape(username)));
    	}
		yipin.psw();
		yipin.checkCode();
       $("#account").input();
       $("#authCode").input();
    })();

});


