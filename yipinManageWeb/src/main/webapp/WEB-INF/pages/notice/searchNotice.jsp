<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.ytoxl.module.yipin.base.dataobject.Notice"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%-- <%@page isELlgnored="false"%> --%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>公告栏</title>
    <link href="${_cssPath}/common.css" rel="stylesheet" />
    <script src="${_jsPath }/jquery/jquery.1.8.1.js"  language="javascript"></script>
    <c:set var="TYPE_REBATE" value="<%=Notice.TYPE_REBATE %>" ></c:set>
    <c:set var="TYPE_NETWORK" value="<%=Notice.TYPE_NETWORK %>"></c:set>
    <c:set var="CHECKED_NO" value="<%=Notice.CHECKED_NO %>"></c:set>
    <c:set var="CHECKED_YES" value="<%=Notice.CHECKED_YES %>"></c:set>
</head>
<body>
<!--start header-->
<jsp:include page="../include/header.jsp" flush="true" />
<!--end header-->
<!--start body-->
<div class="body m-w980p">
	<jsp:include page="../include/pageManageMenu.jsp"  flush="true" />
  	<form class="m-clear m-mt10p" id="searchForm"  method="post">
	    <div class="m-fl">
	    	<span>标题：</span>
	    	<input type="text" class="txt-input" name="noticePage.params.title" value="${noticePage.params.title}"/>
<!-- 	    	<span>类型：</span> -->
<!-- 	    	<select class="m-sel" name="noticePage.params.type"> -->
<!-- 	    		<option value="">请选择</option> -->
<%-- 	    		<option value="${TYPE_REBATE }" <c:if test="${noticePage.params.type eq TYPE_REBATE }">selected = "selected"</c:if>><spring:message code="notice.type.${TYPE_REBATE }"/></option> --%>
<%-- 	    		<option value="${TYPE_NETWORK }" <c:if test="${noticePage.params.type eq TYPE_NETWORK }">selected = "selected"</c:if>><spring:message code="notice.type.${TYPE_NETWORK }"/></option> --%>
<!-- 	    	</select> -->
	    	<span>状态：</span>
	    	<select class="m-sel" name="noticePage.params.status">
	    		<option value="">请选择</option>
	    		<option value="0" <c:if test="${not empty noticePage.params.status &&(noticePage.params.status eq 0 )}">selected = "selected"</c:if>>未审核</option>
	    		<option value="1" <c:if test="${noticePage.params.status eq 1 }">selected = "selected"</c:if>>已审核</option>
	    	</select>
	    	<input type="button" class="m-btn" value="查询" id="search"/>
	    	<aut:authorize url="/admin/notice/notice-addNotice">
	    		<input type="button" class="m-btn J-editAd" value="新增" />
	    	</aut:authorize>
	    </div>
	    <!--右侧显示分页控件 start-->
	    <c:if test="${not empty noticePage.result}">
			<div class="m-fr curr-num">
				<label>当前显示： </label>
				<yp:commPageSize page="${noticePage}" beanName="noticePage"></yp:commPageSize>
			</div>
		</c:if>
    	<!--右侧显示分页控件 end-->
	</form>
	<!--100%表格样式-->
	<c:if test="${not empty noticePage}">
		<div class="m-mt10p">
			<table id="tab" class="tab-control">
		      <thead>
		        <tr>
		          <th width="7%" style="text-align:center"><input type="checkbox" name="checkAll" /> ID</th>
		          <th width="28%" style="text-align:center">标题</th>
		          <th width="18%" style="text-align:center">发布时间</th>
		          <th width="10%" style="text-align:center">状态</th>
		          <th width="17%" style="text-align:center">操作</th>
		        </tr>
		      </thead>
		      <tbody>
		      	<c:forEach items="${noticePage.result }" var="notice" varStatus="status">
					<tr class="list-tr">
						<td style="text-align:center"><input type="checkbox" name="checkSub" value="${notice.noticeId }"/> 
						<c:choose>
							<c:when test="${noticePage.currentPage==0}">${(status.index)+1}</c:when>
							<c:otherwise>
							    	${(noticePage.currentPage)*(noticePage.limit)+(status.index+1)}
							</c:otherwise>
						</c:choose>
						</td>
						<td style="text-align:center"><a href="${_ctxPath}/admin/notice/notice-viewNotice.htm?notice.noticeId=${notice.noticeId}" class="c-blue" title="${fn:escapeXml(notice.title) }" >${notice.title }</a></td>
						<td style="text-align:center"><fmt:formatDate value='${notice.publishTime}' pattern='yyyy-MM-dd HH:mm:ss'/></td>
						<td style="text-align:center"><span class="c-green"><c:if test="${notice.status eq 1}">已审核</c:if><c:if test="${notice.status eq 0}">未审核</c:if></span></td>
						<td style="text-align:center">
		      			<a class="c-blue"  href="${_ctxPath}/admin/notice/notice-viewNotice.htm?notice.noticeId=${notice.noticeId}" >查询</a>
		      			<aut:authorize url="/admin/notice/notice-updateNotice">
		      				<a class="c-blue" href="${_ctxPath}/admin/notice/notice-updateNotice.htm?notice.noticeId=${notice.noticeId}">修改</a>
		      			</aut:authorize>
		      			<aut:authorize url="/admin/notice/notice-deleteNotice">
		      				<a class="c-blue" href="javascript:;" onclick="deleteNotice('${notice.noticeId}')">删除</a>
		      			</aut:authorize>
		      		</td>
					</tr>
				</c:forEach>
		      </tbody>
    		</table>
	    	<div class="m-mt10p">
	    		<aut:authorize url="/admin/notice/notice-checkNotice">
		    		<input type="button" class="m-btn" value="审核" id="checkNotice"/>
		    	</aut:authorize>
		    	<aut:authorize url="/admin/notice/notice-deleteNotice">
		    		<input type="button" class="m-btn" value="删除" id="batchDel"/>
		    	</aut:authorize>
		    </div>
		</div>
	</c:if>
	<!--分页插件 start -->
	<div class="pagination pagination-right">
			<c:if test="${not empty noticePage.result}">
				<yp:commPage page="${noticePage}" beanName="noticePage"></yp:commPage>
			</c:if>
	</div>
	<!--分页插件 end -->
</div>
<!--start footer-->
<jsp:include page="/WEB-INF/pages/include/foot.jsp"></jsp:include>
<!--end footer-->
</body>
<!--end body-->
<script type="text/javascript">
$(function(){
	//查询按钮绑定事件
	$('#search').bind('click', function() {
		$("#searchForm").attr("action","${_ctxPath}/admin/notice/notice-searchNotice.htm");
		$("#searchForm").submit();  
	});
	//复选框事件
	$('.tab-control').Merlin({
			// 全选
	    "checkAll": {
	      "eType":'on', // 事件类型
        "checkAll":'input[name="checkAll"]', // 全选按钮
        "checkSub":'input[name="checkSub"]' // 子选择按钮
	    }
    });
	//“新增公告”按钮绑定事件
	$(".J-editAd").bind('click', function() {
		window.location.href="${_ctxPath}/admin/notice/notice-addNotice.htm";
	});
	//“审核通过”按钮绑定事件
	$('#checkNotice').bind('click', function() {
		var noticeIds = allCheckedVal();
		if(!noticeIds){
			alert("请选中要“审核”的记录！");
			return false;
		}
		if(confirm("确认“审核通过”选中的记录？")){
			$.ajax({
				type : 'POST',
				url : '${_ctxPath}/admin/notice/notice-checkNotice.htm',
				data : {"noticeIds" : noticeIds},
				success : function(data) {
					if(data){
						alert(data.info);
					}
// 					window.location.reload();
					window.location.href=window.location.href;
					}
			});
		}
	});
	//“批量删除”按钮绑定事件
	$('#batchDel').bind('click', function() {
		var noticeIds = allCheckedVal();
		if(!noticeIds){
			alert("请选中要“删除”的记录！");
			return false;
		}
		if(confirm("确认删除选中的记录？")){
			$.ajax({
				type : 'POST',
				url : '${_ctxPath}/admin/notice/notice-deleteNotice.htm',
				data : {"noticeIds" : noticeIds},
				success : function(data) {
					if(data){
						alert(data.info);
					}
// 					window.location.reload();
					window.location.href=window.location.href;
					}
			});
		}
	});
});
//获取所有选中复选框的值
function allCheckedVal(){
	var str="";
	$("input:checkbox[name='checkSub']:checked").each(function(){
		str += $(this).val()+",";
	});
	return str.substring(0,str.length-1);
};
//删除单条公告
function deleteNotice(noticeId){
	if(confirm("确认删除该条记录？")){
		$.ajax({
			type : 'POST',
			url : '${_ctxPath}/admin/notice/notice-deleteNotice.htm',
			data : {"noticeIds" : noticeId},
			success : function(data) {
				if(data){
					alert(data.info);
				}
// 				window.location.reload();
				window.location.href=window.location.href;
				}
		});
	}
};
</script>
</html>