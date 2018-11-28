package com.ytoxl.module.yipin.order.dataobject.resultmap;

public class OrderRefundStaModel {

	private Integer orderId;
	
	private Integer orderAddressId;
	
	private Short refundStatus;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderAddressId() {
		return orderAddressId;
	}

	public void setOrderAddressId(Integer orderAddressId) {
		this.orderAddressId = orderAddressId;
	}

	public Short getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(Short refundStatus) {
		this.refundStatus = refundStatus;
	}

}
