package com.ytoxl.module.yipin.order.dataobject.resultmap;

import java.util.List;

import com.ytoxl.module.yipin.order.dataobject.OrderAddress;
import com.ytoxl.module.yipin.order.dataobject.OrderItem;
import com.ytoxl.module.yipin.order.dataobject.OrderRefund;
import com.ytoxl.module.yipin.order.dataobject.OrderRefundPayment;

/**
 * 退货申请详细
 * 
 * @author huangtianfu
 * 
 */
public class OrderRefundModel extends OrderRefund {

	// 地址信息
	private OrderAddress orderAddress;

	// 商品信息
	private List<OrderItem> orderItems;

	// 退款信息
	private OrderRefundPayment orderRefundPayment;

	public OrderAddress getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(OrderAddress orderAddress) {
		this.orderAddress = orderAddress;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public OrderRefundPayment getOrderRefundPayment() {
		return orderRefundPayment;
	}

	public void setOrderRefundPayment(OrderRefundPayment orderRefundPayment) {
		this.orderRefundPayment = orderRefundPayment;
	}

}
