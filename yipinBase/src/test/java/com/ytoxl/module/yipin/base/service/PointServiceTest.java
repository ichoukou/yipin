package com.ytoxl.module.yipin.base.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ytoxl.module.yipin.base.BaseTest;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Point;

public class PointServiceTest extends BaseTest {
	 @Autowired
	 private PointService pointService;
	@Test
	public void pointTest() {
		 try {
		 short id=1;
		 pointService.addPointByUserId(54, id, 100, Point.TYPE_MINUS, 2);
		 } catch (YiPinStoreException e) {
		 e.printStackTrace();
		 }
	}
}
