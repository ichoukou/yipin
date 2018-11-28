<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>买家管理</title>
 	<link href="${ _cssPath}/common.css" rel="stylesheet" />
    <link href="${ _cssPath}/pages/buyerManagement.css" rel="stylesheet" />
    <link href="${_jsPath }/plugin/My97DatePicker/skin/WdatePicker.css" rel="stylesheet" />
    <script src="${_jsPath }/jquery/jquery.1.8.1.js"  language="javascript"></script>
    <script src="${_jsPath }/plugin/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<jsp:include page="../include/header.jsp"  flush="true" />
<div class="body m-w980p">
<aut:authorize url="/admin/user/buyer-search">
	<form class="m-mt10p m-clear" action="${_ctxPath}/admin/user/user-searchBuyers.htm" id="search-form" method="post">
	    <div class="m-fl" >
          <label>注册时间：</label>
          <input type="text" name="buyerPage.params.startTime" value="${buyerPage.params.startTime}" class="Wdate" 
          id="startTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endTime\')}',readOnly:true})" /> 
          <label>至 </label>
           <input type="text" name="buyerPage.params.endTime" value="${buyerPage.params.endTime}" class="Wdate" 
          id="endTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')||\'%y-%M-%d\'}',readOnly:true})" />
          <input type="submit" class="m-btn" value="查 询"/>
	    </div>
	   	<c:if test="${not empty buyerPage.result}">
		    <div class="m-fr curr-num">
					<label>当前显示： </label> 
	        <yp:commPageSize page="${buyerPage}" beanName="buyerPage"></yp:commPageSize>
				</div>
      </c:if>
	</form>
</aut:authorize> 
	    <div class="buyer-form-fl m-mt10p">
	        <ul class="m-clear">
	        		<li>消费总额
	        			<a href="${_ctxPath}/admin/user/user-searchBuyers.htm?buyerPage.params.startTime=${buyerPage.params.startTime}&buyerPage.params.endTime=${buyerPage.params.endTime}&buyerPage.total=${buyerPage.total}&buyerPage.limit=${buyerPage.limit}&buyerPage.sort=totalPrice&buyerPage.dir=asc&buyerPage.currentPage=${buyerPage.currentPage}"><span><i></i></span></a>
	        			<a href="${_ctxPath}/admin/user/user-searchBuyers.htm?buyerPage.params.startTime=${buyerPage.params.startTime}&buyerPage.params.endTime=${buyerPage.params.endTime}&buyerPage.total=${buyerPage.total}&buyerPage.limit=${buyerPage.limit}&buyerPage.sort=totalPrice&buyerPage.dir=desc&buyerPage.currentPage=${buyerPage.currentPage}"><span><i class="down"></i></span></a>
	        		</li>
	        	
	        		<li>消费次数
	        			<a href="${_ctxPath}/admin/user/user-searchBuyers.htm?buyerPage.params.startTime=${buyerPage.params.startTime}&buyerPage.params.endTime=${buyerPage.params.endTime}&buyerPage.total=${buyerPage.total}&buyerPage.limit=${buyerPage.limit}&buyerPage.sort=orderCount&buyerPage.dir=asc&buyerPage.currentPage=${buyerPage.currentPage}"><span><i></i></span></a>
	        			<a href="${_ctxPath}/admin/user/user-searchBuyers.htm?buyerPage.params.startTime=${buyerPage.params.startTime}&buyerPage.params.endTime=${buyerPage.params.endTime}&buyerPage.total=${buyerPage.total}&buyerPage.limit=${buyerPage.limit}&buyerPage.sort=orderCount&buyerPage.dir=desc&buyerPage.currentPage=${buyerPage.currentPage}"><span><i class="down"></i></span></a>
	        		</li>
	            
	             	<li>注册时间
	             		<a href="${_ctxPath}/admin/user/user-searchBuyers.htm?buyerPage.params.startTime=${buyerPage.params.startTime}&buyerPage.params.endTime=${buyerPage.params.endTime}&buyerPage.total=${buyerPage.total}&buyerPage.limit=${buyerPage.limit}&buyerPage.sort=u.createTime&buyerPage.dir=asc&buyerPage.currentPage=${buyerPage.currentPage}"><span><i></i></span></a>
	        			<a href="${_ctxPath}/admin/user/user-searchBuyers.htm?buyerPage.params.startTime=${buyerPage.params.startTime}&buyerPage.params.endTime=${buyerPage.params.endTime}&buyerPage.total=${buyerPage.total}&buyerPage.limit=${buyerPage.limit}&buyerPage.sort=u.createTime&buyerPage.dir=desc&buyerPage.currentPage=${buyerPage.currentPage}"><span><i class="down"></i></span></a>
	             	</li>
	            
	            	<li>最后登录时间
	            		<a href="${_ctxPath}/admin/user/user-searchBuyers.htm?buyerPage.params.startTime=${buyerPage.params.startTime}&buyerPage.params.endTime=${buyerPage.params.endTime}&buyerPage.total=${buyerPage.total}&buyerPage.limit=${buyerPage.limit}&buyerPage.sort=lastLoginTime&buyerPage.dir=asc&buyerPage.currentPage=${buyerPage.currentPage}"><span><i></i></span></a>
	        			<a href="${_ctxPath}/admin/user/user-searchBuyers.htm?buyerPage.params.startTime=${buyerPage.params.startTime}&buyerPage.params.endTime=${buyerPage.params.endTime}&buyerPage.total=${buyerPage.total}&buyerPage.limit=${buyerPage.limit}&buyerPage.sort=lastLoginTime&buyerPage.dir=desc&buyerPage.currentPage=${buyerPage.currentPage}"><span><i class="down"></i></span></a>
	            	</li>
	           
	          	 <li>年龄
	          	 	<a href="${_ctxPath}/admin/user/user-searchBuyers.htm?buyerPage.params.startTime=${buyerPage.params.startTime}&buyerPage.params.endTime=${buyerPage.params.endTime}&buyerPage.total=${buyerPage.total}&buyerPage.limit=${buyerPage.limit}&buyerPage.sort=birthday&buyerPage.dir=asc&buyerPage.currentPage=${buyerPage.currentPage}"><span><i></i></span></a>
	        			<a href="${_ctxPath}/admin/user/user-searchBuyers.htm?buyerPage.params.startTime=${buyerPage.params.startTime}&buyerPage.params.endTime=${buyerPage.params.endTime}&buyerPage.total=${buyerPage.total}&buyerPage.limit=${buyerPage.limit}&buyerPage.sort=birthday&buyerPage.dir=desc&buyerPage.currentPage=${buyerPage.currentPage}"><span><i class="down"></i></span></a>
	          	 </li>
	        </ul>
	    </div>
<!-- 	    <div class="pagination pagination-right"> -->
<%-- 				<c:if test="${not empty buyerPage.result}"> --%>
<%-- 					<yp:commPage page="${buyerPage}" beanName="buyerPage"></yp:commPage> --%>
<%-- 				</c:if> --%>
<!-- 			</div> -->
	<!--start 买家管理table-->
<div class="m-mt10p">
<table width="100%">
	<thead class="tab-control">
	<tr>
	    <th width="22%">登录名</th>
	    <th width="6%">姓名</th>
	    <th width="5%">性别</th>
	    <th width="8%">生日</th>
	    <th width="8%">电话</th>
	    <th width="10%">所在地</th>
	    <th width="8%">注册日期</th>
	    <th width="11%">消费记录/总额</th>
	    <th width="11%">最后登录时间</th>
	    <th width="11%">最后消费时间</th>
	</tr>
	</thead>
<tbody>
<c:forEach items="${buyerPage.result}" var="buyer"
					varStatus="status">
					<tr>
    <td colspan="10" class="td-nobor">
        <table class="tab-control " >
            <tr class="tr-height list-tr">
                <td class="num-icon" width="22%">
                    <i class="change"></i>
                    <label><a>${buyer.user.username}</a></label>
                </td>
                <td width="6%">
                    ${escape:escapeHtml(buyer.contactName)}
                </td>
                <td width="5%">
                 <c:if test="${buyer.gender==0 }">女</c:if> <c:if
								test="${buyer.gender==1 }">男</c:if>
                </td>
                <td width="8%">
                   <fmt:formatDate value='${buyer.birthday}'
								pattern='yyyy-MM-dd' />
                </td>
                <td width="8%">
                    ${buyer.mobile}
                </td>
                <td  width="10%">${buyer.province }</td>
                <td width="8%"><fmt:formatDate value='${buyer.user.createTime}'
								pattern='yyyy-MM-dd' /></td>
                <td width="11%">
                    ${buyer.orderCount}/${buyer.totalPrice}
                </td>
                <td width="11%">
                    <fmt:formatDate value='${buyer.user.lastLoginTime}'
								pattern='yyyy-MM-dd' />
                </td>
                <td width="11%">
                    <fmt:formatDate value='${buyer.payTime}'
								pattern='yyyy-MM-dd' />
                </td>
            </tr>
            <tr class="show-tr">
                <td colspan="10" class="show-tab">
                    <p class="order-info">当前积分：${buyer.point.total}<br/>
                      	  地址：${escape:escapeHtml(buyer.companyAddress)}<br/>
	                                                                      默认收货地址：${buyer.userAddress.region.province} ${buyer.userAddress.region.city} ${buyer.userAddress.region.county} ${escape:escapeHtml(buyer.userAddress.detailAddress)}<br/>
	                        <%-- <c:if test="${address.isDefault==0 }">
	                        	收货地址<c:if test="${!isDefault}">${status.index+1}</c:if><c:if test="${isDefault}">${status.index}</c:if>：${address.region.province} ${address.region.city} ${address.region.county} ${address.detailAddress}<br/>
	                        </c:if> --%>
                                                                      <%-- 订阅的品牌：${buyer.brandNames }<br/> --%>
                    </p>
                </td>
            </tr>
        </table>
    </td>
</tr>
					
				</c:forEach>
</tbody>
</table>
</div>
<!--end 买家管理table-->
	<div class="pagination pagination-right">
		<c:if test="${not empty buyerPage.result}">
			<yp:commPage page="${buyerPage}" beanName="buyerPage"></yp:commPage>
		</c:if>
	</div>
</div>
<jsp:include page="/WEB-INF/pages/include/foot.jsp"></jsp:include>
</body>
</html>