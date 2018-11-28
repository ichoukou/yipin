<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<title>我的订单</title>
	<!--css-->
	<jsp:include page="/WEB-INF/pages/include/common.jsp"></jsp:include>
	<!--page css-->
	<link rel="stylesheet" type="text/css" href="${_cssPath}/pages/ordData.css" media="all" />
	<style type="text/css">
		.loading{text-align:center;padding:20px 0;}
		.col4{float:left;width:125px;padding-left:10px;}
		.col5{float:left;width:180px;padding-left:10px;}
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
					<div class="uc_act">
						<select name="orderTypes" id="orderTypeId">
							<option value="0">全部类型订单</option>
							<c:forEach items="${orderTypes}" var="ss">
								<option value="${ss}" <c:if test="${ss eq orderPage.params.orderType}">selected = "selected"</c:if>>
									<spring:message code="order.source.${ss}"/>
								</option>
							</c:forEach>
						</select>
						<select name="queryTime" id="queryTimeId">
							<option value="${orderTimeTypes[0]}" <c:if test="${orderTimeTypes[0] eq orderPage.params.queryTime}">selected = "selected"</c:if>>全部时间的订单</option>
							<option value="${orderTimeTypes[1]}" <c:if test="${orderTimeTypes[1] eq orderPage.params.queryTime}">selected = "selected"</c:if>>最近一月的订单</option>
							<option value="${orderTimeTypes[2]}" <c:if test="${orderTimeTypes[2] eq orderPage.params.queryTime}">selected = "selected"</c:if>>一月之前的订单</option>
						</select>
						<select name="orderStatus" id="orderStatusId">
							<option value="">全部订单</option>
							<c:forEach items="${status}" var="ss">
								<option value="${ss}" <c:if test="${ss eq orderPage.params.status}">selected = "selected"</c:if>>
									<spring:message code="order.status.${ss}"/>
								</option>
							</c:forEach>
						</select>
					</div>
					我的订单 | <span class="f12">您可以在这里查看订单详细信息并且进行订单操作</span>
				</div>
				<c:if test="${not empty orderPage.result}">
				<div class="my_order">
					<div class="rank_wrap cf">
						<div class="page_nav" style="width:95px;">
							<span class="J_pagenum"><i></i>/<i></i></span>
							<span><a href="javascript:;" class="next"></a></span>
							<span><a href="javascript:;" class="prev"></a></span>
						</div>
						<div class="o_tips">【合并付款】、【批量确认收货】<a href="${_ctxPath}/help/help-100002.htm" target="_blank">如何操作？</a></div>
						<div class="rank">
							我的交易提醒：
							<a class="J_tradesuggest" href="javascript:;" data-status="${status[0]}"><span>待付款<var>(${orderPage.params.orderStatusNum4WaitPay})</var></span></a>
							<a class="J_tradesuggest" href="javascript:;" data-status="${status[2]}"><span>待确认收货<var>(${orderPage.params.orderStatusNum4WaitReceive})</var></span></a>
						</div>
					</div>
					<div class="data_show">
						<!-- 是否显示checkbox 待付款和已发货显示-->
						<c:set var="isDisplayCheckBox" value="${orderPage.params.status eq status[0] or orderPage.params.status eq status[2]}"/>
						<div class="data_tit cf">
							<div class="col4">
							<c:if test="${isDisplayCheckBox}">	
								<span><input type="checkbox" class="selectAll"/></span>
							</c:if>
								订单号
							</div>
							<div class="col5">下单时间</div>
							<div class="col2">订单类型</div>
							<div class="col4">订单状态</div>
							<div class="col2">数量</div>
							<div class="col3">支付金额</div>
							<div>操作</div>
						</div>
						<!-- 订单列表 start-->
						<c:forEach items="${orderPage.result}" var="order">
						<div class="data_item">
							<div class="fold cf">
								<div class="col4">
									<c:if test="${isDisplayCheckBox}">	
										<span><input type="checkbox" value="${order.orderId}"/></span>
									</c:if>
									<span class="ord_num">${order.orderNo}</span>
								</div>
								<div class="col5"><fmt:formatDate value="${order.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
								<div class="col2">
									<span class='<c:if test="${order.orderType eq orderTypes[1]}">green</c:if><c:if test="${order.orderType eq orderTypes[2]}">red</c:if>'>
										<spring:message code="order.source.${order.orderType}"/>
									</span>
								</div>
								<div class="col4">
									<spring:message code="order.status.${order.status}"/>
									<c:if test="${order.status eq status[1] and !order.displayOrderCancel}">
										<!-- 展示退款状态  -->
										(<spring:message code="order.wait.status.${order.orderCancel.status}"/>)
									</c:if>
								</div>
								<div class="col2 num">${order.skuCount}</div>
								<div class="col3 mny">${order.paymentAmount}</div>
								<div class="relative">
									<!-- 订单跟踪 -->
									<c:if test="${order.status eq status[2] or order.status eq status[3]}">
										<a href="javascript:;" class="J_track" data='{"expressId":"${order.addressItems[0].expressId}","expressNo":"${order.addressItems[0].expressNo}","orderId":"${order.orderId}"}'>物流跟踪<b class="arrow_piont"></b></a>
										<div class="tip_popup track">
											<div class="tip_inner">
												<p><b>订单物流跟踪</b></p>
												<div class="loading">
													<img src="${_imagesPath}/loading.gif" />
													<span>正在努力加载...</span>
												</div>
											</div>
											<div class="tip_arrow" style="right:195px;"><span>◆</span><i>◆</i></div>
										</div>
									</c:if>
									<!-- 确认收获按钮 TODO-->
									<c:if test="${order.status eq status[2]}">
										<a href="javascript:;" class="J_confirmgood" data-id="${order.addressItems[0].orderAddressId}">确认收货</a>
									</c:if>
									<!-- 订单是待付款状态时显示待付款连接 -->
									<c:if test="${order.status eq status[0]}">
										<a class="J_netpay" target="_blank" href="${_ctxPath}/netpay/confirmOrder.htm?orderIds=${order.orderId}">付款</a>
									</c:if>
									<!-- 查看详情TODO -->
									<a href="${_ctxPath}/order/order-myOrderPackageDetail-${order.addressItems[0].orderAddressId}.htm" target="_blank">查看详情</a>
									<!-- 取消状态显示删除 -->
									<c:if test="${order.status eq status[4]}">
										<a href="javascript:;" class = "J_delorder" data-id="${order.orderId}">删除</a>
									</c:if>
									<!-- 取消状态显示取消 -->
									<c:if test="${order.status eq status[0]}">
										<a href="javascript:;" class = "J_cancelorder" data-id = "${order.orderId}">取消</a>
									</c:if>
									<!-- 待发货显示取消并退款 -->
									<c:if test="${order.status eq status[1] and order.displayOrderCancel}">
										<a href="javascript:;" class="J_waitcancelorder" data-id="${order.orderId}">取消并退款</a>
									</c:if>
									<!-- 待发货 已经申请退款 显示取查看退款记录-->
									<c:if test="${order.status eq status[1] and !order.displayOrderCancel}">
										<a href="${_ctxPath}/order/order-myOrderCancelRecord.htm">退款记录</a>
									</c:if>
								</div>
							</div>
							<div class="fold_wp">
								<div class="data_con">
									<!-- 包裹开始 -->
									<!-- 如果只有一个包裹就不显示 包裹金额和数量 -->
									<c:set var="isDisplayPackageDetail" value="${fn:length(order.addressItems) > 1}"/>
									<c:forEach items="${order.addressItems}" var="list" varStatus="ss">
									<div class="uf_item <c:if test='${fn:length(order.addressItems) == ss.index+1}'>nobm</c:if>">
										<div class="unfold cf">
											<table width="100%">
												<tbody>
													<tr>
														<td width="17" class="pak_nm"></td>
														<td width="542">${escape:escapeHtml(list.receiveAddress)} (${list.receiverName} 收) ${list.mobile}</td>
														<td class="num">
															<%-- <c:if test="${isDisplayPackageDetail}">${list.skuCount}</c:if> --%>
														</td>
														<td class="mny">
															<%-- <c:if test="${isDisplayPackageDetail}">${list.packageTotalPrice}</c:if> --%>
														</td>
													</tr>
												</tbody>
											</table>
										</div>
										<div class="package">
											<table width="100%">
												<tbody>
													<c:forEach items="${list.orderAddressItems}" var="itemList">
													<!-- 如果只有一个包裹切只有一个商品就不显示 包裹金额和数量 -->
													<c:set var="isDisplayProductNum" value="${fn:length(list.orderAddressItems) > 1}"/>
													<tr>
														<td width="50"></td>
														<td width="95">
															<a href="${_ctxPath}/item-${itemList.orderItem.productSkuId}-0-0.htm" target="_blank">
																<img src="<yp:thumbImage originalPath='${itemList.orderItem.defaultImage}' imageType='t89'></yp:thumbImage>" alt="${itemList.orderItem.productName}" />
															</a>
														</td>
														<td width="395">
															<p>
																<a href="${_ctxPath}/item-${itemList.orderItem.productSkuId}-0-0.htm" target="_blank">
																${yipin:cutString(itemList.orderItem.productName,25)}
																</a>
															</p>
															<p><span>${itemList.orderItem.productProp}</span></p>
														</td>
														<td width="80">
															<c:if test="${isDisplayProductNum}">${itemList.assignCount}</c:if>
														</td>
														<td width="130">
															<c:if test="${isDisplayProductNum}">${itemList.assignCount * itemList.orderItem.unitPrice}</c:if>
														</td>
														<td>
															<!-- 已经退货的才显示这个 TODO -->
															<c:if test="${not empty itemList.orderItem.refundStatusInfo}">
																退货状态:${itemList.orderItem.refundStatusInfo} 
																<a href="${_ctxPath}/order/order-myRefundRecord.htm" class="c1">退货记录</a>
															</c:if>
															<!-- 订单完成状态并可以退货 TODO -->
															<c:if test="${itemList.orderItem.canRefund}">
																<a href="${_ctxPath}/order/order-myOrderRefundForm-${itemList.orderItem.orderItemId}.htm" class="c1">申请退货</a>
															</c:if>
														</td>
													</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
									</c:forEach>
									<!-- 包裹开始 结束 -->
								</div>
							</div>
						</div>
						</c:forEach>
						<!-- 订单列表 end-->
					</div>
					<c:if test="${isDisplayCheckBox}">	
						<span><input type="checkbox" class="selectAll"/>全选</span>
						<span>
						<c:if test="${orderPage.params.status eq status[0]}">
							<a href="javascript:;" class="J_bathpayment">合并付款已选择订单</a>
							<form id="J_bathpaymentform" action="" method="post" target="_blank"></form>
						</c:if>
						<c:if test="${orderPage.params.status eq status[2]}">
							<a href="javascript:;" class="J_bathconfirmreceive">确认收货已选择订单</a>
						</c:if>
						</span>
					</c:if>
					<!-- 分页导航 start-->
					<yp:yipinFrontPage urlEnd=".htm" urlStart="order-myOrders-${orderPage.params.orderType}-${orderPage.params.queryTime}-${orderPage.params.status}-${orderPage.total}-" page="${orderPage}"></yp:yipinFrontPage>
					<!-- 分页导航 end-->
				</div>
				</c:if>
				<c:if test="${empty orderPage.result}">
					<div class="noRecord">没有找到您要的订单，赶紧去<a href="${_ctxPath}" class="red">购物</a>吧！</div>
				</c:if>
			</div>
			<!-- 用户中心右侧 end-->
		</div>
		<!-- 用户中心框架 end-->
	</div>
	<!--底部 start-->
	<%@include file="/WEB-INF/pages/include/foot.jsp"%>
	<!--底部 end-->
	<!--page js-->
	<script type="text/javascript" src="${_jsPath}/pages/ordData.js"></script>
	<script type="text/javascript">
		$(function(){
			//为订单左右分页添加样式
			var _next = $('.navigation .next_page').get(0);
			if(undefined != _next){
				$('.page_nav .next').parent().addClass('on');
			}
			var _prev = $('.navigation .prev_link').get(0);
			if(undefined != _prev){
				$('.page_nav .prev').parent().addClass('on');
			}
		});
	</script>
</body>
</html>