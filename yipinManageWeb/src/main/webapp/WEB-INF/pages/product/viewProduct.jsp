<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.ytoxl.module.yipin.base.dataobject.Product"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看商品</title>
	<script src="${_jsPath }/jquery/jquery.1.8.1.js" language="javascript"></script>
	<link href="${_cssPath }/common.css" rel="stylesheet" />
  	<link href="${_cssPath }/pages/addProduct.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${_cssPath }/pages/produce-detail.css">
<body>
	<!--start header-->
	<jsp:include page="../include/header.jsp"></jsp:include> 
    <!--end header-->
    <!--start body-->
    <div class="body m-w980p">
	    <div class="produce-top cf">
	    	<input type="hidden" id="productId" value="${product.productId }">
	    	<table border="0" class="addPro-table">
	    		<tr>
					<td  class="left-td">销售方式：</td>
					<td>
						<spring:message code="product.sellType.${product.sellType}"/>
					</td>
				</tr>
				<c:if test="${product.sellType == 2}">
					<tr>
						<td class="left-td">预发货日期：</td>
						<td>
							<fmt:formatDate value="${product.preDeliveryTime }" pattern="yyyy-MM-dd"/>	
						</td>
					</tr>
				</c:if>
				<tr>
					<td class="left-td">品牌：</td>
					<td>
						${product.brand.name }
					</td>
				</tr>
				<tr>
					<td class="left-td">所在地区：</td>
					<td>
						${product.propsMap.area[0].name } &nbsp;&nbsp;${product.propsMap.area[1].name }
					</td>
				</tr>
				<tr>
					<td class="left-td">分类：</td>
					<td>
						${product.propsMap.catalog[0].name } &nbsp;&nbsp;${product.propsMap.catalog[1].name }
					</td>
				</tr>
				<tr>
					<td class="left-td">适宜人群：</td>
					<td>
						<c:forEach items="${product.propsMap.adaptPeople }" var="people">
							${people.name }
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td class="left-td">商品名称：</td>
					<td>
						${product.name }
					</td>
				</tr>
				<tr>
					<td class="left-td">推荐理由：</td>
					<td>
						${product.recommendedReason }
					</td>
				</tr>
				<tr>
					<td class="left-td">库存：</td>
					<td><spring:message code="product.isShowInventory.${product.isShowInventory}"/></td>
				</tr>
				<tr valign="top">
					<td class="left-td"></td>
					<td colspan="3" class="small">
						<table>
							<tr>
								<td width="450">
									<table class="repertories" width="630" class="small">
										<tr>
											<th width="12%">规格</th>
											<th width="12%">重量</th>
											<th width="12%">库存数</th>
											<th width="26%">SKU编码</th>
											<th width="26%">售卖价</th>
											<th width="12%">原价</th>
										</tr>
										<c:forEach items="${product.productSkus }" var="sku">
										<tr>
											<td>${sku.skuSpecification }</td>
											<td>${sku.skuWeight }</td>
											<td>${sku.inventory }</td>
											<td>${sku.sellerSkuCode }</td>
											<td>${sku.unitPrice }</td>
											<td>${sku.marketPrice }</td>
										</tr>
									</c:forEach>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr valign="top">
					<td class="left-td">商品封面：</td>
					<td colspan="3">
						<table class="no-border-table">
							<tr>
								<td width="120">
									<div class="default-img">
										<img src="<yp:thumbImage originalPath='${product.coverPicture}' imageType='t84'></yp:thumbImage>" />
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr valign="top">
					<td class="left-td">商品图片：</td>
					<td colspan="3">
						<table class="no-border-table">
							<tr>
								<c:forEach items="${product.imageList }" var="image">
									<td width="120">
										<div class="default-img">
											<img src="<yp:thumbImage originalPath='${image}' imageType='t84'></yp:thumbImage>" />
										</div>
									</td>
								</c:forEach>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>商品描述：</td>
					<td width="900px;">
						<div style="width:900px;overflow:auto">
							${product.description }
						</div>
				</tr>
			</table>
		</div>
		<c:set var="notPass" value="<%= Product.STATUS_NO_PASS %>"></c:set>
        <c:set var="checkPend" value="<%= Product.STATUS_CHECK_PEND %>"></c:set>
        <c:if test="${product.status == notPass || product.status==checkPend && mark ==2}">
	        <div class="goods-check">
	            <label>不通过原因：<br> (200字符以内)</label>
	            <textarea class="text-area input-marks" id="remark" >${product.remark }</textarea>
	            <span id="remarkTip" style="display: none;"><font color="red">请填写不通过原因</font></span>
	        </div>
        </c:if> 
        <div class="btn-check">
        	<aut:authorize url="/admin/product/product-review">
        		<c:if test="${product.status == checkPend && mark == 2}">
           			<input type="button" class="m-btn" value="审核通过" id="pass"/>
            		<input type="button" class="m-btn" value="审核不通过" id="notPass"/>
        		</c:if>
        	</aut:authorize> 
            <input type="button" class="m-btn" value="返   回" id="back"/>
        </div>
	</div>
    <!--footer.jsp放置在这-->
    <jsp:include page="/WEB-INF/pages/include/foot.jsp"></jsp:include>
	<script type="text/javascript" src="${_jsPath }/pages/viewProduct.js"></script>
	<script type="text/javascript">
	 	var STATUS_PASS = <%= Product.STATUS_PASS%>;
	    var STATUS_NO_PASS = <%= Product.STATUS_NO_PASS%>;
	</script>
</body>
</html>