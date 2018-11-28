package com.ytoxl.module.yipin.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.common.utils.IDUtils;
import com.ytoxl.module.yipin.base.dataobject.GlobalLog;
import com.ytoxl.module.yipin.base.dataobject.Point;
import com.ytoxl.module.yipin.base.service.GlobalLogService;
import com.ytoxl.module.yipin.base.service.PointService;
import com.ytoxl.module.yipin.order.alipay.config.AlipayConfig;
import com.ytoxl.module.yipin.order.alipay.util.AlipayNotify;
import com.ytoxl.module.yipin.order.alipay.util.AlipaySubmit;
import com.ytoxl.module.yipin.order.dataobject.OrderHead;
import com.ytoxl.module.yipin.order.dataobject.OrderNetpay;
import com.ytoxl.module.yipin.order.dataobject.resultmap.AliPayment;
import com.ytoxl.module.yipin.order.dataobject.resultmap.NetpayModel;
import com.ytoxl.module.yipin.order.mapper.OrderHeadMapper;
import com.ytoxl.module.yipin.order.mapper.OrderNetpayMapper;
import com.ytoxl.module.yipin.order.service.OrderPayService;
@Service
public class OrderPayServiceImpl implements OrderPayService{
	
	protected static Logger log = LoggerFactory.getLogger(OrderPayService.class);
	@Autowired
	private OrderHeadMapper<OrderHead> orderHeadMapper;
	@Autowired
	private OrderNetpayMapper<OrderNetpay> orderNetpayMapper;
	@Autowired
	private PointService pointService;
	@Autowired
	private GlobalLogService globalLogService;
	@Value("${alipay_subject}")
	private String alipaySubject;
	
	@Override
	public NetpayModel getOrderPayment(String orders) throws YiPinStoreException {
		NetpayModel netpayModel = new NetpayModel();
		if(com.ytoxl.module.yipin.base.common.utils.StringUtils.isNotBlank(orders)){
			String[] arrOrderIds = orders.split("\\|");
			List<OrderHead> orderHeadList = new ArrayList<OrderHead>();
			List<String> orderIdStrList = new ArrayList<String>(); 
			List<String> orderNoStrList = new ArrayList<String>();
			BigDecimal totalFee = new BigDecimal(0);
			BigDecimal zeroBigDecimal = new BigDecimal(0);
			for(String orderId:arrOrderIds){
				OrderHead orderHead = orderHeadMapper.get(Integer.parseInt(orderId));
				if(orderHead!=null){
					if(OrderHead.STATUS_WAITPAY.equals(orderHead.getStatus())){
						if( orderHead.getPaymentAmount() != null && orderHead.getPaymentAmount().compareTo(zeroBigDecimal)==1){
							orderHeadList.add(orderHead);
							orderIdStrList.add(String.valueOf(orderHead.getOrderId()));
							orderNoStrList.add(String.valueOf(orderHead.getOrderNo()));
							totalFee = totalFee.add(orderHead.getPaymentAmount());
						}
					}
				}
			}
			if(orderHeadList.size()==0){
				throw new YiPinStoreException("没有可支付的订单");
			}
			netpayModel.setOrderIds(StringUtils.join(orderIdStrList, "|"));
			netpayModel.setOrderNos(StringUtils.join(orderNoStrList, ","));
			netpayModel.setTotalFee(totalFee);
			netpayModel.setPayType(OrderNetpay.PAY_TYPE_ALIPAY);
		}
		return netpayModel;
	}
	
	@Override
	public Boolean checkOrderPayament(NetpayModel netpayModel) throws YiPinStoreException {
		Boolean bFlag = false;
		if(netpayModel!=null){
			if(com.ytoxl.module.yipin.base.common.utils.StringUtils.isNotBlank(netpayModel.getOrderIds())){
				String arrOrderIds[] = netpayModel.getOrderIds().split("\\|");
				int num = 0;
				for(String orderId:arrOrderIds){
					OrderHead orderHead = orderHeadMapper.get(Integer.parseInt(orderId));
					if(orderHead!=null){
						if(OrderHead.STATUS_WAITPAY.equals(orderHead.getStatus())){
							num ++;
						}
					}
				}
				if(num!=0&&arrOrderIds.length==num){
					bFlag = true;
				}
			}
		}
		return bFlag;
	}

	@Override
	public NetpayModel addOrderPayment(NetpayModel netpayModel,String ipAddress) throws YiPinStoreException {
		if(com.ytoxl.module.yipin.base.common.utils.StringUtils.isNotBlank(netpayModel.getOrderIds())){
			String[] arrOrderIds = netpayModel.getOrderIds().split("\\|");
			List<OrderHead> orderHeadList = new ArrayList<OrderHead>();
			for(String orderId:arrOrderIds){
				OrderHead orderHead = orderHeadMapper.get(Integer.parseInt(orderId));
				if(orderHead!=null){
					if(OrderHead.STATUS_WAITPAY.equals(orderHead.getStatus())){
						if( orderHead.getPaymentAmount() != null && orderHead.getPaymentAmount().doubleValue() > 0){
							orderHead.setIpAddress(ipAddress);
							orderHeadMapper.updateOrderPayAddress(orderHead);
							orderHeadList.add(orderHead);
						}
					}
				}
			}
			String uuid = IDUtils.getUUID();
			BigDecimal totalFee = new BigDecimal(0);
			List<String> orderIdStrList = new ArrayList<String>(); 
			if(orderHeadList.size()>0){
				for(OrderHead orderHead:orderHeadList){
					OrderNetpay orderNetpay = new OrderNetpay();
					orderNetpay.setOutTradeNo(uuid);
					orderNetpay.setOrderId(orderHead.getOrderId());
					orderNetpay.setOrderAlipayType(OrderNetpay.PAY_TYPE_ALIPAY);
					orderNetpay.setStatus(OrderNetpay.PAY_STATUS_ERROR);
					orderNetpayMapper.add(orderNetpay);
					totalFee = totalFee.add(orderHead.getPaymentAmount());
					orderIdStrList.add(String.valueOf(orderHead.getOrderNo()));
				}
			}else{
				throw new YiPinStoreException("没有可支付的订单");
			}
			netpayModel.setOutTradeNo(uuid);
			netpayModel.setOrderNos(StringUtils.join(orderIdStrList, ","));
			netpayModel.setTotalFee(totalFee);
			netpayModel.setPayType(OrderNetpay.PAY_TYPE_ALIPAY);
		}
		return netpayModel;
	}
	
	
	@Override
	public AliPayment updateOrderPayment(NetpayModel netpayModel) throws YiPinStoreException {
		AliPayment aliPay = new AliPayment();
		BigDecimal totalFee = new BigDecimal(0);
		if(netpayModel!=null){
			if(com.ytoxl.module.yipin.base.common.utils.StringUtils.isNotBlank(netpayModel.getOutTradeNo())){
				List<OrderNetpay> orderNetpayList = orderNetpayMapper.listOrderNetpayByOutTradeNo(netpayModel.getOutTradeNo());
				for(OrderNetpay orderNetpay:orderNetpayList){
					OrderHead orderHead = orderHeadMapper.get(orderNetpay.getOrderId());
					totalFee = totalFee.add(orderHead.getPaymentAmount());
				}
				aliPay.setSubject(alipaySubject);
				aliPay.setOutTradeNo(netpayModel.getOutTradeNo());
				aliPay.setBody(null);
				aliPay.setTotalFee(totalFee);
			}else{
				throw new YiPinStoreException("没有可支付的订单");
			}
		}else{
			throw new YiPinStoreException("没有可支付的订单");
		}
		return aliPay;
	}


	@Override
	public String dealOrderPayment(AliPayment aliPay) throws YiPinStoreException {
		//调用支付宝 (生成请求URL)，把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("out_trade_no", aliPay.getOutTradeNo());
		sParaTemp.put("subject", aliPay.getSubject());
		sParaTemp.put("total_fee", aliPay.getTotalFee().toString());
		//sParaTemp.put("body", aliPay.getBody());
	
		String sHtmlText="";
		//生成请求URL
		try{
			sHtmlText = createDirectAlipayByUser(sParaTemp);
			log.error("调用支付宝的路径: "+sHtmlText);
		}catch(Exception e){
			log.error("生成支付宝URL出错:"+sHtmlText);
		}
		//返回URL(action 跳转)
		return sHtmlText;
	}
	
	@Override
	public  String createDirectAlipayByUser(Map<String, String> sParaTemp) {
		//把请求参数打包成数组
		sParaTemp.put("service", "create_direct_pay_by_user");
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("return_url", AlipayConfig.return_url); 
		sParaTemp.put("notify_url", AlipayConfig.notify_url);
		sParaTemp.put("seller_email", AlipayConfig.seller_email);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", "1");
		String strButtonName = "确认";
		return AlipaySubmit.buildForm(sParaTemp, AlipayConfig.alipay_gateway_url, "get", strButtonName);
	}

	@Override
	public String updateOrderByHavedPayit(Map<String, String> params) throws YiPinStoreException {
		boolean verify_result=AlipayNotify.verify(params);
		List<String> orderIdList = new ArrayList<String>();
		if(verify_result){//验证成功	
			String tradeNo = params.get("trade_no"); // 支付宝支付宝交易号
			String outTradeNo = params.get("out_trade_no"); // 获取本系统交易号
			String totalFee = params.get("total_fee"); // 获取总金额
			//String subject = params.get("subject");// 商品名称、订单名称
			//String body = params.get("body");// 商品描述、订单备注、描述(放值的时候存了dserviceId)
			String buyerEmail = params.get("buyer_email"); // 买家支付宝账号
			String buyerId = params.get("buyer_id"); // 买家支付宝账号
			String tradeStatus = params.get("trade_status"); // 交易状态
			if("TRADE_FINISHED".equals(tradeStatus) || "TRADE_SUCCESS".equals(tradeStatus)){
				//编写订单支付完成逻辑
				List<OrderNetpay> orderNetpayList = orderNetpayMapper.listOrderNetpayByOutTradeNo(outTradeNo);
				List<OrderHead> orderHeadList = new ArrayList<OrderHead>();
				BigDecimal totleAmount = new BigDecimal(0);//订单总价
				if(orderNetpayList!=null&&orderNetpayList.size()>0){
					for(OrderNetpay orderNetpay:orderNetpayList){
						OrderHead orderHead = orderHeadMapper.get(orderNetpay.getOrderId());
						if(orderHead!=null){
							orderHeadList.add(orderHead);
							totleAmount = totleAmount.add(orderHead.getPaymentAmount());
						}
					}
				}
				//判断支付金额是否和订单的总价格相等
				if(totleAmount.compareTo(new BigDecimal(totalFee))==0){
					boolean bFlag = false;
					Integer userId = orderHeadList.get(0).getUserId();
					for(OrderHead orderHead:orderHeadList){ 
						orderHead.setStatus(OrderHead.STATUS_WAITSEND);
						orderHead.setAccountId(buyerId);
						orderHead.setAccountInfo(buyerEmail);
						orderIdList.add(String.valueOf(orderHead.getOrderNo()));
						//如果是待支付或者已取消，支付完成后都可以将状态设置成待发货
						if(OrderHead.STATUS_WAITPAY.equals(orderHead.getPayStatus())||OrderHead.STATUS_CANCEL.equals(orderHead.getPayStatus())){
							orderHead.setPayStatus(OrderHead.PAY_STATUS_YES);
							orderHeadMapper.updateOrderByNetpay(orderHead);
							pointService.addPointByUserId(userId,Point.POINTSOURCE_ADDORDER,
									pointService.computerPoint(orderHead.getPaymentAmount()),Point.TYPE_ADD,orderHead.getOrderId());//添加积分 
							bFlag = true;
						}
					}
					if(bFlag){
						for(OrderNetpay orderNetpay:orderNetpayList){
							orderNetpay.setStatus(OrderNetpay.PAY_STATUS_SUCCESS);
							orderNetpay.setTradeNo(tradeNo);
							orderNetpayMapper.updateOrderNetpayByNetpay(orderNetpay);
						}
					}
				}else{
					globalLogService.recordLog(GlobalLog.LOG_TYPE_ALIPAY, "支付总价和订单总价不一致,支付总价："+totalFee+"订单总价："+totleAmount, params.toString());
					throw new YiPinStoreException("支付总价和订单总价不一致,支付总价："+totalFee+"订单总价："+totleAmount);
				}
			}else{
				globalLogService.recordLog(GlobalLog.LOG_TYPE_ALIPAY, "支付宝未交易成功", params.toString());
				throw new YiPinStoreException("支付宝未交易成功："+params.toString());
			}
		}else{
			globalLogService.recordLog(GlobalLog.LOG_TYPE_ALIPAY, "支付宝参数未验证通过", params.toString());
			throw new YiPinStoreException("支付宝参数未验证通过："+params.toString());
		}
		return StringUtils.join(orderIdList,",");
	}
}
