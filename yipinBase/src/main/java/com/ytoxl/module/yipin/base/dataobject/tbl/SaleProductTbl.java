package com.ytoxl.module.yipin.base.dataobject.tbl;

import java.util.Date;

/**
 * @作者：罗典
 * @描述：销售产品
 * @时间：2013-12-09
 * */
public class SaleProductTbl {
	// ID
	private Integer saleProductId;
	// 销售ID
	private int saleId;
	// 商品ID
	private int productId;
	// 排序
	private Integer rank;
	// 创建时间
	private Date createTime;
	// 更新时间
	private Date updateTime;

	public Integer getSaleProductId() {
		return saleProductId;
	}

	public void setSaleProductId(Integer saleProductId) {
		this.saleProductId = saleProductId;
	}

	public int getSaleId() {
		return saleId;
	}

	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
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
