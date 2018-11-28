package com.ytoxl.module.yipin.base.dataobject.tbl;

import java.util.Date;


public class ProductTbl {
	protected Integer productId;// 商品id
	protected Integer brandId;// 商标id
	protected Integer userId;// 商家id
	protected String name;// 商品名称
	protected Short isShowInventory;// 前如页面是否显示库存：0=不显示，1=显示
	protected String imageUrls;
	protected String description;// 详细描述
	protected String keyWord;// 关键字
	protected Short status; // 状态：1=草稿，2=审核中，3=审核通过，4=审核不通过
	protected String remark; // 备注没有通过审核的原因
	protected Date createTime;// 创建时间
	protected Date updateTime;
	protected Short sellType;// 是否预售商品：1=非预售，2=预售
	protected Date preDeliveryTime;// 预计发货时间
	protected String recommendedReason;//推荐理由
	protected Integer hits;//点击量
	protected String productProp;//商品属性字符串
	protected String coverPicture;//商品封面图片
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getPreDeliveryTime() {
		return preDeliveryTime;
	}

	public void setPreDeliveryTime(Date preDeliveryTime) {
		this.preDeliveryTime = preDeliveryTime;
	}

	public Short getIsShowInventory() {
		return isShowInventory;
	}

	public void setIsShowInventory(Short isShowInventory) {
		this.isShowInventory = isShowInventory;
	}

	public String getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(String imageUrls) {
		this.imageUrls = imageUrls;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Short getSellType() {
		return sellType;
	}

	public void setSellType(Short sellType) {
		this.sellType = sellType;
	}

	public String getRecommendedReason() {
		return recommendedReason;
	}

	public void setRecommendedReason(String recommendedReason) {
		this.recommendedReason = recommendedReason;
	}

	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public String getProductProp() {
		return productProp;
	}

	public void setProductProp(String productProp) {
		this.productProp = productProp;
	}

	public String getCoverPicture() {
		return coverPicture;
	}

	public void setCoverPicture(String coverPicture) {
		this.coverPicture = coverPicture;
	}

}
