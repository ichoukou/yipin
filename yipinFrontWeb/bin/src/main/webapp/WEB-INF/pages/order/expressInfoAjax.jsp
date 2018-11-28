<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.ytoxl.module.yipin.base.dataobject.resultmap.ExpressMessage" %>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>  
 	<c:if test="${not empty expressMessage.data || not empty expressMessage.orderMsg}">
     <table>
         <thead>
         <tr>
             <th width="33.3%">处理时间</th>
             <th width="50%">处理结果 </th>
             <th><c:if test="${expressMessage.YTO}">操作人</c:if></th>
         </tr>
         </thead>
         <tbody>
         <c:forEach items="${expressMessage.orderMsg}" var="orderMsg">
	         <c:if test="${not empty orderMsg.key}">
	         <tr>
				<td>${orderMsg.key}</td>
				<td>${orderMsg.value}</td>
				<td></td>
			 </tr>
			 </c:if>
		 </c:forEach>
         <c:set var="status_success" value="<%=ExpressMessage.STATUS_SUCCESS%>"/>
         <c:choose>
			<c:when test="${expressMessage.status eq status_success}">
				<c:forEach items="${expressMessage.data}" var="dataItem">
				<tr>
                   <td>${dataItem.time}</td>
                   <td>${dataItem.context}</td>
                   <c:if test="${expressMessage.YTO}">
                   <td>${dataItem.operator}</td>
                   </c:if>
                </tr>
				</c:forEach>
			</c:when>
			<%-- <c:otherwise><p class="loading">没有查询到相应的物流信息，给您带来不便请见谅，请稍后再试！</p></c:otherwise> --%>
			</c:choose>
         </tbody>
     </table>
     </c:if>
