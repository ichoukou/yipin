package com.ytoxl.module.yipin.base.service;

import java.util.List;

import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.UserAddress;

public interface UserAddressService {
	
	/**
	 * 根据ID查询地址详细信息（使用中）
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserAddress> getRAddressDetailList(Integer userId)
			throws YiPinStoreException;

	/**
	 * 根据收货地址ID删除对应的地址
	 * 
	 * @param userAddressId
	 * @return
	 */
	public Integer delAddress(Integer id) throws YiPinStoreException;

	/**
	 * 增加一个新的收货地址
	 * 
	 * @param userAddress
	 * @return
	 */
	public Integer addAddress(UserAddress userAddress)
			throws YiPinStoreException;

	/**
	 * 根据adressId 更新一个收货地址
	 * 
	 * @param userAddressId
	 * @return
	 */
	public Integer updateAddress(UserAddress userAddress)
			throws YiPinStoreException;

	/**
	 * 根据userAddress 设置成新的收货地址
	 * 
	 * @param userAddress
	 * @return
	 */
	public void updateDefualtAddress(UserAddress userAddress)
			throws YiPinStoreException;

	/**
	 * 根据id 查询单独的收货地址
	 * 
	 * @param regionId
	 * @return
	 */
	public UserAddress getAddress(Integer regionId) throws YiPinStoreException;

	/**
	 * 根据userid 设置所有的收货地址
	 * 
	 * @param userId
	 * @return
	 */
	public void updatesNotDefaultAddress(Integer userId)
			throws YiPinStoreException;

	/**
	 * 根据用户的Id获取用户的收货地址条数
	 * 
	 * @param userId
	 * @return
	 * @throws YiPinStoreException
	 */
	public int getAddressCountByUserId(Integer userId) throws YiPinStoreException;

	/**
	 * 判断地址是不是当前用户的
	 * 
	 * @return
	 * @throws YiPinStoreException
	 */
	public Boolean checkAddressIsMine(Integer addressId, Integer CurrentUserId)
			throws YiPinStoreException;
	
	/**
	 * 根据主键ID&&userId获取一条地址记录
	 * @param id
	 * @param UserId
	 * @return
	 * @throws YiPinStoreException
	 */
	public UserAddress getUserAddressByUserIdAndId(Integer id, Integer UserId)
			throws YiPinStoreException;
	
	/**
	 * 根据买家用户Id获取一条默认收货地址记录
	 * @param id
	 * @param userId
	 * @return
	 * @throws YiPinStoreException
	 */
	public UserAddress getDefaultAddress(Integer userId)throws YiPinStoreException;
	
}
