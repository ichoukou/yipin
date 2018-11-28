package com.ytoxl.module.yipin.base.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ytoxl.module.core.common.persistence.BaseSqlMapper;
import com.ytoxl.module.user.dataobject.User;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.CustomMyUser;
import com.ytoxl.module.yipin.base.dataobject.UserInfo;

/**
 * 用户信息
 * 
 * @author zengzhiming
 * 
 */
public interface UserInfoMapper<T extends UserInfo> extends BaseSqlMapper<T> {
	/**
	 * 添加用户信息
	 * 
	 * @param user
	 */
	public void registerUserInfo(User user) throws DataAccessException;

	/**
	 * 添加用户信息
	 * 
	 * @param user
	 */
	public void addUserInfo(CustomMyUser user) throws DataAccessException;


	/**
	 * 根据用户的id 查询userInfo
	 * 
	 * @param userId
	 * @return
	 * @throws DataAccessException
	 */
	public UserInfo getUserInfoByUserId(Integer userId)
			throws DataAccessException;

	/**
	 * 根据用户的id更新user
	 * 
	 * @param userInfo
	 * @throws DataAccessException
	 */
	public void updateUser(UserInfo userInfo) throws DataAccessException;

	/**
	 * 更新用户信息
	 * 
	 * @param userInfo
	 * @return
	 */
	public Integer updateUserRegister(UserInfo userInfo)
			throws DataAccessException;

	/**
	 * 统计用户数量
	 * 
	 * @param userInfo
	 * @return
	 */
	public Integer searchSellersCount(Map<String, Object> sellerPage)
			throws DataAccessException;

	/**
	 * 分页查询商家
	 * 
	 * @param sellerPage
	 * @throws UhomeStoreException
	 */
	public List<UserInfo> searchSellers(Map<String, Object> sellerPage)
			throws DataAccessException;

	/**
	 * 根据加类型和激活状态获取商家信息
	 * 
	 * @param map
	 * 
	 * @throws YiPinStoreException
	 */
	public List<UserInfo> listSellersByStatusAndType(Map<String, Object> map)
			throws DataAccessException;

	/**
	 * 根据品牌ID获取商家信息
	 * 
	 * @param map
	 * 
	 * @throws YiPinStoreException
	 */
	public List<UserInfo> listSellersByBrandId(Integer brandId)
			throws DataAccessException;

	/**
	 * 获取需要导出的卖家
	 * @param map
	 * @return
	 * @throws DataAccessException
	 */
	public List<UserInfo> listExportSellers(Map<String,Object> map) throws DataAccessException;

	/**
	 * 分页查询买家
	 * 
	 * @param buyerPage
	 * @throws DataAccessException
	 */
	public List<UserInfo> searchBuyers(Map<String, Object> buyerPage)
			throws DataAccessException;

	/**
	 * 分页查询买家
	 * 
	 * @param buyerPage
	 * @throws DataAccessException
	 */
	public Integer searchBuyersCount(Map<String, Object> buyerPage)
			throws DataAccessException;

	/**
	 * 根据商家id和type获取UserInfo
	 * 
	 * @param map
	 * 
	 * @throws YiPinStoreException
	 */
	public UserInfo getUserInfoByUserIdAndType(Map<String, Object> map)
			throws DataAccessException;

	/**
	 * 添加用户选择的品牌
	 * 
	 * @param userInfo
	 * @return
	 * @throws DataAccessException
	 */
	public Integer addSellerBrand(UserInfo userInfo) throws DataAccessException;

	/**
	 * 添加卖家
	 * 
	 * @param userInfo
	 * @return
	 */
	public Integer addSeller(UserInfo userInfo) throws DataAccessException;

	/**
	 * 删除用户选择的品牌
	 * 
	 * @param seller
	 * @return
	 * @throws DataAccessException
	 */
	public Integer delSellerBrand(UserInfo userInfo) throws DataAccessException;

	/**
	 * 查询用户是否选择该品牌
	 * 
	 * @param seller
	 * @return
	 * @throws DataAccessException
	 */
	public Integer listBrandsCount(UserInfo userInfo)
			throws DataAccessException;

	/**
	 * 更新卖家
	 * 
	 * @param userInfo
	 * @return
	 */
	public Integer updateSeller(UserInfo userInfo) throws DataAccessException;
	/**
	 * 添加卖家的时候判断邮箱是否存在
	 */
	public User validateEmailIsRepate(String email) throws DataAccessException ;
	
	/**
	 * 根据用户id获取已经选择的品牌的城市ID
	 * @param userId
	 */
	public Integer searchCityIdByUserId(Integer userId) throws DataAccessException ;
}
