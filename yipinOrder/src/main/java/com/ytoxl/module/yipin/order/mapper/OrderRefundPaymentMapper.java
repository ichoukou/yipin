package com.ytoxl.module.yipin.order.mapper;

import org.springframework.dao.DataAccessException;

import com.ytoxl.module.core.common.persistence.BaseSqlMapper;
import com.ytoxl.module.yipin.order.dataobject.OrderRefundPayment;

public interface OrderRefundPaymentMapper<T extends OrderRefundPayment> extends BaseSqlMapper<T> {

	/**
	 * 确认退款
	 * @param orderReturnPayment
	 * @throws DataAccessException
	 */
	 public int updateStatus(OrderRefundPayment orderRefundPayment)throws DataAccessException;

	 /**
	  * 根据退款记录id查询一条数据
	  * @param orderReturnPayment
	  * @throws DataAccessException
	  */
	 public OrderRefundPayment getOrderRefundPaymentByOrderRefundId(Integer orderRefundId)throws DataAccessException;

}
