<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en-US">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>我的发现</title>
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
		<!-- 用户中心框架 start-->
		<div class="uc cf">
			<!-- 用户中心右侧 start-->
			<div class="uc_r">
				<!--内容部分 start-->
				<div class="my_find">
					<div class="tc_tit"><span class="f20">发现内容</span>
						<span class="find" style="display:none;color:#F63C30;">您还没有登录，<a href="${_ctxPath}/show-login.htm?index=login">登录</a>后才可以提交发现哦~</span>
					</div>
					<div class="send cf myfindposition">
						<form action="${_ctxPath}/suggest/saveSuggest.htm" id="mySuggestForm" class="cf">
							<input type="hidden" id="types" name="suggest.type" value="0"/>
							<div class="fn_left relative IE7static">
								<textarea name="suggest.content" class="H140" id="myFindText" data-default="在“一城一品”的品质之旅还未尽兴？请写下您想要的特色商品或者为我们推荐特色商品，全国6000家分公司将努力为您搜寻，只要目标出现您将获得高额积分，赶快行动起来吧。"></textarea>
								<div class="IE7relative">
									<span id="myFindTextTip"></span>
								</div>
							</div>
							<div class="fn_right">
								<a href="javascript:" class="sendBtn" id="sendBtn"></a>
							</div>
						</form>
						<p>您还可以输入<i class="red J_textTip"></i>个字</p>
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