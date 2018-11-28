/**
 * ZoneMapper.java
 * Created at Jan 10, 2014
 * Created by kuiYang
 * Copyright (C) 2014 SHANGHAI YUANTONG XINLONG E-Business Co.,Ltd, All rights reserved.
 */
package com.ytoxl.module.yipin.base.mapper;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ytoxl.module.core.common.persistence.BaseSqlMapper;
import com.ytoxl.module.yipin.base.dataobject.Zone;

/**
 * <p>
 * ClassName: ZoneMapper
 * </p>
 * <p>
 * Description: 专区类型
 * </p>
 * <p>
 * Author: Kui.Yang
 * </p>
 * <p>
 * Date: Jan 10, 2014
 * </p>
 */
public interface ZoneMapper<T extends Zone> extends BaseSqlMapper<T> {
	/**
	 * <p>
	 * Description: 查询专区列表
	 * </p>
	 * 
	 * @return 专区集合
	 * @throws DataAccessException
	 */
	public List<Zone> getZoneList() throws DataAccessException;
	
	
	public List<Zone> getAllList() throws DataAccessException;
	/**
	 * <p>
	 * Description: 查询专区列表
	 * </p>
	 * 
	 * @return 查询抢购专区信息 -
	 * @throws DataAccessException
	 */
	public Zone getZoneListBySpecial() throws DataAccessException;
	/**
	 * <p>
	 * Description: 查询专区列表
	 * </p>
	 * 
	 * @return 查询预售和运营专区信息 -
	 * @throws DataAccessException
	 */
	public List<Zone> getZoneListBypreAndDefault() throws DataAccessException;

	/**
	 * <p>
	 * Description: 修改专区状态
	 * </p>
	 * 
	 * @param zone  专区类型
	 * @throws DataAccessException
	 *           
	 */
	public void updateZoneStatus(Zone zone) throws DataAccessException;

	/**
	 * <p>
	 * Description: 根据类型获取专区信息
	 * </p>
	 * 
	 * @param typeName  类型名称
	 * @return 专区信息
	 * @throws DataAccessException
	 */
	public Zone getZoneByZoneType(Short typeName) throws DataAccessException;

	/**
	 * <p>
	 * Description: 根据专区名称获取专区信息
	 * </p>
	 * 
	 * @param zoneName  专区名称
	 * @return 专区信息
	 * @throws DataAccessException
	 */
	public Zone getZoneByName(String zoneName) throws DataAccessException;
	
	/**
	 * <p>
	 * Description: 查询专区最大排序号
	 * </p>
	 * 
	 * @return 最大序号
	 * @throws DataAccessException
	 */
	public Integer getMaxRank() throws DataAccessException;

	/**
	 * <p>
	 * Description: 根据排序数字查询专区
	 * </p>
	 * 
	 * @param rankId 排序数字
	 * @return 专区信息
	 */
	public Zone getZoneByRank(Integer rankId) throws DataAccessException;

	/**
	 * <p>
	 * Description: 更新专区排序
	 * </p>
	 * 
	 * @param zone   专区信息
	 * @throws DataAccessException
	 *          
	 */
	public void updateZoneRank(Zone zone) throws DataAccessException;

	/**
	 * <p>
	 * Description: 查询专区总数目
	 * </p>
	 * 
	 * @return 总数目
	 * @throws DataAccessException
	 */
	public Integer getCountByZoneType()throws DataAccessException;

	/**
	 * <p>
	 * Description: 根据专区ID查询专区信息
	 * </p>
	 * 
	 * @param zoneId  专区ID
	 * @return 专区信息
	 */
	public Zone getZoneById(Integer zoneId)throws DataAccessException;


	/**
	 * <p>Description: 修改专区排数</p>
	 * @param zone
	 */
	public void updateZoneLineNo(Zone zone)throws DataAccessException;


}
