<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<html lang="zh-CN" class="ua-window">
<head>
  <meta charset="utf-8"/>
    <title>找回密码-验证身份</title>
    <link rel="stylesheet" type="text/css" href="${_cssPath}/lib/global.css" media="all" />
    <!--page css-->
     <link rel="stylesheet" href="${_cssPath}/pages/findPsw.css" />
     <jsp:include page="/WEB-INF/pages/include/common.jsp"></jsp:include>
</head>
<body >
	<%@include file="/WEB-INF/pages/include/head.jsp"%>
	<div class="find_psw cf">
       <h1>找回密码</h1>
        <div class="find_plan"><img src="${_imagesPath}/findpsw_2.png"></div>
        <div class="fp_check">
            <p>找回密码邮件已发送到(${email})邮箱</p>
            <ul>
                <li>请查收后根据邮件内容操作</li>
                <li class="red">请在24小时内通过邮件中的连接重设密码。</li>
                <li>注：如果您没有收到“找回密码”邮件，建议去垃圾邮箱中找找看，或者<a href="${_ctxPath}/user/user-findPassWord.htm" class="blue"> 点此重新找回密码 ></a></li>
            </ul>
        </div>
    </div>
    <%@include file="/WEB-INF/pages/include/foot.jsp"%>
    <script src="${_jsPath}/pages/pwdchange.js"></script>
</body>
</html>