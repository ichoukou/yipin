package com.ytoxl.module.yipin.order.dataobject.tbl;

/**
 * 订单商品配送明细
 */
public class OrderAddressItemTbl {
	
	// 主键
	protected Integer orderAddressItemId;
	// 关联配送地址(orderAddressId)
	protected Integer orderAddressId;
	// 关联订单(orderId)
	protected Integer orderId;
	// 关联订单子项(orderItemId)
	protected Integer orderItemId;
	//具体商品id
	protected Integer productSkuId;
	// 配送数量(assignCount)
	protected Integer assignCount;

	public Integer getOrderAddressItemId() {
		return orderAddressItemId;
	}

	public void setOrderAddressItemId(Integer orderAddressItemId) {
		this.orderAddressItemId = orderAddressItemId;
	}

	public Integer getOrderAddressId() {
		return orderAddressId;
	}

	public void setOrderAddressId(Integer orderAddressId) {
		this.orderAddressId = orderAddressId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}
	
	public Integer getProductSkuId() {
		return productSkuId;
	}

	public void setProductSkuId(Integer productSkuId) {
		this.productSkuId = productSkuId;
	}

	public Integer getAssignCount() {
		return assignCount;
	}

	public void setAssignCount(Integer assignCount) {
		this.assignCount = assignCount;
	}

}
