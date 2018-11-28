<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.ytoxl.module.yipin.base.dataobject.resultmap.ExpressMessage" %>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>
    <!--css-->
    <jsp:include page="/WEB-INF/pages/include/common.jsp"></jsp:include>
    <!--page css-->
    <link rel="stylesheet" type="text/css" href="${_cssPath}/pages/orderDetails.css" media="all" />
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
													<td width="50"></td>
						                            <td width="150">订单状态:<spring:message code="order.status.${orderAddress.orderHead.status}"/></td>
						                            <td>支付状态:<spring:message code="order.payment.status.${orderAddress.orderHead.payStatus}"/></td>
						                        </tr>
						                        </tbody>
						                    </table>
						                </div>
						                <div class="package">
						                	<div class="pack_box">
			                                	<div class="p_state">
				                                	<c:if test="${orderAddress.orderHead.status eq status[0]}">
				                                	<!-- 待付款 -->
				                                    <p><img src="${_imagesPath}/state_1.jpg" /> </p>
				                                    </c:if>
				                                    <c:if test="${orderAddress.orderHead.status eq status[1]}">
				                                    <!-- 待发货 -->
		                                    		<p><img src="${_imagesPath}/state_2.jpg" /> </p>
				                                    </c:if>
				                                    <c:if test="${orderAddress.orderHead.status eq status[2]}">
				                                    <!-- 已发货 -->
				                                    <c:choose>
				                                    	<c:when test="${orderAddress.isReceive eq packageStatus[1]}">
				                                    		<p><img src="${_imagesPath}/state_4.jpg" /> </p>
				                                    	</c:when>
				                                    	<c:otherwise>
				                                    		<p><img src="${_imagesPath}/state_3.jpg" /> </p>
				                                    	</c:otherwise>
				                                    </c:choose>	
				                                    </c:if>
				                                    <c:if test="${orderAddress.orderHead.status eq status[3] or orderAddress.orderHead.status eq status[5]}">
				                                    <!-- 已完成 -->
				                                    <p><img src="${_imagesPath}/state_4.jpg" /> </p>
				                                    </c:if>
				                                    <c:if test="${orderAddress.orderHead.status eq status[4]}">
				                                    <!-- 已取消 -->
				                                    <p><img src="${_imagesPath}/state_cancel.jpg" /> </p>
				                                    </c:if>
			                                	</div>
			                                    <div class="p_list cf">
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
			                                            <li>&nbsp;</li>
			                                            <li>&nbsp;</li>
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
			                                <!--显示商品 s-->
						                    <table class="ps_proList">
						                    	<thead>
						                    		<tr>
						                    			<th width="140">商品编号</th>
						                    			<th colspan="2">商品</th>
						                    			<th>数量</th>
						                    			<th>金额</th>
						                    			<th>获赠积分</th>
						                    		</tr>
						                    	</thead>
						                        <tbody>
							                        <c:forEach items="${orderAddress.orderAddressItems}" var="itemList">
							                        <tr>
							                            <td>${itemList.orderItem.productSkuId}</td>
							                            <td width="80">
							                            	<a href="${_ctxPath}/item-${itemList.orderItem.productSkuId}-0-0.htm" target="_blank"><img src="<yp:thumbImage originalPath='${itemList.orderItem.defaultImage}' imageType='t84'></yp:thumbImage>" alt="${itemList.orderItem.productName}" /></a>
							                            </td>
							                            <td>
							                            	<p><a href="${_ctxPath}/item-${itemList.orderItem.productSkuId}-0-0.htm" target="_blank" class="c1">${yipin:cutString(itemList.orderItem.productName,15)}</a></p>
							                            	<p><span class="c2">${itemList.orderItem.productProp}</span></p>
							                            </td>
							                            <td>${itemList.assignCount}</td>
							                            <td><span class="red">￥${itemList.assignCount * itemList.orderItem.unitPrice}</span></td>
							                            <td>${yipin:computerPoint(itemList.assignCount * itemList.orderItem.unitPrice)}</td>
							                        </tr> 
							                        </c:forEach>
						                        </tbody>
						                    </table>
						                    <!--显示商品 e-->
						                </div>
						                <!--总计金额 star-->
						                <div class="total">
										           商品金额:<var>￥${orderAddress.packageTotalPrice}</var> <br/>
										     +运费:<var>￥0.00</var><br/>
										                    订单金额：<var class="red rental">￥${orderAddress.packageTotalPrice}<!-- <i>元</i> --></var>
						                </div>
						                <!--总计金额 end-->
						            </div>
						        </div>
						    </div>
						    <!-- 订单备注 s-->
							<div class="o_remark">
						      <c:if test="${not empty orderAddress.orderHead.orderRemark}">
						      	订单备注:${orderAddress.orderHead.orderRemark}
						      </c:if>
							</div>
							<!-- 订单备注 end-->
						</div>
						<!-- 订单列表 end-->
	    				<!--操作 star-->
					    <div class="detail">
					    	<c:if test="${orderAddress.orderHead.status eq status[2] or orderAddress.orderHead.status eq status[3]}">
					    	<!-- 当订单状态是 已发货和已完成显示 -->
					        <div class="wl_wp" data='{"expressId":"${orderAddress.expressId}","expressNo":"${orderAddress.expressNo}","orderId":"${orderAddress.orderHead.orderId}"}'>
								<div class="loading">
									<img src="${_imagesPath}/loading.gif" />
									<span>正在努力加载...</span>
								</div>
					        </div>
					        </c:if>
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
					                <p class="detail_tit"><a name="invoiceInfo">发票信息</a></p>
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
					                <%-- 
									<p class="blue">快递公司：
										<c:if test="${not empty orderAddress.orderHead.orderInvoiceAddress.express.websiteUrl}">
											<a href="${orderAddress.orderHead.orderInvoiceAddress.express.websiteUrl}" target="_blank"> ${orderAddress.orderHead.orderInvoiceAddress.expressName}</a>
										</c:if>
										<c:if test="${empty orderAddress.orderHead.orderInvoiceAddress.express.websiteUrl}">
											${orderAddress.orderHead.orderInvoiceAddress.expressName}
										</c:if>
									</p>
					                <p>快递单号：${orderAddress.orderHead.orderInvoiceAddress.expressNo}</p>
					                 --%>
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
				if(_this == undefined){
					return;
				}
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