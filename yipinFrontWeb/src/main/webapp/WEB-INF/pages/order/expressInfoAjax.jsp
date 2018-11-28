<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.ytoxl.module.yipin.base.dataobject.resultmap.ExpressMessage" %>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>  
 	<%-- <c:if test="${not empty expressMessage.data || not empty expressMessage.orderMsg}"> --%>
     <table>
         <thead>
         <tr>
             <th width="33.3%">处理时间</th>
             <th width="50%">处理结果</th>
             <c:if test="${expressMessage.YTO}">
             	<th>操作人</th>
             </c:if>
         </tr>
         </thead>
         <tbody>
         <%-- 
         <c:forEach items="${expressMessage.orderMsg}" var="orderMsg">
	         <c:if test="${not empty orderMsg.key}">
	         <tr>
				<td>${orderMsg.key}</td>
				<td>${orderMsg.value}</td>
				<td></td>
			 </tr>
			 </c:if>
		 </c:forEach>
		  --%>
         <c:set var="status_success" value="<%=ExpressMessage.STATUS_SUCCESS%>"/>
         <c:choose>
			<c:when test="${not empty expressMessage.data and expressMessage.status eq status_success}">
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
			<c:otherwise>
				<tr>
					<td colspan="${expressMessage.YTO ? 3 : 2 } ">
						没有查询到相应的物流信息，给您带来不便请见谅，请稍后再试!
					</td>
				</tr>
			</c:otherwise>
		</c:choose>
         </tbody>
     </table>
     <%-- </c:if> --%>