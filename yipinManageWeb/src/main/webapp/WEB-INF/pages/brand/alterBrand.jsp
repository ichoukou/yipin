<!DOCTYPE html>
<%@page language="java" import="com.ytoxl.module.yipin.base.dataobject.Product" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<html lang="zh">
<head>
<meta charset="utf-8" />
<title>新增品牌</title>
	<link href="${_cssPath }/common.css" rel="stylesheet" />
	<link href="${_cssPath }/pages/addBrand.css" rel="stylesheet" />
	<link href="${_jsPath }/plugin/artdialog/skins/ytoxl.css" rel="stylesheet" />
	<script type="text/javascript" src="${_jsPath }/jquery/jquery.1.8.1.js"></script>
	<script src="${_jsPath }/plugin/artdialog/jquery.artDialog.min.js"></script>
	<script type="text/javascript" src="${_jsPath }/plugin/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${_jsPath}/plugin/uploadify/swfobject.js"></script>
	<script type="text/javascript" src="${_jsPath}/plugin/uploadify/jquery.uploadify.v2.1.4.min.js"></script>
	<script src="${_jsPath }/plugin/formvalidator/formValidatorRegex.js"></script>
	<script src="${_jsPath }/plugin/formvalidator/formValidator-4.1.3.js"></script>
</head>
	<script type="text/javascript">
		function getBrandPinYin(id){
			var name = $('#'+id).val();
			var enName = $('#addBrand-en-name').val();
			var zhName = $('#addBrand-zh-name').val();

			if(enName.length >0 && zhName.length>0){
				name = enName;
			}else if(enName.length == 0 && zhName.length>0){
				name = zhName;
			}else if(enName.length > 0 && zhName.length == 0){
				name = enName;
			}else{
				$('#brandNamePinYin').val("");
				return;
			}
			var url = '${_ctxPath}/admin/brand/brand-getBrandPinYinByName.htm';
			$.post(url,{"brand.firstChar":name},function(value){
				$('#brandNamePinYin').val(value.info);
			});
		}
	</script>
<body>
<!--start header-->
<jsp:include page="../include/header.jsp"></jsp:include> 
<!--end header-->
<!--start body-->
<div class="m-w980p m-mt10p">
		<form id="addBrand-form"  method="post" action="${_ctxPath}/admin/brand/brand-singleBrandSset.htm">
		<h1 class="add-title">品牌属性</h1>
		<table class="add-table">
			<tr>
				<td align="right" width="145"><span class="c-red">*</span>品牌名称：</td>
				<td colspan="2">
					<input type="text" id="addBrand-zh-name" class="addBrand-name" name="brand.name" value="${brand.name}" onblur="getBrandPinYin('addBrand-zh-name')"/>
					英文名：<input id="addBrand-en-name" type="text" class="addBrand-name" name="brand.englishName" value="${brand.englishName}"  onblur="getBrandPinYin('addBrand-en-name')"/>
				</td>
				<td width="385"><span id="addBrandnameTip"></span></td>
			</tr>
			<tr>
				<td align="right" width="145">品牌集中营关联字符：</td>
				<td colspan="2">
					<input id="brandNamePinYin" type="text" class="addBrand-name" name="brand.firstChar" value="${brand.firstChar}"/>
				</td>
				<td width="385"><span id="brandNamePinYinTip"></span></td>
			</tr>
			<tr>
				<td align="right">品牌特点：</td>
				<td width="225">
					<input type="text" class="add-text" name="brand.feature" value="${brand.feature}" id="brandTd">
				</td>
				<td colspan="2"><span id="brandTdTip"></span></td>
			</tr>
			<tr>
				<td align="right">品牌所属国家：</td>
				<td>
					<input type="text" class="add-text" name="brand.country"  value="${brand.country}" id="country"/>
				</td>
				<td colspan="2"><span id="countryTip"></span></td>
			</tr>
			<tr>
				<td align="right">品牌创始人：</td>
				<td>
					<input type="text" class="add-text" name="brand.founder"  value="${brand.founder}" id="originator"/>
				</td>
				<td colspan="2"><span id="originatorTip"></span></td>
			</tr>
			<tr>
				<td align="right">品牌创建时间：</td>
				<td>
				  <input class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy' })"  type="text" name="brand.foundationTime" value="<fmt:formatDate value='${brand.foundationTime}' pattern='yyyy' />" id="date">
				</td>
				<td colspan="2"><span id="dateTip"></span></td>
			</tr>
			<tr valign="top">
				<td align="right"><span class="c-red">*</span>品牌简介：</td>
				<td colspan="2">
					<div class="addBrand-edit">
						<textarea class="add-textarea"  id="describArea">${brand.description}</textarea>
					</div>
				</td>
				<td>
					<div id="describAreaTip"></div>
				</td>
			</tr>
			<tr valign="top">
				<td align="right"><span class="c-red">*</span>品牌形象图：</td>
				<td colspan="3">
					<div class="addBrand-Image">
						<div class="brandImg-default">
							<img src="${_filePath}${brand.brandImageUrl}" class="imgshow"  id="brandImg" width="800px" height="400"/>
						</div>
						<div class="upload-img">
							<input type="file" class="addBrand-btn" id="brandImgUpload"  />
						</div>
						<span class="c-red">为保证图片效果，请上传1900*800px的品牌形象图</span>
						<span id="brandImageTip"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<input type="hidden" id="brandImageUrl"  name="brand.brandImageUrl"  value="${brand.brandImageUrl}" />
					<input type="hidden" name="brand.description" id="describeInput" value="${brand.description}" />
					<input type="hidden" name="opert"  value="${opert}" />
					<input type="hidden" name="brand.brandId" value="${brand.brandId}">
					<input type="button" class="m-btn" id="addBrand-btn" value="保存所有信息" />
					<br/><br/><br/>
				</td>
			</tr>
		</table>
	</form>
</div>
<!--end body-->
<!--start footer-->
<jsp:include page="/WEB-INF/pages/include/foot.jsp"></jsp:include>
<!--end footer-->
<script src="${_jsPath }/pages/addBrand.js"></script>
</body>
</html>
