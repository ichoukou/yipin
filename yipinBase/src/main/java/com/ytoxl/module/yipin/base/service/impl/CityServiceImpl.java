package com.ytoxl.module.yipin.base.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Brand;
import com.ytoxl.module.yipin.base.dataobject.City;
import com.ytoxl.module.yipin.base.mapper.BrandMapper;
import com.ytoxl.module.yipin.base.mapper.CityMapper;
import com.ytoxl.module.yipin.base.service.CityService;

@Service
public class CityServiceImpl implements CityService{

	 /**
     * <p>
     * Field logger: 日志
     * </p>
     */
    private Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);
    /**
     * <p>
     * Field brandMapper: 品牌Mapper
     * </p>
     */
    @Autowired
    private BrandMapper<Brand> brandMapper;
    
    /**
     * <p>
     * Field CityMapper: 品牌Mapper
     * </p>
     */
    @Autowired
    private CityMapper<City> cityMapper;
    
	@Override
	public List<City> searchAllCity() throws YiPinStoreException {
		return cityMapper.searchAllCity();
	}

	@Override
	public List<City> listBrandsAndCity() throws YiPinStoreException {
		List<City> cityList = cityMapper.searchAllCity();
		List<Integer> cityIds = new ArrayList<Integer>();
		Integer cityId = null;
		for(City c:cityList){
			cityId = c.getCityId();
		}
		cityIds.add(cityId);
		List<Brand> brandList = brandMapper.getBrandAndCityList(cityIds);
		for(City c : cityList){
			List<Brand> list = new ArrayList<Brand>();
//			List<City> list = new ArrayList<City>();
			for(Brand b:brandList){
				if(b.getCity().getCityId().equals(c.getCityId())){
			  	   list.add(b);
				}
			}
			c.setBrands(list);
		}
		return cityList;
	}

	@Override
	public City searchCityByCityId(Integer cityId) throws YiPinStoreException {
		return cityMapper.searchCityByCityId(cityId);
	}

}
