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
						<td width="70" class="pak_nm">包裹${list.packageNo}：</td>
						<td width="315">${escape:escapeHtml(list.receiveAddress)} (${list.receiverName} 收) ${list.mobile}</td>
						<td width="145">
							<!-- 已经退货的才显示这个 TODO -->
							<c:if test="${not empty list.refundStatusInfo}">
								退货状态:${list.refundStatusInfo}
							</c:if>
						</td>
						<td width="65" class="num">
							<c:if test="${isDisplayPackageDetail}">${list.skuCount}</c:if>
						</td>
						<td width="70" class="mny">
							<c:if test="${isDisplayPackageDetail}">${list.packageTotalPrice}</c:if>
						</td>
						<td>
							<!-- <a href="javascript:;">跟踪</a>
							<span class="line">|</span> -->
							<a href="${_ctxPath}/order/order-myOrderPackageDetail-${list.orderAddressId}.htm" target="_blank">查看</a>
							<!-- 订单完成状态并可以退货 -->
							<c:if test="${list.canRefund}">
								<span class="line">|</span>
								<a href="${_ctxPath}/order/order-myOrderRefundForm-${list.orderAddressId}.htm">退货</a>
							</c:if>
							<!-- 确认收获按钮 TODO-->
							<c:if test="${list.orderHead.status eq status[2] and list.isReceive eq packageStatus[0]}">
								<span class="line">|</span>
								<a href="javascript:;" class="J_confirmgood" data-id="${list.orderAddressId}">确认收货</a>
							</c:if>
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
							<a href="${_ctxPath}/brand/sellbuy-${itemList.orderItem.productSkuId}.htm" target="_blank">
								<img src="<yp:thumbImage originalPath='${itemList.orderItem.defaultImage}' imageType='t84'></yp:thumbImage>" alt="${itemList.orderItem.productName}" />
							</a>
						</td>
						<td width="423">
							<p>${itemList.orderItem.productName}</p>
							<p><span>${itemList.orderItem.productProp}</span></p>
						</td>
						<td width="65">
							<c:if test="${isDisplayProductNum}">${itemList.assignCount}</c:if>
						</td>
						<td>
							<c:if test="${isDisplayProductNum}">${itemList.assignCount * itemList.orderItem.unitPrice}</c:if>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	</c:forEach>
</c:if>