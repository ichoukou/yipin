<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<%@page import="com.ytoxl.module.yipin.base.dataobject.ProductSku"%>
<c:set var="pend" value="<%=ProductSku.SALE_STATUS_PEND %>"></c:set>
<c:set var="on" value="<%=ProductSku.SALE_STATUS_ON %>"></c:set>
<c:set var="stockout" value="<%=ProductSku.SALE_STATUS_STOCKOUT %>"></c:set>
<c:set var="expired" value="<%=ProductSku.SALE_STATUS_EXPIRED %>"></c:set>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<c:if test="${empty seoTitle }">
   	<title>产品详情</title>
	</c:if>
	<c:if test="${!empty seoTitle }">
	<title>${seoTitle}</title>
	</c:if>
	<c:if test="${empty seoKeyWords }">
   	<meta name="keywords" content="" >
	</c:if>
	<c:if test="${!empty seoKeyWords }">
	<meta name="keywords" content="${seoKeyWords}">
	</c:if>
	<c:if test="${empty seoDescription }">
   	<meta name="description" content="" >
	</c:if>
	<c:if test="${!empty seoDescription}">
	<meta name="description" content="${seoDescription}">
	</c:if>
	<jsp:include page="/WEB-INF/pages/include/common.jsp"></jsp:include>
	<!--page css-->
	<link rel="stylesheet" type="text/css" href="${_cssPath}/pages/goodsDetail.css" media="all" />
</head>
<body>
	<!--头部 start-->
	<jsp:include page="/WEB-INF/pages/include/head.jsp"></jsp:include>
	<!--头部 end-->
	<!--详情部分 start-->
	<div class="w_norm">
		<!--面包屑 start-->
		<div class="crumbs">
			<a href="${_domain }" title="首页">首页</a><span>></span>
			<c:if test="${not empty product.zone}">
			<a href="${_ctxPath }/listproduct-${type}-0-${product.zone.zoneId}-${url_sort_100}-${url_dir_100000}-0-0.htm" title="${product.zone.name }">${product.zone.name }</a><span>></span>
			</c:if>
			${yipin:cutString(product.name,15)}
		</div>
		<!--面包屑 end-->
		<!--商品信息 start-->
		<div class="goodsDetail cf">
			<!--左侧轮播 -->
			<div class="goods_left" id="goods_left">
				<div class="slide_box">
					<div class="slides_container">
						<c:forEach items="${product.imageList }" var="image">
						<img src="${_filePath }${image }" alt="" width="510" height="510"/>
						</c:forEach>
					</div>
					<a href="javascript:void(0)" class="prev"></a>
					<a href="javascript:void(0)" class="next"></a>
				</div>
				<ul class="pagination cf">  
					<c:forEach items="${product.imageList }" var="image" varStatus="status">
					<li <c:if test="${status.index eq 0 }">class="current"</c:if>>
					<a href="#${status.index }"><img src="<yp:thumbImage originalPath='${image}' imageType='t87'></yp:thumbImage>" alt="" width="100px" height="100px"/></a>
					</li>
					</c:forEach>
				</ul>
			</div>
			<!--右侧信息 -->
			<div class="goods_right">
				<form action="${_ctxPath}/order/order-getOrderInfo.htm" method="post" id="goodsForm">
					<input type="hidden" name="orders[0].items[0].productSkuId" value="${product.productSku.productSkuId }" id="productSkuId"/>
					<input type="hidden" name="orders[0].items[0].num" value="1"/>
					<h3>${product.name }</h3>
					<div class="p25 goods_price">
						一品价：<span class="red"><i class="f14">￥</i><span class="J_price">${product.productSku.unitPrice }</span></span> <del>原价：<span class="f12">￥</span><span class="J_oPrice">${product.productSku.marketPrice }</span></del>节省：<span class="f12">￥</span><span class="J_js">${product.productSku.marketPrice - product.productSku.unitPrice }</span>
					</div>
					<div class="p25 goods_msg">
						<p class="goods_wi">重量：<span class="J_wi">${product.productSku.skuWeight }</span>g</p>
						<p class="goods_dress">产地：
						<c:forEach items="${product.propsMap['area'] }" var="prop" varStatus="status">
						<c:if test="${status.index != 0 }"><span>|</span></c:if>${prop.name }
						</c:forEach>
						</p>
						<div class="cf goods_sku">
							<span>规格：</span>
							<ul class="cf J_sku">
								<li class="cur" data-productSkuId="${product.productSku.productSkuId }" data-weight="${product.productSku.skuWeight }" data-price="${product.productSku.unitPrice }" data-oPrice="${product.productSku.marketPrice }" data-num="${product.productSku.inventory }">
								<span>${product.productSku.skuSpecification }</span> 
								<img src="${_imagesPath }/btn/gou.png" alt="" class="gou"/>
								</li>
							</ul>
						</div>
						<p class="goods_fy">运费：<span class="J_yf">${product.productSku.postage }</span>元</p>
						<p class="goods_yh">优惠：<span class="J_yh">${product.productSku.postage }</span>元<span class="cf90">（运费优惠）</span></p>
						<p class="goods_time">
							<span class="J-countdown"
								data-config="{
								'startTime':'<fmt:formatDate value="${product.productSku.saleBeginTime }" type="both"/>',
								'endTime':'<fmt:formatDate value="${product.productSku.saleEndTime }" type="both"/>',
								'nowTime':'<%=System.currentTimeMillis()/1000 %>',
								'startTips':'距离开抢时间：',
								'endTips':'剩余时间：',
								'stopTips':'<span>商品抢购已过期</span>',
								'timeStampTow':true
								}">
							</span>
						</p>
						<div class="cf goods_form" id="gq">
						<c:choose>
						<c:when test="${product.productSku.saleStatus eq pend}">
							<a href="javascript:" class="grayBtn">即将开抢</a>
						</c:when>
						<c:when test="${product.productSku.saleStatus eq stockout }">
							<a href="javascript:" class="grayBtn">已抢光</a>
						</c:when>
						<c:when test="${product.productSku.saleStatus eq on }">
							<a href="javascript:" class="redBtn J_submitQg">立即抢购</a>
						</c:when>
						<c:when test="${product.productSku.saleStatus eq expired }">
							<a href="javascript:" class="grayBtn">已过期</a>
						</c:when>
						</c:choose>
						</div>
						<c:if test="${not empty product.recommendedReason }">
						<div class="goods_tj">
							<span class="red">推荐理由：</span>${product.recommendedReason }
						</div>
						</c:if>
					</div>
				</form>
			</div>
		</div>
		<!--商品信息 end-->
		<!--悬浮条 start-->
		<div class="suspend">
			<ul class="fl J_tab">
				<li><a href="#product_details" class="cur">产品详情</a></li>
				<li><a href="#service">服务保障</a></li>
			</ul>
			<div class="cf fl pr_msg">
				<img src="<yp:thumbImage originalPath='${product.defaultImage}' imageType='t87'></yp:thumbImage>" alt="" class="fl"/>
				<div class="fl">
					<p><b>${yipin:cutString(product.name,15)}</b></p>
					<p>一品价：<span class="red">￥</span><b class="red J_price">${product.productSku.unitPrice }</b>规格：<span class="J_gz">${product.productSku.skuSpecification }</span> 重量：<span class="J_wi">${product.productSku.skuWeight }</span>g</p>
				</div>
			</div>
			<div class="fl pr_time">
				<span class="J-countdown"
					data-config="{
					'startTime':'<fmt:formatDate value="${product.productSku.saleBeginTime }" type="both"/>',
					'endTime':'<fmt:formatDate value="${product.productSku.saleEndTime }" type="both"/>',
					'nowTime':'<%=System.currentTimeMillis()/1000 %>',
					'startTips':'距离开抢时间：',
					'endTips':'剩余时间：',
					'stopTips':'<span>商品抢购已过期</span>',
					'timeStampTow':true
					}">
				</span>
			</div>
			<div class="fr pr_btn" id="gq1">
			<c:choose>
			<c:when test="${product.productSku.saleStatus eq pend}">
				<a href="javascript:" class="grayBtn1">即将开抢</a>
			</c:when>
			<c:when test="${product.productSku.saleStatus eq stockout }">
				<a href="javascript:" class="grayBtn1">已抢光</a>
			</c:when>
			<c:when test="${product.productSku.saleStatus eq on }">
				<a href="javascript:" class="redBtn1 J_submitQg">立即抢购</a>
			</c:when>
			<c:when test="${product.productSku.saleStatus eq expired }">
				<a href="javascript:" class="grayBtn1">已过期</a>
			</c:when>
			</c:choose>
			</div>
		</div>
		<!--悬浮条 end-->
		<!--产品详情和服务保障 start-->
		<div class="product_details" id="product_details">
			<div class="hd" id="suspendHd">
				<a href="#product_details" class="cur">产品详情</a>
				<a href="#service">服务保障</a>
			</div>
			<div class="bd">
				<div class="product" id="product">
					<h3>产品详情</h3>
					<div class="product_list">
						${product.description }
					</div>
				</div>
				<div class="service" id="service">
					<h3>服务保障</h3>
					<p>一城一品所售的商品均为正规品牌，供应商类型包括品牌生产商、品牌授权总代理商、品牌授权总经销商、品牌分公司、品牌分支机构及国际品牌驻中国的办事处。如您对货品有任何不满需要退换货，具体信息请查看 <a href="${_domain }/help/help-100008.htm" target="_blank">${_domain }/help/help-100008.htm</a></p>
					<p>关于快递About the Courier</p>
					<p>我们默认使用圆通快递，如发其它快递、EMS，请联系我们在线客服！</p>
					<p>关于发货About Delivery</p>
					<p>每日下午5:00以前的顾客，均可以当天发货，5:00以后的顾客，次日发货。</p>
					<p>色差说明Component Description</p>
					<p>因拍摄灯光及不同显示器色差等问题可能造成商品图片与实物有色差,一切以实物为准。</p>
				</div>
			</div>
		</div>
		<!--产品详情和服务保障 end-->
	</div>
	<!--底部 start-->
	<!--悬浮购物车 s -->
	<%@include file="/WEB-INF/pages/order/miniCart.jsp"%>
	<!--悬浮购物车 e -->
	<%@include file="/WEB-INF/pages/include/foot.jsp"%>
	<!--底部 end-->
	<!--js-->
	<!--page js-->
	<script type="text/javascript" src="${_jsPath}/pages/goodsDetail.js"></script>
</body>
</html>