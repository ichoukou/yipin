<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<div class="uc_l">
	<div class="crumb">
		<a href="${_ctxPath}/login.htm">首页</a> > 我的一品
	</div>
	<div class="lf_nav">
		<!-- <div class="hd">我的一品</div> -->
		<div class="bd">
		    <ul>
		    	<li class="uc_i1" id="myOrder"><a href="${_ctxPath}/order/order-myOrders.htm">我的订单</a></li>
				<li class="uc_i2" id="address"><a href="${_ctxPath}/address/address-getUserAddress.htm">收货地址</a></li>
				<li class="uc_i3" id="myRefundManage"><a href="${_ctxPath}/order/order-myRefundManage.htm">退货管理</a></li>
				<li class="uc_i8" id="myInfo"><a href="${_ctxPath}/user/user-userInfo.htm">我的资料</a></li>
				<li class="uc_i5" id="myPiont"><a href="${_ctxPath}/user/points.htm">积分管理</a></li>
				<li class="uc_i6" id="mySpitslot"><a href="${_ctxPath}/suggest/searchSuggest-1.htm">我的吐槽</a></li>
				<li class="uc_i7" id="myFind"><a href="${_ctxPath}/suggest/searchSuggest-0.htm">我的发现</a></li>
				
				<li class="uc_i4" id="chPwd"><a href="${_ctxPath}/user/user-changePsw.htm">修改密码</a></li>
		    </ul>
		</div>
	</div>
</div>
<script type="text/javascript">
window.onload=function(){
	var url = location.href;
	if(url.indexOf("invoice") > 0 || url.indexOf("order-myOrders") > 0 || url.indexOf("order-myOrderPackageDetail") > 0){
		$("#myOrder").addClass("on").siblings().removeClass("on");
	}else if(url.indexOf("Refund") > 0 ||url.indexOf("refund") > 0 ){
		$("#myRefundManage").addClass("on").siblings().removeClass("on");
	}else if(url.indexOf("address") > 0){
		$("#address").addClass("on").siblings().removeClass("on");
	}else if(url.indexOf('points')>0){
		$("#myPiont").addClass("on").siblings().removeClass("on");
	}else if(url.indexOf('changePsw')>0){
		$("#chPwd").addClass("on").siblings().removeClass("on");
	}else if(url.indexOf('searchSuggest-1')>0){
		$("#mySpitslot").addClass("on").siblings().removeClass("on");
	}else if(url.indexOf("searchSuggest-0")>0){
		$("#myFind").addClass("on").siblings().removeClass("on");
	}else if(url.indexOf("userInfo")>0){
		$("#myInfo").addClass("on").siblings().removeClass("on");
	}
};
</script>
