package com.ytoxl.module.yipin.order.dataobject.tbl;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单收货地址
 */
public class OrderAddressTbl {
	
	// 订单收货地址主键(orderAddressId)
	protected Integer orderAddressId;
	// 包裹编号(packageNo)
	protected String packageNo;
	// 主订单id(orderId)
	protected Integer orderId;
	// 包裹总金额(packageTotalPrice)
	protected BigDecimal packageTotalPrice;
	// 是否收货(isReceive)
	protected Short isReceive;
	// 收货人姓名(receiverName)
	protected String receiverName;
	// 关联的收货地区(regionId)
	protected Integer regionId;
	// 详细地址(detailAddress)
	protected String detailAddress;
	// 收货整体地址(receiveAddress)
	protected String receiveAddress;
	// 收货人手机(mobile)
	protected String mobile;
	// 收货人电话(telephone)
	protected String telephone;
	// 收货地址邮编(postCode)
	protected String postCode;
	// 快递id(expressId)
	protected Integer expressId;
	// 快递名称(expressName)
	protected String expressName;
	// 快递单号(expressNo)
	protected String expressNo;
	//发货时间
	protected Date sendProductTime;
	//收货时间
	protected Date receiveProductTime;
	// 创建时间(createTime)
	protected Date createTime;
	// 记录更新时间(updateTime)
	protected Date updateTime;

	public Integer getOrderAddressId() {
		return orderAddressId;
	}

	public void setOrderAddressId(Integer orderAddressId) {
		this.orderAddressId = orderAddressId;
	}

	public String getPackageNo() {
		return packageNo;
	}

	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getPackageTotalPrice() {
		return packageTotalPrice;
	}

	public void setPackageTotalPrice(BigDecimal packageTotalPrice) {
		this.packageTotalPrice = packageTotalPrice;
	}

	public Short getIsReceive() {
		return isReceive;
	}

	public void setIsReceive(Short isReceive) {
		this.isReceive = isReceive;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public Integer getExpressId() {
		return expressId;
	}

	public void setExpressId(Integer expressId) {
		this.expressId = expressId;
	}

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	
	public Date getSendProductTime() {
		return sendProductTime;
	}

	public void setSendProductTime(Date sendProductTime) {
		this.sendProductTime = sendProductTime;
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

	public Date getReceiveProductTime() {
		return receiveProductTime;
	}

	public void setReceiveProductTime(Date receiveProductTime) {
		this.receiveProductTime = receiveProductTime;
	}

}
