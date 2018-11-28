<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/taglibs.jsp"%><html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>查询公告</title>
    <link href="${_cssPath}/common.css" rel="stylesheet" />
    <script src="${_jsPath }/jquery/jquery.1.8.1.js"  language="javascript"></script>
</head>
<body>
	<!--start header-->
	<jsp:include page="../include/header.jsp" flush="true" />
	<!--end header-->
	<!--start body-->
	<div class="body m-w980p">
	  <jsp:include page="../include/pageManageMenu.jsp"  flush="true" />
	  <div class="title" style='border-bottom: 1px solid #CCCCCC;  margin-bottom: 10px; padding-bottom: 5px; padding-top: 10px;'><h3>查询公告</h3></div>
	  <form id="form1">
		  <table class="tab-a">
		  	<tr>
		  		<td width="50"><span class="c-red">*</span>标题：</td>
		  		<td colspan="2"  width="420">
		  			<span>${fn:escapeXml(notice.title)}</span>
		  		</td>
		  		<td><span id="titleTip"></span></td>
		  	</tr>
		  	<tr><td><br/></td></tr>
		  	
<!-- 		  	<tr> -->
<!-- 		  		<td><span class="c-red">*</span>类型：</td> -->
<!-- 		  		<td width="220"> -->
<!-- 		  			<select class="m-sel" id="type" name="notice.type"> -->
<%-- 		  				<option value="notice.type"><spring:message code="notice.type.${notice.type }"/></option> --%>
<!-- 		  			</select> -->
<!-- 		  		</td> -->
<!-- 		  		<td colspan="2"><span id="typeTip"></span></td> -->
<!-- 		  	</tr> -->
<!-- 		  	<tr> -->
<!-- 		  		<td><span class="c-red">*</span>来源：</td> -->
<%-- 		  		<td><span>${fn:escapeXml(notice.source)}</span></td> --%>
<!-- 		  		<td colspan="2"><span id="sourceTip"></span></td> -->
<!-- 		  	</tr> -->

		  	<tr valign="top">
		  		<td><span class="c-red">*</span>内容：</td>
		  		<td colspan="3">
		  			<!--此处为文本编辑器 start-->
		  			<span>${notice.content }</span>
		  			<!--此处为文本编辑器 end-->
		  		</td>
		  	</tr>
		  	<tr><td><br/></td></tr>
		  	<tr>
	  		<td colspan="3">
	  			<input type="button" class="m-btn" value="取消" onclick="turnSearch();"/>
	  		</td>
	  		</tr>
		  </table>
	  </form>
	</div>
	<!--start footer-->
	<jsp:include page="/WEB-INF/pages/include/foot.jsp"></jsp:include>
	<!--end footer-->
</body>
<!--end body-->
<script type="text/javascript">
	//跳转查询页面
	function turnSearch(){
		window.location.href="${_ctxPath}/admin/notice/notice-searchNotice.htm";
	}
</script>
</html>