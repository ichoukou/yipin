package com.ytoxl.module.yipin.base.dataobject.tbl;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author wangguoqing
 * 
 */
public class ProductSkuTbl {
	protected Integer productSkuId;
	protected Integer productId;
	protected BigDecimal unitPrice; // 售价
	protected BigDecimal marketPrice;// 市场价格
	protected Integer inventory;// 库存
	protected String skuCode; // sku编码
	protected String internationalCode; // 国际码
	protected Integer salesQuantity; // 销售 数量
	protected Short isDelete;
	protected Date createTime;// 创建时间
	protected Date updateTime;// 更新时间
	protected Short isDefault;//是否默认
	protected String sellerSkuCode;
	public Integer getProductSkuId() {
		return productSkuId;
	}

	public void setProductSkuId(Integer productSkuId) {
		this.productSkuId = productSkuId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getInternationalCode() {
		return internationalCode;
	}

	public void setInternationalCode(String internationalCode) {
		this.internationalCode = internationalCode;
	}

	public Integer getSalesQuantity() {
		return salesQuantity;
	}

	public void setSalesQuantity(Integer salesQuantity) {
		this.salesQuantity = salesQuantity;
	}

	public Short getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
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

	public Short getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Short isDefault) {
		this.isDefault = isDefault;
	}

	public String getSellerSkuCode() {
		return sellerSkuCode;
	}

	public void setSellerSkuCode(String sellerSkuCode) {
		this.sellerSkuCode = sellerSkuCode;
	}
 
}
