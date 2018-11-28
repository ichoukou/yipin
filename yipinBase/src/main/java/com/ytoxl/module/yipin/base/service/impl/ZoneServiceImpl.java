/**
 * ZoneServiceImpl.java
 * Created at Jan 10, 2014
 * Created by kuiYang
 * Copyright (C) 2014 SHANGHAI YUANTONG XINLONG E-Business Co.,Ltd, All rights reserved.
 */
package com.ytoxl.module.yipin.base.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Brand;
import com.ytoxl.module.yipin.base.dataobject.Product;
import com.ytoxl.module.yipin.base.dataobject.Zone;
import com.ytoxl.module.yipin.base.dataobject.ZoneSale;
import com.ytoxl.module.yipin.base.mapper.BrandMapper;
import com.ytoxl.module.yipin.base.mapper.ZoneMapper;
import com.ytoxl.module.yipin.base.mapper.ZoneSaleMapper;
import com.ytoxl.module.yipin.base.service.ProductService;
import com.ytoxl.module.yipin.base.service.ZoneService;

/**
 * <p>
 * ClassName: ZoneServiceImpl
 * </p>
 * <p>
 * Description: 专区
 * </p>
 * <p>
 * Author: Kui.Yang
 * </p>
 * <p>
 * Date: Jan 10, 2014
 * </p>
 */
@Service
public class ZoneServiceImpl implements ZoneService {

	/**
	 * <p>
	 * Field zoneMapper: 专区
	 * </p>
	 */
	@Autowired
	private ZoneMapper<Zone> zoneMapper;

	/**
	 * <p>
	 * Field zoneSaleMapper: 专区销售
	 * </p>
	 */
	@Autowired
	private ZoneSaleMapper<ZoneSale> zoneSaleMapper;


	/**
	 * <p>
	 * Field productService: 商品
	 * </p>
	 */
	@Autowired
	private ProductService productService;
	@Value("${columnNo}")
	private Integer columnNo;

	/**
	 * <p>
	 * Field brandMapper: 品牌
	 * </p>
	 */
	@Autowired
	private BrandMapper<Brand> brandMapper;


	/**
	 * <p>
	 * Field logger: 日志
	 * </p>
	 */
	private static Logger logger = LoggerFactory.getLogger(ZoneServiceImpl.class);

	/**
	 * <p>
	 * Title: saveZone
	 * </p>
	 * <p>
	 * Description: 新增专区
	 * </p>
	 * 
	 * @param zone 专区信息
	 * @throws YiPinStoreException
	 * @see com.ytoxl.module.yipin.base.service.ZoneService#saveZone(com.ytoxl.module.yipin.base.dataobject.Zone)
	 */
	@Override
	public void saveZone(Zone zone) throws YiPinStoreException {
		logger.error("zone save begin!", "--------------");
		boolean flag = false; // 专区是否已存在标记
		Zone zoneObj = null; // 专区
		try {
			if (zone.getZoneId() != null) {
				// 修改专区
				this.zoneMapper.update(zone); 
				
				List<ZoneSale> zs = zone.getZoneSaleList();
											
				 // 销售信息集合
				List<ZoneSale> zoneSales = null; 
				
				if(!Zone.ZONE_TYPE_SPECIAL.equals(zone.getZoneType())){
					zoneSales = this.zoneSaleMapper.listZoneSaleByFirstCharAndZoneId(zone.getZoneId(),zone.getBrandFirstChar());
				}else{
					zoneSales = this.zoneSaleMapper.listZoneSaleByFirstCharAndZoneIdQg(zone.getZoneId(),zone.getBrandFirstChar());
				}
				
				Integer count = this.zoneSaleMapper.getZoneSaleCountByZoneId(zone.getZoneId());
				
				if(zs.size()<1 && count<=zoneSales.size()){
					throw new YiPinStoreException("请选择商品");
				}
				
				// 删除专区对应品牌所有在售信息
				if(zoneSales !=null){
					for(ZoneSale z : zoneSales){   
						this.zoneSaleMapper.removeByZoneSale(z); 
					}	
				}
				
				for (ZoneSale o : zs) {
					if (o != null) {
						o.setZoneId(zone.getZoneId());
						o.setZoneSaleId(null);
						// 添加专区销售信息
						this.zoneSaleMapper.add(o);
					}
				}
				
			} else {

				if (Zone.ZONE_TYPE_DEFAULT.equals(zone.getZoneType())) {
					Integer maxNum = 0;
					// 普通专区
					// 根据专区名称查询专区信息
					zoneObj = this.getZoneByName(zone.getName());
					maxNum = this.getCountByZoneType();
					if (maxNum >= 8) {
						logger.error("zone save error!", "专区已超出限定数量！");
						throw new YiPinStoreException("专区已超出限定数量");
					}
					if (zoneObj == null || zoneObj.getZoneId() == null) {
						flag = true;
					}

				} else {
					// 预售、抢购
					// 根据专区类型查询专区信息
					zoneObj = this.getZoneByZoneType(zone.getZoneType());
					if (zoneObj == null || zoneObj.getZoneId() == null) {
						flag = true;
					}
				}
				if (flag) {
					// 获取最大排序
					Integer rankNo = this.zoneMapper.getMaxRank();
					if (rankNo != null) {
						zone.setRank(++rankNo);
						if(++rankNo>10){
							zone.setRank(null);
						}
					} else {
						// 设置首次排序
						zone.setRank(1);
					}
					// 保存专区信息
					this.zoneMapper.add(zone);

					// 获取专区销售信息
					List<ZoneSale> zoneSaleList = zone.getZoneSaleList();

					if (zone.getZoneId() != null && zoneSaleList != null
							&& zoneSaleList.size() > 0) {
						for (ZoneSale o : zoneSaleList) {
							if (o != null) {
								o.setZoneId(zone.getZoneId());
								// 保存专区销售信息
								ZoneSale zs = this.zoneSaleMapper.getZoneSaleByZoneIdAndProductId(o);
								if(zs == null){
									this.zoneSaleMapper.add(o);
								}else{
									logger.error("zone save error!", "销售科目已存在！");
									throw new YiPinStoreException("销售科目存在！");
								}
							}
						}
					}
				} else {
					if (zoneObj.getZoneType().equals(Zone.ZONE_TYPE_DEFAULT)) {
						logger.error("zone save error!", "专区已存在！");
						throw new YiPinStoreException("专区已存在！");
					} else if (zoneObj.getZoneType().equals(Zone.ZONE_TYPE_SALE)) {
						logger.error("zone save error!", "预售专区已存在！只能存在一个预售专区！");
						throw new YiPinStoreException("预售专区已存在！只能存在一个预售专区！");
					} else if (zoneObj.getZoneType().equals(Zone.ZONE_TYPE_SPECIAL)) {
						logger.error("zone save error!", "抢购专区已存在！只能存在一个抢购专区！");
						throw new YiPinStoreException("抢购专区已存在！只能存在一个抢购专区！");
					}
				}
			}
		} catch (Exception e) {
			logger.error("zone save error!", e.getMessage());
			throw new YiPinStoreException("专区信息新增失败！");
		} finally {
			logger.error("zone save end!", "--------------");
		}
	}

	/**
	 * <p>
	 * Description: 查询对应类型专区总数量
	 * </p>
	 * 
	 * @param zoneType  专区类型
	 * @return 总数目
	 */
	private Integer getCountByZoneType() {
		return this.zoneMapper.getCountByZoneType();
	}

	/**
	 * <p>
	 * Title: updateZoneStatus
	 * </p>
	 * <p>
	 * Description: 修改专区状态
	 * </p>
	 * 
	 * @param zone 专区信息
	 * @throws YiPinStoreException
	 * @see com.ytoxl.module.yipin.base.service.ZoneService#updateZoneStatus(com.ytoxl.module.yipin.base.dataobject.Zone)
	 */
	@Override
	public void updateZoneStatus(Zone zone) throws YiPinStoreException {
		logger.error("zone update begin!", "--------------");
		try {
			this.zoneMapper.updateZoneStatus(zone);
		} catch (Exception e) {
			logger.error("zone update error!", e.getMessage());
			throw new YiPinStoreException("操作专区状态失败！");
		} finally {
			logger.error("zone update end!", "--------------");
		}
	}

	/**
	 * <p>
	 * Title: getZoneByZoneType
	 * </p>
	 * <p>
	 * Description: 根据类型获取专区信息
	 * </p>
	 * 
	 * @param typeName 类型名称
	 * @return Zone 专区信息
	 * @throws YiPinStoreException
	 * @see com.ytoxl.module.yipin.base.service.ZoneService#getZoneByZoneType(java.lang.Short)
	 */
	@Override
	public Zone getZoneByZoneType(Short typeName) throws YiPinStoreException {
		Zone zone = this.zoneMapper.getZoneByZoneType(typeName);
		convertCn(zone);
		return zone;
	}

	/**
	 * <p>
	 * Title: getZoneByName
	 * </p>
	 * <p>
	 * Description: 根据名称获取专区信息
	 * </p>
	 * 
	 * @param zoneName 名称
	 * @return Zone 专区信息
	 * @throws YiPinStoreException
	 * @see com.ytoxl.module.yipin.base.service.ZoneService#getZoneByName(java.lang.String)
	 */
	@Override
	public Zone getZoneByName(String zoneName) throws YiPinStoreException {
		Zone zone = this.zoneMapper.getZoneByName(zoneName);
		convertCn(zone);
		return zone;
	}

	/**
	 * 查询查询抢购专区信息
	 */
	@Override
	public Zone getZoneBySpecial() throws YiPinStoreException {
		// Zone zone = this.zoneMapper.getZoneListBySpecial();
		// List<ProductSku> productSkus =
		// productService.getProductByZoneSpecial();
		return null;
	}

	/**
	 * <p>
	 * Description: 查询专区列表
	 * </p>
	 * 
	 * @return 查询预售和运营专区信息 -
	 * @throws DataAccessException
	 */
	@Override
	public List<Zone> getZoneByPreAndDefault() throws YiPinStoreException {
		// List<Zone> zones = this.zoneMapper.getZoneListBypreAndDefault();
		// // List<Product> plist = productService.getProductByZoneId();
		// for(Zone z:zones){
		// List<Product> list = new ArrayList<Product>();
		// for(Product p:plist){
		// if(p != null && p.getZone() != null &&
		// p.getZone().getZoneId().equals(z.getZoneId())){
		// list.add(p);
		// }
		// }
		// z.setProducts(list);
		// }
		return null;
	}

	/**
	 * <p>
	 * Title: listZones
	 * </p>
	 * <p>
	 * Description: 查询专区信息列表
	 * </p>
	 * 
	 * @return 专区信息列表
	 * @throws YiPinStoreException
	 * @see com.ytoxl.module.yipin.base.service.ZoneService#listZones()
	 */
	@Override
	public List<Zone> listZones() throws YiPinStoreException {

		List<Zone> zoneArray = this.zoneMapper.getZoneList(); // 得到所有专区
		if (zoneArray != null && zoneArray.size() > 0) {
			for (Zone o : zoneArray) {
				convertCn(o);
				if (o != null && o.getZoneId() != null) {
					// 获取专区销售信息
					List<ZoneSale> zoneSales = this.zoneSaleMapper
							.listZoneSaleByZoneId(o.getZoneId());
					o.setZoneSaleList(zoneSales);
				}
			}
		}
		return zoneArray;
	}

	/**
	 * <p>
	 * Title: listAllZones
	 * </p>
	 * <p>
	 * Description: 首页所有查询专区信息列表
	 * </p>
	 * 
	 * @return 专区信息列表
	 * @throws YiPinStoreException
	 * @see com.ytoxl.module.yipin.base.service.ZoneService#listZones()
	 */
	@Override
	public List<Zone> listAllZones() throws YiPinStoreException {
		List<Zone> zoneArray = this.zoneMapper.getAllList(); // 得到所有专区
		for (Zone z : zoneArray) {
			Integer limit = z.getLineNo() * columnNo;
			List<Product> list = productService.getProductByZoneId(z, limit);
			for(Product p:list){
				Product pp = productService.getProductCatologOrArea(p.getProductId());
				p.setPropsMap(pp.getPropsMap());
			}
			
			z.setProducts(list);
		}
		return zoneArray;
	
	}

	/**
	 * <p>
	 * Title: getZoneDetail
	 * </p>
	 * <p>
	 * Description: 查询专区详情信息
	 * </p>
	 * 
	 * @param zone
	 * @param zoneTypeSale
	 * @return 专区信息
	 * @throws YiPinStoreException
	 * @see com.ytoxl.module.yipin.base.service.ZoneService#getZoneDetail(com.ytoxl.module.yipin.base.dataobject.Zone,
	 *      java.lang.Short)
	 */
	@Override
	public Zone getZoneDetail(Zone zone, Short zoneTypeSale)
			throws YiPinStoreException {
		Zone target = new Zone();
		// 设置专区信息类型
		target.setZoneType(zoneTypeSale);
		// 加载默认状态
		target.setStatus(Zone.ZONE_STATUS_OFF);
		if (zone != null && zone.getZoneId() != null) {
			// 获取专区类型
			target = this.getZoneByZoneId(zone.getZoneId());
		}
		convertCn(target);
		return target;
	}

	/**
	 * <p>
	 * Title: getZoneByZoneId
	 * </p>
	 * <p>
	 * Description: 根据ID获取专区信息
	 * </p>
	 * 
	 * @param zoneId 专区ID
	 * @return 专区信息
	 * @throws YiPinStoreException
	 * @see com.ytoxl.module.yipin.base.service.ZoneService#getZoneByZoneId(java.lang.Integer)
	 */
	@Override
	public Zone getZoneByZoneId(Integer zoneId) throws YiPinStoreException {
		// 根据ID获取专区
		Zone target = this.zoneMapper.get(zoneId);
	
		if (target != null) {
			// 获取专区销售信息
			List<ZoneSale> zonesales = this.zoneSaleMapper.listZoneSaleByZoneId(target.getZoneId());
			target.setZoneSaleList(zonesales);       
			
			if (zonesales != null && zonesales.size() > 0) {
				ZoneSale z = zonesales.get(0);
				if (z != null && z.getProductSkuId() != null) {
					// 设置专区展示品牌对应首字符
					target.setBrandFirstChar(this.brandMapper.getFirstCharByProductSkuId(z.getProductSkuId()));
				}
				if(z !=null && z.getProductId()!=null){
					// 设置专区展示品牌对应首字符
					target.setBrandFirstChar(this.brandMapper.getFirstCharByProductId(z.getProductId()));
				}
			}
		} else {
			logger.error("zone load error!", "专区信息不存在！");
			throw new YiPinStoreException("专区信息不存在！");
		}
	
		convertCn(target);
		return target;
	}

	/**
	 * <p>
	 * Title: updateSortZoneRank
	 * </p>
	 * <p>
	 * Description: 专区排序修改
	 * </p>
	 * 
	 * @param zone 专区信息
	 * @throws YiPinStoreException
	 * @see com.ytoxl.module.yipin.base.service.ZoneService#updateSortZoneRank(com.ytoxl.module.yipin.base.dataobject.Zone)
	 */
	@Override
	public void updateSortZoneRank(Zone zone) throws YiPinStoreException {
		Zone z = null;
		if (zone != null && zone.getRank() != null && zone.getZoneId() != null) {
			// 获取排序ID
			Integer rankId = zone.getRank();
			z = this.zoneMapper.getZoneByRank(rankId);
			if (z == null) {
				this.zoneMapper.updateZoneRank(zone);
			} else {
				throw new YiPinStoreException("排序号重复");
			}
		}
	}

	/**
	 * <p>Description: 设置字符中文,状态中文</p>
	 * @param zone 专区信息
	 */
	private void convertCn(Zone zone) {
		// 设置类型中文
		if (zone != null && zone.getZoneType() != null) {
			zone.setTypeNameString(getTypeString(zone.getZoneType()));
		}
		// 设置状态中文
		if (zone != null && zone.getStatus() != null) {
			zone.setStateNameString(getStatusString(zone.getStatus()));
		}
	}

	/**
	 * <p>
	 * Description: 设置状态中文名称
	 * </p>
	 */
	private String getStatusString(Short status) {
		String stateNameString = "";
		if (Zone.ZONE_STATUS_OFF.equals(status)) {
			stateNameString = "未发布";
		} else if (Zone.ZONE_STATUS_RELEASE.equals(status)) {
			stateNameString = "已发布";
		} else if (Zone.ZONE_STATUS_NO.equals(status)) {
			stateNameString = "已下架";
		}
		return stateNameString;
	}

	/**
	 * <p>
	 * Description: 设置类型中文名称
	 * </p>
	 */
	private String getTypeString(Short zoneType) {
		String typeNameString = "";
		if (Zone.ZONE_TYPE_DEFAULT.equals(zoneType)) {
			typeNameString = "专区";
		} else if (Zone.ZONE_TYPE_SALE.equals(zoneType)) {
			typeNameString = "预售";
		} else if (Zone.ZONE_TYPE_SPECIAL.equals(zoneType)) {
			typeNameString = "抢购";
		}
		return typeNameString;
	}

	/** 
	 * <p>Title: updateLineNo</p>
	 * <p>Description: 修改专区排数</p>
	 * @param zone
	 * @throws YiPinStoreException
	 * @see com.ytoxl.module.yipin.base.service.ZoneService#updateLineNo(com.ytoxl.module.yipin.base.dataobject.Zone)
	 */
	@Override
	public void updateLineNo(Zone zone) throws YiPinStoreException {
		if (zone != null && zone.getLineNo() != null && zone.getZoneId() != null) {
			this.zoneMapper.updateZoneLineNo(zone);
		} else {
			throw new YiPinStoreException("排数号错误");
		}
		
	}

	/** 
	 * <p>Title: getZoneSaleByProductSkuId</p>
	 * <p>Description: 根据SKUID查询专区销售信息</p>
	 * @param productSkuId 商品skuId
	 * @return 销售信息
	 * @throws YiPinStoreException
	 * @see com.ytoxl.module.yipin.base.service.ZoneService#getZoneSaleByProductSkuId(java.lang.Integer)
	 */
	@Override
	public ZoneSale getZoneSaleByProductSkuId(Integer productSkuId)
			throws YiPinStoreException {
		return this.zoneSaleMapper.getZoneByProductSku(productSkuId);
	}


}
