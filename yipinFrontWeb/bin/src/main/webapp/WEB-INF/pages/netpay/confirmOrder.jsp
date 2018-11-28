<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<title>确认订单</title>
	<jsp:include page="/WEB-INF/pages/include/common.jsp"></jsp:include>
	<link rel="stylesheet" href="${_jsPath }/plugin/artdialog/skins/ytoxl.css" />
	<link rel="stylesheet" type="text/css" href="${_cssPath}/pages/confirmOrder.css" media="all" />
</head>
<body>
	<!--头部导航 start-->
	<jsp:include page="/WEB-INF/pages/include/head.jsp"></jsp:include>
	<!--头部导航 end-->
	<!--确认订单 start-->
	<form action="${_ctxPath}/netpay/payit.htm" method="post" target="_blank" id="payform">
	<input type="hidden" name="netpayModel.orderIds" value="${netpayModel.orderIds }"/>
	<div class="w_norm">
		<div class="comfirm">
			<div class="status_show">
				<p class="status_txt">订单提交成功，请立即付款，以便我们安排发货！</p>
				<p>请您在<i class="red">1</i>小时内完成付款，超过后系统将自动取消订单</p>
			</div>
			<div class="status_desc">
				<strong>订单编号：</strong>${netpayModel.orderNos }
				<p><strong>订单金额：</strong><span class="red">¥${netpayModel.totalFee }</span></p>
			</div>
			<div class="pay_way_wp">
				<p>已选择付款方式：<strong><spring:message code="order.payment.type.${netpayModel.payType}"/></strong></p>
			</div>
			<div class="pay_btn">
				<a href="javascript:;" id="payBtn">立即支付</a>
			</div>
		</div>
	</div>
	</form>
	<!--确认订单 end-->
	<!--支付层-->
	<div class="cf confirmOrder_box J_confirmOrder">
		<div class="fn_left success_box">
			<p class="f22">支付成功</p>
			<p>立即查看<a href="${_ctxPath}/order/order-myOrders.htm" title="订单详情>">订单详情></a></p>
		</div>
		<div class="fn_left failure_box">
			<p class="f22">如果支付失败...</p>
			<p>请尝试<a href="#" title="支付宝快捷支付 >" id="_rePay">重新支付></a></p>
			<p>查看<a href="${_ctxPath}/help/help-100005.htm" title="支付常见问题>" target="_blank">支付常见问题></a></p>
		</div>
	</div>
	<!--底部 start-->
	<%@include file="/WEB-INF/pages/include/foot.jsp"%>
	<!--底部 start-->
	<!--page js-->
	<script type="text/javascript" src="${_jsPath }/pages/confirmOrder.js"></script>
</body>
</html>