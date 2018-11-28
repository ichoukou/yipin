package com.ytoxl.module.yipin.base.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Region;
import com.ytoxl.module.yipin.base.dataobject.UserAddress;
import com.ytoxl.module.yipin.base.dataobject.resultmap.RegionModel;
import com.ytoxl.module.yipin.base.mapper.UserAddressMapper;
import com.ytoxl.module.yipin.base.service.RegionService;
import com.ytoxl.module.yipin.base.service.UserAddressService;

@Service
public class UserAddressServiceImpl implements UserAddressService {

	@Autowired
	private UserAddressMapper<UserAddress> userAddressMapper;
	@Autowired
	private RegionService regionService;

	/**
	 * 根据userID查询地址详细信息
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public List<UserAddress> getRAddressDetailList(Integer userId)
			throws YiPinStoreException {
		List<UserAddress> resultList = userAddressMapper
				.getReceiverAddressList(userId);
		if (resultList != null && resultList.size() > 0) {
			for (UserAddress bean : resultList) {
				if (bean.getRegionId() != null) {
						Region regMrg = regionService.getDetailInfoByRegionId(bean.getRegionId());
						if (regMrg != null) {
							bean.setRegion(regMrg);
							bean.setReceiveAddress(regMrg.getProvince().concat(regMrg.getCity()).concat(regMrg.getCounty()).concat(bean.getDetailAddress()));
					}
				}
			}
			return resultList;
		}
		return null;
	}

	@Override
	public Integer delAddress(Integer id) throws YiPinStoreException {
		return userAddressMapper.del(id);
	}

	@Override
	public Integer addAddress(UserAddress receiverAddress)
			throws YiPinStoreException {

		// 判断是否是默认地址
		if (UserAddress.ISDEFAULT_TRUE.equals(receiverAddress.getIsDefault())) {

			// 取消已有的默认地址
			updatesNotDefaultAddress(receiverAddress.getUserId());
		}

		// 如果是第一条地址,不管有木有设置默认地址都设置为默认地址
		Integer count = userAddressMapper
				.getAddressCountByUserId(receiverAddress.getUserId());
		if (count == 0) {
			receiverAddress.setIsDefault(UserAddress.ISDEFAULT_TRUE);
		}
		return userAddressMapper.add(receiverAddress);
	}

	@Override
	public Integer updateAddress(UserAddress receiverAddress)
			throws YiPinStoreException {

		if (UserAddress.ISDEFAULT_TRUE.equals(receiverAddress.getIsDefault())) {
			updatesNotDefaultAddress(receiverAddress.getUserId());
		}
		return userAddressMapper.update(receiverAddress);
	}

	@Override
	public void updateDefualtAddress(UserAddress receiverAddress)
			throws YiPinStoreException {
		updatesNotDefaultAddress(receiverAddress.getUserId());
		userAddressMapper.updateDefaultAddress(receiverAddress
				.getUserAddressId());
	}

	@Override
	public UserAddress getAddress(Integer receiverAddressId)
			throws YiPinStoreException {
		UserAddress ra = userAddressMapper.get(receiverAddressId);
		if(ra != null && ra.getRegionId() != null){
			RegionModel rm = regionService.getRegionIdsByRegionId(ra.getRegionId());
				ra.setRegionModel(rm);
		}
		return ra;
	}

	@Override
	public void updatesNotDefaultAddress(Integer userId) {
		userAddressMapper.updatesNotDefaultAddress(userId);
	}

	@Override
	public int getAddressCountByUserId(Integer userId) throws YiPinStoreException {
		return userAddressMapper.getAddressCountByUserId(userId);
	}

	@Override
	public Boolean checkAddressIsMine(Integer addressId, Integer CurrentUserId)
			throws YiPinStoreException {
		Map<String,Integer> param = new HashMap<String,Integer>();
		param.put("id", addressId);
		param.put("userId", CurrentUserId);
		UserAddress address = userAddressMapper.getUserAddressByUserIdAndId(param);

		if (address != null) {
			return true;
		}
		return false;
	}

	@Override
	public UserAddress getUserAddressByUserIdAndId(Integer id, Integer userId)
			throws YiPinStoreException {
		Map<String,Integer> param = new HashMap<String,Integer>();
		param.put("id", id);
		param.put("userId", userId);
		UserAddress address = userAddressMapper.getUserAddressByUserIdAndId(param);
		if(address != null && address.getRegionId() != null){
			Region regMrg = regionService.getDetailInfoByRegionId(address.getRegionId());
			if (regMrg != null) {
					address.setRegion(regMrg);
					address.setReceiveAddress(regMrg.getProvince().concat(regMrg.getCity()).concat(regMrg.getCounty()).concat(address.getDetailAddress()));
				} 
			}
		return address;
	}

	@Override
	public UserAddress getDefaultAddress(Integer userId)
			throws YiPinStoreException {
		List<UserAddress> resultList = userAddressMapper.getDefaultAddress(userId);
		UserAddress result = new UserAddress();
		if(resultList != null && resultList.size() > 0){
			result = resultList.get(0);
		}
		if(result != null && result.getRegionId() != null){
			Region regMrg = regionService.getDetailInfoByRegionId(result.getRegionId());
			if (regMrg != null) {
				result.setRegion(regMrg);
				result.setReceiveAddress(regMrg.getProvince().concat(regMrg.getCity()).concat(regMrg.getCounty()).concat(result.getDetailAddress()));
				} 
			}
		return result;
	}

}
