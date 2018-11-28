<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>新增抢购专区</title>
<link href="${_cssPath }/common.css" rel="stylesheet" />
<link href="${_cssPath }/pages/bannerManage.css" rel="stylesheet" />
<link href="${_cssPath }/pages/bannerManage.css" rel="stylesheet" />
<script src="${_jsPath }/jquery/jquery.1.8.1.js"></script>
<script src="${_jsPath }/plugin/artdialog/jquery.artDialog.min.js"></script>
<script src="${_jsPath }/plugin/formvalidator/formValidatorRegex.js"></script>
<script src="${_jsPath }/plugin/formvalidator/formValidator-4.1.3.js"></script>
<script src="${_jsPath }/plugin/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<jsp:include page="../include/header.jsp"></jsp:include> 
	<div class="body m-w980p">
		<!--start 商家管理table-->
		<div class="m-mt10p addsanpUp">
			<form id="addSpecialZoneFrm">
				<div>
					<label for=""><i class="red">*</i>专区类型：</label>  ${zone.typeNameString}<input name="zone.zoneType" value="${zone.zoneType}" type="hidden"/>
				</div>
				<div class="rel">
					<label for=""><i class="red">*</i>专区名称：</label> <input id="zonename" name="zone.name" value="${zone.name}" class="txt-input" /><span id="zonenameTip"></span>
				</div>
				<div class="rel">
					<label for=""><i class="red">*</i>专区排数：</label>  <input id="lineno" name="zone.lineNo" value="${zone.lineNo}" class="txt-input" onkeyup='javascript:replaceLineText(this);' /><span id="linenoTip"></span>
				</div>
				<div>
					<label for=""><i class="red">*</i>可销售品牌：</label> 
					<a href="javascript:void(0);" onclick="queryProduct('A')">A</a> 
					<a href="javascript:void(0);" onclick="queryProduct('B')">B</a> 
					<a href="javascript:void(0);" onclick="queryProduct('C')">C</a> 
					<a href="javascript:void(0);" onclick="queryProduct('D')">D</a> 
					<a href="javascript:void(0);" onclick="queryProduct('E')">E</a> 
					<a href="javascript:void(0);" onclick="queryProduct('F')">F</a> 
					<a href="javascript:void(0);" onclick="queryProduct('G')">G</a> 
					<a href="javascript:void(0);" onclick="queryProduct('H')">H</a> 
					<a href="javascript:void(0);" onclick="queryProduct('I')">I</a> 
					<a href="javascript:void(0);" onclick="queryProduct('J')">J</a> 
					<a href="javascript:void(0);" onclick="queryProduct('K')">K</a> 
					<a href="javascript:void(0);" onclick="queryProduct('L')">L</a> 
					<a href="javascript:void(0);" onclick="queryProduct('M')">M</a> 
					<a href="javascript:void(0);" onclick="queryProduct('A')">N</a> 
					<a href="javascript:void(0);" onclick="queryProduct('O')">O</a> 
					<a href="javascript:void(0);" onclick="queryProduct('P')">P</a>
					<a href="javascript:void(0);" onclick="queryProduct('Q')">Q</a> 
					<a href="javascript:void(0);" onclick="queryProduct('R')">R</a> 
					<a href="javascript:void(0);" onclick="queryProduct('S')">S</a> 
					<a href="javascript:void(0);" onclick="queryProduct('I')">T</a> 
					<a href="javascript:void(0);" onclick="queryProduct('U')">U</a> 
					<a href="javascript:void(0);" onclick="queryProduct('V')">V</a> 
					<a href="javascript:void(0);" onclick="queryProduct('W')">W</a> 
					<a href="javascript:void(0);" onclick="queryProduct('X')">X</a> 
					<a href="javascript:void(0);" onclick="queryProduct('Y')">Y</a> 
					<a href="javascript:void(0);" onclick="queryProduct('Z')">Z</a>
				</div>
				<div class="cf seller" id="prolist"></div>
				<div class="qz_th">
					<label for=""><b>商品权重：(1-10)</b></label>
				</div>
				<div class="rel">
					<label for="">批量设置抢购时间：</label> 
					<input class="txt-input Wdate" id="startTime" /> 
					<span class="m5">至</span>
					<input class="txt-input Wdate" id="endTime" /> 
					<span id="dateTip"></span>
					<input type="button" id="selectTime" value="确认" class="m-btn"> 
				</div>
				<ul class="cf qzBox" id="qzBox">

				</ul>
				<div>
					<label for=""></label> 
						<input type="button" id="submitForm" value="保存" class="m-btn"> 
						<input type="button" id="cancelForm" value="取消" class="m-btn">
						<input type="hidden" id="zoneId" name="zone.zoneId" value="${zone.zoneId}"/>
						<input type="hidden" id="rank" name="zone.rank" value="${zone.rank}"/>
						<input type="hidden" id="status" name="zone.status" value="${zone.status}"/>
						<input type="hidden" id="firstChar" name="zone.brandFirstChar" value="${zone.brandFirstChar}"/>
				</div>
				<c:if test="${zone.zoneSaleList!=null}">
					<c:forEach items="${zone.zoneSaleList}" var="zoneSale">
						<input type="hidden" value="${zoneSale.productSkuId}" class="pid"/>
						<input type="hidden" value="${zoneSale.zoneSaleId}" name="zsId"/>
					</c:forEach>
				</c:if>
				</form>
		</div>
	</div>
	<!--end 商家管理table-->
	<jsp:include page="/WEB-INF/pages/include/foot.jsp"></jsp:include>
	<script type="text/javascript" src="${_jsPath }/pages/addsnapUp.js"></script>
</body>
</html>