<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>

<tr class="show-tr">
	<td colspan="7" width="100%" class="show-tab">
		<div class="order-info" style="display: block;">
			<div class="layout">
				<div><h4>商品详情</h4></div>			
				<table>
					<thead>
						<tr>
							<td width="80px;">商品货号</td><td width="80px;">颜色</td><td width="80px;">重量</td>
							<td width="80px;">价格</td><td width="80px;">库存</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${productSkuList }" var="productSku">
							<tr>
								<td>${productSku.skuCode }</td>
								<td>${productSku.skuColor }</td>
								<td>${productSku.skuWeight }</td>
								<td>${productSku.unitPrice }</td>
								<td>${productSku.inventory }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</td>
</tr>


