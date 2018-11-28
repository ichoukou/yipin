<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<!DOCTYPE HTML>
<html lang="UTF-8">
<head>
	<meta charset="UTF-8">
	<c:if test="${empty seoTitle }">
   	<title>
		<c:choose>
			<c:when test="${type==typeYuanChanDi}">
				原产地
			</c:when>
			<c:when test="${type==typeFenLei}">
				商品分类
			</c:when>
			<c:when test="${type==typeQiangGou}">
				抢购专区
			</c:when>
			<c:when test="${type==typeYuShou}">
				预售专区
			</c:when>
			<c:when test="${type==typePuTong}">
				普通专区				
			</c:when>
			<c:otherwise></c:otherwise>
		</c:choose>
	</title>
	</c:if>
	<c:if test="${!empty seoTitle }">
	<title>${seoTitle}</title>
	</c:if>
	<c:if test="${empty seoKeyWords }">
   	<meta name="keywords" content="1" >
	</c:if>
	<c:if test="${!empty seoKeyWords }">
	<meta name="keywords" content="${seoKeyWords}">
	</c:if>
	<c:if test="${empty seoDescription }">
   	<meta name="description" content="1" >
	</c:if>
	<c:if test="${!empty seoDescription}">
	<meta name="description" content="${seoDescription}">
	</c:if>
	<jsp:include page="/WEB-INF/pages/include/common.jsp"></jsp:include>
	<!--page css-->
	<link rel="stylesheet" type="text/css" href="${_cssPath}/pages/productList.css" media="all" />
</head>
<body>
	<!--头部导航 start-->
	 <jsp:include page="/WEB-INF/pages/include/head.jsp"></jsp:include>
	<!--头部导航 end-->
	<div class="w_norm">
		<!--面包屑 start-->
		<div class="crumb">
			<c:choose>
  				<c:when test="${type==typeYuanChanDi||type==typeFenLei}">
  					<a href="${_ctxPath }/index.htm">首页</a>
  					<c:forEach items="${props}" var="prop">
  						<span class="arrow">></span><a href="listproduct-${type}-${propId}-${zoneId}-${url_sort_100}-${url_dir_100001}-${productSkuPage.total}-0.htm">${prop.name}</a>
  					</c:forEach>
  				</c:when>
  				<c:when test="${type==typeQiangGou}">
  					<a href="${_ctxPath }/index.htm">首页</a><span class="arrow">></span><a href="">抢购专区</a>
  				</c:when>
  				<c:when test="${type==typeYuShou}">
  					<a href="${_ctxPath }/index.htm">首页</a><span class="arrow">></span><a href="">预售专区</a>
  				</c:when>
  				<c:when test="${type==typePuTong}">
  					<a href="${_ctxPath }/index.htm">首页</a><span class="arrow">></span><a href="">${zone.name}</a>				
  				</c:when>
  				<c:otherwise></c:otherwise>
			</c:choose>
		</div>
		<!--面包屑 end-->
		<div class="pro_list">
		<!-- 排序层 start-->
		<div class="rank_wrap cf">
			<div class="page_nav">
				<span><a href="javascript:;" class="next"></a></span>
				<span><a href="javascript:;" class="prev"></a></span>
				<span class="J_pagenum cur" class="cur"><i></i>/<i></i></span>
			</div>
			<div class="s_elect">
				<label>
				<input type="checkbox" id="isHaveInventory" onclick="listByIsHaveInventory();">
				仅显示有货</label>
			</div>
			<div class="rank">
				<span id="tab1" class="cur">
					<a href="${_ctxPath }/listproduct-${type}-${propId}-${zoneId}-${url_sort_100}-${url_dir_100001}-${productSkuPage.total}-0.htm" class="down">按默认</a>
				</span>
				<span id="tab2" class="">
					<a href="${_ctxPath }/listproduct-${type}-${propId}-${zoneId}-${url_sort_200}-${url_dir_100001}-${productSkuPage.total}-0.htm" class="down">按销量</a>
				</span>
				<span id="tab3" class="">
					<a href="${_ctxPath }/listproduct-${type}-${propId}-${zoneId}-${url_sort_300}-${url_dir_100001}-${productSkuPage.total}-0.htm" class="down">按浏览量</a>
				</span>
				<span id="tab4" class="r_spri">
					<a onclick="listByPrice();" href="javascript:;" class="down">按价格</a>
				</span>
			</div>
		</div>
		<!-- 排序层 end-->
		<!--新品 end-->
		<c:choose>
			<c:when test="${type==typeQiangGou}">
				<div class="gd_item J_hover" id="panic_buy">
			</c:when>
			<c:when test="${type==typeYuShou}">
				<div class="gd_item J_hover" id="pre_sale">
			</c:when>
			<c:when test="${type==typePuTong}">
				<div class="gd_item J_hover" id="nor_sale">
			</c:when>
			<c:otherwise>
				<div class="gd_item J_hover" id="newpro_online">
			</c:otherwise>
		</c:choose>
			<div class="bd">
				<ul>
					<c:forEach items="${productSkus}" var="sku" varStatus="skuStatus">
						<li data-productskuid=${sku.productSkuId} data-inventory=${sku.inventory}>
							<c:if test="${not empty sku.product.recommendedReason}">
								<div class="b_reason">
									<h5>推荐理由：</h5>
									<c:choose>
	   									<c:when test="${fn:length(sku.product.recommendedReason)>50}">
	   										${fn:substring(sku.product.recommendedReason,0,50)}...</a>
	   									</c:when>
	   									<c:otherwise>
	   										${sku.product.recommendedReason}
	   									</c:otherwise>	
	   								</c:choose>
								</div>
							</c:if>
							<c:choose>
								<c:when test="${type==typeQiangGou}">
									<div class="s_remain_count">
										<span class="J_countdown" data-config="{
										'startTime':${sku.saleBeginTime.time}/1000,
										'endTime':${sku.saleEndTime.time}/1000,
										'nowTime':<%=System.currentTimeMillis()/1000 %>,
										'startTips':'<span>距离开抢时间：</span>',
										'endTips':'<span>剩余时间：</span>',
										'stopTips':'<span>已过期</span>',
										'timeStampTow':true
										}"></span>
									</div>
								</c:when>
								<c:when test="${type==typeYuShou}">
									<div class="s_remain">
									预计发货时间：<span><fmt:formatDate value='${sku.product.preDeliveryTime}' pattern='yyyy-MM-dd' /></span><br/>可预售数:<span>${sku.inventory}</span>
									</div>
								</c:when>
								<c:otherwise></c:otherwise>
							</c:choose>
							<div class="s_pic">
   								<a href="${_ctxPath }/item-${sku.productSkuId}-${type}-${zoneId}.htm"><img "224" width="224" src="${_filePath}/${sku.product.coverPicture}" alt="" /></a>
							</div>
							<div class="s_meta">
								  <p class="pro_tit">
									<a href="${_ctxPath }/item-${sku.productSkuId}-${type}-${zoneId}.htm" target="_blank" title="${sku.product.name}">
	   								<c:choose>
	   									<c:when test="${fn:length(sku.product.name)>32}">
	   										${fn:substring(sku.product.name,0,32)}...</a>
	   									</c:when>
	   									<c:otherwise>
	   										${sku.product.name}</a>
	   									</c:otherwise>	
	   								</c:choose>		
								</p>
								<p>产地：
									<c:forEach items="${sku.props}" var="prop" varStatus="propStatus">
										<c:choose>
	   										<c:when test="${fn:length(sku.props)==propStatus.index+2}">${prop.name} | </c:when>
	   										<c:otherwise>${prop.name}</c:otherwise>
										</c:choose> 
									</c:forEach>
								</p>
								<p>规格： 
									<c:choose>
										<c:when test="${not empty sku.overrideSkuOptionValue}">
											<c:choose>
			   									<c:when test="${fn:length(sku.overrideSkuOptionValue)>7}">
			   										${fn:substring(sku.overrideSkuOptionValue,0,7)}...</a>
			   									</c:when>
			   									<c:otherwise>
			   										${sku.overrideSkuOptionValue}</a>
			   									</c:otherwise>	
			   								</c:choose>
										</c:when>
										<c:otherwise>
											<c:choose>
			   									<c:when test="${fn:length(sku.skuOptionValue)>7}">
			   										${fn:substring(sku.skuOptionValue,0,7)}...</a>
			   									</c:when>
			   									<c:otherwise>
			   										${sku.skuOptionValue}</a>
			   									</c:otherwise>	
			   								</c:choose>
										</c:otherwise>
									</c:choose></p>
								<p class="tar s_pri"><span class="fl"><em>一品价：</em><var>￥${sku.unitPrice}</var></span><del>￥${sku.marketPrice}</del></p>
							</div>
							<div class="s_opr">
								<c:choose>
  									<c:when test="${type==typeQiangGou}">
  										<c:choose>
  											<c:when test="${sku.inventory==0}">
  												<a href="javascript:;" class="btn_panic_out">已抢光</a>
  											</c:when>
  											<c:when test="${sku.saleEndTime.time<currentTimestamp}">
  												<a href="javascript:;" class="btn_panic_out">已过期</a>
  											</c:when>
  											<c:otherwise>
  												<a href="${_ctxPath}/order/order-getOrderInfo.htm?params.skuId=${product.productSku.productSkuId}" class="g_order" target="_blank">立即抢购</a>
  											</c:otherwise>
  										</c:choose>
  									</c:when>
  									<c:when test="${type==typeYuShou}">
	  									<c:choose>
 											<c:when test="${sku.inventory==0}">
 												<a href="javascript:;" class="btn_sold_out">已售罄</a>
 											</c:when>
 											<c:otherwise>
 												<a href="javascript:;" class="g_pres J_g_presale">立即预订</a>
 											</c:otherwise>
										</c:choose>
  									</c:when>
  									<c:otherwise>
  										<c:choose>
 											<c:when test="${sku.inventory==0}">
 												<a href="javascript:;" class="btn_sold_out">暂时无货</a>
 											</c:when>
 											<c:otherwise>
 												<div class="J_add_cart add_cart cf">
													<div class="fl num_vary">
														<span class="less J_less">-</span><input type="text" class="input-text J_input" value="1"><span class="more J_more">+</span>
													</div>
													<a href="javascript:;" class="fr g_cart J_g_cart">加入购物车</a>
												</div>
												<div class="J_see_cart see_cart cf">
													<span class="fl add_suc">添加成功</span>
													<a href="javascript:;" class="fr g_order">查看购物车</a>
												</div>
 											</c:otherwise>
										</c:choose>
  									</c:otherwise>
								</c:choose>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div class="table-bm-wrap cf">
				<div class="fn-right">
					<div class="pagination pagination-right">
						<yp:yipinFrontPage urlEnd=".htm" urlStart="listproduct-${type}-${propId}-${zoneId}-${productSkuPage.sort}-${productSkuPage.dir}-${productSkuPage.total}-" page="${productSkuPage}"></yp:yipinFrontPage>
					</div>
				</div>
			</div>
		</div>
		<!--新品 end-->
		</div>
	</div>
	<!--悬浮购物车 s -->
	<%@include file="/WEB-INF/pages/order/miniCart.jsp"%>
	<!--悬浮购物车 e -->
	<!-- 底部 start -->
	<%@include file="/WEB-INF/pages/include/foot.jsp"%>
	<!--底部 end-->	
	<!--page js-->
	<script type="text/javascript" src="${_jsPath }/pages/productList.js"></script>
	<script type="text/javascript">
 
		//设置只显示有库存商品复选框
		if(${productSkuPage.params.isHaveInventory==1}){
			$("#isHaveInventory").attr("checked",true);
		} else{
			$("#isHaveInventory").attr("checked",false);
		}
		//设置查询TAB默认选中
		 var tab=${productSkuPage.params.orderType};
		 //alert("orderType:"+tab);
		 if(tab==1){//按销量从高到低
			 $("#tab1").attr("class","");
			 $("#tab2").attr("class","cur");
			 $("#tab3").attr("class","");
			 $("#tab4").attr("class","r_spri");
		 }else if(tab==2){//按浏览量从高到低
			 $("#tab1").attr("class","");
			 $("#tab2").attr("class","");
			 $("#tab3").attr("class","cur");
			 $("#tab4").attr("class","r_spri");
		 }else if(tab==3){//按价格从低到高
			 $("#tab1").attr("class","");
			 $("#tab2").attr("class","");
			 $("#tab3").attr("class","");
			 $("#tab4").attr("class","r_spri cur");
		 }else if(tab==4){//按价格从高到低
			 $("#tab1").attr("class","");
			 $("#tab2").attr("class","");
			 $("#tab3").attr("class","");
			 $("#tab4").attr("class","r_spri");
		 }else{//按默认
			 $("#tab1").attr("class","cur");
			 $("#tab2").attr("class","");
			 $("#tab3").attr("class","");
			 $("#tab4").attr("class","r_spri");
		 }
		 
		 /**
		 ** 根据价格查询
		 **/
		 function listByPrice(){
			 var cls=$("#tab4").attr("class");
			 //alert(cls);
			 var dir;
			 if(cls=="r_spri"){
				 //alert("1");
				 dir='${url_dir_100000}';
			 }else{
				 //alert("2");
				 dir='${url_dir_100001}';
			 }
			 var url="${_ctxPath }/listproduct-${type}-${propId}-${zoneId}-${url_sort_400}-"+dir+"-${productSkuPage.total}-0.htm";
		 	 //alert(url);
			 document.location.href=url;
		 }
		 
		 /**
		 ** 仅显示有货查询
		 **/
		 function listByIsHaveInventory(){
			 var checked=$("#isHaveInventory").attr("checked");
			 var isHaveInventory;
			 
			 if(checked){
				 isHaveInventory=1;
			 }else{
				 isHaveInventory=0;
			 }
			 
			 //alert(isHaveInventory);
			 var type=${type};
			 var sort;
			 var dir;
			  
			 //alert("tab:"+tab);
			 if(tab==1){//按销量,降序
				dir='${url_dir_100001}';
				sort='${url_sort_200}';
			 }else if(tab==2){//按浏览量,降序
				 dir='${url_dir_100001}';
				 sort='${url_sort_300}';
			 }else if(tab==3){//按价格,升序
				 dir='${url_dir_100000}';
				 sort='${url_sort_400}';
			 }else if(tab==4){//按价格,降序
				 dir='${url_dir_100001}';
				 sort='${url_sort_400}';
			 }else{//按默认,降序
				 dir='${url_dir_100001}';
				 sort='${url_sort_100}';
			 }
			 
			 //alert("sort:"+sort+", dir:"+dir);
			 var url="${_ctxPath }/listproduct-${type}-${propId}-${zoneId}-"+sort+"-"+dir+"-${productSkuPage.total}-0.htm?isHaveInventory="+isHaveInventory;
			 document.location.href=url;
		 }

		$(function(){
			//为订单左右分页添加样式
			var _next = $('.navigation .next_page').get(0);
			if(undefined != _next){
				$('.page_nav .next').parent().addClass('on');
			}
			var _prev = $('.navigation .prev_link').get(0);
			if(undefined != _prev){
				$('.page_nav .prev').parent().addClass('on');
			}
		});
	</script>
</body>
</html>