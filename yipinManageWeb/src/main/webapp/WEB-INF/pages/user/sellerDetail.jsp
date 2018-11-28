<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>商家详细信息</title>
<link href="${_cssPath}/common.css" rel="stylesheet" />
<link href="${_cssPath}/pages/addBusiness.css" rel="stylesheet" />
<script type="text/javascript" src="${_jsPath }/jquery/jquery.1.8.1.js"></script>
<script type="text/javascript">
	var returnButton=function(){
		window.location.href="${_ctxPath}/admin/user/user-sellerManage.htm";
	}
</script>
</head>
<body>
<jsp:include page="../include/header.jsp"  flush="true" />
<div class="body m-w980p">
	<div class="addBusiness-border">
		<h1 class="addBusiness-t">基本信息</h1>
		<form id="form1">
		<table border="0" class="addBusiness-table">
			<tr>
				<td align="right">*登录名：</td>
				<td colspan="2"><span>${escape:escapeHtml(userInfo.user.username)}</span></td>
				<td colspan="2" width="340"></td>
			</tr>
			<tr>
				<td align="right">*业务联系人：</td>
				<td colspan="4"><span>${escape:escapeHtml(userInfo.contactName)}</span></td>
			</tr>
			<tr>
				<td align="right">*手机号码：</td>
				<td colspan="4"><span>${escape:escapeHtml(userInfo.mobile)}</span></td>
			</tr>
			<tr>
				<td align="right">固定电话：</td>
				<td colspan="4"><span>${escape:escapeHtml(userInfo.user.tel)}</span></td>
			</tr>
			<tr>
				<td align="right">*电子邮箱：</td>
				<td colspan="4"><span>${escape:escapeHtml(userInfo.user.email)}</span></td>
			</tr>
			<tr>
				<td align="right">QQ：</td>
				<td colspan="4"><span>${escape:escapeHtml(userInfo.qq)}</span></td>
			</tr>
			<tr>
				<td align="right">传真：</td>
				<td colspan="4"><span>${escape:escapeHtml(userInfo.fax)}</span></td>
			</tr>
			<tr>
				<th width="145">公司信息</th>
				<th colspan="4">&nbsp;</th>
			</tr>
			<tr>
				<td align="right">*公司名称：</td>
				<td colspan="4"><span>${escape:escapeHtml(userInfo.companyName)}</span></td>
			</tr>
			<tr>
				<td align="right">公司类型：</td>
				<td colspan="4"><span>${escape:escapeHtml(userInfo.companyType)}</span></td>
			</tr>
			<tr>
				<td align="right">供应商性质：</td>
				<td colspan="4"><span><c:forEach items="${supplierTypes }" var="supplierType" varStatus="status">
											<c:if test="${userInfo.supplierType==status.index }">
											<spring:message code="seller.supplierType.${status.index}"/>
											</c:if>
									</c:forEach></span></td>
			</tr>
			<tr>
				<td align="right">成立日期：</td>
				<td colspan="4"><span><fmt:formatDate value='${userInfo.registerDate}'
								pattern='yyyy-MM-dd' /></span></td>
			</tr>
			<tr>
				<td align="right">注册资本：</td>
				<td colspan="4"><span>${escape:escapeHtml(userInfo.registerCapital)}</span></td>
			</tr>
			<tr>
				<td align="right">实收资本：</td>
				<td colspan="4"><span>${escape:escapeHtml(userInfo.registerCapital)}</span></td>
			</tr>
			<tr valign="top">
				<td align="right">经营范围：</td>
				<td colspan="4"><span>${userInfo.businessScope}</span></td>
			</tr>
			<tr valign="top">
				<td align="right">*公司简介：</td>
				<td colspan="4"><span>${userInfo.remark}</span></td>
			</tr>
			<tr>
				<td align="right">公司网址：</td>
				<td colspan="4"><span>${escape:escapeHtml(userInfo.companyWebSite)}</span></td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td align="right">品牌城市：</td> -->
<%-- 				<td colspan="4"><span>${escape:escapeHtml(userInfo.city.name)}</span></td> --%>
<!-- 			</tr> -->
			<tr>
				<td align="right">*可售品牌：</td>
				<td colspan="4" class="c-green">
					<b>${escape:escapeHtml(userInfo.brandNames)}</b>
				</td>
			</tr>
			<tr>
				<td align="right">公司地址：</td>
				<td colspan="4"><span>${escape:escapeHtml(userInfo.companyAddress)}</span></td>
			</tr>
			<tr>
				<td align="right">*发货地址：</td>
				<td colspan="4"><span>${escape:escapeHtml(userInfo.shiperAddress)}</span></td>
			</tr>
			<tr>
				<td align="right">*退货地址：</td>
				<td colspan="4"><span>${escape:escapeHtml(userInfo.receiverAddress)}</span></td>
			</tr>
			<tr>
				<td align="right">法人代表：</td>
				<td colspan="4"><span>${escape:escapeHtml(userInfo.companyCorporation)}</span></td>
			</tr>
			<tr>
				<td align="right">税务登记号：</td>
				<td width="220"><span>${escape:escapeHtml(userInfo.taxNo)}</span></td>
				<td align="right">企业法人营业执照注册号：</td>
				<td colspan="2"><span>${escape:escapeHtml(userInfo.companyCode)}</span></td>
			</tr>
			<tr>
				<td align="right">企业编码：</td>
				<td colspan="4"><span>${escape:escapeHtml(userInfo.companyNo)}</span></td>
			</tr>
			<tr>
				<td align="right">*支付宝帐号：</td>
				<td width="220"><span>${escape:escapeHtml(userInfo.alipayNo)}</span></td>
				<td align="right">*注册支付宝姓名：</td>
				<td colspan="2"><span>${escape:escapeHtml(userInfo.alipayName)}</span></td>
			</tr>
			<tr>
				<td align="right">*开户银行：</td>
				<td width="220"><span>${escape:escapeHtml(userInfo.bankName)}</span></td>
				<td align="right">*银行帐号：</td>
				<td><span>${escape:escapeHtml(userInfo.bankAccount)}</span></td>
				<td></td>
			</tr>
			<tr valign="top">
				<td align="right">营业执照：</td>
				<td colspan="4">
					<div class="license">
					<c:if test="${not empty userInfo.licenseImageUrl}">
						<img src="<yp:thumbImage originalPath='${userInfo.licenseImageUrl}' imageType='t24'></yp:thumbImage>"  alt=""/>
					</c:if>	
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="5" align="center">
				<input type="button" value="返回" onclick="returnButton()" class="addBusiness-btn"/>
				</td>
			</tr>
		</table>
		</form>
	</div>
</div>
<jsp:include page="/WEB-INF/pages/include/foot.jsp"></jsp:include>
</body>
</html>