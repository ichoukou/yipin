<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.ytoxl.module.yipin.base.dataobject.Product" %>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<!--star 选择商品 -->
<%-- <c:set var="checkPend" value="<%=Product.STATUS_CHECK_PEND %>"></c:set> --%>
<c:forEach items="${userInfos}" var="seller"  varStatus="status">
<div class="addSale-saler-list">
	<div class ="addSale-saler" id="addSale-saler${status.index}">
		<p>${seller.user.username}</p>
		<input type="checkbox" id="select-all${status.index}" onchange="selectAll(${seller.userInfoId},${status.index});"/><label>全选</label>
	</div>
	<div class="addSale-product" id="addSale-product${status.index}">
		<ul class="m-clear">
		<c:choose>
		<c:when test="${empty seller.productList}">
			<li>没有可参加销售的商品</li>
		</c:when>
		<c:otherwise>
			<input type="hidden" id="totalCountSale${seller.userInfoId}" value="${fn:length(seller.productList)}"/>
			<c:forEach items="${seller.productList}" var="product" varStatus="pStatus">
			<li>
   				<input type="hidden" id="productId${seller.userInfoId}${pStatus.index}" value="${product.productId}"/>				
   				<img <c:if test="${product.status == checkPend }">style="filter:gray;-moz-opacity:.1;opacity:0.1;"</c:if> 
				src="<yp:thumbImage originalPath='${product.imageUrls}' imageType='t84'></yp:thumbImage>"/>
				<p><%--imageUrls --%>
					<input type="checkbox"  class="select-box" value="${product.productId}" id="productCbx${seller.userInfoId}${pStatus.index}"
					 onchange="setProcRank(${product.productId},${seller.userInfoId},${pStatus.index});"/>
					  
				</p>
				<div class="sale-product-info left-icon m-hide">
			    	<i></i>
			    	<h1><a href="#">${product.name }</a></h1>
<%-- 			    	<p>库存：${product.saleInventoryCount }件&nbsp;销售次数：${product.salesNum }次</p> --%>
			    </div>
			</li>
			</c:forEach>
		</c:otherwise>
		</c:choose>
		</ul>
	</div>
</div>
</c:forEach>
<!--end 选择商品 -->