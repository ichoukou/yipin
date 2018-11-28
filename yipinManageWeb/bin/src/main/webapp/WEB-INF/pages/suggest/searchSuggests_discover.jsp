<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>站内信</title>
		<link href="${ _cssPath}/common.css" rel="stylesheet" />
		<style type="text/css">
			.tucao .c-blue{padding-left:0;}
			.order-info .reply textarea{width:180px;height:50px;border:1px solid #cdcdcd;padding:5px 0 0 5px;}
			.order-info .reply a{color:#36c;}
			.order-info .send_point input{border:1px solid #cdcdcd;padding:3px 0 3px 5px;}
			.order-info .send_point a{color:#36c;}
			.order-info .show_msg,.order-info .show_point{display:none;}
		</style>
		<script src="${_jsPath }/jquery/jquery.1.8.1.js" language="javascript"></script>
		<script type="text/javascript">
		
			$(function(){
				//阅读发现
				$(".no-read").click(function(){
					var id=$(this).attr('id');
					var status = $("#status_2").val();
					$.ajax({
						url : "${_ctxPath}/admin/suggest/suggest-updateSuggestStatus.htm",
						type : "POST",
						data : {
							'suggest.suggestId' : id,
							'suggest.status':status
						},
						dataType : "json",
						success : function(data) {
							
						},
						error : function(data) {
							console.log(data);
						}
					});
					$(this).removeClass("no-read");
				});
			});
			
			// 删除 发现
			var deleteSuggest=function(id,status){
				var str = "suggestPage.params.status=";
				var p = str.length
				var h = location.href;
				var i = h.lastIndexOf("suggestPage.params.status=")+p;
				var j = h.substr(i,1);
				var flag = $.isNumeric(j);
				if(!flag){
					j="";
				}
				$.dialog({
					title: false,
					content: "<span style='color:#C00;'><br/>您确定要禁止此条记录？</span>",
					lock:true,
					fixed:true,
			        ok: function(){
				 		$.ajax({   
				 			   url:"${_ctxPath}/admin/suggest/suggest-updateSuggestStatus.htm",   
				 			   type:"POST",
				 			   data:{'suggest.suggestId':id,
				 				  'suggest.status':status
				 			   },
				 			   dataType:"json",   
				 			   success:function(data){   
				 					alert(data.info);
				 					window.location.href="${_ctxPath}/admin/suggest/suggest-searchSuggests.htm?suggestPage.params.type=0&suggestPage.params.status="+j
				 			   },
				 			   error:function(data){
				 				   //console.log(data);
				 			   }
				 		 	});
			        	},
			        okValue: '确定'
			     });
			};
		
			//发现回复
			var confirmContent = function(id){
				var revertContent = $("#repTxtare_"+id).val();
				var len = $.trim(revertContent).length
				// 回复字数在0~1000字之内
				if( 1000 >= len && len > 0){
					var d = $.dialog({
						title: false,
						content: "<span style='color:#C00;'>正在保存....</span>",
						lock:true,
						fixed:true
					});
					$(".d-close").hide();
					$.ajax({
						type : 'POST',
						url : '${_ctxPath}/admin/suggest/suggest-updateSuggestStatus.htm',
						data : {'suggest.suggestId':id,
								'suggest.revertContent' : revertContent
								},
						success : function(data) {
							d.close();
							if(data.code=='true'){
								var content='回复：'+ data.info;
								$("#show_msg_"+id).html(content);
								$("#reply_"+id).hide(); //隐藏当前
								$("#show_msg_"+id).show(); //显示回复内容
							}else{
								alert(data.info);
							}
						},
						error:function(data){
							alert(data.info);
						}
					});
				}else{
					alert("请输入1000字以内个文字!");
				}
			};
			
			//给分
			var confirmAddPoint = function(id,userId,type){
				var point = $("#potTxt_"+id).val();
				if(validatePoint(point)){
					var d = $.dialog({
						title: false,
						content: "<span style='color:#C00;'>正在保存....</span>",
						lock:true,
						fixed:true
					});
					$(".d-close").hide();
					$.ajax({
						type : 'POST',
						url : '${_ctxPath}/admin/suggest/suggest-addPointDetail.htm',
						data : {'suggest.suggestId':id,
								'suggest.point' : point,
								'suggest.userId' : userId,
								'suggest.type' : type
								},
						success : function(data) {
							d.close();
							if(data.code=='true'){
							}else{
								alert(data.info);
							}
						},
						error:function(data){
							alert(data.info);
						}
					});
					var point='分数：'+point;
					$("#show_point_"+id).html(point);
					$('#send_point_'+id).hide(); //隐藏当前
					$("#show_point_"+id).show(); //显示给分
				}
			};
		
			// 分数验证
			function validatePoint(point){
				if($.trim(point).length > 0){
					 var reg = new RegExp(/^\d+$/);
					    if(!reg.test(point)){
					        alert("请输入0~100之间的正整数!");
					        return false;
					    }
					    if(0 > point || point > 100){
					    	 alert("请输入0~100之间的正整数!");
					    	 return false;
					    }
					    return true;
				}
				alert("请输入数字!");
			    return false;
			};
		</script>
	</head>
	<body>
	 <jsp:include page="../include/header.jsp"  flush="true" /> 
		<div class="body m-w980p">
			<!--start nav-->
			 <jsp:include page="../include/pageManageMenu.jsp"  flush="true" /> 
			 <aut:authorize url="/admin/suggest/suggest-searchSuggests-sub">
			 	<jsp:include page="../include/pageManageMenuSub.jsp"  flush="true" />
			 </aut:authorize>
			<!--end nav-->
			<div class="m-mt10p m-clear">
				<div class="body-nav subnav m-fl">
			        <ul>
			            <li><a id="suggestPage" <c:if test='${suggestPage.params.status == null || suggestPage.params.status == ""}'>class="current-chose"</c:if> href="${_ctxPath}/admin/suggest/suggest-searchSuggests.htm?suggestPage.params.type=0&suggestPage.params.status=">全部</a> </li>
			            <li><a id="suggestPage1" <c:if test="${suggestPage.params.status == 2 }">class="current-chose"</c:if> href="${_ctxPath}/admin/suggest/suggest-searchSuggests.htm?suggestPage.params.type=0&suggestPage.params.status=2">已读</a></li>
						<li><a id="suggestPage2" <c:if test="${suggestPage.params.status==1 }">class="current-chose"</c:if> href="${_ctxPath}/admin/suggest/suggest-searchSuggests.htm?suggestPage.params.type=0&suggestPage.params.status=1">未读</a></li>
					 	<li><a id="suggestPage3" <c:if test="${suggestPage.params.status==4 }">class="current-chose"</c:if> href="${_ctxPath}/admin/suggest/suggest-searchSuggests.htm?suggestPage.params.type=0&suggestPage.params.status=4">已回复</a></li>
					 	<li><a id="suggestPage4" <c:if test="${suggestPage.params.status==3 }">class="current-chose"</c:if> href="${_ctxPath}/admin/suggest/suggest-searchSuggests.htm?suggestPage.params.type=0&suggestPage.params.status=3">禁止</a></li>
			        </ul>
			    </div>
			    <c:if test="${not empty suggestPage.result}">
	                 <span class="m-fr curr-num">
	                     <label>当前显示： </label>
	                     <yp:commPageSize page="${suggestPage}" beanName="suggestPage"></yp:commPageSize>
	                 </span>
	             </c:if>
			</div>
			<table id="tab" width="100%">
				<thead class="tab-control">
					<tr>
						<th>用户名</th>
						<th width="28%">时间</th>
						<th width="5%">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${suggestPage.result}" var="suggest" varStatus="status">
						<tr>
							<td colspan="4" class="num-icon tucao">
								<table class="tab-control">
									<tr id="${suggest.suggestId}" class="tr-height list-tr<c:if test="${suggest.status==1}"> no-read</c:if>">
										<td class="num-icon">
											<i></i><label><a>${suggest.user.username}</a></label>
										</td>
										<td width="28%">
											<fmt:formatDate value="${suggest.createTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
										</td>
										<td width="5%">
											<c:if test='${suggest.status != 3}'>
												<a class="c-blue" href="javascript:void(0)" onclick='deleteSuggest(${suggest.suggestId},3)'>禁止 </a>
											</c:if>
										</td>
									</tr>
									<tr class="show-tr">
										<td colspan="4" class="show-tab">
											<div class="order-info">
												<table width="100%">
													<tr>
														<td width="645">内容：${escape:escapeHtml(suggest.content)}</td>
														<c:if test='${suggest.status != 3}'>
															<td>
																<div class="reply" id="reply_${suggest.suggestId}">
																	<form id="form_${suggest.suggestId}">
																		<c:if test='${suggest.revertContent!="" && suggest.revertContent != null}'>
																			<div>回复：${escape:escapeHtml(suggest.revertContent)}</div>
																		</c:if>
																		<c:if test='${suggest.revertContent=="" || suggest.revertContent == null}'>
																			<textarea name="suggest.revertContent" id="repTxtare_${suggest.suggestId}" cols="30" rows="10"></textarea><a href="javascript:;" onclick="confirmContent(${suggest.suggestId})" id="replyBtn">回复</a>
																		</c:if>
																	</form>
																</div>
																<div class="show_msg" id ="show_msg_${suggest.suggestId}"></div>
																
																<c:if test='${suggest.point!="" && suggest.point != null}'>
																	<div>给分：${suggest.point}</div>
																</c:if>
																<c:if test='${suggest.point=="" || suggest.point == null}'>
																	<div class="send_point" id ="send_point_${suggest.suggestId}">
																		<form id="pot_form_${suggest.suggestId}">
																			<input type="text" id="potTxt_${suggest.suggestId}" name="${suggest.point}"/>
																			<a href="javascript:;" id="sendPot" onclick="confirmAddPoint(${suggest.suggestId},${suggest.userId},${suggest.type})" >给分</a>
																		</form>
																	</div>
																	<div class="show_point" id="show_point_${suggest.suggestId}"></div>
																</c:if>
															</td>
														</c:if>
														<c:if test='${suggest.status == 3}'>
															<td>
																<c:if test='${suggest.revertContent!="" && suggest.revertContent != null}'>
																	<div>回复：${escape:escapeHtml(suggest.revertContent)}</div>
																</c:if>
																<c:if test='${suggest.point!="" && suggest.point != null}'>
																	<div>给分：${suggest.point}</div>
																</c:if>
															</td>
														</c:if>
													</tr>
												</table>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<input type="hidden" id="status_2" value="2">
			<!--end 站内信table-->
			<div class="pagination pagination-right">
				<c:if test="${not empty suggestPage.result}">
					<yp:commPage page="${suggestPage}" beanName="suggestPage"></yp:commPage>
				</c:if>
			</div>
		</div>
	 	<jsp:include page="/WEB-INF/pages/include/foot.jsp"></jsp:include> 
	</body>
</html>