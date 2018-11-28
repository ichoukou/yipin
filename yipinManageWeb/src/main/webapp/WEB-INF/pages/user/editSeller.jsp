<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>编辑商家</title>
	<link href="${_cssPath}/common.css" rel="stylesheet" />
	<link href="${_cssPath}/pages/addBrand.css" rel="stylesheet" />
	<link href="${_jsPath }/plugin/My97DatePicker/skin/WdatePicker.css" rel="stylesheet" />
</head>
<body >
<jsp:include page="../include/header.jsp"  flush="true" />
	<div class="body m-w980p">
			<h1 class="add-title">编辑商家</h1>
			<form action="${_ctxPath}/admin/user/user-saveSeller.htm" method="post" id="form1">
				<input type="hidden" name="userInfo.userInfoId" id="sellerId" value="${userInfo.userInfoId}" />
				<input type="hidden" name="userInfo.user.userId" id="sellerUserId" value="${userInfo.user.userId }" />
					<table border="0" class="add-table">
						<tr>
							<th width="100">基本信息</th>
							<th colspan="4">&nbsp;</th>
						</tr>
						<tr>
							<td align="right"><span class="c-red">*</span>登录名：</td>
							<td width="225">
								<c:if test="${userInfo.userInfoId !=null && userInfo.userInfoId != ''}">
									${userInfo.user.username}
								</c:if>
								<c:if test="${userInfo.userInfoId ==null || userInfo.userInfoId == ''}">
								<input id="business-name" type="text" name="userInfo.user.username"
									value="${userInfo.user.username}" class="add-text" onblur="test()"/>
								</c:if>
								
							</td>
							<td colspan="3"><span id="business-nameTip"></span></td>
						</tr>
						<tr>
							<td align="right"><span class="c-red">*</span>业务联系人：</td>
							<td><input id="linkman" type="text" name="userInfo.contactName"
								value="${userInfo.contactName}" class="add-text"/></td>
							<td colspan="3"><span id="linkmanTip"></span></td>
						</tr>
						<tr>
							<td align="right"><span class="c-red">*</span>手机号码：</td>
							<td><input id="tel-text" type="text" name="userInfo.mobile"
								value="${userInfo.mobile}" class="add-text" maxlength="11"/></td>
							<td colspan="3"><span id="tel-textTip"></span></td>
						</tr>
						<tr>
							<td align="right">固定电话：</td>
							<td><input id="dh" type="text" name="userInfo.user.tel"
								value="${userInfo.user.tel}" class="add-text"/></td>
							<td colspan="3"><span id="dhTip"></span></td>
						</tr>
						<tr>
							<td align="right"><span class="c-red">*</span>电子邮箱：</td>
							<td><input id="email" type="text" name="userInfo.user.email"
									value="${userInfo.user.email}" class="add-text"/></td>
							<td colspan="3"><span id="emailTip"></span></td>
						</tr>
						<tr>
							<td align="right">QQ：</td>
							<td><input type="text" name="userInfo.qq" value="${userInfo.qq}" class="add-text" id="QQ"/></td>
							<td colspan="3"><span id="QQTip"></span></td>
						</tr>
						<tr>
							<td align="right">传真：</td>
							<td><input type="text" name="userInfo.fax" value="${userInfo.fax}" class="add-text" id="fax"/></td>
							<td colspan="3"><span id="faxTip"></span></td>
						</tr>
						<tr>
							<th>公司信息</th>
							<th colspan="4">&nbsp;</th>
						</tr>
						<tr>
							<td align="right"><span class="c-red">*</span>公司名称：</td>
							<td><input type="text" name="userInfo.companyName"
						value="${userInfo.companyName}" class="add-text" id="company-name"/></td>
						<td colspan="3"><span id="company-nameTip"></span></td>
						</tr>
						<tr>
							<td align="right">公司类型：</td>
							<td>
								<input type="text" name="userInfo.companyType"
								value="${userInfo.companyType}" class="add-text" id="company-type"/>
							</td>
							<td colspan="3"><span id="company-typeTip"></span></td>
						</tr>
						<tr>
							<td align="right">供应商性质：</td>
							<td>
								<select name="userInfo.supplierType" class="m-sel" id="gys">
									<c:forEach items="${supplierTypes}" var="supplierType" varStatus="status">
											<option value="${supplierType}"
											<c:if test="${userInfo.supplierType==status.index }">
											selected = "selected"
											</c:if>
											><spring:message code="seller.supplierType.${status.index}"/></option>
									</c:forEach>
								</select>
							</td>
							<td colspan="3"><span id="gysTip"></span></td>
						</tr>
						<tr>
							<td align="right">成立日期：</td>
							<td><input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM',readOnly:true,})"
								value="<fmt:formatDate value='${userInfo.registerDate}' pattern='yyyy-MM'/>" class="Wdate" id="rq"/>
								<input type="hidden" name="userInfo.registerDate" id="registerDate"/>
							</td>
							<td colspan="3"><span id="rqTip"></span></td>
						</tr>
						<tr>
							<td align="right">注册资本：</td>
							<td><input type="text" name="userInfo.registerCapital"
								value="${userInfo.registerCapital}" class="add-text" id="regdit-zb" /></td>
								<td>万元</td>
							<td colspan="2"><span id="regdit-zbTip"></span></td>
						</tr>
						<tr>
							<td align="right">实收资本：</td>
							<td><input type="text" name="userInfo.paidUpCapital"
								value="${userInfo.paidUpCapital}" class="add-text" id="ss-zb"/></td>
							<td>万元</td>
							<td colspan="2"><span id="ss-zbTip"></span></td>
						</tr>
						<tr valign="top">
							<td align="right">经营范围：</td>
							<td colspan="4"><textarea class="edit-textarea" name="userInfo.businessScope"
							id="businessScope">${userInfo.businessScope}</textarea>
							<span class="c-red" id="businessScope_check"></span>
							</td>
						</tr>
						<tr>
							<td></td>
							<td colspan="4"><div id="businessScopeTip"></div></td>
<!-- 							<td align="center">源码：已输入<b class="c-green" id="businessScopeCount">0</b>/最多输入<b class="c-red">100</b></td> -->
						</tr>
						<tr valign="top">
							<td align="right"><span class="c-red">*</span>公司简介：</td>
							<td colspan="4"><textarea class="edit-textarea" name="userInfo.remark" id="remark">${userInfo.remark}</textarea>
								<span class="c-red" id="remark_check"></span>
							</td>
							
						</tr>
						<tr>
							<td></td>
							<td colspan="4"><div id="remarkTip"></div></td>
<!-- 							<td align="center">源码：已输入<b class="c-green" id="remarkCount">0</b>/最多输入<b class="c-red">200</b></td> -->
						</tr>
						<tr>
							<td align="right">公司网址：</td>
							<td><input type="text" name="userInfo.companyWebSite"
								value="${userInfo.companyWebSite}" class="add-text" id="url" /></td>
							<td colspan="3"><span id="urlTip"></span></td>
						</tr>
						<input type="hidden"  id="brandCityId" value="${userInfo.brandCityId}" />
<!-- 						<tr> -->
<!-- 							<td align="right" valign="top"><span class="c-red">*</span>选择城市：</td> -->
<!-- 							<td colspan="3" class="mc-sel"> -->
<!-- 								<select id="selectCity" class=""></select> -->
<!-- 							<p><span class="c-red">(切换城市后将会清空已选品牌!)</span></p> -->
<!-- 							</td> -->
<!-- 							<td colspan="3"><span id="urlTip"></span></td> -->
<!-- 						</tr> -->
						
						<tr>
							<td align="right"><span class="c-red">*</span>可售品牌：</td>
							<td colspan="3">
								<div class="sal-brand-t">
									<a href="javascript:void(0);">A</a>
									<a href="javascript:void(0);">B</a>
									<a href="javascript:void(0);">C</a>
									<a href="javascript:void(0);">D</a>
									<a href="javascript:void(0);">E</a>
									<a href="javascript:void(0);">F</a>
									<a href="javascript:void(0);">G</a>
									<a href="javascript:void(0);">H</a>
									<a href="javascript:void(0);">I</a>
									<a href="javascript:void(0);">J</a>
									<a href="javascript:void(0);">K</a>
									<a href="javascript:void(0);">L</a>
									<a href="javascript:void(0);">M</a>
									<a href="javascript:void(0);">N</a>
									<a href="javascript:void(0);">O</a>
									<a href="javascript:void(0);">P</a>
									<a href="javascript:void(0);">Q</a>
									<a href="javascript:void(0);">R</a>
									<a href="javascript:void(0);">S</a>
									<a href="javascript:void(0);">T</a>
									<a href="javascript:void(0);">U</a>
									<a href="javascript:void(0);">V</a>
									<a href="javascript:void(0);">W</a>
									<a href="javascript:void(0);">X</a>
									<a href="javascript:void(0);">Y</a>
									<a href="javascript:void(0);">Z</a>
									<a href="javascript:void(0);">0</a>
									<a href="javascript:void(0);">1</a>
									<a href="javascript:void(0);">2</a>
									<a href="javascript:void(0);">3</a>
									<a href="javascript:void(0);">4</a>
									<a href="javascript:void(0);">5</a>
									<a href="javascript:void(0);">6</a>
									<a href="javascript:void(0);">7</a>
									<a href="javascript:void(0);">8</a>
									<a href="javascript:void(0);">9</a>
								</div>
								<div class="br-list" style="display: none;">
									<input type="checkbox" name="userInfo.listBrandIds" />
								</div>
<%-- 								<c:forEach items="${brandMap }" var="map"> --%>
<%-- 								<div class="br-list" id="brand-${map.key }"> --%>
<%-- 									<c:forEach items="${map.value }" var="brand"> --%>
<%-- 									<input type="checkbox" id="brand-${brand.brandId }" name="userInfo.listBrandIds" value="${brand.brandId }"  --%>
<%-- 										<c:if test="${brand.isChecked }">checked</c:if>/> --%>
<%-- 									<label for="brand-${brand.brandId }">${brand.name }</label> --%>
<%-- 									</c:forEach> --%>
<!-- 								</div> -->
<%-- 								</c:forEach> --%>
								
								<div class="br-list" id="br-list-old">
									<s:iterator value="selectBrandMap" id="column">
										<input type="checkbox" id='<s:property value="key"/>' name="userInfo.listBrandIds" 
										 value='<s:property value="key"/>' checked /> 
										 <label for="brand-<s:property value="key"/>"><s:property value="value"/></label>
									</s:iterator>
								</div>
								
								
								
								
							</td>
							<td><span id="brandTip"></span></td>
						</tr>
						<tr>
							<td align="right">已勾选品牌：</td>
							<td colspan="3" id="checkedBrands">
								<b id="selectedBrandNames">${userInfo.brandNames }</b>
							</td>
							<td><span id=""></span></td>
						</tr>
						<tr>
							<td align="right">公司地址：</td>
							<td colspan="3" class="mc-sel">
								<select id="company_province" class="company linkagesel m-mr5p"></select>
		                    	<select id="company_city" class="company linkagesel m-mr5p"></select>
		                    	<select id="company_region" class="company linkagesel"></select>
		                    	<input type="hidden"  id="companyRegionCodes" value="${userInfo.companyRegionId}" name="userInfo.companyRegionId"/>
					            <input type="hidden"  id="company_area_prov" value='${userInfo.companyModel.provinceId}'/>
								<input type="hidden"  id="company_area_city" value='${userInfo.companyModel.cityId}'/>
								<input type="hidden"  id="company_area_region" value='${userInfo.companyModel.countyId}'/>
							</td>
							<td>
								<span class="region-tip"><span id="regionTips1" class='ming'></span></span>
							</td>
						</tr>
						<tr>
							<td></td>
							<td colspan="3">
								<input type="text" name="userInfo.companyAddress"	value="${userInfo.companyAddress}" class="addr-text" id="addr-info"/>
							</td>
							<td width="290"><span id="addrTip"></span></td>
						</tr>
						<tr>
							<td align="right"><span class="c-red">*</span>发货地址：</td>
							<td colspan="3">
								<div id="add-f" class="mc-sel">
								    <select id="shiper_province" class="shiper linkagesel m-mr5p"></select>
                    				<select id="shiper_city" class="shiper linkagesel m-mr5p"></select>
                    				<select id="shiper_region" class="shiper linkagesel"></select>
									<input type="hidden" name="userInfo.shiperRegionId" id="shiperRegionCodes" value="${userInfo.shiperRegionId }" />
									<input type="hidden"  id="shiper_area_prov" value='${userInfo.shiperModel.provinceId}'/>
									<input type="hidden"  id="shiper_area_city" value='${userInfo.shiperModel.cityId}'/>
									<input type="hidden"  id="shiper_area_region" value='${userInfo.shiperModel.countyId}'/>
								</div>
							</td>
							<td>
								 <span id="addrTip-f"></span>
							</td>
						</tr>
						<tr>
							<td></td>
							<td colspan="3"><input type="text" name="userInfo.shiperAddress" value="${userInfo.shiperAddress}"  class="addr-text" id="addr-info-f" /></td>
							<td><span id="addr-info-fTip"></span></td>
						</tr>
						<tr>
							<td align="right"><span class="c-red">*</span>退货地址：</td>
							<td colspan="3">
								<div id="add-t" class="mc-sel">
								<select id="receiver_province" class="receiver linkagesel m-mr5p"></select>
                    			<select id="receiver_city" class="receiver linkagesel m-mr5p"></select>
                    			<select id="receiver_region" class="receiver linkagesel"></select>
								<input type="hidden" name="userInfo.receiverRegionId" id="receiverRegionCodes" value="${userInfo.receiverRegionId}" />
								<input type="hidden"  id="receiver_area_prov" value='${userInfo.receiverModel.provinceId}'/>
								<input type="hidden"  id="receiver_area_city" value='${userInfo.receiverModel.cityId}'/>
								<input type="hidden"  id="receiver_area_region" value='${userInfo.receiverModel.countyId}'/>
								</div>
							</td>
							<td>
							   <span id="addrTip-t"></span>
							</td>
						</tr>
						<tr>
							<td></td>
							<td colspan="3"><input type="text" name="userInfo.receiverAddress"
									value="${userInfo.receiverAddress}" class="addr-text" id="addr-info-t" /></td>
							<td><span id="addr-info-tTip"></span></td>
						</tr>
						<tr>
							<td align="right">法人代表：</td>
							<td><input type="text" name="userInfo.companyCorporation"
								value="${userInfo.companyCorporation}" class="add-text" id="juridicalPperson" /></td>
							<td colspan="3"><span id="juridicalPpersonTip"></span></td>
						</tr>
						<tr>
							<td align="right">税务登记号：</td>
							<td><input type="text" name="userInfo.taxNo"
							value="${userInfo.taxNo}" class="add-text" id="registration"/></td>
							<td colspan="3"><span id="registrationTip"></span></td>
						</tr>
						<tr>
							<td align="right">企业法人营业执照注册号：</td>
							<td><input type="text"
								name="userInfo.companyCode" value="${userInfo.companyCode}" class="add-text" id="registrationNum" /></td>
							<td colspan="3"><span id="registrationNumTip"></span></td>
						</tr>
						<tr>
							<td align="right">企业编码：</td>
							<td><input type="text" name="userInfo.companyNo"
								value="${userInfo.companyNo}" class="add-text" id="companyNum" /></td>
							<td colspan="3"><span id="companyNumTip"></span></td>
						</tr>
						<tr>
							<td align="right"><span class="c-red">*</span>支付宝帐号：</td>
							<td><input type="text" name="userInfo.alipayNo"
						value="${userInfo.alipayNo}" class="add-text" id="alipay" /></td>
							<td align="right"><span class="c-red">*</span>注册支付宝姓名：</td>
							<td><input type="text" 
								name="userInfo.alipayName" value="${userInfo.alipayName}" class="add-text" id="alipayName"/></td>
							<td><span id="paynum"></span></td>
						</tr>
						<tr>
							<td align="right"><span class="c-red">*</span>开户银行：</td>
							<td><input type="text" name="userInfo.bankName"
								value="${userInfo.bankName}" class="add-text" id="bank" /></td>
							<td align="right"><span class="c-red">*</span>银行帐号：</td>
							<td><input type="text"
								name="userInfo.bankAccount" value="${userInfo.bankAccount}" class="add-text" id="bankcardNum"/></td>
							<td></td>
						</tr>
						<tr valign="top">
							<td align="right">营业执照：</td>
							<td colspan="3">
								<input type="file" id="imgUpload" name="file" /> 
								<input type="hidden" id="uploadImageValueId" name="userInfo.licenseImageUrl" value="${userInfo.licenseImageUrl}" />
								
								<div class="license">
<%-- 									<img src="<yp:thumbImage originalPath='${userInfo.licenseImageUrl}' imageType='t24'></yp:thumbImage>" /> --%>
									<c:if test="${not empty userInfo.licenseImageUrl}">
										<img src="${_filePath}${userInfo.licenseImageUrl}" width="300px" height="200"/>
									</c:if>
								</div>
							</td>
							<td><span id="uploadImageValueIdTip"></span></td>
						</tr>
						<tr>
							<td colspan="5" align="center">
								<input type="button" class="m-btn" value="保存所有信息" id="submitForm"/>
								<input type="button" class="m-btn" value="返回" id="backList"/>
							</td>
						</tr>
					</table>
			</form>
	</div>
<%-- 	 <jsp:include page="/WEB-INF/pages/include/foot.jsp"></jsp:include> --%>
	<script type="text/javascript" src="${_jsPath }/jquery/jquery.1.8.1.js"></script>
	<script type="text/javascript" src="${_jsPath }/plugin/formvalidator/formValidator-4.1.3.js" language="javascript"></script>
	<script type="text/javascript" src="${_jsPath }/plugin/formvalidator/formValidatorRegex.js" language="javascript"></script>
	<script type="text/javascript" src="${_jsPath }/plugin/select/linkage_sel.js"></script>
	<script type="text/javascript" src="${_jsPath }/plugin/uploadify/swfobject.js"></script>
	<script type="text/javascript" src="${_jsPath }/plugin/uploadify/jquery.uploadify.v2.1.4.min.js"></script>
	<script type="text/javascript" src="${_jsPath }/plugin/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="${_jsPath }/plugin/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${_jsPath }/pages/seller.js"></script>
	<script type="text/javascript" src="${_jsPath }/pages/editSeller.js"></script>
	<script type="text/javascript">
    var _ctxPath = '${_ctxPath}',
    	_filePath = '${_filePath}',
    	_fileThumbPath = '${_fileThumbPath }',
    	_jsPath = '${_jsPath }';
	</script>
</body>
</html>