<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="aut" uri="http://www.springsecurity.org/jsp"%>
 <div class="m-mt10p m-clear">
		<div class="body-nav subnav m-fl">
        	<ul class="m-clear">
		        <li ><a href="${_ctxPath}/admin/suggest/suggest-searchSuggests.htm?suggestPage.params.type=1&suggestPage.params.status=" id="discovery">吐槽</a> </li>
		        <li ><a href="${_ctxPath}/admin/suggest/suggest-searchSuggests.htm?suggestPage.params.type=0&suggestPage.params.status=" id="spitslot">发现</a> </li>
        	</ul>
  	 	</div>
   </div>
 <script type="text/javascript">
 $(function(){
 	var url = location.href;
 	if(url.indexOf("suggestPage.params.type=1") > 0 || url.indexOf("suggest-searchSuggests.htm?menuFlag") > 0){
 		$("#discovery").addClass("current-chose").siblings().removeClass("current-chose");
 	}else if(url.indexOf("suggestPage.params.type=0") > 0){
 		$("#spitslot").addClass("current-chose").siblings().removeClass("current-chose");
 	}
 });
</script>
