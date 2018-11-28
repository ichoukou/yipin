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
<script src="${_jsPath }/jquery/jquery.1.8.1.js"  type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	$("#search").on("click",function(){
			var keywords = $(".J-keywords").val();
			if(keywords == $(".J-keywords").attr("data-default")){
				$(".J-keywords").val("");
			}
			$(".sub-form").submit();
		});
	//查看
	$(".view").click(function(){
		var productId = $(this).closest("tr").attr("productId");
		window.location.href = _ctxPath +"/admin/product/product-view.htm?product.productId="+productId+"&mark=1";
	});
	//审核
	$(".review").click(function(){
		var productId = $(this).closest("tr").attr("productId");
		window.location.href = _ctxPath + "/admin/product/product-review.htm?product.productId="+productId+"&mark=2";
	});
});
</script>
</head>
 <c:set var="STATUS_DRAFT" value="<%=Product.STATUS_DRAFT %>"></c:set>
    <c:set var="STATUS_PASS" value="<%=Product.STATUS_PASS %>"></c:set>
    <c:set var="CHECK_PEND" value="<%= Product.STATUS_CHECK_PEND %>"></c:set>
    <c:set var="status_no_pass" value="<%= Product.STATUS_NO_PASS %>"></c:set>
<body>
	<jsp:include page="../include/header.jsp" flush="true" />
	<div class="body m-w980p">
	 <!--start form-->
        <form class="sub-form m-clear m-mt10p" action="${_ctxPath}/admin/product/product-searchProducts.htm" method="post">
    <aut:authorize url="/admin/product/product-search">
            <div class="m-fl">
                <select name="productPage.params.brandId" id="brand" class="m-sel">
                   <option value="">全部品牌</option>
                           	<c:forEach items="${brands }" var="brand">
								<option value="${brand.brandId }"
								<c:if test="${productPage.params.brandId==brand.brandId }">
								selected = "selected"
								</c:if>
								>${brand.name }</option>
							</c:forEach>
                </select>
                <select name="productPage.params.status" class="m-sel">
                	<option value="">选择商品状态</option>
							<c:forEach items="${statuses }" var="obj">
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
        </form>
		<!--start 商品管理table-->
		<div class="order-management">
			<table width="100%" id="order-tab">
				<thead class="tab-control">
                    <tr>
                        <th width="12%">品牌</th>
                        <th width="20%">商品名称</th>
                        <th width="8%">商品编码</th>
                        <th width="8%">图片</th>
                        <th width="16%">商品状态</th>
                        <th width="8%">审核状态</th>
                        <th width="8%">售卖类型</th>
                        <th width="8%">操作</th>
                    </tr>
                </thead>
				<tbody>
					<c:if test="${fn:length(productPage.result)>0 }">
							<c:forEach items="${productPage.result}"  var="product" varStatus="status">
								<tr class="list-tr">
									<td colspan="8" class="td-nobor">
										<table class="tab-control">
											<tr class="tr-height" productId="${product.productId }">
												<td class="num-icon" width="12%">
													<i></i><label><a>${product.brand.name }</a></label>
												</td>
												<td width="20%">${product.name}</td>
												<td width="8%">${product.productId }</td>
												<td width="8%">
													<img src="<yp:thumbImage originalPath='${product.coverPicture}' imageType='t84'></yp:thumbImage>" />
												</td>
												<td width="16%">
													<spring:message code="product.status.${product.sellStatus}"/>
												</td>
												<td width="8%">
													<c:if test="${product.status != STATUS_DRAFT }">
					                    				<spring:message code="product.check.${product.status}"/>
					                    			</c:if>
												</td>
												<td width="8%">
					                    			<spring:message code="product.sellType.${product.sellType}"/>
												</td>
												<td width="8%">
													<p>
					                     				<aut:authorize url="/admin/product/product-view">
					                           				<a class="c-blue view" href="javascript:;">查看</a>
					                       		 		</aut:authorize>
				                        			</p>
				                       				 <c:if test="${product.status == CHECK_PEND}">
				                           				<p>
					                       					<aut:authorize url="/admin/product/product-review">
					                           					<a class="c-blue review" href="javascript:;">审核</a>
					                        				</aut:authorize>
				                           				</p>
				                        			</c:if>
												</td>						
											</tr>
										</table>
									</td>
								</tr>
							</c:forEach>
					</c:if>
					<c:if test="${fn:length(productPage.result)==0 }">
						<tr class="list-tr">
							<td colspan="7" class="td-nobor" align="center">
								对不起，没有找到相关商品信息，请重新选择查询条件
							</td>
						</tr>
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
</body>
<script type="text/javascript">
	var isPost = true;
	$(document).ready(function() {
		$(".tr-height").on("click", function(e) {
			var $this = $(this),
				evt = $(e.target);
			if(!evt.is('a')){
				var productId = $this.attr('productId');
				if (!$(this).next(".show-tr")[0]) {
					if( isPost == false) return;
					isPost = false;
					$.ajax({
						type : 'POST',
						url : '${_ctxPath}/admin/product/product-listProductSkuDetail.htm',
						data : {
							"product.productId" : productId,
							"mark" : 2
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