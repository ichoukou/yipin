package com.ytoxl.module.yipin.order.service;

import java.util.List;

import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.order.dataobject.OrderCrm;

public interface OrderCrmService {
	
	/**
	 * 根据订单号查询客服信息
	 * @param orderId
	 * @return
	 * @throws YiPinStoreException
	 */
	public List<OrderCrm> listOrderCrmByOrderId(Integer orderId) throws YiPinStoreException;
	
	/**
	 * 添加客服记录
	 * @param orderCrm
	 * @return
	 * @throws YiPinStoreException
	 */
	public Integer addOrderCrm(OrderCrm orderCrm) throws YiPinStoreException;
	
} 
