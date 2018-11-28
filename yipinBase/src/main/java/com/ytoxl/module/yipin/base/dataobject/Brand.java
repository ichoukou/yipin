/**
 * Brand.java
 * Created at Dec 4, 2013
 * Created by kuiYang
 * Copyright (C) 2013 SHANGHAI YUANTONG XINGLONG E-Business, All rights reserved.
 */
package com.ytoxl.module.yipin.base.dataobject;

import java.util.List;

import com.ytoxl.module.yipin.base.dataobject.tbl.BrandTbl;

/**
 * <p>
 * ClassName: Brand
 * </p>
 * <p>
 * Description: 品牌
 * </p>
 * <p>
 * Author: Kui.Yang
 * </p>
 * <p>
 * Date: Dec 4, 2013
 * </p>
 */
public class Brand extends BrandTbl {
	
	protected ProductCategory productCategory;
	protected List<Product> products;
	//记录该商家是否选择了该品牌
	protected Boolean isChecked;
	protected UserInfo userInfo;
	//作为界面div的id，用于分类动态生成品牌列表
	protected String firstLetter;
	protected String productCategoryName;
	
	//品牌介绍  是否有历史的热卖记录
	protected Boolean isHistoryRecord;
	//是否显示在首页
	protected boolean isCheck = false;
	protected City city;
	
	public static final Short ISFORBIDDEN_STATUS= 0;
	
    public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	/**
     * <p>
     * Field ISFORBIDDEN_NO_FORBIDDEN: 启用
     * </p>
     */
    public static final Short ISFORBIDDEN_NO_FORBIDDEN = 0;

    /**
     * <p>
     * Field ISFORBIDDEN_FORBIDDEN: 禁用
     * </p>
     */
    public static final Short ISFORBIDDEN_FORBIDDEN = 1;

    /**
     * (non-Javadoc)
     * <p>
     * Title: toString
     * </p>
     * <p>
     * Description: 重写toString
     * </p>
     * 
     * @return 当前类toString返回值
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.brandId + "-->" + this.name;
    }

    /**
     * (non-Javadoc)
     * <p>
     * Title: hashCode
     * </p>
     * <p>
     * Description:重写hashCode
     * </p>
     * 
     * @return 当前类对象ID值
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return brandId;
    }

    /**
     * (non-Javadoc)
     * <p>
     * Title: equals
     * </p>
     * <p>
     * Description:重写equals
     * </p>
     * 
     * @param brand 参与比较的对象
     * @return
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object brand) {
        if (null == brand) {
            return false;
        }
        if (brand == this) {
            return true;
        }
        if (brand instanceof Brand) {
            return this.brandId.equals(((Brand) brand).brandId);
        }
        return false;
    }

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Boolean getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getFirstLetter() {
		return firstLetter;
	}

	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}

	public String getProductCategoryName() {
		return productCategoryName;
	}

	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}

	public Boolean getIsHistoryRecord() {
		return isHistoryRecord;
	}

	public void setIsHistoryRecord(Boolean isHistoryRecord) {
		this.isHistoryRecord = isHistoryRecord;
	}

	public boolean isCheck() {
		return isCheck;
	}

	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}

	public static Short getIsforbiddenNoForbidden() {
		return ISFORBIDDEN_NO_FORBIDDEN;
	}

	public static Short getIsforbiddenForbidden() {
		return ISFORBIDDEN_FORBIDDEN;
	}
    
}
