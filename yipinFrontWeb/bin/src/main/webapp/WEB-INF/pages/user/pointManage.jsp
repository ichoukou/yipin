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
	<%@include file="/WEB-INF/pages/include/headUserInfo.jsp"%>
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
						<p>（1）订单付款成功后即可得到相应积分；</p>
						<p>（2）如果发生退货的情况，所退商品对应积分将会扣除（即退货商品所产生的双倍积分、首单消费积分，亦因退货同时扣除）。</p>
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
										订单
									</c:if>
									<c:if test="${pointDetail.pointSource == 1}">
										退货
									</c:if>
									<c:if test="${pointDetail.pointSource == 2}">
										注册
									</c:if>
									<c:if test="${pointDetail.pointSource == 3}">
										吐槽
									</c:if>
									<c:if test="${pointDetail.pointSource == 4}">
										发现
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