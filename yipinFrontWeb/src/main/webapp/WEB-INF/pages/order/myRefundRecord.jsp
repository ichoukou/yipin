<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<title>退货记录</title>
	<!--css-->
	<jsp:include page="/WEB-INF/pages/include/common.jsp"></jsp:include>
	<!--page css-->
	<link rel="stylesheet" type="text/css" href="${_cssPath}/pages/ordData.css" media="all" />
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
					</div>
					<a href="${_ctxPath}/order/order-myRefundManage.htm">退货申请</a>
					<span>|</span>
					<a href="${_ctxPath}/order/order-myRefundRecord.htm" class="on"><span>退货记录</span></a>
					<span>|</span>
					<span class="f12">您可以在此申请退换货以及查询退货订单进度</span>
				</div>
				<c:if test="${not empty refundPage.result}">
				<div class="my_order">
					<div class="TabStyle">
						<table width="100%">
							<thead>
								<tr>
									<th>退货编号</th>
									<th>订单来源</th>
									<th colspan="2">退货商品</th>
									<th>申请时间</th>
									<th>购买/退货数量</th>
									<th>实际退款金额</th>
									<th colspan="2">退货状态</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${refundPage.result}" var="refund">
								<tr>
									<td>${refund.orderRefundNo}</td>
									<td>
										<a href="${_ctxPath}/order/order-myOrderPackageDetail-${refund.orderAddressId}.htm" class="c_1" target="_blank">
										${refund.orderHead.orderNo}
										</a>
									</td>
									<td width="50">
										<a href="${_ctxPath}/item-${refund.orderItem.productSkuId}-0-0.htm" target="_blank">
										<img src="<yp:thumbImage originalPath='${refund.orderItem.defaultImage}' imageType='t89'></yp:thumbImage>" alt="${refund.orderItem.productName}" />
										</a>
									</td>
									<td>
										<a href="${_ctxPath}/item-${refund.orderItem.productSkuId}-0-0.htm" class="c_1" target="_blank">
										${yipin:cutString(refund.orderItem.productName,6)}
										</a>
										<p>${refund.orderItem.productProp}</p>
									</td>
									<td><fmt:formatDate value="${refund.createTime}" type="both"/></td>
									<td>${refund.orderItem.num}/${refund.refundNum}</td>
									<td>${refund.orderItem.unitPrice * refund.refundNum}</td>
									<td><spring:message code="order.refund.status.${refund.status}"/></td>
									<td>
										<c:if test="${refund.status eq refundStatus[2]}">
											<!-- 审核通过了 显示发货连接 -->
											<a href="${_ctxPath}/order/order-writeRefundExpressInfoForm-${refund.orderRefundId}.htm" target="_blank">填写快递信息</a>
										</c:if>
										<c:if test="${refund.status eq refundStatus[3]}">
											<!-- 审核不通过 显示原因 -->
											<div class="relative J_track">
												<a href="javascript:;">原因：${yipin:cutString(refund.reviewDescribe,6)}</a>
												<div class="tip_popup return_resa">
													<div class="tip_inner">
														<p>${refund.reviewDescribe}</p>
													</div>
													<div class="tip_arrow"><span>◆</span><i>◆</i></div>
												</div>
											</div>
										</c:if>
									</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- 分页导航 start-->
					<yp:yipinFrontPage urlEnd=".htm" urlStart="order-myRefundRecord-${refundPage.total}-" page="${refundPage}"></yp:yipinFrontPage>
					<!-- 分页导航 end-->
				</div>
				</c:if>
				<c:if test="${empty refundPage.result}">
					<div class="noRecord">暂无退货记录！</div>
				</c:if>
			</div>
			<!-- 用户中心右侧 end-->
		</div>
		<!-- 用户中心框架 end-->
	</div>
	<!--底部 start-->
	<%@include file="/WEB-INF/pages/include/foot.jsp"%>
	<!--底部 end-->
	<script type="text/javascript">
		$(function(){
			$(".J_track").on({
				'mouseenter': function(){
					$(this).addClass('status_tip_on');
				},
				'mouseleave': function(){
					$(this).removeClass('status_tip_on');
				}
			});
		});
	</script>
</body>
</html>