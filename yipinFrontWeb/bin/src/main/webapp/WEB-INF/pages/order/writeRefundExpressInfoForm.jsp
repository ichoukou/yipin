<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
    <meta charset="UTF-8">
    <title>填写退货快递信息</title>
    <!--css-->
    <jsp:include page="/WEB-INF/pages/include/common.jsp"></jsp:include>
    <!--page css-->
    <link rel="stylesheet" type="text/css" href="${_cssPath}/pages/ordData.css" media="all" />
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
				<div class="ucr_hd"><a href="${_ctxPath}/order/order-myRefundManage.htm">退货管理</a> > 退货快递信息填写</div>
				<div class="exp_wp">
					<div class="show_txt">
						<ul>
							<li><div class="tit">商家姓名：</div><div class="con">${userInfo.contactName}</div></li>
							<li><div class="tit">商家电话：</div><div class="con">${userInfo.mobile}</div></li>
							<li><div class="tit">商家退货地址：</div><div class="con">${userInfo.shiperAddress}</div></li>
						</ul>
					</div>
					<div class="apply_info">
						<form action="" id="form1">
							<ul>
								<li>
									<div class="tit"><span>*</span>快递公司：</div>
									<div class="con relative">
										<input type="text" id="expcompany" />
										<span id="expcompanyTips"></span>
									</div>
								</li>
								<li>
									<div class="tit"><span>*</span>快递单号：</div>
									<div class="con relative">
										<input type="text" id="expInfo" />
										<span id="expInfoTips"></span>
									</div>
								</li>
							</ul>
						</form>
					</div>
					<div class="apply_btn">
						<a href="javascript:;" class="submit" id="refundExpressInfo" data-id="${orderRefundId}">提交申请</a>
						<a href="javascript:;" class="cancle" id="reset">重置</a>
						<a href="${_ctxPath}/order/order-myRefundRecord.htm" class="cancle" id="cancle">返回</a>
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
	<!--page js-->
	<script type="text/javascript" src="${_jsPath}/pages/ordData.js"></script>
</body>
</html>