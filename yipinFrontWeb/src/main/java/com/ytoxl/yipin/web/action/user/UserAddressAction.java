package com.ytoxl.yipin.web.action.user;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.ytoxl.module.user.common.exception.YtoxlUserException;
import com.ytoxl.module.user.security.CustomUserDetails;
import com.ytoxl.module.user.service.UserService;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Region;
import com.ytoxl.module.yipin.base.dataobject.UserAddress;
import com.ytoxl.module.yipin.base.service.RegionService;
import com.ytoxl.module.yipin.base.service.UserAddressService;
import com.ytoxl.yipin.web.action.BaseAction;

public class UserAddressAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory
			.getLogger(UserAddressAction.class);

	@Autowired
	private UserAddressService receiverAddressService;
	@Autowired
	private RegionService regionService;
	@Autowired
	private UserService userService;

	// 我的收货地址的总条数
	private int addTotal = 0;

	// 收货地址
	private UserAddress receiverAdd;

	// 收货地址List
	private List<UserAddress> receiverAddList = new ArrayList<UserAddress>();

	private Integer userId;

	private static final int ADDRESS_COUNT = 20;
	
	private String opert;

	/**
	 * 获取当前买家的所有收货地址
	 * 
	 * @return
	 */
	public String getUserAddress() {
		try {
			CustomUserDetails user = userService.getCurrentUser();
			// 页面显示List
			receiverAddList = receiverAddressService.getRAddressDetailList(user.getUserId());
			// 页面显示List
			userId = user.getUserId();
			if (receiverAddList != null && receiverAddList.size() > 0) {
				// 获取当前用户收货地址总数
				int total = receiverAddList.size();
				addTotal = total >= ADDRESS_COUNT ? ADDRESS_COUNT : total;
			}

		} catch (YiPinStoreException e) {
			setMessage("获取收货地址异常！");
		}
		catch (YtoxlUserException e) {
			setMessage("登陆状态异常！");
		}
		return SUCCESS;
	}

	/**
	 * 获取单个收件地址
	 * 
	 * @return
	 */
	public String getSingleReceiverAddress() {
		try {
			receiverAdd = receiverAddressService.getAddress(receiverAdd
					.getUserAddressId());
		} catch (YiPinStoreException e) {
			logger.error("查询地址出错：" , e);
		}
		return getUserAddress();
	}

	/**
	 * 删除买家收货地址
	 * 
	 * @return
	 */
	public String delReceiverAddress() {
		try {
			CustomUserDetails customUserDetail = userService.getCurrentUser();

			// 判断地址是不是当前用户的
			Boolean result = receiverAddressService.checkAddressIsMine(
					receiverAdd.getUserAddressId(),
					customUserDetail.getUserId());
			if (result) {
				receiverAddressService.delAddress(receiverAdd
						.getUserAddressId());
				setMessage("删除成功！");
			} else {
				setMessage("删除失败！");
			}
		} catch (YiPinStoreException e) {
			setMessage("删除失败！");
		}catch (YtoxlUserException e) {
			setMessage("登陆状态异常！");
		}
		return JSONMSG;
	}

	/**
	 * 订单保存收货地址
	 * 
	 * @return
	 */
	public String saveAddress() {
		try {
			CustomUserDetails customUserDetail = userService.getCurrentUser();
			if (customUserDetail == null) {
				setMessage(Boolean.FALSE.toString(), "请先登陆!");
				return JSONMSG;
			}

			// 添加/编辑收货地址
			if(!editAddress(customUserDetail.getUserId())){
				return JSONMSG;
			}
			
			if(receiverAdd.getRegionId() != null){
				Region region = regionService.getDetailInfoByRegionId(receiverAdd.getRegionId());
				if(region != null){
					receiverAdd.setRegion(region);
					receiverAdd.setReceiveAddress(region.getProvince().concat(region.getCity()).concat(region.getCounty()).concat(receiverAdd.getDetailAddress()));
				}
			}
			
			String jsonString = JSON.toJSONString(receiverAdd);
			
			// 将修改或者新增的地址json格式返回
			setMessage(Boolean.TRUE.toString(), jsonString);
		} catch (YiPinStoreException e) {
			setMessage(Boolean.FALSE.toString(), e.getLocalizedMessage());
			logger.error("添加收货地址异常：",e);
			
		} catch (YtoxlUserException e) {
			setMessage("登陆状态异常！");
		}

		return JSONMSG;
	}
	
	/**
	 * 保存我的收货地址 
	 * 
	 * @return
	 */
	public String addAddress() {
		try {
			CustomUserDetails customUserDetail = userService.getCurrentUser();
			if (customUserDetail == null) {
				setMessage(Boolean.FALSE.toString(), "请先登陆!");
				return JSONMSG;
			}

			// 添加/编辑收货地址
			if(!editAddress(customUserDetail.getUserId())){
				return JSONMSG;
			}
		} catch (YtoxlUserException e) {
			setMessage("登陆状态异常！");
		}

		return JSONMSG;
	}


	/**
	 * 设置默认收货地址
	 * 
	 * @return
	 */
	public String setDefualtReceiverAddress() {
		try {
			CustomUserDetails customUserDetail = userService.getCurrentUser();

			// 判断当前地址是不是当前用户的
			Boolean result = receiverAddressService.checkAddressIsMine(
					receiverAdd.getUserAddressId(), customUserDetail.getUserId());

			if (result) {
				receiverAdd.setUserId(customUserDetail.getUserId());
				receiverAddressService.updateDefualtAddress(receiverAdd);
				setMessage("设置成功！");
			} else {
				setMessage("设置失败！");
			}
		} catch (YiPinStoreException e) {
			setMessage("设置失败！");
		}catch (YtoxlUserException e) {
			setMessage("登陆状态异常！");
		}
		return JSONMSG;
	}

	/**
	 * 获取当前用户收货地址总数
	 * 
	 * @return
	 */
	public String checkAdressCount() {

		boolean flag = false;
		try {
			int total = receiverAddressService.getAddressCountByUserId(userId);
			if (total >= ADDRESS_COUNT) {
				flag = true;
			}
		} catch (YiPinStoreException e) {
			logger.error("获取当前用户收货地址总数异常：",e);
		}
		if (flag) {
			setMessage(Boolean.FALSE.toString(), "收货地址条数已超过限制，请修改或删除后再新增！");
			return JSONMSG;
		} else {
			setMessage(Boolean.TRUE.toString(), "添加地址");
		}
		return JSONMSG;
	}

	/**
	 * 编辑用户地址
	 * @param currentUserId
	 * @return
	 */
	public boolean editAddress(Integer currentUserId) {
		boolean flag = false;
		receiverAdd.setUserId(currentUserId);
		try {
			// 添加/编辑收货地址
			if (receiverAdd != null) {
				
				int total = receiverAddressService.getAddressCountByUserId(currentUserId);
				if (total >= ADDRESS_COUNT && receiverAdd.getUserAddressId() == null ) {
					setMessage(Boolean.FALSE.toString(), "抱歉！收货地址已满，您可以对原有地址进行修改");
					return false;
				}
				
				if(receiverAdd.getIsDefault() == null){
					receiverAdd.setIsDefault(UserAddress.ISDEFAULT_TRUE);
				}

				// 添加一条新纪录
				if (receiverAdd.getUserAddressId() == null) {
					
					receiverAddressService.addAddress(receiverAdd);
					setMessage("添加成功！");
					flag = true;
				} else {

					// 判断地址是不是当前用户的
					Boolean result = receiverAddressService.checkAddressIsMine(
							receiverAdd.getUserAddressId(), currentUserId);
					if (result) {
						// 编辑收货地址
						receiverAddressService.updateAddress(receiverAdd);
						setMessage("修改成功！");
						flag = true;
					} else {
						setMessage(Boolean.FALSE.toString(),"修改失败！");
					}
				}
			} else {
				setMessage(Boolean.FALSE.toString(),"操作失败！");
			}
		} catch (YiPinStoreException e) {
			setMessage(Boolean.FALSE.toString(),"操作失败！");
			logger.error("添加收货地址异常：",e);
		}
		return flag;
	}

	/************************ GET && SET **************************/

	public int getAddTotal() {
		return addTotal;
	}

	public void setAddTotal(int addTotal) {
		this.addTotal = addTotal;
	}

	private int myAddressCount;

	public int getMyAddressCount() {
		return myAddressCount;
	}

	public void setMyAddressCount(int myAddressCount) {
		this.myAddressCount = myAddressCount;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public RegionService getRegionService() {
		return regionService;
	}

	public void setRegionService(RegionService regionService) {
		this.regionService = regionService;
	}

	public UserAddressService getReceiverAddressService() {
		return receiverAddressService;
	}

	public void setReceiverAddressService(
			UserAddressService receiverAddressService) {
		this.receiverAddressService = receiverAddressService;
	}

	public List<UserAddress> getReceiverAddList() {
		return receiverAddList;
	}

	public void setReceiverAddList(List<UserAddress> receiverAddList) {
		this.receiverAddList = receiverAddList;
	}

	public UserAddress getReceiverAdd() {
		return receiverAdd;
	}

	public void setReceiverAdd(UserAddress receiverAdd) {
		this.receiverAdd = receiverAdd;
	}

	public String getOpert() {
		return opert;
	}

	public void setOpert(String opert) {
		this.opert = opert;
	}

}
