package com.ytoxl.module.yipin.base.dataobject;

import com.ytoxl.module.yipin.base.dataobject.tbl.SaleProductTbl;

/**
 * @作者：罗典
 * @描述：销售产品
 * @时间：2013-12-09
 * */
public class SaleProduct extends SaleProductTbl {
	// 商品
	private Product product;
	// 所属卖家Id
	private UserInfo userInfo;
	
	protected Sale sale;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	
}
