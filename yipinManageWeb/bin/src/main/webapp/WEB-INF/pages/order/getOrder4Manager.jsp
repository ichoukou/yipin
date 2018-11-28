<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.ytoxl.module.yipin.base.dataobject.resultmap.ExpressMessage" %>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<tr class="show-tr">
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
										<td rowspan="2" width="70px"><img src="<yp:thumbImage originalPath='${item.imageUrls}' imageType='t84'></yp:thumbImage>" /></td>
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
							<c:forEach items="${orderHead.addressItems}" var="addItems">
								<li>
									<div>
										<ul>
											<li>
												<label>
													<span class="c-red">包裹  ${addItems.packageNo } <c:if test="${addItems.isReceive !=null && addItems.isReceive !=''}">
														(<spring:message code="order.package.status.${addItems.isReceive }" />)
														</c:if>
														<c:if test="${addItems.isReceive ==null || addItems.isReceive ==''}">(未收货)</c:if>
													</span>
											</label>
											</li>
											<c:forEach items="${addItems.orderAddressItems}" var="cs">
												<li>
													<label width="15%">${cs.brandName}</label>
													<label width="20%">${cs.productName}</label>
													<label width="20%">${cs.unitPrice}</label>
													<label width="10%">X ${cs.assignCount}</label>
												</li>
											</c:forEach>
											<li><label>收货人: ${addItems.receiverName }</label></li>         
											<li><label>手机: ${addItems.mobile }</label></li>         
											<li><label>电话: ${addItems.telephone }</label></li>        
											<li><label>邮编: ${addItems.postCode }</label></li>
											<li><label>${addItems.receiveAddress }</label></li>
										</ul>
									</div>
								</li>
							</c:forEach>
						</ul>
	        		</div>
				  	<div class="order-tdwidth fr-tabcont tabcont-item m-hide">
                        <ul class="order-ul m-clear">
                        	<table>
                        		<tr>
		                        	<c:forEach items="${orderHead.addressItems}" var="pkgItems" varStatus="status">
		                        		<td>
		                            		<li id="${status.index}" class="c-red <c:if test="${status.index==0 }">cur</c:if>" >包裹  ${pkgItems.packageNo }</li>
			                            </td>	
		                            	<c:if test="${status.index!=0 && (status.index+1)%5==0 && false==status.last}">
		                            		</tr><tr>
		                            	</c:if>
		                            </c:forEach>
                            	</tr>
                            </table>
                        </ul>
                        <div class="layout">
                        	<c:forEach items="${orderHead.addressItems}" var="expItems" varStatus="status">
	                            <div class="package" <c:if test="${status.index==0 }">style="display:block;" </c:if>>
	                                <p>
	                                    <label>物流公司：${expItems.expressName}</label>
	                                    <label>面单号：${expItems.expressNo}</label>
	                                </p>
	                                <div>
	                                   <c:if test="${expItems.expressMessage != null}">
									            <c:set var="status_success" value="<%=ExpressMessage.STATUS_SUCCESS%>"></c:set>
									            <c:choose>
									             	<c:when test="${expItems.expressMessage.status eq status_success }">
										            	<ul>
															<c:forEach items="${expItems.expressMessage.data }" var="dataItem">
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
                            </c:forEach>
                        </div>
		          	</div>
				</div>
			</div>
			
            <div class="back-shop m-clear">
            	<h4>退货商品信息</h4>
            </div>
			<!-- 退货商品信息 -->
		    <div class="fr-tabcont tabcont-item">
                <div class="m-clear">
                    <c:forEach items="${orderHead.orderRefundModels}" var="refund">
                    <div class="back-content">
                        <div class="back-bar m-clear">
                            <div class="back-details">
                            <span>申请日期：<fmt:formatDate value='${refund.createTime}' pattern='yyyy-MM-dd HH:mm:ss' /></span>
                            <span>联系人：${refund.customerName}</span>
                            <span>联系电话：${refund.mobile}</span>
                            <span>${refund.orderAddress.expressName}</span>
                            <span>${refund.orderAddress.expressNo}</span>
                            </div>
                            <div class="back-sum">应退金额：￥${refund.orderAddress.packageTotalPrice}</div>
                        </div>
                        <table class="back-table">
                            <tbody>         
                                <tr>
                                <td width="428px">
                                    <div class="order-botcolor order-tdwidth bd-none">
                                        <ul class="layout">
                                        <!-- 订单所购的商品 -->
                                        <c:forEach items="${refund.orderItems}" var="orderItem">
                                            <li>
                                                <table>
                                                    <tbody>
                                                        <tr>
                                                            <td width="70px" rowspan="2"><img src="<yp:thumbImage originalPath='${orderItem.imageUrls}' imageType='t84'></yp:thumbImage>" /></td>
                                                            <td width="15%">${orderItem.productName}</td>
                                                            <td width="30%">${orderItem.productProp}</td>
                                                            <td width="15%"><span class="coin">¥</span><label>${orderItem.unitPrice}</label></td>
                                                            <td width="10%">X   ${orderItem.num}</td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </li>
                                        </c:forEach>
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
                                <td width="363px">
                                    <div class="back-hd">
                                        <p>
                                            <label>包裹：${refund.orderAddress.packageNo}</label>
                                             ${refund.orderAddress.receiveAddress}
                                        </p>
                                        <ul>
 	                                        <li><b>退货审核状态：</b>   							                                             
                                            <label id="refundStatusL${refund.orderRefundId}">
											<c:if test="${refund.status==itemStatuses[1]}">
												待审核
											</c:if>
											<c:if test="${refund.status==itemStatuses[3]}">
												审核未通过
											</c:if>
											<c:if test="${refund.status==itemStatuses[2]}">
												待退货
											</c:if>
											<c:if test="${refund.status==itemStatuses[4]}">
												待确认收货
											</c:if>
											<c:if test="${refund.status==itemStatuses[5]}">
												待退款
											</c:if>
											<c:if test="${refund.status==itemStatuses[6]}">
												已完成
											</c:if>
											</label>                                               
											</li>
                                            <li><b>审核日期：</b><fmt:formatDate value='${refund.adminCheckTime}' pattern='yyyy-MM-dd HH:mm:ss' /></li>
                                        </ul>
                                    </div>
                                </td>
                                <td  class="bd-last" width="153px">
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
														<p><input type="button" class="m-btn confirmPayment" value="确认退款"
														id="confirmPayment${refund.orderRefundPayment.orderRefundPaymentId}"
														onclick="confirmPayment(${refund.orderRefundPayment.orderRefundPaymentId},${refund.orderRefundId})" />
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
          </div>
	</td>
</tr>
<script type="text/javascript">
	//确认退款
	function confirmPayment(orderRefundPaymentId,orderRefundId){
		$.ajax({
			type:'post',
			data:{
				'orderRefundPayment.orderRefundPaymentId':orderRefundPaymentId,
				'orderRefundPayment.ordeRrefundId':orderRefundId
			},
			url:'${_ctxPath}/admin/order/order-confirmPayment.htm',
			success:function(data){
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
			},
			error:function(data){
				//alert("error:"+data);
				//error
			},
			oncomplete:function(data){
				//alert("oncomplete:"+data);
			}
		});
	}
</script>