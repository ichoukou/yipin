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
	<%@include file="/WEB-INF/pages/include/headUserInfo.jsp"%>
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
						<!-- 
						<select name="queryTime" id="queryTimeId">
							<option value="1">最近一月的订单</option>
							<option value="2">一月之前的订单</option>
						</select>
						 -->
					</div>
					<a href="${_ctxPath}/order/order-myRefundManage.htm">退货申请</a>
					<span>|</span>
					<a href="${_ctxPath}/order/order-myRefundRecord.htm" class="on"><span>退货记录</span></a>
					<span>|</span>
					<span class="f12">您可以在此申请退换货以及查询退货订单进度</span>
				</div>
				<div class="my_order">
					<div class="data_show">
						<div class="data_tit cf">
							<div class="col6">退货编号</div>
							<div class="col7">商品信息</div>
							<div class="col8">申请时间</div>
							<div class="col9">退货来源</div>
							<div class="col10">退货状态</div>
							<div class="col11"></div>
						</div>
						<!-- 订单列表 start-->
						<c:forEach items="${refundPage.result}" var="refund">
						<div class="data_item">
							<div class="fold cf">
								<div class="col6">${refund.orderRefundNo}</div>
								<div class="col7">
									<a href="${_ctxPath}/order/order-myOrderPackageDetail-${refund.orderAddressId}.htm" target="_blank">
										${yipin:cutString(refund.refundProductNames,6)}
									</a>
								</div>
								<div class="col8"><fmt:formatDate value="${refund.createTime}" type="both"/></div>
								<div class="col9">
									订单号：${refund.orderHead.orderNo},
									<a href="${_ctxPath}/order/order-myOrderPackageDetail-${refund.orderAddressId}.htm" target="_blank">
										包裹号:${refund.orderAddress.packageNo}
									</a>
								</div>
								<div class="col10"><spring:message code="order.refund.status.${refund.status}"/></div>
								<div class="col11">
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
								</div>
							</div>
							<div class="fold_wp">
								<div class="data_con">
									<div class="loading"><img src="${_imagesPath}/loading.gif" /></div>
									<input class="orderIdHiddenVal" type="hidden" value="${order.orderId}"/>
									<!-- 包裹开始 -->
									<!-- //TODO ajax返回-->
									<!-- 包裹开始 结束 -->
								</div>
							</div>
						</div>
						</c:forEach>
						<!-- 订单列表 end-->
					</div>
					<!-- 分页导航 start-->
					<yp:yipinFrontPage urlEnd=".htm" urlStart="order-myRefundRecord-${refundPage.total}-" page="${refundPage}"></yp:yipinFrontPage>
					<!-- 分页导航 end-->
				</div>
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