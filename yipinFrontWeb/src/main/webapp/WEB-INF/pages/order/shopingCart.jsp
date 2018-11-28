<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<title>购物车</title>
	<!--css-->
	<link rel="stylesheet" type="text/css" href="${_cssPath}/lib/global.css" media="all" />
	<!--page css-->
	<link rel="stylesheet" type="text/css" href="${_cssPath}/pages/order.css" media="all" />
</head>
<body>
	<!--头部 start-->
	<jsp:include page="/WEB-INF/pages/include/head.jsp"></jsp:include>
	<!--头部 end-->
	<form action="${_ctxPath}/order/order-getOrderInfo.htm" method="post" id="cartform">
	<!--购物车部分 start-->
	<div class="w_norm order_content">
		<div class="order_lc">
			<img src="${_imagesPath}/navigation/lc1.jpg" alt="" />
		</div>
		<c:choose>
		<c:when test="${not empty orders }">
		<c:set  value="0" var="totalAmount"/>
		<c:set var="totalNum" value="0"/>
		<c:set var="totalWeight" value="0"/>
		<c:set var="totalPostAmount" value="0"/>
		<!--商品清单 start-->
		<div class="order_inventory goods_inventory" id="order_inventory">
			<h2>商品清单<span><a href="${_ctxPath}">继续购物</a></span></h2>
			<table class="th_table">
				<thead>
					<tr>
						<th width="45" class="th_b"><input type="checkbox" class="checkbox allCheck"/>全选</th>
						<th width="400" class="th_a">商品</th>
						<th width="80">重量</th>
						<th width="90">规格</th>
						<th width="130">一品价</th>
						<th width="190">数量</th>
						<th width="100">小计</th>
						<th>操作</th>
					</tr>
				</thead>
			</table>
			<c:forEach items="${orders}" var="orderHead">
			<c:set value="0" var="orderWeight"/>
			<c:forEach items="${orderHead.items }" var="orderItem">
				<c:set value="${orderWeight + orderItem.productSku.skuWeight*orderItem.num}" var="orderWeight"/>
			</c:forEach>
			<c:choose>
				<c:when test="${orderWeight <=1000 }"><c:set value="10" var="postPrice"/></c:when>
				<c:otherwise><c:set value="20" var="postPrice"/></c:otherwise>
			</c:choose>
			<c:set var="totalPostAmount" value="${totalPostAmount + postPrice}"/>
			<div class="order_th">
				<c:choose>
					<c:when test="${orderTypes[1] eq orderHead.orderType}">
						<span class="fr">预计发货时间：<i class="red"><fmt:formatDate value="${orderHead.predictSendTime }"/></i></span>
					</c:when>
				</c:choose>
				<span class="W600">
					<input type="checkbox" class="checkbox parentCheck"/>发货地：${orderHead.deliveryAddress}
					<c:choose>
					<c:when test="${orderTypes[0] eq orderHead.orderType}">（普通订单）</c:when>
					<c:when test="${orderTypes[1] eq orderHead.orderType}">
						<span class="red">（预售订单）</span>
					</c:when>
					<c:when test="${orderTypes[2] eq orderHead.orderType}">
						<span class="red">（抢购订单）</span>
					</c:when>
				</c:choose>
				</span>
				运费：<i class="J_orderFreight">${postPrice }</i>元<span class="yh_freight">运费优惠：<i class="J_yhFreight">-${postPrice }</i>元</span>
			</div>
			<table class="order_table">
				<tbody>
					<c:forEach items="${orderHead.items }" var="orderItem">
					<c:set value="${totalAmount + orderItem.unitPrice*orderItem.num}" var="totalAmount" />
					<c:set value="${totalWeight + orderItem.productSku.skuWeight*orderItem.num}" var="totalWeight"/>
					<tr data-sku="${orderItem.productSku.skuWeight }" data-product="${orderItem.productSku.productSkuId }" data-price="${orderItem.productSku.unitPrice }" data-kucun="${orderItem.productSku.inventory}">
						<td width="40" class="th_b">
							<c:if test="${orderItem.productSku.inventory != 0}">
							<input type="checkbox" class="checkbox subCheck"/>
							</c:if>
							<c:if test="${orderItem.productSku.inventory == 0}">
							<input type="checkbox" class="checkbox subCheck" disabled="disabled"/>
							</c:if>
						</td>
						<td width="455">
							<a class="m_mcPic" href="${_ctxPath}/item-${orderItem.productSku.productSkuId }-0-0.htm" title="${orderItem.productSku.product.name}" target="_blank">
								<img src="<yp:thumbImage originalPath="${orderItem.productSku.product.coverPicture}" imageType="t89"></yp:thumbImage>" alt="${orderItem.productSku.product.name}" width="50" height="50"/>
								<span>${yipin:cutString(orderItem.productSku.product.name,25)}</span>
							</a>
						</td>
						<td width="80"><span class="f14">${orderItem.productSku.skuWeight }g</span></td>
						<td width="90"><span class="f14">${orderItem.productSku.skuSpecification}</span></td>
						<td width="130"><span class="f14 red">￥${orderItem.productSku.unitPrice }</span></td>
						<td width="190">
							<div class="rel">
							<c:choose>
								<c:when test="${orderItem.productSku.inventory == 0}"><label class="red J_outTip">商品已售罄</label></c:when>
								<c:otherwise>
								<div class="J_numChange">
									<c:if test="${orderItem.productSku.inventory >= orderItem.num}">
									<a class="less" href="javascript:">-</a><input type="text" value="${orderItem.num }" class="len_input J_skuNum" maxlength="2"><a class="more" href="javascript:">+</a>
									<c:set value="${totalNum + orderItem.num}" var="totalNum" />
									</c:if>
									<c:if test="${orderItem.productSku.inventory < orderItem.num}">
									<a class="less" href="javascript:">-</a><input type="text" value="${orderItem.productSku.inventory }" class="len_input J_skuNum" maxlength="2"><a class="more" href="javascript:">+</a>
									<c:set value="${totalNum + orderItem.productSku.inventory}" var="totalNum" />
									</c:if>
									
								</div>
								<label class="red J_outTip"></label>
								<label class="red outTip J_productTip">
									<c:if test="${orderItem.productSku.inventory < orderItem.num}">
									库存不足，调整为${orderItem.productSku.inventory }件！
									</c:if>
								</label>
								</c:otherwise>
							</c:choose>
							</div>
						</td>
						<td width="100"><span class="f14 red">￥<i class="J-allPrice">${orderItem.unitPrice * orderItem.num}</i></span></td>
						<td><a href="javascript:" class="delect" title="删除"></a></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			</c:forEach>
			<div class="order_tip">
				<p class="fr"><span class="m20">共<i class="red J_goodsLen">${totalNum }</i>件商品</span>选购商品来自不同的产地，一城一品保证由原产地发货，系统将为您拆分为多个订单！</p>
				<input type="checkbox" class="checkbox allCheck"/>全选
				<a href="javascript:" class="recycle">删除选中商品</a>
				<a href="${_ctxPath}">继续购物</a>
			</div>
			<!--结算信息 start-->
			<div class="cf clearing_msg">
				<div class="fn_right">
					<p>共<span class="shuliang">${totalNum }</span>件商品</p>
					<p>商品总量：<span class="allWeight">${totalWeight}</span>克</p>
					<p>商品总价：<span class="allPrice">${totalAmount }</span>元</p>
					<p>运费：<span class="freight">${totalPostAmount }</span>元</p>
					<p>运费优惠：<span class="freightFire">-${totalPostAmount }</span>元</p>
					<p>获得积分：<span class="J-jifen">${yipin:computerPoint(totalAmount)}</span>分</p>
					<p class="t_line">应付总额：<i class="red">￥</i><span class="price">${totalAmount }</span><span class="rmb">元</span></p>
					<p><a href="${_ctxPath}/" class="goOnBtn" id="goOnBtn">继续挑选</a><a href="javascript:;" class="ClearingBtn" id="ClearingBtn">去结算</a></p>
					<p class="red J_orderTip hide">您的订单里面有库存不足的商品，请自行修改！</p>
				</div>
			</div>
		</div>
		<!--商品清单 end-->
		</c:when>
		<c:otherwise>
		<!--没有商品 start-->
		<div id="no_goods" class="no_goods" style="display:block;">
			<div class="inner">购物车内暂时没有商品，您可以<a href="${_ctxPath}/" class="cf90">去首页</a>挑选喜欢的商品</div>
		</div>
		<!--没有商品 end-->
		</c:otherwise>
		</c:choose>
		<div id="no_goods" class="no_goods">
			<div class="inner">购物车内暂时没有商品，您可以<a href="${_ctxPath}/" class="cf90">去首页</a>挑选喜欢的商品</div>
		</div>
	</div>
	<!--购物车部分 end-->
	<div class="hide" id="_hiddenFormData">
		<div id="_dynamicFormData"></div>
	</div>
	</form>
	<!--底部 start-->
	<%@include file="/WEB-INF/pages/include/foot.jsp"%>
	<!--底部 end-->
	<!--page js-->
	<%@include file="/WEB-INF/pages/include/static_param.jsp"%>
	<link rel="stylesheet" type="text/css" href="${_jsPath}/plugin/artdialog/skins/ytoxl.css" media="all" />
	<script src="${_jsPath}/plugin/select/linkage_sel.js"></script>
	<script src="${_jsPath}/plugin/bigdecimal/mathcontext.js"></script>
	<script src="${_jsPath}/plugin/bigdecimal/bigdecimal.js"></script>
	<script type="text/javascript" src="${_jsPath}/pages/shopingCart.js"></script>
</body>
</html>