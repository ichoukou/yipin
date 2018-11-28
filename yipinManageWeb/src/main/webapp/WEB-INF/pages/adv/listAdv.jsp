<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.ytoxl.module.yipin.content.dataobject.AdvPosition"%>
<%@include file="../include/taglibs.jsp"%>
<%
	pageContext.setAttribute("ADRESS_PRODUCT",com.ytoxl.module.yipin.content.dataobject.AdvPosition.ADRESS_PRODUCT);
	pageContext.setAttribute("PRODUCT_CATEGORY",com.ytoxl.module.yipin.content.dataobject.AdvPosition.PRODUCT_CATEGORY);
	pageContext.setAttribute("SHUFFLING_FIGURE",com.ytoxl.module.yipin.content.dataobject.AdvPosition.SHUFFLING_FIGURE);
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广告位</title>
    <link href="${_cssPath }/common.css" rel="stylesheet" />
    <link href="${_cssPath }/pages/arrangement.css" rel="stylesheet" />
    <link href="${_cssPath }/pages/bannerManage.css" rel="stylesheet" />
    <script src="${_jsPath }/jquery/jquery.1.8.1.js"  language="javascript"></script>
    <script src="${_jsPath}/plugin/formvalidator/formValidatorRegex.js" language="javascript"></script>
    <script src="${_jsPath}/plugin/formvalidator/formValidator-4.1.3.js" language="javascript"></script>
</head>
<body>
	<!--start header-->
	<jsp:include page="../include/header.jsp"></jsp:include> 
	<div class="body m-w980p">
	 <jsp:include page="../include/pageManageMenu.jsp"  flush="true" /> 
		 <form class="sub-form m-clear m-mt10p" action="${_ctxPath }/admin/adv/show.htm" id="search-form" method="post">
		     <div class="m-fl"><%-- 广告类型 --%>
		          <select class="m-sel allSelect" name="advPage.params.positionId">
		          		<option value="-1">全部</option>
		          	<c:forEach var="p" items="${positions}">
		          	<option value="${p.positionId }"
		          		<c:if test="${advPage.params.positionId == p.positionId}">
		          			selected="selected"
		          		</c:if>
		          		>${p.positionName}</option>
		          	</c:forEach>
		          </select>
		          <input type="submit" class="m-btn m-btn-search" value="查 询" id="search"/>
				  <input type="button" class="m-btn" value="新增轮播图" onclick="addSeller('${SHUFFLING_FIGURE}')"/>
				  <input type="button" class="m-btn" value="新增所在地" onclick="addSeller('${ADRESS_PRODUCT}')"/>	
				  <input type="button" class="m-btn" value="新增商品类型" onclick="addSeller('${PRODUCT_CATEGORY}')"/>		
	         </div>
		     <div class="m-fr curr-num">
				  <c:if test="${not empty advPage.result}">
		                 <span class="m-fr curr-num">
		                     <label>当前显示： </label>
		                     <yp:commPageSize page="${advPage}" beanName="advPage"></yp:commPageSize>
		                 </span>
		          </c:if>
			</div>
		</form>
        <!--start 商家管理table-->
        <div class="m-mt10p business-management">
            <table class="tab-control" id="tab">
                <thead>
                    <tr>
                        <th width="40%">广告名称</th>
                        <th width="15%">广告类型</th>
                        <th width="15%">预览广告</th>
                        <th width="15%">顺序</th>
                        <th width="15%">操作</th>
                    </tr>
                </thead>
                <tbody>
                 <c:forEach items="${advPage.result}" var="adv" varStatus="status">
                 	<tr>
						<td>${adv.advertisementName}</td>
						<td>${adv.position.positionName}</td>
						<td>
							<div class="relative">
								<c:if test="${adv.imageUrl != '' && adv.imageUrl!= null}">
									<a class="preview" style="cursor: pointer;" onclick="imgView('${adv.imageUrl}','${adv.position.width}','${adv.position.height}')">预览</a>
								</c:if>
							</div>
						</td>
						<td>${adv.rank}</td>
						<td><a onclick="clickAddOrEdit('${adv.advertisementId}','${adv.position.code}')" style="cursor: pointer;">编辑</a>&nbsp;<a style="cursor: pointer;" onclick="delAdv('${adv.advertisementId}')" class="m-5">删除</a></td>
					</tr>
                 </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="table-bm-wrap cf">
			<!--S 右功能区-->
			<div class="fn-right">
				<!--S 分页-->
				<div class="pagination pagination-right">
					<c:if test="${not empty advPage.result}">
						<yp:commPage page="${advPage}" beanName="advPage"></yp:commPage>
					</c:if>
				</div>
				<!--E 分页-->
			</div>
			<!--E 右功能区-->
		</div>
       <!--end 商家管理table-->
	 </div>
	 <form action="${_ctxPath }/admin/adv/adv_goToAdd.htm"  id="goToAddForm">
	 	<input name="index" value="" id="index" type="hidden"/>
	 </form>
	<!--end foot-->
	<jsp:include page="/WEB-INF/pages/include/foot.jsp"></jsp:include>
	<script language="javascript">
	function addSeller(type){//添加广告
		$("#index").val(type);
		$("#goToAddForm").submit();
	}
	function delAdv(id){//删除广告
		$.dialog({
			  lock: true,
			  padding: "5px",
			  title:false,
			  content:"确认删除该项吗?",
			  fixed: false,
			  cancel: true,
			  cancelValue:"取 消",
			  ok:function(){
				  $.ajax({
						url:_ctxPath + "/admin/adv/adv_delAdv.htm",
						type:"POST",
						data:{'adv.advertisementId':id},
						dataType:"json",
						async: false,
						success:function(data){
							if(data.code==3){//删除成功
								window.location.reload();
							}else{
								alert("删除失败!");
							}
						}
					});
			  },
			  okValue:"删除"
		});
	}
	
	var clickAddOrEdit=function(id,code){
		//alert(code+"----"+${SHUFFLING_FIGURE});
		var html = '';
		$.ajax({
			url:_ctxPath + "/admin/adv/adv_addAdv.htm",
			type:"POST",
			data:{'adv.advertisementId':id},
			dataType:"html",
			async: false,
			disable:false,
			success:function(data){
				html=data;
				$.dialog({
				  lock: true,
				  padding: "5px",
				  title:false,
				  content:html,
				  fixed: false,
				  cancel: true,
				  cancelValue:"取  消",
				  ok:function(){
					if(code=='${SHUFFLING_FIGURE}'){
						var result = $.formValidator.pageIsValid('1');
						if(!result){
							return false;
						}
						$("#advForm").submit();
					}else if(code=='${ADRESS_PRODUCT}'){
							var f = $("#menus").val();
							var s = $("#category").val();
							var r = true;
							if(f<0){
								$("#menusTip").show();
								$("#menusTip-c").hide();
								r = false;
							}else{
								$("#menusTip").hide();
								$("#menusTip-c").show();
							}
							if(s<0){
								$("#categoryTip").show();
								$("#categoryTip-c").hide();
								r = false;
							}else{
								$("#categoryTip").hide();
								$("#categoryTip-c").show();
							}
							if(!r){
								return false;
							}
							var result = $.formValidator.pageIsValid('1');
							if(!result){
								return false;
							}
							$("#advForm").submit();
							
					}else{
							var f = $("#menus").val();
							var s = $("#category").val();
							var r = true;
							if(f<0){
								$("#menusTip").show();
								$("#menusTip-c").hide();
								r = false;
							}else{
								$("#menusTip").hide();
								$("#menusTip-c").show();
							}
							if(s<0){
								$("#categoryTip").show();
								$("#categoryTip-c").hide();
								r = false;
							}else{
								$("#categoryTip").hide();
								$("#categoryTip-c").show();
							}
							if(!r){
								return false;
							}
							var result = $.formValidator.pageIsValid('1');
							if(!result){
								return false;
							}
							$("#advForm").submit();
					}
				  },
				  okValue:"发  布"
				});
			},
			error:function(data){
				//alert(data.responseText);
			}
		});
	} 
	function addAdv(index){
		var result = $.formValidator.pageIsValid('1');
		if(index!=1){
			var f = $("#menus").val();
			var s = $("#category").val();
			if(f<0){
				$("#menusTip").show();
				$("#menusTip-c").hide();
				return false;
			}else{
				$("#menusTip").hide();
				$("#menusTip-c").show();
			}
			if(s<0){
				$("#categoryTip").show();
				$("#categoryTip-c").hide();
				return false;
			}else{
				$("#categoryTip").hide();
				$("#categoryTip-c").show();
			}
			return true;
		}
		if(!result){
			return false;
		}
		$("#advForm").submit();
	}
	var imgView=function(src, width, height){
		var suffix = src.split(".")[1];
	var url =src + "_t5." + suffix;
		if(src!='' && src != null){
			var html = '<img src="'+_fileThumbPath+url+'"/>';
			  $.dialog({
			    lock: true,
			    padding: "5px",
			    title:false,
			    content:html,
			    fixed: true,
			    cancel: true,
			    cancelValue: "关  闭",
			    closed:false
			  });
			  $(".d-close").hide();
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	</script>
</body>
</html>