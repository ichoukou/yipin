<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=UTF-8" import="com.ytoxl.module.yipin.base.dataobject.Product" pageEncoding="UTF-8"%>
<%@include file="../include/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>商品管理</title>
<link href="${ _cssPath}/common.css" rel="stylesheet" />
<link href="${ _cssPath}/pages/orderManagement.css" rel="stylesheet" />
<script src="${_jsPath }/jquery/jquery.1.8.1.js" language="javascript"></script>
<script type="text/javascript">
</script>
</head>
 	<c:set var="STATUS_DRAFT" value="<%=Product.STATUS_DRAFT %>"></c:set>
    <c:set var="STATUS_PASS" value="<%=Product.STATUS_PASS %>"></c:set>
    <c:set var="CHECK_PEND" value="<%= Product.STATUS_CHECK_PEND %>"></c:set>
    <c:set var="status_no_pass" value="<%= Product.STATUS_NO_PASS %>"></c:set>
    <c:set var="STATUS_SELL" value="<%= Product.STATUS_SELL %>"></c:set>
<body>
	<jsp:include page="../include/header.jsp" flush="true" />
	<div class="body m-w980p">
	 <!--start form-->
        <form class="sub-form m-clear m-mt10p" action="${_ctxPath}/seller/product/product-searchSellerProducts.htm" method="post">
    <aut:authorize url="/admin/product/product-search">
            <div class="m-fl">
                <select name="productPage.params.brandId" id="brand" class="m-sel">
                   <option value="">全部品牌</option>
                   	<c:forEach items="${sellerBrands }" var="brand">
						<option value="${brand.brandId }"
							<c:if test="${productPage.params.brandId==brand.brandId }">
								selected = "selected"
							</c:if>
						>${brand.name }</option>
					</c:forEach>
                </select>
                ${s }
                <select name="productPage.params.status" class="m-sel">
                	<option value="">选择商品状态</option>
							<c:forEach items="${frontStatuses }" var="obj">
							${obj }
								<option value="${obj }"
								<c:if test="${productPage.params.status==obj }">
									selected = "selected"
								</c:if>
								><spring:message code="product.status.${obj}"/></option>
							</c:forEach>
                 </select>
                  <select name="productPage.params.reviewStatus" class="m-sel">
                       <option value="">选择审核状态</option>
							<c:forEach items="${reviewStatuses }" var="obj">
								<option value="${obj }"
								<c:if test="${productPage.params.reviewStatus==obj }">
								selected = "selected"
								</c:if>
								><spring:message code="product.check.${obj}"/></option>
							</c:forEach>
                        </select>
              <input type="text" data-default="输入品牌名称，商品名称，SKU码" name="productPage.params.name" value="${productPage.params.name }" class="J-keywords txt-input input-marks" /><input type="button" class="m-btn m-btn-search" value="查 询" id="search"/>
            </div>
            </aut:authorize>
           	<c:if test="${not empty productPage.result}">
	            <div class="m-fr curr-num">
	              <label >当前显示：</label>
	              <yp:commPageSize page="${productPage}" beanName="productPage"></yp:commPageSize>
	            </div>
           	</c:if>
           	 <div class="m-mt10p">
            	<aut:authorize url="/seller/product/product-addProduct">
            		<input type="button" id="addProduct" class="m-btn" value="添加商品" />
            	</aut:authorize>
            </div>
        </form>
        <div class="m-mt10p">
<!-- 	            <span>草稿-审核中-审核通过-等待特卖/等待秒杀-特卖中/秒杀中-等待排期</span> -->
<%-- 	            <a class="download" href="${_ctxPath}/seller/product/product-downloadTemplate.htm">点击下载模版</a> --%>
        </div>
		<!--start 商品管理table-->
		<div class="m-mt10p goods-management">
			<table width="100%" >
				<thead class="tab-control">
                    <tr>
                        <th width="12%">品牌</th>
                        <th width="12%">商品名称</th>
                        <th width="12%">商品编码</th>
                        <th width="12%">图片</th>
                        <th width="12%">商品状态</th>
                        <th width="12%">审核状态</th>
                        <th width="12%">售卖类型</th>
                        <th width="12%">操作</th>
                    </tr>
                </thead>
				<tbody>
					<c:if test="${fn:length(productPage.result)==0 }">
						<tr class="list-tr">
							<td colspan="7" class="td-nobor" align="center">
								对不起，没有找到相关商品信息，请重新选择查询条件
							</td>
						</tr>
					</c:if>
					<c:if test="${fn:length(productPage.result)>0 }">
						<c:forEach items="${productPage.result}"  var="product" varStatus="status">
						<tr class="list-tr" >
							<td colspan="8" class="td-nobor">
								<table class="tab-control">
									<tr class="tr-height" productId="${product.productId }" sellStatus="${product.sellStatus }">
										<td class="num-icon" width="12%">
											<i></i><label><a>${product.brand.name }</a></label>
										</td>
										<td width="12%">${product.name}</td>
										<td width="12%">${product.productId }</td>
										<td width="12%">
											<img src="<yp:thumbImage originalPath='${product.coverPicture}' imageType='t84'></yp:thumbImage>" />
										</td>
										<td width="12%">
											<spring:message code="product.status.${product.sellStatus}"/>
										</td>
										<td width="12%">
											<c:if test="${product.status != STATUS_DRAFT }">
												<div class="shenhe">
			                    					<spring:message code="product.check.${product.status}"/>
<%-- 			                    					<c:if test="${product.status == status_no_pass }"> --%>
<%-- 				                    					<div class="not-through">${product.remark }</div> --%>
<%-- 			                    					</c:if> --%>
			                    				</div>
			                    			</c:if>
										</td>
										<td width="12%">
			                    			<spring:message code="product.sellType.${product.sellType}"/>
										</td>
										<td width="12%">
											 <aut:authorize url="/seller/product/product-sellOut">
		                         				<c:if test="${product.status == STATUS_PASS 
		                         					&& product.sellStatus == STATUS_SELL  && product.inventory > 0}">
		                         					<p><a class="c-blue sellOut" href="javascript:;">售罄</a></p>
		                         				</c:if>
		                     				</aut:authorize>
											<aut:authorize url="/seller/product/product-delete">
												<c:choose>
													<c:when test="${product.status == STATUS_PASS && product.sellStatus == STATUS_SELL }"></c:when>
													<c:otherwise>
														<p><a class="c-blue delete" href="javascript:;">删除</a></p>
													</c:otherwise>
												</c:choose>
	                        				 </aut:authorize>
	                         				<aut:authorize url="/seller/product/product-edit">
	                         					<p><a class="c-blue edit" href="javascript:;">编辑</a></p>
	                         				</aut:authorize>
										</td>						
									</tr>
								</table>
							</td>
						</tr>
					</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
		<div class="table-bm-wrap cf">
			<!--S 右功能区-->
			<div class="fn-right">
				<!--S 分页-->
				<div class="pagination pagination-right">
					<c:if test="${not empty productPage.result}">
						<yp:commPage page="${productPage}" beanName="productPage"></yp:commPage>
					</c:if>
				</div>
				<!--E 分页-->
			</div>
			<!--E 右功能区-->
		</div>
	</div>
	<jsp:include page="/WEB-INF/pages/include/foot.jsp"></jsp:include>
	<script type="text/javascript" src="${_jsPath }/pages/searchSellerProducts.js"></script>
</body>
<script type="text/javascript">
	var isPost = true;
	$(document).ready(function() {
		$(".tr-height").on("click", function(e) {
			var $this = $(this),
			evt = $(e.target);
			if(!evt.is('a')){
				var productId = $this.attr('productId');
				var status = $this.attr('sellStatus');
				if (!$(this).next(".show-tr")[0]) {
					if( isPost == false) return;
					isPost = false;
					$.ajax({
						type : 'POST',
						url : '${_ctxPath}/admin/product/product-listProductSkuDetail.htm',
						data : {
							"product.productId" : productId,
							"product.sellStatus" : status,
							"mark" : 1
						},
						success : function(html) {
							isPost = true;
							var content;
							if (typeof html == "object") {
								data = eval(html);
								content = data.info;
							} else {
								content = html;
							}
							$($this).after($(content));
							$this.addClass("change");
							
						},
						error:function(){
							isPost = true;
						}
					});
				}
			}
		});
	});
</script>
</html>