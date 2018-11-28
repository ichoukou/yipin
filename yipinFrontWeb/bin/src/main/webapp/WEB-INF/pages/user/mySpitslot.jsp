<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en-US">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>吐槽</title>
	<!--css-->
	<link rel="stylesheet" href="${_cssPath}/lib/global.css" />
	<!--page css-->
	<link rel="stylesheet" href="${_cssPath}/pages/changePsw.css" />
</head>
<body>
	<!--头部导航 start-->
	<%@include file="/WEB-INF/pages/include/head.jsp"%>
	<!--头部右侧 end-->
	<!--头部导航 end-->
	<div class="w_norm">
		<!-- 用户中心框架 start-->
		<div class="uc cf">
			<!-- 用户中心右侧 start-->
			<div class="uc_r">
				<!--内容部分 start-->
				<div class="my_find">
					<div class="send cf">
						<form action="${_ctxPath}/suggest/saveSuggest.htm" id="mySuggestForm">
							<input type="hidden" id="types" name="suggest.type" value="1"/>
							<div class="fn_left relative">
								<textarea name="suggest.content" id="myFindText" data-default="登录后才可以提交发现哦"></textarea>
								<span id="myFindTextTip"></span>
							</div>
							<div class="fn_right">
								<a href="javascript:" class="sendBtn" id="sendBtn"></a>
							</div>
							<c:if test="${not empty session.SPRING_SECURITY_CONTEXT.authentication.principal}">
								<div>
									<a href="javascript:" class="searchMySpitslot">查看我的吐槽</a>
								</div>
							</c:if>
						</form>
					</div>
				</div>
			</div>
			<!-- 用户中心右侧 end-->
		</div>
		<!-- 用户中心框架 end-->
	</div>
	<!--内容部分 end-->
	<!--底部 start-->
	<%@include file="/WEB-INF/pages/include/foot.jsp"%>
	<!--底部 end-->
	<!--page js-->
	<script type="text/javascript" src="${_jsPath}/pages/myFind.js"></script>
</body>
</html>