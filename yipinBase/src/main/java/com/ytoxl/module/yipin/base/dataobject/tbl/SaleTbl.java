package com.ytoxl.module.yipin.base.dataobject.tbl;

import java.util.Date;
import java.util.List;

import com.ytoxl.module.yipin.base.dataobject.Brand;
import com.ytoxl.module.yipin.base.dataobject.SaleProduct;

/**
 * @作者：罗典
 * @描述：销售信息实体
 * @时间：2013-12-06
 * */
public class SaleTbl {
	// id
	private Integer saleId;
	// 品牌ID
	private Integer brandId;
	// 预售时间
	private Date preSelltime;
	// 售卖方式：1=预售，2=销售
	private Short sellType;
	// 状态：1=未发布，2=预售中，3=销售中，4=下架(数据库中状态为：  1=未发布，2=已发布，4=下架)
	private Short status;
	// 创建时间
	private Date createtime;
	// 最后修改时间
	private Date updateTime;
	// 创建人ID
	private Integer creator;
	// 最后修改人ID
	private Integer lastModifier;
	public Integer getSaleId() {
		return saleId;
	}
	public void setSaleId(Integer saleId) {
		this.saleId = saleId;
	}
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public Date getPreSelltime() {
		return preSelltime;
	}
	public void setPreSelltime(Date preSelltime) {
		this.preSelltime = preSelltime;
	}
	public Short getSellType() {
		return sellType;
	}
	public void setSellType(Short sellType) {
		this.sellType = sellType;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	public Integer getLastModifier() {
		return lastModifier;
	}
	public void setLastModifier(Integer lastModifier) {
		this.lastModifier = lastModifier;
	}

	
}
