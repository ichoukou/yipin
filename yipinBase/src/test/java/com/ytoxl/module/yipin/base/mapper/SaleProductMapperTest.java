package com.ytoxl.module.yipin.base.mapper;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ytoxl.module.yipin.base.BaseTest;
import com.ytoxl.module.yipin.base.dataobject.SaleProduct;

public class SaleProductMapperTest extends BaseTest {
	@Autowired
	private SaleProductMapper saleProductMapper;
	@Test
	public void testlistAllBySaleProductId() {
		List<SaleProduct> saleProductList = saleProductMapper.listAllBySaleId(21);
		System.out.println(saleProductList.size());
	}

}
