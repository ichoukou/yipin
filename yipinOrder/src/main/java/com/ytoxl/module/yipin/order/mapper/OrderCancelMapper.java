package com.ytoxl.module.yipin.order.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ytoxl.module.core.common.persistence.BaseSqlMapper;
import com.ytoxl.module.yipin.order.dataobject.OrderCancel;
import com.ytoxl.module.yipin.order.dataobject.OrderHead;

public interface OrderCancelMapper<T extends OrderCancel> extends BaseSqlMapper<T> {
	
	/**
	 * 通过orderId查找退款明细
	 * @param orderId
	 * @return
	 * @throws DataAccessException
	 */
	public OrderCancel getOrderCancelByOrderId(Integer orderId) throws DataAccessException;
	
	/**
	 * 更新待发货 - 退款申请状态
	 * 
	 * @param orderCancel
	 * @return
	 */
	public Integer updateStatus(OrderCancel orderCancel) throws DataAccessException;
	
	/**
	 * 后台 查询 待发货 - 退款订单记录
	 * 
	 * @param map
	 * @return
	 * @throws DataAccessException
	 */
	public List<OrderHead> searchOrdersCancel4Manager(Map<String, Object> map)throws DataAccessException;
	public Integer searchOrdersCancel4ManagerCount(Map<String, Object> map) throws DataAccessException;
	
	/**
	 * 前台我的退款记录
	 * @param map
	 * @return
	 * @throws DataAccessException
	 */
	public List<OrderCancel> searchCancelOrderRecords4Front(Map<String,Object> map) throws DataAccessException;
	public Integer searchCancelOrderRecords4FrontCount(Map<String,Object> map) throws DataAccessException;
	
}
