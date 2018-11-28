package com.ytoxl.module.yipin.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.ytoxl.module.core.common.persistence.BaseSqlMapper;
import com.ytoxl.module.yipin.order.dataobject.OrderAddressItem;


public interface OrderAddressItemMapper<T extends OrderAddressItem> extends BaseSqlMapper<T> {
	
	/**
	 * 通过orderAddressId 查找包裹中商品的明细
	 * @param orderAddressId
	 * @return
	 * @throws DataAccessException
	 */
	public List<OrderAddressItem> listOrderAddressItemByOrderAddressId(Integer orderAddressId) throws DataAccessException;
	
	/**
	 * 通过orderId和OrderAddressId查询商品明细信息
	 * @param orderId
	 * @param orderAddressId
	 * @return
	 * @throws DataAccessException
	 */
	public List<OrderAddressItem> listOrderAddressItemByOrderIdAndOrderAddressId(@Param("orderId")Integer orderId,@Param("orderAddressId")Integer orderAddressId) throws DataAccessException;
	
	/**
	 * 通过退货id 获取订单明细的商家id
	 * @param orderRefundId
	 * @return
	 * @throws DataAccessException
	 */
	public List<OrderAddressItem> listOrderAddressItemByOrderRefundId(Integer orderRefundId) throws DataAccessException;
	
	
	
	
}
