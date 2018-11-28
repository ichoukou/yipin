package com.ytoxl.module.yipin.order.dataobject.resultmap;

import java.math.BigDecimal;
import java.util.Date;

import com.ytoxl.module.yipin.base.common.utils.excel.ExcelField;
import com.ytoxl.module.yipin.base.common.utils.excel.FormatType;

public class OrderExportModel {
	
	protected Integer orderId;
	
	protected Integer orderAddressId;
	
	protected Integer orderItemId;

	@ExcelField(filedName="订单号",sort=0)
	protected String orderNo;
	
	protected String packageNo;
	
	@ExcelField(filedName="订单状态",sort=1)
	protected String ostatus;

	protected Short orderStatus;
	
	protected String pstatus;

	protected Short packageStatus;
	
	protected Short cancelStatus;	//待发货订单取消退款状态
	
	@ExcelField(filedName="订单待发货退款状态",sort=2)
	protected String cstatus;
	
	protected Short refundStatus;	//退货包裹状态
	
	@ExcelField(filedName="退货商品状态",sort=3)
	protected String rstatus;
	
	@ExcelField(filedName="商家名称",sort=4)
	protected String username;
	
	@ExcelField(filedName="品牌名称",sort=5)
	protected String brandName;
	
	@ExcelField(filedName="商品名称",sort=6)
	protected String productName;
	
	@ExcelField(filedName="商品规格",sort=7)
	protected String productProp;
	
	@ExcelField(filedName="购买商品数量",sort=8)
	protected Integer num;

	@ExcelField(filedName="商品单价",sort=9)
	protected BigDecimal unitPrice;
	
	@ExcelField(filedName="收货人姓名",sort=10)
	protected String receiverName;
	
	@ExcelField(filedName="收货地址",sort=11)
	protected String receiveAddress;
	
	@ExcelField(filedName="邮编",sort=12)
	protected String postCode;
	
	@ExcelField(filedName="联系电话",sort=13)
	protected String telephone;
	
	@ExcelField(filedName="联系手机",sort=14)
	protected String mobile;
	
	@ExcelField(filedName="发票抬头",sort=15)
	protected String invoiceTitle;
	
	protected String internationalCode;
	
	@ExcelField(filedName="SKU编码",sort=16)
	protected String skuCode;
	
	protected Date orderCreateTime;

	@ExcelField(filedName="订单支付时间",sort=17,formatType=FormatType.DATE,pattern="yyyy-MM-dd HH:mm:ss")
	protected Date payTime;
	
	public String getCstatus() {
		return cstatus;
	}

	public void setCstatus(String cstatus) {
		this.cstatus = cstatus;
	}

	public String getRstatus() {
		return rstatus;
	}

	public void setRstatus(String rstatus) {
		this.rstatus = rstatus;
	}

	protected Short repayStatus;	//包裹退款状态
	
	public Short getCancelStatus() {
		return cancelStatus;
	}

	public void setCancelStatus(Short cancelStatus) {
		this.cancelStatus = cancelStatus;
	}
	
	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	public String getProductProp() {
		return productProp;
	}

	public void setProductProp(String productProp) {
		this.productProp = productProp;
	}
	public Short getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(Short refundStatus) {
		this.refundStatus = refundStatus;
	}

	public Short getRepayStatus() {
		return repayStatus;
	}

	public void setRepayStatus(Short repayStatus) {
		this.repayStatus = repayStatus;
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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPackageNo() {
		return packageNo;
	}

	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo;
	}

	public Short getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Short orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Short getPackageStatus() {
		return packageStatus;
	}

	public void setPackageStatus(Short packageStatus) {
		this.packageStatus = packageStatus;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	public String getInternationalCode() {
		return internationalCode;
	}

	public void setInternationalCode(String internationalCode) {
		this.internationalCode = internationalCode;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public Date getOrderCreateTime() {
		return orderCreateTime;
	}

	public void setOrderCreateTime(Date orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	
	public String getOstatus() {
		return ostatus;
	}

	public void setOstatus(String ostatus) {
		this.ostatus = ostatus;
	}

	public String getPstatus() {
		return pstatus;
	}

	public void setPstatus(String pstatus) {
		this.pstatus = pstatus;
	}

}
