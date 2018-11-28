/**
 * ZoneSaleMapper.java
 * Created at Jan 10, 2014
 * Created by kuiYang
 * Copyright (C) 2014 SHANGHAI YUANTONG XINLONG E-Business Co.,Ltd, All rights reserved.
 */
package com.ytoxl.module.yipin.base.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import com.ytoxl.module.core.common.persistence.BaseSqlMapper;
import com.ytoxl.module.yipin.base.dataobject.Zone;
import com.ytoxl.module.yipin.base.dataobject.ZoneSale;

/**
 * <p>
 * ClassName: ZoneSaleMapper
 * </p>
 * <p>
 * Description: 专区销售
 * </p>
 * <p>
 * Author: Kui.Yang
 * </p>
 * <p>
 * Date: Jan 10, 2014
 * </p>
 */
public interface ZoneSaleMapper<T extends ZoneSale> extends BaseSqlMapper<T> {
	/**
	 * <p>
	 * Description: 根据专区类型ID获取专区销售
	 * </p>
	 * 
	 * @param zoneId  专区类型ID
	 * @return 销售集合
	 * @throws DataAccessException
	 */
	public List<ZoneSale> listZoneSaleByZoneId(Integer zoneId)
			throws DataAccessException;

	/**
	 * <p>
	 * Description: 根据商品ID查询商品权重信息
	 * </p>
	 * 
	 * @param productId 商品ID
	 * @return 商品权重信息
	 * @throws DataAccessException
	 */
	public Integer getRankByProductId(Integer productId)
			throws DataAccessException;
	
	/**
	 * <p>Description: 删除专区销售信息</p>
	 * @param zone
	 */
	public void remove(Zone zone)throws DataAccessException;
	/**
	 * <p>Description: 删除专区销售信息</p>
	 * @param zone
	 */
	public void removeByZoneSale(ZoneSale zs)throws DataAccessException;

	/**
	 * <p>Description: 根据专区ID和商品ID查询销售信息</p>
	 * @param o
	 * @return 
	 */
	public ZoneSale getZoneSaleByZoneIdAndProductId(ZoneSale o);

	public ZoneSale getSpecialZoneSaleBySkuId(Integer skuId) throws DataAccessException;
	
	public List<ZoneSale> listZoneSalesByProductIdZoneType(@Param("productId")Integer productId, @Param("zoneType")Short zoneType) throws DataAccessException;

	/**
	 * <p>Description: 根据商品信息查询销售信息</p>
	 * @param productId 商品ID
	 * @return 销售信息
	 */
	public ZoneSale getZoneSaleByProductId(Integer productId)throws DataAccessException;

	/**
	 * <p>Description: 根据商品skuID获取销售信息</p>
	 * @param productSkuId
	 * @return
	 */
	public ZoneSale getZoneByProductSku(Integer productSkuId)throws DataAccessException;

	/**
	 * <p>Description: 根据品牌首字符和专区ID查询专区销售(普通、预售)</p>
	 * @param zoneId 专区ID
	 * @param firstChar 品牌首字符
	 * @return 销售信息集合
	 * @throws DataAccessException
	 */
	public List<ZoneSale> listZoneSaleByFirstCharAndZoneId(@Param("zoneId")Integer zoneId, @Param("firstChar")String firstChar)throws DataAccessException;


	/**
	 * <p>Description: 根据品牌首字符和专区ID查询专区销售(抢购)</p>
	 * @param zoneId 专区ID
	 * @param firstChar 品牌首字符
	 * @return 销售信息集合
	 * @throws DataAccessException
	 */
	public List<ZoneSale> listZoneSaleByFirstCharAndZoneIdQg(@Param("zoneId")Integer zoneId, @Param("firstChar")String firstChar)throws DataAccessException;

	/**
	 * <p>Description: 根据专区ID查询销售信息</p>
	 * @param zoneId 专区ID
	 * @return 销售记录
	 */
	public Integer getZoneSaleCountByZoneId(Integer zoneId)throws DataAccessException;

	
	/**
	 * <p>Description: 获取商品排序</p>
	 * @param productId 商品ID
	 * @param zoneId 专区ID
	 * @return  销售商品排序
	 * @throws DataAccessException
	 */
	public Integer getRankByProductIdAndZoneId(@Param("productId")Integer productId, @Param("zoneId")Integer zoneId)throws DataAccessException;
}
