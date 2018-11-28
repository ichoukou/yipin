package com.ytoxl.module.yipin.content.dataobject.tbl;

import java.util.Date;

/**
 * @作者：罗典
 * @描述：友情链接实体
 * @时间：2013-12-03
 * */
public class LinksTbl {
	// 主键ID
	private int linkId;
	// 网站名称
	private String name;
	// 网站地址
	private String linkUrl;
	// 排序
	private int sort;
	// 创建时间
	private Date createTime;
	// 修改时间
	private Date updateTime;

	public int getLinkId() {
		return linkId;
	}

	public void setLinkId(int linkId) {
		this.linkId = linkId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

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

}
