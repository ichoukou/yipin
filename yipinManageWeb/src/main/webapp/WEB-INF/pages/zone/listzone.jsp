<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>专区管理</title>
<link href="${_cssPath }/common.css" rel="stylesheet" />
<link href="${_cssPath }/pages/bannerManage.css" rel="stylesheet" />
<script src="${_jsPath }/jquery/jquery.1.8.1.js"></script>
<script type="text/javascript">
    
	// 运营专区
	var addDetailZone = function() {
		window.location.href = "${_ctxPath}/admin/zone/zone-showDetailZone.htm";
	};
	// 预售专区
	var addSaleZone = function() {
		window.location.href = "${_ctxPath}/admin/zone/zone-showSaleZone.htm";
	};
	// 抢购专区
	var addSpecialZone = function() {
		window.location.href = "${_ctxPath}/admin/zone/zone-showSpecialZone.htm";
	};

</script>
</head>
<body>
	<jsp:include page="../include/header.jsp"></jsp:include> 
	<div class="body m-w980p">
		<form id="listZoneFrm" style="margin-top:10px;">
			<div>
				<input type="button" class="m-btn" value="新增专区" onclick="addDetailZone()" /> 
				<input type="button" class="m-btn" value="新增预售" onclick="addSaleZone()" /> 
				<input type="button" class="m-btn" value="新增抢购" onclick="addSpecialZone()" />
			</div>
		</form>
		<!--start 商家管理table-->
		<div class="m-mt10p business-management">
			<table class="tab-control" id="tab">
				<thead>
					<tr>
						<th width="40%">专区名称</th>
						<th width="10%">排数(1-4)</th>
						<th width="15%">专区类型</th>
						<th width="10%">排序(1-10)</th>
						<th width="10%">状态</th>
						<th width="15%">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${zoneList}" var="zone">
						<tr>
							<td>${zone.name}</td>
							<td><span class="J_num">${zone.lineNo}</span><input name="zone.lineNo" data-item="${zone.zoneId}" type="text" class="txt-input J_inputs"/><a href="javascript:" class="J_edits">编辑</a></td>
							<td>${zone.typeNameString}<input name="zone.zoneType" type="hidden" value="${zone.zoneType}"/></td>
							<td><span class="J_num">${zone.rank}</span><input name="zone.rank" data-item="${zone.zoneId }" type="text" class="txt-input J_input"/><a href="javascript:" class="J_edit">编辑</a></td>
							<td>${zone.stateNameString}<input name="zone.status" type="hidden" value="${zone.status}"/></td>
							<td>
								<c:if test="${zone.status == 2}">
								   <a href="javascript:" onclick="editZone('${zone.zoneId}');" class="m-5">编辑</a>
								   <a href="javascript:" onclick="showZone('${zone.zoneId}');" class="m-5">查看</a>
								   <a href="javascript:" onclick="releaseZone('${zone.zoneId}');" class="m-5">发布</a>
								</c:if>
								<c:if test="${zone.status == 0}">
									<a href="javascript:" onclick="editZone('${zone.zoneId}');" class="m-5">编辑</a>
									<a href="javascript:" onclick="showZone('${zone.zoneId}');" class="m-5">查看</a>
									<a href="javascript:" onclick="releaseZone('${zone.zoneId}');" class="m-5">发布</a>
								</c:if>
								<c:if test="${zone.status == 1}">
									<a href="javascript:" class="cgray">编辑</a>
									<a href="javascript:" onclick="showZone('${zone.zoneId}');" class="m-5">查看</a>
									<a href="javascript:" onclick="offZone('${zone.zoneId}');" class="m-5">下架</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
<!--end 商家管理table-->

<jsp:include page="/WEB-INF/pages/include/foot.jsp"></jsp:include>
<script src="${_jsPath }/pages/prefectureManage.js"></script>
</body>
</html>