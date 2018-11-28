package com.ytoxl.module.yipin.base.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ytoxl.module.core.common.persistence.BaseSqlMapper;
import com.ytoxl.module.yipin.base.dataobject.UserAddress;

/**
 * @author wangguoqing
 *
 */
public interface UserAddressMapper<T extends UserAddress> extends BaseSqlMapper<T> {
	
	/**
	 * 根据用户id查询该用户所有的收货地址
	 * @param userId
	 * @return
	 * @throws DataAccessException
	 */
	public List<UserAddress> getReceiverAddressList(Integer userId) throws DataAccessException;

	/**
	 * 根据用户receiverAddressId的设置其默认的收货地址
	 * @param userAddressId
	 * @return
	 * @throws DataAccessException
	 */
	public void updateDefaultAddress(Integer userAddressId) throws DataAccessException;
	
	/**
	 * 根据用户userId的设置其默认的收货地址
	 * @param userId
	 * @return
	 * @throws DataAccessException
	 */
	public void updatesNotDefaultAddress(Integer userId) throws DataAccessException;
	/**
	 *根据用户的Id 获取总收货地址条数
	 * @param userId
	 * @throws DataAccessException
	 */
	public int getAddressCountByUserId(Integer userId) throws DataAccessException;
	
	/**
	 * 根据主键ID&&userId获取一条地址记录
	 * @param param
	 * @return
	 * @throws DataAccessException
	 */
	public UserAddress getUserAddressByUserIdAndId(Map<String,Integer> param) throws DataAccessException;
	
	/**
	 * 获取该用户默认的地址
	 * @param userId
	 * @return
	 * @throws DataAccessException
	 */
	public List<UserAddress> getDefaultAddress(Integer userId) throws DataAccessException;
	
}
