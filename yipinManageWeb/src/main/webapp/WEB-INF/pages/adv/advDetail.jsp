<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="${_jsPath}/plugin/uploadify/swfobject.js"></script>
<script type="text/javascript" src="${_jsPath}/plugin/uploadify/jquery.uploadify.v2.1.4.min.js"></script>
<%
	pageContext.setAttribute("ADRESS_PRODUCT",com.ytoxl.module.yipin.content.dataobject.AdvPosition.ADRESS_PRODUCT);
	pageContext.setAttribute("PRODUCT_CATEGORY",com.ytoxl.module.yipin.content.dataobject.AdvPosition.PRODUCT_CATEGORY);
	pageContext.setAttribute("SHUFFLING_FIGURE",com.ytoxl.module.yipin.content.dataobject.AdvPosition.SHUFFLING_FIGURE);
%>
<div class="body m-w980p">
  		<!--start 商家管理table-->
  		<c:if test="${adv.position.code eq SHUFFLING_FIGURE }">
	  		<form action="${_ctxPath}/admin/adv/adv_saveAdv.htm" method="post" id="advForm">
	  				<input name="adv.advertisementId" value="${adv.advertisementId}" type="hidden"/>
	            	<input name="adv.target" value="${adv.target }" type="hidden"/>
	            	<input name="adv.createUserId" value="${adv.createUserId}" type="hidden"/>
	        <div class="m-mt10p addBanner">
					<!-- 轮播图 -->
					<div class="addADV cf">
						<label for=""><i class="red">*</i>位置：</label>
			          	<%-- <select name="adv.positionId" id="advPositionWH" class="m-sel">
							<c:forEach items="${positions}" var="advPosition">
										<option data-wh="${advPosition.width}:${advPosition.height}" value="${advPosition.positionId}"
											<c:if test="${advPosition.positionId==adv.positionId}"> selected="selected"</c:if>>
											<c:out value="${advPosition.positionName}"></c:out>
										</option>
							</c:forEach>
						</select> --%>
						<div style="display:none;">
				          	<select id="advPositionWH" class="m-sel" readOnly="readOnly" >
								<option data-wh="${adv.position.width}:${adv.position.height}" value="${adv.position.positionId}"selected="selected">
									<c:out value="${adv.position.positionName}"></c:out>
								</option>
							</select>
						</div>
						<div class="infoADV">
						<input  name="adv.positionId" value="${adv.position.positionId}" type="hidden"/>
						<c:out value="${adv.position.positionName}"></c:out>
						</div>
					</div>
					<div class="addADV cf">
						<label for=""><i class="red">*</i>广告位名称：</label>
						<div class="infoADV">
						<input type=text name="adv.advertisementName"  id="advName"  class="txt-input" value="${adv.advertisementName}"/>
						<div id="advNameTip"></div>
						</div>
					</div>
					<div class="addADV cf">
						<label for="">顺序：</label>
						<div class="infoADV">
						<input type="text" name="adv.rank" id="advRank" class="txt-input" value="${adv.rank}"/><span class="red">请选择1-10之间的数字</span>
						<div id="advRankTip"></div>
						</div>
					</div>
					<div class="addADV cf">
						<label for=""><i class="red">*</i>广告图片：</label>
						<div class="infoADV">
	            					<input type="file" id="imgUpload" name="file" />
	            					<div id="advUploadImageTip">
		            					<em class="c-red">*</em> 
										<c:forEach items="${positions}" var="advPosition" varStatus="status">
			            				<span id="advPosition-${advPosition.positionId}" 
			            					<c:choose>
			            					<c:when test="${empty adv }">
			            						<c:if test="${status.index != 0 }">class="m-hide"</c:if>
			            					</c:when>
			            					<c:otherwise>
			            						<c:if test="${adv.positionId != advPosition.positionId }">class="m-hide"</c:if>
			            					</c:otherwise>
			            					</c:choose>
			            				>请上传像素为${adv.position.width}px*${adv.position.height}px的图片</span>
			            				</c:forEach>
		            				</div>
		            				<input type="hidden" id="uploadImageValueId" name="adv.imageUrl" value="${adv.imageUrl}"/>
									<c:if test="${adv.imageUrl != '' && adv.imageUrl!= null}">
										<img src="${_filePath}${adv.imageUrl}" id="showImg" width="${adv.position.width/2 }" height="${adv.position.height/2 }" />
									</c:if>
									<c:if test="${adv.imageUrl == '' || adv.imageUrl==null}">
										<img src="" id="showImg" />
									</c:if>
									<div id="imgTip"></div>
						</div>
					</div>
					<div class="addADV cf">
						<label for=""><i class="red">*</i>跳转地址：</label>
						<div class="infoADV">	
						<input type="text" name="adv.url" id="advUrl"  class="txt-input" value="${adv.url }"/>
						<div id="advUrlTip"></div>
						</div>
					</div>
			</div>
			</form>
		</c:if>
		<c:if test="${adv.position.code eq ADRESS_PRODUCT }">
			<form action="${_ctxPath}/admin/adv/adv_saveAdv.htm" method="post" id="advForm">
		  				<input name="adv.advertisementId" value="${adv.advertisementId}" type="hidden"/>
		            	<input name="adv.target" value="${adv.target }" type="hidden"/>
		            	<input name="adv.createUserId" value="${adv.createUserId }" type="hidden"/>
	        <!--start 商家管理table-->
	        <div class="m-mt10p addBanner">
				<!-- 所在地 -->
					<div class="addADV cf">
						<label for=""><i class="red">*</i>位置：</label>
						<div class="infoADV">
						<input  name="adv.positionId" value="${adv.position.positionId}" type="hidden"/>
						<c:out value="${adv.position.positionName}"></c:out>
						</div>
					</div>
					<div class="addADV cf">
						<label for=""><i class="red">*</i>区域：</label>
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
							<select name="adv.advertisementPositionId" id="category" class="m-sel">
								<option value="-1">请选择</option>
								<c:forEach var="p" items="${provinceP}" varStatus="status">
									<option value="${p.propId }" 
									<c:if test="${adv.advertisementPositionId == p.propId }">
										selected="selected"
									</c:if>
									>${p.name }</option>
								</c:forEach>
							</select>
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
						<input type="text" name="adv.advertisementName" id="advName"  value="${adv.advertisementName }" class="txt-input"/>
						<div id="advNameTip"></div>
						</div>
					</div>
					<div class="addADV cf">
						<label for="">顺序：</label>
						<div class="infoADV">
						<input type="text" name="adv.rank" value="${adv.rank }" id="advRank" class="txt-input"/><span class="red">请选择1-10之间的数字</span>
						<div id="advRankTip"></div>
						</div>
					</div>
					<div class="addADV cf">
						<label for=""><i class="red">*</i>跳转地址：</label>
						<div class="infoADV">
						<input type="text" name="adv.url" id="advUrl" value="${adv.url }"  class="txt-input"/>
						<div id="advUrlTip"></div>
						</div>
					</div>
					<div class="addADV cf">
						<label for=""></label>
					</div>
			 </div>
			 </form>
		</c:if>
       <!--end 商家管理table-->
       <c:if test="${adv.position.code eq PRODUCT_CATEGORY }">
       	<form action="${_ctxPath}/admin/adv/adv_saveAdv.htm" method="post" id="advForm">
	  				<input name="adv.advertisementId" value="${adv.advertisementId}" type="hidden"/>
	            	<input name="adv.target" value="${adv.target }" type="hidden"/>
	            	<input name="adv.createUserId" value="${adv.createUserId }" type="hidden"/>
			<div class="m-mt10p addBanner">
				<!-- 商品分类 -->
					<div class="addADV cf">
						<label for=""><i class="red">*</i>位置：</label>
						<%-- <select class="m-sel " name="adv.positionId">
				          	<c:forEach var="p" items="${positions}">
				          	<option value="${p.positionId }"
				          		<c:if test="${adv.position.positionId == p.positionId}">
				          			selected="selected"
				          		</c:if>
				          		>${p.positionName}</option>
				          	</c:forEach>
				         </select> --%>
				        <div class="infoADV">
				        <input  name="adv.positionId" value="${adv.position.positionId}" type="hidden"/>
						<c:out value="${adv.position.positionName}"></c:out>
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
								>${r.name}</option>
							</c:forEach>
						</select>
						<div id="menusTip" class="onError" style="display:none; margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;">
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
						<select name="adv.advertisementPositionId" id="category" class="m-sel">
							<option value="-1">请选择</option>
							<c:forEach var="p" items="${provinceP}" varStatus="status">
								<option value="${p.propId }" 
								<c:if test="${adv.advertisementPositionId == p.propId }">
									selected="selected"
								</c:if>
								>${p.name }</option>
							</c:forEach>
						</select>
						<div id="categoryTip" class="onError" style="display:none;margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;">
							<span class="onError_top">
							请选择二级分类</span>
							<span class="onError_bot">
							</span>
						</div>
						<span id="categoryTip-c"  style="display:none;margin: 0px; padding: 0px; background-color: transparent; background-position: initial initial; background-repeat: initial initial;">
							<span class="onCorrect"></span>
						</span>
						</div>
					</div>
					<div class="addADV cf">
						<label for=""><i class="red">*</i>广告名称：</label>
						<div class="infoADV">
						<input type="text" name="adv.advertisementName" id="advName"  value="${adv.advertisementName }"  class="txt-input"/>
						<div id="advNameTip"></div>
						</div>
					</div>
					<div class="addADV cf">
						<label for="">顺序：</label>
						<div class="infoADV">
						<input type="text" name="adv.rank"  value="${adv.rank }" id="advRank"  class="txt-input"/><span class="red">请选择1-10之间的数字</span>
						<div id="advRankTip"></div>
						</div>						
					</div>
					<div class="addADV cf">
						<label for=""><i class="red">*</i>跳转地址：</label>
						<div class="infoADV">
						<input type="text" name="adv.url" value="${adv.url }" id="advUrl"  class="txt-input"/>
						<div id="advUrlTip"></div>
						</div>
					</div>
	        </div>
        </form>	
      </c:if>
  </div>
  
<script  type="text/javascript" src="${_jsPath }/plugin/plugin.js" ></script>
<script type="text/javascript"  src="${_jsPath }/pages/bannerManage.js"></script>
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
		$.formValidator.initConfig({formID:"advForm",theme:"Default",submitOnce:true,onError:function(){}});
		$("#advName").formValidator({onShow:"请输入广告名称"}).inputValidator({min:1,max:16,onErrorMin:"请输入大于1个字符",onErrorMax:"请输入小于16个字符",empty:{leftEmpty:false,rightEmpty:false,emptyError:"广告名称两边不能有空格"},onError:"请输入广告名称"});
		$("#advRank")
		.formValidator({empty:false,onShow:"请输入排序号",onCorrect:"谢谢你的合作，你的排序号正确"})
		.inputValidator({min:1,max:2,onErrorMax:"请填写1~10之间数字"})
		.regexValidator({regExp:"intege1",dataType:"enum",onError:"请填写1~10之间数字"})
		.functionValidator({
                    fun: function(val, el) {
	                    if(val>10){
	                    	return "请填写1~10之间数字";
	                    }
                    }
                });
		$("#uploadImageValueId").formValidator({tipID:"imgTip",onShow: "请输入图片名", onCorrect: "谢谢你的合作，你的图片名正确" }).inputValidator({min:1,onErrorMin:"请上传图片"}).regexValidator({ regExp: "picture", dataType: "enum", onError: "图片名格式不正确" });
		$("#advUrl").formValidator({empty:false,onShow:"请输入跳转地址",onFocus:"请输入跳转地址"}).inputValidator({min:3,onError:"你输入网址格式不正确"}).regexValidator({regExp:"url",dataType:"enum",onError:"你输入网址格式不正确"});
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

</script>
