<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="aut" uri="http://www.springsecurity.org/jsp"%>
 <div class="body-nav m-mt10p">
        <ul class="m-clear">
			<aut:authorize url="/admin/suggest/suggest-searchSuggests-sub">
				<input type="hidden" id="mark" value="0">
				<li><a href="${_ctxPath}/admin/suggest/suggest-searchSuggests-sub.htm?suggestPage.params.type=1"  id="suggest">吐槽/发现</a></li>
			</aut:authorize>
			<aut:authorize url="/admin/help/help-listHelps">
            	<input type="hidden" id="mark" value="${_ctxPath}/admin/help/help-listHelps.htm">
            	<li><a href="${_ctxPath}/admin/help/help-listHelps.htm"  id="help">底部信息</a></li>
           	</aut:authorize>
            <aut:authorize url="/admin/seo/seo-listSeoConfigs">
            	<input type="hidden" id="mark" value="${_ctxPath}/admin/seo/seo-listSeoConfigs.htm">
				<li><a href="${_ctxPath}/admin/seo/seo-listSeoConfigs.htm" id="seo">SEO信息</a></li>
			</aut:authorize>
			<aut:authorize url="/admin/link/link-searchLinks">
				<input type="hidden" id="mark" value="${_ctxPath}/admin/link/link-searchLinks.htm">
				<li><a href="${_ctxPath}/admin/link/link-searchLinks.htm" id="link">友情链接</a></li>
			</aut:authorize>
        </ul>
   </div>
 <script type="text/javascript">
 $(function(){
 	var url = location.href;
 	if(url.indexOf("suggest-searchSuggests") > 0){
 		$("#suggest").addClass("current-chose").siblings().removeClass("current-chose");
 	}else if(url.indexOf("help-listHelps") > 0){
 		$("#help").addClass("current-chose").siblings().removeClass("current-chose");
 		return false;
 	}else if(url.indexOf('seo-listSeoConfigs')>0){
 		$("#seo").addClass("current-chose").siblings().removeClass("current-chose");
 		return false;
 	}else if(url.indexOf('link-searchLinks')>0){
 		$("#link").addClass("current-chose").siblings().removeClass("current-chose");
 		return false;
 	}
 	var mark = $('#mark').val();
 	if( mark == 0) return false;
 	window.location.href = mark;
 });
</script>
