﻿<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.ytoxl.module.yipin.base.dataobject.Sale"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<html>
<head>
<meta charset="utf-8" />
<title>编辑预售</title>
<link href="${_cssPath }/common.css" rel="stylesheet" />
<link href="${_cssPath }/pages/arrangement.css" rel="stylesheet" />
<script type="text/javascript" src="${_jsPath }/jquery/jquery.1.8.1.js"></script>
<script type="text/javascript"	src="${_jsPath }/plugin/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"	src="${_jsPath }/plugin/formvalidator/formValidatorRegex.js"></script>
<script type="text/javascript"	src="${_jsPath }/plugin/formvalidator/formValidator-4.1.3.js"></script>
<body>
	<!--start header-->
	<jsp:include page="../include/header.jsp"></jsp:include>
	<!--end header-->
	<!--start body-->
	<div class="m-w980p">
		<!--star 新增特卖 -->
		<form id="sale_date">
			<div class="addSale-table">
				<div class="addSale-time m-clear">
					<input type="hidden" name="sale.saleId" id="saleId"/>
					<div class="m-fl">
						<em class="c-red">*</em>&nbsp;销售方式
						<input type="radio" name="sale.sellType" value="1" checked="checked" onclick="radioChange(this);">预售&nbsp;
						<input type="radio" name="sale.sellType" value="2" onclick="radioChange(this);">销售
					</div>
					</br></br>
					<div class="m-fl" id="preSellTimeDiv">
						<em class="c-red">*</em> <span>选择预发货日期：</span> <input type="text"
							class="Wdate"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-{%d+1}',readOnly:true})"
							value="<fmt:formatDate value='${saleInfo.preSelltime }' pattern='yyyy-MM-dd'/>"
							name="sale.preSelltime" id="preSelltime" />
						<div id="dateTip"></div>
					</div>
					
				</div>
				<div class="addSale-sel m-clear">
					<div class="m-fl">
						<em class="c-red">*</em> <span>选择品牌城市：</span>
						 <select class="m-sel" name="city.cityId" id="city">
							<option value="">选择城市</option>
							<c:forEach items="${cityList}" var="city">
								<option value="${city.cityId }" 
								<c:if test="${sale.cityId==city.cityId}">
									selected = "selected"
								</c:if>
								>${city.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="m-fl">
						<em class="c-red">*</em> <span>选择特卖品牌：</span>
						 <select class="m-sel" name="sale.brandId" id="brand">
							<option value="">选择品牌</option>
							<c:forEach items="${brands}" var="brand">
								<option value="${brand.brandId }" 
									<c:if test="${sale.brandId==brand.brandId }">
										selected = "selected"
									</c:if>
								>${brand.name}</option>
							</c:forEach>
						</select>
					</div>
					<div id="brandTip"></div>
				</div>
				<div class="addSale-select-product">
					<span id="loading" style="display: none;">数据正在加载中……</span>
				</div>
				<!--star 商品排序 -->
				<div>
					<b>商品权重：(1-10)</b>
				</div>
				<div id="productTip"></div>
				<div class="addSale-product-seqencing" id="addSale-product-seqencing">
					<ul></ul>
					<div class="clear"></div>
				</div>
				<div class="m-mt10p">
					<aut:authorize url="/admin/brand/brand-add">
					<input type="button" class="m-btn" value="保&nbsp;&nbsp;存" onclick="saveBtn();"/>
					<input type="button" class="m-btn" value="取&nbsp;&nbsp;消" onclick="canelBtn();" />
					</aut:authorize>
				</div> 
			</div>
		</form>
	</div>
	<!--end 新增特卖 -->
	<!--start footer-->
	<jsp:include page="/WEB-INF/pages/include/foot.jsp"></jsp:include>
	<!--end footer-->
	<script type="text/javascript">
		// 刷新页面 
		function pageLoad(){  
		   clear();
		   $("#saleId").val("");
		   $("#city").find("option[value='']").attr("selected",true);
		} 
		// 预售类型radio切换
		function radioChange(radio){
			if($(radio).val() == <%=Sale.TYPE_SELL%>){
			    $("#preSellTimeDiv").hide();
			    $("#preSellTimeDiv").attr("disabled",true).unFormValidator(true);
		    }else{
		    	$("#preSellTimeDiv").show();
		    	 $("#preSellTimeDiv").attr("disabled",false).unFormValidator(false);
		    };
		}
		// 城市下拉框下拉事件
		$("#city").bind({
			focus:function(){
				$(this).change(function(){
					clear();
					if($("#city").val() == ""){
						$("#brand").empty();
						$("#brand").append("<option value=''>选择品牌</option>");
					}else{
						$.ajax({
							type : 'POST',
							url : _ctxPath
									+ '/admin/sale/sale-searchBrandsByCityId.htm?cityId='+$("#city").val(),
							success : function(data) {
								var barndList = jQuery.parseJSON(data.info);
								  $("#brand").empty();
								  $("#brand").append("<option value=''>选择品牌</option>");
								  $.each(barndList,function(i,brand){
									  $("#brand").append("<option value='"+brand.brandId+"'>"+brand.name+"</option>");
								  });
							}
						}); 
					}
				})
			},
			blur:function(){
				$(this).unbind("change");
			}
		});
		// 预售信息编辑
		function editSale(){
			$("#saleId").val(${saleInfo.saleId});
			loadProducts();
			<c:forEach items="${userInfos}" var="userInfo"  varStatus="status">
				var totalCount = $("#totalCountSale"+"${userInfo.userInfoId}").val();
				for(var i=0;i<totalCount;i++){
					<c:forEach items="${userInfo.productList}" var="product"  varStatus="status">
						var productId = $("#productId"+"${userInfo.userInfoId}"+i).val();
						if(productId == "${product.productId}"){
						var complexIndex = "${userInfo.userInfoId}"+i;
						var rank = ${product.saleProduct.rank}+"";
						if(rank == 0){
							rank ="";
						}
						var $li = $("<li id='productId-"+complexIndex+"'>"
								+ "<input type='text' class='product-seqencing-num' productId='"
								+ productId + "' value = '"+rank+"' onkeyup='javascript:replaceRankText(this);' maxlength='2'/></li>");
						var $img = $("#productCbx"+complexIndex).closest("li").find("img").clone();
			 			$li.prepend($img);
			 			$("#productCbx"+complexIndex).attr("checked","true");
						$("#addSale-product-seqencing").append($li);
						};
					</c:forEach>
				} 
			</c:forEach> 
			$("input:radio[name='sale.sellType']").each(function(){
			    if($(this).val() == ${saleInfo.sellType}+"" && $(this).val() == <%=Sale.TYPE_SELL%>){
			    	$(this).attr("checked","true");
			    	$("#preSellTimeDiv").hide();
			    };
			});
		}
		function clear() {
			$(".addSale-select-product").empty();
			$("#addSale-product-seqencing").empty();
		} 
		//加载商品信息方法
		function loadProducts() {
			$("#loading").show();
			var value = {
				"sale.brandId" : $("#brand").val(),
				"sale.saleId":$("#saleId").val()
			};
			$.ajax({
				type : 'POST',
				async: false,
				url : _ctxPath
						+ '/admin/sale/sale-loadProductList.htm',
				data : value,
				success : function(html) {
					$("#loading").hide();
					 clear();
					$(".addSale-select-product").append(html);
				}
			}); 
		}

		<c:if test="${not empty saleInfo}">
			editSale();
		</c:if>
		<c:if test="${empty saleInfo}">
			pageLoad();
		</c:if>
		// 全选和反选
		function selectAll(userInfoId,index) {
			var selectAllVal = $("#select-all"+index).attr("checked");
			var $checkboxs = $('#addSale-product'+index+' input[type="checkbox"]');
			$.each($checkboxs,function(i,checkbox){
				if(selectAllVal=="checked"){
					$(checkbox).attr("checked","true");
				}else{
					$(checkbox).removeAttr("checked");
				}
			});
			var totalCount = $("#totalCountSale"+userInfoId).val();
			for(var i=0;i<totalCount;i++){
				var productId = $("#productId"+userInfoId+i).val();
				setProcRank(productId,userInfoId,i);
			} 
		}
		// 选中需设置权重商品
		function setProcRank(productId,userInfoId,index){
			var complexIndex = ""+userInfoId+index;
			 var productCbxVal =$("#productCbx"+complexIndex).attr("checked");
			 var existsVal =$("#productId-"+complexIndex).val(); 
			 if(existsVal != 0){
	 			var $li = $("<li id='productId-"+complexIndex+"'>"
						+ "<input type='text' class='product-seqencing-num'  productId='"
						+ productId + "' onkeyup='javascript:replaceRankText(this);' maxlength='2'/></li>");
				var $img = $("#productCbx"+complexIndex).closest("li").find("img").clone();
	 			$li.prepend($img);
				 $("#addSale-product-seqencing").append($li);
			}else{
				 if(productCbxVal != "checked"){
					 $("#productId-"+complexIndex).remove();
				 }
			}
		}
		// 文本框只能输入1-10的限制
		function replaceRankText(input){
			var value = $(input).val();
			$(input).val($(input).val().replace(/[^0-9]/g,''));
			if($(input).val()>10){
				$(input).val(10);
			}
			if($(input).val()==0){
				$(input).val("");
			}
		}
		
		//品牌、品类下拉框事件(此写法解决校验框架引起change事件两次触发问题)
		$("#brand").bind({
			focus:function(){
				$(this).change(function(){
					clear();
					if($(this).val() != ""){
						var saleId =$("#saleId").val();
						$("#saleId").val("");
						loadProducts();
						$("#saleId").val(saleId);
						var $brandTip = $("#brandTip");
						$brandTip.addClass("onCorrect");
						$brandTip.empty();
						$brandTip.html("<span class=\"onCorrect\"></span>"); 
					}
				});
			},
			blur:function(){
				$(this).unbind("change");
			}
		});
		// 取消按钮
		function canelBtn(){
			$("#saleId").val("");
	    	window.location.href=_ctxPath + "/admin/sale/sale-searchSales.htm";
		};
		// 保存按钮
		function saveBtn(){
			var strList = "";
		 	var result = $.formValidator.pageIsValid('1');
		 	var radioVal=$('input:radio[name="sale.sellType"]:checked').val();
			if(radioVal==null){
				alert("请选择销售方式！");
				return false;
			}
			if($(".addSale-select-product input:checked").length == 0){
				alert("请选择特卖商品！");
				return false;
			}
			if($("#preSelltime").val() == "" && radioVal == <%=Sale.TYPE_PRE%>){
				alert("请选择预售时间！");
				return false;
			}
			var dataTemp ="sale.sellType="+$('input:radio[name="sale.sellType"]:checked').val()+"&";
			dataTemp =dataTemp+"sale.preSelltime="+$("#preSelltime").val()+"&";
			dataTemp = dataTemp+"sale.brandId="+$("#brand").val()+"&";
	    	$.each($(".addSale-product-seqencing input"), function(i, input){
	    		dataTemp = dataTemp + "sale.saleProductList["+i+"].productId="+$(input).attr("productId")+"&";
	    		dataTemp = dataTemp + "sale.saleProductList["+i+"].rank="+$(input).val()+"&";
			});
	    	dataTemp = dataTemp + "sale.saleId="+$("#saleId").val();
			//保存预售信息
			$.ajax({
				type: "POST",
			    url: _ctxPath + "/admin/sale/sale-saveSale.htm",
				data: dataTemp,
			    success: function (msg) {
			    	if(msg.code == "success"){
			    		alert(msg.info);
			    		$("#saleId").val("");
			    		window.location.href=_ctxPath + "/admin/sale/sale-searchSales.htm";
			    	}else if(msg.code == "false"){
			    		alert(msg.info);
			    	};
			    },
				error: function(xhr){
					popupDialog("保存预售出错！" + xhr.responseText);
		        }
			}) 
		};
		$(".addSale-product img,.addSale-product-seqencing img,.look-sale-product img").live("mouseenter",function(){
			var this_left =$(this).parent().position().left + 65;
			if( this_left > 820){
				$(this).parent().find(".sale-product-info").removeClass("left-icon").addClass("right-icon");
			};
			$(this).parent().find(".sale-product-info").show();
		});
		$(".addSale-product li img,.addSale-product-seqencing li img,.look-sale-product li img").live("mouseleave",function(){
			$(this).parent().find(".sale-product-info").hide();
		});
		// 验证
		$.formValidator.initConfig({formID:"sale_date",theme:"Default",errorFocus:false,wideWord:false});
		$("#preSelltime").formValidator({tipID:"dateTip",onShow:"请选择预售时间",onFocus:"请选择预售时间",onCorrect:"时间选择正确"}).regexValidator({regExp:"date",dataType:"enum",onError:"日期格式不正确"});
		$("#brand").formValidator({tipID:"brandTip",onempty:"请选择品牌",onShow:"请选择品牌",onFocus:"品牌必须选择",onCorrect:"谢谢你的配合"}).inputValidator({min:1,onError: "品牌必须选择!"});
	</script>
</body>
</html>