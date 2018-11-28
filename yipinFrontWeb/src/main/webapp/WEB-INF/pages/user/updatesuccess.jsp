<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<html lang="zh-CN" class="ua-window">
<head>
  <meta charset="utf-8"/>
    <title>找回密码-我的新龙-${_webSiteName }</title>
    <link rel="stylesheet" type="text/css" href="${_cssPath}/lib/global.css" media="all" />
    <!--page css-->
    <link rel="stylesheet" href="${_cssPath}/pages/findPsw.css" />
    <jsp:include page="/WEB-INF/pages/include/common.jsp"></jsp:include>
</head>
<body >
	<%@include file="/WEB-INF/pages/include/head.jsp"%>
	<div class="content">
    <!--找回密码 start-->
    <div class="find_psw cf">
        <h1>找回密码</h1>
        <div class="find_plan"><img src="${_imagesPath}/findpsw_4.png"></div>
        <div class="fp_check">
            <p>${findPasswordInfo}</p>
            <ul>
                <li class="grey">请等待3秒系统自动跳转首页</li>
                <li>如果3秒后系统未自动跳转，请点击<a href="${_domain }" class="blue"> 立即跳转 ></a></li>
            </ul>
            <input type="hidden" id="_domain" value="${_domain}" >
        </div>
    </div>
    <!--找回密码 end-->
</div>
    <%@include file="/WEB-INF/pages/include/foot.jsp"%>
	<script src="${_jsPath}/pages/pwdchange.js"></script>
   	<form id="true_form" action="${_ctxPath}/j_spring_security_check" style="display:none;"  method="post">
    	<input type="text"  id="tname" name="j_username" type="text" value="${username}">
    	<input name="j_password" id="tpassword"  type="password" value="${password}">
    </form>
</body>
<script type="text/javascript">
    var _domain=$("#_domain").val();
	function goToIndex(){
		window.location=_domain;
	}
	setInterval(goToIndex,3000);
</script>
</html>