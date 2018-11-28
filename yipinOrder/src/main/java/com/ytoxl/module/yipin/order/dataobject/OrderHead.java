package com.ytoxl.module.yipin.order.dataobject;

import java.util.Date;
import java.util.List;

import com.ytoxl.module.yipin.base.dataobject.Product;
import com.ytoxl.module.yipin.order.dataobject.tbl.OrderHeadTbl;

public class OrderHead extends OrderHeadTbl {
	
	public static final Short STATUS_WAITPAY=1;      //1=待付款
	public static final Short STATUS_WAITSEND=2;    //2=待发货
	public static final Short STATUS_SEND=3;    	//3=已发货（默认15天后自动完成，定时器跑任务）
	public static final Short STATUS_FINISHED=4;    //4=已完成
	public static final Short STATUS_CANCEL=5;    	//5=已取消
	public static final Short STATUS_RETURN=6;      //6=已退货
	public static final Short[] STATUSES=new Short[]{STATUS_WAITPAY,STATUS_WAITSEND,STATUS_SEND,STATUS_FINISHED,STATUS_CANCEL,STATUS_RETURN};
	
	//支付状态
	/**未支付 value:1**/
	public static final Short PAY_STATUS_NO = 1;
	/**已支付 value:2**/
	public static final Short PAY_STATUS_YES = 2;
	
	//订单使用类型
	/**自用 value：1**/
	public static final Short ORDER_TYPE_MYSELF = 1;
	/**送给他人 value：2**/
	public static final Short ORDER_TYPE_OTHER =  2;
	
	//订单来源类型
	/**普通订单value:1**/
	public static final Short ORDER_SOURCE_GENERAL = Product.SELLTYPE_XS;
	/**预售订单value:2**/
	public static final Short ORDER_SOURCE_BOOK = Product.SELLTYPE_YS;
	/**抢购订单value:3**/
	public static final Short ORDER_SOURCE_GRAP = Product.SELLTYPE_QG;
	public static final Short[] SOURCESTATUSES = new Short[]{ORDER_SOURCE_GENERAL,ORDER_SOURCE_BOOK,ORDER_SOURCE_GRAP};
	
	//逻辑删除订单状态
	public static final Short DELETE_NO = 1;
	public static final Short DELETE_YES = 0;
	
	public static final Short ALL_TIME = 0; //所有时间订单
	public static final Short THE_LAST_MONTH = 1; //最近一个月
	public static final Short A_MONTH_AGO = 2;    //一个月之前
	public static final Short[] ORDER_TIME_TYPES = new Short[]{ALL_TIME,THE_LAST_MONTH,A_MONTH_AGO};

	public static final Short HAS_INVOICE_NO = 1;  //不要发票
	public static final Short HAS_INVOICE_YES = 2; //要发票
	public static final Short[] INVOICESTATUS = new Short[]{HAS_INVOICE_NO,HAS_INVOICE_YES};
	
	public static final Short defaultOrderCancelTime = 24; //订单超过24小时未付款，将自动取消订单
	public static final Short grapOrderCancelTime = 40;		//抢购订单超过40分钟未付款，将自动取消订单
	
	private Integer packageCount; 	// 包裹数量
	private Integer skuCount; 		// 购买sku的总数量
	private String deliveryAddress;
	
	private List<OrderItem> items; //订单明细
	private List<OrderAddress> addressItems; //订单收货人信息
	private OrderAddress orderAddress;
	private OrderCancel orderCancel;
	private OrderNetpay orderPay;
	private OrderInvoiceAddress orderInvoiceAddress; // 发票物流信息
	private List<OrderRefund> orderRefunds; //订单退货信息列表
	private boolean isDisplayOrderCancel;   //显示是否可以退款
	private Date predictSendTime;			//预计发货时间
	private String refundStatus; 			//订单退货状态
	
	public Integer getPackageCount() {
		return packageCount;
	}

	public void setPackageNum(Integer packageCount) {
		this.packageCount = packageCount;
	}

	public Integer getSkuCount() {
		return skuCount;
	}

	public void setSkuCount(Integer skuCount) {
		this.skuCount = skuCount;
	}
	
	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	
	public List<OrderAddress> getAddressItems() {
		return addressItems;
	}

	public void setAddressItems(List<OrderAddress> addressItems) {
		this.addressItems = addressItems;
	}
	
	public OrderAddress getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(OrderAddress orderAddress) {
		this.orderAddress = orderAddress;
	}
	
	public OrderCancel getOrderCancel() {
		return orderCancel;
	}

	public void setOrderCancel(OrderCancel orderCancel) {
		this.orderCancel = orderCancel;
	}

	public OrderNetpay getOrderPay() {
		return orderPay;
	}

	public void setOrderPay(OrderNetpay orderPay) {
		this.orderPay = orderPay;
	}
	
	public OrderInvoiceAddress getOrderInvoiceAddress() {
		return orderInvoiceAddress;
	}

	public void setOrderInvoiceAddress(OrderInvoiceAddress orderInvoiceAddress) {
		this.orderInvoiceAddress = orderInvoiceAddress;
	}
	
	public List<OrderRefund> getOrderRefunds() {
		return orderRefunds;
	}

	public void setOrderRefunds(List<OrderRefund> orderRefunds) {
		this.orderRefunds = orderRefunds;
	}

	public boolean isDisplayOrderCancel() {
		return isDisplayOrderCancel;
	}

	public void setDisplayOrderCancel(boolean isDisplayOrderCancel) {
		this.isDisplayOrderCancel = isDisplayOrderCancel;
	}
	
	public Date getPredictSendTime() {
		return predictSendTime;
	}

	public void setPredictSendTime(Date predictSendTime) {
		this.predictSendTime = predictSendTime;
	}
	
	public String getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}

	@Override
	public int hashCode() {
		return this.orderId;
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (obj instanceof OrderHead) {
			return this.orderId.equals(((OrderHead) obj).orderId);
		}
		return false;
	}

	@Override
	public String toString() {
		return "OrderHead [orderId=" + orderId + ", orderNo=" + orderNo + "]";
	}

}
