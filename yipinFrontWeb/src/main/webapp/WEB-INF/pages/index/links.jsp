<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<html lang="zh-CN" class="ua-window">
<head>
<meta charset="utf-8" />
<title>友情链接</title>
    <link rel="shortcut icon" type="image/x-icon" href="${_ctxPath}/favicon.ico" media="screen" />
    <link rel="icon" type="image/x-icon" href="${_ctxPath}/favicon.ico" media="screen" />
    <link rel="stylesheet" type="text/css" href="${_cssPath}/lib/global.css" media="all" />
	<link rel="stylesheet" type="text/css" href="${_cssPath}/pages/links.css" media="all">
</head>
<body>
	<!--头部导航 start-->
	<jsp:include page="../include/head.jsp" flush="true" />
	<!--头部导航 end-->
	<div class="w_norm">
        <div class="links-wrap">
            <div class="tit"><div class="crumbs"><a href="${_domain }">${_webSiteName} ></a>友情链接</div><span>友情链接</span></div>
            <ul class="cf">
                <c:forEach items="${linksList}" var="links" varStatus="status">
                    <li><a href="${links.linkUrl }" target="_blank">${links.name }</a></li>
                </c:forEach>
            </ul>
        </div>
	</div>
	<!--底部 start-->
	<%@include file="/WEB-INF/pages/include/foot.jsp"%>
	<!--底部 end-->
</body>
</html>