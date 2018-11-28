<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>发票管理</title>
<link href="${ _cssPath}/pages/orderManagement.css" rel="stylesheet" />
<link href="${_jsPath }/plugin/My97DatePicker/skin/WdatePicker.css" rel="stylesheet" />
<link href="${ _cssPath}/common.css" rel="stylesheet" />
<link href="${_cssPath }/pages/message.css" rel="stylesheet" /> 
<link href="${_jsPath }/plugin/artdialog/skins/ytoxl.css" rel="stylesheet" />

<script src="${_jsPath }/plugin/My97DatePicker/WdatePicker.js"></script>
<script src="${_jsPath }/jquery/jquery.1.8.1.js" language="javascript"></script>
<script src="${_jsPath }/plugin/formvalidator/formValidatorRegex.js" language="javascript"></script>
<script src="${_jsPath }/plugin/formvalidator/formValidator-4.1.3.js" language="javascript"></script>

</head>
<body>
	<jsp:include page="../include/header.jsp" flush="true" />
	<div class="body m-w980p">
		<!--查询框-->
		<form class="m-mt10p m-clear" action="${_ctxPath}/admin/order/order-searchOrderInvoices4Manager.htm" method="post" id="subform">
			<input type="hidden" name="orderInvoicePage.params.adminFlag" value="true"/>
			<div class="m-fl m-bt10">
				<select name="orderInvoicePage.params.brandId" class="m-sel">
					<option value="">全部品牌</option>
					<c:forEach items="${brands }" var="brand">
						<option value="${brand.brandId }" <c:if test="${brand.brandId == orderInvoicePage.params.brandId}"> selected="selected"</c:if>>
								${brand.name}
						</option>
					</c:forEach>
				</select>
				
				<label>订单号：</label> 
			    <input type="text" class="txt-input" id="orderNo" name="orderInvoicePage.params.orderNo" data-default="请输入订单号..." value="${orderInvoicePage.params.orderNo }" />
			    
			    <input type="button" class="m-btn m-btn-search" value="查 询" id="search" />
			</div>
			
			<c:if test="${not empty orderInvoicePage.result}">
				<div class="m-fr curr-num">
					<label>当前显示： </label> 
	       			<yp:commPageSize page="${orderInvoicePage}" beanName="orderInvoicePage"></yp:commPageSize>
				</div>
      		</c:if>
		</form>
		
		<!--列表数据-->
		<div class="order-management">
			<table id="order-tab" width="100%">
				<thead class="tab-control">
					<tr>
						<th width="15%">品牌名称</th>
						<th width="15%">订单编号</th>
						<th width="40%">发票地址</th>
						<th width="30%">物流信息</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${orderInvoicePage.result}" var="orderInvoice" varStatus="status">
						<tr class="list-tr">
							<td colspan="10" class="td-nobor">
								<table class="tab-control">
									<tr class="tr-height">
										<input id="orderId" type="hidden" value="${orderInvoice.orderId}"  />
										<input id="orderId" type="hidden" value="${orderInvoice.orderInvoiceAddressId}"  />
										<td width="15%">${orderInvoice.brandNames}</td>
										<td width="15%">${orderInvoice.orderHead.orderNo}</td>
										<td width="40%">${orderInvoice.receiveAddress}</td>
										<td width="30%">
					                    	<label>物流公司:${orderInvoice.expressName}</label>
											<label>面单号:${orderInvoice.expressNo}</label>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<!-- 分页  -->
		<div class="table-bm-wrap cf">
			<div class="fn-right">
				<div class="pagination pagination-right">
					<c:if test="${not empty orderInvoicePage.result}">
						<yp:commPage page="${orderInvoicePage}" beanName="orderInvoicePage"></yp:commPage>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	
	<jsp:include page="/WEB-INF/pages/include/foot.jsp"></jsp:include>
</body>

<script type="text/javascript">
	$(document).ready(function() {
		
		$("#search").bind("click", function() {
			var keywords = $("orderNo").val();
			if(keywords == $("#orderNo").attr("data-default")){
				$("#orderNo").val("");
			}
			$("#subform").submit();
		});
		
	});
</script>
</html>