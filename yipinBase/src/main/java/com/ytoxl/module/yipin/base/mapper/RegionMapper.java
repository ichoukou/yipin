package com.ytoxl.module.yipin.base.mapper;


import java.util.List;

import com.ytoxl.module.core.common.persistence.BaseSqlMapper;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Region;
import com.ytoxl.module.yipin.base.dataobject.resultmap.RegionModel;

/**
 * 地址库
 * @author user
 *
 * @param <T>
 */
public interface RegionMapper<T extends Region> extends BaseSqlMapper<T> {
	
	/**
	 * 根据地址库编码查询地址库信息
	 * @param regionCode
	 * @return
	 */
	public Region getRegionByCode(String postCode);
	
	
	/**
	 * 查询出所有的省
	 * @return
	 * @throws YiPinStoreException
	 */
	public List<RegionModel> getProvince() throws YiPinStoreException;
	
	
	/**
	 * 根据省份id查询下面所有的城市
	 * @param pId
	 * @return
	 * @throws YiPinStoreException
	 */
	public List<RegionModel> getCityOrArea(String pcId) throws YiPinStoreException;
	
	/**
	 * 根据ID查询省市区信息
	 * @param sellerId
	 * @return
	 */
	public Region getDetailInfoByRegionId(Integer regionId);
	
	/**
	 * 通过id查询 省、市、区的regionId
	 * @param id
	 * @return
	 * @throws YiPinStoreException
	 */
	public RegionModel getRegionIdsByRegionId(Integer regionId) throws YiPinStoreException;
	

}
