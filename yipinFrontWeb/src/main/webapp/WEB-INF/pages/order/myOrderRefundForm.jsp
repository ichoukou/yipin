<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglibs.jsp"%>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<title>退货申请</title>
	<jsp:include page="/WEB-INF/pages/include/common.jsp"></jsp:include>
	<!--page css-->
	<link rel="stylesheet" type="text/css" href="${_cssPath}/pages/returnApply.css" media="all" />
	<!--page css-->
	<link rel="stylesheet" type="text/css" href="${_cssPath}/pages/ordData.css" media="all" />
	<link rel="stylesheet" type="text/css" href="${_jsPath}/plugin/uploadify/uploadify.css" media="all" />
</head>
<body>
	<!--头部导航 start-->
	<%@include file="/WEB-INF/pages/include/head.jsp"%>
	<!--头部导航 end-->
	<div class="w_norm">
		<!-- 用户中心框架 start-->
		<div class="uc cf">
			<!-- 用户中心左侧 start-->
			<%@include file="/WEB-INF/pages/include/left.jsp"%>
			<!-- 用户中心左侧 end-->
			<!-- 用户中心右侧 start-->
			<div class="uc_r">
				<div class="ucr_hd"><a href="${_ctxPath}/order/order-myRefundManage.htm">退货管理</a> > 退货申请</div>
				<div class="return_apply">
					<!-- 退货说明 s-->
					<div class="return_sm">
						退款说明：<br/>
						此退款办理流程适用于下单后已付款需要取消订单的情况，需要特别注意退款会扣取最高3%的手续费。更多信息请查看<a target="_blank" href="${_ctxPath}/help/help-333336.htm">退款政策</a>
					</div>
					<!-- 退货说明 e-->
					<!-- 订单列表 start-->
					<div class="return_bd">
						<table class="ps_proList">
	                    	<thead>
	                    		<tr>
	                    			<th colspan="2">退货商品</th>
	                    			<th>订单来源</th>
	                    			<th>下单时间</th>
	                    			<th>订单状态</th>
	                    			<th>购买数量</th>
	                    			<th>退货数量</th>
	                    			<th>退货商品金额</th>
	                    		</tr>
	                    	</thead>
	                        <tbody>
		                        <tr>
		                            <td>
		                            	<a href="${_ctxPath}/item-${orderItem.productSkuId}-0-0.htm" target="_blank"><img src="<yp:thumbImage originalPath='${orderItem.defaultImage}' imageType='t84'></yp:thumbImage>" alt="${orderItem.productName}" /></a>
									</td>
									<td>
										<p>
											<a href="${_ctxPath}/item-${orderItem.productSkuId}-0-0.htm" target="_blank" class="c1">
											${yipin:cutString(orderItem.productName,15)}
											</a>
										</p>
		                            	<p><span class="c2">${orderItem.productProp}</span></p>
									</td>
		                            <td>
		                            	<a href="${_ctxPath}/order/order-myOrderPackageDetail-${orderItem.orderHead.orderAddress.orderAddressId}.htm" target="_blank" class="c1">${orderItem.orderHead.orderNo}</a>
		                            </td>
		                            <td><fmt:formatDate value="${orderItem.orderHead.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		                            <td><spring:message code="order.status.${orderItem.orderHead.status}"/></td>
		                            <td>${orderItem.num}</td>
		                            <td><input type="text" class="ip_txt J_refundnum" value="${orderItem.num}" data-num="${orderItem.num}" data-unitPrice="${orderItem.unitPrice}"/></td>
		                            <td>
		                            	${orderItem.unitPrice} * <span class="refundnum">${orderItem.num}</span>=<span class="refundamount">${orderItem.unitPrice * orderItem.num}</span>
	                            	</td>
		                        </tr>
	                        </tbody>
	                        <tfoot>
	                        	<tr>
	                        		<td colspan="4"></td>
	                        		<td>小计</td>
	                        		<td>${orderItem.num}</td>
	                        		<td><span class="refundnum">${orderItem.num}</span></td>
	                        		<td class="red"><span class="refundamount">${orderItem.unitPrice * orderItem.num}</span></td>
	                        	</tr>
	                        </tfoot>
	                    </table>
					</div>
					<!-- 订单列表 end-->
					<div class="apply_form">
					<form action="" id="form1">
						<table width="100%">
							<tbody>
								<tr><td class="tit">*联系人：</td>
									<td>
										<input id="refundContactName" type="text" class="txt" />
										<div class="relative"><span id="refundContactNameTips"></span></div>
									</td>
								</tr>
								<tr><td class="tit">*手机号码：</td>
									<td>
										<input id="refundMobile" type="text" class="txt" />
										<div class="relative"><span id="refundMobileTips"></span></div>
									</td>
								</tr>
								<tr><td class="tit">上传图片：</td>
									<td>
										<div id="divUploadImage" class="upload_img cf">
											<ul>
												<li>
													<input id="file_upload1" name="file_upload1" type="file" multiple="true">
													<div id="imageDivId1" class="completed_show" data-url="">
														<img src="" />
														<div class="mask"></div>
														<div class="completed_del"></div>
													</div>
												</li>
												<li>
													<input id="file_upload2" name="file_upload2" type="file" multiple="true">
													<div id="imageDivId2" class="completed_show" data-url="">
														<img src="" />
														<div class="mask"></div>
														<div class="completed_del"></div>
													</div>
												</li>
												<li>
													<input id="file_upload3" name="file_upload3" type="file" multiple="true">
													<div id="imageDivId3" class="completed_show" data-url="">
														<img src="" />
														<div class="mask"></div>
														<div class="completed_del"></div>
													</div>
												</li>
											</ul>
										</div>
										<input id="refundImagesUrl" type="hidden" value=""/>
									</td>
								</tr>
								<tr><td class="tit">*退货原因：</td>
									<td>
										<textarea id="refundReason" cols="30" rows="10"></textarea>
										<p>您可以输入<i class="red J_textTip">300</i></p>
										<div class="relative"><span id="refundReasonTips"></span></div>
									</td>
								</tr>
								<tr><td class="tit">退款方式：</td><td>退货完成后，系统将以原支付方式退回支付金额</td></tr>
								<c:if test="${orderItem.orderHead.hasInvoice eq invoiceStatus[1]}">
									<tr><td class="tit">是否有发票：</td><td>该订单有发票，请将发票与商品一起退回</td></tr>
								</c:if>
								<tr>
									<td></td>
									<td>
										<input id="orderItemIdHidden" type="hidden" value="${orderItem.orderItemId}"/>
										<input id="orderIdHidden" type="hidden" value="${orderItem.orderHead.orderId}"/>
										<input id="orderAddressIdHidden" type="hidden" value="${orderItem.orderHead.orderAddress.orderAddressId}"/>
										<a href="javascript:;" class="submit" id="refundSubmit"> <!--  style="display: none;" -->提交申请</a>
										<a href="javascript:;" class="goback" id="reset">重置</a>
										<a href="${_ctxPath}/order/order-myRefundManage.htm" class="goback">返回</a>
									</td>
								</tr>
							</tbody>
						</table>
						</form>
					</div>
				</div>
			</div>
			<!-- 用户中心右侧 end-->
		</div>
		<!-- 用户中心框架 end-->
	</div>
	<!--底部 start-->
	<%@include file="/WEB-INF/pages/include/foot.jsp"%>
	<!--底部 end-->
	<!--page js-->
	<script type="text/javascript" src="${_jsPath}/plugin/bigdecimal/bigdecimal.js"></script>
	<script type="text/javascript" src="${_jsPath}/pages/returnApply.js"></script>
	<script type="text/javascript" src="${_jsPath}/plugin/uploadify/jquery.uploadify.min.js"></script>
	<script type="text/javascript">
	$(function(){
		//修改退货数量
		$(".J_refundnum").keyup(function(event){
			var _this = $(this);
			olnyNumber(_this);
			editRefundNum(_this);
		});
		//手机只能输入数字
		$("#refundMobile").keyup(function(event){
			var $this = $(this);
		    olnyNumber2($this,11);
		});
		//给上传图片注册事件
		$("#divUploadImage").find("li").each(function(i){
			var $this = $(this),
				sizeLimit = 2097152,  //单位:b
				fileId = $this.find("input").attr("id"), //上传file控件对象
				completedId = $this.find(".completed_show").attr("id"); //上传成功后图片显示层对象
			$('#'+fileId).uploadify({
				'formData':{
					'timestamp':'1386842515',
					'token':'4117e3defacb3cf3464fe71d333818b1',
					'category':'refundOrder'
				},
				'swf':'${_jsPath}/plugin/uploadify/uploadify.swf',
				'uploader':'${_ctxPath}/upload/upload.htm',
				'fileObjName': 'file',
		        'queueID': fileId,//与下面的id对应
		        'queueSizeLimit': 1,
		        'fileTypeExts': '*.jpg;*.gif;*.png',
		        'fileTypeDesc': '(.JPG, .GIF, .PNG)',//控制可上传文件的扩展名，启用本项时需同时声明fileDesc
		        'auto': true,
		        'multi': false,
		        //'fileSizeLimit': '2048KB',
		        'simUploadLimit': 1, //一次可以传多少个文件
		        'queueSizeLimit': 1, //队列文件数量
		        'width':80,
		        'height':80,
		        'buttonText': '',
		        'overrideEvents':['onSelect'],
		        'onUploadSuccess': function (file, data, response) {
		        	//禁止上传大图片
		        	if(file.size > sizeLimit){
		        		return false;
		        	}
		            //处理上传成功后的逻辑
		            $("#" + completedId).attr('data-url',data); //给completedId对象绑定一个data-url值
		            $("#" + completedId + " img").attr("src", '${_fileThumbPath}' + data + "_t84" + file.type); //给图片赋值
		            $("#" + fileId).hide().uploadify('cancel','*'); //隐藏file控件层 并清除队列
		            $("#" + completedId).show(); //显示完成层图片
		        },
		        'onSelect': function(file){
		        	if(file.size > sizeLimit){
		        		$.dialog({
							title: false,
							content: "<p class='dialogDiv'>您的图片已超过 2M ，不能上传！</p>",
					        lock: true,
					        fixed: true,
					        okValue: "确定",
							ok: true
						 });
					    $(".d-close").hide();
		        		//取消上传
		        		this.cancelUpload(fileId);
		        		$("#" + fileId).uploadify('cancel','*');
		        		return false;
		        	}
		        }
			});
			//显示遮罩和删除层
			$("#" + completedId).on({
				'mouseover':function(){
					var _this = $(this);
					_this.find('.mask').show();
					_this.find('.completed_del').show();
				},
				'mouseout':function(){
					var _this = $(this);
					_this.find('.mask').hide();
					_this.find('.completed_del').hide();
				}
			})
			//删除图片重新释放并可重复以上步骤
			$("#" + completedId).find('.completed_del').on('click',function(){
				$.dialog({
					title: false,
					content: "<p class='dialogDiv'>您确定要删除这张图片？</p>",
			        lock: true,
			        fixed: true,
			        okValue: "确定",
					ok: function(){
						$("#" + completedId).attr('data-url','');
						//清除队列
						$("#" + fileId).show().uploadify('cancel','*');
						$("#" + completedId).hide();
			        },
			        cancel: true,
			        cancelValue: "取消"
				 });
			    $(".d-close").hide();
			});
		});
	});
	</script>
</body>
</html>