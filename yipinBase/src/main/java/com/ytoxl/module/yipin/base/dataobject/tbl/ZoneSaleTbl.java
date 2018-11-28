/**
 * ZoneSaleTbl.java
 * Created at Jan 10, 2014
 * Created by kuiYang
 * Copyright (C) 2014 SHANGHAI YUANTONG XINLONG E-Business Co.,Ltd, All rights reserved.
 */
package com.ytoxl.module.yipin.base.dataobject.tbl;

import java.util.Date;

/**
 * <p>
 * ClassName: ZoneSaleTbl
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
public class ZoneSaleTbl {

	/**
	 * <p>
	 * Field zoneSaleId: 销售ID
	 * </p>
	 */
	protected Integer zoneSaleId;
	/**
	 * <p>
	 * Field zoneId: 销售类型
	 * </p>
	 */
	protected Integer zoneId;
	/**
	 * <p>
	 * Field productSkuId: 商品SKUID
	 * </p>
	 */
	protected Integer productSkuId;
	/**
	 * <p>
	 * Field rank: 排序
	 * </p>
	 */
	protected Integer rank;
	/**
	 * <p>
	 * Field saleBeginTime: 抢购开始时间
	 * </p>
	 */
	protected Date saleBeginTime;
	/**
	 * <p>
	 * Field saleEndTime: 抢购结束时间
	 * </p>
	 */
	protected Date saleEndTime;
	/**
	 * <p>
	 * Field createTime: 创建时间
	 * </p>
	 */
	protected Date createTime;
	/**
	 * <p>
	 * Field updateTime: 更新时间
	 * </p>
	 */
	protected Date updateTime;
	/**
	 * <p>
	 * Field productId: 商品ID
	 * </p>
	 */
	protected Integer productId;
	/**
	 * <p>
	 * Field creator: 创建人
	 * </p>
	 */
	protected Integer creator;
	/**
	 * <p>
	 * Field lastModifier: 最后更新人
	 * </p>
	 */
	protected Integer lastModifier;

	/**
	 * <p>
	 * Description: 销售ID
	 * </p>
	 * 
	 * @return the zoneSaleId
	 */
	public Integer getZoneSaleId() {
		return this.zoneSaleId;
	}

	/**
	 * <p>
	 * Description: 销售ID
	 * </p>
	 * 
	 * @param zoneSaleId
	 *            the zoneSaleId to set
	 */
	public void setZoneSaleId(Integer zoneSaleId) {
		this.zoneSaleId = zoneSaleId;
	}

	/**
	 * <p>
	 * Description: 专区类型
	 * </p>
	 * 
	 * @return the zoneId
	 */
	public Integer getZoneId() {
		return this.zoneId;
	}

	/**
	 * <p>
	 * Description: 专区类型
	 * </p>
	 * 
	 * @param zoneId
	 *            the zoneId to set
	 */
	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}

	/**
	 * <p>
	 * Description: 商品SKUID
	 * </p>
	 * 
	 * @return the productSkuId
	 */
	public Integer getProductSkuId() {
		return this.productSkuId;
	}

	/**
	 * <p>
	 * Description: 商品SKUID
	 * </p>
	 * 
	 * @param productSkuId
	 *            the productSkuId to set
	 */
	public void setProductSkuId(Integer productSkuId) {
		this.productSkuId = productSkuId;
	}

	/**
	 * <p>
	 * Description: 排序
	 * </p>
	 * 
	 * @return the rank
	 */
	public Integer getRank() {
		return this.rank;
	}

	/**
	 * <p>
	 * Description: 排序
	 * </p>
	 * 
	 * @param rank
	 *            the rank to set
	 */
	public void setRank(Integer rank) {
		this.rank = rank;
	}

	/**
	 * <p>
	 * Description: 抢购开始时间
	 * </p>
	 * 
	 * @return the saleBeginTime
	 */
	public Date getSaleBeginTime() {
		return this.saleBeginTime;
	}

	/**
	 * <p>
	 * Description: 抢购开始时间
	 * </p>
	 * 
	 * @param saleBeginTime
	 *            the saleBeginTime to set
	 */
	public void setSaleBeginTime(Date saleBeginTime) {
		this.saleBeginTime = saleBeginTime;
	}

	/**
	 * <p>
	 * Description: 抢购结束时间
	 * </p>
	 * 
	 * @return the saleEndTime
	 */
	public Date getSaleEndTime() {
		return this.saleEndTime;
	}

	/**
	 * <p>
	 * Description: 抢购结束时间
	 * </p>
	 * 
	 * @param saleEndTime
	 *            the saleEndTime to set
	 */
	public void setSaleEndTime(Date saleEndTime) {
		this.saleEndTime = saleEndTime;
	}

	/**
	 * <p>
	 * Description: 创建时间
	 * </p>
	 * 
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * <p>
	 * Description: 创建时间
	 * </p>
	 * 
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * <p>
	 * Description: 更新时间
	 * </p>
	 * 
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * <p>
	 * Description: 更新时间
	 * </p>
	 * 
	 * @param updateTime
	 *            the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * <p>
	 * Description: 商品ID
	 * </p>
	 * 
	 * @return the productId
	 */
	public Integer getProductId() {
		return this.productId;
	}

	/**
	 * <p>
	 * Description: 商品ID
	 * </p>
	 * 
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	/**
	 * <p>
	 * Description: 创建人
	 * </p>
	 * 
	 * @return the creator
	 */
	public Integer getCreator() {
		return this.creator;
	}

	/**
	 * <p>
	 * Description: 创建人
	 * </p>
	 * 
	 * @param creator
	 *            the creator to set
	 */
	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	/**
	 * <p>
	 * Description: 最后更新人
	 * </p>
	 * 
	 * @return the lastModifier
	 */
	public Integer getLastModifier() {
		return this.lastModifier;
	}

	/**
	 * <p>
	 * Description: 最后更新人
	 * </p>
	 * 
	 * @param lastModifier
	 *            the lastModifier to set
	 */
	public void setLastModifier(Integer lastModifier) {
		this.lastModifier = lastModifier;
	}
}
