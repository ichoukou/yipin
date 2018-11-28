package com.ytoxl.module.yipin.order.dataobject;

import com.ytoxl.module.yipin.order.dataobject.tbl.OrderRefundTbl;

public class OrderRefund extends OrderRefundTbl {

	public static final Short STATUS_ALL = 0;			//全部
	public static final Short STATUS_NOTAUDIT = 1;      //1=未审核
	public static final Short STATUS_ACCEPT = 2;    	//2=审核通过
	public static final Short STATUS_REFUSEED = 3;      //3=审核不通过
	public static final Short STATUS_RESEND = 4;		//买家已经发货
	public static final Short STATUS_TAKE_GOODS = 5;    //商家已经收货
	public static final Short STATUS_FINISH = 6;		//退货已经完成
	public static final Short[] ITEM_STATUSES=new Short[]{STATUS_ALL,STATUS_NOTAUDIT,STATUS_ACCEPT,STATUS_REFUSEED,STATUS_RESEND,STATUS_TAKE_GOODS,STATUS_FINISH};
	
	/****** 专为订单导出使用 *****/
	public static final Short RETURN_STATUS_NOTAUDIT = 1;	//退货待审核
	public static final Short RETURN_STATUS_WAIT_GOODS = 2;	//待退货
	public static final Short RETURN_STATUS_WAIT_RPAY = 3;	//待退款
	public static final Short RETURN_STATUS_REJECT = 4;		//退货审核未通过
	public static final Short RETURN_STATUS_FINISH = 5;		//退货完成

	public static final Short orderRefundDefaultTime = 15;  //退货时间    发货后15天可以退货
	
	private OrderHead orderHead; 					//退货订单对应的订单头信息
	private OrderItem orderItem;					//唯一对应的订单项
	private OrderRefundExpress refundExpress; 		//退货的物流信息
	private OrderRefundPayment refundPayment;		// 退款信息
	private String refundProductNames; 				//退货商品的名称 用, 隔开

	public OrderHead getOrderHead() {
		return orderHead;
	}

	public void setOrderHead(OrderHead orderHead) {
		this.orderHead = orderHead;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}

	public OrderRefundExpress getRefundExpress() {
		return refundExpress;
	}

	public void setRefundExpress(OrderRefundExpress refundExpress) {
		this.refundExpress = refundExpress;
	}

	public OrderRefundPayment getRefundPayment() {
		return refundPayment;
	}

	public void setRefundPayment(OrderRefundPayment refundPayment) {
		this.refundPayment = refundPayment;
	}

	public String getRefundProductNames() {
		return refundProductNames;
	}

	public void setRefundProductNames(String refundProductNames) {
		this.refundProductNames = refundProductNames;
	}
}
