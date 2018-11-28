<!DOCTYPE html>
<%@page import="java.text.SimpleDateFormat"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.ytoxl.module.yipin.base.dataobject.Sale"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<html>

<head>
<meta charset="utf-8" />
<title>预售管理</title>
	<link href="${_cssPath }/common.css" rel="stylesheet" />
	<link href="${_cssPath }/pages/arrangement.css" rel="stylesheet" />
	<script type="text/javascript" src="${_jsPath }/jquery/jquery.1.8.1.js"></script>
	<script type="text/javascript"	src="${_jsPath }/plugin/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript"	src="${_jsPath }/plugin/formvalidator/formValidatorRegex.js"></script>
	<script type="text/javascript"	src="${_jsPath }/plugin/formvalidator/formValidator-4.1.3.js"></script>
<body>
	<!--start header-->
	<jsp:include page="../include/header.jsp"></jsp:include>
	<!--end header-->
       <div class="body m-w980p">
        <div  class="m-tp10">
            <form id="searchSaleForm" method="POST">
                    <div>
                        <select class="m-sel" name="salePage.params.brandId" id="brand">
                            <option value="">全部品牌</option>
                            <c:forEach items="${brands}" var="brand">
                                <option value="${brand.brandId}"
                                	<c:if test="${sale.brandId==brand.brandId}">
									selected = "selected"
								</c:if>
                                >${brand.name}</option>
                            </c:forEach>
                        </select>
                         <input type="text"
                                            class="Wdate"
                                            onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
                                            value="${salePage.params.startTime}"
                                            name="salePage.params.startTime" id="startTime" />
                        <input type="text"
                                            class="Wdate"
                                            onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')||\'%y-%M-%d\'}',readOnly:true})"
                                            value="${salePage.params.endTime}"
                                            name="salePage.params.endTime" id="endTime" />
                            <input type="button" class="m-btn" id="searchAAA" value="查询" onclick="btnQuery();"/>
                        <input type="hidden" id="statusBtn" name="sale.status"/>
                    </div>
                    <div class="m-tp10">
                    	<aut:authorize url="/admin/sale/sale-load">
                        	<input type="button" class="m-btn" value="新增预售" onclick="btnAddSale();"/>
                        </aut:authorize>
                    	<aut:authorize url="/admin/sale/sale-publishSale">
                        	<input type="button" class="m-btn" value="一键发布" onclick="btnAllPublish();"/>
                        </aut:authorize>
                    </div>
                </form>
        </div>
		<!--start 商品管理table-->
		<div class="m-mt10p goods-management">
			<table width="100%" >
				<thead class="tab-control">
                    <tr>
                        <th width="25%">品牌</th>
                        <th width="15%">售卖方式</th>
                        <th width="25%">预发售日期</th>
                        <th width="15%">状态</th>
                        <th width="20%">操作</th>
                    </tr>
                </thead>
                <tbody  id="saleTbody">
					<c:forEach items="${salePage.result}"  var="sale" varStatus="status">
						<tr class="list-tr" >
							<td colspan="7" class="td-nobor">
								<table class="tab-control">
									<tr class="tr-height"">
										<td  width="25%">
											<label>${sale.brandName}</label>
											<input type="hidden" id="saleId${status.index}" value="${sale.saleId}"/>
											<input type="hidden" id="totalCountSale" value="${fn:length(salePage.result)}"/>
										</td>
										<td  width="15%">
											<spring:message code="sale.sellType.${sale.sellType}"/>
										</td>
										<td  width="25%">
											<fmt:formatDate value='${sale.preSelltime}' pattern='yyyy-MM-dd'/>
										</td>
										<td  width="15%">
											<input type="hidden" id="saleStatus${status.index}" 
													index="${status.index}" value="${sale.status}"/>
												<spring:message code="sale.status.${sale.status}"/>
										</td>
										<td width="20%">
											
											<c:if test="${sale.status==1}">
												<a class="c-blue view" href="javascript:showSaleProduct(${status.index});">查看</a>
												&nbsp;
												<aut:authorize url="/admin/sale/sale-publishSale">
												<a class="c-blue view" href="javascript:btnPublish(${status.index});">发布</a>
												</aut:authorize>
												&nbsp;
												<aut:authorize url="/admin/sale/sale-load">
												<a class="c-blue view" href="javascript:btnEditSale(${sale.saleId});">编辑</a>
												</aut:authorize>
											</c:if>
											<c:if test="${sale.status==2 || sale.status==3}">
												<a class="c-blue view" href="javascript:showSaleProduct(${status.index});">查看</a>
												<aut:authorize url="/admin/sale/sale-offShelf">
												<a class="c-blue view" href="javascript:offShelf(${status.index});">下架</a>
												</aut:authorize>
											</c:if>
											<c:if test="${sale.status==4}">
												<a class="c-blue view" href="javascript:showSaleProduct(${status.index});">查看</a>
											</c:if>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<div class="m-hide" id="PopupBox${status.index}">
						<b> 商品权重：(1-10) </b>
						<div class="look-sale-product m-clear ">
							<c:forEach items="${sale.saleProductList}" var="saleProduct" varStatus="status">
								<ul><li>
									<img src="<yp:thumbImage originalPath='${saleProduct.product.imageUrls}' imageType='t84'/>"/>
									<input type="text" class="product-seqencing-num" value="${saleProduct.rank }" readOnly="true"/>
									<div class="sale-product-info left-icon m-hide">
				    					${saleProduct.product.name }
								    </div>
								</li>
								</ul>
							</c:forEach>
						</div>
						</div>
					</c:forEach>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
				</tbody>
      </table>
      </div>
	       <div class="pagination pagination-right">
	            <c:if test="${not empty salePage.result}">
	            <yp:commPage page="${salePage}" beanName="salePage"></yp:commPage>
	            </c:if>
	        </div>
       </div>
	<!--start footer-->
	<jsp:include page="/WEB-INF/pages/include/foot.jsp"></jsp:include>
	<!--end footer-->
		<script type="text/javascript">
		// 查看功能
		function showSaleProduct(index){
			$(".d-state-highlight").show();
			$.dialog({
				title: false,
				content: $('#PopupBox'+index).get(0),
				lock: true,
				ok: true,
				okValue: '确定'
			});
			$('.d-close').hide();
		}
		function btnStatus(status){
			$("#statusBtn").val(status);
			if($("#startTime").val() > $("#endTime").val()  ){
				alert("起始时间不能大于结束时间！");
			}else{
				$("#searchSaleForm").attr("action", _ctxPath + "/admin/sale/sale-searchSales.htm");
				$("#searchSaleForm").submit();
			}
		};
		// 编辑
		function btnEditSale(saleId){
			window.location.href=_ctxPath + "/admin/sale/sale-load.htm?sale.saleId="+saleId;
		};
		// 查询
		function btnQuery(){
			if($("#startTime").val() > $("#endTime").val()  ){
				alert("起始时间不能大于结束时间！");	
			}else{
				$("#statusBtn").val(null);
				$("#searchSaleForm").attr("action", _ctxPath + "/admin/sale/sale-searchSales.htm?isPageSearch=true");
				$("#searchSaleForm").submit();
			}
		};
		// 下架
		function offShelf(index){
			var indexId = index+"";
			var dataTemp ={ "saleIds[0]" : $("#saleId"+index).val()};
			$.dialog({
	            title: false,
	            lock: true,
	            content: "请确认是否下架？",
	            okValue: "确认",
	            ok: function(){ 
					$.ajax({
					    type : 'POST',
					    url : _ctxPath  + '/admin/sale/sale-offShelf.htm',
					    data : dataTemp,
					    success : function(data) {
					    	btnQuery();
					    }, 
					    error: function(msg){
					     	popupDialog("系统未知异常！" + msg.info);
					    }
		   			});
	            },
				cancelValue: "取消",
	            cancel: true
		    });
		}
		// 一键发布
		function btnAllPublish(){
			var recodeCount = $("#totalCountSale").val();
			var dataTemp = "";
			var i = 0 ;
			for(i=0;i<recodeCount;i++){
				if($("#saleStatus"+i).val() == "1"){
					dataTemp = dataTemp + "saleIds["+i+"]="+$("#saleId"+i).val()+"&";
				}
			};
			if(dataTemp.length > 1){
				dataTemp =	dataTemp.substring(0,dataTemp.length-1);
			};
			$.dialog({
	            title: false,
	            lock: true,
	            content: "请确认是否全部发布？",
	            okValue: "确认",
	            ok: function(){ 
					$.ajax({
					    type : 'POST',
					    url : _ctxPath  + '/admin/sale/sale-publishSale.htm',
					    data : dataTemp,
					    success : function(data) {
					    	btnQuery();
					    }, 
					    error: function(msg){
					     	popupDialog("系统未知异常！" + msg.info);
					    }
				   });
				},
				cancelValue: "取消",
	            cancel: true
		    });
		};
		// 发布
		function btnPublish(index){
			var indexId = index+"";
			var dataTemp ={ "saleIds[0]" : $("#saleId"+index).val()};
			$.dialog({
	            title: false,
	            lock: true,
	            content: "请确认是否发布？",
	            okValue: "确认",
	            ok: function(){ 
					$.ajax({
					    type : 'POST',
					    url : _ctxPath  + '/admin/sale/sale-publishSale.htm',
					    data : dataTemp,
					    success : function(data) {
					    	btnQuery();
					    }, 
					    error: function(msg){
					     	popupDialog("系统未知异常！" + msg.info);
					    }
				   });
	            },
				cancelValue: "取消",
	            cancel: true
		    });
		};
		// 新增预售
		function btnAddSale(){
			 window.location.href=_ctxPath + "/admin/sale/sale-load.htm";
		};
		$(".look-sale-product img").live("mouseenter",function(){
			var $ui = $(this).parent();
			var this_left =$(this).position().left + 65;
			if( this_left > 820){
				$(this).find(".sale-product-info").removeClass("left-icon").addClass("right-icon");
			};
			$ui.find(".sale-product-info").show(); 
		});
		$(".look-sale-product img").live("mouseleave",function(){
			var $ui = $(this).parent();
			$ui.find(".sale-product-info").hide();
		});
	</script>
</body>
</html>