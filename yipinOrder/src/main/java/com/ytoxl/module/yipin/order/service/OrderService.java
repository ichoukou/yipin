package com.ytoxl.module.yipin.order.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ytoxl.module.core.common.pagination.BasePagination;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.UserInfo;
import com.ytoxl.module.yipin.base.dataobject.resultmap.ExpressMessage;
import com.ytoxl.module.yipin.order.dataobject.OrderAddress;
import com.ytoxl.module.yipin.order.dataobject.OrderCancel;
import com.ytoxl.module.yipin.order.dataobject.OrderHead;
import com.ytoxl.module.yipin.order.dataobject.OrderInvoiceAddress;
import com.ytoxl.module.yipin.order.dataobject.OrderItem;
import com.ytoxl.module.yipin.order.dataobject.OrderRefund;
import com.ytoxl.module.yipin.order.dataobject.OrderRefundExpress;
import com.ytoxl.module.yipin.order.dataobject.resultmap.OrderCheckModel;
import com.ytoxl.module.yipin.order.dataobject.resultmap.ShoppingCart;

public interface OrderService {
	
	/**
	 * 添加商品到购物车或者从购物车中删除商品
	 * @param orders
	 * @param shoppingCart
	 * @throws YiPinStoreException
	 */
	public ShoppingCart addOrDelProductToCart(ShoppingCart shoppingCart,Map<String,String> params)throws YiPinStoreException ;
	
	/**
	 * 结算页面调整商品
	 * @param request
	 * @param response
	 * @param orders
	 * @param shoppingCart
	 * @throws YiPinStoreException
	 */
	public OrderCheckModel rectifyProductCount(ShoppingCart shoppingCart,Map<String,String> params)throws YiPinStoreException;
	
	/**
	 * 将购物车cookies转成换成购物车实体,如果没有商品返回null
	 * @param request
	 * @param response
	 * @return
	 * @throws YiPinStoreException
	 */
	public ShoppingCart getShoppingCart(HttpServletRequest request,HttpServletResponse response) throws YiPinStoreException;
	
	/**
	 * 显示购物车
	 * @param orders
	 * @param shoppingCart
	 * @throws YiPinStoreException
	 */
	public void showCart(List<OrderHead> orders,ShoppingCart shoppingCart) throws YiPinStoreException;
	
	/**
	 * 根据cookie存储的商品信息设置订单信息
	 * @param orders
	 * @param shoppingCart
	 * @throws YiPinStoreException
	 */
	public void setOrdersInfo(List<OrderHead> orders) throws YiPinStoreException;
	
	/**
	 * 检车商品库存，是否可售
	 * @param orders
	 * @return
	 * @throws YiPinStoreException
	 */
	public List<OrderCheckModel> checkOrderProductInfo(List<OrderHead> orders) throws YiPinStoreException;
	
	/**
	 * 检查抢购商品状态
	 * @param params
	 * @return
	 * @throws YiPinStoreException
	 */
	public void checkPanicBuyProductInfo(List<OrderHead> orders,Map<String,String> params) throws YiPinStoreException;
	/**
	 * 生成订单
	 * @param orders
	 * @throws YiPinStoreException
	 */
	public String addOrders(HttpServletRequest request,List<OrderHead> orders,Map<String,String> params) throws YiPinStoreException;
	
	/**
	 * 订单生成成功后，从购物车删除已购买的商品
	 * @param orderHead
	 * @param shoppingCart
	 * @throws YiPinStoreException
	 */
	public void deleteHaveBuyProduct(List<OrderHead> orders,ShoppingCart shoppingCart) throws YiPinStoreException;
	
	/**
	 * 前台我的订单分页
	 * @param orderPage
	 * @throws YiPinStoreException
	 */
	public void searchOrder4Front(BasePagination<OrderHead> orderPage)throws YiPinStoreException;
	
	/**
	 * 根据订单id获取包裹明细
	 * @param orderId
	 * @return
	 * @throws YiPinStoreException
	 */
	public List<OrderAddress> listOrderPackagesByOrderId(Integer orderId) throws YiPinStoreException;

	/**
	 * 根据orderAddressId 查询订单包裹明细 
	 * @param orderAddressId
	 * @return
	 * @throws YiPinStoreException
	 */
	public OrderAddress getOrderAddressByOrderAddressId(Integer orderAddressId)throws YiPinStoreException;
	
	/**
	 * 根据orderAddressId 查询包裹信息 (包括商品明细) 退货订单填写表单信息页面使用
	 * @param orderAddressId
	 * @return
	 * @throws YiPinStoreException
	 */
	public OrderItem getOrderInfoByOrderItemId4RefundForm(Integer orderItemId) throws YiPinStoreException;
	
	/**
	 * 前台我的退货管理
	 * @param orderPage
	 * @throws YiPinStoreException
	 */
	public void searchRefundOrder4Front(BasePagination<OrderHead> orderPage) throws YiPinStoreException;

	/**
	 * 保存退货信息
	 * @param orderRefund
	 * @throws YiPinStoreException
	 */
	public void saveOrderRefundInfo(OrderRefund orderRefund) throws YiPinStoreException;
	
	/**
	 * 前台退货记录
	 * @param refundPage
	 * @throws YiPinStoreException
	 */
	public void searchRefundRecords4Front(BasePagination<OrderRefund> refundPage)throws YiPinStoreException;
	
	/**
	 * 通过orderRefundId 获取所退货物中商家信息
	 * @param orderRefundId
	 * @return
	 * @throws YiPinStoreException
	 */
	public UserInfo getSellerInfoByorderRefundId(Integer orderRefundId) throws YiPinStoreException;
	
	/**
	 * 保存退货快递信息
	 * @param orderRefundExpress
	 * @throws YiPinStoreException
	 */
	public void saveRefundExpressInfo(OrderRefundExpress orderRefundExpress) throws YiPinStoreException;
	
	/**
	 * 通过orderId 获取发票信息包括订单头信息
	 * @param orderId
	 * @return
	 * @throws YiPinStoreException
	 */
	public OrderInvoiceAddress getInvoiceDetailByorderId(Integer orderId) throws YiPinStoreException;
	
	/**
	 * 保存买家确定收货信息
	 * @param orderAddressId
	 * @throws YiPinStoreException
	 */
	public void saveConfirmGoods(Integer orderAddressId)throws YiPinStoreException;
	
	/**
	 * 保存买家取消订单信息
	 * @param orderId
	 * @throws YiPinStoreException
	 */
	public void saveCancelOrder(Integer orderId)throws YiPinStoreException;
	
	/**
	 * 保存 买家 逻辑删除订单
	 * @param orderId
	 * @throws YiPinStoreException
	 */
	public void saveDeleteOrder(Integer orderId)throws YiPinStoreException;
	
	/**
	 * 根据快递公司的expressId 和面单好查询物流信息
	 * @param expressId
	 * @param mailNo
	 * @return
	 * @throws YiPinStoreException
	 */
	public ExpressMessage getExpressMessageByExpressIdAndMailNo(Integer expressId,String mailNo,Integer orderId) throws YiPinStoreException;
	
	/**
	 * 根据用户id和订单状态查询的订单的数量
	 * @param userId
	 * @param status
	 * @return
	 * @throws YiPinStoreException
	 */
	public Integer getOrderNumByStatusAndUserId(Integer userId,Short status) throws YiPinStoreException;
	
	/**
	 * 批量确认收货
	 * @param orderIds
	 * @return
	 * @throws YiPinStoreException
	 */
	public boolean saveBathConfrimReceive(String orderIds)throws YiPinStoreException;
	
	/**
	 * 前台我的退款管理
	 * @param orderPage
	 * @throws YiPinStoreException
	 */
	public void searchCancelOrder4Front(BasePagination<OrderHead> orderPage) throws YiPinStoreException;
	
	/**
	 * 前台我的退款记录
	 * @param orderPage
	 * @throws YiPinStoreException
	 */
	public void searchCancelOrderRecords4Front(BasePagination<OrderCancel> cancelPage) throws YiPinStoreException;
	
	/**
	 * 前台申请退款
	 * @param orderId
	 * @return
	 * @throws YiPinStoreException
	 */
	public OrderHead getOrderInfoByOrderId4OrderCancelForm(Integer orderId) throws YiPinStoreException;
	
	/**
	 * 保存退款信息
	 * @param orderCancel
	 * @throws YiPinStoreException
	 */
	public void saveCancelOrderInfo(OrderCancel orderCancel)throws YiPinStoreException;	
	
}
