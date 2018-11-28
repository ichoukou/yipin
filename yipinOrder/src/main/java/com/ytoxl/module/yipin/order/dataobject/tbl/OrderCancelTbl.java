package com.ytoxl.module.yipin.order.dataobject.tbl;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单待发货退款信息
 */
public class OrderCancelTbl {

	// 主键
	protected Integer orderCancelId;		
	//	订单id(orderId)	
	protected Integer orderId;
	//	退货编号(orderCancelNo)	
	protected String orderCancelNo;
	//	退货联系人姓名(customerName)	
	protected String customerName;
	//	手机号码(mobile)	
	protected String mobile;
	//	退货原因(refundReason)	
	protected String refundReason;
	//	审核描述(reviewDescribe)	
	protected String reviewDescribe;
	//	状态(status)	
	protected Short status;
	//	实际退款金额(refundAmount)	
	protected BigDecimal refundAmount;
	//	商家审核人id(sellerCheckUserId)	
	protected Integer sellerCheckUserId;
	//	商家审核时间(sellerCheckTime)	
	protected Date sellerCheckTime;
	//	管理员审核人id(adminCheckUserId)
	protected Integer adminCheckUserId;
	//	管理员审核时间(adminCheckTime)	
	protected Date adminCheckTime;
	//	创建时间(createTime)	
	protected Date createTime;
	//	记录更新时间(updateTime)	
	protected Date updateTime;

	public Integer getOrderCancelId() {
		return orderCancelId;
	}

	public void setOrderCancelId(Integer orderCancelId) {
		this.orderCancelId = orderCancelId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public String getOrderCancelNo() {
		return orderCancelNo;
	}

	public void setOrderCancelNo(String orderCancelNo) {
		this.orderCancelNo = orderCancelNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}

	public String getReviewDescribe() {
		return reviewDescribe;
	}

	public void setReviewDescribe(String reviewDescribe) {
		this.reviewDescribe = reviewDescribe;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public BigDecimal getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}

	public Integer getSellerCheckUserId() {
		return sellerCheckUserId;
	}

	public void setSellerCheckUserId(Integer sellerCheckUserId) {
		this.sellerCheckUserId = sellerCheckUserId;
	}

	public Date getSellerCheckTime() {
		return sellerCheckTime;
	}

	public void setSellerCheckTime(Date sellerCheckTime) {
		this.sellerCheckTime = sellerCheckTime;
	}

	public Integer getAdminCheckUserId() {
		return adminCheckUserId;
	}

	public void setAdminCheckUserId(Integer adminCheckUserId) {
		this.adminCheckUserId = adminCheckUserId;
	}

	public Date getAdminCheckTime() {
		return adminCheckTime;
	}

	public void setAdminCheckTime(Date adminCheckTime) {
		this.adminCheckTime = adminCheckTime;
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
