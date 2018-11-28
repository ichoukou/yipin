<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/include/taglibs.jsp"%>
<%@ page import="com.ytoxl.module.core.common.utils.SpringContextUtils"%>
<%@ page import="com.ytoxl.module.yipin.base.service.CityService"%>
<%
    CityService cityService=(CityService)(SpringContextUtils.getContext().getBean("cityServiceImpl"));
	pageContext.setAttribute("city",cityService.listBrandsAndCity());
	//org.springframework.security.authentication.AbstractAuthenticationToken
	//org.springframework.security.web.authentication.WebAuthenticationDetails
	//CustomUserDetails
%>
<!--[if lt IE 9]>  
<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>  
<![endif]-->
<div class="header">
	<div class="w_norm">
		<!--头部左侧 start-->
		
		<div class="header_left fn_left">
		<h1 class="fn_left"><a href="${_ctxPath}/login.htm"><img src="${_imagesPath}/navigation/logo.png" alt="${_webSiteName}" /></a></h1>
		<p class="fn_left introduce">产地直供品质保证</p>
		<%if(!request.getServletPath().contains("passWordstep") && !request.getServletPath().contains("updateerror") && !request.getServletPath().contains("updatesuccess")){%>
		<c:if test="${empty session.SPRING_SECURITY_CONTEXT.authentication.principal}">
			 <sec:authentication property="name" var="name"/>
			 <sec:authentication property="principal" var="loginUser"/><!-- 当前用户 -->
             <c:if test="${name eq 'anonymousUser' }"><%--是游客让其登陆 --%>
	             <p class="fn_left"><a  href="${_ctxPath}/show-login.htm?index=login" title="登录">登录</a><span class="long_line">|</span><a href="${_ctxPath}/show-register.htm?index=register" title="注册">注册</a></p>
		         <p class="fn_left my_yp"><a href="${_ctxPath}/show-login.htm?index=myyipin">我的一品</a></p>
	    	 </c:if>
	    	 <c:if test="${name ne 'anonymousUser' }"><%--是买家判断其真实姓名是否存在 --%>
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
			    	 			<c:set var="username" value="${fn:split(name,'@')[0]}"></c:set>
			    	 			 ${yipin:cutString(username,8)}
			    	 		</c:otherwise>
		    	 		</c:choose>
					</c:otherwise>
	    	 	</c:choose>
	    	 	<span class="long_line">|</span><a href="javascript:logout();" class="topbar-logout"> 退出</a></p>
	    	 	<p class="fn_left my_yp"><a href="${_ctxPath}/order/order-myOrders.htm"">我的一品</a></p>
	    	 </c:if>
	    </c:if>
		<c:if test="${not empty session.SPRING_SECURITY_CONTEXT.authentication.principal}">
		   <p class="fn_left">您好&nbsp;
		   <c:choose>
				<c:when test="${not empty session.SPRING_SECURITY_CONTEXT.authentication.principal.operateName}"><%--首先判断真实姓名 --%>
						${session.SPRING_SECURITY_CONTEXT.authentication.principal.operateName} 
				</c:when>
				<c:otherwise><%--然后判断昵称  --%>
					<c:choose>
						<c:when test="${session.SPRING_SECURITY_CONTEXT.authentication.principal.nickName!=null&&session.SPRING_SECURITY_CONTEXT.authentication.principal.nickName!=''}">
							${session.SPRING_SECURITY_CONTEXT.authentication.principal.nickName}
						</c:when>
						<c:otherwise><%-- 最后用户名 --%>
							<c:set var="username" value="${fn:split(session.SPRING_SECURITY_CONTEXT.authentication.principal.username,'@')[0]}"></c:set>
	       					${yipin:cutString(username,8)}
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose> | <a href="javascript:logout();" class="topbar-logout"> 退出</a></p>
	       <p class="fn_left my_yp"><a href="${_ctxPath}/order/order-myOrders.htm">我的一品</a></p>
	    </c:if>
	    <%} %>
		</div>
		<!--头部左侧 end-->
		<!--头部右侧 start-->
		<div class="header_right fn_right">
			<ul>
			    <c:forEach items="${city}" var="city" varStatus="status">
				<li <c:choose>
					<c:when test="${(status.index + 1) % 2 != 0 }">class="fir"</c:when>
					<c:otherwise>class="sec"</c:otherwise>
				</c:choose>>
					<a href="javascript:;">${city.name}</a>
					<ul>
					   <c:forEach items="${city.brands}" var="brands" varStatus="status">
						     <li><a target="_blank" href="${_ctxPath}/item-${brands.brandId}.htm" target="_blank" rel="nofollow">${brands.name}</a></li>
					   </c:forEach>
					</ul>
				</li>
			 </c:forEach>
			</ul>
			<div class="find">
				<a href="${_ctxPath}/suggest/myFind.htm">发现</a>
			</div>
		</div>
		<!--头部右侧 end-->
	</div>
</div>
<form id="qq_login_form" action="${_ctxPath}/j_spring_security_check" style="display:none;"  method="post">
    	 <input type="text"  id="qq_name" name="j_username" >
		 <input type="text"  id="qq_password"  name="j_password" >
		 <input type="checkbox" id="remberMe" name="_spring_security_remember_me">
</form>
<form id="qq_logout_form" action="${_ctxPath}/j_spring_security_logout" style="display:none;"  method="post">
</form>
<script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" data-appid="${_qqDataAppid}" data-redirecturi="${_qqDataRedirecturi}" charset="utf-8"></script>
<script type="text/javascript">
function logout(){
	 QC.Login.signOut();
	 var true_from =$('#qq_logout_form');
	 true_from.submit();
}
</script>