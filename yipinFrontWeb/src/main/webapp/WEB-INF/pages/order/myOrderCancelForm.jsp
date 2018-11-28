<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<title>退款申请</title>
	<jsp:include page="/WEB-INF/pages/include/common.jsp"></jsp:include>
	<!--page css-->
	<link rel="stylesheet" type="text/css" href="${_cssPath}/pages/returnApply.css" media="all" />
	<!--page css-->
	<link rel="stylesheet" type="text/css" href="${_cssPath}/pages/ordData.css" media="all" />
	<link rel="stylesheet" type="text/css" href="${_jsPath}/plugin/uploadify/uploadify.css" media="all" />
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
				<div class="ucr_hd"><a href="${_ctxPath}/order/order-myOrderCancelManage.htm">退款管理</a> > 退款申请</div>
				<div class="return_apply">
					<!-- 退货说明 s-->
					<div class="return_sm">
						退款说明：
						如因商品质量问题进行退货的，快递费用由买家先行垫付，卖家收到退货货后，将商品费用及快递费用一起打款给买家；如因买家自身原因进行退货的，退货及发票快递费用由买家自行承担。更多信息请查看<a target="_blank" href="${_ctxPath}/help/help-100008.htm">退换货政策</a>。
					</div>
					<!-- 订单列表 start-->
					<div class="data_show">
						<div class="data_tit cf">
							<div class="fl c_cf1">订单号</div>
							<div class="fl c_cf2">下单时间</div>
							<div class="fl c_cf4">订单状态</div>
							<div class="fl c_cf5">数量</div>
							<div class="fl c_cf6">支付金额</div>
							<div class="fl c_cf7">操作</div>
						</div>
						<div class="data_item">
							<div class="fold cf">
								<div class="fl c_cf1">
									<span class="ord_num">${orderHead.orderNo}</span>
								</div>
								<div class="fl c_cf2"><fmt:formatDate value="${orderHead.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
								<div class="fl c_cf4"><spring:message code="order.status.${orderHead.status}"/></div>
								<div class="fl c_cf5 num">${orderHead.skuCount}</div>
								<div class="fl c_cf6 mny">${orderHead.paymentAmount}</div>
								<div class="fl c_cf7">
									<a href="${_ctxPath}/order/order-myOrderPackageDetail-${orderHead.orderAddress.orderAddressId}.htm" target="_blank">查看详情</a>
								</div>
							</div>
							<div class="fold_wp">
								<div class="data_con">
									<div class="uf_item nobm">
										<div class="unfold cf">
											<table width="100%">
												<tbody>
													<tr>
														<td width="17" class="pak_nm"></td>
														<td width="542">${escape:escapeHtml(orderHead.orderAddress.receiveAddress)} (${orderHead.orderAddress.receiverName} 收) ${orderHead.orderAddress.mobile}</td>
														<td class="num"></td>
														<td class="mny"></td>
													</tr>
												</tbody>
											</table>
										</div>
										<div class="package">
											<table width="100%">
												<tbody>
													<c:forEach items="${orderHead.items}" var="itemList">
													<tr>
														<td width="95">
															<a href="${_ctxPath}/item-${itemList.productSkuId}-0-0.htm" target="_blank">
																<img src="<yp:thumbImage originalPath='${itemList.defaultImage}' imageType='t84'></yp:thumbImage>" alt="${itemList.productName}" />
															</a>
														</td>
														<td width="355">
															<p>
															<a href="${_ctxPath}/item-${itemList.productSkuId}-0-0.htm" target="_blank">
																${yipin:cutString(itemList.productName,25)}
															</a>
															</p>
															<p><span>${itemList.productProp}</span></p>
														</td>
														<td width="110">${itemList.num}</td>
														<td width="130">${itemList.num * itemList.unitPrice}</td>
														<td></td>
													</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- 订单列表 end-->
					<div class="apply_form">
					<form action="" id="form1">
						<table width="100%">
							<tbody>
								<tr><td class="tit">*联系人：</td>
									<td>
										<input id="refundContactName" type="text" class="txt" />
										<div class="relative"><span id="refundContactNameTips"></span></div>
									</td>
								</tr>
								<tr><td class="tit">*手机号码：</td>
									<td>
										<input id="refundMobile" type="text" class="txt" />
										<div class="relative"><span id="refundMobileTips"></span></div>
									</td>
								</tr>
								<tr><td class="tit">*退款原因：</td>
									<td>
										<textarea id="refundReason" cols="30" rows="10"></textarea>
										<p>您可以输入<i class="red J_textTip">300</i></p>
										<div class="relative"><span id="refundReasonTips"></span></div>
									</td>
								</tr>
								<tr>
									<td></td>
									<td>
										<input id="orderItemIdHidden" type="hidden" value="${orderItem.orderItemId}"/>
										<input id="orderIdHidden" type="hidden" value="${orderHead.orderId}"/>
										<a href="javascript:;" class="submit" id="refundSubmit"> <!--  style="display: none;" -->提交申请</a>
										<a href="javascript:;" class="goback" id="reset">重置</a>
										<a href="${_ctxPath}/order/order-myOrderCancelManage.htm" class="goback">返回</a>
									</td>
								</tr>
							</tbody>
						</table>
						</form>
					</div>
				</div>
			</div>
			<!-- 用户中心右侧 end-->
		</div>
		<!-- 用户中心框架 end-->
	</div>
	<!--底部 start-->
	<%@include file="/WEB-INF/pages/include/foot.jsp"%>
	<!--底部 end-->
	<!--page js-->
	<script type="text/javascript" src="${_jsPath}/pages/returnApply.js"></script>
	<script type="text/javascript" src="${_jsPath}/plugin/uploadify/jquery.uploadify.min.js"></script>
	<script type="text/javascript">
	//手机只能输入数字
	$("#refundMobile").keyup(function(event){
		var $this = $(this);
	    olnyNumber2($this,11);
	});
	</script>
</body>
</html>