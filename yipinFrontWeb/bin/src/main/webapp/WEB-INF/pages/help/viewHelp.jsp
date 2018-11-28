<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html lang="zh-CN" class="ua-window">
<head>
<meta charset="utf-8" />
<title>帮助中心-${_webSiteName }</title>
      <jsp:include page="/WEB-INF/pages/include/common.jsp"></jsp:include>
	<link rel="stylesheet" type="text/css" href="${_cssPath}/pages/help.css" media="all" />
</head>
<body>
	  <%@include file="/WEB-INF/pages/include/head.jsp"%>
	<!--头部导航 end-->
	<div class="content cf">
		<div class="help_left fn_left">
			<div class="hd">${_webSiteName }帮助中心</div>
		<div class="bd">
			<c:set value="${fn:length(helpCategorys) }" var="size"></c:set>
			<c:forEach items="${helpCategorys}" var="helpCategory" varStatus="status">
			<dl <c:if test="${status.index + 1 == size}">class="last"</c:if>>
	    		<dt>${helpCategory.name}</dt>
	    		<c:forEach items="${helpCategory.helpCategorys}" var="help1">
					<dd <c:if test="${help1.help.helpId==help.helpId}">class="on"</c:if> >
						<a href="javascript:void(0)" title="${help1.name}" data-id=${help1.help.helpId } class="helpName" >${help1.name}</a>
					</dd>
				</c:forEach>
	   		</dl>
			</c:forEach>
		</div>
		</div>
	<!--help left end-->
		<!--help right start-->
		<div class="help_right fn_right">
			<div class="hd relative">
				<p><a href="${_domain }">首页</a><span class="m5">></span>帮助中心<span class="m5">></span><span id="categoryName">${help.helpCategoryName}</span></p>
				<h2 id="title">${help.helpName} </h2>
			</div>
			<div class="bd">
				<div class="bd cf" id="content">${help.content }</div>
			</div>
		</div>
		<!--help right end-->
	</div>
	<!-- foot -->
	<%@include file="/WEB-INF/pages/include/foot.jsp"%>
    <script type="text/javascript" src="${_jsPath }/pages/viewHelp.js"></script>
</body>
</html>