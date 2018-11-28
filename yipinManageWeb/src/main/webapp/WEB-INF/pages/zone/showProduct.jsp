<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>

<c:if test="${productlist !=null}">
 <div id="p-list" class="seller_list cf">
	<div class="cf seller" id="prolist">
		<c:forEach items="${productlist}" var="p">
			<div class="fl">
				<p>卖家-${p.key}</p>
			</div>
			<ul class="fl">
			    <c:forEach items="${p.value}" var="product">
			    	<c:set var="typeId" value="${product.sellType}"/>
					<li>
						<div class="J_msg">
							<img src="${_filePath }/${product.coverPicture}" alt="" width="58" height="66" />
							<div>
								<p>名称：${product.name}</p>
								<p>规格：${product.productSku.skuSpecification}g</p>
								<p>价格：${product.productSku.unitPrice }元</p>
							</div>
						</div>
					</li>
				</c:forEach>
			</ul>
		</c:forEach>
	</div>
</div>
</c:if>


<!-- 抢购 -->

<c:if test="${productSkulist !=null}">
 <div id="p-list" class="seller_list cf">
	<div class="cf seller" id="prolist">
		<c:forEach items="${productSkulist}" var="p">
			<div class="fl">
				<p>卖家-${p.key}</p>
			</div>
			<ul class="fl">
			    <c:forEach items="${p.value}" var="pro">
					<li>
						<div class="J_msg">
							<img src="${_filePath }/${pro.product.coverPicture}" alt="" width="58" height="66" />
							<div>
								<p>名称：${pro.product.name}</p>
								<p>规格：${pro.skuSpecification}g</p>
								<p>价格：${pro.unitPrice }元</p>
							</div>
						</div>
					</li>
				</c:forEach>
			</ul>
		</c:forEach>
	</div>
</div>
</c:if>

<div class="qz_th">
	<label for=""><b>商品权重：(1-10)</b></label>
</div>

<c:if test="${productlist !=null}">
	<ul class="cf qzBox" id="qzBox">
		<c:forEach items="${productlist}" var="p">
			<c:forEach items="${p.value}" var="product" varStatus="s">
			  <c:if test="${product.sellType ==1}"> 
				<li id="goods${product.productId}" class="cf">
					<img class="fl" alt="" src="${_filePath }/${product.coverPicture}" width="58" height="66"/>
					<div class="fl qz_time"><p>商品权重：<input value="${product.rank}" class="txt input W50" readonly="readonly"></p></div>
				</li>
			  </c:if>
			  <c:if test="${product.sellType ==2}">
				<li id="goods${product.productId}" class="cf">
					<img class="fl" alt="" src="${_filePath }/${product.coverPicture}" width="58" height="66">
					<div class="fl qz_time">
						<p>商品权重：<input class="txt-input W50"  value="${product.rank}" readonly="readonly"></p>
					<div>预计发货时间：<fmt:formatDate value="${product.preDeliveryTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div></div>
				</li>
			  </c:if>
			</c:forEach>
		 </c:forEach>
	</ul>
</c:if>

<c:if test="${productSkulist !=null}">
	<ul class="cf qzBox" id="qzBox">
		<c:forEach items="${productSkulist}" var="p">
			<c:forEach items="${p.value}" var="pro" varStatus="s">
				<li id="goods${pro.productId}" class="cf">
					 <img class="fl" alt="" src="${_filePath }/${pro.product.coverPicture}" width="58" height="66"/>
						<div class="fl qz_time">
						<p>
						商品权重：
						<input class="txt-input W50" value="${pro.product.rank}" readonly="readonly">
						</p>
						<div>
						抢购时间：
						<input value="<fmt:formatDate value="${pro.saleBeginTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" name="beginTime" class="txt-input Wdate startTime">
						<span class="m5">至</span>
						<input value="<fmt:formatDate value="${pro.saleEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" name="endTime" class="txt-input Wdate endTime">
						</div>
					</div>
					</li>
			</c:forEach>
		 </c:forEach>
	</ul>
</c:if> 