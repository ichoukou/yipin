package com.ytoxl.module.yipin.order.dataobject;

import com.ytoxl.module.yipin.order.dataobject.tbl.OrderCancelTbl;

public class OrderCancel extends OrderCancelTbl {

	public static final Short STATUS_ALL = 0;			//	全部
	public static final Short STATUS_AUDIT = 1;     	//	1=退款审核中
	public static final Short STATUS_NOTAUDIT = 2;  	//	2=退款未通过
	public static final Short STATUS_THROUGH = 3;   	//	3=待退款
	public static final Short STATUS_FINISH = 4;	 	//	4=已退款
	public static final Short[] CANCEL_STATUSES = new Short[]{STATUS_ALL,STATUS_AUDIT,STATUS_NOTAUDIT,STATUS_THROUGH,STATUS_FINISH};
	
	protected OrderHead orderHead; 		//订单待发货退款的订单头信息

	public OrderHead getOrderHead() {
		return orderHead;
	}

	public void setOrderHead(OrderHead orderHead) {
		this.orderHead = orderHead;
	}
	
}
