﻿<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.ytoxl.module.yipin.base.dataobject.Prop"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<html>
<head>
<meta charset="utf-8" />
<title>所在地管理信息</title>
<link href="${_cssPath }/common.css" rel="stylesheet" />
<link href="${_cssPath }/pages/arrangement.css" rel="stylesheet" />
<script type="text/javascript" src="${_jsPath }/jquery/jquery.1.8.1.js"></script>
<script type="text/javascript"	src="${_jsPath }/plugin/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"	src="${_jsPath }/plugin/formvalidator/formValidatorRegex.js"></script>
<script type="text/javascript"	src="${_jsPath }/plugin/formvalidator/formValidator-4.1.3.js"></script>
<body>
	<!--start header-->
	<jsp:include page="../include/header.jsp"></jsp:include>
	<!--end header-->
	<!--start body-->
	<div class="m-w980p">
		<div id="addRegionDiv" class="m-hide">
		<form id="addRegionForm" name="addRegionForm" action="${_ctxPath}/admin/prop/prop-loadRegion.htm">
		   <div class="szd_edit">
		  	<c:if test="${prop.level == prop.level_region}">
				<p><span>区域</span><input type="text" name="prop.name" id="propName"/></p>
			</c:if>
			<c:if test="${prop.level == prop.level_province}">
				<p><span>所在大区</span>${regionName}</p>
			</c:if>
			<c:if test="${prop.level == prop.level_city}">
				<p><span>所在大区</span>${regionName}</p>
				<p><span>所在省份</span>${provinceName}</p>
			</c:if>
			<c:if test="${prop.level == prop.level_province}">
				<p><span>省份</span><input type="text" name="prop.name" id="propName"/>	</p>
			</c:if>
			<c:if test="${prop.level == prop.level_city}">
				<p><span>城市</span><input type="text" name="prop.name" id="propName"/></p>
			</c:if>
			<p><span>排序</span><input type="text" name="prop.rank" id="propRank" 
						onkeyup="this.value=this.value.replace(/\D/g,'')" 
	                    onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
	        </p>
	       </div>
			  <input type="hidden" name="prop.level" value="${prop.level}" id="propLevel"/>
			  <input type="hidden" name="prop.propId" id="propId"/>
			  <input type="hidden" name="prop.code" value="${prop.parentCode}" id="propCode">
			  <input type="hidden" name="prop.parentCode" value="${prop.parentCode}" id="propParentCode">
			  <input type="hidden" name="prop.parentId" value="${prop.parentId}" id="propParentId">
			  <input type="hidden" name="provinceName" value="${provinceName}"/>
			  <input type="hidden" name="regionName" value="${regionName}"/>
			  <input type="hidden" name="prop.status" id="propStatus">
			  <input type="hidden" id="isCanOperate" value="${isCanOperate}">
			<div class="szd_btn">
			<input type="submit" value="提交" class="m-btn"/>
			<input id="cancelButton" type="reset" value="取消" class="m-btn"/>
			</div>
		</form>
		</div>
		<div class="szd_wrap" id="szdWrap">
			<div class="szd_panel cf">
				<c:if test="${not empty propPage.result}">
		            <div class="m-fr curr-num">
		              <label >当前显示：</label>
		              <yp:commPageSize page="${propPage}" beanName="propPage"></yp:commPageSize>
		            </div>
	           	</c:if>
				<input type="button" value="新增" onclick="addRegion()" class="m-btn"/>
			</div>
			<table width="100%" class="tab-control">
				<thead>
	                   <tr>
	                    <c:if test="${prop.level == prop.level_region}">
							<th width="15%">区域</th>	
						</c:if>
						<c:if test="${prop.level == prop.level_province}">
							<th width="15%">省份</th>		
						</c:if>
						<c:if test="${prop.level == prop.level_city}">
							<th width="15%">城市</th>		
						</c:if>
						<%-- <c:if test="${prop.level == prop.level_area}">
							<th width="15%">区县</th>		
						</c:if> --%>
	                       <th width="15%">顺序</th>
	                       <th width="15%">状态</th>
	                       <th width="15%">操作</th>
	                   </tr>
	               </thead>
	               <tbody>
	               	<c:forEach items="${propList}" var="propInfo"  varStatus="status">
	               		<tr class="list-tr" >
							<td >
								<label>${propInfo.name}</label>
							</td>
							<td >
								<label>${propInfo.rank}</label>
							</td>
							<td >
								<c:if test="${propInfo.status == propInfo.statusNormal}">
									正常
								</c:if>
								<c:if test="${propInfo.status == propInfo.statusInvalid}">
									禁用
								</c:if>
							</td>
							<td>
								<form id="infoForm${status.index}" action="${_ctxPath}/admin/prop/prop-loadRegion.htm">
								<input type="hidden" value="${propInfo.propId}" id="propId${status.index}">
								<input type="hidden" name="prop.name" value="${propInfo.name}" id="propName${status.index}">
								<input type="hidden" value="${propInfo.rank}" id="propRank${status.index}">
								<input type="hidden" value="${propInfo.status}" id="propStatus${status.index}">
								<input type="hidden" value="${propInfo.parentId}" name="prop.parentId"  id="propParentId${status.index}">
								<input type="hidden" name="provinceName" value="${provinceName}"/>
				  				<input type="hidden" name="regionName" value="${regionName}"/>
								<input type="hidden" name="prop.level" value="${propInfo.level}" id="propLevel${status.index}">
								<input type="hidden" name="prop.code" value="${propInfo.code}" id="propCode${status.index}">
								<input type="hidden" name="prop.parentCode" value="${propInfo.code}" id="propParentCode${status.index}">
								<input type="hidden" name="isCanOperate" value="${isCanOperate}" id="isCanOperate${status.index}"/>
								<c:if test="${propInfo.level != propInfo.level_city}">
									<a class="c-blue view" href="javascript:showChilds(${status.index},(${propInfo.level}+1));">查看子集</a>
								</c:if>
								<c:if test="${isCanOperate != 2}">
									<a class="c-blue view" href="javascript:editRegion(${status.index});">编辑${isCanOperate}</a>
								</c:if>
								<c:if test="${propInfo.status == propInfo.statusNormal && isCanOperate != 2}">
									<a class="c-blue view" href="javascript:updateStatus(${status.index},${propInfo.statusInvalid},'禁用？');">禁用</a>
								</c:if>
								<c:if test="${propInfo.status == propInfo.statusInvalid && isCanOperate != 2}">
									<a class="c-blue view" href="javascript:updateStatus(${status.index},${propInfo.statusNormal},'激活？');">激活</a>
								</c:if>
								</form>
							</td>
						</tr>
	               	</c:forEach>
	               </tbody>
	         </table>
         <div class="pagination pagination-right">
			            <c:if test="${not empty propPage.result}">
			            <yp:commPage page="${propPage}" beanName="propPage"></yp:commPage>
			            </c:if>
			     	</div>
         </div>
	</div>
	<!--start footer-->
	<jsp:include page="/WEB-INF/pages/include/foot.jsp"></jsp:include>
	<!--end footer-->
	<script type="text/javascript">
		// 点击提交按钮
		$("#addRegionForm").submit(function(){
			 if($("#propName").val() == ''){
				 alert("请输入区域名");
				 return false;
			 }
			 if($("#propId").val() != ''){
				document.addRegionForm.action ="${_ctxPath}/admin/prop/prop-editProp.htm";
			 }else{
				 document.addRegionForm.action ="${_ctxPath}/admin/prop/prop-addProp.htm";
			 }
		 });
		// 点击取消按钮
		$("#cancelButton").click(function(){
			$("#addRegionDiv").hide();
			$('#szdWrap').show();
		});
		// 编辑大区
		function editRegion(index){
			$("#addRegionDiv").show();
			$("#propParentId").val($("#propParentId"+index).val());
			$("#propId").val($("#propId"+index).val());
			$("#propName").val($("#propName"+index).val());
			$("#propRank").val($("#propRank"+index).val());
			$('#szdWrap').hide();
		};
		// 新增大区
		function addRegion(){
			if($("#isCanOperate").val() != 2){
				var propLevel = $("#propLevel").val();
				var propCode = $("#propCode").val();
				$("#addRegionForm")[0].reset();
				$("#propLevel").val(propLevel);
				$("#propCode").val(propCode);
				$("#addRegionDiv").show();
				$('#szdWrap').hide();
			}else{
				alert("请先激活上一级再进行此类操作！");
			}
		};
		// 查看子集
		function showChilds(index,level){
			$("#propLevel"+index).val(level);
			$("#propParentId"+index).val($("#propId"+index).val());
			if($("#isCanOperate"+index).val() == 2 || $("#propStatus"+index).val() == 2){
				$("#isCanOperate"+index).val(2); 
			}
			$("#infoForm"+index).submit();
		};
		// 激活或禁用区域
		function updateStatus(index,status,operateName){
			$.dialog({
	            title: false,
	            lock: true,
	            content: "请确认是否"+operateName,
	            okValue: "确认",
	            ok: function(){ 
	            	var propLevel = $("#propLevel").val();
	    			var propParentCode = $("#propParentCode").val();
	            	$("#addRegionForm")[0].reset();
	            	$("#propLevel").val(propLevel);
	    			$("#propCode").val($("#propCode"+index).val());
	    			$("#propParentCode").val(propParentCode);
	            	$("#propParentId").val($("#propParentId").val());
	    			$("#propId").val($("#propId"+index).val());
	    			$("#propName").val($("#propName"+index).val());
	    			$("#propRank").val($("#propRank"+index).val());
	    			$("#propStatus").val(status);
	    			$("#addRegionForm").submit();
	            },
				cancelValue: "取消",
	            cancel: true
		    });
		}
	</script>
</body>
</html>