package com.ytoxl.module.yipin.order.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.order.BaseTest;

public class OrderPayServiceTest extends BaseTest{

	@Autowired
	private OrderPayService orderPayService;
	@Test
	public void testUpdateOrderByHavedPayit(){
		Map<String, String> params = new HashMap<String,String>();
		params.put("trade_no", "2013121128093032");
		params.put("out_trade_no", "18d0d71fb92f4128a0d9ef879dd2506e");
		params.put("total_fee", "0.1");
		params.put("buyer_email", "119609996@qq.com");
		params.put("buyer_id", "2088002713473324");
		params.put("trade_status", "TRADE_FINISHED");
		try {
			orderPayService.updateOrderByHavedPayit(params);
		} catch (YiPinStoreException e) {
			e.printStackTrace();
		}
		System.out.println("success");
	}
}
