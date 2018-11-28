/**
 * ZoneService.java
 * Created at Jan 10, 2014
 * Created by kuiYang
 * Copyright (C) 2014 SHANGHAI YUANTONG XINLONG E-Business Co.,Ltd, All rights reserved.
 */
package com.ytoxl.module.yipin.base.service;

import java.util.List;

import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Zone;
import com.ytoxl.module.yipin.base.dataobject.ZoneSale;

/**
 * <p>
 * ClassName: ZoneService
 * </p>
 * <p>
 * Description: 专区
 * </p>
 * <p>
 * Author: Kui.Yang
 * </p>
 * <p>
 * Date: Jan 10, 2014
 * </p>
 */
public interface ZoneService {

	/**
	 * <p>
	 * Description: 保存专区
	 * </p>
	 * 
	 * @param zone 专区
	 * @throws YiPinStoreException
	 */
	public void saveZone(Zone zone) throws YiPinStoreException;

	/**
	 * <p>
	 * Description: 修改专区状态
	 * </p>
	 * 
	 * @param zone  类型
	 * @throws YiPinStoreException
	 */
	public void updateZoneStatus(Zone zone) throws YiPinStoreException;
	
	/**
	 * <p>
	 * Description: 修改专区排序
	 * </p>
	 * 
	 * @param zone  类型
	 * @throws YiPinStoreException
	 */
	public void updateSortZoneRank(Zone zone) throws YiPinStoreException;

	/**
	 * <p>
	 * Description: 根据专区类型获取专区信息
	 * </p>
	 * 
	 * @param typeName 类型名称
	 * @return 专区信息
	 * @throws YiPinStoreException
	 */
	public Zone getZoneByZoneType(Short typeName) throws YiPinStoreException;

	/**
	 * <p>
	 * Description: 根据专区名称获取专区信息
	 * </p>
	 * 
	 * @param zoneName 专区名称
	 * @return 专区信息
	 * @throws YiPinStoreException
	 */
	public Zone getZoneByName(String zoneName) throws YiPinStoreException;

	
	/**
	 * <p>
	 * Description: 查询查询抢购专区信息 
	 * </p>
	 * 
	 * @return 专区信息集合
	 * @throws YiPinStoreException
	 */
	public Zone getZoneBySpecial()  throws YiPinStoreException;
	
	/**
	 * <p>
	 * Description: 查询预售和运营专区信息 
	 * </p>
	 * 
	 * @return 专区信息集合
	 * @throws YiPinStoreException
	 */
	public List<Zone> getZoneByPreAndDefault() throws YiPinStoreException;
	
	/**
	 * <p>
	 * Description: 查询专区信息列表
	 * </p>
	 * 
	 * @return 专区信息集合
	 * @throws YiPinStoreException
	 */
	public List<Zone> listZones()throws YiPinStoreException;
	
	/**
	 * <p>
	 * Description: 查询专区信息列表
	 * </p>
	 * 
	 * @return 首页所有专区信息集合
	 * @throws YiPinStoreException
	 */
	public List<Zone> listAllZones()throws YiPinStoreException;
	

	/**
	 * <p>
	 * Description: 获取专区信息详情
	 * </p>
	 * 
	 * @param zone 专区信息
	 * @param zoneTypeSale 专区类型
	 * @return 专区信息
	 */
	public Zone getZoneDetail(Zone zone, Short zoneTypeSale)throws YiPinStoreException;
	
	/**
	 * <p>
	 * Description: 根据ID获取专区信息
	 * </p>
	 * 
	 * @param zoneId 专区ID
	 * @return 专区信息
	 * @throws YiPinStoreException
	 */
	public Zone getZoneByZoneId(Integer zoneId)throws YiPinStoreException;

	/**
	 * <p>Description: 修改排数</p>
	 * @param zone
	 */
	public void updateLineNo(Zone zone)throws YiPinStoreException;
	
	/**
	 * <p>Description: </p>
	 * @param productSkuId
	 * @return
	 * @throws YiPinStoreException
	 */
	public ZoneSale getZoneSaleByProductSkuId(Integer productSkuId)throws YiPinStoreException;

}
