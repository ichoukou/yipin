package com.ytoxl.yipin.web.action.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.TokenHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ytoxl.module.core.common.pagination.BasePagination;
import com.ytoxl.module.user.common.exception.YtoxlUserException;
import com.ytoxl.module.user.security.CustomUserDetails;
import com.ytoxl.module.user.service.UserService;
import com.ytoxl.module.yipin.base.common.CodeConstants;
import com.ytoxl.module.yipin.base.common.CommonConstants;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.common.utils.CookieUtils;
import com.ytoxl.module.yipin.base.dataobject.Express;
import com.ytoxl.module.yipin.base.dataobject.UserAddress;
import com.ytoxl.module.yipin.base.dataobject.UserInfo;
import com.ytoxl.module.yipin.base.dataobject.resultmap.ExpressMessage;
import com.ytoxl.module.yipin.base.service.ExpressService;
import com.ytoxl.module.yipin.base.service.UserAddressService;
import com.ytoxl.module.yipin.order.dataobject.OrderAddress;
import com.ytoxl.module.yipin.order.dataobject.OrderCancel;
import com.ytoxl.module.yipin.order.dataobject.OrderHead;
import com.ytoxl.module.yipin.order.dataobject.OrderInvoiceAddress;
import com.ytoxl.module.yipin.order.dataobject.OrderItem;
import com.ytoxl.module.yipin.order.dataobject.OrderRefund;
import com.ytoxl.module.yipin.order.dataobject.OrderRefundExpress;
import com.ytoxl.module.yipin.order.dataobject.resultmap.OrderCheckModel;
import com.ytoxl.module.yipin.order.dataobject.resultmap.ShoppingCart;
import com.ytoxl.module.yipin.order.service.OrderService;
import com.ytoxl.yipin.web.action.BaseAction;
@SuppressWarnings("serial")
public class OrderAction extends BaseAction {
	private static Logger logger = LoggerFactory.getLogger(OrderAction.class);
	private static final String JSP_GET_ORDER_INFO = "getOrderInfo";
	private static final String JSP_GET_ADDRESS_HTML = "getAddressHtml";
	private static final String JSP_CONFIRM_ORDER = "confirmOrder";
	private static final String JSP_GLOBAL_LOGON = "globalLogon";
	private static final String JSP_GLOBAL_INDEX  = "index";
	private static final String JSP_SHOWHOVERCART = "hoverCart";
	private static final String JSP_CART = "shoppingCart";
	//自定义参数
	private Map<String,String> params;
	private List<OrderHead> orders;
	//订单的包裹详情
	private List<OrderAddress> listOrderAddress;
	//包裹详情
	private OrderAddress orderAddress;
	//订单id
	private String orderId;
	//订单ids
	private String orderIds;
	//包裹id
	private String orderAddressId;
	//退货id
	private String orderRefundId;
	//订单明细id
	private String orderItemId;
	//退货快递信息
	private OrderRefundExpress orderRefundExpress;
	//退货信息
	private OrderRefund orderRefund;
	//订单项
	private OrderItem orderItem;
	//发票物流信息
	private OrderInvoiceAddress invoice;
	//用户信息
	private UserInfo userInfo;
	//快递公司
	private List<Express> expresses;
	private List<UserAddress> listUserAddress;
	//订单分页
	private BasePagination<OrderHead> orderPage;
	//退货记录分页
	private BasePagination<OrderRefund> refundPage;
	//退款记录分页
	private BasePagination<OrderCancel> cancelPage;
	//快递id
	private String expressId;
	//快递面单号
	private String mailNo;
	//快递信息
	private ExpressMessage expressMessage;
	//订单信息
	private OrderHead orderHead;
	//退款信息
	private OrderCancel orderCancel;
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserAddressService userAddressService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ExpressService expressService;
	
	/**
	 * 添加商品到购物车或者从购物车中删除商品
	 */
	public String addOrDelProductToCart(){
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			ShoppingCart shoppingCart =  orderService.getShoppingCart(request,response);
			if(shoppingCart==null){
				shoppingCart = new ShoppingCart();
			}
			orderService.addOrDelProductToCart(shoppingCart,params);
			int totalNum = shoppingCart.getTotalNum();
			if(totalNum!=0){
				JSONObject json = JSONObject.fromObject(shoppingCart);
				CookieUtils.addCookie(response, CommonConstants.COOKIE_SHOPPINGCART, json.toString());
			}else{
				CookieUtils.removeCookie(request, response, CommonConstants.COOKIE_SHOPPINGCART);
			}
			setMessage(Boolean.TRUE.toString(), "操作成功");
		} catch (YiPinStoreException e) {
			logger.error("操作购物车失败",e);
			if(CodeConstants.CART_DATA_ERROR.equals(e.getMessage())){
				setMessage(Boolean.FALSE.toString(), e.getLocalizedMessage());
				return JSONMSG;
			}
		}
		return JSONMSG;
	}
	
	/**
	 * 修改商品数量，验证库存
	 */
	public String rectifyProductCount(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			ShoppingCart shoppingCart =  orderService.getShoppingCart(request,response);
			if(shoppingCart!=null){
				OrderCheckModel orderCheckModel = orderService.rectifyProductCount(shoppingCart,params);
				if(orderCheckModel==null){
					setMessage(Boolean.TRUE.toString(), "操作成功");
				}else{
					setMessage(Boolean.FALSE.toString(), JSONObject.fromObject(orderCheckModel).toString());
				}
				JSONObject json = JSONObject.fromObject(shoppingCart);
				CookieUtils.addCookie(response, CommonConstants.COOKIE_SHOPPINGCART, json.toString());
			}else{
				throw new YiPinStoreException(CodeConstants.CART_DATA_ERROR, "数据异常");
			}
		} catch (YiPinStoreException e) {
			logger.error("操作购物车失败",e);
			if(CodeConstants.CART_DATA_ERROR.equals(e.getMessage())){
				setMessage(Boolean.FALSE.toString(), e.getLocalizedMessage());
				return JSONMSG;
			}
		}
		return JSONMSG;
	}
	
	public String showHoverCart(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			ShoppingCart shoppingCart =  orderService.getShoppingCart(request,response);
			if(shoppingCart!=null){
				orders = new ArrayList<OrderHead>();
				orderService.showCart(orders, shoppingCart);
				JSONObject json = JSONObject.fromObject(shoppingCart);
				CookieUtils.addCookie(response, CommonConstants.COOKIE_SHOPPINGCART, json.toString());
			}
		} catch(YiPinStoreException e) {
			logger.error("查看购物车异常",e);
		}
		return JSP_SHOWHOVERCART;
	}
	
	/**
	 * 显示购物车
	 */
	public String showCart(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			ShoppingCart shoppingCart =  orderService.getShoppingCart(request,response);
			if(shoppingCart!=null){
				orders = new ArrayList<OrderHead>();
				orderService.showCart(orders, shoppingCart);
				JSONObject json = JSONObject.fromObject(shoppingCart);
				CookieUtils.addCookie(response, CommonConstants.COOKIE_SHOPPINGCART, json.toString());
			}
		} catch (YiPinStoreException e) {
			logger.error("查看购物车异常",e);
		}
		return JSP_CART;
	}
	
	public String getOrderInfo(){
		try {
			CustomUserDetails user  = userService.getCurrentUser();
			if(orders==null){
				orders = new ArrayList<OrderHead>();
				String skuId = params.get("skuId");
				OrderHead orderHead = new OrderHead();
				OrderItem orderItem = new OrderItem();
				orderItem.setProductSkuId(Integer.parseInt(skuId));
				orderItem.setNum(1);
				List<OrderItem> orderItemList = new ArrayList<OrderItem>();
				orderItemList.add(orderItem);
				orderHead.setItems(orderItemList);
				orders.add(orderHead);
			}
			orderService.setOrdersInfo(orders);
			listUserAddress = userAddressService.getRAddressDetailList(user.getUserId());
		} catch (YiPinStoreException e) {
			logger.error(e.getMessage());
		} catch (YtoxlUserException e) {
			logger.error("用户未登录",e);
			return JSP_GLOBAL_LOGON;
		}
		return JSP_GET_ORDER_INFO;
	}
	
	public String checkOrderProductInfo(){
		try {
			List<OrderCheckModel> dataList = orderService.checkOrderProductInfo(orders);
			if(dataList.size()>0){
				setMessage(Boolean.FALSE.toString(), JSONArray.fromObject(dataList).toString());
			}else{
				setMessage(Boolean.TRUE.toString(), "");
			}
		} catch (YiPinStoreException e) {
			logger.error(e.getMessage());
		}
		return JSONMSG;
	}
	
	public String submitOrder() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession(true);
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragrma", "no-cache");
		response.setDateHeader("Expires", 0);
		try {
			//判断是否重复提交
	        synchronized (session) {
	            if (!TokenHelper.validToken()) {
	            	throw new YiPinStoreException(CodeConstants.ORDER_REPEAT_SUBMIT, "请不要重复提交订单！");
	            }
	        }
	        orderId = orderService.addOrders(request,orders,params);
	        ShoppingCart shoppingCart =  orderService.getShoppingCart(request,response);
	        if(shoppingCart!=null){
	        	orderService.deleteHaveBuyProduct(orders, shoppingCart);
	        	if(shoppingCart.getTotalNum()>0){
	        		JSONObject json = JSONObject.fromObject(shoppingCart);
					CookieUtils.addCookie(response, CommonConstants.COOKIE_SHOPPINGCART, json.toString());
	        	}else{
	        		CookieUtils.removeCookie(request, response, CommonConstants.COOKIE_SHOPPINGCART);
	        	}
	        }
		} catch(YiPinStoreException e) {
			logger.error("生成订单出错",e);
			if(e.getMessage().equals(CodeConstants.ORDER_REPEAT_SUBMIT)){
				setMessage(Boolean.FALSE.toString(), e.getLocalizedMessage());
				return "myOrders";
			}
		}
		return JSP_CONFIRM_ORDER;
	}
	
	/**
	 * 用户中心我的订单
	 * @return
	 */
	public String myOrders(){
		if(null == orderPage){
			orderPage = new BasePagination<OrderHead>();
		}
		//获取订单列表
		try {
			orderService.searchOrder4Front(orderPage);
		} catch (YiPinStoreException e) {
			logger.error("我的订单出错",e);
			return "myOrders";
		}
		return "myOrders";
	}
	
	/**
	 * 用户中心我的订单ajax请求
	 * @return
	 */
	public String myOrdersAjax(){
		if(null == orderPage){
			orderPage = new BasePagination<OrderHead>();
		}
		//获取订单列表
		try {
			orderService.searchOrder4Front(orderPage);
		} catch (YiPinStoreException e) {
			logger.error("我的订单出错",e);
			return "myOrdersAjax";
		}
		return "myOrdersAjax";
	}
	
	/**
	 * 根据订单id获取订单明细
	 * 暂时弃用 不用ajax方式  改成一次加载 2013-12-13 17:45:30
	 * @return
	 */
	public String myOrderDetail(){
		try{
			Integer oId = new Integer(Integer.parseInt(orderId));
			listOrderAddress = orderService.listOrderPackagesByOrderId(oId);
		}catch (Exception e) {
			logger.error("获取订单id错误",e);
		}
		return "myOrderDetail";
	}
	
	/**
	 * 我的订单通过 orderAddressId查看包裹明细
	 * @return
	 */
	public String myOrderPackageDetail(){
		try {
			Integer orderAddrId = Integer.parseInt(orderAddressId);
			orderAddress = orderService.getOrderAddressByOrderAddressId(orderAddrId);
		} catch (YiPinStoreException e) {
			logger.error("获取订单包裹明细出错",e);
		}
		return "myOrderPackageDetail";
	}

	/**
	 * 我的退货管理
	 * @return
	 */
	public String myRefundManage(){
		if(null == orderPage){
			orderPage = new BasePagination<OrderHead>();
		}
		try {
			orderService.searchRefundOrder4Front(orderPage);
		} catch (YiPinStoreException e) {
			logger.error("我的订单退货管理出错",e);
			return "myRefundManage";
		}
		return "myRefundManage";
	}
	
	/**
	 * 填写退货申请
	 * @return
	 */
	public String myOrderRefundForm(){
		try {
			Integer itemId = Integer.parseInt(orderItemId);
			orderItem = orderService.getOrderInfoByOrderItemId4RefundForm(itemId);
		} catch (YiPinStoreException e) {
			logger.error("退货,获取订单项出错",e);
		}
		return "myOrderRefundForm";
	}
	
	/**
	 * 退货申请保存
	 * @return
	 */
	public String saveOrderRefundInfo(){
		//保存orderRefund 退货信息
		//返回json
		try {
			orderService.saveOrderRefundInfo(orderRefund);
		} catch (YiPinStoreException e) {
			logger.error("保存退货信息出错",e);
			setMessage("false",e.getMessage());
			return JSONMSG;
		}
		//如果退货成功 orderRefundId != null
		if(null == orderRefund.getOrderRefundId()){
			setMessage("false", "保存退货信息出错!");
			return JSONMSG;
		}
		setMessage("true", "提交成功!");
		return JSONMSG;
	}
	
	/**
	 * 退货记录
	 * @return
	 */
	public String myRefundRecord(){
		if(null == refundPage){
			refundPage = new BasePagination<OrderRefund>();
		}
		try {
			orderService.searchRefundRecords4Front(refundPage);
		} catch (YiPinStoreException e) {
			logger.error("退货记录报错",e);
		}
		return "myRefundRecord";
	}
	
	/**
	 * 退货填写快递信息
	 */
	public String writeRefundExpressInfoForm() {
		try{
			Integer refundId = Integer.parseInt(orderRefundId);
			//获取商家信息 
			userInfo = orderService.getSellerInfoByorderRefundId(refundId);
			//快递基础信息
			expresses = expressService.listExpresses();
		}catch (Exception e) {
			logger.error("退货填写快递单号(orderRefundId)出错",e);
			orderRefundId = null; 
			//页面进行判断如果orderRefundId = null 给用户提示
		}
		return "writeRefundExpressInfoForm";
	}
	
	/**
	 * 保存退货填写快递信息
	 */
	public String saveRefundExpressInfo() {
		try {
			orderService.saveRefundExpressInfo(orderRefundExpress);
		} catch (YiPinStoreException e) {
			logger.error("保存退货快递信息出错", e);
			setMessage("false", "提交失败!");
			return JSONMSG;
		}
		setMessage("true", "提交成功!");
		return JSONMSG;
	}
	
	/**
	 * 发票详细 2014-01-16 12:28:40 v2.0 弃用
	 * @return
	 */
	public String invoiceDetail(){
		try {
			Integer id = Integer.parseInt(orderId);
			invoice = orderService.getInvoiceDetailByorderId(id);
		} catch (YiPinStoreException e) {
			logger.error("查看发票详情出错",e);
		}
		return "invoiceDetail";
	}
	
	/**
	 * 退款管理
	 * @return
	 */
	public String myOrderCancelManage(){
		if(null == orderPage){
			orderPage = new BasePagination<OrderHead>();
		}
		try {
			orderService.searchCancelOrder4Front(orderPage);
		} catch (YiPinStoreException e) {
			logger.error("我的订单退款管理出错",e);
			return "myOrderCancelManage";
		}
		return "myOrderCancelManage";
	}
	
	/**
	 * 退款记录
	 * @return
	 */
	public String myOrderCancelRecord() {
		if(null == cancelPage){
			cancelPage = new BasePagination<OrderCancel>();
		}
		try {
			orderService.searchCancelOrderRecords4Front(cancelPage);
		} catch (YiPinStoreException e) {
			logger.error("退款记录报错",e);
		}
		return "myOrderCancelRecord";
	}
	
	/**
	 * ajax 查询物流信息
	 * @return
	 */
	public String expressInfoAjax(){
		try {
			Integer eId = null;
			Integer oId = null;
			if(StringUtils.isNotEmpty(expressId)){
				eId = Integer.parseInt(expressId);
			}
			if(StringUtils.isNotEmpty(orderId)){
				oId = Integer.parseInt(orderId);
			}
			expressMessage = orderService.getExpressMessageByExpressIdAndMailNo(eId, mailNo,oId);
		} catch (YiPinStoreException e) {
			logger.error(e.getMessage(),e);
		}
		return "expressInfoAjax";
	}
	
	/**
	 * 确认收货
	 * @return
	 */
	public String confirmGoods(){
		try {
			Integer orderAddrId = Integer.parseInt(orderAddressId);
			orderService.saveConfirmGoods(orderAddrId);
		} catch (YiPinStoreException e) {
			logger.error("确认收货出错",e);
			setMessage("false","确认收货出错!");
			return JSONMSG;
		}
		setMessage("true","确认收货成功!");
		return JSONMSG;
	}
	
	/**
	 * 取消订单
	 * @return
	 */
	public String cancelOrder(){
		try{
			Integer id = Integer.parseInt(orderId);
			orderService.saveCancelOrder(id);
		}catch (YiPinStoreException e) {
			logger.error("取消订单出错",e);
			setMessage("false","取消订单出错!");
			return JSONMSG;
		}
		setMessage("true","取消订单成功!");
		return JSONMSG;
	}
	
	/**
	 * 逻辑删除订单
	 * @return
	 */
	public String deleteOrder(){
		try{
			Integer id = Integer.parseInt(orderId);
			orderService.saveDeleteOrder(id);
		}catch (YiPinStoreException e) {
			logger.error("逻辑删除订单出错",e);
			setMessage("false","逻辑删除订单出错!");
			return JSONMSG;
		}
		setMessage("true","逻辑删除订单成功!");
		return JSONMSG;
	}
	
	/**
	 * 批量确认收货
	 * @return
	 */
	public String bathConfirmReceive(){
		boolean b = false;
		try {
			b = orderService.saveBathConfrimReceive(orderIds);
		} catch (YiPinStoreException e) {
			logger.error("批量确认收货失败!",e);
		}
		if(b){
			setMessage(Boolean.TRUE.toString(), "批量确认收货成功!");
		}else{
			setMessage(Boolean.FALSE.toString(), "批量确认收货失败!");
		}
		return JSONMSG;
	}
	
	/**
	 * 填写退款申请
	 * @return
	 */
	public String myOrderCancelForm(){
		Integer oId = Integer.parseInt(orderId);
		try {
			orderHead = orderService.getOrderInfoByOrderId4OrderCancelForm(oId);
		} catch (YiPinStoreException e) {
			logger.error("填写退款申请出错", e);
		}
		return "myOrderCancelForm";
	}
	
	/**
	 * 退款申请保存
	 * @return
	 */
	public String saveCancelOrderInfo(){
		//保存orderCancel 退款信息 TODO
		//返回json
		try {
			orderService.saveCancelOrderInfo(orderCancel);
		} catch (YiPinStoreException e) {
			logger.error("保存退款信息出错",e);
			setMessage("false",e.getMessage());
			return JSONMSG;
		}
		//如果退款成功 orderRefundId != null
		if(null == orderCancel.getOrderCancelId()){
			setMessage("false", "保存退款信息出错!");
			return JSONMSG;
		}
		setMessage("true", "提交成功!");
		return JSONMSG;
	}
	
	public String getAddressHtml(){
		return 	JSP_GET_ADDRESS_HTML;
	}
	
	//返回所有的订单状态
	public Short[] getStatus() {
		return OrderHead.STATUSES;
	}

	//返回是否要发票
	public Short[] getInvoiceStatus(){
		return OrderHead.INVOICESTATUS;
	}
	
	//退货的状态
	public Short[] getRefundStatus(){
		return OrderRefund.ITEM_STATUSES;
	}
	
	//包裹状态
	public Short[] getPackageStatus(){
		return OrderAddress.PACKAGE_STATUSES;
	}
	
	//订单类型
	public Short[] getOrderTypes(){
		return OrderHead.SOURCESTATUSES;
	}
	
	//退款状态
	public Short[] getOrderCancelStatus(){
		return OrderCancel.CANCEL_STATUSES;
	}
	
	//查询订单时间
	public Short[] getOrderTimeTypes(){
		return OrderHead.ORDER_TIME_TYPES;
	}
	
	public BasePagination<OrderHead> getOrderPage() {
		return orderPage;
	}

	public void setOrderPage(BasePagination<OrderHead> orderPage) {
		this.orderPage = orderPage;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}

	public List<OrderAddress> getListOrderAddress() {
		return listOrderAddress;
	}

	public OrderRefundExpress getOrderRefundExpress() {
		return orderRefundExpress;
	}

	public void setOrderRefundExpress(OrderRefundExpress orderRefundExpress) {
		this.orderRefundExpress = orderRefundExpress;
	}

	public void setListOrderAddress(List<OrderAddress> listOrderAddress) {
		this.listOrderAddress = listOrderAddress;
	}
	
	public List<UserAddress> getListUserAddress() {
		return listUserAddress;
	}

	public List<Express> getExpresses() {
		return expresses;
	}

	public void setExpresses(List<Express> expresses) {
		this.expresses = expresses;
	}

	public String getOrderRefundId() {
		return orderRefundId;
	}

	public void setOrderRefundId(String orderRefundId) {
		this.orderRefundId = orderRefundId;
	}

	public void setListUserAddress(List<UserAddress> listUserAddress) {
		this.listUserAddress = listUserAddress;
	}

	public List<OrderHead> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderHead> orders) {
		this.orders = orders;
	}
	
	
	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	public OrderAddress getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(OrderAddress orderAddress) {
		this.orderAddress = orderAddress;
	}

	public String getOrderAddressId() {
		return orderAddressId;
	}

	public void setOrderAddressId(String orderAddressId) {
		this.orderAddressId = orderAddressId;
	}

	public OrderRefund getOrderRefund() {
		return orderRefund;
	}

	public void setOrderRefund(OrderRefund orderRefund) {
		this.orderRefund = orderRefund;
	}

	public BasePagination<OrderRefund> getRefundPage() {
		return refundPage;
	}

	public void setRefundPage(BasePagination<OrderRefund> refundPage) {
		this.refundPage = refundPage;
	}

	public OrderInvoiceAddress getInvoice() {
		return invoice;
	}

	public void setInvoice(OrderInvoiceAddress invoice) {
		this.invoice = invoice;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getExpressId() {
		return expressId;
	}

	public void setExpressId(String expressId) {
		this.expressId = expressId;
	}

	public String getMailNo() {
		return mailNo;
	}

	public void setMailNo(String mailNo) {
		this.mailNo = mailNo;
	}

	public ExpressMessage getExpressMessage() {
		return expressMessage;
	}

	public void setExpressMessage(ExpressMessage expressMessage) {
		this.expressMessage = expressMessage;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}

	public String getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(String orderIds) {
		this.orderIds = orderIds;
	}

	public BasePagination<OrderCancel> getCancelPage() {
		return cancelPage;
	}

	public void setCancelPage(BasePagination<OrderCancel> cancelPage) {
		this.cancelPage = cancelPage;
	}

	public OrderHead getOrderHead() {
		return orderHead;
	}

	public void setOrderHead(OrderHead orderHead) {
		this.orderHead = orderHead;
	}

	public OrderCancel getOrderCancel() {
		return orderCancel;
	}

	public void setOrderCancel(OrderCancel orderCancel) {
		this.orderCancel = orderCancel;
	}

}
