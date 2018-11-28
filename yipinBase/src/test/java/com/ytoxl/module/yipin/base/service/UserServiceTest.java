package com.ytoxl.module.yipin.base.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.ytoxl.module.user.dataobject.User;
import com.ytoxl.module.user.service.UserService;
import com.ytoxl.module.yipin.base.BaseTest;

	


public class UserServiceTest extends BaseTest {
	protected static Logger log = LoggerFactory.getLogger(UserServiceTest.class);

	@Autowired
	private UserService userService;
	/**
	 * 根据用户名获取用户信息
	 */
	@Test
	public void getByNameTest(){
		User user = userService.getByName("ytoxl");
		System.out.println(user);
	}
	/**
	 * 检查 用户名
	 */
	@Test
	public void repeatUsername(){
		if(!userService.repeatUsername("ytoxl")||!userService.repeatUsername("ytoxl")){
			System.out.println("ytoxl"+"不存在");
		}else{
			System.out.println("ytoxl"+"已经存在");
		}
	}
	/**
	 * 检查Email
	 */
	@Test
	public void registerCheck(){
		if(!userService.repeatEmail("ytoxl")||!userService.repeatUsername("ytoxl")){
			System.out.println("ytoxl"+"不存在");
		}else{
			System.out.println("ytoxl"+"已经存在");
		}
	}
}
