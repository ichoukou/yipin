<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<title>修改密码</title>
	<jsp:include page="/WEB-INF/pages/include/common.jsp"></jsp:include>
	<!--css-->
	<link rel="stylesheet" href="${_cssPath}/lib/global.css" />
	<!--page css-->
	<link rel="stylesheet" href="${_cssPath}/pages/changePsw.css" />
</head>
<body>

	<%@include file="/WEB-INF/pages/include/head.jsp"%>
	
	<!--内容部分 start-->
	<div class="w_norm">
		<!-- 用户中心框架 start-->
		<div class="uc cf">
			<!-- 用户中心左侧 start-->
			<%@include file="/WEB-INF/pages/include/left.jsp"%>
			<!-- 用户中心左侧 end-->
			<!-- 用户中心右侧 start-->
			<div class="uc_r">
			<div class="ucr_hd">修改密码 <span class="f12"> | 为了保证您的账户安全，请设置至少6位以上的密码，最好将字母与数字混合使用</span></div>
			<c:if test="${not empty session.SPRING_SECURITY_CONTEXT.authentication.principal}">
				<c:if test="${not empty session.SPRING_SECURITY_CONTEXT.authentication.principal.nickName}">
					<div class="change_psw">第三方登录帐号，暂不支持修改密码！</div>
				</c:if>
				<c:if test="${empty session.SPRING_SECURITY_CONTEXT.authentication.principal.nickName}">
					<div class="change_psw">
						<form  id="changePswForm" action="${_ctxPath}/user/user-updatePassword.htm">
							<div class="cf psw_list">
								<label class="mange_label fn_left">原密码：</label>
								<div class="psw_input">
									<input type="password" class="input-text" id="password"  name="password"/>
									<span id="passwordTip"></span>
								</div>
							</div>
							<div class="cf psw_list">
								<label class="mange_label fn_left">新密码：</label>
								<div class="psw_input">
									<input type="password" class="input-text" id="newPsw" name="newPassword"/>
									<span id="newPswTip"></span>
								</div>
							</div>
							<div class="cf psw_list">
								<label class="mange_label fn_left">确认新密码：</label>
								<div class="psw_input">
									<input type="password" class="input-text" id="newPswEn"/>
									<span id="newPswEnTip"></span>
								</div>
							</div>
							<div class="save">
								<a href="javascript:" class="saveBtn" id="saveBtn">保存修改</a>
							</div>
							<input type="hidden" id="_ctxPath" value="${_ctxPath}" >
						</form>
					</div>
				</c:if>
			</c:if>
			</div>
			<!-- 用户中心右侧 end-->
		</div>
		<!-- 用户中心框架 end-->
	</div>
	<!--内容部分 end-->
	
	<%@include file="/WEB-INF/pages/include/foot.jsp"%>
	
	<script type="text/javascript" src="${_jsPath}/pages/changePsw.js"></script>
</body>
</html>