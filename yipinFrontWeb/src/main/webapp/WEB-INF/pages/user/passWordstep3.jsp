<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<html lang="zh-CN" class="ua-window">
<head>
  <meta charset="utf-8"/>
    <title>找回密码-重置密码</title>
    <link rel="stylesheet" type="text/css" href="${_cssPath}/lib/global.css" media="all" />
    <!--page css-->
    <link rel="stylesheet" href="${_cssPath}/pages/findPsw.css" />
    <jsp:include page="/WEB-INF/pages/include/common.jsp"></jsp:include>
</head>
<body >
	<%@include file="/WEB-INF/pages/include/head.jsp"%>
 	<div class="find_psw cf">
         <h1>找回密码</h1>
        <div class="find_plan"><img src="${_imagesPath}/findpsw_3.png"></div>
        <div class="fp_form">
            <form action="${_ctxPath}/user/user-updatePasswordByMail.htm" id ="getpassFrom"  method="post">
            	<div class="fp_bar cf">
                    <label>新密码：</label>
                    <p class="relative">
                        <input type="password" class="input-text input-default" name="password1" id="newPsw">
                    	<span id="newPswTips"></span>
                    </p>
                </div>
                <div class="fp_bar cf">
                    <label>确认新密码：</label>
                    <p class="relative">
                        <input type="password" class="input-text input-default" name="password" id="password">
                    	<span id="passwordTips"></span>
                    </p>
                </div>
                <div class="fp_bar cf" >
                    <a class="sub_reset" style="cursor:pointer;" id="savePwd"><span>确定</span></a>
                </div>
            <input type="hidden" id="_ctxPath" value="${_ctxPath}" >
            <%-- 
            <input type="hidden" name="userId" value="${userId}">
             --%>
            <input type="hidden" name="username" value="${username}"> 
            <input type="hidden" name="active" value="${active}"> 
            <input type="hidden" name="sendTime" value="${sendTime}"> 
        </form>    
        </div>
    </div>
    <%@include file="/WEB-INF/pages/include/foot.jsp"%>
	<script src="${_jsPath}/pages/pwdchange.js"></script>
</body>
</html>