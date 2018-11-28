package com.ytoxl.module.yipin.order.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ytoxl.module.yipin.order.BaseTest;
import com.ytoxl.module.yipin.order.dataobject.OrderAddress;

public class OrderAddressMapperTest extends BaseTest {

	@Autowired
	private OrderAddressMapper<OrderAddress> orderAddressMapper;
	
	@Test
	public void testSearchOrderAddress(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderId", 1);
		map.put("packageNo", "001");
		OrderAddress  oa = orderAddressMapper.getOrderAddressByOrderPackageNo(map);
		System.out.println(oa.getReceiveAddress());
	}
	
	@Test
	public void testUpdateBathOrderPacekageStatus(){
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderAddressIds", list);
		map.put("status", (short)2);
		int r = orderAddressMapper.updateBathOrderPacekageStatus(list,(short)2);
		System.out.println(r);
	}
}
