<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<title>收货地址</title>
	<jsp:include page="/WEB-INF/pages/include/common.jsp"></jsp:include>
	<!--page css-->
	<link rel="stylesheet" href="${_cssPath}/pages/deliveryAddress.css" />
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
			<div class="uc_r" id="delivAdr">
				<div class="ucr_hd">收货地址 <span class="f12"> | 您可以在这里进行收货地址的增加或删除以及其其他地址设定操作</span></div>
				<div class="newadr_wp">
					<div class="new_adr">
						<form id="form1"  method="post">
							<table width="100%">
								<tbody>
									<tr>
										<td class="tit" width="100"><i>*</i>收货人姓名：</td>
										<td width="220">
											<div class="tip_wp">
												<input type="text" class="inp foma1" id="addName" name="receiverAdd.receiverName" value="${receiverAdd.receiverName}"/>
												<p class="adr_tips"><span id="addNameTips"></span></p>
											</div>
										</td>
											<td class="tit"><i>*</i>移动电话：</td>
										<td>
											<div class="tip_wp">
												<input type="text" class="inp foma1" id="mobile" maxlength="11" name="receiverAdd.mobile" value="${receiverAdd.mobile}"  />
												<p class="adr_tips"><span id="telephoneTips"></span></p>
											</div>
										</td>
									</tr>
									<tr>
										<td class="tit"><i>*</i>所在地区：</td>
										<td colspan="3">
											<div class="tip_wp">
												<select name="" class="linkagesel m-mr5p" id="province"></select>
												<select name="" class="linkagesel m-mr5p" id="city"></select>
												<select name="" class="linkagesel" id="region"></select>
												<p class="adr_tips"><span id="provinceTip"></span></p>
											</div>
										</td>
									</tr>
									<tr>
										<td class="tit"><i>*</i>详细地址：</td>
										<td colspan="3">
											<div class="tip_wp">
												<input type="text" class="inp foma2" id="detailAddress" name="receiverAdd.detailAddress" value="${receiverAdd.detailAddress}"/>
												<p class="adr_tips"><span id="addressTips"></span></p>
											</div>
										</td>
									</tr>
									<tr>
										<td class="tit">固定电话：</td>
										<td>
											<div class="tip_wp">
												<input type="text" class="inp foma1" name="receiverAdd.telephone" value="${receiverAdd.telephone}" id="phoneNum"/>
												<p class="adr_tips"><span id="phoneNumTips"></span></p>
											</div>
										</td>
										<td class="tit">邮      编：</td>
										<td>
											<div class="tip_wp">
												<input type="text" class="inp foma1" name="receiverAdd.postCode" value="${receiverAdd.postCode}" id="zipcode"/>
												<p class="adr_tips"><span id="zipcodeTips"></span></p>
											</div>
										</td>
									</tr>
									<tr class="last_line">
										<td class="tit">设为默认：</td>
										<td colspan="3">
										<label class="set_def"><input type="checkbox" id="checkLoad" />设置为默认地址</label>设置后系统将在购买时自动选中该收货地址
										</td>
									</tr>
									<tr>
										<td></td>
										<td colspan="3"><a class="save" href="javascript:;" id="address_save">保存</a><a href="javascript:;" class="cancel">取消</a></td>
									</tr>
								</tbody>
							</table>
							<input type="hidden" id="opert" name="opert" value="${opert}">
							<input type="hidden" id="isDefault" name="receiverAdd.isDefault" value="${receiverAdd.isDefault}" >
		                    <input type="hidden" value="${userId}" name="userId">
		                    <input type="hidden" id="area_prov" value='${receiverAdd.regionModel.provinceId}'/>
		                    <input type="hidden" id="area_city" value='${receiverAdd.regionModel.cityId}'/>
		                    <input type="hidden" id="area_region" value='${receiverAdd.regionModel.countyId}'/>
		                    <input type="hidden" id="regionId" name="receiverAdd.regionId" value="${receiverAdd.regionId}">
		                    <input type="hidden" id="_ctxPath" value="${_ctxPath}" >
		                    <input type="hidden" id="userAddressId" name="receiverAdd.userAddressId" value="${receiverAdd.userAddressId}">
							<input type="hidden" id="addTotal" name="addTotal" value="${addTotal}"/>
		                </form>
					</div>
				</div>
				<div class="deliv_adr">
					<div class="has_adr">
						<ul>
							<c:forEach items="${receiverAddList}" var="item">
								<li class="adr_list">
									<table width="100%">
										<tbody>
											<tr>
												<td>${escape:escapeHtml(item.receiverName)}</td>
												<td>${item.mobile}</td>
												<td>${item.region.province}&nbsp;&nbsp;${item.region.city}&nbsp;&nbsp;${item.region.county}</td>
												<td>${escape:escapeHtml(item.detailAddress)}</td>
												<td>
													<c:if test="${item.isDefault==1}">
						                            	<i class="has_def">默认地址</i>
						                            </c:if>
					                            	<c:if test="${item.isDefault==0}">
						                            	<a href="javaScript:setDefualt(${item.userAddressId})"><span class="defa">设为默认</span></a>
						                            </c:if>
												</td>
												<td><a href="javascript:updateAddress(${item.userAddressId})"><span class="edit">修改</span></a><span class="line">|</span><a href="javaScript:setDel(${item.userAddressId},${item.isDefault})"><span class="del">删除</span></a></td>
											</tr>
										</tbody>
									</table>
								</li>
		                    </c:forEach>
						</ul>
					</div>
				</div>
			</div>
			<!-- 用户中心右侧 end-->
		</div>
		<!-- 用户中心框架 end-->
	</div>
	
	<!--内容部分 end-->
	<!--底部 start-->
	<%@include file="/WEB-INF/pages/include/foot.jsp"%>
	<!--底部 end-->
	<!--page-->
	<script src="${_jsPath}/pages/deliveryAddress.js?d=${_resVerion}"></script>
	<script src="${_jsPath}/plugin/select/linkage_sel.js"></script>
	<script src="${_jsPath}/plugin/select/jquery.bgiframe.js"></script>
</body>
</html>