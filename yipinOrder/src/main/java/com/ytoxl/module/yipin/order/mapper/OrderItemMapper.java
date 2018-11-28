package com.ytoxl.module.yipin.order.mapper;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ytoxl.module.core.common.persistence.BaseSqlMapper;
import com.ytoxl.module.yipin.order.dataobject.OrderItem;

public interface OrderItemMapper<T extends OrderItem> extends BaseSqlMapper<T> {
	
	/**
	 * 通过orderId查询此订单一共有多少个商品
	 * @param orderId
	 * @return
	 * @throws DataAccessException
	 */
	public Integer getOrderProductSkuNumByOrderId(Integer orderId)throws DataAccessException; 

	/**
	 * 根据订单id获取订单明细
	 * @param orderId
	 * @return
	 * @throws DataAccessException
	 */
	public List<OrderItem> listOrderItemsByOrderId(Integer orderId)  throws DataAccessException;

	/**
	 * 根据订单id获得订单商品skuId和商品数量
	 * @param orderId
	 * @return
	 * @throws DataAccessException
	 */
	public List<OrderItem> listOrderItemCancelNum(Integer orderId) throws DataAccessException;
	
	/**
	 * 通过orderItemId查询订单项和订单头信息 
	 * @param orderItemId
	 * @return
	 * @throws DataAccessException
	 */
	public OrderItem getOrderItemInfoByOrderItemId(Integer orderItemId) throws DataAccessException;
	
	/**
	 * 根据orderId查询订单项的数量
	 * @param orderId
	 * @return
	 * @throws DataAccessException
	 */
	public Integer countOrderItemNumByOrderId(Integer orderId)throws DataAccessException;
	
	/**
	 * 根据orderId查询订单项
	 * @param orderId
	 * @return
	 * @throws DataAccessException
	 */
	public List<OrderItem> listOrderItemByOrderId4Simple(Integer orderId)throws DataAccessException;
}
