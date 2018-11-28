package com.ytoxl.module.yipin.order.dataobject.tbl;

import java.util.Date;

/**
 * 订单退货物流信息
 */
public class OrderRefundExpressTbl {

	// orderRefundExpressId
	protected Integer orderRefundExpressId;
	// orderRefundId
	protected Integer orderRefundId;
	// 退货物流id(expressId)
	protected Integer expressId;
	// 退货物流名称(expressName)
	protected String expressName;
	// 退货物流面单号(expressNo)
	protected String expressNo;
	// createTime
	protected Date createTime;
	// updateTime
	protected Date updateTime;

	public Integer getOrderRefundExpressId() {
		return orderRefundExpressId;
	}

	public void setOrderRefundExpressId(Integer orderRefundExpressId) {
		this.orderRefundExpressId = orderRefundExpressId;
	}

	public Integer getOrderRefundId() {
		return orderRefundId;
	}

	public void setOrderRefundId(Integer orderRefundId) {
		this.orderRefundId = orderRefundId;
	}

	public Integer getExpressId() {
		return expressId;
	}

	public void setExpressId(Integer expressId) {
		this.expressId = expressId;
	}

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
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
