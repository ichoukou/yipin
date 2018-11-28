<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<html lang="zh-CN" class="ua-window">
<head>
  <meta charset="utf-8"/>
    <title>找回密码-输入用户名</title>
    <link rel="stylesheet" type="text/css" href="${_cssPath}/lib/global.css" media="all" />
    <!--page css-->
    <link rel="stylesheet" href="${_cssPath}/pages/findPsw.css" />
</head>
<body >
	<%@include file="/WEB-INF/pages/include/head.jsp"%>
	 <div class="content">
        <!--找回密码 start-->
        <div class="find_psw cf">
	        <h1>找回密码</h1>
	        	<div class="find_plan"><img src="${_imagesPath}/findpsw_1.png"></div>
	        	<div class="fp_form">
	            <form action="${_ctxPath}/user/user-passwordBackQueryMsg.htm" id ="getpassFrom" method="post">
	            	<div class="fp_bar cf">
	                    <label>邮箱：</label>
	                    <p class="relative">
	                        <input type="text" data-default="请输入邮箱" id="username" class="input-text input-default" name="username">
	                    	<span id="usernameTips">该邮箱不存在</span>
	                    </p>
	                </div>
	                <div class="fp_bar cf">
	                    <label>验证码：</label>
	                    <p class="relative">
	                        <input type="text" data-default="请输入验证码" id="j_captcha_1" name="jCaptchaResponse" class="input-text input-default">
	                        <span class="yzmImg"><img alt="" id="j_validateCodePass" src="${_ctxPath}/validationCode.htm"></span>
	                        <a class="yzmchange" href="javascript:nextValidateCodePass()">看不清<br>换一张</a>
	                    </p>
	                </div>
	                <div class="fp_bar cf" >
                    	<a class="submit" id="save" href="javascript:;"><span>提交</span></a>
               		</div>
	            <input type="hidden" id="_ctxPath" value="${_ctxPath}" >
	            </form>
	          </div>
        </div>
    </div>
   	<%@include file="/WEB-INF/pages/include/foot.jsp"%>
	<script src="${_jsPath}/pages/pwdchange.js"></script>
	<script type="text/javascript">
		$("#username").focus(function(){
			$(".relative").find("#usernameTips").html("");
		});
	</script>
</body>
</html>