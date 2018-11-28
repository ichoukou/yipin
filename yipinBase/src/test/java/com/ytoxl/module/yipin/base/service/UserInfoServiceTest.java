package com.ytoxl.module.yipin.base.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.ytoxl.module.yipin.base.BaseTest;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.UserInfo;

public class UserInfoServiceTest extends BaseTest{
	protected static Logger log = LoggerFactory.getLogger(UserInfoServiceTest.class);
	@Autowired
	private UserInfoService userInfoService;
	
	@Test
	public void infoTest(){
		try {
//			userInfoService.getUserInfoById(1);
			Integer id=userInfoService.searchUserIdByUserType();
			System.out.print("-------------"+id);
		} catch (YiPinStoreException e) {
			
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unused")
	@Test
	public void listUserInfoTest(){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("status", 1);
		map.put("userType", 1);
		try {
			Collection<UserInfo> collection=userInfoService.listSellersByStatusAndType(map);
			System.out.print(collection);
		} catch (YiPinStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void listUserInfoByBrandIdTest(){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("status", 1);
		map.put("userType", 1);
		try {
			List<UserInfo> collection=userInfoService.listSellersByBrandId(100000);
			System.out.print(collection);
		} catch (YiPinStoreException e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unused")
	@Test
	public void getUserInfoTest(){
//		try {
//			UserInfo userInfo=userInfoService.getUserInfoByUserIdAndType(2, 2);
//			System.out.print(userInfo);
//		} catch (YiPinStoreException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	@Test
	public void getSellerByUserId(){
		try {
			UserInfo userInfo=userInfoService.getSellerByUserId(2, false);
			System.out.print(userInfo);
		} catch (YiPinStoreException e) {
			e.printStackTrace();
		}
	}
}
