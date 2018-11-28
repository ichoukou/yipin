package com.ytoxl.module.yipin.base.service;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ytoxl.module.yipin.base.BaseTest;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;

public class ExpressServiceTest extends BaseTest {
	
	@Autowired
	private ExpressService expressService;
	
	@Test
	public void test(){
		try {
//			expressService.getExpressDetailInfoFromKuaidi100("zhongtong", "757136102830");
			expressService.getExpressDetailInfoFromKuaidi100("yuantong", "7429233221");
			List<Map<Object,Object>> list = expressService.getExpressDetailInfo("7429233221");
			System.out.println(list);
		} catch (YiPinStoreException e) {
			System.err.println(e);
		}
	}
	
}
