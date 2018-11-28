package com.ytoxl.module.yipin.order.mapper;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ytoxl.module.core.common.persistence.BaseSqlMapper;
import com.ytoxl.module.yipin.order.dataobject.OrderCrm;

public interface OrderCrmMapper<T extends OrderCrm> extends BaseSqlMapper<T> {
	/**
	 * 根据订单号查询客服信息
	 * @param orderId
	 * @return
	 * @throws DataAccessException
	 */
	public List<OrderCrm> listOrderCrmByOrderId(Integer orderId) throws DataAccessException;
	
	
}
