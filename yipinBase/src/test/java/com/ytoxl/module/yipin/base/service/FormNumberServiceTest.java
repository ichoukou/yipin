package com.ytoxl.module.yipin.base.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ytoxl.module.yipin.base.BaseTest;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;

public class FormNumberServiceTest extends BaseTest {
	
	@Autowired
	private DocNumSystemService docNumSystemService;
	
	@Test
	public void testGenernateOrderNo(){
		String no;
		try {
			no = docNumSystemService.getOrderNum();
			System.out.println(no);
		} catch (YiPinStoreException e) {
			System.out.println("exception order no "+e);
		}
		
	} 
	
	@Test
	public void testGenernateOrderNo2(){
		String no;
		try {
			no = docNumSystemService.getOrderNum();
			System.out.println(no);
		} catch (YiPinStoreException e) {
			System.out.println("exception order no "+e);
		}
		
	}

}
