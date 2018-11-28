/**
 * Zone.java
 * Created at Jan 10, 2014
 * Created by kuiYang
 * Copyright (C) 2014 SHANGHAI YUANTONG XINLONG E-Business Co.,Ltd, All rights reserved.
 */
package com.ytoxl.module.yipin.base.dataobject;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;


import com.ytoxl.module.yipin.base.dataobject.tbl.ZoneTbl;

/**
 * <p>
 * ClassName: Zone
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
public class Zone extends ZoneTbl {

	/**
	 * <p>
	 * Field ZONE_STATUS_OFF: 未发布
	 * </p>
	 */
	public static final Short ZONE_STATUS_OFF = 0;
	/**
	 * <p>
	 * Field ZONE_STATUS_ON: 已发布
	 * </p>
	 */
	public static final Short ZONE_STATUS_RELEASE = 1;
	/**
	 * <p>
	 * Field ZONE_STATUS_NO: 下架
	 * </p>
	 */
	public static final Short ZONE_STATUS_NO = 2;
	/**
	 * <p>
	 * Field ZONE_TYPE_SPECIAL: 抢购
	 * </p>
	 */
	public static final Short ZONE_TYPE_SPECIAL = 1;
	/**
	 * <p>
	 * Field ZONE_TYPE_SALE: 预售
	 * </p>
	 */
	public static final Short ZONE_TYPE_SALE = 2;
	/**
	 * <p>
	 * Field ZONE_TYPE_DEFAULT: 普通
	 * </p>
	 */
	public static final Short ZONE_TYPE_DEFAULT = 3;

	/**
	 * <p>
	 * Field zoneSaleList: 专区销售商品集合
	 * </p>
	 */
	private List<ZoneSale> zoneSaleList;
	
	/**
	 * <p>Field brandFirstChar: 品牌首字符</p>
	 */
	private String brandFirstChar;

	/**
	 * <p>
	 * Field typeNameString: 类型字符名称
	 * </p>
	 */
	private String typeNameString;
	/**
	 * <p>
	 * Field stateNameString: 状态字符名称
	 * </p>
	 */
	private String stateNameString;
	
	private List<Product> products;
	private List<ProductSku> productSkus;
	/**
	 * <p>
	 * Field stateNameString: 状态字符名称
	 * </p>
	 */
	private Integer count;
	
	
	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<ProductSku> getProductSkus() {
		return productSkus;
	}

	public void setProductSkus(List<ProductSku> productSkus) {
		this.productSkus = productSkus;
	}

	/**
	 * <p>
	 * Description: 专区销售商品集合
	 * </p>
	 * 
	 * @return the zoneSaleList
	 */
	public List<ZoneSale> getZoneSaleList() {
		return this.zoneSaleList;
	}

	/**
	 * <p>
	 * Description: 专区销售商品集合
	 * </p>
	 * 
	 * @param zoneSaleList
	 *            the zoneSaleList to set
	 */
	public void setZoneSaleList(List<ZoneSale> zoneSaleList) {
		this.zoneSaleList = zoneSaleList;
	}

	/**
	 * <p>
	 * Description: 类型字符名称
	 * </p>
	 * 
	 * @return the typeNameString
	 */
	public String getTypeNameString() {
		return this.typeNameString;
	}

	/**
	 * <p>
	 * Description: 类型字符名称
	 * </p>
	 * 
	 * @param typeNameString
	 *            the typeNameString to set
	 */
	public void setTypeNameString(String typeNameString) {
		this.typeNameString = typeNameString;
	}

	/**
	 * <p>
	 * Description: 状态字符名称
	 * </p>
	 * 
	 * @return the stateNameString
	 */
	public String getStateNameString() {
		return this.stateNameString;
	}

	/**
	 * <p>
	 * Description: 状态字符名称
	 * </p>
	 * 
	 * @param stateNameString
	 *            the stateNameString to set
	 */
	public void setStateNameString(String stateNameString) {
		this.stateNameString = stateNameString;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	

	/**
	 * <p>Description: 品牌首字符</p>
	 * @return the brandFirstChar
	 */
	public String getBrandFirstChar() {
		return this.brandFirstChar;
	}

	/**
	 * <p>Description: 品牌首字符</p>
	 * @param brandFirstChar the brandFirstChar to set
	 */
	public void setBrandFirstChar(String brandFirstChar) {
		this.brandFirstChar = brandFirstChar;
	}

}
