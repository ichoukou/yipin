package com.ytoxl.yipin.web.action.order;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.icu.text.SimpleDateFormat;
import com.opensymphony.xwork2.ActionContext;
import com.ytoxl.module.core.common.pagination.BasePagination;
import com.ytoxl.module.core.common.utils.DateUtil;
import com.ytoxl.module.core.common.utils.StringUtils;
import com.ytoxl.module.user.common.exception.YtoxlUserException;
import com.ytoxl.module.user.dataobject.User;
import com.ytoxl.module.user.security.CustomUserDetails;
import com.ytoxl.module.user.service.UserService;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.common.utils.ExcelUtils;
import com.ytoxl.module.yipin.base.common.utils.PropertyUtil;
import com.ytoxl.module.yipin.base.common.utils.excel.ExportExcel;
import com.ytoxl.module.yipin.base.dataobject.Brand;
import com.ytoxl.module.yipin.base.dataobject.Express;
import com.ytoxl.module.yipin.base.dataobject.UserInfo;
import com.ytoxl.module.yipin.base.service.BrandService;
import com.ytoxl.module.yipin.base.service.ExpressService;
import com.ytoxl.module.yipin.base.service.UserInfoService;
import com.ytoxl.module.yipin.order.dataobject.OrderAddress;
import com.ytoxl.module.yipin.order.dataobject.OrderCancel;
import com.ytoxl.module.yipin.order.dataobject.OrderHead;
import com.ytoxl.module.yipin.order.dataobject.OrderInvoiceAddress;
import com.ytoxl.module.yipin.order.dataobject.OrderNetpay;
import com.ytoxl.module.yipin.order.dataobject.OrderRefund;
import com.ytoxl.module.yipin.order.dataobject.OrderRefundExpress;
import com.ytoxl.module.yipin.order.dataobject.OrderRefundPayment;
import com.ytoxl.module.yipin.order.dataobject.resultmap.OrderExportModel;
import com.ytoxl.module.yipin.order.service.OrderService4Manage;
import com.ytoxl.yipin.web.action.BaseAction;

@SuppressWarnings("serial")
public class OrderAction extends BaseAction {

	private static Logger logger = LoggerFactory.getLogger(OrderAction.class);
	// 后台订单管理
	private static final String SEARCHORDERS4MANAGER = "searchOrders4Manager";
	private static final String SEARCHORDERS4SELLER = "searchOrders4Seller";
	// 后台管理员查看订单明细
	private static final String GETORDER4MANAGER = "getOrder4Manager";
	private static final String GETORDER4SELLER = "getOrder4Seller";
	//发票管理
	private static final String INVOICE4MANAGER = "searchOrderInvoices4Manager";
	private static final String INVOICE4SELLER = "searchOrderInvoices4Seller";
	
	@Autowired
	private UserService userService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private ExpressService expressService;
	@Autowired
	private OrderService4Manage orderService4Manage;

	private UserInfo user;
	private OrderHead orderHead;
	private Integer status;
	private Boolean isAdmin;
	private OrderCancel orderCancel;
	
	/** 退款申请请求参数bean **/
	private OrderRefund orderRefund;
	/** 退款申请快递信息请求参数bean **/
	private OrderRefundExpress orderRefundExpress;
	/** 用于 管理员确认退款 **/
	private OrderRefundPayment orderRefundPayment;
	/** 用于确认退货的请求参数 **/
	private Integer orderRefundId;
	private BigDecimal packageTotalPrice;
	
	private File file;
	private String fileName;
	private InputStream excelStream;
	// 快递公司列表
	private List<Express> listExpresses;
	// 包裹发货
	private OrderAddress orderAddress;
	// 发票发货
	private OrderInvoiceAddress orderInvoiceAddress;
	// 分页对象
	private BasePagination<OrderHead> orderPage;
	private BasePagination<OrderInvoiceAddress> orderInvoicePage;
   
	// 管理员订单后台
	public String searchOrders4Manager() {
		if (orderPage == null) {
			orderPage = new BasePagination<OrderHead>();
		}
		try {
			UserInfo userInfo = userInfoService.getUserByUserId(userInfoService.searchUserIdByUserType());
			if (userInfo!=null && userInfo.getUserType().equals(UserInfo.USER_TYPE_MANAGER)) {
				isAdmin = true;

				if (this.orderPage.getParams() == null) {
					Map<String, String> params = new HashMap<String, String>();
					// 默认交易日期近一周
					Date curDate = new Date();
					String beginTime = DateUtil.toDay(DateUtil.add(curDate,Calendar.DATE, -6));
					String endTime = DateUtil.toDay(curDate);
					params.put("beginTime", beginTime);
					params.put("endTime", endTime);
					this.orderPage.setParams(params);
				}
				orderService4Manage.searchOrders4ManagerOrSeller(orderPage);
			} 
		} catch (YiPinStoreException e) {
			logger.error("searchOrders4Manager YiPinStoreException",e);
		}
		return SEARCHORDERS4MANAGER;
	}

	/** 卖家管理订单入口 */
	public String searchOrders4Seller() {
		if (orderPage == null) {
			orderPage = new BasePagination<OrderHead>();
		}
		try {
			Integer userId = userInfoService.searchUserIdByUserType();
			UserInfo userInfo = userInfoService.getUserByUserId(userId);
			if (userInfo!=null && userInfo.getUserType().equals(UserInfo.USER_TYPE_SELLER)) {
				if (this.orderPage.getParams() == null) {
					Map<String, String> params = new HashMap<String, String>();
					Date curDate = new Date();
					String beginTime = DateUtil.toDay(DateUtil.add(curDate,Calendar.DATE, -6));
					String endTime = DateUtil.toDay(curDate);
					params.put("beginTime", beginTime);
					params.put("endTime", endTime);
					this.orderPage.setParams(params);
				} 
				this.orderPage.getParams().put("sellerId", String.valueOf(userId));

				orderService4Manage.searchOrders4ManagerOrSeller(orderPage);
			}
		} catch (YiPinStoreException e) {
			logger.error("searchOrders4Seller YiPinStoreException",e);
		}
		return SEARCHORDERS4SELLER;
	}

	/**
	 * 获取全部品牌
	 * 
	 * @return
	 */
	public List<Brand> getBrands() {
		List<Brand> brands = new ArrayList<Brand>();
		try {
			brands = brandService.listBrands();
		} catch (YiPinStoreException e) {
			logger.error("getBrands",e.getMessage());
		}
		return brands;
	}
	
	/**
	 * 获取商家可售品牌
	 * 
	 * @return
	 * @throws YiPinStoreException
	 */
	public List<Brand> getBrandList() {
		List<Brand> brandlist = new ArrayList<Brand>();
		try {
			CustomUserDetails user = userService.getCurrentUser();
			brandlist = brandService.listBrandsBySellerId(user.getUserId());
		} catch (YiPinStoreException e) {
			logger.error("getBrandList YiPinStoreException",e);
		} catch (YtoxlUserException e) {
			logger.error("getBrandList YtoxlUserException",e);
		}
		return brandlist;
	}


	/**
	 * 获取全部商家
	 * 
	 * @return
	 */
	public List<UserInfo> getSellers() {
		List<UserInfo> sellers = new ArrayList<UserInfo>();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userType", UserInfo.USER_TYPE_SELLER);
			map.put("status", User.STATUS_ABLE);
			sellers = (List<UserInfo>) userInfoService.listSellersByStatusAndType(map);
		} catch (YiPinStoreException e) {
			logger.error("getSellers YiPinStoreException",e);
		} 
		return sellers;
	}


	/**
	 * 管理员查看订单明细
	 */
	public String getOrder4Manager() {
		try {
			orderHead = orderService4Manage.getOrderById(orderHead.getOrderId());
			listExpresses = expressService.listExpresses();
		} catch (YiPinStoreException e) {
			logger.error("getOrder4Manager YiPinStoreException",e);
			setMessage(Boolean.FALSE.toString(), e.getLocalizedMessage());
			return JSONMSG;
		}
		return GETORDER4MANAGER;
	}

	/**
	 * 卖家查看订单明细
	 */
	public String getOrder4Seller() {
		try {
			orderHead = orderService4Manage.getOrderById(orderHead.getOrderId());
			listExpresses = expressService.listExpresses();
		} catch (YiPinStoreException e) {
			logger.error("getOrder4Seller YiPinStoreException",e);
			setMessage(Boolean.FALSE.toString(), e.getLocalizedMessage());
			return JSONMSG;
		}
		return GETORDER4SELLER;
	}

	/**
	 * 导出 退货订单
	 * 
	 * @return
	 */
	public String exportReturnOrders() {
		return this.exportOrders();
	}

	/**
	 * 导出订单 2013-06-13
	 * 
	 * @return
	 */
	public String exportOrders() {
		try {
			if (this.orderPage == null) {
				this.orderPage = new BasePagination<OrderHead>();
			}
			if(this.orderPage.getParams() == null ){
				Map<String, String > params=new HashMap<String, String>();
				this.orderPage.setParams(params);
			}
					
			Map<String, Object> searchParams = orderPage.getSearchParamsMap();
			if(searchParams == null){
				searchParams = new HashMap<String, Object>();
			}
			List<OrderExportModel> orderList = orderService4Manage.listOrders(searchParams);
			
			String orderStatus = "全部";
			String selectStatus = orderPage.getParams().get("status");
			if (null != selectStatus && selectStatus.length() > 0) {
				short status = Short.parseShort(selectStatus);
				orderStatus = PropertyUtil.getPropertyValue("order.status."+status,null);
				if(status == OrderHead.STATUS_RETURN.shortValue()){
					orderStatus = "退货";
				}		
				if(status == OrderHead.STATUS_WAITPAY.shortValue()){
	        		orderStatus = "待付款";
	        	}

			}		

			ActionContext ac = ActionContext.getContext();
	        ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
	        orderStatus += "订单";
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String fName = orderStatus+sdf.format(new Date()).replace("-", "").replace(" ", "").replace(":", "");

			HttpServletRequest request = ServletActionContext.getRequest();
			String userAgent = request.getHeader("USER-AGENT");
			String finalFileName = null;
			if (StringUtils.contains(userAgent, "MSIE")) {// IE浏览器
				finalFileName = URLEncoder.encode(fName, "UTF8");
			} else if (StringUtils.contains(userAgent, "Mozilla")) {// google,火狐浏览器
				finalFileName = new String(fName.getBytes(), "ISO8859-1");
			} else {
				finalFileName = URLEncoder.encode(fName, "UTF8");// 其他浏览器
			}
			this.setFileName(finalFileName);
			String x = sc.getRealPath("/") + File.separator + fName + ".xls";
			ExportExcel<OrderExportModel> ee = new ExportExcel<OrderExportModel>(
					orderList);
			ee.setMerge(true);
			ee.export(x);

			File file = new File(x);
			this.setExcelStream(new FileInputStream(file));
		} catch (Exception e) {
			logger.error("订单导出失败",e);
		}
		return "excels";
	}

	/**
	 * 商家导出订单
	 * 
	 * @return
	 */
	public String exportSellerOrders() {
		return this.exportOrders();
	}

	/**
	 * 确认发货
	 * 
	 * @return
	 */
	public String confirmSendProduct() {
		try {
			String orderStatus = orderService4Manage.confirmSendProduct(this.orderAddress);
			setMessage(Boolean.TRUE.toString(), "发货成功",new String[] { orderStatus });
		} catch (YiPinStoreException e) {
			logger.error("confirmSendProduct YiPinStoreException", e);
			setMessage(Boolean.FALSE.toString(),new String[] { e.getMessage() });
		} catch (Exception e) {
			setMessage(Boolean.FALSE.toString(), e.getMessage());
		}
		return JSONMSG;
	}
	
	/**
	 * 待发货退款 - 审核通过
	 * 
	 * @return
	 */
	public String passCancelAudit() {
		try {
			orderCancel.setStatus(OrderCancel.STATUS_THROUGH);
			orderCancel.setSellerCheckUserId(userService.getCurrentUser().getUserId());
			String status = orderService4Manage.auditOrderCancel(orderCancel);
			setMessage(Boolean.TRUE.toString(),"审核通过", new String[]{status});
		} catch (YiPinStoreException e) {
			logger.error("passCancelAudit YiPinStoreException", e);
			setMessage(Boolean.FALSE.toString(),e.getMessage());
		} catch (YtoxlUserException e) {
			logger.error("passCancelAudit YtoxlUserException", e);
			setMessage(Boolean.FALSE.toString(),e.getMessage());
		} 
		return JSONMSG;
	}

	/**
	 * 待发货退款 - 审核不通过
	 * 
	 * @return
	 */
	public String rejectCancelAudit() {
		try {
			orderCancel.setStatus(OrderCancel.STATUS_NOTAUDIT);
			orderCancel.setSellerCheckUserId(userService.getCurrentUser().getUserId());
			String status = orderService4Manage.auditOrderCancel(orderCancel);
			setMessage(Boolean.TRUE.toString(),"审核不通过",new String[]{status});
		} catch (YiPinStoreException e) {
			logger.error("rejectCancelAudit YiPinStoreException", e);
			setMessage(Boolean.FALSE.toString(),e.getMessage());
		} catch (YtoxlUserException e) {
			logger.error("rejectCancelAudit YtoxlUserException", e);
			setMessage(Boolean.FALSE.toString(),e.getMessage());
		} 
		return JSONMSG;
	}
	
	/**
	 * 待发货退款 - 同意退款
	 * 
	 * @return
	 */
	public String agreePayment() {
		try {
			orderCancel.setStatus(OrderCancel.STATUS_FINISH);
			orderCancel.setAdminCheckUserId(userService.getCurrentUser().getUserId());
			String status = orderService4Manage.confirmOrderCancel(orderCancel);
			setMessage(Boolean.TRUE.toString(), "退款成功", new String[] { status });
		} catch (YiPinStoreException e) {
			logger.error("confirmCancelPayment YiPinStoreException",e);
			setMessage(Boolean.FALSE.toString(), e.getMessage());
		} catch (YtoxlUserException e) {
			logger.error("confirmCancelPayment YtoxlUserException", e);
			setMessage(Boolean.FALSE.toString(),e.getMessage());
		} 
		return JSONMSG;
	}
	
	/**
	 * 审核通过
	 * 
	 * @return
	 */
	public String passAudit() {
		try {
			//this.orderReturn.setStatus(OrderReturn.STATUS_ACCEPT);
			//将item设置为审核通过
			this.orderRefund.setStatus(OrderRefund.STATUS_ACCEPT);
			String status=orderService4Manage.audit(this.orderRefund);
			setMessage(Boolean.TRUE.toString(),"审核通过", new String[]{status});
		} catch (YiPinStoreException e) {
			logger.error("passAudit YiPinStoreException", e);
			setMessage(Boolean.FALSE.toString(),e.getMessage());
		} 
		return JSONMSG;
	}

	/**
	 * 审核不通过
	 * 
	 * @return
	 */
	public String rejectAudit() {
		try {
			//this.orderReturn.setStatus(OrderReturn.STATUS_REFUSEED);
			//将item设置为不通过
			this.orderRefund.setStatus(OrderRefund.STATUS_REFUSEED);
			String status=orderService4Manage.audit(this.orderRefund);
			setMessage(Boolean.TRUE.toString(),"审核不通过",new String[]{status});
		} catch (YiPinStoreException e) {
			logger.error("rejectAudit YiPinStoreException", e);
			setMessage(Boolean.FALSE.toString(),e.getMessage());
		}
		return JSONMSG;
	}
	
	/**
	 * 确认退货
	 * **/
	public String confirmBackProduct(){
		try {
			String status=orderService4Manage.confirmBackProduct(orderRefundId,packageTotalPrice);
			setMessage(Boolean.TRUE.toString(),"已经退货", new String[]{status});
		} catch (YiPinStoreException e) {
			logger.error("confirmBackProduct:",e);
			setMessage(Boolean.FALSE.toString(),e.getMessage());
		} 
		return JSONMSG;
	}

	/**
	 * 确认退款
	 * 
	 * @return
	 */
	public String confirmPayment() {
		try {
			// 设置ip
			orderRefundPayment.setIpAddress(ServletActionContext.getRequest().getRemoteAddr());
			// 退款成功
			orderRefundPayment.setStatus(OrderRefundPayment.STATUS_REFUND);
			String status = orderService4Manage.confirmPayment(orderRefundPayment);
			setMessage(Boolean.TRUE.toString(), "退款成功", new String[] { status });
		} catch (YiPinStoreException e) {
			logger.error("confirmPayment YiPinStoreException",e);
			setMessage(Boolean.FALSE.toString(), e.getMessage());
		}
		return JSONMSG;
	}

	/**
	 * 批量发货
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String batchUpload() {
		try {
			// 获得当前登录用户
			CustomUserDetails user = userService.getCurrentUser();
			InputStream inp = new FileInputStream(this.file);
			ExcelUtils excel = new ExcelUtils();
			excel.setSheetName("Sheet1");
			List<String[]> orders = excel.readForExport(inp);
			if (orders != null && orders.size() > 0) {
				boolean flag = checkInportOrders(orders);
				if(flag){
					orderService4Manage.updateBatchUpload(orders, user.getUserId());
					setMessage(Boolean.TRUE.toString(), "发货成功");
				}else{
					setMessage(Boolean.TRUE.toString(), "表格中没有数据");
				}	
			} else {
				setMessage(Boolean.TRUE.toString(), "表格中没有数据");
			}

		} catch (FileNotFoundException e) {
			logger.error("上传文件没找到",e);
			setMessage(Boolean.FALSE.toString(), "上传文件没找到");
		} catch(YiPinStoreException e1){
			logger.error("批量发货文件导入失败",e1);
			setMessage(Boolean.FALSE.toString(), e1.getMessage());
		} catch (Exception e2) {
			logger.error("批量发货文件导入失败",e2);
			setMessage(Boolean.FALSE.toString(), "您上传的表格有问题，请重新上传！");
		}
		return JSONMSG;
	}

	@SuppressWarnings("rawtypes")
	private boolean checkInportOrders(List<String[]> orders) {
		boolean flag = false;	//全部没有数据
		for (Iterator iterator = orders.iterator(); iterator.hasNext();) {
			String[] strings = (String[]) iterator.next();
			if (StringUtils.isNotBlank(strings[0])
					|| StringUtils.isNotBlank(strings[1])
					|| StringUtils.isNotBlank(strings[2])) {
				flag = true; 
			}	
		}
		return flag;
	}

	/**
	 * 下载批量发货模板
	 * 
	 * @return
	 */
	public String downloadTemplate() {
		try {
			ActionContext ac = ActionContext.getContext();
	        ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
	        String x = sc.getRealPath("/")+"/default_template.xlsx";
	        this.setExcelStream(new FileInputStream(new File(x)));
		} catch (FileNotFoundException e) {
			logger.error("下载批量发货文件失败",e);
			setMessage(Boolean.FALSE.toString(),e.getMessage());
		} 
		return "template";
	}

	// 管理员发票管理后台
	public String searchOrderInvoices4Manager() {
		if (this.orderInvoicePage == null) {
			this.orderInvoicePage = new BasePagination<OrderInvoiceAddress>();
		}
		try {
			UserInfo userInfo = userInfoService.getUserByUserId(userInfoService.searchUserIdByUserType());
			if (userInfo!=null && userInfo.getUserType().equals(UserInfo.USER_TYPE_MANAGER)) {
				isAdmin = true;
				
				if (this.orderInvoicePage.getParams() == null) {
					this.orderInvoicePage.setParams(new HashMap<String, String>());
					this.orderInvoicePage.getParams().put("adminFlag", "true");
				} 
				orderService4Manage.searchOrderInvoices4ManagerOrSeller(orderInvoicePage);
				listExpresses = expressService.listExpresses();
			} 
		} catch (YiPinStoreException e) {
			logger.error("searchOrderInvoices4Manager YiPinStoreException",e);
		}
		return INVOICE4MANAGER;
	}

	/** 卖家发票管理入口 */
	public String searchOrderInvoices4Seller() {
		if (this.orderInvoicePage == null) {
			this.orderInvoicePage = new BasePagination<OrderInvoiceAddress>();
		}
		try {
			Integer userId = userInfoService.searchUserIdByUserType();
			UserInfo userInfo = userInfoService.getUserByUserId(userId);
			if (userInfo!=null && userInfo.getUserType().equals(UserInfo.USER_TYPE_SELLER)) {
				if (this.orderInvoicePage.getParams() == null) {
					this.orderInvoicePage.setParams(new HashMap<String, String>());
				} 
				this.orderInvoicePage.getParams().put("sellerId", String.valueOf(userId));
	
				orderService4Manage.searchOrderInvoices4ManagerOrSeller(orderInvoicePage);
			}
			listExpresses = expressService.listExpresses();
		} catch (YiPinStoreException e) {
			logger.error("searchOrderInvoices4Seller YiPinStoreException",e);
		}
		return INVOICE4SELLER;
	}
	
	/**
	 * 发票确认发货
	 * 
	 * @return
	 */
	public String confirmSendOrderInvoice() {
		try {
			String orderStatus = orderService4Manage.confirmSendOrderInvoice(this.orderInvoiceAddress);
			setMessage(Boolean.TRUE.toString(), "发货成功", new String[] { orderStatus });
		} catch (YiPinStoreException e) {
			logger.error("confirmSendOrderInvoice YiPinStoreException",e);
			setMessage(Boolean.FALSE.toString(),new String[] { e.getMessage() });
		} catch (Exception e) {
			setMessage(Boolean.FALSE.toString(), e.getMessage());
		}
		return JSONMSG;
	}
	
	/**
	 * 获取订单状态
	 * 
	 * @return
	 */
	public Short[] getStatuses() {
		return OrderHead.STATUSES;
	}

	/** 获取待发货订单子状态 */
	public Short[] getCancelStatuses() {
		return OrderCancel.CANCEL_STATUSES;
	}
	
	/** 获取退货订单子状态 */
	public Short[] getItemStatuses() {
		return OrderRefund.ITEM_STATUSES;
	}

	/**
	 * 获取支付状态待退款
	 * 
	 * @return
	 */
	public Short getPaymentStatusArrived() {
		return OrderNetpay.STATUS_WAITREFUND;
	}

	/**
	 * 获支付状态：已支付
	 * 
	 * @return
	 */
	public Short getPaymentStatusPayed() {
		return OrderNetpay.STATUS_PAYED;
	}

	/**
	 * 退货订单 已经退款
	 * 
	 * @return
	 */
	public Short getAlreadyRefund() {
		return OrderRefundPayment.STATUS_REFUND;
	}

	/**
	 * 获订单状态：待发货
	 * 
	 * @return
	 */
	public Short getOrderStatusWaitSend() {
		return OrderHead.STATUS_WAITSEND;
	}

	/**
	 * 获订单状态：已发货
	 * 
	 * @return
	 */
	public Short getOrderStatusSend() {
		return OrderHead.STATUS_SEND;
	}

	/**
	 * 订单状态：已完成
	 * 
	 * @return
	 */
	public Short getOrderStatusFinished() {
		return OrderHead.STATUS_FINISHED;
	}
	
	/**
	 * 订单状态：已退货
	 * 
	 * @return
	 */
	public Short getOrderStatusReturn() {
		return OrderHead.STATUS_RETURN;
	}

	/**
	 * 退货订单状态 未审核
	 * 
	 * @return
	 */
	public Short getOrderReturnItemStatusNotAudit() {
		return OrderRefund.STATUS_NOTAUDIT;
	}

	/**
	 * 退货订单状态 审核通过
	 * 
	 * @return
	 */
	public Short getOrderReturnItemStatusAccept() {
		return OrderRefund.STATUS_ACCEPT;
	}

	/**
	 * 退货订单状态 审核未通过
	 * 
	 * @return
	 */
	public Short getOrderReturnItemStatusRefuseed() {
		return OrderRefund.STATUS_REFUSEED;
	}

	/**
	 * 退货订单状态 已收货
	 * 
	 * @return
	 */
	public Short getOrderReturnItemStatusTakeGoods() {
		return OrderRefund.STATUS_TAKE_GOODS;
	}

	/**
	 * 退货获支付状态：待退款
	 * 
	 * @return
	 */
	public Short getReturnPaymentStatusWaitRefund() {
		return OrderRefundPayment.STATUS_WAITREFUND;
	}

	/**
	 * 退货获支付状态：已退款
	 * 
	 * @return
	 */
	public Short getPaymentStatusRefund() {
		return OrderRefundPayment.STATUS_REFUND;
	}

	/**
	 * 订单是否有发票状态：有发票
	 * 
	 * @return
	 */
	public Short getOrderInvoiceStatus() {
		return OrderHead.HAS_INVOICE_YES;
	}
	
	public BasePagination<OrderHead> getOrderPage() {
		return orderPage;
	}

	public void setOrderPage(BasePagination<OrderHead> orderPage) {
		this.orderPage = orderPage;
	}

	public BasePagination<OrderInvoiceAddress> getOrderInvoicePage() {
		return orderInvoicePage;
	}

	public void setOrderInvoicePage(
			BasePagination<OrderInvoiceAddress> orderInvoicePage) {
		this.orderInvoicePage = orderInvoicePage;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public OrderHead getOrderHead() {
		return orderHead;
	}

	public void setOrderHead(OrderHead orderHead) {
		this.orderHead = orderHead;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public OrderCancel getOrderCancel() {
		return orderCancel;
	}

	public void setOrderCancel(OrderCancel orderCancel) {
		this.orderCancel = orderCancel;
	}
	
	public OrderRefund getOrderRefund() {
		return orderRefund;
	}

	public void setOrderRefund(OrderRefund orderRefund) {
		this.orderRefund = orderRefund;
	}

	public OrderRefundExpress getOrderRefundExpress() {
		return orderRefundExpress;
	}

	public void setOrderRefundExpress(OrderRefundExpress orderRefundExpress) {
		this.orderRefundExpress = orderRefundExpress;
	}

	public OrderRefundPayment getOrderRefundPayment() {
		return orderRefundPayment;
	}

	public void setOrderRefundPayment(OrderRefundPayment orderRefundPayment) {
		this.orderRefundPayment = orderRefundPayment;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	public List<Express> getListExpresses() {
		return listExpresses;
	}

	public void setListExpresses(List<Express> listExpresses) {
		this.listExpresses = listExpresses;
	}

	public OrderAddress getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(OrderAddress orderAddress) {
		this.orderAddress = orderAddress;
	}

	public OrderInvoiceAddress getOrderInvoiceAddress() {
		return orderInvoiceAddress;
	}

	public void setOrderInvoiceAddress(OrderInvoiceAddress orderInvoiceAddress) {
		this.orderInvoiceAddress = orderInvoiceAddress;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public Integer getOrderRefundId() {
		return orderRefundId;
	}

	public void setOrderRefundId(Integer orderRefundId) {
		this.orderRefundId = orderRefundId;
	}

	public BigDecimal getPackageTotalPrice() {
		return packageTotalPrice;
	}

	public void setPackageTotalPrice(BigDecimal packageTotalPrice) {
		this.packageTotalPrice = packageTotalPrice;
	}

}
