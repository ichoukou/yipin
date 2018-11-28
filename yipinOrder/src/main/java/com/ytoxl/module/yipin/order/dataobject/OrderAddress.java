package com.ytoxl.module.yipin.order.dataobject;

import java.util.List;

import com.ytoxl.module.yipin.base.dataobject.Express;
import com.ytoxl.module.yipin.base.dataobject.resultmap.ExpressMessage;
import com.ytoxl.module.yipin.order.dataobject.tbl.OrderAddressTbl;

public class OrderAddress extends OrderAddressTbl {
	
	private Integer skuCount; // 包裹中的sku数量
	private List<OrderAddressItem> orderAddressItems;

	private OrderHead orderHead;
	private Express express;
	private ExpressMessage expressMessage;   //包裹对应的物流信息
	private Integer userAddressId;
	
	public static final Short STATUS_NOTRECEIVE=1;  //1=未收货
	public static final Short STATUS_RECEIVED=2;    //2=已收货
	public static final Short[] PACKAGE_STATUSES = new Short[]{STATUS_NOTRECEIVE,STATUS_RECEIVED};
	
	private boolean isCanRefund; // 是否可以退货  true = 能   false = 不能
	
	private String refundStatusInfo;  //若退货保存退货的状态信息 

	public Integer getSkuCount() {
		return skuCount;
	}

	public void setSkuCount(Integer skuCount) {
		this.skuCount = skuCount;
	}

	public List<OrderAddressItem> getOrderAddressItems() {
		return orderAddressItems;
	}

	public void setOrderAddressItems(List<OrderAddressItem> orderAddressItems) {
		this.orderAddressItems = orderAddressItems;
	}

	public OrderHead getOrderHead() {
		return orderHead;
	}

	public void setOrderHead(OrderHead orderHead) {
		this.orderHead = orderHead;
	}

	public Express getExpress() {
		return express;
	}

	public void setExpress(Express express) {
		this.express = express;
	}

	public ExpressMessage getExpressMessage() {
		return expressMessage;
	}

	public void setExpressMessage(ExpressMessage expressMessage) {
		this.expressMessage = expressMessage;
	}

	public Integer getUserAddressId() {
		return userAddressId;
	}

	public void setUserAddressId(Integer userAddressId) {
		this.userAddressId = userAddressId;
	}

	
	public boolean isCanRefund() {
		return isCanRefund;
	}

	public void setCanRefund(boolean isCanRefund) {
		this.isCanRefund = isCanRefund;
	}

	public String getRefundStatusInfo() {
		return refundStatusInfo;
	}

	public void setRefundStatusInfo(String refundStatusInfo) {
		this.refundStatusInfo = refundStatusInfo;
	}

}
