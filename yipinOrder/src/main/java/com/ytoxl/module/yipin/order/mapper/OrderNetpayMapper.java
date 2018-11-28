package com.ytoxl.module.yipin.order.mapper;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ytoxl.module.core.common.persistence.BaseSqlMapper;
import com.ytoxl.module.yipin.order.dataobject.OrderNetpay;

public interface OrderNetpayMapper<T extends OrderNetpay> extends BaseSqlMapper<T>  {

	/**
	 * 根据交易号查询支付是插入的记录
	 * @param outTradeNo
	 * @return
	 * @throws DataAccessException
	 */
	public List<OrderNetpay> listOrderNetpayByOutTradeNo(String outTradeNo) throws DataAccessException;
	
	
	/**
	 * 支付完成后，更新在线支付的相关信息(次方法只供支付调用)
	 * @param orderId
	 * @throws DataAccessException
	 */
	public void updateOrderNetpayByNetpay(OrderNetpay orderNetpay) throws DataAccessException;
}
