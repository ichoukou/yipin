/**
 * ZoneTbl.java
 * Created at Jan 10, 2014
 * Created by kuiYang
 * Copyright (C) 2014 SHANGHAI YUANTONG XINLONG E-Business Co.,Ltd, All rights reserved.
 */
package com.ytoxl.module.yipin.base.dataobject.tbl;

import java.util.Date;

import com.ytoxl.module.yipin.base.common.utils.DateUtil;

/**
 * <p>
 * ClassName: ZoneTbl
 * </p>
 * <p>
 * Description: 专区类型
 * </p>
 * <p>
 * Author: Kui.Yang
 * </p>
 * <p>
 * Date: Jan 10, 2014
 * </p>
 */
public class ZoneTbl {

	/**
	 * <p>
	 * Field zoneId: 类型唯一标识ID
	 * </p>
	 */
	protected Integer zoneId;
	/**
	 * <p>
	 * Field name: 类型名称
	 * </p>
	 */
	protected String name;
	/**
	 * <p>
	 * Field zoneType: 类型
	 * </p>
	 */
	protected Short zoneType;
	/**
	 * <p>
	 * Field rank: 排序
	 * </p>
	 */
	protected Integer rank;
	/**
	 * <p>
	 * Field status: 状态
	 * </p>
	 */
	protected Short status;
	/**
	 * <p>
	 * Field createTime: 创建时间
	 * </p>
	 */
	protected Date createTime;
	/**
	 * <p>
	 * Field updateTime: 修改时间
	 * </p>
	 */
	protected Date updateTime;
	/**
	 * <p>
	 * Field creator: 创建人
	 * </p>
	 */
	protected Integer creator;
	/**
	 * <p>
	 * Field lastModifier: 最后修改人
	 * </p>
	 */
	protected Integer lastModifier;
	/**
	 * <p>
	 * Field lineNo: 显示行数编号
	 * </p>
	 */
	protected Short lineNo;

	/**
	 * <p>
	 * Description: 显示行数编号
	 * </p>
	 * 
	 * @return the lineNo
	 */
	public Short getLineNo() {
		return this.lineNo;
	}

	/**
	 * <p>
	 * Description: 显示行数编号
	 * </p>
	 * 
	 * @param lineNo
	 *            the lineNo to set
	 */
	public void setLineNo(Short lineNo) {
		this.lineNo = lineNo;
	}

	/**
	 * <p>
	 * Description: 类型唯一标识ID
	 * </p>
	 * 
	 * @return the zoneId
	 */
	public Integer getZoneId() {
		return this.zoneId;
	}

	/**
	 * <p>
	 * Description: 类型唯一标识ID
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
	 * Description: 类型名称
	 * </p>
	 * 
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * <p>
	 * Description: 类型名称
	 * </p>
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * <p>
	 * Description: 类型
	 * </p>
	 * 
	 * @return the zoneType
	 */
	public Short getZoneType() {
		return this.zoneType;
	}

	/**
	 * <p>
	 * Description: 类型
	 * </p>
	 * 
	 * @param zoneType
	 *            the zoneType to set
	 */
	public void setZoneType(Short zoneType) {
		this.zoneType = zoneType;
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
	 * Description: 状态
	 * </p>
	 * 
	 * @return the status
	 */
	public Short getStatus() {
		return this.status;
	}

	/**
	 * <p>
	 * Description: 状态
	 * </p>
	 * 
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Short status) {
		this.status = status;
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
	public void setCreateTime(String createTime) {
		// this.createTime = createTime;
		if (createTime == null || "".equals(createTime)) {
			this.createTime = null;
		} else {
			this.createTime = DateUtil.valueof(createTime, "yyyy-MM-dd");
		}
	}

	/**
	 * <p>
	 * Description: 修改时间
	 * </p>
	 * 
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * <p>
	 * Description: 修改时间
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
	 * Description: 最后修改人
	 * </p>
	 * 
	 * @return the lastModifier
	 */
	public Integer getLastModifier() {
		return this.lastModifier;
	}

	/**
	 * <p>
	 * Description: 最后修改人
	 * </p>
	 * 
	 * @param lastModifier
	 *            the lastModifier to set
	 */
	public void setLastModifier(Integer lastModifier) {
		this.lastModifier = lastModifier;
	}

}
