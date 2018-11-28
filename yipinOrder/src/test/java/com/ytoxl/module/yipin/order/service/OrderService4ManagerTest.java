package com.ytoxl.module.yipin.order.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.common.utils.ExcelUtils;
import com.ytoxl.module.yipin.order.BaseTest;

public class OrderService4ManagerTest extends BaseTest {

	@Autowired
	private OrderService4Manage orderService4Manage;
	@Autowired
	private OrderService4Timer orderService4Timer;
	
	@Test
	public void testExcelImport(){
		try{
			File file = new File("D:\\批量发货模板.xlsx");
			InputStream inp = new FileInputStream(file);
			ExcelUtils excel = new ExcelUtils();
			excel.setSheetName("Sheet1");
			List<String[]> orders = excel.read(inp);
			orderService4Manage.updateBatchUpload(orders, 1);
			System.out.println("OK");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试超过1小时订单取消
	 */
	@Test
	public void testOrderCancelTimer(){
		try {
			int size = orderService4Timer.updateStatusToCanceled();
			System.out.println(size);
		} catch (YiPinStoreException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGrapOrderCancelTimer(){
		try{
			int size = orderService4Timer.updateGrapStatusToCancel();
			System.out.println(size);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试超过15天订单自动收货完成
	 */
	@Test
	public void testOrderFinishTimer(){
		try {
			int size = orderService4Timer.updateStatusToFinished();
			System.out.println(size);
		} catch (YiPinStoreException e) {
			e.printStackTrace();
		}
	}
}
