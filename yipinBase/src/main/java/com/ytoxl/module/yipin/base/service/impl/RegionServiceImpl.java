package com.ytoxl.module.yipin.base.service.impl;


import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Region;
import com.ytoxl.module.yipin.base.dataobject.resultmap.RegionModel;
import com.ytoxl.module.yipin.base.mapper.RegionMapper;
import com.ytoxl.module.yipin.base.service.RegionService;

@Service
public class RegionServiceImpl implements RegionService {
	
	@Autowired
	private RegionMapper<Region> regionMapper;

	@Override
	public String getProvince() throws YiPinStoreException {
		List<RegionModel> regions = regionMapper.getProvince();
		JSONArray json = JSONArray.fromObject(regions);
		return json.toString();
	}

	@Override
	public String getCity(String pId) throws YiPinStoreException {
		List<RegionModel> regions = regionMapper.getCityOrArea(pId);
		JSONArray json = JSONArray.fromObject(regions);
		return json.toString();
	}

	@Override
	public String getArea(String cId) throws YiPinStoreException {
		List<RegionModel> regions = regionMapper.getCityOrArea(cId);
		JSONArray json = JSONArray.fromObject(regions);
		return json.toString();
	}

	@Override
	public Region getDetailInfoByRegionId(Integer regionId)
			throws YiPinStoreException {
		return regionMapper.getDetailInfoByRegionId(regionId);
	}
	
	@Override
	public RegionModel getRegionIdsByRegionId(Integer regionId)
			throws YiPinStoreException {
		return regionMapper.getRegionIdsByRegionId(regionId);
	}
}
