package com.ytoxl.module.yipin.order.dataobject.tbl;

import java.util.Date;

public class OrderNetpayTbl {

	protected Integer orderNetpayId;	//支付关联订单主键
	protected Integer orderId;			//订单id
	protected String outTradeNo;		//本系统生成的交易号
	protected String tradeNo;			//第三方支付平台产生的交易号
	protected Short orderAlipayType;	//支付类型
	protected Short status;				//1未支付 2 支付成功
	protected Date callbackTime;		//回调时间
	protected Date createTime;			//记录创建时间
	protected Date updateTime;			//记录更新时间
		
	public Integer getOrderNetpayId() {
		return orderNetpayId;
	}
	public void setOrderNetpayId(Integer orderNetpayId) {
		this.orderNetpayId = orderNetpayId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public Short getOrderAlipayType() {
		return orderAlipayType;
	}
	public void setOrderAlipayType(Short orderAlipayType) {
		this.orderAlipayType = orderAlipayType;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public Date getCallbackTime() {
		return callbackTime;
	}
	public void setCallbackTime(Date callbackTime) {
		this.callbackTime = callbackTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
