package com.ytoxl.module.yipin.base.mapper;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ytoxl.module.yipin.base.BaseTest;
import com.ytoxl.module.yipin.base.dataobject.Region;

/**
 * RegionMapper测试类
 * @author bing
 *
 */
public class RegionMapperTest extends BaseTest{
	
	@Autowired
	private RegionMapper<Region> regionMapper;
	
	/**
	 * 通过regionCode查询到regionId
	 */
	@Test
	public void getRegionByCode(){
		Region region = regionMapper.getRegionByCode("");
		System.out.println(region);
	}
	
}
