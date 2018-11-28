package com.ytoxl.module.yipin.order.dataobject.resultmap;

import java.math.BigDecimal;

public class NetpayModel {
	
	private String orderIds;
	private String orderNos;
	private String outTradeNo;
	private BigDecimal totalFee;
	private Short payType;
	
	public String getOrderIds() {
		return orderIds;
	}
	public void setOrderIds(String orderIds) {
		this.orderIds = orderIds;
	}
	public String getOrderNos() {
		return orderNos;
	}
	public void setOrderNos(String orderNos) {
		this.orderNos = orderNos;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public BigDecimal getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}
	public Short getPayType() {
		return payType;
	}
	public void setPayType(Short payType) {
		this.payType = payType;
	}
	
	
	
}
