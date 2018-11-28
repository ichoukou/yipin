<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en-US">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>我的吐槽</title>
	<jsp:include page="/WEB-INF/pages/include/common.jsp"></jsp:include>
	<!--page css-->
	<link rel="stylesheet" href="${_cssPath}/pages/changePsw.css" />
</head>
<body>
	<!--头部导航 start-->
	<%@include file="/WEB-INF/pages/include/head.jsp"%>
	<!--头部右侧 end-->
	<!--头部导航 end-->
	<div class="w_norm">
		<!-- 吐槽框架 start-->
		<div class="uc cf">
			<div class="uc_r">
				<!--内容部分 start-->
				<div class="my_find style="width:812px;">
					<div class="tc_tit">
						<span class="f20">吐槽内容</span>
						<span class="find" style="display: none; color: #F63C30;">您还没有登录，<a href="${_ctxPath}/show-login.htm?index=login">登录</a>后才可以提交吐槽哦~</span>
					</div>
					<div class="send cf myfindposition">
						<form action="${_ctxPath}/suggest/saveSuggest.htm" id="mySuggestForm" class="cf">
							<input type="hidden" id="types" name="suggest.type" value="1" />
							<div class="fn_left relative IE7static">
								<textarea name="suggest.content" id="myFindText" class="H140" data-default="请在这里写下您想告诉我们的任何内容。我们将尽快完善这些问题，请随时回来视察我们的工作，并查看您的积分，会有惊喜哦。"></textarea>
								<div class="IE7relative">
									<span id="myFindTextTip"></span>
								</div>
							</div>
							<div class="fn_right">
								<a href="javascript:" class="sendBtn" id="sendBtn">下达指令<br /><i>提交我的吐槽</i></a>
							</div>
						</form>
						<p>您还可以输入<i class="red J_textTip"></i>个字</p>
				 	</div>
				</div>
			</div>
		</div>
		<!-- 吐槽框架 end-->
	</div>
	<!--内容部分 end-->
	<!--底部 start-->
	<%@include file="/WEB-INF/pages/include/foot.jsp"%>
	<!--底部 end-->
	<!--page js-->
	<script type="text/javascript" src="${_jsPath}/pages/myFind.js"></script>
</body>
</html>