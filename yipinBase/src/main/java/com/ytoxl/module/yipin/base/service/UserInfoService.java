package com.ytoxl.module.yipin.base.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.ytoxl.module.core.common.pagination.BasePagination;
import com.ytoxl.module.user.common.exception.YtoxlUserException;
import com.ytoxl.module.user.dataobject.User;
import com.ytoxl.module.user.security.CustomUserDetails;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.CustomMyUser;
import com.ytoxl.module.yipin.base.dataobject.UserInfo;

/**
 * 用户信息服务接口
 * 
 * @author qixiaobing
 * 
 */
public interface UserInfoService {
	/**
	 * 根据userId查询卖家资料
	 * 
	 * @return
	 * @throws YiPinStoreException
	 */
	public UserInfo getSellerByUserId(Integer userId, boolean isEdit)
			throws YiPinStoreException;

	/***
	 * 前台用户的注册
	 * 
	 * @param user
	 */
	public void addRegister(User user) throws YiPinStoreException,
			YtoxlUserException;

	/***
	 * 前台用户的注册
	 * 
	 * @param user
	 */
	public void addRegister(CustomMyUser user) throws YiPinStoreException,
			YtoxlUserException;

	/**
	 * 根据用户id获取userInfo信息(包含品牌信息)
	 * 
	 * @param userId
	 * @return
	 * @throws YiPinStoreException
	 */
	public UserInfo getUserInfoById(Integer userId) throws YiPinStoreException;

	/**
	 * 根据用户id 和type获取userInfo信息
	 * 
	 * @param userId
	 *            用户id
	 * @param type
	 *            0 管理员 1 商家用户 2 买家
	 * @return
	 * @throws YiPinStoreException
	 */
//	public UserInfo getUserInfoByUserIdAndType(Integer userId, Integer type)
//			throws YiPinStoreException;

	/**
	 * 更新用户信息
	 * 
	 * @param userInfo
	 * @throws YiPinStoreException
	 */
	public void updateUserAndUserInfo(UserInfo userInfo)
			throws YiPinStoreException;

	/**
	 * 更新用户表
	 * 
	 * @param userInfo
	 * @throws YiPinStoreException
	 */
	public void updateUserInfo(UserInfo userInfo) throws YiPinStoreException;

	/**
	 * 
	 * @param userInfo
	 * @throws YiPinStoreException
	 */
	public void updateUserRegister(UserInfo userInfo)
			throws YiPinStoreException;

	/**
	 * 根据用户id获取user信息
	 * 
	 * @param userId
	 * @throws YiPinStoreException
	 */
	public UserInfo getUserByUserId(Integer userId) throws YiPinStoreException;

	/**
	 * 检测用户是否登录
	 * 
	 * @return
	 * @throws YiPinStoreException
	 */
	public CustomUserDetails checkUserIsLogin() throws YiPinStoreException;

	/**
	 * 商家返回 userId,商家子账号就返回 createByuserId
	 * 
	 * @return
	 * @throws YiPinStoreException
	 */
	public Integer searchUserIdByUserType() throws YiPinStoreException;

	/**
	 * 分页查询商家
	 * 
	 * @param sellerPage
	 * @throws YiPinStoreException
	 */
	public void searchSellers(BasePagination<UserInfo> sellerPage)
			throws YiPinStoreException;

	/**
	 * 更新用户状态
	 * 
	 * @param userId
	 * @param status
	 * @throws YiPinStoreException
	 */
	public void updateUserStauts(List<Integer> userIds, Integer status)
			throws YiPinStoreException;

	/**
	 * 重置密码
	 * 
	 * @param userId
	 * @throws YiPinStoreException
	 */
	public void resetPassword(Integer userId) throws YiPinStoreException;

	/**
	 * 根据加类型和激活状态获取商家信息
	 * 
	 * @param map
	 *            status(0=禁用，1=激活) userType(0 管理员 1 商家用户 2 买家)
	 * @return Collection<UserInfo>
	 * @throws YiPinStoreException
	 */
	public Collection<UserInfo> listSellersByStatusAndType(
			Map<String, Object> map) throws YiPinStoreException;

	/**
	 * 根据品牌ID获取商家信息
	 * 
	 * @param brandId
	 *            (品牌ID)
	 * @return Collection<UserInfo>
	 * @throws YiPinStoreException
	 */
	public List<UserInfo> listSellersByBrandId(Integer brandId)
			throws YiPinStoreException;

	/**
	 * 查询需要导出的商家
	 * 
	 * @return
	 * @throws YiPinStoreException
	 */

	public List<UserInfo> listExportSellers(Map<String,Object> map) throws YiPinStoreException;

	/**
	 * 更新密码
	 * @param seller
	 * @return
	 * @throws YiPinStoreException
	 */
	public Integer savePassword(UserInfo seller)throws YiPinStoreException;
	
	/**
	 * 分页查询买家
	 * 
	 * @param buyerPage
	 * @throws YiPinStoreException
	 */
	public void searchBuyers(BasePagination<UserInfo> buyerPage)
			throws YiPinStoreException;

	/**
	 * 更新商家信息
	 * 
	 * @param buyerPage
	 * @throws YiPinStoreException
	 */
	public void updateSeller(UserInfo userInfo) throws YiPinStoreException;

	/**
	 * 添加用户选择的品牌
	 * 
	 * @param userInfo
	 * @return
	 * @throws YiPinStoreException
	 */
	public Integer addSellerBrand(UserInfo userInfo) throws YiPinStoreException;
	/**
	 * 添加卖家
	 * @param seller
	 * @return
	 * @throws YiPinStoreException
	 */
	public Integer addSeller(UserInfo userInfo) throws YiPinStoreException;
	/**
	 * 删除用户选择的品牌
	 * @param userInfo
	 * @return
	 * @
	 */
	public Integer delSellerBrand(UserInfo userInfo) throws YiPinStoreException;
	/**
	 * 查询用户是否选择该品牌
	 * @param userInfo
	 * @return
	 * @throws YiPinStoreException
	 */
	public Integer listBrandsCount(UserInfo userInfo) throws YiPinStoreException;
	/**
	 * 添加卖家的时候判断邮箱是否存在
	 * @param email
	 * @param userId
	 * @return boolean true :非重复 false:重复
	 */
	public boolean validateEmailIsRepate(String email, Integer userId);

}
