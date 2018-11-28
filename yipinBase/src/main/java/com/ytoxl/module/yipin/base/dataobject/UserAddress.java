package com.ytoxl.module.yipin.base.dataobject;

import com.ytoxl.module.yipin.base.dataobject.resultmap.RegionModel;
import com.ytoxl.module.yipin.base.dataobject.tbl.UserAddressTbl;

public class UserAddress extends UserAddressTbl {
	
	// 默认地址
	public static final Short ISDEFAULT_TRUE = 1;
	
	// 非默认地址
	public static final Short ISDEFAULT_FALSE = 0; 
	
	// 每个用户收货地址最大条数
	public static final Short ADDRESS_LIMIT = 20; 
	
	// 下拉菜单初始化值
	private String regionCodes; 
	
	// 用于订单审核页面的快速注册
	private String email; 
	private Region region;
	
	// 地址全称
	private String receiveAddress;
	
	// 新的地址插件
	private RegionModel regionModel;

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public RegionModel getRegionModel() {
		return regionModel;
	}

	public void setRegionModel(RegionModel regionModel) {
		this.regionModel = regionModel;
	}

	public String getRegionCodes() {
		return regionCodes;
	}

	public void setRegionCodes(String regionCodes) {
		this.regionCodes = regionCodes;
	}

	public String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}
}
