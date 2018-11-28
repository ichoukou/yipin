package com.ytoxl.module.yipin.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ytoxl.module.core.common.pagination.BasePagination;
import com.ytoxl.module.user.common.exception.YtoxlUserException;
import com.ytoxl.module.user.security.CustomUserDetails;
import com.ytoxl.module.user.service.UserService;
import com.ytoxl.module.yipin.base.common.CodeConstants;
import com.ytoxl.module.yipin.base.common.CommonConstants;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.common.utils.CookieUtils;
import com.ytoxl.module.yipin.base.common.utils.DateUtil;
import com.ytoxl.module.yipin.base.common.utils.PropertyUtil;
import com.ytoxl.module.yipin.base.common.utils.StringUtils;
import com.ytoxl.module.yipin.base.dataobject.Express;
import com.ytoxl.module.yipin.base.dataobject.Product;
import com.ytoxl.module.yipin.base.dataobject.ProductSku;
import com.ytoxl.module.yipin.base.dataobject.UserAddress;
import com.ytoxl.module.yipin.base.dataobject.UserInfo;
import com.ytoxl.module.yipin.base.dataobject.resultmap.ExpressMessage;
import com.ytoxl.module.yipin.base.mapper.ExpressMapper;
import com.ytoxl.module.yipin.base.service.DocNumSystemService;
import com.ytoxl.module.yipin.base.service.ExpressService;
import com.ytoxl.module.yipin.base.service.ProductService;
import com.ytoxl.module.yipin.base.service.UserAddressService;
import com.ytoxl.module.yipin.base.service.UserInfoService;
import com.ytoxl.module.yipin.order.dataobject.OrderAddress;
import com.ytoxl.module.yipin.order.dataobject.OrderAddressItem;
import com.ytoxl.module.yipin.order.dataobject.OrderCancel;
import com.ytoxl.module.yipin.order.dataobject.OrderHead;
import com.ytoxl.module.yipin.order.dataobject.OrderInvoiceAddress;
import com.ytoxl.module.yipin.order.dataobject.OrderItem;
import com.ytoxl.module.yipin.order.dataobject.OrderRefund;
import com.ytoxl.module.yipin.order.dataobject.OrderRefundExpress;
import com.ytoxl.module.yipin.order.dataobject.resultmap.OrderCheckModel;
import com.ytoxl.module.yipin.order.dataobject.resultmap.ShoppingCart;
import com.ytoxl.module.yipin.order.mapper.OrderAddressItemMapper;
import com.ytoxl.module.yipin.order.mapper.OrderAddressMapper;
import com.ytoxl.module.yipin.order.mapper.OrderCancelMapper;
import com.ytoxl.module.yipin.order.mapper.OrderHeadMapper;
import com.ytoxl.module.yipin.order.mapper.OrderInvoiceAddressMapper;
import com.ytoxl.module.yipin.order.mapper.OrderItemMapper;
import com.ytoxl.module.yipin.order.mapper.OrderRefundExpressMapper;
import com.ytoxl.module.yipin.order.mapper.OrderRefundMapper;
import com.ytoxl.module.yipin.order.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	@Value("${filterParamReg}")
	private String filterParamReg;//正则过滤参数里面的非法字符
	@Autowired
	private OrderHeadMapper<OrderHead> orderHeadMapper;
	@Autowired
	private OrderItemMapper<OrderItem> orderItemMapper;
	@Autowired
	private OrderAddressMapper<OrderAddress> orderAddressMapper;
	@Autowired
	private OrderAddressItemMapper<OrderAddressItem> orderAddressItemMapper;
	@Autowired
	private OrderInvoiceAddressMapper<OrderInvoiceAddress> orderInvoiceAddressMapper;
	@Autowired
	private ExpressMapper<Express> expressMapper;
	@Autowired
	private OrderRefundMapper<OrderRefund> orderRefundMapper;
	@Autowired
	private OrderRefundExpressMapper<OrderRefundExpress> orderRefundExpressMapper;
	@Autowired
	private OrderCancelMapper<OrderCancel> orderCancelMapper;
	
	@Autowired
	private ProductService productService;
	@Autowired
	private UserAddressService userAddressService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private DocNumSystemService docNumSystemService;
	@Autowired
	private ExpressService expressService;
	
	@Override
	public ShoppingCart addOrDelProductToCart(ShoppingCart shoppingCart,Map<String,String> params) throws YiPinStoreException {
		Map<String,Integer> cartItems  = shoppingCart.getCartItems();
		if(cartItems==null){
			cartItems = new HashMap<String,Integer>();
			shoppingCart.setCartItems(cartItems);
		}
		if(params!=null&&params.size()>0){
			String skuId = params.get("skuId");
			String skuNum = params.get("skuNum");
			if(StringUtils.isBlank(skuId)||StringUtils.isBlank(skuNum)){
				throw new YiPinStoreException(CodeConstants.CART_DATA_ERROR, "请选择要购买的商品");
			}
			Integer iSkuNum = Integer.parseInt(skuNum);
			if(iSkuNum.intValue()!=0){
				int tempNum = cartItems.get(skuId)==null?0:cartItems.get(skuId);
				int newNum = Integer.parseInt(skuNum)+tempNum;
				if(newNum>99){
					newNum = 99;
				}
				cartItems.put(skuId, newNum);
			}else{
				cartItems.remove(skuId);
			}
		}else{
			throw new YiPinStoreException(CodeConstants.CART_DATA_ERROR, "请选择要购买的商品");
		}
		Integer totalNum = 0;
		for(Iterator<String> it = cartItems.keySet().iterator();it.hasNext();){
			Integer tempNum = cartItems.get(it.next());
			totalNum = totalNum +tempNum;
		}
		shoppingCart.setTotalNum(totalNum);
		return shoppingCart;
	}
	
	@Override
	public OrderCheckModel rectifyProductCount(ShoppingCart shoppingCart,Map<String,String> params) throws YiPinStoreException {
		OrderCheckModel orderCheckModel = null;
		Map<String,Integer> cartItems = null;
		if(shoppingCart!=null){
			cartItems = shoppingCart.getCartItems();
			if(params!=null&&params.size()>0){
				String skuId = params.get("skuId");
				String skuNum = params.get("skuNum");
				ProductSku productSku = productService.getProductSkuById(Integer.parseInt(skuId));
				if(productSku!=null){
					Integer iSkuNum = Integer.parseInt(skuNum);
					if(iSkuNum>99){
						iSkuNum = 99;
					}
					if(!productSku.getIsActivity()){
						orderCheckModel = new OrderCheckModel(false, productSku.getProductSkuId(),iSkuNum,
								CodeConstants.CART_PRODUCT_DROP);
					}else{
						if(productSku.getInventory().intValue()==0){//商品已售罄
							orderCheckModel = new OrderCheckModel(false, productSku.getProductSkuId(),iSkuNum,
									CodeConstants.CART_PRODUCT_SOLDOUT);
						}
						if(iSkuNum.intValue()!=0){
							if(productSku.getInventory().intValue()>=iSkuNum.intValue()){
								cartItems.put(skuId, Integer.parseInt(skuNum));
							}else{
								cartItems.put(skuId, productSku.getInventory());
								orderCheckModel = new OrderCheckModel(false, productSku.getProductSkuId(), productSku.getInventory(),
										CodeConstants.CART_PRODUCT_SHORTAGE);
							}
						}
					}
				}else{
					throw new YiPinStoreException(CodeConstants.CART_DATA_ERROR, "数据异常");
				}
			}else{
				throw new YiPinStoreException(CodeConstants.CART_DATA_ERROR, "数据异常");
			}
			Integer totalNum = 0;
			for(Iterator<String> it = cartItems.keySet().iterator();it.hasNext();){
				Integer tempNum = cartItems.get(it.next());
				totalNum = totalNum +tempNum;
			}
			shoppingCart.setTotalNum(totalNum);
		}else{
			throw new YiPinStoreException(CodeConstants.CART_DATA_ERROR, "数据异常");
		}
		return orderCheckModel;
	}

	@Override
	public ShoppingCart getShoppingCart(HttpServletRequest request,HttpServletResponse response) throws YiPinStoreException {
		String products = CookieUtils.getCookie(request, CommonConstants.COOKIE_SHOPPINGCART);
		ShoppingCart shoppingCart = null; 
		if(StringUtils.isNotEmpty(products)){
			JSONObject object = JSONObject.fromObject(products);
			shoppingCart = (ShoppingCart)JSONObject.toBean(object, ShoppingCart.class);
			Map<String,Integer> cartItems  = shoppingCart.getCartItems();
			if(cartItems==null||cartItems.size()==0){
				shoppingCart = null;
			}
		}
		return shoppingCart;
	}
	
	@Override
	public void showCart(List<OrderHead> orders, ShoppingCart shoppingCart)throws YiPinStoreException {
		Map<String,Integer> cartItems = shoppingCart.getCartItems();
		Map<String,Integer> newCartItems = new HashMap<String,Integer>();
		List<ProductSku> productSkuList = new ArrayList<ProductSku>();
		for(Iterator<String> it = cartItems.keySet().iterator();it.hasNext();){
			String productSkuIdStr = it.next();
			Integer num = cartItems.get(productSkuIdStr);
			if(num!=null&&num.intValue()!=0){
				ProductSku productSku = productService.getProductSkuById(Integer.parseInt(productSkuIdStr));
				if(productSku!=null){
					productSku.setBuyAmount(cartItems.get(String.valueOf(productSku.getProductSkuId())));
					productSkuList.add(productSku);
					newCartItems.put(productSkuIdStr, num);
				}
			}
		}
		shoppingCart.setCartItems(newCartItems);
		checkShoppingCart(shoppingCart);
		productToOrders(orders,productSkuList);
	}

	@Override
	public void setOrdersInfo(List<OrderHead> orders) throws YiPinStoreException {
		List<ProductSku> productSkuList = new ArrayList<ProductSku>();
		if(orders!=null&&orders.size()>0){
			for(OrderHead orderHead:orders){
				List<OrderItem> orderItemList = orderHead.getItems();
				if(orderItemList!=null&&orderItemList.size()>0){
					for(OrderItem orderItem:orderItemList){
						if(orderItem.getNum()!=null&&orderItem.getNum().intValue()!=0){
							ProductSku productSku = productService.getProductSkuById(orderItem.getProductSkuId());
							if(productSku==null){
								throw new YiPinStoreException(CodeConstants.ORDER_PRODUCTSKU_ERROR, "请选择正确的商品");
							}
							productSku.setBuyAmount(orderItem.getNum());
							productSkuList.add(productSku);
						}
					}
				}
			}
			productToOrders(orders,productSkuList);
		}
	}
	
	@Override
	public List<OrderCheckModel> checkOrderProductInfo(List<OrderHead> orders)throws YiPinStoreException {
		List<OrderCheckModel> checkModelList = new ArrayList<OrderCheckModel>();
		for(OrderHead orderHead:orders){
			List<OrderItem> orderItemList = orderHead.getItems();
			for(OrderItem orderItem:orderItemList){
				ProductSku productSku = productService.getProductSkuById(orderItem.getProductSkuId());
				if((!productSku.getIsActivity())
						||productSku.getInventory().intValue()==0||orderItem.getNum().intValue()>productSku.getInventory().intValue()){
					OrderCheckModel orderCheckModel = new OrderCheckModel();
					orderCheckModel.setProductSkuId(orderItem.getProductSkuId());
					orderCheckModel.setResult(false);
					orderCheckModel.setNum(productSku.getInventory());
					if(!productSku.getIsActivity()){
						orderCheckModel.setCode(CodeConstants.CART_PRODUCT_DROP);
					}
					if(productSku.getInventory().intValue()==0){
						orderCheckModel.setCode(CodeConstants.CART_PRODUCT_SOLDOUT);
					}
					if(orderItem.getNum().intValue()>productSku.getInventory().intValue()){
						orderCheckModel.setCode(CodeConstants.CART_PRODUCT_SHORTAGE);
					}
					checkModelList.add(orderCheckModel);
				}
			}
		}
		return checkModelList;
	}

	@Override
	public void checkPanicBuyProductInfo(List<OrderHead> orders,Map<String, String> params)throws YiPinStoreException {
		if(params!=null&&params.size()>0){
			String skuId = params.get("skuId");
			if(StringUtils.isNotBlank(skuId)){
				ProductSku productSku = productService.getProductSkuById(Integer.parseInt(skuId));
				if(productSku!=null&&productSku.getIsActivity()){
					OrderHead orderHead = new OrderHead();
					OrderItem orderItem = new OrderItem();
					orderItem.setProductSkuId(productSku.getProductSkuId());
					orderItem.setNum(1);
					List<OrderItem> orderItemList = new ArrayList<OrderItem>();
					orderItemList.add(orderItem);
					orderHead.setItems(orderItemList);
					orders.clear();
					orders.add(orderHead);
				}
			}
		}
	}
	

	@Override
	public String addOrders(HttpServletRequest request,List<OrderHead> orders,Map<String,String> params) throws YiPinStoreException {
		if(orders!=null&&orders.size()>0){
			CustomUserDetails user = null;
			try {
				user = userService.getCurrentUser();
				params.put("userId", String.valueOf(user.getUserId()));
			} catch (YtoxlUserException e) {
				throw new YiPinStoreException(CodeConstants.ORDER_CREATE_PARAMETER_ERROR, "提交数据有误，请核对后再提交！");
			}
			setOrdersInfo(orders);
			checkOrderOtherInfo(orders,params);
			setOrdersRelationData(orders,params);
			List<String> orderIds = new ArrayList<String>();
			for(OrderHead orderHead:orders){
				String orderNo = docNumSystemService.getOrderNum();//生成订单号
				orderHead.setOrderNo(orderNo);//设置订单号
				orderHeadMapper.add(orderHead);
				List<OrderItem> orderItemList = orderHead.getItems();
				Map<Integer,Integer> productSkuIdOrderItemIdMap = new HashMap<Integer,Integer>();
				for(OrderItem orderItem:orderItemList){
					orderItem.setOrderId(orderHead.getOrderId());
					orderItemMapper.add(orderItem);
					productService.updateInventory4Reduce(orderItem.getProductSkuId(),orderItem.getNum());//减库存
					productSkuIdOrderItemIdMap.put(orderItem.getProductSkuId(), orderItem.getOrderItemId());
				}
				OrderAddress orderAddress = orderHead.getOrderAddress();
				orderAddress.setPackageNo(String.format("%1$03d",1));
				orderAddress.setOrderId(orderHead.getOrderId());
				orderAddressMapper.add(orderAddress);
				
				List<OrderAddressItem> orderAddressItemList = orderAddress.getOrderAddressItems();
				for(OrderAddressItem orderAddressItem:orderAddressItemList){
					if(orderAddressItem.getAssignCount()!=0){
						orderAddressItem.setOrderId(orderHead.getOrderId());
						orderAddressItem.setOrderItemId(productSkuIdOrderItemIdMap.get(orderAddressItem.getProductSkuId()));
						orderAddressItem.setOrderAddressId(orderAddress.getOrderAddressId());
						orderAddressItemMapper.add(orderAddressItem);
					}
				}
				orderIds.add(String.valueOf(orderHead.getOrderId()));
			}
			return org.apache.commons.lang3.StringUtils.join(orderIds, "|");
		}else{
			return null;
		}
	}
	
	@Override
	public void deleteHaveBuyProduct(List<OrderHead> orders,ShoppingCart shoppingCart) throws YiPinStoreException {
		Map<String,Integer> cartItemMap = shoppingCart.getCartItems();
		if(cartItemMap!=null&&cartItemMap.size()>0){
			for(OrderHead orderHead:orders){
				if(orderHead!=null){
					List<OrderItem> orderItemList = orderHead.getItems();
					if(orderItemList!=null){
						for(OrderItem orderItem:orderItemList){
							if(orderItem!=null&&orderItem.getProductSkuId()!=null){
								cartItemMap.remove(String.valueOf(orderItem.getProductSkuId()));
							}
						}
					}
				}
			}
			Integer totalNum = 0;
			for(Iterator<String> it = cartItemMap.keySet().iterator();it.hasNext();){
				Integer tempNum = cartItemMap.get(it.next());
				totalNum = totalNum +tempNum;
			}
			shoppingCart.setTotalNum(totalNum);
		}
	}

	/**
	 * 我的订单
	 * @param orderPage
	 * @throws YiPinStoreException 
	 */
	public void searchOrder4Front(BasePagination<OrderHead> orderPage) throws YiPinStoreException{
		//过滤非法字符
		orderPage.setFilterParamReg(filterParamReg);
		Map<String, Object> searchParams = orderPage.getSearchParamsMap();
		//获取当前用户  如果为null  可以查询到其他用户的数据 故默认为-1  0 mybtis if tset标签做了判断 源码在IfSqlNode类
		Integer userId = -1;
		try {
			userId = userService.getCurrentUser().getUserId();
		} catch (YtoxlUserException e) {
			logger.error("我的订单获取当前用户出错:",e);
		}
		searchParams.put("userId", userId);
		//如果orderType 为空 默认为所有订单类型 TODO
		String orderType = (String)searchParams.get("orderType");
		if(CommonConstants.CONSTANT_ZERO.equals(orderType)){
			searchParams.remove("orderType");
		}
		//如果status 为空默认全部订单
		String s = (String)searchParams.get("status");
		if(CommonConstants.CONSTANT_ZERO.equals(s)){
			searchParams.remove("status");
		}
		//1 = 查询最近一个月还是  2 = 一个月以前的数据 0 = 所有时间订单
		String qt = (String)searchParams.get("queryTime");
		Date queryTime = DateUtil.add(new Date(), Calendar.MONTH, -1);
		if(StringUtils.isNotEmpty(qt) && OrderHead.A_MONTH_AGO.equals(Short.parseShort(qt))){
			//一个月之前
			searchParams.put("queryTimeOperator", "<=");
			searchParams.put("queryTime", queryTime);
		}else if(StringUtils.isNotEmpty(qt) && OrderHead.ALL_TIME.equals(Short.parseShort(qt))){
			//如果没有选中了 所有时间订单 将条件存到查询中 TODO
			searchParams.remove("queryTimeOperator");
			searchParams.remove("queryTime");
		}else{
			//最近一个月  默认是最近一个月的
			searchParams.put("queryTimeOperator", ">");
			searchParams.put("queryTime", queryTime);
		}
		searchParams.put(CommonConstants.PAGE_SORT, "head.createTime");
		searchParams.put(CommonConstants.PAGE_DIR, "desc");
		if (orderPage.isNeedSetTotal()) {
			Integer total = orderHeadMapper.searchOrder4FrontCount(searchParams);
			orderPage.setTotal(total);
		}
		Collection<OrderHead> result = orderHeadMapper.searchOrder4Front(searchParams);
		if (result != null) {
			for(OrderHead head : result){
				//1.查询sku数量  2.查询包裹数量
				Integer orderId = head.getOrderId();
				Integer skuCount = orderItemMapper.getOrderProductSkuNumByOrderId(orderId);
				Integer packageCount = orderAddressMapper.getOrderPackageNumByOrderId(orderId);
				head.setSkuCount(skuCount);
				head.setPackageNum(packageCount);
				//一次加载所有包裹信息 2013-12-13 17:42:59
				List<OrderAddress> addrs = listOrderPackagesByOrderId(orderId);
				head.setAddressItems(addrs);
				//前台是否显示取消并退款
				OrderCancel orderCancel = orderCancelMapper.getOrderCancelByOrderId(orderId);
				if(null == orderCancel){
					head.setDisplayOrderCancel(true);
				}
				head.setOrderCancel(orderCancel);
			}
			orderPage.setResult(result);
		}
		//设置分页参数
		Map<String,String> m = orderPage.getParams();
		if(null == m){
			m = new HashMap<String,String>();
		}
		//查询待付款和待确认收货的数量 TODO 一个月之前的数据???
		Integer orderStatusNum4WaitPay = orderHeadMapper.getOrderNumByStatusAndUserId(userId, OrderHead.STATUS_WAITPAY);
		Integer orderStatusNum4WaitReceive = orderHeadMapper.getOrderNumByStatusAndUserId(userId, OrderHead.STATUS_SEND);
		m.put("orderStatusNum4WaitPay", orderStatusNum4WaitPay.toString());
		m.put("orderStatusNum4WaitReceive", orderStatusNum4WaitReceive.toString());
		String ss = (String)searchParams.get("status");
		if(null == ss || StringUtils.isEmpty(ss) || qt == null || orderType == null){
			if(null == m.get("status") || StringUtils.isEmpty(m.get("status"))){
				m.put("status", CommonConstants.CONSTANT_ZERO);
			}
			//默认是最近一个月的
			if(null == m.get("queryTime")){
				m.put("queryTime", OrderHead.THE_LAST_MONTH.toString());
			}
			//默认全部类型订单
			if(null == m.get("orderType")){
				m.put("orderType", CommonConstants.CONSTANT_ZERO);
			}
			orderPage.setParams(m);
		}
		orderPage.setParams(m);
	}
	
	/**
	 * 我的订单包裹明细
	 */
	public List<OrderAddress> listOrderPackagesByOrderId(Integer orderId){
		List<OrderAddress> list = null;
		list = orderAddressMapper.listOrderPackagesByOrderId(orderId);
		for(OrderAddress addr : list){
			Integer orderAddressId = addr.getOrderAddressId();
			List<OrderAddressItem> orderAddressItems = orderAddressItemMapper.listOrderAddressItemByOrderAddressId(orderAddressId);
			addr.setOrderAddressItems(orderAddressItems);
			//是否可以退货 
			for(OrderAddressItem addrItem : orderAddressItems){
				//boolean isCanRefund = judgeCanRefund(orderAddressId);
				Integer orderItemId = addrItem.getOrderItem().getOrderItemId();
				boolean isCanRefund = judgeOrderItemCanRefund(orderItemId);
				//addr.setCanRefund(isCanRefund);
				addrItem.getOrderItem().setCanRefund(isCanRefund);
				//如果不能退货     原因是已经退过了 或者已经退货状态(前台显示退货的状态)或者订单不是已经完成的状态(前台不显示退货的状态)  
				if(! isCanRefund){
					//OrderRefund refund = orderRefundMapper.getOrderRefundByOrderAddressId(orderAddressId);
					OrderRefund refund = orderRefundMapper.getOrderRefundByOrderItemId(orderItemId);
					//除已完成状态
					if(null != refund){
						addrItem.getOrderItem().setRefundStatusInfo(PropertyUtil.getPropertyValue("order.refund.status."+refund.getStatus(), null));
					}
				}
			}
			//显示确认收获按钮 TODO
			OrderHead orderHead = orderHeadMapper.get(orderId);
			addr.setOrderHead(orderHead);
		}
		return list;
	}
	@Override
	public OrderAddress getOrderAddressByOrderAddressId(Integer orderAddressId) throws YiPinStoreException {
		//包裹信息
		OrderAddress orderAddress = orderAddressMapper.getOrderAddressByOrderAddressId(orderAddressId);
		//包裹中的商品信息
		if(null != orderAddress){
			//包裹商品明细
			List<OrderAddressItem> orderAddressItems = orderAddressItemMapper.listOrderAddressItemByOrderAddressId(orderAddress.getOrderAddressId());
			orderAddress.setOrderAddressItems(orderAddressItems);
			//查询发票信息
			OrderInvoiceAddress orderInvoiceAddress = orderInvoiceAddressMapper.getOrderInvoiceAddressByOrderId(orderAddress.getOrderHead().getOrderId());
			orderAddress.getOrderHead().setOrderInvoiceAddress(orderInvoiceAddress);
		}
		//获取物流信息
		if(StringUtils.isNotEmpty(orderAddress.getExpressNo()) && StringUtils.isNotEmpty(orderAddress.getExpressName())){
			//查询物流信息的code
			Express express = (Express) expressMapper.get(orderAddress.getExpressId());
			//改用ajax
			//ExpressMessage msg = expressService.getExpressDetailInfoFromKuaidi100(express.getCompanyCode(), orderAddress.getExpressNo());
			orderAddress.setExpress(express);
			//orderAddress.setExpressMessage(msg);
		}
		return orderAddress;
	}
	
	/**
	 * 根据快递公司的expressId 和面单好查询物流信息
	 * @param expressId
	 * @param mailNo
	 * @return
	 * @throws YiPinStoreException
	 */
	public ExpressMessage getExpressMessageByExpressIdAndMailNo(Integer expressId,String mailNo,Integer orderId) throws YiPinStoreException{
		/*//获取订单信息
		OrderHead orderHead = orderHeadMapper.get(orderId);
		Map<String, String> map = new LinkedHashMap<String, String> ();
		if(null != orderHead){
			map.put(DateUtil.toSeconds(orderHead.getCreateTime()), "订单提交成功");
			map.put(DateUtil.toSeconds(orderHead.getPayTime()), "订单支付成功");
			//如果订单取消了显示updateTime 取消时间
			if(OrderHead.STATUS_CANCEL.equals(orderHead.getStatus())){
				map.put(DateUtil.toSeconds(orderHead.getUpdateTime()), "订单已取消");
			}
		}*/
		//获取物流信息
		ExpressMessage msg = new ExpressMessage();
		if(StringUtils.isNotEmpty(mailNo) && null != expressId){
			//查询物流信息的code
			Express express = (Express) expressMapper.get(expressId);
			try {
				if(null != express){
					msg = expressService.getExpressDetailInfoFromKuaidi100(express.getCompanyCode(), mailNo);
				}
			} catch (YiPinStoreException e) {
				logger.error("查询物流信息出错:",e);
				throw new YiPinStoreException("查询物流信息出错");
			}
		}
		//msg.setOrderMsg(map);
		return msg;
	}
	
	/**
	 * 根据orderAddressId 查询包裹信息 (包括商品明细) 退货订单填写表单信息页面使用
	 */
	@Override
	public OrderItem getOrderInfoByOrderItemId4RefundForm(Integer orderItemId) throws YiPinStoreException {
		OrderItem item = orderItemMapper.getOrderItemInfoByOrderItemId(orderItemId);
		return item;
	}
	
	/**
	 * 前台我的退货管理
	 */
	@Override
	public void searchRefundOrder4Front(BasePagination<OrderHead> orderPage) throws YiPinStoreException {
		//过滤非法字符
		orderPage.setFilterParamReg(filterParamReg);
		Map<String, Object> searchParams = orderPage.getSearchParamsMap();
		//获取当前用户  如果为null  可以查询到其他用户的数据 故默认为-1
		Integer userId = -1;
		try {
			userId = userService.getCurrentUser().getUserId();
		} catch (YtoxlUserException e) {
			logger.error("我的订单获取当前用户出错:",e);
		}
		searchParams.put("userId", userId);
		searchParams.put(CommonConstants.PAGE_SORT, "head.createTime");
		searchParams.put(CommonConstants.PAGE_DIR, "desc");
		if (orderPage.isNeedSetTotal()) {
			Integer total = orderRefundMapper.searchRefundOrder4FrontCount(searchParams);
			orderPage.setTotal(total);
		}
		Collection<OrderHead> result = orderRefundMapper.searchRefundOrder4Front(searchParams);
		if (result != null) {
			for(OrderHead head : result){
				//1.查询sku数量  2.查询包裹数量
				Integer orderId = head.getOrderId();
				Integer skuCount = orderItemMapper.getOrderProductSkuNumByOrderId(orderId);
				Integer packageCount = orderAddressMapper.getOrderPackageNumByOrderId(orderId);
				head.setSkuCount(skuCount);
				head.setPackageNum(packageCount);
			}
			orderPage.setResult(result);
		}
	}
	
	@Override
	public void saveOrderRefundInfo(OrderRefund orderRefund)throws YiPinStoreException {
		Integer orderItemId = orderRefund.getOrderItemId();
		//查询orderItemId 在order_item是否有数据
		OrderItem orderItem = orderItemMapper.get(orderItemId);
		if(null == orderItem){
			throw new YiPinStoreException("没有此订单项");
		}
		//查询是否以前是否提交过
		OrderRefund refund = orderRefundMapper.getOrderRefundByOrderItemId(orderItemId);
		if(null != refund){
			throw new YiPinStoreException("已经退过了");
		}else{
			//退货编号  t+毫秒数
			String refundNo = CommonConstants.REFUND_NO_PRE + System.currentTimeMillis();
			orderRefund.setOrderRefundNo(refundNo);
			//默认退货状态是 未审核
			orderRefund.setStatus(OrderRefund.STATUS_NOTAUDIT);
			Integer orderId = orderItem.getOrderId();
			if(orderId == null || ! orderId.equals(orderRefund.getOrderId())){
				//如果用户修改了orderRefund 中的orderId
				throw new YiPinStoreException("没有此订单号(orderId)"+orderId);
			}
			int row = orderRefundMapper.add(orderRefund);
			//TODO 查询是否所有包裹都退货   若都退货了  订单状态变更成  退货状态
			//通过orderId查询到order_address 中的包裹数 弃用 v1.0
			//1.通过orderId查询到order_item 中的订单项数量
			//2.通过ordeId查询到order_refund 中的退货记录数量
			//3.如果order_refund中的记录与order_address记录相等则将order_head status 更新成 退货     同一个事务 可以查询到
			Integer itemNum = orderItemMapper.countOrderItemNumByOrderId(orderId);
			Integer refundItemNum = orderRefundMapper.countRefundOrderItemNumByorderId(orderId);
			if(row > 0 && (refundItemNum.intValue() == itemNum.intValue())){
				//更新order_head status 更新成 退货
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("status", OrderHead.STATUS_RETURN);
				map.put("orderId", orderId);
				orderHeadMapper.updateOrderStatus(map);
			}
		}
	}
	
	@Override
	public void searchRefundRecords4Front(BasePagination<OrderRefund> refundPage) throws YiPinStoreException {
		//过滤非法字符
		refundPage.setFilterParamReg(filterParamReg);
		Map<String, Object> searchParams = refundPage.getSearchParamsMap();
		//获取当前用户  如果为null  可以查询到其他用户的数据 故默认为-1
		Integer userId = -1;
		try {
			userId = userService.getCurrentUser().getUserId();
		} catch (YtoxlUserException e) {
			logger.error("我的订单获取当前用户出错:",e);
		}
		searchParams.put("userId", userId);
		searchParams.put(CommonConstants.PAGE_SORT, "refund.createTime");
		searchParams.put(CommonConstants.PAGE_DIR, "desc");
		// TODO 
		if (refundPage.isNeedSetTotal()) {
			Integer total = orderRefundMapper.searchRefundRecords4FrontCount(searchParams);
			refundPage.setTotal(total);
		}
		Collection<OrderRefund> result = orderRefundMapper.searchRefundRecords4Front(searchParams);
		//获取退货包裹中的商品信息
		for(OrderRefund refund : result){
			/*List<OrderAddressItem> list = orderAddressItemMapper.listOrderAddressItemByOrderAddressId(refund.getOrderAddressId());
			StringBuilder sb = new StringBuilder("");
			for(OrderAddressItem addrItem : list){
				sb.append(addrItem.getOrderItem().getProductName());
				sb.append(CommonConstants.STR_SPLIT);
			}
			refund.setRefundProductNames(sb.toString());*/
			OrderItem orderItem = orderRefundMapper.getOrderRefundByOrderItemId(refund.getOrderItemId()).getOrderItem();
			refund.setOrderItem(orderItem);
		}
		refundPage.setResult(result);
	}
	
	/**
	 * 通过orderRefundId 获取所退货物中商家信息
	 */
	@Override
	public UserInfo getSellerInfoByorderRefundId(Integer orderRefundId)throws YiPinStoreException {
		List<OrderAddressItem> items = orderAddressItemMapper.listOrderAddressItemByOrderRefundId(orderRefundId);
		UserInfo userInfo = null;
		if(null != items && items.size() > 0){
			Integer userId = items.get(0).getOrderItem().getSellerId();
			//userInfo = userInfoService.getUserInfoByUserIdAndType(userId, UserInfo.USER_TYPE_SELLER);
			userInfo = userInfoService.getSellerByUserId(userId, false);
		}
		return userInfo;
	}
	
	@Override
	public void saveRefundExpressInfo(OrderRefundExpress orderRefundExpress) throws YiPinStoreException {
		Integer orderRefundId = orderRefundExpress.getOrderRefundId();
		OrderRefund refund = orderRefundMapper.get(orderRefundId);
		if(null == refund){
			throw new YiPinStoreException("没有这个退货订单(orderRefundId):"+orderRefundId);
		}
		//先通过id查询  如果id查不到再通过name查询
		Integer expId = orderRefundExpress.getExpressId();
		Express exp = null;
		if(null != expId){
			exp = expressMapper.get(orderRefundExpress.getExpressId());
		}
		if(null != exp){
			orderRefundExpress.setExpressId(exp.getExpressId());
		}else{
			//保存快递 基础expressId
			List<Express> list = expressMapper.searchExpress(orderRefundExpress.getExpressName());
			if(null != list && list.size() > 0){
				//若返回多个  默认取第一个
				Express express = list.get(0);
				orderRefundExpress.setExpressId(express.getExpressId());
			}
		}
		OrderRefundExpress refundExpress = orderRefundExpressMapper.getOrderRefundExpressByOrderRefundId(orderRefundId);
		Integer row = 0;
		if(null == refundExpress){
			//若没有插入
			row = orderRefundExpressMapper.add(orderRefundExpress);
		}else{
			//若有更新
			orderRefundExpress.setOrderRefundExpressId(refundExpress.getOrderRefundExpressId());
			row = orderRefundExpressMapper.update(orderRefundExpress);
		}
		if(row <= 0){
			throw new YiPinStoreException("保存退后订单快递信息出错(orderRefundId):"+orderRefundId);
		}
		//更新退货状态
		row = 0;
		refund.setStatus(OrderRefund.STATUS_RESEND);
		row = orderRefundMapper.update(refund);
		if(row <= 0){
			throw new YiPinStoreException("保存退后订单快递信息更新退货订单状态出错(orderRefundId):"+orderRefundId);
		}
	}
	
	@Override
	public OrderInvoiceAddress getInvoiceDetailByorderId(Integer orderId) throws YiPinStoreException {
		OrderInvoiceAddress invoice = orderInvoiceAddressMapper.getOrderInvoiceDetailByOrderId(orderId);
		//物流信息 改用ajax
		/*Integer expressId = invoice.getExpressId();
		if(null != invoice && null != expressId){
			Express express = (Express) expressMapper.get(expressId);
			ExpressMessage msg = expressService.getExpressDetailInfoFromKuaidi100(express.getCompanyCode(), invoice.getExpressNo());
			invoice.setExpressMessage(msg);
		}*/
		return invoice;
	}
	
	/**
	 * 前台我的退款管理
	 */
	@Override
	public void searchCancelOrder4Front(BasePagination<OrderHead> orderPage) throws YiPinStoreException {
		//过滤非法字符
		orderPage.setFilterParamReg(filterParamReg);
		Map<String, Object> searchParams = orderPage.getSearchParamsMap();
		//获取当前用户  如果为null  可以查询到其他用户的数据 故默认为-1
		Integer userId = -1;
		try {
			userId = userService.getCurrentUser().getUserId();
		} catch (YtoxlUserException e) {
			logger.error("我的订单退款管理获取当前用户出错:",e);
		}
		searchParams.put("userId", userId);
		searchParams.put(CommonConstants.PAGE_SORT, "head.createTime");
		searchParams.put(CommonConstants.PAGE_DIR, "desc");
		if (orderPage.isNeedSetTotal()) {
			Integer total = orderHeadMapper.searchCancelOrder4FrontCount(searchParams);
			orderPage.setTotal(total);
		}
		Collection<OrderHead> result = orderHeadMapper.searchCancelOrder4Front(searchParams);
		if (result != null) {
			for(OrderHead head : result){
				//1.查询sku数量  2.查询包裹数量
				Integer orderId = head.getOrderId();
				Integer skuCount = orderItemMapper.getOrderProductSkuNumByOrderId(orderId);
				//Integer packageCount = orderAddressMapper.getOrderPackageNumByOrderId(orderId);
				head.setSkuCount(skuCount);
				//head.setPackageNum(packageCount);
				//前台是否显示取消并退款
				OrderCancel orderCancel = orderCancelMapper.getOrderCancelByOrderId(orderId);
				if(null == orderCancel){
					head.setDisplayOrderCancel(true);
				}
				head.setOrderCancel(orderCancel);
				/*//获取订单发货信息  已经在查询时关联了
				List<OrderAddress> list = orderAddressMapper.listOrderAddressByOrderId(orderId);
				head.setAddressItems(list);*/
			}
			orderPage.setResult(result);
		}
	}
	
	/**
	 * 退款记录
	 */
	@Override
	public void searchCancelOrderRecords4Front(BasePagination<OrderCancel> cancelPage) throws YiPinStoreException {
		//过滤非法字符
		cancelPage.setFilterParamReg(filterParamReg);
		Map<String, Object> searchParams = cancelPage.getSearchParamsMap();
		//获取当前用户  如果为null  可以查询到其他用户的数据 故默认为-1
		Integer userId = -1;
		try {
			userId = userService.getCurrentUser().getUserId();
		} catch (YtoxlUserException e) {
			logger.error("我的订单退款记录获取当前用户出错:",e);
		}
		searchParams.put("userId", userId);
		searchParams.put(CommonConstants.PAGE_SORT, "cancel.createTime");
		searchParams.put(CommonConstants.PAGE_DIR, "desc");
		if (cancelPage.isNeedSetTotal()) {
			Integer total = orderCancelMapper.searchCancelOrderRecords4FrontCount(searchParams);
			cancelPage.setTotal(total);
		}
		Collection<OrderCancel> result = orderCancelMapper.searchCancelOrderRecords4Front(searchParams);
		/*for(OrderCancel cancel : result){
			//TODO
			
		}*/
		cancelPage.setResult(result);
	}
	
	/**
	 * 填写退款申请
	 */
	@Override
	public OrderHead getOrderInfoByOrderId4OrderCancelForm(Integer orderId) throws YiPinStoreException {
		//获取当前用户  如果为null  可以查询到其他用户的数据 故默认为-1
		Integer userId = -1;
		try {
			userId = userService.getCurrentUser().getUserId();
		} catch (YtoxlUserException e) {
			logger.error("我的订单退款记录获取当前用户出错:",e);
		}
		OrderHead head = orderHeadMapper.getOrderInfoByOrderIdAndUserId(userId, orderId);
		if(null != head){
			//查询明细
			List<OrderItem> list = orderItemMapper.listOrderItemByOrderId4Simple(orderId);
			head.setItems(list);
			Integer skuCount = orderItemMapper.getOrderProductSkuNumByOrderId(orderId);
			head.setSkuCount(skuCount);
		}
		return head;
	}
	
	/**
	 * 买家确认收货
	 */
	@Override
	public void saveConfirmGoods(Integer orderAddressId) throws YiPinStoreException {
		//更新包裹状态为已经收货
		Integer row = orderAddressMapper.updateOrderPackageStatusByOrderAdderssId(orderAddressId, OrderAddress.STATUS_RECEIVED);
		Integer orderId = orderAddressMapper.get(orderAddressId).getOrderId();
		//订单中所有的包裹数量
		Integer packageNum = orderAddressMapper.countProductPackageNumByOrderId(orderId);
		Integer receivePackageNum = orderAddressMapper.countReceiveProductPackageNumByOrderId(orderId);
		//在同一个事务中可以查到上面更新的数据
		if(row > 0 && (receivePackageNum .intValue() == packageNum.intValue())){
			//如果是最后一个包裹确认收货更新订单状态
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("orderId",orderId);
			map.put("status",OrderHead.STATUS_FINISHED);
			orderHeadMapper.updateOrderStatus(map);
		}
	}
	
	@Override
	public void saveCancelOrder(Integer orderId) throws YiPinStoreException {
		//更新订单状态 
		List<OrderItem> orderItemList = orderItemMapper.listOrderItemsByOrderId(orderId);
		Integer row = orderHeadMapper.updateCancelOrderByOrderId(orderId);
		if(orderItemList!=null&&orderItemList.size()>0){//归还库存
			for(OrderItem orderItem:orderItemList){
				productService.updateInventory4Revert(orderItem.getProductSkuId(), orderItem.getNum());
			}
		}
		if(null != row && row.intValue() == 0){
			throw new YiPinStoreException("取消订单出错");
		}
	}
	
	@Override
	public void saveDeleteOrder(Integer orderId) throws YiPinStoreException {
		Integer row = orderHeadMapper.updateDelOrderByOrderId(orderId);
		if(null == row || row.intValue() == 0){
			throw new YiPinStoreException("逻辑删除订单出错");
		}
	}
	
	@Override
	public boolean saveBathConfrimReceive(String orderIds) throws YiPinStoreException {
		Integer userId = -1;
		try {
			userId = userService.getCurrentUser().getUserId();
		} catch (YtoxlUserException e) {
			logger.error("我的订单获取当前用户出错:",e);
			return false;
		}
		String[] ids = orderIds.split("\\|");
		List<Integer> list = new ArrayList<Integer>();
		List<Integer> listAddr = new ArrayList<Integer>();
		for(String id : ids ){
			Integer orderId = Integer.parseInt(id);
			//判断是否已发货
			//如果状态不是已经发货 提示
			OrderHead orderHead = orderHeadMapper.getOrderInfoByOrderIdAndUserId(userId, orderId);
			//订单是已发货  包裹是未收货
			if(null != orderHead && OrderHead.STATUS_SEND.equals(orderHead.getStatus()) && OrderAddress.STATUS_NOTRECEIVE.equals(orderHead.getOrderAddress().getIsReceive())){
				list.add(orderId);
				listAddr.add(orderHead.getOrderAddress().getOrderAddressId());
			}
		}
		//确认收货操作 TODO
		if(list.size() >0 && list.size() == listAddr.size()){
			int r1 = orderHeadMapper.updateBathOrderStatus(list, OrderHead.STATUS_FINISHED);
			int r2 = orderAddressMapper.updateBathOrderPacekageStatus(listAddr, OrderAddress.STATUS_RECEIVED);
			if(r1 == r2){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Integer getOrderNumByStatusAndUserId(Integer userId, Short status)throws YiPinStoreException {
		return orderHeadMapper.getOrderNumByStatusAndUserId(userId, status);
	}
	
	/**
	 * 保存退款信息
	 */
	@Override
	public void saveCancelOrderInfo(OrderCancel orderCancel) throws YiPinStoreException {
		// TODO 
		//验证数据的正确性
		Integer orderId = orderCancel.getOrderId();
		if(null == orderId){
			throw new YiPinStoreException("订单id不能为空");
		}
		OrderHead head = orderHeadMapper.get(orderId);
		if(null == head){
			throw new YiPinStoreException("没有此订单");
		}
		OrderCancel cancel = orderCancelMapper.getOrderCancelByOrderId(orderId);
		if(null != cancel){
			throw new YiPinStoreException("此订单已经申请过退款");
		}
		//生成退款编号
		String cancelNo = CommonConstants.CANCEL_NO_PRE + System.currentTimeMillis();
		orderCancel.setStatus(OrderCancel.STATUS_AUDIT);
		orderCancel.setOrderCancelNo(cancelNo);
		int row = orderCancelMapper.add(orderCancel);
		if(row <= 0){
			throw new YiPinStoreException("订单退款保存成功");
		}
	}
	
	/**
	 * 
	 * @param orders
	 * @param productSkuList
	 * @throws YiPinStoreException
	 */
	private void productToOrders(List<OrderHead> orders,List<ProductSku> productSkuList) throws YiPinStoreException{
		Map<String,List<ProductSku>> sellerIdSellTypeProductSkuMap = new HashMap<String,List<ProductSku>>();
		for(ProductSku prodcutSku:productSkuList){
			String key = String.valueOf(prodcutSku.getProduct().getUserId())+"-"+String.valueOf(prodcutSku.getProduct().getSellType());
			List<ProductSku> tempProductSkuList = sellerIdSellTypeProductSkuMap.get(key);
			if(tempProductSkuList==null){
				tempProductSkuList = new ArrayList<ProductSku>();
			}
			tempProductSkuList.add(prodcutSku);
			sellerIdSellTypeProductSkuMap.put(key, tempProductSkuList);
		}
		List<OrderHead> orderHeadList = splitOrdersByMerchant(sellerIdSellTypeProductSkuMap);
		if(orders.size()==orderHeadList.size()){
			for(int i=0;i<orderHeadList.size();i++){
				OrderHead oh = orders.get(i);
				if(oh!=null&&StringUtils.isNotBlank(oh.getOrderRemark())){
					orderHeadList.get(i).setOrderRemark(oh.getOrderRemark());
				}
			}
		}
		orders.clear();
		orders.addAll(orderHeadList);
	}
	
	/**
	 * 根据商家分拆订单那
	 * @throws YiPinStoreException
	 */
	private List<OrderHead> splitOrdersByMerchant(Map<String,List<ProductSku>> sellerIdSellTypeProductSkuMap)  throws YiPinStoreException{
		List<OrderHead> orderHeadList = new ArrayList<OrderHead>();
		for(Iterator<String> it = sellerIdSellTypeProductSkuMap.keySet().iterator();it.hasNext();){
			String key = it.next();
			List<ProductSku> tempProductSkuList = sellerIdSellTypeProductSkuMap.get(key);
			String arr[] = key.split("-");
			UserInfo userInfo = userInfoService.getUserInfoById(Integer.parseInt(arr[0]));
			if(OrderHead.ORDER_SOURCE_GENERAL.equals(Short.parseShort(arr[1]))){//普通订单
				List<OrderItem> orderItemList = new ArrayList<OrderItem>();
				for(ProductSku productSku:tempProductSkuList){
					OrderItem orderItem = new OrderItem();
					copyProductSkuToOrderItem(productSku,orderItem);
					orderItemList.add(orderItem);
				}
				OrderHead orderHead = new OrderHead();
				orderHead.setDeliveryAddress(userInfo.getShiperAddress());
				orderHead.setItems(orderItemList);
				orderHead.setOrderType(Short.parseShort(arr[1]));
				orderHeadList.add(orderHead);
			}else if(OrderHead.ORDER_SOURCE_BOOK.equals(Short.parseShort(arr[1]))){//预售订单
				Map<Integer,List<ProductSku>> productIdProductSkusMap = new HashMap<Integer,List<ProductSku>>();
				for(ProductSku productSku:tempProductSkuList){
					Integer productId = productSku.getProductId();
					List<ProductSku> productSkuList = productIdProductSkusMap.get(productId);
					if(productSkuList==null){
						productSkuList = new ArrayList<ProductSku>();
					}
					productSkuList.add(productSku);
					productIdProductSkusMap.put(productId, productSkuList);
				}
				Date preDate = null;
				for(Iterator<Integer> itor=productIdProductSkusMap.keySet().iterator();itor.hasNext();){
					List<ProductSku>  bookProductSkuList = productIdProductSkusMap.get(itor.next());
					OrderHead orderHead = new OrderHead();
					List<OrderItem> orderItemList = new ArrayList<OrderItem>();
					for(ProductSku productSku:bookProductSkuList){
						preDate = productSku.getProduct().getPreDeliveryTime();
						OrderItem orderItem = new OrderItem();
						copyProductSkuToOrderItem(productSku,orderItem);
						orderItemList.add(orderItem);
					}
					orderHead.setDeliveryAddress(userInfo.getShiperAddress());
					orderHead.setItems(orderItemList);
					orderHead.setPredictSendTime(preDate);
					orderHead.setOrderType(Short.parseShort(arr[1]));
					
					orderHeadList.add(orderHead);
				}
			}else if(OrderHead.ORDER_SOURCE_GRAP.equals(Short.parseShort(arr[1]))){//抢购订单
				for(ProductSku productSku:tempProductSkuList){
					List<OrderItem> orderItemList = new ArrayList<OrderItem>();
					OrderItem orderItem = new OrderItem();
					copyProductSkuToOrderItem(productSku,orderItem);
					orderItemList.add(orderItem);
					OrderHead orderHead = new OrderHead();
					orderHead.setDeliveryAddress(userInfo.getShiperAddress());
					orderHead.setItems(orderItemList);
					orderHead.setOrderType(Short.parseShort(arr[1]));
					orderHeadList.add(orderHead);
				}
			}
		}
		return orderHeadList;
	}
	/**
	 * 检查订单除商品以外的数据是否正确
	 * @param params
	 * @throws YiPinStoreException
	 */
	private void checkOrderOtherInfo(List<OrderHead> orders,Map<String, String> params) throws YiPinStoreException{
		if(StringUtils.isBlank(params.get("userAddressId"))){//检查收货地址
			throw new YiPinStoreException(CodeConstants.ORDER_CREATE_PARAMETER_ERROR, "提交数据有误，未选择收货地址，请核对后再提交！");
		}
		if(StringUtils.isBlank(params.get("hasInvoice"))){//检查发票
			throw new YiPinStoreException(CodeConstants.ORDER_CREATE_PARAMETER_ERROR, "提交数据有误，发票信息未选择，请核对后再提交！");
		}
		if(StringUtils.isBlank(params.get("payType"))){//检查支付类型
			throw new YiPinStoreException(CodeConstants.ORDER_CREATE_PARAMETER_ERROR, "提交数据有误，未选择支付类型，请核对后再提交！");
		}
		for(OrderHead orderHead:orders){
			for(OrderItem orderItem:orderHead.getItems()){
				if(!orderItem.getProductSku().getIsActivity()||orderItem.getNum()==0){
					throw new YiPinStoreException(CodeConstants.ORDER_CREATE_PARAMETER_ERROR, "提交数据有误，未选择支付类型，请核对后再提交！");
				}
			}
		}
	}
	/**
	 * 设置订单关联数据
	 * @param orders
	 * @param params
	 * @throws YiPinStoreException
	 */
	private void setOrdersRelationData(List<OrderHead> orders,Map<String, String> params) throws YiPinStoreException{
		UserAddress userAddress = userAddressService.getUserAddressByUserIdAndId(Integer.parseInt(params.get("userAddressId")),Integer.parseInt(params.get("userId")));
		if(userAddress==null){
			throw new YiPinStoreException(CodeConstants.ORDER_CREATE_PARAMETER_ERROR, "提交数据有误，未选择支付类型，请核对后再提交！");
		}
		for(OrderHead orderHead:orders){
			orderHead.setPayType(Short.parseShort(params.get("payType")));
			orderHead.setHasInvoice(Short.parseShort(params.get("hasInvoice")));
			orderHead.setInvoiceTitle(params.get("invoiceTitle"));
			orderHead.setStatus(OrderHead.STATUS_WAITPAY);
			orderHead.setUserId(Integer.parseInt(params.get("userId")));
			orderHead.setPayStatus(OrderHead.PAY_STATUS_NO);
			orderHead.setIsDelete(OrderHead.DELETE_NO);
			BigDecimal totlePrice = new BigDecimal(0);//订单总价
			List<OrderAddressItem> orderAddressItemList = new ArrayList<OrderAddressItem>();
			for(OrderItem orderItem:orderHead.getItems()){
				OrderAddressItem orderAddressItem = new OrderAddressItem();
				orderAddressItem.setProductSkuId(orderItem.getProductSkuId());
				orderAddressItem.setAssignCount(orderItem.getNum());
				orderAddressItemList.add(orderAddressItem);
				totlePrice = totlePrice.add(orderItem.getUnitPrice().multiply(new BigDecimal(orderItem.getNum())));//
			}
			orderHead.setPaymentAmount(totlePrice);
			orderHead.setOriginalAmount(totlePrice);
			
			OrderAddress orderAddress = new OrderAddress();
			BeanUtils.copyProperties(userAddress, orderAddress, new String[]{"createTime","updateTime","sendProductTime"});
			orderHead.setOrderAddress(orderAddress);
			orderAddress.setOrderAddressItems(orderAddressItemList);
			orderAddress.setPackageTotalPrice(totlePrice);
		}
	}
	/**
	 * 将productSku中的部分值copy到orderItem中
	 * @param productSku
	 * @param orderItem
	 * @throws YiPinStoreException
	 */
	private void copyProductSkuToOrderItem(ProductSku productSku,OrderItem orderItem) throws YiPinStoreException{
		if(productSku!=null&&orderItem!=null){
			orderItem.setProductSkuId(productSku.getProductSkuId());
			orderItem.setUnitPrice(productSku.getUnitPrice());//将查到的商品价格设置到orderItem中
			orderItem.setProductSku(productSku);
			orderItem.setNum(productSku.getBuyAmount());
			StringBuffer sb = new StringBuffer();
			if(StringUtils.isNotBlank(productSku.getSkuSpecification())){
				sb.append("规格："+productSku.getSkuSpecification());
			}
			if(productSku.getSkuWeight()!=null&&new BigDecimal(0).compareTo(productSku.getSkuWeight())!=0){
				sb.append(" 重量："+productSku.getSkuWeight()+"g");
			}
			orderItem.setProductProp(sb.toString());
			Product product = productSku.getProduct();
			if(product!=null){
				orderItem.setProductName(product.getName());//快照商品名称
				orderItem.setImageUrls(product.getCoverPicture());//快照图片url
				orderItem.setSellerId(product.getUserId());
				orderItem.setOrderSource(product.getSellType());
			}
		}
	}
	
	private void checkShoppingCart(ShoppingCart shoppingCart) throws YiPinStoreException{
		Map<String,Integer> cartItems = shoppingCart.getCartItems();
		if(cartItems!=null){
			Integer totalNum = 0;
			for(Iterator<String> it = cartItems.keySet().iterator();it.hasNext();){
				Integer tempNum = cartItems.get(it.next());
				totalNum = totalNum +tempNum;
			}
			shoppingCart.setTotalNum(totalNum);
		}
	}
	/**
	 * 通过包裹id判断是否可以退货
	 * @param orderAddressId
	 * @return true = 能   false = 不能
	 */
	private boolean judgeCanRefund(Integer orderAddressId){
		OrderHead head = orderHeadMapper.getOrderHeadByOrderAddressId(orderAddressId);
		if(null == head){
			return false;
		}
		//订单对应的状态是已经完成
		//不是已完成状态
		if(!OrderHead.STATUS_FINISHED.equals(head.getStatus())){
			return false;
		}
		//包裹是发货后15天内
		OrderAddress orderAddress = orderAddressMapper.get(orderAddressId);
		Date sendProTime = orderAddress.getSendProductTime();
		if(DateUtil.dayInterval(new Date(), sendProTime) > OrderRefund.orderRefundDefaultTime){
			return false;
		}
		//如果已经退过了 就不能退
		OrderRefund refund = orderRefundMapper.getOrderRefundByOrderAddressId(orderAddressId);
		if(null != refund){
			return false;
		}
		return true;
	}
	/**
	 * 判断订单项是否可以退货
	 * @param orderItemId
	 * @return true = 能   false = 不能
	 */
	private boolean judgeOrderItemCanRefund(Integer orderItemId){
		OrderItem item = orderItemMapper.get(orderItemId);
		if(null == item){
			return false;
		}
		OrderHead head = orderHeadMapper.get(item.getOrderId());
		//订单对应的状态是已经完成
		//不是已完成状态
		if(!OrderHead.STATUS_FINISHED.equals(head.getStatus())){
			return false;
		}
		//如果已经退过了 就不能退
		OrderRefund refund = orderRefundMapper.getOrderRefundByOrderItemId(orderItemId);
		if(null != refund){
			return false;
		}
		List<OrderAddress> list = orderAddressMapper.listOrderAddressByOrderId(item.getOrderId());
		//如果有多个包裹 说明是1.0版本的 或者数据有问题
		if(null == list || list.size() > 1){
			return false;
		}
		OrderAddress addr = list.get(0);
		//包裹是发货后15天内
		Date sendProTime = addr.getSendProductTime();
		if(DateUtil.dayInterval(new Date(), sendProTime) > OrderRefund.orderRefundDefaultTime){
			return false;
		}
		return true;
	}
}
