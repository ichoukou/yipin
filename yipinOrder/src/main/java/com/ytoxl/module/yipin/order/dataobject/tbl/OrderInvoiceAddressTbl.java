package com.ytoxl.module.yipin.order.dataobject.tbl;

import java.util.Date;

public class OrderInvoiceAddressTbl {

	protected Integer orderInvoiceAddressId;	//订单收货地址主键
	protected Integer orderId;			//主订单id
	protected String receiverName;		//收货人姓名
	protected Integer regionId;			//关联的收货地区
	protected String detailAddress;		//详细地址
	protected String receiveAddress;	//拼接的整体地址
	protected String mobile;			//收货人手机
	protected String telephone;			//收货人电话
	protected String postCode;			//收货地址邮编
	protected Integer expressId;		//快递id
	protected String expressNo;			//快递单号
	protected String expressName;       // 快递名称
	protected Date createTime;			//创建时间
	protected Date updateTime;			//记录更新时间
	

	public Integer getOrderInvoiceAddressId() {
		return orderInvoiceAddressId;
	}
	public void setOrderInvoiceAddressId(Integer orderInvoiceAddressId) {
		this.orderInvoiceAddressId = orderInvoiceAddressId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
	public String getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	public String getExpressName() {
		return expressName;
	}
	public void setExpressName(String expressName) {
		this.expressName = expressName;
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
