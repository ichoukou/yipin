package com.ytoxl.module.yipin.order.service.impl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ytoxl.module.core.common.constant.CommonConstants;
import com.ytoxl.module.core.common.pagination.BasePagination;
import com.ytoxl.module.user.common.exception.YtoxlUserException;
import com.ytoxl.module.user.security.CustomUserDetails;
import com.ytoxl.module.user.service.UserService;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.common.utils.PropertyUtil;
import com.ytoxl.module.yipin.base.dataobject.Express;
import com.ytoxl.module.yipin.base.dataobject.Point;
import com.ytoxl.module.yipin.base.dataobject.UserInfo;
import com.ytoxl.module.yipin.base.dataobject.resultmap.ExpressMessage;
import com.ytoxl.module.yipin.base.mapper.ExpressMapper;
import com.ytoxl.module.yipin.base.service.ExpressService;
import com.ytoxl.module.yipin.base.service.PointService;
import com.ytoxl.module.yipin.base.service.ProductService;
import com.ytoxl.module.yipin.base.service.UserInfoService;
import com.ytoxl.module.yipin.order.dataobject.OrderAddress;
import com.ytoxl.module.yipin.order.dataobject.OrderCancel;
import com.ytoxl.module.yipin.order.dataobject.OrderHead;
import com.ytoxl.module.yipin.order.dataobject.OrderInvoiceAddress;
import com.ytoxl.module.yipin.order.dataobject.OrderItem;
import com.ytoxl.module.yipin.order.dataobject.OrderRefund;
import com.ytoxl.module.yipin.order.dataobject.OrderRefundPayment;
import com.ytoxl.module.yipin.order.dataobject.resultmap.OrderExportModel;
import com.ytoxl.module.yipin.order.dataobject.resultmap.OrderPackageExpressInfo;
import com.ytoxl.module.yipin.order.mapper.OrderAddressMapper;
import com.ytoxl.module.yipin.order.mapper.OrderCancelMapper;
import com.ytoxl.module.yipin.order.mapper.OrderHeadMapper;
import com.ytoxl.module.yipin.order.mapper.OrderInvoiceAddressMapper;
import com.ytoxl.module.yipin.order.mapper.OrderItemMapper;
import com.ytoxl.module.yipin.order.mapper.OrderRefundMapper;
import com.ytoxl.module.yipin.order.mapper.OrderRefundPaymentMapper;
import com.ytoxl.module.yipin.order.service.OrderService4Manage;

@Service
public class OrderService4ManageImpl implements OrderService4Manage {

	@Autowired
	private ExpressService expressService;
	@Autowired
	private OrderHeadMapper<OrderHead> orderHeadMapper;
	@Autowired
	private OrderItemMapper<OrderItem> orderItemMapper;
	@Autowired
	private OrderAddressMapper<OrderAddress> orderAddressMapper;
	@Autowired
	private OrderRefundMapper<OrderRefund> orderRefundMapper;
	@Autowired
	private OrderCancelMapper<OrderCancel> orderCancelMapper;
	@Autowired
	private ExpressMapper<Express> expressMapper;
	@Autowired
	private OrderInvoiceAddressMapper<OrderInvoiceAddress> orderInvoiceAddressMapper;
	@Autowired
	private OrderRefundPaymentMapper<OrderRefundPayment> orderRefundPaymentMapper;
	@Autowired
	private UserService userService;
	@Autowired
	private PointService pointService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private ProductService productService;
	
	@Override
	public void searchOrders4ManagerOrSeller(BasePagination<OrderHead> orderPage) {
		Integer total=0;
		Collection<OrderHead> result=null;
		
		Map<String, Object> searchParams = orderPage.getSearchParamsMap();
		if(null != searchParams){
			searchParams.put(CommonConstants.PAGE_SORT, "head.createTime");
			searchParams.put(CommonConstants.PAGE_DIR, "desc");
		
			//如果选择 退货  则显示 全部退货 和 部分退货的订单
			Object obj = searchParams.get("status");
			Short status =0; //status=0:查询全部订单
			if(null != obj  && StringUtils.isNotEmpty(obj.toString())){
				status=Short.parseShort(obj.toString());
			}
			//退货
			if(status == OrderHead.STATUS_RETURN){
				//为后台查询拼接sql条件 
				Object objReturn = searchParams.get("returnStatus");//退货订单状态
				if(objReturn!=null&&StringUtils.isNotEmpty(objReturn.toString())){
					// 0全部  1待审核  2审核通过  3审核未通过 4已发货  5已收货 6已完成
					Integer index = Integer.parseInt(objReturn.toString());
					switch (index) {
						case 1:searchParams.put("status",OrderRefund.STATUS_NOTAUDIT);break;
						case 2:searchParams.put("status",OrderRefund.STATUS_ACCEPT);break;
						case 3:searchParams.put("status",OrderRefund.STATUS_REFUSEED);break;
						case 4:searchParams.put("status",OrderRefund.STATUS_RESEND);break;
						case 5:searchParams.put("status",OrderRefund.STATUS_TAKE_GOODS);break;
						case 6:searchParams.put("status",OrderRefund.STATUS_FINISH);break;
						default:searchParams.put("status",null);//全部
					}
				}else{
					searchParams.put("status",null);//全部
				}
				
				//首先我必须查询所有满足退货订单状态的总数
				if (orderPage.isNeedSetTotal()) {
					total = orderRefundMapper.searchOrdersRefund4ManagerCount(searchParams);
					orderPage.setTotal(total);
				}
				
				//获得所有订单id集合  里面带有退货订单的退货id 
				result = orderRefundMapper.searchOrdersRefund4Manager(searchParams);
			}
			//待发货
			else if(status == OrderHead.STATUS_WAITSEND && searchParams.get("waitStatus")!=null && !String.valueOf(searchParams.get("waitStatus")).equals("0") && !String.valueOf(searchParams.get("waitStatus")).equals("")){
				Object objWait = searchParams.get("waitStatus");//待发货订单状态
				if(objWait!=null && StringUtils.isNotEmpty(objWait.toString())){
					// 0全部  1=退款审核中，2=退款未通过，3=审核通过，4=已退款
					Integer index = Integer.parseInt(objWait.toString());
					switch (index) {
						case 1:searchParams.put("status",OrderCancel.STATUS_AUDIT);break;
						case 2:searchParams.put("status",OrderCancel.STATUS_NOTAUDIT);break;
						case 3:searchParams.put("status",OrderCancel.STATUS_THROUGH);break;
						case 4:searchParams.put("status",OrderCancel.STATUS_FINISH);break;
						default:searchParams.put("status",null);//全部
					}
				}else{
					searchParams.put("status",null);//全部
				}
				
				if (orderPage.isNeedSetTotal()) {
					total = orderCancelMapper.searchOrdersCancel4ManagerCount(searchParams);
					orderPage.setTotal(total);
				}
				result = orderCancelMapper.searchOrdersCancel4Manager(searchParams);
			}else{
				if(status!=0){
					searchParams.put("status", status);
				}
				
				if (orderPage.isNeedSetTotal()) {
					total = orderHeadMapper.searchOrders4ManagerCount(searchParams);
					orderPage.setTotal(total);
				}
				result = orderHeadMapper.searchOrders4Manager(searchParams);
			}
		}
		orderPage.setResult(result);
	}
 
	/**
	 * 根据订单id获取订单明细
	 */
	@Override
	public OrderHead getOrderById(Integer orderId) throws YiPinStoreException {
		OrderHead order = orderHeadMapper.getOrderById(orderId);
		if(order == null){
			throw new YiPinStoreException("订单相关数据不存在");
		}
		
		// 获取订单商品明细
		List<OrderItem> orderItems = orderItemMapper.listOrderItemsByOrderId(orderId);
		if(orderItems != null && orderItems.size()>0){
			order.setItems(orderItems);
		}
		
		//获取收货人明细信息
		OrderAddress address  = orderAddressMapper.getOrderAddressByOrderId(orderId);
		if(address!= null && address.getExpressId()!=null && StringUtils.isNotEmpty(address.getExpressNo()) ){
			//获取订单包裹快递信息
			Express express = expressMapper.get(address.getExpressId());
			if(express!=null){
				ExpressMessage em = expressService.getExpressDetailInfoFromKuaidi100(express.getCompanyCode(), address.getExpressNo());
				address.setExpressMessage(em);
			}
		}
		order.setOrderAddress(address);
	
		//如果是待发货，,获取退款信息
		if(OrderHead.STATUS_WAITSEND == order.getStatus()){
			OrderCancel orderCancel = orderCancelMapper.getOrderCancelByOrderId(orderId);
			order.setOrderCancel(orderCancel);
		}
		
		//如果是退货,获取退货信息
		List<OrderRefund> OrderRefunds = orderRefundMapper.listOrderRefundByOrderId(orderId);
		order.setOrderRefunds(OrderRefunds);
		
		return order;
	}
	 
	@Override
	public boolean updateStatus(OrderRefund orderRefund) {
		return orderRefundMapper.updateStatus(orderRefund) > 0;
	}
 
	@Override
	public List<OrderExportModel> listOrders(Map<String,Object> map) throws YiPinStoreException, YtoxlUserException{
		boolean returnFlag = false;
		boolean cancelFlag = false;
		List<OrderExportModel> exportModel = null;
		
		String endTime = (String) map.get(com.ytoxl.module.yipin.base.common.CommonConstants.ENDTIME);
		if (StringUtils.isNotEmpty(endTime)) {
			map.put(com.ytoxl.module.yipin.base.common.CommonConstants.ENDTIME, 
					endTime + com.ytoxl.module.yipin.base.common.CommonConstants.ENDTIME_VALUE);
		}
		
		// 获得当前登录用户
		boolean isAdmin = false;
		CustomUserDetails user = userService.getCurrentUser();
		UserInfo info = userInfoService.getUserByUserId(user.getUserId());
		if(info.getUserType().equals(UserInfo.USER_TYPE_MANAGER)||info.getUserType().equals(UserInfo.USER_TYPE_MANAGER_SON)){//管理员 或管理员子账号
			isAdmin = true;
		}else{
			isAdmin = false;
		}
		
		Integer realUserId = userInfoService.searchUserIdByUserType();
		
		if(!isAdmin){	//不是管理员登录（目前认为是卖家登录，不考虑前台买家登录情况）
			map.put("sellerId",realUserId.toString());
			map.put("sellerFlag", "1");
		}
		
		String selectStatus = (String)map.get("status");
		if(null != selectStatus && selectStatus.length() > 0){
			short status = Short.parseShort(selectStatus);
			if(status == OrderHead.STATUS_RETURN.shortValue()){
				returnFlag = true;
			}else if(status == OrderHead.STATUS_WAITSEND.shortValue()){
				cancelFlag = true;
			}
		}
		
		if(returnFlag){	//退货
			String rstatus = (String)map.get("returnStatus");
			if(rstatus != null && rstatus.length() > 0 && !"0".equals(rstatus)){
				short srstatus = Short.parseShort(rstatus);
				map.put("refundStatus", srstatus);
			}
			exportModel = orderHeadMapper.listOrders4Return(map);
		}else{	//其他非退货
			if(cancelFlag){
				String wstatus = (String)map.get("waitStatus");
				if(wstatus != null && wstatus.length() > 0 && !"0".equals(wstatus)){
					short swstatus = Short.parseShort(wstatus);
					map.put("cancelStatus", swstatus);
				}
			}
			exportModel = orderHeadMapper.listOrders(map);
		}
				
		for(OrderExportModel model:exportModel){
			//订单状态翻译（数字->字符串）
			String ostatus = PropertyUtil.getPropertyValue("order.status."+model.getOrderStatus(),null);
			model.setOstatus(ostatus);
			
			//待发货取消订单状态翻译
			if(model.getCancelStatus() != null){
				String cstatus = PropertyUtil.getPropertyValue("order.wait.status."+model.getCancelStatus(),null);
				model.setCstatus(cstatus);
			}
			
			//退货状态翻译
			if(returnFlag){
				String rstatus = PropertyUtil.getPropertyValue("order.refund.status."+model.getRefundStatus().shortValue(),null);
				model.setRstatus(rstatus);
			}else{
				//查询退货表是否有相应记录
				OrderRefund refund = orderRefundMapper.getOrderRefundByOrderItemId(model.getOrderItemId());
				if(refund != null){
					String rstatus = PropertyUtil.getPropertyValue("order.refund.status."+refund.getStatus().shortValue(),null);
					model.setRstatus(rstatus);
				}
			}
		}
		return exportModel;
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
	public void updateBatchUpload(List<String[]> excels, Integer userId) throws YiPinStoreException {
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,OrderPackageExpressInfo> orderMap = new HashMap<String,OrderPackageExpressInfo>();
		for (Iterator iterator = excels.iterator(); iterator.hasNext();) {
			String[] strings = (String[]) iterator.next();
			if(strings.length!=3){
				throw new YiPinStoreException("订单发货数据不完整");
			}else{
				if(StringUtils.isBlank(strings[0]) && StringUtils.isBlank(strings[1]) && StringUtils.isBlank(strings[2])){
					continue;
				}
				if(StringUtils.isBlank(strings[0]) || StringUtils.isBlank(strings[1]) || StringUtils.isBlank(strings[2])){
					throw new YiPinStoreException("订单发货数据不完整");
				}
				if(strings[1].length() > 20){
					throw new YiPinStoreException("订单包裹中的快递单号不能超过20位");
				}
				
				if(orderMap.containsKey(strings[0])){
					throw new YiPinStoreException("订单发货数据重复");
				}else{
					OrderPackageExpressInfo exInfo = new OrderPackageExpressInfo();
					exInfo.setOrderNo(strings[0]);
					exInfo.setExpressNo(strings[1]);
					exInfo.setExpressName(strings[2]);
					orderMap.put(strings[0], exInfo);
				}
			}
		}
		
		Integer realUserId = userInfoService.searchUserIdByUserType();	
		Iterator itOrders = orderMap.entrySet().iterator();
		while(itOrders.hasNext()){
			map.clear();
			Map.Entry entry = (Map.Entry) itOrders.next();
			String keyOrderNo = (String)entry.getKey();
			OrderPackageExpressInfo packExpress = (OrderPackageExpressInfo)entry.getValue();
			
			//首先检查订单表中是否存在指定的订单
			map.put("orderNo", keyOrderNo);
			map.put("userId", realUserId);
			OrderHead oh = orderHeadMapper.findOrder(map);
			if(oh == null){
				throw new YiPinStoreException("订单号 "+keyOrderNo+" 不存在");
			}
			if(oh.getStatus() != OrderHead.STATUS_WAITSEND){
				throw new YiPinStoreException("请确认订单 "+keyOrderNo+" 为待发货状态");
			}
			
			map.put("orderId", oh.getOrderId());
			
			//检查该待发货订单是否有退款的情况，检查审批状态(只有“退款未通过”状态才可以批量发货)
			OrderCancel cancelOrder = orderCancelMapper.getOrderCancelByOrderId(oh.getOrderId());
			if(cancelOrder != null){
				if(cancelOrder.getStatus().shortValue() != OrderCancel.STATUS_NOTAUDIT){
					String alertFlag = PropertyUtil.getPropertyValue("order.wait.status."+cancelOrder.getStatus().shortValue(),null);
					throw new YiPinStoreException("订单号 "+keyOrderNo+" "+alertFlag+"，不能发货");
				}
			}
			
			//根据excel中快递公司名称，查询express表
			Express express = null;
			List<Express> expList = expressMapper.searchExpress(packExpress.getExpressName());
			if(expList!=null && expList.size()>0){
				express = expList.get(0);
			}
			
			//获取order_address表中的信息并更新
			OrderAddress  oa = orderAddressMapper.getOrderAddressByOrderId(oh.getOrderId());
			if(oa == null){
				throw new YiPinStoreException("订单号："+keyOrderNo+",包裹信息 不存在");
			}
			
			if(express != null){
				oa.setExpressId(express.getExpressId());
				oa.setExpressName(express.getExpressName());
			}else{
				oa.setExpressName(packExpress.getExpressName());	
			}
			oa.setExpressNo(packExpress.getExpressNo());
			orderAddressMapper.updateOrderAddressByOrderAddressId(oa);
			
			map.put("status", OrderHead.STATUS_SEND);
			orderHeadMapper.updateOrderStatus(map);
		}
		
	}

	/**
	 * 确认发货
	 * @return
	 * @throws YiPinStoreException
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
	public String confirmSendProduct(OrderAddress orderExpress) throws YiPinStoreException {
		/**更新包裹表中的快递单号，快递公司，是否发货，发货时间*/
		OrderAddress orderAddress = orderAddressMapper.get(orderExpress.getOrderAddressId());
		if(orderAddress==null){
			throw new YiPinStoreException("该收货地址已被删除");
		}
		orderAddress.setExpressId(orderExpress.getExpressId());
		orderAddress.setExpressName(orderExpress.getExpressName());
		orderAddress.setExpressNo(orderExpress.getExpressNo());
		orderAddressMapper.updateOrderAddressByOrderAddressId(orderAddress);
		
		/**修改订单主表状态为已发货*/
		OrderHead oh = orderHeadMapper.getOrderById(orderExpress.getOrderId());
		if(oh==null){
			throw new YiPinStoreException("该订单已被删除");
		}
		if(oh.getStatus()!= OrderHead.STATUS_WAITSEND){
			throw new YiPinStoreException("请确认该订单未发货");
		}
		oh.setStatus(OrderHead.STATUS_SEND);
		orderHeadMapper.update(oh);

		return PropertyUtil.getPropertyValue("order.status."+OrderHead.STATUS_SEND, null);
	}

	/**
	 * 审核
	 * @return
	 * @throws YiPinStoreException
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
	public String audit(OrderRefund orderRefund) throws YiPinStoreException {
		OrderRefund item = orderRefundMapper.get(orderRefund.getOrderRefundId());
		if(item==null){
			throw new YiPinStoreException("该退货订单项已被删除");
		}
		if( item.getStatus() != OrderRefund.STATUS_NOTAUDIT){
			throw new YiPinStoreException("请确认该订单未审核");
		}
		orderRefundMapper.updateStatus(orderRefund);
		return PropertyUtil.getPropertyValue("order.return.status."+orderRefund.getStatus(), null);
	}
 
	/**
	 * 确认退款
	 * @param orderPayment
	 * @return
	 * @throws YiPinStoreException
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
	public String confirmPayment(OrderRefundPayment orderRefundPayment) throws YiPinStoreException {
		OrderRefundPayment orp=orderRefundPaymentMapper.get(orderRefundPayment.getOrderRefundPaymentId());
		if(orp.getStatus()!=OrderRefundPayment.STATUS_WAITREFUND){
			throw new YiPinStoreException("请确认该订单是否已经退款");
		}
		int rows=orderRefundPaymentMapper.updateStatus(orderRefundPayment);
		if(rows>0){
			OrderRefund orderRefund=new OrderRefund();
			orderRefund.setOrderRefundId(orderRefundPayment.getOrdeRrefundId());
			orderRefund.setStatus(OrderRefund.STATUS_FINISH);
			orderRefundMapper.updateStatus(orderRefund);	
		}
		
		//TODO 更新积分   获取买家userId  通过order_head 表
		OrderRefund orderRefund = orderRefundMapper.get(orderRefundPayment.getOrdeRrefundId());
		OrderHead orderHead = orderHeadMapper.get(orderRefund.getOrderId());
		pointService.updatePointByUserId(orderHead.getUserId(), orp.getRefundAmount(), Point.TYPE_MINUS, Point.POINTSOURCE_RETURNORDER);
		return PropertyUtil.getPropertyValue("order.return.payment.status."+orderRefundPayment.getStatus(), null);
	}

	/**
	 * 管理员|卖家  - 发票管理查询
	 * @param orderPage
	 * @return
	 * @throws YiPinStoreException
	 */
	@Override
	public void searchOrderInvoices4ManagerOrSeller(BasePagination<OrderInvoiceAddress> orderInvoicePage) throws YiPinStoreException {
		Integer total=0;
		Collection<OrderInvoiceAddress> result = null;
		
		Map<String, Object> searchParams = orderInvoicePage.getSearchParamsMap();
		if(null!=searchParams){
			searchParams.put(CommonConstants.PAGE_SORT, "invoice.createTime");
			searchParams.put(CommonConstants.PAGE_DIR, "desc");
			
			if (orderInvoicePage.isNeedSetTotal()) {
				total = orderInvoiceAddressMapper.searchOrderInvoices4ManagerCount(searchParams);
				orderInvoicePage.setTotal(total);
			}
			result = orderInvoiceAddressMapper.searchOrderInvoices4Manager(searchParams);
		}
		
		orderInvoicePage.setResult(result);
	}

	/**
	 * 发票确认发货
	 * @return
	 * @throws YiPinStoreException
	 */
	@Override
	public String confirmSendOrderInvoice(OrderInvoiceAddress invoiceExpress) throws YiPinStoreException {
		/**更新包裹表中的快递单号，快递公司，是否发货，发货时间*/
		OrderInvoiceAddress orderInvoiceAddress = orderInvoiceAddressMapper.get(invoiceExpress.getOrderInvoiceAddressId());
		if(orderInvoiceAddress == null){
			throw new YiPinStoreException("该发票已被删除");
		}
		
		/**判断快递单号是否重复*/
		if(invoiceExpress.getExpressId()!=null && StringUtils.isNotEmpty(invoiceExpress.getExpressNo())){
			Integer noCount = orderInvoiceAddressMapper.getExpressNOCountByExpressIdAndExpressNo(invoiceExpress.getExpressId(), invoiceExpress.getExpressNo());
			if(noCount>0){
				throw new YiPinStoreException("快递单号重复！");
			}
		}
		
		orderInvoiceAddress.setExpressId(invoiceExpress.getExpressId());
		orderInvoiceAddress.setExpressName(invoiceExpress.getExpressName());
		orderInvoiceAddress.setExpressNo(invoiceExpress.getExpressNo());
		orderInvoiceAddressMapper.updateOrderInvoiceAddressById(orderInvoiceAddress);

		return PropertyUtil.getPropertyValue("order.status."+OrderHead.STATUS_SEND, null);
	}

	/**
	 * 确认退货
	 * @return
	 * @throws YiPinStoreException
	 */
	@Override
	public String confirmBackProduct(Integer orderRefundId,BigDecimal packageTotalPrice) throws YiPinStoreException{
		OrderRefund orderRefund = orderRefundMapper.get(orderRefundId); 
		if(orderRefund==null){
			throw new YiPinStoreException("该退货记录不存在！");
		}
		if(orderRefund.getStatus()== OrderRefund.STATUS_TAKE_GOODS){
			throw new YiPinStoreException("该订单退货已确认收货！");
		}
		orderRefund.setOrderRefundId(orderRefundId);
		orderRefund.setStatus(OrderRefund.STATUS_TAKE_GOODS);
		orderRefundMapper.updateStatus(orderRefund);
	
		/**增加退款记录*/
		OrderRefundPayment orderRefundPayment = orderRefundPaymentMapper.getOrderRefundPaymentByOrderRefundId(orderRefundId);
		if(orderRefundPayment==null){
			orderRefundPayment = new OrderRefundPayment();
			orderRefundPayment.setOrdeRrefundId(orderRefundId);
			orderRefundPayment.setRefundAmount(packageTotalPrice);
			orderRefundPayment.setStatus(OrderRefundPayment.STATUS_WAITREFUND);
			orderRefundPaymentMapper.add(orderRefundPayment);
		}
		
		return PropertyUtil.getPropertyValue("order.refund.status." + OrderRefund.STATUS_TAKE_GOODS, null);
	}

	/**
	 * 待发货订单退款审核
	 * @param orderPayment
	 * @return
	 * @throws YiPinStoreException
	 */
	@Override
	public String auditOrderCancel(OrderCancel orderCancel)throws YiPinStoreException {
		OrderCancel oc = orderCancelMapper.get(orderCancel.getOrderCancelId());
		if(oc==null){
			throw new YiPinStoreException("该订单退款申请已被删除");
		}
		if(OrderCancel.STATUS_THROUGH == oc.getStatus() || OrderCancel.STATUS_NOTAUDIT == oc.getStatus()){
			throw new YiPinStoreException("请确认该订单退款申请未审核");
		}
		orderCancelMapper.updateStatus(orderCancel);
		return PropertyUtil.getPropertyValue("order.wait.status." + orderCancel.getStatus(), null);
	}
	
	/**
	 * 待发货订单退款审核 - 同意退款
	 * @param orderPayment
	 * @return
	 * @throws YiPinStoreException
	 */
	@Override
	public String confirmOrderCancel(OrderCancel orderCancel)throws YiPinStoreException {
		OrderCancel oc = orderCancelMapper.get(orderCancel.getOrderCancelId());
		if(oc==null){
			throw new YiPinStoreException("该订单退款申请已被删除");
		}
		if(oc.getStatus() == OrderCancel.STATUS_FINISH){
			throw new YiPinStoreException("请确认该订单退款申请未退款");
		}
		orderCancelMapper.updateStatus(orderCancel);
		
		/**还原库存*/
		List<OrderItem> orderItemList = orderItemMapper.listOrderItemsByOrderId(orderCancel.getOrderId());
		if(orderItemList!=null && orderItemList.size()>0){
			for(OrderItem orderItem:orderItemList){
				productService.updateInventory4Revert(orderItem.getProductSkuId(), orderItem.getNum());
			}
		}
		
		/**扣除积分  */ 
		OrderHead orderHead = orderHeadMapper.get(oc.getOrderId());
		pointService.updatePointByUserId(orderHead.getUserId(), orderHead.getPaymentAmount(), Point.TYPE_MINUS, Point.POINTSOURCE_CANCEL);
		
		return PropertyUtil.getPropertyValue("order.wait.status." + orderCancel.getStatus(), null);
	}
	
	
}
