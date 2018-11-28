package com.ytoxl.module.yipin.base.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ytoxl.module.core.common.pagination.BasePagination;
import com.ytoxl.module.user.common.exception.YtoxlUserException;
import com.ytoxl.module.user.dataobject.User;
import com.ytoxl.module.user.dataobject.resultmap.UserModel;
import com.ytoxl.module.user.mapper.UserMapper;
import com.ytoxl.module.user.security.CustomUserDetails;
import com.ytoxl.module.user.service.UserService;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.common.utils.StringUtils;
import com.ytoxl.module.yipin.base.dataobject.City;
import com.ytoxl.module.yipin.base.dataobject.CustomMyUser;
import com.ytoxl.module.yipin.base.dataobject.Point;
import com.ytoxl.module.yipin.base.dataobject.PointDetail;
import com.ytoxl.module.yipin.base.dataobject.Region;
import com.ytoxl.module.yipin.base.dataobject.UserInfo;
import com.ytoxl.module.yipin.base.dataobject.resultmap.RegionModel;
import com.ytoxl.module.yipin.base.mapper.PointDetailMapper;
import com.ytoxl.module.yipin.base.mapper.PointMapper;
import com.ytoxl.module.yipin.base.mapper.RegionMapper;
import com.ytoxl.module.yipin.base.mapper.UserInfoMapper;
import com.ytoxl.module.yipin.base.service.CityService;
import com.ytoxl.module.yipin.base.service.PointService;
import com.ytoxl.module.yipin.base.service.RegionService;
import com.ytoxl.module.yipin.base.service.UserAddressService;
import com.ytoxl.module.yipin.base.service.UserInfoService;

/**
 * 用户
 * 
 * @author qixiaobing
 * 
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

	private static Logger logger = LoggerFactory
			.getLogger(UserInfoServiceImpl.class);
	@Autowired
	private UserMapper<User> userMapper;
	@Autowired
	private UserInfoMapper<UserInfo> userInfoMapper;
	@Autowired
	private RegionService regionService;
	@Autowired
	private PointMapper<Point> pointMapper;
	@Autowired
	private RegionMapper<Region> regionMapper;
	@Autowired
	private PointDetailMapper<PointDetail> pointDetailMapper;
	@Autowired
	private UserService userService;
	@Autowired
	private UserAddressService userAddressService;
	@Autowired
	private CityService cityService;
	@Value("${defaultPass}")
	private String defaultPass;
	@Autowired
	private PointService pointService;

	/**
	 * 根据ID查询卖家信息
	 * 
	 * @param sellerId
	 * @return
	 */
	@Override
	public UserInfo getUserInfoById(Integer userId) throws YiPinStoreException {
		
		UserInfo userInfo = null;
		try{
		userInfo = userInfoMapper.getUserInfoByUserId(userId);
		if (null != userInfo.getCompanyRegionId()) {
			// Region region =
			// regionMapper.getRegionCodesByRegionId(userInfo.getRegionId());
			// userInfo.setRegion(region);
			RegionModel regionModel = regionMapper
					.getRegionIdsByRegionId(userInfo.getCompanyRegionId());
			Region region = regionMapper.getDetailInfoByRegionId(userInfo
					.getCompanyRegionId());
			userInfo.setArea(region.getProvince() + " " + region.getCity()
					+ " " + region.getCounty());
			userInfo.setRegionModel(regionModel);
		}
		}catch(Exception e){
			
		}
		return userInfo;
	}

	@Override
	public void updateUserAndUserInfo(UserInfo userInfo)
			throws YiPinStoreException {
		try {
			updateUserInfo(userInfo);// 更新userInfo表中的数据
			updateUserRegister(userInfo);// 更新user表中的数据
			return;
		} catch (DataAccessException d) {
			// 抛出异常提示action 其他选项长度超出异常 故注释email重复! 提示
			throw new YiPinStoreException("email重复!");
		}

	}

	@Override
	public UserInfo getUserByUserId(Integer userId) throws YiPinStoreException {
		UserInfo userInfo = userInfoMapper.getUserInfoByUserId(userId);
		/*
		 * if (null != userInfo.getCompanyRegionId()) { // Region region = //
		 * regionMapper.getRegionCodesByRegionId(userInfo.getRegionId()); //
		 * userInfo.setRegion(region); RegionModel regionModel = regionMapper
		 * .getRegionIdsByRegionId(userInfo.getCompanyRegionId());
		 * userInfo.setRegionModel(regionModel); }
		 */
		return userInfo;
	}

	/**
	 * 根据userId查询卖家资料
	 * 
	 * @return
	 * @throws YiPinStoreException
	 */
	public UserInfo getSellerByUserId(Integer userId, boolean isEdit)
			throws YiPinStoreException {
		// 首先得到用戶资料
		UserInfo userInfo = userInfoMapper.getUserInfoByUserId(userId);
//		Integer cityId=userInfoMapper.searchCityIdByUserId(userId);
//		City city=cityService.searchCityByCityId(cityId);
//		userInfo.setCity(city);
//		userInfo.setBrandCityId(cityId);
		if(!isEdit){
			//查询用户所在地
			if(userInfo.getCompanyRegionId()!=null && !userInfo.getCompanyRegionId().equals("")){
				Region region=regionMapper.getDetailInfoByRegionId(userInfo.getCompanyRegionId());
				StringBuffer sb=new StringBuffer();
				if(region!=null){
					sb.append(region.getProvince());
					sb.append(" "+region.getCity());
					sb.append(" "+region.getCounty());
				}
				sb.append(" "+userInfo.getCompanyAddress());
				userInfo.setCompanyAddress(sb.toString());
			}
			//查询退货地址
			if(userInfo.getReceiverRegionId()!=null && !userInfo.getReceiverRegionId().equals("")){
				Region region=regionMapper.getDetailInfoByRegionId(userInfo.getReceiverRegionId());
				StringBuffer sb=new StringBuffer();
				sb.append(region.getProvince());
				sb.append(" "+region.getCity());
				sb.append(" "+region.getCounty());
				sb.append(" "+userInfo.getReceiverAddress());
				userInfo.setReceiverAddress(sb.toString());
			}
			//查询用户所在地
			if(userInfo.getShiperRegionId()!=null && !userInfo.getShiperRegionId().equals("")){
				Region region=regionMapper.getDetailInfoByRegionId(userInfo.getShiperRegionId());
				StringBuffer sb=new StringBuffer();
				sb.append(region.getProvince());
				sb.append(" "+region.getCity());
				sb.append(" "+region.getCounty());
				sb.append(" "+userInfo.getShiperAddress());
				userInfo.setShiperAddress(sb.toString());
			}
		}else{
			//查询地址code
			if(userInfo.getShiperRegionId()!=null){
				RegionModel shiperModel=regionMapper.getRegionIdsByRegionId(userInfo.getShiperRegionId());
				if(shiperModel!=null){
					userInfo.setShiperModel(shiperModel);
				}
			}
			
			if(userInfo.getReceiverRegionId()!=null){
				RegionModel receiverModel=regionMapper.getRegionIdsByRegionId(userInfo.getReceiverRegionId());
				if(receiverModel!=null){
					userInfo.setReceiverModel(receiverModel);
				}
			}

			if(userInfo.getCompanyRegionId()!=null){
				RegionModel companyModel=regionMapper.getRegionIdsByRegionId(userInfo.getCompanyRegionId());
				if(companyModel!=null){
					userInfo.setCompanyModel(companyModel);
				}
			}
		}
		return userInfo;
	}

	@Override
	public void updateUserInfo(UserInfo userInfo) throws YiPinStoreException {
		userInfoMapper.updateUser(userInfo);
	}

	@Override
	public void updateUserRegister(UserInfo userInfo)
			throws YiPinStoreException {
		userInfoMapper.updateUserRegister(userInfo);
	}

	@Override
	public void addRegister(User user) throws YiPinStoreException,
			YtoxlUserException {
		CustomMyUser cUser = new CustomMyUser();
		cUser.setUserType(UserInfo.USER_TYPE_CUSTOMER);// 定义买家类型
		List<Integer> list = new ArrayList<Integer>();// 为买家加载角色
		list.add(UserInfo.USER_ROLE_BUYER);
		user.setStatus(User.STATUS_ABLE);// 设置其为激活状态
		user.setCreateByUserId(0);// 设置其为激活状态
		userMapper.add(user);//
		userMapper.addUserUroles(user.getUserId(), list);
		try {
			BeanUtils.copyProperties(cUser, user);
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
			throw new YiPinStoreException("用户资料存储出错!");
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage());
			throw new YiPinStoreException("用户资料存储出错!");
		}
		addRegister(cUser);// 添加用户
		boolean index = pointService.addPointByUserId(user.getUserId(),Point.POINTSOURCE_REGISTER,Point.POINTSOURCE_REGISTER_SCOPE,Point.TYPE_ADD,cUser.getUserId());
		if(!index){
			logger.error("注册积分失败！");
			throw new YiPinStoreException("注册积分失败！");
		}
		// 注册成功发送邮件

	}

	@Override
	public void addRegister(CustomMyUser user) throws YiPinStoreException,
			YtoxlUserException {
		userInfoMapper.addUserInfo(user);
	}

	@Override
	public CustomUserDetails checkUserIsLogin() throws YiPinStoreException {
		// 找到当前 登录的用户
		CustomUserDetails customUserDetail = null;
		try {
			customUserDetail = userService.getCurrentUser();
			if (null != customUserDetail) {
				return customUserDetail;
			}
		} catch (YtoxlUserException e) {
			logger.error("当前用户没有登录:" + e.getMessage());
			throw new YiPinStoreException("当前用户没有登录");
		}
		return null;
	}

	@Override
	public Integer searchUserIdByUserType() throws YiPinStoreException {
		Integer userTypeId = 0;
		Integer userId;
		try {
			// 获取当前用户id
			userId = userService.getCurrentUser().getUserId();
			UserModel user = userService.getUserModel(userId);
			UserInfo userInfo = userInfoMapper.getUserInfoByUserId(userId);//得到当前用户资料
			if (userInfo.getUserType().equals(UserInfo.USER_TYPE_SELLER_SON)) {// 商家子账号
				return user.getCreateByUserId();
			}
			if (userInfo.getUserType().equals(UserInfo.USER_TYPE_SELLER)) {// 商家账号
				return userId;
			}
			if(userInfo.getUserType().equals(UserInfo.USER_TYPE_MANAGER)){//如果是管理员账号
				return userId;
			}
			if(userInfo.getUserType().equals(UserInfo.USER_TYPE_MANAGER_SON)){//如果是管理员子账号
				return user.getCreateByUserId();
			}
		} catch (YtoxlUserException e) {
			logger.error("获取用户信息出现异常" + e);
		}
		return userTypeId;
	}

	@Override
	public void searchSellers(BasePagination<UserInfo> sellerPage)
			throws YiPinStoreException {
		Map<String, Object> searchParams = sellerPage.getSearchParamsMap();
		searchParams.put("userType", 1);//userType ：1  为商家
		if (sellerPage.isNeedSetTotal()) {
			Integer total = userInfoMapper.searchSellersCount(searchParams);
			sellerPage.setTotal(total);
		}
		Collection<UserInfo> result = userInfoMapper
				.searchSellers(searchParams);
		sellerPage.setResult(result);

	}

	/**
	 * 更新用户状态
	 * 
	 * @param userId
	 * @param status
	 * @throws UhomeStoreException
	 */
	@Override
	public void updateUserStauts(List<Integer> userIds, Integer status)
			throws YiPinStoreException {
		userMapper.updateStatusByUserIds(userIds, status);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void resetPassword(Integer userId) throws YiPinStoreException {
		userService.updateDefaultPassword(userId);
	}

	@Override
	public Collection<UserInfo> listSellersByStatusAndType(
			Map<String, Object> map) throws YiPinStoreException {
		Collection<UserInfo> collection = userInfoMapper
				.listSellersByStatusAndType(map);
		return collection;
	}

	@Override
	public List<UserInfo> listSellersByBrandId(Integer brandId)
			throws YiPinStoreException {
		List<UserInfo> collection = userInfoMapper
				.listSellersByBrandId(brandId);
		return collection;
	}

	/**
	 * 查询需要导出的商家
	 */
	@Override
	public List<UserInfo> listExportSellers(Map<String,Object> map) throws YiPinStoreException {
		return userInfoMapper.listExportSellers(map);
	}

	@Override
	public void searchBuyers(BasePagination<UserInfo> buyerPage)
			throws YiPinStoreException {
		Map<String, Object> searchParams = buyerPage.getSearchParamsMap();
		if (buyerPage.isNeedSetTotal()) {

			// 获取买家总数
			Integer total = userInfoMapper.searchBuyersCount(searchParams);
			buyerPage.setTotal(total);
		}

		// 获取买家list
		Collection<UserInfo> result = userInfoMapper.searchBuyers(searchParams);

		// 循环获取买家地址
		for (Iterator<UserInfo> iterator = result.iterator(); iterator
				.hasNext();) {
			UserInfo userInfo = (UserInfo) iterator.next();

			// 查询用户所在地
			if (userInfo.getCompanyRegionId() != null
					&& !userInfo.getCompanyRegionId().equals("")) {
				Region region = regionService.getDetailInfoByRegionId(userInfo
						.getCompanyRegionId());
				StringBuffer sb = new StringBuffer();
				sb.append(region.getProvince());
				sb.append(" " + region.getCity());
				sb.append(" " + region.getCounty());
				sb.append(" "
						+ (userInfo.getCompanyAddress() == null ? "" : userInfo
								.getCompanyAddress()));
				userInfo.setCompanyAddress(sb.toString());
				userInfo.setProvince(region.getProvince());
			}

			// 查询用户订阅的品牌名称
			/*
			 * userInfo.setBrandNames(userInfoMapper.listSubBrands(userInfo
			 * .getUserId()));
			 */

			// 查询用户收货地址
			userInfo.setUserAddress(userAddressService
					.getDefaultAddress(userInfo.getUserId()));
		}

		buyerPage.setResult(result);
	}

//	@Override
//	public UserInfo getUserInfoByUserIdAndType(Integer userId, Integer type)
//			throws YiPinStoreException {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("userId", userId);
//		map.put("userType", type);
//		UserInfo userInfo = userInfoMapper.getUserInfoByUserIdAndType(map);
//		return userInfo;
//	}

	/**
	 * 更新卖家
	 * 
	 * @param seller
	 * @throws UhomeStoreException
	 */
	@Override
	public void updateSeller(UserInfo userInfo) throws YiPinStoreException {
		userInfo.setTel(userInfo.getUser().getTel());
		userInfo.setEmail(userInfo.getUser().getEmail());
//		// 公司地址
//		Region companyRegion = regionService.getDetailInfoByRegionId(userInfo
//				.getCompanyRegionId());
//		userInfo.setCompanyAddress(companyRegion.getProvince() + " "
//				+ companyRegion.getCity() + " " + companyRegion.getCounty());
//		// 发货地址
//		Region shiperRegion = regionService.getDetailInfoByRegionId(userInfo
//				.getShiperRegionId());
//		userInfo.setShiperAddress(shiperRegion.getProvince() + " "
//				+ shiperRegion.getCity() + " " + shiperRegion.getCounty());
//		// 退货地址
//		Region receiverRegion = regionService.getDetailInfoByRegionId(userInfo
//				.getReceiverRegionId());
//		userInfo.setReceiverAddress(receiverRegion.getProvince() + " "
//				+ receiverRegion.getCity() + " " + receiverRegion.getCounty());
		// 需要根据最后一个regionCode查询到regionId
		if (StringUtils.isNotEmpty(userInfo.getShiperRegionCodes())
				&& userInfo.getShiperRegionCodes().split(",").length == 3) {
			Integer shiperId = regionMapper.getRegionByCode(
					userInfo.getShiperRegionCodes()
							.substring(
									userInfo.getShiperRegionCodes()
											.lastIndexOf(",") + 1))
					.getRegionId(); // 发货地址
			userInfo.setShiperRegionId(shiperId);
		}
		if (StringUtils.isNotEmpty(userInfo.getReceiverRegionCodes())
				&& userInfo.getReceiverRegionCodes().split(",").length == 3) {
			Integer receiverId = regionMapper.getRegionByCode(
					userInfo.getReceiverRegionCodes()
							.substring(
									userInfo.getReceiverRegionCodes()
											.lastIndexOf(",") + 1))
					.getRegionId(); // 退货地址
			userInfo.setReceiverRegionId(receiverId);
		}
		if (StringUtils.isNotEmpty(userInfo.getCompanyRegionCodes())
				&& userInfo.getCompanyRegionCodes().split(",").length == 3) {
			Integer companyId = regionMapper.getRegionByCode(
					userInfo.getCompanyRegionCodes()
							.substring(
									userInfo.getCompanyRegionCodes()
											.lastIndexOf(",") + 1))
					.getRegionId(); // 公司地址
			userInfo.setCompanyRegionId(companyId);
		}

		try {
			userService.saveUser(userInfo.getUser());
		} catch (YtoxlUserException e) {
			throw new YiPinStoreException(e.getMessage());
		}
		userInfoMapper.updateSeller(userInfo);

		// 添加该商家要卖的品牌
		List listBrandIds = userInfo.getListBrandIds();
		if (listBrandIds == null || listBrandIds.size() == 0) {
			// 删除所有用户选择的品牌
			this.delSellerBrand(userInfo);
		} else {
			// 选删除用户取消选择的品牌
			// StringBuffer sb=new StringBuffer();
			// Iterator iterator1 = listBrandIds.iterator();
			// sb.append(iterator1.next());
			// for (; iterator1
			// .hasNext();) {
			// Integer object = (Integer) iterator1.next();
			// sb.append(","+object);
			// }
			userInfo.setBrandIds(listBrandIds.toString().substring(1,
					listBrandIds.toString().length() - 1));
			this.delSellerBrand(userInfo);

			for (Iterator iterator = listBrandIds.iterator(); iterator
					.hasNext();) {
				Integer brandId = (Integer) iterator.next();
				userInfo.setBrandId(brandId);
				if (this.listBrandsCount(userInfo) == 0) {
					this.addSellerBrand(userInfo);
				}
			}
		}
	}

	/**
	 * 添加用户选择的品牌
	 * 
	 * @param seller
	 * @return
	 * @throws UhomeStoreException
	 */
	@Override
	public Integer addSellerBrand(UserInfo userInfo) throws YiPinStoreException {
		return userInfoMapper.addSellerBrand(userInfo);
	}

	@Override
	public Integer savePassword(UserInfo userInfo) throws YiPinStoreException {
		try {
			if (userService.checkPassword(userService.getCurrentUser()
					.getUsername(), userInfo.getUser().getPassword())) {
				if (StringUtils.isNotEmpty(userInfo.getNewPassword())
						&& StringUtils.isNotEmpty(userInfo
								.getConfirmNewPassword())
						&& userInfo.getNewPassword().equals(
								userInfo.getConfirmNewPassword())) {
					User user = userInfo.getUser();
					user.setUserId(userService.getCurrentUser().getUserId());
					user.setPassword(userInfo.getNewPassword());
					userService.saveUser(user);
				} else {
					throw new YiPinStoreException("与新密码不一致");
				}

			} else {
				throw new YiPinStoreException("密码错误");
			}
		} catch (YtoxlUserException e) {
			throw new YiPinStoreException(e.getMessage());
		}
		return null;
	}

	@Override
	public Integer addSeller(UserInfo userInfo) throws YiPinStoreException {
		userInfo.setTel(userInfo.getUser().getTel());
		userInfo.setEmail(userInfo.getUser().getEmail());
		userInfo.getUser().setStatus(User.STATUS_ABLE);
		userInfo.getUser().setPassword(defaultPass);

		// 添加user
		User user = userInfo.getUser();
		try {
			user.setCreateByUserId(userService.getCurrentUser().getUserId());
			userService.saveUser(user);
		} catch (YtoxlUserException e) {
			throw new YiPinStoreException(e.getMessage());
		}
		// 需要根据最后一个regionCode查询到regionId
		if (StringUtils.isNotEmpty(userInfo.getShiperRegionCodes())
				&& userInfo.getShiperRegionCodes().split(",").length == 3) {
			Integer shiperId = regionMapper.getRegionByCode(
					userInfo.getShiperRegionCodes()
							.substring(
									userInfo.getShiperRegionCodes()
											.lastIndexOf(",") + 1))
					.getRegionId(); // 发货地址
			userInfo.setShiperRegionId(shiperId);
		}
		if (StringUtils.isNotEmpty(userInfo.getReceiverRegionCodes())
				&& userInfo.getReceiverRegionCodes().split(",").length == 3) {
			Integer receiverId = regionMapper.getRegionByCode(
					userInfo.getReceiverRegionCodes()
							.substring(
									userInfo.getReceiverRegionCodes()
											.lastIndexOf(",") + 1))
					.getRegionId(); // 退货地址
			userInfo.setReceiverRegionId(receiverId);
		}
		if (StringUtils.isNotEmpty(userInfo.getCompanyRegionCodes())
				&& userInfo.getCompanyRegionCodes().split(",").length == 3) {
			Integer companyId = regionMapper.getRegionByCode(
					userInfo.getCompanyRegionCodes()
							.substring(
									userInfo.getCompanyRegionCodes()
											.lastIndexOf(",") + 1))
					.getRegionId(); // 公司地址
			userInfo.setCompanyRegionId(companyId);
		}

		// 添加商家
		userInfo.setUserId(user.getUserId());
		userInfoMapper.addSeller(userInfo);

		// 添加角色
		List<Integer> uroleIds = new ArrayList<Integer>();
		uroleIds.add(UserInfo.USER_ROLE_SELLER);
		userMapper.addUserUroles(user.getUserId(), uroleIds);

		// 添加该商家要卖的品牌
		List listBrandIds = userInfo.getListBrandIds();
		if (listBrandIds != null && listBrandIds.size() > 0) {
			for (Iterator iterator = listBrandIds.iterator(); iterator
					.hasNext();) {
				Integer brandId = (Integer) iterator.next();
				userInfo.setBrandId(brandId);
				this.addSellerBrand(userInfo);
			}
		}
		return userInfo.getUserInfoId();
	}

	/**
	 * 删除用户选择的品牌
	 * 
	 * @param userInfo
	 * @return
	 * @throws YiPinStoreException
	 */
	@Override
	public Integer delSellerBrand(UserInfo userInfo) throws YiPinStoreException {
		return userInfoMapper.delSellerBrand(userInfo);
	}

	/**
	 * 查询用户是否选择该品牌
	 * 
	 * @param userInfo
	 * @return
	 * @throws YiPinStoreException
	 */
	@Override
	public Integer listBrandsCount(UserInfo userInfo)
			throws YiPinStoreException {
		return userInfoMapper.listBrandsCount(userInfo);
	}

	/**
	 * 添加卖家的时候判断邮箱是否存在
	 * 
	 * @param email
	 * @param userId
	 * @return boolean true :非重复 false:重复
	 */
	@Override
	public boolean validateEmailIsRepate(String email, Integer userId) {
		User user = userInfoMapper.validateEmailIsRepate(email);
		if (userId == null) {
			if (user != null) {
				return false;
			}
			return true;
		}
		if (user != null) {
			if (user.getUserId().equals(userId)) {
				return true;
			}
			return false;
		}
		return true;
	}

}
