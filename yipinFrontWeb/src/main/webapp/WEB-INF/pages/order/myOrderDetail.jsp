<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<c:if test="${empty listOrderAddress}">
	<div class="loading">查询订单详细出错啦!</div>
</c:if>
<c:if test="${not empty listOrderAddress}">
	<!-- 如果只有一个包裹就不显示 包裹金额和数量 -->
	<c:set var="isDisplayPackageDetail" value="${fn:length(listOrderAddress) > 1}"/>
	<c:forEach items="${listOrderAddress}" var="list" varStatus="ss">
	<div class="uf_item <c:if test='${fn:length(listOrderAddress) == ss.index+1}'>nobm</c:if>">
		<div class="unfold cf">
			<table width="100%">
				<tbody>
					<tr>
						<td width="17" class="pak_nm"></td>
						<td width="542">${escape:escapeHtml(list.receiveAddress)} (${list.receiverName} 收) ${list.mobile}</td>
						<td class="num">
							<%-- <c:if test="${isDisplayPackageDetail}">${list.skuCount}</c:if> --%>
						</td>
						<td class="mny">
							<%-- <c:if test="${isDisplayPackageDetail}">${list.packageTotalPrice}</c:if> --%>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="package">
			<table width="100%">
				<tbody>
					<c:forEach items="${list.orderAddressItems}" var="itemList">
					<!-- 如果只有一个包裹切只有一个商品就不显示 包裹金额和数量 -->
					<c:set var="isDisplayProductNum" value="${fn:length(list.orderAddressItems) > 1}"/>
					<tr>
						<td width="50"></td>
						<td width="95">
							<a href="${_ctxPath}/item-${itemList.orderItem.productSkuId}-0-0.htm" target="_blank">
								<img src="<yp:thumbImage originalPath='${itemList.orderItem.defaultImage}' imageType='t89'></yp:thumbImage>" alt="${itemList.orderItem.productName}" />
							</a>
						</td>
						<td width="395">
							<p>
								<a href="${_ctxPath}/item-${itemList.orderItem.productSkuId}-0-0.htm" target="_blank">
								${yipin:cutString(itemList.orderItem.productName,25)}
								</a>
							</p>
							<p><span>${itemList.orderItem.productProp}</span></p>
						</td>
						<td width="80">
							<c:if test="${isDisplayProductNum}">${itemList.assignCount}</c:if>
						</td>
						<td width="130">
							<c:if test="${isDisplayProductNum}">${itemList.assignCount * itemList.orderItem.unitPrice}</c:if>
						</td>
						<td>
							<!-- 已经退货的才显示这个 TODO -->
							<c:if test="${not empty itemList.orderItem.refundStatusInfo}">
								退货状态:${itemList.orderItem.refundStatusInfo}
								<a href="${_ctxPath}/order/order-myRefundRecord.htm" class="c1">退货记录</a>
							</c:if>
							<!-- 订单完成状态并可以退货 -->
							<c:if test="${itemList.orderItem.canRefund}">
								<a href="${_ctxPath}/order/order-myOrderRefundForm-${itemList.orderItem.orderItemId}.htm" class="c1">申请退货</a>
							</c:if>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	</c:forEach>
</c:if>