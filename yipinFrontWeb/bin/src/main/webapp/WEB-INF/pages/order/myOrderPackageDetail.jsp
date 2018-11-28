<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.ytoxl.module.yipin.base.dataobject.resultmap.ExpressMessage" %>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
    <meta charset="UTF-8">
    <title>包裹详情</title>
    <!--css-->
    <jsp:include page="/WEB-INF/pages/include/common.jsp"></jsp:include>
    <!--page css-->
    <link rel="stylesheet" type="text/css" href="${_cssPath}/pages/orderDetails.css" media="all" />
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
				<div class="ucr_hd"><a href="${_ctxPath}/order/order-myOrders.htm">我的订单</a> > ${orderAddress.orderHead.orderNo}</div>
				<div class="my_order">
					<div class="data_show">
						<!-- 订单列表 start-->
						<div class="data_item">
						    <div class="fold_wp">
						        <div class="data_con">
						            <div class="uf_item">
						                <div class="unfold cf">
						                    <table width="100%">
						                        <tbody>
						                        <tr>
						                            <td width="150" class="pak_nm">订单号：${orderAddress.orderHead.orderNo} </td>
													<td width="30"></td>
						                            <td width="150">包裹号：${orderAddress.packageNo}</td>
													<td width="30"></td>
						                            <td>状态：<spring:message code="order.status.${orderAddress.orderHead.status}"/></td>
						                        </tr>
						                        </tbody>
						                    </table>
						                </div>
						                <div class="package">
						                    <table width="100%">
						                        <tbody>
						                        <tr>
						                            <td colspan="6">
						                                <div class="pack_box">
						                                	<c:if test="${orderAddress.orderHead.status eq status[0]}">
						                                	<!-- 待付款 -->
						                                    <p><img src="${_imagesPath}/uploadfolder/myOrder/state_1.jpg" /> </p>
						                                    </c:if>
						                                    <c:if test="${orderAddress.orderHead.status eq status[1]}">
						                                    <!-- 待发货  如果有多个包裹 本包裹发货了显示发货图片-->
					                                    	<c:choose>
						                                    	<c:when test="${orderAddress.isReceive eq packageStatus[0]}">
						                                    		<p><img src="${_imagesPath}/uploadfolder/myOrder/state_3.jpg" /> </p>
						                                    	</c:when>
						                                    	<c:when test="${orderAddress.isReceive eq packageStatus[1]}">
						                                    		<p><img src="${_imagesPath}/uploadfolder/myOrder/state_4.jpg" /> </p>
						                                    	</c:when>
						                                    	<c:otherwise>
						                                    		<p><img src="${_imagesPath}/uploadfolder/myOrder/state_2.jpg" /> </p>
						                                    	</c:otherwise>
					                                    	</c:choose>
						                                    </c:if>
						                                    <c:if test="${orderAddress.orderHead.status eq status[2]}">
						                                    <!-- 已发货 -->
						                                    <c:choose>
						                                    	<c:when test="${orderAddress.isReceive eq packageStatus[1]}">
						                                    		<p><img src="${_imagesPath}/uploadfolder/myOrder/state_4.jpg" /> </p>
						                                    	</c:when>
						                                    	<c:otherwise>
						                                    		<p><img src="${_imagesPath}/uploadfolder/myOrder/state_3.jpg" /> </p>
						                                    	</c:otherwise>
						                                    </c:choose>	
						                                    </c:if>
						                                    <c:if test="${orderAddress.orderHead.status eq status[3] or orderAddress.orderHead.status eq status[5]}">
						                                    <!-- 已完成 -->
						                                    <p><img src="${_imagesPath}/uploadfolder/myOrder/state_4.jpg" /> </p>
						                                    </c:if>
						                                    <c:if test="${orderAddress.orderHead.status eq status[4]}">
						                                    <!-- 已取消 -->
						                                    <p><img src="${_imagesPath}/uploadfolder/myOrder/state_cancel.png" /> </p>
						                                    </c:if>
						                                    <div class="pack_hd">
						                                        <ul>
						                                            <li>
						                                                <span>提交订单</span>
						                                                <span class="color">
						                                                <fmt:formatDate value="${orderAddress.orderHead.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						                                                </span>
						                                            </li>
						                                            <!-- status=4 取消 -->
						                                            <c:if test="${orderAddress.orderHead.status ne status[4]}">
						                                            <li>
						                                                <span>付款成功</span>
						                                                <span class="color">
						                                                <fmt:formatDate value="${orderAddress.orderHead.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						                                                </span>
						                                            </li>
						                                            <li>
						                                                <span>已发货</span>
						                                                <span class="color">
						                                                <fmt:formatDate value="${orderAddress.sendProductTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						                                                </span>
						                                            </li>
						                                            <li>
						                                                <span>完成</span>
						                                                <span class="color">
						                                                <fmt:formatDate value="${orderAddress.receiveProductTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						                                                </span>
						                                            </li>
						                                            </c:if>
						                                            <c:if test="${orderAddress.orderHead.status eq status[4]}">
						                                            <li></li>
						                                            <li></li>
						                                            <li>
						                                                <span>取消时间</span>
						                                                <span class="color">
						                                                <fmt:formatDate value="${orderAddress.orderHead.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						                                                </span>
						                                            </li>
						                                            </c:if>
						                                        </ul>
						                                    </div>
						                                </div>
						                            </td>
						                        </tr>
						                        <c:forEach items="${orderAddress.orderAddressItems}" var="itemList">
						                        <tr>
						                            <td width="50"></td>
						                            <td width="95"><img src="<yp:thumbImage originalPath='${itemList.orderItem.defaultImage}' imageType='t84'></yp:thumbImage>" alt="${itemList.orderItem.productName}" /></td>
						                            <td width="445">
						                                <p>${itemList.orderItem.productName}</p>
						                                <p><span>${itemList.orderItem.productProp}</span></p>
						                            </td>
						                            <td width="65">数量：${itemList.assignCount}</td>
						                            <td>金额：<span class="red">${itemList.assignCount * itemList.orderItem.unitPrice}</span></td>
						                            <td>积分：${yipin:computerPoint(itemList.assignCount * itemList.orderItem.unitPrice)}</td>
						                        </tr> 
						                        </c:forEach>
						                        </tbody>
						                    </table>
						                </div>
						                <!--总计金额 star-->
						                <div class="total">
										                     金额总计：<var>￥${orderAddress.packageTotalPrice}</var> <br/>
										                     运费：<var>￥0.00</var><br/>
										                     包裹金额：<var class="red rental">￥${orderAddress.packageTotalPrice}<!-- <i>元</i> --></var>
						                </div>
						                <!--总计金额 end-->
						            </div>
						        </div>
						    </div>
						</div>
						<!-- 订单列表 end-->
	    				<!--操作 star-->
					    <div class="detail">
					        <div class="wl_wp" data='{"expressId":"${orderAddress.expressId}","expressNo":"${orderAddress.expressNo}","orderId":"${orderAddress.orderHead.orderId}"}'>
								<div class="loading">
									<img src="${_imagesPath}/loading.gif" />
									<span>正在努力加载...</span>
								</div>
					        </div>
					        <div class="detail_wp">
					            <div class="detail_con">
					                <p class="detail_tit">收货人信息</p>
					                <p>收货人：${escape:escapeHtml(orderAddress.receiverName)}</p>
					                <p>地     址：${escape:escapeHtml(orderAddress.receiveAddress)}</p>
					                <p>固定电话：${orderAddress.telephone}</p>
					                <p>手机号码：${orderAddress.mobile}</p>
					                <p>电子邮件：${orderAddress.postCode}</p>
					            </div>
					            <div class="detail_con">
					                <p class="detail_tit">支付及配送方式</p>
					                <p>支付方式：<spring:message code="order.payment.type.${orderAddress.orderHead.payType}"/></p>
					                <p>运    费：￥0.00</p>
					                <p>送货日期：不限</p>
					                <p class="blue">快递公司：
					                	<c:if test="${not empty orderAddress.express.websiteUrl}">
											<a href="${orderAddress.express.websiteUrl}" target="_blank"> ${orderAddress.expressName}</a>
										</c:if>
										<c:if test="${empty orderAddress.express.websiteUrl}">
											${orderAddress.expressName}
										</c:if>
				                	</p>
					                <p>快递单号：${orderAddress.expressNo}</p>
					            </div>
					            <div  class="detail_con detail_last">
				                <c:if test="${not empty orderAddress.orderHead.hasInvoice and orderAddress.orderHead.hasInvoice eq invoiceStatus[1]}">
					                <p class="detail_tit">发票信息</p>
					                <p>发票类型：普通发票</p>
					                <p>发票抬头：
										<c:if test="${not empty orderAddress.orderHead.invoiceTitle}">
											${orderAddress.orderHead.invoiceTitle}
										</c:if>
										<c:if test="${empty orderAddress.orderHead.invoiceTitle}">
											个人
										</c:if>
									</p>
					                <p>发票内容：商品明细</p>
									<p class="blue">快递公司：
										<c:if test="${not empty orderAddress.orderHead.orderInvoiceAddress.express.websiteUrl}">
											<a href="${orderAddress.orderHead.orderInvoiceAddress.express.websiteUrl}" target="_blank"> ${orderAddress.orderHead.orderInvoiceAddress.expressName}</a>
										</c:if>
										<c:if test="${empty orderAddress.orderHead.orderInvoiceAddress.express.websiteUrl}">
											${orderAddress.orderHead.orderInvoiceAddress.expressName}
										</c:if>
									</p>
					                <p>快递单号：${orderAddress.orderHead.orderInvoiceAddress.expressNo}</p>
				                </c:if>
					            </div>
					        </div>
					    </div>
	    				<!--操作 end-->
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
	<!--js-->
	<script type="text/javascript" src="${_jsPath}/plugin/plugin.js"></script>
	<!--page js-->
	<script type="text/javascript">
		$(function(){
			//已发货的发送物流信息请求
			//var isSendRequestExpressInfo = ${orderAddress.orderHead.status eq status[2]};
			//if(isSendRequestExpressInfo){
				sendRequestExpressInfo();
			//}
			function sendRequestExpressInfo(){
				var _this = $('.wl_wp');
				var queryData = _this.attr("data");
				var _queryData = $.parseJSON(queryData);
				var expressId = $.trim(_queryData['expressId']);
				var mailNo = $.trim(_queryData['expressNo']);
				var orderId = $.trim(_queryData['orderId']);
				if(orderId != ""){
					var q = $.param({"expressId":expressId,"mailNo":mailNo,"orderId":orderId});
					$.ajax({
						type : "POST",
						url : _ctxPath + "/order/order-expressInfoAjax.htm",
						data : q,
						success : function(html){
							_this.empty().append(html);
						},
						error:function(){
							_this.find(".loading").find("span").text("没有查询到相应的物流信息，给您带来不便请见谅，请稍后再试！");
							_this.find(".loading").find("img").remove();
						}
					});
				}else{
					_this.find(".loading").find("span").text("没有查询到相应的物流信息，给您带来不便请见谅，请稍后再试！");
					_this.find(".loading").find("img").remove();
				}
			}
		});
	</script>
</body>
</html>