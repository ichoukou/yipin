package com.ytoxl.module.yipin.base.dataobject.tbl;

import java.util.Date;

/**
 * @作者：罗典
 * @时间：2014-01-10
 * @描述：层级分类属性实体
 * */
public class PropTbl {
	
	// ID
	private Integer propId;
	// 名称
	private String name;
	// 父节点ID
	private Integer parentId;
	// 所属编码(所有上级节点ID以‘-’拼接)
	private String code;
	// 级别(大区 1 ；省份 2；市级 3；区县 4)
	private Integer level;
	// 排序
	private Integer rank;
	// 状态(有效 1；无效2)
	private Integer status;
	// 创建时间
	private Date createTime;
	// 修改时间
	private Date updateTime;
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	
	public Integer getPropId() {
		return propId;
	}

	public void setPropId(Integer propId) {
		this.propId = propId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

}
