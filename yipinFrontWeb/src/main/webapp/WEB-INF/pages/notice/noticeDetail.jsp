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
	<script type="text/javascript" >
		function getDetail(noticeId,e) {
			var url = '${_ctxPath }/notice/detail-' + noticeId + '.htm';
			window.location.href = url;
			//阻止默认浏览器动作(W3C) 
			if ( e && e.preventDefault ) 
				e.preventDefault(); 
			//IE中阻止函数器默认动作的方式 
			else
				window.event.returnValue = false; 
			return false; 
		}

    </script>
</head>
<body>
	<%@include file="/WEB-INF/pages/include/head.jsp"%>
	<div class="content cf">
		<p style="padding-bottom:5px;"><a href="${_domain}">首页</a><span class="m5">></span><a href="${_domain}/notice/notice-noticeList.htm">公告列表</a><span class="m5">></span><span> ${yipin:cutString(notice.title,8)}</span></p>
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
				<dd <c:if test="${not empty notice}">class="on"</c:if>><a href="${_ctxPath }/notice/notice-noticeList.htm" title="" class="notice" >• 动态列表</a></dd>
			</dl>
		</div>
		</div>
		<div class="help_right fn_right">
			<div class="hd relative">
				<div class="remark" style=' float:right;padding-top:20px; color: #A1A1A1 '>时间：<fmt:formatDate value='${notice.publishTime}' pattern='yyyy-MM-dd HH:mm:ss'/></div>
				<h2 id="title">${escape:escapeHtml(notice.title)}</h2>
			</div>
       		<div class="bd">
       			<div class="bd cf" id="content">
        			<br/>
        			<!--此处为文本编辑器 start-->
		  			<span>${notice.content}</span>
		  			<!--此处为文本编辑器 end-->
       			</div>
       		</div>
       		<div id = "_page" style="border-top: 1px solid rgb(204, 204, 204); margin-top: 20px; padding-top: 10px;">
	       		<p>
	       			上一篇：
	       			<c:if test="${previousNotice.noticeId != null && previousNotice.noticeId != ''}">
	       				<a href="javascript:;" onclick="getDetail(${previousNotice.noticeId},this)">${previousNotice.title}</a>
	       			</c:if>
	       			<c:if test="${previousNotice.noticeId == null || previousNotice.noticeId == ''}">
	       				已经是第一篇了
	       			</c:if>
	       		</p>
	       		<p>
					下一篇：
					<c:if test="${nextNotice.noticeId != null && nextNotice.noticeId != ''}">
						<a href="javascript:;" onclick="getDetail(${nextNotice.noticeId},this)">${nextNotice.title}</a>
					</c:if>
					<c:if test="${nextNotice.noticeId == null || nextNotice.noticeId == ''}">
						已经是最后一篇了
					</c:if>
				</p>
       		</div>
		</div>
		<!--help right end-->
	</div>
	<!-- foot -->
	<%@include file="/WEB-INF/pages/include/foot.jsp"%>
</body>
</html>