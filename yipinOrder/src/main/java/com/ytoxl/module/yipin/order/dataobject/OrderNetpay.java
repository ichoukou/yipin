package com.ytoxl.module.yipin.order.dataobject;

import com.ytoxl.module.yipin.order.dataobject.tbl.OrderNetpayTbl;

public class OrderNetpay extends OrderNetpayTbl {
	//支付类型
	/**支付宝 value:1**/
	public static final Short PAY_TYPE_ALIPAY=1;
	//支付状态
	/**未支付 value:1**/
	public static final Short PAY_STATUS_ERROR=1;
	/**支付成功 value:2**/
	public static final Short PAY_STATUS_SUCCESS=2;
	
	public static final Short STATUS_PAYED=1;    //1=已付款
	public static final Short STATUS_WAITREFUND=2;    //2=待退款
	public static final Short STATUS_REFUND=3;    //3=已退款
	
	
	public static final Short DOCUMENTTYPE_RECEIPT=1;    //单据类型：1=收款，
	public static final Short DOCUMENTTYPE_REFUND=2;    //2=退款
	
	public static final Short HASINVOICE_NOTASK=0;    //是否需要发票，0=不要，
	public static final Short HASINVOICE_ASK=1;    //1=要
	
	
}
