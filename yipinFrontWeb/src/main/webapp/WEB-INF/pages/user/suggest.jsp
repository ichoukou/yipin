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
		<!-- 用户中心框架 start-->
		<div class="uc cf">
			<!-- 用户中心左侧 start-->
				<%@include file="/WEB-INF/pages/include/left.jsp"%>
			<!-- 用户中心左侧 end-->
			<!-- 用户中心右侧 start-->
			<div class="uc_r">
				<div class="ucr_hd">我的吐槽</div>
				<!--内容部分 start-->
				<div class="my_find">
					<table width="100%" class="table">
						<thead>
							<tr>
								<th width="680px" id="tilt">吐槽内容/管理员回复</th>
								<th width="110px">获得积分</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${suggestPage.result}" var="suggest" varStatus="status">
								<tr <c:if test="${(suggest.revertContent!=null and suggest.revertContent!='') or (suggest.point!=null and suggest.point!='')}">class="reply"</c:if>>
									<td><P class="wordBreak">${escape:escapeHtml(suggest.content)}</P>
										<p class="time">[<fmt:formatDate value="${suggest.createTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>]</p>
										<c:if test="${suggest.revertContent!=null and suggest.revertContent!=''}">
											<div class="manage_reply">
												<p class="com_txt">管理员回复：${escape:escapeHtml(suggest.revertContent)}</p>
												<p class="time">[<fmt:formatDate value="${suggest.revertTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>]</p>
											</div>
										</c:if>
									</td>
									<td class="point_style">
										<c:choose>
											<c:when test="${(suggest.revertContent!=null and suggest.revertContent!='') or (suggest.point!=null and suggest.point!='')}">
											+${suggest.point == null ? 0 : suggest.point}
											</c:when>
											<c:otherwise>
												等待<br />管理员点评
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="send cf">
						<form action="" id="mySuggestForm" class="cf">
						<input type="hidden" id="types" name="suggest.type" value="${suggestPage.params.type}"/>
							<h3 id="sub">我要吐槽</h3>
							<div class="fn_left relative">
								<textarea name="suggest.content" maxlength="1000" id="myFindText" class="input-default" data-default="请在这里写下您想告诉我们的任何内容。我们将尽快完善这些问题，请随时回来视察我们的工作，并查看您的积分，会有惊喜哦。"></textarea>
								<span id="myFindTextTip"></span>
							</div>
							<div class="fn_right">
								<a href="javascript:" class="sendBtn" id="sendBtn">下达指令<br /><i>提交我的吐槽</i></a>
							</div>
						</form>
						<p>您还可以输入<i class="red J_textTip"></i>个字</p>
					</div>
					<!-- 分页 -->
					<div class="navigation-wp">
						<c:if test="${not empty suggestPage.result}">
							<yp:yipinFrontPage urlEnd=".htm"
								urlStart="searchSuggest-${suggestPage.params.type}-${suggestPage.limit }-${suggestPage.total}-"
								page="${suggestPage}"></yp:yipinFrontPage>
						</c:if>
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