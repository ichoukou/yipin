/**
 * BrandTbl.java
 * Created at Dec 4, 2013
 * Created by kuiYang
 * Copyright (C) 2013 SHANGHAI YUANTONG XINGLONG E-Business, All rights reserved.
 */
package com.ytoxl.module.yipin.base.dataobject.tbl;

import java.util.Date;

import com.ytoxl.module.yipin.base.common.utils.DateUtil;

/**
 * <p>
 * ClassName: BrandTbl
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
public class BrandTbl {

    /**
     * <p>
     * Field brandId: 品牌ID
     * </p>
     */
    protected Integer brandId;
    
    /**
     * <p>Field cityId: 城市ID</p>
     */
    protected Integer cityId;

    /**
     * <p>
     * Field name: 品牌名称
     * </p>
     */
    protected String name;

    /**
     * <p>
     * Field englishName: 英文名称
     * </p>
     */
    protected String englishName;

    /**
     * <p>
     * Field firstChar: 名称首字符
     * </p>
     */
    protected String firstChar;

    /**
     * <p>
     * Field founder: 品牌创始人
     * </p>
     */
    protected String founder;

    /**
     * <p>
     * Field country: 所属国家
     * </p>
     */
    protected String country;

    /**
     * <p>
     * Field foundationTime: 品牌创建时间
     * </p>
     */
    protected Date foundationTime;

    /**
     * <p>
     * Field feature: 品牌特点
     * </p>
     */
    protected String feature;

//    /**
//     * <p>
//     * Field logoImageUrl: 品牌logo图
//     * </p>
//     */
//    protected String logoImageUrl;

    /**
     * <p>
     * Field brandImageUrl: 品牌形象图
     * </p>
     */
    protected String brandImageUrl;

    /**
     * <p>
     * Field describe: 品牌的描述
     * </p>
     */
    protected String description;

    /**
     * <p>
     * Field isForbidden: 是否禁用
     * </p>
     */
    protected Short isForbidden;
    /**
     * <p>
     * Field rank: 排序
     * </p>
     */
    protected Integer rank;

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
	protected Integer productCategoryId; // 品牌所属类目
	protected String brandPinYin;  //品牌名称拼音
	protected Short isHotSeller; //是否热卖品牌
	protected String logoImageUrl;
	protected String brandBannerImageUrl;
	protected String describe; // 品牌的描述

    /**
     * <p>
     * Description: 品牌ID
     * </p>
     * 
     * @return the brandId
     */
    public Integer getBrandId() {
        return this.brandId;
    }

    /**
     * <p>
     * Description: 品牌ID
     * </p>
     * 
     * @param brandId the brandId to set
     */
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    /**
     * <p>
     * Description: 品牌名称
     * </p>
     * 
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * <p>
     * Description: 品牌名称
     * </p>
     * 
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * <p>
     * Description: 英文名称
     * </p>
     * 
     * @return the englishName
     */
    public String getEnglishName() {
        return this.englishName;
    }

    /**
     * <p>
     * Description: 英文名称
     * </p>
     * 
     * @param englishName the englishName to set
     */
    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    /**
     * <p>
     * Description: 名称首字符
     * </p>
     * 
     * @return the firstChar
     */
    public String getFirstChar() {
        return this.firstChar;
    }

    /**
     * <p>
     * Description: 名称首字符
     * </p>
     * 
     * @param firstChar the firstChar to set
     */
    public void setFirstChar(String firstChar) {
        this.firstChar = firstChar;
    }

    /**
     * <p>
     * Description: 品牌创始人
     * </p>
     * 
     * @return the founder
     */
    public String getFounder() {
        return this.founder;
    }

    /**
     * <p>
     * Description: 品牌创始人
     * </p>
     * 
     * @param founder the founder to set
     */
    public void setFounder(String founder) {
        this.founder = founder;
    }

    /**
     * <p>
     * Description: 所属国家
     * </p>
     * 
     * @return the country
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * <p>
     * Description: 所属国家
     * </p>
     * 
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * <p>
     * Description: 品牌创建时间
     * </p>
     * 
     * @return the foundationTime
     */
    public Date getFoundationTime() {
        return this.foundationTime;
    }

    /**
     * <p>
     * Description: 品牌创建时间
     * </p>
     * 
     * @param foundationTime the foundationTime to set
     */
    public void setFoundationTime(String foundationTime) {
        if(foundationTime == null || "".equals(foundationTime)){
            this.foundationTime  = null;
        }else{
            this.foundationTime = DateUtil.valueof(foundationTime,"yyyy");
        }
    }

    /**
     * <p>
     * Description: 品牌特点
     * </p>
     * 
     * @return the feature
     */
    public String getFeature() {
        return this.feature;
    }

    /**
     * <p>
     * Description: 品牌特点
     * </p>
     * 
     * @param feature the feature to set
     */
    public void setFeature(String feature) {
        this.feature = feature;
    }

//    /**
//     * <p>
//     * Description: 品牌logo图
//     * </p>
//     * 
//     * @return the logoImageUrl
//     */
//    public String getLogoImageUrl() {
//        return this.logoImageUrl;
//    }
//
//    /**
//     * <p>
//     * Description: 品牌logo图
//     * </p>
//     * 
//     * @param logoImageUrl the logoImageUrl to set
//     */
//    public void setLogoImageUrl(String logoImageUrl) {
//        this.logoImageUrl = logoImageUrl;
//    }

    /**
     * <p>
     * Description: 品牌形象图
     * </p>
     * 
     * @return the brandImageUrl
     */
    public String getBrandImageUrl() {
        return this.brandImageUrl;
    }

    /**
     * <p>
     * Description: 品牌形象图
     * </p>
     * 
     * @param brandImageUrl the brandImageUrl to set
     */
    public void setBrandImageUrl(String brandImageUrl) {
        this.brandImageUrl = brandImageUrl;
    }


    /**
     * <p>Description:  品牌的描述 </p>
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * <p>Description:  品牌的描述 </p>
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * <p>
     * Description: 是否禁用
     * </p>
     * 
     * @return the isForbidden
     */
    public Short getIsForbidden() {
        return this.isForbidden;
    }

    /**
     * <p>
     * Description: 是否禁用
     * </p>
     * 
     * @param isForbidden the isForbidden to set
     */
    public void setIsForbidden(Short isForbidden) {
        this.isForbidden = isForbidden;
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
     * @param rank the rank to set
     */
    public void setRank(Integer rank) {
        this.rank = rank;
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
     * @param createTime the createTime to set
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
     * @param updateTime the updateTime to set
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public Integer getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(Integer productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public String getBrandPinYin() {
		return brandPinYin;
	}

	public void setBrandPinYin(String brandPinYin) {
		this.brandPinYin = brandPinYin;
	}

	public Short getIsHotSeller() {
		return isHotSeller;
	}

	public void setIsHotSeller(Short isHotSeller) {
		this.isHotSeller = isHotSeller;
	}

	public String getLogoImageUrl() {
		return logoImageUrl;
	}

	public void setLogoImageUrl(String logoImageUrl) {
		this.logoImageUrl = logoImageUrl;
	}

	public String getBrandBannerImageUrl() {
		return brandBannerImageUrl;
	}

	public void setBrandBannerImageUrl(String brandBannerImageUrl) {
		this.brandBannerImageUrl = brandBannerImageUrl;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public void setFoundationTime(Date foundationTime) {
		this.foundationTime = foundationTime;
	}
	
    /**
     * <p>Description: 城市ID</p>
     * @return the cityId
     */
    public Integer getCityId() {
        return this.cityId;
    }

    /**
     * <p>Description: 城市ID</p>
     * @param cityId the cityId to set
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
    
}
