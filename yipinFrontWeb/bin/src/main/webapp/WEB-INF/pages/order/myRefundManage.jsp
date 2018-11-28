<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<title>退货管理</title>
	<!--css-->
	<jsp:include page="/WEB-INF/pages/include/common.jsp"></jsp:include>
	<!--page css-->
	<link rel="stylesheet" type="text/css" href="${_cssPath}/pages/ordData.css" media="all" />
</head>
<body>
	<!--头部导航 start-->
	<%@include file="/WEB-INF/pages/include/headUserInfo.jsp"%>
	<!--头部导航 end-->
	<div class="w_norm">
		<!-- 用户中心框架 start-->
		<div class="uc cf">
			<!-- 用户中心左侧 start-->
			<%@include file="/WEB-INF/pages/include/left.jsp"%>
			<!-- 用户中心左侧 end-->
			<!-- 用户中心右侧 start-->
			<div class="uc_r">
				<div class="ucr_hd">
					<a href="${_ctxPath}/order/order-myRefundManage.htm" class="on"><span>退货申请</span></a>
					<span>|</span>
					<a href="${_ctxPath}/order/order-myRefundRecord.htm">退货记录</a>
					<span>|</span>
					<span class="f12">您可以在此申请退换货以及查询退货订单进度</span>
				</div>
				<div class="my_order">
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
									<span style="margin-right:0px;">
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
										<!-- <span class="line">|</span> -->
										<a href="javascript:;">付款</a>
									</c:if>
									<!-- 取消状态显示删除 -->
									<c:if test="${order.status eq status[4]}">
										<span class="line">|</span>
										<a href="javascript:;">删除</a>
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
					<yp:yipinFrontPage urlEnd=".htm" urlStart="order-myRefundManage-${orderPage.total}-" page="${orderPage}"></yp:yipinFrontPage>
					<!-- 分页导航 end-->
				</div>
			</div>
			<!-- 用户中心右侧 end-->
		</div>
		<!-- 用户中心框架 end-->
	</div>
	<!--底部 start-->
	<%@include file="/WEB-INF/pages/include/foot.jsp"%>
	<!--底部 end-->
	<!--js-->
	<script type="text/javascript" src="${_jsPath}/plugin/plugin.js"></script>
	<!--page js-->
	<script type="text/javascript" src="${_jsPath}/pages/ordData.js"></script>
</body>
</html>