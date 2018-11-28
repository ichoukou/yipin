﻿<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=UTF-8" import="com.ytoxl.module.yipin.base.dataobject.Prop" pageEncoding="UTF-8"%>
<%@page import="com.ytoxl.module.yipin.base.dataobject.Product" %>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<html lang="zh">
<head>
<meta charset="utf-8" />
<title>编辑商品</title>
	<link href="${_cssPath }/common.css" rel="stylesheet" />
  	<link href="${_cssPath }/pages/addProduct.css" rel="stylesheet" />
  	<script type="text/javascript" src="${_jsPath }/jquery/jquery.1.8.1.js"></script>
  	<script type="text/javascript" src="${_jsPath}/plugin/uploadify/swfobject.js"></script>  
	<script type="text/javascript" src="${_jsPath}/plugin/uploadify/jquery.uploadify.v2.1.4.min.js"></script>
	<script type="text/javascript" src="${_jsPath }/plugin/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="${_jsPath }/plugin/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${_jsPath }/plugin/formvalidator/formValidatorRegex.js"></script>
  	<script type="text/javascript" src="${_jsPath }/plugin/formvalidator/formValidator-4.1.3.js"></script>
  	<script type="text/javascript">
  		var place = <%=Prop.CODE_PLACE %>;
  		var code_product = <%=Prop.CODE_PRODUCT %>
  		var code_people = <%=Prop.CODE_PEOPLE %>
  		var productProp = '${product.productProp}';
  		var sellType = '${product.sellType}';
<%--   		var province_leve = <%=Prop.LEVEL_PROVINCE %> --%>
<%--   		var city_leve = <%=Prop.LEVEL_CITY %> --%>
<%--   		var province_leve = <%=Prop.LEVEL_PROVINCE %> --%>
<%--   		var city_leve = <%=Prop.LEVEL_PROVINCE %> --%>
  	</script>
  	<script type="text/javascript" src="${_jsPath }/pages/jquery.select.js"></script>
  	
<body>
<!--start header-->
<jsp:include page="../include/header.jsp"></jsp:include> 
<!--end header-->
<!--start body-->
<div class="body m-w980p">
	<form id="addForm" method="post" target="_blank">
		<input type="hidden" name="product.productId" value="${product.productId }" id="productId"/>
		<input type="hidden" name="product.status" id="status"/>
		<input type="hidden" name="product.imageUrls" id="imageUrls"/>
		<input type="hidden" name="product.keyWord" id="keyWord"/>
		<table border="0" class="addPro-table">
			<tr>
				<th width="145" class="left-td">商品属性</th>
				<th width="130">&nbsp;</th>
				<th width="160">&nbsp;</th>
				<th width="543">&nbsp;</th>
			</tr>
			<tr>
				<td class="left-td">*销售方式：</td>
				<td colspan="2">
					<input type="radio" name="product.sellType" value="2" checked="checked" onclick="changeTimeStatus(this.value)">预售
					<input type="radio" name="product.sellType" value="1" <c:if test="${product.sellType==1 }">checked='checked'</c:if>  onclick="changeTimeStatus(this.value)"> 销售
					<input type="radio" name="product.sellType" value="3" <c:if test="${product.sellType==3 }">checked='checked'</c:if>  onclick="changeTimeStatus(this.value)"> 抢购
				</td>
<!-- 				<td colspan="1"></td> -->
			</tr>
			<tr id="time">
				<td class="left-td">*预发货日期：</td>
				<td>
					<input type="text" id="perDeliveryTime" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-{%d+1}',readOnly:true})"
						value="<fmt:formatDate value='${product.preDeliveryTime }' pattern='yyyy-MM-dd'/>" name="product.preDeliveryTime"
					 />
				</td>
				<td colspan="2"><span id="perDeliveryTimeTip"></span></td>
			</tr>
			<tr>
				<td class="left-td">*品牌：</td>
				<td>
					<select class="m-sel" name="product.brandId" id="brandId">
						<option>请选择品牌</option>
						<c:forEach items="${sellerBrands }" var="brand">
							<option value="${brand.brandId }"
							<c:if test="${brand.brandId == product.brandId }">selected="selected"</c:if>
							>${brand.name }</option>
						</c:forEach>
					</select>
				</td>
				<td colspan="2"><span id="brandIdTip"></span></td>
			</tr>
			<tr id='zpf'>
				<td class="left-td">*所在地区：</td>
				<td colspan="2">
					<select name="area" class="m-sel" id="province"></select>
					<select name="area" class="m-sel" id="city"></select>
				</td>
				<td><span id="cityTip"></span></td>
			</tr>
			<tr>
				<td class="left-td">*分类：</td>
				<td colspan="2">
					<select name="catalog" id="bigcatalog"></select>
					<select name="catalog" id="smallcatalog"></select>
				</td>
				<td><span id="smallcatalogTip"></span></td>
			</tr>
			<tr>
				<td class="left-td">适应人群：</td>
				<td colspan="2"><div id="people"></div></td>
				<td></td>
			</tr>
			<tr>
				<td class="left-td">*商品名称：</td>
				<td colspan="2"><input type="text" class="pro-name" name="product.name" value="${product.name }" id="name"/></td>
				<td><span id="nameTip"></span></td>
			</tr>
			<tr>
				<td class="left-td">推荐理由：</td>
				<td colspan="3">
					<textarea style="width:700px;" maxlength="150" name="product.recommendedReason">${product.recommendedReason }</textarea>
				</td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td colspan="3">
					<span class="small">温馨提示：推荐理由字数最多是150个中文字</span>
				</td>
			</tr>
			<tr>
				<td class="left-td">*库存：</td>
				<td colspan="2">
					<c:forEach items="${isShowInventorys }" var="isShowInventory" varStatus="status">
					<input type="radio" class="input-radio" name="product.isShowInventory" 
						<c:if test="${product.isShowInventory == isShowInventory || product.isShowInventory ==null && isShowInventory == isShowInventoryNo}">checked="checked"</c:if>
					value="${isShowInventory }"/>
					<label><small><spring:message code="product.isShowInventory.${status.index}"/></small></label>
					</c:forEach>
				</td>
				<td></td>
			</tr>
			<tr>
				<td class="left-td">${skuOptions[0].skuOptionName }：</td>
				<td colspan="3" class="c-select"   id="size">
					<input type="hidden" id="wt"  value="${skuOptions[1].skuOptionValues[0].skuOptionValueId }"/>
					<c:forEach items="${skuOptions[0].skuOptionValues }" var="obj" varStatus="status">
						<dd><input type="checkbox" class="input-checkbox" value="${obj.skuOptionValueId }"/><span class="label-sel">${obj.skuOptionValue }</span></dd>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td></td>
				<td colspan="3">
					<span id="sizeTip"></span>
				</td>
			</tr>

			<tr valign="top">
<!-- 				<td class="left-td"></td> -->
				<td colspan="4" class="small" align="center">
					<table>
						<tr id="aaa">
							<td></td>
							<td width="450" >
<!-- 								<input type="radio" >默认 -->
								<table class="repertories" width="630" class="small">
									<tr>
										<th></th>
										<th style="width:100px;">*规格</th>
										<th>*重量</th>
										<th>*库存数</th>
										<th>*SKU编码</th>
										<th>*售卖价</th>
										<th>*原价</th>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td></td>
				<td colspan="3">
					<span class="small">温馨提示：请填写1-10位数字、小数点组成的商品原价</span>
				</td>
			</tr>
			<tr valign="top" class="small">
				<td class="left-td">*封面图片：</td>
				<td colspan="3">
					<table id="coverProductPicture">
						<tr>
							<td width="120">
								<div class="default-img" id="default-img">
									<div class="del-img" id="del-img"></div>
									<!-- <i class="img-prev"></i>
									<i class="img-next"></i> -->
									<c:if test="${product.coverPicture != '' && product.coverPicture != null}">
										<img src="<yp:thumbImage originalPath='${product.coverPicture}' imageType='t84'></yp:thumbImage>" />
									</c:if>
								</div>
								<input type="hidden" id="coverImgUrl" class="coverimageUrl" name="product.coverPicture" value="${product.coverPicture}"/>
								<input type="file" id="coverImgUpload" />
							</td>
							<td valign="bottom" id="no_img"><span id="coverUrlTip"></span></td>
						</tr>
						<tr>
							<td colspan="5">温馨提示：为保证图片效果请上传285px X 285px的商品图片</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr valign="top" class="small">
				<td class="left-td">*商品图片：</td>
				<td colspan="3">
					<table class="no-border-table" >
						<tr>
							<td width="120">
								<div class="default-img">
									<div class="del-img"></div>
									<!-- <i class="img-prev"></i>
									<i class="img-next"></i> -->
								</div>
								<input type="hidden" id="fontImgUrl" class="imageUrl"/>
								<input type="file" id="fontImgUpload"/>
							</td>
							 <td width="120">
								<div class="default-img">
									<div class="del-img"></div>
									<i class="img-prev"></i>
									<i class="img-next"></i>
								</div>
								<input type="hidden" id="backImgUrl" class="imageUrl"/>
								<input type="file" id="backImgUpload"/>
							</td>
							<td width="120">
								<div class="default-img">
									<div class="del-img"></div>
									<i class="img-prev"></i>
									<i class="img-next"></i>
								</div>
								<input type="hidden" id="styleImgUrl" class="imageUrl"/>
								<input type="file" id="styleImgUpload"/>
							</td>
							<td width="120">
								<div class="default-img">
									<div class="del-img"></div>
									<i class="img-prev"></i>
									<i class="img-next"></i>
								</div>
								<input type="hidden" id="workmanshipImgUrl" class="imageUrl"/>
								<input type="file" id="workmanshipImgUpload"/>
							</td>
							<td width="120">
								<div class="default-img">
									<div class="del-img"></div>
									<i class="img-prev"></i>
									<i class="img-next"></i>
								</div>
								<input type="hidden" id="fiveImgUrl" class="imageUrl"/>
								<input type="file" id="fiveImgUpload"/>
							</td> 
							<td valign="bottom" id="no_img"><span id="imgUrlTip"></span></td>
						</tr>
						<tr>
							<td colspan="5">温馨提示：为保证图片效果请上传510px X 510px的商品图片</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr valign="top" class="small">
				<td class="left-td">批量上传图片：</td>
				<td colspan="3">
					<input type="file" id="uploadBatch" />
				</td>
			</tr>
			<tr valign="top" class="small">
				<td class="left-td">商品描述：</td>
				<td colspan="3">
					<textarea class="edit-textarea" maxlength="1000" name="product.description" id="description">${product.description }</textarea>
				</td>
			</tr>
			<tr>
				<td></td>
				<td colspan="2"><span id="descriptionTip"></span></td>
				<td></td>
<!-- 				<td align="center">源码：已输入<b class="c-green">0</b>/最多输入<b class="c-red">25000</b></td> -->
			</tr>
			<tr>
				<td></td>
				<td colspan="3">
					<div class="cilck-tip">
							<c:set var="draft" value="<%=Product.STATUS_DRAFT %>"></c:set>
							<c:if test="${product.productId == null || product.status == draft }">
							<input type="button" class="m-btn" value="保&nbsp;&nbsp;存" id="save"/>
							</c:if>
							<input type="button" class="m-btn" value="提交审核" id="submitReview"/>
							<!-- 
							<input type="button" class="m-btn" value="预&nbsp;&nbsp;览" id="preview"/>
							 -->
							<c:if test="${product.productId == null }">
								<input type="button" class="m-btn" value="提交审核并复制" id="submitReviewAndCopy"/>
							</c:if>
						<div class="tip-main">
							<i class="save-succseed"></i><span id="message"></span>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</form>
</div>
<!--end body-->
<!--start footer-->
<jsp:include page="/WEB-INF/pages/include/foot.jsp"></jsp:include>
<!--end footer-->
<script type="text/javascript" src="${_jsPath }/pages/editProduct.js"></script>
<script type="text/javascript">
	var STATUS_DRAFT = <%= Product.STATUS_DRAFT%>;
	var STATUS_CHECK_PEND = <%= Product.STATUS_CHECK_PEND%>;
	//图片上传操作
	var uploadDefaultParams = {
			'auto' : true,
			'buttonImg' : '${_jsPath}/plugin/uploadify/uploadimg_btn.png',
			'cancelImg' : '${_jsPath}/plugin/uploadify/cancel.png',
			'expressInstall' : '${_jsPath}/plugin/uploadify/expressInstall.swf',
			'fileDataName'   : 'file', 
			'fileDesc' : '请选择jpg、gif、png文件',
			'fileExt' : '*.jpg;*.jpeg;*.gif;*.png',
		    'height' : 20,
		    'multi' : false,
		    'script' : '${_ctxPath}/upload/upload.htm',
		    'sizeLimit' : 2097152,    
		    'uploader' : '${_jsPath}/plugin/uploadify/uploadify.allglyphs.swf',
		    'width' : 94,
		    'scriptData'  : {'category':'product'}
	};
	 var multiUploadParams = {
			'auto' : true,
			'buttonImg' : '${_jsPath}/plugin/uploadify/uploadimg_btn.png',
			'cancelImg' : '${_jsPath}/plugin/uploadify/cancel.png',
			'expressInstall' : '${_jsPath}/plugin/uploadify/expressInstall.swf',
			'fileDataName'   : 'file', 
			'fileDesc' : '请选择jpg、gif、png文件',
			'fileExt' : '*.jpg;*.jpeg;*.gif;*.png',
		    'height' : 20,
		    'multi' : true,
		    'script' : '${_ctxPath}/upload/upload.htm',
		    'sizeLimit' : 2097152,    
		    'uploader' : '${_jsPath}/plugin/uploadify/uploadify.allglyphs.swf',
		    'width' : 94,
		    'queueSizeLimit' : 5,
		    'scriptData'  : {'category':'product'}
	};
	 
	 $.each( $("#coverProductPicture").find(":file"),function(i,obj){
			var uploadImgParams = uploadDefaultParams;
			uploadImgParams.onComplete = function(event, ID, fileObj, response, data){
				var $td = $(obj).closest("td");
				//移除原有图片
				$td.find(".default-img").find("img").remove();
				//得到缩略图url
				var suffix = response.split(".")[1];
				var url = '${_fileThumbPath}' + response + "_t84." + suffix;
				
				$td.find(".del-img").after("<img src='"+url+"'/>");
				$td.find(".imageUrl").val(response);
				$('#coverImgUrl').val(response);
		     };
			 $(obj).uploadify(uploadImgParams);
		});
	 
	//商品图片上传
	$.each($(".no-border-table").find(":file"),function(i,obj){
		var uploadImgParams = uploadDefaultParams;
		uploadImgParams.onComplete = function(event, ID, fileObj, response, data){
			var $td = $(obj).closest("td");
			//移除原有图片
			$td.find(".default-img").find("img").remove();
			//得到缩略图url
			var suffix = response.split(".")[1];
			var url = '${_fileThumbPath}' + response + "_t84." + suffix;
			
			$td.find(".del-img").after("<img src='"+url+"'/>");
			$td.find(".imageUrl").val(response);
	     };
		 $(obj).uploadify(uploadImgParams);
	});
	
	//商品图片批量上传
	 var uploadMultiImgParams = multiUploadParams;
	var index = 0;
	uploadMultiImgParams.onComplete = function(event, ID, fileObj, response, data){
    	var $td = $(".no-border-table td").eq(index++);
		$td.find(".imageUrl").val(response);
		if(index > 4){
			index = 0;
		}
	};
	uploadMultiImgParams.onAllComplete = function(event, data){
		var i = 0;
		var timer = setInterval(function(){
			if(i == 4){
				clearInterval(timer);
			}
			var $td = $(".no-border-table td").eq(i);
	    	$td.find(".default-img").find("img").remove();
	    	var url = $td.find(".imageUrl").val();
			if(url){
				var suffix = url.split(".")[1];
				var thumbUrl = '${_fileThumbPath}' + url + "_t15." + suffix;
				$td.find(".del-img").after("<img src='" + thumbUrl + "'/>");	
			}
			i++;
		},500);
	};
	$("#uploadBatch").uploadify(uploadMultiImgParams); 
	
	//库存表格数据操作
	<c:if test="${product != null}">
		<c:forEach items="${product.productSkus }" var="proSku" varStatus="status">
			var id = "";
			<c:set var="size" value="${fn:length(proSku.productSkuOptionValues)}"></c:set>
			<c:forEach items="${proSku.productSkuOptionValues }" var="proSkuVal">
 				id += "${proSkuVal.skuOptionValueId}_";
			</c:forEach> 
			if(${size} > 1){
				id = id.substring(0, id.length-1);
			}else if(${size} == 0){
				id = "_";
			}
			var value = {
					"id" : id,
					"productSkuId" : "${proSku.productSkuId}",
					"weight" : "${proSku.skuWeight}",
					"inventory" : "${proSku.inventory}",
					"skuCode" : "${proSku.sellerSkuCode}",
					"unitPrice" : "${proSku.unitPrice}",
					"marketPrice" : "${proSku.marketPrice}",
					"isDefault" : "${proSku.isDefault}"
			};
			data.push(value);
		</c:forEach>
		
		//颜色、尺寸复选框处理
		<c:forEach items="${product.skuOptions }" var="skuOpt">
			<c:forEach items="${skuOpt.skuOptionValues }" var="skuOptVal">
				var $input_checkbox = $(".input-checkbox[value='${skuOptVal.skuOptionValueId}']");
				$input_checkbox.attr("checked", "checked");
				var $span = $input_checkbox.closest("dd").find("span");
				if("${skuOptVal.overrideFlag}" == "1"){
					$input_checkbox.attr("overrideFlag", "1");
					$span.text("${skuOptVal.skuOptionValue}");
				}
				$span.hide().after("<input type='text' maxLength='14' class='c-input' value='"+$span.text()+"' onchange='checkSkuInput(this)'/>");
			</c:forEach>
		</c:forEach>
		
		//商品图片显示
		<c:forEach items="${product.imageList }" var="imageUrl" varStatus="status">
			<c:set var="suffix" value="${fn:split(imageUrl, '.')[1]}"></c:set>
			var $del_img = $(".no-border-table .del-img").eq(${status.index});
			$del_img.after("<img src='${_fileThumbPath}${imageUrl}_t84.${suffix}'/>");
			var $td = $del_img.closest("td");
			$td.find(".imageUrl").val("${imageUrl}");
		</c:forEach>
	</c:if>
	
	//生成库存表格
	createRepertoriesTable();
	
	var editor = CKEDITOR.replace("description");
	if(sellType == '1' || sellType == '3'){
			changeTimeStatus(sellType);
	}
	//验证
	$.formValidator.initConfig({validatorGroup: '1',formID:"addForm",theme:"Default",errorFocus:true,wideWord:false});
	$("#brandId").formValidator({onShow:"请选择品牌",onFocus:"请选择品牌",onCorrect:"谢谢你的配合"}).inputValidator({min:1,onError: "请选择品牌"});
	$("#city").formValidator({onShow:"请选择所在地区",onFocus:"请选择所在地区",onCorrect:"谢谢你的配合"}).inputValidator({min:1,onError: "请选择所在地区"});
	$("#smallcatalog").formValidator({onShow:"请选择分类",onFocus:"请选择分类",onCorrect:"谢谢你的配合"}).inputValidator({min:1,onError: "请选择分类"});
	$("#name").formValidator({onFocus:"请填写1-40个字符组成的商品名称"}).inputValidator({min:1,max:40,empty:{leftEmpty:false,rightEmpty:false,emptyError:"两边不能有空格"},onError:"请填写1-40个字符组成的商品名称"});
	$(".input-radio[name='product.isShowInventory']").formValidator({tipID:"isShowInventoryTip",onShow:"请选择是否显示库存",onFocus:"请选择是否显示库存",onCorrect:"输入正确"}).inputValidator({min:1,max:1,onError:"请选择是否显示库存"});
	$(".imageUrl").formValidator({tipID:"imgUrlTip", onFocus: "请上传完整的商品展示图片", onCorrect: "谢谢你的合作，你的图片名正确" })
	.functionValidator({
		fun:function() {
			if($(".default-img img").length == 0){
				return "请上传完整的商品展示图片";
			}
		}
	});
	$(".coverimageUrl").formValidator({tipID:"coverUrlTip", onFocus: "请上传完整的商品封面图片", onCorrect: "谢谢你的合作，你的图片名正确" })
	.functionValidator({
		fun:function() {
			if($("#default-img").find("img").length == 0){
				return "请上传完整的商品封面图片";
			}
		}
	});
	$(".c-select").formValidator({tipID:"sizeTip", onFocus: "请选择商品规格", onCorrect: "谢谢你的合作" })
	.functionValidator({
		fun:function() {
			var b = false;
			$.each($('.input-checkbox'),function(index,dom){
				if(b) return;
			    b = $(dom)[0].checked
			})
			if(b ==false){
				return "请选择商品规格";
			}
		}
	});
</script>
</body>
</html>