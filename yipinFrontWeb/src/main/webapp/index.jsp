<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.ytoxl.module.yipin.base.dataobject.Notice"%>
<%@page import="com.ytoxl.module.yipin.content.dataobject.AdvPosition"%>
<%@page import="com.ytoxl.module.yipin.content.dataobject.Advertisement"%>
<%@page import="com.ytoxl.module.core.common.utils.SpringContextUtils"%>
<%@page import="com.ytoxl.module.yipin.base.service.ZoneService"%>
<%@ page import="com.ytoxl.module.yipin.content.service.AdvertisementService"%>
<%@page import="com.ytoxl.module.yipin.content.dataobject.AdvPosition"%>
<%@page import="com.ytoxl.module.yipin.content.service.NoticeService"%>
<%@page import="com.ytoxl.module.yipin.base.dataobject.ProductSku"%>
<%@page import="com.ytoxl.module.yipin.base.common.CommonConstants"%>
<%@page import="com.ytoxl.module.yipin.content.service.SeoConfigService"%>
<%
    ZoneService zoneService = (ZoneService)(SpringContextUtils.getContext().getBean("zoneServiceImpl"));
	NoticeService noticeService = (NoticeService)(SpringContextUtils.getContext().getBean("noticeServiceImpl"));
	pageContext.setAttribute("zones",zoneService.listAllZones());
	AdvertisementService advertisementService1 = (AdvertisementService)(SpringContextUtils.getContext().getBean("advertisementServiceImpl"));
	pageContext.setAttribute("adversePics",advertisementService1.selectAdvPic(AdvPosition.SHUFFLING_FIGURE));

	//最新动态
	List<Notice> noticeList=new ArrayList<Notice>();
	noticeList=noticeService.getHomePageNotices();
	pageContext.setAttribute("noticeList",noticeList);
	
	pageContext.setAttribute("pend",ProductSku.SALE_STATUS_PEND);
	pageContext.setAttribute("on",ProductSku.SALE_STATUS_ON);
	pageContext.setAttribute("stockout",ProductSku.SALE_STATUS_STOCKOUT);
	pageContext.setAttribute("expired",ProductSku.SALE_STATUS_EXPIRED);
	
	pageContext.setAttribute("qiangGou",CommonConstants.TYPE_QIANGGOU);
	pageContext.setAttribute("yuShou",CommonConstants.TYPE_YUSHOU);
	pageContext.setAttribute("puTong",CommonConstants.TYPE_PUTONG);
	
	//获取seo信息
 	SeoConfigService seoConfigService = (SeoConfigService)(SpringContextUtils.getContext().getBean("seoConfigServiceImpl"));
 	String[] seoInfo = seoConfigService.getSeoConfigByUrlKey("/index", null);
 	if(seoInfo!=null){
 	 	pageContext.setAttribute("seoTitle", seoInfo[0]);
 	 	pageContext.setAttribute("seoKeyWords", seoInfo[1]);
 	 	pageContext.setAttribute("seoDescription", seoInfo[2]);
	}
%>
<!DOCTYPE html>
<html>
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
	<!--page css-->
	<jsp:include page="/WEB-INF/pages/include/common.jsp"></jsp:include>
	<link rel="stylesheet" type="text/css" href="${_cssPath}/pages/index.css" media="all" />
</head>
<body>
	<!--头部 start-->
	<!--顶部bar start-->
	<jsp:include page="/WEB-INF/pages/include/head.jsp"></jsp:include>
	<!--导航 end-->
	<!--头部 end-->
	<div class="w_norm">
		<!--单张广告图 start-->
		<c:if test="${fn:length(adversePics) == 1}">
		<div class="mt20">
           <a href="${adversePics[0].url}" title="" target="_blank"><img src="${_filePath }${adversePics[0].imageUrl}" width="1200" height="420" alt="Slide 1"></a>
       	</div>
       	</c:if>
		<!--单张广告图 end-->
        <c:if test="${fn:length(adversePics) > 1}">
        <!--焦点图 start-->
		<div class="slides" id="slides">
			<div class="slides_container"> 
             <c:if test="${fn:length(adversePics) <= 5}">
                  <c:forEach items="${adversePics}" var="adv">
				     <div class="pic">
				 		<a href="${adv.url}" title="" target="_blank"><img src="${_filePath }${adv.imageUrl}" width="1200" height="420" alt="Slide 1"></a>
				 	</div>
				 </c:forEach>
             </c:if>
             <c:if test="${fn:length(adversePics) > 5}">
                 <div class="pic"><a href="${adversePics[0].url}" title="" target="_blank"><img src="${_filePath }${adversePics[0].imageUrl}" width="1200" height="420" alt="Slide 1"></a></div>
				 <div class="pic"> <a href="${adversePics[1].url}" title="" target="_blank"><img src="${_filePath }${adversePics[1].imageUrl}" width="1200" height="420" alt="Slide 1"></a></div>
				 <div class="pic"><a href="${adversePics[2].url}" title="" target="_blank"><img src="${_filePath }${adversePics[2].imageUrl}" width="1200" height="420" alt="Slide 1"></a></div>
				 <div class="pic"><a href="${adversePics[3].url}" title="" target="_blank"><img src="${_filePath }${adversePics[3].imageUrl}" width="1200" height="420" alt="Slide 1"></a></div>
				 <div class="pic"> <a href="${adversePics[4].url}" title="" target="_blank"><img src="${_filePath }${adversePics[4].imageUrl}" width="1200" height="420" alt="Slide 1"></a></div>
             </c:if>
<%-- 			    <c:forEach items="${fn:substring(adversePics,0,5)}" var="adv"> --%>
<!-- 				     <div class="pic"> -->
<%-- 				 		<a href="${adv.url}" title="" target="_blank"><img src="${_filePath }${adv.imageUrl}" width="1200" height="420" alt="Slide 1"></a> --%>
<!-- 				 	</div> -->
<%-- 				 </c:forEach> --%>
			</div>
			<a href="#" class="prev"></a>
			<a href="#" class="next"></a>
		</div>
		<!--焦点图 end-->
		</c:if>
		<!--动态 start-->
		<div class="trend cf">
			<div class="tre_tit">最新动态：</div>
			<div class="tre_txt">
				<ul>
				    
					<c:forEach items="${noticeList}" var="notice">
					<li><a href="${_ctxPath}/notice/detail-${notice.noticeId}.htm" title="${notice.title}" target="_blank">${yipin:cutString(notice.title,15)}</a></li>
<%-- 						<c:choose> --%>
<%-- 							<c:when test="${fn:length(notice.title)<15}"> --%>
<%-- 								<li><a href="${_ctxPath}/notice/detail-${notice.noticeId}.htm" title="${notice.title}" target="_blank">${notice.title}</a></li> --%>
<%-- 							</c:when> --%>
<%-- 							<c:otherwise> --%>
<%-- 								<li><a href="${_ctxPath}/notice/detail-${notice.noticeId}.htm" title="${notice.title}" target="_blank">${fn:substring(notice.title,0,15)} ...</a></li> --%>
<%-- 							</c:otherwise> --%>
<%-- 						</c:choose> --%>
					</c:forEach>
				</ul>
			</div>
			<div class="tre_more">
				<a href="${_ctxPath }/notice/notice-noticeList.htm" target="_blank" title="更多动态">更多动态 ></a>
			</div>
		</div>
		<!--动态 end-->
		<c:forEach items="${zones}" var="z" varStatus="status">   
		   <c:if test="${z.zoneType == 1}">
		   	 <!--抢购 s-->
		     <div class="gd_item J_hover" id="panic_buy">
		     	<div class="hd cf">
			      <c:if test="${z.count > fn:length(z.products) }">
			         <a href="${_ctxPath }/listproduct-${qiangGou}-0-${z.zoneId}-${url_sort_100}-${url_dir_100000}-0-0.htm" class="more fr" target="_blank">更多 ></a>
			      </c:if>
		     	<span class="watches fl">${z.name }</span></div>
		       	<div class="bd">
					<ul>
					   <c:forEach items="${z.products }" var="product" varStatus="status">
					   <li data-url="${_ctxPath}/order/order-getOrderInfo.htm?params.skuId=${product.productSku.productSkuId}">
					       <c:if test="${product.productSku.saleStatus == stockout}">
					           <div class="sold_out"></div>
					       </c:if>
					       <c:if test="${product.productSku.saleStatus != stockout && null != product.recommendedReason && product.recommendedReason != ''}">
					          <div class="b_reason">
									<h5>推荐理由：</h5>
						               ${yipin:cutString(product.recommendedReason,50)}
								  </div>
					       </c:if>
					       <div class="s_remain_count">
					          	<%-- <c:if test="${product.productSku.saleStatus == pend}">
							                                距离开抢时间：
							    </c:if>
							    <c:if test="${product.productSku.saleStatus == on}">
							                                剩余时间：
							     </c:if> --%>
							<span class="J_countdown" sTime="0" data-config="{
							'startTime':${product.productSku.saleBeginTime.time}/1000,
							'endTime':${product.productSku.saleEndTime.time}/1000,
							'nowTime':<%=System.currentTimeMillis()/1000 %>,
							'startTips':'<span>距离开抢时间：</span>',
							'endTips':'<span>剩余时间：</span>',
							'stopTips':'<span>已过期</span>',
							'timeStampTow':true
							}"></span>
							</div>
		                  <div class="s_pic">
				                 <a href="${_ctxPath }/item-${product.productSku.productSkuId}-${qiangGou }-${z.zoneId}.htm" target="_blank">
				                    <img src="${_filePath}${product.coverPicture}" alt="" width="285px" height="285px"/>
				                 </a>
		                   </div>
					    <div class="s_meta">
                                   <p class="pro_tit"><a href="${_ctxPath }/item-${product.productSku.productSkuId}-${qiangGou }-${z.zoneId}.htm" target="_blank" title="${product.name}">${yipin:cutString(product.name,35)}</a></p>
					         <p class="tar">
					               <c:if test="${product.propsMap != null}">
						                      <c:if test="${fn:length(product.propsMap.area[0].name) >= 7}">
				                                  <span class="fl" title="${product.propsMap.area[0].name }|${product.propsMap.area[1].name }"> 产地：${fn:substring(product.propsMap.area[0].name,0,7)}</span>
				                               </c:if>
				                               <c:if test="${fn:length(product.propsMap.area[0].name) < 7 && fn:length(product.propsMap.area[0].name) > 0}">
				                                  <c:choose>
				                                     <c:when test="${fn:length(product.propsMap.area[1].name) == 0}">
						                                 <span class="fl" title="${product.propsMap.area[0].name }"> 产地：${fn:substring(product.propsMap.area[0].name,0,7)}</span>
						                            </c:when>
						                            <c:otherwise>
						                                <span class="fl" title="${product.propsMap.area[0].name }|${product.propsMap.area[1].name }"> 产地：${product.propsMap.area[0].name }|${fn:substring(product.propsMap.area[1].name,0,7-(fn:length(product.propsMap.area[0].name)))}</span>
						                            </c:otherwise>
				                                  </c:choose>
				                               </c:if>
					          </c:if>
<%-- 					          ${product.defaultProductSku.skuSpecification} --%>
<%-- 					        aa${product.productSku.skuSpecification} --%>
					                规格： ${yipin:cutString(product.productSku.skuSpecification,7)}</p>
					         <p class="tar s_pri"><span class="fl"><em>一品价：</em><var>${product.productSku.unitPrice}</var></span><del>原价：${product.productSku.marketPrice } </del></p> 
					    </div> 
						<div class="s_opr">
							<form class="hide" action="${_ctxPath}/order/order-getOrderInfo.htm" method="post">
								<input type="hidden" name="orders[0].items[0].productSkuId" value="${product.productSku.productSkuId }"/>
								<input type="hidden" name="orders[0].items[0].num" value="1"/>
							</form>
							<c:if test="${product.productSku.saleStatus == pend}">
								<a href="javascript:;" class="coming_order" target="_blank">即将开抢...</a>
							</c:if>
							<c:if test="${product.productSku.saleStatus == on}">
								<a href="${_ctxPath}/order/order-getOrderInfo.htm?params.skuId=${product.productSku.productSkuId}" class="g_order" target="_blank">立即抢购</a>
							</c:if>
							<c:if test="${product.productSku.saleStatus == stockout or product.productSku.inventory == 0}">
							    <a href="javascript:;" class="btn_panic_out">已抢光</a>
							</c:if>
							<c:if test="${product.productSku.saleStatus == expired}">
								<a href="javascript:;" class="btn_panic_out" target="_blank">已过期</a>
							</c:if>
						</div>
					    </li>
					   </c:forEach>
					</ul>
				</div>
			</div>
			<!--抢购 e-->
		  </c:if>
	     <c:if test="${z.zoneType == 2}">
	     <!--预订 s-->
	     <div class="gd_item J_hover" id="pre_sale">
				<div class="hd cf">
				    <c:if test="${z.count > fn:length(z.products) }">
			         <a href="${_ctxPath }/listproduct-${qiangGou}-0-${z.zoneId}-${url_sort_100}-${url_dir_100000}-0-0.htm" class="more fr" target="_blank">更多 ></a>
			      </c:if>
				  <span class="watches fl">${z.name }</span></div>
	            <div class="bd">
					<ul>
				   <c:forEach items="${z.products }" var="product" varStatus="status">
			        <li data-productSkuId="${product.defaultProductSku.productSkuId }" data-inventory=${product.defaultProductSku.inventory }>
				       <c:if test="${null != product.recommendedReason && product.recommendedReason != ''}">
								<div class="b_reason">
									<h5>推荐理由：</h5>
									   ${yipin:cutString(product.recommendedReason,50)}
								  </div>
						</c:if>
						<div class="s_remain">
							预计发货时间：<span><fmt:formatDate value="${product.preDeliveryTime }" pattern="yyyy-MM-dd"/></span>
						</div>
				       <div class="s_pic">
							 <a href="${_ctxPath }/item-${product.defaultProductSku.productSkuId}-${yuShou}-${z.zoneId}.htm" target="_blank">
							   <img src="${_filePath}${product.coverPicture}" alt="" width="285px" height="285px"/>
							</a>
						</div>
					    <div class="s_meta">
			          		 <p class="pro_tit"><a href="${_ctxPath }/item-${product.defaultProductSku.productSkuId}-${yuShou}-${z.zoneId}.htm" target="_blank" title="${product.name}">${yipin:cutString(product.name,35)}</a></p>
				             <p class="tar"> 
				            <c:if test="${product.propsMap != null}">
						           <c:if test="${fn:length(product.propsMap.area[0].name) >= 7}">
				                         <span class="fl" title="${product.propsMap.area[0].name }|${product.propsMap.area[1].name }"> 产地：${fn:substring(product.propsMap.area[0].name,0,7)}</span>
				                      </c:if>
				                     <c:if test="${fn:length(product.propsMap.area[0].name) < 7 && fn:length(product.propsMap.area[0].name) > 0}">
				                          <c:choose>
				                              <c:when test="${fn:length(product.propsMap.area[1].name) == 0}">
						                            <span class="fl" title="${product.propsMap.area[0].name }"> 产地：${fn:substring(product.propsMap.area[0].name,0,7)}</span>
						                       </c:when>
						                        <c:otherwise>
						                            <span class="fl" title="${product.propsMap.area[0].name }|${product.propsMap.area[1].name }"> 产地：${product.propsMap.area[0].name }|${fn:substring(product.propsMap.area[1].name,0,7-(fn:length(product.propsMap.area[0].name)))}</span>
						                          </c:otherwise>
				                               </c:choose>
				                       </c:if>          
					          </c:if>
					                  规格： ${yipin:cutString(product.defaultProductSku.skuSpecification,7)}</p>
					         <p class="tar s_pri"><span class="fl"><em>一品价：</em><var>￥${product.defaultProductSku.unitPrice }</var></span><del>原价： ${product.defaultProductSku.marketPrice }</del></p> 
					    </div>
					    <c:if test="${product.defaultProductSku.inventory eq 0}">
							<div class="s_opr">
								<a href="javascript:;" class="btn_sold_out">已售罄</a>
							</div>
						</c:if>
						<c:if test="${product.defaultProductSku.inventory gt 0}">
							<div class="s_opr">
								<a href="javascript:;" class="g_pres J_g_presale">立即预订</a>
							</div>
						</c:if>
			       </li> 
				   </c:forEach>
				</ul>
				</div>
			</div>
			<!--预订 e-->
	     </c:if>
		 <c:if test="${z.zoneType == 3}">
		    <!--运营 s-->
<%-- 		    ${z.count} --%>
		    <div class="gd_item J_hover" id="opre_area">
			<div class="hd cf">
			   <c:if test="${z.count > fn:length(z.products) }">
			         <a href="${_ctxPath }/listproduct-${qiangGou}-0-${z.zoneId}-${url_sort_100}-${url_dir_100000}-0-0.htm" class="more fr" target="_blank">更多 ></a>
			    </c:if>
			  <span class="watches fl">${z.name }</span></div>
		           <div class="bd">
					<ul>
					   <c:forEach items="${z.products }" var="product" varStatus="status">
					   <li data-productSkuId="${product.defaultProductSku.productSkuId }" data-inventory="${product.defaultProductSku.inventory }">
					        <c:if test="${null != product.recommendedReason && product.recommendedReason != ''}">
									  <div class="b_reason">
									     <h5>推荐理由：</h5>
									        ${yipin:cutString(product.recommendedReason,50)}
								       </div>
						      </c:if>
			                  <div class="s_pic">
				                 <a href="${_ctxPath }/item-${product.defaultProductSku.productSkuId}-${puTong }-${z.zoneId}.htm" target="_blank">
				                   <img src="${_filePath}${product.coverPicture}" alt="" width="285px" height="285px"/>
				                 </a>
			                   </div>
						    <div class="s_meta">
						         <p class="pro_tit"><a href="${_ctxPath }/item-${product.defaultProductSku.productSkuId}-${puTong }-${z.zoneId}.htm" target="_blank" title="${product.name}">${yipin:cutString(product.name,35)}</a></p>
						         <p class="tar">
						          <c:if test="${product.propsMap != null}">
						                      <c:if test="${fn:length(product.propsMap.area[0].name) >= 7}">
				                                  <span class="fl" title="${product.propsMap.area[0].name }|${product.propsMap.area[1].name }"> 产地：${fn:substring(product.propsMap.area[0].name,0,7)}</span>
				                               </c:if>
				                               <c:if test="${fn:length(product.propsMap.area[0].name) < 7 && fn:length(product.propsMap.area[0].name) > 0}">
				                                  <c:choose>
				                                     <c:when test="${fn:length(product.propsMap.area[1].name) == 0}">
						                                 <span class="fl" title="${product.propsMap.area[0].name }"> 产地：${fn:substring(product.propsMap.area[0].name,0,7)}</span>
						                            </c:when>
						                            <c:otherwise>
						                                <span class="fl" title="${product.propsMap.area[0].name }|${product.propsMap.area[1].name }"> 产地：${product.propsMap.area[0].name }|${fn:substring(product.propsMap.area[1].name,0,7-(fn:length(product.propsMap.area[0].name)))}</span>
						                            </c:otherwise>
				                                  </c:choose>
				                              </c:if>
					          </c:if>
						           规格： ${yipin:cutString(product.defaultProductSku.skuSpecification,7)}</p>
						         <p class="tar s_pri"><span class="fl"><em>一品价：</em><var>${product.defaultProductSku.unitPrice}</var></span><del>原价：${product.defaultProductSku.marketPrice } </del></p> 
						    </div>  
								 <c:if test="${product.defaultProductSku.inventory == 0}">
									    <div class="s_opr">
								               <a href="javascript:;" class="btn_sold_out">暂时无货</a>
							            </div>
						          </c:if>
									<c:if test="${product.defaultProductSku.inventory > 0 }">
										<div class="s_opr">
											<div class="J_add_cart add_cart cf">
												<div class="fl num_vary">
													<span class="less J_less">-</span><input type="text" class="input-text J_input" value="1"><span class="more J_more">+</span>
												</div>
												<a href="javascript:;" class="fr g_cart J_g_cart">加入购物车</a>
											</div>
											<div class="J_see_cart see_cart cf">
												<span class="fl add_suc">添加成功</span>
												<a href="${_ctxPath }/order/order-showCart.htm" class="fr g_order">查看购物车</a>
											</div>
							            </div>
							       </c:if>
				            </li>
				     </c:forEach>
				</ul>
			</div>
        </div>
        <!--运营 e-->
     </c:if>
	 </c:forEach>
	</div> 
	<!--底部 start -->
	<!--悬浮购物车 s -->
	<%@include file="/WEB-INF/pages/order/miniCart.jsp"%>
	<!--悬浮购物车 e -->
	<!-- 底部 start -->
	<%@include file="/WEB-INF/pages/include/foot.jsp"%>
	<!--底部 end-->
	<!--js-->
	<script type="text/javascript" src="${_jsPath }/pages/index.js"></script>
</body>
</html>