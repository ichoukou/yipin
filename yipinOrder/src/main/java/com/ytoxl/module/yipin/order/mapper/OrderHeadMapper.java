package com.ytoxl.module.yipin.order.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.ytoxl.module.core.common.persistence.BaseSqlMapper;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.order.dataobject.OrderHead;
import com.ytoxl.module.yipin.order.dataobject.resultmap.OrderExportModel;

public interface OrderHeadMapper<T extends OrderHead> extends BaseSqlMapper<T> {
	
	/**
	 * 我的订单分页
	 * @param map
	 * @return
	 * @throws DataAccessException
	 */
	public List<OrderHead> searchOrder4Front(Map<String,Object> map) throws DataAccessException;
	public Integer searchOrder4FrontCount(Map<String,Object> map)throws DataAccessException;
	
	/**
	 * 后台订单分页
	 * @param map
	 * @throws DataAccessException
	 */
	public List<OrderHead> searchOrders4Manager(Map<String,Object> map) throws DataAccessException;
	public Integer searchOrders4ManagerCount(Map<String,Object> map) throws DataAccessException;

	/**
	 * 根据订单id获取订单信息
	 * @param orderId
	 * @return
	 * @throws DataAccessException
	 */
	public OrderHead getOrderById(Integer orderId)  throws DataAccessException;
	
	/**
	 * 查询要导出的订单信息(非退货订单)
	 * @param map
	 * @return
	 */
	public List<OrderExportModel> listOrders(Map<String,Object> map) throws DataAccessException;
	
	/**
	 * 查询要导出的订单信息(退货订单)
	 * @param map
	 * @return
	 */
	public List<OrderExportModel> listOrders4Return(Map<String,Object> map) throws DataAccessException;
	
	/**
	 * 根据订单id和当前用户id查找订单
	 * @param map
	 * @return 
	 * @throws DataAccessException
	 */
	public OrderHead findOrder(Map<String,Object> map) throws DataAccessException;
	
	/**
	 * 根据订单id更新订单表订单状态
	 * @param map
	 * @return
	 * @throws DataAccessException
	 */
	public Integer updateOrderStatus(Map<String,Object> map) throws DataAccessException;

	
	/**
	 * 支付完成后，更新订单的相关信息(次方法只供支付调用)
	 * @param orderId
	 * @throws DataAccessException
	 */
	public void updateOrderByNetpay(OrderHead orderHead) throws DataAccessException;
	/**
	 * 更新订单支付ip
	 * @param orderHead
	 * @throws DataAccessException
	 */
	public void updateOrderPayAddress(OrderHead orderHead) throws DataAccessException;
	
	/**
	 * 通过orderAddressId 查询订单头信息
	 * @param orderAddressId
	 * @return
	 * @throws DataAccessException
	 */
	public OrderHead getOrderHeadByOrderAddressId(Integer orderAddressId) throws DataAccessException;
	
	/**
	 * 查询订单表中所有24小时内未付款的订单Id
	 * @return
	 * @throws DataAccessException
	 */
	public List<Integer> listNotPayOrders(Map<String,Object> map) throws DataAccessException;
	
	/**
	 * 查询订单已发货达到或超过15天还未收货的所有订单
	 * @return
	 * @throws DataAccessException
	 */
	public List<Integer> listNotReceiveOrders() throws DataAccessException;
	
	/**
	 * 取消订单
	 * @param orderId
	 * @return
	 * @throws DataAccessException
	 */
	public Integer updateCancelOrderByOrderId(Integer orderId) throws DataAccessException;
	
	/**
	 * 逻辑删除 待付款的订单
	 * @param orderId
	 * @return
	 * @throws DataAccessException
	 */
	public Integer updateDelOrderByOrderId(Integer orderId) throws DataAccessException;

	/**
	 * 根据用户id和订单状态查询的订单的数量
	 * @param userId
	 * @param status
	 * @return
	 * @throws YiPinStoreException
	 */
	public Integer getOrderNumByStatusAndUserId(@Param("userId")Integer userId,@Param("status")Short status) throws YiPinStoreException;

	/**
	 * 查询订单表中所有40分钟内未付款的促销订单Id
	 * @return
	 * @throws DataAccessException
	 */
	public List<Integer> listNotPayGrapOrders(Map<String,Object> map) throws DataAccessException;
	
	/**
	 * 根据订单id和userId查询新的信息包括物流信息
	 * @param userId
	 * @param orderId
	 * @return
	 * @throws DataAccessException
	 */
	public OrderHead getOrderInfoByOrderIdAndUserId(@Param("userId")Integer userId,@Param("orderId")Integer orderId) throws DataAccessException;

	/**
	 * 根据orderIds集合批量更新订单状态
	 * @param orderIds
	 * @param status
	 * @return
	 * @throws DataAccessException
	 */
	public Integer updateBathOrderStatus(@Param("orderIds") List<Integer> orderIds,@Param("status") Short status) throws DataAccessException;
	
	/**
	 * 前台我的退款管理
	 * @param map
	 * @return
	 * @throws DataAccessException
	 */
	public List<OrderHead> searchCancelOrder4Front(Map<String,Object> map) throws DataAccessException;
	public Integer searchCancelOrder4FrontCount(Map<String,Object> map) throws DataAccessException;
	
}
