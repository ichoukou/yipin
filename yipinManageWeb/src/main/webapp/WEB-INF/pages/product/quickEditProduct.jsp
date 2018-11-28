<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<link href="${_cssPath }/pages/addProduct.css" rel="stylesheet" />
<script type="text/javascript">
<!--
var mark = true;
function checkInventory(domObj){
	var regFn = /^(0|[1-9]\d{0,4})$/ ;
	if(!regFn.test(domObj.value)){
		domObj.value = "";
		domObj.focus();
		mark = false
		return mark;
	}
}
//-->
</script>
<form id="productSkuForm">
	<input type="hidden" name="product.productSku.productSkuId" value="${product.productSku.productSkuId }"/>
	
	<table class="repertories" style="width:150px;" class="small">
		<tr>
			<th>*库存数</th>
		</tr>
		<tr>
			<td>
				<input type="text" value="${product.productSku.inventory }" maxlength="5" name="product.productSku.inventory" onblur="checkInventory(this)">
			</td>
		</tr>
	</table>
	<div id="inventoryTip"></div>
</form>
