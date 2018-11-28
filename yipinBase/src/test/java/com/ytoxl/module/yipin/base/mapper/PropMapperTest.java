package com.ytoxl.module.yipin.base.mapper;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ytoxl.module.yipin.base.BaseTest;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Prop;
import com.ytoxl.module.yipin.base.service.PropService;

public class PropMapperTest extends BaseTest {
	@Autowired
	private PropService propService;

	@Test
	public void getByCode() {
		try {
			propService.getByCode("10000");
		} catch (YiPinStoreException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void updateStatusByCode() {
		try {
			Prop prop = new Prop();
			prop.setCode("10000");
			prop.setStatus(Prop.STATUS_INVALID);
			propService.updateStatusByCode(prop);
		} catch (YiPinStoreException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void listByProp() {
		Prop prop = new Prop();
		prop.setCode("10000");
		prop.setLevel(1);
		// prop.setName("华东");
		// prop.setParentId(1);
		// prop.setRank(1);
		try {
			propService.listByProp(prop);
		} catch (YiPinStoreException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAdd() {
		Prop prop = new Prop();
		prop.setCode("dddd");
		prop.setLevel(1);
		prop.setName("1111");
		prop.setParentId(1);
		prop.setRank(1);
		try {
			propService.add(prop);
		} catch (YiPinStoreException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdate() {
		Prop prop = new Prop();
		prop.setCode("dddd");
		prop.setLevel(1);
		prop.setName("华东22");
		prop.setParentId(1);
		prop.setPropId(10000);
		prop.setRank(2);
		try {
			propService.update(prop);
		} catch (YiPinStoreException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGet() {
		try {
			propService.get(10001);
		} catch (YiPinStoreException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDel() {
		try {
			propService.del(10000);
		} catch (YiPinStoreException e) {
			e.printStackTrace();
		}
	}
}
