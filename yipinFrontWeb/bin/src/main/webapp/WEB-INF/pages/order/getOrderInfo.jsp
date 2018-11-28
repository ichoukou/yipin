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
	<input type="hidden" name="params.orderType" value="1"/>
	<input type="hidden" name="params.hasInvoice" value="1">
	<input type="hidden" name="orders[0].orderInvoiceAddress.userAddressId">
	<s:token/>
	<div class="w_norm order_content">
		<!--商品清单 start-->
		<div class="goods_inventory">
			<h2>商品清单<span>您可以逐项选择，我们将为您定制专属配送方式</span></h2>
			<table width="100%" class="ggods_table" id="goodsTable">
				<thead>
					<tr>
						<th width="485px" class="first">商品名称</th>
						<th width="130px">单品价格</th>
						<th width="160px">购买数量</th>
						<th width="140px">金额</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="totalPrice" value="0"/>
					<c:set var="totalNum" value="0"/>
					<c:set var="totalWeight" value="0"/>
					<c:forEach items="${orders}" var="orderHead">
						<c:forEach items="${orderHead.items }" var="orderItem" varStatus="_product">
							<c:set value="${totalPrice + orderItem.unitPrice*orderItem.num}" var="totalPrice" />
							<c:set value="${totalNum + orderItem.num}" var="totalNum" />
							<c:set value="${totalWeight + orderItem.productSku.skuWeight*orderItem.num}" var="totalWeight"/>
							<tr data-jifen="<fmt:formatNumber value="${orderItem.unitPrice}" pattern="#"/>" 
								data-kucun="${orderItem.productSku.inventory}" data-price="${orderItem.productSku.unitPrice}" 
								data-product="${orderItem.productSku.productSkuId}" data-sku="${orderItem.productSku.skuWeight }" id="goods${orderItem.productSku.productSkuId}">
								<td class="frist">
									<div class="cf">
										<div class="goods_img">
											<input type="hidden" name="orders[0].items[${_product.index}].productSkuId" value="${orderItem.productSkuId }" />
											<img src="<yp:thumbImage originalPath="${orderItem.productSku.product.imageUrls}" imageType="t83"></yp:thumbImage>" alt="${orderItem.productSku.product.name}" width="76" height="76"/>
										</div>
										<div class="goods_Msg">
											<p class="goods_name">${orderItem.productSku.product.name}</p>
											<p class="c999">
												<c:if test="${not empty orderItem.productSku.skuColor}">
												颜色：${orderItem.productSku.skuColor}
												</c:if>
												<c:if test="${not empty orderItem.productSku.skuWeight && orderItem.productSku.skuWeight != '0'}">
												<span style="margin-left:5px;">重量：${orderItem.productSku.skuWeight }g</span>
												</c:if>
											</p>
										</div>
									</div>
								</td>
								<td><span class="red">￥${orderItem.unitPrice}</span></td>
								<td>
									<div class="relative">
										<a href="javascript:" class="less">-</a><input type="text" class="len_input" name="orders[0].items[${_product.index}].num" value="${orderItem.num}" /><a href="javascript:" class="more">+</a>
										<label class="red J_outTip outTip">${orderItem.productSku.errorMsg}</label>
									</div>
								</td>
								<td><span class="red">￥</span><span class="red J-allPrice">${orderItem.unitPrice*orderItem.num}</span></td>
								<td ><a href="javascript:" class="J_delete delete">删除</a></td>
							</tr>
						</c:forEach>
					</c:forEach>
					<tr class="last">
						<td>&nbsp;</td>
						<td>合计</td>
						<td><span class="W100 J_shuliang">${totalNum}</span></td>
						<td><span class="red">￥</span><span class="red J_jine">${totalPrice}</span></td>
						<td >&nbsp;</td>
					</tr>
				</tbody>
			</table>
		</div>
		<!--商品清单 end-->
		<!--收货地址 start-->
		<div class="profile">
			<h2>收货模式<span>请选择您的商品用途及收货地址</span></h2>
			<ul class="profile_hd cf J_profile_hd">
				<li class="cur">自用模式<span>为您自己的生活品质加分，请选择此选项<br>(此选项只可选择单一送货地址)</span></li>
				<li class="last">送礼模式<span>为您的亲朋好友生活品质加分，请选择此选项<br>(此选项可按需求对多个地址分配商品)</span></li>
			</ul>
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
			<div class="deliv_adr hide J_myDress give_adr" id="giveAdr">
				<div class="has_adr">
					<ul>
						<c:forEach items="${listUserAddress}" var="userAddress">
						<li class="adr_list" data-address="${userAddress.userAddressId}">
							<div class="adr_tab">
								<table width="100%">
									<tbody>
										<tr>
											<td width="830px">
												${escape:escapeHtml(userAddress.receiveAddress)}  (${userAddress.receiverName } 收)  ${userAddress.mobile }
											</td>
											<td class="right">
												<div class="right_div">
													<a class="addGoodBtn J_addBtn" href="javascript:">添加商品</a>
													<p class="J_editBox hide"><span class="bg_num"></span><a class="edit J_edit" title="">取消</a></p>
												</div>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</li>
						</c:forEach>
					</ul>
				</div>
				<div class="btn_adr">
					<span class="create_adr J_adress_2">添加新地址</span>
					<a href="javascript:" class="J_enter enterBtn">确认</a>
				</div>
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
				<div class="deliv_adr" id="invoiceAdr">
					<div class="has_adr">
						<ul class="invoiceDress">
							<c:forEach items="${listUserAddress}" var="userAddress">
							<li class="adr_list <c:if test="${userAddress.isDefault eq '1' }">adr_listCur</c:if>" data-address="${userAddress.userAddressId }">
								<a href="javascript:" title="寄送到此地址" class="J_hoverDress">寄送到此地址</a>
								${escape:escapeHtml(userAddress.receiveAddress)}  (${userAddress.receiverName } 收)  ${userAddress.mobile }
								<c:if test="${userAddress.isDefault eq '1' }">
									<span class="J_defaultDress" style="margin-left:10px;">(默认地址)</span>
								</c:if>
							</li>
							</c:forEach>
						</ul>
					</div>
					<div class="btn_adr"><span class="create_adr J_adress_3">添加新地址</span></div>
					<div class="newadr_wp"><div class="new_adr"></div></div>
				</div>
				<div class="invoice_Msg">
					<div>发票类型：</div>
					<div><a href="javascript:" class="orderBtn orderBtn_cur">普通发票</a></div>
					<div>发票抬头：</div>
					<div class="J_rise personBtn">
						<a href="javascript:" class="orderBtn orderBtn_cur">个人</a>
						<a href="javascript:" class="orderBtn m22">单位</a></div>
					<div class="hide J_riseBox"><font color="#e5372e">*</font>单位名称：
						<input type="text" class="input-text" id="company_name" name="orders[0].invoiceTitle" maxlength="50"/>
						<div class="invoice_tip">
						温馨提示：您所填写的所有内容都将被系统自动打印到发票上，所以请不要填写和发票抬头无关的信息。
						<br />受品牌商客观因素影响，发票可能会发生延迟；如果延迟请见谅。
					</div>
					</div>
				</div>
			</div>
		</div>
		<!--发票信息 end-->
		<!--结算信息 start-->
		<div class="cf clearing_msg">
			<div class="fn_right">
				<p>共<span class="shuliang">${totalNum}</span>件</p>
				<p>金额总计：<span class="allPrice">${totalPrice}</span>元</p>
				<p>运费：
						<c:choose>
							<c:when test="${totalWeight <= 1000}"><span class="freight">10.00</span></c:when>
							<c:otherwise><span class="freight">20.00</span></c:otherwise>
						</c:choose>
					元</p>
				<p>运费优惠：
						<c:choose>
							<c:when test="${totalWeight <= 1000}"><span class="freightFire">-10.00</span></c:when>
							<c:otherwise><span class="freightFire">-20.00</span></c:otherwise>
						</c:choose>
					元</p>
				<p>积分：<span class="J-jifen">${yipin:computerPoint(totalPrice)}</span></p>
				<p>应付金额：<span class="price">${totalPrice}</span><span class="rmb">元</span></p>
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
	<!--内容部分 end-->
	<!---送礼层-->
	<div id="give" class="hide">
		<div class="goods_list">
			<table width="100%" class="gift_table">
				<tbody>
					<c:forEach items="${orders[0].items }" var="orderItem">
					<tr class="giveGoods${orderItem.productSku.productSkuId}" data-product="${orderItem.productSkuId}" data-sku="${orderItem.productSku.skuWeight }">
						<td class="table_a">
							<div class="cf">
								<div class="goods_img">
									<img src="<yp:thumbImage originalPath="${orderItem.productSku.product.imageUrls}" imageType="t83"></yp:thumbImage>" alt="${orderItem.productSku.product.name}" width="76" height="76"/>
								</div>
								<div class="goods_Msg">
									<p>${orderItem.productSku.product.name}</p>
									<p class="c999">
										<c:if test="${not empty orderItem.productSku.skuColor}">
											颜色：${orderItem.productSku.skuColor}
										</c:if>
										<c:if test="${not empty orderItem.productSku.skuWeight && orderItem.productSku.skuWeight != '0'}">
										<span style="margin-left:5px;">重量：${orderItem.productSku.skuWeight }g</span>
										</c:if>
									</p>
								</div>
							</div>
						</td>
						<td class="table_b"></td>
						<td class="table_c J_changeLen">
							<div>
								<a href="javascript:" class="less">-</a><input type="text" class="len_input" value="0"/><a href="javascript:" class="more">+</a>
							</div>
							<p></p>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<!--地址层-->
	<%@include file="/WEB-INF/pages/include/foot.jsp"%>
</body>
	<script src="${_jsPath}/plugin/select/linkage_sel.js"></script>
	<script src="${_jsPath}/plugin/bigdecimal/mathcontext.js"></script>
	<script src="${_jsPath}/plugin/bigdecimal/bigdecimal.js"></script>
	<script type="text/javascript" src="${_jsPath }/pages/order.js"></script>
</html>