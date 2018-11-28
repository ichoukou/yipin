<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<!--[if lt IE 9]>  
	<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script> 
<![endif]--> 
<div class="topbar">
			<div class="w_norm tar">
				<div class="fl">
				<%if(!request.getServletPath().contains("passWordstep") && !request.getServletPath().contains("updateerror") && !request.getServletPath().contains("updatesuccess")){%>
		<c:if test="${empty session.SPRING_SECURITY_CONTEXT.authentication.principal}">
			 <sec:authentication property="name" var="name"/>
			 <sec:authentication property="principal" var="loginUser"/><!-- 当前用户 -->
             <c:if test="${name eq 'anonymousUser' }">
	             <a  href="${_ctxPath}/show-login.htm?index=login" title="登录">登录</a>|</span><a href="${_ctxPath}/show-register.htm?index=register" title="注册">注册</a>
		         <a href="${_ctxPath}/show-login.htm?index=myyipin">我的一品</a>
	    	 </c:if>
	    	 <c:if test="${name ne 'anonymousUser' }">
	    	 	<p class="fn_left">您好&nbsp;
	    	 	<c:choose>
	    	 		<c:when test="${not empty loginUser.operateName}">
	    	 			${loginUser.operateName}
	    	 		</c:when>
					<c:otherwise>
						<c:choose>
			    	 		<c:when test="${loginUser.nickName!=null&&loginUser.nickName!=''}">
				           		 ${loginUser.nickName}
			    	 		</c:when>
			    	 		<c:otherwise>
			    	 			<c:set var="username" value="${name}"></c:set>
			    	 			 ${yipin:cutString(username,11)}
			    	 		</c:otherwise>
		    	 		</c:choose>
					</c:otherwise>
	    	 	</c:choose>
	    	 	|<a href="javascript:logout();" class="topbar-logout"> 退出</a>
	    	 	<a href="${_ctxPath}/order/order-myOrders.htm"">我的一品</a></p>
	    	 </c:if>
	    </c:if>
		<c:if test="${not empty session.SPRING_SECURITY_CONTEXT.authentication.principal}">
		   <p class="fn_left">您好&nbsp;
		   <c:choose>
				<c:when test="${not empty session.SPRING_SECURITY_CONTEXT.authentication.principal.operateName}">
						${yipin:cutString(session.SPRING_SECURITY_CONTEXT.authentication.principal.operateName,11)}
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${session.SPRING_SECURITY_CONTEXT.authentication.principal.nickName!=null&&session.SPRING_SECURITY_CONTEXT.authentication.principal.nickName!=''}">
							${yipin:cutString(session.SPRING_SECURITY_CONTEXT.authentication.principal.nickName,11)}
						</c:when>
						<c:otherwise>
							<c:set var="username" value="${session.SPRING_SECURITY_CONTEXT.authentication.principal.username}"></c:set>
	       					${yipin:cutString(username,11)}
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose> | <a href="javascript:logout();" class="topbar-logout"> 退出</a>
	        <a href="${_ctxPath}/order/order-myOrders.htm">我的一品</a></p>
	    </c:if>
	    <%} %>
				</div>
				<span class="cs">客服热线：021-6090 4918 转 8012</span><a href="javascript:;">帮助中心</a>
			</div>
		</div>
<form id="qq_logout_form" action="${_ctxPath}/j_spring_security_logout" style="display:none;"  method="post">
</form>
<%-- --%> <script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" data-appid="${_qqDataAppid}" data-redirecturi="${_qqDataRedirecturi}" charset="utf-8"></script>
<script type="text/javascript">
function logout(){
	 QC.Login.signOut();
	 var true_from =$('#qq_logout_form');
	 true_from.submit();
}
</script>
