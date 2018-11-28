package com.ytoxl.module.yipin.order.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.ytoxl.module.core.common.persistence.BaseSqlMapper;
import com.ytoxl.module.yipin.order.dataobject.OrderHead;
import com.ytoxl.module.yipin.order.dataobject.OrderRefund;
import com.ytoxl.module.yipin.order.dataobject.resultmap.OrderRefundStaModel;

public interface OrderRefundMapper<T extends OrderRefund> extends BaseSqlMapper<T> {

	/**
	 * 查询退货申请列表
	 * 
	 * @param params
	 * @return
	 */
	public List<OrderRefund> listOrderRefund(Map<String, String> params) throws DataAccessException;

	/**
	 * 更新退货申请状态
	 * 
	 * @param orderRefund
	 * @return
	 */
	public int updateStatus(OrderRefund orderRefund) throws DataAccessException;

	/**
	 * 分页查询我的退货订单总数
	 * 
	 * @param map
	 * @return
	 * @throws DataAccessException
	 */
	public Integer searchOrdersRefund4ManagerCount(Map<String, Object> map)throws DataAccessException;

	/**
	 * 分页查询我的退货订单
	 * 
	 * @param map
	 * @return
	 * @throws DataAccessException
	 */
	public List<OrderHead> searchOrdersRefund4Manager(Map<String, Object> map)throws DataAccessException;
			
	/**
	 * 查询退货订单的退货信息列表
	 * 
	 * @param orderId
	 * @return
	 */
	public List<OrderRefund> listOrderRefundByOrderId(Integer orderId)throws DataAccessException;
	
	/**
	 * 根据订单id和包裹地址id查询退货表中的退货状态
	 * @param orderId
	 * @param orderAddressId
	 * @return
	 * @throws DataAccessException
	 */
	public OrderRefundStaModel listOrderReturnByAddressId(@Param("orderId") Integer orderId, @Param("orderAddressId") Integer orderAddressId)throws DataAccessException;
	
	/**
	 * 前台我的退货管理
	 * @param map
	 * @return
	 * @throws DataAccessException
	 */
	public List<OrderHead> searchRefundOrder4Front(Map<String,Object> map) throws DataAccessException;
	public Integer searchRefundOrder4FrontCount(Map<String,Object> map) throws DataAccessException;
	
	/**
	 * 通过orderAddressId 查询包裹退货信息
	 * @param orderAddressId
	 * @return
	 * @throws DataAccessException
	 */
	public OrderRefund getOrderRefundByOrderAddressId(Integer orderAddressId) throws DataAccessException;
	
	/**
	 * 通过orderItemId 查询包裹退货信息 
	 * @param orderItemId
	 * @return
	 * @throws DataAccessException
	 */
	public OrderRefund getOrderRefundByOrderItemId(Integer orderItemId)throws DataAccessException;
	
	/**
	 * 前台我的退货记录
	 * @param map
	 * @return
	 * @throws DataAccessException
	 */
	public List<OrderRefund> searchRefundRecords4Front(Map<String,Object> map) throws DataAccessException;
	public Integer searchRefundRecords4FrontCount(Map<String,Object> map) throws DataAccessException;
	
	/**
	 * 根据orderId 查询退货的包裹数量
	 * @param orderId
	 * @return
	 * @throws DataAccessException
	 */
	public Integer countRefundPackageNumByorderId(Integer orderId) throws DataAccessException;
	
	/**
	 * 根据orderId 查询退货的订单项数量
	 * @param orderId
	 * @return
	 * @throws DataAccessException
	 */
	public Integer countRefundOrderItemNumByorderId(Integer orderId) throws DataAccessException;
}
