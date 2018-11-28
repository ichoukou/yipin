<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<title>退款记录</title>
	<!--css-->
	<jsp:include page="/WEB-INF/pages/include/common.jsp"></jsp:include>
	<!--page css-->
	<link rel="stylesheet" type="text/css" href="${_cssPath}/pages/ordData.css" media="all" />
	<style type="text/css">
		.loading{text-align:center;padding:20px 0;}
	</style>
</head>
<body>
	<!--头部导航 start-->
	<%@include file="/WEB-INF/pages/include/head.jsp"%>
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
					<a href="${_ctxPath}/order/order-myOrderCancelManage.htm"><span>退款申请</span></a>
					<span>|</span>
					<a href="${_ctxPath}/order/order-myOrderCancelRecord.htm" class="on">退款记录</a>
					<span>|</span>
					<span class="f12">您可以在此申请退换货以及查询退货订单进度</span>
				</div>
				<c:if test="${not empty cancelPage.result}">
				<div class="my_order">
					<div class="data_show">
						<div class="data_tit cf">
							<div class="fl c_cr1">退款编号</div>
							<div class="fl c_cr2">来源订单</div>
							<div class="fl c_cr3">申请时间</div>
							<div class="fl c_cr4">订单支付金额</div>
							<div class="fl c_cr5">实际退款金额</div>
							<div class="fl c_cr6">状态</div>
							<div class="fl c_cr7">操作</div>
						</div>
						<!-- 订单列表 start-->
						<c:forEach items="${cancelPage.result}" var="order">
						<div class="data_item">
							<div class="fold cf">
								<div class="fl c_cr1">${order.orderCancelNo}</div>
								<div class="fl c_cr2">
									<a href="${_ctxPath}/order/order-myOrderPackageDetail-${order.orderHead.orderAddress.orderAddressId}.htm" target="_blank">
									${order.orderHead.orderNo}
									</a>
								</div>
								<div class="fl c_cr3"><fmt:formatDate value="${order.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
								<div class="fl c_cr4">${order.orderHead.paymentAmount}</div>
								<div class="fl c_cr5">${order.refundAmount}&nbsp;</div>
								<div class="fl c_cr6"><spring:message code="order.wait.status.${order.status}"/></div>
								<div class="fl c_cr7 rel">
									<!-- 查看详情TODO -->
									<a href="${_ctxPath}/order/order-myOrderPackageDetail-${order.orderHead.orderAddress.orderAddressId}.htm" target="_blank">查看详情</a>
									<c:if test="${order.status eq orderCancelStatus[2]}">
										<!-- 审核不通过 显示原因 -->
										<a href="javascript:;" class="J_track">原因：${yipin:cutString(order.reviewDescribe,6)}</a>
										<div class="tip_popup return_resa">
											<div class="tip_inner">
												<p>${order.reviewDescribe}</p>
											</div>
											<div class="tip_arrow"><span>◆</span><i>◆</i></div>
										</div>
									</c:if>
								</div>
							</div>
						</div>
						</c:forEach>
						<!-- 订单列表 end-->
					</div>
					<!-- 分页导航 start-->
					<yp:yipinFrontPage urlEnd=".htm" urlStart="order-myOrderCancelRecord-${cancelPage.total}-" page="${cancelPage}"></yp:yipinFrontPage>
					<!-- 分页导航 end-->
				</div>
				</c:if>
				<c:if test="${empty cancelPage.result}">
					<div class="noRecord">暂无退款记录！</div>
				</c:if>
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