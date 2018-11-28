<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<title>结算</title>
	<jsp:include page="/WEB-INF/pages/include/common.jsp"></jsp:include>
	<link rel="stylesheet" href="${_cssPath}/pages/order.css" />
</head>
<body>
	<jsp:include page="/WEB-INF/pages/include/head.jsp"></jsp:include>
	<!--内容部分 start-->
	<form action="${_ctxPath}/order/order-submitOrder.htm" method="post" id="cartform">
	<input type="hidden" name="params.hasInvoice" value="1">
	<input type="hidden" name="params.userAddressId">
	<s:token/>
	<div class="w_norm order_content">
		<div class="order_lc"><img src="${_imagesPath}/navigation/lc.png" alt="" /></div>
		<!--收货地址 start-->
		<div class="profile">
			<h2>收货地址<span>请选择您的收货地址</span></h2>
			<div class="deliv_adr J_myDress J_myDressCur" id="myAdr">
				<div class="has_adr">
					<ul>
						<c:forEach items="${listUserAddress}" var="userAddress">
						<li class="adr_list <c:if test="${userAddress.isDefault eq '1' }">adr_listCur</c:if>"  data-address="${userAddress.userAddressId }">
							<a href="javascript:" title="寄送到此地址" class="J_hoverDress">寄送到此地址</a>
							${escape:escapeHtml(userAddress.receiveAddress)}  (${userAddress.receiverName } 收)  ${userAddress.mobile }
							<c:if test="${userAddress.isDefault eq '1' }">
							<span class="J_defaultDress" style="margin-left:10px;">(默认地址)</span>
							</c:if>
						</li>
						</c:forEach>
					</ul>
				</div>
				<div class="btn_adr"><span class="create_adr J_adress_1">添加新地址</span></div>
				<div class="newadr_wp"><div class="new_adr"></div></div>
			</div>
		</div>
		<!--收货地址 end-->
		<!--支付宝 start-->
		<div class="pay_type">
			<h2>支付方式</h2>
			<div class="p22 zfnBtn">
				<a href="javascript:" class="orderBtn orderBtn_cur">
					<input type="hidden" name="params.payType" value="1"/>支付宝
				</a>
			</div>
		</div>
		<!--支付方式 end-->
		<!--配送方式 start-->
		<div class="delivery_type">
			<h2>配送方式</h2>
			<div class="p22"><a href="javascript:" class="orderBtn orderBtn_cur">免费配送</a></div>
		</div>
		<!--配送方式 end-->
		<!--发票信息 start-->
		<div class="invoice">
			<h2>发票信息</h2>
			<div class="p22 J_invoiceTab cf">
				<a href="javascript:" class="orderBtn orderBtn_cur fn_left">不开发票</a>
				<a href="javascript:" class="orderBtn m22 fn_left mx">需要发票<br /><span>(商品明细)</span></a>
			</div>
			<div class="hide J_invoice">
				<div class="join">发票说明：1.发票类型，本网站商家开具的发票类型统一为普通发票；
<span style="padding-left:60px;display:block;">2.寄送时间，如发票寄送地址与收货地址相同，我们尽量将发票一起寄出，如遇发票送达时间晚于货物送达时间，敬请谅解。</span></div>
				<div class="invoice_Msg">
					<div>发票类型：</div>
					<div><a href="javascript:" class="orderBtn orderBtn_cur">普通发票</a></div>
					<div>发票抬头：</div>
					<div class="J_rise personBtn">
						<a href="javascript:" class="orderBtn orderBtn_cur">个人</a>
						<a href="javascript:" class="orderBtn m22">单位</a></div>
					<div class="hide J_riseBox"><font color="#e5372e">*</font>单位名称：
						<input type="text" class="input-text" id="company_name" name="params.invoiceTitle" maxlength="50"/>
						<div class="invoice_tip">
						温馨提示：您所填写的所有内容都将被系统自动打印到发票上，所以请不要填写和发票抬头无关的信息。
						<br />受品牌商客观因素影响，发票可能会发生延迟；如果延迟请见谅。
					</div>
					</div>
				</div>
			</div>
		</div>
		<!--发票信息 end-->
		<!--商品清单 start-->
		<div class="goods_inventory">
			<h2>商品清单<span><c:if test="${!(orderTypes[2] eq orders[0].orderType)}"><a href="${_ctxPath}/order/order-showCart.htm">返回购物车</a></c:if></span></h2>
			<table class="th_table">
				<thead>
					<tr>
						<th width="480" class="th_a">商品</th>
						<th width="90">规格</th>
						<th width="90">重量</th>
						<th width="130">一品价</th>
						<th width="110">数量</th>
						<th>小计</th>
					</tr>
				</thead>
			</table>
			<c:set var="totalWeight" value="0"/>
			<c:set  value="0" var="totalAmount"/>
			<c:set var="totalNum" value="0"/>
			<c:set var="totalPostAmount" value="0"/>
			<c:forEach items="${orders}" var="orderHead" varStatus="loopOrderParam">
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
				发货地：${orderHead.deliveryAddress}
				<c:choose>
					<c:when test="${orderTypes[0] eq orderHead.orderType}">（普通订单）</c:when>
					<c:when test="${orderTypes[1] eq orderHead.orderType}">
						<span class="red">（预售订单）</span>
					</c:when>
					<c:when test="${orderTypes[2] eq orderHead.orderType}">
						<span class="red">（抢购订单）</span><span>剩余时间：</span><span  class="J_countdown" data-config="{'endTime':'2014-1-15 9:59:59','nowTime':'1389263419','stopTips':'<span>活动结束谢谢参与！</span>','timeStampTow':true}"></span>
					</c:when>
				</c:choose>
			</div>
			<table class="order_table">
				<tbody>
					<c:forEach items="${orderHead.items }" var="orderItem" varStatus="loopItemsParam">
					<c:set value="${totalWeight + orderItem.productSku.skuWeight*orderItem.num}" var="totalWeight"/>
					<c:set value="${totalAmount + orderItem.unitPrice*orderItem.num}" var="totalAmount" />
					<c:set value="${totalNum + orderItem.num}" var="totalNum" />
					<tr data-product="${orderItem.productSku.productSkuId }" data-num="${orderItem.num }" data-name="${orderItem.productSku.product.name}">
						<td width="560" class="p20">
							<a class="m_mcPic" href="${_ctxPath}/item-${orderItem.productSku.productSkuId }-0-0.htm" title="${orderItem.productSku.product.name}" target="_blank">
							<img src="<yp:thumbImage originalPath="${orderItem.productSku.product.coverPicture}" imageType="t89"></yp:thumbImage>" alt="${orderItem.productSku.product.name}" width="76" height="76"/>
							<span>${yipin:cutString(orderItem.productSku.product.name,25)}</span>
							</a>
							<input type="hidden" name="orders[${loopOrderParam.index}].items[${loopItemsParam.index }].productSkuId" value="${orderItem.productSkuId}"/>
							<input type="hidden" name="orders[${loopOrderParam.index}].items[${loopItemsParam.index }].num" value="${orderItem.num}"/>
						</td>
						<td width="90"><span class="f14">${orderItem.productSku.skuSpecification}</span></td>
						<td width="90"><span class="f14">${orderItem.productSku.skuWeight }g</span></td>
						<td width="130"><span class="f14 red">${orderItem.unitPrice}</span></td>
						<td width="110">
							<div class="rel">
							<c:choose>
								<c:when test="${orderItem.productSku.inventory == 0}"><label class="red J_outTip">商品已售罄</label></c:when>
								<c:when test="${orderItem.productSku.inventory < orderItem.num}"><label class="red J_outTip">库存不足</label></c:when>
								<c:otherwise><span class="f14">${orderItem.num}</span></c:otherwise>
							</c:choose>
							</div>
						</td>
						<td><span class="f14 red">￥${orderItem.unitPrice * orderItem.num}</span></td>
					</tr>
					</c:forEach>
					<tr class="last">
						<td colspan="6">
							<div class="fl bz_box">
								<a href="javascript:" class="J_write">填写订单备注</a>
								<div class="J_writeBox hide">
									订单备注：<input type="text" class="input-text J_bzInput bzInput" name="orders[${loopOrderParam.index}].orderRemark" maxlength="50" data-default="选填：对本次交易的补充说明（建议填写已经和卖家达成一致的说明），最多50个字"/>
									<a href="javascript:" class="orderEnterBtn J_orderEnter">确定</a>
									<a href="javascript:" class="orderCancelBtn J_orderCancel">取消</a>
								</div>
								<div class="J_changeBox hide">
									<a href="javascript:" class="J_edit">修改</a>
									订单备注：<span class="J_bz"></span>
								</div>
							</div>
							<div class="fl yz_box">
								运费：${postPrice }元<span class="M30">运费优惠：-${postPrice }元</span>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
			</c:forEach>
		</div>
		<div class="order_tip">
			<c:choose>
				<c:when test="${orderTypes[2] eq orders[0].orderType}">
				<p class="fr">此商品每账号限抢<i class="red">1</i>件，请在抢拍成功后尽快付款，可能会被其他客官抢走，若<i class="red">40</i>分钟内未付款，系统将自动取消您的抢购订单！</p>
				</c:when>
				<c:otherwise>
				<p class="fr"><span class="m20">共<i class="red">${totalNum}</i>件商品</span>选购商品来自不同的产地，一城一品保证由原产地发货，系统将为您拆分为多个订单！</p>
				<a href="${_ctxPath}/order/order-showCart.htm">返回购物车</a>
				</c:otherwise>
			</c:choose>
			
		</div>
		<!--商品清单 end-->
		<!--结算信息 start-->
		<div class="cf clearing_msg">
			<div class="fn_right">
				<p>共<span class="shuliang">${totalNum}</span>件商品</p>
				<p>商品总量：${totalWeight }克</p>
				<p>商品总价：<span class="allPrice">${totalAmount}</span>元</p>
				<p>运费：<span class="freight">${totalPostAmount }</span>元</p>
				<p>运费优惠：<span class="freightFire">-${totalPostAmount }</span>元</p>
				<p>获得积分：<span class="J-jifen">${yipin:computerPoint(totalAmount)}</span>分</p>
				<p class="t_line">应付总额：<i class="red">￥</i><span class="price">${totalAmount}</span><span class="rmb">元</span></p>
				<p class="red">请尽快支付款项，您未支付订单的商品可能被其他客官抢购一空！</p>
				<p><a href="javascript:;" class="ClearingBtn" id="ClearingBtn">提交订单</a></p>
			</div>
		</div>
		<!--结算信息 start-->
		<div class="hide" id="_hiddenFormData">
			<c:forEach items="${listUserAddress}" var="userAddress">
				<c:if test="${userAddress.isDefault eq '1' }">
				<input type='hidden' name='orders[0].addressItems[0].userAddressId' value='${userAddress.userAddressId }'/>
				</c:if>
			</c:forEach>
		</div>
	</div>
	</form>
	<%@include file="/WEB-INF/pages/include/foot.jsp"%>
	<%@include file="/WEB-INF/pages/include/static_param.jsp"%>
</body>
	<script src="${_jsPath}/plugin/select/linkage_sel.js"></script>
	<script type="text/javascript" src="${_jsPath }/pages/order.js"></script>
</html>