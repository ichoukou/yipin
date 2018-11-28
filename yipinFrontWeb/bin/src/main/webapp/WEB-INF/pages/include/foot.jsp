<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<%@page import="com.ytoxl.module.core.common.utils.SpringContextUtils"%>
<%@page import="com.ytoxl.module.yipin.content.service.HelpService"%>
<%
	HelpService helpService=(HelpService)(SpringContextUtils.getContext().getBean("helpServiceImpl"));
	pageContext.setAttribute("helpCategorys",helpService.listHelpCategorys());
%>
<div class="foot">
	<!--底部服务保障 start-->
	<div class="foot_top cf">
		<ul>
			<li>
				<h4 class="ensure1">100%原产地发货</h4>
			</li>
			<li>
				<h4 class="ensure2">产地直供 品质保证</h4>
			</li>
			<li class="none">
				<h4 class="ensure3">专业物流 安全速达</h4>
			</li>
		</ul>
	</div>
	<!--底部服务保障 end-->
	<div class="foot_mid_wp">
		<!--底部导航 start-->
		<div class="foot_mid cf">
			<!--帮助信息 start-->
			<div class="help">
                 <c:forEach items="${helpCategorys}" var="helpCategory" varStatus="status">
				<dl>
					<dt>${helpCategory.name}</dt>
					<c:forEach items="${helpCategory.helpCategorys}" var="help" varStatus="index">
                           <dd><a target="_blank" href="${_ctxPath}/help/help-${help.help.helpId}.htm" target="_blank" rel="nofollow">${help.name}</a></dd>
                       </c:forEach>
                       <c:if test="${status.last}">
                       	 <dd><a href="${_ctxPath }/links.htm" target="_blank">友情链接</a></dd>
                       </c:if> 
				</dl>
			</c:forEach>
			</div>
			<!--帮助信息 end-->
			<!--关注信息 start-->
			<div class="fol_c1">
				<p class="hotline">${_serviceTel}</p>
				<p class="yy_time">客服热线：${_serviceTime}(工作日)</p>
				<p><a href="http://weibo.com/yichengpin" target="_blank"><img src="${_imagesPath}/foot/weibo.png" alt="" /></a></p>
			</div>
			<div class="fol_c2">
				<p class="wx_tit">一城一品官方微信</p>
				<p class="img">
					<img src="${_imagesPath}/foot/weixin.jpg" alt="" />
				</p>
				<p>扫一扫，拿优惠</p>
			</div>
			<!--关注信息 end-->
		</div>
		<!--底部导航 end-->
	</div>
    <div class="foot_bot_wp">
        <div class="foot_bot">
           <p><span>${_footCopyright}</span>${_footAddress}</p>
        </div>
    </div>
</div>
<!--js-->
<script type="text/javascript" src="${_jsPath }/jquery/jquery-1.8.1.js"></script>
<script type="text/javascript" src="${_jsPath }/plugin/json2.js"></script>
<script type="text/javascript" src="${_jsPath }/plugin/plugin.js"></script>
<script type="text/javascript" src="${_jsPath }/lib/global.js"></script>
<script type="text/javascript" src="${_jsPath }/plugin/artdialog/jquery.artDialog.min.js"></script>
<script type="text/javascript" src="${_jsPath }/plugin/formvalidator/formValidator-4.1.3.js"></script>
<script type="text/javascript" src="${_jsPath }/plugin/formvalidator/formValidatorRegex.js"></script>
<script type="text/javascript" src="${_jsPath }/plugin/calendar/WdatePicker.js"></script>
<%-- <script type="text/javascript" src="${_jsPath }/plugin/mailAutoComplete/jquery.mailAutoComplete.js"></script> --%>
<script type="text/javascript">
	var _ctxPath = '${_ctxPath}';
	//分页数量只可以输入数字
	$("#pageIndex").keyup(function(event) {
	    var $this = $(this);
	    olnyNumber($this);
	});
	function olnyNumber($this){
		var value = $this.val();
	    if(value!=""){
			var reg = /[^0-9]/g;
			value = value.replace(reg,"")
			if(parseInt(value)==0){
				value = "";
			}
			if(value.length>9){
				value = value.substring(0,100);
			}
			$this.val(value);
		}
	}
</script>
<tongji:baidu showStatistics="${showStatistic}" ></tongji:baidu>