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
					<span>
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
					<div class="loading"><img src="${_imagesPath}/loading.gif" /></div>
					<input class="orderIdHiddenVal" type="hidden" value="${order.orderId}"/>
					<!-- 包裹开始 -->
					<!-- //TODO ajax返回-->
					<!-- 包裹开始 结束 -->
				</div>
			</div>
		</div>
		</c:forEach>
		<!-- 订单列表 end-->
	</div>
	<!-- 分页导航 start-->
	<yp:yipinFrontPage urlEnd=".htm" urlStart="order-myOrders-${orderPage.total}-" page="${orderPage}"></yp:yipinFrontPage>
	<!-- 分页导航 end-->