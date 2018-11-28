<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.ytoxl.module.yipin.content.dataobject.AdvPosition"%>
<%@include file="../include/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广告位</title>
    <link href="${_cssPath }/common.css" rel="stylesheet" />
    <link href="${_cssPath }/pages/arrangement.css" rel="stylesheet" />
    <link href="${_cssPath }/pages/bannerManage.css" rel="stylesheet" />
    <link href="${_jsPath }/plugin/artdialog/skins/ytoxl.css" rel="stylesheet" />
    <script src="${_jsPath }/jquery/jquery.1.8.1.js"  language="javascript"></script>
    <script src="${_jsPath }/plugin/artdialog/jquery.artDialog.min.js"></script>
    <script type="text/javascript" src="${_jsPath}/plugin/uploadify/swfobject.js"></script>
	<script type="text/javascript" src="${_jsPath}/plugin/uploadify/jquery.uploadify.v2.1.4.min.js"></script>
	<script src="${_jsPath}/plugin/formvalidator/formValidator-4.1.3.js" language="javascript"></script>
	<script src="${_jsPath}/plugin/formvalidator/formValidatorRegex.js" language="javascript"></script>
</head>
<%
	pageContext.setAttribute("ADRESS_PRODUCT",com.ytoxl.module.yipin.content.dataobject.AdvPosition.ADRESS_PRODUCT);
	pageContext.setAttribute("PRODUCT_CATEGORY",com.ytoxl.module.yipin.content.dataobject.AdvPosition.PRODUCT_CATEGORY);
	pageContext.setAttribute("SHUFFLING_FIGURE",com.ytoxl.module.yipin.content.dataobject.AdvPosition.SHUFFLING_FIGURE);
%>
<body>
	<!--start header-->
	<jsp:include page="../include/header.jsp"></jsp:include> 
	<div class="body m-w980p">
	 <jsp:include page="../include/pageManageMenu.jsp"  flush="true" /> 
		 <c:if test="${index eq SHUFFLING_FIGURE}"><%--轮播图 --%>
				<form action="${_ctxPath}/admin/adv/adv_saveAdv.htm" method="post" id="advForm">
	  				<input name="adv.advertisementId" value="" type="hidden"/>
	            	<input name="adv.target" value="" type="hidden"/>
	            	<input name="adv.advertisementPositionId" value="" type="hidden"/>
	        <div class="m-mt10p addBanner">
				<!-- 轮播图 -->
				<div class="addADV cf">
					<label for=""><i class="red">*</i>位置：</label>
					<div class="infoADV">
						<div style="display:none;">
				          	<select id="advPositionWH" class="m-sel" readOnly="readOnly" >
								<option data-wh="${position.width}:${position.height}" value="${position.positionId}"selected="selected">
									<c:out value="${position.positionName}"></c:out>
								</option>
							</select>
						</div>
						<input  name="adv.positionId" value="${position.positionId}" type="hidden"/>
						<c:out value="${position.positionName}"></c:out>
					</div>
				</div>
				<div class="addADV cf">
					<label for=""><i class="red">*</i>广告位名称：</label>
					<div class="infoADV">
						<input type=text name="adv.advertisementName" id="advName" class="txt-input" />
						<div id="advNameTip"></div>
					</div>
				</div>
				<div class="addADV cf">
					<label for="">顺序：</label>
					<div class="infoADV">
						<input type="text" name="adv.rank" id="advRank" class="txt-input" /><span class="red">请选择1-10之间的数字</span>
						<div id="advRankTip"></div>
					</div>
				</div>
				<div class="addADV cf">
					<label for=""><i class="red">*</i>广告图片：</label>
					<div class="infoADV">
	            		<input type="file" id="imgUpload" class="txt-input" name="file" />
	            		<div id="advUploadImageTip" ">
		            					<em class="c-red">*</em> 
			            				<span id="advPosition-${position.positionId}" class="red">请上传像素为${position.width}px*${position.height}px的图片</span>
		            	</div>
		            	<input type="hidden" id="uploadImageValueId" id="img" name="adv.imageUrl"/>
						<img src="" id="showImg" />
						<div id="imgTip"></div>
					</div>
				</div>
				<div class="addADV cf">
					<label for=""><i class="red">*</i>跳转地址：</label>
					<div class="infoADV">
						<input type="text" name="adv.url" id="advUrl" class="txt-input" />
						<div id="advUrlTip"></div>
					</div>
				</div>
				<div class="addADV cf">
					<label>&nbsp;</label>
					<div class="infoADV">
						<input type="button" id="submitForm"  onclick="addAdv('1')" value="保存" class="m-btn"/>
						<input type="button" id="cancelForm" value="取消" onclick="window.history.go(-1)" class="m-btn"/>
					</div>
				</div>
			</div>
			</form>
		 </c:if>
		 <c:if test="${index eq ADRESS_PRODUCT}"><%--所在地 --%>
		 <form action="${_ctxPath}/admin/adv/adv_saveAdv.htm" method="post" id="advForm">
				<!--start 商家管理table-->
	       		<div class="m-mt10p addBanner">
					<div class="addADV cf">
						<label for=""><i class="red">*</i>位置：</label>
						<div class="infoADV">
							<input  name="adv.positionId" value="${position.positionId}" type="hidden"/>
							<c:out value="${position.positionName}"></c:out>
						</div>
					</div>
					<div class="addADV cf">
						<label for=""><i class="red">*</i>区域：</label>
						<div class="infoADV" style="float: left;">
							<select  id="menus" class="m-sel">
								<option value="-1">请选择</option>
								<c:forEach var="r" items="${regionP}" varStatus="status">
									<option value="${r.propId }" 
									<c:if test="${adv.p.parentId == r.propId }">
										selected="selected"
									</c:if>
									>${r.name }</option>
								</c:forEach>
							</select>
						    <div id="menusTip" class="onError" style="display:none;margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;">
										<span class="onError_top">
										请选择区域</span>
										<span class="onError_bot">
										</span>
							</div>
							<span id="menusTip-c"  style="display:none;margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;">
								<span class="onCorrect"></span>
							</span>
						</div>
					</div>
					<div class="addADV cf">
						<label for=""><i class="red">*</i>所在地：</label>
						<div class="infoADV">
							<select name="adv.advertisementPositionId" id="category" class="m-sel"><option value="-1">请选择</option></select>
							<div id="categoryTip" class="onError" style="display:none;margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;">
								<span class="onError_top">
								请选择所在地</span>
								<span class="onError_bot">
								</span>
							</div>
							<span id="categoryTip-c" style="display:none;margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;">
									<span class="onCorrect"></span>
							</span>
							</div>
					</div>
					<div class="addADV cf">
						<label for=""><i class="red">*</i>广告名称：</label>
						<div class="infoADV">
							<input type="text"  name="adv.advertisementName" id="advName" class="txt-input"/>
							<div id="advNameTip"></div>
						</div>
					</div>
					<div class="addADV cf">
						<label for="">顺序：</label>
						<div class="infoADV">
							<input type="text"  name="adv.rank" id="advRank" class="txt-input"/><span class="red">请选择1-10之间的数字</span>
							<div id="advRankTip"></div>
						</div>
					</div>
					<div class="addADV cf">
						<label for=""><i class="red">*</i>跳转地址：</label>
						<div class="infoADV">
							<input type="text" name="adv.url"  id="advUrl" class="txt-input"/>
							<div id="advUrlTip"></div>
						</div>
					</div>
					<div class="addADV cf">
						<label>&nbsp;</label>
						<div class="infoADV">
							<input type="button" id="submitForm"  onclick="addAdv('2')" value="保存" class="m-btn"/>
							<input type="button" id="cancelForm" value="取消" onclick="window.history.go(-1)" class="m-btn"/>
						</div>
					</div>
				</div>
				</form>
	       <!--end 商家管理table-->
		 </c:if>
		 <c:if test="${index eq PRODUCT_CATEGORY}"><%--商品分类 --%>
		  <form action="${_ctxPath}/admin/adv/adv_saveAdv.htm" method="post" id="advForm">
			 <!--start 商家管理table-->
        	<div class="m-mt10p addBanner">
				<div  class="addADV cf">
					<label for=""><i class="red">*</i>位置：</label>
					<div class="infoADV">
					<input  name="adv.positionId" value="${position.positionId}" type="hidden"/>
					<c:out value="${position.positionName}"></c:out>
					</div>		
				</div>
				<div class="addADV cf">
					<label for=""><i class="red">*</i>分类：</label>
					<div class="infoADV">
						<select  id="menus" class="m-sel">
								<option value="-1">请选择</option>
								<c:forEach var="r" items="${regionP}" varStatus="status">
									<option value="${r.propId }" 
									<c:if test="${adv.p.parentId == r.propId }">
										selected="selected"
									</c:if>
									>${r.name }</option>
								</c:forEach>
						</select>	
						<div id="menusTip" class="onError" style="display:none;margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;">
									<span class="onError_top">
									请选择分类</span>
									<span class="onError_bot">
									</span>
						</div>
						<span id="menusTip-c"  style="display:none;margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;">
							<span class="onCorrect"></span>
						</span>
					</div>
				</div>
				<div class="addADV cf">
					<label for=""><i class="red">*</i>二级分类：</label>
					<div class="infoADV">
					<select name="adv.advertisementPositionId" id="category" class="m-sel"><option value="-1">请选择</option></select>
						<div id="categoryTip" class="onError" style="display:none;margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;">
								<span class="onError_top">
								请选择二级分类</span>
								<span class="onError_bot">
								</span>
						</div>
						<span id="categoryTip-c" style="display:none;margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;">
								<span class="onCorrect"></span>
						</span>
					</div>
				</div>
				<div class="addADV cf">
					<label for=""><i class="red">*</i>广告名称：</label>
					<div class="infoADV">
					<input type="text" name="adv.advertisementName" id="advName" class="txt-input"/>
					<div id="advNameTip"></div>
					</div>
				</div>
				<div class="addADV cf">
					<label for="">顺序：</label>
					<div class="infoADV">
					<input type="text" name="adv.rank" id="advRank" class="txt-input"/><span class="red">请选择1-10之间的数字</span>
					<div id="advRankTip"></div>
					</div>
				</div>
				<div class="addADV cf">
					<label for=""><i class="red">*</i>跳转地址：</label>
					<div class="infoADV">
					<input type="text" name="adv.url" id="advUrl" class="txt-input"/>
					<div id="advUrlTip"></div>
					</div>
				</div>
				<div class="addADV cf">
						<label>&nbsp;</label>
						<div class="infoADV">
							<input type="button" id="submitForm"  onclick="addAdv('2')" value="保存" class="m-btn"/>
							<input type="button" id="cancelForm" value="取消" onclick="window.history.go(-1)" class="m-btn"/>
						</div>
					</div>		
        	</div>
		</form>
       <!--end 商家管理table-->
		 </c:if>
	 </div>
	<!--end foot-->
	<jsp:include page="/WEB-INF/pages/include/foot.jsp"></jsp:include>
</body>
<script type="text/javascript">
$(document).ready(function(){
		if('${adv.imageUrl}'==''||'${adv.imageUrl}'==null){
			$('#showImg').hide();
		}
		var uploadDefaultParams = {
			'auto' : true,
			'buttonImg' : '${_jsPath}/plugin/uploadify/uploadimg_btn.png',
			'cancelImg' : '${_jsPath}/plugin/uploadify/cancel.png',
			'expressInstall' : '${_jsPath}/plugin/uploadify/expressInstall.swf',
			'fileDataName' : 'file',
			'fileDesc' : '请选择jpg、gif、png文件',
			'fileExt' : '*.jpg;*.jpeg;*.gif;*.png',
			'multi' : false,
			'script' : '${_ctxPath}/upload/upload.htm',
			'sizeLimit' : 2097152,
			'uploader' : '${_jsPath}/plugin/uploadify/uploadify.allglyphs.swf'
		};
		var uploadlogoParams = uploadDefaultParams;
		uploadlogoParams.scriptData = {'category' : 'adv'}; //目录名字 可以是多级  logo/2013/02/26
		uploadlogoParams.onComplete = function(event, ID, fileObj, response, data) {
			$('#uploadImageValueId').val(response);
			var url = '${_filePath}' + response;
			var whArray = $("#advPositionWH > option:selected").attr("data-wh").split(":");
			$('#showImg').attr({src: url, width: whArray[0]/2 + "px", height: whArray[1]/2 + "px"});
			$('#showImg').show();
		};
		$('#imgUpload').uploadify(uploadlogoParams);
		$("#menus").change(function(){
	        var index = $(this).children('option:selected').val();  //弹出select的值
	        if(index<0){
	        	$("#category").html("<option value=\"-1\">请选择</option>");
	        	$("#menusTip").show();
				$("#menusTip-c").hide();
				$("#categoryTip-c").hide();
	        }else{//ajax 请求后台
	        	$("#menusTip").hide();
				$("#menusTip-c").show();
	        	$.ajax({
	    			url:_ctxPath + "/admin/adv/showCategory.htm",
	    			type:"POST",
	    			data:{'adv.advertisementPositionId':index},
	    			dataType:"json",
	    			async: false,
	    			success:function(data){
	    				var html = "<option value=\"-1\">请选择</option>";
	    				if(data.info!=null&&data.info!=undefined){//不等于
	    					var array =  eval('(' + data.info + ')');   
	    					for(var i=0;i<array.length;i++){
	    						//alert(array[i].propId+""+array[i].name);
	    						html +="<option value='"+array[i].propId+"'>"+array[i].name+"</option>";;
	    					}
	    				}
	    				$("#category").html(html);
	    			}
	    		});
	        }
	    });
		$("#category").change(function(){
			 var index = $(this).children('option:selected').val();  //弹出select的值
			 if(index<0){
				 $("#categoryTip").show();
				 $("#categoryTip-c").hide();
			 }else{
				 $("#categoryTip").hide();
				 $("#categoryTip-c").show();
			 }
		});
	});

//选择广告位置 改变图片大小提示
$(function(){
	var advPositionWH = $("#advPositionWH");
	var data,dataDetail;
	advPositionWH.on("change",function(){
		var $this = $(this).find("option:selected");
		var advPositionId = $this.val();
		$("#advUploadImageTip > span:visible").hide();
		$("#advPosition-" + advPositionId).show();
	});
	
});
function addAdv(index){
	var result = $.formValidator.pageIsValid('1');
	if(index!=1){
		var f = $("#menus").val();
		var s = $("#category").val();
		var r = true;
		if(f<0){
			$("#menusTip").show();
			$("#menusTip-c").hide();
			r =  false;
		}else{
			$("#menusTip").hide();
			$("#menusTip-c").show();
		}
		if(s<0){
			$("#categoryTip").show();
			$("#categoryTip-c").hide();
			r =  false;
		}else{
			$("#categoryTip").hide();
			$("#categoryTip-c").show();
		}
		if(!r){
			return r;	
		}
	}
	if(!result){
		return false;
	}
	$("#advForm").submit();
}
$.formValidator.initConfig({formID:"advForm",theme:"Default",submitOnce:true,onError:function(){}});
$("#advName").formValidator({onShow:"请输入广告名称"}).inputValidator({min:1,max:16,onErrorMin:"请输入大于1个字符",onErrorMax:"请输入小于16个字符",empty:{leftEmpty:false,rightEmpty:false,emptyError:"广告名称两边不能有空格"},onError:"请输入广告名称"});
$("#advRank").formValidator({empty:false,onShow:"请输入排序号",onCorrect:"谢谢你的合作，你的排序号正确"}).inputValidator({min:0,max:2,onErrorMax:"请填写1~10之间数字"}).regexValidator({regExp:"intege1",dataType:"enum",onError:"请填写1~10之间数字"})
	.functionValidator({
                    fun: function(val, el) {
	                    if(val>10){
	                    	return "请填写1~10之间数字";
	                    }
                    }
                });;
$("#uploadImageValueId").formValidator({tipID:"imgTip",onShow: "请输入图片名", onCorrect: "谢谢你的合作，你的图片名正确" }).inputValidator({min:1,onErrorMin:"请上传图片"}).regexValidator({ regExp: "picture", dataType: "enum", onError: "图片名格式不正确" });
$("#advUrl").formValidator({empty:false,onShow:"请输入跳转地址",onFocus:"请输入跳转地址"}).inputValidator({min:3,onError:"你输入网址格式不正确"}).regexValidator({regExp:"url",dataType:"enum",onError:"你输入网址格式不正确"});



</script>
</html>