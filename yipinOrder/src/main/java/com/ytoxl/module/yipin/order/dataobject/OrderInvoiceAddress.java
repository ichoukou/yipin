package com.ytoxl.module.yipin.order.dataobject;

import com.ytoxl.module.yipin.base.dataobject.Express;
import com.ytoxl.module.yipin.base.dataobject.resultmap.ExpressMessage;
import com.ytoxl.module.yipin.order.dataobject.tbl.OrderInvoiceAddressTbl;

public class OrderInvoiceAddress extends OrderInvoiceAddressTbl {
	
	private OrderHead orderHead;
	private Express express;
	private String brandNames;
	private ExpressMessage expressMessage;   //包裹对应的物流信息
	private Integer userAddressId;
	
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

	public String getBrandNames() {
		return brandNames;
	}

	public void setBrandNames(String brandNames) {
		this.brandNames = brandNames;
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
	
}
