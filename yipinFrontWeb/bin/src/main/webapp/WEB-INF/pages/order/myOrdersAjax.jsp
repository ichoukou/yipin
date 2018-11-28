<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
	<div class="data_show">
		<div class="data_tit cf">
			<div class="col1">订单信息</div>
			<div class="col2">数量</div>
			<div class="col3">支付金额</div>
			<div class="col4">操作</div>
		</div>
		<!-- 订单列表 start-->
		<c:forEach items="${orderPage.result}" var="order">
		<div class="data_item">
			<div class="fold cf">
				<div class="col1">
					<span class="ord_num">订单号：${order.orderNo}</span>
					<span>下单时间：<fmt:formatDate value="${order.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
					<span><spring:message code="order.status.${order.status}"/></span>
					<span style="margin-right:0;">
						<c:if test="${not empty order.hasInvoice and order.hasInvoice eq invoiceStatus[1]}">
						<a href="${_ctxPath}/order/order-invoiceDetail-${order.orderId}.htm" target="_blank">发票信息</a>
						</c:if>
					</span>
				</div>
				<div class="col2 num">${order.skuCount}</div>
				<div class="col3 mny">${order.paymentAmount}</div>
				<div class="col4">
					<!-- 订单是待付款状态时显示待付款连接 -->
					<c:if test="${order.status eq status[0]}">
						<a target="_blank" href="${_ctxPath}/netpay/confirmOrder.htm?orderIds=${order.orderId}">付款</a>
					</c:if>
					<!-- 取消状态显示删除 -->
					<c:if test="${order.status eq status[4]}">
						<a href="javascript:;" class = "J_delorder" data-id="${order.orderId}">删除</a>
					</c:if>
					<!-- 取消状态显示取消 -->
					<c:if test="${order.status eq status[0]}">
						<span class="line">|</span>
						<a href="javascript:;" class = "J_cancelorder" data-id = "${order.orderId}">取消</a>
					</c:if>
				</div>
			</div>
			<div class="fold_wp">
				<div class="data_con">
					<!-- 包裹开始 -->
					<!-- 如果只有一个包裹就不显示 包裹金额和数量 -->
					<c:set var="isDisplayPackageDetail" value="${fn:length(order.addressItems) > 1}"/>
					<c:forEach items="${order.addressItems}" var="list" varStatus="ss">
					<div class="uf_item <c:if test='${fn:length(order.addressItems) == ss.index+1}'>nobm</c:if>">
						<div class="unfold cf">
							<table width="100%">
								<tbody>
									<tr>
										<td width="70" class="pak_nm">
											<c:if test="${isDisplayPackageDetail}">包裹${list.packageNo}：</c:if>
										</td>
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
											<div class="relative">
											<a href="javascript:;" class="J_track" data='{"expressId":"${list.expressId}","expressNo":"${list.expressNo}","orderId":"${order.orderId}"}'>跟踪<b class="arrow_piont"></b></a>
											<div class="tip_popup track">
												<div class="tip_inner">
													<p><b>订单跟踪</b></p>
													<div class="loading">
														<img src="${_imagesPath}/loading.gif" />
														<span>正在努力加载...</span>
													</div>
												</div>
												<div class="tip_arrow" style="right:130px;"><span>◆</span><i>◆</i></div>
											</div>
											<span class="line">|</span>
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
											</div>
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
										<td width="384">
											<p><a href="javascript:;">${itemList.orderItem.productName}</a></p>
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
					<!-- 包裹开始 结束 -->
				</div>
			</div>
		</div>
		</c:forEach>
		<!-- 订单列表 end-->
	</div>
	<!-- 分页导航 start-->
	<yp:yipinFrontPage urlEnd=".htm" urlStart="order-myOrders-${orderPage.params.queryTime}-${orderPage.params.status}-${orderPage.total}-" page="${orderPage}"></yp:yipinFrontPage>
	<!-- 分页导航 end-->