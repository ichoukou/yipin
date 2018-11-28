package com.ytoxl.module.yipin.order.service;

import java.util.Map;

import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.order.dataobject.resultmap.AliPayment;
import com.ytoxl.module.yipin.order.dataobject.resultmap.NetpayModel;

public interface OrderPayService {
	
	/**
	 * 获得支付信息
	 * @param orders
	 * @return
	 * @throws YiPinStoreException
	 */
	public NetpayModel getOrderPayment(String orders) throws YiPinStoreException;
	
	/**
	 * 检查订单支付状态
	 * @param netpayModel
	 * @return
	 * @throws YiPinStoreException
	 */
	public Boolean checkOrderPayament(NetpayModel netpayModel) throws YiPinStoreException;
	/**
	 * 添加支付信息
	 * @param orders
	 * @return
	 * @throws YiPinStoreException
	 */
	public NetpayModel addOrderPayment(NetpayModel netpayModel,String ipAddress) throws YiPinStoreException;
	
	/**
	 * 更新支付相关信息
	 * @param outTradeNo
	 * @return
	 * @throws YiPinStoreException
	 */
	public AliPayment updateOrderPayment(NetpayModel netpayModel) throws YiPinStoreException;
	/**
	 * 生成请求URL
	 * @param aliPay
	 * @return
	 * @throws YiPinStoreException
	 */
	public String dealOrderPayment(AliPayment aliPay) throws YiPinStoreException;
	/**
	 * 构造即时到帐接口
	 * @param sParaTemp请求参数集合
	 * @return 表单提交HTML信息
	 */
	public String createDirectAlipayByUser(Map<String, String> sParaTemp) throws YiPinStoreException;
	
	/**
	 * 支付完成，处理订单相关相关状态
	 * @param params
	 * @throws YiPinStoreException
	 */
	public String updateOrderByHavedPayit(Map<String,String> params) throws YiPinStoreException;
	
}
