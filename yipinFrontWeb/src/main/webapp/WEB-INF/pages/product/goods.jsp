<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<title>选择商品</title>
	<jsp:include page="/WEB-INF/pages/include/common.jsp"></jsp:include>
	<!--page css-->
	<link rel="stylesheet" href="${_cssPath}/pages/goods.css" />
</head>
<body>
	<!--头部导航 start-->
	 <jsp:include page="/WEB-INF/pages/include/head.jsp"></jsp:include>
	<!--头部导航 end-->
	<!--内容部分 start-->
	<div class="w_norm">
		<!--商品 start-->
		<div class="goods">
			<div class="cf goods_hd">
				<span class="fn_right">当前位置：<a href="${_domain}">首页</a><span>></span><a href="${_ctxPath}/item-${brand.brandId}.htm">${brand.name}</a><span>></span>购买页面</span>
				<span class="fn_left">请选择您需要的商品规格及数量（可多选）</span>
			</div>
				<c:if test="${isEnable}">
					<p class="red p10">预售商品预计发货日期：<fmt:formatDate value='${sale.preSelltime}' pattern='yyyy年MM月dd日' /></p>
				</c:if>
			 	<c:if test="${not isEnable}">
					<p class="red p10"></p>
				</c:if> 
			<div class="goods_bd">
				<ul class="goods_list cf">
				<c:if test="${!empty products}">
					 <c:forEach items="${products}" var="product" varStatus="state" >
						<c:if test="${product.productId == productId }">
							<li data-id="${product.productSkuId}" data-sellerId="${product.userId }" data-len="${product.inventory}" class="cur">
						 	    <c:if test="${product.inventory gt 0}"> 
									<div class="goods_img">
										<a href="javascript:" title="商品" class="J_img">
										<img src="<yp:thumbImage originalPath="${product.imageUrls}" imageType="t83"></yp:thumbImage>" alt="${product.name}"/>
										</a>
									</div>
									<p class="goods_price"><span class="fn_right">库存：${product.inventory}件</span>一品价：<span class="red">￥${product.unitPrice }</span></p>
									<p class="goods_name">${product.name}</p>
									<div class="len_box">
										<span class="f16">购买数量</span><a href="javascript:" class="less">-</a><input type="text" class="len_input" value="1"/><a href="javascript:" class="more">+</a>
									</div>
								</c:if>				
								<c:if test="${product.inventory le 0}">
									<div class="goods_img relative">
										<a href="javascript:" title="商品" class="J_img"><img src="<yp:thumbImage originalPath="${product.imageUrls}" imageType="t83"></yp:thumbImage>" alt="${product.name}"/></a>
										<img src="${_imagesPath }/goods_end.png" alt="" class="end_bg"/>
									</div>
									<p class="goods_name">${product.name}</p>
								</c:if> 
							</li>
						</c:if>
						
						 <c:if test="${product.productId != productId }">
						 	<li data-id="${product.productSkuId}" data-sellerId="${product.userId }" data-len="${product.inventory}">
						 	    <c:if test="${product.inventory gt 0}"> 
									<div class="goods_img">
										<a href="javascript:" title="商品" class="J_img">
										<img src="<yp:thumbImage originalPath="${product.imageUrls}" imageType="t83"></yp:thumbImage>" alt="${product.name}"/>
										</a>
									</div>
									<p class="goods_price"><span class="fn_right">库存：${product.inventory}件</span>一品价：<span class="red">￥${product.unitPrice }</span></p>
									<p class="goods_name">${product.name}</p>
									<div class="len_box">
										<span class="f16">购买数量</span><a href="javascript:" class="less">-</a><input type="text" class="len_input" value="0"/><a href="javascript:" class="more">+</a>
									</div>
								</c:if>
								<c:if test="${product.inventory le 0}">
									<div class="goods_img relative">
										<a href="javascript:" title="商品" class="J_img"><img src="<yp:thumbImage originalPath="${product.imageUrls}" imageType="t83"></yp:thumbImage>" alt="${product.name}"/></a>
										<img src="${_imagesPath }/goods_end.png" alt="" class="end_bg"/>
									</div>
									<p class="goods_price"><span class="fn_right">库存：${product.inventory}件</span>一品价：<span class="red">￥${product.unitPrice }</span></p>
									<p class="goods_name">${product.name}</p>
								</c:if> 	
							</li>			
						</c:if>
					</c:forEach>
				</c:if>
				</ul>
				<div class="clearing J_clearing">
					<p class="already">您选择的商品：</p>
					<div class="J_goodsMsg"></div>
				</div>
			</div>
			<div class="continue cf J_continue">
				<div class="fn_right">
		             <span class="c999">下一步：量身定制您的配送方案</span><a href="javascript:" class="continueBtn">继续</a>
				</div>
				<input type="hidden" id="_ctxPath" value="${_ctxPath}" >
			</div>
			<div class="state cf">
				<ul>
					<li>
						<dl>
							<dt>购物保障</dt>
							<dd>我们郑重承诺您在“一城一品”所购商品均具有正品保障，并且均为经过严格筛选高品质商品</dd>
						</dl>
					</li>
					<li>
						<dl>
							<dt>关于快递</dt>
							<dd>我们默认圆通快递，如您有特定要求请联系“一城一品”专业客服，我们将竭诚为您服务，保证商品快速、安全到达目的地</dd>
						</dl>
					</li>
					<li>
						<dl>
							<dt>关于发货</dt>
							<dd>每天上午11：00前下单商品将于当天发货，11:00以后的订单将于次日发货(预售商品将根据活动规则相应调整）</dd>
						</dl>
					</li>
					<li>
						<dl>
							<dt>色差说明</dt>
							<dd>“一城一品”所有商品均属实物拍摄，详尽描述商品属性及形态，尽最大可能将商品如实呈现，如由于灯光、显示器及其他客观原因导致少许色彩偏差，请您谅解</dd>
						</dl>
					</li>
				</ul>
			</div>
		</div>
		<!--商品 end-->
	</div>
	<!--内容部分 end-->
	<!--底部 start-->
		<%@include file="/WEB-INF/pages/include/foot.jsp"%>
	<!--底部 end-->
	<!--page js-->
	<script type="text/javascript" src="${_jsPath }/pages/goods.js"></script>
</body>
</html>