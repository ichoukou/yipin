package com.ytoxl.module.yipin.order.dataobject.tbl;

import java.math.BigDecimal;
import java.util.Date;

public class OrderRefundPaymentTbl {

	protected Integer orderRefundPaymentId;//订单退款信息主键
	protected Integer ordeRrefundId;
	protected BigDecimal refundAmount;		//实际退款金额
	protected BigDecimal originalAmount;		//未参与任何优惠的商品总价格
	protected Short orderNetPayType;		
	protected String batchNo;				//退款编号:支付宝返回的编号
	protected String refundAccount;			//退款帐号
	protected Date refundTime;				//退款时间
	protected String ipAddress;				//退款申请的ip地址
	protected String refundReason;			//退款原因
	protected Short status;					//0=待退款，1=已退款
	protected Date createTime;				//记录创建时间
	protected Date updateTime;				//记录更新时间
	
	public Integer getOrderRefundPaymentId() {
		return orderRefundPaymentId;
	}
	public void setOrderRefundPaymentId(Integer orderRefundPaymentId) {
		this.orderRefundPaymentId = orderRefundPaymentId;
	}
	public Integer getOrdeRrefundId() {
		return ordeRrefundId;
	}
	public void setOrdeRrefundId(Integer ordeRrefundId) {
		this.ordeRrefundId = ordeRrefundId;
	}
	public BigDecimal getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}
	public BigDecimal getOriginalAmount() {
		return originalAmount;
	}
	public void setOriginalAmount(BigDecimal originalAmount) {
		this.originalAmount = originalAmount;
	}
	public Short getOrderNetPayType() {
		return orderNetPayType;
	}
	public void setOrderNetPayType(Short orderNetPayType) {
		this.orderNetPayType = orderNetPayType;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getRefundAccount() {
		return refundAccount;
	}
	public void setRefundAccount(String refundAccount) {
		this.refundAccount = refundAccount;
	}
	public Date getRefundTime() {
		return refundTime;
	}
	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getRefundReason() {
		return refundReason;
	}
	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
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
