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
		<!--start form-->
		<form class="m-mt10p m-clear" action="${_ctxPath}/admin/order/order-searchOrderInvoices4Seller.htm" method="post" id="subform">
			<input type="hidden" name="orderPage.params.sellerId" value="${orderPage.params.sellerId }"/>
			<div class="m-fl m-bt10">
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
		
		<!--start 下单管理table-->
		<div class="order-management">
			<table id="order-tab" width="100%">
				<thead class="tab-control">
					<tr>
						<th width="20%">订单编号</th>
						<th width="40%">发票地址</th>
						<th width="40%">物流信息</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${orderInvoicePage.result}" var="orderInvoice" varStatus="status">
						<tr class="list-tr">
							<td colspan="10" class="td-nobor">
								<table class="tab-control">
									<tr class="tr-height">
										<input id="orderInvoiceAddressId" type="hidden" value="${orderInvoice.orderInvoiceAddressId}"  />
										<input id="orderId" type="hidden" value="${orderInvoice.orderId}"  />
										<td width="20%">${orderInvoice.orderHead.orderNo}</td>
										<td width="40%">${orderInvoice.receiveAddress}</td>
										<td width="40%">
											<c:if test="${orderInvoice.expressNo==null || orderInvoice.expressNo==''}">
                                            <form id="form${orderInvoice.orderInvoiceAddressId}">
							                   	<label>发货：</label>
							                   	<select class="select-mail" id="expressId${orderInvoice.orderInvoiceAddressId}">
													<c:forEach items="${listExpresses }" var="express" >
														<option value="${express.expressId }">${express.expressName}</option>
													</c:forEach>
												</select>
                                                
                                                <input id="expressNo${orderInvoice.orderInvoiceAddressId}" type="text" class="odd-numbers" />
                                                <!--<div id="expressNo${orderInvoice.orderInvoiceAddressId}Tip"></div>		-->				                    								                	
                                                
                                                <aut:authorize url="/admin/order/order-confirmSendOrderInvoice">
                                                	<input onclick="confirmSend('${orderInvoice.orderInvoiceAddressId}')" type="button" class="confirm-btn" value="确认发货" />
							                	</aut:authorize>
                                            </form>
						                	</c:if>
						                	<c:if test="${orderInvoice.expressNo!=null && orderInvoice.expressNo!=''}">
							                   	<label>物流公司:${orderInvoice.expressName}</label>
												<label>面单号:${orderInvoice.expressNo}</label>
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

//确认发货
function confirmSend(id){
	var num = id.toString();
	/* var result = $.formValidator.pageIsValid(num); // 组号必须用带引号
    if (!result) {
        return false;   // 验证不通过,直接返回
    } */
	
	var expressName = $("#expressId"+id+" option:selected").text();
	var expressId = $("#expressId"+id+" option:selected").val();
    var expressMailNo = $("#expressNo"+id).val();
    if('请输入快递单号'== expressMailNo || ''==expressMailNo){
    	alert("请输入快递单号");
    	return;
    }
    if(expressMailNo.length>20){
    	alert("请输入1-20位快递单号");
    	$("#expressNo"+id).val("");
    	return;
    }
    $.ajax({
		type : 'POST',
		url : '${_ctxPath}/admin/order/order-confirmSendOrderInvoice.htm',
		data : {'orderInvoiceAddress.expressId':expressId,
				'orderInvoiceAddress.expressName' : expressName,
				'orderInvoiceAddress.expressNo' :expressMailNo,
				'orderInvoiceAddress.orderInvoiceAddressId': id
				},
		success : function(data) {
			if(data.code=='true'){
				var content='<label>物流公司：</label><span>'+expressName+'</span>\t'+'<label>运单号码：</label><span>'+expressMailNo+'</span>'
				$tr = $("#expressNo"+id).closest("td");
				$tr.empty();
				$tr.html(content);
			}else{
				alert("运单号重复!");
			}
		},
		error:function(data){
			alert(data.infoValues);
		}
	});
}
</script>
</html>