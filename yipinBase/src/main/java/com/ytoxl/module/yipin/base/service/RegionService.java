package com.ytoxl.module.yipin.base.service;

import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Region;
import com.ytoxl.module.yipin.base.dataobject.resultmap.RegionModel;


public interface RegionService {

	/**
	 * 查询出所有的省json返回
	 * @return
	 * @throws YiPinStoreException
	 */
	public String getProvince() throws YiPinStoreException;
	
	
	/**
	 * 根据省份id查询下面所有的城市json返回
	 * @param pId
	 * @return
	 * @throws YiPinStoreException
	 */
	public String getCity(String pId) throws YiPinStoreException;
	
	/**
	 * 根据城市id查询下面所有的地区json返回
	 * @param cId
	 * @return
	 * @throws YiPinStoreException
	 */
	
	public String getArea(String cId) throws YiPinStoreException;
	
	/**
	 * 根据ID查询省市区信息
	 * @param regionId
	 * @return
	 */
	public Region getDetailInfoByRegionId(Integer regionId) throws YiPinStoreException;
	
	/**
	 * 根据ID获取省市区id
	 * @param regionId
	 * @return
	 */
	public RegionModel getRegionIdsByRegionId(Integer regionId) throws YiPinStoreException;

}
