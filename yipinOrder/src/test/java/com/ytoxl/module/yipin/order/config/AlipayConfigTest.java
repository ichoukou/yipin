package com.ytoxl.module.yipin.order.config;

import org.junit.Test;

import com.ytoxl.module.yipin.order.BaseTest;
import com.ytoxl.module.yipin.order.alipay.config.AlipayConfig;


public class AlipayConfigTest extends BaseTest {

	@Test
	public void testChangeDefaultProperty(){
		System.out.println(AlipayConfig.partner);
		System.out.println(AlipayConfig.key);
		System.out.println(AlipayConfig.seller_email);
		System.out.println(AlipayConfig.notify_url);
		System.out.println(AlipayConfig.return_url);
		System.out.println(AlipayConfig.input_charset);
		System.out.println(AlipayConfig.sign_type);
	}
}
