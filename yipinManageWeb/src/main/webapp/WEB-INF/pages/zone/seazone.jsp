<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>查看</title>
    <link href="${_cssPath }/common.css" rel="stylesheet" />
	<!--当前页面css-->
	<link href="${_cssPath }/pages/bannerManage.css" rel="stylesheet" />
	<script src="${_jsPath }/jquery/jquery.1.8.1.js"></script>
	<script src="${_jsPath }/plugin/artdialog/jquery.artDialog.min.js"></script>
	<script src="${_jsPath }/plugin/formvalidator/formValidatorRegex.js"></script>
	<script src="${_jsPath }/plugin/formvalidator/formValidator-4.1.3.js"></script>
</head>
<body>
<jsp:include page="../include/header.jsp"></jsp:include> 

 <div class="body m-w980p">
         <!--start 商家管理table-->
        <div class="m-mt10p addsanpUp">
			<form id="seaZoneFrm">
				<div>
					<label for=""><i class="red">*</i>专区类型：</label>
					${zone.zoneType}
				</div>
				<div>
					<label for=""><i class="red">*</i>专区名称：</label>
					${zone.typeNameString}
				</div>
				<div>
					<label for=""><i class="red">*</i>排序：</label>
					${zone.rank}
				</div>
				<div>
					<label for=""><i class="red">*</i>专区排数：</label>
					${zone.lineNo}行
				</div>
				<div id="dvstate">
					<label for=""><i class="red">*</i>状态：</label>
					${zone.stateNameString}
				</div>
			
					
				<div>
					<label for=""></label>
					<input type="button" id="cancelForm" value="返回" class="m-btn"/>
					<input type="hidden" id="zoneType" value="${zone.zoneType}" />
					<input type="hidden" id="zoneId" name="zone.zoneId" value="${zone.zoneId}"/>
					<input type="hidden" id="rank" name="zone.rank" value="${zone.rank}"/>
					<input type="hidden" id="status" name="zone.status" value="${zone.status}"/>
					<input type="hidden" id="firstChar" name="zone.brandFirstChar" value="${zone.brandFirstChar}"/>
				</div>
				</form>
			</div>
        </div>
       <!--end 商家管理table-->
	<jsp:include page="/WEB-INF/pages/include/foot.jsp"></jsp:include>
	<script src="${_jsPath }/pages/showsnapUp.js"></script>
	
</body>
</html>