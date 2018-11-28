package com.ytoxl.module.yipin.base.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ytoxl.module.yipin.base.BaseTest;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;

public class SaleServiceImplTest extends BaseTest {
	@Autowired
	private SaleService saleService;
	
	@Test
	public void testGetSaleById(){
		try {
			saleService.getSaleById(23);
		} catch (YiPinStoreException e) {
			e.printStackTrace();
		}
	}
}
