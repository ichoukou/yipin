package com.ytoxl.module.yipin.base.mapper;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ytoxl.module.yipin.base.BaseTest;
import com.ytoxl.module.yipin.base.dataobject.Express;

public class ExpressMapperTest extends BaseTest{

	@Autowired
	private ExpressMapper<Express> expressMapper;
	
	@Test
	public void testExpress(){
		String expressName = "铁通";
		List<Express> exList = expressMapper.searchExpress(expressName);
		if(exList != null && exList.size()>0)
			System.out.println(exList.get(0).getExpressName());
	}
}
