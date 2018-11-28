<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<%@page import="com.ytoxl.module.core.common.utils.SpringContextUtils"%>
<%@page import="com.ytoxl.module.yipin.base.service.BrandService"%>
<%@page import="com.ytoxl.module.yipin.base.service.ProductService"%>
<%@page import="com.ytoxl.module.yipin.content.service.SeoConfigService"%>
<% 
	//获取品牌信息
    BrandService brandService=(BrandService)(SpringContextUtils.getContext().getBean("brandServiceImpl")); 
 	pageContext.setAttribute("brand",brandService.listBrands()); 
 	//获取seo信息
 	SeoConfigService seoConfigService = (SeoConfigService)(SpringContextUtils.getContext().getBean("seoConfigServiceImpl"));
 	String[] seoInfo = seoConfigService.getSeoConfigByUrlKey("/index", null);
 	if(seoInfo!=null){
 	 	pageContext.setAttribute("seoTitle", seoInfo[0]);
 	 	pageContext.setAttribute("seoKeyWords", seoInfo[1]);
 	 	pageContext.setAttribute("seoDescription", seoInfo[2]);
	}
 	//获取商品信息
 	ProductService productService=(ProductService)(SpringContextUtils.getContext().getBean("productServiceImpl"));
 	pageContext.setAttribute("map", productService.listProductSkusBySkuCode());
 %> 
<!DOCTYPE html>
<html lang="zh-CN" class="ua-window">
<head>
    <c:if test="${empty seoTitle }">
   	<title>首页-${_webSiteName }</title>
	</c:if>
	<c:if test="${!empty seoTitle }">
	<title>${seoTitle}</title>
	</c:if>
	<c:if test="${empty seoKeyWords }">
   	<meta name="keywords" content="" >
	</c:if>
	<c:if test="${!empty seoKeyWords }">
	<meta name="keywords" content="${seoKeyWords}">
	</c:if>
	<c:if test="${empty seoDescription }">
   	<meta name="description" content="" >
	</c:if>
	<c:if test="${!empty seoDescription}">
	<meta name="description" content="${seoDescription}">
	</c:if>
    <jsp:include page="/WEB-INF/pages/include/common.jsp"></jsp:include>
	<link rel="stylesheet" type="text/css" href="${_cssPath}/pages/index.css" media="all" />
</head>
<body>
	<!--头部导航 start-->
 	<jsp:include page="/WEB-INF/pages/include/head.jsp"></jsp:include>
 	<!--头部导航 end-->
 	<!--焦点图 start-->
	<div class="focus">
		<div class="show_wp">
			<!-- 西湖龙井 -->
			<div class="pro_show" style="background-image:url('${_filePath}${brand[0].brandImageUrl}');background-position:center top;background-repeat:no-repeat;"> 
				<div class="w_norm relative">
					<dl>
					    <dt>${brand[0].name }</dt>
					    <dd class="price">
					    ${map['HZLG01'].unitPrice }元/<span>150g*2</span>
<%-- 					    <br />${map['HZLG02'].unitPrice }元/<span>250g*2</span> --%>
					    </dd>
					    <dd>茶有“四绝”：形美、色翠、香郁、味甘。<br />
集名山、名寺、名湖、名泉和名茶于一体的一杯龍井茶，<br />喝出的却是独特而骄人的千年茶文化。
					    </dd>
			      		<dd>
			      			<a target="_blank" href="${_ctxPath}/item-${brand[0].brandId}.htm" target="_blank" rel="nofollow">
			      			<img src="${_imagesPath}/btn/readDetail.png" alt="" />
			      			</a>
			      		</dd>
					</dl>
				</div>
			</div>
			<!-- 猴头菇 -->
			<div class="pro_show" style="background-image:url('${_filePath}${brand[1].brandImageUrl}');background-position:center top;background-repeat:no-repeat;"> 
				<div class="w_norm relative">
					<dl>
					    <dt>${brand[1].name }</dt>
					    <dd class="price">
					    ${map['JLHG01'].unitPrice }元/<span>250g</span>
<%-- 					    <br />${map['JLHG02'].unitPrice }元/<span>500g</span> --%>
					    </dd>
					    <dd>“物以稀为贵”，猴头菇的药用、食用功效已流传百世，<br />其美味的口感和强身健体的效用在民间广为流传。</dd>
			      		<dd>
			      			<a target="_blank" href="${_ctxPath}/item-${brand[1].brandId}.htm" target="_blank" rel="nofollow">
			      			<img src="${_imagesPath}/btn/readDetail.png" alt="" />
			      			</a>
			      		</dd>
					</dl>
				</div>
			</div>
			<!-- 木耳 -->
			<div class="pro_show" style="background-image:url('${_filePath}${brand[2].brandImageUrl}');background-position:center top;background-repeat:no-repeat;"> 
				<div class="w_norm relative">
					<dl>
					    <dt>${brand[2].name }</dt>
					    <dd class="price">
					     ${map['JLME01'].unitPrice }元/<span>250g</span>
<%-- 					     <br />${map['JLME02'].unitPrice }元/<span>500g</span> --%>
					    </dd>
					    <dd>野生黑木耳不仅是美味菜肴，而且具有多种药用价值。<br />益气强身、滋肾养胃，使血液流动顺畅，减少心血管病发生。</dd>
			      		<dd>
			      			<a target="_blank" href="${_ctxPath}/item-${brand[2].brandId}.htm" target="_blank" rel="nofollow">
			      			<img src="${_imagesPath}/btn/readDetail.png" alt="" />
			      			</a>
			      		</dd>
					</dl>
				</div>
			</div>
			<!-- 雪蛤油 -->
			<div class="pro_show" style="background-image:url('${_filePath}${brand[3].brandImageUrl}');background-position:center top;background-repeat:no-repeat;"> 
				<div class="w_norm relative">
					<dl>
					    <dt>${brand[3].name }</dt>
					    <dd class="price">
					    ${map['JLWY01'].unitPrice }元/<span>5g</span>
<%-- 					    <br />${map['JLWY02'].unitPrice }元/<span>10g</span> --%>
					    </dd>
					    <dd>雪蛤油又名林蛙油。泡发后洁白细腻，如冰似雪，营养价值极高，<br />为宫廷八珍之一，集药食补为一体的珍品，被称为“绿色软黄金”。</dd>
			      		<dd>
			      			<a target="_blank" href="${_ctxPath}/item-${brand[3].brandId}.htm" target="_blank" rel="nofollow">
			      			<img src="${_imagesPath}/btn/readDetail.png" alt="" />
			      			</a>
			      		</dd>
					</dl>
				</div>
			</div>
		</div>
		<div class="arrow" style="left:0;"><a href="javascript:;" class="prev"></a></div>
		<div class="arrow" style="right:0;"><a href="javascript:;" class="next"></a></div>
	</div>
	<!--焦点图 end-->
	<!--底部 start-->
	<%@include file="/WEB-INF/pages/include/foot.jsp"%>
	<!--底部 end-->
	<!--page js-->
	<script type="text/javascript" src="${_jsPath }/pages/index.js"></script>
</body>
</html>