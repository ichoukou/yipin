﻿<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.ytoxl.module.yipin.base.dataobject.Product" %>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<html lang="zh">
<head>
<meta charset="utf-8" />
<title>查看商品</title>
	<link href="${_cssPath }/common.css" rel="stylesheet" />
  	<link href="${_cssPath }/pages/addProduct.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${_cssPath }/pages/produce-detail.css">
  	<script type="text/javascript" src="${_jsPath }/jquery/jquery.1.8.1.js"></script>
<body>
<!--start header-->
<jsp:include page="../include/header.jsp"></jsp:include> 
<!--end header-->
<!--start body-->
<div class="body m-w980p">
    	<input type="hidden" id="productId" value="${product.productId }">
		<table border="0" class="addPro-table">
			<tr>
				<td class="left-td">品牌：</td>
				<td>${product.brand.name }</td>
				<td colspan="2"><span id="brandIdTip"></span></td>
			</tr>
			<tr>
				<td class="left-td">商品名称：</td>
				<td colspan="2">${product.name }</td>
				<td><span id="nameTip"></span></td>
			</tr>
			<tr valign="top">
				<td class="left-td" class="small">库存：</td>
				<td colspan="3" class="small">
					<table>
						<tr>
							<td width="450">
								<table class="repertories" width="630" class="small">
								<tr>
									<th width="12%">颜色</th>
									<th width="12%">规格</th>
									<th width="12%">数量</th>
									<th width="26%">国际码</th>
									<th width="26%">SKU编码</th>
									<th width="12%">价格</th>
								</tr>
								<c:set value="0" var="num"></c:set>
								<c:choose>
								<c:when test="${empty product.productSkuColorNum or empty product.productSkuSizeNum }">
									<c:set value="1" var="rowspan"></c:set>
								</c:when>
								<c:otherwise>
									<c:set value="${product.productSkuSizeNum }" var="rowspan"></c:set>
								</c:otherwise>
								</c:choose>
								<c:forEach items="${product.productSkus }" var="productSku" varStatus="status">
								<tr>
									<c:if test="${num == 0 }">
									<td rowspan="${rowspan}">${productSku.productSkuColor }</td>
									</c:if>
									<td>${productSku.productSkuSize }</td>
									<td>${productSku.inventory }</td>
									<td>${productSku.internationalCode }</td>
									<td>${productSku.skuCode }</td>
									<td>${productSku.unitPrice }</td>
								</tr>
								<c:set value="${num + 1 }" var="num"></c:set>
								<c:if test="${num == rowspan }">
									<c:set value="0" var="num"></c:set>
								</c:if>
								</c:forEach>
							</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr valign="top" class="small">
				<td class="left-td">商品图片：</td>
				<td colspan="3">
					<table class="no-border-table">
						<tr>
							<td width="120">
								<div class="default-img">
									<img src="<zx:thumbImage originalPath='${product.defaultImage}' imageType='t15'></zx:thumbImage>" />
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<%-- <tr valign="top" class="small">
				<td class="left-td">商品描述：</td>
				<td colspan="3">
					${product.description }
				</td>
			</tr> --%>
		</table>
		<%-- <div class="layout  goods-dis">
            <div>
                <ul class="panel">
                  <li class="cur">商品信息</li>
              	</ul>
            </div>
            
            <div class="clear"></div>
            <div class="pro-detail">
            	${product.description }
            </div>
        </div> --%>
		<c:set var="notPass" value="<%= Product.STATUS_NO_PASS %>"></c:set>
        <c:set var="checkPend" value="<%= Product.STATUS_CHECK_PEND %>"></c:set>
        <c:if test="${product.status == notPass || product.status==checkPend && mark ==2}">
        <div class="goods-check">
            <label>不通过原因：</label>
            <textarea class="text-area input-marks" id="remark">${product.remark }</textarea>
            <span id="remarkTip" style="display: none;"><font color="red">请填写不通过原因</font></span>
        </div>
        </c:if> 
        <div class="btn-check">
        	<aut:authorize url="/admin/product/product-review">
        		<c:if test="${product.status == checkPend && mark == 2}">
           			<input type="button" class="m-btn" value="审核通过" id="pass"/>
            		<input type="button" class="m-btn" value="审核不通过" id="notPass"/>
        		</c:if>
        	</aut:authorize> 
            <input type="button" class="m-btn" value="返   回" id="back"/>
        </div>
</div>
<!--end body-->
<!--start footer-->
<jsp:include page="/WEB-INF/pages/include/foot.jsp"></jsp:include>
<!--end footer-->
<script type="text/javascript" src="${_jsPath }/pages/viewProduct.js"></script>
<script type="text/javascript">
 	var STATUS_PASS = <%= Product.STATUS_PASS%>;
    var STATUS_NO_PASS = <%= Product.STATUS_NO_PASS%>;
</script>
</body>
</html>