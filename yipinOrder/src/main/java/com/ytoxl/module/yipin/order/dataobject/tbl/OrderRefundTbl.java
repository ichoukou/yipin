package com.ytoxl.module.yipin.order.dataobject.tbl;

import java.util.Date;

/**
 * 退货订单主信息
 */
public class OrderRefundTbl {

	// 主键
	protected Integer orderRefundId;
	// 订单id(orderId)
	protected Integer orderId;
	// 订单收货地址(orderAddressId)
	protected Integer orderAddressId;
	//退货商品明细
	protected Integer orderItemId;
	//退货编号
	protected String orderRefundNo;
	// 退货数量(refundNum)
	protected Short refundNum;
	// 退货联系人姓名(customerName)
	protected String customerName;
	// 手机号码(mobile)
	protected String mobile;
	// 退货原因(refundReason)
	protected String refundReason;
	// 商品url(imageUrls)
	protected String imageUrls;
	// 审核描述(reviewDescribe)
	protected String reviewDescribe;
	// 状态(status)
	protected Short status;
	// 商家审核人id(sellerCheckUserId)
	protected Integer sellerCheckUserId;
	// 商家审核时间(sellerCheckTime)
	protected Date sellerCheckTime;
	// 管理员审核人id(adminCheckUserId)
	protected Integer adminCheckUserId;
	// 管理员审核时间(adminCheckTime)
	protected Date adminCheckTime;
	// 创建时间(createTime)
	protected Date createTime;
	// 记录更新时间(updateTime)
	protected Date updateTime;
	
	public Integer getOrderRefundId() {
		return orderRefundId;
	}

	public void setOrderRefundId(Integer orderRefundId) {
		this.orderRefundId = orderRefundId;
	}

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

	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	public String getOrderRefundNo() {
		return orderRefundNo;
	}

	public void setOrderRefundNo(String orderRefundNo) {
		this.orderRefundNo = orderRefundNo;
	}

	public Short getRefundNum() {
		return refundNum;
	}

	public void setRefundNum(Short refundNum) {
		this.refundNum = refundNum;
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

	public String getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(String imageUrls) {
		this.imageUrls = imageUrls;
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
