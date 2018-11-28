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
	<link rel="stylesheet" type="text/css" href="${_jsPath}/plugin/uploadify/uploadify.css" media="all" />
</head>
<body>
	<!--头部导航 start-->
	<%@include file="/WEB-INF/pages/include/headUserInfo.jsp"%>
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
					<!-- 订单列表 start-->
					<div class="data_item">
						<div class="fold_wp">
							<div class="data_con">
								<div class="uf_item">
									<div class="unfold cf">
										<table width="100%">
											<tbody>
											<tr>
												<td width="60" class="pak_nm">包裹${orderAddress.packageNo}：</td>
												<td width="530">${escape:escapeHtml(orderAddress.receiveAddress)} (${orderAddress.receiverName} 收) ${orderAddress.mobile}</td>
												<td width="65" class="num"><!-- 2 --></td>
												<td width="80" class="mny"><!-- 100 --></td>
												<td>
													<a href="${_ctxPath}/order/order-myOrderPackageDetail-${orderAddress.orderAddressId}.htm" target="_blank">查看</a>
												</td>
											</tr>
											</tbody>
										</table>
									</div>
									<div class="package">
										<table width="100%">
											<tbody>
											<c:forEach items="${orderAddress.orderAddressItems}" var="itemList">
											<tr>
												<td width="50"></td>
												<td width="95"><img src="<yp:thumbImage originalPath='${itemList.orderItem.defaultImage}' imageType='t84'></yp:thumbImage>" alt="${itemList.orderItem.productName}" /></td>
												<td width="445">
													<p><a href="javascript:void(0);">${itemList.orderItem.productName}</a></p>
													<p><span>${itemList.orderItem.productProp}</span></p>
												</td>
												<td width="65">数量：${itemList.assignCount}</td>
												<td>金额：<span class="red">${itemList.assignCount * itemList.orderItem.unitPrice}</span></td>
												<td>积分：${yipin:computerPoint(itemList.assignCount * itemList.orderItem.unitPrice)}</td>
											</tr>
											</c:forEach>
											</tbody>
										</table>
									</div>
									<!--总计金额 star-->
									<div class="total">
										 金额总计：<var>￥${orderAddress.packageTotalPrice}</var> <br/>
										 运费：<var>￥0.00</var><br/>
										 包裹金额：<var class="red rental">￥${orderAddress.packageTotalPrice}<!-- <i>元</i> --></var>
									</div>
									<!--总计金额 end-->
								</div>
							</div>
						</div>
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
												<li>
													<input id="file_upload4" name="file_upload4" type="file" multiple="true">
													<div id="imageDivId4" class="completed_show" data-url="">
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
										<div class="relative"><span id="refundReasonTips"></span></div>
									</td>
								</tr>
								<tr><td class="tit">退款方式：</td><td>退货完成后，系统将以原支付方式退回支付金额</td></tr>
								<c:if test="${orderAddress.orderHead.hasInvoice eq invoiceStatus[1]}">
									<tr><td class="tit">是否有发票：</td><td>是，请将发票与商品一起退回</td></tr>
								</c:if>
								<tr>
									<td></td>
									<td>
										<input id="orderAddressIdHidden" type="hidden" value="${orderAddress.orderAddressId}"/>
										<input id="orderIdHidden" type="hidden" value="${orderAddress.orderHead.orderId}"/>
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
	<script type="text/javascript" src="${_jsPath}/pages/returnApply.js"></script>
	<script type="text/javascript" src="${_jsPath}/plugin/uploadify/jquery.uploadify.min.js"></script>
	<script type="text/javascript">
	$(function(){
		//手机只能输入数字
		$("#refundMobile").keyup(function(event){
			var $this = $(this);
		    olnyNumber($this);
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