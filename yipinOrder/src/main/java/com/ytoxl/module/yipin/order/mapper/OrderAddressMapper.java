package com.ytoxl.module.yipin.order.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.ytoxl.module.core.common.persistence.BaseSqlMapper;
import com.ytoxl.module.yipin.order.dataobject.OrderAddress;

public interface OrderAddressMapper<T extends OrderAddress> extends BaseSqlMapper<T>{
	
	/**
	 * 通过orderId查询包裹总数量
	 * @param orderId
	 * @return
	 * @throws DataAccessException
	 */
	public Integer getOrderPackageNumByOrderId(Integer orderId) throws DataAccessException;
	
	/**
	 * 通过orderId查找包裹明细
	 * @param orderId
	 * @return
	 * @throws DataAccessException
	 */
	public List<OrderAddress> listOrderPackagesByOrderId(Integer orderId) throws DataAccessException;

	/**
	 * 根据订单id获取收货人信息
	 * @param orderId
	 * @return
	 * @throws DataAccessException
	 */
	public List<OrderAddress> listOrderAddressByOrderId(Integer orderId) throws DataAccessException;
	
	/**
	 * 根据包裹id查询包裹信息
	 * @param orderAddressId
	 * @return
	 * @throws DataAccessException
	 */
	public OrderAddress getOrderAddressByOrderAddressId(Integer orderAddressId) throws DataAccessException;

	/**
	 * 根据订单id和包裹号查询包裹信息
	 * @param orderId
	 * @param packageNo
	 * @return
	 * @throws DataAccessException
	 */
	public OrderAddress getOrderAddressByOrderPackageNo(Map<String,Object> map) throws DataAccessException;
	
	/**
	 * 更新包裹快递信息
	 * @param orderExpress
	 * @throws DataAccessException
	 */
	public void updateOrderAddressByOrderAddressId(OrderAddress orderAddress) throws DataAccessException;
	
	
	/**
	 * 根据订单id查找未发货包裹数量
	 * @param orderId
	 * @throws DataAccessException
	 */
	public Integer countNotSendProductPackageNum(Integer orderId)  throws DataAccessException;
	
	/**
	 * 根据订单id更新包裹状态信息
	 * @param map
	 * @return
	 * @throws DataAccessException
	 */
	public Integer updateOrderPackageStatus(Map<String,Object> map) throws DataAccessException;
	
	/**
	 * 根据orderId查询包裹数量
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public Integer countProductPackageNumByOrderId(Integer orderId) throws DataAccessException;
	
	/**
	 * 根据orderId查询已经收货的包裹数量
	 * @param orderId
	 * @return
	 * @throws DataAccessException
	 */
	public Integer countReceiveProductPackageNumByOrderId(Integer orderId)throws DataAccessException;
	
	/**
	 * 根据orderAddressId 更新包裹收获状态
	 * @param orderAddressId
	 * @return
	 * @throws DataAccessException
	 */
	public Integer updateOrderPackageStatusByOrderAdderssId(@Param("orderAddressId")Integer orderAddressId,@Param("status")Short status) throws DataAccessException;
	
	/**
	 * 根据包裹发货时间，查询超过15天的包裹
	 * @return
	 * @throws DataAccessException
	 */
	public List<OrderAddress> listOrderPackagesBySendTime(Integer limit) throws DataAccessException;
	
	/**
	 * 根据订单id统计未收货的包裹数
	 * @param orderId
	 * @return
	 * @throws DataAccessException
	 */
	public Integer countNotReceivePackageNumByOrderId(Integer orderId) throws DataAccessException;
	
	/**
	 * 根据expressId和expressNo查询是否存在重复快递单号
	 * @param orderAddressId
	 * @return
	 * @throws DataAccessException
	 */
	public Integer getExpressNOCountByExpressIdAndExpressNo(@Param("expressId")Integer expressId,@Param("expressNo")String expressNo) throws DataAccessException;
	
	/**
	 * 根据订单Id查询订单收货人信息
	 * @param map
	 * @return
	 * @throws DataAccessException
	 */
	public OrderAddress getOrderAddressByOrderId(Integer orderId) throws DataAccessException;
	
	/**
	 * 根据orderAddressId 批量跟新包裹状态
	 * @param orderAddressIds
	 * @param status
	 * @return
	 * @throws DataAccessException
	 */
	public Integer updateBathOrderPacekageStatus(@Param("orderAddressIds") List<Integer> orderAddressIds,@Param("status") Short status)throws DataAccessException;
}