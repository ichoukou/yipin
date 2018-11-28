package com.ytoxl.module.yipin.order.dataobject;

import com.ytoxl.module.yipin.base.common.CommonConstants;
import com.ytoxl.module.yipin.base.common.utils.StringUtils;
import com.ytoxl.module.yipin.base.dataobject.ProductSku;
import com.ytoxl.module.yipin.order.dataobject.tbl.OrderItemTbl;

public class OrderItem extends OrderItemTbl {
	
	private String brandName;
	private ProductSku productSku;
	private boolean isCanRefund; // 是否可以退货  true = 能   false = 不能
	private String refundStatusInfo;  //若退货保存退货的状态信息 
	private OrderHead orderHead;
	
	/**
	 * 获取订单想默认图片 缓存的商品第一张默认图片，添加相应前后缀可以访问缩略图
	 * @return
	 */
	public String getDefaultImage(){
		String thumbnail = null;
		if(StringUtils.isNotEmpty(imageUrls)){
			String[] thumbnails = imageUrls.split(CommonConstants.STR_SPLIT);
			thumbnail = thumbnails != null  && thumbnails.length > 0 ? thumbnails[0] : "";
		}
		return thumbnail;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public ProductSku getProductSku() {
		return productSku;
	}

	public void setProductSku(ProductSku productSku) {
		this.productSku = productSku;
	}

	public boolean isCanRefund() {
		return isCanRefund;
	}

	public void setCanRefund(boolean isCanRefund) {
		this.isCanRefund = isCanRefund;
	}

	public String getRefundStatusInfo() {
		return refundStatusInfo;
	}

	public void setRefundStatusInfo(String refundStatusInfo) {
		this.refundStatusInfo = refundStatusInfo;
	}

	public OrderHead getOrderHead() {
		return orderHead;
	}

	public void setOrderHead(OrderHead orderHead) {
		this.orderHead = orderHead;
	}
}
