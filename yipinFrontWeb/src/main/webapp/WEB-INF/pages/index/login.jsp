<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/include/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/l

<html lang="en-US">
<head>
	<title>登录页面</title>
	<jsp:include page="/WEB-INF/pages/include/common.jsp"></jsp:include>
	<!--page css-->
	<link rel="stylesheet" href="${_cssPath}/pages/login.css" />
</head>
<body>
	<%@ include file="/WEB-INF/pages/include/head.jsp"%>
	<!--内容部分 start-->
	<div class="content">
		<!--登陆 start-->
		<div class="login cf">
			<div class="login_left fn_left">
				<h2>登录一城一品<span>立即开始品质之旅/使用其他方式登录“一城一品”</span></h2>
				<div class="login_bd">
					<form action="" method="post" id="login_form">
						<p class="f14">账号：</p>
						<div class="relative"><input type="text" name="username" class="input-text input-default" data-default="请输入账号" id="account" autocomplete="off"/><span id="accountTip"></span></div>
						<p class="f14">密码：</p>
						<div class="relative"><label for="password" class="labelPsw" id="pwdStr">请输入密码</label><input type="password" name="password" autocomplete="off" class="input-text" id="password" data-default="请输入密码"/><span id="passwordTip"></span></div>
						<div style="display:none;" id="codeDiv">
						<p class="f14">验证码</p>
						<div class="relative"><input type="text" class="input-text" data-default="请输入验证码" name="jCaptchaResponse" id="authCode" autocomplete="off"/><span class="yzmImg"><img style="width: 129px;height: 38px;" alt="验证码" id="imgLogin" src="${_ctxPath}/validationCode.htm"></span><a  onclick="nextValidateCode('imgLogin');" class="yzmchange">看不清<br />换一张</a><span id="authCodetTip"></span></div>
						</div>
						<div class="loginBtn_bd"><a href="javascript:" title="登录" id="loginBtn" class="loginBtn">登录</a><label><input id="remember" checked="checked" name="remeberName" type="checkbox" class="memoryUserName"/>记住用户名</label><span class="long_line">|</span><a href="${_ctxPath}/user/user-findPassWord.htm" title="忘记密码了">忘记密码了？</a></div>
						<input type="hidden" name="opertNum" value="${opertNum}" id="opertNum">
						<c:if test="${_qqShow==1}">
							<br>使用合作网站账号登录：<span id="qqLoginBtn"></span> 
						</c:if>
					</form>
					<p class="loginTip" id="loginTip">还未加入“一城一品”？只需一步即可拥有品质生活，请点击<a href="${_ctxPath}/show-register.htm?index=login" title="去注册">去注册></a></p>
				</div>
				<form id="true_form" action="${_ctxPath}/j_spring_security_check" style="display:none;"  method="post">
			    <input type="text"  id="tname" name="j_username" >
			    <input name="j_password" id="tpassword"  type="password">
			    <input type="checkbox" id="remberMe" name="_spring_security_remember_me">
			    </form>

			</div>
			<div class="login_right fn_right">
				<a href="javascript:"><img src="${_imagesPath}/register/banner.jpg" alt="广告广告" /></a>
			</div>
		</div>
		<!--登陆 end-->
	</div>
    <!--内容部分 end-->
	<%@include file="/WEB-INF/pages/include/foot.jsp"%>
</body>
<c:if test="${_qqShow==1}">
<script type="text/javascript">
    //调用QC.Login方法，指定btnId参数将按钮绑定在容器节点中
   QC.Login({
       //btnId：插入按钮的节点id，必选
       btnId:"qqLoginBtn",    
       //用户需要确认的scope授权项，可选，默认all
       scope:"all",
       //按钮尺寸，可用值[A_XL| A_L| A_M| A_S|  B_M| B_S| C_S]，可选，默认B_S
       size: "B_M"
   }, function(reqData, opts){//登录成功
       //根据返回数据，更换按钮显示状态方法
       checkQQLogin(QC.String.escHTML(reqData.nickname));
   }, function(opts){//注销成功
         //alert('QQ登录 注销成功');
         var true_from =$('#qq_logout_form');
    	 true_from.submit();
   }
);

function checkQQLogin(nickname){
	if(QC.Login.check()){//如果已登录
		QC.Login.getMe(function(openId, accessToken){
			//alert(["当前登录用户的", "openId为："+openId, "nickname为："+nickname].join("\n"));
			$.ajax({
				type: 'POST',
				url:_ctxPath+'/user/user-thirdLogin.htm',
				data: {openId:openId,nickName:nickname},
				error:function (XMLHttpRequest, textStatus, errorThrown) {
					alert('服务器异常，请重新登录');
					var true_from =$('#qq_logout_form');
					true_from.submit();
				},
				success:function(data){
					if(data.info=='2' || data.info=='6'){
						//--已经登录或者第一次登录
						$('#qq_name').val("Login_QQ_"+openId);
						$('#qq_password').val(openId);
						var true_from =$('#qq_login_form');
						true_from.submit();
					}
				},
				dataType:'json'
			});
		});
	}
}
</script> 
</c:if>
<script type="text/javascript" src="${_jsPath }/pages/login.js"></script>
<script type="text/javascript">
//登录
$("#account,#password,#authCode").on("keyup",function(e){
    var code = (e.keyCode ? e.keyCode : e.which);
    if (code == 13) {
        $("#loginBtn").trigger('click');
    }
});

//点击输入验证码
function nextValidateCode(id) {
	$("#"+id).attr("src",_ctxPath+'/validationCode.htm?date='+new Date().getTime());
	return false;
}
function showMsg(id,msg,codeId,status){
	opertNum++;
	if(opertNum>3){
		$("#opertNum").val(opertNum);
		$("#codeDiv").show();
	}
	returnMsg(id,msg,codeId,status);
}
function returnMsg(id,msg,codeId,status){
	if(status=='3'){
		//$("#authCode").focus();
		yipin.showIcon.error($("#"+id),msg);
	}else{
		yipin.showIcon.error($("#"+id),msg);
	}
	$("#authCode").val("");
	nextValidateCode(codeId);
}
var opertNum = $("#opertNum").val();//获得当前错误标记
$(function(){
	if(!opertNum){//
		$('#opertNum').val(1);
		opertNum =1;
	}
	//登陆
	$("#loginBtn").click(function(){
		//验证表单输入合法性
		var login_form = $('#login_form');
		var result = $.formValidator.pageIsValid('1'); // 组号必须用带引号
		if(result){
	    	$.ajax({
				type: 'POST',
				url:_ctxPath+'/user/user-checkUserName.htm',
				data: login_form.serializeArray(),
				error:function (XMLHttpRequest, textStatus, errorThrown) {
				},
				success:function(data){
					switch(data.info){
						//1 账号被禁用被禁用 、用户的账号重复 3 验证码不正确 5 密码不正确、用户的邮箱不正确 7注册异常 8  禁止登陆
						case '0':
							showMsg("accountTip","用户不存在","imgLogin",data.info);
							break;
						case '1':
							showMsg("accountTip","用户被禁用","imgLogin",data.info);
							break;
						case '3':
							showMsg("authCodetTip","验证码不正确","imgLogin",data.info);
							break;
						case '5':
							showMsg("accountTip","账号或密码不正确 ","imgLogin",data.info);
							break;
						case '7':
							showMsg("accountTip","登陆异常","imgLogin",data.info);
							break;
						case '8':
							showMsg("accountTip","用户禁止登陆","imgLogin",data.info);
							break;
						case '9':
							showMsg("accountTip","非买家身份禁止登陆","imgLogin",data.info);
							break;
						case '6':
							//yipin.remeberName("yipinusername",$('#account').val());
							//$('#remberMe').attr("checked",$("#remember").attr("checked"));
							$('#tname').val( $('#account').val());
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