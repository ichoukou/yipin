package com.ytoxl.module.yipin.order.dataobject;

import com.ytoxl.module.yipin.order.dataobject.tbl.OrderRefundPaymentTbl;

public class OrderRefundPayment extends OrderRefundPaymentTbl {

	public static final Short STATUS_WAITREFUND=0;    //0=待退款
	public static final Short STATUS_REFUND=1;    //1=已退款
	
}
