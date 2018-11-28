<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html lang="zh-CN" class="ua-window">
<head>
<meta charset="utf-8" />
<title>帮助中心-${_webSiteName }</title>
      <jsp:include page="/WEB-INF/pages/include/common.jsp"></jsp:include>
	<link rel="stylesheet" type="text/css" href="${_cssPath}/pages/notice.css" media="all" />
</head>
<body>
	  <%@include file="/WEB-INF/pages/include/head.jsp"%>
	<!--头部导航 end-->
	<div class="content cf">
		<p style="padding-bottom:5px;"><a href="${_domain }">首页</a><span class="m5">></span>公告列表</p>
		<div class="help_left fn_left">
			<div class="hd">${_webSiteName }帮助中心</div>
		<div class="bd">
			<c:set value="${fn:length(helpCategorys) }" var="size"></c:set>
			<c:forEach items="${helpCategorys}" var="helpCategory" varStatus="status">
			<dl <c:if test="${status.index + 1 == size}">class="last"</c:if>>
	    		<dt>${helpCategory.name}</dt>
	    		<c:forEach items="${helpCategory.helpCategorys}" var="help1">
					<dd <c:if test="${help1.help.helpId==help.helpId}">class="on"</c:if> >
						<a href="${_ctxPath}/help/help-${help1.help.helpId}.htm"  title="${help1.name}" class="helpName" >• ${help1.name}</a>
					</dd>
				</c:forEach>
	   		</dl>
			</c:forEach>
			<dl class="last">
				<dt>最新动态</dt>
				<dd <c:if test="${not empty noticePage}">class="on"</c:if>><a href="${_ctxPath }/notice/notice-noticeList.htm" title="动态列表" class="notice" >• 动态列表</a></dd>
			</dl>
		</div>
		</div>
		<!--help left end-->
		<!--notice left start-->
		<div class="help_right fn_right" id="notice">
				<table class="notice_table" width="100%">
					<thead>
						<tr>
							<th width="60%">标题</th>
							<th width="40%" style="text-align:center">时间</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty noticePage.result}">
								<c:forEach items="${noticePage.result}" var="notice">
									<tr>
										<td>
											<c:choose>
												<c:when test="${fn:length(notice.title)<20}">
													<a href="${_ctxPath}/notice/detail-${notice.noticeId}.htm">${notice.title}</a>
												</c:when>
												<c:otherwise>
													<a href="${_ctxPath}/notice/detail-${notice.noticeId}.htm">${fn:substring(notice.title,0,20)}</a>
												</c:otherwise>
											</c:choose>
										
										</td>
										<td style="text-align:center"><fmt:formatDate value="${notice.publishTime}" pattern="yyyy-MM-dd  HH:mm:ss" />  </td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="2">
										暂时还没有动态
									</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
				<!--pagination start-->
				<div class="pagination cf">
					<div id="lastFy">
				        <c:if test="${not empty noticePage.result}">
				        <yp:yipinFrontPage urlEnd=".htm"
										urlStart="noticeList-${noticePage.total}-${noticePage.limit}-"
										page="${noticePage}"></yp:yipinFrontPage>
				     	</c:if>
			        </div>
				</div>	
				<!--pagination end-->	
		</div>
		<!--notice right end-->
		
	</div>
	<!-- foot -->
	<%@include file="/WEB-INF/pages/include/foot.jsp"%>
</body>
</html>