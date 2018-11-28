<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.ytoxl.module.yipin.base.dataobject.resultmap.ExpressMessage" %>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<tr class="show-tr">
	<c:set var="orderCancel" value="${orderHead.orderCancel}"/>
	<c:set var="orderAddress" value="${orderHead.orderAddress}"/>
	<td colspan="10" class="show-tab">
		<div class="order-info">
			<div class="m-clear m-bd">
				<div class="m-fl f-wl">
					<div class="order-botcolor order-tdwidth">
						<h4>订单详情</h4>
						<ul class="layout">
						<!-- 订单所购的商品 -->
						<c:forEach items="${orderHead.items}" var="item">
							<li>
								<table>
									<tr>
										<td rowspan="2" width="70px"><img src="<yp:thumbImage originalPath='${item.defaultImage}' imageType='t84'></yp:thumbImage>" /></td>
										<td width="15%">${item.brandName}</td>
										<td width="30%">${item.productName}</td>
										<td width="15%"><span class="coin">&yen;</span><label>${item.unitPrice}</label></td>
										<td width="10%">X   ${item.num}</td>
									</tr>
								</table>
							</li>
						</c:forEach>
						</ul>
					</div>
					<div class="order-tdwidth">
						<table>
							<tr>
								<td width="15%">商品金额：</td>
								<td width="23%"><span class="coin">&yen;</span><label>${orderHead.paymentAmount}</label></td>
								<td width="10%">运费：</td>
								<td width="15%"><span class="coin">&yen;</span><label>0.00</label></td>
								<td width="*">
									支付总额：<span class="coin">&yen;</span>${orderHead.paymentAmount}
								</td>
							</tr>
							<c:if test="${orderHead.hasInvoice == orderInvoiceStatus}">
								<tr>
									<td width="15%">发票信息：</td>
									<td colspan="4">
										<label>
											<c:if test="${empty orderHead.invoiceTitle}">个人</c:if>
											<c:if test="${not empty orderHead.invoiceTitle}">${orderHead.invoiceTitle}</c:if>
										</label>
									</td>
								</tr>
							</c:if>
							<tr>
								<td colspan="5">支付宝帐号：${orderHead.accountInfo}</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="m-fr f-wr">
					<div class="fr-tab">
						<ul class="m-clear">
							<li class="bot-no">收货信息</li>
							<li>物流状态</li>
						</ul>
					</div>
					<div class="order-tdwidth fr-tabcont tabcont-item">
						<ul class="layout">
							<c:forEach items="${orderHead.items}" var="cs">
								<li>
									<label width="15%">${cs.brandName}</label>
									<label width="20%">${cs.productName}</label>
									<label width="20%">${cs.unitPrice}</label>
									<label width="10%">X ${cs.num}</label>
								</li>
							</c:forEach>
							<li><label>收货人: ${orderAddress.receiverName }</label></li>         
							<li><label>手机: ${orderAddress.mobile }</label></li>         
							<li><label>电话: ${orderAddress.telephone }</label></li>        
							<li><label>邮编: ${orderAddress.postCode }</label></li>
							<li><label>${orderAddress.receiveAddress }</label></li>
						</ul>
	        		</div>
				  	<div class="order-tdwidth fr-tabcont tabcont-item m-hide">
                        <div class="layout">
                            <div class="package" style="display:block;">
                                <p>
                                    <label>物流公司：${orderAddress.expressName}</label>
                                    <label>面单号：${orderAddress.expressNo}</label>
                                </p>
                                <div>
                                   <c:if test="${orderAddress.expressMessage != null}">
							            <c:set var="status_success" value="<%=ExpressMessage.STATUS_SUCCESS%>"></c:set>
							            <c:choose>
							             	<c:when test="${orderAddress.expressMessage.status eq status_success }">
								            	<ul>
													<c:forEach items="${orderAddress.expressMessage.data }" var="dataItem">
														<li>
															<label>${dataItem.time }</label>
															<span>${dataItem.context }</span>
														</li>
													</c:forEach>
									            </ul>
							            	</c:when>
								            <c:otherwise><p>系统正在更新，给您带来不便请见谅，请稍后查询！</p></c:otherwise>
							            </c:choose>
						            </c:if>
                                </div>
                            </div>
                        </div>
		          	</div>
				</div>
			</div>
			
			<!-- 订单备注 -->
			<div class="m-clear m-bd back-shop">
                <h4>订单备注：</h4>
                <div class="m-fl">
                	${orderHead.orderRemark}
                </div>
	        </div>
	        
	        <!-- 退款申请 -->
            <c:if test="${orderHead.status == orderStatusWaitSend && orderHead.orderCancel!=null}">
	            <div class="back-shop m-clear">
	            	<h4>退款申请:</h4>
	            </div>
	            <div class="fr-tabcont tabcont-item">
	                <div class="m-clear">
	                    <div class="back-content">
	                        <div class="back-bar m-clear">
	                            <div class="back-details">
		                            <span>申请日期：<fmt:formatDate value='${orderCancel.createTime}' pattern='yyyy-MM-dd HH:mm:ss' /></span>
		                            <span>联系人：${orderCancel.customerName}</span>
		                            <span>联系电话：${orderCancel.mobile}</span>
	                            </div>
	                            <div class="back-sum">订单金额：￥${orderHead.paymentAmount}</div>
	                        </div>
	                        <table class="back-table">
	                            <tbody>         
	                                <tr>
		                                <td width="<c:if test="${orderCancel.status==cancelStatuses[1]}">728px</c:if><c:if test="${orderCancel.status!=cancelStatuses[1]}">528px</c:if>">
		                                    <div class="order-botcolor order-tdwidth bd-none">
		                                        <ul class="layout">
			                                        <!-- 订单所购的商品 -->
			                                        <c:forEach items="${orderHead.items}" var="orderItem">
			                                            <li>
			                                                <table>
			                                                    <tbody>
			                                                        <tr>
			                                                            <td width="70px" rowspan="2"><img src="<yp:thumbImage originalPath='${orderItem.defaultImage}' imageType='t84'></yp:thumbImage>" /></td>
			                                                            <td width="15%">${orderItem.productName}</td>
			                                                            <td width="30%">${orderItem.productProp}</td>
			                                                            <td width="15%"><span class="coin">¥</span><label>${orderItem.unitPrice}</label></td>
			                                                            <td width="10%">X   ${orderItem.num}</td>
			                                                        </tr>
			                                                    </tbody>
			                                                </table>
			                                            </li>
			                                        </c:forEach>
			                                        <li><p>问题描述：${escape:escapeHtml(orderCancel.refundReason)}</p></li>
		                                        </ul>
		                                    </div>
		                                </td>
		                                <c:if test="${orderCancel.status!=cancelStatuses[1]}">
			                                <td width="200px">
			                                	<div class="back-hd">
			                                		<ul>
			                                    	<li>
			                                    		<b>退款审核状态：</b><br/>
			                                            <label id="cancelStatusL_${orderCancel.orderCancelId}">
															<c:if test="${orderCancel.status!=null && orderCancel.status!=''}">
																<spring:message code="order.wait.status.${orderCancel.status}" /> 
															</c:if>
														</label>     
													</li>	  
	                                            	<li>
	                                            		<b>审核日期：</b><br/>
	                                            		<c:if test="${orderCancel.status == cancelStatuses[3]}">
	                                            			<fmt:formatDate value='${orderCancel.sellerCheckTime}' pattern='yyyy-MM-dd HH:mm:ss' />
	                                            		</c:if>
	                                            		<c:if test="${orderCancel.status == cancelStatuses[4]}">
	                                            			<fmt:formatDate value='${orderCancel.adminCheckTime}' pattern='yyyy-MM-dd HH:mm:ss' />
	                                            		</c:if>
	                                            	</li>
			                                   		</ul>
			                                   	</div>    
			                                </td>
		                                </c:if>
		                                <td class="bd-last" width="220px">
											<c:if test="${orderCancel.status==cancelStatuses[1]}">
												<b>退款审核中</b>
											</c:if>
											<!-- 订单项状态为 审核未通过 -->
											<c:if test="${orderCancel.status==cancelStatuses[2]}">
												<b>审核未通过</b><br/>
												<p>
													<b>审核描述:</b><br/>
													${orderCancel.reviewDescribe}
												</p>
											</c:if>
											<!-- 订单项状态为 审核通过  -->
											<c:if test="${orderCancel.status == cancelStatuses[3]}">
												<b id="agreePaymentlabel_${orderCancel.orderCancelId}">
													<label>退款金额：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><br/>
													<input type="text" id="money_${orderCancel.orderCancelId}" value=""/>
												  	<aut:authorize url="/admin/order/order-agreePayment">
														<p>
															<input type="button" id="cancelbtn_${orderCancel.orderCancelId}" class="m-btn agreePayment" value="同意退款" onclick="confirmCancelPayment(${orderCancel.orderCancelId},${orderHead.paymentAmount})" />
														</p>
													</aut:authorize>
											 	</b>
											</c:if>
											<c:if test="${orderCancel.status==cancelStatuses[4]}">
												<b>已退款</b>
											</c:if>
		                                </td>
		                            </tr>              
	                            </tbody>
	                        </table>
	                    </div>
	                </div>
	            </div>
            </c:if>
            
			<!-- 退货商品信息 -->
			<c:if test="${orderHead.orderRefunds!=null && orderHead.orderRefunds[0]!=null}">
	            <div class="back-shop m-clear">
	           		<h4>退货商品:</h4>
	            </div>
			    <div class="fr-tabcont tabcont-item">
	                <div class="m-clear">
	                    <c:forEach items="${orderHead.orderRefunds}" var="refund">
	                    	<c:set var="refundItem" value="${refund.orderItem }"/>
		                    <div class="back-content">
		                        <div class="back-bar m-clear">
		                            <div class="back-details">
		                            <span>申请日期：<fmt:formatDate value='${refund.createTime}' pattern='yyyy-MM-dd HH:mm:ss' /></span>
		                            <span>联系人：${refund.customerName}</span>
		                            <span>联系电话：${refund.mobile}</span>
		                            <span>${refund.refundExpress.expressName}</span>
		                            <span>${refund.refundExpress.expressNo}</span>
		                            </div>
		                            <div class="back-sum">应退金额：￥${refund.refundNum*refundItem.unitPrice}</div>
		                        </div>
		                        <table class="back-table">
		                            <tbody>         
		                                <tr>
		                                <td width="568px">
		                                    <div class="order-botcolor order-tdwidth bd-none">
		                                        <ul class="layout">
		                                       	 	<!-- 订单所购的商品 -->
		                                            <li>
		                                                <table>
		                                                    <tbody>
		                                                        <tr>
		                                                            <td width="70px" rowspan="2"><img src="<yp:thumbImage originalPath='${refundItem.imageUrls}' imageType='t84'></yp:thumbImage>" /></td>
		                                                            <td width="15%">${refundItem.productName}</td>
		                                                            <td width="30%">${refundItem.productProp}</td>
		                                                            <td width="15%"><span class="coin">¥</span><label>${refundItem.unitPrice}</label></td>
		                                                            <td width="10%">X   ${refund.refundNum}</td>
		                                                        </tr>
		                                                    </tbody>
		                                                </table>
		                                            </li>
		                                        </ul>
		                                        <p>问题描述：${escape:escapeHtml(refund.refundReason)}</p>
		                                       	买家退货图片：
		                                        </br>
		                                        <table>
		                                            <tbody>
		                                                <tr>
		                                                    <td>
		                                                		<c:set value="${ fn:split(refund.imageUrls, ',') }" var="images" />
		 		                                        		<c:forEach items="${ images }" var="image" begin="0" end="3">
		 		                                        			<c:if test="${not empty image}">
		                                            	    	    	<img src="<yp:thumbImage originalPath='${image}' imageType='t84'></yp:thumbImage>" />&nbsp;
		                                            				</c:if>
		                                            			</c:forEach>    	    
		                                                    </td>
		                                                </tr>
		                                            </tbody>
		                                        </table>
		                                    </div>
		                                </td>
		                                <td width="220px">
		                                    <div class="back-hd">
		                                        <ul>
		 	                                        <li>
			 	                                        <b>退货审核状态：</b><br>							                                             
			                                            <label id="refundStatusL${refund.orderRefundId}">
															<c:if test="${refund.status!=null}">
			                                                    <spring:message code="order.refund.status.${refund.status}" /> 
			                                                </c:if>
														</label>                                               
													</li>
		                                            <li>
		                                            	<b>审核日期：</b><br>
		                                            	<fmt:formatDate value='${refund.adminCheckTime}' pattern='yyyy-MM-dd HH:mm:ss' />
		                                            </li>
		                                        </ul>
		                                    </div>
		                                </td>
		                                <td  class="bd-last" width="180px">
											<c:if test="${refund.status==itemStatuses[1]}">
												<b>待审核</b>
											</c:if>
											<c:if test="${refund.status==itemStatuses[3]}">
												<b>审核未通过</b><br/>
												<b>审核描述:</b>${refund.reviewDescribe}
											</c:if>
											<c:if test="${refund.status==itemStatuses[2]}">
												<b>待退货</b>
											</c:if>
											<c:if test="${refund.status==itemStatuses[4]}">
												<b>待确认收货</b>
											</c:if>
											<c:if test="${refund.status==itemStatuses[5]}">
												<b>
												<aut:authorize url="/admin/order/order-confirmPayment">
													<p>
														<input type="button" class="m-btn confirmPayment" value="同意退款"
															id="confirmPayment${refund.refundPayment.orderRefundPaymentId}"
															onclick="confirmPayment(${refund.refundPayment.orderRefundPaymentId},${refund.orderRefundId})" />
													</p>
												</aut:authorize>
												</b>
											</c:if>
											<c:if test="${refund.status==itemStatuses[6]}">
												<b>已完成</b>
											</c:if>
		                                </td>
		                            </tr>              
		                            </tbody>
		                        </table>
		                    </div>
	                    </c:forEach>
	                </div>
	          	</div>
	    	</c:if>      
        </div>
	</td>
</tr>
<script type="text/javascript">
	//待发货-同意退款
	function confirmCancelPayment(orderCancelId,paymentAmount){
		var money = $("#money_"+orderCancelId).val();
		if(!money){
			alert("请输入退款金额！");
			return;
		}
		if(!/^(?!0\d)\d+(\.\d{0,3})?$/.test(money)){
			alert("请输入正确的金额数字，最多可以有3为小数！");
			return;
		}
		money = parseFloat(money);
		if(money<=0 || money > paymentAmount){
			alert("退款金额不正确！");
			return;
		}
		
		$.ajax({
			type:'post',
			data:{
				'orderCancel.orderCancelId':orderCancelId,
				'orderCancel.refundAmount':money
			},
			url:'${_ctxPath}/admin/order/order-agreePayment.htm',
			success:function(data){
				if(data.code=='true'){
					$("#agreePaymentlabel_"+orderCancelId).html("已完成");
					$("#cancelStatusL_"+orderCancelId).html("已完成");
				}else{
					alert(data.info);
				}
			},
			error:function(data){
				alert(data.info);
			},
			oncomplete:function(data){
				alert(data.info);
			}
		});
	}
	
	//同意退款
	function confirmPayment(orderRefundPaymentId,orderRefundId){
		$.ajax({
			type:'post',
			data:{
				'orderRefundPayment.orderRefundPaymentId':orderRefundPaymentId,
				'orderRefundPayment.ordeRrefundId':orderRefundId
			},
			url:'${_ctxPath}/admin/order/order-confirmPayment.htm',
			success:function(data){
				if(data.code=='true'){
					$id = $("#confirmPayment"+orderRefundPaymentId);
					var $a = $id.closest(".show-tr").prev("tr").find(".c-blue");
					$id.parent().append("已完成");
					$id.remove();
					//移除"退款"操作
					if($(".confirmPayment").length == 0){
						$a.remove();	
					}
					
					$id = $("#refundStatusL"+orderRefundId);
					$id.html("已完成");
				}else{
					alert(data.info);
				}
			},
			error:function(data){
				alert(data.info);
			},
			oncomplete:function(data){
				alert(data.info);
			}
		});
	}
</script>