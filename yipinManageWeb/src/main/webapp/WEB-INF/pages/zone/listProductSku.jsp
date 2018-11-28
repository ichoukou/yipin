<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>

<c:if test="${productSkulist !=null}">
	<c:forEach items="${productSkulist}" var="p">
		<div class="fl">
			<p>卖家-${p.key}</p>
			<input type="checkbox" class="allCheck" />全选
		</div>
		<ul class="fl">
		    <c:forEach items="${p.value}" var="pro">
				<li data-id="${pro.productSkuId }" data-rank="${pro.product.rank}" data-btime="<fmt:formatDate value="${pro.saleBeginTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" data-etime="<fmt:formatDate value="${pro.saleEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>">
					<div class="J_msg">
						<img src="${_filePath }/${pro.product.coverPicture}" alt="" width="58" height="66" />
						<div class="defaSku">
							<p>名称：${pro.product.name}</p>
							<p>规格：${pro.skuSpecification}g</p>
							<p>价格：${pro.unitPrice }元</p>
						</div>
					</div>
					<p class="cb">
						<input type="checkbox" class="subCheck" name="skuId" value="${pro.productSkuId}"/>
					</p>
				</li>
			</c:forEach>
		</ul>
	</c:forEach>
</c:if>

