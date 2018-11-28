package com.ytoxl.module.yipin.order.dataobject.tbl;

import java.math.BigDecimal;
import java.util.Date;

public class OrderHeadTbl {

	protected Integer orderId;	//主键
	protected String  orderNo;	//订单号
	protected Integer userId;	//用户身份标示
	protected Short  orderType; //订单类型
	protected Short   status;	//订单状态：1=待付款，2=待发货，3=已发货，4=已完成（发货后,默认15天后自动完成，定时器跑任务），5=已取消，6=退货
	protected BigDecimal paymentAmount;	//订单实际付款金额
	protected BigDecimal originalAmount;//订单没有使用优惠券的总价
	protected Short hasInvoice;			//是否需要发票，1=不要，2=要
	protected String invoiceTitle;	//发票抬头
	protected String orderRemark;  //订单备注
	protected Date payTime;		//支付时间
	protected String accountId;	//支付账号id
	protected String accountInfo;	//支付账号信息
	protected String ipAddress;		//支付ip
	protected Short payStatus;		//1=已付款
	protected Short payType; //1=支付宝
	protected Short isDelete; //0=是  1=否
	protected Date createTime;		//订单创建时间
	protected Date updateTime;		//记录数据记录更新时间
	
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Short getOrderType() {
		return orderType;
	}

	public void setOrderType(Short orderType) {
		this.orderType = orderType;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public BigDecimal getOriginalAmount() {
		return originalAmount;
	}

	public void setOriginalAmount(BigDecimal originalAmount) {
		this.originalAmount = originalAmount;
	}

	public Short getHasInvoice() {
		return hasInvoice;
	}

	public void setHasInvoice(Short hasInvoice) {
		this.hasInvoice = hasInvoice;
	}

	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	public String getOrderRemark() {
		return orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(String accountInfo) {
		this.accountInfo = accountInfo;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Short getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Short payStatus) {
		this.payStatus = payStatus;
	}

	public Short getPayType() {
		return payType;
	}

	public void setPayType(Short payType) {
		this.payType = payType;
	}

	public Short getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
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
