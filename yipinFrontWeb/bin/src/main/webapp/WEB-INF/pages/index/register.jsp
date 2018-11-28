<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
	<title>注册页面</title>
	<jsp:include page="/WEB-INF/pages/include/common.jsp"></jsp:include>
	<!--page css-->
	<link rel="stylesheet" href="${_cssPath}/pages/login.css" />
</head>
<body>
	<%@include file="/WEB-INF/pages/include/head.jsp"%>
	<!--内容部分 start-->
	<div class="content">
		<!--登陆 start-->

		<div class="login cf">
			<div class="login_left fn_left">
				<h2>注册一城一品账号<span class="registerTip">只需一步即可成为尊贵会员</span></h2>
				<div class="login_bd">
					<form action="" id="register_form">
						<p class="f14">邮箱：</p>
						<div class="relative"><input type="text" class="input-text input-default" name="username" autocomplete="off" data-default="请输入邮箱" id="mail"/><span id="mailTip"></span></div>
						<p class="f14">密码：</p>
						<div class="relative"><label for="password" class="labelPsw" id="passwordStr">请输入密码</label><input type="password" name="password" class="input-text" id="password" autocomplete="off"/><span id="passwordTip"></span></div>
						<p class="f14">确认密码：</p>
						<div class="relative"><label for="psw" class="labelPsw" id="pswStr">请确认密码</label><input type="password" class="input-text" id="psw" autocomplete="off"/><span id="pswTip"></span></div>
						<p class="f14">验证码</p>
						<div class="relative"><input type="text" class="input-text" name="jCaptchaResponse" data-default="请输入验证码" id="authCode" autocomplete="off"/><span class="yzmImg"><img alt="验证码" style="width: 129px;height: 38px;"  id="imgLogin" src="${_ctxPath}/validationCode.htm"></span><a class="yzmchange" onclick="nextValidateCode('imgLogin');">看不清<br />换一张</a><span id="authCodetTip"></span></div>
						<div class="loginBtn_bd"><a href="javascript:" title="立即注册" id="registerBtn" class="loginBtn">立即注册</a></div>
						<p><label><input type="checkbox" checked="checked" id="cheboxTK"/> 我已阅读并接受</label> <a href="${_ctxPath}/user/serviceTerms.htm" target="_blank" title="一城一品服务条款">一城一品服务条款</a><span id="cheboxTKTip"></span></p>
					</form>
					<p class="loginTip">已有一城一品账号！已有账号，登录后开始品质之旅<a href="${_ctxPath}/show-login.htm?index=register" title="立即登录">立即登录></a></p>
				</div>
			</div>
			<div class="login_right fn_right">
				<a href="javascript:"><img src="${_imagesPath}/register/banner.jpg" alt="广告广告" /></a>
			</div>
			<form id="true_form" action="${_ctxPath}/j_spring_security_check" style="display:none;"  method="post">
			    <input type="text"  id="tname" name="j_username" >
			    <input name="j_password" id="tpassword"  type="text">
			    <input type="checkbox" id="remberMe" name="_spring_security_remember_me">
			</form>
		</div>
		<!--登陆 end-->

	</div>
	<!--内容部分 end-->
	<%@include file="/WEB-INF/pages/include/foot.jsp"%>
</body>
<script type="text/javascript" src="${_jsPath }/pages/register.js"></script>
<script type="text/javascript">
//登录
$("#mail,#password,#psw,#authCode").on("keyup",function(e){
    var code = (e.keyCode ? e.keyCode : e.which);
    if (code == 13) {
        $("#registerBtn").trigger('click');
    }
});
function returnMsg(id,msg,codeId,status){
	$("#authCode").val("");
	switch(status){
		//1 账号异常 2 账号重复 5验证码失误 6注册成功
		case '1':
			yipin.showIcon.error($("#"+id),msg);
			break;
		case '2':
			yipin.showIcon.error($("#"+id),msg);
			break;
		case '5':
			//$("#authCode").focus();
			yipin.showIcon.error($("#"+id),msg);
			break;
	}
	nextValidateCode(codeId);
}
//点击输入验证码
function nextValidateCode(id) {
	$("#"+id).attr("src",_ctxPath+'/validationCode.htm?date='+new Date().getTime());
	return false;
}
$(function(){
	$("#registerBtn").click(function(){
		var register_form = $("#register_form");
		var index = 0;
		var result = $.formValidator.pageIsValid('1');
			if(!$("#cheboxTK").prop("checked")){
				$("#cheboxTKTip").html("请接受条款");
				$("#cheboxTKTip").show();
				index++;
			}
		if(result&&index<=0){
			//验证表单合法性
			$.ajax({
				type: 'POST',
				url:_ctxPath+'/register-email.htm',
				data: register_form.serializeArray(),
				error:function (XMLHttpRequest, textStatus, errorThrown) {
					
				},
				success:function(data){
					switch(data.info){
						//1 账号异常 2 账号重复 5验证码失误 6注册成功
						case '1':
							returnMsg("mailTip","注册异常!","imgLogin",data.info);
							break;
						case '2':
							returnMsg("mailTip","邮箱已经注册!","imgLogin",data.info);
							break;
						case '5':
							returnMsg("authCodetTip","验证码错误!","imgLogin",data.info);
							break;
						case '6':
							$('#tname').val( $('#mail').val());
							$('#tpassword').val($('#password').val());
							var true_from =$('#true_form');
							true_from.submit();
							break;//表示成功
					}
				},
				dataType:'json'
			});
		}
	});
})
</script>
</html>