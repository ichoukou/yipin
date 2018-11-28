<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.ytoxl.module.yipin.base.dataobject.resultmap.ExpressMessage" %>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
    <meta charset="UTF-8">
    <title>订单发票信息</title>
    <!--css-->
    <jsp:include page="/WEB-INF/pages/include/common.jsp"></jsp:include>
    <!--page css-->
    <link rel="stylesheet" type="text/css" href="${_cssPath}/pages/orderDetails.css" media="all" />
</head>
<body>
	<!--头部导航 start-->
	<%@include file="/WEB-INF/pages/include/headUserInfo.jsp"%>
	<!--头部导航 end-->
	<div class="w_norm">
		<!-- 用户中心框架 start-->
		<div class="uc cf">
			<!-- 用户中心左侧 start-->
			<%@include file="/WEB-INF/pages/include/left.jsp"%>
			<!-- 用户中心左侧 end-->
			<!-- 用户中心右侧 start-->
			<div class="uc_r">
				<div class="ucr_hd"><a href="${_ctxPath}/order/order-myOrders.htm">我的订单</a> > 发票信息</div>
				<div class="my_order">
					<div class="data_show">
						<!--操作 star-->
		                <div class="detail">
		                    <div class="wl_wp" data='{"expressId":"${invoice.expressId}","expressNo":"${invoice.expressNo}"}'>
		                    	<div class="loading">
									<img src="${_imagesPath}/loading.gif" />
									<span>正在努力加载...</span>
								</div>
		                    </div>
		                    <div class="detail_wp">
		                        <div  class="detail_con detail_last">
		                            <p class="detail_tit">发票信息</p>
		                            <p>发票类型：普通发票</p>
		                            <p>发票抬头：
										<c:if test="${not empty invoice.orderHead.invoiceTitle}">
											${invoice.orderHead.invoiceTitle}
										</c:if>
										<c:if test="${empty invoice.orderHead.invoiceTitle}">
											个人
										</c:if>
									</p>
		                            <p>发票内容：明细</p>
		                            <p class="blue">订单号：${invoice.orderHead.orderNo}</p>
		                            <p class="blue">快递公司：
										<c:if test="${not empty invoice.express.websiteUrl}">
											<a href="${invoice.express.websiteUrl}" target="_blank"> ${invoice.expressName}</a>
										</c:if>
										<c:if test="${empty invoice.express.websiteUrl}">
											${invoice.expressName}
										</c:if>
									</p>
		                            <p>发票快递单号：${invoice.expressNo}</p>
		                        </div>
		                    </div>
		                </div>
		                <!--操作 end-->
		                <c:if test="${empty invoice}">
		                	暂时没有发票信息
		                </c:if>
					</div>
				</div>
			</div>
			<!-- 用户中心右侧 end-->
		</div>
	<!-- 用户中心框架 end-->
	</div>
	<!--底部 start-->
	<%@include file="/WEB-INF/pages/include/foot.jsp"%>
	<!--底部 end-->
	<!--js-->
	<script type="text/javascript" src="${_jsPath}/plugin/plugin.js"></script>
	<!--page js-->
	<script type="text/javascript">
		$(function(){
			var _this = $('.wl_wp');
			var queryData = _this.attr("data");
			var _queryData = $.parseJSON(queryData);
			var expressId = $.trim(_queryData['expressId']);
			var mailNo = $.trim(_queryData['expressNo']);
			if(expressId != "" && mailNo != ""){
				var q = $.param({"expressId":expressId,"mailNo":mailNo});
				$.ajax({
					type : "POST",
					url : _ctxPath + "/order/order-expressInfoAjax.htm",
					data : q,
					success : function(html){
						if("" == $.trim(html)){
							_this.remove();
						}else{
							_this.empty().append(html);
						}
					},
					error:function(){
						//_this.find(".loading").find("span").text("没有查询到相应的物流信息，给您带来不便请见谅，请稍后再试！");
						//_this.find(".loading").remove();
						_this.remove();
					}
				});
			}else{
				//_this.find(".loading").find("span").text("没有查询到相应的物流信息，给您带来不便请见谅，请稍后再试！");
				//_this.find(".loading").remove();
				_this.remove();
			}
		});
	</script>
</body>
</html>