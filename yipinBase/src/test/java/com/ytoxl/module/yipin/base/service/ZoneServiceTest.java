/**
 * ZoneServiceTest.java
 * Created at Jan 11, 2014
 * Created by kuiYang
 * Copyright (C) 2014 SHANGHAI YUANTONG XINLONG E-Business Co.,Ltd, All rights reserved.
 */
package com.ytoxl.module.yipin.base.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ytoxl.module.yipin.base.BaseTest;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Zone;
import com.ytoxl.module.yipin.base.dataobject.ZoneSale;

/**
 * <p>
 * ClassName: ZoneServiceTest
 * </p>
 * <p>
 * Description: 专区测试类
 * </p>
 * <p>
 * Author: Kui.Yang
 * </p>
 * <p>
 * Date: Jan 11, 2014
 * </p>
 */
public class ZoneServiceTest extends BaseTest {

	/**
	 * <p>Field logger: 日志</p>
	 */
	private static Logger logger = LoggerFactory.getLogger(ZoneServiceTest.class);
	
	/**
	 * <p>Field zoneService: 被测试专区业务接口</p>
	 */
	@Autowired
	private ZoneService zoneService;
	
	

	/**
	 * <p>
	 * Description: 测试专区新增功能
	 * </p>
	 */
	@Test
	public void testSaveZone() {
	   // 专区集合
	   List<Zone> zoneSource = new ArrayList<Zone>();
	   try {
		   // 首页专区广告
		   Zone zone = new Zone();
		   zone.setName("专区");
		   zone.setZoneType(Zone.ZONE_TYPE_DEFAULT);
		   zone.setRank(new Integer(3));
		   zoneSource.add(zone);   
		   
		   //  首页预售专区广告
		   Zone zoneSale = new Zone();
		   zoneSale.setName("预售专区");
		   zoneSale.setZoneType(Zone.ZONE_TYPE_SALE);
		   zoneSale.setRank(new Integer(2));
		   zoneSource.add(zoneSale);
		   
		   //  首页抢购专区广告
		   Zone zoneSpecial = new Zone();
		   zoneSpecial.setName("抢购专区");
		   zoneSpecial.setZoneType(Zone.ZONE_TYPE_SPECIAL);
		   zoneSpecial.setRank(new Integer(1));
		   zoneSource.add(zoneSpecial);
		   
		   
		   for(Zone z : zoneSource){
			   List<ZoneSale> zoneSaleArray = new ArrayList<ZoneSale>();
			   if(z.getZoneType().equals(Zone.ZONE_TYPE_DEFAULT)){
				   ZoneSale zs = new ZoneSale();
				   zs.setProductId(new Integer(100015));
				   zoneSaleArray.add(zs);
			   }else{
				   ZoneSale zs = new ZoneSale();
				   zs.setProductSkuId(new Integer(100000));
				   zoneSaleArray.add(zs);
			   }
			   z.setZoneSaleList(zoneSaleArray);
			   this.zoneService.saveZone(z);
			   logger.error("zone init success", z.getZoneId());
		   }
		} catch (YiPinStoreException e) {
			logger.error("zone test error",e.getMessage());
		}
	}
	
	/**
	 * <p>Description: 测试专区ID获取专区信息</p>
	 */
	@Test
	public void testGetZoneByZoneId(){
		try {
			Zone zone = this.zoneService.getZoneByZoneId(new Integer(3));
			logger.error("zone load success zoneId:",zone.getZoneId());
		} catch (YiPinStoreException e) {
			logger.error("zone test error",e.getMessage());
		}
	}
	
	/**
	 * <p>Description: 测试专区状态修改</p>
	 */
	@Test
	public void testUpdateZoneStatus(){
		try {
			Zone zone = this.zoneService.getZoneByZoneId(new Integer(3));
			zone.setStatus(Zone.ZONE_STATUS_RELEASE);
			this.zoneService.updateZoneStatus(zone);
			logger.error("zone update success zoneId:",zone.getZoneId());
		} catch (YiPinStoreException e) {
			logger.error("zone test error",e.getMessage());
		}
		
	}
	
	/**
	 * <p>Description: 测试专区名称获取专区信息</p>
	 */
	@Test
	public void testGetZoneByName(){
		try {
			Zone zone = this.zoneService.getZoneByName("预售专区");
			logger.error("zone load success :",zone.getName());
		} catch (YiPinStoreException e) {
			logger.error("zone test error",e.getMessage());
		}
	}
	
	
	/**
	 * <p>Description: 测试专区列表信息</p>
	 */
	@Test
	public void testListZones(){
		try {
			List<Zone> zone= this.zoneService.listZones();
			logger.error("zone query success :",zone.size());
		} catch (YiPinStoreException e) {
			logger.error("zone test error",e.getMessage());
		}
	}
	
	
	/**
	 * <p>Description: 测试专区详情信息</p>
	 */
	@Test
	public void testGetZoneDetail(){
		try {
			Zone zone= this.zoneService.getZoneDetail(new Zone(),Zone.ZONE_TYPE_DEFAULT);
			zone.setZoneId(new Integer(3));
			logger.error("zone query success :",zone.getName());
			zone= this.zoneService.getZoneDetail(zone,Zone.ZONE_TYPE_DEFAULT);
			logger.error("zone query success :",zone.getName());
		} catch (YiPinStoreException e) {
			logger.error("zone test error",e.getMessage());
		}
	}

}
