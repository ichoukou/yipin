package com.ytoxl.module.yipin.order.mapper;

import org.springframework.dao.DataAccessException;

import com.ytoxl.module.core.common.persistence.BaseSqlMapper;
import com.ytoxl.module.yipin.order.dataobject.OrderRefundExpress;

public interface OrderRefundExpressMapper<T extends OrderRefundExpress> extends BaseSqlMapper<T> {
	
	/**
	 * 通过orderReundId获取退货的快递信息 
	 * @return
	 * @throws DataAccessException
	 */
	public OrderRefundExpress getOrderRefundExpressByOrderRefundId(Integer orderRefundId) throws DataAccessException;

}
