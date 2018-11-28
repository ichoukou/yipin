package com.ytoxl.module.yipin.base.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ytoxl.module.yipin.base.BaseTest;
import com.ytoxl.module.yipin.base.dataobject.Sale;

public class SaleMapperTest extends BaseTest {
	@Autowired
	private SaleMapper<Sale> saleMapper;

	@Test
	public void testUpdateStatusByIds() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", 2);
		List<String> list = new ArrayList<String>();
		list.add("11");
		list.add("13");
		map.put("list", list);
		saleMapper.updateStatusByIds(map);
	}
	
	@Test
	public void querySaleWithBrandByLimit(){
		Sale saleCondition = new Sale(); 
		saleCondition.setBrandId(100008);
		saleCondition.setPreSelltime(new Date());
		saleCondition.setStatus(Sale.STATUS_RELEASE);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("limit", 10);
		map.put("start", 1);
		map.put("sale", saleCondition);
		saleMapper.searchSaleWithBrandByLimit(map);
		saleMapper.countSaleWithBrand(map);
	}
	
	
	@Test
	public void testQuerySales(){
		Sale saleCondition = new Sale(); 
		saleCondition.setBrandId(100008);
		saleCondition.setPreSelltime(new Date());
		saleCondition.setStatus(Sale.STATUS_RELEASE);
		// 商品状态
		List<Short> statusList = new ArrayList<Short>();
		statusList.add(Sale.STATUS_RELEASE);
		statusList.add(Sale.STATUS_UNRELEASE);
//		saleCondition.setStatusList(statusList);
		List<Sale> list = saleMapper.listBySale(saleCondition);
	}
}
