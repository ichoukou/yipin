package com.ytoxl.module.yipin.order.mapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ytoxl.module.yipin.base.common.utils.ExcelUtils;
import com.ytoxl.module.yipin.order.BaseTest;
import com.ytoxl.module.yipin.order.dataobject.OrderHead;
import com.ytoxl.module.yipin.order.dataobject.resultmap.OrderExportModel;

public class OrderHeadMapperTest extends BaseTest {

	@Autowired
	private OrderHeadMapper<OrderHead> orderHeadMapper;
	
	@Test
	public void testExportOrder(){
		Map<String,Object> map = new HashMap<String,Object>();
		List<OrderExportModel> list = orderHeadMapper.listOrders(map);
		System.out.println(list.get(0).getOrderCreateTime().toString());
	}
	
	@Test
	public void testGetOrder(){
		Integer orderId = 1;
		OrderHead oh = orderHeadMapper.getOrderById(orderId);
		System.out.println(oh.getOrderNo());
	}
	
}
