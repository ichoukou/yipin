<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>

<c:if test="${productlist !=null}">
	<c:forEach items="${productlist}" var="p">
		<div class="fl">
			<p>卖家-${p.key}</p>
			<input type="checkbox" class="allCheck" />全选
		</div>
		<ul class="fl">
		    <c:forEach items="${p.value}" var="product">
				<li data-id="${product.productId }" data-rank="${product.rank}" data-time="<fmt:formatDate value="${product.preDeliveryTime}" pattern="yyyy-MM-dd HH:mm:ss"/>">
					<div class="J_msg">
						<img src="${_filePath }/${product.coverPicture}" alt="" width="58" height="66" />
						<div class="defaSku">
							<p>名称：${product.name}</p>
							<p>规格：${product.productSku.skuSpecification}g</p>
							<p>价格：${product.productSku.unitPrice }元</p>
						</div>
					</div>
					<p class="cb">
						<input type="checkbox" class="subCheck" name="productId" value="${product.productId}"/>
					</p>
				</li>
			</c:forEach>
		</ul>
	</c:forEach>
</c:if>

