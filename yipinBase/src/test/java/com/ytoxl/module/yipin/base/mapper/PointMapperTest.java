package com.ytoxl.module.yipin.base.mapper;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ytoxl.module.yipin.base.BaseTest;
import com.ytoxl.module.yipin.base.dataobject.Point;

public class PointMapperTest extends BaseTest{
	@Autowired
	private PointMapper<Point> pointMapper;
	
	@Test
	public void getPointByUserId(){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userId", 1);
		map.put("start", 1);
		map.put("limit", 10);
		pointMapper.getPointByUserId(1);
	}
	
	@Test
	public void updatePointByUserId(){
		
	}
}
