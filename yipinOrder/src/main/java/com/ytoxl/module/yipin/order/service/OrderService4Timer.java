package com.ytoxl.module.yipin.order.service;

import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;

public interface OrderService4Timer {

	/**
	 * 更改普通订单状态为取消
	 * @return
	 * @throws UhomeStoreException
	 */
	public Integer updateStatusToCanceled() throws YiPinStoreException;
	
	/**
	 * 更改订单状态为已完成
	 * @return
	 * @throws UhomeStoreException
	 */
	public Integer updateStatusToFinished() throws YiPinStoreException;
	
	/**
	 * 更改促销订单状态为取消
	 * @return
	 * @throws YiPinStoreException
	 */
	public Integer updateGrapStatusToCancel() throws YiPinStoreException;
}
