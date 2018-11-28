package com.ytoxl.module.yipin.base.dataobject.tbl;

import java.util.Date;

public class ProductPropTbl {
	protected Integer productPropid;
	protected Integer productId;
	protected Integer propId;
	protected Date createTime;
	protected Date updateTime;
	public Integer getProductPropid() {
		return productPropid;
	}
	public void setProductPropid(Integer productPropid) {
		this.productPropid = productPropid;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getPropId() {
		return propId;
	}
	public void setPropId(Integer propId) {
		this.propId = propId;
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
