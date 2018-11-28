<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<title>积分管理</title>
	<jsp:include page="/WEB-INF/pages/include/common.jsp"></jsp:include>
	<!--css-->
	<link rel="stylesheet" href="${_cssPath}/lib/global.css" />
	<!--page css-->
	<link rel="stylesheet" href="${_cssPath}/pages/changePsw.css" />
</head>
<body>
	<%@include file="/WEB-INF/pages/include/head.jsp"%>
	<!--内容部分 start-->
	<div class="w_norm">
		<!-- 用户中心框架 start-->
		<div class="uc cf">
			<!-- 用户中心左侧 start-->
			<%@include file="/WEB-INF/pages/include/left.jsp"%>
			<!-- 用户中心左侧 end-->
			<!-- 用户中心右侧 start-->
			<div class="uc_r">
				<div class="ucr_hd">积分管理</div>
				<div class="point_manage">
					<div class="point_msg">
						<p class="f14">您目前的会员积分：${point.total } ，可用积分：${point.total }</p>
						<p>（1）订单付款后（订单状态变为“待发货”），可收到相应积分；</p>
						<p>（2）如果发生退货的情况，将扣除所退商品的相应积分；如果该商品产生的双倍积分、首单消费积分，也会因为退货而全部扣回。</p>
					</div>
					<table width="100%" class="table">
						<thead>
							<tr>
								<th width="30%">所获积分</th>
								<th width="25%">积分来源</th>
								<th>获取时间</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${point.pointDetails}" var="pointDetail">
								<tr>
									<td>${pointDetail.point}</td>
									<td>
									<c:if test="${pointDetail.pointSource == 0}">
										订单积分
									</c:if>
									<c:if test="${pointDetail.pointSource == 1}">
										退货积分
									</c:if>
									<c:if test="${pointDetail.pointSource == 2}">
										注册用户赠送
									</c:if>
									<c:if test="${pointDetail.pointSource == 3}">
										吐槽积分
									</c:if>
									<c:if test="${pointDetail.pointSource == 4}">
										发现积分
									</c:if>
									<c:if test="${pointDetail.pointSource == 5}">
										退款积分
									</c:if>
									</td>
									<td><span><fmt:formatDate value="${pointDetail.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></span></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
<!-- 					<div class="pagination cf"> -->
<!-- 						<div id="lastFy"> -->
<%-- 		                    <c:if test="${not empty pointPage}"> --%>
<%-- 		     					<yp:commPage page="${pointPage}" beanName="pointPage"></yp:commPage> --%>
<%-- 		     				</c:if> --%>
<!-- 	        			</div> -->
<!-- 					</div> -->
					<yp:yipinFrontPage urlEnd=".htm" urlStart="points-${pointPage.params.queryTime}-${pointPage.total}-" page="${pointPage}"></yp:yipinFrontPage>
				</div>
			</div>
			<!-- 用户中心右侧 end-->
		</div>
		<!-- 用户中心框架 end-->
	</div>
	<!--内容部分 end-->
	<%@include file="/WEB-INF/pages/include/foot.jsp"%>
</body>
</html>