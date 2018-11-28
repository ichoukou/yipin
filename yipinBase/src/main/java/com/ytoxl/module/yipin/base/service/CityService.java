package com.ytoxl.module.yipin.base.service;

import java.util.List;

import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Brand;
import com.ytoxl.module.yipin.base.dataobject.City;

public interface CityService {
	/**
	 * 查询所有城市
	 * 
	 * @return
	 * @throws YiPinStoreException
	 */
	List<City> searchAllCity() throws YiPinStoreException;	
    /**
     * <p>
     * Description: 查询品牌城市
     * </p>
     * 
     * @param brand 品牌
     * @return 品牌集合
     * @throws YiPinStoreException
     */
      List<City> listBrandsAndCity() throws YiPinStoreException;
      /**
       * <p>
       * Description: 根据城市id查询品牌城市名称
       * </p>
       * 
       * @param cityId
       * @return City
       * @throws YiPinStoreException
       */
       City searchCityByCityId(Integer cityId) throws YiPinStoreException;
}
