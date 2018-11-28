<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en-US">
<head>
    <title>404</title>
    <jsp:include page="/WEB-INF/pages/include/common.jsp"></jsp:include>
    <!--page css-->
    <link rel="stylesheet" href="${_cssPath}/pages/fault.css" />
</head>
<body>
    <%@include file="/WEB-INF/pages/include/head.jsp"%>
	<!--内容部分 start-->
	<div class="w_norm">
		<div class="notfind">
			<div class="con"><h3>404</h3><p>We are Sorry，小城找不到你要的页面呐……</p></div>
			<div class="btn"><%--<a href="javascript:history.go(-1);" class="go_prev">返回上一个页面</a> --%><a href="${_ctxPath}/index.htm" class="go_index">返回首页</a></div>
		</div>
	</div>
	<%@include file="/WEB-INF/pages/include/foot.jsp"%>
</body>
</html>