package com.ytoxl.module.yipin.order.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import com.ytoxl.module.core.common.persistence.BaseSqlMapper;
import com.ytoxl.module.yipin.order.dataobject.OrderInvoiceAddress;

public interface OrderInvoiceAddressMapper<T extends OrderInvoiceAddress> extends BaseSqlMapper<T> {
	
	/**
	 * 根据orderId查询发票的物流信息
	 * @param orderId
	 * @return
	 * @throws DataAccessException
	 */
	public OrderInvoiceAddress getOrderInvoiceAddressByOrderId(Integer orderId)throws DataAccessException;

	/**
	 * 发票管理 - 查询记录总数
	 * @param orderId
	 * @return
	 * @throws DataAccessException
	 */
	public Integer searchOrderInvoices4ManagerCount(Map<String, Object> searchParams)throws DataAccessException;
	
	/**
	 * 发票管理 - 查询记录
	 * @param orderId
	 * @return
	 * @throws DataAccessException
	 */
	public List<OrderInvoiceAddress> searchOrderInvoices4Manager(Map<String, Object> searchParams)throws DataAccessException;

	/**
	 * 更新订单发票快递信息
	 * @param orderInvoiceAddress
	 * @throws DataAccessException
	 */
	public void updateOrderInvoiceAddressById(OrderInvoiceAddress orderInvoiceAddress)throws DataAccessException;
	
	/**
	 * 根据orderId 查询订单和发票详细信息
	 * @param orderId
	 * @return
	 * @throws DataAccessException
	 */
	public OrderInvoiceAddress getOrderInvoiceDetailByOrderId(Integer orderId)throws DataAccessException;

	/**
	 * 根据expressId和expressNo查询是否存在重复快递单号
	 * @param orderAddressId
	 * @return
	 * @throws DataAccessException
	 */
	public Integer getExpressNOCountByExpressIdAndExpressNo(@Param("expressId")Integer expressId,@Param("expressNo")String expressNo) throws DataAccessException;
	
}
