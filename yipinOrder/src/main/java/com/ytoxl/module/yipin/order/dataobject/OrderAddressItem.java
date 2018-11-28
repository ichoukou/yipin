package com.ytoxl.module.yipin.order.dataobject;

import java.math.BigDecimal;

import com.ytoxl.module.yipin.order.dataobject.tbl.OrderAddressItemTbl;

public class OrderAddressItem extends OrderAddressItemTbl {

	protected String brandName;
	protected String productName;
	protected BigDecimal unitPrice;
	protected BigDecimal marketPrice;
	protected Short orderSource;
	// 包裹对应的订单orderItem
	private OrderItem orderItem;

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
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

	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Short getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(Short orderSource) {
		this.orderSource = orderSource;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}
}
