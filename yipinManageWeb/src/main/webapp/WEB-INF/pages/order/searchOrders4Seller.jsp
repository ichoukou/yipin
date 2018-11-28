<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商家订单管理</title>
<link href="${ _cssPath}/common.css" rel="stylesheet" />
<link href="${ _cssPath}/pages/orderManagement.css" rel="stylesheet" />
<link href="${_jsPath }/plugin/formvalidator/themes/Default/style/style.css" rel="stylesheet" />

<script src="${_jsPath }/jquery/jquery.1.8.1.js" language="javascript"></script>
<script src="${_jsPath }/plugin/My97DatePicker/WdatePicker.js"></script>
<script src="${_jsPath }/plugin/formvalidator/formValidatorRegex.js" language="javascript"></script>
<script src="${_jsPath }/plugin/formvalidator/formValidator-4.1.3.js" language="javascript"></script>
<script type="text/javascript" src="${_jsPath}/plugin/uploadify/jquery.uploadify.v2.1.4.min.js"></script>
<script type="text/javascript" src="${_jsPath}/plugin/uploadify/swfobject.js"></script>
</head>
<body>
	<jsp:include page="../include/header.jsp" flush="true" />
	<div class="body m-w980p">
		<!--start form-->
		<form class="sub-form" action="${_ctxPath}/admin/order/order-searchOrders4Seller.htm" method="post" id="subform">
			<input type="hidden" name="orderPage.params.sellerId" value="${orderPage.params.sellerId }"/>
			<input type="hidden" name="orderPage.params.sellerFlag" value="true" />
			<input type="hidden" id="status" name="orderPage.params.status" value="${orderPage.params.status}" /><%--订单状态 --%>
			<input type="hidden" id="returnStatus" name="orderPage.params.returnStatus" value="${orderPage.params.returnStatus}" /><%--退货订单状态 --%>
			<input type="hidden" id="waitStatus" name="orderPage.params.waitStatus" value="${orderPage.params.waitStatus}" /><%--待退货订单状态 --%>
			<div class="m-clear m-mt10p">
				<div class="m-fl"> 
					<select name="orderPage.params.brandId" class="m-sel">
						<option value="">全部品牌</option>
						<c:forEach items="${brandList }" var="brand">
							<option value="${brand.brandId }"
								<c:if test="${orderPage.params.brandId==brand.brandId }"> selected = "selected" </c:if>>
									${brand.name}
							</option>
						</c:forEach>
					</select>
					
					<label>交易日期：</label> 
					<input id="beginTime" name="orderPage.params.beginTime" value="${orderPage.params.beginTime}" class="Wdate" type="text" readOnly="readOnly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
					<label>至 </label> 
					<input id="endTime" name="orderPage.params.endTime" value="${orderPage.params.endTime }" class="Wdate" type="text" readOnly="readOnly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
					
					<input type="text" name="orderPage.params.keywords" value="${orderPage.params.keywords }" class="J-keywords txt-input input-marks" data-default="输入买家姓名、电话、订单号" />
					<input type="button" class="m-btn m-btn-search" value="查 询" id="search" /> 
					
		      		<aut:authorize url="/admin/order/order-exportSellerOrders">
		      			<input type="button" class="m-btn" value="导出" id="export" />
		      		</aut:authorize>
				</div>
				<c:if test="${not empty orderPage.result}">
					<div class="m-fr curr-num">
						<label>当前显示： </label> 
		        		<yp:commPageSize page="${orderPage}" beanName="orderPage"></yp:commPageSize>
					</div>
	      		</c:if>
	      	</div>
		</form>
		<div class="m-clear m-mt10p">
			<div class="m-fl body-nav subnav" id="status_con">
				<ul class="m-clear">
					<li >
					<a
						<c:if test="${orderPage.params.status==null || orderPage.params.status=='' }"> class="current-chose"</c:if>>
						 全部订单
					</a>
					</li>
					<c:forEach items="${statuses }" var="statuse" varStatus="status">
					<c:if test="${statuse < 6}">	
						<li value="${statuse}">
						<a
							<c:if test="${orderPage.params.status==status.index+1 }"> class="current-chose" </c:if>>
								<spring:message code="order.status.${status.index+1}" /> 
						</a>
						</li>
					</c:if>
					<!-- 部分退货 和 全部退货  显示退货 -->
					<c:if test="${statuse == 6}">	
						<li value="${statuse}">
						<a
							<c:if test="${orderPage.params.status==status.index+1 }"> class="current-chose" </c:if>>
								退货 
						</a>
						</li>
					</c:if>
					</c:forEach>
				</ul>
			</div>
				
			<div class="m-fr">
				<a class="download m-fr" href="${_ctxPath}/admin/order/order-downloadTemplate.htm">点击下载模板</a>
					<div class="inline">
					<script type="text/javascript">
						$(document).ready(
										function() {
											var uploadDefaultParams = {
												'auto' : true,
												'buttonImg' : '${_jsPath}/plugin/uploadify/batch.jpg',
												'cancelImg' : '${_jsPath}/plugin/uploadify/cancel.png',
												'expressInstall' : '${_jsPath}/plugin/uploadify/expressInstall.swf',
												'fileDataName' : 'file',
												'fileDesc' : '请选择xls、xlsx文件',
												'width' : 80,
												'fileExt' : '*.xls;*.xlsx',
												'multi' : false,
												'script' : '${_ctxPath}/admin/order/order-batchUpload.htm',
												'sizeLimit' : 2097152,
												'uploader' : '${_jsPath}/plugin/uploadify/uploadify.allglyphs.swf'
											};
		
											var uploadlogoParams = uploadDefaultParams;
		
											uploadlogoParams.onComplete = function(
													event, ID, fileObj,
													response, data) {
												alert($.parseJSON(response).info);
												$('#search').click();
											};
												$('#imgUpload').uploadify(
														uploadlogoParams);
										});
					</script>
					<input type="file" id="imgUpload" name="file" />
				</div>
			</div>
		</div>
		
		<%--当选择查询状态是待发货时 --%>
		<c:if test="${orderPage.params.status == orderStatusWaitSend}">	
			<div class="child-menu"><%--1=退款审核中，2=退款未通过，3=审核通过，4=已退款 --%>
				<ul>
					<c:forEach items="${cancelStatuses}" var="wait" varStatus="status">
						<li 
							<c:if test="${status.index==0 && (orderPage.params.waitStatus==''||orderPage.params.waitStatus==0)}"> class="on" </c:if>
							<c:if test="${status.index!=0 && orderPage.params.waitStatus!=null && orderPage.params.waitStatus==status.index }"> class="on" </c:if> 
							>
							<a class="waitStatus" id="${wait}">
								<spring:message code="order.wait.status.${status.index}" /> 
							</a>
						</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
		
		<%--当选择查询状态是退货时 --%>
		<c:if test="${orderPage.params.status == orderStatusReturn}">	
			<div class="child-menu"><%--1=未审核，2=审核通过，3=审核不通过，4=已到货 --%>
				<ul>
					<c:forEach items="${itemStatuses}" var="statuse" varStatus="status">
						<li 
							<c:if test="${status.index==0 && (orderPage.params.returnStatus==''||orderPage.params.returnStatus==0)}"> class="on" </c:if>
							<c:if test="${status.index!=0 && orderPage.params.returnStatus==status.index }"> class="on" </c:if> >
							<a class="returnStatus" id="${statuse}">
								<spring:message code="order.refund.status.${status.index}" /> 
							</a><%--1=未审核，2=审核通过，3=审核不通过，4=已到货 --%>
						</li>		
					</c:forEach>
				</ul>
			</div>
		</c:if>
		
		<!--start 下单管理table-->
		<div class="order-management">
			<table id="order-tab" width="100%">
				<thead class="tab-control">
					<tr>
						<th width="13%">订单编号</th>
						<th width="13%">下单时间</th>
						<th width="13%">总金额</th>
						<th width="13%">支付方式</th>
						<th width="14%">支付状态</th>
						<th width="13%">订单状态</th>
						<th width="13%">订单来源</th>
						<th width="8%">操作</th>
					</tr>
				
				</thead>
				<tbody>
				
					<c:forEach items="${orderPage.result}" var="order" varStatus="status">
						<tr class="list-tr">
							<td colspan="10" class="td-nobor">
								<table class="tab-control">
									<tr class="tr-height">
										<td class="num-icon" width="13%">
											<input type="hidden" value="${order.orderId}" id='orderId' />
											<input type="hidden" value="${order.orderNo}" id='orderNo' />
											<input type="hidden" value="" id="orderReturnId"/>
											<i></i><label><a>${order.orderNo}</a></label>
										</td>
										<td width="13%"><fmt:formatDate value='${order.createTime}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
										<td width="13%">${order.paymentAmount }</td>
										<td width="13%">
											<c:if test="${order.payType !=null && order.payType !=''}">
												<spring:message code="order.payment.type.${order.payType }" />
											</c:if>
										</td>
										<td width="14%">
											<c:if test="${order.payStatus ==null}">
												未付款
											</c:if> 
											<c:if test="${order.payStatus !=null}">
												<spring:message code="order.payment.status.${order.payStatus }" />
											</c:if>
										</td>
										<td width="13%">
											<c:if test="${empty order.refundStatus || orderPage.params.status != orderStatusReturn}">
												<spring:message code="order.status.${order.status }" />
												<c:if test="${not empty order.orderCancel.status && orderPage.params.status == orderStatusWaitSend}">
													(<spring:message code="order.wait.status.${order.orderCancel.status }" />)
												</c:if>
											</c:if>
											<c:if test="${not empty order.refundStatus && orderPage.params.status == orderStatusReturn}">
												<spring:message code="order.refund.status.${order.refundStatus}" />
											</c:if> 
										</td>
										<td width="13%">
											<c:if test="${order.orderType !=null && order.orderType !=''}">
												<spring:message code="order.source.${order.orderType }" />
											</c:if>
										</td>
										<td width="8%">
											<c:if test="${orderPage.params.status == orderStatusReturn && (order.refundStatus==orderReturnItemStatusNotAudit || orderPage.params.returnStatus == orderReturnItemStatusNotAudit)}">
												<a href="javascript:;" class="c-blue">退货审核</a>
											</c:if>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="table-bm-wrap cf">
			<!--S 右功能区-->
			<div class="fn-right">
				<!--S 分页-->
				<div class="pagination pagination-right">
					<c:if test="${not empty orderPage.result}">
						<yp:commPage page="${orderPage}" beanName="orderPage"></yp:commPage>
					</c:if>
				</div>
				<!--E 分页-->
			</div>
			<!--E 右功能区-->
		</div>
	</div>
<jsp:include page="/WEB-INF/pages/include/foot.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		$(".returnStatus").bind("click",function(){
			$("#returnStatus").val($(this).attr("id"));
			$('#search').click();
		});
		
		$(".waitStatus").bind("click",function(){
			$("#waitStatus").val($(this).attr("id"));
			$("#search").click();
		});
		
		$('#status_con li').bind('click', function() {
			if ($(this).val() == 0) {
				$('#status').val('');
			} else {
				$('#status').val($(this).val());
			}
			$("#returnStatus").val("");
			$("#waitStatus").val("");
			$('#search').click();
		});
		
		$('#search').bind('click', function() {
			var keywords = $(".J-keywords").val();
			if(keywords == $(".J-keywords").attr("data-default")){
				$(".J-keywords").val("");
			}
			if($("#beginTime").val() && $("#endTime").val()){
				if(compareDate($("#beginTime").val(),$("#endTime").val())){
					alert("开始时间不能大于结束时间！");
					return;
				}
			}
			
			if($("#status").val()==6 && !$("#returnStatus").val()){
				$("#returnStatus").val(0);
			}
			
			$('#subform').submit();
		});
		
		$('#export').bind('click', function() {
			if($.trim($('tbody').html())==''){
				return;
			}
			if($("#beginTime").val() && $("#endTime").val()){
				if(compareDate($("#beginTime").val(),$("#endTime").val())){
					alert("开始时间不能大于结束时间！");
					return;
				}
			}
			var keywords = $(".J-keywords").val();
			if(keywords == $(".J-keywords").attr("data-default")){
				$(".J-keywords").val("");
			}

			$('#subform').attr("action","${_ctxPath}/admin/order/order-exportSellerOrders.htm");
			$('#subform').submit();
			$('#subform').attr("action","${_ctxPath}/admin/order/order-searchOrders4Seller.htm");
		});
	
		//防止在一次请求 多次点击
		var isPost = true;
		$(".tr-height").on("click", function() {
			$this = $(this);
			var orderId = $this.find('#orderId').val();
			var returnStatus = $('#returnStatus').val();<%--当前选中的退货订单状态--%>
			var orderReturnId = $this.find('#orderReturnId').val();
			var status = $('#status').val();<%--当前选中的订单状态--%>
			if (!$(this).next(".show-tr")[0]) {
				if(isPost){
					isPost = false;
					$.ajax({
						type : 'POST',
						url : '${_ctxPath}/admin/order/order-getOrder4Seller.htm',
						data : {
							"orderHead.orderId" : orderId,
							"orderPage.params.returnStatus":returnStatus,
							"orderPage.params.orderReturnId":orderReturnId,
							"orderPage.params.status":status,
							"orderPage.params.orderId":orderId
						},
						success : function(html) {
							var content;
							if (typeof html == "object") {
								data = eval(html);
								content = data.info;
							} else {
								content = html;
							}
							$($this).after($(content));
							$this.addClass("change");
							$this.click();
							isPost = true;
						},
						error:function(){
							isPost = true;
						}
					});
				}
			}
		});
	});
	
	//订单内包裹切换
    $('.order-ul li').live('click',function(){
    	var _this = $(this),
    	_idx = _this.attr("id");
    	
    	$('.order-ul li').removeClass('cur');
    	_this.addClass('cur');
    	
    	$('.package').eq(_idx).show().siblings().hide();
    })

	function returnOrder(){
		if($('tbody').html().trim()==''){
			return;
		}
		var keywords = $(".J-keywords").val();
		if(keywords == $(".J-keywords").attr("data-default")){
			$(".J-keywords").val("");
		}
		$('#subform').attr("action","${_ctxPath}/admin/order/order-exportReturnOrders.htm");
		$('#subform').submit();
		$('#subform').attr("action","${_ctxPath}/admin/order/order-searchOrders4Seller.htm");
	}
	
	
	//比较日前大小  如果开始大于结束，返回true
	function compareDate(checkStartDate, checkEndDate) {      
	    var arys1= new Array();      
	    var arys2= new Array();      
		if(checkStartDate != null && checkEndDate != null) {      
		    arys1=checkStartDate.split('-');      
		    var sdate=new Date(arys1[0],parseInt(arys1[1]-1),arys1[2]);      
		    arys2=checkEndDate.split('-');      
		    var edate=new Date(arys2[0],parseInt(arys2[1]-1),arys2[2]);      
			if(sdate > edate) {      
			    return true;         
			}else{   
			    return false;      
		    }   
	    }      
	}   
</script>
</body>
</html>