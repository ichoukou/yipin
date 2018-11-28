package com.ytoxl.module.yipin.order.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ytoxl.module.core.common.pagination.BasePagination;
import com.ytoxl.module.user.common.exception.YtoxlUserException;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.order.dataobject.OrderAddress;
import com.ytoxl.module.yipin.order.dataobject.OrderCancel;
import com.ytoxl.module.yipin.order.dataobject.OrderHead;
import com.ytoxl.module.yipin.order.dataobject.OrderInvoiceAddress;
import com.ytoxl.module.yipin.order.dataobject.OrderRefund;
import com.ytoxl.module.yipin.order.dataobject.OrderRefundPayment;
import com.ytoxl.module.yipin.order.dataobject.resultmap.OrderExportModel;

public interface OrderService4Manage {

	/**
	 * 管理员或者买家  后台查询订单
	 * 
	 * @param orderPage
	 * @throws YiPinStoreException
	 */
	public void searchOrders4ManagerOrSeller(BasePagination<OrderHead> orderPage) throws YiPinStoreException;


	/**
	 * 根据id查找订单
	 * 
	 * @param orderId
	 * @throws UhomeStoreException
	 */
	public OrderHead getOrderById(Integer orderId) throws YiPinStoreException;
	 
	/**
	 * 更新退货申请信息
	 * 
	 * @param orderRefund
	 * @return
	 */
	boolean updateStatus(OrderRefund orderRefund) throws YiPinStoreException;

	/**
	 * 查询要导出的订单信息(非退货订单)
	 * 
	 * @param map
	 * @return
	 */
	public List<OrderExportModel> listOrders(Map<String, Object> map)
			throws YiPinStoreException,YtoxlUserException;

	/**
	 * 确认发货
	 * 
	 * @return
	 * @throws UhomeStoreException
	 */
	public String confirmSendProduct(OrderAddress orderExpress)
			throws YiPinStoreException;

	/**
	 * 审核
	 * 
	 * @return
	 * @throws UhomeStoreException
	 */
	public String audit(OrderRefund Refund) throws YiPinStoreException;

	/**
	 * 确认退款
	 * 
	 * @param orderPayment
	 * @return
	 * @throws UhomeStoreException
	 */
	public String confirmPayment(OrderRefundPayment orderRefundPayment)
			throws YiPinStoreException;

	/**
	 * 批量发货
	 * 
	 * @return
	 * @throws UhomeStoreException
	 */
	public void updateBatchUpload(List<String[]> excels, Integer userId)
			throws YiPinStoreException;

	/**
	 * 管理员|卖家  - 发票管理查询
	 * 
	 * @param orderPage
	 * @throws YiPinStoreException
	 */
	public void searchOrderInvoices4ManagerOrSeller(
			BasePagination<OrderInvoiceAddress> orderInvoicePage)
			throws YiPinStoreException;

	/**
	 * 卖家 - 发票确认发货
	 * 
	 * @param orderPage
	 * @throws YiPinStoreException
	 */
	public String confirmSendOrderInvoice(
			OrderInvoiceAddress invoiceExpress) throws YiPinStoreException;

	/**
	 * 卖家- 确认退货
	 * 
	 * @param orderRefund
	 * @return
	 */
	public String confirmBackProduct(Integer orderRefundId, BigDecimal packageTotalPrice) throws YiPinStoreException;

	/**
	 * 卖家- 待发货退款- 审核
	 * 
	 * @param orderRefund
	 * @return
	 */
	public String auditOrderCancel(OrderCancel orderCancel) throws YiPinStoreException;

	/**
	 * 卖家- 待发货退款- 同意退款
	 * 
	 * @param orderRefund
	 * @return
	 */
	public String confirmOrderCancel(OrderCancel orderCancel) throws YiPinStoreException;

}
