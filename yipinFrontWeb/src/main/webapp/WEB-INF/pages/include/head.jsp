<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="org.springframework.security.core.context.SecurityContextImpl,org.springframework.security.core.Authentication"%>
<%@ include file="/WEB-INF/pages/include/taglibs.jsp"%>
<%@ page import="com.ytoxl.module.core.common.utils.SpringContextUtils"%>
<%@ page import="com.ytoxl.module.yipin.base.service.CityService"%>
<%@ page import="com.ytoxl.module.yipin.base.service.PropService"%>
<%@ page import="com.ytoxl.module.yipin.content.service.AdvertisementService"%>
<%@ page import="com.ytoxl.module.yipin.base.dataobject.Prop"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>

<%
    CityService cityService=(CityService)(SpringContextUtils.getContext().getBean("cityServiceImpl"));
	pageContext.setAttribute("city",cityService.listBrandsAndCity());
	PropService propService = (PropService)(SpringContextUtils.getContext().getBean("propServiceImpl"));
	AdvertisementService advertisementService =
		(AdvertisementService)(SpringContextUtils.getContext().getBean("advertisementServiceImpl")); 
	Prop prop = new Prop();
	prop.setLevel(Prop.LEVEL_REGION);
	prop.setCode(Prop.CODE_PLACE);
	prop.setStatus(Prop.STATUS_NORMAL);
	List<Prop> propList = new ArrayList<Prop>();
	propList = propService.listByProp(prop);
	// 大区集合
	pageContext.setAttribute("regionList",propList);
	pageContext.setAttribute("districtADList",advertisementService.selectAdvByAddressAndCategory(propList));
	// 商品分类集合
	prop.setCode(Prop.CODE_PRODUCT);
	propList = propService.listByProp(prop);
	pageContext.setAttribute("productList",propList);
	pageContext.setAttribute("productADList",advertisementService.selectAdvByAddressAndCategory(propList));
	Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");//登陆情况下有值
	if(obj!=null){
		pageContext.setAttribute("aut",obj);
	}
%>
<!--[if lt IE 9]>  
<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>  
<![endif]-->
<!--头部 start-->
		<div class="topbar">
		
			<div class="w_norm tar">
				<div class="fl">
	<%if(!request.getServletPath().contains("passWordstep") && !request.getServletPath().contains("updateerror") && !request.getServletPath().contains("updatesuccess")){%>
		<c:if test="${empty session.SPRING_SECURITY_CONTEXT.authentication.principal}">
					<%if(obj==null){%>
			             <a  href="${_ctxPath}/show-login.htm?index=login" title="登录">登录</a> | <a href="${_ctxPath}/show-register.htm?index=register" title="注册">注册</a>
				         <a href="${_ctxPath}/show-login.htm?index=myyipin" class="go_uc">我的一品</a>
					<%}else{%>
						<p class="fn_left">您好&nbsp;
			    	 	<c:choose>
			    	 		<c:when test="${aut.authentication.principal.operateName!=null&&aut.authentication.principal.operateName!=''}">
			    	 					 <a href="${_ctxPath}/user/user-userInfo.htm" class="w_info">${yipin:cutString(aut.authentication.principal.operateName,11)}</a>
			    	 		</c:when>
							<c:otherwise>
								<c:choose>
					    	 		<c:when test="${aut.authentication.principal.nickName!=null&&aut.authentication.principal.nickName!=''}">
						           		  <a href="${_ctxPath}/user/user-userInfo.htm" class="w_info">${yipin:cutString(aut.authentication.principal.nickName,11)}</a>
					    	 		</c:when>
					    	 		<c:otherwise>
					    	 			 <a href="${_ctxPath}/user/user-userInfo.htm" class="w_info">${yipin:cutString(aut.authentication.principal.username,11)}</a>
					    	 		</c:otherwise>
				    	 		</c:choose>
							</c:otherwise>
			    	 	</c:choose>
			    	 	欢迎回到一城一品！
			    	 	<a href="${_ctxPath}/order/order-myOrders.htm" class="go_uc">我的一品</a>
			    	 	<a href="javascript:logout();" class="topbar-logout">退出</a>
			    	 	</p>
					<%} %>
	    </c:if>
		<c:if test="${not empty session.SPRING_SECURITY_CONTEXT.authentication.principal}">
		   <p class="fn_left">您好&nbsp;
		   <c:choose>
				<c:when test="${not empty session.SPRING_SECURITY_CONTEXT.authentication.principal.operateName}">
						<a href="${_ctxPath}/user/user-userInfo.htm" class="w_info">${yipin:cutString(session.SPRING_SECURITY_CONTEXT.authentication.principal.operateName,11)}</a>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${session.SPRING_SECURITY_CONTEXT.authentication.principal.nickName!=null&&session.SPRING_SECURITY_CONTEXT.authentication.principal.nickName!=''}">
							<a href="${_ctxPath}/user/user-userInfo.htm" class="w_info">${yipin:cutString(session.SPRING_SECURITY_CONTEXT.authentication.principal.nickName,11)}</a>
						</c:when>
						<c:otherwise>
							<c:set var="username" value="${session.SPRING_SECURITY_CONTEXT.authentication.principal.username}"></c:set>
	       					<a href="${_ctxPath}/user/user-userInfo.htm" class="w_info">${yipin:cutString(username,11)}</a>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
			欢迎回到一城一品！
			<a href="${_ctxPath}/order/order-myOrders.htm" class="go_uc">我的一品</a>
	        <a href="javascript:logout();" class="topbar-logout">退出</a>
	        </p>
	    </c:if>
	    <%} %>
				</div>
				<span class="cs">客服热线：${_serviceTel }</span><a href="javascript:void(0);" onclick="goHelp()" >帮助中心</a>
			</div>
		</div>
		<!--顶部bar end-->
		<!--slogan start-->
		<div class="w_norm cf">
			<div class="logo">
				<a href="${_ctxPath}/"><img src="${_imagesPath}/navigation/logo.jpg" alt="" /></a>
			</div>
			<div class="slogan">
				<h3>开启健康生活</h3>
				www.yichengpin.com
			</div>
		</div>
		<!--slogan end-->
		<!--导航 start-->
		<div class="nav_wrap">
			<div class="w_norm cf">
				<div class="main_nav">
					<ul>
						<li><a href="${_ctxPath}">首页</a></li>
						<li class="J_nav"><a href="javascript:">原产地</a></li>
						<li class="J_nav"><a href="javascript:">商品分类</a></li>
					</ul>
					<!-- 下拉层 start-->
					<div class="s_nav_box">
						<div class="nav_box J_panel" style="width:588px;">
							<div class="panel cf">
								<ul>
									<c:forEach items="${regionList}" var="region">
										<li><a target="_blank" href="${_ctxPath }/listproduct-1-${region.propId}-0-${url_sort_100}-${url_dir_100000}-0-0.htm">${region.name}</a></li>
									</c:forEach>
								</ul>
							</div>
							<!--原产地-->
							<div class="nav_con_wp">
							<c:forEach items="${districtADList}" var="advertiesmentMap">
									<div class="nav_con">
									<c:forEach items="${advertiesmentMap}" var="adMap">
										<div class="link_item">
											<div class="nav_tit"><a target="_blank" href="${_ctxPath }/listproduct-1-${fn:split(adMap.key,'-')[0]}-0-${url_sort_100}-${url_dir_100000}-0-0.htm">${fn:split(adMap.key,'-')[1]}</a></div>
											<div class="nav_link">
												<c:forEach items="${adMap.value}" var="advertisement"  varStatus="status">
													<c:if test="${status.index == 15}">
														<a target="_blank" href="${_ctxPath }/listproduct-1-${fn:split(adMap.key,'-')[0]}-0-${url_sort_100}-${url_dir_100000}-0-0.htm">更多…</a>
													</c:if>
													<c:if test="${status.index < 15}">
														<a target="_blank" href="${advertisement.url}">${advertisement.advertisementName}</a>
													</c:if>									
												</c:forEach>
											</div>
										</div>
									</c:forEach>
									</div>
							</c:forEach>
							</div>
						</div>
						<!--分类 -->
						<div class="nav_box J_panel" style="width:1008px;">
							<div class="panel cf">
								<ul>
									<c:forEach items="${productList}" var="product">
											<li><a target="_blank" href="${_ctxPath }/listproduct-2-${product.propId}-0-${url_sort_100}-${url_dir_100000}-0-0.htm">${product.name}</a></li>
									</c:forEach>
								</ul>
							</div>
							<div class="nav_con_wp">
								<c:forEach items="${productADList}" var="advertiesmentMap">
									<div class="nav_con">
									<c:forEach items="${advertiesmentMap}" var="adMap">
										<div class="link_item">
											<div class="nav_tit"><a target="_blank" href="${_ctxPath }/listproduct-2-${fn:split(adMap.key,'-')[0]}-0-${url_sort_100}-${url_dir_100000}-0-0.htm">${fn:split(adMap.key,'-')[1]}</a></div>
											<div class="nav_link">
												<c:forEach items="${adMap.value}" var="advertisement"  varStatus="status">
													<c:if test="${status.index == 15}">
														<a target="_blank" href="${_ctxPath }/listproduct-2-${fn:split(adMap.key,'-')[0]}-0-${url_sort_100}-${url_dir_100000}-0-0.htm">更多…</a>
													</c:if>
													<c:if test="${status.index < 15}">
														<a target="_blank" href="${advertisement.url}">${advertisement.advertisementName}</a>
													</c:if>									
												</c:forEach>
											</div>
										</div>
									</c:forEach>
									</div>
							</c:forEach>
							</div>
						</div>
					</div>
					<!-- 下拉层 end-->
				</div>
				<a href="${_ctxPath}/suggest/myFind.htm" class="find">发现</a>
			</div>
		</div>
<form id="qq_login_form" action="${_ctxPath}/j_spring_security_check" style="display:none;"  method="post">
    	 <input type="text"  id="qq_name" name="j_username" >
		 <input type="text"  id="qq_password"  name="j_password" >
		 <input type="checkbox" id="remberMe" name="_spring_security_remember_me">
</form>
<form id="qq_logout_form" action="${_ctxPath}/j_spring_security_logout" style="display:none;"  method="post">
</form>
<c:if test="${_qqShow==1}">
<script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" data-appid="${_qqDataAppid}" data-redirecturi="${_qqDataRedirecturi}" charset="utf-8"></script>
</c:if>
<script type="text/javascript">
function logout(){
	 <c:if test="${_qqShow==1}">
	 QC.Login.signOut();
	 </c:if>
	 var true_from =$('#qq_logout_form');
	 true_from.submit();
}

</script>
