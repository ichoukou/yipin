package com.ytoxl.yipin.web.action.order;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.common.utils.StringUtils;
import com.ytoxl.module.yipin.base.common.utils.SystemUtils;
import com.ytoxl.module.yipin.order.dataobject.resultmap.AliPayment;
import com.ytoxl.module.yipin.order.dataobject.resultmap.NetpayModel;
import com.ytoxl.module.yipin.order.service.OrderPayService;
import com.ytoxl.yipin.web.action.BaseAction;

@SuppressWarnings("serial")
public class NetpayAction extends BaseAction {
	private static Logger logger = LoggerFactory.getLogger(NetpayAction.class);
	private final static String JSP_ALIPAY = "alipay";
	private final static String JSP_CONFIRM_ORDER = "confirmOrder";
	private final static String ACTION_MY_ORDER = "actionMyOrder";
	private final static String JSP_PAY_SUCCESS = "paySuccess";
	private final static String ACTION_PAY_SUCCESS = "paySuccessAction";
	private String orderIds;
	private NetpayModel netpayModel;
	private AliPayment aliPay;

	@Autowired
	private OrderPayService orderPayService;
	
	/**
	 * 取得要支付的信息
	 * @return
	 */
	public String confirmOrder(){
		try { 
			netpayModel = orderPayService.getOrderPayment(orderIds);
		} catch (YiPinStoreException e) {
			logger.error("取得支付相关信息报错",e);
			return ACTION_MY_ORDER;
		}
		return JSP_CONFIRM_ORDER;
	}
	
	/**
	 * 检查订单状态
	 * @return
	 */
	public String checkOrder(){
		try { 
			boolean bFlag = orderPayService.checkOrderPayament(netpayModel);
			if(bFlag){
				setMessage(Boolean.TRUE.toString(),"验证通过");
			}else{
				setMessage(Boolean.FALSE.toString(),"没有可支付的订单");
			}
		} catch (YiPinStoreException e) {
			logger.error("检查支付相关信息报错",e);
		}
		return JSONMSG;
	}
	
	/**
	 * 订单支付
	 */
	public String payit(){
		HttpServletRequest request = ServletActionContext.getRequest();
    	String alipayURL = null;
    	try {
    		String ipAddress = SystemUtils.getClientIpAddr(request);
    		netpayModel = orderPayService.addOrderPayment(netpayModel,ipAddress);
    		aliPay = orderPayService.updateOrderPayment(netpayModel);
    		alipayURL = orderPayService.dealOrderPayment(aliPay);
    	} catch (YiPinStoreException e) {
    		logger.error(e.getMessage());
    		setMessage(Boolean.FALSE.toString(),  e.getLocalizedMessage());
			return JSONMSG;
    	}
    	request.setAttribute("alipayURL", alipayURL);
    	return JSP_ALIPAY;
	}
	/**
	 * 支付宝同步回调处理
	 */
	public String alipayReturn(){
		logger.error("---------------==================及时到账支付宝返回信息处理开始=====================--------------------");
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String,String> params = getAliPayReturnMap(request);		
		try {
			orderIds = orderPayService.updateOrderByHavedPayit(params);
			logger.error("支付宝返回调用参数:"+params.toString());
		} catch (Exception e) {
			logger.error("及时到账支付宝返回信息处理失败",e);
			 for (Entry<String, String> entry: params.entrySet()) {
	                logger.error(entry.getKey()+"="+entry.getValue());
	         }
		}
		return ACTION_PAY_SUCCESS;
	}
	
	public String paySuccess(){
		if(StringUtils.isNotBlank(orderIds)){
			return JSP_PAY_SUCCESS;
		}else{
			return ACTION_MY_ORDER;
		}
	}
	
	/**
	 * 支付宝异步回调处理
	 */
	public void alipayNotify(){
		logger.error("---------------==================及时到账支付宝返回调用的方法(异步)处理开始==================--------------");
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String,String> params =getAliPayReturnMap(request);
		try {
			logger.error("支付宝返回调用参数:"+params.toString());
			orderPayService.updateOrderByHavedPayit(params);
			logger.error("及时到账(异步)支付宝返回信息处理success");
			print("success");
		} catch (Exception e) {			
			logger.error("及时到账(异步)支付宝返回信息处理fail");
			try {
				print("fail");
			} catch (IOException e1) {
				logger.error(e1.getMessage());
			}
		}
	}
	
	/**
	 * 获取支付宝的通知返回参数
	 * @param request
	 * @return
	 */
	private Map<String, String> getAliPayReturnMap(HttpServletRequest request) {
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator<String> it = requestParams.keySet().iterator(); it.hasNext();) {
			String name = it.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]: valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		return params;
	}
	
	/**
	 * 支付宝异步调用返回支付宝状态使用
	 */
	public void print(String string) throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
		out.print(string);
		out.flush();
		out.close();
	}
	
	public String getOrderIds() {
		return orderIds;
	}
	public void setOrderIds(String orderIds) {
		this.orderIds = orderIds;
	}
	public NetpayModel getNetpayModel() {
		return netpayModel;
	}
	public void setNetpayModel(NetpayModel netpayModel) {
		this.netpayModel = netpayModel;
	}
	public AliPayment getAliPay() {
		return aliPay;
	}
	public void setAliPay(AliPayment aliPay) {
		this.aliPay = aliPay;
	}
}
