<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:choose>
<c:when test="${not empty orders }">
<div class="m_mcListBox">
	<div class="m_mcTop cf">
		<a data-tmc="topCart" class="m_mcCartBtn" href="${_ctxPath}/order/order-showCart.htm" title="全屏查看" target="_blank">全屏查看</a>
		<div class="m_mcChk">
			<label><input type="checkbox" checked="" class="m_mcElectCart">全选</label>
		</div>
	</div>
	<c:set value="0" var="totalNum"/>
	<c:set  value="0" var="totalAmount"/>
	<c:forEach items="${orders }" var="orderHead">
		<c:set value="0" var="orderTotalPrice"/>
		<c:forEach items="${orderHead.items }" var="orderItem">
			<c:set value="${orderTotalPrice + orderItem.unitPrice*orderItem.num}" var="orderTotalPrice"/>
		</c:forEach>
	<div class="m_mcBundle">
		<div class="m_mcBundleHeader cf">
			<div class="m_mcChk"><input class="m_mcElectBundle" type="checkbox" checked=""></div>
			<div class="m_mcTitle elip"><span title="${orderHead.deliveryAddress }">发货地：${orderHead.deliveryAddress }</span></div>
			<div class="m_mcCost"><strong class="m_hdPrice J_bundle">${orderTotalPrice}</strong></div>
		</div>
		<c:forEach items="${orderHead.items }" var="orderItem">
		<c:set value="${totalNum + orderItem.num}" var="totalNum" />
		<c:set value="${totalAmount + orderItem.unitPrice*orderItem.num}" var="totalAmount" />
		<div class="m_mcBundleList">
			<div class="cf m_mcOrder" data-order="${orderItem.productSku.inventory}" data-product="${orderItem.productSku.productSkuId }" data-price="${orderItem.unitPrice  }">
				<div class="m_mcChk"><input class="m_mcElect" type="checkbox" checked=""></div>
				<div class="m_mcItem">
					<a class="m_mcPic" href="${_ctxPath}/item-${orderItem.productSku.productSkuId }-0-0.htm" title="${orderItem.productSku.product.name}" target="_blank">
						<img src="<yp:thumbImage originalPath="${orderItem.productSku.product.coverPicture}" imageType="t84"></yp:thumbImage>" alt="${orderItem.productSku.product.name}" width="50" height="50"/>
					</a>
				</div>
				<div class="m_mcSku">
					<p class="elip" title="${orderItem.productSku.product.name}"><a href="${_ctxPath}/item-${orderItem.productSku.productSkuId }-0-0.htm" target="_blank">${orderItem.productSku.product.name}</a></p>
					<p class="elip" title="规格:${orderItem.productSku.skuSpecification }">${orderItem.productSku.skuSpecification}</p>
				</div>
				<div class="m_mcAmount">
					<a href="javascript:;" class="m_mcMinus" hidefocus="true">-</a>
					<span class="m_mcQuantity">${orderItem.num }</span>
					<a href="javascript:;" class="m_mcPlus" hidefocus="true">+</a>
				</div>
				<div class="m_mcCost">
					<a href="javascript:;" class="m_mcDel" title="删除"></a>
					<strong class="m_mcPrice">${orderItem.unitPrice *  orderItem.num}</strong>
				</div>
			</div>
		</div>
		</c:forEach>
	</div>
	</c:forEach>
</div>
<div class="m_mcCashier">
	<a href="${_ctxPath}/order/order-showCart.htm" class="fr g_cash" target="_blank">去结算</a>
	<span>共计<strong class="J_item_num">${totalNum }</strong>件</span>合计￥<strong class="J_total">${totalAmount}</strong>
</div>
</c:when>
<c:otherwise>
<div class="nogoods">购物车中还没有商品，赶紧选购吧！</div>
</c:otherwise>
</c:choose>