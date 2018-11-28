<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<title>支付成功页</title>
	<link rel="stylesheet" type="text/css" href="${_cssPath}/lib/global.css" media="all" />
	<link rel="stylesheet" type="text/css" href="${_cssPath}/pages/confirmOrder.css" media="all" />
</head>
<body>
	<jsp:include page="/WEB-INF/pages/include/head.jsp"></jsp:include>
	<div class="w_norm">
		<div class="comfirm">
			<div class="status_show">
				<p class="status_txt">订单支付成功，我们会尽快安排发货！</p>
			</div>
			<div class="status_desc">
				订单编号：${orderIds }
			</div>
			<div class="pay_btn paySuccess">
				<a href="${_ctxPath}" id="getBack">继续购物</a>
				<a href="${_ctxPath}/order/order-myOrders.htm" id="payBtn">查看订单</a>
			</div>
		</div>
	</div>
	<!--悬浮购物车 s -->
	<%@include file="/WEB-INF/pages/order/miniCart.jsp"%>
	<!--悬浮购物车 e -->
	<!-- 底部 start -->
	<%@include file="/WEB-INF/pages/include/foot.jsp"%>
	<!--底部 end-->
</body>
</html>