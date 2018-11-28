package com.ytoxl.module.yipin.base.service;

import java.util.List;

import com.ytoxl.module.user.dataobject.Uresource;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.MyUrole;
import com.ytoxl.module.yipin.base.dataobject.MyUser;

public interface AuthorityService {

		/**
		 * 根据创建人Id查询创建的角色
		 * @param userId
		 * @return
		 * @throws UhomeStoreException
		 */
		List<MyUrole> searchUroleByCreateUserId(Integer userId) throws YiPinStoreException;	
		/**
		 * 根据角色id查询所拥有资源信息
		 * @param uroleId
		 * @return
		 * @throws UhomeStoreException
		 */
		List<Uresource> getUresourcesByUroleId(Integer uroleId) throws YiPinStoreException;	
		/**
		 * 保存或更新角色及对应资源信息
		 * @param uroleId
		 * @param listUresource
		 * @throws UhomeStoreException
		 */
		void saveOrUpdateUroleAndUresource(Integer uroleId,String uroleName,Integer createUserId,List<Integer> uresourceIds) throws YiPinStoreException;
		/**
		 * 根据创建人ID查询创建的用户
		 * @param userId
		 * @return
		 * @throws UhomeStoreException
		 */
		List<MyUser> listUserBycreateUserId(Integer userId)
				throws YiPinStoreException;
		/**
		 * 根据uroleid查询urole信息
		 * @param uroleId
		 * @return
		 * @throws UhomeStoreException
		 */
		MyUrole getUroleByUroleId(Integer uroleId) throws YiPinStoreException;
		/**
		 * 根据userid查询user信息
		 * @param userId
		 * @return
		 * @throws UhomeStoreException
		 */
		MyUser getMyUserByUserId(Integer userId) throws YiPinStoreException;
		/**
		 * 更新用户角色
		 * @param myUser
		 * @throws UhomeStoreException
		 */
		void addUserUroles(MyUser myUser) throws YiPinStoreException;
		/**
		 * 根据uroleId删除角色
		 * @param uroleId
		 * @throws UhomeStoreException
		 */
		void deleteUrole(Integer uroleId) throws YiPinStoreException;
		/**
		 * 更新用户状态
		 * @param myUser
		 * @throws UhomeStoreException
		 */
		void updateStatusByUserId(MyUser myUser) throws YiPinStoreException;
		/**
		 * 删除子账户
		 * @param userId
		 * @throws UhomeStoreException
		 */
		void deleteUser(Integer userId) throws YiPinStoreException;
		
		
}
