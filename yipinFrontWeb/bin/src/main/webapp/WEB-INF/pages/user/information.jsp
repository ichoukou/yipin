<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<title>我的资料</title>
	<jsp:include page="/WEB-INF/pages/include/common.jsp"></jsp:include>
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
				<div class="ucr_hd">我的资料</div>
				<div class="my_data">
					<form action="${_ctxPath}/user/user-updateUserInfo.htm" method="post" id="myDataForm">
						<ul>
							<li class="cf">
								<div class="data_type">
									<label for="">登录名：</label>
									<div class="data_msg"><span class="nameMsg">${escape:escapeHtml(username)}</span><span class="checkTip" id="loginNameTip"></span></div>
								</div>
								<div class="data_type">
									<label for="">真实姓名：</label>
									<div class="data_msg">
										<input type="text" class="input-text" id="name" name="userinfo.contactName" value="${userinfo.contactName}"/><span class="J_detail">${escape:escapeHtml(userinfo.contactName)}</span>
									</div>
									<span class="checkTip" id="nameTip"></span>
								</div>
							</li>
							<li class="cf">
								<div class="data_type">
									<label for="">性别：</label>
									<div class="data_msg">
										<c:if test="${userinfo.gender ==1 }"><span class="J_detail">男</span></c:if>
										<c:if test="${userinfo.gender ==0 }"><span class="J_detail">女</span></c:if>
										<input type="radio" name="userinfo.gender" checked value="1" <c:if test='${userinfo.gender eq 1}'>checked="checked"</c:if> class="inf-sex"/><span class="gender" style="display:none;">男</span>&nbsp;&nbsp;
                    					<input type="radio" name="userinfo.gender" value="0" <c:if test="${userinfo.gender eq 0}">checked="checked"</c:if> class="inf-sex"/><span class="gender" style="display:none;">女</span>
										<span class="checkTip" id="sexTip"></span>
									</div>
								</div>
								<div class="data_type">
									<label for="">生日：</label>
									<div class="data_msg">
									<input type="text" class="input-text" id="feteDay" name="userinfo.birthday" value="<fmt:formatDate value='${userinfo.birthday}' pattern='yyyy-MM-dd' />"/>
									<span class="J_detail"><fmt:formatDate value='${userinfo.birthday}' pattern='yyyy-MM-dd' /></span>
									</div>
									<span class="checkTip" id="feteDayTip"></span>
								</div>
							</li>
							<li class="cf">
								<div class="data_type">
									<label for=""><i class="red">*</i>移动电话：</label><input type="text" class="input-text" id="cellphone"  name="userinfo.mobile" value="${userinfo.mobile}" maxlength="11"/><span class="J_detail">${escape:escapeHtml(userinfo.mobile)}</span>
									<span class="checkTip" id="cellphoneTip"></span>
								</div>
								<div class="data_type">
									<label for="">固定电话：</label><input type="text" class="input-text" id="dixphone" name="userinfo.tel" value="${userinfo.tel}"/>
									<span class="J_detail"> ${escape:escapeHtml(userinfo.tel)}</span>
									<span class="checkTip" id="dixphoneTip"></span>
								</div>
							</li>
							<li class="diquBox">
								<label for=""><i class="red">*</i>所在地区：</label>
								<select name="" id="province" class="linkagesel m-mr5p"></select>
								
								<select name="" id="city" class="linkagesel m-mr5p"></select>
								
								<select name="" id="region" class="linkagesel"></select>
								<span class="checkTip" id="dressTip"></span>
								<span class="J_detail">${userinfo.area}</span>
								<input type="hidden"  id="regionId" value="${userinfo.companyRegionId}" name="userinfo.companyRegionId"/>
					            <input type="hidden"  id="area_prov" value='${userinfo.regionModel.provinceId}'/>
								<input type="hidden"  id="area_city" value='${userinfo.regionModel.cityId}'/>
								<input type="hidden"  id="area_region" value='${userinfo.regionModel.countyId}'/>
							</li>
							<li class="cf">
								<label for="">详细地址：</label>
								<div class="data_msgall">
								<input type="text" class="input-text detail_dress" id="detailDress" name="userinfo.companyAddress" value="${userinfo.companyAddress}"/>
								  <span class="J_detail">${escape:escapeHtml(userinfo.companyAddress)}</span>
							</div>
								<span class="checkTip" id="detailDressTip"></span>
							</li>
							<li class="cf">
								<label for=""><i class="red">*</i>联系邮箱：</label>
								<div class="data_msgall">
								<input type="text" class="input-text" id="mail" name="userinfo.email" value="${userinfo.email}"/><span class="red hide J_tip">（重要：用于找回密码！）</span><span class="J_detail">${userinfo.email}</span>
								</div>
								<span class="checkTip" id="mailTip"></span>
							</li>
							<li class="submitBtn"><a href="javascript:" class="saveBtn" id="saveBtn">保存个人资料</a><a href="javascript:" class="changeBtn" id="changeBtn">修改</a></li>
						</ul>
						<input type="hidden" name="userinfo.userInfoId" value="${userinfo.userInfoId}" />
                   		<input type="hidden" name="userinfo.userId" value="${userinfo.userId}" />
					</form>
				</div>
			</div>
			<!-- 用户中心右侧 end-->
		</div>
		<!-- 用户中心框架 end-->
	</div>
	<!--内容部分 end-->
	<%@include file="/WEB-INF/pages/include/foot.jsp"%>
	<!--page js-->
	<script type="text/javascript" src="${_jsPath}/pages/information.js"></script>
	<script src="${_jsPath}/plugin/select/linkage_sel.js"></script>
</body>
</html>