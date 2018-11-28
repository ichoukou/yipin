package com.ytoxl.module.yipin.order.dataobject.tbl;

import java.math.BigDecimal;

public class OrderItemTbl {
	
	protected Integer orderItemId; // 订单详情主键
	protected Integer orderId; 		// 对应订单主表中orderId
	protected Integer productSkuId; // 对应商品的productskuid
	protected String productName; 	// 商品名称(快照商品名称)
	protected BigDecimal unitPrice; // 单价(快照商品信息中的单价)
	protected Integer num; 			// 产品数量
	protected BigDecimal marketPrice; // 市场价格
	protected String productProp; 	// 商品属性
	protected String imageUrls; 	// 商品图片url
	protected Short orderSource; 	// 订单来源 1=非预售 2=预售
	protected Integer sellerId;       //商家身份标示

	public Integer getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getProductSkuId() {
		return productSkuId;
	}
	public void setProductSkuId(Integer productSkuId) {
		this.productSkuId = productSkuId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public BigDecimal getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}
	public String getProductProp() {
		return productProp;
	}
	public void setProductProp(String productProp) {
		this.productProp = productProp;
	}
	public String getImageUrls() {
		return imageUrls;
	}
	public void setImageUrls(String imageUrls) {
		this.imageUrls = imageUrls;
	}
	public Integer getSellerId() {
		return sellerId;
	}
	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}
	public Short getOrderSource() {
		return orderSource;
	}
	public void setOrderSource(Short orderSource) {
		this.orderSource = orderSource;
	}
	
}
