package com.ytoxl.module.yipin.base.mapper;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ytoxl.module.core.common.persistence.BaseSqlMapper;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Brand;
import com.ytoxl.module.yipin.base.dataobject.City;

public interface CityMapper<T extends City> extends BaseSqlMapper<T>  {
	 /**
     * <p>
     * Description: 查询所有城市
     * </p>
     * 
     * @return 城市集合
     * @throws DataAccessException
     */
    public List<City> searchAllCity() throws DataAccessException;
    
    /**
     * <p>
     * Description: 根据城市id查询品牌城市名称
     * </p>
     * 
     * @param cityId
     * @return City
     * @throws DataAccessException
     */
    public City searchCityByCityId(Integer cityId) throws DataAccessException;
}
