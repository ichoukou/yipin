<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
	<title>完善资料</title>
	<jsp:include page="/WEB-INF/pages/include/common.jsp"></jsp:include>
	<!--page css-->
	<link rel="stylesheet" type="text/css" href="${_cssPath }/pages/login.css" media="all" />
</head>
<body>
	<%@include file="/WEB-INF/pages/include/head.jsp"%>
	<!--内容部分 start-->
	<div class="content">
		<div class="w_norm">
		<div class="regSuc">
			<h5>恭喜，${session.SPRING_SECURITY_CONTEXT.authentication.principal.username}已注册成功！</h5>
			<div class="g_buy"><a href="${_ctxPath}/index.htm">立即购物</a></div>
			<div class="tip">检测到<em>您不是邮箱注册用户</em>，建议您完善资料，并填写您的邮箱，已便<a href="#" class="f_psw">找回密码</a>！ <a href="${_ctxPath}/user/user-userInfo.htm" class="w_info">立即完善资料</a></div>
		</div>
	</div>
	</div>
	<!--内容部分 end-->
	<%@include file="/WEB-INF/pages/include/foot.jsp"%>
</body>
</html>