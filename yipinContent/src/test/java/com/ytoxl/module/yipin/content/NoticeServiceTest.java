package com.ytoxl.module.yipin.content;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.content.service.NoticeService;


public class NoticeServiceTest extends BaseTest{
	@Autowired
	private NoticeService noticeService;
	@Test
	public void getNotice(){
		try {
			noticeService.getNoticeById(1);
		} catch (YiPinStoreException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void homePageNoticeTest(){
		try {
			noticeService.getHomePageNotices();
		} catch (YiPinStoreException e) {
			e.printStackTrace();
		}
	}
}
