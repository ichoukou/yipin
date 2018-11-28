/**
 * ZoneAction.java
 * Created at Jan 9, 2014
 * Created by kuiYang
 * Copyright (C) 2014 SHANGHAI YUANTONG XINLONG E-Business Co.,Ltd, All rights reserved.
 */
package com.ytoxl.yipin.web.action.zone;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.ytoxl.module.core.common.utils.DateUtil;
import com.ytoxl.module.core.common.utils.StringUtils;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Zone;
import com.ytoxl.module.yipin.base.dataobject.ZoneSale;
import com.ytoxl.module.yipin.base.service.ZoneService;
import com.ytoxl.yipin.web.action.BaseAction;
import com.ytoxl.yipin.web.action.brand.BrandAction;

/**
 * <p>
 * ClassName: ZoneAction
 * </p>
 * <p>
 * Description: 专区
 * </p>
 * <p>
 * Author: Kui.Yang
 * </p>
 * <p>
 * Date: Jan 9, 2014
 * </p>
 */
public class ZoneAction extends BaseAction {

	/**
	 * <p>
	 * Field serialVersionUID: 序列号
	 * </p>
	 */
	private static final long serialVersionUID = 2588689735015048667L;
	/**
	 * <p>
	 * Field logger: 日志
	 * </p>
	 */
	private static Logger logger = LoggerFactory.getLogger(BrandAction.class);

	/**
	 * <p>
	 * Field SALEZONE: 预售
	 * </p>
	 */
	private static final String SALEZONE = "salezone";

	/**
	 * <p>
	 * Field SPECIALZONE: 抢购
	 * </p>
	 */
	private static final String SPECIALZONE = "specialzone";

	/**
	 * <p>
	 * Field DETAILZONE: 专区
	 * </p>
	 */
	private static final String DETAILZONE = "detailzone";

	/**
	 * <p>
	 * Field LISTZONE: 专区列表
	 * </p>
	 */
	private static final String LISTZONE = "listzone";
	
	/**
	 * <p>Field SEAZONE: 专区详情页</p>
	 */
	private static final String SEAZONE = "seazone";

	/**
	 * <p>
	 * Field zone: 专区数据传输对象
	 * </p>
	 */
	private Zone zone;

	/**
	 * <p>
	 * Field zoneList: 专区信息列表
	 * </p>
	 */
	private List<Zone> zoneList;

	/**
	 * <p>
	 * Field productId: 商品ID
	 * </p>
	 */
	private List<String> productId;
	/**
	 * <p>
	 * Field rankId: 权重ID
	 * </p>
	 */
	private List<String> rankId;
	/**
	 * <p>
	 * Field zsId: 品牌销售ID集合
	 * </p>
	 */
	private List<String> zsId;

	/**
	 * <p>
	 * Field beginTime: 抢购开始时间
	 * </p>
	 */
	private List<String> beginTime;
	/**
	 * <p>
	 * Field endTime: 抢购结束时间
	 * </p>
	 */
	private List<String> endTime;

	/**
	 * <p>
	 * Field skuId: 商品skuId
	 * </p>
	 */
	private List<String> skuId;

	/**
	 * <p>
	 * Field zoneService: 专区
	 * </p>
	 */
	@Autowired
	private ZoneService zoneService;

	/**
	 * <p>
	 * Description: 专区列表
	 * </p>
	 * 
	 * @return LISTZONE
	 */
	public String searchZone() {
		try {
			zoneList = this.zoneService.listZones();
		} catch (YiPinStoreException e) {
			logger.error("zone query error!", e.getMessage());
		}
		return LISTZONE;
	}

	/**
	 * <p>
	 * Description: 保存专区
	 * </p>
	 * 
	 * @return JSONMSG
	 */
	public String saveZone() {
		try {
			// 设置销售商品
			this.zone.setZoneSaleList(getZoneSales());
			this.zoneService.saveZone(this.zone);
			// 添加成功
			setMessage(Boolean.TRUE.toString(), "2");
		} catch (YiPinStoreException e) {
			logger.error("zone save error!", e.getMessage());
			// 添加失败
			setMessage(Boolean.TRUE.toString(), "1");
		}
		return JSONMSG;
	}

	/**
	 * <p>
	 * Description: 专区排序
	 * </p>
	 * 
	 * @return JSONMSG
	 */
	public String doSort() {
		try {
			this.zoneService.updateSortZoneRank(this.zone);
			// 修改排序成功
			setMessage(Boolean.TRUE.toString(), "2");
		} catch (YiPinStoreException e) {
			logger.error("zone release error!", e.getMessage());
			// 修改排序失败
			setMessage(Boolean.TRUE.toString(), "1");
		}
		return JSONMSG;
	}
	
	/**
	 * <p>
	 * Description: 专区排数
	 * </p>
	 * 
	 * @return JSONMSG
	 */
	public String doModifyLineNo() {
		try {
			this.zoneService.updateLineNo(this.zone);
			// 修改排序成功
			setMessage(Boolean.TRUE.toString(), "2");
		} catch (YiPinStoreException e) {
			logger.error("zone release error!", e.getMessage());
			// 修改排序失败
			setMessage(Boolean.TRUE.toString(), "1");
		}
		return JSONMSG;
	}

	/**
	 * <p>
	 * Description: 发布专区
	 * </p>
	 * 
	 * @return JSONMSG
	 */
	public String doRelease() {
		try {
			// 设置状态为发布
			this.zone.setStatus(Zone.ZONE_STATUS_RELEASE);
			this.zoneService.updateZoneStatus(this.zone);
			// 发布成功
			setMessage(Boolean.TRUE.toString(), "2");
		} catch (YiPinStoreException e) {
			logger.error("zone release error!", e.getMessage());
			// 发布失败
			setMessage(Boolean.TRUE.toString(), "1");
		}
		return JSONMSG;
	}

	/**
	 * <p>
	 * Description: 下架专区
	 * </p>
	 * 
	 * @return JSONMSG
	 */
	public String doOff() {
		try {
			// 设置状态为下架
			this.zone.setStatus(Zone.ZONE_STATUS_NO);
			this.zoneService.updateZoneStatus(this.zone);
			// 下架成功
			setMessage(Boolean.TRUE.toString(), "2");
		} catch (YiPinStoreException e) {
			logger.error("zone off error!", e.getMessage());
			// 下架失败
			setMessage(Boolean.TRUE.toString(), "1");
		}
		return JSONMSG;
	}

	/**
	 * <p>
	 * Description: 专区编辑页面
	 * </p>
	 * 
	 * @return 编辑页
	 */
	public String showUpdateZone() {
		String targetUrl = null;
		try {
			this.zone = this.zoneService.getZoneByZoneId(this.zone.getZoneId());
			if (Zone.ZONE_TYPE_DEFAULT.equals(this.zone.getZoneType())) {
				targetUrl = "targetdefault";
			} else if (Zone.ZONE_TYPE_SALE.equals(this.zone.getZoneType())) {
				targetUrl = "targetsale";
			} else if (Zone.ZONE_TYPE_SPECIAL.equals(this.zone.getZoneType())) {
				targetUrl = "targetspecial";
			}
		} catch (YiPinStoreException e) {
			logger.error("zone show error", e.getMessage());
		}
		return targetUrl;
	}

	/**
	 * <p>
	 * Description: 预售专区
	 * </p>
	 * 
	 * @return SALEZONE
	 */
	public String showSaleZone() {
		try {
			this.zone = this.zoneService.getZoneDetail(this.zone,
					Zone.ZONE_TYPE_SALE);
		} catch (Exception e) {
			logger.error("zone show error", e.getMessage());
		}
		return SALEZONE;
	}

	/**
	 * <p>
	 * Description: 抢购专区
	 * </p>
	 * 
	 * @return SPECIALZONE
	 */
	public String showSpecialZone() {
		try {
			this.zone = this.zoneService.getZoneDetail(this.zone,
					Zone.ZONE_TYPE_SPECIAL);
		} catch (Exception e) {
			logger.error("zone show error", e.getMessage());
		}
		return SPECIALZONE;
	}

	/**
	 * <p>
	 * Description: 专区
	 * </p>
	 * 
	 * @return DETAILZONE
	 */
	public String showDetailZone() {
		try {
			this.zone = this.zoneService.getZoneDetail(this.zone,
					Zone.ZONE_TYPE_DEFAULT);
		} catch (Exception e) {
			logger.error("zone show error", e.getMessage());
		}
		return DETAILZONE;
	}
	
	/**
	 * <p>
	 * Description: 根据专区ID获取专区
	 * </p>
	 * 
	 * @return DETAILZONE
	 */
	public String showZone() {
		try {
			this.zone = this.zoneService.getZoneByZoneId(this.zone.getZoneId());
		} catch (Exception e) {
			logger.error("zone show error", e.getMessage());
		}
		return SEAZONE;
	}
		

	/**
	 * <p>
	 * Description: 设置商品详情
	 * </p>
	 * 
	 * @return 专区商品详情
	 */
	private List<ZoneSale> getZoneSales() {
		List<ZoneSale>  zoneSaleList = new ArrayList<ZoneSale>();
		if (this.productId != null && this.productId.size() > 0) {
			 zoneSaleList.addAll(getProducts());
		} 
		if(this.skuId != null && this.skuId.size() > 0){
			zoneSaleList.addAll(getSkus());
		}
		return  zoneSaleList;
		
	}

	/**
	 * <p>
	 * Description: 获取商品信息
	 * </p>
	 * 
	 * @return
	 */
	private List<ZoneSale> getProducts() {
		List<ZoneSale> zoneSales = new ArrayList<ZoneSale>();
		for (int i = 0; i < this.productId.size(); i++) {
			ZoneSale zoneSale = new ZoneSale();
			// 商品ID
			String pid = this.productId.get(i);
			if (StringUtils.isNotBlank(pid) && isNumeric(pid)) {
				zoneSale.setProductId(new Integer(pid));
			}
			// 权重排序
			if (rankId != null && rankId.size() > 0) {
				String rid = this.rankId.get(i);
				if (StringUtils.isNotBlank(rid) && isNumeric(rid)) {
					zoneSale.setRank(new Integer(rid));
				}
			}
			if (this.zsId != null && this.zsId.size() > 0) {
				// 销售商品ID
				String zid = this.zsId.get(i);
				if (StringUtils.isNotBlank(zid) && isNumeric(zid)) {
					zoneSale.setZoneSaleId(new Integer(zid));
				}
			}
			zoneSales.add(zoneSale);
		}
		return zoneSales;
	}

	/**
	 * <p>
	 * Description: 获取商品SKU信息
	 * </p>
	 * 
	 * @return
	 */
	private List<ZoneSale> getSkus() {
		List<ZoneSale> zoneSales = new ArrayList<ZoneSale>();
		for (int i = 0; i < this.skuId.size(); i++) {
			ZoneSale zoneSale = new ZoneSale();
			// 商品SkuID
			String sid = this.skuId.get(i);
			if (StringUtils.isNotBlank(sid) && isNumeric(sid)) {
				zoneSale.setProductSkuId(new Integer(sid));
			}

			// 抢购开始时间
			if (beginTime != null && beginTime.size() > 0) {
				String btime = this.beginTime.get(i);
				if (StringUtils.isNotBlank(btime)) {
					zoneSale.setSaleBeginTime(DateUtil.valueof(btime,
							"yyyy-MM-dd hh:mm:ss"));
				}
			}
			// 抢购结束时间
			if (endTime != null && endTime.size() > 0) {
				String etime = this.endTime.get(i);
				if (StringUtils.isNotBlank(etime)) {
					zoneSale.setSaleEndTime(DateUtil.valueof(etime,
							"yyyy-MM-dd hh:mm:ss"));
				}
			}
			if (this.zsId != null && this.zsId.size() > 0) {
				// 销售商品ID
				String zid = this.zsId.get(i);
				if (StringUtils.isNotBlank(zid) && isNumeric(zid)) {
					zoneSale.setZoneSaleId(new Integer(zid));
				}
			}
			// 权重排序
			if (rankId != null && rankId.size() > 0) {
				String rid = this.rankId.get(i);
				if (StringUtils.isNotBlank(rid) && isNumeric(rid)) {
					zoneSale.setRank(new Integer(rid));
				}
			}
			zoneSales.add(zoneSale);
		}
		return zoneSales;
	}

	/**
	 * <p>
	 * Description: 数字判断
	 * </p>
	 * 
	 * @param 字符串
	 * @return boolean
	 */
	private boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * <p>
	 * Description: 专区数据传输对象
	 * </p>
	 * 
	 * @return the zone
	 */
	public Zone getZone() {
		return this.zone;
	}

	/**
	 * <p>
	 * Description: 专区数据传输对象
	 * </p>
	 * 
	 * @param zone
	 *            the zone to set
	 */
	public void setZone(Zone zone) {
		this.zone = zone;
	}

	/**
	 * <p>
	 * Description: 专区信息列表
	 * </p>
	 * 
	 * @return the zoneList
	 */
	public List<Zone> getZoneList() {
		return this.zoneList;
	}

	/**
	 * <p>
	 * Description: 专区信息列表
	 * </p>
	 * 
	 * @param zoneList
	 *            the zoneList to set
	 */
	public void setZoneList(List<Zone> zoneList) {
		this.zoneList = zoneList;
	}

	/**
	 * <p>
	 * Description: 商品ID集合
	 * </p>
	 * 
	 * @return the productId
	 */
	public List<String> getProductId() {
		return this.productId;
	}

	/**
	 * <p>
	 * Description: 商品ID集合
	 * </p>
	 * 
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(List<String> productId) {
		this.productId = productId;
	}

	/**
	 * <p>
	 * Description: 权重ID集合
	 * </p>
	 * 
	 * @return the rankId
	 */
	public List<String> getRankId() {
		return this.rankId;
	}

	/**
	 * <p>
	 * Description: 权重ID集合
	 * </p>
	 * 
	 * @param rankId
	 *            the rankId to set
	 */
	public void setRankId(List<String> rankId) {
		this.rankId = rankId;
	}

	/**
	 * <p>
	 * Description: 商品skuId
	 * </p>
	 * 
	 * @return the skuId
	 */
	public List<String> getSkuId() {
		return this.skuId;
	}

	/**
	 * <p>
	 * Description: 商品skuId
	 * </p>
	 * 
	 * @param skuId
	 *            the skuId to set
	 */
	public void setSkuId(List<String> skuId) {
		this.skuId = skuId;
	}

	/**
	 * <p>
	 * Description: 抢购开始时间
	 * </p>
	 * 
	 * @return the beginTime
	 */
	public List<String> getBeginTime() {
		return this.beginTime;
	}

	/**
	 * <p>
	 * Description: 抢购开始时间
	 * </p>
	 * 
	 * @param beginTime
	 *            the beginTime to set
	 */
	public void setBeginTime(List<String> beginTime) {
		this.beginTime = beginTime;
	}

	/**
	 * <p>
	 * Description: 抢购结束时间
	 * </p>
	 * 
	 * @return the endTime
	 */
	public List<String> getEndTime() {
		return this.endTime;
	}

	/**
	 * <p>
	 * Description: 抢购结束时间
	 * </p>
	 * 
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(List<String> endTime) {
		this.endTime = endTime;
	}

	/**
	 * <p>
	 * Description: 品牌销售ID集合
	 * </p>
	 * 
	 * @return the zsId
	 */
	public List<String> getZsId() {
		return this.zsId;
	}

	/**
	 * <p>
	 * Description: 品牌销售ID集合
	 * </p>
	 * 
	 * @param zsId
	 *            the zsId to set
	 */
	public void setZsId(List<String> zsId) {
		this.zsId = zsId;
	}

}