package com.ytoxl.module.yipin.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.order.dataobject.OrderCrm;
import com.ytoxl.module.yipin.order.mapper.OrderCrmMapper;
import com.ytoxl.module.yipin.order.service.OrderCrmService;

@Service
public class OrderCrmServiceImpl implements OrderCrmService {
	
	@Autowired
	private OrderCrmMapper<OrderCrm> orderCrmMapper;
	
	@Override
	public List<OrderCrm> listOrderCrmByOrderId(Integer orderId) throws YiPinStoreException {
		List<OrderCrm> orderCrmList = orderCrmMapper.listOrderCrmByOrderId(orderId);
		return orderCrmList;
	}

	@Override
	public Integer addOrderCrm(OrderCrm orderCrm) throws YiPinStoreException {
		return orderCrmMapper.add(orderCrm);
	}
}
